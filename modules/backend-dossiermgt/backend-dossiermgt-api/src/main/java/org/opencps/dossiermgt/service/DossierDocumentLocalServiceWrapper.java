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
 * Provides a wrapper for {@link DossierDocumentLocalService}.
 *
 * @author huymq
 * @see DossierDocumentLocalService
 * @generated
 */
@ProviderType
public class DossierDocumentLocalServiceWrapper
	implements DossierDocumentLocalService,
		ServiceWrapper<DossierDocumentLocalService> {
	public DossierDocumentLocalServiceWrapper(
		DossierDocumentLocalService dossierDocumentLocalService) {
		_dossierDocumentLocalService = dossierDocumentLocalService;
	}

	@Override
	public org.opencps.dossiermgt.model.DossierDocument addDossierDoc(
		long groupId, Long dossierId, long dossierActionId,
		String documentType, String documentName, String documentCode,
		String sourceFileName, long fileSize, java.io.InputStream inputStream,
		String fileType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _dossierDocumentLocalService.addDossierDoc(groupId, dossierId,
			dossierActionId, documentType, documentName, documentCode,
			sourceFileName, fileSize, inputStream, fileType, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierDocument addDossierDoc(
		long groupId, Long dossierId, String referenceUid,
		long dossierActionId, String documentType, String documentName,
		String documentCode, long documentFileId, int docSync,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _dossierDocumentLocalService.addDossierDoc(groupId, dossierId,
			referenceUid, dossierActionId, documentType, documentName,
			documentCode, documentFileId, docSync, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierDocument addDossierDoc(
		long groupId, Long dossierId, String referenceUid,
		long dossierActionId, String documentType, String documentName,
		String documentCode, String sourceFileName, long fileSize,
		java.io.InputStream inputStream, String fileType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _dossierDocumentLocalService.addDossierDoc(groupId, dossierId,
			referenceUid, dossierActionId, documentType, documentName,
			documentCode, sourceFileName, fileSize, inputStream, fileType,
			serviceContext);
	}

	/**
	* Adds the dossier document to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierDocument the dossier document
	* @return the dossier document that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierDocument addDossierDocument(
		org.opencps.dossiermgt.model.DossierDocument dossierDocument) {
		return _dossierDocumentLocalService.addDossierDocument(dossierDocument);
	}

	@Override
	public long countDossierDocumentList(Long dossierId) {
		return _dossierDocumentLocalService.countDossierDocumentList(dossierId);
	}

	/**
	* Creates a new dossier document with the primary key. Does not add the dossier document to the database.
	*
	* @param DossierDocumentId the primary key for the new dossier document
	* @return the new dossier document
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierDocument createDossierDocument(
		long DossierDocumentId) {
		return _dossierDocumentLocalService.createDossierDocument(DossierDocumentId);
	}

	/**
	* Deletes the dossier document from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierDocument the dossier document
	* @return the dossier document that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierDocument deleteDossierDocument(
		org.opencps.dossiermgt.model.DossierDocument dossierDocument) {
		return _dossierDocumentLocalService.deleteDossierDocument(dossierDocument);
	}

	/**
	* Deletes the dossier document with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param DossierDocumentId the primary key of the dossier document
	* @return the dossier document that was removed
	* @throws PortalException if a dossier document with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierDocument deleteDossierDocument(
		long DossierDocumentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierDocumentLocalService.deleteDossierDocument(DossierDocumentId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierDocumentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierDocumentLocalService.dynamicQuery();
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
		return _dossierDocumentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierDocumentLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierDocumentLocalService.dynamicQuery(dynamicQuery, start,
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
		return _dossierDocumentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierDocumentLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierDocument fetchDossierDocument(
		long DossierDocumentId) {
		return _dossierDocumentLocalService.fetchDossierDocument(DossierDocumentId);
	}

	/**
	* Returns the dossier document matching the UUID and group.
	*
	* @param uuid the dossier document's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierDocument fetchDossierDocumentByUuidAndGroupId(
		String uuid, long groupId) {
		return _dossierDocumentLocalService.fetchDossierDocumentByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierDocumentLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.dossiermgt.model.DossierDocument getByActiocId(
		long groupId, long dossierActionId) {
		return _dossierDocumentLocalService.getByActiocId(groupId,
			dossierActionId);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierDocument getDocByReferenceUid(
		long groupId, long dossierId, String referenceUid) {
		return _dossierDocumentLocalService.getDocByReferenceUid(groupId,
			dossierId, referenceUid);
	}

	/**
	* Returns the dossier document with the primary key.
	*
	* @param DossierDocumentId the primary key of the dossier document
	* @return the dossier document
	* @throws PortalException if a dossier document with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierDocument getDossierDocument(
		long DossierDocumentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierDocumentLocalService.getDossierDocument(DossierDocumentId);
	}

	/**
	* Returns the dossier document matching the UUID and group.
	*
	* @param uuid the dossier document's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier document
	* @throws PortalException if a matching dossier document could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierDocument getDossierDocumentByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierDocumentLocalService.getDossierDocumentByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierDocument> getDossierDocumentList(
		Long dossierId, Integer start, Integer end) {
		return _dossierDocumentLocalService.getDossierDocumentList(dossierId,
			start, end);
	}

	/**
	* Returns a range of all the dossier documents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier documents
	* @param end the upper bound of the range of dossier documents (not inclusive)
	* @return the range of dossier documents
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierDocument> getDossierDocuments(
		int start, int end) {
		return _dossierDocumentLocalService.getDossierDocuments(start, end);
	}

	/**
	* Returns the number of dossier documents.
	*
	* @return the number of dossier documents
	*/
	@Override
	public int getDossierDocumentsCount() {
		return _dossierDocumentLocalService.getDossierDocumentsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierDocumentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dossierDocumentLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierDocumentLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierDocument updateDossierDoc(
		long groupId, long dossierDocId, Long dossierId, String referenceUid,
		long dossierActionId, String documentType, String documentName,
		String documentCode, long documentFileId, int docSync,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return _dossierDocumentLocalService.updateDossierDoc(groupId,
			dossierDocId, dossierId, referenceUid, dossierActionId,
			documentType, documentName, documentCode, documentFileId, docSync,
			serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierDocument updateDossierDoc(
		long groupId, long dossierDocId, Long dossierId, String referenceUid,
		long dossierActionId, String documentType, String documentName,
		String documentCode, String sourceFileName, long fileSize,
		java.io.InputStream inputStream, String fileType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _dossierDocumentLocalService.updateDossierDoc(groupId,
			dossierDocId, dossierId, referenceUid, dossierActionId,
			documentType, documentName, documentCode, sourceFileName, fileSize,
			inputStream, fileType, serviceContext);
	}

	/**
	* Updates the dossier document in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierDocument the dossier document
	* @return the dossier document that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierDocument updateDossierDocument(
		org.opencps.dossiermgt.model.DossierDocument dossierDocument) {
		return _dossierDocumentLocalService.updateDossierDocument(dossierDocument);
	}

	@Override
	public DossierDocumentLocalService getWrappedService() {
		return _dossierDocumentLocalService;
	}

	@Override
	public void setWrappedService(
		DossierDocumentLocalService dossierDocumentLocalService) {
		_dossierDocumentLocalService = dossierDocumentLocalService;
	}

	private DossierDocumentLocalService _dossierDocumentLocalService;
}