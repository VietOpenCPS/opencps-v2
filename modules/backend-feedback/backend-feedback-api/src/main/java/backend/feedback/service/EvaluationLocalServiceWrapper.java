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

package backend.feedback.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EvaluationLocalService}.
 *
 * @author sondt
 * @see EvaluationLocalService
 * @generated
 */
@ProviderType
public class EvaluationLocalServiceWrapper implements EvaluationLocalService,
	ServiceWrapper<EvaluationLocalService> {
	public EvaluationLocalServiceWrapper(
		EvaluationLocalService evaluationLocalService) {
		_evaluationLocalService = evaluationLocalService;
	}

	/**
	* Adds the evaluation to the database. Also notifies the appropriate model listeners.
	*
	* @param evaluation the evaluation
	* @return the evaluation that was added
	*/
	@Override
	public backend.feedback.model.Evaluation addEvaluation(
		backend.feedback.model.Evaluation evaluation) {
		return _evaluationLocalService.addEvaluation(evaluation);
	}

	@Override
	public backend.feedback.model.Evaluation addEvaluation(long groupId,
		long employeeId, java.lang.String employeeName, int score,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _evaluationLocalService.addEvaluation(groupId, employeeId,
			employeeName, score, serviceContext);
	}

	/**
	* Creates a new evaluation with the primary key. Does not add the evaluation to the database.
	*
	* @param evaluationId the primary key for the new evaluation
	* @return the new evaluation
	*/
	@Override
	public backend.feedback.model.Evaluation createEvaluation(long evaluationId) {
		return _evaluationLocalService.createEvaluation(evaluationId);
	}

	/**
	* Deletes the evaluation from the database. Also notifies the appropriate model listeners.
	*
	* @param evaluation the evaluation
	* @return the evaluation that was removed
	*/
	@Override
	public backend.feedback.model.Evaluation deleteEvaluation(
		backend.feedback.model.Evaluation evaluation) {
		return _evaluationLocalService.deleteEvaluation(evaluation);
	}

	/**
	* Deletes the evaluation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param evaluationId the primary key of the evaluation
	* @return the evaluation that was removed
	* @throws PortalException if a evaluation with the primary key could not be found
	*/
	@Override
	public backend.feedback.model.Evaluation deleteEvaluation(long evaluationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _evaluationLocalService.deleteEvaluation(evaluationId);
	}

	@Override
	public backend.feedback.model.Evaluation fetchEvaluation(long evaluationId) {
		return _evaluationLocalService.fetchEvaluation(evaluationId);
	}

	/**
	* Returns the evaluation matching the UUID and group.
	*
	* @param uuid the evaluation's UUID
	* @param groupId the primary key of the group
	* @return the matching evaluation, or <code>null</code> if a matching evaluation could not be found
	*/
	@Override
	public backend.feedback.model.Evaluation fetchEvaluationByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _evaluationLocalService.fetchEvaluationByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the evaluation with the primary key.
	*
	* @param evaluationId the primary key of the evaluation
	* @return the evaluation
	* @throws PortalException if a evaluation with the primary key could not be found
	*/
	@Override
	public backend.feedback.model.Evaluation getEvaluation(long evaluationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _evaluationLocalService.getEvaluation(evaluationId);
	}

	/**
	* Returns the evaluation matching the UUID and group.
	*
	* @param uuid the evaluation's UUID
	* @param groupId the primary key of the group
	* @return the matching evaluation
	* @throws PortalException if a matching evaluation could not be found
	*/
	@Override
	public backend.feedback.model.Evaluation getEvaluationByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _evaluationLocalService.getEvaluationByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the evaluation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param evaluation the evaluation
	* @return the evaluation that was updated
	*/
	@Override
	public backend.feedback.model.Evaluation updateEvaluation(
		backend.feedback.model.Evaluation evaluation) {
		return _evaluationLocalService.updateEvaluation(evaluation);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _evaluationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _evaluationLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _evaluationLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _evaluationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _evaluationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _evaluationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of evaluations.
	*
	* @return the number of evaluations
	*/
	@Override
	public int getEvaluationsCount() {
		return _evaluationLocalService.getEvaluationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _evaluationLocalService.getOSGiServiceIdentifier();
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
		return _evaluationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link backend.feedback.model.impl.EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _evaluationLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link backend.feedback.model.impl.EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _evaluationLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<backend.feedback.model.Evaluation> getEvaluationbyEmployeeId(
		long employeeId) {
		return _evaluationLocalService.getEvaluationbyEmployeeId(employeeId);
	}

	@Override
	public java.util.List<backend.feedback.model.Evaluation> getEvaluationbyEmployeeIdScore(
		long employeeId, int score) {
		return _evaluationLocalService.getEvaluationbyEmployeeIdScore(employeeId,
			score);
	}

	/**
	* Returns a range of all the evaluations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link backend.feedback.model.impl.EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @return the range of evaluations
	*/
	@Override
	public java.util.List<backend.feedback.model.Evaluation> getEvaluations(
		int start, int end) {
		return _evaluationLocalService.getEvaluations(start, end);
	}

	/**
	* Returns all the evaluations matching the UUID and company.
	*
	* @param uuid the UUID of the evaluations
	* @param companyId the primary key of the company
	* @return the matching evaluations, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<backend.feedback.model.Evaluation> getEvaluationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _evaluationLocalService.getEvaluationsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of evaluations matching the UUID and company.
	*
	* @param uuid the UUID of the evaluations
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching evaluations, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<backend.feedback.model.Evaluation> getEvaluationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<backend.feedback.model.Evaluation> orderByComparator) {
		return _evaluationLocalService.getEvaluationsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
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
		return _evaluationLocalService.dynamicQueryCount(dynamicQuery);
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
		return _evaluationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public EvaluationLocalService getWrappedService() {
		return _evaluationLocalService;
	}

	@Override
	public void setWrappedService(EvaluationLocalService evaluationLocalService) {
		_evaluationLocalService = evaluationLocalService;
	}

	private EvaluationLocalService _evaluationLocalService;
}