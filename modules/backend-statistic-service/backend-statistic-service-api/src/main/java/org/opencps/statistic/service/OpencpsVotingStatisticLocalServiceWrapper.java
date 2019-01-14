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

package org.opencps.statistic.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OpencpsVotingStatisticLocalService}.
 *
 * @author khoavu
 * @see OpencpsVotingStatisticLocalService
 * @generated
 */
@ProviderType
public class OpencpsVotingStatisticLocalServiceWrapper
	implements OpencpsVotingStatisticLocalService,
		ServiceWrapper<OpencpsVotingStatisticLocalService> {
	public OpencpsVotingStatisticLocalServiceWrapper(
		OpencpsVotingStatisticLocalService opencpsVotingStatisticLocalService) {
		_opencpsVotingStatisticLocalService = opencpsVotingStatisticLocalService;
	}

	/**
	* Adds the opencps voting statistic to the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsVotingStatistic the opencps voting statistic
	* @return the opencps voting statistic that was added
	*/
	@Override
	public org.opencps.statistic.model.OpencpsVotingStatistic addOpencpsVotingStatistic(
		org.opencps.statistic.model.OpencpsVotingStatistic opencpsVotingStatistic) {
		return _opencpsVotingStatisticLocalService.addOpencpsVotingStatistic(opencpsVotingStatistic);
	}

	@Override
	public org.opencps.statistic.model.OpencpsVotingStatistic checkExsit(
		long groupId, int month, int year, String govAgency, String domain,
		String votingCode) {
		return _opencpsVotingStatisticLocalService.checkExsit(groupId, month,
			year, govAgency, domain, votingCode);
	}

	/**
	* Creates a new opencps voting statistic with the primary key. Does not add the opencps voting statistic to the database.
	*
	* @param votingStatisticId the primary key for the new opencps voting statistic
	* @return the new opencps voting statistic
	*/
	@Override
	public org.opencps.statistic.model.OpencpsVotingStatistic createOpencpsVotingStatistic(
		long votingStatisticId) {
		return _opencpsVotingStatisticLocalService.createOpencpsVotingStatistic(votingStatisticId);
	}

	/**
	* Deletes the opencps voting statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingStatisticId the primary key of the opencps voting statistic
	* @return the opencps voting statistic that was removed
	* @throws PortalException if a opencps voting statistic with the primary key could not be found
	*/
	@Override
	public org.opencps.statistic.model.OpencpsVotingStatistic deleteOpencpsVotingStatistic(
		long votingStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsVotingStatisticLocalService.deleteOpencpsVotingStatistic(votingStatisticId);
	}

	/**
	* Deletes the opencps voting statistic from the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsVotingStatistic the opencps voting statistic
	* @return the opencps voting statistic that was removed
	*/
	@Override
	public org.opencps.statistic.model.OpencpsVotingStatistic deleteOpencpsVotingStatistic(
		org.opencps.statistic.model.OpencpsVotingStatistic opencpsVotingStatistic) {
		return _opencpsVotingStatisticLocalService.deleteOpencpsVotingStatistic(opencpsVotingStatistic);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsVotingStatisticLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _opencpsVotingStatisticLocalService.dynamicQuery();
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
		return _opencpsVotingStatisticLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _opencpsVotingStatisticLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _opencpsVotingStatisticLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _opencpsVotingStatisticLocalService.dynamicQueryCount(dynamicQuery);
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
		return _opencpsVotingStatisticLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.statistic.model.OpencpsVotingStatistic fetchOpencpsVotingStatistic(
		long votingStatisticId) {
		return _opencpsVotingStatisticLocalService.fetchOpencpsVotingStatistic(votingStatisticId);
	}

	/**
	* Returns the opencps voting statistic matching the UUID and group.
	*
	* @param uuid the opencps voting statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	@Override
	public org.opencps.statistic.model.OpencpsVotingStatistic fetchOpencpsVotingStatisticByUuidAndGroupId(
		String uuid, long groupId) {
		return _opencpsVotingStatisticLocalService.fetchOpencpsVotingStatisticByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsVotingStatistic> fetchVotingStatistic(
		long groupId, int month, int year, String votingCode, String domain,
		String govAgencyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _opencpsVotingStatisticLocalService.fetchVotingStatistic(groupId,
			month, year, votingCode, domain, govAgencyCode, start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _opencpsVotingStatisticLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _opencpsVotingStatisticLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _opencpsVotingStatisticLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the opencps voting statistic with the primary key.
	*
	* @param votingStatisticId the primary key of the opencps voting statistic
	* @return the opencps voting statistic
	* @throws PortalException if a opencps voting statistic with the primary key could not be found
	*/
	@Override
	public org.opencps.statistic.model.OpencpsVotingStatistic getOpencpsVotingStatistic(
		long votingStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsVotingStatisticLocalService.getOpencpsVotingStatistic(votingStatisticId);
	}

	/**
	* Returns the opencps voting statistic matching the UUID and group.
	*
	* @param uuid the opencps voting statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps voting statistic
	* @throws PortalException if a matching opencps voting statistic could not be found
	*/
	@Override
	public org.opencps.statistic.model.OpencpsVotingStatistic getOpencpsVotingStatisticByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsVotingStatisticLocalService.getOpencpsVotingStatisticByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the opencps voting statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of opencps voting statistics
	*/
	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsVotingStatistic> getOpencpsVotingStatistics(
		int start, int end) {
		return _opencpsVotingStatisticLocalService.getOpencpsVotingStatistics(start,
			end);
	}

	/**
	* Returns all the opencps voting statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps voting statistics
	* @param companyId the primary key of the company
	* @return the matching opencps voting statistics, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsVotingStatistic> getOpencpsVotingStatisticsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _opencpsVotingStatisticLocalService.getOpencpsVotingStatisticsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of opencps voting statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps voting statistics
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching opencps voting statistics, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsVotingStatistic> getOpencpsVotingStatisticsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.statistic.model.OpencpsVotingStatistic> orderByComparator) {
		return _opencpsVotingStatisticLocalService.getOpencpsVotingStatisticsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of opencps voting statistics.
	*
	* @return the number of opencps voting statistics
	*/
	@Override
	public int getOpencpsVotingStatisticsCount() {
		return _opencpsVotingStatisticLocalService.getOpencpsVotingStatisticsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _opencpsVotingStatisticLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsVotingStatisticLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public void removeVotingStatisticByD_M_Y(long groupId, String domainCode,
		int month, int year) {
		_opencpsVotingStatisticLocalService.removeVotingStatisticByD_M_Y(groupId,
			domainCode, month, year);
	}

	@Override
	public void removeVotingStatisticByMonthYear(long groupId, int month,
		int year) {
		_opencpsVotingStatisticLocalService.removeVotingStatisticByMonthYear(groupId,
			month, year);
	}

	@Override
	public void removeVotingStatisticByYear(long companyId, long groupId,
		int month, int year) {
		_opencpsVotingStatisticLocalService.removeVotingStatisticByYear(companyId,
			groupId, month, year);
	}

	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsVotingStatistic> searchVotingStatistic(
		long groupId, int month, int year, String votingCode, String domain,
		String govAgencyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _opencpsVotingStatisticLocalService.searchVotingStatistic(groupId,
			month, year, votingCode, domain, govAgencyCode, start, end);
	}

	/**
	* Updates the opencps voting statistic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param opencpsVotingStatistic the opencps voting statistic
	* @return the opencps voting statistic that was updated
	*/
	@Override
	public org.opencps.statistic.model.OpencpsVotingStatistic updateOpencpsVotingStatistic(
		org.opencps.statistic.model.OpencpsVotingStatistic opencpsVotingStatistic) {
		return _opencpsVotingStatisticLocalService.updateOpencpsVotingStatistic(opencpsVotingStatistic);
	}

	@Override
	public org.opencps.statistic.model.OpencpsVotingStatistic updateVotingStatistic(
		long votingStatisticId, long companyId, long groupId, long userId,
		String userName, int month, int year, String votingSubject,
		int totalVoted, int veryGoodCount, int goodCount, int badCount,
		int percentVeryGood, int percentGood, int percentBad,
		String govAgencyCode, String govAgencyName, String domainCode,
		String domainName, String votingCode, int totalCount) {
		return _opencpsVotingStatisticLocalService.updateVotingStatistic(votingStatisticId,
			companyId, groupId, userId, userName, month, year, votingSubject,
			totalVoted, veryGoodCount, goodCount, badCount, percentVeryGood,
			percentGood, percentBad, govAgencyCode, govAgencyName, domainCode,
			domainName, votingCode, totalCount);
	}

	@Override
	public OpencpsVotingStatisticLocalService getWrappedService() {
		return _opencpsVotingStatisticLocalService;
	}

	@Override
	public void setWrappedService(
		OpencpsVotingStatisticLocalService opencpsVotingStatisticLocalService) {
		_opencpsVotingStatisticLocalService = opencpsVotingStatisticLocalService;
	}

	private OpencpsVotingStatisticLocalService _opencpsVotingStatisticLocalService;
}