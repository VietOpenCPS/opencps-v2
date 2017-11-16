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

package org.opencps.dossiermgt.service.impl;

import aQute.bnd.annotation.ProviderType;

import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.exception.DuplicateDossierFileException;
import org.opencps.dossiermgt.exception.InvalidDossierStatusException;
import org.opencps.dossiermgt.exception.NoSuchDossierPartException;
//import org.opencps.dossiermgt.jasperreport.util.JRReportUtil;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.service.base.DossierFileLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

/**
 * The implementation of the dossier file local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierFileLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierFileLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierFileLocalServiceUtil
 */
@ProviderType
public class DossierFileLocalServiceImpl extends DossierFileLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DossierFileLocalServiceUtil} to access the
	 * dossier file local service.
	 */

	/**
	 * POST /dossiers/{id|referenceUid}/files
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DossierFile addDossierFile(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo, String displayName, String sourceFileName, long fileSize,
			InputStream inputStream, ServiceContext serviceContext) throws PortalException, SystemException {

		long userId = serviceContext.getUserId();

		validateAddDossierFile(groupId, dossierId, referenceUid, dossierTemplateNo, dossierPartNo, fileTemplateNo);

		DossierPart dossierPart = dossierPartPersistence.findByTP_NO_PART(groupId, dossierTemplateNo, dossierPartNo);

		long fileEntryId = 0;

		try {
			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, groupId, inputStream, sourceFileName, null,
					fileSize, serviceContext);

			if (fileEntry != null) {
				fileEntryId = fileEntry.getFileEntryId();
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);

		long dossierFileId = counterLocalService.increment(DossierFile.class.getName());

		DossierFile object = dossierFilePersistence.create(dossierFileId);

		// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());
		object.setUserName(userAction.getFullName());

		// Add other fields

		object.setDossierId(dossierId);
		if (Validator.isNull(referenceUid)) {
			referenceUid = PortalUUIDUtil.generate();
		}

		object.setReferenceUid(referenceUid);
		object.setDossierTemplateNo(dossierTemplateNo);
		object.setFileEntryId(fileEntryId);
		object.setDossierPartNo(dossierPartNo);
		object.setFileTemplateNo(fileTemplateNo);
		object.setDossierPartType(dossierPart.getPartType());
		if (Validator.isNull(displayName)) {
			displayName = sourceFileName;
		}

		object.setDisplayName(displayName);
		object.setOriginal(true);
		object.setIsNew(true);

		return dossierFilePersistence.update(object);
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
	@Indexable(type = IndexableType.REINDEX)
	public DossierFile cloneDossierFile(long groupId, long dossierId, long dossierFileId, String dossierTemplateNo,
			String dossierPartNo, ServiceContext serviceContext) throws PortalException, SystemException {

		DossierFile dossierFile = dossierFilePersistence.findByPrimaryKey(dossierFileId);

		User user = userPersistence.findByPrimaryKey(serviceContext.getUserId());

		DossierPart dossierPart = dossierPartPersistence.findByTP_NO_PART(groupId, dossierTemplateNo, dossierPartNo);

		long fileEntryId = 0;

		if (dossierFile.getFileEntryId() > 0) {
			try {
				FileEntry fileEntry = FileUploadUtils.cloneDossierFile(user.getPrimaryKey(), groupId,
						dossierFile.getFileEntryId(), serviceContext);

				fileEntryId = fileEntry.getFileEntryId();
			} catch (Exception e) {
				throw new SystemException(e);
			}
		}

		Date now = new Date();

		DossierFile object = dossierFilePersistence.create(dossierFileId);

		// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(user.getPrimaryKey());
		object.setUserName(user.getFullName());

		// Add other fields

		object.setDossierId(dossierId);
		object.setReferenceUid(PortalUUIDUtil.generate());
		object.setDossierTemplateNo(dossierTemplateNo);
		object.setFileEntryId(fileEntryId);
		object.setDossierPartNo(dossierPartNo);
		object.setFileTemplateNo(dossierFile.getFileTemplateNo());
		object.setDossierPartType(dossierPart.getPartType());
		object.setDisplayName(dossierFile.getDisplayName());
		object.setFormData(dossierFile.getFormData());
		object.setOriginal(false);
		object.setIsNew(true);
		object.setFormScript(dossierFile.getFormScript());
		object.setFormReport(dossierFile.getFormReport());

		return dossierFilePersistence.update(object);
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
	public void cloneDossierFilesByDossierId(long groupId, long newDossierId, long oldDossierId, int dossierPartType,
			ServiceContext serviceContext) throws PortalException, SystemException {

		List<DossierFile> dossierFiles = dossierFileLocalService.getDossierFilesByD_DP(oldDossierId, dossierPartType);

		for (DossierFile dossierFile : dossierFiles) {
			cloneDossierFile(groupId, newDossierId, dossierFile.getDossierFileId(), dossierFile.getDossierTemplateNo(),
					dossierFile.getDossierPartNo(), serviceContext);
		}
	}

	/**
	 * POST /dossiers/{id|referenceUid}/files/{referenceUid}
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DossierFile updateDossierFile(long groupId, long dossierId, String referenceUid, String displayName,
			String sourceFileName, InputStream inputStream, ServiceContext serviceContext)
			throws PortalException, SystemException {

		long userId = serviceContext.getUserId();

		DossierFile dossierFile = dossierFileLocalService.getDossierFileByReferenceUid(dossierId, referenceUid);

		long fileEntryId = 0;

		try {
			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, groupId, dossierFile.getFileEntryId(),
					inputStream, sourceFileName, null, 0, serviceContext);

			if (fileEntry != null) {
				fileEntryId = fileEntry.getFileEntryId();
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);

		// Add audit fields
		dossierFile.setModifiedDate(now);
		dossierFile.setUserId(userAction.getUserId());
		dossierFile.setUserName(userAction.getFullName());

		// Add other fields

		dossierFile.setDossierId(dossierId);
		if (Validator.isNull(referenceUid)) {
			referenceUid = PortalUUIDUtil.generate();
		}

		dossierFile.setFileEntryId(fileEntryId);
		if (Validator.isNull(displayName)) {
			displayName = sourceFileName;
		}

		dossierFile.setDisplayName(displayName);
		dossierFile.setOriginal(true);
		dossierFile.setIsNew(true);

		return dossierFilePersistence.update(dossierFile);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierFile updateFormData(long groupId, long dossierId, String referenceUid, String formData,
			ServiceContext serviceContext) throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(serviceContext.getUserId());

		DossierFile dossierFile = dossierFileLocalService.getDossierFileByReferenceUid(dossierId, referenceUid);

		String jrxmlTemplate = dossierFile.getFormReport();

		if (Validator.isNull(jrxmlTemplate)) {
			DossierPart dossierPart = dossierPartLocalService.fetchByTemplatePartNo(groupId,
					dossierFile.getDossierTemplateNo(), dossierFile.getDossierPartNo());

			if (dossierPart == null) {
				throw new NoSuchDossierPartException();
			}

			jrxmlTemplate = dossierPart.getFormReport();

			dossierFile.setFormReport(jrxmlTemplate);
		}

		dossierFile.setFormData(formData);
		dossierFile.setIsNew(true);

		// auto generate pdf
		long fileEntryId = 0;

		/*
		 * try { File file =
		 * FileUtil.createTempFile(JRReportUtil.DocType.PDF.toString());
		 * 
		 * String sourceFileName = System.currentTimeMillis() +
		 * StringPool.PERIOD + JRReportUtil.DocType.PDF.toString();
		 * 
		 * //JRReportUtil.createReportFile(jrxmlTemplate, formData, null,
		 * file.getCanonicalPath());
		 * 
		 * FileEntry fileEntry = FileUploadUtils.uploadDossierFile(
		 * user.getPrimaryKey(), groupId, file, sourceFileName, serviceContext);
		 * 
		 * fileEntryId = fileEntry.getFileEntryId(); } catch(Exception e) {
		 * throw new SystemException(e); }
		 */

		dossierFile.setFileEntryId(fileEntryId);

		return dossierFilePersistence.update(dossierFile);
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierFile deleteDossierFile(long dossierFileId) throws PortalException {
		DossierFile dossierFile = dossierFilePersistence.findByPrimaryKey(dossierFileId);

		return deleteDossierFile(dossierFile);
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierFile deleteDossierFile(long dossierId, String referenceUid) throws PortalException {
		DossierFile dossierFile = dossierFileLocalService.getDossierFileByReferenceUid(dossierId, referenceUid);

		return deleteDossierFile(dossierFile);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierFile resetDossierFile(long dossierFileId) {
		DossierFile dossierFile = dossierFilePersistence.fetchByPrimaryKey(dossierFileId);
		
		dossierFile.setIsNew(false);
		
		dossierFilePersistence.update(dossierFile);
		
		return dossierFile;
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierFile deleteDossierFile(DossierFile dossierFile) throws PortalException {

		// TODO: validate remove delete dossier file
		validateDeleteDossierFile(dossierFile);

		dossierFile.setModifiedDate(new Date());
		dossierFile.setRemoved(true);

		return dossierFilePersistence.update(dossierFile);
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierFile deletePermanentDossierFile(DossierFile dossierFile) {

		return dossierFilePersistence.remove(dossierFile);
	}

	public List<DossierFile> getDossierFilesByDossierId(long dossierId) {
		return dossierFilePersistence.findByDossierId(dossierId, false);
	}

	public List<DossierFile> getDossierFilesByD_DP(long dossierId, int dossierPartType) {
		return dossierFilePersistence.findByD_DPT(dossierId, dossierPartType, false);
	}

	// TODO: POST /dossiers/{id|referenceUid}/files/{referenceUid}

	public DossierFile getDossierFileByReferenceUid(long dossierId, String referenceUid) throws PortalException {

		return dossierFilePersistence.findByD_RUID(dossierId, referenceUid, false);
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<DossierFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierFile.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(DossierFileTerm.FILE_TEMPLATE_NO);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String fileTemplateNo = GetterUtil.getString(params.get(DossierFileTerm.FILE_TEMPLATE_NO));
		String dossierPartType = GetterUtil.getString(params.get(DossierFileTerm.DOSSIER_PART_TYPE));
		String user_id = GetterUtil.getString(params.get(DossierFileTerm.USER_ID));
		String original = GetterUtil.getString(params.get(DossierFileTerm.ORIGINAL));

		if (Validator.isNotNull(fileTemplateNo)) {
			MultiMatchQuery query = new MultiMatchQuery(fileTemplateNo);

			query.addFields(DossierFileTerm.FILE_TEMPLATE_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dossierPartType)) {
			MultiMatchQuery query = new MultiMatchQuery(dossierPartType);

			query.addFields(DossierFileTerm.DOSSIER_PART_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(user_id)) {
			MultiMatchQuery query = new MultiMatchQuery(user_id);

			query.addFields(DossierFileTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(original)) {
			MultiMatchQuery query = new MultiMatchQuery(original);

			query.addFields(DossierFileTerm.ORIGINAL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<DossierFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierFile.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(DossierFileTerm.FILE_TEMPLATE_NO);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String fileTemplateNo = GetterUtil.getString(params.get(DossierFileTerm.FILE_TEMPLATE_NO));
		String dossierPartType = GetterUtil.getString(params.get(DossierFileTerm.DOSSIER_PART_TYPE));
		String user_id = GetterUtil.getString(params.get(DossierFileTerm.USER_ID));
		String original = GetterUtil.getString(params.get(DossierFileTerm.ORIGINAL));

		if (Validator.isNotNull(fileTemplateNo)) {
			MultiMatchQuery query = new MultiMatchQuery(fileTemplateNo);

			query.addFields(DossierFileTerm.FILE_TEMPLATE_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dossierPartType)) {
			MultiMatchQuery query = new MultiMatchQuery(dossierPartType);

			query.addFields(DossierFileTerm.DOSSIER_PART_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(user_id)) {
			MultiMatchQuery query = new MultiMatchQuery(user_id);

			query.addFields(DossierFileTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(original)) {
			MultiMatchQuery query = new MultiMatchQuery(original);

			query.addFields(DossierFileTerm.ORIGINAL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	/**
	 * Deny if status of dossier not new or waiting
	 * 
	 * @param dossierFile
	 * @throws PortalException
	 */
	private void validateDeleteDossierFile(DossierFile dossierFile) throws PortalException {

		Dossier dossier = dossierPersistence.fetchByPrimaryKey(dossierFile.getDossierId());

		if (dossier != null) {
			if (!dossier.getDossierStatus().equalsIgnoreCase("new")
					&& !dossier.getDossierStatus().equalsIgnoreCase("waiting")) {

				throw new InvalidDossierStatusException();
			}
		}
	}

	private void validateAddDossierFile(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo) throws PortalException {

		// TODO add more logic here

		dossierPersistence.findByPrimaryKey(dossierId);

		if (Validator.isNotNull(referenceUid)) {
			DossierFile dossierFile = dossierFilePersistence.fetchByD_RUID(dossierId, referenceUid, false);

			if (dossierFile != null) {
				throw new DuplicateDossierFileException();
			}
		}
	}

	public static final String CLASS_NAME = DossierFile.class.getName();
}