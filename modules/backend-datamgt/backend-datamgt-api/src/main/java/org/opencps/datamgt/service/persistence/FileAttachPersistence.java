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

package org.opencps.datamgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.datamgt.exception.NoSuchFileAttachException;
import org.opencps.datamgt.model.FileAttach;

/**
 * The persistence interface for the file attach service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.datamgt.service.persistence.impl.FileAttachPersistenceImpl
 * @see FileAttachUtil
 * @generated
 */
@ProviderType
public interface FileAttachPersistence extends BasePersistence<FileAttach> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FileAttachUtil} to access the file attach persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	* @return the matching file attachs
	*/
	public java.util.List<FileAttach> findByF_docFileId(long groupId,
		String className, String classPK, long docFileId);

	/**
	* Returns a range of all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileAttachModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	* @param start the lower bound of the range of file attachs
	* @param end the upper bound of the range of file attachs (not inclusive)
	* @return the range of matching file attachs
	*/
	public java.util.List<FileAttach> findByF_docFileId(long groupId,
		String className, String classPK, long docFileId, int start, int end);

	/**
	* Returns an ordered range of all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileAttachModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	* @param start the lower bound of the range of file attachs
	* @param end the upper bound of the range of file attachs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching file attachs
	*/
	public java.util.List<FileAttach> findByF_docFileId(long groupId,
		String className, String classPK, long docFileId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator);

	/**
	* Returns an ordered range of all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileAttachModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	* @param start the lower bound of the range of file attachs
	* @param end the upper bound of the range of file attachs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching file attachs
	*/
	public java.util.List<FileAttach> findByF_docFileId(long groupId,
		String className, String classPK, long docFileId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first file attach in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file attach
	* @throws NoSuchFileAttachException if a matching file attach could not be found
	*/
	public FileAttach findByF_docFileId_First(long groupId, String className,
		String classPK, long docFileId,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator)
		throws NoSuchFileAttachException;

	/**
	* Returns the first file attach in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file attach, or <code>null</code> if a matching file attach could not be found
	*/
	public FileAttach fetchByF_docFileId_First(long groupId, String className,
		String classPK, long docFileId,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator);

	/**
	* Returns the last file attach in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file attach
	* @throws NoSuchFileAttachException if a matching file attach could not be found
	*/
	public FileAttach findByF_docFileId_Last(long groupId, String className,
		String classPK, long docFileId,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator)
		throws NoSuchFileAttachException;

	/**
	* Returns the last file attach in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file attach, or <code>null</code> if a matching file attach could not be found
	*/
	public FileAttach fetchByF_docFileId_Last(long groupId, String className,
		String classPK, long docFileId,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator);

	/**
	* Returns the file attachs before and after the current file attach in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63;.
	*
	* @param fileAttachId the primary key of the current file attach
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next file attach
	* @throws NoSuchFileAttachException if a file attach with the primary key could not be found
	*/
	public FileAttach[] findByF_docFileId_PrevAndNext(long fileAttachId,
		long groupId, String className, String classPK, long docFileId,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator)
		throws NoSuchFileAttachException;

	/**
	* Removes all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	*/
	public void removeByF_docFileId(long groupId, String className,
		String classPK, long docFileId);

	/**
	* Returns the number of file attachs where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	* @return the number of matching file attachs
	*/
	public int countByF_docFileId(long groupId, String className,
		String classPK, long docFileId);

	/**
	* Returns all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the matching file attachs
	*/
	public java.util.List<FileAttach> findByF_className_classPK(long groupId,
		String className, String classPK);

	/**
	* Returns a range of all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileAttachModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param start the lower bound of the range of file attachs
	* @param end the upper bound of the range of file attachs (not inclusive)
	* @return the range of matching file attachs
	*/
	public java.util.List<FileAttach> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end);

	/**
	* Returns an ordered range of all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileAttachModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param start the lower bound of the range of file attachs
	* @param end the upper bound of the range of file attachs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching file attachs
	*/
	public java.util.List<FileAttach> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator);

	/**
	* Returns an ordered range of all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileAttachModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param start the lower bound of the range of file attachs
	* @param end the upper bound of the range of file attachs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching file attachs
	*/
	public java.util.List<FileAttach> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first file attach in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file attach
	* @throws NoSuchFileAttachException if a matching file attach could not be found
	*/
	public FileAttach findByF_className_classPK_First(long groupId,
		String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator)
		throws NoSuchFileAttachException;

	/**
	* Returns the first file attach in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file attach, or <code>null</code> if a matching file attach could not be found
	*/
	public FileAttach fetchByF_className_classPK_First(long groupId,
		String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator);

	/**
	* Returns the last file attach in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file attach
	* @throws NoSuchFileAttachException if a matching file attach could not be found
	*/
	public FileAttach findByF_className_classPK_Last(long groupId,
		String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator)
		throws NoSuchFileAttachException;

	/**
	* Returns the last file attach in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file attach, or <code>null</code> if a matching file attach could not be found
	*/
	public FileAttach fetchByF_className_classPK_Last(long groupId,
		String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator);

	/**
	* Returns the file attachs before and after the current file attach in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param fileAttachId the primary key of the current file attach
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next file attach
	* @throws NoSuchFileAttachException if a file attach with the primary key could not be found
	*/
	public FileAttach[] findByF_className_classPK_PrevAndNext(
		long fileAttachId, long groupId, String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator)
		throws NoSuchFileAttachException;

	/**
	* Removes all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	*/
	public void removeByF_className_classPK(long groupId, String className,
		String classPK);

	/**
	* Returns the number of file attachs where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the number of matching file attachs
	*/
	public int countByF_className_classPK(long groupId, String className,
		String classPK);

	/**
	* Caches the file attach in the entity cache if it is enabled.
	*
	* @param fileAttach the file attach
	*/
	public void cacheResult(FileAttach fileAttach);

	/**
	* Caches the file attachs in the entity cache if it is enabled.
	*
	* @param fileAttachs the file attachs
	*/
	public void cacheResult(java.util.List<FileAttach> fileAttachs);

	/**
	* Creates a new file attach with the primary key. Does not add the file attach to the database.
	*
	* @param fileAttachId the primary key for the new file attach
	* @return the new file attach
	*/
	public FileAttach create(long fileAttachId);

	/**
	* Removes the file attach with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileAttachId the primary key of the file attach
	* @return the file attach that was removed
	* @throws NoSuchFileAttachException if a file attach with the primary key could not be found
	*/
	public FileAttach remove(long fileAttachId)
		throws NoSuchFileAttachException;

	public FileAttach updateImpl(FileAttach fileAttach);

	/**
	* Returns the file attach with the primary key or throws a {@link NoSuchFileAttachException} if it could not be found.
	*
	* @param fileAttachId the primary key of the file attach
	* @return the file attach
	* @throws NoSuchFileAttachException if a file attach with the primary key could not be found
	*/
	public FileAttach findByPrimaryKey(long fileAttachId)
		throws NoSuchFileAttachException;

	/**
	* Returns the file attach with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fileAttachId the primary key of the file attach
	* @return the file attach, or <code>null</code> if a file attach with the primary key could not be found
	*/
	public FileAttach fetchByPrimaryKey(long fileAttachId);

	@Override
	public java.util.Map<java.io.Serializable, FileAttach> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the file attachs.
	*
	* @return the file attachs
	*/
	public java.util.List<FileAttach> findAll();

	/**
	* Returns a range of all the file attachs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileAttachModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file attachs
	* @param end the upper bound of the range of file attachs (not inclusive)
	* @return the range of file attachs
	*/
	public java.util.List<FileAttach> findAll(int start, int end);

	/**
	* Returns an ordered range of all the file attachs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileAttachModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file attachs
	* @param end the upper bound of the range of file attachs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of file attachs
	*/
	public java.util.List<FileAttach> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator);

	/**
	* Returns an ordered range of all the file attachs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileAttachModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file attachs
	* @param end the upper bound of the range of file attachs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of file attachs
	*/
	public java.util.List<FileAttach> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileAttach> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the file attachs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of file attachs.
	*
	* @return the number of file attachs
	*/
	public int countAll();
}