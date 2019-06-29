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

package org.opencps.usermgt.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.usermgt.model.Question;
import org.opencps.usermgt.service.QuestionLocalService;
import org.opencps.usermgt.service.persistence.AnswerPersistence;
import org.opencps.usermgt.service.persistence.ApplicantPersistence;
import org.opencps.usermgt.service.persistence.EmployeeJobPosPersistence;
import org.opencps.usermgt.service.persistence.EmployeePersistence;
import org.opencps.usermgt.service.persistence.HmacAuthenPersistence;
import org.opencps.usermgt.service.persistence.JobPosPersistence;
import org.opencps.usermgt.service.persistence.JobPosWorkPersistence;
import org.opencps.usermgt.service.persistence.OfficeSitePersistence;
import org.opencps.usermgt.service.persistence.PreferencesPersistence;
import org.opencps.usermgt.service.persistence.QuestionFinder;
import org.opencps.usermgt.service.persistence.QuestionPersistence;
import org.opencps.usermgt.service.persistence.ResourceRolePersistence;
import org.opencps.usermgt.service.persistence.ResourceUserPersistence;
import org.opencps.usermgt.service.persistence.UserLoginPersistence;
import org.opencps.usermgt.service.persistence.UserTrackPathPersistence;
import org.opencps.usermgt.service.persistence.VisibilityPersistence;
import org.opencps.usermgt.service.persistence.WorkingUnitPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the question local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link org.opencps.usermgt.service.impl.QuestionLocalServiceImpl}.
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.impl.QuestionLocalServiceImpl
 * @see org.opencps.usermgt.service.QuestionLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class QuestionLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements QuestionLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link org.opencps.usermgt.service.QuestionLocalServiceUtil} to access the question local service.
	 */

	/**
	 * Adds the question to the database. Also notifies the appropriate model listeners.
	 *
	 * @param question the question
	 * @return the question that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Question addQuestion(Question question) {
		question.setNew(true);

		return questionPersistence.update(question);
	}

	/**
	 * Creates a new question with the primary key. Does not add the question to the database.
	 *
	 * @param questionId the primary key for the new question
	 * @return the new question
	 */
	@Override
	@Transactional(enabled = false)
	public Question createQuestion(long questionId) {
		return questionPersistence.create(questionId);
	}

	/**
	 * Deletes the question with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param questionId the primary key of the question
	 * @return the question that was removed
	 * @throws PortalException if a question with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Question deleteQuestion(long questionId) throws PortalException {
		return questionPersistence.remove(questionId);
	}

	/**
	 * Deletes the question from the database. Also notifies the appropriate model listeners.
	 *
	 * @param question the question
	 * @return the question that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Question deleteQuestion(Question question) {
		return questionPersistence.remove(question);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Question.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return questionPersistence.findWithDynamicQuery(dynamicQuery);
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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return questionPersistence.findWithDynamicQuery(dynamicQuery, start, end);
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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return questionPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return questionPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return questionPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public Question fetchQuestion(long questionId) {
		return questionPersistence.fetchByPrimaryKey(questionId);
	}

	/**
	 * Returns the question with the primary key.
	 *
	 * @param questionId the primary key of the question
	 * @return the question
	 * @throws PortalException if a question with the primary key could not be found
	 */
	@Override
	public Question getQuestion(long questionId) throws PortalException {
		return questionPersistence.findByPrimaryKey(questionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(questionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Question.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("questionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(questionLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Question.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("questionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(questionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Question.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("questionId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return questionLocalService.deleteQuestion((Question)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return questionPersistence.findByPrimaryKey(primaryKeyObj);
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
	public List<Question> getQuestions(int start, int end) {
		return questionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of questions.
	 *
	 * @return the number of questions
	 */
	@Override
	public int getQuestionsCount() {
		return questionPersistence.countAll();
	}

	/**
	 * Updates the question in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param question the question
	 * @return the question that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Question updateQuestion(Question question) {
		return questionPersistence.update(question);
	}

	/**
	 * Returns the answer local service.
	 *
	 * @return the answer local service
	 */
	public org.opencps.usermgt.service.AnswerLocalService getAnswerLocalService() {
		return answerLocalService;
	}

	/**
	 * Sets the answer local service.
	 *
	 * @param answerLocalService the answer local service
	 */
	public void setAnswerLocalService(
		org.opencps.usermgt.service.AnswerLocalService answerLocalService) {
		this.answerLocalService = answerLocalService;
	}

	/**
	 * Returns the answer persistence.
	 *
	 * @return the answer persistence
	 */
	public AnswerPersistence getAnswerPersistence() {
		return answerPersistence;
	}

	/**
	 * Sets the answer persistence.
	 *
	 * @param answerPersistence the answer persistence
	 */
	public void setAnswerPersistence(AnswerPersistence answerPersistence) {
		this.answerPersistence = answerPersistence;
	}

	/**
	 * Returns the applicant local service.
	 *
	 * @return the applicant local service
	 */
	public org.opencps.usermgt.service.ApplicantLocalService getApplicantLocalService() {
		return applicantLocalService;
	}

	/**
	 * Sets the applicant local service.
	 *
	 * @param applicantLocalService the applicant local service
	 */
	public void setApplicantLocalService(
		org.opencps.usermgt.service.ApplicantLocalService applicantLocalService) {
		this.applicantLocalService = applicantLocalService;
	}

	/**
	 * Returns the applicant persistence.
	 *
	 * @return the applicant persistence
	 */
	public ApplicantPersistence getApplicantPersistence() {
		return applicantPersistence;
	}

	/**
	 * Sets the applicant persistence.
	 *
	 * @param applicantPersistence the applicant persistence
	 */
	public void setApplicantPersistence(
		ApplicantPersistence applicantPersistence) {
		this.applicantPersistence = applicantPersistence;
	}

	/**
	 * Returns the employee local service.
	 *
	 * @return the employee local service
	 */
	public org.opencps.usermgt.service.EmployeeLocalService getEmployeeLocalService() {
		return employeeLocalService;
	}

	/**
	 * Sets the employee local service.
	 *
	 * @param employeeLocalService the employee local service
	 */
	public void setEmployeeLocalService(
		org.opencps.usermgt.service.EmployeeLocalService employeeLocalService) {
		this.employeeLocalService = employeeLocalService;
	}

	/**
	 * Returns the employee persistence.
	 *
	 * @return the employee persistence
	 */
	public EmployeePersistence getEmployeePersistence() {
		return employeePersistence;
	}

	/**
	 * Sets the employee persistence.
	 *
	 * @param employeePersistence the employee persistence
	 */
	public void setEmployeePersistence(EmployeePersistence employeePersistence) {
		this.employeePersistence = employeePersistence;
	}

	/**
	 * Returns the employee job pos local service.
	 *
	 * @return the employee job pos local service
	 */
	public org.opencps.usermgt.service.EmployeeJobPosLocalService getEmployeeJobPosLocalService() {
		return employeeJobPosLocalService;
	}

	/**
	 * Sets the employee job pos local service.
	 *
	 * @param employeeJobPosLocalService the employee job pos local service
	 */
	public void setEmployeeJobPosLocalService(
		org.opencps.usermgt.service.EmployeeJobPosLocalService employeeJobPosLocalService) {
		this.employeeJobPosLocalService = employeeJobPosLocalService;
	}

	/**
	 * Returns the employee job pos persistence.
	 *
	 * @return the employee job pos persistence
	 */
	public EmployeeJobPosPersistence getEmployeeJobPosPersistence() {
		return employeeJobPosPersistence;
	}

	/**
	 * Sets the employee job pos persistence.
	 *
	 * @param employeeJobPosPersistence the employee job pos persistence
	 */
	public void setEmployeeJobPosPersistence(
		EmployeeJobPosPersistence employeeJobPosPersistence) {
		this.employeeJobPosPersistence = employeeJobPosPersistence;
	}

	/**
	 * Returns the hmac authen local service.
	 *
	 * @return the hmac authen local service
	 */
	public org.opencps.usermgt.service.HmacAuthenLocalService getHmacAuthenLocalService() {
		return hmacAuthenLocalService;
	}

	/**
	 * Sets the hmac authen local service.
	 *
	 * @param hmacAuthenLocalService the hmac authen local service
	 */
	public void setHmacAuthenLocalService(
		org.opencps.usermgt.service.HmacAuthenLocalService hmacAuthenLocalService) {
		this.hmacAuthenLocalService = hmacAuthenLocalService;
	}

	/**
	 * Returns the hmac authen persistence.
	 *
	 * @return the hmac authen persistence
	 */
	public HmacAuthenPersistence getHmacAuthenPersistence() {
		return hmacAuthenPersistence;
	}

	/**
	 * Sets the hmac authen persistence.
	 *
	 * @param hmacAuthenPersistence the hmac authen persistence
	 */
	public void setHmacAuthenPersistence(
		HmacAuthenPersistence hmacAuthenPersistence) {
		this.hmacAuthenPersistence = hmacAuthenPersistence;
	}

	/**
	 * Returns the job pos local service.
	 *
	 * @return the job pos local service
	 */
	public org.opencps.usermgt.service.JobPosLocalService getJobPosLocalService() {
		return jobPosLocalService;
	}

	/**
	 * Sets the job pos local service.
	 *
	 * @param jobPosLocalService the job pos local service
	 */
	public void setJobPosLocalService(
		org.opencps.usermgt.service.JobPosLocalService jobPosLocalService) {
		this.jobPosLocalService = jobPosLocalService;
	}

	/**
	 * Returns the job pos persistence.
	 *
	 * @return the job pos persistence
	 */
	public JobPosPersistence getJobPosPersistence() {
		return jobPosPersistence;
	}

	/**
	 * Sets the job pos persistence.
	 *
	 * @param jobPosPersistence the job pos persistence
	 */
	public void setJobPosPersistence(JobPosPersistence jobPosPersistence) {
		this.jobPosPersistence = jobPosPersistence;
	}

	/**
	 * Returns the job pos work local service.
	 *
	 * @return the job pos work local service
	 */
	public org.opencps.usermgt.service.JobPosWorkLocalService getJobPosWorkLocalService() {
		return jobPosWorkLocalService;
	}

	/**
	 * Sets the job pos work local service.
	 *
	 * @param jobPosWorkLocalService the job pos work local service
	 */
	public void setJobPosWorkLocalService(
		org.opencps.usermgt.service.JobPosWorkLocalService jobPosWorkLocalService) {
		this.jobPosWorkLocalService = jobPosWorkLocalService;
	}

	/**
	 * Returns the job pos work persistence.
	 *
	 * @return the job pos work persistence
	 */
	public JobPosWorkPersistence getJobPosWorkPersistence() {
		return jobPosWorkPersistence;
	}

	/**
	 * Sets the job pos work persistence.
	 *
	 * @param jobPosWorkPersistence the job pos work persistence
	 */
	public void setJobPosWorkPersistence(
		JobPosWorkPersistence jobPosWorkPersistence) {
		this.jobPosWorkPersistence = jobPosWorkPersistence;
	}

	/**
	 * Returns the office site local service.
	 *
	 * @return the office site local service
	 */
	public org.opencps.usermgt.service.OfficeSiteLocalService getOfficeSiteLocalService() {
		return officeSiteLocalService;
	}

	/**
	 * Sets the office site local service.
	 *
	 * @param officeSiteLocalService the office site local service
	 */
	public void setOfficeSiteLocalService(
		org.opencps.usermgt.service.OfficeSiteLocalService officeSiteLocalService) {
		this.officeSiteLocalService = officeSiteLocalService;
	}

	/**
	 * Returns the office site persistence.
	 *
	 * @return the office site persistence
	 */
	public OfficeSitePersistence getOfficeSitePersistence() {
		return officeSitePersistence;
	}

	/**
	 * Sets the office site persistence.
	 *
	 * @param officeSitePersistence the office site persistence
	 */
	public void setOfficeSitePersistence(
		OfficeSitePersistence officeSitePersistence) {
		this.officeSitePersistence = officeSitePersistence;
	}

	/**
	 * Returns the preferences local service.
	 *
	 * @return the preferences local service
	 */
	public org.opencps.usermgt.service.PreferencesLocalService getPreferencesLocalService() {
		return preferencesLocalService;
	}

	/**
	 * Sets the preferences local service.
	 *
	 * @param preferencesLocalService the preferences local service
	 */
	public void setPreferencesLocalService(
		org.opencps.usermgt.service.PreferencesLocalService preferencesLocalService) {
		this.preferencesLocalService = preferencesLocalService;
	}

	/**
	 * Returns the preferences persistence.
	 *
	 * @return the preferences persistence
	 */
	public PreferencesPersistence getPreferencesPersistence() {
		return preferencesPersistence;
	}

	/**
	 * Sets the preferences persistence.
	 *
	 * @param preferencesPersistence the preferences persistence
	 */
	public void setPreferencesPersistence(
		PreferencesPersistence preferencesPersistence) {
		this.preferencesPersistence = preferencesPersistence;
	}

	/**
	 * Returns the question local service.
	 *
	 * @return the question local service
	 */
	public QuestionLocalService getQuestionLocalService() {
		return questionLocalService;
	}

	/**
	 * Sets the question local service.
	 *
	 * @param questionLocalService the question local service
	 */
	public void setQuestionLocalService(
		QuestionLocalService questionLocalService) {
		this.questionLocalService = questionLocalService;
	}

	/**
	 * Returns the question persistence.
	 *
	 * @return the question persistence
	 */
	public QuestionPersistence getQuestionPersistence() {
		return questionPersistence;
	}

	/**
	 * Sets the question persistence.
	 *
	 * @param questionPersistence the question persistence
	 */
	public void setQuestionPersistence(QuestionPersistence questionPersistence) {
		this.questionPersistence = questionPersistence;
	}

	/**
	 * Returns the question finder.
	 *
	 * @return the question finder
	 */
	public QuestionFinder getQuestionFinder() {
		return questionFinder;
	}

	/**
	 * Sets the question finder.
	 *
	 * @param questionFinder the question finder
	 */
	public void setQuestionFinder(QuestionFinder questionFinder) {
		this.questionFinder = questionFinder;
	}

	/**
	 * Returns the resource role local service.
	 *
	 * @return the resource role local service
	 */
	public org.opencps.usermgt.service.ResourceRoleLocalService getResourceRoleLocalService() {
		return resourceRoleLocalService;
	}

	/**
	 * Sets the resource role local service.
	 *
	 * @param resourceRoleLocalService the resource role local service
	 */
	public void setResourceRoleLocalService(
		org.opencps.usermgt.service.ResourceRoleLocalService resourceRoleLocalService) {
		this.resourceRoleLocalService = resourceRoleLocalService;
	}

	/**
	 * Returns the resource role persistence.
	 *
	 * @return the resource role persistence
	 */
	public ResourceRolePersistence getResourceRolePersistence() {
		return resourceRolePersistence;
	}

	/**
	 * Sets the resource role persistence.
	 *
	 * @param resourceRolePersistence the resource role persistence
	 */
	public void setResourceRolePersistence(
		ResourceRolePersistence resourceRolePersistence) {
		this.resourceRolePersistence = resourceRolePersistence;
	}

	/**
	 * Returns the resource user local service.
	 *
	 * @return the resource user local service
	 */
	public org.opencps.usermgt.service.ResourceUserLocalService getResourceUserLocalService() {
		return resourceUserLocalService;
	}

	/**
	 * Sets the resource user local service.
	 *
	 * @param resourceUserLocalService the resource user local service
	 */
	public void setResourceUserLocalService(
		org.opencps.usermgt.service.ResourceUserLocalService resourceUserLocalService) {
		this.resourceUserLocalService = resourceUserLocalService;
	}

	/**
	 * Returns the resource user persistence.
	 *
	 * @return the resource user persistence
	 */
	public ResourceUserPersistence getResourceUserPersistence() {
		return resourceUserPersistence;
	}

	/**
	 * Sets the resource user persistence.
	 *
	 * @param resourceUserPersistence the resource user persistence
	 */
	public void setResourceUserPersistence(
		ResourceUserPersistence resourceUserPersistence) {
		this.resourceUserPersistence = resourceUserPersistence;
	}

	/**
	 * Returns the user login local service.
	 *
	 * @return the user login local service
	 */
	public org.opencps.usermgt.service.UserLoginLocalService getUserLoginLocalService() {
		return userLoginLocalService;
	}

	/**
	 * Sets the user login local service.
	 *
	 * @param userLoginLocalService the user login local service
	 */
	public void setUserLoginLocalService(
		org.opencps.usermgt.service.UserLoginLocalService userLoginLocalService) {
		this.userLoginLocalService = userLoginLocalService;
	}

	/**
	 * Returns the user login persistence.
	 *
	 * @return the user login persistence
	 */
	public UserLoginPersistence getUserLoginPersistence() {
		return userLoginPersistence;
	}

	/**
	 * Sets the user login persistence.
	 *
	 * @param userLoginPersistence the user login persistence
	 */
	public void setUserLoginPersistence(
		UserLoginPersistence userLoginPersistence) {
		this.userLoginPersistence = userLoginPersistence;
	}

	/**
	 * Returns the user track path local service.
	 *
	 * @return the user track path local service
	 */
	public org.opencps.usermgt.service.UserTrackPathLocalService getUserTrackPathLocalService() {
		return userTrackPathLocalService;
	}

	/**
	 * Sets the user track path local service.
	 *
	 * @param userTrackPathLocalService the user track path local service
	 */
	public void setUserTrackPathLocalService(
		org.opencps.usermgt.service.UserTrackPathLocalService userTrackPathLocalService) {
		this.userTrackPathLocalService = userTrackPathLocalService;
	}

	/**
	 * Returns the user track path persistence.
	 *
	 * @return the user track path persistence
	 */
	public UserTrackPathPersistence getUserTrackPathPersistence() {
		return userTrackPathPersistence;
	}

	/**
	 * Sets the user track path persistence.
	 *
	 * @param userTrackPathPersistence the user track path persistence
	 */
	public void setUserTrackPathPersistence(
		UserTrackPathPersistence userTrackPathPersistence) {
		this.userTrackPathPersistence = userTrackPathPersistence;
	}

	/**
	 * Returns the visibility local service.
	 *
	 * @return the visibility local service
	 */
	public org.opencps.usermgt.service.VisibilityLocalService getVisibilityLocalService() {
		return visibilityLocalService;
	}

	/**
	 * Sets the visibility local service.
	 *
	 * @param visibilityLocalService the visibility local service
	 */
	public void setVisibilityLocalService(
		org.opencps.usermgt.service.VisibilityLocalService visibilityLocalService) {
		this.visibilityLocalService = visibilityLocalService;
	}

	/**
	 * Returns the visibility persistence.
	 *
	 * @return the visibility persistence
	 */
	public VisibilityPersistence getVisibilityPersistence() {
		return visibilityPersistence;
	}

	/**
	 * Sets the visibility persistence.
	 *
	 * @param visibilityPersistence the visibility persistence
	 */
	public void setVisibilityPersistence(
		VisibilityPersistence visibilityPersistence) {
		this.visibilityPersistence = visibilityPersistence;
	}

	/**
	 * Returns the working unit local service.
	 *
	 * @return the working unit local service
	 */
	public org.opencps.usermgt.service.WorkingUnitLocalService getWorkingUnitLocalService() {
		return workingUnitLocalService;
	}

	/**
	 * Sets the working unit local service.
	 *
	 * @param workingUnitLocalService the working unit local service
	 */
	public void setWorkingUnitLocalService(
		org.opencps.usermgt.service.WorkingUnitLocalService workingUnitLocalService) {
		this.workingUnitLocalService = workingUnitLocalService;
	}

	/**
	 * Returns the working unit persistence.
	 *
	 * @return the working unit persistence
	 */
	public WorkingUnitPersistence getWorkingUnitPersistence() {
		return workingUnitPersistence;
	}

	/**
	 * Sets the working unit persistence.
	 *
	 * @param workingUnitPersistence the working unit persistence
	 */
	public void setWorkingUnitPersistence(
		WorkingUnitPersistence workingUnitPersistence) {
		this.workingUnitPersistence = workingUnitPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("org.opencps.usermgt.model.Question",
			questionLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"org.opencps.usermgt.model.Question");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return QuestionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Question.class;
	}

	protected String getModelClassName() {
		return Question.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = questionPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = org.opencps.usermgt.service.AnswerLocalService.class)
	protected org.opencps.usermgt.service.AnswerLocalService answerLocalService;
	@BeanReference(type = AnswerPersistence.class)
	protected AnswerPersistence answerPersistence;
	@BeanReference(type = org.opencps.usermgt.service.ApplicantLocalService.class)
	protected org.opencps.usermgt.service.ApplicantLocalService applicantLocalService;
	@BeanReference(type = ApplicantPersistence.class)
	protected ApplicantPersistence applicantPersistence;
	@BeanReference(type = org.opencps.usermgt.service.EmployeeLocalService.class)
	protected org.opencps.usermgt.service.EmployeeLocalService employeeLocalService;
	@BeanReference(type = EmployeePersistence.class)
	protected EmployeePersistence employeePersistence;
	@BeanReference(type = org.opencps.usermgt.service.EmployeeJobPosLocalService.class)
	protected org.opencps.usermgt.service.EmployeeJobPosLocalService employeeJobPosLocalService;
	@BeanReference(type = EmployeeJobPosPersistence.class)
	protected EmployeeJobPosPersistence employeeJobPosPersistence;
	@BeanReference(type = org.opencps.usermgt.service.HmacAuthenLocalService.class)
	protected org.opencps.usermgt.service.HmacAuthenLocalService hmacAuthenLocalService;
	@BeanReference(type = HmacAuthenPersistence.class)
	protected HmacAuthenPersistence hmacAuthenPersistence;
	@BeanReference(type = org.opencps.usermgt.service.JobPosLocalService.class)
	protected org.opencps.usermgt.service.JobPosLocalService jobPosLocalService;
	@BeanReference(type = JobPosPersistence.class)
	protected JobPosPersistence jobPosPersistence;
	@BeanReference(type = org.opencps.usermgt.service.JobPosWorkLocalService.class)
	protected org.opencps.usermgt.service.JobPosWorkLocalService jobPosWorkLocalService;
	@BeanReference(type = JobPosWorkPersistence.class)
	protected JobPosWorkPersistence jobPosWorkPersistence;
	@BeanReference(type = org.opencps.usermgt.service.OfficeSiteLocalService.class)
	protected org.opencps.usermgt.service.OfficeSiteLocalService officeSiteLocalService;
	@BeanReference(type = OfficeSitePersistence.class)
	protected OfficeSitePersistence officeSitePersistence;
	@BeanReference(type = org.opencps.usermgt.service.PreferencesLocalService.class)
	protected org.opencps.usermgt.service.PreferencesLocalService preferencesLocalService;
	@BeanReference(type = PreferencesPersistence.class)
	protected PreferencesPersistence preferencesPersistence;
	@BeanReference(type = QuestionLocalService.class)
	protected QuestionLocalService questionLocalService;
	@BeanReference(type = QuestionPersistence.class)
	protected QuestionPersistence questionPersistence;
	@BeanReference(type = QuestionFinder.class)
	protected QuestionFinder questionFinder;
	@BeanReference(type = org.opencps.usermgt.service.ResourceRoleLocalService.class)
	protected org.opencps.usermgt.service.ResourceRoleLocalService resourceRoleLocalService;
	@BeanReference(type = ResourceRolePersistence.class)
	protected ResourceRolePersistence resourceRolePersistence;
	@BeanReference(type = org.opencps.usermgt.service.ResourceUserLocalService.class)
	protected org.opencps.usermgt.service.ResourceUserLocalService resourceUserLocalService;
	@BeanReference(type = ResourceUserPersistence.class)
	protected ResourceUserPersistence resourceUserPersistence;
	@BeanReference(type = org.opencps.usermgt.service.UserLoginLocalService.class)
	protected org.opencps.usermgt.service.UserLoginLocalService userLoginLocalService;
	@BeanReference(type = UserLoginPersistence.class)
	protected UserLoginPersistence userLoginPersistence;
	@BeanReference(type = org.opencps.usermgt.service.UserTrackPathLocalService.class)
	protected org.opencps.usermgt.service.UserTrackPathLocalService userTrackPathLocalService;
	@BeanReference(type = UserTrackPathPersistence.class)
	protected UserTrackPathPersistence userTrackPathPersistence;
	@BeanReference(type = org.opencps.usermgt.service.VisibilityLocalService.class)
	protected org.opencps.usermgt.service.VisibilityLocalService visibilityLocalService;
	@BeanReference(type = VisibilityPersistence.class)
	protected VisibilityPersistence visibilityPersistence;
	@BeanReference(type = org.opencps.usermgt.service.WorkingUnitLocalService.class)
	protected org.opencps.usermgt.service.WorkingUnitLocalService workingUnitLocalService;
	@BeanReference(type = WorkingUnitPersistence.class)
	protected WorkingUnitPersistence workingUnitPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}