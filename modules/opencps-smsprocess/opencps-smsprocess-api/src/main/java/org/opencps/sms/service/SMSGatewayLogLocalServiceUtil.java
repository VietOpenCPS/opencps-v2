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

package org.opencps.sms.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for SMSGatewayLog. This utility wraps
 * {@link org.opencps.sms.service.impl.SMSGatewayLogLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoa
 * @see SMSGatewayLogLocalService
 * @see org.opencps.sms.service.base.SMSGatewayLogLocalServiceBaseImpl
 * @see org.opencps.sms.service.impl.SMSGatewayLogLocalServiceImpl
 * @generated
 */
@ProviderType
public class SMSGatewayLogLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.sms.service.impl.SMSGatewayLogLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the sms gateway log to the database. Also notifies the appropriate model listeners.
	*
	* @param smsGatewayLog the sms gateway log
	* @return the sms gateway log that was added
	*/
	public static org.opencps.sms.model.SMSGatewayLog addSMSGatewayLog(
		org.opencps.sms.model.SMSGatewayLog smsGatewayLog) {
		return getService().addSMSGatewayLog(smsGatewayLog);
	}

	/**
	* Creates a new sms gateway log with the primary key. Does not add the sms gateway log to the database.
	*
	* @param smsId the primary key for the new sms gateway log
	* @return the new sms gateway log
	*/
	public static org.opencps.sms.model.SMSGatewayLog createSMSGatewayLog(
		long smsId) {
		return getService().createSMSGatewayLog(smsId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the sms gateway log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param smsId the primary key of the sms gateway log
	* @return the sms gateway log that was removed
	* @throws PortalException if a sms gateway log with the primary key could not be found
	*/
	public static org.opencps.sms.model.SMSGatewayLog deleteSMSGatewayLog(
		long smsId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSMSGatewayLog(smsId);
	}

	/**
	* Deletes the sms gateway log from the database. Also notifies the appropriate model listeners.
	*
	* @param smsGatewayLog the sms gateway log
	* @return the sms gateway log that was removed
	*/
	public static org.opencps.sms.model.SMSGatewayLog deleteSMSGatewayLog(
		org.opencps.sms.model.SMSGatewayLog smsGatewayLog) {
		return getService().deleteSMSGatewayLog(smsGatewayLog);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.sms.model.impl.SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.sms.model.impl.SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.sms.model.SMSGatewayLog fetchSMSGatewayLog(
		long smsId) {
		return getService().fetchSMSGatewayLog(smsId);
	}

	/**
	* Returns the sms gateway log matching the UUID and group.
	*
	* @param uuid the sms gateway log's UUID
	* @param groupId the primary key of the group
	* @return the matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	*/
	public static org.opencps.sms.model.SMSGatewayLog fetchSMSGatewayLogByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchSMSGatewayLogByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
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

	/**
	* Returns the sms gateway log with the primary key.
	*
	* @param smsId the primary key of the sms gateway log
	* @return the sms gateway log
	* @throws PortalException if a sms gateway log with the primary key could not be found
	*/
	public static org.opencps.sms.model.SMSGatewayLog getSMSGatewayLog(
		long smsId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSMSGatewayLog(smsId);
	}

	/**
	* Returns the sms gateway log matching the UUID and group.
	*
	* @param uuid the sms gateway log's UUID
	* @param groupId the primary key of the group
	* @return the matching sms gateway log
	* @throws PortalException if a matching sms gateway log could not be found
	*/
	public static org.opencps.sms.model.SMSGatewayLog getSMSGatewayLogByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSMSGatewayLogByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the sms gateway logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.sms.model.impl.SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @return the range of sms gateway logs
	*/
	public static java.util.List<org.opencps.sms.model.SMSGatewayLog> getSMSGatewayLogs(
		int start, int end) {
		return getService().getSMSGatewayLogs(start, end);
	}

	/**
	* Returns all the sms gateway logs matching the UUID and company.
	*
	* @param uuid the UUID of the sms gateway logs
	* @param companyId the primary key of the company
	* @return the matching sms gateway logs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.sms.model.SMSGatewayLog> getSMSGatewayLogsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getSMSGatewayLogsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of sms gateway logs matching the UUID and company.
	*
	* @param uuid the UUID of the sms gateway logs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching sms gateway logs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.sms.model.SMSGatewayLog> getSMSGatewayLogsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.sms.model.SMSGatewayLog> orderByComparator) {
		return getService()
				   .getSMSGatewayLogsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of sms gateway logs.
	*
	* @return the number of sms gateway logs
	*/
	public static int getSMSGatewayLogsCount() {
		return getService().getSMSGatewayLogsCount();
	}

	/**
	* Updates the sms gateway log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param smsGatewayLog the sms gateway log
	* @return the sms gateway log that was updated
	*/
	public static org.opencps.sms.model.SMSGatewayLog updateSMSGatewayLog(
		org.opencps.sms.model.SMSGatewayLog smsGatewayLog) {
		return getService().updateSMSGatewayLog(smsGatewayLog);
	}

	public static SMSGatewayLogLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SMSGatewayLogLocalService, SMSGatewayLogLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SMSGatewayLogLocalService.class);

		ServiceTracker<SMSGatewayLogLocalService, SMSGatewayLogLocalService> serviceTracker =
			new ServiceTracker<SMSGatewayLogLocalService, SMSGatewayLogLocalService>(bundle.getBundleContext(),
				SMSGatewayLogLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}