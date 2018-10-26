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
 * Provides the local service utility for DossierPart. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.DossierPartLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see DossierPartLocalService
 * @see org.opencps.dossiermgt.service.base.DossierPartLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.DossierPartLocalServiceImpl
 * @generated
 */
@ProviderType
public class DossierPartLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.DossierPartLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dossier part to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierPart the dossier part
	* @return the dossier part that was added
	*/
	public static org.opencps.dossiermgt.model.DossierPart addDossierPart(
		org.opencps.dossiermgt.model.DossierPart dossierPart) {
		return getService().addDossierPart(dossierPart);
	}

	public static org.opencps.dossiermgt.model.DossierPart adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.dossiermgt.model.DossierPart adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	public static long countLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLucene(params, searchContext);
	}

	/**
	* Creates a new dossier part with the primary key. Does not add the dossier part to the database.
	*
	* @param dossierPartId the primary key for the new dossier part
	* @return the new dossier part
	*/
	public static org.opencps.dossiermgt.model.DossierPart createDossierPart(
		long dossierPartId) {
		return getService().createDossierPart(dossierPartId);
	}

	/**
	* Deletes the dossier part from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierPart the dossier part
	* @return the dossier part that was removed
	*/
	public static org.opencps.dossiermgt.model.DossierPart deleteDossierPart(
		org.opencps.dossiermgt.model.DossierPart dossierPart) {
		return getService().deleteDossierPart(dossierPart);
	}

	/**
	* Deletes the dossier part with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierPartId the primary key of the dossier part
	* @return the dossier part that was removed
	* @throws PortalException if a dossier part with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierPart deleteDossierPart(
		long dossierPartId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDossierPart(dossierPartId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.DossierPart fetchByTemplatePartNo(
		long groupId, String templateNo, String partNo)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchByTemplatePartNo(groupId, templateNo, partNo);
	}

	public static org.opencps.dossiermgt.model.DossierPart fetchDossierPart(
		long dossierPartId) {
		return getService().fetchDossierPart(dossierPartId);
	}

	/**
	* Returns the dossier part matching the UUID and group.
	*
	* @param uuid the dossier part's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierPart fetchDossierPartByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchDossierPartByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.dossiermgt.model.DossierPart getByFileTemplateNo(
		long groupId, String fileTemplateNo) {
		return getService().getByFileTemplateNo(groupId, fileTemplateNo);
	}

	public static org.opencps.dossiermgt.model.DossierPart getByPartTypeEsign(
		String templateNo, String partNo, int partType, boolean eSign) {
		return getService()
				   .getByPartTypeEsign(templateNo, partNo, partType, eSign);
	}

	public static org.opencps.dossiermgt.model.DossierPart getByTempAndPartNo(
		long groupId, String templateNo, String partNo) {
		return getService().getByTempAndPartNo(groupId, templateNo, partNo);
	}

	/**
	* @param groupId
	* @param templateNo
	* @return
	* @throws PortalException
	*/
	public static java.util.List<org.opencps.dossiermgt.model.DossierPart> getByTemplateNo(
		long groupId, String templateNo)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getByTemplateNo(groupId, templateNo);
	}

	/**
	* @param dossierPartId
	* @param contentType
	* @return
	* @throws PortalException
	*/
	public static String getContent(long dossierPartId, int contentType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getContent(dossierPartId, contentType);
	}

	/**
	* Returns the dossier part with the primary key.
	*
	* @param dossierPartId the primary key of the dossier part
	* @return the dossier part
	* @throws PortalException if a dossier part with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierPart getDossierPart(
		long dossierPartId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDossierPart(dossierPartId);
	}

	/**
	* Returns the dossier part matching the UUID and group.
	*
	* @param uuid the dossier part's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier part
	* @throws PortalException if a matching dossier part could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierPart getDossierPartByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDossierPartByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the dossier parts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @return the range of dossier parts
	*/
	public static java.util.List<org.opencps.dossiermgt.model.DossierPart> getDossierParts(
		int start, int end) {
		return getService().getDossierParts(start, end);
	}

	/**
	* Returns all the dossier parts matching the UUID and company.
	*
	* @param uuid the UUID of the dossier parts
	* @param companyId the primary key of the company
	* @return the matching dossier parts, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.DossierPart> getDossierPartsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getDossierPartsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of dossier parts matching the UUID and company.
	*
	* @param uuid the UUID of the dossier parts
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier parts, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.DossierPart> getDossierPartsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.DossierPart> orderByComparator) {
		return getService()
				   .getDossierPartsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of dossier parts.
	*
	* @return the number of dossier parts
	*/
	public static int getDossierPartsCount() {
		return getService().getDossierPartsCount();
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

	public static org.opencps.dossiermgt.model.DossierPart removeDossierPart(
		long dossierPartId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeDossierPart(dossierPartId);
	}

	public static com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .searchLucene(params, sorts, start, end, searchContext);
	}

	/**
	* @param dossierPartId
	* @param contentType
	* @param input
	* @param context
	* @return
	* @throws PortalException
	*/
	public static String updateContent(long dossierPartId, int contentType,
		String input, com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateContent(dossierPartId, contentType, input, context);
	}

	/**
	* Updates the dossier part in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierPart the dossier part
	* @return the dossier part that was updated
	*/
	public static org.opencps.dossiermgt.model.DossierPart updateDossierPart(
		org.opencps.dossiermgt.model.DossierPart dossierPart) {
		return getService().updateDossierPart(dossierPart);
	}

	public static org.opencps.dossiermgt.model.DossierPart updateDossierPart(
		long groupId, long dossierPartId, String templateNo, String partNo,
		String partName, String partTip, int partType, boolean multiple,
		String formScript, String formReport, String sampleData,
		boolean required, String fileTemplateNo, boolean eSign,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDossierPart(groupId, dossierPartId, templateNo,
			partNo, partName, partTip, partType, multiple, formScript,
			formReport, sampleData, required, fileTemplateNo, eSign, context);
	}

	public static org.opencps.dossiermgt.model.DossierPart updateDossierPart(
		long groupId, long dossierPartId, String templateNo, String partNo,
		String partName, String partTip, int partType, boolean multiple,
		String formScript, String formReport, String sampleData,
		boolean required, String fileTemplateNo, boolean eSign,
		String deliverableType, int deliverableAction,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDossierPart(groupId, dossierPartId, templateNo,
			partNo, partName, partTip, partType, multiple, formScript,
			formReport, sampleData, required, fileTemplateNo, eSign,
			deliverableType, deliverableAction, context);
	}

	public static org.opencps.dossiermgt.model.DossierPart updateDossierPartDB(
		long userId, long groupId, String templateNo, String partNo,
		String partName, String partTip, Integer partType, boolean multiple,
		String formScript, String formReport, boolean required, boolean esign,
		String fileTemplateNo, String deliverableType,
		Integer deliverableAction, boolean eForm, String sampleData,
		Integer fileMark,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDossierPartDB(userId, groupId, templateNo, partNo,
			partName, partTip, partType, multiple, formScript, formReport,
			required, esign, fileTemplateNo, deliverableType,
			deliverableAction, eForm, sampleData, fileMark, serviceContext);
	}

	public static DossierPartLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierPartLocalService, DossierPartLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierPartLocalService.class);

		ServiceTracker<DossierPartLocalService, DossierPartLocalService> serviceTracker =
			new ServiceTracker<DossierPartLocalService, DossierPartLocalService>(bundle.getBundleContext(),
				DossierPartLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}