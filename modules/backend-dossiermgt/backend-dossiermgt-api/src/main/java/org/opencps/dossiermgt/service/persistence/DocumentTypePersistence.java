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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.dossiermgt.exception.NoSuchDocumentTypeException;
import org.opencps.dossiermgt.model.DocumentType;

/**
 * The persistence interface for the document type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DocumentTypePersistenceImpl
 * @see DocumentTypeUtil
 * @generated
 */
@ProviderType
public interface DocumentTypePersistence extends BasePersistence<DocumentType> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DocumentTypeUtil} to access the document type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the document types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching document types
	*/
	public java.util.List<DocumentType> findByUuid(String uuid);

	/**
	* Returns a range of all the document types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of document types
	* @param end the upper bound of the range of document types (not inclusive)
	* @return the range of matching document types
	*/
	public java.util.List<DocumentType> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the document types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of document types
	* @param end the upper bound of the range of document types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document types
	*/
	public java.util.List<DocumentType> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentType> orderByComparator);

	/**
	* Returns an ordered range of all the document types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of document types
	* @param end the upper bound of the range of document types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching document types
	*/
	public java.util.List<DocumentType> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first document type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document type
	* @throws NoSuchDocumentTypeException if a matching document type could not be found
	*/
	public DocumentType findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentType> orderByComparator)
		throws NoSuchDocumentTypeException;

	/**
	* Returns the first document type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document type, or <code>null</code> if a matching document type could not be found
	*/
	public DocumentType fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentType> orderByComparator);

	/**
	* Returns the last document type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document type
	* @throws NoSuchDocumentTypeException if a matching document type could not be found
	*/
	public DocumentType findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentType> orderByComparator)
		throws NoSuchDocumentTypeException;

	/**
	* Returns the last document type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document type, or <code>null</code> if a matching document type could not be found
	*/
	public DocumentType fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentType> orderByComparator);

	/**
	* Returns the document types before and after the current document type in the ordered set where uuid = &#63;.
	*
	* @param DocumentTypeId the primary key of the current document type
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document type
	* @throws NoSuchDocumentTypeException if a document type with the primary key could not be found
	*/
	public DocumentType[] findByUuid_PrevAndNext(long DocumentTypeId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentType> orderByComparator)
		throws NoSuchDocumentTypeException;

	/**
	* Removes all the document types where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of document types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching document types
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the document type where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDocumentTypeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching document type
	* @throws NoSuchDocumentTypeException if a matching document type could not be found
	*/
	public DocumentType findByUUID_G(String uuid, long groupId)
		throws NoSuchDocumentTypeException;

	/**
	* Returns the document type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching document type, or <code>null</code> if a matching document type could not be found
	*/
	public DocumentType fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the document type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching document type, or <code>null</code> if a matching document type could not be found
	*/
	public DocumentType fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the document type where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the document type that was removed
	*/
	public DocumentType removeByUUID_G(String uuid, long groupId)
		throws NoSuchDocumentTypeException;

	/**
	* Returns the number of document types where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching document types
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns the document type where groupId = &#63; and typeCode = &#63; or throws a {@link NoSuchDocumentTypeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the matching document type
	* @throws NoSuchDocumentTypeException if a matching document type could not be found
	*/
	public DocumentType findByF_CODE(long groupId, String typeCode)
		throws NoSuchDocumentTypeException;

	/**
	* Returns the document type where groupId = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the matching document type, or <code>null</code> if a matching document type could not be found
	*/
	public DocumentType fetchByF_CODE(long groupId, String typeCode);

	/**
	* Returns the document type where groupId = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching document type, or <code>null</code> if a matching document type could not be found
	*/
	public DocumentType fetchByF_CODE(long groupId, String typeCode,
		boolean retrieveFromCache);

	/**
	* Removes the document type where groupId = &#63; and typeCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the document type that was removed
	*/
	public DocumentType removeByF_CODE(long groupId, String typeCode)
		throws NoSuchDocumentTypeException;

	/**
	* Returns the number of document types where groupId = &#63; and typeCode = &#63;.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the number of matching document types
	*/
	public int countByF_CODE(long groupId, String typeCode);

	/**
	* Caches the document type in the entity cache if it is enabled.
	*
	* @param documentType the document type
	*/
	public void cacheResult(DocumentType documentType);

	/**
	* Caches the document types in the entity cache if it is enabled.
	*
	* @param documentTypes the document types
	*/
	public void cacheResult(java.util.List<DocumentType> documentTypes);

	/**
	* Creates a new document type with the primary key. Does not add the document type to the database.
	*
	* @param DocumentTypeId the primary key for the new document type
	* @return the new document type
	*/
	public DocumentType create(long DocumentTypeId);

	/**
	* Removes the document type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param DocumentTypeId the primary key of the document type
	* @return the document type that was removed
	* @throws NoSuchDocumentTypeException if a document type with the primary key could not be found
	*/
	public DocumentType remove(long DocumentTypeId)
		throws NoSuchDocumentTypeException;

	public DocumentType updateImpl(DocumentType documentType);

	/**
	* Returns the document type with the primary key or throws a {@link NoSuchDocumentTypeException} if it could not be found.
	*
	* @param DocumentTypeId the primary key of the document type
	* @return the document type
	* @throws NoSuchDocumentTypeException if a document type with the primary key could not be found
	*/
	public DocumentType findByPrimaryKey(long DocumentTypeId)
		throws NoSuchDocumentTypeException;

	/**
	* Returns the document type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param DocumentTypeId the primary key of the document type
	* @return the document type, or <code>null</code> if a document type with the primary key could not be found
	*/
	public DocumentType fetchByPrimaryKey(long DocumentTypeId);

	@Override
	public java.util.Map<java.io.Serializable, DocumentType> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the document types.
	*
	* @return the document types
	*/
	public java.util.List<DocumentType> findAll();

	/**
	* Returns a range of all the document types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of document types
	* @param end the upper bound of the range of document types (not inclusive)
	* @return the range of document types
	*/
	public java.util.List<DocumentType> findAll(int start, int end);

	/**
	* Returns an ordered range of all the document types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of document types
	* @param end the upper bound of the range of document types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of document types
	*/
	public java.util.List<DocumentType> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentType> orderByComparator);

	/**
	* Returns an ordered range of all the document types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of document types
	* @param end the upper bound of the range of document types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of document types
	*/
	public java.util.List<DocumentType> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the document types from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of document types.
	*
	* @return the number of document types
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}