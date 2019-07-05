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

package org.opencps.communication.service.persistence.impl;

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
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.communication.exception.NoSuchNotificationtemplateException;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.model.impl.NotificationtemplateImpl;
import org.opencps.communication.model.impl.NotificationtemplateModelImpl;
import org.opencps.communication.service.persistence.NotificationtemplatePersistence;

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
 * The persistence implementation for the notificationtemplate service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see NotificationtemplatePersistence
 * @see org.opencps.communication.service.persistence.NotificationtemplateUtil
 * @generated
 */
@ProviderType
public class NotificationtemplatePersistenceImpl extends BasePersistenceImpl<Notificationtemplate>
	implements NotificationtemplatePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NotificationtemplateUtil} to access the notificationtemplate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NotificationtemplateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED,
			NotificationtemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED,
			NotificationtemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_F_NOTIFICATIONTEMPLATEBYTYPE =
		new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED,
			NotificationtemplateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_NotificationtemplateByType",
			new String[] { Long.class.getName(), String.class.getName() },
			NotificationtemplateModelImpl.GROUPID_COLUMN_BITMASK |
			NotificationtemplateModelImpl.NOTIFICATIONTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEBYTYPE =
		new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_NotificationtemplateByType",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; or throws a {@link NoSuchNotificationtemplateException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @return the matching notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate findByF_NotificationtemplateByType(
		long groupId, String notificationType)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = fetchByF_NotificationtemplateByType(groupId,
				notificationType);

		if (notificationtemplate == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", notificationType=");
			msg.append(notificationType);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchNotificationtemplateException(msg.toString());
		}

		return notificationtemplate;
	}

	/**
	 * Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @return the matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate fetchByF_NotificationtemplateByType(
		long groupId, String notificationType) {
		return fetchByF_NotificationtemplateByType(groupId, notificationType,
			true);
	}

	/**
	 * Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate fetchByF_NotificationtemplateByType(
		long groupId, String notificationType, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, notificationType };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_NOTIFICATIONTEMPLATEBYTYPE,
					finderArgs, this);
		}

		if (result instanceof Notificationtemplate) {
			Notificationtemplate notificationtemplate = (Notificationtemplate)result;

			if ((groupId != notificationtemplate.getGroupId()) ||
					!Objects.equals(notificationType,
						notificationtemplate.getNotificationType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_NOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYTYPE_GROUPID_2);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYTYPE_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals("")) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYTYPE_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYTYPE_NOTIFICATIONTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindNotificationType) {
					qPos.add(notificationType);
				}

				List<Notificationtemplate> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_NOTIFICATIONTEMPLATEBYTYPE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"NotificationtemplatePersistenceImpl.fetchByF_NotificationtemplateByType(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Notificationtemplate notificationtemplate = list.get(0);

					result = notificationtemplate;

					cacheResult(notificationtemplate);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_NOTIFICATIONTEMPLATEBYTYPE,
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
			return (Notificationtemplate)result;
		}
	}

	/**
	 * Removes the notificationtemplate where groupId = &#63; and notificationType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @return the notificationtemplate that was removed
	 */
	@Override
	public Notificationtemplate removeByF_NotificationtemplateByType(
		long groupId, String notificationType)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = findByF_NotificationtemplateByType(groupId,
				notificationType);

		return remove(notificationtemplate);
	}

	/**
	 * Returns the number of notificationtemplates where groupId = &#63; and notificationType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @return the number of matching notificationtemplates
	 */
	@Override
	public int countByF_NotificationtemplateByType(long groupId,
		String notificationType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEBYTYPE;

		Object[] finderArgs = new Object[] { groupId, notificationType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_NOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYTYPE_GROUPID_2);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYTYPE_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals("")) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYTYPE_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYTYPE_NOTIFICATIONTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindNotificationType) {
					qPos.add(notificationType);
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

	private static final String _FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYTYPE_GROUPID_2 =
		"notificationtemplate.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYTYPE_NOTIFICATIONTYPE_1 =
		"notificationtemplate.notificationType IS NULL";
	private static final String _FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYTYPE_NOTIFICATIONTYPE_2 =
		"notificationtemplate.notificationType = ?";
	private static final String _FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYTYPE_NOTIFICATIONTYPE_3 =
		"(notificationtemplate.notificationType IS NULL OR notificationtemplate.notificationType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEBYGROUP =
		new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED,
			NotificationtemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_NotificationtemplateByGroup",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEBYGROUP =
		new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED,
			NotificationtemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_NotificationtemplateByGroup",
			new String[] { Long.class.getName() },
			NotificationtemplateModelImpl.GROUPID_COLUMN_BITMASK |
			NotificationtemplateModelImpl.NOTIFICATIONTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEBYGROUP =
		new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_NotificationtemplateByGroup",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the notificationtemplates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_NotificationtemplateByGroup(
		long groupId) {
		return findByF_NotificationtemplateByGroup(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notificationtemplates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @return the range of matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_NotificationtemplateByGroup(
		long groupId, int start, int end) {
		return findByF_NotificationtemplateByGroup(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notificationtemplates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_NotificationtemplateByGroup(
		long groupId, int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return findByF_NotificationtemplateByGroup(groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notificationtemplates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_NotificationtemplateByGroup(
		long groupId, int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEBYGROUP;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEBYGROUP;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<Notificationtemplate> list = null;

		if (retrieveFromCache) {
			list = (List<Notificationtemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Notificationtemplate notificationtemplate : list) {
					if ((groupId != notificationtemplate.getGroupId())) {
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

			query.append(_SQL_SELECT_NOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYGROUP_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(NotificationtemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Notificationtemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Notificationtemplate>)QueryUtil.list(q,
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
	 * Returns the first notificationtemplate in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate findByF_NotificationtemplateByGroup_First(
		long groupId, OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = fetchByF_NotificationtemplateByGroup_First(groupId,
				orderByComparator);

		if (notificationtemplate != null) {
			return notificationtemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchNotificationtemplateException(msg.toString());
	}

	/**
	 * Returns the first notificationtemplate in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate fetchByF_NotificationtemplateByGroup_First(
		long groupId, OrderByComparator<Notificationtemplate> orderByComparator) {
		List<Notificationtemplate> list = findByF_NotificationtemplateByGroup(groupId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notificationtemplate in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate findByF_NotificationtemplateByGroup_Last(
		long groupId, OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = fetchByF_NotificationtemplateByGroup_Last(groupId,
				orderByComparator);

		if (notificationtemplate != null) {
			return notificationtemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchNotificationtemplateException(msg.toString());
	}

	/**
	 * Returns the last notificationtemplate in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate fetchByF_NotificationtemplateByGroup_Last(
		long groupId, OrderByComparator<Notificationtemplate> orderByComparator) {
		int count = countByF_NotificationtemplateByGroup(groupId);

		if (count == 0) {
			return null;
		}

		List<Notificationtemplate> list = findByF_NotificationtemplateByGroup(groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notificationtemplates before and after the current notificationtemplate in the ordered set where groupId = &#63;.
	 *
	 * @param notificationTemplateId the primary key of the current notificationtemplate
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	 */
	@Override
	public Notificationtemplate[] findByF_NotificationtemplateByGroup_PrevAndNext(
		long notificationTemplateId, long groupId,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = findByPrimaryKey(notificationTemplateId);

		Session session = null;

		try {
			session = openSession();

			Notificationtemplate[] array = new NotificationtemplateImpl[3];

			array[0] = getByF_NotificationtemplateByGroup_PrevAndNext(session,
					notificationtemplate, groupId, orderByComparator, true);

			array[1] = notificationtemplate;

			array[2] = getByF_NotificationtemplateByGroup_PrevAndNext(session,
					notificationtemplate, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Notificationtemplate getByF_NotificationtemplateByGroup_PrevAndNext(
		Session session, Notificationtemplate notificationtemplate,
		long groupId,
		OrderByComparator<Notificationtemplate> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NOTIFICATIONTEMPLATE_WHERE);

		query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYGROUP_GROUPID_2);

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
			query.append(NotificationtemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(notificationtemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Notificationtemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notificationtemplates where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_NotificationtemplateByGroup(long groupId) {
		for (Notificationtemplate notificationtemplate : findByF_NotificationtemplateByGroup(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(notificationtemplate);
		}
	}

	/**
	 * Returns the number of notificationtemplates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching notificationtemplates
	 */
	@Override
	public int countByF_NotificationtemplateByGroup(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEBYGROUP;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYGROUP_GROUPID_2);

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

	private static final String _FINDER_COLUMN_F_NOTIFICATIONTEMPLATEBYGROUP_GROUPID_2 =
		"notificationtemplate.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEEMAILSUBJECT =
		new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED,
			NotificationtemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_NotificationtemplateEmailSubject",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEEMAILSUBJECT =
		new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED,
			NotificationtemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_NotificationtemplateEmailSubject",
			new String[] { String.class.getName() },
			NotificationtemplateModelImpl.EMAILSUBJECT_COLUMN_BITMASK |
			NotificationtemplateModelImpl.NOTIFICATIONTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEEMAILSUBJECT =
		new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_NotificationtemplateEmailSubject",
			new String[] { String.class.getName() });

	/**
	 * Returns all the notificationtemplates where emailSubject = &#63;.
	 *
	 * @param emailSubject the email subject
	 * @return the matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_NotificationtemplateEmailSubject(
		String emailSubject) {
		return findByF_NotificationtemplateEmailSubject(emailSubject,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notificationtemplates where emailSubject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param emailSubject the email subject
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @return the range of matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_NotificationtemplateEmailSubject(
		String emailSubject, int start, int end) {
		return findByF_NotificationtemplateEmailSubject(emailSubject, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the notificationtemplates where emailSubject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param emailSubject the email subject
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_NotificationtemplateEmailSubject(
		String emailSubject, int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return findByF_NotificationtemplateEmailSubject(emailSubject, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notificationtemplates where emailSubject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param emailSubject the email subject
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_NotificationtemplateEmailSubject(
		String emailSubject, int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEEMAILSUBJECT;
			finderArgs = new Object[] { emailSubject };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEEMAILSUBJECT;
			finderArgs = new Object[] {
					emailSubject,
					
					start, end, orderByComparator
				};
		}

		List<Notificationtemplate> list = null;

		if (retrieveFromCache) {
			list = (List<Notificationtemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Notificationtemplate notificationtemplate : list) {
					if (!Objects.equals(emailSubject,
								notificationtemplate.getEmailSubject())) {
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

			query.append(_SQL_SELECT_NOTIFICATIONTEMPLATE_WHERE);

			boolean bindEmailSubject = false;

			if (emailSubject == null) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEEMAILSUBJECT_EMAILSUBJECT_1);
			}
			else if (emailSubject.equals("")) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEEMAILSUBJECT_EMAILSUBJECT_3);
			}
			else {
				bindEmailSubject = true;

				query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEEMAILSUBJECT_EMAILSUBJECT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(NotificationtemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEmailSubject) {
					qPos.add(emailSubject);
				}

				if (!pagination) {
					list = (List<Notificationtemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Notificationtemplate>)QueryUtil.list(q,
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
	 * Returns the first notificationtemplate in the ordered set where emailSubject = &#63;.
	 *
	 * @param emailSubject the email subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate findByF_NotificationtemplateEmailSubject_First(
		String emailSubject,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = fetchByF_NotificationtemplateEmailSubject_First(emailSubject,
				orderByComparator);

		if (notificationtemplate != null) {
			return notificationtemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("emailSubject=");
		msg.append(emailSubject);

		msg.append("}");

		throw new NoSuchNotificationtemplateException(msg.toString());
	}

	/**
	 * Returns the first notificationtemplate in the ordered set where emailSubject = &#63;.
	 *
	 * @param emailSubject the email subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate fetchByF_NotificationtemplateEmailSubject_First(
		String emailSubject,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		List<Notificationtemplate> list = findByF_NotificationtemplateEmailSubject(emailSubject,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notificationtemplate in the ordered set where emailSubject = &#63;.
	 *
	 * @param emailSubject the email subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate findByF_NotificationtemplateEmailSubject_Last(
		String emailSubject,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = fetchByF_NotificationtemplateEmailSubject_Last(emailSubject,
				orderByComparator);

		if (notificationtemplate != null) {
			return notificationtemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("emailSubject=");
		msg.append(emailSubject);

		msg.append("}");

		throw new NoSuchNotificationtemplateException(msg.toString());
	}

	/**
	 * Returns the last notificationtemplate in the ordered set where emailSubject = &#63;.
	 *
	 * @param emailSubject the email subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate fetchByF_NotificationtemplateEmailSubject_Last(
		String emailSubject,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		int count = countByF_NotificationtemplateEmailSubject(emailSubject);

		if (count == 0) {
			return null;
		}

		List<Notificationtemplate> list = findByF_NotificationtemplateEmailSubject(emailSubject,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notificationtemplates before and after the current notificationtemplate in the ordered set where emailSubject = &#63;.
	 *
	 * @param notificationTemplateId the primary key of the current notificationtemplate
	 * @param emailSubject the email subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	 */
	@Override
	public Notificationtemplate[] findByF_NotificationtemplateEmailSubject_PrevAndNext(
		long notificationTemplateId, String emailSubject,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = findByPrimaryKey(notificationTemplateId);

		Session session = null;

		try {
			session = openSession();

			Notificationtemplate[] array = new NotificationtemplateImpl[3];

			array[0] = getByF_NotificationtemplateEmailSubject_PrevAndNext(session,
					notificationtemplate, emailSubject, orderByComparator, true);

			array[1] = notificationtemplate;

			array[2] = getByF_NotificationtemplateEmailSubject_PrevAndNext(session,
					notificationtemplate, emailSubject, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Notificationtemplate getByF_NotificationtemplateEmailSubject_PrevAndNext(
		Session session, Notificationtemplate notificationtemplate,
		String emailSubject,
		OrderByComparator<Notificationtemplate> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NOTIFICATIONTEMPLATE_WHERE);

		boolean bindEmailSubject = false;

		if (emailSubject == null) {
			query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEEMAILSUBJECT_EMAILSUBJECT_1);
		}
		else if (emailSubject.equals("")) {
			query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEEMAILSUBJECT_EMAILSUBJECT_3);
		}
		else {
			bindEmailSubject = true;

			query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEEMAILSUBJECT_EMAILSUBJECT_2);
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
			query.append(NotificationtemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindEmailSubject) {
			qPos.add(emailSubject);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(notificationtemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Notificationtemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notificationtemplates where emailSubject = &#63; from the database.
	 *
	 * @param emailSubject the email subject
	 */
	@Override
	public void removeByF_NotificationtemplateEmailSubject(String emailSubject) {
		for (Notificationtemplate notificationtemplate : findByF_NotificationtemplateEmailSubject(
				emailSubject, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(notificationtemplate);
		}
	}

	/**
	 * Returns the number of notificationtemplates where emailSubject = &#63;.
	 *
	 * @param emailSubject the email subject
	 * @return the number of matching notificationtemplates
	 */
	@Override
	public int countByF_NotificationtemplateEmailSubject(String emailSubject) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEEMAILSUBJECT;

		Object[] finderArgs = new Object[] { emailSubject };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NOTIFICATIONTEMPLATE_WHERE);

			boolean bindEmailSubject = false;

			if (emailSubject == null) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEEMAILSUBJECT_EMAILSUBJECT_1);
			}
			else if (emailSubject.equals("")) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEEMAILSUBJECT_EMAILSUBJECT_3);
			}
			else {
				bindEmailSubject = true;

				query.append(_FINDER_COLUMN_F_NOTIFICATIONTEMPLATEEMAILSUBJECT_EMAILSUBJECT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEmailSubject) {
					qPos.add(emailSubject);
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

	private static final String _FINDER_COLUMN_F_NOTIFICATIONTEMPLATEEMAILSUBJECT_EMAILSUBJECT_1 =
		"notificationtemplate.emailSubject IS NULL";
	private static final String _FINDER_COLUMN_F_NOTIFICATIONTEMPLATEEMAILSUBJECT_EMAILSUBJECT_2 =
		"notificationtemplate.emailSubject = ?";
	private static final String _FINDER_COLUMN_F_NOTIFICATIONTEMPLATEEMAILSUBJECT_EMAILSUBJECT_3 =
		"(notificationtemplate.emailSubject IS NULL OR notificationtemplate.emailSubject = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_EXPIREDURATION =
		new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED,
			NotificationtemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_expireDuration",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EXPIREDURATION =
		new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED,
			NotificationtemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_expireDuration", new String[] { Integer.class.getName() },
			NotificationtemplateModelImpl.EXPIREDURATION_COLUMN_BITMASK |
			NotificationtemplateModelImpl.NOTIFICATIONTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_EXPIREDURATION = new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_expireDuration", new String[] { Integer.class.getName() });

	/**
	 * Returns all the notificationtemplates where expireDuration = &#63;.
	 *
	 * @param expireDuration the expire duration
	 * @return the matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_expireDuration(int expireDuration) {
		return findByF_expireDuration(expireDuration, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notificationtemplates where expireDuration = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expireDuration the expire duration
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @return the range of matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_expireDuration(
		int expireDuration, int start, int end) {
		return findByF_expireDuration(expireDuration, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notificationtemplates where expireDuration = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expireDuration the expire duration
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_expireDuration(
		int expireDuration, int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return findByF_expireDuration(expireDuration, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notificationtemplates where expireDuration = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expireDuration the expire duration
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_expireDuration(
		int expireDuration, int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EXPIREDURATION;
			finderArgs = new Object[] { expireDuration };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_EXPIREDURATION;
			finderArgs = new Object[] {
					expireDuration,
					
					start, end, orderByComparator
				};
		}

		List<Notificationtemplate> list = null;

		if (retrieveFromCache) {
			list = (List<Notificationtemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Notificationtemplate notificationtemplate : list) {
					if ((expireDuration != notificationtemplate.getExpireDuration())) {
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

			query.append(_SQL_SELECT_NOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_F_EXPIREDURATION_EXPIREDURATION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(NotificationtemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(expireDuration);

				if (!pagination) {
					list = (List<Notificationtemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Notificationtemplate>)QueryUtil.list(q,
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
	 * Returns the first notificationtemplate in the ordered set where expireDuration = &#63;.
	 *
	 * @param expireDuration the expire duration
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate findByF_expireDuration_First(
		int expireDuration,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = fetchByF_expireDuration_First(expireDuration,
				orderByComparator);

		if (notificationtemplate != null) {
			return notificationtemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expireDuration=");
		msg.append(expireDuration);

		msg.append("}");

		throw new NoSuchNotificationtemplateException(msg.toString());
	}

	/**
	 * Returns the first notificationtemplate in the ordered set where expireDuration = &#63;.
	 *
	 * @param expireDuration the expire duration
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate fetchByF_expireDuration_First(
		int expireDuration,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		List<Notificationtemplate> list = findByF_expireDuration(expireDuration,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notificationtemplate in the ordered set where expireDuration = &#63;.
	 *
	 * @param expireDuration the expire duration
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate findByF_expireDuration_Last(
		int expireDuration,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = fetchByF_expireDuration_Last(expireDuration,
				orderByComparator);

		if (notificationtemplate != null) {
			return notificationtemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expireDuration=");
		msg.append(expireDuration);

		msg.append("}");

		throw new NoSuchNotificationtemplateException(msg.toString());
	}

	/**
	 * Returns the last notificationtemplate in the ordered set where expireDuration = &#63;.
	 *
	 * @param expireDuration the expire duration
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate fetchByF_expireDuration_Last(
		int expireDuration,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		int count = countByF_expireDuration(expireDuration);

		if (count == 0) {
			return null;
		}

		List<Notificationtemplate> list = findByF_expireDuration(expireDuration,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notificationtemplates before and after the current notificationtemplate in the ordered set where expireDuration = &#63;.
	 *
	 * @param notificationTemplateId the primary key of the current notificationtemplate
	 * @param expireDuration the expire duration
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	 */
	@Override
	public Notificationtemplate[] findByF_expireDuration_PrevAndNext(
		long notificationTemplateId, int expireDuration,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = findByPrimaryKey(notificationTemplateId);

		Session session = null;

		try {
			session = openSession();

			Notificationtemplate[] array = new NotificationtemplateImpl[3];

			array[0] = getByF_expireDuration_PrevAndNext(session,
					notificationtemplate, expireDuration, orderByComparator,
					true);

			array[1] = notificationtemplate;

			array[2] = getByF_expireDuration_PrevAndNext(session,
					notificationtemplate, expireDuration, orderByComparator,
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

	protected Notificationtemplate getByF_expireDuration_PrevAndNext(
		Session session, Notificationtemplate notificationtemplate,
		int expireDuration,
		OrderByComparator<Notificationtemplate> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NOTIFICATIONTEMPLATE_WHERE);

		query.append(_FINDER_COLUMN_F_EXPIREDURATION_EXPIREDURATION_2);

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
			query.append(NotificationtemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(expireDuration);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(notificationtemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Notificationtemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notificationtemplates where expireDuration = &#63; from the database.
	 *
	 * @param expireDuration the expire duration
	 */
	@Override
	public void removeByF_expireDuration(int expireDuration) {
		for (Notificationtemplate notificationtemplate : findByF_expireDuration(
				expireDuration, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(notificationtemplate);
		}
	}

	/**
	 * Returns the number of notificationtemplates where expireDuration = &#63;.
	 *
	 * @param expireDuration the expire duration
	 * @return the number of matching notificationtemplates
	 */
	@Override
	public int countByF_expireDuration(int expireDuration) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_EXPIREDURATION;

		Object[] finderArgs = new Object[] { expireDuration };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_F_EXPIREDURATION_EXPIREDURATION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(expireDuration);

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

	private static final String _FINDER_COLUMN_F_EXPIREDURATION_EXPIREDURATION_2 =
		"notificationtemplate.expireDuration = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_INTERVAL =
		new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED,
			NotificationtemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_interval",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_INTERVAL =
		new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED,
			NotificationtemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_interval",
			new String[] { String.class.getName() },
			NotificationtemplateModelImpl.INTERVAL_COLUMN_BITMASK |
			NotificationtemplateModelImpl.NOTIFICATIONTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_INTERVAL = new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_interval",
			new String[] { String.class.getName() });

	/**
	 * Returns all the notificationtemplates where interval = &#63;.
	 *
	 * @param interval the interval
	 * @return the matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_interval(String interval) {
		return findByF_interval(interval, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the notificationtemplates where interval = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param interval the interval
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @return the range of matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_interval(String interval,
		int start, int end) {
		return findByF_interval(interval, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notificationtemplates where interval = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param interval the interval
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_interval(String interval,
		int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return findByF_interval(interval, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notificationtemplates where interval = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param interval the interval
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findByF_interval(String interval,
		int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_INTERVAL;
			finderArgs = new Object[] { interval };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_INTERVAL;
			finderArgs = new Object[] { interval, start, end, orderByComparator };
		}

		List<Notificationtemplate> list = null;

		if (retrieveFromCache) {
			list = (List<Notificationtemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Notificationtemplate notificationtemplate : list) {
					if (!Objects.equals(interval,
								notificationtemplate.getInterval())) {
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

			query.append(_SQL_SELECT_NOTIFICATIONTEMPLATE_WHERE);

			boolean bindInterval = false;

			if (interval == null) {
				query.append(_FINDER_COLUMN_F_INTERVAL_INTERVAL_1);
			}
			else if (interval.equals("")) {
				query.append(_FINDER_COLUMN_F_INTERVAL_INTERVAL_3);
			}
			else {
				bindInterval = true;

				query.append(_FINDER_COLUMN_F_INTERVAL_INTERVAL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(NotificationtemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindInterval) {
					qPos.add(interval);
				}

				if (!pagination) {
					list = (List<Notificationtemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Notificationtemplate>)QueryUtil.list(q,
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
	 * Returns the first notificationtemplate in the ordered set where interval = &#63;.
	 *
	 * @param interval the interval
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate findByF_interval_First(String interval,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = fetchByF_interval_First(interval,
				orderByComparator);

		if (notificationtemplate != null) {
			return notificationtemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("interval=");
		msg.append(interval);

		msg.append("}");

		throw new NoSuchNotificationtemplateException(msg.toString());
	}

	/**
	 * Returns the first notificationtemplate in the ordered set where interval = &#63;.
	 *
	 * @param interval the interval
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate fetchByF_interval_First(String interval,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		List<Notificationtemplate> list = findByF_interval(interval, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notificationtemplate in the ordered set where interval = &#63;.
	 *
	 * @param interval the interval
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate findByF_interval_Last(String interval,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = fetchByF_interval_Last(interval,
				orderByComparator);

		if (notificationtemplate != null) {
			return notificationtemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("interval=");
		msg.append(interval);

		msg.append("}");

		throw new NoSuchNotificationtemplateException(msg.toString());
	}

	/**
	 * Returns the last notificationtemplate in the ordered set where interval = &#63;.
	 *
	 * @param interval the interval
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate fetchByF_interval_Last(String interval,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		int count = countByF_interval(interval);

		if (count == 0) {
			return null;
		}

		List<Notificationtemplate> list = findByF_interval(interval, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notificationtemplates before and after the current notificationtemplate in the ordered set where interval = &#63;.
	 *
	 * @param notificationTemplateId the primary key of the current notificationtemplate
	 * @param interval the interval
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	 */
	@Override
	public Notificationtemplate[] findByF_interval_PrevAndNext(
		long notificationTemplateId, String interval,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = findByPrimaryKey(notificationTemplateId);

		Session session = null;

		try {
			session = openSession();

			Notificationtemplate[] array = new NotificationtemplateImpl[3];

			array[0] = getByF_interval_PrevAndNext(session,
					notificationtemplate, interval, orderByComparator, true);

			array[1] = notificationtemplate;

			array[2] = getByF_interval_PrevAndNext(session,
					notificationtemplate, interval, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Notificationtemplate getByF_interval_PrevAndNext(
		Session session, Notificationtemplate notificationtemplate,
		String interval,
		OrderByComparator<Notificationtemplate> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NOTIFICATIONTEMPLATE_WHERE);

		boolean bindInterval = false;

		if (interval == null) {
			query.append(_FINDER_COLUMN_F_INTERVAL_INTERVAL_1);
		}
		else if (interval.equals("")) {
			query.append(_FINDER_COLUMN_F_INTERVAL_INTERVAL_3);
		}
		else {
			bindInterval = true;

			query.append(_FINDER_COLUMN_F_INTERVAL_INTERVAL_2);
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
			query.append(NotificationtemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindInterval) {
			qPos.add(interval);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(notificationtemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Notificationtemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notificationtemplates where interval = &#63; from the database.
	 *
	 * @param interval the interval
	 */
	@Override
	public void removeByF_interval(String interval) {
		for (Notificationtemplate notificationtemplate : findByF_interval(
				interval, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(notificationtemplate);
		}
	}

	/**
	 * Returns the number of notificationtemplates where interval = &#63;.
	 *
	 * @param interval the interval
	 * @return the number of matching notificationtemplates
	 */
	@Override
	public int countByF_interval(String interval) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_INTERVAL;

		Object[] finderArgs = new Object[] { interval };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NOTIFICATIONTEMPLATE_WHERE);

			boolean bindInterval = false;

			if (interval == null) {
				query.append(_FINDER_COLUMN_F_INTERVAL_INTERVAL_1);
			}
			else if (interval.equals("")) {
				query.append(_FINDER_COLUMN_F_INTERVAL_INTERVAL_3);
			}
			else {
				bindInterval = true;

				query.append(_FINDER_COLUMN_F_INTERVAL_INTERVAL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindInterval) {
					qPos.add(interval);
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

	private static final String _FINDER_COLUMN_F_INTERVAL_INTERVAL_1 = "notificationtemplate.interval IS NULL";
	private static final String _FINDER_COLUMN_F_INTERVAL_INTERVAL_2 = "notificationtemplate.interval = ?";
	private static final String _FINDER_COLUMN_F_INTERVAL_INTERVAL_3 = "(notificationtemplate.interval IS NULL OR notificationtemplate.interval = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_TYPE_INTER = new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED,
			NotificationtemplateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_GID_TYPE_INTER",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			NotificationtemplateModelImpl.GROUPID_COLUMN_BITMASK |
			NotificationtemplateModelImpl.NOTIFICATIONTYPE_COLUMN_BITMASK |
			NotificationtemplateModelImpl.INTERVAL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_TYPE_INTER = new FinderPath(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_GID_TYPE_INTER",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; and interval = &#63; or throws a {@link NoSuchNotificationtemplateException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @param interval the interval
	 * @return the matching notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate findByF_GID_TYPE_INTER(long groupId,
		String notificationType, String interval)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = fetchByF_GID_TYPE_INTER(groupId,
				notificationType, interval);

		if (notificationtemplate == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", notificationType=");
			msg.append(notificationType);

			msg.append(", interval=");
			msg.append(interval);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchNotificationtemplateException(msg.toString());
		}

		return notificationtemplate;
	}

	/**
	 * Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; and interval = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @param interval the interval
	 * @return the matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate fetchByF_GID_TYPE_INTER(long groupId,
		String notificationType, String interval) {
		return fetchByF_GID_TYPE_INTER(groupId, notificationType, interval, true);
	}

	/**
	 * Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; and interval = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @param interval the interval
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	 */
	@Override
	public Notificationtemplate fetchByF_GID_TYPE_INTER(long groupId,
		String notificationType, String interval, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, notificationType, interval };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_TYPE_INTER,
					finderArgs, this);
		}

		if (result instanceof Notificationtemplate) {
			Notificationtemplate notificationtemplate = (Notificationtemplate)result;

			if ((groupId != notificationtemplate.getGroupId()) ||
					!Objects.equals(notificationType,
						notificationtemplate.getNotificationType()) ||
					!Objects.equals(interval, notificationtemplate.getInterval())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_NOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_GROUPID_2);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_NOTIFICATIONTYPE_2);
			}

			boolean bindInterval = false;

			if (interval == null) {
				query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_INTERVAL_1);
			}
			else if (interval.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_INTERVAL_3);
			}
			else {
				bindInterval = true;

				query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_INTERVAL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindNotificationType) {
					qPos.add(notificationType);
				}

				if (bindInterval) {
					qPos.add(interval);
				}

				List<Notificationtemplate> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_TYPE_INTER,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"NotificationtemplatePersistenceImpl.fetchByF_GID_TYPE_INTER(long, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Notificationtemplate notificationtemplate = list.get(0);

					result = notificationtemplate;

					cacheResult(notificationtemplate);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_TYPE_INTER,
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
			return (Notificationtemplate)result;
		}
	}

	/**
	 * Removes the notificationtemplate where groupId = &#63; and notificationType = &#63; and interval = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @param interval the interval
	 * @return the notificationtemplate that was removed
	 */
	@Override
	public Notificationtemplate removeByF_GID_TYPE_INTER(long groupId,
		String notificationType, String interval)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = findByF_GID_TYPE_INTER(groupId,
				notificationType, interval);

		return remove(notificationtemplate);
	}

	/**
	 * Returns the number of notificationtemplates where groupId = &#63; and notificationType = &#63; and interval = &#63;.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @param interval the interval
	 * @return the number of matching notificationtemplates
	 */
	@Override
	public int countByF_GID_TYPE_INTER(long groupId, String notificationType,
		String interval) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_TYPE_INTER;

		Object[] finderArgs = new Object[] { groupId, notificationType, interval };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_NOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_GROUPID_2);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_NOTIFICATIONTYPE_2);
			}

			boolean bindInterval = false;

			if (interval == null) {
				query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_INTERVAL_1);
			}
			else if (interval.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_INTERVAL_3);
			}
			else {
				bindInterval = true;

				query.append(_FINDER_COLUMN_F_GID_TYPE_INTER_INTERVAL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindNotificationType) {
					qPos.add(notificationType);
				}

				if (bindInterval) {
					qPos.add(interval);
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

	private static final String _FINDER_COLUMN_F_GID_TYPE_INTER_GROUPID_2 = "notificationtemplate.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_TYPE_INTER_NOTIFICATIONTYPE_1 =
		"notificationtemplate.notificationType IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_TYPE_INTER_NOTIFICATIONTYPE_2 =
		"notificationtemplate.notificationType = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_TYPE_INTER_NOTIFICATIONTYPE_3 =
		"(notificationtemplate.notificationType IS NULL OR notificationtemplate.notificationType = '') AND ";
	private static final String _FINDER_COLUMN_F_GID_TYPE_INTER_INTERVAL_1 = "notificationtemplate.interval IS NULL";
	private static final String _FINDER_COLUMN_F_GID_TYPE_INTER_INTERVAL_2 = "notificationtemplate.interval = ?";
	private static final String _FINDER_COLUMN_F_GID_TYPE_INTER_INTERVAL_3 = "(notificationtemplate.interval IS NULL OR notificationtemplate.interval = '')";

	public NotificationtemplatePersistenceImpl() {
		setModelClass(Notificationtemplate.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("interval", "interval_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the notificationtemplate in the entity cache if it is enabled.
	 *
	 * @param notificationtemplate the notificationtemplate
	 */
	@Override
	public void cacheResult(Notificationtemplate notificationtemplate) {
		entityCache.putResult(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateImpl.class,
			notificationtemplate.getPrimaryKey(), notificationtemplate);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_NOTIFICATIONTEMPLATEBYTYPE,
			new Object[] {
				notificationtemplate.getGroupId(),
				notificationtemplate.getNotificationType()
			}, notificationtemplate);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_TYPE_INTER,
			new Object[] {
				notificationtemplate.getGroupId(),
				notificationtemplate.getNotificationType(),
				notificationtemplate.getInterval()
			}, notificationtemplate);

		notificationtemplate.resetOriginalValues();
	}

	/**
	 * Caches the notificationtemplates in the entity cache if it is enabled.
	 *
	 * @param notificationtemplates the notificationtemplates
	 */
	@Override
	public void cacheResult(List<Notificationtemplate> notificationtemplates) {
		for (Notificationtemplate notificationtemplate : notificationtemplates) {
			if (entityCache.getResult(
						NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
						NotificationtemplateImpl.class,
						notificationtemplate.getPrimaryKey()) == null) {
				cacheResult(notificationtemplate);
			}
			else {
				notificationtemplate.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all notificationtemplates.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NotificationtemplateImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the notificationtemplate.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Notificationtemplate notificationtemplate) {
		entityCache.removeResult(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateImpl.class, notificationtemplate.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((NotificationtemplateModelImpl)notificationtemplate,
			true);
	}

	@Override
	public void clearCache(List<Notificationtemplate> notificationtemplates) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Notificationtemplate notificationtemplate : notificationtemplates) {
			entityCache.removeResult(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
				NotificationtemplateImpl.class,
				notificationtemplate.getPrimaryKey());

			clearUniqueFindersCache((NotificationtemplateModelImpl)notificationtemplate,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		NotificationtemplateModelImpl notificationtemplateModelImpl) {
		Object[] args = new Object[] {
				notificationtemplateModelImpl.getGroupId(),
				notificationtemplateModelImpl.getNotificationType()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEBYTYPE,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_NOTIFICATIONTEMPLATEBYTYPE,
			args, notificationtemplateModelImpl, false);

		args = new Object[] {
				notificationtemplateModelImpl.getGroupId(),
				notificationtemplateModelImpl.getNotificationType(),
				notificationtemplateModelImpl.getInterval()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_TYPE_INTER, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_TYPE_INTER, args,
			notificationtemplateModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		NotificationtemplateModelImpl notificationtemplateModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					notificationtemplateModelImpl.getGroupId(),
					notificationtemplateModelImpl.getNotificationType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEBYTYPE,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_NOTIFICATIONTEMPLATEBYTYPE,
				args);
		}

		if ((notificationtemplateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_NOTIFICATIONTEMPLATEBYTYPE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					notificationtemplateModelImpl.getOriginalGroupId(),
					notificationtemplateModelImpl.getOriginalNotificationType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEBYTYPE,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_NOTIFICATIONTEMPLATEBYTYPE,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					notificationtemplateModelImpl.getGroupId(),
					notificationtemplateModelImpl.getNotificationType(),
					notificationtemplateModelImpl.getInterval()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_TYPE_INTER, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_TYPE_INTER, args);
		}

		if ((notificationtemplateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_TYPE_INTER.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					notificationtemplateModelImpl.getOriginalGroupId(),
					notificationtemplateModelImpl.getOriginalNotificationType(),
					notificationtemplateModelImpl.getOriginalInterval()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_TYPE_INTER, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_TYPE_INTER, args);
		}
	}

	/**
	 * Creates a new notificationtemplate with the primary key. Does not add the notificationtemplate to the database.
	 *
	 * @param notificationTemplateId the primary key for the new notificationtemplate
	 * @return the new notificationtemplate
	 */
	@Override
	public Notificationtemplate create(long notificationTemplateId) {
		Notificationtemplate notificationtemplate = new NotificationtemplateImpl();

		notificationtemplate.setNew(true);
		notificationtemplate.setPrimaryKey(notificationTemplateId);

		notificationtemplate.setCompanyId(companyProvider.getCompanyId());

		return notificationtemplate;
	}

	/**
	 * Removes the notificationtemplate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notificationTemplateId the primary key of the notificationtemplate
	 * @return the notificationtemplate that was removed
	 * @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	 */
	@Override
	public Notificationtemplate remove(long notificationTemplateId)
		throws NoSuchNotificationtemplateException {
		return remove((Serializable)notificationTemplateId);
	}

	/**
	 * Removes the notificationtemplate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the notificationtemplate
	 * @return the notificationtemplate that was removed
	 * @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	 */
	@Override
	public Notificationtemplate remove(Serializable primaryKey)
		throws NoSuchNotificationtemplateException {
		Session session = null;

		try {
			session = openSession();

			Notificationtemplate notificationtemplate = (Notificationtemplate)session.get(NotificationtemplateImpl.class,
					primaryKey);

			if (notificationtemplate == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNotificationtemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(notificationtemplate);
		}
		catch (NoSuchNotificationtemplateException nsee) {
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
	protected Notificationtemplate removeImpl(
		Notificationtemplate notificationtemplate) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(notificationtemplate)) {
				notificationtemplate = (Notificationtemplate)session.get(NotificationtemplateImpl.class,
						notificationtemplate.getPrimaryKeyObj());
			}

			if (notificationtemplate != null) {
				session.delete(notificationtemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (notificationtemplate != null) {
			clearCache(notificationtemplate);
		}

		return notificationtemplate;
	}

	@Override
	public Notificationtemplate updateImpl(
		Notificationtemplate notificationtemplate) {
		boolean isNew = notificationtemplate.isNew();

		if (!(notificationtemplate instanceof NotificationtemplateModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(notificationtemplate.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(notificationtemplate);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in notificationtemplate proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Notificationtemplate implementation " +
				notificationtemplate.getClass());
		}

		NotificationtemplateModelImpl notificationtemplateModelImpl = (NotificationtemplateModelImpl)notificationtemplate;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (notificationtemplate.getCreateDate() == null)) {
			if (serviceContext == null) {
				notificationtemplate.setCreateDate(now);
			}
			else {
				notificationtemplate.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!notificationtemplateModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				notificationtemplate.setModifiedDate(now);
			}
			else {
				notificationtemplate.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (notificationtemplate.isNew()) {
				session.save(notificationtemplate);

				notificationtemplate.setNew(false);
			}
			else {
				notificationtemplate = (Notificationtemplate)session.merge(notificationtemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!NotificationtemplateModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					notificationtemplateModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEBYGROUP,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEBYGROUP,
				args);

			args = new Object[] { notificationtemplateModelImpl.getEmailSubject() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEEMAILSUBJECT,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEEMAILSUBJECT,
				args);

			args = new Object[] {
					notificationtemplateModelImpl.getExpireDuration()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EXPIREDURATION, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EXPIREDURATION,
				args);

			args = new Object[] { notificationtemplateModelImpl.getInterval() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_INTERVAL, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_INTERVAL,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((notificationtemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEBYGROUP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						notificationtemplateModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEBYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEBYGROUP,
					args);

				args = new Object[] { notificationtemplateModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEBYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEBYGROUP,
					args);
			}

			if ((notificationtemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEEMAILSUBJECT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						notificationtemplateModelImpl.getOriginalEmailSubject()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEEMAILSUBJECT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEEMAILSUBJECT,
					args);

				args = new Object[] {
						notificationtemplateModelImpl.getEmailSubject()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_NOTIFICATIONTEMPLATEEMAILSUBJECT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_NOTIFICATIONTEMPLATEEMAILSUBJECT,
					args);
			}

			if ((notificationtemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EXPIREDURATION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						notificationtemplateModelImpl.getOriginalExpireDuration()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EXPIREDURATION,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EXPIREDURATION,
					args);

				args = new Object[] {
						notificationtemplateModelImpl.getExpireDuration()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EXPIREDURATION,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EXPIREDURATION,
					args);
			}

			if ((notificationtemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_INTERVAL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						notificationtemplateModelImpl.getOriginalInterval()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_INTERVAL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_INTERVAL,
					args);

				args = new Object[] { notificationtemplateModelImpl.getInterval() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_INTERVAL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_INTERVAL,
					args);
			}
		}

		entityCache.putResult(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
			NotificationtemplateImpl.class,
			notificationtemplate.getPrimaryKey(), notificationtemplate, false);

		clearUniqueFindersCache(notificationtemplateModelImpl, false);
		cacheUniqueFindersCache(notificationtemplateModelImpl);

		notificationtemplate.resetOriginalValues();

		return notificationtemplate;
	}

	/**
	 * Returns the notificationtemplate with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the notificationtemplate
	 * @return the notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	 */
	@Override
	public Notificationtemplate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNotificationtemplateException {
		Notificationtemplate notificationtemplate = fetchByPrimaryKey(primaryKey);

		if (notificationtemplate == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNotificationtemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return notificationtemplate;
	}

	/**
	 * Returns the notificationtemplate with the primary key or throws a {@link NoSuchNotificationtemplateException} if it could not be found.
	 *
	 * @param notificationTemplateId the primary key of the notificationtemplate
	 * @return the notificationtemplate
	 * @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	 */
	@Override
	public Notificationtemplate findByPrimaryKey(long notificationTemplateId)
		throws NoSuchNotificationtemplateException {
		return findByPrimaryKey((Serializable)notificationTemplateId);
	}

	/**
	 * Returns the notificationtemplate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the notificationtemplate
	 * @return the notificationtemplate, or <code>null</code> if a notificationtemplate with the primary key could not be found
	 */
	@Override
	public Notificationtemplate fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
				NotificationtemplateImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Notificationtemplate notificationtemplate = (Notificationtemplate)serializable;

		if (notificationtemplate == null) {
			Session session = null;

			try {
				session = openSession();

				notificationtemplate = (Notificationtemplate)session.get(NotificationtemplateImpl.class,
						primaryKey);

				if (notificationtemplate != null) {
					cacheResult(notificationtemplate);
				}
				else {
					entityCache.putResult(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
						NotificationtemplateImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
					NotificationtemplateImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return notificationtemplate;
	}

	/**
	 * Returns the notificationtemplate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notificationTemplateId the primary key of the notificationtemplate
	 * @return the notificationtemplate, or <code>null</code> if a notificationtemplate with the primary key could not be found
	 */
	@Override
	public Notificationtemplate fetchByPrimaryKey(long notificationTemplateId) {
		return fetchByPrimaryKey((Serializable)notificationTemplateId);
	}

	@Override
	public Map<Serializable, Notificationtemplate> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Notificationtemplate> map = new HashMap<Serializable, Notificationtemplate>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Notificationtemplate notificationtemplate = fetchByPrimaryKey(primaryKey);

			if (notificationtemplate != null) {
				map.put(primaryKey, notificationtemplate);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
					NotificationtemplateImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Notificationtemplate)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_NOTIFICATIONTEMPLATE_WHERE_PKS_IN);

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

			for (Notificationtemplate notificationtemplate : (List<Notificationtemplate>)q.list()) {
				map.put(notificationtemplate.getPrimaryKeyObj(),
					notificationtemplate);

				cacheResult(notificationtemplate);

				uncachedPrimaryKeys.remove(notificationtemplate.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(NotificationtemplateModelImpl.ENTITY_CACHE_ENABLED,
					NotificationtemplateImpl.class, primaryKey, nullModel);
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
	 * Returns all the notificationtemplates.
	 *
	 * @return the notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notificationtemplates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @return the range of notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the notificationtemplates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findAll(int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notificationtemplates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of notificationtemplates
	 * @param end the upper bound of the range of notificationtemplates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of notificationtemplates
	 */
	@Override
	public List<Notificationtemplate> findAll(int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator,
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

		List<Notificationtemplate> list = null;

		if (retrieveFromCache) {
			list = (List<Notificationtemplate>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NOTIFICATIONTEMPLATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NOTIFICATIONTEMPLATE;

				if (pagination) {
					sql = sql.concat(NotificationtemplateModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Notificationtemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Notificationtemplate>)QueryUtil.list(q,
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
	 * Removes all the notificationtemplates from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Notificationtemplate notificationtemplate : findAll()) {
			remove(notificationtemplate);
		}
	}

	/**
	 * Returns the number of notificationtemplates.
	 *
	 * @return the number of notificationtemplates
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NOTIFICATIONTEMPLATE);

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
		return NotificationtemplateModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the notificationtemplate persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NotificationtemplateImpl.class.getName());
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
	private static final String _SQL_SELECT_NOTIFICATIONTEMPLATE = "SELECT notificationtemplate FROM Notificationtemplate notificationtemplate";
	private static final String _SQL_SELECT_NOTIFICATIONTEMPLATE_WHERE_PKS_IN = "SELECT notificationtemplate FROM Notificationtemplate notificationtemplate WHERE notificationTemplateId IN (";
	private static final String _SQL_SELECT_NOTIFICATIONTEMPLATE_WHERE = "SELECT notificationtemplate FROM Notificationtemplate notificationtemplate WHERE ";
	private static final String _SQL_COUNT_NOTIFICATIONTEMPLATE = "SELECT COUNT(notificationtemplate) FROM Notificationtemplate notificationtemplate";
	private static final String _SQL_COUNT_NOTIFICATIONTEMPLATE_WHERE = "SELECT COUNT(notificationtemplate) FROM Notificationtemplate notificationtemplate WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "notificationtemplate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Notificationtemplate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Notificationtemplate exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(NotificationtemplatePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"interval"
			});
}