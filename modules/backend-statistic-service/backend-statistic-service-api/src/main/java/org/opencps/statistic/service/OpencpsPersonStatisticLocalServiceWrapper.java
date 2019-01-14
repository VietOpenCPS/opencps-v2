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
 * Provides a wrapper for {@link OpencpsPersonStatisticLocalService}.
 *
 * @author khoavu
 * @see OpencpsPersonStatisticLocalService
 * @generated
 */
@ProviderType
public class OpencpsPersonStatisticLocalServiceWrapper
	implements OpencpsPersonStatisticLocalService,
		ServiceWrapper<OpencpsPersonStatisticLocalService> {
	public OpencpsPersonStatisticLocalServiceWrapper(
		OpencpsPersonStatisticLocalService opencpsPersonStatisticLocalService) {
		_opencpsPersonStatisticLocalService = opencpsPersonStatisticLocalService;
	}

	/**
	* Adds the opencps person statistic to the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsPersonStatistic the opencps person statistic
	* @return the opencps person statistic that was added
	*/
	@Override
	public org.opencps.statistic.model.OpencpsPersonStatistic addOpencpsPersonStatistic(
		org.opencps.statistic.model.OpencpsPersonStatistic opencpsPersonStatistic) {
		return _opencpsPersonStatisticLocalService.addOpencpsPersonStatistic(opencpsPersonStatistic);
	}

	@Override
	public org.opencps.statistic.model.OpencpsPersonStatistic checkExsit(
		long groupId, int month, int year, String govAgency, Long employeeId,
		String votingCode) {
		return _opencpsPersonStatisticLocalService.checkExsit(groupId, month,
			year, govAgency, employeeId, votingCode);
	}

	/**
	* Creates a new opencps person statistic with the primary key. Does not add the opencps person statistic to the database.
	*
	* @param personStatisticId the primary key for the new opencps person statistic
	* @return the new opencps person statistic
	*/
	@Override
	public org.opencps.statistic.model.OpencpsPersonStatistic createOpencpsPersonStatistic(
		long personStatisticId) {
		return _opencpsPersonStatisticLocalService.createOpencpsPersonStatistic(personStatisticId);
	}

	/**
	* Deletes the opencps person statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param personStatisticId the primary key of the opencps person statistic
	* @return the opencps person statistic that was removed
	* @throws PortalException if a opencps person statistic with the primary key could not be found
	*/
	@Override
	public org.opencps.statistic.model.OpencpsPersonStatistic deleteOpencpsPersonStatistic(
		long personStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsPersonStatisticLocalService.deleteOpencpsPersonStatistic(personStatisticId);
	}

	/**
	* Deletes the opencps person statistic from the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsPersonStatistic the opencps person statistic
	* @return the opencps person statistic that was removed
	*/
	@Override
	public org.opencps.statistic.model.OpencpsPersonStatistic deleteOpencpsPersonStatistic(
		org.opencps.statistic.model.OpencpsPersonStatistic opencpsPersonStatistic) {
		return _opencpsPersonStatisticLocalService.deleteOpencpsPersonStatistic(opencpsPersonStatistic);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsPersonStatisticLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _opencpsPersonStatisticLocalService.dynamicQuery();
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
		return _opencpsPersonStatisticLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _opencpsPersonStatisticLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _opencpsPersonStatisticLocalService.dynamicQuery(dynamicQuery,
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
		return _opencpsPersonStatisticLocalService.dynamicQueryCount(dynamicQuery);
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
		return _opencpsPersonStatisticLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.statistic.model.OpencpsPersonStatistic fetchOpencpsPersonStatistic(
		long personStatisticId) {
		return _opencpsPersonStatisticLocalService.fetchOpencpsPersonStatistic(personStatisticId);
	}

	/**
	* Returns the opencps person statistic matching the UUID and group.
	*
	* @param uuid the opencps person statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	@Override
	public org.opencps.statistic.model.OpencpsPersonStatistic fetchOpencpsPersonStatisticByUuidAndGroupId(
		String uuid, long groupId) {
		return _opencpsPersonStatisticLocalService.fetchOpencpsPersonStatisticByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsPersonStatistic> fetchPersonStatistic(
		long groupId, int month, int year, String votingCode, Long employeeId,
		String govAgencyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _opencpsPersonStatisticLocalService.fetchPersonStatistic(groupId,
			month, year, votingCode, employeeId, govAgencyCode, start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _opencpsPersonStatisticLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _opencpsPersonStatisticLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _opencpsPersonStatisticLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the opencps person statistic with the primary key.
	*
	* @param personStatisticId the primary key of the opencps person statistic
	* @return the opencps person statistic
	* @throws PortalException if a opencps person statistic with the primary key could not be found
	*/
	@Override
	public org.opencps.statistic.model.OpencpsPersonStatistic getOpencpsPersonStatistic(
		long personStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsPersonStatisticLocalService.getOpencpsPersonStatistic(personStatisticId);
	}

	/**
	* Returns the opencps person statistic matching the UUID and group.
	*
	* @param uuid the opencps person statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps person statistic
	* @throws PortalException if a matching opencps person statistic could not be found
	*/
	@Override
	public org.opencps.statistic.model.OpencpsPersonStatistic getOpencpsPersonStatisticByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsPersonStatisticLocalService.getOpencpsPersonStatisticByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the opencps person statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of opencps person statistics
	*/
	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsPersonStatistic> getOpencpsPersonStatistics(
		int start, int end) {
		return _opencpsPersonStatisticLocalService.getOpencpsPersonStatistics(start,
			end);
	}

	/**
	* Returns all the opencps person statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps person statistics
	* @param companyId the primary key of the company
	* @return the matching opencps person statistics, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsPersonStatistic> getOpencpsPersonStatisticsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _opencpsPersonStatisticLocalService.getOpencpsPersonStatisticsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of opencps person statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps person statistics
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching opencps person statistics, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsPersonStatistic> getOpencpsPersonStatisticsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.statistic.model.OpencpsPersonStatistic> orderByComparator) {
		return _opencpsPersonStatisticLocalService.getOpencpsPersonStatisticsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of opencps person statistics.
	*
	* @return the number of opencps person statistics
	*/
	@Override
	public int getOpencpsPersonStatisticsCount() {
		return _opencpsPersonStatisticLocalService.getOpencpsPersonStatisticsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _opencpsPersonStatisticLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsPersonStatisticLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public void removePersonStatisticByMonthYear(long groupId, int month,
		int year) {
		_opencpsPersonStatisticLocalService.removePersonStatisticByMonthYear(groupId,
			month, year);
	}

	@Override
	public void removePersonStatisticByYear(long companyId, long groupId,
		int month, int year) {
		_opencpsPersonStatisticLocalService.removePersonStatisticByYear(companyId,
			groupId, month, year);
	}

	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsPersonStatistic> searchPersonStatistic(
		long groupId, int month, int year, String votingCode, Long employeeId,
		String govAgencyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _opencpsPersonStatisticLocalService.searchPersonStatistic(groupId,
			month, year, votingCode, employeeId, govAgencyCode, start, end);
	}

	/**
	* Updates the opencps person statistic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param opencpsPersonStatistic the opencps person statistic
	* @return the opencps person statistic that was updated
	*/
	@Override
	public org.opencps.statistic.model.OpencpsPersonStatistic updateOpencpsPersonStatistic(
		org.opencps.statistic.model.OpencpsPersonStatistic opencpsPersonStatistic) {
		return _opencpsPersonStatisticLocalService.updateOpencpsPersonStatistic(opencpsPersonStatistic);
	}

	@Override
	public org.opencps.statistic.model.OpencpsPersonStatistic updatePersonStatistic(
		long personStatisticId, long companyId, long groupId, long userId,
		String userName, int month, int year, String votingSubject,
		int totalVoted, int veryGoodCount, int goodCount, int badCount,
		int percentVeryGood, int percentGood, int percentBad,
		String govAgencyCode, String govAgencyName, long employeeId,
		String votingCode, int totalCount) {
		return _opencpsPersonStatisticLocalService.updatePersonStatistic(personStatisticId,
			companyId, groupId, userId, userName, month, year, votingSubject,
			totalVoted, veryGoodCount, goodCount, badCount, percentVeryGood,
			percentGood, percentBad, govAgencyCode, govAgencyName, employeeId,
			votingCode, totalCount);
	}

	@Override
	public OpencpsPersonStatisticLocalService getWrappedService() {
		return _opencpsPersonStatisticLocalService;
	}

	@Override
	public void setWrappedService(
		OpencpsPersonStatisticLocalService opencpsPersonStatisticLocalService) {
		_opencpsPersonStatisticLocalService = opencpsPersonStatisticLocalService;
	}

	private OpencpsPersonStatisticLocalService _opencpsPersonStatisticLocalService;
}