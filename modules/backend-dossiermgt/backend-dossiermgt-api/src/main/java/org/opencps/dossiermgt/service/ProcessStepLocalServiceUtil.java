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

package org.opencps.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ProcessStep. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.ProcessStepLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ProcessStepLocalService
 * @see org.opencps.dossiermgt.service.base.ProcessStepLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ProcessStepLocalServiceImpl
 * @generated
 */
@ProviderType
public class ProcessStepLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ProcessStepLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the process step to the database. Also notifies the appropriate model listeners.
	*
	* @param processStep the process step
	* @return the process step that was added
	*/
	public static org.opencps.dossiermgt.model.ProcessStep addProcessStep(
		org.opencps.dossiermgt.model.ProcessStep processStep) {
		return getService().addProcessStep(processStep);
	}

	public static org.opencps.dossiermgt.model.ProcessStep adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.dossiermgt.model.ProcessStep adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	public static long countLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLucene(params, searchContext);
	}

	/**
	* Creates a new process step with the primary key. Does not add the process step to the database.
	*
	* @param processStepId the primary key for the new process step
	* @return the new process step
	*/
	public static org.opencps.dossiermgt.model.ProcessStep createProcessStep(
		long processStepId) {
		return getService().createProcessStep(processStepId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the process step with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processStepId the primary key of the process step
	* @return the process step that was removed
	* @throws PortalException if a process step with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessStep deleteProcessStep(
		long processStepId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteProcessStep(processStepId);
	}

	/**
	* Deletes the process step from the database. Also notifies the appropriate model listeners.
	*
	* @param processStep the process step
	* @return the process step that was removed
	*/
	public static org.opencps.dossiermgt.model.ProcessStep deleteProcessStep(
		org.opencps.dossiermgt.model.ProcessStep processStep) {
		return getService().deleteProcessStep(processStep);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.ProcessStep fetchBySC_GID(
		String stepCode, long groupId, long serviceProcessId) {
		return getService().fetchBySC_GID(stepCode, groupId, serviceProcessId);
	}

	public static org.opencps.dossiermgt.model.ProcessStep fetchProcessStep(
		long processStepId) {
		return getService().fetchProcessStep(processStepId);
	}

	/**
	* Returns the process step matching the UUID and group.
	*
	* @param uuid the process step's UUID
	* @param groupId the primary key of the group
	* @return the matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessStep fetchProcessStepByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchProcessStepByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ProcessStep> findByG_SP_SNO(
		long groupId, long serviceProcessId, String sequenceNo) {
		return getService().findByG_SP_SNO(groupId, serviceProcessId, sequenceNo);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<org.opencps.dossiermgt.model.ProcessStep> getBySC_SPID(
		String stepCode, long serviceProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBySC_SPID(stepCode, serviceProcessId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ProcessStep> getByStatusAnsSubStatus(
		String dossierStatus, String dossierSubStatus, long groupId) {
		return getService()
				   .getByStatusAnsSubStatus(dossierStatus, dossierSubStatus,
			groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the process step with the primary key.
	*
	* @param processStepId the primary key of the process step
	* @return the process step
	* @throws PortalException if a process step with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessStep getProcessStep(
		long processStepId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcessStep(processStepId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ProcessStep> getProcessStepbyServiceProcessId(
		long serviceProcessId) {
		return getService().getProcessStepbyServiceProcessId(serviceProcessId);
	}

	/**
	* Returns the process step matching the UUID and group.
	*
	* @param uuid the process step's UUID
	* @param groupId the primary key of the group
	* @return the matching process step
	* @throws PortalException if a matching process step could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessStep getProcessStepByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcessStepByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the process steps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @return the range of process steps
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ProcessStep> getProcessSteps(
		int start, int end) {
		return getService().getProcessSteps(start, end);
	}

	/**
	* Returns all the process steps matching the UUID and company.
	*
	* @param uuid the UUID of the process steps
	* @param companyId the primary key of the company
	* @return the matching process steps, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ProcessStep> getProcessStepsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getProcessStepsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of process steps matching the UUID and company.
	*
	* @param uuid the UUID of the process steps
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching process steps, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ProcessStep> getProcessStepsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ProcessStep> orderByComparator) {
		return getService()
				   .getProcessStepsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of process steps.
	*
	* @return the number of process steps
	*/
	public static int getProcessStepsCount() {
		return getService().getProcessStepsCount();
	}

	public static org.opencps.dossiermgt.model.ProcessStep removeProcessStep(
		long processStepId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeProcessStep(processStepId);
	}

	public static com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .searchLucene(params, sorts, start, end, searchContext);
	}

	public static org.opencps.dossiermgt.model.ProcessStep updateProcessStep(
		long groupId, long processStepId, String stepCode, String stepName,
		long serviceProcessId, String sequenceNo, String dossierStatus,
		String dossierSubStatus, int durationCount, String customProcessUrl,
		String stepInstruction, String briefNote, boolean editable,
		String lockState,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateProcessStep(groupId, processStepId, stepCode,
			stepName, serviceProcessId, sequenceNo, dossierStatus,
			dossierSubStatus, durationCount, customProcessUrl, stepInstruction,
			briefNote, editable, lockState, context);
	}

	/**
	* Updates the process step in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processStep the process step
	* @return the process step that was updated
	*/
	public static org.opencps.dossiermgt.model.ProcessStep updateProcessStep(
		org.opencps.dossiermgt.model.ProcessStep processStep) {
		return getService().updateProcessStep(processStep);
	}

	public static org.opencps.dossiermgt.model.ProcessStep updateProcessStepDB(
		long userId, long groupId, long serviceProcessId, String stepCode,
		String stepName, String sequenceNo, String groupName,
		String dossierStatus, String dossierSubStatus, Double durationCount,
		String instructionNote, String briefNote, String roleAsStep,
		Integer checkInput,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateProcessStepDB(userId, groupId, serviceProcessId,
			stepCode, stepName, sequenceNo, groupName, dossierStatus,
			dossierSubStatus, durationCount, instructionNote, briefNote,
			roleAsStep, checkInput, serviceContext);
	}

	public static ProcessStepLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessStepLocalService, ProcessStepLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessStepLocalService.class);

		ServiceTracker<ProcessStepLocalService, ProcessStepLocalService> serviceTracker =
			new ServiceTracker<ProcessStepLocalService, ProcessStepLocalService>(bundle.getBundleContext(),
				ProcessStepLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}