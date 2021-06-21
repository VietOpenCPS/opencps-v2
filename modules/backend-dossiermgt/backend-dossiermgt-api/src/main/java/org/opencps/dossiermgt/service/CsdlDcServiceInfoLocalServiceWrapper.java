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
 * Provides a wrapper for {@link CsdlDcServiceInfoLocalService}.
 *
 * @author huymq
 * @see CsdlDcServiceInfoLocalService
 * @generated
 */
@ProviderType
public class CsdlDcServiceInfoLocalServiceWrapper
	implements CsdlDcServiceInfoLocalService,
		ServiceWrapper<CsdlDcServiceInfoLocalService> {
	public CsdlDcServiceInfoLocalServiceWrapper(
		CsdlDcServiceInfoLocalService csdlDcServiceInfoLocalService) {
		_csdlDcServiceInfoLocalService = csdlDcServiceInfoLocalService;
	}

	/**
	* Adds the csdl dc service info to the database. Also notifies the appropriate model listeners.
	*
	* @param csdlDcServiceInfo the csdl dc service info
	* @return the csdl dc service info that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcServiceInfo addCsdlDcServiceInfo(
		org.opencps.dossiermgt.model.CsdlDcServiceInfo csdlDcServiceInfo) {
		return _csdlDcServiceInfoLocalService.addCsdlDcServiceInfo(csdlDcServiceInfo);
	}

	/**
	* Creates a new csdl dc service info with the primary key. Does not add the csdl dc service info to the database.
	*
	* @param idDcService the primary key for the new csdl dc service info
	* @return the new csdl dc service info
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcServiceInfo createCsdlDcServiceInfo(
		long idDcService) {
		return _csdlDcServiceInfoLocalService.createCsdlDcServiceInfo(idDcService);
	}

	/**
	* Deletes the csdl dc service info from the database. Also notifies the appropriate model listeners.
	*
	* @param csdlDcServiceInfo the csdl dc service info
	* @return the csdl dc service info that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcServiceInfo deleteCsdlDcServiceInfo(
		org.opencps.dossiermgt.model.CsdlDcServiceInfo csdlDcServiceInfo) {
		return _csdlDcServiceInfoLocalService.deleteCsdlDcServiceInfo(csdlDcServiceInfo);
	}

	/**
	* Deletes the csdl dc service info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param idDcService the primary key of the csdl dc service info
	* @return the csdl dc service info that was removed
	* @throws PortalException if a csdl dc service info with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcServiceInfo deleteCsdlDcServiceInfo(
		long idDcService)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _csdlDcServiceInfoLocalService.deleteCsdlDcServiceInfo(idDcService);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _csdlDcServiceInfoLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _csdlDcServiceInfoLocalService.dynamicQuery();
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
		return _csdlDcServiceInfoLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _csdlDcServiceInfoLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _csdlDcServiceInfoLocalService.dynamicQuery(dynamicQuery, start,
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
		return _csdlDcServiceInfoLocalService.dynamicQueryCount(dynamicQuery);
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
		return _csdlDcServiceInfoLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.CsdlDcServiceInfo fetchCsdlDcServiceInfo(
		long idDcService) {
		return _csdlDcServiceInfoLocalService.fetchCsdlDcServiceInfo(idDcService);
	}

	/**
	* Returns the csdl dc service info matching the UUID and group.
	*
	* @param uuid the csdl dc service info's UUID
	* @param groupId the primary key of the group
	* @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcServiceInfo fetchCsdlDcServiceInfoByUuidAndGroupId(
		String uuid, long groupId) {
		return _csdlDcServiceInfoLocalService.fetchCsdlDcServiceInfoByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public org.opencps.dossiermgt.model.CsdlDcServiceInfo findByServiceCodeAndStatus(
		String serviceCode, int status) {
		return _csdlDcServiceInfoLocalService.findByServiceCodeAndStatus(serviceCode,
			status);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _csdlDcServiceInfoLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the csdl dc service info with the primary key.
	*
	* @param idDcService the primary key of the csdl dc service info
	* @return the csdl dc service info
	* @throws PortalException if a csdl dc service info with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcServiceInfo getCsdlDcServiceInfo(
		long idDcService)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _csdlDcServiceInfoLocalService.getCsdlDcServiceInfo(idDcService);
	}

	/**
	* Returns the csdl dc service info matching the UUID and group.
	*
	* @param uuid the csdl dc service info's UUID
	* @param groupId the primary key of the group
	* @return the matching csdl dc service info
	* @throws PortalException if a matching csdl dc service info could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcServiceInfo getCsdlDcServiceInfoByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _csdlDcServiceInfoLocalService.getCsdlDcServiceInfoByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the csdl dc service infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @return the range of csdl dc service infos
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.CsdlDcServiceInfo> getCsdlDcServiceInfos(
		int start, int end) {
		return _csdlDcServiceInfoLocalService.getCsdlDcServiceInfos(start, end);
	}

	/**
	* Returns all the csdl dc service infos matching the UUID and company.
	*
	* @param uuid the UUID of the csdl dc service infos
	* @param companyId the primary key of the company
	* @return the matching csdl dc service infos, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.CsdlDcServiceInfo> getCsdlDcServiceInfosByUuidAndCompanyId(
		String uuid, long companyId) {
		return _csdlDcServiceInfoLocalService.getCsdlDcServiceInfosByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of csdl dc service infos matching the UUID and company.
	*
	* @param uuid the UUID of the csdl dc service infos
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching csdl dc service infos, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.CsdlDcServiceInfo> getCsdlDcServiceInfosByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.CsdlDcServiceInfo> orderByComparator) {
		return _csdlDcServiceInfoLocalService.getCsdlDcServiceInfosByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of csdl dc service infos.
	*
	* @return the number of csdl dc service infos
	*/
	@Override
	public int getCsdlDcServiceInfosCount() {
		return _csdlDcServiceInfoLocalService.getCsdlDcServiceInfosCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _csdlDcServiceInfoLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _csdlDcServiceInfoLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _csdlDcServiceInfoLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the csdl dc service info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param csdlDcServiceInfo the csdl dc service info
	* @return the csdl dc service info that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcServiceInfo updateCsdlDcServiceInfo(
		org.opencps.dossiermgt.model.CsdlDcServiceInfo csdlDcServiceInfo) {
		return _csdlDcServiceInfoLocalService.updateCsdlDcServiceInfo(csdlDcServiceInfo);
	}

	@Override
	public CsdlDcServiceInfoLocalService getWrappedService() {
		return _csdlDcServiceInfoLocalService;
	}

	@Override
	public void setWrappedService(
		CsdlDcServiceInfoLocalService csdlDcServiceInfoLocalService) {
		_csdlDcServiceInfoLocalService = csdlDcServiceInfoLocalService;
	}

	private CsdlDcServiceInfoLocalService _csdlDcServiceInfoLocalService;
}