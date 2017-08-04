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

import org.opencps.usermgt.exception.NoSuchPartnerFileException;
import org.opencps.usermgt.model.PartnerFile;

/**
 * The persistence interface for the partner file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.mobilink.backend.usermgt.service.persistence.impl.PartnerFilePersistenceImpl
 * @see PartnerFileUtil
 * @generated
 */
@ProviderType
public interface PartnerFilePersistence extends BasePersistence<PartnerFile> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PartnerFileUtil} to access the partner file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the partner files where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching partner files
	*/
	public java.util.List<PartnerFile> findByUuid(java.lang.String uuid);

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
	public java.util.List<PartnerFile> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<PartnerFile> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator);

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
	public java.util.List<PartnerFile> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first partner file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner file
	* @throws NoSuchPartnerFileException if a matching partner file could not be found
	*/
	public PartnerFile findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator)
		throws NoSuchPartnerFileException;

	/**
	* Returns the first partner file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner file, or <code>null</code> if a matching partner file could not be found
	*/
	public PartnerFile fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator);

	/**
	* Returns the last partner file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner file
	* @throws NoSuchPartnerFileException if a matching partner file could not be found
	*/
	public PartnerFile findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator)
		throws NoSuchPartnerFileException;

	/**
	* Returns the last partner file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner file, or <code>null</code> if a matching partner file could not be found
	*/
	public PartnerFile fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator);

	/**
	* Returns the partner files before and after the current partner file in the ordered set where uuid = &#63;.
	*
	* @param partnerFileId the primary key of the current partner file
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner file
	* @throws NoSuchPartnerFileException if a partner file with the primary key could not be found
	*/
	public PartnerFile[] findByUuid_PrevAndNext(long partnerFileId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator)
		throws NoSuchPartnerFileException;

	/**
	* Removes all the partner files where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of partner files where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching partner files
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the partner file where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPartnerFileException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching partner file
	* @throws NoSuchPartnerFileException if a matching partner file could not be found
	*/
	public PartnerFile findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPartnerFileException;

	/**
	* Returns the partner file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching partner file, or <code>null</code> if a matching partner file could not be found
	*/
	public PartnerFile fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the partner file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching partner file, or <code>null</code> if a matching partner file could not be found
	*/
	public PartnerFile fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the partner file where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the partner file that was removed
	*/
	public PartnerFile removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPartnerFileException;

	/**
	* Returns the number of partner files where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching partner files
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the partner files where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching partner files
	*/
	public java.util.List<PartnerFile> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<PartnerFile> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<PartnerFile> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator);

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
	public java.util.List<PartnerFile> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first partner file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner file
	* @throws NoSuchPartnerFileException if a matching partner file could not be found
	*/
	public PartnerFile findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator)
		throws NoSuchPartnerFileException;

	/**
	* Returns the first partner file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner file, or <code>null</code> if a matching partner file could not be found
	*/
	public PartnerFile fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator);

	/**
	* Returns the last partner file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner file
	* @throws NoSuchPartnerFileException if a matching partner file could not be found
	*/
	public PartnerFile findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator)
		throws NoSuchPartnerFileException;

	/**
	* Returns the last partner file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner file, or <code>null</code> if a matching partner file could not be found
	*/
	public PartnerFile fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator);

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
	public PartnerFile[] findByUuid_C_PrevAndNext(long partnerFileId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator)
		throws NoSuchPartnerFileException;

	/**
	* Removes all the partner files where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of partner files where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching partner files
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the partner file in the entity cache if it is enabled.
	*
	* @param partnerFile the partner file
	*/
	public void cacheResult(PartnerFile partnerFile);

	/**
	* Caches the partner files in the entity cache if it is enabled.
	*
	* @param partnerFiles the partner files
	*/
	public void cacheResult(java.util.List<PartnerFile> partnerFiles);

	/**
	* Creates a new partner file with the primary key. Does not add the partner file to the database.
	*
	* @param partnerFileId the primary key for the new partner file
	* @return the new partner file
	*/
	public PartnerFile create(long partnerFileId);

	/**
	* Removes the partner file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerFileId the primary key of the partner file
	* @return the partner file that was removed
	* @throws NoSuchPartnerFileException if a partner file with the primary key could not be found
	*/
	public PartnerFile remove(long partnerFileId)
		throws NoSuchPartnerFileException;

	public PartnerFile updateImpl(PartnerFile partnerFile);

	/**
	* Returns the partner file with the primary key or throws a {@link NoSuchPartnerFileException} if it could not be found.
	*
	* @param partnerFileId the primary key of the partner file
	* @return the partner file
	* @throws NoSuchPartnerFileException if a partner file with the primary key could not be found
	*/
	public PartnerFile findByPrimaryKey(long partnerFileId)
		throws NoSuchPartnerFileException;

	/**
	* Returns the partner file with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param partnerFileId the primary key of the partner file
	* @return the partner file, or <code>null</code> if a partner file with the primary key could not be found
	*/
	public PartnerFile fetchByPrimaryKey(long partnerFileId);

	@Override
	public java.util.Map<java.io.Serializable, PartnerFile> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the partner files.
	*
	* @return the partner files
	*/
	public java.util.List<PartnerFile> findAll();

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
	public java.util.List<PartnerFile> findAll(int start, int end);

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
	public java.util.List<PartnerFile> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator);

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
	public java.util.List<PartnerFile> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the partner files from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of partner files.
	*
	* @return the number of partner files
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}