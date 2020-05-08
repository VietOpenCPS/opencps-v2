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

package org.opencps.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.FileItem;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the file item service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.FileItemPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see FileItemPersistence
 * @see org.opencps.usermgt.service.persistence.impl.FileItemPersistenceImpl
 * @generated
 */
@ProviderType
public class FileItemUtil {
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
	public static void clearCache(FileItem fileItem) {
		getPersistence().clearCache(fileItem);
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
	public static List<FileItem> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FileItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FileItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FileItem> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FileItem update(FileItem fileItem) {
		return getPersistence().update(fileItem);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FileItem update(FileItem fileItem,
		ServiceContext serviceContext) {
		return getPersistence().update(fileItem, serviceContext);
	}

	/**
	* Returns all the file items where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching file items
	*/
	public static List<FileItem> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the file items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @return the range of matching file items
	*/
	public static List<FileItem> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the file items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching file items
	*/
	public static List<FileItem> findByUuid(String uuid, int start, int end,
		OrderByComparator<FileItem> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the file items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching file items
	*/
	public static List<FileItem> findByUuid(String uuid, int start, int end,
		OrderByComparator<FileItem> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first file item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file item
	* @throws NoSuchFileItemException if a matching file item could not be found
	*/
	public static FileItem findByUuid_First(String uuid,
		OrderByComparator<FileItem> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchFileItemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first file item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public static FileItem fetchByUuid_First(String uuid,
		OrderByComparator<FileItem> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last file item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file item
	* @throws NoSuchFileItemException if a matching file item could not be found
	*/
	public static FileItem findByUuid_Last(String uuid,
		OrderByComparator<FileItem> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchFileItemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last file item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public static FileItem fetchByUuid_Last(String uuid,
		OrderByComparator<FileItem> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the file items before and after the current file item in the ordered set where uuid = &#63;.
	*
	* @param fileItemId the primary key of the current file item
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next file item
	* @throws NoSuchFileItemException if a file item with the primary key could not be found
	*/
	public static FileItem[] findByUuid_PrevAndNext(long fileItemId,
		String uuid, OrderByComparator<FileItem> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchFileItemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(fileItemId, uuid, orderByComparator);
	}

	/**
	* Removes all the file items where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of file items where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching file items
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the file item where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchFileItemException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching file item
	* @throws NoSuchFileItemException if a matching file item could not be found
	*/
	public static FileItem findByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchFileItemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the file item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public static FileItem fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the file item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public static FileItem fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the file item where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the file item that was removed
	*/
	public static FileItem removeByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchFileItemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of file items where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching file items
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the file items where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching file items
	*/
	public static List<FileItem> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the file items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @return the range of matching file items
	*/
	public static List<FileItem> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the file items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching file items
	*/
	public static List<FileItem> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<FileItem> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the file items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching file items
	*/
	public static List<FileItem> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<FileItem> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first file item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file item
	* @throws NoSuchFileItemException if a matching file item could not be found
	*/
	public static FileItem findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<FileItem> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchFileItemException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first file item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public static FileItem fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<FileItem> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last file item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file item
	* @throws NoSuchFileItemException if a matching file item could not be found
	*/
	public static FileItem findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<FileItem> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchFileItemException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last file item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public static FileItem fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<FileItem> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the file items before and after the current file item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param fileItemId the primary key of the current file item
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next file item
	* @throws NoSuchFileItemException if a file item with the primary key could not be found
	*/
	public static FileItem[] findByUuid_C_PrevAndNext(long fileItemId,
		String uuid, long companyId,
		OrderByComparator<FileItem> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchFileItemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(fileItemId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the file items where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of file items where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching file items
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the file item in the entity cache if it is enabled.
	*
	* @param fileItem the file item
	*/
	public static void cacheResult(FileItem fileItem) {
		getPersistence().cacheResult(fileItem);
	}

	/**
	* Caches the file items in the entity cache if it is enabled.
	*
	* @param fileItems the file items
	*/
	public static void cacheResult(List<FileItem> fileItems) {
		getPersistence().cacheResult(fileItems);
	}

	/**
	* Creates a new file item with the primary key. Does not add the file item to the database.
	*
	* @param fileItemId the primary key for the new file item
	* @return the new file item
	*/
	public static FileItem create(long fileItemId) {
		return getPersistence().create(fileItemId);
	}

	/**
	* Removes the file item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileItemId the primary key of the file item
	* @return the file item that was removed
	* @throws NoSuchFileItemException if a file item with the primary key could not be found
	*/
	public static FileItem remove(long fileItemId)
		throws org.opencps.usermgt.exception.NoSuchFileItemException {
		return getPersistence().remove(fileItemId);
	}

	public static FileItem updateImpl(FileItem fileItem) {
		return getPersistence().updateImpl(fileItem);
	}

	/**
	* Returns the file item with the primary key or throws a {@link NoSuchFileItemException} if it could not be found.
	*
	* @param fileItemId the primary key of the file item
	* @return the file item
	* @throws NoSuchFileItemException if a file item with the primary key could not be found
	*/
	public static FileItem findByPrimaryKey(long fileItemId)
		throws org.opencps.usermgt.exception.NoSuchFileItemException {
		return getPersistence().findByPrimaryKey(fileItemId);
	}

	/**
	* Returns the file item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fileItemId the primary key of the file item
	* @return the file item, or <code>null</code> if a file item with the primary key could not be found
	*/
	public static FileItem fetchByPrimaryKey(long fileItemId) {
		return getPersistence().fetchByPrimaryKey(fileItemId);
	}

	public static java.util.Map<java.io.Serializable, FileItem> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the file items.
	*
	* @return the file items
	*/
	public static List<FileItem> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the file items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @return the range of file items
	*/
	public static List<FileItem> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the file items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of file items
	*/
	public static List<FileItem> findAll(int start, int end,
		OrderByComparator<FileItem> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the file items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of file items
	*/
	public static List<FileItem> findAll(int start, int end,
		OrderByComparator<FileItem> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the file items from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of file items.
	*
	* @return the number of file items
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static FileItemPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FileItemPersistence, FileItemPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(FileItemPersistence.class);

		ServiceTracker<FileItemPersistence, FileItemPersistence> serviceTracker = new ServiceTracker<FileItemPersistence, FileItemPersistence>(bundle.getBundleContext(),
				FileItemPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}