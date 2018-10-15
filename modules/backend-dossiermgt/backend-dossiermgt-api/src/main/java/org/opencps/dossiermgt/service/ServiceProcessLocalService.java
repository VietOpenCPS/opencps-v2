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

import org.opencps.dossiermgt.model.ServiceProcess;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for ServiceProcess. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see ServiceProcessLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.ServiceProcessLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ServiceProcessLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ServiceProcessLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceProcessLocalServiceUtil} to access the service process local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ServiceProcessLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the service process to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcess the service process
	* @return the service process that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ServiceProcess addServiceProcess(ServiceProcess serviceProcess);

	@Indexable(type = IndexableType.REINDEX)
	public void cloneServiceProcess(long serviceProcessId, long groupId,
		String processNo, ServiceContext serviceContext)
		throws PortalException;

	public long countLucene(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	/**
	* Creates a new service process with the primary key. Does not add the service process to the database.
	*
	* @param serviceProcessId the primary key for the new service process
	* @return the new service process
	*/
	@Transactional(enabled = false)
	public ServiceProcess createServiceProcess(long serviceProcessId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the service process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process that was removed
	* @throws PortalException if a service process with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ServiceProcess deleteServiceProcess(long serviceProcessId)
		throws PortalException;

	/**
	* Deletes the service process from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcess the service process
	* @return the service process that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ServiceProcess deleteServiceProcess(ServiceProcess serviceProcess);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ServiceProcess fetchServiceProcess(long serviceProcessId);

	/**
	* Returns the service process matching the UUID and group.
	*
	* @param uuid the service process's UUID
	* @param groupId the primary key of the group
	* @return the matching service process, or <code>null</code> if a matching service process could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceProcess fetchServiceProcessByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceProcess> getByG_ID(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceProcess getByG_PNO(long groupId, String processNo);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceProcess> getByServerNo(String serverNo);

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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceProcess getServiceByCode(long groupId, String serviceCode,
		String govAgencyCode, String dossierTemplateNo)
		throws PortalException;

	/**
	* Returns the service process with the primary key.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process
	* @throws PortalException if a service process with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceProcess getServiceProcess(long serviceProcessId)
		throws PortalException;

	/**
	* Returns the service process matching the UUID and group.
	*
	* @param uuid the service process's UUID
	* @param groupId the primary key of the group
	* @return the matching service process
	* @throws PortalException if a matching service process could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceProcess getServiceProcessByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the service processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @return the range of service processes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceProcess> getServiceProcesses(int start, int end);

	/**
	* Returns all the service processes matching the UUID and company.
	*
	* @param uuid the UUID of the service processes
	* @param companyId the primary key of the company
	* @return the matching service processes, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceProcess> getServiceProcessesByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of service processes matching the UUID and company.
	*
	* @param uuid the UUID of the service processes
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching service processes, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceProcess> getServiceProcessesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator);

	/**
	* Returns the number of service processes.
	*
	* @return the number of service processes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getServiceProcessesCount();

	@Indexable(type = IndexableType.REINDEX)
	public void initServiceProcess(long groupId, ServiceContext context);

	@Indexable(type = IndexableType.DELETE)
	public ServiceProcess removeServiceProcess(long serviceProcessId,
		long groupId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits searchLucene(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	@Indexable(type = IndexableType.REINDEX)
	public ServiceProcess updateServiceProcess(long groupId,
		long serviceProcessId, String processNo, String processName,
		String description, Double durationCount, int durationUnit,
		long counter, boolean generateDossierNo, String dossierNoPattern,
		boolean generateDueDate, String dueDatePattern,
		boolean generatePassword, boolean directNotification, String serverNo,
		String paymentFee, ServiceContext context) throws PortalException;

	/**
	* Updates the service process in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceProcess the service process
	* @return the service process that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ServiceProcess updateServiceProcess(ServiceProcess serviceProcess);

	@Indexable(type = IndexableType.REINDEX)
	public ServiceProcess updateServiceProcessDB(long userId, long groupId,
		String processNo, String processName, String description,
		Double durationCount, Integer durationUnit, boolean generatePassword,
		String serverNo, String serverName, String dossierNoPattern,
		String dueDatePattern, ServiceContext serviceContext)
		throws PortalException;
}