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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.backend.dossiermgt.exception.NoSuchFileTemplateException;
import org.opencps.backend.dossiermgt.model.FileTemplate;

/**
 * The persistence interface for the file template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.backend.dossiermgt.service.persistence.impl.FileTemplatePersistenceImpl
 * @see FileTemplateUtil
 * @generated
 */
@ProviderType
public interface FileTemplatePersistence extends BasePersistence<FileTemplate> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FileTemplateUtil} to access the file template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the file templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching file templates
	*/
	public java.util.List<FileTemplate> findByUuid(java.lang.String uuid);

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
	public java.util.List<FileTemplate> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<FileTemplate> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator);

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
	public java.util.List<FileTemplate> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file template
	* @throws NoSuchFileTemplateException if a matching file template could not be found
	*/
	public FileTemplate findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator)
		throws NoSuchFileTemplateException;

	/**
	* Returns the first file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file template, or <code>null</code> if a matching file template could not be found
	*/
	public FileTemplate fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator);

	/**
	* Returns the last file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file template
	* @throws NoSuchFileTemplateException if a matching file template could not be found
	*/
	public FileTemplate findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator)
		throws NoSuchFileTemplateException;

	/**
	* Returns the last file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file template, or <code>null</code> if a matching file template could not be found
	*/
	public FileTemplate fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator);

	/**
	* Returns the file templates before and after the current file template in the ordered set where uuid = &#63;.
	*
	* @param fileTemplateId the primary key of the current file template
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next file template
	* @throws NoSuchFileTemplateException if a file template with the primary key could not be found
	*/
	public FileTemplate[] findByUuid_PrevAndNext(long fileTemplateId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator)
		throws NoSuchFileTemplateException;

	/**
	* Removes all the file templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of file templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching file templates
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the file template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchFileTemplateException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching file template
	* @throws NoSuchFileTemplateException if a matching file template could not be found
	*/
	public FileTemplate findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchFileTemplateException;

	/**
	* Returns the file template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching file template, or <code>null</code> if a matching file template could not be found
	*/
	public FileTemplate fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the file template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching file template, or <code>null</code> if a matching file template could not be found
	*/
	public FileTemplate fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the file template where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the file template that was removed
	*/
	public FileTemplate removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchFileTemplateException;

	/**
	* Returns the number of file templates where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching file templates
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the file templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching file templates
	*/
	public java.util.List<FileTemplate> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<FileTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<FileTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator);

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
	public java.util.List<FileTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first file template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file template
	* @throws NoSuchFileTemplateException if a matching file template could not be found
	*/
	public FileTemplate findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator)
		throws NoSuchFileTemplateException;

	/**
	* Returns the first file template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching file template, or <code>null</code> if a matching file template could not be found
	*/
	public FileTemplate fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator);

	/**
	* Returns the last file template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file template
	* @throws NoSuchFileTemplateException if a matching file template could not be found
	*/
	public FileTemplate findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator)
		throws NoSuchFileTemplateException;

	/**
	* Returns the last file template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching file template, or <code>null</code> if a matching file template could not be found
	*/
	public FileTemplate fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator);

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
	public FileTemplate[] findByUuid_C_PrevAndNext(long fileTemplateId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator)
		throws NoSuchFileTemplateException;

	/**
	* Removes all the file templates where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of file templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching file templates
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the file template in the entity cache if it is enabled.
	*
	* @param fileTemplate the file template
	*/
	public void cacheResult(FileTemplate fileTemplate);

	/**
	* Caches the file templates in the entity cache if it is enabled.
	*
	* @param fileTemplates the file templates
	*/
	public void cacheResult(java.util.List<FileTemplate> fileTemplates);

	/**
	* Creates a new file template with the primary key. Does not add the file template to the database.
	*
	* @param fileTemplateId the primary key for the new file template
	* @return the new file template
	*/
	public FileTemplate create(long fileTemplateId);

	/**
	* Removes the file template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileTemplateId the primary key of the file template
	* @return the file template that was removed
	* @throws NoSuchFileTemplateException if a file template with the primary key could not be found
	*/
	public FileTemplate remove(long fileTemplateId)
		throws NoSuchFileTemplateException;

	public FileTemplate updateImpl(FileTemplate fileTemplate);

	/**
	* Returns the file template with the primary key or throws a {@link NoSuchFileTemplateException} if it could not be found.
	*
	* @param fileTemplateId the primary key of the file template
	* @return the file template
	* @throws NoSuchFileTemplateException if a file template with the primary key could not be found
	*/
	public FileTemplate findByPrimaryKey(long fileTemplateId)
		throws NoSuchFileTemplateException;

	/**
	* Returns the file template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fileTemplateId the primary key of the file template
	* @return the file template, or <code>null</code> if a file template with the primary key could not be found
	*/
	public FileTemplate fetchByPrimaryKey(long fileTemplateId);

	@Override
	public java.util.Map<java.io.Serializable, FileTemplate> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the file templates.
	*
	* @return the file templates
	*/
	public java.util.List<FileTemplate> findAll();

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
	public java.util.List<FileTemplate> findAll(int start, int end);

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
	public java.util.List<FileTemplate> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator);

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
	public java.util.List<FileTemplate> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileTemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the file templates from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of file templates.
	*
	* @return the number of file templates
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of service infos associated with the file template.
	*
	* @param pk the primary key of the file template
	* @return long[] of the primaryKeys of service infos associated with the file template
	*/
	public long[] getServiceInfoPrimaryKeys(long pk);

	/**
	* Returns all the service infos associated with the file template.
	*
	* @param pk the primary key of the file template
	* @return the service infos associated with the file template
	*/
	public java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfos(
		long pk);

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
	public java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfos(
		long pk, int start, int end);

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
	public java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfos(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.ServiceInfo> orderByComparator);

	/**
	* Returns the number of service infos associated with the file template.
	*
	* @param pk the primary key of the file template
	* @return the number of service infos associated with the file template
	*/
	public int getServiceInfosSize(long pk);

	/**
	* Returns <code>true</code> if the service info is associated with the file template.
	*
	* @param pk the primary key of the file template
	* @param serviceInfoPK the primary key of the service info
	* @return <code>true</code> if the service info is associated with the file template; <code>false</code> otherwise
	*/
	public boolean containsServiceInfo(long pk, long serviceInfoPK);

	/**
	* Returns <code>true</code> if the file template has any service infos associated with it.
	*
	* @param pk the primary key of the file template to check for associations with service infos
	* @return <code>true</code> if the file template has any service infos associated with it; <code>false</code> otherwise
	*/
	public boolean containsServiceInfos(long pk);

	/**
	* Adds an association between the file template and the service info. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfoPK the primary key of the service info
	*/
	public void addServiceInfo(long pk, long serviceInfoPK);

	/**
	* Adds an association between the file template and the service info. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfo the service info
	*/
	public void addServiceInfo(long pk,
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo);

	/**
	* Adds an association between the file template and the service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfoPKs the primary keys of the service infos
	*/
	public void addServiceInfos(long pk, long[] serviceInfoPKs);

	/**
	* Adds an association between the file template and the service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfos the service infos
	*/
	public void addServiceInfos(long pk,
		java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> serviceInfos);

	/**
	* Clears all associations between the file template and its service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template to clear the associated service infos from
	*/
	public void clearServiceInfos(long pk);

	/**
	* Removes the association between the file template and the service info. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfoPK the primary key of the service info
	*/
	public void removeServiceInfo(long pk, long serviceInfoPK);

	/**
	* Removes the association between the file template and the service info. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfo the service info
	*/
	public void removeServiceInfo(long pk,
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo);

	/**
	* Removes the association between the file template and the service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfoPKs the primary keys of the service infos
	*/
	public void removeServiceInfos(long pk, long[] serviceInfoPKs);

	/**
	* Removes the association between the file template and the service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfos the service infos
	*/
	public void removeServiceInfos(long pk,
		java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> serviceInfos);

	/**
	* Sets the service infos associated with the file template, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfoPKs the primary keys of the service infos to be associated with the file template
	*/
	public void setServiceInfos(long pk, long[] serviceInfoPKs);

	/**
	* Sets the service infos associated with the file template, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the file template
	* @param serviceInfos the service infos to be associated with the file template
	*/
	public void setServiceInfos(long pk,
		java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> serviceInfos);

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}