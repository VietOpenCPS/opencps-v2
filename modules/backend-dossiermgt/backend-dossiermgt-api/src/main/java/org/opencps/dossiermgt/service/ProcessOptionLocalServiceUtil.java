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
 * Provides the local service utility for ProcessOption. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.ProcessOptionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ProcessOptionLocalService
 * @see org.opencps.dossiermgt.service.base.ProcessOptionLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ProcessOptionLocalServiceImpl
 * @generated
 */
@ProviderType
public class ProcessOptionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ProcessOptionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the process option to the database. Also notifies the appropriate model listeners.
	*
	* @param processOption the process option
	* @return the process option that was added
	*/
	public static org.opencps.dossiermgt.model.ProcessOption addProcessOption(
		org.opencps.dossiermgt.model.ProcessOption processOption) {
		return getService().addProcessOption(processOption);
	}

	public static long countLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLucene(params, searchContext);
	}

	/**
	* Creates a new process option with the primary key. Does not add the process option to the database.
	*
	* @param processOptionId the primary key for the new process option
	* @return the new process option
	*/
	public static org.opencps.dossiermgt.model.ProcessOption createProcessOption(
		long processOptionId) {
		return getService().createProcessOption(processOptionId);
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
	* Deletes the process option with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processOptionId the primary key of the process option
	* @return the process option that was removed
	* @throws PortalException if a process option with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessOption deleteProcessOption(
		long processOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteProcessOption(processOptionId);
	}

	/**
	* Deletes the process option from the database. Also notifies the appropriate model listeners.
	*
	* @param processOption the process option
	* @return the process option that was removed
	*/
	public static org.opencps.dossiermgt.model.ProcessOption deleteProcessOption(
		org.opencps.dossiermgt.model.ProcessOption processOption) {
		return getService().deleteProcessOption(processOption);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.ProcessOption fetchProcessOption(
		long processOptionId) {
		return getService().fetchProcessOption(processOptionId);
	}

	/**
	* Returns the process option matching the UUID and group.
	*
	* @param uuid the process option's UUID
	* @param groupId the primary key of the group
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessOption fetchProcessOptionByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchProcessOptionByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ProcessOption> findAll(
		int start, int end) {
		return getService().findAll(start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.dossiermgt.model.ProcessOption getByDTPLNoAndServiceCF(
		long groupId, String dossierTemplateNo, long serviceConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getByDTPLNoAndServiceCF(groupId, dossierTemplateNo,
			serviceConfigId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ProcessOption> getByServiceProcessId(
		long serviceConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getByServiceProcessId(serviceConfigId);
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
	* Returns the process option with the primary key.
	*
	* @param processOptionId the primary key of the process option
	* @return the process option
	* @throws PortalException if a process option with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessOption getProcessOption(
		long processOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcessOption(processOptionId);
	}

	/**
	* Returns the process option matching the UUID and group.
	*
	* @param uuid the process option's UUID
	* @param groupId the primary key of the group
	* @return the matching process option
	* @throws PortalException if a matching process option could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessOption getProcessOptionByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcessOptionByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<org.opencps.dossiermgt.model.ProcessOption> getProcessOptions(
		int start, int end) {
		return getService().getProcessOptions(start, end);
	}

	/**
	* Returns all the process options matching the UUID and company.
	*
	* @param uuid the UUID of the process options
	* @param companyId the primary key of the company
	* @return the matching process options, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ProcessOption> getProcessOptionsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getProcessOptionsByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.dossiermgt.model.ProcessOption> getProcessOptionsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ProcessOption> orderByComparator) {
		return getService()
				   .getProcessOptionsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of process options.
	*
	* @return the number of process options
	*/
	public static int getProcessOptionsCount() {
		return getService().getProcessOptionsCount();
	}

	public static org.opencps.dossiermgt.model.ProcessOption removeProcessOption(
		long processOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeProcessOption(processOptionId);
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

	public static org.opencps.dossiermgt.model.ProcessOption updateOptionDB(
		long userId, long groupId, String optionCode, String optionName,
		long serviceConfigId, Integer seqOrder, String autoSelect,
		String instructionNote, String submissionNote, String templateNo,
		String templateName, String processNo, String processName,
		String registerBookCode, Integer sampleCount,
		com.liferay.portal.kernel.service.ServiceContext context) {
		return getService()
				   .updateOptionDB(userId, groupId, optionCode, optionName,
			serviceConfigId, seqOrder, autoSelect, instructionNote,
			submissionNote, templateNo, templateName, processNo, processName,
			registerBookCode, sampleCount, context);
	}

	public static org.opencps.dossiermgt.model.ProcessOption updateProcessOption(
		long groupId, String optionName, long processOptionId,
		long serviceConfigId, int seqOrder, String autoSelect,
		String instructionNote, String submissionNote, long dossierTemplateId,
		long serviceProcessId,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateProcessOption(groupId, optionName, processOptionId,
			serviceConfigId, seqOrder, autoSelect, instructionNote,
			submissionNote, dossierTemplateId, serviceProcessId, context);
	}

	/**
	* Updates the process option in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processOption the process option
	* @return the process option that was updated
	*/
	public static org.opencps.dossiermgt.model.ProcessOption updateProcessOption(
		org.opencps.dossiermgt.model.ProcessOption processOption) {
		return getService().updateProcessOption(processOption);
	}

	public static ProcessOptionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessOptionLocalService, ProcessOptionLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessOptionLocalService.class);

		ServiceTracker<ProcessOptionLocalService, ProcessOptionLocalService> serviceTracker =
			new ServiceTracker<ProcessOptionLocalService, ProcessOptionLocalService>(bundle.getBundleContext(),
				ProcessOptionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}