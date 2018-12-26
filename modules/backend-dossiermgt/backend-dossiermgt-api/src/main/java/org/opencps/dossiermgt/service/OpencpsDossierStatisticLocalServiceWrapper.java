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
 * Provides a wrapper for {@link OpencpsDossierStatisticLocalService}.
 *
 * @author huymq
 * @see OpencpsDossierStatisticLocalService
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticLocalServiceWrapper
	implements OpencpsDossierStatisticLocalService,
		ServiceWrapper<OpencpsDossierStatisticLocalService> {
	public OpencpsDossierStatisticLocalServiceWrapper(
		OpencpsDossierStatisticLocalService opencpsDossierStatisticLocalService) {
		_opencpsDossierStatisticLocalService = opencpsDossierStatisticLocalService;
	}

	/**
	* Adds the opencps dossier statistic to the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatistic the opencps dossier statistic
	* @return the opencps dossier statistic that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.OpencpsDossierStatistic addOpencpsDossierStatistic(
		org.opencps.dossiermgt.model.OpencpsDossierStatistic opencpsDossierStatistic) {
		return _opencpsDossierStatisticLocalService.addOpencpsDossierStatistic(opencpsDossierStatistic);
	}

	/**
	* Creates a new opencps dossier statistic with the primary key. Does not add the opencps dossier statistic to the database.
	*
	* @param dossierStatisticId the primary key for the new opencps dossier statistic
	* @return the new opencps dossier statistic
	*/
	@Override
	public org.opencps.dossiermgt.model.OpencpsDossierStatistic createOpencpsDossierStatistic(
		long dossierStatisticId) {
		return _opencpsDossierStatisticLocalService.createOpencpsDossierStatistic(dossierStatisticId);
	}

	/**
	* Deletes the opencps dossier statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic that was removed
	* @throws PortalException if a opencps dossier statistic with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.OpencpsDossierStatistic deleteOpencpsDossierStatistic(
		long dossierStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsDossierStatisticLocalService.deleteOpencpsDossierStatistic(dossierStatisticId);
	}

	/**
	* Deletes the opencps dossier statistic from the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatistic the opencps dossier statistic
	* @return the opencps dossier statistic that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.OpencpsDossierStatistic deleteOpencpsDossierStatistic(
		org.opencps.dossiermgt.model.OpencpsDossierStatistic opencpsDossierStatistic) {
		return _opencpsDossierStatisticLocalService.deleteOpencpsDossierStatistic(opencpsDossierStatistic);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsDossierStatisticLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _opencpsDossierStatisticLocalService.dynamicQuery();
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
		return _opencpsDossierStatisticLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _opencpsDossierStatisticLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _opencpsDossierStatisticLocalService.dynamicQuery(dynamicQuery,
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
		return _opencpsDossierStatisticLocalService.dynamicQueryCount(dynamicQuery);
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
		return _opencpsDossierStatisticLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.OpencpsDossierStatistic fetchByG_M_Y_G_D(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode) {
		return _opencpsDossierStatisticLocalService.fetchByG_M_Y_G_D(groupId,
			month, year, govAgencyCode, domainCode);
	}

	@Override
	public org.opencps.dossiermgt.model.OpencpsDossierStatistic fetchOpencpsDossierStatistic(
		long dossierStatisticId) {
		return _opencpsDossierStatisticLocalService.fetchOpencpsDossierStatistic(dossierStatisticId);
	}

	/**
	* Returns the opencps dossier statistic matching the UUID and group.
	*
	* @param uuid the opencps dossier statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.OpencpsDossierStatistic fetchOpencpsDossierStatisticByUuidAndGroupId(
		String uuid, long groupId) {
		return _opencpsDossierStatisticLocalService.fetchOpencpsDossierStatisticByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _opencpsDossierStatisticLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _opencpsDossierStatisticLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _opencpsDossierStatisticLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the opencps dossier statistic with the primary key.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic
	* @throws PortalException if a opencps dossier statistic with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.OpencpsDossierStatistic getOpencpsDossierStatistic(
		long dossierStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsDossierStatisticLocalService.getOpencpsDossierStatistic(dossierStatisticId);
	}

	/**
	* Returns the opencps dossier statistic matching the UUID and group.
	*
	* @param uuid the opencps dossier statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier statistic
	* @throws PortalException if a matching opencps dossier statistic could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.OpencpsDossierStatistic getOpencpsDossierStatisticByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsDossierStatisticLocalService.getOpencpsDossierStatisticByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the opencps dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of opencps dossier statistics
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.OpencpsDossierStatistic> getOpencpsDossierStatistics(
		int start, int end) {
		return _opencpsDossierStatisticLocalService.getOpencpsDossierStatistics(start,
			end);
	}

	/**
	* Returns all the opencps dossier statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps dossier statistics
	* @param companyId the primary key of the company
	* @return the matching opencps dossier statistics, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.OpencpsDossierStatistic> getOpencpsDossierStatisticsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _opencpsDossierStatisticLocalService.getOpencpsDossierStatisticsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of opencps dossier statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps dossier statistics
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching opencps dossier statistics, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.OpencpsDossierStatistic> getOpencpsDossierStatisticsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.OpencpsDossierStatistic> orderByComparator) {
		return _opencpsDossierStatisticLocalService.getOpencpsDossierStatisticsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of opencps dossier statistics.
	*
	* @return the number of opencps dossier statistics
	*/
	@Override
	public int getOpencpsDossierStatisticsCount() {
		return _opencpsDossierStatisticLocalService.getOpencpsDossierStatisticsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _opencpsDossierStatisticLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsDossierStatisticLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the opencps dossier statistic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatistic the opencps dossier statistic
	* @return the opencps dossier statistic that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.OpencpsDossierStatistic updateOpencpsDossierStatistic(
		org.opencps.dossiermgt.model.OpencpsDossierStatistic opencpsDossierStatistic) {
		return _opencpsDossierStatisticLocalService.updateOpencpsDossierStatistic(opencpsDossierStatistic);
	}

	@Override
	public OpencpsDossierStatisticLocalService getWrappedService() {
		return _opencpsDossierStatisticLocalService;
	}

	@Override
	public void setWrappedService(
		OpencpsDossierStatisticLocalService opencpsDossierStatisticLocalService) {
		_opencpsDossierStatisticLocalService = opencpsDossierStatisticLocalService;
	}

	private OpencpsDossierStatisticLocalService _opencpsDossierStatisticLocalService;
}