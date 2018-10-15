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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.exception.NoSuchDossierDocumentException;
import org.opencps.dossiermgt.model.DossierDocument;

import java.io.InputStream;
import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for DossierDocument. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see DossierDocumentLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.DossierDocumentLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.DossierDocumentLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DossierDocumentLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierDocumentLocalServiceUtil} to access the dossier document local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.DossierDocumentLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DossierDocument addDossierDoc(long groupId, Long dossierId,
		long dossierActionId, String documentType, String documentName,
		String documentCode, String sourceFileName, long fileSize,
		InputStream inputStream, String fileType, ServiceContext serviceContext);

	@Indexable(type = IndexableType.REINDEX)
	public DossierDocument addDossierDoc(long groupId, Long dossierId,
		String referenceUid, long dossierActionId, String documentType,
		String documentName, String documentCode, long documentFileId,
		int docSync, ServiceContext serviceContext);

	@Indexable(type = IndexableType.REINDEX)
	public DossierDocument addDossierDoc(long groupId, Long dossierId,
		String referenceUid, long dossierActionId, String documentType,
		String documentName, String documentCode, String sourceFileName,
		long fileSize, InputStream inputStream, String fileType,
		ServiceContext serviceContext);

	/**
	* Adds the dossier document to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierDocument the dossier document
	* @return the dossier document that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DossierDocument addDossierDocument(DossierDocument dossierDocument);

	public long countDossierDocumentList(Long dossierId);

	/**
	* Creates a new dossier document with the primary key. Does not add the dossier document to the database.
	*
	* @param DossierDocumentId the primary key for the new dossier document
	* @return the new dossier document
	*/
	@Transactional(enabled = false)
	public DossierDocument createDossierDocument(long DossierDocumentId);

	/**
	* Deletes the dossier document from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierDocument the dossier document
	* @return the dossier document that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public DossierDocument deleteDossierDocument(
		DossierDocument dossierDocument);

	/**
	* Deletes the dossier document with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param DossierDocumentId the primary key of the dossier document
	* @return the dossier document that was removed
	* @throws PortalException if a dossier document with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DossierDocument deleteDossierDocument(long DossierDocumentId)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierDocument fetchDossierDocument(long DossierDocumentId);

	/**
	* Returns the dossier document matching the UUID and group.
	*
	* @param uuid the dossier document's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierDocument fetchDossierDocumentByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierDocument getByActiocId(long groupId, long dossierActionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierDocument getDocByReferenceUid(long groupId, long dossierId,
		String referenceUid);

	/**
	* Returns the dossier document with the primary key.
	*
	* @param DossierDocumentId the primary key of the dossier document
	* @return the dossier document
	* @throws PortalException if a dossier document with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierDocument getDossierDocument(long DossierDocumentId)
		throws PortalException;

	/**
	* Returns the dossier document matching the UUID and group.
	*
	* @param uuid the dossier document's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier document
	* @throws PortalException if a matching dossier document could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierDocument getDossierDocumentByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierDocument> getDossierDocumentList(Long dossierId,
		Integer start, Integer end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierDocument> getDossierDocuments(int start, int end);

	/**
	* Returns the number of dossier documents.
	*
	* @return the number of dossier documents
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDossierDocumentsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public DossierDocument updateDossierDoc(long groupId, long dossierDocId,
		Long dossierId, String referenceUid, long dossierActionId,
		String documentType, String documentName, String documentCode,
		long documentFileId, int docSync, ServiceContext serviceContext)
		throws NoSuchDossierDocumentException;

	@Indexable(type = IndexableType.REINDEX)
	public DossierDocument updateDossierDoc(long groupId, long dossierDocId,
		Long dossierId, String referenceUid, long dossierActionId,
		String documentType, String documentName, String documentCode,
		String sourceFileName, long fileSize, InputStream inputStream,
		String fileType, ServiceContext serviceContext);

	/**
	* Updates the dossier document in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierDocument the dossier document
	* @return the dossier document that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DossierDocument updateDossierDocument(
		DossierDocument dossierDocument);
}