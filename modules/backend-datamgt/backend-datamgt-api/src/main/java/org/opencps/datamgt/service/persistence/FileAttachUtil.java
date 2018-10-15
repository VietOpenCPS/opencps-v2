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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.datamgt.model.FileAttach;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the file attach service. This utility wraps {@link org.opencps.datamgt.service.persistence.impl.FileAttachPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see FileAttachPersistence
 * @see org.opencps.datamgt.service.persistence.impl.FileAttachPersistenceImpl
 * @generated
 */
@ProviderType
public class FileAttachUtil {
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
	public static void clearCache(FileAttach fileAttach) {
		getPersistence().clearCache(fileAttach);
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
	public static List<FileAttach> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FileAttach> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FileAttach> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FileAttach> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FileAttach update(FileAttach fileAttach) {
		return getPersistence().update(fileAttach);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FileAttach update(FileAttach fileAttach,
		ServiceContext serviceContext) {
		return getPersistence().update(fileAttach, serviceContext);
	}

	/**
	* Returns all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	* @return the matching file attachs
	*/
	public static List<FileAttach> findByF_docFileId(long groupId,
		String className, String classPK, long docFileId) {
		return getPersistence()
				   .findByF_docFileId(groupId, className, classPK, docFileId);
	}

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
	public static List<FileAttach> findByF_docFileId(long groupId,
		String className, String classPK, long docFileId, int start, int end) {
		return getPersistence()
				   .findByF_docFileId(groupId, className, classPK, docFileId,
			start, end);
	}

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
	public static List<FileAttach> findByF_docFileId(long groupId,
		String className, String classPK, long docFileId, int start, int end,
		OrderByComparator<FileAttach> orderByComparator) {
		return getPersistence()
				   .findByF_docFileId(groupId, className, classPK, docFileId,
			start, end, orderByComparator);
	}

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
	public static List<FileAttach> findByF_docFileId(long groupId,
		String className, String classPK, long docFileId, int start, int end,
		OrderByComparator<FileAttach> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_docFileId(groupId, className, classPK, docFileId,
			start, end, orderByComparator, retrieveFromCache);
	}

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
	public static FileAttach findByF_docFileId_First(long groupId,
		String className, String classPK, long docFileId,
		OrderByComparator<FileAttach> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchFileAttachException {
		return getPersistence()
				   .findByF_docFileId_First(groupId, className, classPK,
			docFileId, orderByComparator);
	}

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
	public static FileAttach fetchByF_docFileId_First(long groupId,
		String className, String classPK, long docFileId,
		OrderByComparator<FileAttach> orderByComparator) {
		return getPersistence()
				   .fetchByF_docFileId_First(groupId, className, classPK,
			docFileId, orderByComparator);
	}

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
	public static FileAttach findByF_docFileId_Last(long groupId,
		String className, String classPK, long docFileId,
		OrderByComparator<FileAttach> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchFileAttachException {
		return getPersistence()
				   .findByF_docFileId_Last(groupId, className, classPK,
			docFileId, orderByComparator);
	}

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
	public static FileAttach fetchByF_docFileId_Last(long groupId,
		String className, String classPK, long docFileId,
		OrderByComparator<FileAttach> orderByComparator) {
		return getPersistence()
				   .fetchByF_docFileId_Last(groupId, className, classPK,
			docFileId, orderByComparator);
	}

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
	public static FileAttach[] findByF_docFileId_PrevAndNext(
		long fileAttachId, long groupId, String className, String classPK,
		long docFileId, OrderByComparator<FileAttach> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchFileAttachException {
		return getPersistence()
				   .findByF_docFileId_PrevAndNext(fileAttachId, groupId,
			className, classPK, docFileId, orderByComparator);
	}

	/**
	* Removes all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	*/
	public static void removeByF_docFileId(long groupId, String className,
		String classPK, long docFileId) {
		getPersistence()
			.removeByF_docFileId(groupId, className, classPK, docFileId);
	}

	/**
	* Returns the number of file attachs where groupId = &#63; and className = &#63; and classPK = &#63; and docFileId = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param docFileId the doc file ID
	* @return the number of matching file attachs
	*/
	public static int countByF_docFileId(long groupId, String className,
		String classPK, long docFileId) {
		return getPersistence()
				   .countByF_docFileId(groupId, className, classPK, docFileId);
	}

	/**
	* Returns all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the matching file attachs
	*/
	public static List<FileAttach> findByF_className_classPK(long groupId,
		String className, String classPK) {
		return getPersistence()
				   .findByF_className_classPK(groupId, className, classPK);
	}

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
	public static List<FileAttach> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end) {
		return getPersistence()
				   .findByF_className_classPK(groupId, className, classPK,
			start, end);
	}

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
	public static List<FileAttach> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end,
		OrderByComparator<FileAttach> orderByComparator) {
		return getPersistence()
				   .findByF_className_classPK(groupId, className, classPK,
			start, end, orderByComparator);
	}

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
	public static List<FileAttach> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end,
		OrderByComparator<FileAttach> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_className_classPK(groupId, className, classPK,
			start, end, orderByComparator, retrieveFromCache);
	}

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
	public static FileAttach findByF_className_classPK_First(long groupId,
		String className, String classPK,
		OrderByComparator<FileAttach> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchFileAttachException {
		return getPersistence()
				   .findByF_className_classPK_First(groupId, className,
			classPK, orderByComparator);
	}

	/**
	* Returns the first file attach in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file attach, or <code>null</code> if a matching file attach could not be found
	*/
	public static FileAttach fetchByF_className_classPK_First(long groupId,
		String className, String classPK,
		OrderByComparator<FileAttach> orderByComparator) {
		return getPersistence()
				   .fetchByF_className_classPK_First(groupId, className,
			classPK, orderByComparator);
	}

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
	public static FileAttach findByF_className_classPK_Last(long groupId,
		String className, String classPK,
		OrderByComparator<FileAttach> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchFileAttachException {
		return getPersistence()
				   .findByF_className_classPK_Last(groupId, className, classPK,
			orderByComparator);
	}

	/**
	* Returns the last file attach in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file attach, or <code>null</code> if a matching file attach could not be found
	*/
	public static FileAttach fetchByF_className_classPK_Last(long groupId,
		String className, String classPK,
		OrderByComparator<FileAttach> orderByComparator) {
		return getPersistence()
				   .fetchByF_className_classPK_Last(groupId, className,
			classPK, orderByComparator);
	}

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
	public static FileAttach[] findByF_className_classPK_PrevAndNext(
		long fileAttachId, long groupId, String className, String classPK,
		OrderByComparator<FileAttach> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchFileAttachException {
		return getPersistence()
				   .findByF_className_classPK_PrevAndNext(fileAttachId,
			groupId, className, classPK, orderByComparator);
	}

	/**
	* Removes all the file attachs where groupId = &#63; and className = &#63; and classPK = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	*/
	public static void removeByF_className_classPK(long groupId,
		String className, String classPK) {
		getPersistence().removeByF_className_classPK(groupId, className, classPK);
	}

	/**
	* Returns the number of file attachs where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the number of matching file attachs
	*/
	public static int countByF_className_classPK(long groupId,
		String className, String classPK) {
		return getPersistence()
				   .countByF_className_classPK(groupId, className, classPK);
	}

	/**
	* Caches the file attach in the entity cache if it is enabled.
	*
	* @param fileAttach the file attach
	*/
	public static void cacheResult(FileAttach fileAttach) {
		getPersistence().cacheResult(fileAttach);
	}

	/**
	* Caches the file attachs in the entity cache if it is enabled.
	*
	* @param fileAttachs the file attachs
	*/
	public static void cacheResult(List<FileAttach> fileAttachs) {
		getPersistence().cacheResult(fileAttachs);
	}

	/**
	* Creates a new file attach with the primary key. Does not add the file attach to the database.
	*
	* @param fileAttachId the primary key for the new file attach
	* @return the new file attach
	*/
	public static FileAttach create(long fileAttachId) {
		return getPersistence().create(fileAttachId);
	}

	/**
	* Removes the file attach with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileAttachId the primary key of the file attach
	* @return the file attach that was removed
	* @throws NoSuchFileAttachException if a file attach with the primary key could not be found
	*/
	public static FileAttach remove(long fileAttachId)
		throws org.opencps.datamgt.exception.NoSuchFileAttachException {
		return getPersistence().remove(fileAttachId);
	}

	public static FileAttach updateImpl(FileAttach fileAttach) {
		return getPersistence().updateImpl(fileAttach);
	}

	/**
	* Returns the file attach with the primary key or throws a {@link NoSuchFileAttachException} if it could not be found.
	*
	* @param fileAttachId the primary key of the file attach
	* @return the file attach
	* @throws NoSuchFileAttachException if a file attach with the primary key could not be found
	*/
	public static FileAttach findByPrimaryKey(long fileAttachId)
		throws org.opencps.datamgt.exception.NoSuchFileAttachException {
		return getPersistence().findByPrimaryKey(fileAttachId);
	}

	/**
	* Returns the file attach with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fileAttachId the primary key of the file attach
	* @return the file attach, or <code>null</code> if a file attach with the primary key could not be found
	*/
	public static FileAttach fetchByPrimaryKey(long fileAttachId) {
		return getPersistence().fetchByPrimaryKey(fileAttachId);
	}

	public static java.util.Map<java.io.Serializable, FileAttach> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the file attachs.
	*
	* @return the file attachs
	*/
	public static List<FileAttach> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<FileAttach> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<FileAttach> findAll(int start, int end,
		OrderByComparator<FileAttach> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<FileAttach> findAll(int start, int end,
		OrderByComparator<FileAttach> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the file attachs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of file attachs.
	*
	* @return the number of file attachs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FileAttachPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FileAttachPersistence, FileAttachPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(FileAttachPersistence.class);

		ServiceTracker<FileAttachPersistence, FileAttachPersistence> serviceTracker =
			new ServiceTracker<FileAttachPersistence, FileAttachPersistence>(bundle.getBundleContext(),
				FileAttachPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}