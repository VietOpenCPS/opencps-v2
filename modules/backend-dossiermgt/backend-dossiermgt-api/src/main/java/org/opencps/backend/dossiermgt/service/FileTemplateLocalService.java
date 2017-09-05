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

package org.opencps.backend.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.backend.dossiermgt.model.FileTemplate;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for FileTemplate. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see FileTemplateLocalServiceUtil
 * @see org.opencps.backend.dossiermgt.service.base.FileTemplateLocalServiceBaseImpl
 * @see org.opencps.backend.dossiermgt.service.impl.FileTemplateLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface FileTemplateLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FileTemplateLocalServiceUtil} to access the file template local service. Add custom service methods to {@link org.opencps.backend.dossiermgt.service.impl.FileTemplateLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasServiceInfoFileTemplate(long serviceInfoId,
		long fileTemplateId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasServiceInfoFileTemplates(long serviceInfoId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of file templates.
	*
	* @return the number of file templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFileTemplatesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getServiceInfoFileTemplatesCount(long serviceInfoId);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns a range of all the file templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @return the range of file templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FileTemplate> getFileTemplates(int start, int end);

	/**
	* Returns all the file templates matching the UUID and company.
	*
	* @param uuid the UUID of the file templates
	* @param companyId the primary key of the company
	* @return the matching file templates, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FileTemplate> getFileTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of file templates matching the UUID and company.
	*
	* @param uuid the UUID of the file templates
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching file templates, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FileTemplate> getFileTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<FileTemplate> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FileTemplate> getServiceInfoFileTemplates(long serviceInfoId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FileTemplate> getServiceInfoFileTemplates(long serviceInfoId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FileTemplate> getServiceInfoFileTemplates(long serviceInfoId,
		int start, int end, OrderByComparator<FileTemplate> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* Returns the serviceInfoIds of the service infos associated with the file template.
	*
	* @param fileTemplateId the fileTemplateId of the file template
	* @return long[] the serviceInfoIds of service infos associated with the file template
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getServiceInfoPrimaryKeys(long fileTemplateId);

	/**
	* Adds the file template to the database. Also notifies the appropriate model listeners.
	*
	* @param fileTemplate the file template
	* @return the file template that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public FileTemplate addFileTemplate(FileTemplate fileTemplate);

	/**
	* Creates a new file template with the primary key. Does not add the file template to the database.
	*
	* @param fileTemplateId the primary key for the new file template
	* @return the new file template
	*/
	public FileTemplate createFileTemplate(long fileTemplateId);

	/**
	* Deletes the file template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileTemplateId the primary key of the file template
	* @return the file template that was removed
	* @throws PortalException if a file template with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public FileTemplate deleteFileTemplate(long fileTemplateId)
		throws PortalException;

	/**
	* Deletes the file template from the database. Also notifies the appropriate model listeners.
	*
	* @param fileTemplate the file template
	* @return the file template that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public FileTemplate deleteFileTemplate(FileTemplate fileTemplate);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FileTemplate fetchFileTemplate(long fileTemplateId);

	/**
	* Returns the file template matching the UUID and group.
	*
	* @param uuid the file template's UUID
	* @param groupId the primary key of the group
	* @return the matching file template, or <code>null</code> if a matching file template could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FileTemplate fetchFileTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the file template with the primary key.
	*
	* @param fileTemplateId the primary key of the file template
	* @return the file template
	* @throws PortalException if a file template with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FileTemplate getFileTemplate(long fileTemplateId)
		throws PortalException;

	/**
	* Returns the file template matching the UUID and group.
	*
	* @param uuid the file template's UUID
	* @param groupId the primary key of the group
	* @return the matching file template
	* @throws PortalException if a matching file template could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FileTemplate getFileTemplateByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the file template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param fileTemplate the file template
	* @return the file template that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public FileTemplate updateFileTemplate(FileTemplate fileTemplate);

	public void addServiceInfoFileTemplate(long serviceInfoId,
		long fileTemplateId);

	public void addServiceInfoFileTemplate(long serviceInfoId,
		FileTemplate fileTemplate);

	public void addServiceInfoFileTemplates(long serviceInfoId,
		List<FileTemplate> fileTemplates);

	public void addServiceInfoFileTemplates(long serviceInfoId,
		long[] fileTemplateIds);

	public void clearServiceInfoFileTemplates(long serviceInfoId);

	public void deleteServiceInfoFileTemplate(long serviceInfoId,
		long fileTemplateId);

	public void deleteServiceInfoFileTemplate(long serviceInfoId,
		FileTemplate fileTemplate);

	public void deleteServiceInfoFileTemplates(long serviceInfoId,
		List<FileTemplate> fileTemplates);

	public void deleteServiceInfoFileTemplates(long serviceInfoId,
		long[] fileTemplateIds);

	public void setServiceInfoFileTemplates(long serviceInfoId,
		long[] fileTemplateIds);
}