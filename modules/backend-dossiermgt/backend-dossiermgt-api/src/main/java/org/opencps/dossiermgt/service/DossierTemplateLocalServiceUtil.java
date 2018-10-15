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
 * Provides the local service utility for DossierTemplate. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.DossierTemplateLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see DossierTemplateLocalService
 * @see org.opencps.dossiermgt.service.base.DossierTemplateLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.DossierTemplateLocalServiceImpl
 * @generated
 */
@ProviderType
public class DossierTemplateLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.DossierTemplateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dossier template to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierTemplate the dossier template
	* @return the dossier template that was added
	*/
	public static org.opencps.dossiermgt.model.DossierTemplate addDossierTemplate(
		org.opencps.dossiermgt.model.DossierTemplate dossierTemplate) {
		return getService().addDossierTemplate(dossierTemplate);
	}

	public static long countLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLucene(params, searchContext);
	}

	/**
	* Creates a new dossier template with the primary key. Does not add the dossier template to the database.
	*
	* @param dossierTemplateId the primary key for the new dossier template
	* @return the new dossier template
	*/
	public static org.opencps.dossiermgt.model.DossierTemplate createDossierTemplate(
		long dossierTemplateId) {
		return getService().createDossierTemplate(dossierTemplateId);
	}

	/**
	* Deletes the dossier template from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierTemplate the dossier template
	* @return the dossier template that was removed
	*/
	public static org.opencps.dossiermgt.model.DossierTemplate deleteDossierTemplate(
		org.opencps.dossiermgt.model.DossierTemplate dossierTemplate) {
		return getService().deleteDossierTemplate(dossierTemplate);
	}

	/**
	* Deletes the dossier template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierTemplateId the primary key of the dossier template
	* @return the dossier template that was removed
	* @throws PortalException if a dossier template with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierTemplate deleteDossierTemplate(
		long dossierTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDossierTemplate(dossierTemplateId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.DossierTemplate fetchDossierTemplate(
		long dossierTemplateId) {
		return getService().fetchDossierTemplate(dossierTemplateId);
	}

	/**
	* Returns the dossier template matching the UUID and group.
	*
	* @param uuid the dossier template's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier template, or <code>null</code> if a matching dossier template could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierTemplate fetchDossierTemplateByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchDossierTemplateByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.dossiermgt.model.DossierTemplate getByTemplateNo(
		long groupId, String templateNo)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getByTemplateNo(groupId, templateNo);
	}

	/**
	* Returns the dossier template with the primary key.
	*
	* @param dossierTemplateId the primary key of the dossier template
	* @return the dossier template
	* @throws PortalException if a dossier template with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierTemplate getDossierTemplate(
		long dossierTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDossierTemplate(dossierTemplateId);
	}

	/**
	* Returns the dossier template matching the UUID and group.
	*
	* @param uuid the dossier template's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier template
	* @throws PortalException if a matching dossier template could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierTemplate getDossierTemplateByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDossierTemplateByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the dossier templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier templates
	* @param end the upper bound of the range of dossier templates (not inclusive)
	* @return the range of dossier templates
	*/
	public static java.util.List<org.opencps.dossiermgt.model.DossierTemplate> getDossierTemplates(
		int start, int end) {
		return getService().getDossierTemplates(start, end);
	}

	/**
	* Returns all the dossier templates matching the UUID and company.
	*
	* @param uuid the UUID of the dossier templates
	* @param companyId the primary key of the company
	* @return the matching dossier templates, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.DossierTemplate> getDossierTemplatesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getDossierTemplatesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of dossier templates matching the UUID and company.
	*
	* @param uuid the UUID of the dossier templates
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier templates
	* @param end the upper bound of the range of dossier templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier templates, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.DossierTemplate> getDossierTemplatesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.DossierTemplate> orderByComparator) {
		return getService()
				   .getDossierTemplatesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of dossier templates.
	*
	* @return the number of dossier templates
	*/
	public static int getDossierTemplatesCount() {
		return getService().getDossierTemplatesCount();
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

	public static org.opencps.dossiermgt.model.DossierTemplate removeDossierTemplate(
		long dossierTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeDossierTemplate(dossierTemplateId);
	}

	/**
	* @author khoavu
	* @param params
	* @param sorts
	* @param start
	* @param end
	* @param searchContext
	* @return
	* @throws ParseException
	* @throws SearchException
	*/
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
	* Updates the dossier template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierTemplate the dossier template
	* @return the dossier template that was updated
	*/
	public static org.opencps.dossiermgt.model.DossierTemplate updateDossierTemplate(
		org.opencps.dossiermgt.model.DossierTemplate dossierTemplate) {
		return getService().updateDossierTemplate(dossierTemplate);
	}

	public static org.opencps.dossiermgt.model.DossierTemplate updateDossierTemplate(
		long groupId, long dossierTemplateId, String templateName,
		String templateNo, String description,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDossierTemplate(groupId, dossierTemplateId,
			templateName, templateNo, description, context);
	}

	public static org.opencps.dossiermgt.model.DossierTemplate updateDossierTemplateDB(
		long userId, long groupId, String templateNo, String templateName,
		String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDossierTemplateDB(userId, groupId, templateNo,
			templateName, description, serviceContext);
	}

	public static DossierTemplateLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierTemplateLocalService, DossierTemplateLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierTemplateLocalService.class);

		ServiceTracker<DossierTemplateLocalService, DossierTemplateLocalService> serviceTracker =
			new ServiceTracker<DossierTemplateLocalService, DossierTemplateLocalService>(bundle.getBundleContext(),
				DossierTemplateLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}