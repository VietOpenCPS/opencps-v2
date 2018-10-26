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
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.ServiceConfig;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for ServiceConfig. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see ServiceConfigLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.ServiceConfigLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ServiceConfigLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ServiceConfigLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceConfigLocalServiceUtil} to access the service config local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ServiceConfigLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the service config to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfig the service config
	* @return the service config that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ServiceConfig addServiceConfig(ServiceConfig serviceConfig);

	@Indexable(type = IndexableType.REINDEX)
	public ServiceConfig adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public ServiceConfig adminProcessDelete(Long id);

	public long countByGovAgency(String keyword, String govAgencyCode,
		long groupId);

	public long countLucene(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	/**
	* Creates a new service config with the primary key. Does not add the service config to the database.
	*
	* @param serviceConfigId the primary key for the new service config
	* @return the new service config
	*/
	@Transactional(enabled = false)
	public ServiceConfig createServiceConfig(long serviceConfigId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the service config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigId the primary key of the service config
	* @return the service config that was removed
	* @throws PortalException if a service config with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ServiceConfig deleteServiceConfig(long serviceConfigId)
		throws PortalException;

	/**
	* Deletes the service config from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfig the service config
	* @return the service config that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ServiceConfig deleteServiceConfig(ServiceConfig serviceConfig);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ServiceConfig fetchServiceConfig(long serviceConfigId);

	/**
	* Returns the service config matching the UUID and group.
	*
	* @param uuid the service config's UUID
	* @param groupId the primary key of the group
	* @return the matching service config, or <code>null</code> if a matching service config could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceConfig fetchServiceConfigByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceConfig> getByGovAgencyCode(String govAgencyCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceConfig> getByGroupId(long groupId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceConfig> getByLevel(long groupId, int level);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceConfig> getByServiceInfo(long groupId, long serviceInfoId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceConfig getBySICodeAndGAC(long groupId,
		String serviceInfoCode, String govAgencyCode) throws PortalException;

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
	* Returns the service config with the primary key.
	*
	* @param serviceConfigId the primary key of the service config
	* @return the service config
	* @throws PortalException if a service config with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceConfig getServiceConfig(long serviceConfigId)
		throws PortalException;

	/**
	* Returns the service config matching the UUID and group.
	*
	* @param uuid the service config's UUID
	* @param groupId the primary key of the group
	* @return the matching service config
	* @throws PortalException if a matching service config could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceConfig getServiceConfigByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the service configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @return the range of service configs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceConfig> getServiceConfigs(int start, int end);

	/**
	* Returns all the service configs matching the UUID and company.
	*
	* @param uuid the UUID of the service configs
	* @param companyId the primary key of the company
	* @return the matching service configs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceConfig> getServiceConfigsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of service configs matching the UUID and company.
	*
	* @param uuid the UUID of the service configs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching service configs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceConfig> getServiceConfigsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns the number of service configs.
	*
	* @return the number of service configs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getServiceConfigsCount();

	@Indexable(type = IndexableType.DELETE)
	public ServiceConfig removeServiceConfigById(long serviceConfigId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceConfig> searchByGovAgency(String keyword,
		String govAgencyCode, long groupId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits searchLucene(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	@Indexable(type = IndexableType.REINDEX)
	public ServiceConfig updateServiceConfig(long serviceConfigId, long userId,
		long groupId, long serviceInfoId, String govAgencyCode,
		String serviceInstruction, int serviceLevel, String serviceUrl,
		boolean forCitizen, boolean forBusiness, boolean postalService,
		boolean registration, ServiceContext context) throws PortalException;

	/**
	* Updates the service config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceConfig the service config
	* @return the service config that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ServiceConfig updateServiceConfig(ServiceConfig serviceConfig);

	@Indexable(type = IndexableType.REINDEX)
	public ServiceConfig updateServiceConfigDB(long userId, long groupId,
		long serviceInfoId, String govAgencyCode, String govAgencyName,
		String serviceInstruction, Integer serviceLevel, String serviceUrl,
		boolean forCitizen, boolean forBusiness, boolean postalService,
		boolean registration, ServiceContext context);
}