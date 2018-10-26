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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProcessStepLocalService}.
 *
 * @author huymq
 * @see ProcessStepLocalService
 * @generated
 */
@ProviderType
public class ProcessStepLocalServiceWrapper implements ProcessStepLocalService,
	ServiceWrapper<ProcessStepLocalService> {
	public ProcessStepLocalServiceWrapper(
		ProcessStepLocalService processStepLocalService) {
		_processStepLocalService = processStepLocalService;
	}

	/**
	* Adds the process step to the database. Also notifies the appropriate model listeners.
	*
	* @param processStep the process step
	* @return the process step that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStep addProcessStep(
		org.opencps.dossiermgt.model.ProcessStep processStep) {
		return _processStepLocalService.addProcessStep(processStep);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessStep adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _processStepLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessStep adminProcessDelete(Long id) {
		return _processStepLocalService.adminProcessDelete(id);
	}

	@Override
	public long countLucene(java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _processStepLocalService.countLucene(params, searchContext);
	}

	/**
	* Creates a new process step with the primary key. Does not add the process step to the database.
	*
	* @param processStepId the primary key for the new process step
	* @return the new process step
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStep createProcessStep(
		long processStepId) {
		return _processStepLocalService.createProcessStep(processStepId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processStepLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the process step with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processStepId the primary key of the process step
	* @return the process step that was removed
	* @throws PortalException if a process step with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStep deleteProcessStep(
		long processStepId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processStepLocalService.deleteProcessStep(processStepId);
	}

	/**
	* Deletes the process step from the database. Also notifies the appropriate model listeners.
	*
	* @param processStep the process step
	* @return the process step that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStep deleteProcessStep(
		org.opencps.dossiermgt.model.ProcessStep processStep) {
		return _processStepLocalService.deleteProcessStep(processStep);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _processStepLocalService.dynamicQuery();
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
		return _processStepLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _processStepLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _processStepLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _processStepLocalService.dynamicQueryCount(dynamicQuery);
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
		return _processStepLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessStep fetchBySC_GID(
		String stepCode, long groupId, long serviceProcessId) {
		return _processStepLocalService.fetchBySC_GID(stepCode, groupId,
			serviceProcessId);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessStep fetchProcessStep(
		long processStepId) {
		return _processStepLocalService.fetchProcessStep(processStepId);
	}

	/**
	* Returns the process step matching the UUID and group.
	*
	* @param uuid the process step's UUID
	* @param groupId the primary key of the group
	* @return the matching process step, or <code>null</code> if a matching process step could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStep fetchProcessStepByUuidAndGroupId(
		String uuid, long groupId) {
		return _processStepLocalService.fetchProcessStepByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessStep> findByG_SP_SNO(
		long groupId, long serviceProcessId, String sequenceNo) {
		return _processStepLocalService.findByG_SP_SNO(groupId,
			serviceProcessId, sequenceNo);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _processStepLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessStep> getBySC_SPID(
		String stepCode, long serviceProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processStepLocalService.getBySC_SPID(stepCode, serviceProcessId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessStep> getByStatusAnsSubStatus(
		String dossierStatus, String dossierSubStatus, long groupId) {
		return _processStepLocalService.getByStatusAnsSubStatus(dossierStatus,
			dossierSubStatus, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _processStepLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _processStepLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _processStepLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processStepLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the process step with the primary key.
	*
	* @param processStepId the primary key of the process step
	* @return the process step
	* @throws PortalException if a process step with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStep getProcessStep(
		long processStepId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processStepLocalService.getProcessStep(processStepId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessStep> getProcessStepbyServiceProcessId(
		long serviceProcessId) {
		return _processStepLocalService.getProcessStepbyServiceProcessId(serviceProcessId);
	}

	/**
	* Returns the process step matching the UUID and group.
	*
	* @param uuid the process step's UUID
	* @param groupId the primary key of the group
	* @return the matching process step
	* @throws PortalException if a matching process step could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStep getProcessStepByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processStepLocalService.getProcessStepByUuidAndGroupId(uuid,
			groupId);
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
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessStep> getProcessSteps(
		int start, int end) {
		return _processStepLocalService.getProcessSteps(start, end);
	}

	/**
	* Returns all the process steps matching the UUID and company.
	*
	* @param uuid the UUID of the process steps
	* @param companyId the primary key of the company
	* @return the matching process steps, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessStep> getProcessStepsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _processStepLocalService.getProcessStepsByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessStep> getProcessStepsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ProcessStep> orderByComparator) {
		return _processStepLocalService.getProcessStepsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of process steps.
	*
	* @return the number of process steps
	*/
	@Override
	public int getProcessStepsCount() {
		return _processStepLocalService.getProcessStepsCount();
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessStep removeProcessStep(
		long processStepId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processStepLocalService.removeProcessStep(processStepId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _processStepLocalService.searchLucene(params, sorts, start, end,
			searchContext);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessStep updateProcessStep(
		long groupId, long processStepId, String stepCode, String stepName,
		long serviceProcessId, String sequenceNo, String dossierStatus,
		String dossierSubStatus, int durationCount, String customProcessUrl,
		String stepInstruction, String briefNote, boolean editable,
		String lockState,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processStepLocalService.updateProcessStep(groupId,
			processStepId, stepCode, stepName, serviceProcessId, sequenceNo,
			dossierStatus, dossierSubStatus, durationCount, customProcessUrl,
			stepInstruction, briefNote, editable, lockState, context);
	}

	/**
	* Updates the process step in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processStep the process step
	* @return the process step that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStep updateProcessStep(
		org.opencps.dossiermgt.model.ProcessStep processStep) {
		return _processStepLocalService.updateProcessStep(processStep);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessStep updateProcessStepDB(
		long userId, long groupId, long serviceProcessId, String stepCode,
		String stepName, String sequenceNo, String groupName,
		String dossierStatus, String dossierSubStatus, Double durationCount,
		String instructionNote, String briefNote, String roleAsStep,
		Integer checkInput,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processStepLocalService.updateProcessStepDB(userId, groupId,
			serviceProcessId, stepCode, stepName, sequenceNo, groupName,
			dossierStatus, dossierSubStatus, durationCount, instructionNote,
			briefNote, roleAsStep, checkInput, serviceContext);
	}

	@Override
	public ProcessStepLocalService getWrappedService() {
		return _processStepLocalService;
	}

	@Override
	public void setWrappedService(
		ProcessStepLocalService processStepLocalService) {
		_processStepLocalService = processStepLocalService;
	}

	private ProcessStepLocalService _processStepLocalService;
}