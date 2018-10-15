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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HolidayLocalService}.
 *
 * @author khoavu
 * @see HolidayLocalService
 * @generated
 */
@ProviderType
public class HolidayLocalServiceWrapper implements HolidayLocalService,
	ServiceWrapper<HolidayLocalService> {
	public HolidayLocalServiceWrapper(HolidayLocalService holidayLocalService) {
		_holidayLocalService = holidayLocalService;
	}

	/**
	* Adds the holiday to the database. Also notifies the appropriate model listeners.
	*
	* @param holiday the holiday
	* @return the holiday that was added
	*/
	@Override
	public org.opencps.datamgt.model.Holiday addHoliday(
		org.opencps.datamgt.model.Holiday holiday) {
		return _holidayLocalService.addHoliday(holiday);
	}

	@Override
	public org.opencps.datamgt.model.Holiday addHoliday(long userId,
		long groupId, java.util.Date holidayDate, String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return _holidayLocalService.addHoliday(userId, groupId, holidayDate,
			description, serviceContext);
	}

	@Override
	public long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _holidayLocalService.countLuceneSearchEngine(params,
			searchContext);
	}

	/**
	* Creates a new holiday with the primary key. Does not add the holiday to the database.
	*
	* @param holidayId the primary key for the new holiday
	* @return the new holiday
	*/
	@Override
	public org.opencps.datamgt.model.Holiday createHoliday(long holidayId) {
		return _holidayLocalService.createHoliday(holidayId);
	}

	/**
	* Deletes the holiday from the database. Also notifies the appropriate model listeners.
	*
	* @param holiday the holiday
	* @return the holiday that was removed
	*/
	@Override
	public org.opencps.datamgt.model.Holiday deleteHoliday(
		org.opencps.datamgt.model.Holiday holiday) {
		return _holidayLocalService.deleteHoliday(holiday);
	}

	/**
	* Deletes the holiday with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayId the primary key of the holiday
	* @return the holiday that was removed
	* @throws PortalException if a holiday with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Holiday deleteHoliday(long holidayId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayLocalService.deleteHoliday(holidayId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Override
	public org.opencps.datamgt.model.Holiday deleteHoliday(long holidayId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return _holidayLocalService.deleteHoliday(holidayId, serviceContext);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _holidayLocalService.dynamicQuery();
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
		return _holidayLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _holidayLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _holidayLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _holidayLocalService.dynamicQueryCount(dynamicQuery);
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
		return _holidayLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.datamgt.model.Holiday fetchByF_holidayDate(
		long groupId, java.util.Date holidayDate) {
		return _holidayLocalService.fetchByF_holidayDate(groupId, holidayDate);
	}

	@Override
	public org.opencps.datamgt.model.Holiday fetchHoliday(long holidayId) {
		return _holidayLocalService.fetchHoliday(holidayId);
	}

	/**
	* Returns the holiday matching the UUID and group.
	*
	* @param uuid the holiday's UUID
	* @param groupId the primary key of the group
	* @return the matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Holiday fetchHolidayByUuidAndGroupId(
		String uuid, long groupId) {
		return _holidayLocalService.fetchHolidayByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _holidayLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _holidayLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	* Returns a range of all the holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @return the range of holidaies
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.Holiday> getHolidaies(
		int start, int end) {
		return _holidayLocalService.getHolidaies(start, end);
	}

	/**
	* Returns all the holidaies matching the UUID and company.
	*
	* @param uuid the UUID of the holidaies
	* @param companyId the primary key of the company
	* @return the matching holidaies, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.Holiday> getHolidaiesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _holidayLocalService.getHolidaiesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of holidaies matching the UUID and company.
	*
	* @param uuid the UUID of the holidaies
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching holidaies, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.Holiday> getHolidaiesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.Holiday> orderByComparator) {
		return _holidayLocalService.getHolidaiesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of holidaies.
	*
	* @return the number of holidaies
	*/
	@Override
	public int getHolidaiesCount() {
		return _holidayLocalService.getHolidaiesCount();
	}

	/**
	* Returns the holiday with the primary key.
	*
	* @param holidayId the primary key of the holiday
	* @return the holiday
	* @throws PortalException if a holiday with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Holiday getHoliday(long holidayId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayLocalService.getHoliday(holidayId);
	}

	@Override
	public java.util.List<org.opencps.datamgt.model.Holiday> getHolidayByGroupId(
		long groupId) {
		return _holidayLocalService.getHolidayByGroupId(groupId);
	}

	/**
	* Returns the holiday matching the UUID and group.
	*
	* @param uuid the holiday's UUID
	* @param groupId the primary key of the group
	* @return the matching holiday
	* @throws PortalException if a matching holiday could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Holiday getHolidayByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayLocalService.getHolidayByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _holidayLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _holidayLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _holidayLocalService.luceneSearchEngine(params, sorts, start,
			end, searchContext);
	}

	/**
	* Updates the holiday in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param holiday the holiday
	* @return the holiday that was updated
	*/
	@Override
	public org.opencps.datamgt.model.Holiday updateHoliday(
		org.opencps.datamgt.model.Holiday holiday) {
		return _holidayLocalService.updateHoliday(holiday);
	}

	@Override
	public org.opencps.datamgt.model.Holiday updateHoliday(long userId,
		long holidayId, java.util.Date holidayDate, String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return _holidayLocalService.updateHoliday(userId, holidayId,
			holidayDate, description, serviceContext);
	}

	@Override
	public HolidayLocalService getWrappedService() {
		return _holidayLocalService;
	}

	@Override
	public void setWrappedService(HolidayLocalService holidayLocalService) {
		_holidayLocalService = holidayLocalService;
	}

	private HolidayLocalService _holidayLocalService;
}