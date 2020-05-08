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

import org.opencps.usermgt.exception.NoSuchFileItemException;
import org.opencps.usermgt.model.FileItem;

/**
 * The persistence interface for the file item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.FileItemPersistenceImpl
 * @see FileItemUtil
 * @generated
 */
@ProviderType
public interface FileItemPersistence extends BasePersistence<FileItem> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FileItemUtil} to access the file item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the file items where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching file items
	*/
	public java.util.List<FileItem> findByUuid(String uuid);

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
	public java.util.List<FileItem> findByUuid(String uuid, int start, int end);

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
	public java.util.List<FileItem> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator);

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
	public java.util.List<FileItem> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first file item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file item
	* @throws NoSuchFileItemException if a matching file item could not be found
	*/
	public FileItem findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator)
		throws NoSuchFileItemException;

	/**
	* Returns the first file item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public FileItem fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator);

	/**
	* Returns the last file item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file item
	* @throws NoSuchFileItemException if a matching file item could not be found
	*/
	public FileItem findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator)
		throws NoSuchFileItemException;

	/**
	* Returns the last file item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public FileItem fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator);

	/**
	* Returns the file items before and after the current file item in the ordered set where uuid = &#63;.
	*
	* @param fileItemId the primary key of the current file item
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next file item
	* @throws NoSuchFileItemException if a file item with the primary key could not be found
	*/
	public FileItem[] findByUuid_PrevAndNext(long fileItemId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator)
		throws NoSuchFileItemException;

	/**
	* Removes all the file items where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of file items where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching file items
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the file item where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchFileItemException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching file item
	* @throws NoSuchFileItemException if a matching file item could not be found
	*/
	public FileItem findByUUID_G(String uuid, long groupId)
		throws NoSuchFileItemException;

	/**
	* Returns the file item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public FileItem fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the file item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public FileItem fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the file item where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the file item that was removed
	*/
	public FileItem removeByUUID_G(String uuid, long groupId)
		throws NoSuchFileItemException;

	/**
	* Returns the number of file items where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching file items
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the file items where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching file items
	*/
	public java.util.List<FileItem> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<FileItem> findByUuid_C(String uuid, long companyId,
		int start, int end);

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
	public java.util.List<FileItem> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator);

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
	public java.util.List<FileItem> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first file item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file item
	* @throws NoSuchFileItemException if a matching file item could not be found
	*/
	public FileItem findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator)
		throws NoSuchFileItemException;

	/**
	* Returns the first file item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public FileItem fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator);

	/**
	* Returns the last file item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file item
	* @throws NoSuchFileItemException if a matching file item could not be found
	*/
	public FileItem findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator)
		throws NoSuchFileItemException;

	/**
	* Returns the last file item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public FileItem fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator);

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
	public FileItem[] findByUuid_C_PrevAndNext(long fileItemId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator)
		throws NoSuchFileItemException;

	/**
	* Removes all the file items where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of file items where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching file items
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the file item where groupId = &#63; and fileTemplateNo = &#63; or throws a {@link NoSuchFileItemException} if it could not be found.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @return the matching file item
	* @throws NoSuchFileItemException if a matching file item could not be found
	*/
	public FileItem findByG_FTN(long groupId, String fileTemplateNo)
		throws NoSuchFileItemException;

	/**
	* Returns the file item where groupId = &#63; and fileTemplateNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @return the matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public FileItem fetchByG_FTN(long groupId, String fileTemplateNo);

	/**
	* Returns the file item where groupId = &#63; and fileTemplateNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public FileItem fetchByG_FTN(long groupId, String fileTemplateNo,
		boolean retrieveFromCache);

	/**
	* Removes the file item where groupId = &#63; and fileTemplateNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @return the file item that was removed
	*/
	public FileItem removeByG_FTN(long groupId, String fileTemplateNo)
		throws NoSuchFileItemException;

	/**
	* Returns the number of file items where groupId = &#63; and fileTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @return the number of matching file items
	*/
	public int countByG_FTN(long groupId, String fileTemplateNo);

	/**
	* Returns all the file items where groupId = &#63; and fileTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @return the matching file items
	*/
	public java.util.List<FileItem> findByG_FTNS(long groupId,
		String fileTemplateNo);

	/**
	* Returns a range of all the file items where groupId = &#63; and fileTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @return the range of matching file items
	*/
	public java.util.List<FileItem> findByG_FTNS(long groupId,
		String fileTemplateNo, int start, int end);

	/**
	* Returns an ordered range of all the file items where groupId = &#63; and fileTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching file items
	*/
	public java.util.List<FileItem> findByG_FTNS(long groupId,
		String fileTemplateNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator);

	/**
	* Returns an ordered range of all the file items where groupId = &#63; and fileTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching file items
	*/
	public java.util.List<FileItem> findByG_FTNS(long groupId,
		String fileTemplateNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first file item in the ordered set where groupId = &#63; and fileTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file item
	* @throws NoSuchFileItemException if a matching file item could not be found
	*/
	public FileItem findByG_FTNS_First(long groupId, String fileTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator)
		throws NoSuchFileItemException;

	/**
	* Returns the first file item in the ordered set where groupId = &#63; and fileTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public FileItem fetchByG_FTNS_First(long groupId, String fileTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator);

	/**
	* Returns the last file item in the ordered set where groupId = &#63; and fileTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file item
	* @throws NoSuchFileItemException if a matching file item could not be found
	*/
	public FileItem findByG_FTNS_Last(long groupId, String fileTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator)
		throws NoSuchFileItemException;

	/**
	* Returns the last file item in the ordered set where groupId = &#63; and fileTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public FileItem fetchByG_FTNS_Last(long groupId, String fileTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator);

	/**
	* Returns the file items before and after the current file item in the ordered set where groupId = &#63; and fileTemplateNo = &#63;.
	*
	* @param fileItemId the primary key of the current file item
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next file item
	* @throws NoSuchFileItemException if a file item with the primary key could not be found
	*/
	public FileItem[] findByG_FTNS_PrevAndNext(long fileItemId, long groupId,
		String fileTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator)
		throws NoSuchFileItemException;

	/**
	* Returns all the file items where groupId = &#63; and fileTemplateNo = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param fileTemplateNos the file template nos
	* @return the matching file items
	*/
	public java.util.List<FileItem> findByG_FTNS(long groupId,
		String[] fileTemplateNos);

	/**
	* Returns a range of all the file items where groupId = &#63; and fileTemplateNo = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param fileTemplateNos the file template nos
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @return the range of matching file items
	*/
	public java.util.List<FileItem> findByG_FTNS(long groupId,
		String[] fileTemplateNos, int start, int end);

	/**
	* Returns an ordered range of all the file items where groupId = &#63; and fileTemplateNo = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param fileTemplateNos the file template nos
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching file items
	*/
	public java.util.List<FileItem> findByG_FTNS(long groupId,
		String[] fileTemplateNos, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator);

	/**
	* Returns an ordered range of all the file items where groupId = &#63; and fileTemplateNo = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching file items
	*/
	public java.util.List<FileItem> findByG_FTNS(long groupId,
		String[] fileTemplateNos, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the file items where groupId = &#63; and fileTemplateNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	*/
	public void removeByG_FTNS(long groupId, String fileTemplateNo);

	/**
	* Returns the number of file items where groupId = &#63; and fileTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @return the number of matching file items
	*/
	public int countByG_FTNS(long groupId, String fileTemplateNo);

	/**
	* Returns the number of file items where groupId = &#63; and fileTemplateNo = any &#63;.
	*
	* @param groupId the group ID
	* @param fileTemplateNos the file template nos
	* @return the number of matching file items
	*/
	public int countByG_FTNS(long groupId, String[] fileTemplateNos);

	/**
	* Caches the file item in the entity cache if it is enabled.
	*
	* @param fileItem the file item
	*/
	public void cacheResult(FileItem fileItem);

	/**
	* Caches the file items in the entity cache if it is enabled.
	*
	* @param fileItems the file items
	*/
	public void cacheResult(java.util.List<FileItem> fileItems);

	/**
	* Creates a new file item with the primary key. Does not add the file item to the database.
	*
	* @param fileItemId the primary key for the new file item
	* @return the new file item
	*/
	public FileItem create(long fileItemId);

	/**
	* Removes the file item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileItemId the primary key of the file item
	* @return the file item that was removed
	* @throws NoSuchFileItemException if a file item with the primary key could not be found
	*/
	public FileItem remove(long fileItemId) throws NoSuchFileItemException;

	public FileItem updateImpl(FileItem fileItem);

	/**
	* Returns the file item with the primary key or throws a {@link NoSuchFileItemException} if it could not be found.
	*
	* @param fileItemId the primary key of the file item
	* @return the file item
	* @throws NoSuchFileItemException if a file item with the primary key could not be found
	*/
	public FileItem findByPrimaryKey(long fileItemId)
		throws NoSuchFileItemException;

	/**
	* Returns the file item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fileItemId the primary key of the file item
	* @return the file item, or <code>null</code> if a file item with the primary key could not be found
	*/
	public FileItem fetchByPrimaryKey(long fileItemId);

	@Override
	public java.util.Map<java.io.Serializable, FileItem> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the file items.
	*
	* @return the file items
	*/
	public java.util.List<FileItem> findAll();

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
	public java.util.List<FileItem> findAll(int start, int end);

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
	public java.util.List<FileItem> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator);

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
	public java.util.List<FileItem> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the file items from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of file items.
	*
	* @return the number of file items
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}