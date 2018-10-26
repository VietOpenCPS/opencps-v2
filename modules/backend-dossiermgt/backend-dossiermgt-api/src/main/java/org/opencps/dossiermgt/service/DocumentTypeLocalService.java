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
import com.liferay.portal.kernel.json.JSONObject;
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

import org.opencps.dossiermgt.model.DocumentType;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for DocumentType. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see DocumentTypeLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.DocumentTypeLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.DocumentTypeLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DocumentTypeLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DocumentTypeLocalServiceUtil} to access the document type local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.DocumentTypeLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the document type to the database. Also notifies the appropriate model listeners.
	*
	* @param documentType the document type
	* @return the document type that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DocumentType addDocumentType(DocumentType documentType);

	@Indexable(type = IndexableType.REINDEX)
	public DocumentType adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public DocumentType adminProcessDelete(Long id);

	/**
	* Creates a new document type with the primary key. Does not add the document type to the database.
	*
	* @param DocumentTypeId the primary key for the new document type
	* @return the new document type
	*/
	@Transactional(enabled = false)
	public DocumentType createDocumentType(long DocumentTypeId);

	/**
	* Deletes the document type from the database. Also notifies the appropriate model listeners.
	*
	* @param documentType the document type
	* @return the document type that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public DocumentType deleteDocumentType(DocumentType documentType);

	/**
	* Deletes the document type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param DocumentTypeId the primary key of the document type
	* @return the document type that was removed
	* @throws PortalException if a document type with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DocumentType deleteDocumentType(long DocumentTypeId)
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public DocumentType fetchDocumentType(long DocumentTypeId);

	/**
	* Returns the document type matching the UUID and group.
	*
	* @param uuid the document type's UUID
	* @param groupId the primary key of the group
	* @return the matching document type, or <code>null</code> if a matching document type could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DocumentType fetchDocumentTypeByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DocumentType getByTypeCode(long groupId, String typeCode);

	/**
	* Returns the document type with the primary key.
	*
	* @param DocumentTypeId the primary key of the document type
	* @return the document type
	* @throws PortalException if a document type with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DocumentType getDocumentType(long DocumentTypeId)
		throws PortalException;

	/**
	* Returns the document type matching the UUID and group.
	*
	* @param uuid the document type's UUID
	* @param groupId the primary key of the group
	* @return the matching document type
	* @throws PortalException if a matching document type could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DocumentType getDocumentTypeByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DocumentType> getDocumentTypes(int start, int end);

	/**
	* Returns the number of document types.
	*
	* @return the number of document types
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDocumentTypesCount();

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

	public DocumentType insertDocType(long userId, long groupId,
		String typeCode, int templateClass, String documentName,
		String codePattern, String documentScript, int docSync,
		ServiceContext serviceContext);

	@Indexable(type = IndexableType.REINDEX)
	public DocumentType updateDocType(Long docId, long userId, long groupId,
		String typeCode, int templateClass, String documentName,
		String codePattern, String documentScript, int docSync,
		ServiceContext serviceContext);

	/**
	* Updates the document type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param documentType the document type
	* @return the document type that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DocumentType updateDocumentType(DocumentType documentType);

	@Indexable(type = IndexableType.REINDEX)
	public DocumentType updateDocumentTypeDB(long userId, long groupId,
		String typeCode, Integer templateClass, String documentName,
		String codePattern, Integer docSync, String documentScript);
}