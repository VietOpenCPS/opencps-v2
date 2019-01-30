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

package org.opencps.usermgt.service.persistence.impl;

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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.usermgt.exception.NoSuchAnswerException;
import org.opencps.usermgt.model.Answer;
import org.opencps.usermgt.model.impl.AnswerImpl;
import org.opencps.usermgt.model.impl.AnswerModelImpl;
import org.opencps.usermgt.service.persistence.AnswerPersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the answer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see AnswerPersistence
 * @see org.opencps.usermgt.service.persistence.AnswerUtil
 * @generated
 */
@ProviderType
public class AnswerPersistenceImpl extends BasePersistenceImpl<Answer>
	implements AnswerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AnswerUtil} to access the answer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AnswerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AnswerModelImpl.ENTITY_CACHE_ENABLED,
			AnswerModelImpl.FINDER_CACHE_ENABLED, AnswerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AnswerModelImpl.ENTITY_CACHE_ENABLED,
			AnswerModelImpl.FINDER_CACHE_ENABLED, AnswerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AnswerModelImpl.ENTITY_CACHE_ENABLED,
			AnswerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Q_PL = new FinderPath(AnswerModelImpl.ENTITY_CACHE_ENABLED,
			AnswerModelImpl.FINDER_CACHE_ENABLED, AnswerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_Q_PL",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Q_PL =
		new FinderPath(AnswerModelImpl.ENTITY_CACHE_ENABLED,
			AnswerModelImpl.FINDER_CACHE_ENABLED, AnswerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_Q_PL",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			AnswerModelImpl.GROUPID_COLUMN_BITMASK |
			AnswerModelImpl.QUESTIONID_COLUMN_BITMASK |
			AnswerModelImpl.PUBLISH_COLUMN_BITMASK |
			AnswerModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_Q_PL = new FinderPath(AnswerModelImpl.ENTITY_CACHE_ENABLED,
			AnswerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_Q_PL",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Q_PL = new FinderPath(AnswerModelImpl.ENTITY_CACHE_ENABLED,
			AnswerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_Q_PL",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the answers where groupId = &#63; and questionId = &#63; and publish = &#63;.
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publish the publish
	 * @return the matching answers
	 */
	@Override
	public List<Answer> findByG_Q_PL(long groupId, long questionId, int publish) {
		return findByG_Q_PL(groupId, questionId, publish, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the answers where groupId = &#63; and questionId = &#63; and publish = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publish the publish
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @return the range of matching answers
	 */
	@Override
	public List<Answer> findByG_Q_PL(long groupId, long questionId,
		int publish, int start, int end) {
		return findByG_Q_PL(groupId, questionId, publish, start, end, null);
	}

	/**
	 * Returns an ordered range of all the answers where groupId = &#63; and questionId = &#63; and publish = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publish the publish
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching answers
	 */
	@Override
	public List<Answer> findByG_Q_PL(long groupId, long questionId,
		int publish, int start, int end,
		OrderByComparator<Answer> orderByComparator) {
		return findByG_Q_PL(groupId, questionId, publish, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the answers where groupId = &#63; and questionId = &#63; and publish = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publish the publish
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching answers
	 */
	@Override
	public List<Answer> findByG_Q_PL(long groupId, long questionId,
		int publish, int start, int end,
		OrderByComparator<Answer> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Q_PL;
			finderArgs = new Object[] { groupId, questionId, publish };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Q_PL;
			finderArgs = new Object[] {
					groupId, questionId, publish,
					
					start, end, orderByComparator
				};
		}

		List<Answer> list = null;

		if (retrieveFromCache) {
			list = (List<Answer>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Answer answer : list) {
					if ((groupId != answer.getGroupId()) ||
							(questionId != answer.getQuestionId()) ||
							(publish != answer.getPublish())) {
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

			query.append(_SQL_SELECT_ANSWER_WHERE);

			query.append(_FINDER_COLUMN_G_Q_PL_GROUPID_2);

			query.append(_FINDER_COLUMN_G_Q_PL_QUESTIONID_2);

			query.append(_FINDER_COLUMN_G_Q_PL_PUBLISH_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AnswerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(questionId);

				qPos.add(publish);

				if (!pagination) {
					list = (List<Answer>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Answer>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first answer in the ordered set where groupId = &#63; and questionId = &#63; and publish = &#63;.
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publish the publish
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching answer
	 * @throws NoSuchAnswerException if a matching answer could not be found
	 */
	@Override
	public Answer findByG_Q_PL_First(long groupId, long questionId,
		int publish, OrderByComparator<Answer> orderByComparator)
		throws NoSuchAnswerException {
		Answer answer = fetchByG_Q_PL_First(groupId, questionId, publish,
				orderByComparator);

		if (answer != null) {
			return answer;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", questionId=");
		msg.append(questionId);

		msg.append(", publish=");
		msg.append(publish);

		msg.append("}");

		throw new NoSuchAnswerException(msg.toString());
	}

	/**
	 * Returns the first answer in the ordered set where groupId = &#63; and questionId = &#63; and publish = &#63;.
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publish the publish
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching answer, or <code>null</code> if a matching answer could not be found
	 */
	@Override
	public Answer fetchByG_Q_PL_First(long groupId, long questionId,
		int publish, OrderByComparator<Answer> orderByComparator) {
		List<Answer> list = findByG_Q_PL(groupId, questionId, publish, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last answer in the ordered set where groupId = &#63; and questionId = &#63; and publish = &#63;.
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publish the publish
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching answer
	 * @throws NoSuchAnswerException if a matching answer could not be found
	 */
	@Override
	public Answer findByG_Q_PL_Last(long groupId, long questionId, int publish,
		OrderByComparator<Answer> orderByComparator)
		throws NoSuchAnswerException {
		Answer answer = fetchByG_Q_PL_Last(groupId, questionId, publish,
				orderByComparator);

		if (answer != null) {
			return answer;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", questionId=");
		msg.append(questionId);

		msg.append(", publish=");
		msg.append(publish);

		msg.append("}");

		throw new NoSuchAnswerException(msg.toString());
	}

	/**
	 * Returns the last answer in the ordered set where groupId = &#63; and questionId = &#63; and publish = &#63;.
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publish the publish
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching answer, or <code>null</code> if a matching answer could not be found
	 */
	@Override
	public Answer fetchByG_Q_PL_Last(long groupId, long questionId,
		int publish, OrderByComparator<Answer> orderByComparator) {
		int count = countByG_Q_PL(groupId, questionId, publish);

		if (count == 0) {
			return null;
		}

		List<Answer> list = findByG_Q_PL(groupId, questionId, publish,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the answers before and after the current answer in the ordered set where groupId = &#63; and questionId = &#63; and publish = &#63;.
	 *
	 * @param answerId the primary key of the current answer
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publish the publish
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next answer
	 * @throws NoSuchAnswerException if a answer with the primary key could not be found
	 */
	@Override
	public Answer[] findByG_Q_PL_PrevAndNext(long answerId, long groupId,
		long questionId, int publish,
		OrderByComparator<Answer> orderByComparator)
		throws NoSuchAnswerException {
		Answer answer = findByPrimaryKey(answerId);

		Session session = null;

		try {
			session = openSession();

			Answer[] array = new AnswerImpl[3];

			array[0] = getByG_Q_PL_PrevAndNext(session, answer, groupId,
					questionId, publish, orderByComparator, true);

			array[1] = answer;

			array[2] = getByG_Q_PL_PrevAndNext(session, answer, groupId,
					questionId, publish, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Answer getByG_Q_PL_PrevAndNext(Session session, Answer answer,
		long groupId, long questionId, int publish,
		OrderByComparator<Answer> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ANSWER_WHERE);

		query.append(_FINDER_COLUMN_G_Q_PL_GROUPID_2);

		query.append(_FINDER_COLUMN_G_Q_PL_QUESTIONID_2);

		query.append(_FINDER_COLUMN_G_Q_PL_PUBLISH_2);

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
			query.append(AnswerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(questionId);

		qPos.add(publish);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(answer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Answer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the answers where groupId = &#63; and questionId = &#63; and publish = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publishs the publishs
	 * @return the matching answers
	 */
	@Override
	public List<Answer> findByG_Q_PL(long groupId, long questionId,
		int[] publishs) {
		return findByG_Q_PL(groupId, questionId, publishs, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the answers where groupId = &#63; and questionId = &#63; and publish = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publishs the publishs
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @return the range of matching answers
	 */
	@Override
	public List<Answer> findByG_Q_PL(long groupId, long questionId,
		int[] publishs, int start, int end) {
		return findByG_Q_PL(groupId, questionId, publishs, start, end, null);
	}

	/**
	 * Returns an ordered range of all the answers where groupId = &#63; and questionId = &#63; and publish = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publishs the publishs
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching answers
	 */
	@Override
	public List<Answer> findByG_Q_PL(long groupId, long questionId,
		int[] publishs, int start, int end,
		OrderByComparator<Answer> orderByComparator) {
		return findByG_Q_PL(groupId, questionId, publishs, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the answers where groupId = &#63; and questionId = &#63; and publish = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publish the publish
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching answers
	 */
	@Override
	public List<Answer> findByG_Q_PL(long groupId, long questionId,
		int[] publishs, int start, int end,
		OrderByComparator<Answer> orderByComparator, boolean retrieveFromCache) {
		if (publishs == null) {
			publishs = new int[0];
		}
		else if (publishs.length > 1) {
			publishs = ArrayUtil.unique(publishs);

			Arrays.sort(publishs);
		}

		if (publishs.length == 1) {
			return findByG_Q_PL(groupId, questionId, publishs[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, questionId, StringUtil.merge(publishs)
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, questionId, StringUtil.merge(publishs),
					
					start, end, orderByComparator
				};
		}

		List<Answer> list = null;

		if (retrieveFromCache) {
			list = (List<Answer>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Q_PL,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Answer answer : list) {
					if ((groupId != answer.getGroupId()) ||
							(questionId != answer.getQuestionId()) ||
							!ArrayUtil.contains(publishs, answer.getPublish())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ANSWER_WHERE);

			query.append(_FINDER_COLUMN_G_Q_PL_GROUPID_2);

			query.append(_FINDER_COLUMN_G_Q_PL_QUESTIONID_2);

			if (publishs.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_Q_PL_PUBLISH_7);

				query.append(StringUtil.merge(publishs));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AnswerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(questionId);

				if (!pagination) {
					list = (List<Answer>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Answer>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Q_PL,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Q_PL,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the answers where groupId = &#63; and questionId = &#63; and publish = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publish the publish
	 */
	@Override
	public void removeByG_Q_PL(long groupId, long questionId, int publish) {
		for (Answer answer : findByG_Q_PL(groupId, questionId, publish,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(answer);
		}
	}

	/**
	 * Returns the number of answers where groupId = &#63; and questionId = &#63; and publish = &#63;.
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publish the publish
	 * @return the number of matching answers
	 */
	@Override
	public int countByG_Q_PL(long groupId, long questionId, int publish) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_Q_PL;

		Object[] finderArgs = new Object[] { groupId, questionId, publish };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ANSWER_WHERE);

			query.append(_FINDER_COLUMN_G_Q_PL_GROUPID_2);

			query.append(_FINDER_COLUMN_G_Q_PL_QUESTIONID_2);

			query.append(_FINDER_COLUMN_G_Q_PL_PUBLISH_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(questionId);

				qPos.add(publish);

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

	/**
	 * Returns the number of answers where groupId = &#63; and questionId = &#63; and publish = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param questionId the question ID
	 * @param publishs the publishs
	 * @return the number of matching answers
	 */
	@Override
	public int countByG_Q_PL(long groupId, long questionId, int[] publishs) {
		if (publishs == null) {
			publishs = new int[0];
		}
		else if (publishs.length > 1) {
			publishs = ArrayUtil.unique(publishs);

			Arrays.sort(publishs);
		}

		Object[] finderArgs = new Object[] {
				groupId, questionId, StringUtil.merge(publishs)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Q_PL,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ANSWER_WHERE);

			query.append(_FINDER_COLUMN_G_Q_PL_GROUPID_2);

			query.append(_FINDER_COLUMN_G_Q_PL_QUESTIONID_2);

			if (publishs.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_Q_PL_PUBLISH_7);

				query.append(StringUtil.merge(publishs));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(questionId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Q_PL,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Q_PL,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_Q_PL_GROUPID_2 = "answer.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_Q_PL_QUESTIONID_2 = "answer.questionId = ? AND ";
	private static final String _FINDER_COLUMN_G_Q_PL_PUBLISH_2 = "answer.publish = ?";
	private static final String _FINDER_COLUMN_G_Q_PL_PUBLISH_7 = "answer.publish IN (";

	public AnswerPersistenceImpl() {
		setModelClass(Answer.class);
	}

	/**
	 * Caches the answer in the entity cache if it is enabled.
	 *
	 * @param answer the answer
	 */
	@Override
	public void cacheResult(Answer answer) {
		entityCache.putResult(AnswerModelImpl.ENTITY_CACHE_ENABLED,
			AnswerImpl.class, answer.getPrimaryKey(), answer);

		answer.resetOriginalValues();
	}

	/**
	 * Caches the answers in the entity cache if it is enabled.
	 *
	 * @param answers the answers
	 */
	@Override
	public void cacheResult(List<Answer> answers) {
		for (Answer answer : answers) {
			if (entityCache.getResult(AnswerModelImpl.ENTITY_CACHE_ENABLED,
						AnswerImpl.class, answer.getPrimaryKey()) == null) {
				cacheResult(answer);
			}
			else {
				answer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all answers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AnswerImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the answer.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Answer answer) {
		entityCache.removeResult(AnswerModelImpl.ENTITY_CACHE_ENABLED,
			AnswerImpl.class, answer.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Answer> answers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Answer answer : answers) {
			entityCache.removeResult(AnswerModelImpl.ENTITY_CACHE_ENABLED,
				AnswerImpl.class, answer.getPrimaryKey());
		}
	}

	/**
	 * Creates a new answer with the primary key. Does not add the answer to the database.
	 *
	 * @param answerId the primary key for the new answer
	 * @return the new answer
	 */
	@Override
	public Answer create(long answerId) {
		Answer answer = new AnswerImpl();

		answer.setNew(true);
		answer.setPrimaryKey(answerId);

		answer.setCompanyId(companyProvider.getCompanyId());

		return answer;
	}

	/**
	 * Removes the answer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param answerId the primary key of the answer
	 * @return the answer that was removed
	 * @throws NoSuchAnswerException if a answer with the primary key could not be found
	 */
	@Override
	public Answer remove(long answerId) throws NoSuchAnswerException {
		return remove((Serializable)answerId);
	}

	/**
	 * Removes the answer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the answer
	 * @return the answer that was removed
	 * @throws NoSuchAnswerException if a answer with the primary key could not be found
	 */
	@Override
	public Answer remove(Serializable primaryKey) throws NoSuchAnswerException {
		Session session = null;

		try {
			session = openSession();

			Answer answer = (Answer)session.get(AnswerImpl.class, primaryKey);

			if (answer == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAnswerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(answer);
		}
		catch (NoSuchAnswerException nsee) {
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
	protected Answer removeImpl(Answer answer) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(answer)) {
				answer = (Answer)session.get(AnswerImpl.class,
						answer.getPrimaryKeyObj());
			}

			if (answer != null) {
				session.delete(answer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (answer != null) {
			clearCache(answer);
		}

		return answer;
	}

	@Override
	public Answer updateImpl(Answer answer) {
		boolean isNew = answer.isNew();

		if (!(answer instanceof AnswerModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(answer.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(answer);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in answer proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Answer implementation " +
				answer.getClass());
		}

		AnswerModelImpl answerModelImpl = (AnswerModelImpl)answer;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (answer.getCreateDate() == null)) {
			if (serviceContext == null) {
				answer.setCreateDate(now);
			}
			else {
				answer.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!answerModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				answer.setModifiedDate(now);
			}
			else {
				answer.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (answer.isNew()) {
				session.save(answer);

				answer.setNew(false);
			}
			else {
				answer = (Answer)session.merge(answer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AnswerModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					answerModelImpl.getGroupId(),
					answerModelImpl.getQuestionId(),
					answerModelImpl.getPublish()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_Q_PL, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Q_PL,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((answerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Q_PL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						answerModelImpl.getOriginalGroupId(),
						answerModelImpl.getOriginalQuestionId(),
						answerModelImpl.getOriginalPublish()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_Q_PL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Q_PL,
					args);

				args = new Object[] {
						answerModelImpl.getGroupId(),
						answerModelImpl.getQuestionId(),
						answerModelImpl.getPublish()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_Q_PL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Q_PL,
					args);
			}
		}

		entityCache.putResult(AnswerModelImpl.ENTITY_CACHE_ENABLED,
			AnswerImpl.class, answer.getPrimaryKey(), answer, false);

		answer.resetOriginalValues();

		return answer;
	}

	/**
	 * Returns the answer with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the answer
	 * @return the answer
	 * @throws NoSuchAnswerException if a answer with the primary key could not be found
	 */
	@Override
	public Answer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAnswerException {
		Answer answer = fetchByPrimaryKey(primaryKey);

		if (answer == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAnswerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return answer;
	}

	/**
	 * Returns the answer with the primary key or throws a {@link NoSuchAnswerException} if it could not be found.
	 *
	 * @param answerId the primary key of the answer
	 * @return the answer
	 * @throws NoSuchAnswerException if a answer with the primary key could not be found
	 */
	@Override
	public Answer findByPrimaryKey(long answerId) throws NoSuchAnswerException {
		return findByPrimaryKey((Serializable)answerId);
	}

	/**
	 * Returns the answer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the answer
	 * @return the answer, or <code>null</code> if a answer with the primary key could not be found
	 */
	@Override
	public Answer fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AnswerModelImpl.ENTITY_CACHE_ENABLED,
				AnswerImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Answer answer = (Answer)serializable;

		if (answer == null) {
			Session session = null;

			try {
				session = openSession();

				answer = (Answer)session.get(AnswerImpl.class, primaryKey);

				if (answer != null) {
					cacheResult(answer);
				}
				else {
					entityCache.putResult(AnswerModelImpl.ENTITY_CACHE_ENABLED,
						AnswerImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AnswerModelImpl.ENTITY_CACHE_ENABLED,
					AnswerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return answer;
	}

	/**
	 * Returns the answer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param answerId the primary key of the answer
	 * @return the answer, or <code>null</code> if a answer with the primary key could not be found
	 */
	@Override
	public Answer fetchByPrimaryKey(long answerId) {
		return fetchByPrimaryKey((Serializable)answerId);
	}

	@Override
	public Map<Serializable, Answer> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Answer> map = new HashMap<Serializable, Answer>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Answer answer = fetchByPrimaryKey(primaryKey);

			if (answer != null) {
				map.put(primaryKey, answer);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AnswerModelImpl.ENTITY_CACHE_ENABLED,
					AnswerImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Answer)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ANSWER_WHERE_PKS_IN);

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

			for (Answer answer : (List<Answer>)q.list()) {
				map.put(answer.getPrimaryKeyObj(), answer);

				cacheResult(answer);

				uncachedPrimaryKeys.remove(answer.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AnswerModelImpl.ENTITY_CACHE_ENABLED,
					AnswerImpl.class, primaryKey, nullModel);
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
	 * Returns all the answers.
	 *
	 * @return the answers
	 */
	@Override
	public List<Answer> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the answers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @return the range of answers
	 */
	@Override
	public List<Answer> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the answers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of answers
	 */
	@Override
	public List<Answer> findAll(int start, int end,
		OrderByComparator<Answer> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the answers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of answers
	 */
	@Override
	public List<Answer> findAll(int start, int end,
		OrderByComparator<Answer> orderByComparator, boolean retrieveFromCache) {
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

		List<Answer> list = null;

		if (retrieveFromCache) {
			list = (List<Answer>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ANSWER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ANSWER;

				if (pagination) {
					sql = sql.concat(AnswerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Answer>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Answer>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the answers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Answer answer : findAll()) {
			remove(answer);
		}
	}

	/**
	 * Returns the number of answers.
	 *
	 * @return the number of answers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ANSWER);

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
		return AnswerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the answer persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AnswerImpl.class.getName());
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
	private static final String _SQL_SELECT_ANSWER = "SELECT answer FROM Answer answer";
	private static final String _SQL_SELECT_ANSWER_WHERE_PKS_IN = "SELECT answer FROM Answer answer WHERE answerId IN (";
	private static final String _SQL_SELECT_ANSWER_WHERE = "SELECT answer FROM Answer answer WHERE ";
	private static final String _SQL_COUNT_ANSWER = "SELECT COUNT(answer) FROM Answer answer";
	private static final String _SQL_COUNT_ANSWER_WHERE = "SELECT COUNT(answer) FROM Answer answer WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "answer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Answer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Answer exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AnswerPersistenceImpl.class);
}