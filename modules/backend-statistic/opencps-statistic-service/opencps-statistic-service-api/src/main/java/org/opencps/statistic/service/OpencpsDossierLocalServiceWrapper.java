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
 * Provides a wrapper for {@link OpencpsDossierLocalService}.
 *
 * @author khoavu
 * @see OpencpsDossierLocalService
 * @generated
 */
@ProviderType
public class OpencpsDossierLocalServiceWrapper
	implements OpencpsDossierLocalService,
		ServiceWrapper<OpencpsDossierLocalService> {
	public OpencpsDossierLocalServiceWrapper(
		OpencpsDossierLocalService opencpsDossierLocalService) {
		_opencpsDossierLocalService = opencpsDossierLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _opencpsDossierLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _opencpsDossierLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _opencpsDossierLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _opencpsDossierLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsDossierLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsDossierLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of opencps dossiers.
	*
	* @return the number of opencps dossiers
	*/
	@Override
	public int getOpencpsDossiersCount() {
		return _opencpsDossierLocalService.getOpencpsDossiersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _opencpsDossierLocalService.getOSGiServiceIdentifier();
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
		return _opencpsDossierLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _opencpsDossierLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _opencpsDossierLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the opencps dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @return the range of opencps dossiers
	*/
	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsDossier> getOpencpsDossiers(
		int start, int end) {
		return _opencpsDossierLocalService.getOpencpsDossiers(start, end);
	}

	/**
	* Returns all the opencps dossiers matching the UUID and company.
	*
	* @param uuid the UUID of the opencps dossiers
	* @param companyId the primary key of the company
	* @return the matching opencps dossiers, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsDossier> getOpencpsDossiersByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _opencpsDossierLocalService.getOpencpsDossiersByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of opencps dossiers matching the UUID and company.
	*
	* @param uuid the UUID of the opencps dossiers
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching opencps dossiers, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsDossier> getOpencpsDossiersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.statistic.model.OpencpsDossier> orderByComparator) {
		return _opencpsDossierLocalService.getOpencpsDossiersByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<org.opencps.statistic.model.OpencpsDossier> searchDossier(
		long groupId, java.lang.String keyword,
		java.lang.String registerBookCode, java.lang.String processNo,
		java.lang.String serviceCode, java.lang.String govAgencyCode,
		java.lang.String applicantIdType, java.lang.String applicantIdNo,
		java.lang.String cityCode, java.lang.String districtCode,
		java.lang.String wardCode, java.lang.String contactTelNo,
		java.lang.String contactEmail, java.lang.String delegateIdNo,
		java.lang.String delegateTelNo, java.lang.String dossierStatus,
		java.lang.String dossierSubStatus, long dossierActionId, int viaPostal,
		boolean online, boolean onlineValue, int originality,
		java.lang.String serverNo, long originDossierId, boolean isReceiveDate,
		boolean isReleaseDate, boolean isBetimes, boolean isOntime,
		boolean isUndue, boolean order, java.lang.String orderBy, int start,
		int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _opencpsDossierLocalService.searchDossier(groupId, keyword,
			registerBookCode, processNo, serviceCode, govAgencyCode,
			applicantIdType, applicantIdNo, cityCode, districtCode, wardCode,
			contactTelNo, contactEmail, delegateIdNo, delegateTelNo,
			dossierStatus, dossierSubStatus, dossierActionId, viaPostal,
			online, onlineValue, originality, serverNo, originDossierId,
			isReceiveDate, isReleaseDate, isBetimes, isOntime, isUndue, order,
			orderBy, start, end);
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
		return _opencpsDossierLocalService.dynamicQueryCount(dynamicQuery);
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
		return _opencpsDossierLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Adds the opencps dossier to the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossier the opencps dossier
	* @return the opencps dossier that was added
	*/
	@Override
	public org.opencps.statistic.model.OpencpsDossier addOpencpsDossier(
		org.opencps.statistic.model.OpencpsDossier opencpsDossier) {
		return _opencpsDossierLocalService.addOpencpsDossier(opencpsDossier);
	}

	/**
	* Creates a new opencps dossier with the primary key. Does not add the opencps dossier to the database.
	*
	* @param dossierId the primary key for the new opencps dossier
	* @return the new opencps dossier
	*/
	@Override
	public org.opencps.statistic.model.OpencpsDossier createOpencpsDossier(
		long dossierId) {
		return _opencpsDossierLocalService.createOpencpsDossier(dossierId);
	}

	/**
	* Deletes the opencps dossier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierId the primary key of the opencps dossier
	* @return the opencps dossier that was removed
	* @throws PortalException if a opencps dossier with the primary key could not be found
	*/
	@Override
	public org.opencps.statistic.model.OpencpsDossier deleteOpencpsDossier(
		long dossierId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsDossierLocalService.deleteOpencpsDossier(dossierId);
	}

	/**
	* Deletes the opencps dossier from the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossier the opencps dossier
	* @return the opencps dossier that was removed
	*/
	@Override
	public org.opencps.statistic.model.OpencpsDossier deleteOpencpsDossier(
		org.opencps.statistic.model.OpencpsDossier opencpsDossier) {
		return _opencpsDossierLocalService.deleteOpencpsDossier(opencpsDossier);
	}

	@Override
	public org.opencps.statistic.model.OpencpsDossier fetchOpencpsDossier(
		long dossierId) {
		return _opencpsDossierLocalService.fetchOpencpsDossier(dossierId);
	}

	/**
	* Returns the opencps dossier matching the UUID and group.
	*
	* @param uuid the opencps dossier's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	@Override
	public org.opencps.statistic.model.OpencpsDossier fetchOpencpsDossierByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _opencpsDossierLocalService.fetchOpencpsDossierByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the opencps dossier with the primary key.
	*
	* @param dossierId the primary key of the opencps dossier
	* @return the opencps dossier
	* @throws PortalException if a opencps dossier with the primary key could not be found
	*/
	@Override
	public org.opencps.statistic.model.OpencpsDossier getOpencpsDossier(
		long dossierId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsDossierLocalService.getOpencpsDossier(dossierId);
	}

	/**
	* Returns the opencps dossier matching the UUID and group.
	*
	* @param uuid the opencps dossier's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier
	* @throws PortalException if a matching opencps dossier could not be found
	*/
	@Override
	public org.opencps.statistic.model.OpencpsDossier getOpencpsDossierByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _opencpsDossierLocalService.getOpencpsDossierByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the opencps dossier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossier the opencps dossier
	* @return the opencps dossier that was updated
	*/
	@Override
	public org.opencps.statistic.model.OpencpsDossier updateOpencpsDossier(
		org.opencps.statistic.model.OpencpsDossier opencpsDossier) {
		return _opencpsDossierLocalService.updateOpencpsDossier(opencpsDossier);
	}

	@Override
	public OpencpsDossierLocalService getWrappedService() {
		return _opencpsDossierLocalService;
	}

	@Override
	public void setWrappedService(
		OpencpsDossierLocalService opencpsDossierLocalService) {
		_opencpsDossierLocalService = opencpsDossierLocalService;
	}

	private OpencpsDossierLocalService _opencpsDossierLocalService;
}