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
 * Provides a wrapper for {@link ProcessActionLocalService}.
 *
 * @author huymq
 * @see ProcessActionLocalService
 * @generated
 */
@ProviderType
public class ProcessActionLocalServiceWrapper
	implements ProcessActionLocalService,
		ServiceWrapper<ProcessActionLocalService> {
	public ProcessActionLocalServiceWrapper(
		ProcessActionLocalService processActionLocalService) {
		_processActionLocalService = processActionLocalService;
	}

	/**
	* Adds the process action to the database. Also notifies the appropriate model listeners.
	*
	* @param processAction the process action
	* @return the process action that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessAction addProcessAction(
		org.opencps.dossiermgt.model.ProcessAction processAction) {
		return _processActionLocalService.addProcessAction(processAction);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessAction adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _processActionLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessAction adminProcessDelete(
		Long id) {
		return _processActionLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new process action with the primary key. Does not add the process action to the database.
	*
	* @param processActionId the primary key for the new process action
	* @return the new process action
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessAction createProcessAction(
		long processActionId) {
		return _processActionLocalService.createProcessAction(processActionId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the process action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processActionId the primary key of the process action
	* @return the process action that was removed
	* @throws PortalException if a process action with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessAction deleteProcessAction(
		long processActionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.deleteProcessAction(processActionId);
	}

	/**
	* Deletes the process action from the database. Also notifies the appropriate model listeners.
	*
	* @param processAction the process action
	* @return the process action that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessAction deleteProcessAction(
		org.opencps.dossiermgt.model.ProcessAction processAction) {
		return _processActionLocalService.deleteProcessAction(processAction);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _processActionLocalService.dynamicQuery();
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
		return _processActionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _processActionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _processActionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _processActionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _processActionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessAction fetchBySPI_PRESC_AEV(
		long serviceProcessId, String preStepCode, String autoEvent) {
		return _processActionLocalService.fetchBySPI_PRESC_AEV(serviceProcessId,
			preStepCode, autoEvent);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessAction fetchBySPID_AC(
		long serviceProcessId, String actionCode) {
		return _processActionLocalService.fetchBySPID_AC(serviceProcessId,
			actionCode);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessAction fetchProcessAction(
		long processActionId) {
		return _processActionLocalService.fetchProcessAction(processActionId);
	}

	/**
	* Returns the process action matching the UUID and group.
	*
	* @param uuid the process action's UUID
	* @param groupId the primary key of the group
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessAction fetchProcessActionByUuidAndGroupId(
		String uuid, long groupId) {
		return _processActionLocalService.fetchProcessActionByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _processActionLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessAction> getByActionCode(
		long groupId, String actionCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.getByActionCode(groupId, actionCode);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessAction> getByActionCode(
		long groupId, String actionCode, long serviceProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.getByActionCode(groupId, actionCode,
			serviceProcessId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessAction> getByGroupAndAutoEvent(
		long groupId, String autoEvent, int start, int end) {
		return _processActionLocalService.getByGroupAndAutoEvent(groupId,
			autoEvent, start, end);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessAction getByNameActionNo(
		long serviceProcessId, String actionCode, String actionName) {
		return _processActionLocalService.getByNameActionNo(serviceProcessId,
			actionCode, actionName);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessAction getByServiceProcess(
		long serviceProcessId, String actionCode) {
		return _processActionLocalService.getByServiceProcess(serviceProcessId,
			actionCode);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessAction> getByServiceStepCode(
		long groupId, long serviceProcessId, String preStepCode) {
		return _processActionLocalService.getByServiceStepCode(groupId,
			serviceProcessId, preStepCode);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _processActionLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _processActionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _processActionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the process action with the primary key.
	*
	* @param processActionId the primary key of the process action
	* @return the process action
	* @throws PortalException if a process action with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessAction getProcessAction(
		long processActionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.getProcessAction(processActionId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessAction> getProcessActionByG_SPID_PRESC(
		long groupId, long serviceProcessId, String preStepCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.getProcessActionByG_SPID_PRESC(groupId,
			serviceProcessId, preStepCode);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessAction> getProcessActionbyServiceProcessId(
		long serviceProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.getProcessActionbyServiceProcessId(serviceProcessId);
	}

	/**
	* Returns the process action matching the UUID and group.
	*
	* @param uuid the process action's UUID
	* @param groupId the primary key of the group
	* @return the matching process action
	* @throws PortalException if a matching process action could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessAction getProcessActionByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.getProcessActionByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the process actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of process actions
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessAction> getProcessActions(
		int start, int end) {
		return _processActionLocalService.getProcessActions(start, end);
	}

	/**
	* Returns all the process actions matching the UUID and company.
	*
	* @param uuid the UUID of the process actions
	* @param companyId the primary key of the company
	* @return the matching process actions, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessAction> getProcessActionsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _processActionLocalService.getProcessActionsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of process actions matching the UUID and company.
	*
	* @param uuid the UUID of the process actions
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching process actions, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessAction> getProcessActionsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ProcessAction> orderByComparator) {
		return _processActionLocalService.getProcessActionsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of process actions.
	*
	* @return the number of process actions
	*/
	@Override
	public int getProcessActionsCount() {
		return _processActionLocalService.getProcessActionsCount();
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessAction removeProcessAction(
		long processActionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.removeProcessAction(processActionId);
	}

	@Override
	public long searchCount(java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _processActionLocalService.searchCount(params, searchContext);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _processActionLocalService.searchLucene(params, sorts, start,
			end, searchContext);
	}

	@Deprecated
	@Override
	public org.opencps.dossiermgt.model.ProcessAction updateProcessAction(
		long groupId, long processActionId, long serviceProcessId,
		String preStepCode, String postStepCode, String autoEvent,
		String preCondition, String actionCode, String actionName,
		int allowAssignUser, long assignUserId, Integer requestPayment,
		String paymentFee, String createDossierFiles,
		String returnDossierFiles, String makeBriefNote, String syncActionCode,
		boolean rollbackable, boolean createDossierNo, boolean eSignature,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.updateProcessAction(groupId,
			processActionId, serviceProcessId, preStepCode, postStepCode,
			autoEvent, preCondition, actionCode, actionName, allowAssignUser,
			assignUserId, requestPayment, paymentFee, createDossierFiles,
			returnDossierFiles, makeBriefNote, syncActionCode, rollbackable,
			createDossierNo, eSignature, context);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessAction updateProcessAction(
		long groupId, long processActionId, long serviceProcessId,
		String preStepCode, String postStepCode, String autoEvent,
		String preCondition, String actionCode, String actionName,
		int allowAssignUser, long assignUserId, Integer requestPayment,
		String paymentFee, String createDossierFiles,
		String returnDossierFiles, String makeBriefNote, String syncActionCode,
		boolean rollbackable, boolean createDossierNo, boolean eSignature,
		String configNote, String dossierTemplateNo,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.updateProcessAction(groupId,
			processActionId, serviceProcessId, preStepCode, postStepCode,
			autoEvent, preCondition, actionCode, actionName, allowAssignUser,
			assignUserId, requestPayment, paymentFee, createDossierFiles,
			returnDossierFiles, makeBriefNote, syncActionCode, rollbackable,
			createDossierNo, eSignature, configNote, dossierTemplateNo, context);
	}

	@Deprecated
	@Override
	public org.opencps.dossiermgt.model.ProcessAction updateProcessAction(
		long groupId, long processActionId, long serviceProcessId,
		String preStepCode, String postStepCode, String autoEvent,
		String preCondition, String actionCode, String actionName,
		int allowAssignUser, long assignUserId, Integer requestPayment,
		String paymentFee, String createDossierFiles,
		String returnDossierFiles, String makeBriefNote, String syncActionCode,
		boolean rollbackable,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.updateProcessAction(groupId,
			processActionId, serviceProcessId, preStepCode, postStepCode,
			autoEvent, preCondition, actionCode, actionName, allowAssignUser,
			assignUserId, requestPayment, paymentFee, createDossierFiles,
			returnDossierFiles, makeBriefNote, syncActionCode, rollbackable,
			context);
	}

	/**
	* Updates the process action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processAction the process action
	* @return the process action that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessAction updateProcessAction(
		org.opencps.dossiermgt.model.ProcessAction processAction) {
		return _processActionLocalService.updateProcessAction(processAction);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessAction updateProcessActionDB(
		long userId, long groupId, long serviceProcessId, String actionCode,
		String actionName, String preStepCode, String postStepCode,
		String autoEvent, String preCondition, int allowAssignUser,
		long assignUserId, String assignUserName, Integer requestPayment,
		String paymentFee, String createDossierFiles,
		String returnDossierFiles, boolean eSignature, String signatureType,
		String createDossiers,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processActionLocalService.updateProcessActionDB(userId,
			groupId, serviceProcessId, actionCode, actionName, preStepCode,
			postStepCode, autoEvent, preCondition, allowAssignUser,
			assignUserId, assignUserName, requestPayment, paymentFee,
			createDossierFiles, returnDossierFiles, eSignature, signatureType,
			createDossiers, serviceContext);
	}

	@Override
	public ProcessActionLocalService getWrappedService() {
		return _processActionLocalService;
	}

	@Override
	public void setWrappedService(
		ProcessActionLocalService processActionLocalService) {
		_processActionLocalService = processActionLocalService;
	}

	private ProcessActionLocalService _processActionLocalService;
}