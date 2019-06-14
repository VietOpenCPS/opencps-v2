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
 * Provides the local service utility for Booking. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.BookingLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see BookingLocalService
 * @see org.opencps.dossiermgt.service.base.BookingLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.BookingLocalServiceImpl
 * @generated
 */
@ProviderType
public class BookingLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.BookingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the booking to the database. Also notifies the appropriate model listeners.
	*
	* @param booking the booking
	* @return the booking that was added
	*/
	public static org.opencps.dossiermgt.model.Booking addBooking(
		org.opencps.dossiermgt.model.Booking booking) {
		return getService().addBooking(booking);
	}

	public static long countLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLucene(params, searchContext);
	}

	/**
	* Creates a new booking with the primary key. Does not add the booking to the database.
	*
	* @param bookingId the primary key for the new booking
	* @return the new booking
	*/
	public static org.opencps.dossiermgt.model.Booking createBooking(
		long bookingId) {
		return getService().createBooking(bookingId);
	}

	/**
	* Deletes the booking from the database. Also notifies the appropriate model listeners.
	*
	* @param booking the booking
	* @return the booking that was removed
	*/
	public static org.opencps.dossiermgt.model.Booking deleteBooking(
		org.opencps.dossiermgt.model.Booking booking) {
		return getService().deleteBooking(booking);
	}

	/**
	* Deletes the booking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bookingId the primary key of the booking
	* @return the booking that was removed
	* @throws PortalException if a booking with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.Booking deleteBooking(
		long bookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteBooking(bookingId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.Booking fetchBooking(
		long bookingId) {
		return getService().fetchBooking(bookingId);
	}

	/**
	* Returns the booking matching the UUID and group.
	*
	* @param uuid the booking's UUID
	* @param groupId the primary key of the group
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static org.opencps.dossiermgt.model.Booking fetchBookingByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchBookingByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the booking with the primary key.
	*
	* @param bookingId the primary key of the booking
	* @return the booking
	* @throws PortalException if a booking with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.Booking getBooking(
		long bookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBooking(bookingId);
	}

	/**
	* Returns the booking matching the UUID and group.
	*
	* @param uuid the booking's UUID
	* @param groupId the primary key of the group
	* @return the matching booking
	* @throws PortalException if a matching booking could not be found
	*/
	public static org.opencps.dossiermgt.model.Booking getBookingByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBookingByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @return the range of bookings
	*/
	public static java.util.List<org.opencps.dossiermgt.model.Booking> getBookings(
		int start, int end) {
		return getService().getBookings(start, end);
	}

	/**
	* Returns all the bookings matching the UUID and company.
	*
	* @param uuid the UUID of the bookings
	* @param companyId the primary key of the company
	* @return the matching bookings, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.Booking> getBookingsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getBookingsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of bookings matching the UUID and company.
	*
	* @param uuid the UUID of the bookings
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching bookings, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.Booking> getBookingsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.Booking> orderByComparator) {
		return getService()
				   .getBookingsByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of bookings.
	*
	* @return the number of bookings
	*/
	public static int getBookingsCount() {
		return getService().getBookingsCount();
	}

	public static org.opencps.dossiermgt.model.Booking getByClassName_PK(
		String className, long classPK) {
		return getService().getByClassName_PK(className, classPK);
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
	* Updates the booking in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param booking the booking
	* @return the booking that was updated
	*/
	public static org.opencps.dossiermgt.model.Booking updateBooking(
		org.opencps.dossiermgt.model.Booking booking) {
		return getService().updateBooking(booking);
	}

	public static org.opencps.dossiermgt.model.Booking updateBooking(
		long userId, long groupId, long bookingId, String className,
		long classPK, String serviceCode, String codeNumber,
		String bookingName, String gateNumber, Integer state,
		java.util.Date checkinDate, java.util.Date bookingDate,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService()
				   .updateBooking(userId, groupId, bookingId, className,
			classPK, serviceCode, codeNumber, bookingName, gateNumber, state,
			checkinDate, bookingDate, serviceContext);
	}

	public static BookingLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BookingLocalService, BookingLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(BookingLocalService.class);

		ServiceTracker<BookingLocalService, BookingLocalService> serviceTracker = new ServiceTracker<BookingLocalService, BookingLocalService>(bundle.getBundleContext(),
				BookingLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}