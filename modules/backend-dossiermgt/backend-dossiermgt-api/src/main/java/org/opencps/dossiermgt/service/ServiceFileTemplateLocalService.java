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

package org.opencps.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ServiceFileTemplate. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see ServiceFileTemplateLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.ServiceFileTemplateLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ServiceFileTemplateLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ServiceFileTemplateLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceFileTemplateLocalServiceUtil} to access the service file template local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ServiceFileTemplateLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public ServiceFileTemplate addServiceFileTemplate(long userId,
		long groupId, long folderId, long serviceInfoId, String fileTemplateNo,
		String templateName, String sourceFileName, InputStream inputStream,
		ServiceContext serviceContext) throws PortalException, IOException;

	public ServiceFileTemplate addServiceFileTemplate(long serviceInfoId,
		String fileTemplateNo, String templateName, long fileEntryId,
		ServiceContext serviceContext) throws PortalException, IOException;

	/**
	* Adds the service file template to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceFileTemplate the service file template
	* @return the service file template that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ServiceFileTemplate addServiceFileTemplate(
		ServiceFileTemplate serviceFileTemplate);

	public int countByServiceInfoId(long serviceInfoId);

	/**
	* Creates a new service file template with the primary key. Does not add the service file template to the database.
	*
	* @param serviceFileTemplatePK the primary key for the new service file template
	* @return the new service file template
	*/
	@Transactional(enabled = false)
	public ServiceFileTemplate createServiceFileTemplate(
		ServiceFileTemplatePK serviceFileTemplatePK);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the service file template from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceFileTemplate the service file template
	* @return the service file template that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ServiceFileTemplate deleteServiceFileTemplate(
		ServiceFileTemplate serviceFileTemplate);

	/**
	* Deletes the service file template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceFileTemplatePK the primary key of the service file template
	* @return the service file template that was removed
	* @throws PortalException if a service file template with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ServiceFileTemplate deleteServiceFileTemplate(
		ServiceFileTemplatePK serviceFileTemplatePK) throws PortalException;

	public DynamicQuery dynamicQuery();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceFileTemplate fetchByF_serviceInfoId_fileTemplateNo(
		long serviceInfoId, String fileTemplateNo);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceFileTemplate fetchServiceFileTemplate(
		ServiceFileTemplatePK serviceFileTemplatePK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceFileTemplate> getByServiceInfoId(long serviceInfoId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the service file template with the primary key.
	*
	* @param serviceFileTemplatePK the primary key of the service file template
	* @return the service file template
	* @throws PortalException if a service file template with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceFileTemplate getServiceFileTemplate(
		ServiceFileTemplatePK serviceFileTemplatePK) throws PortalException;

	/**
	* Returns a range of all the service file templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service file templates
	* @param end the upper bound of the range of service file templates (not inclusive)
	* @return the range of service file templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceFileTemplate> getServiceFileTemplates(int start, int end);

	/**
	* Returns the number of service file templates.
	*
	* @return the number of service file templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getServiceFileTemplatesCount();

	public ServiceFileTemplate removeServiceFileTemplate(long serviceInfoId,
		String fileTemplateNo) throws PortalException;

	public ServiceFileTemplate updateServiceFileTemplate(long userId,
		long groupId, long folderId, long serviceInfoId, String fileTemplateNo,
		String templateName, String sourceFileName, InputStream inputStream,
		ServiceContext serviceContext) throws PortalException, IOException;

	/**
	* Updates the service file template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceFileTemplate the service file template
	* @return the service file template that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ServiceFileTemplate updateServiceFileTemplate(
		ServiceFileTemplate serviceFileTemplate);

	@Indexable(type = IndexableType.REINDEX)
	public ServiceFileTemplate updateServiceFileTemplateDB(long serviceInfoId,
		String fileTemplateNo, String fileTemplateName, String fileName,
		long fileEntryId);
}