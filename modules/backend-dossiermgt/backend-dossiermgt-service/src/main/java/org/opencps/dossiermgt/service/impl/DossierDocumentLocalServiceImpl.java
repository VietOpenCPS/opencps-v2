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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.exception.NoSuchDossierDocumentException;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.service.base.DossierDocumentLocalServiceBaseImpl;

/**
 * The implementation of the dossier document local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierDocumentLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierDocumentLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierDocumentLocalServiceUtil
 */
public class DossierDocumentLocalServiceImpl extends DossierDocumentLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DossierDocumentLocalServiceUtil} to access the
	 * dossier document local service.
	 */

	public List<DossierDocument> getDossierDocumentList(Long dossierId, Integer start, Integer end) {
		return dossierDocumentPersistence.findByF_DOSSIERID(dossierId, start, end);
	}

	public long countDossierDocumentList(Long dossierId) {
		return dossierDocumentPersistence.countByF_DOSSIERID(dossierId);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierDocument addDossierDoc(long groupId, Long dossierId, long dossierActionId, String documentType,
			String documentName, String documentCode, String sourceFileName, long fileSize, InputStream inputStream,
			String fileType, ServiceContext serviceContext) {
		long userId = serviceContext.getUserId();

		// _log.info("****Start add file at:" + new Date());

		// validateAddDossierFile(groupId, dossierId, referenceUid, dossierTemplateNo,
		// dossierPartNo, fileTemplateNo);

		// _log.info("****End validator file at:" + new Date());

		// DossierPart dossierPart = dossierPartPersistence.findByTP_NO_PART(groupId,
		// dossierTemplateNo, dossierPartNo);

		long fileEntryId = 0;

		try {
			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, groupId, inputStream, sourceFileName,
					fileType, fileSize, serviceContext);

			if (fileEntry != null) {
				fileEntryId = fileEntry.getFileEntryId();
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}
		// _log.info("****End uploadFile file at:" + new Date());

		Date now = new Date();

		// User userAction = null;

		// if (userId != 0) {
		// userAction = userLocalService.getUser(userId);
		// }

		long dossierDocId = counterLocalService.increment(DossierDocument.class.getName());

		DossierDocument object = dossierDocumentPersistence.create(dossierDocId);

		// Add audit fields
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userId);

		// Add other fields
		object.setDossierId(dossierId);
		object.setDossierActionId(dossierActionId);
		object.setDocumentType(documentType);
		object.setDocumentName(documentName);
		object.setDocumentCode(documentCode);
		object.setDocumentFileId(fileEntryId);
		// TODO: docSync

		return dossierDocumentPersistence.update(object);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierDocument addDossierDoc(long groupId, Long dossierId, String referenceUid, long dossierActionId,
			String documentType, String documentName, String documentCode, long documentFileId, int docSync,
			ServiceContext serviceContext) {
		long userId = serviceContext.getUserId();

		Date now = new Date();

		long dossierDocId = counterLocalService.increment(DossierDocument.class.getName());

		DossierDocument object = dossierDocumentPersistence.create(dossierDocId);

		// Add audit fields
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userId);

		// Add other fields
		object.setDossierId(dossierId);
		object.setReferenceUid(referenceUid);
		object.setDossierActionId(dossierActionId);
		object.setDocumentType(documentType);
		object.setDocumentName(documentName);
		object.setDocumentCode(documentCode);
		object.setDocumentFileId(documentFileId);
		object.setDocSync(docSync);
		// TODO: docSync

		return dossierDocumentPersistence.update(object);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierDocument updateDossierDoc(long groupId, long dossierDocId, Long dossierId, String referenceUid,
			long dossierActionId, String documentType, String documentName, String documentCode, long documentFileId,
			int docSync, ServiceContext serviceContext) throws NoSuchDossierDocumentException {
		long userId = serviceContext.getUserId();

		Date now = new Date();

		DossierDocument object = dossierDocumentPersistence.findByPrimaryKey(dossierDocId);

		// Add audit fields
		object.setGroupId(groupId);
		object.setModifiedDate(now);
		object.setUserId(userId);

		// Add other fields
		object.setDossierId(dossierId);
		object.setReferenceUid(referenceUid);
		object.setDossierActionId(dossierActionId);
		object.setDocumentType(documentType);
		object.setDocumentName(documentName);
		object.setDocumentCode(documentCode);
		object.setDocumentFileId(documentFileId);
		object.setDocSync(docSync);

		return dossierDocumentPersistence.update(object);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierDocument addDossierDoc(long groupId, Long dossierId, String referenceUid, long dossierActionId,
			String documentType, String documentName, String documentCode, String sourceFileName, long fileSize,
			InputStream inputStream, String fileType, ServiceContext serviceContext) {
		long userId = serviceContext.getUserId();

		// _log.info("****Start add file at:" + new Date());

		// validateAddDossierFile(groupId, dossierId, referenceUid, dossierTemplateNo,
		// dossierPartNo, fileTemplateNo);

		// _log.info("****End validator file at:" + new Date());

		// DossierPart dossierPart = dossierPartPersistence.findByTP_NO_PART(groupId,
		// dossierTemplateNo, dossierPartNo);

		long fileEntryId = 0;

		try {
			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, groupId, inputStream, sourceFileName,
					fileType, fileSize, serviceContext);

			if (fileEntry != null) {
				fileEntryId = fileEntry.getFileEntryId();
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}
		// _log.info("****End uploadFile file at:" + new Date());

		Date now = new Date();

		// User userAction = null;

		// if (userId != 0) {
		// userAction = userLocalService.getUser(userId);
		// }

		long dossierDocId = counterLocalService.increment(DossierDocument.class.getName());

		DossierDocument object = dossierDocumentPersistence.create(dossierDocId);

		// Add audit fields
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userId);

		// Add other fields
		object.setReferenceUid(referenceUid);
		object.setDossierId(dossierId);
		object.setDossierActionId(dossierActionId);
		object.setDocumentType(documentType);
		object.setDocumentName(documentName);
		object.setDocumentCode(documentCode);
		object.setDocumentFileId(fileEntryId);
		// TODO: docSync

		return dossierDocumentPersistence.update(object);
	}

	public DossierDocument getByActiocId(long groupId, long dossierActionId) {
		return dossierDocumentPersistence.fetchByF_GID_DID(groupId, dossierActionId);
	}

	public DossierDocument getDocByReferenceUid(long groupId, long dossierId, String referenceUid) {
		return dossierDocumentPersistence.fetchByF_GID_DID_REF(groupId, dossierId, referenceUid);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierDocument updateDossierDoc(long groupId, long dossierDocId, Long dossierId, String referenceUid,
			long dossierActionId, String documentType, String documentName, String documentCode, String sourceFileName,
			long fileSize, InputStream inputStream, String fileType, ServiceContext serviceContext) {
		long userId = serviceContext.getUserId();

		// _log.info("****Start add file at:" + new Date());

		// validateAddDossierFile(groupId, dossierId, referenceUid, dossierTemplateNo,
		// dossierPartNo, fileTemplateNo);

		// _log.info("****End validator file at:" + new Date());

		// DossierPart dossierPart = dossierPartPersistence.findByTP_NO_PART(groupId,
		// dossierTemplateNo, dossierPartNo);

		long fileEntryId = 0;

		try {
			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, groupId, inputStream, sourceFileName,
					fileType, fileSize, serviceContext);

			if (fileEntry != null) {
				fileEntryId = fileEntry.getFileEntryId();
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}
		// _log.info("****End uploadFile file at:" + new Date());

		Date now = new Date();

		// User userAction = null;

		// if (userId != 0) {
		// userAction = userLocalService.getUser(userId);
		// }

		DossierDocument object = dossierDocumentPersistence.fetchByPrimaryKey(dossierDocId);
		if (object != null) {
			// Add audit fields
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userId);

			// Add other fields
			object.setReferenceUid(referenceUid);
			object.setDossierId(dossierId);
			object.setDossierActionId(dossierActionId);
			object.setDocumentType(documentType);
			object.setDocumentName(documentName);
			object.setDocumentCode(documentCode);
			object.setDocumentFileId(fileEntryId);
		}

		return dossierDocumentPersistence.update(object);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public DossierDocument adminProcessDelete(Long id) {

		DossierDocument object = dossierDocumentPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			dossierDocumentPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierDocument adminProcessData(JSONObject objectData) {

		DossierDocument object = null;

		if (objectData.getLong("DossierDocumentId") > 0) {

			object = dossierDocumentPersistence.fetchByPrimaryKey(objectData.getLong("DossierDocumentId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DossierDocument.class.getName());

			object = dossierDocumentPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setDossierId(objectData.getLong("dossierId"));
		object.setReferenceUid(objectData.getString("referenceUid"));
		object.setDossierActionId(objectData.getLong("dossierActionId"));
		object.setDocumentType(objectData.getString("documentType"));
		object.setDocumentName(objectData.getString("documentName"));
		object.setDocumentCode(objectData.getString("documentCode"));
		// object.setDocumentFileId(objectData.getString("userName")documentFileId);
		object.setDocSync(objectData.getInt("docSync"));

		dossierDocumentPersistence.update(object);

		return object;
	}
}