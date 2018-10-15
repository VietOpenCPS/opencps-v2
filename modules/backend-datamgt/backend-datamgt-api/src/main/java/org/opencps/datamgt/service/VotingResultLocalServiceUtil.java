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

package org.opencps.datamgt.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for VotingResult. This utility wraps
 * {@link org.opencps.datamgt.service.impl.VotingResultLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see VotingResultLocalService
 * @see org.opencps.datamgt.service.base.VotingResultLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.impl.VotingResultLocalServiceImpl
 * @generated
 */
@ProviderType
public class VotingResultLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.datamgt.service.impl.VotingResultLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the voting result to the database. Also notifies the appropriate model listeners.
	*
	* @param votingResult the voting result
	* @return the voting result that was added
	*/
	public static org.opencps.datamgt.model.VotingResult addVotingResult(
		org.opencps.datamgt.model.VotingResult votingResult) {
		return getService().addVotingResult(votingResult);
	}

	/**
	* Creates a new voting result with the primary key. Does not add the voting result to the database.
	*
	* @param votingResultId the primary key for the new voting result
	* @return the new voting result
	*/
	public static org.opencps.datamgt.model.VotingResult createVotingResult(
		long votingResultId) {
		return getService().createVotingResult(votingResultId);
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
	* Deletes the voting result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingResultId the primary key of the voting result
	* @return the voting result that was removed
	* @throws PortalException if a voting result with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.VotingResult deleteVotingResult(
		long votingResultId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteVotingResult(votingResultId);
	}

	/**
	* Deletes the voting result from the database. Also notifies the appropriate model listeners.
	*
	* @param votingResult the voting result
	* @return the voting result that was removed
	*/
	public static org.opencps.datamgt.model.VotingResult deleteVotingResult(
		org.opencps.datamgt.model.VotingResult votingResult) {
		return getService().deleteVotingResult(votingResult);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.datamgt.model.VotingResult fetchVotingResult(
		long votingResultId) {
		return getService().fetchVotingResult(votingResultId);
	}

	/**
	* Returns the voting result matching the UUID and group.
	*
	* @param uuid the voting result's UUID
	* @param groupId the primary key of the group
	* @return the matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public static org.opencps.datamgt.model.VotingResult fetchVotingResultByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchVotingResultByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
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
	* Returns the voting result with the primary key.
	*
	* @param votingResultId the primary key of the voting result
	* @return the voting result
	* @throws PortalException if a voting result with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.VotingResult getVotingResult(
		long votingResultId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getVotingResult(votingResultId);
	}

	/**
	* Returns the voting result matching the UUID and group.
	*
	* @param uuid the voting result's UUID
	* @param groupId the primary key of the group
	* @return the matching voting result
	* @throws PortalException if a matching voting result could not be found
	*/
	public static org.opencps.datamgt.model.VotingResult getVotingResultByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getVotingResultByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the voting results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @return the range of voting results
	*/
	public static java.util.List<org.opencps.datamgt.model.VotingResult> getVotingResults(
		int start, int end) {
		return getService().getVotingResults(start, end);
	}

	/**
	* Returns all the voting results matching the UUID and company.
	*
	* @param uuid the UUID of the voting results
	* @param companyId the primary key of the company
	* @return the matching voting results, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.datamgt.model.VotingResult> getVotingResultsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getVotingResultsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of voting results matching the UUID and company.
	*
	* @param uuid the UUID of the voting results
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching voting results, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.datamgt.model.VotingResult> getVotingResultsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.VotingResult> orderByComparator) {
		return getService()
				   .getVotingResultsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of voting results.
	*
	* @return the number of voting results
	*/
	public static int getVotingResultsCount() {
		return getService().getVotingResultsCount();
	}

	/**
	* Updates the voting result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param votingResult the voting result
	* @return the voting result that was updated
	*/
	public static org.opencps.datamgt.model.VotingResult updateVotingResult(
		org.opencps.datamgt.model.VotingResult votingResult) {
		return getService().updateVotingResult(votingResult);
	}

	public static VotingResultLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VotingResultLocalService, VotingResultLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(VotingResultLocalService.class);

		ServiceTracker<VotingResultLocalService, VotingResultLocalService> serviceTracker =
			new ServiceTracker<VotingResultLocalService, VotingResultLocalService>(bundle.getBundleContext(),
				VotingResultLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}