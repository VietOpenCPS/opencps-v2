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

import org.opencps.usermgt.exception.NoSuchQuestionException;
import org.opencps.usermgt.model.Question;
import org.opencps.usermgt.model.impl.QuestionImpl;
import org.opencps.usermgt.model.impl.QuestionModelImpl;
import org.opencps.usermgt.service.persistence.QuestionPersistence;

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
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the question service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see QuestionPersistence
 * @see org.opencps.usermgt.service.persistence.QuestionUtil
 * @generated
 */
@ProviderType
public class QuestionPersistenceImpl extends BasePersistenceImpl<Question>
	implements QuestionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link QuestionUtil} to access the question persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = QuestionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, QuestionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, QuestionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL = new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, QuestionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_PL",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL = new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, QuestionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_PL",
			new String[] { Long.class.getName(), Integer.class.getName() },
			QuestionModelImpl.GROUPID_COLUMN_BITMASK |
			QuestionModelImpl.PUBLISH_COLUMN_BITMASK |
			QuestionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_PL = new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_PL",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL = new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_PL",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the questions where groupId = &#63; and publish = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @return the matching questions
	 */
	@Override
	public List<Question> findByG_PL(long groupId, int publish) {
		return findByG_PL(groupId, publish, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the questions where groupId = &#63; and publish = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @return the range of matching questions
	 */
	@Override
	public List<Question> findByG_PL(long groupId, int publish, int start,
		int end) {
		return findByG_PL(groupId, publish, start, end, null);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL(long groupId, int publish, int start,
		int end, OrderByComparator<Question> orderByComparator) {
		return findByG_PL(groupId, publish, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL(long groupId, int publish, int start,
		int end, OrderByComparator<Question> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL;
			finderArgs = new Object[] { groupId, publish };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL;
			finderArgs = new Object[] {
					groupId, publish,
					
					start, end, orderByComparator
				};
		}

		List<Question> list = null;

		if (retrieveFromCache) {
			list = (List<Question>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Question question : list) {
					if ((groupId != question.getGroupId()) ||
							(publish != question.getPublish())) {
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

			query.append(_SQL_SELECT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_GROUPID_2);

			query.append(_FINDER_COLUMN_G_PL_PUBLISH_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(QuestionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(publish);

				if (!pagination) {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first question in the ordered set where groupId = &#63; and publish = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching question
	 * @throws NoSuchQuestionException if a matching question could not be found
	 */
	@Override
	public Question findByG_PL_First(long groupId, int publish,
		OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException {
		Question question = fetchByG_PL_First(groupId, publish,
				orderByComparator);

		if (question != null) {
			return question;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", publish=");
		msg.append(publish);

		msg.append("}");

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the first question in the ordered set where groupId = &#63; and publish = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching question, or <code>null</code> if a matching question could not be found
	 */
	@Override
	public Question fetchByG_PL_First(long groupId, int publish,
		OrderByComparator<Question> orderByComparator) {
		List<Question> list = findByG_PL(groupId, publish, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last question in the ordered set where groupId = &#63; and publish = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching question
	 * @throws NoSuchQuestionException if a matching question could not be found
	 */
	@Override
	public Question findByG_PL_Last(long groupId, int publish,
		OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException {
		Question question = fetchByG_PL_Last(groupId, publish, orderByComparator);

		if (question != null) {
			return question;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", publish=");
		msg.append(publish);

		msg.append("}");

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the last question in the ordered set where groupId = &#63; and publish = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching question, or <code>null</code> if a matching question could not be found
	 */
	@Override
	public Question fetchByG_PL_Last(long groupId, int publish,
		OrderByComparator<Question> orderByComparator) {
		int count = countByG_PL(groupId, publish);

		if (count == 0) {
			return null;
		}

		List<Question> list = findByG_PL(groupId, publish, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the questions before and after the current question in the ordered set where groupId = &#63; and publish = &#63;.
	 *
	 * @param questionId the primary key of the current question
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next question
	 * @throws NoSuchQuestionException if a question with the primary key could not be found
	 */
	@Override
	public Question[] findByG_PL_PrevAndNext(long questionId, long groupId,
		int publish, OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException {
		Question question = findByPrimaryKey(questionId);

		Session session = null;

		try {
			session = openSession();

			Question[] array = new QuestionImpl[3];

			array[0] = getByG_PL_PrevAndNext(session, question, groupId,
					publish, orderByComparator, true);

			array[1] = question;

			array[2] = getByG_PL_PrevAndNext(session, question, groupId,
					publish, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Question getByG_PL_PrevAndNext(Session session,
		Question question, long groupId, int publish,
		OrderByComparator<Question> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_QUESTION_WHERE);

		query.append(_FINDER_COLUMN_G_PL_GROUPID_2);

		query.append(_FINDER_COLUMN_G_PL_PUBLISH_2);

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
			query.append(QuestionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(publish);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(question);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Question> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the questions where groupId = &#63; and publish = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @return the matching questions
	 */
	@Override
	public List<Question> findByG_PL(long groupId, int[] publishs) {
		return findByG_PL(groupId, publishs, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the questions where groupId = &#63; and publish = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @return the range of matching questions
	 */
	@Override
	public List<Question> findByG_PL(long groupId, int[] publishs, int start,
		int end) {
		return findByG_PL(groupId, publishs, start, end, null);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL(long groupId, int[] publishs, int start,
		int end, OrderByComparator<Question> orderByComparator) {
		return findByG_PL(groupId, publishs, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL(long groupId, int[] publishs, int start,
		int end, OrderByComparator<Question> orderByComparator,
		boolean retrieveFromCache) {
		if (publishs == null) {
			publishs = new int[0];
		}
		else if (publishs.length > 1) {
			publishs = ArrayUtil.unique(publishs);

			Arrays.sort(publishs);
		}

		if (publishs.length == 1) {
			return findByG_PL(groupId, publishs[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { groupId, StringUtil.merge(publishs) };
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(publishs),
					
					start, end, orderByComparator
				};
		}

		List<Question> list = null;

		if (retrieveFromCache) {
			list = (List<Question>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Question question : list) {
					if ((groupId != question.getGroupId()) ||
							!ArrayUtil.contains(publishs, question.getPublish())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_GROUPID_2);

			if (publishs.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_PL_PUBLISH_7);

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
				query.append(QuestionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL,
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
	 * Removes all the questions where groupId = &#63; and publish = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 */
	@Override
	public void removeByG_PL(long groupId, int publish) {
		for (Question question : findByG_PL(groupId, publish,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(question);
		}
	}

	/**
	 * Returns the number of questions where groupId = &#63; and publish = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @return the number of matching questions
	 */
	@Override
	public int countByG_PL(long groupId, int publish) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_PL;

		Object[] finderArgs = new Object[] { groupId, publish };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_GROUPID_2);

			query.append(_FINDER_COLUMN_G_PL_PUBLISH_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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
	 * Returns the number of questions where groupId = &#63; and publish = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @return the number of matching questions
	 */
	@Override
	public int countByG_PL(long groupId, int[] publishs) {
		if (publishs == null) {
			publishs = new int[0];
		}
		else if (publishs.length > 1) {
			publishs = ArrayUtil.unique(publishs);

			Arrays.sort(publishs);
		}

		Object[] finderArgs = new Object[] { groupId, StringUtil.merge(publishs) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_GROUPID_2);

			if (publishs.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_PL_PUBLISH_7);

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

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_PL_GROUPID_2 = "question.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_PL_PUBLISH_2 = "question.publish = ?";
	private static final String _FINDER_COLUMN_G_PL_PUBLISH_7 = "question.publish IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT = new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, QuestionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_PL_QT",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT =
		new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, QuestionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_PL_QT",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName()
			},
			QuestionModelImpl.GROUPID_COLUMN_BITMASK |
			QuestionModelImpl.PUBLISH_COLUMN_BITMASK |
			QuestionModelImpl.QUESTIONTYPE_COLUMN_BITMASK |
			QuestionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_PL_QT = new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_PL_QT",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL_QT = new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_PL_QT",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the questions where groupId = &#63; and publish = &#63; and questionType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @return the matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT(long groupId, int publish,
		String questionType) {
		return findByG_PL_QT(groupId, publish, questionType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the questions where groupId = &#63; and publish = &#63; and questionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @return the range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT(long groupId, int publish,
		String questionType, int start, int end) {
		return findByG_PL_QT(groupId, publish, questionType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = &#63; and questionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT(long groupId, int publish,
		String questionType, int start, int end,
		OrderByComparator<Question> orderByComparator) {
		return findByG_PL_QT(groupId, publish, questionType, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = &#63; and questionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT(long groupId, int publish,
		String questionType, int start, int end,
		OrderByComparator<Question> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT;
			finderArgs = new Object[] { groupId, publish, questionType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT;
			finderArgs = new Object[] {
					groupId, publish, questionType,
					
					start, end, orderByComparator
				};
		}

		List<Question> list = null;

		if (retrieveFromCache) {
			list = (List<Question>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Question question : list) {
					if ((groupId != question.getGroupId()) ||
							(publish != question.getPublish()) ||
							!Objects.equals(questionType,
								question.getQuestionType())) {
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

			query.append(_SQL_SELECT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_QT_GROUPID_2);

			query.append(_FINDER_COLUMN_G_PL_QT_PUBLISH_2);

			boolean bindQuestionType = false;

			if (questionType == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_1);
			}
			else if (questionType.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_3);
			}
			else {
				bindQuestionType = true;

				query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(QuestionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(publish);

				if (bindQuestionType) {
					qPos.add(questionType);
				}

				if (!pagination) {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching question
	 * @throws NoSuchQuestionException if a matching question could not be found
	 */
	@Override
	public Question findByG_PL_QT_First(long groupId, int publish,
		String questionType, OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException {
		Question question = fetchByG_PL_QT_First(groupId, publish,
				questionType, orderByComparator);

		if (question != null) {
			return question;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", publish=");
		msg.append(publish);

		msg.append(", questionType=");
		msg.append(questionType);

		msg.append("}");

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the first question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching question, or <code>null</code> if a matching question could not be found
	 */
	@Override
	public Question fetchByG_PL_QT_First(long groupId, int publish,
		String questionType, OrderByComparator<Question> orderByComparator) {
		List<Question> list = findByG_PL_QT(groupId, publish, questionType, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching question
	 * @throws NoSuchQuestionException if a matching question could not be found
	 */
	@Override
	public Question findByG_PL_QT_Last(long groupId, int publish,
		String questionType, OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException {
		Question question = fetchByG_PL_QT_Last(groupId, publish, questionType,
				orderByComparator);

		if (question != null) {
			return question;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", publish=");
		msg.append(publish);

		msg.append(", questionType=");
		msg.append(questionType);

		msg.append("}");

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the last question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching question, or <code>null</code> if a matching question could not be found
	 */
	@Override
	public Question fetchByG_PL_QT_Last(long groupId, int publish,
		String questionType, OrderByComparator<Question> orderByComparator) {
		int count = countByG_PL_QT(groupId, publish, questionType);

		if (count == 0) {
			return null;
		}

		List<Question> list = findByG_PL_QT(groupId, publish, questionType,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the questions before and after the current question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63;.
	 *
	 * @param questionId the primary key of the current question
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next question
	 * @throws NoSuchQuestionException if a question with the primary key could not be found
	 */
	@Override
	public Question[] findByG_PL_QT_PrevAndNext(long questionId, long groupId,
		int publish, String questionType,
		OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException {
		Question question = findByPrimaryKey(questionId);

		Session session = null;

		try {
			session = openSession();

			Question[] array = new QuestionImpl[3];

			array[0] = getByG_PL_QT_PrevAndNext(session, question, groupId,
					publish, questionType, orderByComparator, true);

			array[1] = question;

			array[2] = getByG_PL_QT_PrevAndNext(session, question, groupId,
					publish, questionType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Question getByG_PL_QT_PrevAndNext(Session session,
		Question question, long groupId, int publish, String questionType,
		OrderByComparator<Question> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_QUESTION_WHERE);

		query.append(_FINDER_COLUMN_G_PL_QT_GROUPID_2);

		query.append(_FINDER_COLUMN_G_PL_QT_PUBLISH_2);

		boolean bindQuestionType = false;

		if (questionType == null) {
			query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_1);
		}
		else if (questionType.equals("")) {
			query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_3);
		}
		else {
			bindQuestionType = true;

			query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_2);
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
			query.append(QuestionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(publish);

		if (bindQuestionType) {
			qPos.add(questionType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(question);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Question> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the questions where groupId = &#63; and publish = any &#63; and questionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param questionType the question type
	 * @return the matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT(long groupId, int[] publishs,
		String questionType) {
		return findByG_PL_QT(groupId, publishs, questionType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the questions where groupId = &#63; and publish = any &#63; and questionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param questionType the question type
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @return the range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT(long groupId, int[] publishs,
		String questionType, int start, int end) {
		return findByG_PL_QT(groupId, publishs, questionType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = any &#63; and questionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param questionType the question type
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT(long groupId, int[] publishs,
		String questionType, int start, int end,
		OrderByComparator<Question> orderByComparator) {
		return findByG_PL_QT(groupId, publishs, questionType, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = &#63; and questionType = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT(long groupId, int[] publishs,
		String questionType, int start, int end,
		OrderByComparator<Question> orderByComparator, boolean retrieveFromCache) {
		if (publishs == null) {
			publishs = new int[0];
		}
		else if (publishs.length > 1) {
			publishs = ArrayUtil.unique(publishs);

			Arrays.sort(publishs);
		}

		if (publishs.length == 1) {
			return findByG_PL_QT(groupId, publishs[0], questionType, start,
				end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(publishs), questionType
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(publishs), questionType,
					
					start, end, orderByComparator
				};
		}

		List<Question> list = null;

		if (retrieveFromCache) {
			list = (List<Question>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Question question : list) {
					if ((groupId != question.getGroupId()) ||
							!ArrayUtil.contains(publishs, question.getPublish()) ||
							!Objects.equals(questionType,
								question.getQuestionType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_QT_GROUPID_2);

			if (publishs.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_PL_QT_PUBLISH_7);

				query.append(StringUtil.merge(publishs));

				query.append(")");

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindQuestionType = false;

			if (questionType == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_1);
			}
			else if (questionType.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_3);
			}
			else {
				bindQuestionType = true;

				query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(QuestionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindQuestionType) {
					qPos.add(questionType);
				}

				if (!pagination) {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT,
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
	 * Removes all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 */
	@Override
	public void removeByG_PL_QT(long groupId, int publish, String questionType) {
		for (Question question : findByG_PL_QT(groupId, publish, questionType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(question);
		}
	}

	/**
	 * Returns the number of questions where groupId = &#63; and publish = &#63; and questionType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @return the number of matching questions
	 */
	@Override
	public int countByG_PL_QT(long groupId, int publish, String questionType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_PL_QT;

		Object[] finderArgs = new Object[] { groupId, publish, questionType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_QT_GROUPID_2);

			query.append(_FINDER_COLUMN_G_PL_QT_PUBLISH_2);

			boolean bindQuestionType = false;

			if (questionType == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_1);
			}
			else if (questionType.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_3);
			}
			else {
				bindQuestionType = true;

				query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(publish);

				if (bindQuestionType) {
					qPos.add(questionType);
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

	/**
	 * Returns the number of questions where groupId = &#63; and publish = any &#63; and questionType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param questionType the question type
	 * @return the number of matching questions
	 */
	@Override
	public int countByG_PL_QT(long groupId, int[] publishs, String questionType) {
		if (publishs == null) {
			publishs = new int[0];
		}
		else if (publishs.length > 1) {
			publishs = ArrayUtil.unique(publishs);

			Arrays.sort(publishs);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(publishs), questionType
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL_QT,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_QT_GROUPID_2);

			if (publishs.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_PL_QT_PUBLISH_7);

				query.append(StringUtil.merge(publishs));

				query.append(")");

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindQuestionType = false;

			if (questionType == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_1);
			}
			else if (questionType.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_3);
			}
			else {
				bindQuestionType = true;

				query.append(_FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_2);
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

				if (bindQuestionType) {
					qPos.add(questionType);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL_QT,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL_QT,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_PL_QT_GROUPID_2 = "question.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_PL_QT_PUBLISH_2 = "question.publish = ? AND ";
	private static final String _FINDER_COLUMN_G_PL_QT_PUBLISH_7 = "question.publish IN (";
	private static final String _FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_1 = "question.questionType IS NULL";
	private static final String _FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_2 = "question.questionType = ?";
	private static final String _FINDER_COLUMN_G_PL_QT_QUESTIONTYPE_3 = "(question.questionType IS NULL OR question.questionType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT_GC =
		new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, QuestionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_PL_QT_GC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT_GC =
		new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, QuestionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_PL_QT_GC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName()
			},
			QuestionModelImpl.GROUPID_COLUMN_BITMASK |
			QuestionModelImpl.PUBLISH_COLUMN_BITMASK |
			QuestionModelImpl.QUESTIONTYPE_COLUMN_BITMASK |
			QuestionModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			QuestionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_PL_QT_GC = new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_PL_QT_GC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL_QT_GC =
		new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_PL_QT_GC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName()
			});

	/**
	 * Returns all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @return the matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_GC(long groupId, int publish,
		String questionType, String govAgencyCode) {
		return findByG_PL_QT_GC(groupId, publish, questionType, govAgencyCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @return the range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_GC(long groupId, int publish,
		String questionType, String govAgencyCode, int start, int end) {
		return findByG_PL_QT_GC(groupId, publish, questionType, govAgencyCode,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_GC(long groupId, int publish,
		String questionType, String govAgencyCode, int start, int end,
		OrderByComparator<Question> orderByComparator) {
		return findByG_PL_QT_GC(groupId, publish, questionType, govAgencyCode,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_GC(long groupId, int publish,
		String questionType, String govAgencyCode, int start, int end,
		OrderByComparator<Question> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT_GC;
			finderArgs = new Object[] {
					groupId, publish, questionType, govAgencyCode
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT_GC;
			finderArgs = new Object[] {
					groupId, publish, questionType, govAgencyCode,
					
					start, end, orderByComparator
				};
		}

		List<Question> list = null;

		if (retrieveFromCache) {
			list = (List<Question>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Question question : list) {
					if ((groupId != question.getGroupId()) ||
							(publish != question.getPublish()) ||
							!Objects.equals(questionType,
								question.getQuestionType()) ||
							!Objects.equals(govAgencyCode,
								question.getGovAgencyCode())) {
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

			query.append(_SQL_SELECT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_QT_GC_GROUPID_2);

			query.append(_FINDER_COLUMN_G_PL_QT_GC_PUBLISH_2);

			boolean bindQuestionType = false;

			if (questionType == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_1);
			}
			else if (questionType.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_3);
			}
			else {
				bindQuestionType = true;

				query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(QuestionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(publish);

				if (bindQuestionType) {
					qPos.add(questionType);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (!pagination) {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching question
	 * @throws NoSuchQuestionException if a matching question could not be found
	 */
	@Override
	public Question findByG_PL_QT_GC_First(long groupId, int publish,
		String questionType, String govAgencyCode,
		OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException {
		Question question = fetchByG_PL_QT_GC_First(groupId, publish,
				questionType, govAgencyCode, orderByComparator);

		if (question != null) {
			return question;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", publish=");
		msg.append(publish);

		msg.append(", questionType=");
		msg.append(questionType);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append("}");

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the first question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching question, or <code>null</code> if a matching question could not be found
	 */
	@Override
	public Question fetchByG_PL_QT_GC_First(long groupId, int publish,
		String questionType, String govAgencyCode,
		OrderByComparator<Question> orderByComparator) {
		List<Question> list = findByG_PL_QT_GC(groupId, publish, questionType,
				govAgencyCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching question
	 * @throws NoSuchQuestionException if a matching question could not be found
	 */
	@Override
	public Question findByG_PL_QT_GC_Last(long groupId, int publish,
		String questionType, String govAgencyCode,
		OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException {
		Question question = fetchByG_PL_QT_GC_Last(groupId, publish,
				questionType, govAgencyCode, orderByComparator);

		if (question != null) {
			return question;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", publish=");
		msg.append(publish);

		msg.append(", questionType=");
		msg.append(questionType);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append("}");

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the last question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching question, or <code>null</code> if a matching question could not be found
	 */
	@Override
	public Question fetchByG_PL_QT_GC_Last(long groupId, int publish,
		String questionType, String govAgencyCode,
		OrderByComparator<Question> orderByComparator) {
		int count = countByG_PL_QT_GC(groupId, publish, questionType,
				govAgencyCode);

		if (count == 0) {
			return null;
		}

		List<Question> list = findByG_PL_QT_GC(groupId, publish, questionType,
				govAgencyCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the questions before and after the current question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * @param questionId the primary key of the current question
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next question
	 * @throws NoSuchQuestionException if a question with the primary key could not be found
	 */
	@Override
	public Question[] findByG_PL_QT_GC_PrevAndNext(long questionId,
		long groupId, int publish, String questionType, String govAgencyCode,
		OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException {
		Question question = findByPrimaryKey(questionId);

		Session session = null;

		try {
			session = openSession();

			Question[] array = new QuestionImpl[3];

			array[0] = getByG_PL_QT_GC_PrevAndNext(session, question, groupId,
					publish, questionType, govAgencyCode, orderByComparator,
					true);

			array[1] = question;

			array[2] = getByG_PL_QT_GC_PrevAndNext(session, question, groupId,
					publish, questionType, govAgencyCode, orderByComparator,
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

	protected Question getByG_PL_QT_GC_PrevAndNext(Session session,
		Question question, long groupId, int publish, String questionType,
		String govAgencyCode, OrderByComparator<Question> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_QUESTION_WHERE);

		query.append(_FINDER_COLUMN_G_PL_QT_GC_GROUPID_2);

		query.append(_FINDER_COLUMN_G_PL_QT_GC_PUBLISH_2);

		boolean bindQuestionType = false;

		if (questionType == null) {
			query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_1);
		}
		else if (questionType.equals("")) {
			query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_3);
		}
		else {
			bindQuestionType = true;

			query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_2);
		}

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_2);
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
			query.append(QuestionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(publish);

		if (bindQuestionType) {
			qPos.add(questionType);
		}

		if (bindGovAgencyCode) {
			qPos.add(govAgencyCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(question);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Question> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the questions where groupId = &#63; and publish = any &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @return the matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_GC(long groupId, int[] publishs,
		String questionType, String govAgencyCode) {
		return findByG_PL_QT_GC(groupId, publishs, questionType, govAgencyCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the questions where groupId = &#63; and publish = any &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @return the range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_GC(long groupId, int[] publishs,
		String questionType, String govAgencyCode, int start, int end) {
		return findByG_PL_QT_GC(groupId, publishs, questionType, govAgencyCode,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = any &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_GC(long groupId, int[] publishs,
		String questionType, String govAgencyCode, int start, int end,
		OrderByComparator<Question> orderByComparator) {
		return findByG_PL_QT_GC(groupId, publishs, questionType, govAgencyCode,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_GC(long groupId, int[] publishs,
		String questionType, String govAgencyCode, int start, int end,
		OrderByComparator<Question> orderByComparator, boolean retrieveFromCache) {
		if (publishs == null) {
			publishs = new int[0];
		}
		else if (publishs.length > 1) {
			publishs = ArrayUtil.unique(publishs);

			Arrays.sort(publishs);
		}

		if (publishs.length == 1) {
			return findByG_PL_QT_GC(groupId, publishs[0], questionType,
				govAgencyCode, start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(publishs), questionType,
					govAgencyCode
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(publishs), questionType,
					govAgencyCode,
					
					start, end, orderByComparator
				};
		}

		List<Question> list = null;

		if (retrieveFromCache) {
			list = (List<Question>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT_GC,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Question question : list) {
					if ((groupId != question.getGroupId()) ||
							!ArrayUtil.contains(publishs, question.getPublish()) ||
							!Objects.equals(questionType,
								question.getQuestionType()) ||
							!Objects.equals(govAgencyCode,
								question.getGovAgencyCode())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_QT_GC_GROUPID_2);

			if (publishs.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_PL_QT_GC_PUBLISH_7);

				query.append(StringUtil.merge(publishs));

				query.append(")");

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindQuestionType = false;

			if (questionType == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_1);
			}
			else if (questionType.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_3);
			}
			else {
				bindQuestionType = true;

				query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(QuestionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindQuestionType) {
					qPos.add(questionType);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (!pagination) {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT_GC,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT_GC,
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
	 * Removes all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 */
	@Override
	public void removeByG_PL_QT_GC(long groupId, int publish,
		String questionType, String govAgencyCode) {
		for (Question question : findByG_PL_QT_GC(groupId, publish,
				questionType, govAgencyCode, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(question);
		}
	}

	/**
	 * Returns the number of questions where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @return the number of matching questions
	 */
	@Override
	public int countByG_PL_QT_GC(long groupId, int publish,
		String questionType, String govAgencyCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_PL_QT_GC;

		Object[] finderArgs = new Object[] {
				groupId, publish, questionType, govAgencyCode
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_QT_GC_GROUPID_2);

			query.append(_FINDER_COLUMN_G_PL_QT_GC_PUBLISH_2);

			boolean bindQuestionType = false;

			if (questionType == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_1);
			}
			else if (questionType.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_3);
			}
			else {
				bindQuestionType = true;

				query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(publish);

				if (bindQuestionType) {
					qPos.add(questionType);
				}

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

	/**
	 * Returns the number of questions where groupId = &#63; and publish = any &#63; and questionType = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param questionType the question type
	 * @param govAgencyCode the gov agency code
	 * @return the number of matching questions
	 */
	@Override
	public int countByG_PL_QT_GC(long groupId, int[] publishs,
		String questionType, String govAgencyCode) {
		if (publishs == null) {
			publishs = new int[0];
		}
		else if (publishs.length > 1) {
			publishs = ArrayUtil.unique(publishs);

			Arrays.sort(publishs);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(publishs), questionType, govAgencyCode
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL_QT_GC,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_QT_GC_GROUPID_2);

			if (publishs.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_PL_QT_GC_PUBLISH_7);

				query.append(StringUtil.merge(publishs));

				query.append(")");

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindQuestionType = false;

			if (questionType == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_1);
			}
			else if (questionType.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_3);
			}
			else {
				bindQuestionType = true;

				query.append(_FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_2);
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

				if (bindQuestionType) {
					qPos.add(questionType);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL_QT_GC,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL_QT_GC,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_PL_QT_GC_GROUPID_2 = "question.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_PL_QT_GC_PUBLISH_2 = "question.publish = ? AND ";
	private static final String _FINDER_COLUMN_G_PL_QT_GC_PUBLISH_7 = "question.publish IN (";
	private static final String _FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_1 = "question.questionType IS NULL AND ";
	private static final String _FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_2 = "question.questionType = ? AND ";
	private static final String _FINDER_COLUMN_G_PL_QT_GC_QUESTIONTYPE_3 = "(question.questionType IS NULL OR question.questionType = '') AND ";
	private static final String _FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_1 = "question.govAgencyCode IS NULL";
	private static final String _FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_2 = "question.govAgencyCode = ?";
	private static final String _FINDER_COLUMN_G_PL_QT_GC_GOVAGENCYCODE_3 = "(question.govAgencyCode IS NULL OR question.govAgencyCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT_SDC =
		new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, QuestionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_PL_QT_SDC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT_SDC =
		new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, QuestionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_PL_QT_SDC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName()
			},
			QuestionModelImpl.GROUPID_COLUMN_BITMASK |
			QuestionModelImpl.PUBLISH_COLUMN_BITMASK |
			QuestionModelImpl.QUESTIONTYPE_COLUMN_BITMASK |
			QuestionModelImpl.SUBDOMAINCODE_COLUMN_BITMASK |
			QuestionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_PL_QT_SDC = new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_PL_QT_SDC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL_QT_SDC =
		new FinderPath(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_PL_QT_SDC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName()
			});

	/**
	 * Returns all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @return the matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_SDC(long groupId, int publish,
		String questionType, String subDomainCode) {
		return findByG_PL_QT_SDC(groupId, publish, questionType, subDomainCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @return the range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_SDC(long groupId, int publish,
		String questionType, String subDomainCode, int start, int end) {
		return findByG_PL_QT_SDC(groupId, publish, questionType, subDomainCode,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_SDC(long groupId, int publish,
		String questionType, String subDomainCode, int start, int end,
		OrderByComparator<Question> orderByComparator) {
		return findByG_PL_QT_SDC(groupId, publish, questionType, subDomainCode,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_SDC(long groupId, int publish,
		String questionType, String subDomainCode, int start, int end,
		OrderByComparator<Question> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT_SDC;
			finderArgs = new Object[] {
					groupId, publish, questionType, subDomainCode
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT_SDC;
			finderArgs = new Object[] {
					groupId, publish, questionType, subDomainCode,
					
					start, end, orderByComparator
				};
		}

		List<Question> list = null;

		if (retrieveFromCache) {
			list = (List<Question>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Question question : list) {
					if ((groupId != question.getGroupId()) ||
							(publish != question.getPublish()) ||
							!Objects.equals(questionType,
								question.getQuestionType()) ||
							!Objects.equals(subDomainCode,
								question.getSubDomainCode())) {
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

			query.append(_SQL_SELECT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_QT_SDC_GROUPID_2);

			query.append(_FINDER_COLUMN_G_PL_QT_SDC_PUBLISH_2);

			boolean bindQuestionType = false;

			if (questionType == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_1);
			}
			else if (questionType.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_3);
			}
			else {
				bindQuestionType = true;

				query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_2);
			}

			boolean bindSubDomainCode = false;

			if (subDomainCode == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_1);
			}
			else if (subDomainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_3);
			}
			else {
				bindSubDomainCode = true;

				query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(QuestionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(publish);

				if (bindQuestionType) {
					qPos.add(questionType);
				}

				if (bindSubDomainCode) {
					qPos.add(subDomainCode);
				}

				if (!pagination) {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching question
	 * @throws NoSuchQuestionException if a matching question could not be found
	 */
	@Override
	public Question findByG_PL_QT_SDC_First(long groupId, int publish,
		String questionType, String subDomainCode,
		OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException {
		Question question = fetchByG_PL_QT_SDC_First(groupId, publish,
				questionType, subDomainCode, orderByComparator);

		if (question != null) {
			return question;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", publish=");
		msg.append(publish);

		msg.append(", questionType=");
		msg.append(questionType);

		msg.append(", subDomainCode=");
		msg.append(subDomainCode);

		msg.append("}");

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the first question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching question, or <code>null</code> if a matching question could not be found
	 */
	@Override
	public Question fetchByG_PL_QT_SDC_First(long groupId, int publish,
		String questionType, String subDomainCode,
		OrderByComparator<Question> orderByComparator) {
		List<Question> list = findByG_PL_QT_SDC(groupId, publish, questionType,
				subDomainCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching question
	 * @throws NoSuchQuestionException if a matching question could not be found
	 */
	@Override
	public Question findByG_PL_QT_SDC_Last(long groupId, int publish,
		String questionType, String subDomainCode,
		OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException {
		Question question = fetchByG_PL_QT_SDC_Last(groupId, publish,
				questionType, subDomainCode, orderByComparator);

		if (question != null) {
			return question;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", publish=");
		msg.append(publish);

		msg.append(", questionType=");
		msg.append(questionType);

		msg.append(", subDomainCode=");
		msg.append(subDomainCode);

		msg.append("}");

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the last question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching question, or <code>null</code> if a matching question could not be found
	 */
	@Override
	public Question fetchByG_PL_QT_SDC_Last(long groupId, int publish,
		String questionType, String subDomainCode,
		OrderByComparator<Question> orderByComparator) {
		int count = countByG_PL_QT_SDC(groupId, publish, questionType,
				subDomainCode);

		if (count == 0) {
			return null;
		}

		List<Question> list = findByG_PL_QT_SDC(groupId, publish, questionType,
				subDomainCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the questions before and after the current question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * @param questionId the primary key of the current question
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next question
	 * @throws NoSuchQuestionException if a question with the primary key could not be found
	 */
	@Override
	public Question[] findByG_PL_QT_SDC_PrevAndNext(long questionId,
		long groupId, int publish, String questionType, String subDomainCode,
		OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException {
		Question question = findByPrimaryKey(questionId);

		Session session = null;

		try {
			session = openSession();

			Question[] array = new QuestionImpl[3];

			array[0] = getByG_PL_QT_SDC_PrevAndNext(session, question, groupId,
					publish, questionType, subDomainCode, orderByComparator,
					true);

			array[1] = question;

			array[2] = getByG_PL_QT_SDC_PrevAndNext(session, question, groupId,
					publish, questionType, subDomainCode, orderByComparator,
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

	protected Question getByG_PL_QT_SDC_PrevAndNext(Session session,
		Question question, long groupId, int publish, String questionType,
		String subDomainCode, OrderByComparator<Question> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_QUESTION_WHERE);

		query.append(_FINDER_COLUMN_G_PL_QT_SDC_GROUPID_2);

		query.append(_FINDER_COLUMN_G_PL_QT_SDC_PUBLISH_2);

		boolean bindQuestionType = false;

		if (questionType == null) {
			query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_1);
		}
		else if (questionType.equals("")) {
			query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_3);
		}
		else {
			bindQuestionType = true;

			query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_2);
		}

		boolean bindSubDomainCode = false;

		if (subDomainCode == null) {
			query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_1);
		}
		else if (subDomainCode.equals("")) {
			query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_3);
		}
		else {
			bindSubDomainCode = true;

			query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_2);
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
			query.append(QuestionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(publish);

		if (bindQuestionType) {
			qPos.add(questionType);
		}

		if (bindSubDomainCode) {
			qPos.add(subDomainCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(question);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Question> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the questions where groupId = &#63; and publish = any &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @return the matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_SDC(long groupId, int[] publishs,
		String questionType, String subDomainCode) {
		return findByG_PL_QT_SDC(groupId, publishs, questionType,
			subDomainCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the questions where groupId = &#63; and publish = any &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @return the range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_SDC(long groupId, int[] publishs,
		String questionType, String subDomainCode, int start, int end) {
		return findByG_PL_QT_SDC(groupId, publishs, questionType,
			subDomainCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = any &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_SDC(long groupId, int[] publishs,
		String questionType, String subDomainCode, int start, int end,
		OrderByComparator<Question> orderByComparator) {
		return findByG_PL_QT_SDC(groupId, publishs, questionType,
			subDomainCode, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching questions
	 */
	@Override
	public List<Question> findByG_PL_QT_SDC(long groupId, int[] publishs,
		String questionType, String subDomainCode, int start, int end,
		OrderByComparator<Question> orderByComparator, boolean retrieveFromCache) {
		if (publishs == null) {
			publishs = new int[0];
		}
		else if (publishs.length > 1) {
			publishs = ArrayUtil.unique(publishs);

			Arrays.sort(publishs);
		}

		if (publishs.length == 1) {
			return findByG_PL_QT_SDC(groupId, publishs[0], questionType,
				subDomainCode, start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(publishs), questionType,
					subDomainCode
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(publishs), questionType,
					subDomainCode,
					
					start, end, orderByComparator
				};
		}

		List<Question> list = null;

		if (retrieveFromCache) {
			list = (List<Question>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT_SDC,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Question question : list) {
					if ((groupId != question.getGroupId()) ||
							!ArrayUtil.contains(publishs, question.getPublish()) ||
							!Objects.equals(questionType,
								question.getQuestionType()) ||
							!Objects.equals(subDomainCode,
								question.getSubDomainCode())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_QT_SDC_GROUPID_2);

			if (publishs.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_PL_QT_SDC_PUBLISH_7);

				query.append(StringUtil.merge(publishs));

				query.append(")");

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindQuestionType = false;

			if (questionType == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_1);
			}
			else if (questionType.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_3);
			}
			else {
				bindQuestionType = true;

				query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_2);
			}

			boolean bindSubDomainCode = false;

			if (subDomainCode == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_1);
			}
			else if (subDomainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_3);
			}
			else {
				bindSubDomainCode = true;

				query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(QuestionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindQuestionType) {
					qPos.add(questionType);
				}

				if (bindSubDomainCode) {
					qPos.add(subDomainCode);
				}

				if (!pagination) {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT_SDC,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PL_QT_SDC,
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
	 * Removes all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 */
	@Override
	public void removeByG_PL_QT_SDC(long groupId, int publish,
		String questionType, String subDomainCode) {
		for (Question question : findByG_PL_QT_SDC(groupId, publish,
				questionType, subDomainCode, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(question);
		}
	}

	/**
	 * Returns the number of questions where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publish the publish
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @return the number of matching questions
	 */
	@Override
	public int countByG_PL_QT_SDC(long groupId, int publish,
		String questionType, String subDomainCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_PL_QT_SDC;

		Object[] finderArgs = new Object[] {
				groupId, publish, questionType, subDomainCode
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_QT_SDC_GROUPID_2);

			query.append(_FINDER_COLUMN_G_PL_QT_SDC_PUBLISH_2);

			boolean bindQuestionType = false;

			if (questionType == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_1);
			}
			else if (questionType.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_3);
			}
			else {
				bindQuestionType = true;

				query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_2);
			}

			boolean bindSubDomainCode = false;

			if (subDomainCode == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_1);
			}
			else if (subDomainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_3);
			}
			else {
				bindSubDomainCode = true;

				query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(publish);

				if (bindQuestionType) {
					qPos.add(questionType);
				}

				if (bindSubDomainCode) {
					qPos.add(subDomainCode);
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

	/**
	 * Returns the number of questions where groupId = &#63; and publish = any &#63; and questionType = &#63; and subDomainCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param publishs the publishs
	 * @param questionType the question type
	 * @param subDomainCode the sub domain code
	 * @return the number of matching questions
	 */
	@Override
	public int countByG_PL_QT_SDC(long groupId, int[] publishs,
		String questionType, String subDomainCode) {
		if (publishs == null) {
			publishs = new int[0];
		}
		else if (publishs.length > 1) {
			publishs = ArrayUtil.unique(publishs);

			Arrays.sort(publishs);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(publishs), questionType, subDomainCode
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL_QT_SDC,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_QUESTION_WHERE);

			query.append(_FINDER_COLUMN_G_PL_QT_SDC_GROUPID_2);

			if (publishs.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_PL_QT_SDC_PUBLISH_7);

				query.append(StringUtil.merge(publishs));

				query.append(")");

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindQuestionType = false;

			if (questionType == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_1);
			}
			else if (questionType.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_3);
			}
			else {
				bindQuestionType = true;

				query.append(_FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_2);
			}

			boolean bindSubDomainCode = false;

			if (subDomainCode == null) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_1);
			}
			else if (subDomainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_3);
			}
			else {
				bindSubDomainCode = true;

				query.append(_FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_2);
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

				if (bindQuestionType) {
					qPos.add(questionType);
				}

				if (bindSubDomainCode) {
					qPos.add(subDomainCode);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL_QT_SDC,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_PL_QT_SDC,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_PL_QT_SDC_GROUPID_2 = "question.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_PL_QT_SDC_PUBLISH_2 = "question.publish = ? AND ";
	private static final String _FINDER_COLUMN_G_PL_QT_SDC_PUBLISH_7 = "question.publish IN (";
	private static final String _FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_1 = "question.questionType IS NULL AND ";
	private static final String _FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_2 = "question.questionType = ? AND ";
	private static final String _FINDER_COLUMN_G_PL_QT_SDC_QUESTIONTYPE_3 = "(question.questionType IS NULL OR question.questionType = '') AND ";
	private static final String _FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_1 = "question.subDomainCode IS NULL";
	private static final String _FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_2 = "question.subDomainCode = ?";
	private static final String _FINDER_COLUMN_G_PL_QT_SDC_SUBDOMAINCODE_3 = "(question.subDomainCode IS NULL OR question.subDomainCode = '')";

	public QuestionPersistenceImpl() {
		setModelClass(Question.class);
	}

	/**
	 * Caches the question in the entity cache if it is enabled.
	 *
	 * @param question the question
	 */
	@Override
	public void cacheResult(Question question) {
		entityCache.putResult(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionImpl.class, question.getPrimaryKey(), question);

		question.resetOriginalValues();
	}

	/**
	 * Caches the questions in the entity cache if it is enabled.
	 *
	 * @param questions the questions
	 */
	@Override
	public void cacheResult(List<Question> questions) {
		for (Question question : questions) {
			if (entityCache.getResult(QuestionModelImpl.ENTITY_CACHE_ENABLED,
						QuestionImpl.class, question.getPrimaryKey()) == null) {
				cacheResult(question);
			}
			else {
				question.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all questions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(QuestionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the question.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Question question) {
		entityCache.removeResult(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionImpl.class, question.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Question> questions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Question question : questions) {
			entityCache.removeResult(QuestionModelImpl.ENTITY_CACHE_ENABLED,
				QuestionImpl.class, question.getPrimaryKey());
		}
	}

	/**
	 * Creates a new question with the primary key. Does not add the question to the database.
	 *
	 * @param questionId the primary key for the new question
	 * @return the new question
	 */
	@Override
	public Question create(long questionId) {
		Question question = new QuestionImpl();

		question.setNew(true);
		question.setPrimaryKey(questionId);

		question.setCompanyId(companyProvider.getCompanyId());

		return question;
	}

	/**
	 * Removes the question with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param questionId the primary key of the question
	 * @return the question that was removed
	 * @throws NoSuchQuestionException if a question with the primary key could not be found
	 */
	@Override
	public Question remove(long questionId) throws NoSuchQuestionException {
		return remove((Serializable)questionId);
	}

	/**
	 * Removes the question with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the question
	 * @return the question that was removed
	 * @throws NoSuchQuestionException if a question with the primary key could not be found
	 */
	@Override
	public Question remove(Serializable primaryKey)
		throws NoSuchQuestionException {
		Session session = null;

		try {
			session = openSession();

			Question question = (Question)session.get(QuestionImpl.class,
					primaryKey);

			if (question == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchQuestionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(question);
		}
		catch (NoSuchQuestionException nsee) {
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
	protected Question removeImpl(Question question) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(question)) {
				question = (Question)session.get(QuestionImpl.class,
						question.getPrimaryKeyObj());
			}

			if (question != null) {
				session.delete(question);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (question != null) {
			clearCache(question);
		}

		return question;
	}

	@Override
	public Question updateImpl(Question question) {
		boolean isNew = question.isNew();

		if (!(question instanceof QuestionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(question.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(question);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in question proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Question implementation " +
				question.getClass());
		}

		QuestionModelImpl questionModelImpl = (QuestionModelImpl)question;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (question.getCreateDate() == null)) {
			if (serviceContext == null) {
				question.setCreateDate(now);
			}
			else {
				question.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!questionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				question.setModifiedDate(now);
			}
			else {
				question.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (question.isNew()) {
				session.save(question);

				question.setNew(false);
			}
			else {
				question = (Question)session.merge(question);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!QuestionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					questionModelImpl.getGroupId(),
					questionModelImpl.getPublish()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PL, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL,
				args);

			args = new Object[] {
					questionModelImpl.getGroupId(),
					questionModelImpl.getPublish(),
					questionModelImpl.getQuestionType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PL_QT, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT,
				args);

			args = new Object[] {
					questionModelImpl.getGroupId(),
					questionModelImpl.getPublish(),
					questionModelImpl.getQuestionType(),
					questionModelImpl.getGovAgencyCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PL_QT_GC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT_GC,
				args);

			args = new Object[] {
					questionModelImpl.getGroupId(),
					questionModelImpl.getPublish(),
					questionModelImpl.getQuestionType(),
					questionModelImpl.getSubDomainCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PL_QT_SDC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT_SDC,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((questionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						questionModelImpl.getOriginalGroupId(),
						questionModelImpl.getOriginalPublish()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL,
					args);

				args = new Object[] {
						questionModelImpl.getGroupId(),
						questionModelImpl.getPublish()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL,
					args);
			}

			if ((questionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						questionModelImpl.getOriginalGroupId(),
						questionModelImpl.getOriginalPublish(),
						questionModelImpl.getOriginalQuestionType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PL_QT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT,
					args);

				args = new Object[] {
						questionModelImpl.getGroupId(),
						questionModelImpl.getPublish(),
						questionModelImpl.getQuestionType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PL_QT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT,
					args);
			}

			if ((questionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT_GC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						questionModelImpl.getOriginalGroupId(),
						questionModelImpl.getOriginalPublish(),
						questionModelImpl.getOriginalQuestionType(),
						questionModelImpl.getOriginalGovAgencyCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PL_QT_GC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT_GC,
					args);

				args = new Object[] {
						questionModelImpl.getGroupId(),
						questionModelImpl.getPublish(),
						questionModelImpl.getQuestionType(),
						questionModelImpl.getGovAgencyCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PL_QT_GC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT_GC,
					args);
			}

			if ((questionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT_SDC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						questionModelImpl.getOriginalGroupId(),
						questionModelImpl.getOriginalPublish(),
						questionModelImpl.getOriginalQuestionType(),
						questionModelImpl.getOriginalSubDomainCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PL_QT_SDC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT_SDC,
					args);

				args = new Object[] {
						questionModelImpl.getGroupId(),
						questionModelImpl.getPublish(),
						questionModelImpl.getQuestionType(),
						questionModelImpl.getSubDomainCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PL_QT_SDC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PL_QT_SDC,
					args);
			}
		}

		entityCache.putResult(QuestionModelImpl.ENTITY_CACHE_ENABLED,
			QuestionImpl.class, question.getPrimaryKey(), question, false);

		question.resetOriginalValues();

		return question;
	}

	/**
	 * Returns the question with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the question
	 * @return the question
	 * @throws NoSuchQuestionException if a question with the primary key could not be found
	 */
	@Override
	public Question findByPrimaryKey(Serializable primaryKey)
		throws NoSuchQuestionException {
		Question question = fetchByPrimaryKey(primaryKey);

		if (question == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchQuestionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return question;
	}

	/**
	 * Returns the question with the primary key or throws a {@link NoSuchQuestionException} if it could not be found.
	 *
	 * @param questionId the primary key of the question
	 * @return the question
	 * @throws NoSuchQuestionException if a question with the primary key could not be found
	 */
	@Override
	public Question findByPrimaryKey(long questionId)
		throws NoSuchQuestionException {
		return findByPrimaryKey((Serializable)questionId);
	}

	/**
	 * Returns the question with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the question
	 * @return the question, or <code>null</code> if a question with the primary key could not be found
	 */
	@Override
	public Question fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(QuestionModelImpl.ENTITY_CACHE_ENABLED,
				QuestionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Question question = (Question)serializable;

		if (question == null) {
			Session session = null;

			try {
				session = openSession();

				question = (Question)session.get(QuestionImpl.class, primaryKey);

				if (question != null) {
					cacheResult(question);
				}
				else {
					entityCache.putResult(QuestionModelImpl.ENTITY_CACHE_ENABLED,
						QuestionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(QuestionModelImpl.ENTITY_CACHE_ENABLED,
					QuestionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return question;
	}

	/**
	 * Returns the question with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param questionId the primary key of the question
	 * @return the question, or <code>null</code> if a question with the primary key could not be found
	 */
	@Override
	public Question fetchByPrimaryKey(long questionId) {
		return fetchByPrimaryKey((Serializable)questionId);
	}

	@Override
	public Map<Serializable, Question> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Question> map = new HashMap<Serializable, Question>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Question question = fetchByPrimaryKey(primaryKey);

			if (question != null) {
				map.put(primaryKey, question);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(QuestionModelImpl.ENTITY_CACHE_ENABLED,
					QuestionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Question)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_QUESTION_WHERE_PKS_IN);

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

			for (Question question : (List<Question>)q.list()) {
				map.put(question.getPrimaryKeyObj(), question);

				cacheResult(question);

				uncachedPrimaryKeys.remove(question.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(QuestionModelImpl.ENTITY_CACHE_ENABLED,
					QuestionImpl.class, primaryKey, nullModel);
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
	 * Returns all the questions.
	 *
	 * @return the questions
	 */
	@Override
	public List<Question> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the questions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @return the range of questions
	 */
	@Override
	public List<Question> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the questions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of questions
	 */
	@Override
	public List<Question> findAll(int start, int end,
		OrderByComparator<Question> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the questions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of questions
	 */
	@Override
	public List<Question> findAll(int start, int end,
		OrderByComparator<Question> orderByComparator, boolean retrieveFromCache) {
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

		List<Question> list = null;

		if (retrieveFromCache) {
			list = (List<Question>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_QUESTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_QUESTION;

				if (pagination) {
					sql = sql.concat(QuestionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Question>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the questions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Question question : findAll()) {
			remove(question);
		}
	}

	/**
	 * Returns the number of questions.
	 *
	 * @return the number of questions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_QUESTION);

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
		return QuestionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the question persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(QuestionImpl.class.getName());
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
	private static final String _SQL_SELECT_QUESTION = "SELECT question FROM Question question";
	private static final String _SQL_SELECT_QUESTION_WHERE_PKS_IN = "SELECT question FROM Question question WHERE questionId IN (";
	private static final String _SQL_SELECT_QUESTION_WHERE = "SELECT question FROM Question question WHERE ";
	private static final String _SQL_COUNT_QUESTION = "SELECT COUNT(question) FROM Question question";
	private static final String _SQL_COUNT_QUESTION_WHERE = "SELECT COUNT(question) FROM Question question WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "question.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Question exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Question exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(QuestionPersistenceImpl.class);
}