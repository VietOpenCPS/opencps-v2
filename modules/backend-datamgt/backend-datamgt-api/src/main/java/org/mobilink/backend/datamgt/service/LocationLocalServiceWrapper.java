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

package org.mobilink.backend.datamgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LocationLocalService}.
 *
 * @author Binhth
 * @see LocationLocalService
 * @generated
 */
@ProviderType
public class LocationLocalServiceWrapper implements LocationLocalService,
	ServiceWrapper<LocationLocalService> {
	public LocationLocalServiceWrapper(
		LocationLocalService locationLocalService) {
		_locationLocalService = locationLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _locationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _locationLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _locationLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _locationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _locationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _locationLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _locationLocalService.luceneSearchEngine(params, sorts, start,
			end, searchContext);
	}

	/**
	* Returns the number of locations.
	*
	* @return the number of locations
	*/
	@Override
	public int getLocationsCount() {
		return _locationLocalService.getLocationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _locationLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _locationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.LocationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _locationLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.LocationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _locationLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the locations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.LocationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of locations
	* @param end the upper bound of the range of locations (not inclusive)
	* @return the range of locations
	*/
	@Override
	public java.util.List<org.mobilink.backend.datamgt.model.Location> getLocations(
		int start, int end) {
		return _locationLocalService.getLocations(start, end);
	}

	/**
	* Returns all the locations matching the UUID and company.
	*
	* @param uuid the UUID of the locations
	* @param companyId the primary key of the company
	* @return the matching locations, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.mobilink.backend.datamgt.model.Location> getLocationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _locationLocalService.getLocationsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of locations matching the UUID and company.
	*
	* @param uuid the UUID of the locations
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of locations
	* @param end the upper bound of the range of locations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching locations, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.mobilink.backend.datamgt.model.Location> getLocationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.mobilink.backend.datamgt.model.Location> orderByComparator) {
		return _locationLocalService.getLocationsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _locationLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _locationLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.mobilink.backend.datamgt.model.Location addLocation(
		long userId, long groupId, java.lang.String location,
		java.lang.String geolocation,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _locationLocalService.addLocation(userId, groupId, location,
			geolocation, serviceContext);
	}

	/**
	* Adds the location to the database. Also notifies the appropriate model listeners.
	*
	* @param location the location
	* @return the location that was added
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Location addLocation(
		org.mobilink.backend.datamgt.model.Location location) {
		return _locationLocalService.addLocation(location);
	}

	/**
	* Creates a new location with the primary key. Does not add the location to the database.
	*
	* @param locationId the primary key for the new location
	* @return the new location
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Location createLocation(
		long locationId) {
		return _locationLocalService.createLocation(locationId);
	}

	/**
	* Deletes the location with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param locationId the primary key of the location
	* @return the location that was removed
	* @throws PortalException if a location with the primary key could not be found
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Location deleteLocation(
		long locationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _locationLocalService.deleteLocation(locationId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Location deleteLocation(
		long locationId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _locationLocalService.deleteLocation(locationId, serviceContext);
	}

	/**
	* Deletes the location from the database. Also notifies the appropriate model listeners.
	*
	* @param location the location
	* @return the location that was removed
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Location deleteLocation(
		org.mobilink.backend.datamgt.model.Location location) {
		return _locationLocalService.deleteLocation(location);
	}

	@Override
	public org.mobilink.backend.datamgt.model.Location fetchLocation(
		long locationId) {
		return _locationLocalService.fetchLocation(locationId);
	}

	/**
	* Returns the location matching the UUID and group.
	*
	* @param uuid the location's UUID
	* @param groupId the primary key of the group
	* @return the matching location, or <code>null</code> if a matching location could not be found
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Location fetchLocationByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _locationLocalService.fetchLocationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the location with the primary key.
	*
	* @param locationId the primary key of the location
	* @return the location
	* @throws PortalException if a location with the primary key could not be found
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Location getLocation(
		long locationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _locationLocalService.getLocation(locationId);
	}

	/**
	* Returns the location matching the UUID and group.
	*
	* @param uuid the location's UUID
	* @param groupId the primary key of the group
	* @return the matching location
	* @throws PortalException if a matching location could not be found
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Location getLocationByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _locationLocalService.getLocationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* @param userId
	* @param dictCollectionId
	* @param fullName
	* @param companyName
	* @param telNo
	* @param email
	* @param mobilinkId
	* @param userMappingId
	* @param outSide
	* @param isOrg
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Location updateLocation(
		long userId, long groupId, long locationId, java.lang.String location,
		java.lang.String geolocation,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _locationLocalService.updateLocation(userId, groupId,
			locationId, location, geolocation, serviceContext);
	}

	/**
	* Updates the location in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param location the location
	* @return the location that was updated
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Location updateLocation(
		org.mobilink.backend.datamgt.model.Location location) {
		return _locationLocalService.updateLocation(location);
	}

	@Override
	public LocationLocalService getWrappedService() {
		return _locationLocalService;
	}

	@Override
	public void setWrappedService(LocationLocalService locationLocalService) {
		_locationLocalService = locationLocalService;
	}

	private LocationLocalService _locationLocalService;
}