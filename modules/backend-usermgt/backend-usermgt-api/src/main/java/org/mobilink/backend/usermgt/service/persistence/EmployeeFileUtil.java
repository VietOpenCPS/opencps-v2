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

import org.mobilink.backend.usermgt.model.EmployeeFile;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the employee file service. This utility wraps {@link org.mobilink.backend.usermgt.service.persistence.impl.EmployeeFilePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see EmployeeFilePersistence
 * @see org.mobilink.backend.usermgt.service.persistence.impl.EmployeeFilePersistenceImpl
 * @generated
 */
@ProviderType
public class EmployeeFileUtil {
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
	public static void clearCache(EmployeeFile employeeFile) {
		getPersistence().clearCache(employeeFile);
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
	public static List<EmployeeFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EmployeeFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EmployeeFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EmployeeFile> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EmployeeFile update(EmployeeFile employeeFile) {
		return getPersistence().update(employeeFile);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EmployeeFile update(EmployeeFile employeeFile,
		ServiceContext serviceContext) {
		return getPersistence().update(employeeFile, serviceContext);
	}

	/**
	* Returns all the employee files where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching employee files
	*/
	public static List<EmployeeFile> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the employee files where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of employee files
	* @param end the upper bound of the range of employee files (not inclusive)
	* @return the range of matching employee files
	*/
	public static List<EmployeeFile> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the employee files where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of employee files
	* @param end the upper bound of the range of employee files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching employee files
	*/
	public static List<EmployeeFile> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<EmployeeFile> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the employee files where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of employee files
	* @param end the upper bound of the range of employee files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching employee files
	*/
	public static List<EmployeeFile> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<EmployeeFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first employee file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee file
	* @throws NoSuchEmployeeFileException if a matching employee file could not be found
	*/
	public static EmployeeFile findByUuid_First(java.lang.String uuid,
		OrderByComparator<EmployeeFile> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeFileException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first employee file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	public static EmployeeFile fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<EmployeeFile> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last employee file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee file
	* @throws NoSuchEmployeeFileException if a matching employee file could not be found
	*/
	public static EmployeeFile findByUuid_Last(java.lang.String uuid,
		OrderByComparator<EmployeeFile> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeFileException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last employee file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	public static EmployeeFile fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<EmployeeFile> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the employee files before and after the current employee file in the ordered set where uuid = &#63;.
	*
	* @param employeeFileId the primary key of the current employee file
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee file
	* @throws NoSuchEmployeeFileException if a employee file with the primary key could not be found
	*/
	public static EmployeeFile[] findByUuid_PrevAndNext(long employeeFileId,
		java.lang.String uuid, OrderByComparator<EmployeeFile> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeFileException {
		return getPersistence()
				   .findByUuid_PrevAndNext(employeeFileId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the employee files where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of employee files where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching employee files
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the employee file where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEmployeeFileException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching employee file
	* @throws NoSuchEmployeeFileException if a matching employee file could not be found
	*/
	public static EmployeeFile findByUUID_G(java.lang.String uuid, long groupId)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeFileException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the employee file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	public static EmployeeFile fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the employee file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	public static EmployeeFile fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the employee file where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the employee file that was removed
	*/
	public static EmployeeFile removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeFileException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of employee files where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching employee files
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the employee files where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching employee files
	*/
	public static List<EmployeeFile> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the employee files where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of employee files
	* @param end the upper bound of the range of employee files (not inclusive)
	* @return the range of matching employee files
	*/
	public static List<EmployeeFile> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the employee files where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of employee files
	* @param end the upper bound of the range of employee files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching employee files
	*/
	public static List<EmployeeFile> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<EmployeeFile> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the employee files where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of employee files
	* @param end the upper bound of the range of employee files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching employee files
	*/
	public static List<EmployeeFile> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<EmployeeFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first employee file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee file
	* @throws NoSuchEmployeeFileException if a matching employee file could not be found
	*/
	public static EmployeeFile findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<EmployeeFile> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeFileException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first employee file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	public static EmployeeFile fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<EmployeeFile> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last employee file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee file
	* @throws NoSuchEmployeeFileException if a matching employee file could not be found
	*/
	public static EmployeeFile findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<EmployeeFile> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeFileException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last employee file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	public static EmployeeFile fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<EmployeeFile> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the employee files before and after the current employee file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param employeeFileId the primary key of the current employee file
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee file
	* @throws NoSuchEmployeeFileException if a employee file with the primary key could not be found
	*/
	public static EmployeeFile[] findByUuid_C_PrevAndNext(long employeeFileId,
		java.lang.String uuid, long companyId,
		OrderByComparator<EmployeeFile> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeFileException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(employeeFileId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the employee files where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of employee files where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching employee files
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the employee file in the entity cache if it is enabled.
	*
	* @param employeeFile the employee file
	*/
	public static void cacheResult(EmployeeFile employeeFile) {
		getPersistence().cacheResult(employeeFile);
	}

	/**
	* Caches the employee files in the entity cache if it is enabled.
	*
	* @param employeeFiles the employee files
	*/
	public static void cacheResult(List<EmployeeFile> employeeFiles) {
		getPersistence().cacheResult(employeeFiles);
	}

	/**
	* Creates a new employee file with the primary key. Does not add the employee file to the database.
	*
	* @param employeeFileId the primary key for the new employee file
	* @return the new employee file
	*/
	public static EmployeeFile create(long employeeFileId) {
		return getPersistence().create(employeeFileId);
	}

	/**
	* Removes the employee file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeFileId the primary key of the employee file
	* @return the employee file that was removed
	* @throws NoSuchEmployeeFileException if a employee file with the primary key could not be found
	*/
	public static EmployeeFile remove(long employeeFileId)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeFileException {
		return getPersistence().remove(employeeFileId);
	}

	public static EmployeeFile updateImpl(EmployeeFile employeeFile) {
		return getPersistence().updateImpl(employeeFile);
	}

	/**
	* Returns the employee file with the primary key or throws a {@link NoSuchEmployeeFileException} if it could not be found.
	*
	* @param employeeFileId the primary key of the employee file
	* @return the employee file
	* @throws NoSuchEmployeeFileException if a employee file with the primary key could not be found
	*/
	public static EmployeeFile findByPrimaryKey(long employeeFileId)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeFileException {
		return getPersistence().findByPrimaryKey(employeeFileId);
	}

	/**
	* Returns the employee file with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param employeeFileId the primary key of the employee file
	* @return the employee file, or <code>null</code> if a employee file with the primary key could not be found
	*/
	public static EmployeeFile fetchByPrimaryKey(long employeeFileId) {
		return getPersistence().fetchByPrimaryKey(employeeFileId);
	}

	public static java.util.Map<java.io.Serializable, EmployeeFile> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the employee files.
	*
	* @return the employee files
	*/
	public static List<EmployeeFile> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the employee files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employee files
	* @param end the upper bound of the range of employee files (not inclusive)
	* @return the range of employee files
	*/
	public static List<EmployeeFile> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the employee files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employee files
	* @param end the upper bound of the range of employee files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of employee files
	*/
	public static List<EmployeeFile> findAll(int start, int end,
		OrderByComparator<EmployeeFile> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the employee files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employee files
	* @param end the upper bound of the range of employee files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of employee files
	*/
	public static List<EmployeeFile> findAll(int start, int end,
		OrderByComparator<EmployeeFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the employee files from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of employee files.
	*
	* @return the number of employee files
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static EmployeeFilePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EmployeeFilePersistence, EmployeeFilePersistence> _serviceTracker =
		ServiceTrackerFactory.open(EmployeeFilePersistence.class);
}