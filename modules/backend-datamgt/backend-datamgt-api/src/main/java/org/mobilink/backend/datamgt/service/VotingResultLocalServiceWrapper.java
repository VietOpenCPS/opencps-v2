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

package org.mobilink.backend.datamgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VotingResultLocalService}.
 *
 * @author Binhth
 * @see VotingResultLocalService
 * @generated
 */
@ProviderType
public class VotingResultLocalServiceWrapper implements VotingResultLocalService,
	ServiceWrapper<VotingResultLocalService> {
	public VotingResultLocalServiceWrapper(
		VotingResultLocalService votingResultLocalService) {
		_votingResultLocalService = votingResultLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _votingResultLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _votingResultLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _votingResultLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _votingResultLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _votingResultLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _votingResultLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of voting results.
	*
	* @return the number of voting results
	*/
	@Override
	public int getVotingResultsCount() {
		return _votingResultLocalService.getVotingResultsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _votingResultLocalService.getOSGiServiceIdentifier();
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
		return _votingResultLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _votingResultLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _votingResultLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the voting results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @return the range of voting results
	*/
	@Override
	public java.util.List<org.mobilink.backend.datamgt.model.VotingResult> getVotingResults(
		int start, int end) {
		return _votingResultLocalService.getVotingResults(start, end);
	}

	/**
	* Returns all the voting results matching the UUID and company.
	*
	* @param uuid the UUID of the voting results
	* @param companyId the primary key of the company
	* @return the matching voting results, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.mobilink.backend.datamgt.model.VotingResult> getVotingResultsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _votingResultLocalService.getVotingResultsByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<org.mobilink.backend.datamgt.model.VotingResult> getVotingResultsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.mobilink.backend.datamgt.model.VotingResult> orderByComparator) {
		return _votingResultLocalService.getVotingResultsByUuidAndCompanyId(uuid,
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
		return _votingResultLocalService.dynamicQueryCount(dynamicQuery);
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
		return _votingResultLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Adds the voting result to the database. Also notifies the appropriate model listeners.
	*
	* @param votingResult the voting result
	* @return the voting result that was added
	*/
	@Override
	public org.mobilink.backend.datamgt.model.VotingResult addVotingResult(
		org.mobilink.backend.datamgt.model.VotingResult votingResult) {
		return _votingResultLocalService.addVotingResult(votingResult);
	}

	/**
	* Creates a new voting result with the primary key. Does not add the voting result to the database.
	*
	* @param votingResultId the primary key for the new voting result
	* @return the new voting result
	*/
	@Override
	public org.mobilink.backend.datamgt.model.VotingResult createVotingResult(
		long votingResultId) {
		return _votingResultLocalService.createVotingResult(votingResultId);
	}

	/**
	* Deletes the voting result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingResultId the primary key of the voting result
	* @return the voting result that was removed
	* @throws PortalException if a voting result with the primary key could not be found
	*/
	@Override
	public org.mobilink.backend.datamgt.model.VotingResult deleteVotingResult(
		long votingResultId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _votingResultLocalService.deleteVotingResult(votingResultId);
	}

	/**
	* Deletes the voting result from the database. Also notifies the appropriate model listeners.
	*
	* @param votingResult the voting result
	* @return the voting result that was removed
	*/
	@Override
	public org.mobilink.backend.datamgt.model.VotingResult deleteVotingResult(
		org.mobilink.backend.datamgt.model.VotingResult votingResult) {
		return _votingResultLocalService.deleteVotingResult(votingResult);
	}

	@Override
	public org.mobilink.backend.datamgt.model.VotingResult fetchVotingResult(
		long votingResultId) {
		return _votingResultLocalService.fetchVotingResult(votingResultId);
	}

	/**
	* Returns the voting result matching the UUID and group.
	*
	* @param uuid the voting result's UUID
	* @param groupId the primary key of the group
	* @return the matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	@Override
	public org.mobilink.backend.datamgt.model.VotingResult fetchVotingResultByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _votingResultLocalService.fetchVotingResultByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the voting result with the primary key.
	*
	* @param votingResultId the primary key of the voting result
	* @return the voting result
	* @throws PortalException if a voting result with the primary key could not be found
	*/
	@Override
	public org.mobilink.backend.datamgt.model.VotingResult getVotingResult(
		long votingResultId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _votingResultLocalService.getVotingResult(votingResultId);
	}

	/**
	* Returns the voting result matching the UUID and group.
	*
	* @param uuid the voting result's UUID
	* @param groupId the primary key of the group
	* @return the matching voting result
	* @throws PortalException if a matching voting result could not be found
	*/
	@Override
	public org.mobilink.backend.datamgt.model.VotingResult getVotingResultByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _votingResultLocalService.getVotingResultByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the voting result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param votingResult the voting result
	* @return the voting result that was updated
	*/
	@Override
	public org.mobilink.backend.datamgt.model.VotingResult updateVotingResult(
		org.mobilink.backend.datamgt.model.VotingResult votingResult) {
		return _votingResultLocalService.updateVotingResult(votingResult);
	}

	@Override
	public VotingResultLocalService getWrappedService() {
		return _votingResultLocalService;
	}

	@Override
	public void setWrappedService(
		VotingResultLocalService votingResultLocalService) {
		_votingResultLocalService = votingResultLocalService;
	}

	private VotingResultLocalService _votingResultLocalService;
}