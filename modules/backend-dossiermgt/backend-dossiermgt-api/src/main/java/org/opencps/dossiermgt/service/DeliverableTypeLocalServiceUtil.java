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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DeliverableType. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.DeliverableTypeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see DeliverableTypeLocalService
 * @see org.opencps.dossiermgt.service.base.DeliverableTypeLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.DeliverableTypeLocalServiceImpl
 * @generated
 */
@ProviderType
public class DeliverableTypeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.DeliverableTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the deliverable type to the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableType the deliverable type
	* @return the deliverable type that was added
	*/
	public static org.opencps.dossiermgt.model.DeliverableType addDeliverableType(
		org.opencps.dossiermgt.model.DeliverableType deliverableType) {
		return getService().addDeliverableType(deliverableType);
	}

	public static org.opencps.dossiermgt.model.DeliverableType addDeliverableType(
		long groupId, String deliverableName, String deliverableType_,
		String codePattern, String counter, String formScript,
		String formReport, String mappingData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addDeliverableType(groupId, deliverableName,
			deliverableType_, codePattern, counter, formScript, formReport,
			mappingData, serviceContext);
	}

	public static org.opencps.dossiermgt.model.DeliverableType adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.dossiermgt.model.DeliverableType adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new deliverable type with the primary key. Does not add the deliverable type to the database.
	*
	* @param deliverableTypeId the primary key for the new deliverable type
	* @return the new deliverable type
	*/
	public static org.opencps.dossiermgt.model.DeliverableType createDeliverableType(
		long deliverableTypeId) {
		return getService().createDeliverableType(deliverableTypeId);
	}

	/**
	* Deletes the deliverable type from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableType the deliverable type
	* @return the deliverable type that was removed
	*/
	public static org.opencps.dossiermgt.model.DeliverableType deleteDeliverableType(
		org.opencps.dossiermgt.model.DeliverableType deliverableType) {
		return getService().deleteDeliverableType(deliverableType);
	}

	/**
	* Deletes the deliverable type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeId the primary key of the deliverable type
	* @return the deliverable type that was removed
	* @throws PortalException if a deliverable type with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.DeliverableType deleteDeliverableType(
		long deliverableTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDeliverableType(deliverableTypeId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.opencps.dossiermgt.model.DeliverableType fetchDeliverableType(
		long deliverableTypeId) {
		return getService().fetchDeliverableType(deliverableTypeId);
	}

	/**
	* Returns the deliverable type matching the UUID and group.
	*
	* @param uuid the deliverable type's UUID
	* @param groupId the primary key of the group
	* @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public static org.opencps.dossiermgt.model.DeliverableType fetchDeliverableTypeByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchDeliverableTypeByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.dossiermgt.model.DeliverableType getByCode(
		long groupId, String typeCode) {
		return getService().getByCode(groupId, typeCode);
	}

	/**
	* Returns the deliverable type with the primary key.
	*
	* @param deliverableTypeId the primary key of the deliverable type
	* @return the deliverable type
	* @throws PortalException if a deliverable type with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.DeliverableType getDeliverableType(
		long deliverableTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDeliverableType(deliverableTypeId);
	}

	public static org.opencps.dossiermgt.model.DeliverableType getDeliverableTypebyId(
		long groupId, String deliverableTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDeliverableTypebyId(groupId, deliverableTypeId);
	}

	/**
	* Returns the deliverable type matching the UUID and group.
	*
	* @param uuid the deliverable type's UUID
	* @param groupId the primary key of the group
	* @return the matching deliverable type
	* @throws PortalException if a matching deliverable type could not be found
	*/
	public static org.opencps.dossiermgt.model.DeliverableType getDeliverableTypeByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDeliverableTypeByUuidAndGroupId(uuid, groupId);
	}

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
	public static java.util.List<org.opencps.dossiermgt.model.DeliverableType> getDeliverableTypes(
		int start, int end) {
		return getService().getDeliverableTypes(start, end);
	}

	/**
	* Returns all the deliverable types matching the UUID and company.
	*
	* @param uuid the UUID of the deliverable types
	* @param companyId the primary key of the company
	* @return the matching deliverable types, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.DeliverableType> getDeliverableTypesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getDeliverableTypesByUuidAndCompanyId(uuid, companyId);
	}

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
	public static java.util.List<org.opencps.dossiermgt.model.DeliverableType> getDeliverableTypesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.DeliverableType> orderByComparator) {
		return getService()
				   .getDeliverableTypesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of deliverable types.
	*
	* @return the number of deliverable types
	*/
	public static int getDeliverableTypesCount() {
		return getService().getDeliverableTypesCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static org.opencps.dossiermgt.model.DeliverableType removeDeliverableType(
		long groupId, String deliverableTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeDeliverableType(groupId, deliverableTypeId);
	}

	/**
	* Updates the deliverable type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param deliverableType the deliverable type
	* @return the deliverable type that was updated
	*/
	public static org.opencps.dossiermgt.model.DeliverableType updateDeliverableType(
		org.opencps.dossiermgt.model.DeliverableType deliverableType) {
		return getService().updateDeliverableType(deliverableType);
	}

	public static org.opencps.dossiermgt.model.DeliverableType updateDeliverableType(
		long groupId, long deliverableTypeId, String deliverableName,
		String deliverableType_, String codePattern, String counter,
		String formScript, String formReport, String mappingData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDeliverableType(groupId, deliverableTypeId,
			deliverableName, deliverableType_, codePattern, counter,
			formScript, formReport, mappingData, serviceContext);
	}

	public static org.opencps.dossiermgt.model.DeliverableType updateDeliverableTypeDB(
		long userId, long groupId, String typeCode, String typeName,
		String codePattern, Integer docSync, String mappingData,
		String govAgencies, String formReport, String formScript) {
		return getService()
				   .updateDeliverableTypeDB(userId, groupId, typeCode,
			typeName, codePattern, docSync, mappingData, govAgencies,
			formReport, formScript);
	}

	public static org.opencps.dossiermgt.model.DeliverableType updateFormReport(
		long groupId, long deliverableTypeId, String formReport,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateFormReport(groupId, deliverableTypeId, formReport,
			serviceContext);
	}

	public static org.opencps.dossiermgt.model.DeliverableType updateFormScript(
		long groupId, long deliverableTypeId, String formScript,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateFormScript(groupId, deliverableTypeId, formScript,
			serviceContext);
	}

	public static org.opencps.dossiermgt.model.DeliverableType updateMappingData(
		long groupId, long deliverableTypeId, String mappingData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateMappingData(groupId, deliverableTypeId, mappingData,
			serviceContext);
	}

	public static DeliverableTypeLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DeliverableTypeLocalService, DeliverableTypeLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DeliverableTypeLocalService.class);

		ServiceTracker<DeliverableTypeLocalService, DeliverableTypeLocalService> serviceTracker =
			new ServiceTracker<DeliverableTypeLocalService, DeliverableTypeLocalService>(bundle.getBundleContext(),
				DeliverableTypeLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}