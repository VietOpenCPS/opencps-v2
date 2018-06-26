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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Evaluation. This utility wraps
 * {@link backend.feedback.service.impl.EvaluationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author sondt
 * @see EvaluationLocalService
 * @see backend.feedback.service.base.EvaluationLocalServiceBaseImpl
 * @see backend.feedback.service.impl.EvaluationLocalServiceImpl
 * @generated
 */
@ProviderType
public class EvaluationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link backend.feedback.service.impl.EvaluationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the evaluation to the database. Also notifies the appropriate model listeners.
	*
	* @param evaluation the evaluation
	* @return the evaluation that was added
	*/
	public static backend.feedback.model.Evaluation addEvaluation(
		backend.feedback.model.Evaluation evaluation) {
		return getService().addEvaluation(evaluation);
	}

	public static backend.feedback.model.Evaluation addEvaluation(
		long groupId, long employeeId, java.lang.String employeeName,
		int score,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addEvaluation(groupId, employeeId, employeeName, score,
			serviceContext);
	}

	/**
	* Creates a new evaluation with the primary key. Does not add the evaluation to the database.
	*
	* @param evaluationId the primary key for the new evaluation
	* @return the new evaluation
	*/
	public static backend.feedback.model.Evaluation createEvaluation(
		long evaluationId) {
		return getService().createEvaluation(evaluationId);
	}

	/**
	* Deletes the evaluation from the database. Also notifies the appropriate model listeners.
	*
	* @param evaluation the evaluation
	* @return the evaluation that was removed
	*/
	public static backend.feedback.model.Evaluation deleteEvaluation(
		backend.feedback.model.Evaluation evaluation) {
		return getService().deleteEvaluation(evaluation);
	}

	/**
	* Deletes the evaluation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param evaluationId the primary key of the evaluation
	* @return the evaluation that was removed
	* @throws PortalException if a evaluation with the primary key could not be found
	*/
	public static backend.feedback.model.Evaluation deleteEvaluation(
		long evaluationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteEvaluation(evaluationId);
	}

	public static backend.feedback.model.Evaluation fetchEvaluation(
		long evaluationId) {
		return getService().fetchEvaluation(evaluationId);
	}

	/**
	* Returns the evaluation matching the UUID and group.
	*
	* @param uuid the evaluation's UUID
	* @param groupId the primary key of the group
	* @return the matching evaluation, or <code>null</code> if a matching evaluation could not be found
	*/
	public static backend.feedback.model.Evaluation fetchEvaluationByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchEvaluationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the evaluation with the primary key.
	*
	* @param evaluationId the primary key of the evaluation
	* @return the evaluation
	* @throws PortalException if a evaluation with the primary key could not be found
	*/
	public static backend.feedback.model.Evaluation getEvaluation(
		long evaluationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEvaluation(evaluationId);
	}

	/**
	* Returns the evaluation matching the UUID and group.
	*
	* @param uuid the evaluation's UUID
	* @param groupId the primary key of the group
	* @return the matching evaluation
	* @throws PortalException if a matching evaluation could not be found
	*/
	public static backend.feedback.model.Evaluation getEvaluationByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEvaluationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the evaluation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param evaluation the evaluation
	* @return the evaluation that was updated
	*/
	public static backend.feedback.model.Evaluation updateEvaluation(
		backend.feedback.model.Evaluation evaluation) {
		return getService().updateEvaluation(evaluation);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of evaluations.
	*
	* @return the number of evaluations
	*/
	public static int getEvaluationsCount() {
		return getService().getEvaluationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<backend.feedback.model.Evaluation> getEvaluationbyEmployeeId(
		long employeeId) {
		return getService().getEvaluationbyEmployeeId(employeeId);
	}

	public static java.util.List<backend.feedback.model.Evaluation> getEvaluationbyEmployeeIdScore(
		long employeeId, int score) {
		return getService().getEvaluationbyEmployeeIdScore(employeeId, score);
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
	public static java.util.List<backend.feedback.model.Evaluation> getEvaluations(
		int start, int end) {
		return getService().getEvaluations(start, end);
	}

	/**
	* Returns all the evaluations matching the UUID and company.
	*
	* @param uuid the UUID of the evaluations
	* @param companyId the primary key of the company
	* @return the matching evaluations, or an empty list if no matches were found
	*/
	public static java.util.List<backend.feedback.model.Evaluation> getEvaluationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getEvaluationsByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<backend.feedback.model.Evaluation> getEvaluationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<backend.feedback.model.Evaluation> orderByComparator) {
		return getService()
				   .getEvaluationsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static EvaluationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EvaluationLocalService, EvaluationLocalService> _serviceTracker =
		ServiceTrackerFactory.open(EvaluationLocalService.class);
}