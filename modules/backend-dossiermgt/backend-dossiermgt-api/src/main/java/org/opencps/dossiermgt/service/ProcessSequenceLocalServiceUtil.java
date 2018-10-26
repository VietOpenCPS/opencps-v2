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
 * Provides the local service utility for ProcessSequence. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.ProcessSequenceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ProcessSequenceLocalService
 * @see org.opencps.dossiermgt.service.base.ProcessSequenceLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ProcessSequenceLocalServiceImpl
 * @generated
 */
@ProviderType
public class ProcessSequenceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ProcessSequenceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.dossiermgt.model.ProcessSequence addProcessSequence(
		long userId, long groupId, long serviceProcessId, String sequenceNo,
		String sequenceName, double durationCount)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addProcessSequence(userId, groupId, serviceProcessId,
			sequenceNo, sequenceName, durationCount);
	}

	/**
	* Adds the process sequence to the database. Also notifies the appropriate model listeners.
	*
	* @param processSequence the process sequence
	* @return the process sequence that was added
	*/
	public static org.opencps.dossiermgt.model.ProcessSequence addProcessSequence(
		org.opencps.dossiermgt.model.ProcessSequence processSequence) {
		return getService().addProcessSequence(processSequence);
	}

	public static org.opencps.dossiermgt.model.ProcessSequence adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.dossiermgt.model.ProcessSequence adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new process sequence with the primary key. Does not add the process sequence to the database.
	*
	* @param processSequenceId the primary key for the new process sequence
	* @return the new process sequence
	*/
	public static org.opencps.dossiermgt.model.ProcessSequence createProcessSequence(
		long processSequenceId) {
		return getService().createProcessSequence(processSequenceId);
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
	* Deletes the process sequence with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processSequenceId the primary key of the process sequence
	* @return the process sequence that was removed
	* @throws PortalException if a process sequence with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessSequence deleteProcessSequence(
		long processSequenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteProcessSequence(processSequenceId);
	}

	/**
	* Deletes the process sequence from the database. Also notifies the appropriate model listeners.
	*
	* @param processSequence the process sequence
	* @return the process sequence that was removed
	*/
	public static org.opencps.dossiermgt.model.ProcessSequence deleteProcessSequence(
		org.opencps.dossiermgt.model.ProcessSequence processSequence) {
		return getService().deleteProcessSequence(processSequence);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.ProcessSequence fetchProcessSequence(
		long processSequenceId) {
		return getService().fetchProcessSequence(processSequenceId);
	}

	/**
	* Returns the process sequence matching the UUID and group.
	*
	* @param uuid the process sequence's UUID
	* @param groupId the primary key of the group
	* @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessSequence fetchProcessSequenceByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchProcessSequenceByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ProcessSequence> findByG_SN(
		long groupId, String sequenceNo) {
		return getService().findByG_SN(groupId, sequenceNo);
	}

	public static org.opencps.dossiermgt.model.ProcessSequence findBySID_SNO(
		long groupId, long serviceProcessId, String sequenceNo) {
		return getService().findBySID_SNO(groupId, serviceProcessId, sequenceNo);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<org.opencps.dossiermgt.model.ProcessSequence> getByServiceProcess(
		long groupId, long serviceProcessId) {
		return getService().getByServiceProcess(groupId, serviceProcessId);
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
	* Returns the process sequence with the primary key.
	*
	* @param processSequenceId the primary key of the process sequence
	* @return the process sequence
	* @throws PortalException if a process sequence with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessSequence getProcessSequence(
		long processSequenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcessSequence(processSequenceId);
	}

	/**
	* Returns the process sequence matching the UUID and group.
	*
	* @param uuid the process sequence's UUID
	* @param groupId the primary key of the group
	* @return the matching process sequence
	* @throws PortalException if a matching process sequence could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessSequence getProcessSequenceByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcessSequenceByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the process sequences.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @return the range of process sequences
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ProcessSequence> getProcessSequences(
		int start, int end) {
		return getService().getProcessSequences(start, end);
	}

	/**
	* Returns all the process sequences matching the UUID and company.
	*
	* @param uuid the UUID of the process sequences
	* @param companyId the primary key of the company
	* @return the matching process sequences, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ProcessSequence> getProcessSequencesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getProcessSequencesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of process sequences matching the UUID and company.
	*
	* @param uuid the UUID of the process sequences
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching process sequences, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ProcessSequence> getProcessSequencesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ProcessSequence> orderByComparator) {
		return getService()
				   .getProcessSequencesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of process sequences.
	*
	* @return the number of process sequences
	*/
	public static int getProcessSequencesCount() {
		return getService().getProcessSequencesCount();
	}

	public static org.opencps.dossiermgt.model.ProcessSequence updateProcessSequence(
		long userId, long groupId, long processSequenceId,
		long serviceProcessId, String sequenceNo, String sequenceName,
		double durationCount)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateProcessSequence(userId, groupId, processSequenceId,
			serviceProcessId, sequenceNo, sequenceName, durationCount);
	}

	/**
	* Updates the process sequence in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processSequence the process sequence
	* @return the process sequence that was updated
	*/
	public static org.opencps.dossiermgt.model.ProcessSequence updateProcessSequence(
		org.opencps.dossiermgt.model.ProcessSequence processSequence) {
		return getService().updateProcessSequence(processSequence);
	}

	public static void updateProcessSequenceDB(long userId, long groupId,
		long serviceProcessId, String sequenceNo, String sequenceName,
		String sequenceRole, Double durationCount,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateProcessSequenceDB(userId, groupId, serviceProcessId,
			sequenceNo, sequenceName, sequenceRole, durationCount,
			serviceContext);
	}

	public static ProcessSequenceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessSequenceLocalService, ProcessSequenceLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessSequenceLocalService.class);

		ServiceTracker<ProcessSequenceLocalService, ProcessSequenceLocalService> serviceTracker =
			new ServiceTracker<ProcessSequenceLocalService, ProcessSequenceLocalService>(bundle.getBundleContext(),
				ProcessSequenceLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}