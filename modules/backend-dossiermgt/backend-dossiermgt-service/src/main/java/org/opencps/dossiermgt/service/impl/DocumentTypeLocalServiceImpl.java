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
import com.liferay.portal.kernel.cache.thread.local.ThreadLocalCachable;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.service.base.DocumentTypeLocalServiceBaseImpl;

/**
 * The implementation of the document type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DocumentTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DocumentTypeLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil
 */
public class DocumentTypeLocalServiceImpl extends DocumentTypeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil} to access the
	 * document type local service.
	 */

	public DocumentType insertDocType(long userId, long groupId, String typeCode, int templateClass,
			String documentName, String codePattern, String documentScript, int docSync,
			ServiceContext serviceContext) {

		Date now = new Date();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		long docTypeId = counterLocalService.increment(DocumentType.class.getName());

		DocumentType docType = documentTypePersistence.create(docTypeId);

		// Audit field
		docType.setGroupId(groupId);
		docType.setUserId(auditUser.getUserId());
		docType.setCreateDate(now);
		docType.setModifiedDate(now);

		docType.setTypeCode(typeCode);
		docType.setTemplateClass(templateClass);
		docType.setDocumentName(documentName);
		docType.setCodePattern(codePattern);
		docType.setDocumentScript(documentScript);
		docType.setDocSync(docSync);

		return documentTypePersistence.update(docType);

	}

	@ThreadLocalCachable
	public DocumentType getByTypeCode(long groupId, String typeCode) {
		return documentTypePersistence.fetchByF_CODE(groupId, typeCode);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DocumentType updateDocType(Long docId, long userId, long groupId, String typeCode, int templateClass,
			String documentName, String codePattern, String documentScript, int docSync,
			ServiceContext serviceContext) {

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		DocumentType docType = documentTypePersistence.fetchByPrimaryKey(docId);
		if (docType != null) {
			// Audit field
			docType.setUserId(auditUser.getUserId());
			docType.setModifiedDate(now);

			docType.setTypeCode(typeCode);
			docType.setTemplateClass(templateClass);
			docType.setDocumentName(documentName);
			docType.setCodePattern(codePattern);
			docType.setDocumentScript(documentScript);
			docType.setDocSync(docSync);
		} else {
			long docTypeId = counterLocalService.increment(DocumentType.class.getName());
			docType = documentTypePersistence.create(docTypeId);

			// Audit field
			docType.setGroupId(groupId);
			docType.setUserId(auditUser.getUserId());
			docType.setCreateDate(now);
			docType.setModifiedDate(now);

			docType.setTypeCode(typeCode);
			docType.setTemplateClass(templateClass);
			docType.setDocumentName(documentName);
			docType.setCodePattern(codePattern);
			docType.setDocumentScript(documentScript);
			docType.setDocSync(docSync);
		}

		return documentTypePersistence.update(docType);

	}

	@Indexable(type = IndexableType.REINDEX)
	public DocumentType updateDocumentTypeDB(long userId, long groupId, String typeCode, Integer templateClass,
			String documentName, String codePattern, Integer docSync, String documentScript) {

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		DocumentType docType = documentTypePersistence.fetchByF_CODE(groupId, typeCode);
		if (docType != null) {
			// Audit field
			docType.setUserId(auditUser.getUserId());
			docType.setModifiedDate(now);

			if (Validator.isNotNull(typeCode)) {
				docType.setTypeCode(typeCode);
			}
			if (Validator.isNotNull(templateClass)) {
				docType.setTemplateClass(templateClass);
			}
			if (Validator.isNotNull(documentName)) {
				docType.setDocumentName(documentName);
			}
			if (Validator.isNotNull(codePattern)) {
				docType.setCodePattern(codePattern);
			}
			if (Validator.isNotNull(documentScript)) {
				docType.setDocumentScript(documentScript);
			}
			if (Validator.isNotNull(docSync)) {
				docType.setDocSync(docSync);
			}
		} else {
			long docTypeId = counterLocalService.increment(DocumentType.class.getName());
			docType = documentTypePersistence.create(docTypeId);

			// Audit field
			docType.setGroupId(groupId);
			docType.setUserId(auditUser.getUserId());
			docType.setCreateDate(now);
			docType.setModifiedDate(now);

			docType.setTypeCode(typeCode);
			docType.setTemplateClass(templateClass);
			docType.setDocumentName(documentName);
			docType.setCodePattern(codePattern);
			docType.setDocumentScript(documentScript);
			docType.setDocSync(docSync);
		}

		return documentTypePersistence.update(docType);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public DocumentType adminProcessDelete(Long id) {

		DocumentType object = documentTypePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			documentTypePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DocumentType adminProcessData(JSONObject objectData) {

		DocumentType object = null;

		if (objectData.getLong("documentTypeId") > 0) {

			object = documentTypePersistence.fetchByPrimaryKey(objectData.getLong("documentTypeId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DocumentType.class.getName());

			object = documentTypePersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setTypeCode(objectData.getString("typeCode"));
		object.setTemplateClass(objectData.getInt("templateClass"));
		object.setDocumentName(objectData.getString("documentName"));
		object.setCodePattern(objectData.getString("codePattern"));
		object.setDocumentScript(objectData.getString("documentScript"));
		object.setDocSync(objectData.getInt("docSync"));

		documentTypePersistence.update(object);

		return object;
	}
	
	public List<DocumentType> findByG(long groupId) {
		return documentTypePersistence.findByG(groupId);
	}
}