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
 * Provides a wrapper for {@link DossierSyncLocalService}.
 *
 * @author huymq
 * @see DossierSyncLocalService
 * @generated
 */
@ProviderType
public class DossierSyncLocalServiceWrapper implements DossierSyncLocalService,
	ServiceWrapper<DossierSyncLocalService> {
	public DossierSyncLocalServiceWrapper(
		DossierSyncLocalService dossierSyncLocalService) {
		_dossierSyncLocalService = dossierSyncLocalService;
	}

	/**
	* Adds the dossier sync to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierSync the dossier sync
	* @return the dossier sync that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierSync addDossierSync(
		org.opencps.dossiermgt.model.DossierSync dossierSync) {
		return _dossierSyncLocalService.addDossierSync(dossierSync);
	}

	@Override
	public long countByDossierAndInfoType(long groupId, String dossierRefUid,
		int infoType) {
		return _dossierSyncLocalService.countByDossierAndInfoType(groupId,
			dossierRefUid, infoType);
	}

	@Override
	public long countByDossierAndInfoTypeArr(long groupId,
		String dossierRefUid, int[] infoType) {
		return _dossierSyncLocalService.countByDossierAndInfoTypeArr(groupId,
			dossierRefUid, infoType);
	}

	@Override
	public long countDossierSyncByIdList(Long dossierId, Integer model,
		int actionCodeNo) {
		return _dossierSyncLocalService.countDossierSyncByIdList(dossierId,
			model, actionCodeNo);
	}

	@Override
	public long countDossierSyncList(String actionCode, int syncType) {
		return _dossierSyncLocalService.countDossierSyncList(actionCode,
			syncType);
	}

	@Override
	public long countForApplicantAndActionCode(long groupId, String actionCode) {
		return _dossierSyncLocalService.countForApplicantAndActionCode(groupId,
			actionCode);
	}

	/**
	* Creates a new dossier sync with the primary key. Does not add the dossier sync to the database.
	*
	* @param DossierSyncId the primary key for the new dossier sync
	* @return the new dossier sync
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierSync createDossierSync(
		long DossierSyncId) {
		return _dossierSyncLocalService.createDossierSync(DossierSyncId);
	}

	/**
	* Deletes the dossier sync from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierSync the dossier sync
	* @return the dossier sync that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierSync deleteDossierSync(
		org.opencps.dossiermgt.model.DossierSync dossierSync) {
		return _dossierSyncLocalService.deleteDossierSync(dossierSync);
	}

	/**
	* Deletes the dossier sync with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param DossierSyncId the primary key of the dossier sync
	* @return the dossier sync that was removed
	* @throws PortalException if a dossier sync with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierSync deleteDossierSync(
		long DossierSyncId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierSyncLocalService.deleteDossierSync(DossierSyncId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierSyncLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierSyncLocalService.dynamicQuery();
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
		return _dossierSyncLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierSyncLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierSyncLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _dossierSyncLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierSyncLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierSync fetchDossierSync(
		long DossierSyncId) {
		return _dossierSyncLocalService.fetchDossierSync(DossierSyncId);
	}

	/**
	* Returns the dossier sync matching the UUID and group.
	*
	* @param uuid the dossier sync's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierSync fetchDossierSyncByUuidAndGroupId(
		String uuid, long groupId) {
		return _dossierSyncLocalService.fetchDossierSyncByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierSync> findAll(
		Integer start, Integer end) {
		return _dossierSyncLocalService.findAll(start, end);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierSync> findByDossierAndInfoType(
		long groupId, String dossierRefUid, int infoType, int start, int end) {
		return _dossierSyncLocalService.findByDossierAndInfoType(groupId,
			dossierRefUid, infoType, start, end);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierSync> findByDossierAndInfoTypeArr(
		long groupId, String dossierRefUid, int[] infoType, int start, int end) {
		return _dossierSyncLocalService.findByDossierAndInfoTypeArr(groupId,
			dossierRefUid, infoType, start, end);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierSync> findByDossierAndInfoTypeArr(
		long groupId, String dossierRefUid, int[] infoType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.DossierSync> orderByComparator) {
		return _dossierSyncLocalService.findByDossierAndInfoTypeArr(groupId,
			dossierRefUid, infoType, start, end, orderByComparator);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierSync> findByG_DID(
		long groupId, long dossierId) {
		return _dossierSyncLocalService.findByG_DID(groupId, dossierId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierSync> findByG_DID_ST(
		long groupId, long dossierId, int state) {
		return _dossierSyncLocalService.findByG_DID_ST(groupId, dossierId, state);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierSync> findByState(
		Integer state, Integer start, Integer end) {
		return _dossierSyncLocalService.findByState(state, start, end);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierSync> findForApplicantAndActionCode(
		long groupId, String actionCode, int start, int end) {
		return _dossierSyncLocalService.findForApplicantAndActionCode(groupId,
			actionCode, start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierSyncLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.dossiermgt.model.DossierSync getByDID_DAD(long groupId,
		long dossierId, long dossierActionId) {
		return _dossierSyncLocalService.getByDID_DAD(groupId, dossierId,
			dossierActionId);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierSync getByDID_DAD_AC(
		long groupId, long dossierId, long dossierActionId, String actionCode) {
		return _dossierSyncLocalService.getByDID_DAD_AC(groupId, dossierId,
			dossierActionId, actionCode);
	}

	/**
	* Returns the dossier sync with the primary key.
	*
	* @param DossierSyncId the primary key of the dossier sync
	* @return the dossier sync
	* @throws PortalException if a dossier sync with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierSync getDossierSync(
		long DossierSyncId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierSyncLocalService.getDossierSync(DossierSyncId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierSync> getDossierSyncByIdList(
		Long dossierId, Integer model, int actionCodeNo, Integer start,
		Integer limit) {
		return _dossierSyncLocalService.getDossierSyncByIdList(dossierId,
			model, actionCodeNo, start, limit);
	}

	/**
	* Returns the dossier sync matching the UUID and group.
	*
	* @param uuid the dossier sync's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier sync
	* @throws PortalException if a matching dossier sync could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierSync getDossierSyncByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierSyncLocalService.getDossierSyncByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierSync> getDossierSyncList(
		String actionCode, int syncType, Integer start, Integer limit) {
		return _dossierSyncLocalService.getDossierSyncList(actionCode,
			syncType, start, limit);
	}

	/**
	* Returns a range of all the dossier syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of dossier syncs
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierSync> getDossierSyncs(
		int start, int end) {
		return _dossierSyncLocalService.getDossierSyncs(start, end);
	}

	/**
	* Returns the number of dossier syncs.
	*
	* @return the number of dossier syncs
	*/
	@Override
	public int getDossierSyncsCount() {
		return _dossierSyncLocalService.getDossierSyncsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierSyncLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dossierSyncLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierSyncLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public void removeByDossierId(long groupId, long dossierId) {
		_dossierSyncLocalService.removeByDossierId(groupId, dossierId);
	}

	/**
	* Updates the dossier sync in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierSync the dossier sync
	* @return the dossier sync that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierSync updateDossierSync(
		org.opencps.dossiermgt.model.DossierSync dossierSync) {
		return _dossierSyncLocalService.updateDossierSync(dossierSync);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierSync updateDossierSync(
		long groupId, long userId, long dossierId, String dossierReferenceUid,
		String syncRefUid, long dossierActionId, String actionCode,
		String actionName, String actionUser, String actionNote, int syncType,
		int infoType, String payload, String serverNo, int state) {
		return _dossierSyncLocalService.updateDossierSync(groupId, userId,
			dossierId, dossierReferenceUid, syncRefUid, dossierActionId,
			actionCode, actionName, actionUser, actionNote, syncType, infoType,
			payload, serverNo, state);
	}

	@Override
	public DossierSyncLocalService getWrappedService() {
		return _dossierSyncLocalService;
	}

	@Override
	public void setWrappedService(
		DossierSyncLocalService dossierSyncLocalService) {
		_dossierSyncLocalService = dossierSyncLocalService;
	}

	private DossierSyncLocalService _dossierSyncLocalService;
}