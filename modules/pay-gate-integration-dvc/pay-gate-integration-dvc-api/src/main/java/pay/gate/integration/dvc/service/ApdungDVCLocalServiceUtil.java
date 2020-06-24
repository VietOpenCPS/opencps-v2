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

package pay.gate.integration.dvc.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ApdungDVC. This utility wraps
 * {@link pay.gate.integration.dvc.service.impl.ApdungDVCLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ApdungDVCLocalService
 * @see pay.gate.integration.dvc.service.base.ApdungDVCLocalServiceBaseImpl
 * @see pay.gate.integration.dvc.service.impl.ApdungDVCLocalServiceImpl
 * @generated
 */
@ProviderType
public class ApdungDVCLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link pay.gate.integration.dvc.service.impl.ApdungDVCLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the apdung dvc to the database. Also notifies the appropriate model listeners.
	*
	* @param apdungDVC the apdung dvc
	* @return the apdung dvc that was added
	*/
	public static pay.gate.integration.dvc.model.ApdungDVC addApdungDVC(
		pay.gate.integration.dvc.model.ApdungDVC apdungDVC) {
		return getService().addApdungDVC(apdungDVC);
	}

	/**
	* Creates a new apdung dvc with the primary key. Does not add the apdung dvc to the database.
	*
	* @param apdungDVCId the primary key for the new apdung dvc
	* @return the new apdung dvc
	*/
	public static pay.gate.integration.dvc.model.ApdungDVC createApdungDVC(
		long apdungDVCId) {
		return getService().createApdungDVC(apdungDVCId);
	}

	/**
	* Deletes the apdung dvc from the database. Also notifies the appropriate model listeners.
	*
	* @param apdungDVC the apdung dvc
	* @return the apdung dvc that was removed
	*/
	public static pay.gate.integration.dvc.model.ApdungDVC deleteApdungDVC(
		pay.gate.integration.dvc.model.ApdungDVC apdungDVC) {
		return getService().deleteApdungDVC(apdungDVC);
	}

	/**
	* Deletes the apdung dvc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param apdungDVCId the primary key of the apdung dvc
	* @return the apdung dvc that was removed
	* @throws PortalException if a apdung dvc with the primary key could not be found
	*/
	public static pay.gate.integration.dvc.model.ApdungDVC deleteApdungDVC(
		long apdungDVCId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteApdungDVC(apdungDVCId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link pay.gate.integration.dvc.model.impl.ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link pay.gate.integration.dvc.model.impl.ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static pay.gate.integration.dvc.model.ApdungDVC fetchApdungDVC(
		long apdungDVCId) {
		return getService().fetchApdungDVC(apdungDVCId);
	}

	/**
	* Returns the apdung dvc matching the UUID and group.
	*
	* @param uuid the apdung dvc's UUID
	* @param groupId the primary key of the group
	* @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public static pay.gate.integration.dvc.model.ApdungDVC fetchApdungDVCByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchApdungDVCByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the apdung dvc with the primary key.
	*
	* @param apdungDVCId the primary key of the apdung dvc
	* @return the apdung dvc
	* @throws PortalException if a apdung dvc with the primary key could not be found
	*/
	public static pay.gate.integration.dvc.model.ApdungDVC getApdungDVC(
		long apdungDVCId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getApdungDVC(apdungDVCId);
	}

	public static pay.gate.integration.dvc.model.ApdungDVC getApdungDVCByTTHCCQTHMD(
		String maTTHC, String maCQTH, int mucdo) {
		return getService().getApdungDVCByTTHCCQTHMD(maTTHC, maCQTH, mucdo);
	}

	/**
	* Returns the apdung dvc matching the UUID and group.
	*
	* @param uuid the apdung dvc's UUID
	* @param groupId the primary key of the group
	* @return the matching apdung dvc
	* @throws PortalException if a matching apdung dvc could not be found
	*/
	public static pay.gate.integration.dvc.model.ApdungDVC getApdungDVCByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getApdungDVCByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the apdung dvcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link pay.gate.integration.dvc.model.impl.ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @return the range of apdung dvcs
	*/
	public static java.util.List<pay.gate.integration.dvc.model.ApdungDVC> getApdungDVCs(
		int start, int end) {
		return getService().getApdungDVCs(start, end);
	}

	/**
	* Returns all the apdung dvcs matching the UUID and company.
	*
	* @param uuid the UUID of the apdung dvcs
	* @param companyId the primary key of the company
	* @return the matching apdung dvcs, or an empty list if no matches were found
	*/
	public static java.util.List<pay.gate.integration.dvc.model.ApdungDVC> getApdungDVCsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getApdungDVCsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of apdung dvcs matching the UUID and company.
	*
	* @param uuid the UUID of the apdung dvcs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching apdung dvcs, or an empty list if no matches were found
	*/
	public static java.util.List<pay.gate.integration.dvc.model.ApdungDVC> getApdungDVCsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<pay.gate.integration.dvc.model.ApdungDVC> orderByComparator) {
		return getService()
				   .getApdungDVCsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of apdung dvcs.
	*
	* @return the number of apdung dvcs
	*/
	public static int getApdungDVCsCount() {
		return getService().getApdungDVCsCount();
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
	* Updates the apdung dvc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param apdungDVC the apdung dvc
	* @return the apdung dvc that was updated
	*/
	public static pay.gate.integration.dvc.model.ApdungDVC updateApdungDVC(
		pay.gate.integration.dvc.model.ApdungDVC apdungDVC) {
		return getService().updateApdungDVC(apdungDVC);
	}

	public static ApdungDVCLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ApdungDVCLocalService, ApdungDVCLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ApdungDVCLocalService.class);

		ServiceTracker<ApdungDVCLocalService, ApdungDVCLocalService> serviceTracker =
			new ServiceTracker<ApdungDVCLocalService, ApdungDVCLocalService>(bundle.getBundleContext(),
				ApdungDVCLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}