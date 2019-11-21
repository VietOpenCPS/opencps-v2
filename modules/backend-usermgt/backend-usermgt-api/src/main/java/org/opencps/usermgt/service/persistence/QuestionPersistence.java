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

package org.opencps.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.usermgt.exception.NoSuchQuestionException;
import org.opencps.usermgt.model.Question;

/**
 * The persistence interface for the question service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.QuestionPersistenceImpl
 * @see QuestionUtil
 * @generated
 */
@ProviderType
public interface QuestionPersistence extends BasePersistence<Question> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link QuestionUtil} to access the question persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the questions where groupId = &#63; and publish = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @return the matching questions
	*/
	public java.util.List<Question> findByG_PL(long groupId, int publish);

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
	public java.util.List<Question> findByG_PL(long groupId, int publish,
		int start, int end);

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
	public java.util.List<Question> findByG_PL(long groupId, int publish,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public java.util.List<Question> findByG_PL(long groupId, int publish,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first question in the ordered set where groupId = &#63; and publish = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching question
	* @throws NoSuchQuestionException if a matching question could not be found
	*/
	public Question findByG_PL_First(long groupId, int publish,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException;

	/**
	* Returns the first question in the ordered set where groupId = &#63; and publish = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching question, or <code>null</code> if a matching question could not be found
	*/
	public Question fetchByG_PL_First(long groupId, int publish,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

	/**
	* Returns the last question in the ordered set where groupId = &#63; and publish = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching question
	* @throws NoSuchQuestionException if a matching question could not be found
	*/
	public Question findByG_PL_Last(long groupId, int publish,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException;

	/**
	* Returns the last question in the ordered set where groupId = &#63; and publish = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching question, or <code>null</code> if a matching question could not be found
	*/
	public Question fetchByG_PL_Last(long groupId, int publish,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public Question[] findByG_PL_PrevAndNext(long questionId, long groupId,
		int publish,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException;

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
	public java.util.List<Question> findByG_PL(long groupId, int[] publishs);

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
	public java.util.List<Question> findByG_PL(long groupId, int[] publishs,
		int start, int end);

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
	public java.util.List<Question> findByG_PL(long groupId, int[] publishs,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public java.util.List<Question> findByG_PL(long groupId, int[] publishs,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the questions where groupId = &#63; and publish = &#63; from the database.
	*
	* @param groupId the group ID
	* @param publish the publish
	*/
	public void removeByG_PL(long groupId, int publish);

	/**
	* Returns the number of questions where groupId = &#63; and publish = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @return the number of matching questions
	*/
	public int countByG_PL(long groupId, int publish);

	/**
	* Returns the number of questions where groupId = &#63; and publish = any &#63;.
	*
	* @param groupId the group ID
	* @param publishs the publishs
	* @return the number of matching questions
	*/
	public int countByG_PL(long groupId, int[] publishs);

	/**
	* Returns all the questions where groupId = &#63; and publish = &#63; and questionType = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param questionType the question type
	* @return the matching questions
	*/
	public java.util.List<Question> findByG_PL_QT(long groupId, int publish,
		String questionType);

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
	public java.util.List<Question> findByG_PL_QT(long groupId, int publish,
		String questionType, int start, int end);

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
	public java.util.List<Question> findByG_PL_QT(long groupId, int publish,
		String questionType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public java.util.List<Question> findByG_PL_QT(long groupId, int publish,
		String questionType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator,
		boolean retrieveFromCache);

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
	public Question findByG_PL_QT_First(long groupId, int publish,
		String questionType,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException;

	/**
	* Returns the first question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param questionType the question type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching question, or <code>null</code> if a matching question could not be found
	*/
	public Question fetchByG_PL_QT_First(long groupId, int publish,
		String questionType,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public Question findByG_PL_QT_Last(long groupId, int publish,
		String questionType,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException;

	/**
	* Returns the last question in the ordered set where groupId = &#63; and publish = &#63; and questionType = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param questionType the question type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching question, or <code>null</code> if a matching question could not be found
	*/
	public Question fetchByG_PL_QT_Last(long groupId, int publish,
		String questionType,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public Question[] findByG_PL_QT_PrevAndNext(long questionId, long groupId,
		int publish, String questionType,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException;

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
	public java.util.List<Question> findByG_PL_QT(long groupId, int[] publishs,
		String questionType);

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
	public java.util.List<Question> findByG_PL_QT(long groupId, int[] publishs,
		String questionType, int start, int end);

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
	public java.util.List<Question> findByG_PL_QT(long groupId, int[] publishs,
		String questionType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public java.util.List<Question> findByG_PL_QT(long groupId, int[] publishs,
		String questionType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param questionType the question type
	*/
	public void removeByG_PL_QT(long groupId, int publish, String questionType);

	/**
	* Returns the number of questions where groupId = &#63; and publish = &#63; and questionType = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param questionType the question type
	* @return the number of matching questions
	*/
	public int countByG_PL_QT(long groupId, int publish, String questionType);

	/**
	* Returns the number of questions where groupId = &#63; and publish = any &#63; and questionType = &#63;.
	*
	* @param groupId the group ID
	* @param publishs the publishs
	* @param questionType the question type
	* @return the number of matching questions
	*/
	public int countByG_PL_QT(long groupId, int[] publishs, String questionType);

	/**
	* Returns all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param questionType the question type
	* @param govAgencyCode the gov agency code
	* @return the matching questions
	*/
	public java.util.List<Question> findByG_PL_QT_GC(long groupId, int publish,
		String questionType, String govAgencyCode);

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
	public java.util.List<Question> findByG_PL_QT_GC(long groupId, int publish,
		String questionType, String govAgencyCode, int start, int end);

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
	public java.util.List<Question> findByG_PL_QT_GC(long groupId, int publish,
		String questionType, String govAgencyCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public java.util.List<Question> findByG_PL_QT_GC(long groupId, int publish,
		String questionType, String govAgencyCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator,
		boolean retrieveFromCache);

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
	public Question findByG_PL_QT_GC_First(long groupId, int publish,
		String questionType, String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException;

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
	public Question fetchByG_PL_QT_GC_First(long groupId, int publish,
		String questionType, String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public Question findByG_PL_QT_GC_Last(long groupId, int publish,
		String questionType, String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException;

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
	public Question fetchByG_PL_QT_GC_Last(long groupId, int publish,
		String questionType, String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public Question[] findByG_PL_QT_GC_PrevAndNext(long questionId,
		long groupId, int publish, String questionType, String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException;

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
	public java.util.List<Question> findByG_PL_QT_GC(long groupId,
		int[] publishs, String questionType, String govAgencyCode);

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
	public java.util.List<Question> findByG_PL_QT_GC(long groupId,
		int[] publishs, String questionType, String govAgencyCode, int start,
		int end);

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
	public java.util.List<Question> findByG_PL_QT_GC(long groupId,
		int[] publishs, String questionType, String govAgencyCode, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public java.util.List<Question> findByG_PL_QT_GC(long groupId,
		int[] publishs, String questionType, String govAgencyCode, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param questionType the question type
	* @param govAgencyCode the gov agency code
	*/
	public void removeByG_PL_QT_GC(long groupId, int publish,
		String questionType, String govAgencyCode);

	/**
	* Returns the number of questions where groupId = &#63; and publish = &#63; and questionType = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param questionType the question type
	* @param govAgencyCode the gov agency code
	* @return the number of matching questions
	*/
	public int countByG_PL_QT_GC(long groupId, int publish,
		String questionType, String govAgencyCode);

	/**
	* Returns the number of questions where groupId = &#63; and publish = any &#63; and questionType = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param publishs the publishs
	* @param questionType the question type
	* @param govAgencyCode the gov agency code
	* @return the number of matching questions
	*/
	public int countByG_PL_QT_GC(long groupId, int[] publishs,
		String questionType, String govAgencyCode);

	/**
	* Returns all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param questionType the question type
	* @param subDomainCode the sub domain code
	* @return the matching questions
	*/
	public java.util.List<Question> findByG_PL_QT_SDC(long groupId,
		int publish, String questionType, String subDomainCode);

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
	public java.util.List<Question> findByG_PL_QT_SDC(long groupId,
		int publish, String questionType, String subDomainCode, int start,
		int end);

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
	public java.util.List<Question> findByG_PL_QT_SDC(long groupId,
		int publish, String questionType, String subDomainCode, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public java.util.List<Question> findByG_PL_QT_SDC(long groupId,
		int publish, String questionType, String subDomainCode, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator,
		boolean retrieveFromCache);

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
	public Question findByG_PL_QT_SDC_First(long groupId, int publish,
		String questionType, String subDomainCode,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException;

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
	public Question fetchByG_PL_QT_SDC_First(long groupId, int publish,
		String questionType, String subDomainCode,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public Question findByG_PL_QT_SDC_Last(long groupId, int publish,
		String questionType, String subDomainCode,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException;

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
	public Question fetchByG_PL_QT_SDC_Last(long groupId, int publish,
		String questionType, String subDomainCode,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public Question[] findByG_PL_QT_SDC_PrevAndNext(long questionId,
		long groupId, int publish, String questionType, String subDomainCode,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator)
		throws NoSuchQuestionException;

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
	public java.util.List<Question> findByG_PL_QT_SDC(long groupId,
		int[] publishs, String questionType, String subDomainCode);

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
	public java.util.List<Question> findByG_PL_QT_SDC(long groupId,
		int[] publishs, String questionType, String subDomainCode, int start,
		int end);

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
	public java.util.List<Question> findByG_PL_QT_SDC(long groupId,
		int[] publishs, String questionType, String subDomainCode, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public java.util.List<Question> findByG_PL_QT_SDC(long groupId,
		int[] publishs, String questionType, String subDomainCode, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the questions where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param questionType the question type
	* @param subDomainCode the sub domain code
	*/
	public void removeByG_PL_QT_SDC(long groupId, int publish,
		String questionType, String subDomainCode);

	/**
	* Returns the number of questions where groupId = &#63; and publish = &#63; and questionType = &#63; and subDomainCode = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param questionType the question type
	* @param subDomainCode the sub domain code
	* @return the number of matching questions
	*/
	public int countByG_PL_QT_SDC(long groupId, int publish,
		String questionType, String subDomainCode);

	/**
	* Returns the number of questions where groupId = &#63; and publish = any &#63; and questionType = &#63; and subDomainCode = &#63;.
	*
	* @param groupId the group ID
	* @param publishs the publishs
	* @param questionType the question type
	* @param subDomainCode the sub domain code
	* @return the number of matching questions
	*/
	public int countByG_PL_QT_SDC(long groupId, int[] publishs,
		String questionType, String subDomainCode);

	/**
	* Caches the question in the entity cache if it is enabled.
	*
	* @param question the question
	*/
	public void cacheResult(Question question);

	/**
	* Caches the questions in the entity cache if it is enabled.
	*
	* @param questions the questions
	*/
	public void cacheResult(java.util.List<Question> questions);

	/**
	* Creates a new question with the primary key. Does not add the question to the database.
	*
	* @param questionId the primary key for the new question
	* @return the new question
	*/
	public Question create(long questionId);

	/**
	* Removes the question with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param questionId the primary key of the question
	* @return the question that was removed
	* @throws NoSuchQuestionException if a question with the primary key could not be found
	*/
	public Question remove(long questionId) throws NoSuchQuestionException;

	public Question updateImpl(Question question);

	/**
	* Returns the question with the primary key or throws a {@link NoSuchQuestionException} if it could not be found.
	*
	* @param questionId the primary key of the question
	* @return the question
	* @throws NoSuchQuestionException if a question with the primary key could not be found
	*/
	public Question findByPrimaryKey(long questionId)
		throws NoSuchQuestionException;

	/**
	* Returns the question with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param questionId the primary key of the question
	* @return the question, or <code>null</code> if a question with the primary key could not be found
	*/
	public Question fetchByPrimaryKey(long questionId);

	@Override
	public java.util.Map<java.io.Serializable, Question> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the questions.
	*
	* @return the questions
	*/
	public java.util.List<Question> findAll();

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
	public java.util.List<Question> findAll(int start, int end);

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
	public java.util.List<Question> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator);

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
	public java.util.List<Question> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Question> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the questions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of questions.
	*
	* @return the number of questions
	*/
	public int countAll();
}