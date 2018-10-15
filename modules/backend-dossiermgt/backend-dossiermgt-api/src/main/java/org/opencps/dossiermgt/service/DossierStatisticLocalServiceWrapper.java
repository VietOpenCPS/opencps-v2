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
 * Provides a wrapper for {@link DossierStatisticLocalService}.
 *
 * @author huymq
 * @see DossierStatisticLocalService
 * @generated
 */
@ProviderType
public class DossierStatisticLocalServiceWrapper
	implements DossierStatisticLocalService,
		ServiceWrapper<DossierStatisticLocalService> {
	public DossierStatisticLocalServiceWrapper(
		DossierStatisticLocalService dossierStatisticLocalService) {
		_dossierStatisticLocalService = dossierStatisticLocalService;
	}

	/**
	* Adds the dossier statistic to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatistic the dossier statistic
	* @return the dossier statistic that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatistic addDossierStatistic(
		org.opencps.dossiermgt.model.DossierStatistic dossierStatistic) {
		return _dossierStatisticLocalService.addDossierStatistic(dossierStatistic);
	}

	@Override
	public long countLucene(java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dossierStatisticLocalService.countLucene(params, searchContext);
	}

	/**
	* Creates a new dossier statistic with the primary key. Does not add the dossier statistic to the database.
	*
	* @param dossierStatisticId the primary key for the new dossier statistic
	* @return the new dossier statistic
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatistic createDossierStatistic(
		long dossierStatisticId) {
		return _dossierStatisticLocalService.createDossierStatistic(dossierStatisticId);
	}

	/**
	* Deletes the dossier statistic from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatistic the dossier statistic
	* @return the dossier statistic that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatistic deleteDossierStatistic(
		org.opencps.dossiermgt.model.DossierStatistic dossierStatistic) {
		return _dossierStatisticLocalService.deleteDossierStatistic(dossierStatistic);
	}

	/**
	* Deletes the dossier statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatisticId the primary key of the dossier statistic
	* @return the dossier statistic that was removed
	* @throws PortalException if a dossier statistic with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatistic deleteDossierStatistic(
		long dossierStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierStatisticLocalService.deleteDossierStatistic(dossierStatisticId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierStatisticLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierStatisticLocalService.dynamicQuery();
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
		return _dossierStatisticLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierStatisticLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierStatisticLocalService.dynamicQuery(dynamicQuery, start,
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
		return _dossierStatisticLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierStatisticLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierStatistic fetchDossierStatistic(
		long dossierStatisticId) {
		return _dossierStatisticLocalService.fetchDossierStatistic(dossierStatisticId);
	}

	/**
	* Returns the dossier statistic matching the UUID and group.
	*
	* @param uuid the dossier statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatistic fetchDossierStatisticByUuidAndGroupId(
		String uuid, long groupId) {
		return _dossierStatisticLocalService.fetchDossierStatisticByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierStatisticLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the dossier statistic with the primary key.
	*
	* @param dossierStatisticId the primary key of the dossier statistic
	* @return the dossier statistic
	* @throws PortalException if a dossier statistic with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatistic getDossierStatistic(
		long dossierStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierStatisticLocalService.getDossierStatistic(dossierStatisticId);
	}

	/**
	* Returns the dossier statistic matching the UUID and group.
	*
	* @param uuid the dossier statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier statistic
	* @throws PortalException if a matching dossier statistic could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatistic getDossierStatisticByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierStatisticLocalService.getDossierStatisticByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierStatistic> getDossierStatisticbyYear(
		long groupId, long userId, int year) {
		return _dossierStatisticLocalService.getDossierStatisticbyYear(groupId,
			userId, year);
	}

	/**
	* Returns a range of all the dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @return the range of dossier statistics
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierStatistic> getDossierStatistics(
		int start, int end) {
		return _dossierStatisticLocalService.getDossierStatistics(start, end);
	}

	/**
	* Returns all the dossier statistics matching the UUID and company.
	*
	* @param uuid the UUID of the dossier statistics
	* @param companyId the primary key of the company
	* @return the matching dossier statistics, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierStatistic> getDossierStatisticsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _dossierStatisticLocalService.getDossierStatisticsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dossier statistics matching the UUID and company.
	*
	* @param uuid the UUID of the dossier statistics
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier statistics, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierStatistic> getDossierStatisticsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.DossierStatistic> orderByComparator) {
		return _dossierStatisticLocalService.getDossierStatisticsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of dossier statistics.
	*
	* @return the number of dossier statistics
	*/
	@Override
	public int getDossierStatisticsCount() {
		return _dossierStatisticLocalService.getDossierStatisticsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dossierStatisticLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierStatisticLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dossierStatisticLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierStatisticLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierStatistic insert(long groupId,
		int month, int year, int remainingCount, int receivedCount,
		int onlineCount, int undueCount, int overdueCount, int ontimeCount,
		int overtimeCount, String govAgencyCode, String govAgencyName,
		String domainCode, String domainName, int administrationLevel,
		boolean reporting,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierStatisticLocalService.insert(groupId, month, year,
			remainingCount, receivedCount, onlineCount, undueCount,
			overdueCount, ontimeCount, overtimeCount, govAgencyCode,
			govAgencyName, domainCode, domainName, administrationLevel,
			reporting, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dossierStatisticLocalService.searchLucene(params, sorts, start,
			end, searchContext);
	}

	/**
	* Updates the dossier statistic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierStatistic the dossier statistic
	* @return the dossier statistic that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatistic updateDossierStatistic(
		org.opencps.dossiermgt.model.DossierStatistic dossierStatistic) {
		return _dossierStatisticLocalService.updateDossierStatistic(dossierStatistic);
	}

	@Override
	public DossierStatisticLocalService getWrappedService() {
		return _dossierStatisticLocalService;
	}

	@Override
	public void setWrappedService(
		DossierStatisticLocalService dossierStatisticLocalService) {
		_dossierStatisticLocalService = dossierStatisticLocalService;
	}

	private DossierStatisticLocalService _dossierStatisticLocalService;
}