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

package org.opencps.dossiermgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link DocumentType}.
 * </p>
 *
 * @author huymq
 * @see DocumentType
 * @generated
 */
@ProviderType
public class DocumentTypeWrapper implements DocumentType,
	ModelWrapper<DocumentType> {
	public DocumentTypeWrapper(DocumentType documentType) {
		_documentType = documentType;
	}

	@Override
	public Class<?> getModelClass() {
		return DocumentType.class;
	}

	@Override
	public String getModelClassName() {
		return DocumentType.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("DocumentTypeId", getDocumentTypeId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("typeCode", getTypeCode());
		attributes.put("templateClass", getTemplateClass());
		attributes.put("documentName", getDocumentName());
		attributes.put("codePattern", getCodePattern());
		attributes.put("documentScript", getDocumentScript());
		attributes.put("docSync", getDocSync());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long DocumentTypeId = (Long)attributes.get("DocumentTypeId");

		if (DocumentTypeId != null) {
			setDocumentTypeId(DocumentTypeId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String typeCode = (String)attributes.get("typeCode");

		if (typeCode != null) {
			setTypeCode(typeCode);
		}

		Integer templateClass = (Integer)attributes.get("templateClass");

		if (templateClass != null) {
			setTemplateClass(templateClass);
		}

		String documentName = (String)attributes.get("documentName");

		if (documentName != null) {
			setDocumentName(documentName);
		}

		String codePattern = (String)attributes.get("codePattern");

		if (codePattern != null) {
			setCodePattern(codePattern);
		}

		String documentScript = (String)attributes.get("documentScript");

		if (documentScript != null) {
			setDocumentScript(documentScript);
		}

		Integer docSync = (Integer)attributes.get("docSync");

		if (docSync != null) {
			setDocSync(docSync);
		}
	}

	@Override
	public Object clone() {
		return new DocumentTypeWrapper((DocumentType)_documentType.clone());
	}

	@Override
	public int compareTo(DocumentType documentType) {
		return _documentType.compareTo(documentType);
	}

	/**
	* Returns the code pattern of this document type.
	*
	* @return the code pattern of this document type
	*/
	@Override
	public String getCodePattern() {
		return _documentType.getCodePattern();
	}

	/**
	* Returns the create date of this document type.
	*
	* @return the create date of this document type
	*/
	@Override
	public Date getCreateDate() {
		return _documentType.getCreateDate();
	}

	/**
	* Returns the doc sync of this document type.
	*
	* @return the doc sync of this document type
	*/
	@Override
	public int getDocSync() {
		return _documentType.getDocSync();
	}

	/**
	* Returns the document name of this document type.
	*
	* @return the document name of this document type
	*/
	@Override
	public String getDocumentName() {
		return _documentType.getDocumentName();
	}

	/**
	* Returns the document script of this document type.
	*
	* @return the document script of this document type
	*/
	@Override
	public String getDocumentScript() {
		return _documentType.getDocumentScript();
	}

	/**
	* Returns the document type ID of this document type.
	*
	* @return the document type ID of this document type
	*/
	@Override
	public long getDocumentTypeId() {
		return _documentType.getDocumentTypeId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _documentType.getExpandoBridge();
	}

	/**
	* Returns the group ID of this document type.
	*
	* @return the group ID of this document type
	*/
	@Override
	public long getGroupId() {
		return _documentType.getGroupId();
	}

	/**
	* Returns the modified date of this document type.
	*
	* @return the modified date of this document type
	*/
	@Override
	public Date getModifiedDate() {
		return _documentType.getModifiedDate();
	}

	/**
	* Returns the primary key of this document type.
	*
	* @return the primary key of this document type
	*/
	@Override
	public long getPrimaryKey() {
		return _documentType.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _documentType.getPrimaryKeyObj();
	}

	/**
	* Returns the template class of this document type.
	*
	* @return the template class of this document type
	*/
	@Override
	public int getTemplateClass() {
		return _documentType.getTemplateClass();
	}

	/**
	* Returns the type code of this document type.
	*
	* @return the type code of this document type
	*/
	@Override
	public String getTypeCode() {
		return _documentType.getTypeCode();
	}

	/**
	* Returns the user ID of this document type.
	*
	* @return the user ID of this document type
	*/
	@Override
	public long getUserId() {
		return _documentType.getUserId();
	}

	/**
	* Returns the user uuid of this document type.
	*
	* @return the user uuid of this document type
	*/
	@Override
	public String getUserUuid() {
		return _documentType.getUserUuid();
	}

	/**
	* Returns the uuid of this document type.
	*
	* @return the uuid of this document type
	*/
	@Override
	public String getUuid() {
		return _documentType.getUuid();
	}

	@Override
	public int hashCode() {
		return _documentType.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _documentType.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _documentType.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _documentType.isNew();
	}

	@Override
	public void persist() {
		_documentType.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_documentType.setCachedModel(cachedModel);
	}

	/**
	* Sets the code pattern of this document type.
	*
	* @param codePattern the code pattern of this document type
	*/
	@Override
	public void setCodePattern(String codePattern) {
		_documentType.setCodePattern(codePattern);
	}

	/**
	* Sets the create date of this document type.
	*
	* @param createDate the create date of this document type
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_documentType.setCreateDate(createDate);
	}

	/**
	* Sets the doc sync of this document type.
	*
	* @param docSync the doc sync of this document type
	*/
	@Override
	public void setDocSync(int docSync) {
		_documentType.setDocSync(docSync);
	}

	/**
	* Sets the document name of this document type.
	*
	* @param documentName the document name of this document type
	*/
	@Override
	public void setDocumentName(String documentName) {
		_documentType.setDocumentName(documentName);
	}

	/**
	* Sets the document script of this document type.
	*
	* @param documentScript the document script of this document type
	*/
	@Override
	public void setDocumentScript(String documentScript) {
		_documentType.setDocumentScript(documentScript);
	}

	/**
	* Sets the document type ID of this document type.
	*
	* @param DocumentTypeId the document type ID of this document type
	*/
	@Override
	public void setDocumentTypeId(long DocumentTypeId) {
		_documentType.setDocumentTypeId(DocumentTypeId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_documentType.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_documentType.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_documentType.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this document type.
	*
	* @param groupId the group ID of this document type
	*/
	@Override
	public void setGroupId(long groupId) {
		_documentType.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this document type.
	*
	* @param modifiedDate the modified date of this document type
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_documentType.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_documentType.setNew(n);
	}

	/**
	* Sets the primary key of this document type.
	*
	* @param primaryKey the primary key of this document type
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_documentType.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_documentType.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the template class of this document type.
	*
	* @param templateClass the template class of this document type
	*/
	@Override
	public void setTemplateClass(int templateClass) {
		_documentType.setTemplateClass(templateClass);
	}

	/**
	* Sets the type code of this document type.
	*
	* @param typeCode the type code of this document type
	*/
	@Override
	public void setTypeCode(String typeCode) {
		_documentType.setTypeCode(typeCode);
	}

	/**
	* Sets the user ID of this document type.
	*
	* @param userId the user ID of this document type
	*/
	@Override
	public void setUserId(long userId) {
		_documentType.setUserId(userId);
	}

	/**
	* Sets the user uuid of this document type.
	*
	* @param userUuid the user uuid of this document type
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_documentType.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this document type.
	*
	* @param uuid the uuid of this document type
	*/
	@Override
	public void setUuid(String uuid) {
		_documentType.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DocumentType> toCacheModel() {
		return _documentType.toCacheModel();
	}

	@Override
	public DocumentType toEscapedModel() {
		return new DocumentTypeWrapper(_documentType.toEscapedModel());
	}

	@Override
	public String toString() {
		return _documentType.toString();
	}

	@Override
	public DocumentType toUnescapedModel() {
		return new DocumentTypeWrapper(_documentType.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _documentType.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DocumentTypeWrapper)) {
			return false;
		}

		DocumentTypeWrapper documentTypeWrapper = (DocumentTypeWrapper)obj;

		if (Objects.equals(_documentType, documentTypeWrapper._documentType)) {
			return true;
		}

		return false;
	}

	@Override
	public DocumentType getWrappedModel() {
		return _documentType;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _documentType.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _documentType.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_documentType.resetOriginalValues();
	}

	private final DocumentType _documentType;
}