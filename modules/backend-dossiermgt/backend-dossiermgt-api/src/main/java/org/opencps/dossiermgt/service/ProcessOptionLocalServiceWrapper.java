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
 * Provides a wrapper for {@link ProcessOptionLocalService}.
 *
 * @author huymq
 * @see ProcessOptionLocalService
 * @generated
 */
@ProviderType
public class ProcessOptionLocalServiceWrapper
	implements ProcessOptionLocalService,
		ServiceWrapper<ProcessOptionLocalService> {
	public ProcessOptionLocalServiceWrapper(
		ProcessOptionLocalService processOptionLocalService) {
		_processOptionLocalService = processOptionLocalService;
	}

	/**
	* Adds the process option to the database. Also notifies the appropriate model listeners.
	*
	* @param processOption the process option
	* @return the process option that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessOption addProcessOption(
		org.opencps.dossiermgt.model.ProcessOption processOption) {
		return _processOptionLocalService.addProcessOption(processOption);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessOption adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _processOptionLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessOption adminProcessDelete(
		Long id) {
		return _processOptionLocalService.adminProcessDelete(id);
	}

	@Override
	public long countLucene(java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _processOptionLocalService.countLucene(params, searchContext);
	}

	/**
	* Creates a new process option with the primary key. Does not add the process option to the database.
	*
	* @param processOptionId the primary key for the new process option
	* @return the new process option
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessOption createProcessOption(
		long processOptionId) {
		return _processOptionLocalService.createProcessOption(processOptionId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processOptionLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the process option with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processOptionId the primary key of the process option
	* @return the process option that was removed
	* @throws PortalException if a process option with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessOption deleteProcessOption(
		long processOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processOptionLocalService.deleteProcessOption(processOptionId);
	}

	/**
	* Deletes the process option from the database. Also notifies the appropriate model listeners.
	*
	* @param processOption the process option
	* @return the process option that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessOption deleteProcessOption(
		org.opencps.dossiermgt.model.ProcessOption processOption) {
		return _processOptionLocalService.deleteProcessOption(processOption);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _processOptionLocalService.dynamicQuery();
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
		return _processOptionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _processOptionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _processOptionLocalService.dynamicQuery(dynamicQuery, start,
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
		return _processOptionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _processOptionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessOption fetchProcessOption(
		long processOptionId) {
		return _processOptionLocalService.fetchProcessOption(processOptionId);
	}

	/**
	* Returns the process option matching the UUID and group.
	*
	* @param uuid the process option's UUID
	* @param groupId the primary key of the group
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessOption fetchProcessOptionByUuidAndGroupId(
		String uuid, long groupId) {
		return _processOptionLocalService.fetchProcessOptionByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessOption> findAll(
		int start, int end) {
		return _processOptionLocalService.findAll(start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _processOptionLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessOption getByDTPLNoAndServiceCF(
		long groupId, String dossierTemplateNo, long serviceConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processOptionLocalService.getByDTPLNoAndServiceCF(groupId,
			dossierTemplateNo, serviceConfigId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessOption> getByServiceProcessId(
		long serviceConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processOptionLocalService.getByServiceProcessId(serviceConfigId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _processOptionLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _processOptionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _processOptionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processOptionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the process option with the primary key.
	*
	* @param processOptionId the primary key of the process option
	* @return the process option
	* @throws PortalException if a process option with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessOption getProcessOption(
		long processOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processOptionLocalService.getProcessOption(processOptionId);
	}

	/**
	* Returns the process option matching the UUID and group.
	*
	* @param uuid the process option's UUID
	* @param groupId the primary key of the group
	* @return the matching process option
	* @throws PortalException if a matching process option could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessOption getProcessOptionByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processOptionLocalService.getProcessOptionByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the process options.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @return the range of process options
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessOption> getProcessOptions(
		int start, int end) {
		return _processOptionLocalService.getProcessOptions(start, end);
	}

	/**
	* Returns all the process options matching the UUID and company.
	*
	* @param uuid the UUID of the process options
	* @param companyId the primary key of the company
	* @return the matching process options, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessOption> getProcessOptionsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _processOptionLocalService.getProcessOptionsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of process options matching the UUID and company.
	*
	* @param uuid the UUID of the process options
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching process options, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessOption> getProcessOptionsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ProcessOption> orderByComparator) {
		return _processOptionLocalService.getProcessOptionsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of process options.
	*
	* @return the number of process options
	*/
	@Override
	public int getProcessOptionsCount() {
		return _processOptionLocalService.getProcessOptionsCount();
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessOption removeProcessOption(
		long processOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processOptionLocalService.removeProcessOption(processOptionId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _processOptionLocalService.searchLucene(params, sorts, start,
			end, searchContext);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessOption updateOptionDB(
		long userId, long groupId, String optionCode, String optionName,
		long serviceConfigId, Integer seqOrder, String autoSelect,
		String instructionNote, String submissionNote, String templateNo,
		String templateName, String processNo, String processName,
		String registerBookCode, Integer sampleCount,
		com.liferay.portal.kernel.service.ServiceContext context) {
		return _processOptionLocalService.updateOptionDB(userId, groupId,
			optionCode, optionName, serviceConfigId, seqOrder, autoSelect,
			instructionNote, submissionNote, templateNo, templateName,
			processNo, processName, registerBookCode, sampleCount, context);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessOption updateProcessOption(
		long groupId, String optionName, long processOptionId,
		long serviceConfigId, int seqOrder, String autoSelect,
		String instructionNote, String submissionNote, long dossierTemplateId,
		long serviceProcessId,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processOptionLocalService.updateProcessOption(groupId,
			optionName, processOptionId, serviceConfigId, seqOrder, autoSelect,
			instructionNote, submissionNote, dossierTemplateId,
			serviceProcessId, context);
	}

	/**
	* Updates the process option in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processOption the process option
	* @return the process option that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessOption updateProcessOption(
		org.opencps.dossiermgt.model.ProcessOption processOption) {
		return _processOptionLocalService.updateProcessOption(processOption);
	}

	@Override
	public ProcessOptionLocalService getWrappedService() {
		return _processOptionLocalService;
	}

	@Override
	public void setWrappedService(
		ProcessOptionLocalService processOptionLocalService) {
		_processOptionLocalService = processOptionLocalService;
	}

	private ProcessOptionLocalService _processOptionLocalService;
}