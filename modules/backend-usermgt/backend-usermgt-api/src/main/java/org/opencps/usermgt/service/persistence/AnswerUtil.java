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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.Answer;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the answer service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.AnswerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see AnswerPersistence
 * @see org.opencps.usermgt.service.persistence.impl.AnswerPersistenceImpl
 * @generated
 */
@ProviderType
public class AnswerUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Answer answer) {
		getPersistence().clearCache(answer);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Answer> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Answer> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Answer> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Answer> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Answer update(Answer answer) {
		return getPersistence().update(answer);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Answer update(Answer answer, ServiceContext serviceContext) {
		return getPersistence().update(answer, serviceContext);
	}

	/**
	* Returns all the answers where groupId = &#63; and questionId = &#63; and publish = &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param publish the publish
	* @return the matching answers
	*/
	public static List<Answer> findByG_Q_PL(long groupId, long questionId,
		int publish) {
		return getPersistence().findByG_Q_PL(groupId, questionId, publish);
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
	public static List<Answer> findByG_Q_PL(long groupId, long questionId,
		int publish, int start, int end) {
		return getPersistence()
				   .findByG_Q_PL(groupId, questionId, publish, start, end);
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
	public static List<Answer> findByG_Q_PL(long groupId, long questionId,
		int publish, int start, int end,
		OrderByComparator<Answer> orderByComparator) {
		return getPersistence()
				   .findByG_Q_PL(groupId, questionId, publish, start, end,
			orderByComparator);
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
	public static List<Answer> findByG_Q_PL(long groupId, long questionId,
		int publish, int start, int end,
		OrderByComparator<Answer> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_Q_PL(groupId, questionId, publish, start, end,
			orderByComparator, retrieveFromCache);
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
	public static Answer findByG_Q_PL_First(long groupId, long questionId,
		int publish, OrderByComparator<Answer> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchAnswerException {
		return getPersistence()
				   .findByG_Q_PL_First(groupId, questionId, publish,
			orderByComparator);
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
	public static Answer fetchByG_Q_PL_First(long groupId, long questionId,
		int publish, OrderByComparator<Answer> orderByComparator) {
		return getPersistence()
				   .fetchByG_Q_PL_First(groupId, questionId, publish,
			orderByComparator);
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
	public static Answer findByG_Q_PL_Last(long groupId, long questionId,
		int publish, OrderByComparator<Answer> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchAnswerException {
		return getPersistence()
				   .findByG_Q_PL_Last(groupId, questionId, publish,
			orderByComparator);
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
	public static Answer fetchByG_Q_PL_Last(long groupId, long questionId,
		int publish, OrderByComparator<Answer> orderByComparator) {
		return getPersistence()
				   .fetchByG_Q_PL_Last(groupId, questionId, publish,
			orderByComparator);
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
	public static Answer[] findByG_Q_PL_PrevAndNext(long answerId,
		long groupId, long questionId, int publish,
		OrderByComparator<Answer> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchAnswerException {
		return getPersistence()
				   .findByG_Q_PL_PrevAndNext(answerId, groupId, questionId,
			publish, orderByComparator);
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
	public static List<Answer> findByG_Q_PL(long groupId, long questionId,
		int[] publishs) {
		return getPersistence().findByG_Q_PL(groupId, questionId, publishs);
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
	public static List<Answer> findByG_Q_PL(long groupId, long questionId,
		int[] publishs, int start, int end) {
		return getPersistence()
				   .findByG_Q_PL(groupId, questionId, publishs, start, end);
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
	public static List<Answer> findByG_Q_PL(long groupId, long questionId,
		int[] publishs, int start, int end,
		OrderByComparator<Answer> orderByComparator) {
		return getPersistence()
				   .findByG_Q_PL(groupId, questionId, publishs, start, end,
			orderByComparator);
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
	public static List<Answer> findByG_Q_PL(long groupId, long questionId,
		int[] publishs, int start, int end,
		OrderByComparator<Answer> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_Q_PL(groupId, questionId, publishs, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the answers where groupId = &#63; and questionId = &#63; and publish = &#63; from the database.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param publish the publish
	*/
	public static void removeByG_Q_PL(long groupId, long questionId, int publish) {
		getPersistence().removeByG_Q_PL(groupId, questionId, publish);
	}

	/**
	* Returns the number of answers where groupId = &#63; and questionId = &#63; and publish = &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param publish the publish
	* @return the number of matching answers
	*/
	public static int countByG_Q_PL(long groupId, long questionId, int publish) {
		return getPersistence().countByG_Q_PL(groupId, questionId, publish);
	}

	/**
	* Returns the number of answers where groupId = &#63; and questionId = &#63; and publish = any &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param publishs the publishs
	* @return the number of matching answers
	*/
	public static int countByG_Q_PL(long groupId, long questionId,
		int[] publishs) {
		return getPersistence().countByG_Q_PL(groupId, questionId, publishs);
	}

	/**
	* Returns the answer where groupId = &#63; and className = &#63; and classPK = &#63; or throws a {@link NoSuchAnswerException} if it could not be found.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the matching answer
	* @throws NoSuchAnswerException if a matching answer could not be found
	*/
	public static Answer findByG_CN_CPK(long groupId, String className,
		String classPK)
		throws org.opencps.usermgt.exception.NoSuchAnswerException {
		return getPersistence().findByG_CN_CPK(groupId, className, classPK);
	}

	/**
	* Returns the answer where groupId = &#63; and className = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the matching answer, or <code>null</code> if a matching answer could not be found
	*/
	public static Answer fetchByG_CN_CPK(long groupId, String className,
		String classPK) {
		return getPersistence().fetchByG_CN_CPK(groupId, className, classPK);
	}

	/**
	* Returns the answer where groupId = &#63; and className = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching answer, or <code>null</code> if a matching answer could not be found
	*/
	public static Answer fetchByG_CN_CPK(long groupId, String className,
		String classPK, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_CN_CPK(groupId, className, classPK,
			retrieveFromCache);
	}

	/**
	* Removes the answer where groupId = &#63; and className = &#63; and classPK = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the answer that was removed
	*/
	public static Answer removeByG_CN_CPK(long groupId, String className,
		String classPK)
		throws org.opencps.usermgt.exception.NoSuchAnswerException {
		return getPersistence().removeByG_CN_CPK(groupId, className, classPK);
	}

	/**
	* Returns the number of answers where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the number of matching answers
	*/
	public static int countByG_CN_CPK(long groupId, String className,
		String classPK) {
		return getPersistence().countByG_CN_CPK(groupId, className, classPK);
	}

	/**
	* Returns all the answers where groupId = &#63; and questionId = &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @return the matching answers
	*/
	public static List<Answer> findByG_Q(long groupId, long questionId) {
		return getPersistence().findByG_Q(groupId, questionId);
	}

	/**
	* Returns a range of all the answers where groupId = &#63; and questionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param start the lower bound of the range of answers
	* @param end the upper bound of the range of answers (not inclusive)
	* @return the range of matching answers
	*/
	public static List<Answer> findByG_Q(long groupId, long questionId,
		int start, int end) {
		return getPersistence().findByG_Q(groupId, questionId, start, end);
	}

	/**
	* Returns an ordered range of all the answers where groupId = &#63; and questionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param start the lower bound of the range of answers
	* @param end the upper bound of the range of answers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching answers
	*/
	public static List<Answer> findByG_Q(long groupId, long questionId,
		int start, int end, OrderByComparator<Answer> orderByComparator) {
		return getPersistence()
				   .findByG_Q(groupId, questionId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the answers where groupId = &#63; and questionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param start the lower bound of the range of answers
	* @param end the upper bound of the range of answers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching answers
	*/
	public static List<Answer> findByG_Q(long groupId, long questionId,
		int start, int end, OrderByComparator<Answer> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_Q(groupId, questionId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first answer in the ordered set where groupId = &#63; and questionId = &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching answer
	* @throws NoSuchAnswerException if a matching answer could not be found
	*/
	public static Answer findByG_Q_First(long groupId, long questionId,
		OrderByComparator<Answer> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchAnswerException {
		return getPersistence()
				   .findByG_Q_First(groupId, questionId, orderByComparator);
	}

	/**
	* Returns the first answer in the ordered set where groupId = &#63; and questionId = &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching answer, or <code>null</code> if a matching answer could not be found
	*/
	public static Answer fetchByG_Q_First(long groupId, long questionId,
		OrderByComparator<Answer> orderByComparator) {
		return getPersistence()
				   .fetchByG_Q_First(groupId, questionId, orderByComparator);
	}

	/**
	* Returns the last answer in the ordered set where groupId = &#63; and questionId = &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching answer
	* @throws NoSuchAnswerException if a matching answer could not be found
	*/
	public static Answer findByG_Q_Last(long groupId, long questionId,
		OrderByComparator<Answer> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchAnswerException {
		return getPersistence()
				   .findByG_Q_Last(groupId, questionId, orderByComparator);
	}

	/**
	* Returns the last answer in the ordered set where groupId = &#63; and questionId = &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching answer, or <code>null</code> if a matching answer could not be found
	*/
	public static Answer fetchByG_Q_Last(long groupId, long questionId,
		OrderByComparator<Answer> orderByComparator) {
		return getPersistence()
				   .fetchByG_Q_Last(groupId, questionId, orderByComparator);
	}

	/**
	* Returns the answers before and after the current answer in the ordered set where groupId = &#63; and questionId = &#63;.
	*
	* @param answerId the primary key of the current answer
	* @param groupId the group ID
	* @param questionId the question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next answer
	* @throws NoSuchAnswerException if a answer with the primary key could not be found
	*/
	public static Answer[] findByG_Q_PrevAndNext(long answerId, long groupId,
		long questionId, OrderByComparator<Answer> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchAnswerException {
		return getPersistence()
				   .findByG_Q_PrevAndNext(answerId, groupId, questionId,
			orderByComparator);
	}

	/**
	* Removes all the answers where groupId = &#63; and questionId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	*/
	public static void removeByG_Q(long groupId, long questionId) {
		getPersistence().removeByG_Q(groupId, questionId);
	}

	/**
	* Returns the number of answers where groupId = &#63; and questionId = &#63;.
	*
	* @param groupId the group ID
	* @param questionId the question ID
	* @return the number of matching answers
	*/
	public static int countByG_Q(long groupId, long questionId) {
		return getPersistence().countByG_Q(groupId, questionId);
	}

	/**
	* Returns all the answers where groupId = &#63; and publish = &#63; and synced = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param synced the synced
	* @return the matching answers
	*/
	public static List<Answer> findByG_P_SYNC(long groupId, int publish,
		int synced) {
		return getPersistence().findByG_P_SYNC(groupId, publish, synced);
	}

	/**
	* Returns a range of all the answers where groupId = &#63; and publish = &#63; and synced = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param synced the synced
	* @param start the lower bound of the range of answers
	* @param end the upper bound of the range of answers (not inclusive)
	* @return the range of matching answers
	*/
	public static List<Answer> findByG_P_SYNC(long groupId, int publish,
		int synced, int start, int end) {
		return getPersistence()
				   .findByG_P_SYNC(groupId, publish, synced, start, end);
	}

	/**
	* Returns an ordered range of all the answers where groupId = &#63; and publish = &#63; and synced = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param synced the synced
	* @param start the lower bound of the range of answers
	* @param end the upper bound of the range of answers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching answers
	*/
	public static List<Answer> findByG_P_SYNC(long groupId, int publish,
		int synced, int start, int end,
		OrderByComparator<Answer> orderByComparator) {
		return getPersistence()
				   .findByG_P_SYNC(groupId, publish, synced, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the answers where groupId = &#63; and publish = &#63; and synced = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param synced the synced
	* @param start the lower bound of the range of answers
	* @param end the upper bound of the range of answers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching answers
	*/
	public static List<Answer> findByG_P_SYNC(long groupId, int publish,
		int synced, int start, int end,
		OrderByComparator<Answer> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P_SYNC(groupId, publish, synced, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first answer in the ordered set where groupId = &#63; and publish = &#63; and synced = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param synced the synced
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching answer
	* @throws NoSuchAnswerException if a matching answer could not be found
	*/
	public static Answer findByG_P_SYNC_First(long groupId, int publish,
		int synced, OrderByComparator<Answer> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchAnswerException {
		return getPersistence()
				   .findByG_P_SYNC_First(groupId, publish, synced,
			orderByComparator);
	}

	/**
	* Returns the first answer in the ordered set where groupId = &#63; and publish = &#63; and synced = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param synced the synced
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching answer, or <code>null</code> if a matching answer could not be found
	*/
	public static Answer fetchByG_P_SYNC_First(long groupId, int publish,
		int synced, OrderByComparator<Answer> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_SYNC_First(groupId, publish, synced,
			orderByComparator);
	}

	/**
	* Returns the last answer in the ordered set where groupId = &#63; and publish = &#63; and synced = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param synced the synced
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching answer
	* @throws NoSuchAnswerException if a matching answer could not be found
	*/
	public static Answer findByG_P_SYNC_Last(long groupId, int publish,
		int synced, OrderByComparator<Answer> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchAnswerException {
		return getPersistence()
				   .findByG_P_SYNC_Last(groupId, publish, synced,
			orderByComparator);
	}

	/**
	* Returns the last answer in the ordered set where groupId = &#63; and publish = &#63; and synced = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param synced the synced
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching answer, or <code>null</code> if a matching answer could not be found
	*/
	public static Answer fetchByG_P_SYNC_Last(long groupId, int publish,
		int synced, OrderByComparator<Answer> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_SYNC_Last(groupId, publish, synced,
			orderByComparator);
	}

	/**
	* Returns the answers before and after the current answer in the ordered set where groupId = &#63; and publish = &#63; and synced = &#63;.
	*
	* @param answerId the primary key of the current answer
	* @param groupId the group ID
	* @param publish the publish
	* @param synced the synced
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next answer
	* @throws NoSuchAnswerException if a answer with the primary key could not be found
	*/
	public static Answer[] findByG_P_SYNC_PrevAndNext(long answerId,
		long groupId, int publish, int synced,
		OrderByComparator<Answer> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchAnswerException {
		return getPersistence()
				   .findByG_P_SYNC_PrevAndNext(answerId, groupId, publish,
			synced, orderByComparator);
	}

	/**
	* Removes all the answers where groupId = &#63; and publish = &#63; and synced = &#63; from the database.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param synced the synced
	*/
	public static void removeByG_P_SYNC(long groupId, int publish, int synced) {
		getPersistence().removeByG_P_SYNC(groupId, publish, synced);
	}

	/**
	* Returns the number of answers where groupId = &#63; and publish = &#63; and synced = &#63;.
	*
	* @param groupId the group ID
	* @param publish the publish
	* @param synced the synced
	* @return the number of matching answers
	*/
	public static int countByG_P_SYNC(long groupId, int publish, int synced) {
		return getPersistence().countByG_P_SYNC(groupId, publish, synced);
	}

	/**
	* Caches the answer in the entity cache if it is enabled.
	*
	* @param answer the answer
	*/
	public static void cacheResult(Answer answer) {
		getPersistence().cacheResult(answer);
	}

	/**
	* Caches the answers in the entity cache if it is enabled.
	*
	* @param answers the answers
	*/
	public static void cacheResult(List<Answer> answers) {
		getPersistence().cacheResult(answers);
	}

	/**
	* Creates a new answer with the primary key. Does not add the answer to the database.
	*
	* @param answerId the primary key for the new answer
	* @return the new answer
	*/
	public static Answer create(long answerId) {
		return getPersistence().create(answerId);
	}

	/**
	* Removes the answer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param answerId the primary key of the answer
	* @return the answer that was removed
	* @throws NoSuchAnswerException if a answer with the primary key could not be found
	*/
	public static Answer remove(long answerId)
		throws org.opencps.usermgt.exception.NoSuchAnswerException {
		return getPersistence().remove(answerId);
	}

	public static Answer updateImpl(Answer answer) {
		return getPersistence().updateImpl(answer);
	}

	/**
	* Returns the answer with the primary key or throws a {@link NoSuchAnswerException} if it could not be found.
	*
	* @param answerId the primary key of the answer
	* @return the answer
	* @throws NoSuchAnswerException if a answer with the primary key could not be found
	*/
	public static Answer findByPrimaryKey(long answerId)
		throws org.opencps.usermgt.exception.NoSuchAnswerException {
		return getPersistence().findByPrimaryKey(answerId);
	}

	/**
	* Returns the answer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param answerId the primary key of the answer
	* @return the answer, or <code>null</code> if a answer with the primary key could not be found
	*/
	public static Answer fetchByPrimaryKey(long answerId) {
		return getPersistence().fetchByPrimaryKey(answerId);
	}

	public static java.util.Map<java.io.Serializable, Answer> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the answers.
	*
	* @return the answers
	*/
	public static List<Answer> findAll() {
		return getPersistence().findAll();
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
	public static List<Answer> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<Answer> findAll(int start, int end,
		OrderByComparator<Answer> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<Answer> findAll(int start, int end,
		OrderByComparator<Answer> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the answers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of answers.
	*
	* @return the number of answers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AnswerPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AnswerPersistence, AnswerPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AnswerPersistence.class);

		ServiceTracker<AnswerPersistence, AnswerPersistence> serviceTracker = new ServiceTracker<AnswerPersistence, AnswerPersistence>(bundle.getBundleContext(),
				AnswerPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}