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

import org.opencps.dossiermgt.model.DocumentType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the document type service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DocumentTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DocumentTypePersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DocumentTypePersistenceImpl
 * @generated
 */
@ProviderType
public class DocumentTypeUtil {
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
	public static void clearCache(DocumentType documentType) {
		getPersistence().clearCache(documentType);
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
	public static List<DocumentType> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DocumentType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DocumentType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DocumentType> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DocumentType update(DocumentType documentType) {
		return getPersistence().update(documentType);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DocumentType update(DocumentType documentType,
		ServiceContext serviceContext) {
		return getPersistence().update(documentType, serviceContext);
	}

	/**
	* Returns all the document types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching document types
	*/
	public static List<DocumentType> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<DocumentType> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<DocumentType> findByUuid(String uuid, int start,
		int end, OrderByComparator<DocumentType> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<DocumentType> findByUuid(String uuid, int start,
		int end, OrderByComparator<DocumentType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first document type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document type
	* @throws NoSuchDocumentTypeException if a matching document type could not be found
	*/
	public static DocumentType findByUuid_First(String uuid,
		OrderByComparator<DocumentType> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDocumentTypeException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first document type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document type, or <code>null</code> if a matching document type could not be found
	*/
	public static DocumentType fetchByUuid_First(String uuid,
		OrderByComparator<DocumentType> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last document type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document type
	* @throws NoSuchDocumentTypeException if a matching document type could not be found
	*/
	public static DocumentType findByUuid_Last(String uuid,
		OrderByComparator<DocumentType> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDocumentTypeException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last document type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document type, or <code>null</code> if a matching document type could not be found
	*/
	public static DocumentType fetchByUuid_Last(String uuid,
		OrderByComparator<DocumentType> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the document types before and after the current document type in the ordered set where uuid = &#63;.
	*
	* @param DocumentTypeId the primary key of the current document type
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document type
	* @throws NoSuchDocumentTypeException if a document type with the primary key could not be found
	*/
	public static DocumentType[] findByUuid_PrevAndNext(long DocumentTypeId,
		String uuid, OrderByComparator<DocumentType> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDocumentTypeException {
		return getPersistence()
				   .findByUuid_PrevAndNext(DocumentTypeId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the document types where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of document types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching document types
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the document type where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDocumentTypeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching document type
	* @throws NoSuchDocumentTypeException if a matching document type could not be found
	*/
	public static DocumentType findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDocumentTypeException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the document type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching document type, or <code>null</code> if a matching document type could not be found
	*/
	public static DocumentType fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the document type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching document type, or <code>null</code> if a matching document type could not be found
	*/
	public static DocumentType fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the document type where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the document type that was removed
	*/
	public static DocumentType removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDocumentTypeException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of document types where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching document types
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the document type where groupId = &#63; and typeCode = &#63; or throws a {@link NoSuchDocumentTypeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the matching document type
	* @throws NoSuchDocumentTypeException if a matching document type could not be found
	*/
	public static DocumentType findByF_CODE(long groupId, String typeCode)
		throws org.opencps.dossiermgt.exception.NoSuchDocumentTypeException {
		return getPersistence().findByF_CODE(groupId, typeCode);
	}

	/**
	* Returns the document type where groupId = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the matching document type, or <code>null</code> if a matching document type could not be found
	*/
	public static DocumentType fetchByF_CODE(long groupId, String typeCode) {
		return getPersistence().fetchByF_CODE(groupId, typeCode);
	}

	/**
	* Returns the document type where groupId = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching document type, or <code>null</code> if a matching document type could not be found
	*/
	public static DocumentType fetchByF_CODE(long groupId, String typeCode,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_CODE(groupId, typeCode, retrieveFromCache);
	}

	/**
	* Removes the document type where groupId = &#63; and typeCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the document type that was removed
	*/
	public static DocumentType removeByF_CODE(long groupId, String typeCode)
		throws org.opencps.dossiermgt.exception.NoSuchDocumentTypeException {
		return getPersistence().removeByF_CODE(groupId, typeCode);
	}

	/**
	* Returns the number of document types where groupId = &#63; and typeCode = &#63;.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the number of matching document types
	*/
	public static int countByF_CODE(long groupId, String typeCode) {
		return getPersistence().countByF_CODE(groupId, typeCode);
	}

	/**
	* Caches the document type in the entity cache if it is enabled.
	*
	* @param documentType the document type
	*/
	public static void cacheResult(DocumentType documentType) {
		getPersistence().cacheResult(documentType);
	}

	/**
	* Caches the document types in the entity cache if it is enabled.
	*
	* @param documentTypes the document types
	*/
	public static void cacheResult(List<DocumentType> documentTypes) {
		getPersistence().cacheResult(documentTypes);
	}

	/**
	* Creates a new document type with the primary key. Does not add the document type to the database.
	*
	* @param DocumentTypeId the primary key for the new document type
	* @return the new document type
	*/
	public static DocumentType create(long DocumentTypeId) {
		return getPersistence().create(DocumentTypeId);
	}

	/**
	* Removes the document type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param DocumentTypeId the primary key of the document type
	* @return the document type that was removed
	* @throws NoSuchDocumentTypeException if a document type with the primary key could not be found
	*/
	public static DocumentType remove(long DocumentTypeId)
		throws org.opencps.dossiermgt.exception.NoSuchDocumentTypeException {
		return getPersistence().remove(DocumentTypeId);
	}

	public static DocumentType updateImpl(DocumentType documentType) {
		return getPersistence().updateImpl(documentType);
	}

	/**
	* Returns the document type with the primary key or throws a {@link NoSuchDocumentTypeException} if it could not be found.
	*
	* @param DocumentTypeId the primary key of the document type
	* @return the document type
	* @throws NoSuchDocumentTypeException if a document type with the primary key could not be found
	*/
	public static DocumentType findByPrimaryKey(long DocumentTypeId)
		throws org.opencps.dossiermgt.exception.NoSuchDocumentTypeException {
		return getPersistence().findByPrimaryKey(DocumentTypeId);
	}

	/**
	* Returns the document type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param DocumentTypeId the primary key of the document type
	* @return the document type, or <code>null</code> if a document type with the primary key could not be found
	*/
	public static DocumentType fetchByPrimaryKey(long DocumentTypeId) {
		return getPersistence().fetchByPrimaryKey(DocumentTypeId);
	}

	public static java.util.Map<java.io.Serializable, DocumentType> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the document types.
	*
	* @return the document types
	*/
	public static List<DocumentType> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<DocumentType> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<DocumentType> findAll(int start, int end,
		OrderByComparator<DocumentType> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<DocumentType> findAll(int start, int end,
		OrderByComparator<DocumentType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the document types from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of document types.
	*
	* @return the number of document types
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DocumentTypePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DocumentTypePersistence, DocumentTypePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DocumentTypePersistence.class);

		ServiceTracker<DocumentTypePersistence, DocumentTypePersistence> serviceTracker =
			new ServiceTracker<DocumentTypePersistence, DocumentTypePersistence>(bundle.getBundleContext(),
				DocumentTypePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}