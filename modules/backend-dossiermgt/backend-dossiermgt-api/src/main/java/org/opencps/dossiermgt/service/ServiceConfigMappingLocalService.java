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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
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

import org.opencps.dossiermgt.model.ServiceConfigMapping;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ServiceConfigMapping. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see ServiceConfigMappingLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.ServiceConfigMappingLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ServiceConfigMappingLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ServiceConfigMappingLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceConfigMappingLocalServiceUtil} to access the service config mapping local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ServiceConfigMappingLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
		PortalException.class, SystemException.class}
	)
	public ServiceConfigMapping addServiceConfigMaping(long groupId,
		String serviceConfigCode, String serviceConfigName, String serviceCode,
		String serviceName, String govAgencyName, String domainName,
		JSONArray applicableInfoObj, JSONArray paymentFeeInfoObj,
		ServiceContext context) throws PortalException;

	/**
	* Adds the service config mapping to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMapping the service config mapping
	* @return the service config mapping that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ServiceConfigMapping addServiceConfigMapping(
		ServiceConfigMapping serviceConfigMapping);

	/**
	* Creates a new service config mapping with the primary key. Does not add the service config mapping to the database.
	*
	* @param serviceConfigMappingId the primary key for the new service config mapping
	* @return the new service config mapping
	*/
	@Transactional(enabled = false)
	public ServiceConfigMapping createServiceConfigMapping(
		long serviceConfigMappingId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the service config mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMappingId the primary key of the service config mapping
	* @return the service config mapping that was removed
	* @throws PortalException if a service config mapping with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ServiceConfigMapping deleteServiceConfigMapping(
		long serviceConfigMappingId) throws PortalException;

	/**
	* Deletes the service config mapping from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMapping the service config mapping
	* @return the service config mapping that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ServiceConfigMapping deleteServiceConfigMapping(
		ServiceConfigMapping serviceConfigMapping);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ServiceConfigMapping fetchServiceConfigMapping(
		long serviceConfigMappingId);

	/**
	* Returns the service config mapping matching the UUID and group.
	*
	* @param uuid the service config mapping's UUID
	* @param groupId the primary key of the group
	* @return the matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceConfigMapping fetchServiceConfigMappingByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

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
	* Returns the service config mapping with the primary key.
	*
	* @param serviceConfigMappingId the primary key of the service config mapping
	* @return the service config mapping
	* @throws PortalException if a service config mapping with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceConfigMapping getServiceConfigMapping(
		long serviceConfigMappingId) throws PortalException;

	/**
	* Returns the service config mapping matching the UUID and group.
	*
	* @param uuid the service config mapping's UUID
	* @param groupId the primary key of the group
	* @return the matching service config mapping
	* @throws PortalException if a matching service config mapping could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceConfigMapping getServiceConfigMappingByUuidAndGroupId(
		String uuid, long groupId) throws PortalException;

	/**
	* Returns a range of all the service config mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @return the range of service config mappings
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceConfigMapping> getServiceConfigMappings(int start,
		int end);

	/**
	* Returns all the service config mappings matching the UUID and company.
	*
	* @param uuid the UUID of the service config mappings
	* @param companyId the primary key of the company
	* @return the matching service config mappings, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceConfigMapping> getServiceConfigMappingsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of service config mappings matching the UUID and company.
	*
	* @param uuid the UUID of the service config mappings
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching service config mappings, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceConfigMapping> getServiceConfigMappingsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator);

	/**
	* Returns the number of service config mappings.
	*
	* @return the number of service config mappings
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getServiceConfigMappingsCount();

	public ServiceConfigMapping removeServiceConfigMapping(long groupId,
		long serviceConfigMappingId) throws PortalException;

	/**
	* Updates the service config mapping in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMapping the service config mapping
	* @return the service config mapping that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ServiceConfigMapping updateServiceConfigMapping(
		ServiceConfigMapping serviceConfigMapping);
}