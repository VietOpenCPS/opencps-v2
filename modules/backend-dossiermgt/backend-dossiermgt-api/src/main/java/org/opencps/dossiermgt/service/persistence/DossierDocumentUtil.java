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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.DossierDocument;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier document service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DossierDocumentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierDocumentPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierDocumentPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierDocumentUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(DossierDocument dossierDocument) {
		getPersistence().clearCache(dossierDocument);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DossierDocument> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierDocument> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierDocument> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierDocument> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierDocument update(DossierDocument dossierDocument) {
		return getPersistence().update(dossierDocument);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierDocument update(DossierDocument dossierDocument,
		ServiceContext serviceContext) {
		return getPersistence().update(dossierDocument, serviceContext);
	}

	/**
	* Returns all the dossier documents where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier documents
	*/
	public static List<DossierDocument> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<DossierDocument> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<DossierDocument> findByUuid(String uuid, int start,
		int end, OrderByComparator<DossierDocument> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<DossierDocument> findByUuid(String uuid, int start,
		int end, OrderByComparator<DossierDocument> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier document in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public static DossierDocument findByUuid_First(String uuid,
		OrderByComparator<DossierDocument> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier document in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public static DossierDocument fetchByUuid_First(String uuid,
		OrderByComparator<DossierDocument> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier document in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public static DossierDocument findByUuid_Last(String uuid,
		OrderByComparator<DossierDocument> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier document in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public static DossierDocument fetchByUuid_Last(String uuid,
		OrderByComparator<DossierDocument> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossier documents before and after the current dossier document in the ordered set where uuid = &#63;.
	*
	* @param DossierDocumentId the primary key of the current dossier document
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier document
	* @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	*/
	public static DossierDocument[] findByUuid_PrevAndNext(
		long DossierDocumentId, String uuid,
		OrderByComparator<DossierDocument> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence()
				   .findByUuid_PrevAndNext(DossierDocumentId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dossier documents where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossier documents where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier documents
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dossier document where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierDocumentException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public static DossierDocument findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier document where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public static DossierDocument fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier document where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public static DossierDocument fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dossier document where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier document that was removed
	*/
	public static DossierDocument removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dossier documents where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier documents
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dossier documents where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the matching dossier documents
	*/
	public static List<DossierDocument> findByF_DOSSIERID(long dossierId) {
		return getPersistence().findByF_DOSSIERID(dossierId);
	}

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
	public static List<DossierDocument> findByF_DOSSIERID(long dossierId,
		int start, int end) {
		return getPersistence().findByF_DOSSIERID(dossierId, start, end);
	}

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
	public static List<DossierDocument> findByF_DOSSIERID(long dossierId,
		int start, int end, OrderByComparator<DossierDocument> orderByComparator) {
		return getPersistence()
				   .findByF_DOSSIERID(dossierId, start, end, orderByComparator);
	}

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
	public static List<DossierDocument> findByF_DOSSIERID(long dossierId,
		int start, int end,
		OrderByComparator<DossierDocument> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_DOSSIERID(dossierId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier document in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public static DossierDocument findByF_DOSSIERID_First(long dossierId,
		OrderByComparator<DossierDocument> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence()
				   .findByF_DOSSIERID_First(dossierId, orderByComparator);
	}

	/**
	* Returns the first dossier document in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public static DossierDocument fetchByF_DOSSIERID_First(long dossierId,
		OrderByComparator<DossierDocument> orderByComparator) {
		return getPersistence()
				   .fetchByF_DOSSIERID_First(dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier document in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public static DossierDocument findByF_DOSSIERID_Last(long dossierId,
		OrderByComparator<DossierDocument> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence()
				   .findByF_DOSSIERID_Last(dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier document in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public static DossierDocument fetchByF_DOSSIERID_Last(long dossierId,
		OrderByComparator<DossierDocument> orderByComparator) {
		return getPersistence()
				   .fetchByF_DOSSIERID_Last(dossierId, orderByComparator);
	}

	/**
	* Returns the dossier documents before and after the current dossier document in the ordered set where dossierId = &#63;.
	*
	* @param DossierDocumentId the primary key of the current dossier document
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier document
	* @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	*/
	public static DossierDocument[] findByF_DOSSIERID_PrevAndNext(
		long DossierDocumentId, long dossierId,
		OrderByComparator<DossierDocument> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence()
				   .findByF_DOSSIERID_PrevAndNext(DossierDocumentId, dossierId,
			orderByComparator);
	}

	/**
	* Removes all the dossier documents where dossierId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	*/
	public static void removeByF_DOSSIERID(long dossierId) {
		getPersistence().removeByF_DOSSIERID(dossierId);
	}

	/**
	* Returns the number of dossier documents where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the number of matching dossier documents
	*/
	public static int countByF_DOSSIERID(long dossierId) {
		return getPersistence().countByF_DOSSIERID(dossierId);
	}

	/**
	* Returns the dossier document where groupId = &#63; and dossierActionId = &#63; or throws a {@link NoSuchDossierDocumentException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dossierActionId the dossier action ID
	* @return the matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public static DossierDocument findByF_GID_DID(long groupId,
		long dossierActionId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence().findByF_GID_DID(groupId, dossierActionId);
	}

	/**
	* Returns the dossier document where groupId = &#63; and dossierActionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dossierActionId the dossier action ID
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public static DossierDocument fetchByF_GID_DID(long groupId,
		long dossierActionId) {
		return getPersistence().fetchByF_GID_DID(groupId, dossierActionId);
	}

	/**
	* Returns the dossier document where groupId = &#63; and dossierActionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dossierActionId the dossier action ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public static DossierDocument fetchByF_GID_DID(long groupId,
		long dossierActionId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_DID(groupId, dossierActionId, retrieveFromCache);
	}

	/**
	* Removes the dossier document where groupId = &#63; and dossierActionId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierActionId the dossier action ID
	* @return the dossier document that was removed
	*/
	public static DossierDocument removeByF_GID_DID(long groupId,
		long dossierActionId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence().removeByF_GID_DID(groupId, dossierActionId);
	}

	/**
	* Returns the number of dossier documents where groupId = &#63; and dossierActionId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierActionId the dossier action ID
	* @return the number of matching dossier documents
	*/
	public static int countByF_GID_DID(long groupId, long dossierActionId) {
		return getPersistence().countByF_GID_DID(groupId, dossierActionId);
	}

	/**
	* Returns the dossier document where groupId = &#63; and dossierId = &#63; and referenceUid = &#63; or throws a {@link NoSuchDossierDocumentException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the matching dossier document
	* @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	*/
	public static DossierDocument findByF_GID_DID_REF(long groupId,
		long dossierId, String referenceUid)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence()
				   .findByF_GID_DID_REF(groupId, dossierId, referenceUid);
	}

	/**
	* Returns the dossier document where groupId = &#63; and dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public static DossierDocument fetchByF_GID_DID_REF(long groupId,
		long dossierId, String referenceUid) {
		return getPersistence()
				   .fetchByF_GID_DID_REF(groupId, dossierId, referenceUid);
	}

	/**
	* Returns the dossier document where groupId = &#63; and dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	*/
	public static DossierDocument fetchByF_GID_DID_REF(long groupId,
		long dossierId, String referenceUid, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_DID_REF(groupId, dossierId, referenceUid,
			retrieveFromCache);
	}

	/**
	* Removes the dossier document where groupId = &#63; and dossierId = &#63; and referenceUid = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the dossier document that was removed
	*/
	public static DossierDocument removeByF_GID_DID_REF(long groupId,
		long dossierId, String referenceUid)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence()
				   .removeByF_GID_DID_REF(groupId, dossierId, referenceUid);
	}

	/**
	* Returns the number of dossier documents where groupId = &#63; and dossierId = &#63; and referenceUid = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the number of matching dossier documents
	*/
	public static int countByF_GID_DID_REF(long groupId, long dossierId,
		String referenceUid) {
		return getPersistence()
				   .countByF_GID_DID_REF(groupId, dossierId, referenceUid);
	}

	/**
	* Caches the dossier document in the entity cache if it is enabled.
	*
	* @param dossierDocument the dossier document
	*/
	public static void cacheResult(DossierDocument dossierDocument) {
		getPersistence().cacheResult(dossierDocument);
	}

	/**
	* Caches the dossier documents in the entity cache if it is enabled.
	*
	* @param dossierDocuments the dossier documents
	*/
	public static void cacheResult(List<DossierDocument> dossierDocuments) {
		getPersistence().cacheResult(dossierDocuments);
	}

	/**
	* Creates a new dossier document with the primary key. Does not add the dossier document to the database.
	*
	* @param DossierDocumentId the primary key for the new dossier document
	* @return the new dossier document
	*/
	public static DossierDocument create(long DossierDocumentId) {
		return getPersistence().create(DossierDocumentId);
	}

	/**
	* Removes the dossier document with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param DossierDocumentId the primary key of the dossier document
	* @return the dossier document that was removed
	* @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	*/
	public static DossierDocument remove(long DossierDocumentId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence().remove(DossierDocumentId);
	}

	public static DossierDocument updateImpl(DossierDocument dossierDocument) {
		return getPersistence().updateImpl(dossierDocument);
	}

	/**
	* Returns the dossier document with the primary key or throws a {@link NoSuchDossierDocumentException} if it could not be found.
	*
	* @param DossierDocumentId the primary key of the dossier document
	* @return the dossier document
	* @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	*/
	public static DossierDocument findByPrimaryKey(long DossierDocumentId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierDocumentException {
		return getPersistence().findByPrimaryKey(DossierDocumentId);
	}

	/**
	* Returns the dossier document with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param DossierDocumentId the primary key of the dossier document
	* @return the dossier document, or <code>null</code> if a dossier document with the primary key could not be found
	*/
	public static DossierDocument fetchByPrimaryKey(long DossierDocumentId) {
		return getPersistence().fetchByPrimaryKey(DossierDocumentId);
	}

	public static java.util.Map<java.io.Serializable, DossierDocument> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier documents.
	*
	* @return the dossier documents
	*/
	public static List<DossierDocument> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<DossierDocument> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<DossierDocument> findAll(int start, int end,
		OrderByComparator<DossierDocument> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<DossierDocument> findAll(int start, int end,
		OrderByComparator<DossierDocument> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier documents from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier documents.
	*
	* @return the number of dossier documents
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DossierDocumentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierDocumentPersistence, DossierDocumentPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierDocumentPersistence.class);

		ServiceTracker<DossierDocumentPersistence, DossierDocumentPersistence> serviceTracker =
			new ServiceTracker<DossierDocumentPersistence, DossierDocumentPersistence>(bundle.getBundleContext(),
				DossierDocumentPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}