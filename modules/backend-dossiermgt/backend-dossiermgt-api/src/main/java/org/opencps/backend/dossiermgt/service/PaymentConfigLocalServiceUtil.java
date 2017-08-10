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

package org.opencps.backend.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for PaymentConfig. This utility wraps
 * {@link org.opencps.backend.dossiermgt.service.impl.PaymentConfigLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see PaymentConfigLocalService
 * @see org.opencps.backend.dossiermgt.service.base.PaymentConfigLocalServiceBaseImpl
 * @see org.opencps.backend.dossiermgt.service.impl.PaymentConfigLocalServiceImpl
 * @generated
 */
@ProviderType
public class PaymentConfigLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.backend.dossiermgt.service.impl.PaymentConfigLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of payment configs.
	*
	* @return the number of payment configs
	*/
	public static int getPaymentConfigsCount() {
		return getService().getPaymentConfigsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the payment configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @return the range of payment configs
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.PaymentConfig> getPaymentConfigs(
		int start, int end) {
		return getService().getPaymentConfigs(start, end);
	}

	/**
	* Returns all the payment configs matching the UUID and company.
	*
	* @param uuid the UUID of the payment configs
	* @param companyId the primary key of the company
	* @return the matching payment configs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.PaymentConfig> getPaymentConfigsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getPaymentConfigsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of payment configs matching the UUID and company.
	*
	* @param uuid the UUID of the payment configs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching payment configs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.PaymentConfig> getPaymentConfigsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.PaymentConfig> orderByComparator) {
		return getService()
				   .getPaymentConfigsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
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

	/**
	* Adds the payment config to the database. Also notifies the appropriate model listeners.
	*
	* @param paymentConfig the payment config
	* @return the payment config that was added
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentConfig addPaymentConfig(
		org.opencps.backend.dossiermgt.model.PaymentConfig paymentConfig) {
		return getService().addPaymentConfig(paymentConfig);
	}

	/**
	* Creates a new payment config with the primary key. Does not add the payment config to the database.
	*
	* @param paymentConfigId the primary key for the new payment config
	* @return the new payment config
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentConfig createPaymentConfig(
		long paymentConfigId) {
		return getService().createPaymentConfig(paymentConfigId);
	}

	/**
	* Deletes the payment config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentConfigId the primary key of the payment config
	* @return the payment config that was removed
	* @throws PortalException if a payment config with the primary key could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentConfig deletePaymentConfig(
		long paymentConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePaymentConfig(paymentConfigId);
	}

	/**
	* Deletes the payment config from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentConfig the payment config
	* @return the payment config that was removed
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentConfig deletePaymentConfig(
		org.opencps.backend.dossiermgt.model.PaymentConfig paymentConfig) {
		return getService().deletePaymentConfig(paymentConfig);
	}

	public static org.opencps.backend.dossiermgt.model.PaymentConfig fetchPaymentConfig(
		long paymentConfigId) {
		return getService().fetchPaymentConfig(paymentConfigId);
	}

	/**
	* Returns the payment config matching the UUID and group.
	*
	* @param uuid the payment config's UUID
	* @param groupId the primary key of the group
	* @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentConfig fetchPaymentConfigByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchPaymentConfigByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the payment config with the primary key.
	*
	* @param paymentConfigId the primary key of the payment config
	* @return the payment config
	* @throws PortalException if a payment config with the primary key could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentConfig getPaymentConfig(
		long paymentConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPaymentConfig(paymentConfigId);
	}

	/**
	* Returns the payment config matching the UUID and group.
	*
	* @param uuid the payment config's UUID
	* @param groupId the primary key of the group
	* @return the matching payment config
	* @throws PortalException if a matching payment config could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentConfig getPaymentConfigByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPaymentConfigByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the payment config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentConfig the payment config
	* @return the payment config that was updated
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentConfig updatePaymentConfig(
		org.opencps.backend.dossiermgt.model.PaymentConfig paymentConfig) {
		return getService().updatePaymentConfig(paymentConfig);
	}

	public static PaymentConfigLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PaymentConfigLocalService, PaymentConfigLocalService> _serviceTracker =
		ServiceTrackerFactory.open(PaymentConfigLocalService.class);
}