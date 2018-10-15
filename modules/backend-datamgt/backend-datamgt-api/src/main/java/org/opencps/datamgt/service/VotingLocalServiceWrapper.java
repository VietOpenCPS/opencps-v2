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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VotingLocalService}.
 *
 * @author khoavu
 * @see VotingLocalService
 * @generated
 */
@ProviderType
public class VotingLocalServiceWrapper implements VotingLocalService,
	ServiceWrapper<VotingLocalService> {
	public VotingLocalServiceWrapper(VotingLocalService votingLocalService) {
		_votingLocalService = votingLocalService;
	}

	/**
	* Adds the voting to the database. Also notifies the appropriate model listeners.
	*
	* @param voting the voting
	* @return the voting that was added
	*/
	@Override
	public org.opencps.datamgt.model.Voting addVoting(
		org.opencps.datamgt.model.Voting voting) {
		return _votingLocalService.addVoting(voting);
	}

	/**
	* Creates a new voting with the primary key. Does not add the voting to the database.
	*
	* @param votingId the primary key for the new voting
	* @return the new voting
	*/
	@Override
	public org.opencps.datamgt.model.Voting createVoting(long votingId) {
		return _votingLocalService.createVoting(votingId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _votingLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the voting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingId the primary key of the voting
	* @return the voting that was removed
	* @throws PortalException if a voting with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Voting deleteVoting(long votingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _votingLocalService.deleteVoting(votingId);
	}

	/**
	* Deletes the voting from the database. Also notifies the appropriate model listeners.
	*
	* @param voting the voting
	* @return the voting that was removed
	*/
	@Override
	public org.opencps.datamgt.model.Voting deleteVoting(
		org.opencps.datamgt.model.Voting voting) {
		return _votingLocalService.deleteVoting(voting);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _votingLocalService.dynamicQuery();
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
		return _votingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _votingLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _votingLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _votingLocalService.dynamicQueryCount(dynamicQuery);
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
		return _votingLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.datamgt.model.Voting fetchVoting(long votingId) {
		return _votingLocalService.fetchVoting(votingId);
	}

	/**
	* Returns the voting matching the UUID and group.
	*
	* @param uuid the voting's UUID
	* @param groupId the primary key of the group
	* @return the matching voting, or <code>null</code> if a matching voting could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Voting fetchVotingByUuidAndGroupId(
		String uuid, long groupId) {
		return _votingLocalService.fetchVotingByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _votingLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _votingLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _votingLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _votingLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _votingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the voting with the primary key.
	*
	* @param votingId the primary key of the voting
	* @return the voting
	* @throws PortalException if a voting with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Voting getVoting(long votingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _votingLocalService.getVoting(votingId);
	}

	/**
	* Returns the voting matching the UUID and group.
	*
	* @param uuid the voting's UUID
	* @param groupId the primary key of the group
	* @return the matching voting
	* @throws PortalException if a matching voting could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Voting getVotingByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _votingLocalService.getVotingByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the votings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @return the range of votings
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.Voting> getVotings(
		int start, int end) {
		return _votingLocalService.getVotings(start, end);
	}

	/**
	* Returns all the votings matching the UUID and company.
	*
	* @param uuid the UUID of the votings
	* @param companyId the primary key of the company
	* @return the matching votings, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.Voting> getVotingsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _votingLocalService.getVotingsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of votings matching the UUID and company.
	*
	* @param uuid the UUID of the votings
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching votings, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.Voting> getVotingsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.Voting> orderByComparator) {
		return _votingLocalService.getVotingsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of votings.
	*
	* @return the number of votings
	*/
	@Override
	public int getVotingsCount() {
		return _votingLocalService.getVotingsCount();
	}

	/**
	* Updates the voting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param voting the voting
	* @return the voting that was updated
	*/
	@Override
	public org.opencps.datamgt.model.Voting updateVoting(
		org.opencps.datamgt.model.Voting voting) {
		return _votingLocalService.updateVoting(voting);
	}

	@Override
	public VotingLocalService getWrappedService() {
		return _votingLocalService;
	}

	@Override
	public void setWrappedService(VotingLocalService votingLocalService) {
		_votingLocalService = votingLocalService;
	}

	private VotingLocalService _votingLocalService;
}