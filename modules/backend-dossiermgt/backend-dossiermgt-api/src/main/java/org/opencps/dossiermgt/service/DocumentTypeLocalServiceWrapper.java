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
 * Provides a wrapper for {@link DocumentTypeLocalService}.
 *
 * @author huymq
 * @see DocumentTypeLocalService
 * @generated
 */
@ProviderType
public class DocumentTypeLocalServiceWrapper implements DocumentTypeLocalService,
	ServiceWrapper<DocumentTypeLocalService> {
	public DocumentTypeLocalServiceWrapper(
		DocumentTypeLocalService documentTypeLocalService) {
		_documentTypeLocalService = documentTypeLocalService;
	}

	/**
	* Adds the document type to the database. Also notifies the appropriate model listeners.
	*
	* @param documentType the document type
	* @return the document type that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.DocumentType addDocumentType(
		org.opencps.dossiermgt.model.DocumentType documentType) {
		return _documentTypeLocalService.addDocumentType(documentType);
	}

	/**
	* Creates a new document type with the primary key. Does not add the document type to the database.
	*
	* @param DocumentTypeId the primary key for the new document type
	* @return the new document type
	*/
	@Override
	public org.opencps.dossiermgt.model.DocumentType createDocumentType(
		long DocumentTypeId) {
		return _documentTypeLocalService.createDocumentType(DocumentTypeId);
	}

	/**
	* Deletes the document type from the database. Also notifies the appropriate model listeners.
	*
	* @param documentType the document type
	* @return the document type that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.DocumentType deleteDocumentType(
		org.opencps.dossiermgt.model.DocumentType documentType) {
		return _documentTypeLocalService.deleteDocumentType(documentType);
	}

	/**
	* Deletes the document type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param DocumentTypeId the primary key of the document type
	* @return the document type that was removed
	* @throws PortalException if a document type with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DocumentType deleteDocumentType(
		long DocumentTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentTypeLocalService.deleteDocumentType(DocumentTypeId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentTypeLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _documentTypeLocalService.dynamicQuery();
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
		return _documentTypeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _documentTypeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _documentTypeLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _documentTypeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _documentTypeLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.DocumentType fetchDocumentType(
		long DocumentTypeId) {
		return _documentTypeLocalService.fetchDocumentType(DocumentTypeId);
	}

	/**
	* Returns the document type matching the UUID and group.
	*
	* @param uuid the document type's UUID
	* @param groupId the primary key of the group
	* @return the matching document type, or <code>null</code> if a matching document type could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DocumentType fetchDocumentTypeByUuidAndGroupId(
		String uuid, long groupId) {
		return _documentTypeLocalService.fetchDocumentTypeByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _documentTypeLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.dossiermgt.model.DocumentType getByTypeCode(
		long groupId, String typeCode) {
		return _documentTypeLocalService.getByTypeCode(groupId, typeCode);
	}

	/**
	* Returns the document type with the primary key.
	*
	* @param DocumentTypeId the primary key of the document type
	* @return the document type
	* @throws PortalException if a document type with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DocumentType getDocumentType(
		long DocumentTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentTypeLocalService.getDocumentType(DocumentTypeId);
	}

	/**
	* Returns the document type matching the UUID and group.
	*
	* @param uuid the document type's UUID
	* @param groupId the primary key of the group
	* @return the matching document type
	* @throws PortalException if a matching document type could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DocumentType getDocumentTypeByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentTypeLocalService.getDocumentTypeByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the document types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of document types
	* @param end the upper bound of the range of document types (not inclusive)
	* @return the range of document types
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DocumentType> getDocumentTypes(
		int start, int end) {
		return _documentTypeLocalService.getDocumentTypes(start, end);
	}

	/**
	* Returns the number of document types.
	*
	* @return the number of document types
	*/
	@Override
	public int getDocumentTypesCount() {
		return _documentTypeLocalService.getDocumentTypesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _documentTypeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _documentTypeLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentTypeLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.dossiermgt.model.DocumentType insertDocType(
		long userId, long groupId, String typeCode, int templateClass,
		String documentName, String codePattern, String documentScript,
		int docSync,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _documentTypeLocalService.insertDocType(userId, groupId,
			typeCode, templateClass, documentName, codePattern, documentScript,
			docSync, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.DocumentType updateDocType(Long docId,
		long userId, long groupId, String typeCode, int templateClass,
		String documentName, String codePattern, String documentScript,
		int docSync,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _documentTypeLocalService.updateDocType(docId, userId, groupId,
			typeCode, templateClass, documentName, codePattern, documentScript,
			docSync, serviceContext);
	}

	/**
	* Updates the document type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param documentType the document type
	* @return the document type that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.DocumentType updateDocumentType(
		org.opencps.dossiermgt.model.DocumentType documentType) {
		return _documentTypeLocalService.updateDocumentType(documentType);
	}

	@Override
	public org.opencps.dossiermgt.model.DocumentType updateDocumentTypeDB(
		long userId, long groupId, String typeCode, Integer templateClass,
		String documentName, String codePattern, Integer docSync,
		String documentScript) {
		return _documentTypeLocalService.updateDocumentTypeDB(userId, groupId,
			typeCode, templateClass, documentName, codePattern, docSync,
			documentScript);
	}

	@Override
	public DocumentTypeLocalService getWrappedService() {
		return _documentTypeLocalService;
	}

	@Override
	public void setWrappedService(
		DocumentTypeLocalService documentTypeLocalService) {
		_documentTypeLocalService = documentTypeLocalService;
	}

	private DocumentTypeLocalService _documentTypeLocalService;
}