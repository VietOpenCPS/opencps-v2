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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PaymentFeeInfoLocalService}.
 *
 * @author huymq
 * @see PaymentFeeInfoLocalService
 * @generated
 */
@ProviderType
public class PaymentFeeInfoLocalServiceWrapper
	implements PaymentFeeInfoLocalService,
		ServiceWrapper<PaymentFeeInfoLocalService> {
	public PaymentFeeInfoLocalServiceWrapper(
		PaymentFeeInfoLocalService paymentFeeInfoLocalService) {
		_paymentFeeInfoLocalService = paymentFeeInfoLocalService;
	}

	@Override
	public org.opencps.dossiermgt.model.PaymentFeeInfo addPaymentFeeInfo(
		long groupId, long serviceConfigMappingId, String paymentFeeCode,
		String paymentFeeName, String type, String amount,
		com.liferay.portal.kernel.service.ServiceContext context) {
		return _paymentFeeInfoLocalService.addPaymentFeeInfo(groupId,
			serviceConfigMappingId, paymentFeeCode, paymentFeeName, type,
			amount, context);
	}

	/**
	* Adds the payment fee info to the database. Also notifies the appropriate model listeners.
	*
	* @param paymentFeeInfo the payment fee info
	* @return the payment fee info that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.PaymentFeeInfo addPaymentFeeInfo(
		org.opencps.dossiermgt.model.PaymentFeeInfo paymentFeeInfo) {
		return _paymentFeeInfoLocalService.addPaymentFeeInfo(paymentFeeInfo);
	}

	/**
	* Creates a new payment fee info with the primary key. Does not add the payment fee info to the database.
	*
	* @param paymentFeeInfoId the primary key for the new payment fee info
	* @return the new payment fee info
	*/
	@Override
	public org.opencps.dossiermgt.model.PaymentFeeInfo createPaymentFeeInfo(
		long paymentFeeInfoId) {
		return _paymentFeeInfoLocalService.createPaymentFeeInfo(paymentFeeInfoId);
	}

	/**
	* Deletes the payment fee info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentFeeInfoId the primary key of the payment fee info
	* @return the payment fee info that was removed
	* @throws PortalException if a payment fee info with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.PaymentFeeInfo deletePaymentFeeInfo(
		long paymentFeeInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _paymentFeeInfoLocalService.deletePaymentFeeInfo(paymentFeeInfoId);
	}

	/**
	* Deletes the payment fee info from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentFeeInfo the payment fee info
	* @return the payment fee info that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.PaymentFeeInfo deletePaymentFeeInfo(
		org.opencps.dossiermgt.model.PaymentFeeInfo paymentFeeInfo) {
		return _paymentFeeInfoLocalService.deletePaymentFeeInfo(paymentFeeInfo);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _paymentFeeInfoLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _paymentFeeInfoLocalService.dynamicQuery();
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
		return _paymentFeeInfoLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _paymentFeeInfoLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _paymentFeeInfoLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _paymentFeeInfoLocalService.dynamicQueryCount(dynamicQuery);
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
		return _paymentFeeInfoLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.PaymentFeeInfo fetchPaymentFeeInfo(
		long paymentFeeInfoId) {
		return _paymentFeeInfoLocalService.fetchPaymentFeeInfo(paymentFeeInfoId);
	}

	/**
	* Returns the payment fee info matching the UUID and group.
	*
	* @param uuid the payment fee info's UUID
	* @param groupId the primary key of the group
	* @return the matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.PaymentFeeInfo fetchPaymentFeeInfoByUuidAndGroupId(
		String uuid, long groupId) {
		return _paymentFeeInfoLocalService.fetchPaymentFeeInfoByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId) {
		return _paymentFeeInfoLocalService.findByServiceConfigMappingId(serviceConfigMappingId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _paymentFeeInfoLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _paymentFeeInfoLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _paymentFeeInfoLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _paymentFeeInfoLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Returns the payment fee info with the primary key.
	*
	* @param paymentFeeInfoId the primary key of the payment fee info
	* @return the payment fee info
	* @throws PortalException if a payment fee info with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.PaymentFeeInfo getPaymentFeeInfo(
		long paymentFeeInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _paymentFeeInfoLocalService.getPaymentFeeInfo(paymentFeeInfoId);
	}

	/**
	* Returns the payment fee info matching the UUID and group.
	*
	* @param uuid the payment fee info's UUID
	* @param groupId the primary key of the group
	* @return the matching payment fee info
	* @throws PortalException if a matching payment fee info could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.PaymentFeeInfo getPaymentFeeInfoByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _paymentFeeInfoLocalService.getPaymentFeeInfoByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the payment fee infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @return the range of payment fee infos
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.PaymentFeeInfo> getPaymentFeeInfos(
		int start, int end) {
		return _paymentFeeInfoLocalService.getPaymentFeeInfos(start, end);
	}

	/**
	* Returns all the payment fee infos matching the UUID and company.
	*
	* @param uuid the UUID of the payment fee infos
	* @param companyId the primary key of the company
	* @return the matching payment fee infos, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.PaymentFeeInfo> getPaymentFeeInfosByUuidAndCompanyId(
		String uuid, long companyId) {
		return _paymentFeeInfoLocalService.getPaymentFeeInfosByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of payment fee infos matching the UUID and company.
	*
	* @param uuid the UUID of the payment fee infos
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching payment fee infos, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.PaymentFeeInfo> getPaymentFeeInfosByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.PaymentFeeInfo> orderByComparator) {
		return _paymentFeeInfoLocalService.getPaymentFeeInfosByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of payment fee infos.
	*
	* @return the number of payment fee infos
	*/
	@Override
	public int getPaymentFeeInfosCount() {
		return _paymentFeeInfoLocalService.getPaymentFeeInfosCount();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _paymentFeeInfoLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the payment fee info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentFeeInfo the payment fee info
	* @return the payment fee info that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.PaymentFeeInfo updatePaymentFeeInfo(
		org.opencps.dossiermgt.model.PaymentFeeInfo paymentFeeInfo) {
		return _paymentFeeInfoLocalService.updatePaymentFeeInfo(paymentFeeInfo);
	}

	@Override
	public PaymentFeeInfoLocalService getWrappedService() {
		return _paymentFeeInfoLocalService;
	}

	@Override
	public void setWrappedService(
		PaymentFeeInfoLocalService paymentFeeInfoLocalService) {
		_paymentFeeInfoLocalService = paymentFeeInfoLocalService;
	}

	private PaymentFeeInfoLocalService _paymentFeeInfoLocalService;
}