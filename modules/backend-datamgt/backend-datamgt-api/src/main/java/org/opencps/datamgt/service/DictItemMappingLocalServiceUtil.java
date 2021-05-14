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

package org.opencps.datamgt.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DictItemMapping. This utility wraps
 * {@link org.opencps.datamgt.service.impl.DictItemMappingLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see DictItemMappingLocalService
 * @see org.opencps.datamgt.service.base.DictItemMappingLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.impl.DictItemMappingLocalServiceImpl
 * @generated
 */
@ProviderType
public class DictItemMappingLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.datamgt.service.impl.DictItemMappingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dict item mapping to the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemMapping the dict item mapping
	* @return the dict item mapping that was added
	*/
	public static org.opencps.datamgt.model.DictItemMapping addDictItemMapping(
		org.opencps.datamgt.model.DictItemMapping dictItemMapping) {
		return getService().addDictItemMapping(dictItemMapping);
	}

	public static org.opencps.datamgt.model.DictItemMapping adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.datamgt.model.DictItemMapping adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new dict item mapping with the primary key. Does not add the dict item mapping to the database.
	*
	* @param mappingId the primary key for the new dict item mapping
	* @return the new dict item mapping
	*/
	public static org.opencps.datamgt.model.DictItemMapping createDictItemMapping(
		long mappingId) {
		return getService().createDictItemMapping(mappingId);
	}

	public static org.opencps.datamgt.model.DictItemMapping createDictItemMapping(
		long companyId, long groupId, long userId, String itemCode,
		String itemCodeDVCQG, long collectionId) {
		return getService()
				   .createDictItemMapping(companyId, groupId, userId, itemCode,
			itemCodeDVCQG, collectionId);
	}

	/**
	* Deletes the dict item mapping from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemMapping the dict item mapping
	* @return the dict item mapping that was removed
	*/
	public static org.opencps.datamgt.model.DictItemMapping deleteDictItemMapping(
		org.opencps.datamgt.model.DictItemMapping dictItemMapping) {
		return getService().deleteDictItemMapping(dictItemMapping);
	}

	/**
	* Deletes the dict item mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mappingId the primary key of the dict item mapping
	* @return the dict item mapping that was removed
	* @throws PortalException if a dict item mapping with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.DictItemMapping deleteDictItemMapping(
		long mappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDictItemMapping(mappingId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.datamgt.model.DictItemMapping fetchByF_GID_IC_CID(
		long groupId, String itemCode, long collectionId) {
		return getService().fetchByF_GID_IC_CID(groupId, itemCode, collectionId);
	}

	public static org.opencps.datamgt.model.DictItemMapping fetchByF_GID_ICDVCQG_CID(
		long groupId, String itemCodeDVCQG, long collectionId) {
		return getService()
				   .fetchByF_GID_ICDVCQG_CID(groupId, itemCodeDVCQG,
			collectionId);
	}

	public static org.opencps.datamgt.model.DictItemMapping fetchByF_IC(
		String itemCode) {
		return getService().fetchByF_IC(itemCode);
	}

	public static org.opencps.datamgt.model.DictItemMapping fetchDictItemMapping(
		long mappingId) {
		return getService().fetchDictItemMapping(mappingId);
	}

	public static java.util.List<org.opencps.datamgt.model.DictItemMapping> findByF_GID_CID(
		long groupId, long collectionId) {
		return getService().findByF_GID_CID(groupId, collectionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the dict item mapping with the primary key.
	*
	* @param mappingId the primary key of the dict item mapping
	* @return the dict item mapping
	* @throws PortalException if a dict item mapping with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.DictItemMapping getDictItemMapping(
		long mappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictItemMapping(mappingId);
	}

	/**
	* Returns a range of all the dict item mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @return the range of dict item mappings
	*/
	public static java.util.List<org.opencps.datamgt.model.DictItemMapping> getDictItemMappings(
		int start, int end) {
		return getService().getDictItemMappings(start, end);
	}

	/**
	* Returns the number of dict item mappings.
	*
	* @return the number of dict item mappings
	*/
	public static int getDictItemMappingsCount() {
		return getService().getDictItemMappingsCount();
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

	/**
	* Updates the dict item mapping in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictItemMapping the dict item mapping
	* @return the dict item mapping that was updated
	*/
	public static org.opencps.datamgt.model.DictItemMapping updateDictItemMapping(
		org.opencps.datamgt.model.DictItemMapping dictItemMapping) {
		return getService().updateDictItemMapping(dictItemMapping);
	}

	public static DictItemMappingLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictItemMappingLocalService, DictItemMappingLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DictItemMappingLocalService.class);

		ServiceTracker<DictItemMappingLocalService, DictItemMappingLocalService> serviceTracker =
			new ServiceTracker<DictItemMappingLocalService, DictItemMappingLocalService>(bundle.getBundleContext(),
				DictItemMappingLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}