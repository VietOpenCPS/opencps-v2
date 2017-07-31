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

package org.mobilink.backend.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PartnerFileLocalService}.
 *
 * @author Binhth
 * @see PartnerFileLocalService
 * @generated
 */
@ProviderType
public class PartnerFileLocalServiceWrapper implements PartnerFileLocalService,
	ServiceWrapper<PartnerFileLocalService> {
	public PartnerFileLocalServiceWrapper(
		PartnerFileLocalService partnerFileLocalService) {
		_partnerFileLocalService = partnerFileLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _partnerFileLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _partnerFileLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _partnerFileLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _partnerFileLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerFileLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerFileLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _partnerFileLocalService.luceneSearchEngine(params, sorts,
			start, end, searchContext);
	}

	/**
	* Returns the number of partner files.
	*
	* @return the number of partner files
	*/
	@Override
	public int getPartnerFilesCount() {
		return _partnerFileLocalService.getPartnerFilesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _partnerFileLocalService.getOSGiServiceIdentifier();
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
		return _partnerFileLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.PartnerFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _partnerFileLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.PartnerFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _partnerFileLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the partner files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.PartnerFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner files
	* @param end the upper bound of the range of partner files (not inclusive)
	* @return the range of partner files
	*/
	@Override
	public java.util.List<org.mobilink.backend.usermgt.model.PartnerFile> getPartnerFiles(
		int start, int end) {
		return _partnerFileLocalService.getPartnerFiles(start, end);
	}

	/**
	* Returns all the partner files matching the UUID and company.
	*
	* @param uuid the UUID of the partner files
	* @param companyId the primary key of the company
	* @return the matching partner files, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.mobilink.backend.usermgt.model.PartnerFile> getPartnerFilesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _partnerFileLocalService.getPartnerFilesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of partner files matching the UUID and company.
	*
	* @param uuid the UUID of the partner files
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of partner files
	* @param end the upper bound of the range of partner files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching partner files, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.mobilink.backend.usermgt.model.PartnerFile> getPartnerFilesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.mobilink.backend.usermgt.model.PartnerFile> orderByComparator) {
		return _partnerFileLocalService.getPartnerFilesByUuidAndCompanyId(uuid,
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
		return _partnerFileLocalService.dynamicQueryCount(dynamicQuery);
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
		return _partnerFileLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.mobilink.backend.usermgt.model.PartnerFile addPartnerFile(
		long userId, long groupId, long partnerId, java.io.File file,
		java.lang.String documentName, java.lang.String title,
		java.lang.String mimeType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerFileLocalService.addPartnerFile(userId, groupId,
			partnerId, file, documentName, title, mimeType, serviceContext);
	}

	/**
	* Adds the partner file to the database. Also notifies the appropriate model listeners.
	*
	* @param partnerFile the partner file
	* @return the partner file that was added
	*/
	@Override
	public org.mobilink.backend.usermgt.model.PartnerFile addPartnerFile(
		org.mobilink.backend.usermgt.model.PartnerFile partnerFile) {
		return _partnerFileLocalService.addPartnerFile(partnerFile);
	}

	/**
	* Creates a new partner file with the primary key. Does not add the partner file to the database.
	*
	* @param partnerFileId the primary key for the new partner file
	* @return the new partner file
	*/
	@Override
	public org.mobilink.backend.usermgt.model.PartnerFile createPartnerFile(
		long partnerFileId) {
		return _partnerFileLocalService.createPartnerFile(partnerFileId);
	}

	@Override
	public org.mobilink.backend.usermgt.model.PartnerFile deletePartnerFile(
		long PartnerFileId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _partnerFileLocalService.deletePartnerFile(PartnerFileId,
			serviceContext);
	}

	/**
	* Deletes the partner file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerFileId the primary key of the partner file
	* @return the partner file that was removed
	* @throws PortalException if a partner file with the primary key could not be found
	*/
	@Override
	public org.mobilink.backend.usermgt.model.PartnerFile deletePartnerFile(
		long partnerFileId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerFileLocalService.deletePartnerFile(partnerFileId);
	}

	/**
	* Deletes the partner file from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerFile the partner file
	* @return the partner file that was removed
	*/
	@Override
	public org.mobilink.backend.usermgt.model.PartnerFile deletePartnerFile(
		org.mobilink.backend.usermgt.model.PartnerFile partnerFile) {
		return _partnerFileLocalService.deletePartnerFile(partnerFile);
	}

	@Override
	public org.mobilink.backend.usermgt.model.PartnerFile fetchPartnerFile(
		long partnerFileId) {
		return _partnerFileLocalService.fetchPartnerFile(partnerFileId);
	}

	/**
	* Returns the partner file matching the UUID and group.
	*
	* @param uuid the partner file's UUID
	* @param groupId the primary key of the group
	* @return the matching partner file, or <code>null</code> if a matching partner file could not be found
	*/
	@Override
	public org.mobilink.backend.usermgt.model.PartnerFile fetchPartnerFileByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _partnerFileLocalService.fetchPartnerFileByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the partner file with the primary key.
	*
	* @param partnerFileId the primary key of the partner file
	* @return the partner file
	* @throws PortalException if a partner file with the primary key could not be found
	*/
	@Override
	public org.mobilink.backend.usermgt.model.PartnerFile getPartnerFile(
		long partnerFileId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerFileLocalService.getPartnerFile(partnerFileId);
	}

	/**
	* Returns the partner file matching the UUID and group.
	*
	* @param uuid the partner file's UUID
	* @param groupId the primary key of the group
	* @return the matching partner file
	* @throws PortalException if a matching partner file could not be found
	*/
	@Override
	public org.mobilink.backend.usermgt.model.PartnerFile getPartnerFileByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerFileLocalService.getPartnerFileByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the partner file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerFile the partner file
	* @return the partner file that was updated
	*/
	@Override
	public org.mobilink.backend.usermgt.model.PartnerFile updatePartnerFile(
		org.mobilink.backend.usermgt.model.PartnerFile partnerFile) {
		return _partnerFileLocalService.updatePartnerFile(partnerFile);
	}

	@Override
	public PartnerFileLocalService getWrappedService() {
		return _partnerFileLocalService;
	}

	@Override
	public void setWrappedService(
		PartnerFileLocalService partnerFileLocalService) {
		_partnerFileLocalService = partnerFileLocalService;
	}

	private PartnerFileLocalService _partnerFileLocalService;
}