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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.usermgt.exception.NoSuchEmployeeFileException;
import org.opencps.usermgt.model.EmployeeFile;

/**
 * The persistence interface for the employee file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.mobilink.backend.usermgt.service.persistence.impl.EmployeeFilePersistenceImpl
 * @see EmployeeFileUtil
 * @generated
 */
@ProviderType
public interface EmployeeFilePersistence extends BasePersistence<EmployeeFile> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EmployeeFileUtil} to access the employee file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the employee files where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching employee files
	*/
	public java.util.List<EmployeeFile> findByUuid(java.lang.String uuid);

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
	public java.util.List<EmployeeFile> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<EmployeeFile> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator);

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
	public java.util.List<EmployeeFile> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first employee file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee file
	* @throws NoSuchEmployeeFileException if a matching employee file could not be found
	*/
	public EmployeeFile findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator)
		throws NoSuchEmployeeFileException;

	/**
	* Returns the first employee file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	public EmployeeFile fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator);

	/**
	* Returns the last employee file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee file
	* @throws NoSuchEmployeeFileException if a matching employee file could not be found
	*/
	public EmployeeFile findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator)
		throws NoSuchEmployeeFileException;

	/**
	* Returns the last employee file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	public EmployeeFile fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator);

	/**
	* Returns the employee files before and after the current employee file in the ordered set where uuid = &#63;.
	*
	* @param employeeFileId the primary key of the current employee file
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee file
	* @throws NoSuchEmployeeFileException if a employee file with the primary key could not be found
	*/
	public EmployeeFile[] findByUuid_PrevAndNext(long employeeFileId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator)
		throws NoSuchEmployeeFileException;

	/**
	* Removes all the employee files where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of employee files where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching employee files
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the employee file where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEmployeeFileException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching employee file
	* @throws NoSuchEmployeeFileException if a matching employee file could not be found
	*/
	public EmployeeFile findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchEmployeeFileException;

	/**
	* Returns the employee file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	public EmployeeFile fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the employee file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	public EmployeeFile fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the employee file where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the employee file that was removed
	*/
	public EmployeeFile removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchEmployeeFileException;

	/**
	* Returns the number of employee files where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching employee files
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the employee files where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching employee files
	*/
	public java.util.List<EmployeeFile> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<EmployeeFile> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<EmployeeFile> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator);

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
	public java.util.List<EmployeeFile> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first employee file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee file
	* @throws NoSuchEmployeeFileException if a matching employee file could not be found
	*/
	public EmployeeFile findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator)
		throws NoSuchEmployeeFileException;

	/**
	* Returns the first employee file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	public EmployeeFile fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator);

	/**
	* Returns the last employee file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee file
	* @throws NoSuchEmployeeFileException if a matching employee file could not be found
	*/
	public EmployeeFile findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator)
		throws NoSuchEmployeeFileException;

	/**
	* Returns the last employee file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	public EmployeeFile fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator);

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
	public EmployeeFile[] findByUuid_C_PrevAndNext(long employeeFileId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator)
		throws NoSuchEmployeeFileException;

	/**
	* Removes all the employee files where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of employee files where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching employee files
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the employee file in the entity cache if it is enabled.
	*
	* @param employeeFile the employee file
	*/
	public void cacheResult(EmployeeFile employeeFile);

	/**
	* Caches the employee files in the entity cache if it is enabled.
	*
	* @param employeeFiles the employee files
	*/
	public void cacheResult(java.util.List<EmployeeFile> employeeFiles);

	/**
	* Creates a new employee file with the primary key. Does not add the employee file to the database.
	*
	* @param employeeFileId the primary key for the new employee file
	* @return the new employee file
	*/
	public EmployeeFile create(long employeeFileId);

	/**
	* Removes the employee file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeFileId the primary key of the employee file
	* @return the employee file that was removed
	* @throws NoSuchEmployeeFileException if a employee file with the primary key could not be found
	*/
	public EmployeeFile remove(long employeeFileId)
		throws NoSuchEmployeeFileException;

	public EmployeeFile updateImpl(EmployeeFile employeeFile);

	/**
	* Returns the employee file with the primary key or throws a {@link NoSuchEmployeeFileException} if it could not be found.
	*
	* @param employeeFileId the primary key of the employee file
	* @return the employee file
	* @throws NoSuchEmployeeFileException if a employee file with the primary key could not be found
	*/
	public EmployeeFile findByPrimaryKey(long employeeFileId)
		throws NoSuchEmployeeFileException;

	/**
	* Returns the employee file with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param employeeFileId the primary key of the employee file
	* @return the employee file, or <code>null</code> if a employee file with the primary key could not be found
	*/
	public EmployeeFile fetchByPrimaryKey(long employeeFileId);

	@Override
	public java.util.Map<java.io.Serializable, EmployeeFile> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the employee files.
	*
	* @return the employee files
	*/
	public java.util.List<EmployeeFile> findAll();

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
	public java.util.List<EmployeeFile> findAll(int start, int end);

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
	public java.util.List<EmployeeFile> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator);

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
	public java.util.List<EmployeeFile> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the employee files from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of employee files.
	*
	* @return the number of employee files
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}