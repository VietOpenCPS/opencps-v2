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

package org.mobilink.backend.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.mobilink.backend.usermgt.model.PartnerFile;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the partner file service. This utility wraps {@link org.mobilink.backend.usermgt.service.persistence.impl.PartnerFilePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see PartnerFilePersistence
 * @see org.mobilink.backend.usermgt.service.persistence.impl.PartnerFilePersistenceImpl
 * @generated
 */
@ProviderType
public class PartnerFileUtil {
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
	public static void clearCache(PartnerFile partnerFile) {
		getPersistence().clearCache(partnerFile);
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
	public static List<PartnerFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PartnerFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PartnerFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PartnerFile> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PartnerFile update(PartnerFile partnerFile) {
		return getPersistence().update(partnerFile);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PartnerFile update(PartnerFile partnerFile,
		ServiceContext serviceContext) {
		return getPersistence().update(partnerFile, serviceContext);
	}

	/**
	* Returns all the partner files where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching partner files
	*/
	public static List<PartnerFile> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the partner files where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of partner files
	* @param end the upper bound of the range of partner files (not inclusive)
	* @return the range of matching partner files
	*/
	public static List<PartnerFile> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the partner files where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of partner files
	* @param end the upper bound of the range of partner files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner files
	*/
	public static List<PartnerFile> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<PartnerFile> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the partner files where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of partner files
	* @param end the upper bound of the range of partner files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching partner files
	*/
	public static List<PartnerFile> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<PartnerFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first partner file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner file
	* @throws NoSuchPartnerFileException if a matching partner file could not be found
	*/
	public static PartnerFile findByUuid_First(java.lang.String uuid,
		OrderByComparator<PartnerFile> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchPartnerFileException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first partner file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner file, or <code>null</code> if a matching partner file could not be found
	*/
	public static PartnerFile fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<PartnerFile> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last partner file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner file
	* @throws NoSuchPartnerFileException if a matching partner file could not be found
	*/
	public static PartnerFile findByUuid_Last(java.lang.String uuid,
		OrderByComparator<PartnerFile> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchPartnerFileException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last partner file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner file, or <code>null</code> if a matching partner file could not be found
	*/
	public static PartnerFile fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<PartnerFile> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the partner files before and after the current partner file in the ordered set where uuid = &#63;.
	*
	* @param partnerFileId the primary key of the current partner file
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner file
	* @throws NoSuchPartnerFileException if a partner file with the primary key could not be found
	*/
	public static PartnerFile[] findByUuid_PrevAndNext(long partnerFileId,
		java.lang.String uuid, OrderByComparator<PartnerFile> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchPartnerFileException {
		return getPersistence()
				   .findByUuid_PrevAndNext(partnerFileId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the partner files where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of partner files where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching partner files
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the partner file where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPartnerFileException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching partner file
	* @throws NoSuchPartnerFileException if a matching partner file could not be found
	*/
	public static PartnerFile findByUUID_G(java.lang.String uuid, long groupId)
		throws org.mobilink.backend.usermgt.exception.NoSuchPartnerFileException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the partner file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching partner file, or <code>null</code> if a matching partner file could not be found
	*/
	public static PartnerFile fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the partner file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching partner file, or <code>null</code> if a matching partner file could not be found
	*/
	public static PartnerFile fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the partner file where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the partner file that was removed
	*/
	public static PartnerFile removeByUUID_G(java.lang.String uuid, long groupId)
		throws org.mobilink.backend.usermgt.exception.NoSuchPartnerFileException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of partner files where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching partner files
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the partner files where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching partner files
	*/
	public static List<PartnerFile> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the partner files where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of partner files
	* @param end the upper bound of the range of partner files (not inclusive)
	* @return the range of matching partner files
	*/
	public static List<PartnerFile> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the partner files where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of partner files
	* @param end the upper bound of the range of partner files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner files
	*/
	public static List<PartnerFile> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<PartnerFile> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the partner files where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of partner files
	* @param end the upper bound of the range of partner files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching partner files
	*/
	public static List<PartnerFile> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<PartnerFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first partner file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner file
	* @throws NoSuchPartnerFileException if a matching partner file could not be found
	*/
	public static PartnerFile findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<PartnerFile> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchPartnerFileException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first partner file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner file, or <code>null</code> if a matching partner file could not be found
	*/
	public static PartnerFile fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<PartnerFile> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last partner file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner file
	* @throws NoSuchPartnerFileException if a matching partner file could not be found
	*/
	public static PartnerFile findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<PartnerFile> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchPartnerFileException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last partner file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner file, or <code>null</code> if a matching partner file could not be found
	*/
	public static PartnerFile fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<PartnerFile> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the partner files before and after the current partner file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param partnerFileId the primary key of the current partner file
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner file
	* @throws NoSuchPartnerFileException if a partner file with the primary key could not be found
	*/
	public static PartnerFile[] findByUuid_C_PrevAndNext(long partnerFileId,
		java.lang.String uuid, long companyId,
		OrderByComparator<PartnerFile> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchPartnerFileException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(partnerFileId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the partner files where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of partner files where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching partner files
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the partner file in the entity cache if it is enabled.
	*
	* @param partnerFile the partner file
	*/
	public static void cacheResult(PartnerFile partnerFile) {
		getPersistence().cacheResult(partnerFile);
	}

	/**
	* Caches the partner files in the entity cache if it is enabled.
	*
	* @param partnerFiles the partner files
	*/
	public static void cacheResult(List<PartnerFile> partnerFiles) {
		getPersistence().cacheResult(partnerFiles);
	}

	/**
	* Creates a new partner file with the primary key. Does not add the partner file to the database.
	*
	* @param partnerFileId the primary key for the new partner file
	* @return the new partner file
	*/
	public static PartnerFile create(long partnerFileId) {
		return getPersistence().create(partnerFileId);
	}

	/**
	* Removes the partner file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerFileId the primary key of the partner file
	* @return the partner file that was removed
	* @throws NoSuchPartnerFileException if a partner file with the primary key could not be found
	*/
	public static PartnerFile remove(long partnerFileId)
		throws org.mobilink.backend.usermgt.exception.NoSuchPartnerFileException {
		return getPersistence().remove(partnerFileId);
	}

	public static PartnerFile updateImpl(PartnerFile partnerFile) {
		return getPersistence().updateImpl(partnerFile);
	}

	/**
	* Returns the partner file with the primary key or throws a {@link NoSuchPartnerFileException} if it could not be found.
	*
	* @param partnerFileId the primary key of the partner file
	* @return the partner file
	* @throws NoSuchPartnerFileException if a partner file with the primary key could not be found
	*/
	public static PartnerFile findByPrimaryKey(long partnerFileId)
		throws org.mobilink.backend.usermgt.exception.NoSuchPartnerFileException {
		return getPersistence().findByPrimaryKey(partnerFileId);
	}

	/**
	* Returns the partner file with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param partnerFileId the primary key of the partner file
	* @return the partner file, or <code>null</code> if a partner file with the primary key could not be found
	*/
	public static PartnerFile fetchByPrimaryKey(long partnerFileId) {
		return getPersistence().fetchByPrimaryKey(partnerFileId);
	}

	public static java.util.Map<java.io.Serializable, PartnerFile> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the partner files.
	*
	* @return the partner files
	*/
	public static List<PartnerFile> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the partner files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner files
	* @param end the upper bound of the range of partner files (not inclusive)
	* @return the range of partner files
	*/
	public static List<PartnerFile> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the partner files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner files
	* @param end the upper bound of the range of partner files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of partner files
	*/
	public static List<PartnerFile> findAll(int start, int end,
		OrderByComparator<PartnerFile> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the partner files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner files
	* @param end the upper bound of the range of partner files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of partner files
	*/
	public static List<PartnerFile> findAll(int start, int end,
		OrderByComparator<PartnerFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the partner files from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of partner files.
	*
	* @return the number of partner files
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PartnerFilePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PartnerFilePersistence, PartnerFilePersistence> _serviceTracker =
		ServiceTrackerFactory.open(PartnerFilePersistence.class);
}