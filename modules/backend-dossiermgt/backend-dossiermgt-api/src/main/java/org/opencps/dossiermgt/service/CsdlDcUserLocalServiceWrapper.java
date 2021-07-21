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
 * Provides a wrapper for {@link CsdlDcUserLocalService}.
 *
 * @author huymq
 * @see CsdlDcUserLocalService
 * @generated
 */
@ProviderType
public class CsdlDcUserLocalServiceWrapper implements CsdlDcUserLocalService,
	ServiceWrapper<CsdlDcUserLocalService> {
	public CsdlDcUserLocalServiceWrapper(
		CsdlDcUserLocalService csdlDcUserLocalService) {
		_csdlDcUserLocalService = csdlDcUserLocalService;
	}

	/**
	* Adds the csdl dc user to the database. Also notifies the appropriate model listeners.
	*
	* @param csdlDcUser the csdl dc user
	* @return the csdl dc user that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcUser addCsdlDcUser(
		org.opencps.dossiermgt.model.CsdlDcUser csdlDcUser) {
		return _csdlDcUserLocalService.addCsdlDcUser(csdlDcUser);
	}

	/**
	* Creates a new csdl dc user with the primary key. Does not add the csdl dc user to the database.
	*
	* @param idDcUser the primary key for the new csdl dc user
	* @return the new csdl dc user
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcUser createCsdlDcUser(
		long idDcUser) {
		return _csdlDcUserLocalService.createCsdlDcUser(idDcUser);
	}

	/**
	* Deletes the csdl dc user from the database. Also notifies the appropriate model listeners.
	*
	* @param csdlDcUser the csdl dc user
	* @return the csdl dc user that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcUser deleteCsdlDcUser(
		org.opencps.dossiermgt.model.CsdlDcUser csdlDcUser) {
		return _csdlDcUserLocalService.deleteCsdlDcUser(csdlDcUser);
	}

	/**
	* Deletes the csdl dc user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param idDcUser the primary key of the csdl dc user
	* @return the csdl dc user that was removed
	* @throws PortalException if a csdl dc user with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcUser deleteCsdlDcUser(
		long idDcUser)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _csdlDcUserLocalService.deleteCsdlDcUser(idDcUser);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _csdlDcUserLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _csdlDcUserLocalService.dynamicQuery();
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
		return _csdlDcUserLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _csdlDcUserLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _csdlDcUserLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _csdlDcUserLocalService.dynamicQueryCount(dynamicQuery);
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
		return _csdlDcUserLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.CsdlDcUser fetchCsdlDcUser(
		long idDcUser) {
		return _csdlDcUserLocalService.fetchCsdlDcUser(idDcUser);
	}

	/**
	* Returns the csdl dc user matching the UUID and group.
	*
	* @param uuid the csdl dc user's UUID
	* @param groupId the primary key of the group
	* @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcUser fetchCsdlDcUserByUuidAndGroupId(
		String uuid, long groupId) {
		return _csdlDcUserLocalService.fetchCsdlDcUserByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public org.opencps.dossiermgt.model.CsdlDcUser findByGovAndEmailAndStatus(
		String govAgencyCode, String email, int status) {
		return _csdlDcUserLocalService.findByGovAndEmailAndStatus(govAgencyCode,
			email, status);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _csdlDcUserLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the csdl dc user with the primary key.
	*
	* @param idDcUser the primary key of the csdl dc user
	* @return the csdl dc user
	* @throws PortalException if a csdl dc user with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcUser getCsdlDcUser(long idDcUser)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _csdlDcUserLocalService.getCsdlDcUser(idDcUser);
	}

	/**
	* Returns the csdl dc user matching the UUID and group.
	*
	* @param uuid the csdl dc user's UUID
	* @param groupId the primary key of the group
	* @return the matching csdl dc user
	* @throws PortalException if a matching csdl dc user could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcUser getCsdlDcUserByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _csdlDcUserLocalService.getCsdlDcUserByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the csdl dc users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of csdl dc users
	* @param end the upper bound of the range of csdl dc users (not inclusive)
	* @return the range of csdl dc users
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.CsdlDcUser> getCsdlDcUsers(
		int start, int end) {
		return _csdlDcUserLocalService.getCsdlDcUsers(start, end);
	}

	/**
	* Returns all the csdl dc users matching the UUID and company.
	*
	* @param uuid the UUID of the csdl dc users
	* @param companyId the primary key of the company
	* @return the matching csdl dc users, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.CsdlDcUser> getCsdlDcUsersByUuidAndCompanyId(
		String uuid, long companyId) {
		return _csdlDcUserLocalService.getCsdlDcUsersByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of csdl dc users matching the UUID and company.
	*
	* @param uuid the UUID of the csdl dc users
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of csdl dc users
	* @param end the upper bound of the range of csdl dc users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching csdl dc users, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.CsdlDcUser> getCsdlDcUsersByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.CsdlDcUser> orderByComparator) {
		return _csdlDcUserLocalService.getCsdlDcUsersByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of csdl dc users.
	*
	* @return the number of csdl dc users
	*/
	@Override
	public int getCsdlDcUsersCount() {
		return _csdlDcUserLocalService.getCsdlDcUsersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _csdlDcUserLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _csdlDcUserLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _csdlDcUserLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the csdl dc user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param csdlDcUser the csdl dc user
	* @return the csdl dc user that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.CsdlDcUser updateCsdlDcUser(
		org.opencps.dossiermgt.model.CsdlDcUser csdlDcUser) {
		return _csdlDcUserLocalService.updateCsdlDcUser(csdlDcUser);
	}

	@Override
	public CsdlDcUserLocalService getWrappedService() {
		return _csdlDcUserLocalService;
	}

	@Override
	public void setWrappedService(CsdlDcUserLocalService csdlDcUserLocalService) {
		_csdlDcUserLocalService = csdlDcUserLocalService;
	}

	private CsdlDcUserLocalService _csdlDcUserLocalService;
}