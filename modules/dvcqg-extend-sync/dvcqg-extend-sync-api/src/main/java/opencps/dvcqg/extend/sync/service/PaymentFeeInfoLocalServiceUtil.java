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

package opencps.dvcqg.extend.sync.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for PaymentFeeInfo. This utility wraps
 * {@link opencps.dvcqg.extend.sync.service.impl.PaymentFeeInfoLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PaymentFeeInfoLocalService
 * @see opencps.dvcqg.extend.sync.service.base.PaymentFeeInfoLocalServiceBaseImpl
 * @see opencps.dvcqg.extend.sync.service.impl.PaymentFeeInfoLocalServiceImpl
 * @generated
 */
@ProviderType
public class PaymentFeeInfoLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link opencps.dvcqg.extend.sync.service.impl.PaymentFeeInfoLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static opencps.dvcqg.extend.sync.model.PaymentFeeInfo addPaymentFeeInfo(
		long groupId, long serviceConfigMappingId, String paymentFeeCode,
		String paymentFeeName, String type, String amount,
		com.liferay.portal.kernel.service.ServiceContext context) {
		return getService()
				   .addPaymentFeeInfo(groupId, serviceConfigMappingId,
			paymentFeeCode, paymentFeeName, type, amount, context);
	}

	/**
	* Adds the payment fee info to the database. Also notifies the appropriate model listeners.
	*
	* @param paymentFeeInfo the payment fee info
	* @return the payment fee info that was added
	*/
	public static opencps.dvcqg.extend.sync.model.PaymentFeeInfo addPaymentFeeInfo(
		opencps.dvcqg.extend.sync.model.PaymentFeeInfo paymentFeeInfo) {
		return getService().addPaymentFeeInfo(paymentFeeInfo);
	}

	/**
	* Creates a new payment fee info with the primary key. Does not add the payment fee info to the database.
	*
	* @param paymentFeeInfoId the primary key for the new payment fee info
	* @return the new payment fee info
	*/
	public static opencps.dvcqg.extend.sync.model.PaymentFeeInfo createPaymentFeeInfo(
		long paymentFeeInfoId) {
		return getService().createPaymentFeeInfo(paymentFeeInfoId);
	}

	/**
	* Deletes the payment fee info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentFeeInfoId the primary key of the payment fee info
	* @return the payment fee info that was removed
	* @throws PortalException if a payment fee info with the primary key could not be found
	*/
	public static opencps.dvcqg.extend.sync.model.PaymentFeeInfo deletePaymentFeeInfo(
		long paymentFeeInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePaymentFeeInfo(paymentFeeInfoId);
	}

	/**
	* Deletes the payment fee info from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentFeeInfo the payment fee info
	* @return the payment fee info that was removed
	*/
	public static opencps.dvcqg.extend.sync.model.PaymentFeeInfo deletePaymentFeeInfo(
		opencps.dvcqg.extend.sync.model.PaymentFeeInfo paymentFeeInfo) {
		return getService().deletePaymentFeeInfo(paymentFeeInfo);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link opencps.dvcqg.extend.sync.model.impl.PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link opencps.dvcqg.extend.sync.model.impl.PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static opencps.dvcqg.extend.sync.model.PaymentFeeInfo fetchPaymentFeeInfo(
		long paymentFeeInfoId) {
		return getService().fetchPaymentFeeInfo(paymentFeeInfoId);
	}

	/**
	* Returns the payment fee info matching the UUID and group.
	*
	* @param uuid the payment fee info's UUID
	* @param groupId the primary key of the group
	* @return the matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public static opencps.dvcqg.extend.sync.model.PaymentFeeInfo fetchPaymentFeeInfoByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchPaymentFeeInfoByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<opencps.dvcqg.extend.sync.model.PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId) {
		return getService().findByServiceConfigMappingId(serviceConfigMappingId);
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

	/**
	* Returns the payment fee info with the primary key.
	*
	* @param paymentFeeInfoId the primary key of the payment fee info
	* @return the payment fee info
	* @throws PortalException if a payment fee info with the primary key could not be found
	*/
	public static opencps.dvcqg.extend.sync.model.PaymentFeeInfo getPaymentFeeInfo(
		long paymentFeeInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPaymentFeeInfo(paymentFeeInfoId);
	}

	/**
	* Returns the payment fee info matching the UUID and group.
	*
	* @param uuid the payment fee info's UUID
	* @param groupId the primary key of the group
	* @return the matching payment fee info
	* @throws PortalException if a matching payment fee info could not be found
	*/
	public static opencps.dvcqg.extend.sync.model.PaymentFeeInfo getPaymentFeeInfoByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPaymentFeeInfoByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the payment fee infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link opencps.dvcqg.extend.sync.model.impl.PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @return the range of payment fee infos
	*/
	public static java.util.List<opencps.dvcqg.extend.sync.model.PaymentFeeInfo> getPaymentFeeInfos(
		int start, int end) {
		return getService().getPaymentFeeInfos(start, end);
	}

	/**
	* Returns all the payment fee infos matching the UUID and company.
	*
	* @param uuid the UUID of the payment fee infos
	* @param companyId the primary key of the company
	* @return the matching payment fee infos, or an empty list if no matches were found
	*/
	public static java.util.List<opencps.dvcqg.extend.sync.model.PaymentFeeInfo> getPaymentFeeInfosByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getPaymentFeeInfosByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<opencps.dvcqg.extend.sync.model.PaymentFeeInfo> getPaymentFeeInfosByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<opencps.dvcqg.extend.sync.model.PaymentFeeInfo> orderByComparator) {
		return getService()
				   .getPaymentFeeInfosByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of payment fee infos.
	*
	* @return the number of payment fee infos
	*/
	public static int getPaymentFeeInfosCount() {
		return getService().getPaymentFeeInfosCount();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the payment fee info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentFeeInfo the payment fee info
	* @return the payment fee info that was updated
	*/
	public static opencps.dvcqg.extend.sync.model.PaymentFeeInfo updatePaymentFeeInfo(
		opencps.dvcqg.extend.sync.model.PaymentFeeInfo paymentFeeInfo) {
		return getService().updatePaymentFeeInfo(paymentFeeInfo);
	}

	public static PaymentFeeInfoLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PaymentFeeInfoLocalService, PaymentFeeInfoLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PaymentFeeInfoLocalService.class);

		ServiceTracker<PaymentFeeInfoLocalService, PaymentFeeInfoLocalService> serviceTracker =
			new ServiceTracker<PaymentFeeInfoLocalService, PaymentFeeInfoLocalService>(bundle.getBundleContext(),
				PaymentFeeInfoLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}