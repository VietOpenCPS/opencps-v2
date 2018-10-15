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
 * Provides a wrapper for {@link DossierUserLocalService}.
 *
 * @author huymq
 * @see DossierUserLocalService
 * @generated
 */
@ProviderType
public class DossierUserLocalServiceWrapper implements DossierUserLocalService,
	ServiceWrapper<DossierUserLocalService> {
	public DossierUserLocalServiceWrapper(
		DossierUserLocalService dossierUserLocalService) {
		_dossierUserLocalService = dossierUserLocalService;
	}

	/**
	* Adds the dossier user to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierUser the dossier user
	* @return the dossier user that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierUser addDossierUser(
		org.opencps.dossiermgt.model.DossierUser dossierUser) {
		return _dossierUserLocalService.addDossierUser(dossierUser);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierUser addDossierUser(
		long groupId, long dossierId, long userId, int moderator,
		boolean visited) {
		return _dossierUserLocalService.addDossierUser(groupId, dossierId,
			userId, moderator, visited);
	}

	/**
	* Creates a new dossier user with the primary key. Does not add the dossier user to the database.
	*
	* @param dossierUserPK the primary key for the new dossier user
	* @return the new dossier user
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierUser createDossierUser(
		org.opencps.dossiermgt.service.persistence.DossierUserPK dossierUserPK) {
		return _dossierUserLocalService.createDossierUser(dossierUserPK);
	}

	/**
	* Deletes the dossier user from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierUser the dossier user
	* @return the dossier user that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierUser deleteDossierUser(
		org.opencps.dossiermgt.model.DossierUser dossierUser) {
		return _dossierUserLocalService.deleteDossierUser(dossierUser);
	}

	/**
	* Deletes the dossier user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierUserPK the primary key of the dossier user
	* @return the dossier user that was removed
	* @throws PortalException if a dossier user with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierUser deleteDossierUser(
		org.opencps.dossiermgt.service.persistence.DossierUserPK dossierUserPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierUserLocalService.deleteDossierUser(dossierUserPK);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierUser deleteDossierUser(
		long dossierId, long userId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return _dossierUserLocalService.deleteDossierUser(dossierId, userId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierUserLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierUserLocalService.dynamicQuery();
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
		return _dossierUserLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierUserLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierUserLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _dossierUserLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierUserLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierUser fetchDossierUser(
		org.opencps.dossiermgt.service.persistence.DossierUserPK dossierUserPK) {
		return _dossierUserLocalService.fetchDossierUser(dossierUserPK);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierUser> findByDID(
		long dossierId) {
		return _dossierUserLocalService.findByDID(dossierId);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierUser findByDID_UD(
		long dossierId, long userId) {
		return _dossierUserLocalService.findByDID_UD(dossierId, userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierUserLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.dossiermgt.model.DossierUser getByDossierUser(
		long dossierId, long userId) {
		return _dossierUserLocalService.getByDossierUser(dossierId, userId);
	}

	/**
	* Returns the dossier user with the primary key.
	*
	* @param dossierUserPK the primary key of the dossier user
	* @return the dossier user
	* @throws PortalException if a dossier user with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierUser getDossierUser(
		org.opencps.dossiermgt.service.persistence.DossierUserPK dossierUserPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierUserLocalService.getDossierUser(dossierUserPK);
	}

	/**
	* Returns a range of all the dossier users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @return the range of dossier users
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierUser> getDossierUsers(
		int start, int end) {
		return _dossierUserLocalService.getDossierUsers(start, end);
	}

	/**
	* Returns the number of dossier users.
	*
	* @return the number of dossier users
	*/
	@Override
	public int getDossierUsersCount() {
		return _dossierUserLocalService.getDossierUsersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierUserLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dossierUserLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierUserLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the dossier user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierUser the dossier user
	* @return the dossier user that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierUser updateDossierUser(
		org.opencps.dossiermgt.model.DossierUser dossierUser) {
		return _dossierUserLocalService.updateDossierUser(dossierUser);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierUser updateDossierUser(
		long dossierId, long userId, int moderator, boolean visited)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return _dossierUserLocalService.updateDossierUser(dossierId, userId,
			moderator, visited);
	}

	@Override
	public DossierUserLocalService getWrappedService() {
		return _dossierUserLocalService;
	}

	@Override
	public void setWrappedService(
		DossierUserLocalService dossierUserLocalService) {
		_dossierUserLocalService = dossierUserLocalService;
	}

	private DossierUserLocalService _dossierUserLocalService;
}