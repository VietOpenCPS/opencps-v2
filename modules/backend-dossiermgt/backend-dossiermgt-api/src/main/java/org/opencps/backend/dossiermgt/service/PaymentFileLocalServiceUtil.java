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
 * Provides the local service utility for PaymentFile. This utility wraps
 * {@link org.opencps.backend.dossiermgt.service.impl.PaymentFileLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see PaymentFileLocalService
 * @see org.opencps.backend.dossiermgt.service.base.PaymentFileLocalServiceBaseImpl
 * @see org.opencps.backend.dossiermgt.service.impl.PaymentFileLocalServiceImpl
 * @generated
 */
@ProviderType
public class PaymentFileLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.backend.dossiermgt.service.impl.PaymentFileLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Returns the number of payment files.
	*
	* @return the number of payment files
	*/
	public static int getPaymentFilesCount() {
		return getService().getPaymentFilesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the payment files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of payment files
	* @param end the upper bound of the range of payment files (not inclusive)
	* @return the range of payment files
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.PaymentFile> getPaymentFiles(
		int start, int end) {
		return getService().getPaymentFiles(start, end);
	}

	/**
	* Returns all the payment files matching the UUID and company.
	*
	* @param uuid the UUID of the payment files
	* @param companyId the primary key of the company
	* @return the matching payment files, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.PaymentFile> getPaymentFilesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getPaymentFilesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of payment files matching the UUID and company.
	*
	* @param uuid the UUID of the payment files
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of payment files
	* @param end the upper bound of the range of payment files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching payment files, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.PaymentFile> getPaymentFilesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.PaymentFile> orderByComparator) {
		return getService()
				   .getPaymentFilesByUuidAndCompanyId(uuid, companyId, start,
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
	* Adds the payment file to the database. Also notifies the appropriate model listeners.
	*
	* @param paymentFile the payment file
	* @return the payment file that was added
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentFile addPaymentFile(
		org.opencps.backend.dossiermgt.model.PaymentFile paymentFile) {
		return getService().addPaymentFile(paymentFile);
	}

	/**
	* Creates a new payment file with the primary key. Does not add the payment file to the database.
	*
	* @param paymentFileId the primary key for the new payment file
	* @return the new payment file
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentFile createPaymentFile(
		long paymentFileId) {
		return getService().createPaymentFile(paymentFileId);
	}

	/**
	* Deletes the payment file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentFileId the primary key of the payment file
	* @return the payment file that was removed
	* @throws PortalException if a payment file with the primary key could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentFile deletePaymentFile(
		long paymentFileId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePaymentFile(paymentFileId);
	}

	/**
	* Deletes the payment file from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentFile the payment file
	* @return the payment file that was removed
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentFile deletePaymentFile(
		org.opencps.backend.dossiermgt.model.PaymentFile paymentFile) {
		return getService().deletePaymentFile(paymentFile);
	}

	public static org.opencps.backend.dossiermgt.model.PaymentFile fetchPaymentFile(
		long paymentFileId) {
		return getService().fetchPaymentFile(paymentFileId);
	}

	/**
	* Returns the payment file matching the UUID and group.
	*
	* @param uuid the payment file's UUID
	* @param groupId the primary key of the group
	* @return the matching payment file, or <code>null</code> if a matching payment file could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentFile fetchPaymentFileByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchPaymentFileByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the payment file with the primary key.
	*
	* @param paymentFileId the primary key of the payment file
	* @return the payment file
	* @throws PortalException if a payment file with the primary key could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentFile getPaymentFile(
		long paymentFileId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPaymentFile(paymentFileId);
	}

	/**
	* Returns the payment file matching the UUID and group.
	*
	* @param uuid the payment file's UUID
	* @param groupId the primary key of the group
	* @return the matching payment file
	* @throws PortalException if a matching payment file could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentFile getPaymentFileByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPaymentFileByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the payment file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentFile the payment file
	* @return the payment file that was updated
	*/
	public static org.opencps.backend.dossiermgt.model.PaymentFile updatePaymentFile(
		org.opencps.backend.dossiermgt.model.PaymentFile paymentFile) {
		return getService().updatePaymentFile(paymentFile);
	}

	public static PaymentFileLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PaymentFileLocalService, PaymentFileLocalService> _serviceTracker =
		ServiceTrackerFactory.open(PaymentFileLocalService.class);
}