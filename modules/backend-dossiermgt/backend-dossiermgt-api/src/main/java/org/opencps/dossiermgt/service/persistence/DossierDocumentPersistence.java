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

import org.opencps.dossiermgt.exception.NoSuchDossierDocumentException;
import org.opencps.dossiermgt.model.DossierDocument;

/**
 * The persistence interface for the dossier document service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierDocumentPersistenceImpl
 * @see DossierDocumentUtil
 * @generated
 */
@ProviderType
public interface DossierDocumentPersistence extends BasePersistence<DossierDocument> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierDocumentUtil} to access the dossier document persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dossier documents where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier documents
	*/
	public java.util.List<DossierDocument> findByUuid(String uuid);

	/**
	* Returns a range of all the dossier documents where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier documents
	* @param end the upper bound of the range of dossier documents (not inclusive)
	* @return the range of matching dossier documents
	*/
	public java.util.List<DossierDocument> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier documents where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier documents
	* @param end the upper bound of the range of dossier documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier documents
	*/
	public java.util.List<DossierDocument> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator);

	/**
	* Returns an ordered range of all the dossier documents where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier documents
	* @param end the upper bound of the range of dossier documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier documents
	*/
	public java.util.List<DossierDocument> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier document in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public DossierDocument findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator)
		throws NoSuchDossierDocumentException;

	/**
	* Returns the first dossier document in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public DossierDocument fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator);

	/**
	* Returns the last dossier document in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public DossierDocument findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator)
		throws NoSuchDossierDocumentException;

	/**
	* Returns the last dossier document in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public DossierDocument fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator);

	/**
	* Returns the dossier documents before and after the current dossier document in the ordered set where uuid = &#63;.
	*
	* @param DossierDocumentId the primary key of the current dossier document
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier document
	* @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	*/
	public DossierDocument[] findByUuid_PrevAndNext(long DossierDocumentId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator)
		throws NoSuchDossierDocumentException;

	/**
	* Removes all the dossier documents where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dossier documents where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier documents
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dossier document where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierDocumentException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public DossierDocument findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierDocumentException;

	/**
	* Returns the dossier document where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public DossierDocument fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dossier document where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public DossierDocument fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier document where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier document that was removed
	*/
	public DossierDocument removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierDocumentException;

	/**
	* Returns the number of dossier documents where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier documents
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dossier documents where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the matching dossier documents
	*/
	public java.util.List<DossierDocument> findByF_DOSSIERID(long dossierId);

	/**
	* Returns a range of all the dossier documents where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier documents
	* @param end the upper bound of the range of dossier documents (not inclusive)
	* @return the range of matching dossier documents
	*/
	public java.util.List<DossierDocument> findByF_DOSSIERID(long dossierId,
		int start, int end);

	/**
	* Returns an ordered range of all the dossier documents where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier documents
	* @param end the upper bound of the range of dossier documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier documents
	*/
	public java.util.List<DossierDocument> findByF_DOSSIERID(long dossierId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator);

	/**
	* Returns an ordered range of all the dossier documents where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier documents
	* @param end the upper bound of the range of dossier documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier documents
	*/
	public java.util.List<DossierDocument> findByF_DOSSIERID(long dossierId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier document in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public DossierDocument findByF_DOSSIERID_First(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator)
		throws NoSuchDossierDocumentException;

	/**
	* Returns the first dossier document in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public DossierDocument fetchByF_DOSSIERID_First(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator);

	/**
	* Returns the last dossier document in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public DossierDocument findByF_DOSSIERID_Last(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator)
		throws NoSuchDossierDocumentException;

	/**
	* Returns the last dossier document in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public DossierDocument fetchByF_DOSSIERID_Last(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator);

	/**
	* Returns the dossier documents before and after the current dossier document in the ordered set where dossierId = &#63;.
	*
	* @param DossierDocumentId the primary key of the current dossier document
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier document
	* @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	*/
	public DossierDocument[] findByF_DOSSIERID_PrevAndNext(
		long DossierDocumentId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator)
		throws NoSuchDossierDocumentException;

	/**
	* Removes all the dossier documents where dossierId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	*/
	public void removeByF_DOSSIERID(long dossierId);

	/**
	* Returns the number of dossier documents where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the number of matching dossier documents
	*/
	public int countByF_DOSSIERID(long dossierId);

	/**
	* Returns the dossier document where groupId = &#63; and dossierActionId = &#63; or throws a {@link NoSuchDossierDocumentException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dossierActionId the dossier action ID
	* @return the matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public DossierDocument findByF_GID_DID(long groupId, long dossierActionId)
		throws NoSuchDossierDocumentException;

	/**
	* Returns the dossier document where groupId = &#63; and dossierActionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dossierActionId the dossier action ID
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public DossierDocument fetchByF_GID_DID(long groupId, long dossierActionId);

	/**
	* Returns the dossier document where groupId = &#63; and dossierActionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dossierActionId the dossier action ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public DossierDocument fetchByF_GID_DID(long groupId, long dossierActionId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier document where groupId = &#63; and dossierActionId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierActionId the dossier action ID
	* @return the dossier document that was removed
	*/
	public DossierDocument removeByF_GID_DID(long groupId, long dossierActionId)
		throws NoSuchDossierDocumentException;

	/**
	* Returns the number of dossier documents where groupId = &#63; and dossierActionId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierActionId the dossier action ID
	* @return the number of matching dossier documents
	*/
	public int countByF_GID_DID(long groupId, long dossierActionId);

	/**
	* Returns the dossier document where groupId = &#63; and dossierId = &#63; and referenceUid = &#63; or throws a {@link NoSuchDossierDocumentException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public DossierDocument findByF_GID_DID_REF(long groupId, long dossierId,
		String referenceUid) throws NoSuchDossierDocumentException;

	/**
	* Returns the dossier document where groupId = &#63; and dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public DossierDocument fetchByF_GID_DID_REF(long groupId, long dossierId,
		String referenceUid);

	/**
	* Returns the dossier document where groupId = &#63; and dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public DossierDocument fetchByF_GID_DID_REF(long groupId, long dossierId,
		String referenceUid, boolean retrieveFromCache);

	/**
	* Removes the dossier document where groupId = &#63; and dossierId = &#63; and referenceUid = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the dossier document that was removed
	*/
	public DossierDocument removeByF_GID_DID_REF(long groupId, long dossierId,
		String referenceUid) throws NoSuchDossierDocumentException;

	/**
	* Returns the number of dossier documents where groupId = &#63; and dossierId = &#63; and referenceUid = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the number of matching dossier documents
	*/
	public int countByF_GID_DID_REF(long groupId, long dossierId,
		String referenceUid);

	/**
	* Caches the dossier document in the entity cache if it is enabled.
	*
	* @param dossierDocument the dossier document
	*/
	public void cacheResult(DossierDocument dossierDocument);

	/**
	* Caches the dossier documents in the entity cache if it is enabled.
	*
	* @param dossierDocuments the dossier documents
	*/
	public void cacheResult(java.util.List<DossierDocument> dossierDocuments);

	/**
	* Creates a new dossier document with the primary key. Does not add the dossier document to the database.
	*
	* @param DossierDocumentId the primary key for the new dossier document
	* @return the new dossier document
	*/
	public DossierDocument create(long DossierDocumentId);

	/**
	* Removes the dossier document with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param DossierDocumentId the primary key of the dossier document
	* @return the dossier document that was removed
	* @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	*/
	public DossierDocument remove(long DossierDocumentId)
		throws NoSuchDossierDocumentException;

	public DossierDocument updateImpl(DossierDocument dossierDocument);

	/**
	* Returns the dossier document with the primary key or throws a {@link NoSuchDossierDocumentException} if it could not be found.
	*
	* @param DossierDocumentId the primary key of the dossier document
	* @return the dossier document
	* @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	*/
	public DossierDocument findByPrimaryKey(long DossierDocumentId)
		throws NoSuchDossierDocumentException;

	/**
	* Returns the dossier document with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param DossierDocumentId the primary key of the dossier document
	* @return the dossier document, or <code>null</code> if a dossier document with the primary key could not be found
	*/
	public DossierDocument fetchByPrimaryKey(long DossierDocumentId);

	@Override
	public java.util.Map<java.io.Serializable, DossierDocument> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dossier documents.
	*
	* @return the dossier documents
	*/
	public java.util.List<DossierDocument> findAll();

	/**
	* Returns a range of all the dossier documents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier documents
	* @param end the upper bound of the range of dossier documents (not inclusive)
	* @return the range of dossier documents
	*/
	public java.util.List<DossierDocument> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dossier documents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier documents
	* @param end the upper bound of the range of dossier documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier documents
	*/
	public java.util.List<DossierDocument> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator);

	/**
	* Returns an ordered range of all the dossier documents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier documents
	* @param end the upper bound of the range of dossier documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier documents
	*/
	public java.util.List<DossierDocument> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierDocument> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier documents from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dossier documents.
	*
	* @return the number of dossier documents
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}