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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PhiLePhiLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PhiLePhiLocalService
 * @generated
 */
@ProviderType
public class PhiLePhiLocalServiceWrapper implements PhiLePhiLocalService,
	ServiceWrapper<PhiLePhiLocalService> {
	public PhiLePhiLocalServiceWrapper(
		PhiLePhiLocalService phiLePhiLocalService) {
		_phiLePhiLocalService = phiLePhiLocalService;
	}

	/**
	* Adds the phi le phi to the database. Also notifies the appropriate model listeners.
	*
	* @param phiLePhi the phi le phi
	* @return the phi le phi that was added
	*/
	@Override
	public pay.gate.integration.dvc.model.PhiLePhi addPhiLePhi(
		pay.gate.integration.dvc.model.PhiLePhi phiLePhi) {
		return _phiLePhiLocalService.addPhiLePhi(phiLePhi);
	}

	/**
	* Creates a new phi le phi with the primary key. Does not add the phi le phi to the database.
	*
	* @param phiLePhiId the primary key for the new phi le phi
	* @return the new phi le phi
	*/
	@Override
	public pay.gate.integration.dvc.model.PhiLePhi createPhiLePhi(
		long phiLePhiId) {
		return _phiLePhiLocalService.createPhiLePhi(phiLePhiId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phiLePhiLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the phi le phi with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param phiLePhiId the primary key of the phi le phi
	* @return the phi le phi that was removed
	* @throws PortalException if a phi le phi with the primary key could not be found
	*/
	@Override
	public pay.gate.integration.dvc.model.PhiLePhi deletePhiLePhi(
		long phiLePhiId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phiLePhiLocalService.deletePhiLePhi(phiLePhiId);
	}

	/**
	* Deletes the phi le phi from the database. Also notifies the appropriate model listeners.
	*
	* @param phiLePhi the phi le phi
	* @return the phi le phi that was removed
	*/
	@Override
	public pay.gate.integration.dvc.model.PhiLePhi deletePhiLePhi(
		pay.gate.integration.dvc.model.PhiLePhi phiLePhi) {
		return _phiLePhiLocalService.deletePhiLePhi(phiLePhi);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _phiLePhiLocalService.dynamicQuery();
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
		return _phiLePhiLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link pay.gate.integration.dvc.model.impl.PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _phiLePhiLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link pay.gate.integration.dvc.model.impl.PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _phiLePhiLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _phiLePhiLocalService.dynamicQueryCount(dynamicQuery);
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
		return _phiLePhiLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public pay.gate.integration.dvc.model.PhiLePhi fetchPhiLePhi(
		long phiLePhiId) {
		return _phiLePhiLocalService.fetchPhiLePhi(phiLePhiId);
	}

	/**
	* Returns the phi le phi matching the UUID and group.
	*
	* @param uuid the phi le phi's UUID
	* @param groupId the primary key of the group
	* @return the matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	@Override
	public pay.gate.integration.dvc.model.PhiLePhi fetchPhiLePhiByUuidAndGroupId(
		String uuid, long groupId) {
		return _phiLePhiLocalService.fetchPhiLePhiByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _phiLePhiLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _phiLePhiLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _phiLePhiLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _phiLePhiLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phiLePhiLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the phi le phi with the primary key.
	*
	* @param phiLePhiId the primary key of the phi le phi
	* @return the phi le phi
	* @throws PortalException if a phi le phi with the primary key could not be found
	*/
	@Override
	public pay.gate.integration.dvc.model.PhiLePhi getPhiLePhi(long phiLePhiId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phiLePhiLocalService.getPhiLePhi(phiLePhiId);
	}

	/**
	* Returns the phi le phi matching the UUID and group.
	*
	* @param uuid the phi le phi's UUID
	* @param groupId the primary key of the group
	* @return the matching phi le phi
	* @throws PortalException if a matching phi le phi could not be found
	*/
	@Override
	public pay.gate.integration.dvc.model.PhiLePhi getPhiLePhiByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phiLePhiLocalService.getPhiLePhiByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the phi le phis.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link pay.gate.integration.dvc.model.impl.PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @return the range of phi le phis
	*/
	@Override
	public java.util.List<pay.gate.integration.dvc.model.PhiLePhi> getPhiLePhis(
		int start, int end) {
		return _phiLePhiLocalService.getPhiLePhis(start, end);
	}

	@Override
	public java.util.List<pay.gate.integration.dvc.model.PhiLePhi> getPhiLePhis(
		long groupId, long serviceConfigMappingId) {
		return _phiLePhiLocalService.getPhiLePhis(groupId,
			serviceConfigMappingId);
	}

	/**
	* Returns all the phi le phis matching the UUID and company.
	*
	* @param uuid the UUID of the phi le phis
	* @param companyId the primary key of the company
	* @return the matching phi le phis, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<pay.gate.integration.dvc.model.PhiLePhi> getPhiLePhisByUuidAndCompanyId(
		String uuid, long companyId) {
		return _phiLePhiLocalService.getPhiLePhisByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of phi le phis matching the UUID and company.
	*
	* @param uuid the UUID of the phi le phis
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching phi le phis, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<pay.gate.integration.dvc.model.PhiLePhi> getPhiLePhisByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<pay.gate.integration.dvc.model.PhiLePhi> orderByComparator) {
		return _phiLePhiLocalService.getPhiLePhisByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of phi le phis.
	*
	* @return the number of phi le phis
	*/
	@Override
	public int getPhiLePhisCount() {
		return _phiLePhiLocalService.getPhiLePhisCount();
	}

	/**
	* Updates the phi le phi in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param phiLePhi the phi le phi
	* @return the phi le phi that was updated
	*/
	@Override
	public pay.gate.integration.dvc.model.PhiLePhi updatePhiLePhi(
		pay.gate.integration.dvc.model.PhiLePhi phiLePhi) {
		return _phiLePhiLocalService.updatePhiLePhi(phiLePhi);
	}

	@Override
	public PhiLePhiLocalService getWrappedService() {
		return _phiLePhiLocalService;
	}

	@Override
	public void setWrappedService(PhiLePhiLocalService phiLePhiLocalService) {
		_phiLePhiLocalService = phiLePhiLocalService;
	}

	private PhiLePhiLocalService _phiLePhiLocalService;
}