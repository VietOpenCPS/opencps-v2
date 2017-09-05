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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PaymentConfigLocalService}.
 *
 * @author huymq
 * @see PaymentConfigLocalService
 * @generated
 */
@ProviderType
public class PaymentConfigLocalServiceWrapper
	implements PaymentConfigLocalService,
		ServiceWrapper<PaymentConfigLocalService> {
	public PaymentConfigLocalServiceWrapper(
		PaymentConfigLocalService paymentConfigLocalService) {
		_paymentConfigLocalService = paymentConfigLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _paymentConfigLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _paymentConfigLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _paymentConfigLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _paymentConfigLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _paymentConfigLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _paymentConfigLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of payment configs.
	*
	* @return the number of payment configs
	*/
	@Override
	public int getPaymentConfigsCount() {
		return _paymentConfigLocalService.getPaymentConfigsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _paymentConfigLocalService.getOSGiServiceIdentifier();
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
		return _paymentConfigLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _paymentConfigLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _paymentConfigLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.PaymentConfig> getPaymentConfigs(
		int start, int end) {
		return _paymentConfigLocalService.getPaymentConfigs(start, end);
	}

	/**
	* Returns all the payment configs matching the UUID and company.
	*
	* @param uuid the UUID of the payment configs
	* @param companyId the primary key of the company
	* @return the matching payment configs, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.PaymentConfig> getPaymentConfigsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _paymentConfigLocalService.getPaymentConfigsByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.PaymentConfig> getPaymentConfigsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.PaymentConfig> orderByComparator) {
		return _paymentConfigLocalService.getPaymentConfigsByUuidAndCompanyId(uuid,
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
		return _paymentConfigLocalService.dynamicQueryCount(dynamicQuery);
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
		return _paymentConfigLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Adds the payment config to the database. Also notifies the appropriate model listeners.
	*
	* @param paymentConfig the payment config
	* @return the payment config that was added
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.PaymentConfig addPaymentConfig(
		org.opencps.backend.dossiermgt.model.PaymentConfig paymentConfig) {
		return _paymentConfigLocalService.addPaymentConfig(paymentConfig);
	}

	/**
	* Creates a new payment config with the primary key. Does not add the payment config to the database.
	*
	* @param paymentConfigId the primary key for the new payment config
	* @return the new payment config
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.PaymentConfig createPaymentConfig(
		long paymentConfigId) {
		return _paymentConfigLocalService.createPaymentConfig(paymentConfigId);
	}

	/**
	* Deletes the payment config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentConfigId the primary key of the payment config
	* @return the payment config that was removed
	* @throws PortalException if a payment config with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.PaymentConfig deletePaymentConfig(
		long paymentConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _paymentConfigLocalService.deletePaymentConfig(paymentConfigId);
	}

	/**
	* Deletes the payment config from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentConfig the payment config
	* @return the payment config that was removed
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.PaymentConfig deletePaymentConfig(
		org.opencps.backend.dossiermgt.model.PaymentConfig paymentConfig) {
		return _paymentConfigLocalService.deletePaymentConfig(paymentConfig);
	}

	@Override
	public org.opencps.backend.dossiermgt.model.PaymentConfig fetchPaymentConfig(
		long paymentConfigId) {
		return _paymentConfigLocalService.fetchPaymentConfig(paymentConfigId);
	}

	/**
	* Returns the payment config matching the UUID and group.
	*
	* @param uuid the payment config's UUID
	* @param groupId the primary key of the group
	* @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.PaymentConfig fetchPaymentConfigByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _paymentConfigLocalService.fetchPaymentConfigByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the payment config with the primary key.
	*
	* @param paymentConfigId the primary key of the payment config
	* @return the payment config
	* @throws PortalException if a payment config with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.PaymentConfig getPaymentConfig(
		long paymentConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _paymentConfigLocalService.getPaymentConfig(paymentConfigId);
	}

	/**
	* Returns the payment config matching the UUID and group.
	*
	* @param uuid the payment config's UUID
	* @param groupId the primary key of the group
	* @return the matching payment config
	* @throws PortalException if a matching payment config could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.PaymentConfig getPaymentConfigByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _paymentConfigLocalService.getPaymentConfigByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the payment config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentConfig the payment config
	* @return the payment config that was updated
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.PaymentConfig updatePaymentConfig(
		org.opencps.backend.dossiermgt.model.PaymentConfig paymentConfig) {
		return _paymentConfigLocalService.updatePaymentConfig(paymentConfig);
	}

	@Override
	public PaymentConfigLocalService getWrappedService() {
		return _paymentConfigLocalService;
	}

	@Override
	public void setWrappedService(
		PaymentConfigLocalService paymentConfigLocalService) {
		_paymentConfigLocalService = paymentConfigLocalService;
	}

	private PaymentConfigLocalService _paymentConfigLocalService;
}