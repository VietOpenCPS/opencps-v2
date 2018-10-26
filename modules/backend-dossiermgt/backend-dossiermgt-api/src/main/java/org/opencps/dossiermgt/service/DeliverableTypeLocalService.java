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
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.DeliverableType;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for DeliverableType. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see DeliverableTypeLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.DeliverableTypeLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.DeliverableTypeLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DeliverableTypeLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DeliverableTypeLocalServiceUtil} to access the deliverable type local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.DeliverableTypeLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the deliverable type to the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableType the deliverable type
	* @return the deliverable type that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DeliverableType addDeliverableType(DeliverableType deliverableType);

	public DeliverableType addDeliverableType(long groupId,
		String deliverableName, String deliverableType_, String codePattern,
		String counter, String formScript, String formReport,
		String mappingData, ServiceContext serviceContext)
		throws PortalException, SystemException;

	@Indexable(type = IndexableType.REINDEX)
	public DeliverableType adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public DeliverableType adminProcessDelete(Long id);

	/**
	* Creates a new deliverable type with the primary key. Does not add the deliverable type to the database.
	*
	* @param deliverableTypeId the primary key for the new deliverable type
	* @return the new deliverable type
	*/
	@Transactional(enabled = false)
	public DeliverableType createDeliverableType(long deliverableTypeId);

	/**
	* Deletes the deliverable type from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableType the deliverable type
	* @return the deliverable type that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public DeliverableType deleteDeliverableType(
		DeliverableType deliverableType);

	/**
	* Deletes the deliverable type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeId the primary key of the deliverable type
	* @return the deliverable type that was removed
	* @throws PortalException if a deliverable type with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DeliverableType deleteDeliverableType(long deliverableTypeId)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public DeliverableType fetchDeliverableType(long deliverableTypeId);

	/**
	* Returns the deliverable type matching the UUID and group.
	*
	* @param uuid the deliverable type's UUID
	* @param groupId the primary key of the group
	* @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DeliverableType fetchDeliverableTypeByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DeliverableType getByCode(long groupId, String typeCode);

	/**
	* Returns the deliverable type with the primary key.
	*
	* @param deliverableTypeId the primary key of the deliverable type
	* @return the deliverable type
	* @throws PortalException if a deliverable type with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DeliverableType getDeliverableType(long deliverableTypeId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DeliverableType getDeliverableTypebyId(long groupId,
		String deliverableTypeId) throws PortalException;

	/**
	* Returns the deliverable type matching the UUID and group.
	*
	* @param uuid the deliverable type's UUID
	* @param groupId the primary key of the group
	* @return the matching deliverable type
	* @throws PortalException if a matching deliverable type could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DeliverableType getDeliverableTypeByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @return the range of deliverable types
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DeliverableType> getDeliverableTypes(int start, int end);

	/**
	* Returns all the deliverable types matching the UUID and company.
	*
	* @param uuid the UUID of the deliverable types
	* @param companyId the primary key of the company
	* @return the matching deliverable types, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DeliverableType> getDeliverableTypesByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of deliverable types matching the UUID and company.
	*
	* @param uuid the UUID of the deliverable types
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching deliverable types, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DeliverableType> getDeliverableTypesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DeliverableType> orderByComparator);

	/**
	* Returns the number of deliverable types.
	*
	* @return the number of deliverable types
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDeliverableTypesCount();

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

	public DeliverableType removeDeliverableType(long groupId,
		String deliverableTypeId) throws PortalException;

	/**
	* Updates the deliverable type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param deliverableType the deliverable type
	* @return the deliverable type that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DeliverableType updateDeliverableType(
		DeliverableType deliverableType);

	public DeliverableType updateDeliverableType(long groupId,
		long deliverableTypeId, String deliverableName,
		String deliverableType_, String codePattern, String counter,
		String formScript, String formReport, String mappingData,
		ServiceContext serviceContext) throws PortalException;

	public DeliverableType updateDeliverableTypeDB(long userId, long groupId,
		String typeCode, String typeName, String codePattern, Integer docSync,
		String mappingData, String govAgencies, String formReport,
		String formScript);

	public DeliverableType updateFormReport(long groupId,
		long deliverableTypeId, String formReport, ServiceContext serviceContext)
		throws PortalException, SystemException;

	public DeliverableType updateFormScript(long groupId,
		long deliverableTypeId, String formScript, ServiceContext serviceContext)
		throws PortalException, SystemException;

	public DeliverableType updateMappingData(long groupId,
		long deliverableTypeId, String mappingData,
		ServiceContext serviceContext) throws PortalException, SystemException;
}