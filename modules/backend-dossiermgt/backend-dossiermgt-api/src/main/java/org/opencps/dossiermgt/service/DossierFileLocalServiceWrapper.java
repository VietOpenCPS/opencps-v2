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
 * Provides a wrapper for {@link DossierFileLocalService}.
 *
 * @author huymq
 * @see DossierFileLocalService
 * @generated
 */
@ProviderType
public class DossierFileLocalServiceWrapper implements DossierFileLocalService,
	ServiceWrapper<DossierFileLocalService> {
	public DossierFileLocalServiceWrapper(
		DossierFileLocalService dossierFileLocalService) {
		_dossierFileLocalService = dossierFileLocalService;
	}

	/**
	* Adds the dossier file to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierFile the dossier file
	* @return the dossier file that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierFile addDossierFile(
		org.opencps.dossiermgt.model.DossierFile dossierFile) {
		return _dossierFileLocalService.addDossierFile(dossierFile);
	}

	/**
	* POST /dossiers/{id|referenceUid}/files
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierFile addDossierFile(
		long groupId, long dossierId, String referenceUid,
		String dossierTemplateNo, String dossierPartNo, String fileTemplateNo,
		String displayName, String sourceFileName, long fileSize,
		java.io.InputStream inputStream, String fileType, String isSync,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dossierFileLocalService.addDossierFile(groupId, dossierId,
			referenceUid, dossierTemplateNo, dossierPartNo, fileTemplateNo,
			displayName, sourceFileName, fileSize, inputStream, fileType,
			isSync, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile addDossierFileEForm(
		long groupId, long dossierId, String referenceUid,
		String dossierTemplateNo, String dossierPartNo, String fileTemplateNo,
		String displayName, String sourceFileName, long fileSize,
		java.io.InputStream inputStream, String fileType, String isSync,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dossierFileLocalService.addDossierFileEForm(groupId, dossierId,
			referenceUid, dossierTemplateNo, dossierPartNo, fileTemplateNo,
			displayName, sourceFileName, fileSize, inputStream, fileType,
			isSync, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _dossierFileLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile adminProcessDelete(Long id) {
		return _dossierFileLocalService.adminProcessDelete(id);
	}

	/**
	* POST /dossiers/{id}/files/copyfile
	*
	* @param groupId
	* @param dossierId
	* @param dossierFileId
	* @param dossierTemplateNo
	* @param dossierPartNo
	* @param serviceContext
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierFile cloneDossierFile(
		long groupId, long dossierId, long dossierFileId,
		String dossierTemplateNo, String dossierPartNo,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dossierFileLocalService.cloneDossierFile(groupId, dossierId,
			dossierFileId, dossierTemplateNo, dossierPartNo, serviceContext);
	}

	/**
	* for POST /dossiers/{id}/cloning
	*
	* @param groupId
	* @param newDossierId
	* @param oldDossierId
	* @param dossierPartType
	* @param serviceContext
	* @throws PortalException
	* @throws SystemException
	*/
	@Override
	public void cloneDossierFilesByDossierId(long groupId, long newDossierId,
		long oldDossierId, int dossierPartType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dossierFileLocalService.cloneDossierFilesByDossierId(groupId,
			newDossierId, oldDossierId, dossierPartType, serviceContext);
	}

	@Override
	public long countLucene(java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dossierFileLocalService.countLucene(params, searchContext);
	}

	/**
	* Creates a new dossier file with the primary key. Does not add the dossier file to the database.
	*
	* @param dossierFileId the primary key for the new dossier file
	* @return the new dossier file
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierFile createDossierFile(
		long dossierFileId) {
		return _dossierFileLocalService.createDossierFile(dossierFileId);
	}

	/**
	* Deletes the dossier file from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierFile the dossier file
	* @return the dossier file that was removed
	* @throws PortalException
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierFile deleteDossierFile(
		org.opencps.dossiermgt.model.DossierFile dossierFile)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.deleteDossierFile(dossierFile);
	}

	/**
	* Deletes the dossier file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierFileId the primary key of the dossier file
	* @return the dossier file that was removed
	* @throws PortalException if a dossier file with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierFile deleteDossierFile(
		long dossierFileId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.deleteDossierFile(dossierFileId);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile deleteDossierFile(
		long dossierId, String referenceUid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.deleteDossierFile(dossierId,
			referenceUid);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile deletePermanentDossierFile(
		org.opencps.dossiermgt.model.DossierFile dossierFile) {
		return _dossierFileLocalService.deletePermanentDossierFile(dossierFile);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierFileLocalService.dynamicQuery();
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
		return _dossierFileLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierFileLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierFileLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _dossierFileLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierFileLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile fetchDossierFile(
		long dossierFileId) {
		return _dossierFileLocalService.fetchDossierFile(dossierFileId);
	}

	/**
	* Returns the dossier file matching the UUID and group.
	*
	* @param uuid the dossier file's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierFile fetchDossierFileByUuidAndGroupId(
		String uuid, long groupId) {
		return _dossierFileLocalService.fetchDossierFileByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> findByDID(
		long dossierId) {
		return _dossierFileLocalService.findByDID(dossierId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> findByGroup(
		long groupId) {
		return _dossierFileLocalService.findByGroup(groupId);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile findLastDossierFile(
		long dossierId, String fileTemplateNo, String dossierTemplateNo) {
		return _dossierFileLocalService.findLastDossierFile(dossierId,
			fileTemplateNo, dossierTemplateNo);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierFileLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getAllDossierFile(
		long dossierId) {
		return _dossierFileLocalService.getAllDossierFile(dossierId);
	}

	/**
	* Get dossierFile by deliverable Code using output DB
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierFile getByDeliverableCode(
		String deliverableCode) {
		return _dossierFileLocalService.getByDeliverableCode(deliverableCode);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile getByDossierAndRef(
		long dossierId, String referenceUid) {
		return _dossierFileLocalService.getByDossierAndRef(dossierId,
			referenceUid);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getByDossierIdAndIsNew(
		long dossierId, boolean isNew) {
		return _dossierFileLocalService.getByDossierIdAndIsNew(dossierId, isNew);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile getByFileEntryId(
		long fileEntryId) {
		return _dossierFileLocalService.getByFileEntryId(fileEntryId);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile getByFileTemplateNo(
		long id, String fileTemplateNo) {
		return _dossierFileLocalService.getByFileTemplateNo(id, fileTemplateNo);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile getByRefAndGroupId(
		long groupId, String referenceUid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.getByRefAndGroupId(groupId, referenceUid);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getByReferenceUid(
		String referenceUid) {
		return _dossierFileLocalService.getByReferenceUid(referenceUid);
	}

	/**
	* Returns the dossier file with the primary key.
	*
	* @param dossierFileId the primary key of the dossier file
	* @return the dossier file
	* @throws PortalException if a dossier file with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierFile getDossierFile(
		long dossierFileId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.getDossierFile(dossierFileId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getDossierFileByDID_DPNO(
		long dossierId, String dossierPartNo, boolean removed) {
		return _dossierFileLocalService.getDossierFileByDID_DPNO(dossierId,
			dossierPartNo, removed);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getDossierFileByDID_FTN(
		long dossierId, String fileTemplateNo) {
		return _dossierFileLocalService.getDossierFileByDID_FTN(dossierId,
			fileTemplateNo);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getDossierFileByDID_FTNO(
		long dossierId, String fileTemplateNo, boolean removed) {
		return _dossierFileLocalService.getDossierFileByDID_FTNO(dossierId,
			fileTemplateNo, removed);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getDossierFileByDID_FTNO_DPT(
		long dossierId, String fileTemplateNo, int dossierPartType,
		boolean removed) {
		return _dossierFileLocalService.getDossierFileByDID_FTNO_DPT(dossierId,
			fileTemplateNo, dossierPartType, removed);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getDossierFileByDID_FTNO_DPT(
		long dossierId, String fileTemplateNo, int dossierPartType,
		boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _dossierFileLocalService.getDossierFileByDID_FTNO_DPT(dossierId,
			fileTemplateNo, dossierPartType, removed, start, end,
			orderByComparator);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile getDossierFileByDID_FTNO_DPT_First(
		long dossierId, String fileTemplateNo, int dossierPartType,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return _dossierFileLocalService.getDossierFileByDID_FTNO_DPT_First(dossierId,
			fileTemplateNo, dossierPartType, removed, orderByComparator);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed) {
		return _dossierFileLocalService.getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(dossierId,
			fileTemplateNo, dossierPartType, fileEntryId, removed);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _dossierFileLocalService.getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(dossierId,
			fileTemplateNo, dossierPartType, fileEntryId, removed, start, end,
			orderByComparator);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile getDossierFileByDID_FTNO_First(
		long dossierId, String fileTemplateNo, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _dossierFileLocalService.getDossierFileByDID_FTNO_First(dossierId,
			fileTemplateNo, removed, orderByComparator);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile getDossierFileByReferenceUid(
		long dossierId, String referenceUid) {
		return _dossierFileLocalService.getDossierFileByReferenceUid(dossierId,
			referenceUid);
	}

	/**
	* Returns the dossier file matching the UUID and group.
	*
	* @param uuid the dossier file's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier file
	* @throws PortalException if a matching dossier file could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierFile getDossierFileByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.getDossierFileByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the dossier files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of dossier files
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getDossierFiles(
		int start, int end) {
		return _dossierFileLocalService.getDossierFiles(start, end);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getDossierFilesByD_DP(
		long dossierId, int dossierPartType) {
		return _dossierFileLocalService.getDossierFilesByD_DP(dossierId,
			dossierPartType);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getDossierFilesByDossierId(
		long dossierId) {
		return _dossierFileLocalService.getDossierFilesByDossierId(dossierId);
	}

	/**
	* Returns all the dossier files matching the UUID and company.
	*
	* @param uuid the UUID of the dossier files
	* @param companyId the primary key of the company
	* @return the matching dossier files, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getDossierFilesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _dossierFileLocalService.getDossierFilesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dossier files matching the UUID and company.
	*
	* @param uuid the UUID of the dossier files
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier files, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierFile> getDossierFilesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.DossierFile> orderByComparator) {
		return _dossierFileLocalService.getDossierFilesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of dossier files.
	*
	* @return the number of dossier files
	*/
	@Override
	public int getDossierFilesCount() {
		return _dossierFileLocalService.getDossierFilesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dossierFileLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierFileLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dossierFileLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile permanentDeleteDossierFile(
		long dossierFileId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.permanentDeleteDossierFile(dossierFileId);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile removeDossierFile(
		long dossierId, String referenceUid,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.removeDossierFile(dossierId,
			referenceUid, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile resetDossierFile(
		long dossierFileId) {
		return _dossierFileLocalService.resetDossierFile(dossierFileId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dossierFileLocalService.searchLucene(params, sorts, start, end,
			searchContext);
	}

	/**
	* Updates the dossier file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierFile the dossier file
	* @return the dossier file that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierFile updateDossierFile(
		org.opencps.dossiermgt.model.DossierFile dossierFile) {
		return _dossierFileLocalService.updateDossierFile(dossierFile);
	}

	/**
	* POST /dossiers/{id|referenceUid}/files/{referenceUid}
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierFile updateDossierFile(
		long groupId, long dossierId, String referenceUid, String displayName,
		String sourceFileName, java.io.InputStream inputStream,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dossierFileLocalService.updateDossierFile(groupId, dossierId,
			referenceUid, displayName, sourceFileName, inputStream,
			serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile updateFormData(
		long groupId, long dossierId, String referenceUid, String formData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dossierFileLocalService.updateFormData(groupId, dossierId,
			referenceUid, formData, serviceContext);
	}

	@Override
	public DossierFileLocalService getWrappedService() {
		return _dossierFileLocalService;
	}

	@Override
	public void setWrappedService(
		DossierFileLocalService dossierFileLocalService) {
		_dossierFileLocalService = dossierFileLocalService;
	}

	private DossierFileLocalService _dossierFileLocalService;
}