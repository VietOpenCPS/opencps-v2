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

package org.opencps.usermgt.service.impl;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.auth.utils.DLFolderUtil;
import org.opencps.backend.usermgt.service.util.ConfigConstants;
import org.opencps.usermgt.constants.ApplicantDataTerm;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.exception.NoSuchApplicantDataException;
import org.opencps.usermgt.model.ApplicantData;
import org.opencps.usermgt.service.base.ApplicantDataLocalServiceBaseImpl;

/**
 * The implementation of the applicant data local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.usermgt.service.ApplicantDataLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see ApplicantDataLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.ApplicantDataLocalServiceUtil
 */
public class ApplicantDataLocalServiceImpl
	extends ApplicantDataLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.usermgt.service.ApplicantDataLocalServiceUtil} to access the applicant data local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public ApplicantData createApplicantData(ServiceContext context, long groupId, 
			String fileTemplateNo,
			String fileNo,
			String fileName,
			long fileEntryId,
			String metadata,
			int status,
			String applicantIdNo,
			int applicantDataType) throws PortalException, SystemException {
		ApplicantData applicantData = null;

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(context.getUserId());
		long applicantDataId = counterLocalService.increment(ApplicantData.class.getName());
		
		applicantData = applicantDataPersistence.create(applicantDataId);
		applicantData.setModifiedDate(now);
		applicantData.setCreateDate(now);
		applicantData.setCompanyId(context.getCompanyId());
		applicantData.setGroupId(groupId);
		applicantData.setUserId(auditUser.getUserId());
		applicantData.setUserName(auditUser.getScreenName());
		applicantData.setFileTemplateNo(fileTemplateNo);
		applicantData.setFileNo(fileNo);
		applicantData.setFileName(fileName);
		applicantData.setFileEntryId(fileEntryId);
		applicantData.setMetadata(metadata);
		applicantData.setStatus(status);
		applicantData.setApplicantIdNo(applicantIdNo);
		applicantData.setApplicantDataType(applicantDataType);
		
		return applicantData;
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public ApplicantData updateApplicantData(ServiceContext context, long groupId, 
			long applicantDataId,
			String fileTemplateNo,
			String fileNo,
			String fileName,
			long fileEntryId,
			String metadata,
			int status,
			String applicantIdNo,
			int applicantDataType) throws PortalException, SystemException {
		ApplicantData applicantData = null;

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(context.getUserId());
		applicantData = applicantDataPersistence.fetchByPrimaryKey(applicantDataId);
		
		applicantData.setModifiedDate(now);
		applicantData.setCompanyId(context.getCompanyId());
		applicantData.setGroupId(groupId);
		applicantData.setUserId(auditUser.getUserId());
		applicantData.setUserName(auditUser.getScreenName());
		applicantData.setFileTemplateNo(fileTemplateNo);
		applicantData.setFileNo(fileNo);
		applicantData.setFileName(fileName);
		applicantData.setFileEntryId(fileEntryId);
		applicantData.setMetadata(metadata);
		applicantData.setStatus(status);
		applicantData.setApplicantIdNo(applicantIdNo);
		applicantData.setApplicantDataType(applicantDataType);
		
		return applicantData;
	}

	@Override
	public ApplicantData updateApplicantData(long groupId, long applicantDataId, String fileTemplateNo, String fileNo, String fileName, String applicantIdNo, int status, ServiceContext serviceContext) throws PortalException, SystemException {
		ApplicantData applicantData = null;

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(serviceContext.getUserId());

		applicantData = applicantDataPersistence.fetchByPrimaryKey(applicantDataId);
		applicantData.setModifiedDate(now);
		applicantData.setCompanyId(serviceContext.getCompanyId());
		applicantData.setGroupId(groupId);
		applicantData.setUserId(auditUser.getUserId());
		applicantData.setUserName(auditUser.getScreenName());
		applicantData.setFileTemplateNo(fileTemplateNo);
		applicantData.setFileNo(fileNo);
		applicantData.setFileName(fileName);
		applicantData.setApplicantIdNo(applicantIdNo);
		applicantData.setStatus(status);
		applicantData.setApplicantDataType(0);

		applicantData = applicantDataPersistence.update(applicantData);

		return applicantData;

	}

	@Indexable(type = IndexableType.REINDEX)
	public ApplicantData updateApplicantData(ServiceContext context, long groupId, 
			String fileTemplateNo,
			String fileName,
			long fileEntryId,
			String metadata,
			int status,
			String applicantIdNo,
			int applicantDataType,
			String dossierNo,
			String log) throws PortalException, SystemException {
		ApplicantData applicantData = null;

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(context.getUserId());
		applicantData = applicantDataPersistence.fetchByG_DN_FTN_AIN(groupId, dossierNo, fileTemplateNo, applicantIdNo);
		
		if (applicantData == null) {
			long applicantDataId = counterLocalService.increment(ApplicantData.class.getName());
			
			applicantData = applicantDataPersistence.create(applicantDataId);
		}
		
		applicantData.setModifiedDate(now);
		applicantData.setCompanyId(context.getCompanyId());
		applicantData.setGroupId(groupId);
		applicantData.setUserId(auditUser.getUserId());
		applicantData.setUserName(auditUser.getScreenName());
		applicantData.setFileTemplateNo(fileTemplateNo);
		applicantData.setFileName(fileName);
		applicantData.setFileEntryId(fileEntryId);
		applicantData.setMetadata(metadata);
		applicantData.setStatus(status);
		applicantData.setApplicantIdNo(applicantIdNo);
		applicantData.setApplicantDataType(applicantDataType);
		applicantData.setDossierNo(dossierNo);
		applicantData.setLog(log);
		
		return applicantData;
	}
	
	public ApplicantData fetchApplicantData(long applicantDataId) {
		return applicantDataPersistence.fetchByPrimaryKey(applicantDataId);
	}
	
	@Indexable(type = IndexableType.DELETE)
	public ApplicantData deleteApplicantData(long applicantDataId) {
		try {
			ApplicantData applicantData = applicantDataPersistence.remove(applicantDataId);
			return applicantData;
		}
		catch (NoSuchApplicantDataException e) {
			_log.debug(e);
		}
		return null;
	}

	@Indexable(type = IndexableType.REINDEX)
	public ApplicantData createApplicantData(long groupId, 
			String fileTemplateNo,
			String fileNo,
			String fileName,
			String applicantIdNo,
			int status,
			String sourceFileName, 
			InputStream inputStream,
			ServiceContext serviceContext) throws PortalException, SystemException {
		ApplicantData applicantData = null;

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(serviceContext.getUserId());
		long applicantDataId = counterLocalService.increment(ApplicantData.class.getName());
		
		applicantData = applicantDataPersistence.create(applicantDataId);
		applicantData.setModifiedDate(now);
		applicantData.setCreateDate(now);
		applicantData.setCompanyId(serviceContext.getCompanyId());

		applicantData.setUserId(auditUser.getUserId());
		applicantData.setUserName(auditUser.getScreenName());
		applicantData.setFileTemplateNo(fileTemplateNo);
		applicantData.setFileNo(fileNo);
		applicantData.setFileName(fileName);
		applicantData.setApplicantIdNo(applicantIdNo);
		applicantData.setStatus(status);
		applicantData.setApplicantDataType(0);
		
		long fileEntryId = 0;
		if (inputStream != null) {
			try {
				FileEntry fileEntry = uploadApplicantDataFile(serviceContext.getUserId(), groupId, inputStream, sourceFileName, StringPool.BLANK,
					0, serviceContext);

				if (fileEntry != null) {
					fileEntryId = fileEntry.getFileEntryId();
				}
			}
			catch (Exception e) {
				_log.debug(e);
			}
		}
		// Mặc định groupId =0
		applicantData.setGroupId(ApplicantTerm.GROUP_ID_DEFAULT);
		if (fileEntryId != 0) {
			applicantData.setFileEntryId(fileEntryId);
		}
		if (Validator.isNull(fileName) && !Validator.isNotNull(sourceFileName)) {
			applicantData.setFileName(sourceFileName);
		}
		
		applicantData = applicantDataPersistence.update(applicantData);
		
		return applicantData;
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public ApplicantData updateApplicantData(long groupId, 
			long applicantDataId,
			String fileTemplateNo,
			String fileNo,
			String fileName,
			String applicantIdNo,
			int status,
			String sourceFileName, InputStream inputStream,
			ServiceContext serviceContext) throws PortalException, SystemException {
		ApplicantData applicantData = null;

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(serviceContext.getUserId());
		
		applicantData = applicantDataPersistence.fetchByPrimaryKey(applicantDataId);
		applicantData.setModifiedDate(now);
		applicantData.setCompanyId(serviceContext.getCompanyId());
		applicantData.setUserId(auditUser.getUserId());
		applicantData.setUserName(auditUser.getScreenName());
		applicantData.setFileTemplateNo(fileTemplateNo);
		applicantData.setFileNo(fileNo);
		applicantData.setFileName(fileName);
		applicantData.setApplicantIdNo(applicantIdNo);
		applicantData.setStatus(status);
		applicantData.setApplicantDataType(0);
		
		if (applicantData.getFileEntryId() != 0) {
			DLAppLocalServiceUtil.deleteFileEntry(applicantData.getFileEntryId());
		}
		
		long fileEntryId = 0;
		if (inputStream != null) {
			try {
				FileEntry fileEntry = uploadApplicantDataFile(serviceContext.getUserId(), groupId, inputStream, sourceFileName, StringPool.BLANK,
					0, serviceContext);

				if (fileEntry != null) {
					fileEntryId = fileEntry.getFileEntryId();
				}
			}
			catch (Exception e) {
				_log.debug(e);
			}
		}
		applicantData.setGroupId(ApplicantTerm.GROUP_ID_DEFAULT);
		if (fileEntryId != 0) {
			applicantData.setFileEntryId(fileEntryId);
		}
		if (Validator.isNull(fileName) && !Validator.isNotNull(sourceFileName)) {
			applicantData.setFileName(sourceFileName);
		}
		
		applicantData = applicantDataPersistence.update(applicantData);
		
		return applicantData;
	}
	
	private String getFileName(String sourceFileName) {
		String ext = FileUtil.getExtension(sourceFileName);
		
		return Validator.isNotNull(ext) ? (System.currentTimeMillis() + StringPool.PERIOD + ext) :  String.valueOf(System.currentTimeMillis());
	}

	public FileEntry uploadApplicantDataFile(long userId, long groupId,
			InputStream inputStream, String sourceFileName,
			String fileType, long fileSize, ServiceContext serviceContext)
		throws Exception {
		
		return uploadFile(userId, groupId, 0, inputStream, sourceFileName, 
				fileType, fileSize, FOLDER_NAME_APPLICANT_DATA_FILE, serviceContext);
	}

	@Override
	public FileEntry uploadFileDLEntry(long userId, long groupId, InputStream inputStream, String sourceFileName, String fileType, long fileSize, ServiceContext serviceContext) throws Exception {
		return uploadFile(userId, groupId, 0, inputStream, sourceFileName,
				fileType, fileSize, FOLDER_NAME_APPLICANT_DATA_FILE, serviceContext);
	}

	private FileEntry uploadFile(long userId, long groupId, long fileEntryId, InputStream inputStream, String sourceFileName,
								 String fileType, long fileSize, String destination, ServiceContext serviceContext)
		throws Exception {
		
		FileEntry fileEntry = null;
		if (inputStream != null && Validator.isNotNull(sourceFileName)) {
			
			if(Validator.isNull(fileType)) {
				fileType = MimeTypesUtil.getContentType(sourceFileName);
			}
			
			if(fileSize == 0) {
				fileSize = inputStream.available();
			}
			
			String title = getFileName(sourceFileName);

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

			Calendar calendar = Calendar.getInstance();

			calendar.setTime(new Date());
			
			if(destination == null) {
				destination = StringPool.BLANK;
			}

			destination += calendar.get(Calendar.YEAR) + StringPool.SLASH;
			destination += calendar.get(Calendar.MONTH) + StringPool.SLASH;
			destination += calendar.get(Calendar.DAY_OF_MONTH);

			DLFolder dlFolder = DLFolderUtil.getTargetFolder(userId, groupId, groupId, false, 0, destination,
					StringPool.BLANK, false, serviceContext);
			User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

			PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
			PermissionThreadLocal.setPermissionChecker(checker);
			
			if(fileEntryId > 0) {
				fileEntry = DLAppLocalServiceUtil.updateFileEntry(userId, fileEntryId, sourceFileName, 
						fileType, title, title, title, true, inputStream, fileSize, serviceContext);
			} else {
				fileEntry = DLAppLocalServiceUtil.addFileEntry(userId, groupId, dlFolder.getFolderId(), title,
					fileType, title, title,
					StringPool.BLANK, inputStream, fileSize, serviceContext);
			}

		}

		return fileEntry;
	}

	@SuppressWarnings("deprecation")
	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(ApplicantDataTerm.GROUP_ID);
		
		Indexer<ApplicantData> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(ApplicantData.class);

		searchContext.addFullQueryEntryClassName(ApplicantData.class.getName());
		searchContext.setEntryClassNames(new String[] { ApplicantData.class.getName() });
		searchContext.setAttribute(ApplicantDataTerm.PAGINATION_TYPE, ApplicantDataTerm.REGULAR);
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

				query.addFields(
					ApplicantDataTerm.FILE_NAME, ApplicantDataTerm.FILE_NO);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}
		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);
			query.addFields(ApplicantDataTerm.GROUP_ID);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		processSearchParams(params, booleanQuery);
		
		booleanQuery.addRequiredTerm(
			Field.ENTRY_CLASS_NAME, ApplicantData.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	@SuppressWarnings("deprecation")
	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ApplicantData> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ApplicantData.class);

		searchContext.addFullQueryEntryClassName(ApplicantData.class.getName());
		searchContext.setEntryClassNames(new String[] {
			ApplicantData.class.getName()
		});
		searchContext.setAttribute(ApplicantDataTerm.PAGINATION_TYPE, ConfigConstants.PAGINATION_TYPE_REGULAR);
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

				query.addFields(
					ApplicantDataTerm.FILE_NAME, ApplicantDataTerm.FILE_NO);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(ApplicantDataTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		processSearchParams(params, booleanQuery);
		
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, ApplicantData.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}
	
	private void processSearchParams(LinkedHashMap<String, Object> params, BooleanQuery booleanQuery) throws ParseException {
		String applicantIdNo = String.valueOf(params.get(ApplicantDataTerm.APPLICANT_ID_NO));
		String fileNo = String.valueOf(params.get(ApplicantDataTerm.FILE_NO));
		String status = String.valueOf(params.get(ApplicantDataTerm.STATUS));
		String applicantDataType = String.valueOf(params.get(ApplicantDataTerm.APPLICANT_DATA_TYPE));
		String fileTemplateNo = String.valueOf(params.get(ApplicantDataTerm.FILE_TEMPLATE_NO));
		
		if (Validator.isNotNull(applicantIdNo)) {
			MultiMatchQuery query = new MultiMatchQuery(applicantIdNo);

			query.addFields(ApplicantDataTerm.APPLICANT_ID_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(fileNo)) {
			MultiMatchQuery query = new MultiMatchQuery(fileNo);

			query.addFields(ApplicantDataTerm.FILE_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);			
		}
		if (Validator.isNotNull(fileTemplateNo)) {
			MultiMatchQuery query = new MultiMatchQuery(fileTemplateNo);

			query.addFields(ApplicantDataTerm.FILE_TEMPLATE_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);			
		}
		if (Validator.isNotNull(status)) {
			MultiMatchQuery query = new MultiMatchQuery(status);

			query.addFields(ApplicantDataTerm.STATUS);

			booleanQuery.add(query, BooleanClauseOccur.MUST);			
		}
		if (Validator.isNotNull(applicantDataType)) {
			MultiMatchQuery query = new MultiMatchQuery(applicantDataType);

			query.addFields(ApplicantDataTerm.APPLICANT_DATA_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);			
		}		
	}
	
	public ApplicantData active(long applicantDataId) {
		ApplicantData applicantData = applicantDataPersistence.fetchByPrimaryKey(applicantDataId);
		applicantData.setStatus(ApplicantDataTerm.STATUS_ACTIVE);
		return applicantDataPersistence.update(applicantData);
	}
	
	public ApplicantData inActive(long applicantDataId) {
		ApplicantData applicantData = applicantDataPersistence.fetchByPrimaryKey(applicantDataId);
		applicantData.setStatus(ApplicantDataTerm.STATUS_INACTIVE);
		return applicantDataPersistence.update(applicantData);
	}

	public ApplicantData findByG_DN_FTN_AIN(long groupId, String dossierNo, String fileTemplateNo, String applicantIdNo) {
		return applicantDataPersistence.fetchByG_DN_FTN_AIN(groupId, dossierNo, fileTemplateNo, applicantIdNo);
	}
	
	private static final String FOLDER_NAME_APPLICANT_DATA_FILE = "PAYMENT_FILE";

	private static Log _log =
			LogFactoryUtil.getLog(ApplicantDataLocalServiceImpl.class);
}