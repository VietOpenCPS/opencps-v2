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
 * Provides a wrapper for {@link DossierActionUserLocalService}.
 *
 * @author huymq
 * @see DossierActionUserLocalService
 * @generated
 */
@ProviderType
public class DossierActionUserLocalServiceWrapper
	implements DossierActionUserLocalService,
		ServiceWrapper<DossierActionUserLocalService> {
	public DossierActionUserLocalServiceWrapper(
		DossierActionUserLocalService dossierActionUserLocalService) {
		_dossierActionUserLocalService = dossierActionUserLocalService;
	}

	/**
	* Adds the dossier action user to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierActionUser the dossier action user
	* @return the dossier action user that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierActionUser addDossierActionUser(
		org.opencps.dossiermgt.model.DossierActionUser dossierActionUser) {
		return _dossierActionUserLocalService.addDossierActionUser(dossierActionUser);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierActionUser addDossierActionUser(
		long userId, long groupId, long dossierActionId, long dossierId,
		String stepCode, int moderator, int assigned, boolean visited)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierActionUserLocalService.addDossierActionUser(userId,
			groupId, dossierActionId, dossierId, stepCode, moderator, assigned,
			visited);
	}

	/**
	* Creates a new dossier action user with the primary key. Does not add the dossier action user to the database.
	*
	* @param dossierActionUserPK the primary key for the new dossier action user
	* @return the new dossier action user
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierActionUser createDossierActionUser(
		org.opencps.dossiermgt.service.persistence.DossierActionUserPK dossierActionUserPK) {
		return _dossierActionUserLocalService.createDossierActionUser(dossierActionUserPK);
	}

	@Override
	public void deleteByDossierAction(long dossierActionId) {
		_dossierActionUserLocalService.deleteByDossierAction(dossierActionId);
	}

	@Override
	public void deleteByDossierAndStepCode(long dossierId, String stepCode) {
		_dossierActionUserLocalService.deleteByDossierAndStepCode(dossierId,
			stepCode);
	}

	/**
	* Deletes the dossier action user from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierActionUser the dossier action user
	* @return the dossier action user that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierActionUser deleteDossierActionUser(
		org.opencps.dossiermgt.model.DossierActionUser dossierActionUser) {
		return _dossierActionUserLocalService.deleteDossierActionUser(dossierActionUser);
	}

	/**
	* Deletes the dossier action user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierActionUserPK the primary key of the dossier action user
	* @return the dossier action user that was removed
	* @throws PortalException if a dossier action user with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierActionUser deleteDossierActionUser(
		org.opencps.dossiermgt.service.persistence.DossierActionUserPK dossierActionUserPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierActionUserLocalService.deleteDossierActionUser(dossierActionUserPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierActionUserLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierActionUserLocalService.dynamicQuery();
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
		return _dossierActionUserLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierActionUserLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierActionUserLocalService.dynamicQuery(dynamicQuery, start,
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
		return _dossierActionUserLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierActionUserLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierActionUser fetchDossierActionUser(
		org.opencps.dossiermgt.service.persistence.DossierActionUserPK dossierActionUserPK) {
		return _dossierActionUserLocalService.fetchDossierActionUser(dossierActionUserPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierActionUserLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierActionUser> getByDossierAndStepCode(
		long dossierId, String stepCode) {
		return _dossierActionUserLocalService.getByDossierAndStepCode(dossierId,
			stepCode);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierActionUser getByDossierAndUser(
		long dossierActionId, long userId) {
		return _dossierActionUserLocalService.getByDossierAndUser(dossierActionId,
			userId);
	}

	/**
	* Returns the dossier action user with the primary key.
	*
	* @param dossierActionUserPK the primary key of the dossier action user
	* @return the dossier action user
	* @throws PortalException if a dossier action user with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierActionUser getDossierActionUser(
		org.opencps.dossiermgt.service.persistence.DossierActionUserPK dossierActionUserPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierActionUserLocalService.getDossierActionUser(dossierActionUserPK);
	}

	/**
	* Returns a range of all the dossier action users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @return the range of dossier action users
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierActionUser> getDossierActionUsers(
		int start, int end) {
		return _dossierActionUserLocalService.getDossierActionUsers(start, end);
	}

	/**
	* Returns the number of dossier action users.
	*
	* @return the number of dossier action users
	*/
	@Override
	public int getDossierActionUsersCount() {
		return _dossierActionUserLocalService.getDossierActionUsersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierActionUserLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierActionUser> getListUser(
		long dossierActionId) {
		return _dossierActionUserLocalService.getListUser(dossierActionId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierActionUser> getListUserByUserId(
		long userId) {
		return _dossierActionUserLocalService.getListUserByUserId(userId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dossierActionUserLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierActionUserLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the dossier action user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierActionUser the dossier action user
	* @return the dossier action user that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierActionUser updateDossierActionUser(
		org.opencps.dossiermgt.model.DossierActionUser dossierActionUser) {
		return _dossierActionUserLocalService.updateDossierActionUser(dossierActionUser);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierActionUser updateDossierActionUser(
		long userId, long groupId, long dossierActionId, long dossierId,
		String stepCode, int moderator, int assigned, boolean visited)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierActionUserLocalService.updateDossierActionUser(userId,
			groupId, dossierActionId, dossierId, stepCode, moderator, assigned,
			visited);
	}

	@Override
	public DossierActionUserLocalService getWrappedService() {
		return _dossierActionUserLocalService;
	}

	@Override
	public void setWrappedService(
		DossierActionUserLocalService dossierActionUserLocalService) {
		_dossierActionUserLocalService = dossierActionUserLocalService;
	}

	private DossierActionUserLocalService _dossierActionUserLocalService;
}