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

import org.opencps.usermgt.exception.NoSuchAnswerException;
import org.opencps.usermgt.model.Answer;

/**
 * The persistence interface for the answer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.AnswerPersistenceImpl
 * @see AnswerUtil
 * @generated
 */
@ProviderType
public interface AnswerPersistence extends BasePersistence<Answer> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AnswerUtil} to access the answer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the answers where groupId = &#63; and questionId = &#63; and publish = &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param publish the publish
	* @return the matching answers
	*/
	public java.util.List<Answer> findByG_Q_PL(long groupId, long questionId,
		int publish);

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
	public java.util.List<Answer> findByG_Q_PL(long groupId, long questionId,
		int publish, int start, int end);

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
	public java.util.List<Answer> findByG_Q_PL(long groupId, long questionId,
		int publish, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Answer> orderByComparator);

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
	public java.util.List<Answer> findByG_Q_PL(long groupId, long questionId,
		int publish, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Answer> orderByComparator,
		boolean retrieveFromCache);

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
	public Answer findByG_Q_PL_First(long groupId, long questionId,
		int publish,
		com.liferay.portal.kernel.util.OrderByComparator<Answer> orderByComparator)
		throws NoSuchAnswerException;

	/**
	* Returns the first answer in the ordered set where groupId = &#63; and questionId = &#63; and publish = &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param publish the publish
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching answer, or <code>null</code> if a matching answer could not be found
	*/
	public Answer fetchByG_Q_PL_First(long groupId, long questionId,
		int publish,
		com.liferay.portal.kernel.util.OrderByComparator<Answer> orderByComparator);

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
	public Answer findByG_Q_PL_Last(long groupId, long questionId, int publish,
		com.liferay.portal.kernel.util.OrderByComparator<Answer> orderByComparator)
		throws NoSuchAnswerException;

	/**
	* Returns the last answer in the ordered set where groupId = &#63; and questionId = &#63; and publish = &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param publish the publish
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching answer, or <code>null</code> if a matching answer could not be found
	*/
	public Answer fetchByG_Q_PL_Last(long groupId, long questionId,
		int publish,
		com.liferay.portal.kernel.util.OrderByComparator<Answer> orderByComparator);

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
	public Answer[] findByG_Q_PL_PrevAndNext(long answerId, long groupId,
		long questionId, int publish,
		com.liferay.portal.kernel.util.OrderByComparator<Answer> orderByComparator)
		throws NoSuchAnswerException;

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
	public java.util.List<Answer> findByG_Q_PL(long groupId, long questionId,
		int[] publishs);

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
	public java.util.List<Answer> findByG_Q_PL(long groupId, long questionId,
		int[] publishs, int start, int end);

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
	public java.util.List<Answer> findByG_Q_PL(long groupId, long questionId,
		int[] publishs, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Answer> orderByComparator);

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
	public java.util.List<Answer> findByG_Q_PL(long groupId, long questionId,
		int[] publishs, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Answer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the answers where groupId = &#63; and questionId = &#63; and publish = &#63; from the database.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param publish the publish
	*/
	public void removeByG_Q_PL(long groupId, long questionId, int publish);

	/**
	* Returns the number of answers where groupId = &#63; and questionId = &#63; and publish = &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param publish the publish
	* @return the number of matching answers
	*/
	public int countByG_Q_PL(long groupId, long questionId, int publish);

	/**
	* Returns the number of answers where groupId = &#63; and questionId = &#63; and publish = any &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param publishs the publishs
	* @return the number of matching answers
	*/
	public int countByG_Q_PL(long groupId, long questionId, int[] publishs);

	/**
	* Caches the answer in the entity cache if it is enabled.
	*
	* @param answer the answer
	*/
	public void cacheResult(Answer answer);

	/**
	* Caches the answers in the entity cache if it is enabled.
	*
	* @param answers the answers
	*/
	public void cacheResult(java.util.List<Answer> answers);

	/**
	* Creates a new answer with the primary key. Does not add the answer to the database.
	*
	* @param answerId the primary key for the new answer
	* @return the new answer
	*/
	public Answer create(long answerId);

	/**
	* Removes the answer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param answerId the primary key of the answer
	* @return the answer that was removed
	* @throws NoSuchAnswerException if a answer with the primary key could not be found
	*/
	public Answer remove(long answerId) throws NoSuchAnswerException;

	public Answer updateImpl(Answer answer);

	/**
	* Returns the answer with the primary key or throws a {@link NoSuchAnswerException} if it could not be found.
	*
	* @param answerId the primary key of the answer
	* @return the answer
	* @throws NoSuchAnswerException if a answer with the primary key could not be found
	*/
	public Answer findByPrimaryKey(long answerId) throws NoSuchAnswerException;

	/**
	* Returns the answer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param answerId the primary key of the answer
	* @return the answer, or <code>null</code> if a answer with the primary key could not be found
	*/
	public Answer fetchByPrimaryKey(long answerId);

	@Override
	public java.util.Map<java.io.Serializable, Answer> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the answers.
	*
	* @return the answers
	*/
	public java.util.List<Answer> findAll();

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
	public java.util.List<Answer> findAll(int start, int end);

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
	public java.util.List<Answer> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Answer> orderByComparator);

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
	public java.util.List<Answer> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Answer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the answers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of answers.
	*
	* @return the number of answers
	*/
	public int countAll();
}