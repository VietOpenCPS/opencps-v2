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

package org.opencps.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ApplicantDataLocalService}.
 *
 * @author khoavu
 * @see ApplicantDataLocalService
 * @generated
 */
@ProviderType
public class ApplicantDataLocalServiceWrapper
	implements ApplicantDataLocalService,
		ServiceWrapper<ApplicantDataLocalService> {
	public ApplicantDataLocalServiceWrapper(
		ApplicantDataLocalService applicantDataLocalService) {
		_applicantDataLocalService = applicantDataLocalService;
	}

	@Override
	public org.opencps.usermgt.model.ApplicantData active(long applicantDataId) {
		return _applicantDataLocalService.active(applicantDataId);
	}

	/**
	* Adds the applicant data to the database. Also notifies the appropriate model listeners.
	*
	* @param applicantData the applicant data
	* @return the applicant data that was added
	*/
	@Override
	public org.opencps.usermgt.model.ApplicantData addApplicantData(
		org.opencps.usermgt.model.ApplicantData applicantData) {
		return _applicantDataLocalService.addApplicantData(applicantData);
	}

	@Override
	public long countLucene(java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _applicantDataLocalService.countLucene(params, searchContext);
	}

	/**
	* Creates a new applicant data with the primary key. Does not add the applicant data to the database.
	*
	* @param applicantDataId the primary key for the new applicant data
	* @return the new applicant data
	*/
	@Override
	public org.opencps.usermgt.model.ApplicantData createApplicantData(
		long applicantDataId) {
		return _applicantDataLocalService.createApplicantData(applicantDataId);
	}

	@Override
	public org.opencps.usermgt.model.ApplicantData createApplicantData(
		long groupId, String fileTemplateNo, String fileNo, String fileName,
		String applicantIdNo, int status, String sourceFileName,
		java.io.InputStream inputStream,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _applicantDataLocalService.createApplicantData(groupId,
			fileTemplateNo, fileNo, fileName, applicantIdNo, status,
			sourceFileName, inputStream, serviceContext);
	}

	@Override
	public org.opencps.usermgt.model.ApplicantData createApplicantData(
		com.liferay.portal.kernel.service.ServiceContext context, long groupId,
		String fileTemplateNo, String fileNo, String fileName,
		long fileEntryId, String metadata, int status, String applicantIdNo,
		int applicantDataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _applicantDataLocalService.createApplicantData(context, groupId,
			fileTemplateNo, fileNo, fileName, fileEntryId, metadata, status,
			applicantIdNo, applicantDataType);
	}

	/**
	* Deletes the applicant data from the database. Also notifies the appropriate model listeners.
	*
	* @param applicantData the applicant data
	* @return the applicant data that was removed
	*/
	@Override
	public org.opencps.usermgt.model.ApplicantData deleteApplicantData(
		org.opencps.usermgt.model.ApplicantData applicantData) {
		return _applicantDataLocalService.deleteApplicantData(applicantData);
	}

	/**
	* Deletes the applicant data with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicantDataId the primary key of the applicant data
	* @return the applicant data that was removed
	* @throws PortalException if a applicant data with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.ApplicantData deleteApplicantData(
		long applicantDataId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _applicantDataLocalService.deleteApplicantData(applicantDataId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _applicantDataLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _applicantDataLocalService.dynamicQuery();
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
		return _applicantDataLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _applicantDataLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _applicantDataLocalService.dynamicQuery(dynamicQuery, start,
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
		return _applicantDataLocalService.dynamicQueryCount(dynamicQuery);
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
		return _applicantDataLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.usermgt.model.ApplicantData fetchApplicantData(
		long applicantDataId) {
		return _applicantDataLocalService.fetchApplicantData(applicantDataId);
	}

	/**
	* Returns the applicant data matching the UUID and group.
	*
	* @param uuid the applicant data's UUID
	* @param groupId the primary key of the group
	* @return the matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	@Override
	public org.opencps.usermgt.model.ApplicantData fetchApplicantDataByUuidAndGroupId(
		String uuid, long groupId) {
		return _applicantDataLocalService.fetchApplicantDataByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public org.opencps.usermgt.model.ApplicantData findByG_DN_FTN_AIN(
		long groupId, String dossierNo, String fileTemplateNo,
		String applicantIdNo) {
		return _applicantDataLocalService.findByG_DN_FTN_AIN(groupId,
			dossierNo, fileTemplateNo, applicantIdNo);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _applicantDataLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the applicant data with the primary key.
	*
	* @param applicantDataId the primary key of the applicant data
	* @return the applicant data
	* @throws PortalException if a applicant data with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.ApplicantData getApplicantData(
		long applicantDataId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _applicantDataLocalService.getApplicantData(applicantDataId);
	}

	/**
	* Returns the applicant data matching the UUID and group.
	*
	* @param uuid the applicant data's UUID
	* @param groupId the primary key of the group
	* @return the matching applicant data
	* @throws PortalException if a matching applicant data could not be found
	*/
	@Override
	public org.opencps.usermgt.model.ApplicantData getApplicantDataByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _applicantDataLocalService.getApplicantDataByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the applicant datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @return the range of applicant datas
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.ApplicantData> getApplicantDatas(
		int start, int end) {
		return _applicantDataLocalService.getApplicantDatas(start, end);
	}

	/**
	* Returns all the applicant datas matching the UUID and company.
	*
	* @param uuid the UUID of the applicant datas
	* @param companyId the primary key of the company
	* @return the matching applicant datas, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.ApplicantData> getApplicantDatasByUuidAndCompanyId(
		String uuid, long companyId) {
		return _applicantDataLocalService.getApplicantDatasByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of applicant datas matching the UUID and company.
	*
	* @param uuid the UUID of the applicant datas
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching applicant datas, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.ApplicantData> getApplicantDatasByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.ApplicantData> orderByComparator) {
		return _applicantDataLocalService.getApplicantDatasByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of applicant datas.
	*
	* @return the number of applicant datas
	*/
	@Override
	public int getApplicantDatasCount() {
		return _applicantDataLocalService.getApplicantDatasCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _applicantDataLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _applicantDataLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicantDataLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _applicantDataLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.usermgt.model.ApplicantData inActive(
		long applicantDataId) {
		return _applicantDataLocalService.inActive(applicantDataId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _applicantDataLocalService.searchLucene(params, sorts, start,
			end, searchContext);
	}

	/**
	* Updates the applicant data in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param applicantData the applicant data
	* @return the applicant data that was updated
	*/
	@Override
	public org.opencps.usermgt.model.ApplicantData updateApplicantData(
		org.opencps.usermgt.model.ApplicantData applicantData) {
		return _applicantDataLocalService.updateApplicantData(applicantData);
	}

	@Override
	public org.opencps.usermgt.model.ApplicantData updateApplicantData(
		long groupId, long applicantDataId, String fileTemplateNo,
		String fileNo, String fileName, String applicantIdNo, String status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _applicantDataLocalService.updateApplicantData(groupId,
			applicantDataId, fileTemplateNo, fileNo, fileName, applicantIdNo,
			status, serviceContext);
	}

	@Override
	public org.opencps.usermgt.model.ApplicantData updateApplicantData(
		long groupId, long applicantDataId, String fileTemplateNo,
		String fileNo, String fileName, String applicantIdNo, String status,
		String sourceFileName, java.io.InputStream inputStream,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _applicantDataLocalService.updateApplicantData(groupId,
			applicantDataId, fileTemplateNo, fileNo, fileName, applicantIdNo,
			status, sourceFileName, inputStream, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry uploadFileDLEntry(long userId, long groupId, java.io.InputStream inputStream,
		String sourceFileName, String fileType, long fileSize, com.liferay.portal.kernel.service.ServiceContext serviceContext)
			throws Exception {
		return _applicantDataLocalService.uploadFileDLEntry(userId, groupId, inputStream, sourceFileName,
				fileType, fileSize, serviceContext );
	}

	@Override
	public org.opencps.usermgt.model.ApplicantData updateApplicantData(
		com.liferay.portal.kernel.service.ServiceContext context, long groupId,
		long applicantDataId, String fileTemplateNo, String fileNo,
		String fileName, long fileEntryId, String metadata, int status,
		String applicantIdNo, int applicantDataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _applicantDataLocalService.updateApplicantData(context, groupId,
			applicantDataId, fileTemplateNo, fileNo, fileName, fileEntryId,
			metadata, status, applicantIdNo, applicantDataType);
	}

	@Override
	public org.opencps.usermgt.model.ApplicantData updateApplicantData(
		com.liferay.portal.kernel.service.ServiceContext context, long groupId,
		String fileTemplateNo, String fileName, long fileEntryId,
		String metadata, int status, String applicantIdNo,
		int applicantDataType, String dossierNo, String log)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _applicantDataLocalService.updateApplicantData(context, groupId,
			fileTemplateNo, fileName, fileEntryId, metadata, status,
			applicantIdNo, applicantDataType, dossierNo, log);
	}

	@Override
	public ApplicantDataLocalService getWrappedService() {
		return _applicantDataLocalService;
	}

	@Override
	public void setWrappedService(
		ApplicantDataLocalService applicantDataLocalService) {
		_applicantDataLocalService = applicantDataLocalService;
	}

	private ApplicantDataLocalService _applicantDataLocalService;
}