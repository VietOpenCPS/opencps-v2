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

package org.opencps.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link QuestionLocalService}.
 *
 * @author khoavu
 * @see QuestionLocalService
 * @generated
 */
@ProviderType
public class QuestionLocalServiceWrapper implements QuestionLocalService,
	ServiceWrapper<QuestionLocalService> {
	public QuestionLocalServiceWrapper(
		QuestionLocalService questionLocalService) {
		_questionLocalService = questionLocalService;
	}

	/**
	* Adds the question to the database. Also notifies the appropriate model listeners.
	*
	* @param question the question
	* @return the question that was added
	*/
	@Override
	public org.opencps.usermgt.model.Question addQuestion(
		org.opencps.usermgt.model.Question question) {
		return _questionLocalService.addQuestion(question);
	}

	@Override
	public int countByG_PL(long groupId, int[] publishs) {
		return _questionLocalService.countByG_PL(groupId, publishs);
	}

	@Override
	public int countByQuerySearch(long groupId, String keyword,
		String govAgencyCode, Integer publish, String questionType,
		Boolean answer) {
		return _questionLocalService.countByQuerySearch(groupId, keyword,
			govAgencyCode, publish, questionType, answer);
	}

	/**
	* Creates a new question with the primary key. Does not add the question to the database.
	*
	* @param questionId the primary key for the new question
	* @return the new question
	*/
	@Override
	public org.opencps.usermgt.model.Question createQuestion(long questionId) {
		return _questionLocalService.createQuestion(questionId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _questionLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the question with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param questionId the primary key of the question
	* @return the question that was removed
	* @throws PortalException if a question with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Question deleteQuestion(long questionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _questionLocalService.deleteQuestion(questionId);
	}

	/**
	* Deletes the question from the database. Also notifies the appropriate model listeners.
	*
	* @param question the question
	* @return the question that was removed
	*/
	@Override
	public org.opencps.usermgt.model.Question deleteQuestion(
		org.opencps.usermgt.model.Question question) {
		return _questionLocalService.deleteQuestion(question);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _questionLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _questionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _questionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _questionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _questionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _questionLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.usermgt.model.Question fetchQuestion(long questionId) {
		return _questionLocalService.fetchQuestion(questionId);
	}

	@Override
	public java.util.List<org.opencps.usermgt.model.Question> findByG_PL(
		long groupId, int[] publishs, int start, int end) {
		return _questionLocalService.findByG_PL(groupId, publishs, start, end);
	}

	@Override
	public java.util.List<org.opencps.usermgt.model.Question> findByQuerySearch(
		long groupId, String keyword, String govAgencyCode, Integer publish,
		String questionType, Boolean answer, int start, int limit) {
		return _questionLocalService.findByQuerySearch(groupId, keyword,
			govAgencyCode, publish, questionType, answer, start, limit);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _questionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _questionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _questionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _questionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the question with the primary key.
	*
	* @param questionId the primary key of the question
	* @return the question
	* @throws PortalException if a question with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Question getQuestion(long questionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _questionLocalService.getQuestion(questionId);
	}

	/**
	* Returns a range of all the questions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.QuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of questions
	* @param end the upper bound of the range of questions (not inclusive)
	* @return the range of questions
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.Question> getQuestions(
		int start, int end) {
		return _questionLocalService.getQuestions(start, end);
	}

	/**
	* Returns the number of questions.
	*
	* @return the number of questions
	*/
	@Override
	public int getQuestionsCount() {
		return _questionLocalService.getQuestionsCount();
	}

	@Override
	public org.opencps.usermgt.model.Question updateQuestion(long companyId,
		long groupId, long questionId, String fullname, String email,
		String content, int publish, String govAgencyCode, String govAgencyName) {
		return _questionLocalService.updateQuestion(companyId, groupId,
			questionId, fullname, email, content, publish, govAgencyCode,
			govAgencyName);
	}

	/**
	* Updates the question in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param question the question
	* @return the question that was updated
	*/
	@Override
	public org.opencps.usermgt.model.Question updateQuestion(
		org.opencps.usermgt.model.Question question) {
		return _questionLocalService.updateQuestion(question);
	}

	@Override
	public QuestionLocalService getWrappedService() {
		return _questionLocalService;
	}

	@Override
	public void setWrappedService(QuestionLocalService questionLocalService) {
		_questionLocalService = questionLocalService;
	}

	private QuestionLocalService _questionLocalService;
}