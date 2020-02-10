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
 * Provides a wrapper for {@link NotarizationLocalService}.
 *
 * @author huymq
 * @see NotarizationLocalService
 * @generated
 */
@ProviderType
public class NotarizationLocalServiceWrapper implements NotarizationLocalService,
	ServiceWrapper<NotarizationLocalService> {
	public NotarizationLocalServiceWrapper(
		NotarizationLocalService notarizationLocalService) {
		_notarizationLocalService = notarizationLocalService;
	}

	@Override
	public org.opencps.dossiermgt.model.Notarization addNotarization(
		long groupId, long dossierId, String fileName, int totalRecord,
		int totalPage, int totalCopy, long totalFee, long notarizationNo,
		int notarizationYear, java.util.Date notarizationDate,
		String signerName, String signerPosition, String statusCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _notarizationLocalService.addNotarization(groupId, dossierId,
			fileName, totalRecord, totalPage, totalCopy, totalFee,
			notarizationNo, notarizationYear, notarizationDate, signerName,
			signerPosition, statusCode, serviceContext);
	}

	/**
	* Adds the notarization to the database. Also notifies the appropriate model listeners.
	*
	* @param notarization the notarization
	* @return the notarization that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.Notarization addNotarization(
		org.opencps.dossiermgt.model.Notarization notarization) {
		return _notarizationLocalService.addNotarization(notarization);
	}

	/**
	* Creates a new notarization with the primary key. Does not add the notarization to the database.
	*
	* @param notarizationId the primary key for the new notarization
	* @return the new notarization
	*/
	@Override
	public org.opencps.dossiermgt.model.Notarization createNotarization(
		long notarizationId) {
		return _notarizationLocalService.createNotarization(notarizationId);
	}

	/**
	* Deletes the notarization with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notarizationId the primary key of the notarization
	* @return the notarization that was removed
	* @throws PortalException if a notarization with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.Notarization deleteNotarization(
		long notarizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _notarizationLocalService.deleteNotarization(notarizationId);
	}

	/**
	* Deletes the notarization from the database. Also notifies the appropriate model listeners.
	*
	* @param notarization the notarization
	* @return the notarization that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.Notarization deleteNotarization(
		org.opencps.dossiermgt.model.Notarization notarization) {
		return _notarizationLocalService.deleteNotarization(notarization);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _notarizationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _notarizationLocalService.dynamicQuery();
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
		return _notarizationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _notarizationLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _notarizationLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _notarizationLocalService.dynamicQueryCount(dynamicQuery);
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
		return _notarizationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.Notarization fetchNotarization(
		long notarizationId) {
		return _notarizationLocalService.fetchNotarization(notarizationId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.Notarization> findByG_DID(
		long groupId, long dossierId) {
		return _notarizationLocalService.findByG_DID(groupId, dossierId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _notarizationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _notarizationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the notarization with the primary key.
	*
	* @param notarizationId the primary key of the notarization
	* @return the notarization
	* @throws PortalException if a notarization with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.Notarization getNotarization(
		long notarizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _notarizationLocalService.getNotarization(notarizationId);
	}

	/**
	* Returns a range of all the notarizations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @return the range of notarizations
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.Notarization> getNotarizations(
		int start, int end) {
		return _notarizationLocalService.getNotarizations(start, end);
	}

	/**
	* Returns the number of notarizations.
	*
	* @return the number of notarizations
	*/
	@Override
	public int getNotarizationsCount() {
		return _notarizationLocalService.getNotarizationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _notarizationLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _notarizationLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public void removeNotarization(long notarizationId)
		throws org.opencps.dossiermgt.exception.NoSuchNotarizationException {
		_notarizationLocalService.removeNotarization(notarizationId);
	}

	@Override
	public org.opencps.dossiermgt.model.Notarization updateNotarization(
		long groupId, long notarizationId, long dossierId, String fileName,
		int totalRecord, int totalPage, int totalCopy, long totalFee,
		long notarizationNo, int notarizationYear,
		java.util.Date notarizationDate, String signerName,
		String signerPosition, String statusCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _notarizationLocalService.updateNotarization(groupId,
			notarizationId, dossierId, fileName, totalRecord, totalPage,
			totalCopy, totalFee, notarizationNo, notarizationYear,
			notarizationDate, signerName, signerPosition, statusCode,
			serviceContext);
	}

	/**
	* Updates the notarization in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param notarization the notarization
	* @return the notarization that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.Notarization updateNotarization(
		org.opencps.dossiermgt.model.Notarization notarization) {
		return _notarizationLocalService.updateNotarization(notarization);
	}

	@Override
	public NotarizationLocalService getWrappedService() {
		return _notarizationLocalService;
	}

	@Override
	public void setWrappedService(
		NotarizationLocalService notarizationLocalService) {
		_notarizationLocalService = notarizationLocalService;
	}

	private NotarizationLocalService _notarizationLocalService;
}