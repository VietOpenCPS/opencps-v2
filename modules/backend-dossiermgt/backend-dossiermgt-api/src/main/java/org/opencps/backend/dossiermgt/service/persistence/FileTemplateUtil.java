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

package org.opencps.backend.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.backend.dossiermgt.model.FileTemplate;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the file template service. This utility wraps {@link org.opencps.backend.dossiermgt.service.persistence.impl.FileTemplatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see FileTemplatePersistence
 * @see org.opencps.backend.dossiermgt.service.persistence.impl.FileTemplatePersistenceImpl
 * @generated
 */
@ProviderType
public class FileTemplateUtil {
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
	public static void clearCache(FileTemplate fileTemplate) {
		getPersistence().clearCache(fileTemplate);
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
	public static List<FileTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FileTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FileTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FileTemplate> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FileTemplate update(FileTemplate fileTemplate) {
		return getPersistence().update(fileTemplate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FileTemplate update(FileTemplate fileTemplate,
		ServiceContext serviceContext) {
		return getPersistence().update(fileTemplate, serviceContext);
	}

	/**
	* Returns all the file templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching file templates
	*/
	public static List<FileTemplate> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the file templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @return the range of matching file templates
	*/
	public static List<FileTemplate> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the file templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching file templates
	*/
	public static List<FileTemplate> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<FileTemplate> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the file templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching file templates
	*/
	public static List<FileTemplate> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<FileTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file template
	* @throws NoSuchFileTemplateException if a matching file template could not be found
	*/
	public static FileTemplate findByUuid_First(java.lang.String uuid,
		OrderByComparator<FileTemplate> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchFileTemplateException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file template, or <code>null</code> if a matching file template could not be found
	*/
	public static FileTemplate fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<FileTemplate> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file template
	* @throws NoSuchFileTemplateException if a matching file template could not be found
	*/
	public static FileTemplate findByUuid_Last(java.lang.String uuid,
		OrderByComparator<FileTemplate> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchFileTemplateException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file template, or <code>null</code> if a matching file template could not be found
	*/
	public static FileTemplate fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<FileTemplate> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the file templates before and after the current file template in the ordered set where uuid = &#63;.
	*
	* @param fileTemplateId the primary key of the current file template
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next file template
	* @throws NoSuchFileTemplateException if a file template with the primary key could not be found
	*/
	public static FileTemplate[] findByUuid_PrevAndNext(long fileTemplateId,
		java.lang.String uuid, OrderByComparator<FileTemplate> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchFileTemplateException {
		return getPersistence()
				   .findByUuid_PrevAndNext(fileTemplateId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the file templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of file templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching file templates
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the file template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchFileTemplateException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching file template
	* @throws NoSuchFileTemplateException if a matching file template could not be found
	*/
	public static FileTemplate findByUUID_G(java.lang.String uuid, long groupId)
		throws org.opencps.backend.dossiermgt.exception.NoSuchFileTemplateException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the file template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching file template, or <code>null</code> if a matching file template could not be found
	*/
	public static FileTemplate fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the file template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching file template, or <code>null</code> if a matching file template could not be found
	*/
	public static FileTemplate fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the file template where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the file template that was removed
	*/
	public static FileTemplate removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.backend.dossiermgt.exception.NoSuchFileTemplateException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of file templates where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching file templates
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the file templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching file templates
	*/
	public static List<FileTemplate> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the file templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @return the range of matching file templates
	*/
	public static List<FileTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the file templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching file templates
	*/
	public static List<FileTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<FileTemplate> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the file templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching file templates
	*/
	public static List<FileTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<FileTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first file template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file template
	* @throws NoSuchFileTemplateException if a matching file template could not be found
	*/
	public static FileTemplate findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<FileTemplate> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchFileTemplateException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first file template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file template, or <code>null</code> if a matching file template could not be found
	*/
	public static FileTemplate fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<FileTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last file template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file template
	* @throws NoSuchFileTemplateException if a matching file template could not be found
	*/
	public static FileTemplate findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<FileTemplate> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchFileTemplateException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last file template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file template, or <code>null</code> if a matching file template could not be found
	*/
	public static FileTemplate fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<FileTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the file templates before and after the current file template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param fileTemplateId the primary key of the current file template
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next file template
	* @throws NoSuchFileTemplateException if a file template with the primary key could not be found
	*/
	public static FileTemplate[] findByUuid_C_PrevAndNext(long fileTemplateId,
		java.lang.String uuid, long companyId,
		OrderByComparator<FileTemplate> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchFileTemplateException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(fileTemplateId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the file templates where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of file templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching file templates
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the file template in the entity cache if it is enabled.
	*
	* @param fileTemplate the file template
	*/
	public static void cacheResult(FileTemplate fileTemplate) {
		getPersistence().cacheResult(fileTemplate);
	}

	/**
	* Caches the file templates in the entity cache if it is enabled.
	*
	* @param fileTemplates the file templates
	*/
	public static void cacheResult(List<FileTemplate> fileTemplates) {
		getPersistence().cacheResult(fileTemplates);
	}

	/**
	* Creates a new file template with the primary key. Does not add the file template to the database.
	*
	* @param fileTemplateId the primary key for the new file template
	* @return the new file template
	*/
	public static FileTemplate create(long fileTemplateId) {
		return getPersistence().create(fileTemplateId);
	}

	/**
	* Removes the file template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileTemplateId the primary key of the file template
	* @return the file template that was removed
	* @throws NoSuchFileTemplateException if a file template with the primary key could not be found
	*/
	public static FileTemplate remove(long fileTemplateId)
		throws org.opencps.backend.dossiermgt.exception.NoSuchFileTemplateException {
		return getPersistence().remove(fileTemplateId);
	}

	public static FileTemplate updateImpl(FileTemplate fileTemplate) {
		return getPersistence().updateImpl(fileTemplate);
	}

	/**
	* Returns the file template with the primary key or throws a {@link NoSuchFileTemplateException} if it could not be found.
	*
	* @param fileTemplateId the primary key of the file template
	* @return the file template
	* @throws NoSuchFileTemplateException if a file template with the primary key could not be found
	*/
	public static FileTemplate findByPrimaryKey(long fileTemplateId)
		throws org.opencps.backend.dossiermgt.exception.NoSuchFileTemplateException {
		return getPersistence().findByPrimaryKey(fileTemplateId);
	}

	/**
	* Returns the file template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fileTemplateId the primary key of the file template
	* @return the file template, or <code>null</code> if a file template with the primary key could not be found
	*/
	public static FileTemplate fetchByPrimaryKey(long fileTemplateId) {
		return getPersistence().fetchByPrimaryKey(fileTemplateId);
	}

	public static java.util.Map<java.io.Serializable, FileTemplate> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the file templates.
	*
	* @return the file templates
	*/
	public static List<FileTemplate> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the file templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @return the range of file templates
	*/
	public static List<FileTemplate> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the file templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of file templates
	*/
	public static List<FileTemplate> findAll(int start, int end,
		OrderByComparator<FileTemplate> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the file templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of file templates
	*/
	public static List<FileTemplate> findAll(int start, int end,
		OrderByComparator<FileTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the file templates from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of file templates.
	*
	* @return the number of file templates
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of service infos associated with the file template.
	*
	* @param pk the primary key of the file template
	* @return long[] of the primaryKeys of service infos associated with the file template
	*/
	public static long[] getServiceInfoPrimaryKeys(long pk) {
		return getPersistence().getServiceInfoPrimaryKeys(pk);
	}

	/**
	* Returns all the service infos associated with the file template.
	*
	* @param pk the primary key of the file template
	* @return the service infos associated with the file template
	*/
	public static List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfos(
		long pk) {
		return getPersistence().getServiceInfos(pk);
	}

	/**
	* Returns a range of all the service infos associated with the file template.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the file template
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @return the range of service infos associated with the file template
	*/
	public static List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfos(
		long pk, int start, int end) {
		return getPersistence().getServiceInfos(pk, start, end);
	}

	/**
	* Returns an ordered range of all the service infos associated with the file template.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the file template
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of service infos associated with the file template
	*/
	public static List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfos(
		long pk, int start, int end,
		OrderByComparator<org.opencps.backend.dossiermgt.model.ServiceInfo> orderByComparator) {
		return getPersistence()
				   .getServiceInfos(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of service infos associated with the file template.
	*
	* @param pk the primary key of the file template
	* @return the number of service infos associated with the file template
	*/
	public static int getServiceInfosSize(long pk) {
		return getPersistence().getServiceInfosSize(pk);
	}

	/**
	* Returns <code>true</code> if the service info is associated with the file template.
	*
	* @param pk the primary key of the file template
	* @param serviceInfoPK the primary key of the service info
	* @return <code>true</code> if the service info is associated with the file template; <code>false</code> otherwise
	*/
	public static boolean containsServiceInfo(long pk, long serviceInfoPK) {
		return getPersistence().containsServiceInfo(pk, serviceInfoPK);
	}

	/**
	* Returns <code>true</code> if the file template has any service infos associated with it.
	*
	* @param pk the primary key of the file template to check for associations with service infos
	* @return <code>true</code> if the file template has any service infos associated with it; <code>false</code> otherwise
	*/
	public static boolean containsServiceInfos(long pk) {
		return getPersistence().containsServiceInfos(pk);
	}

	/**
	* Adds an association between the file template and the service info. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfoPK the primary key of the service info
	*/
	public static void addServiceInfo(long pk, long serviceInfoPK) {
		getPersistence().addServiceInfo(pk, serviceInfoPK);
	}

	/**
	* Adds an association between the file template and the service info. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfo the service info
	*/
	public static void addServiceInfo(long pk,
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		getPersistence().addServiceInfo(pk, serviceInfo);
	}

	/**
	* Adds an association between the file template and the service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfoPKs the primary keys of the service infos
	*/
	public static void addServiceInfos(long pk, long[] serviceInfoPKs) {
		getPersistence().addServiceInfos(pk, serviceInfoPKs);
	}

	/**
	* Adds an association between the file template and the service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfos the service infos
	*/
	public static void addServiceInfos(long pk,
		List<org.opencps.backend.dossiermgt.model.ServiceInfo> serviceInfos) {
		getPersistence().addServiceInfos(pk, serviceInfos);
	}

	/**
	* Clears all associations between the file template and its service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template to clear the associated service infos from
	*/
	public static void clearServiceInfos(long pk) {
		getPersistence().clearServiceInfos(pk);
	}

	/**
	* Removes the association between the file template and the service info. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfoPK the primary key of the service info
	*/
	public static void removeServiceInfo(long pk, long serviceInfoPK) {
		getPersistence().removeServiceInfo(pk, serviceInfoPK);
	}

	/**
	* Removes the association between the file template and the service info. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfo the service info
	*/
	public static void removeServiceInfo(long pk,
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		getPersistence().removeServiceInfo(pk, serviceInfo);
	}

	/**
	* Removes the association between the file template and the service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfoPKs the primary keys of the service infos
	*/
	public static void removeServiceInfos(long pk, long[] serviceInfoPKs) {
		getPersistence().removeServiceInfos(pk, serviceInfoPKs);
	}

	/**
	* Removes the association between the file template and the service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfos the service infos
	*/
	public static void removeServiceInfos(long pk,
		List<org.opencps.backend.dossiermgt.model.ServiceInfo> serviceInfos) {
		getPersistence().removeServiceInfos(pk, serviceInfos);
	}

	/**
	* Sets the service infos associated with the file template, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfoPKs the primary keys of the service infos to be associated with the file template
	*/
	public static void setServiceInfos(long pk, long[] serviceInfoPKs) {
		getPersistence().setServiceInfos(pk, serviceInfoPKs);
	}

	/**
	* Sets the service infos associated with the file template, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfos the service infos to be associated with the file template
	*/
	public static void setServiceInfos(long pk,
		List<org.opencps.backend.dossiermgt.model.ServiceInfo> serviceInfos) {
		getPersistence().setServiceInfos(pk, serviceInfos);
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static FileTemplatePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FileTemplatePersistence, FileTemplatePersistence> _serviceTracker =
		ServiceTrackerFactory.open(FileTemplatePersistence.class);
}