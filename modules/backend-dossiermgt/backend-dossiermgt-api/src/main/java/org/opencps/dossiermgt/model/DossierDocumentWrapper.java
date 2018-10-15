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
 * This class is a wrapper for {@link DossierDocument}.
 * </p>
 *
 * @author huymq
 * @see DossierDocument
 * @generated
 */
@ProviderType
public class DossierDocumentWrapper implements DossierDocument,
	ModelWrapper<DossierDocument> {
	public DossierDocumentWrapper(DossierDocument dossierDocument) {
		_dossierDocument = dossierDocument;
	}

	@Override
	public Class<?> getModelClass() {
		return DossierDocument.class;
	}

	@Override
	public String getModelClassName() {
		return DossierDocument.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("DossierDocumentId", getDossierDocumentId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierId", getDossierId());
		attributes.put("referenceUid", getReferenceUid());
		attributes.put("dossierActionId", getDossierActionId());
		attributes.put("documentType", getDocumentType());
		attributes.put("documentName", getDocumentName());
		attributes.put("documentCode", getDocumentCode());
		attributes.put("documentFileId", getDocumentFileId());
		attributes.put("docSync", getDocSync());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long DossierDocumentId = (Long)attributes.get("DossierDocumentId");

		if (DossierDocumentId != null) {
			setDossierDocumentId(DossierDocumentId);
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

		Long dossierId = (Long)attributes.get("dossierId");

		if (dossierId != null) {
			setDossierId(dossierId);
		}

		String referenceUid = (String)attributes.get("referenceUid");

		if (referenceUid != null) {
			setReferenceUid(referenceUid);
		}

		Long dossierActionId = (Long)attributes.get("dossierActionId");

		if (dossierActionId != null) {
			setDossierActionId(dossierActionId);
		}

		String documentType = (String)attributes.get("documentType");

		if (documentType != null) {
			setDocumentType(documentType);
		}

		String documentName = (String)attributes.get("documentName");

		if (documentName != null) {
			setDocumentName(documentName);
		}

		String documentCode = (String)attributes.get("documentCode");

		if (documentCode != null) {
			setDocumentCode(documentCode);
		}

		Long documentFileId = (Long)attributes.get("documentFileId");

		if (documentFileId != null) {
			setDocumentFileId(documentFileId);
		}

		Integer docSync = (Integer)attributes.get("docSync");

		if (docSync != null) {
			setDocSync(docSync);
		}
	}

	@Override
	public Object clone() {
		return new DossierDocumentWrapper((DossierDocument)_dossierDocument.clone());
	}

	@Override
	public int compareTo(DossierDocument dossierDocument) {
		return _dossierDocument.compareTo(dossierDocument);
	}

	/**
	* Returns the create date of this dossier document.
	*
	* @return the create date of this dossier document
	*/
	@Override
	public Date getCreateDate() {
		return _dossierDocument.getCreateDate();
	}

	/**
	* Returns the doc sync of this dossier document.
	*
	* @return the doc sync of this dossier document
	*/
	@Override
	public int getDocSync() {
		return _dossierDocument.getDocSync();
	}

	/**
	* Returns the document code of this dossier document.
	*
	* @return the document code of this dossier document
	*/
	@Override
	public String getDocumentCode() {
		return _dossierDocument.getDocumentCode();
	}

	/**
	* Returns the document file ID of this dossier document.
	*
	* @return the document file ID of this dossier document
	*/
	@Override
	public long getDocumentFileId() {
		return _dossierDocument.getDocumentFileId();
	}

	/**
	* Returns the document name of this dossier document.
	*
	* @return the document name of this dossier document
	*/
	@Override
	public String getDocumentName() {
		return _dossierDocument.getDocumentName();
	}

	/**
	* Returns the document type of this dossier document.
	*
	* @return the document type of this dossier document
	*/
	@Override
	public String getDocumentType() {
		return _dossierDocument.getDocumentType();
	}

	/**
	* Returns the dossier action ID of this dossier document.
	*
	* @return the dossier action ID of this dossier document
	*/
	@Override
	public long getDossierActionId() {
		return _dossierDocument.getDossierActionId();
	}

	/**
	* Returns the dossier document ID of this dossier document.
	*
	* @return the dossier document ID of this dossier document
	*/
	@Override
	public long getDossierDocumentId() {
		return _dossierDocument.getDossierDocumentId();
	}

	/**
	* Returns the dossier ID of this dossier document.
	*
	* @return the dossier ID of this dossier document
	*/
	@Override
	public long getDossierId() {
		return _dossierDocument.getDossierId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierDocument.getExpandoBridge();
	}

	/**
	* Returns the group ID of this dossier document.
	*
	* @return the group ID of this dossier document
	*/
	@Override
	public long getGroupId() {
		return _dossierDocument.getGroupId();
	}

	/**
	* Returns the modified date of this dossier document.
	*
	* @return the modified date of this dossier document
	*/
	@Override
	public Date getModifiedDate() {
		return _dossierDocument.getModifiedDate();
	}

	/**
	* Returns the primary key of this dossier document.
	*
	* @return the primary key of this dossier document
	*/
	@Override
	public long getPrimaryKey() {
		return _dossierDocument.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierDocument.getPrimaryKeyObj();
	}

	/**
	* Returns the reference uid of this dossier document.
	*
	* @return the reference uid of this dossier document
	*/
	@Override
	public String getReferenceUid() {
		return _dossierDocument.getReferenceUid();
	}

	/**
	* Returns the user ID of this dossier document.
	*
	* @return the user ID of this dossier document
	*/
	@Override
	public long getUserId() {
		return _dossierDocument.getUserId();
	}

	/**
	* Returns the user uuid of this dossier document.
	*
	* @return the user uuid of this dossier document
	*/
	@Override
	public String getUserUuid() {
		return _dossierDocument.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier document.
	*
	* @return the uuid of this dossier document
	*/
	@Override
	public String getUuid() {
		return _dossierDocument.getUuid();
	}

	@Override
	public int hashCode() {
		return _dossierDocument.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dossierDocument.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossierDocument.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dossierDocument.isNew();
	}

	@Override
	public void persist() {
		_dossierDocument.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossierDocument.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this dossier document.
	*
	* @param createDate the create date of this dossier document
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dossierDocument.setCreateDate(createDate);
	}

	/**
	* Sets the doc sync of this dossier document.
	*
	* @param docSync the doc sync of this dossier document
	*/
	@Override
	public void setDocSync(int docSync) {
		_dossierDocument.setDocSync(docSync);
	}

	/**
	* Sets the document code of this dossier document.
	*
	* @param documentCode the document code of this dossier document
	*/
	@Override
	public void setDocumentCode(String documentCode) {
		_dossierDocument.setDocumentCode(documentCode);
	}

	/**
	* Sets the document file ID of this dossier document.
	*
	* @param documentFileId the document file ID of this dossier document
	*/
	@Override
	public void setDocumentFileId(long documentFileId) {
		_dossierDocument.setDocumentFileId(documentFileId);
	}

	/**
	* Sets the document name of this dossier document.
	*
	* @param documentName the document name of this dossier document
	*/
	@Override
	public void setDocumentName(String documentName) {
		_dossierDocument.setDocumentName(documentName);
	}

	/**
	* Sets the document type of this dossier document.
	*
	* @param documentType the document type of this dossier document
	*/
	@Override
	public void setDocumentType(String documentType) {
		_dossierDocument.setDocumentType(documentType);
	}

	/**
	* Sets the dossier action ID of this dossier document.
	*
	* @param dossierActionId the dossier action ID of this dossier document
	*/
	@Override
	public void setDossierActionId(long dossierActionId) {
		_dossierDocument.setDossierActionId(dossierActionId);
	}

	/**
	* Sets the dossier document ID of this dossier document.
	*
	* @param DossierDocumentId the dossier document ID of this dossier document
	*/
	@Override
	public void setDossierDocumentId(long DossierDocumentId) {
		_dossierDocument.setDossierDocumentId(DossierDocumentId);
	}

	/**
	* Sets the dossier ID of this dossier document.
	*
	* @param dossierId the dossier ID of this dossier document
	*/
	@Override
	public void setDossierId(long dossierId) {
		_dossierDocument.setDossierId(dossierId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierDocument.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierDocument.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierDocument.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dossier document.
	*
	* @param groupId the group ID of this dossier document
	*/
	@Override
	public void setGroupId(long groupId) {
		_dossierDocument.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this dossier document.
	*
	* @param modifiedDate the modified date of this dossier document
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dossierDocument.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dossierDocument.setNew(n);
	}

	/**
	* Sets the primary key of this dossier document.
	*
	* @param primaryKey the primary key of this dossier document
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dossierDocument.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierDocument.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reference uid of this dossier document.
	*
	* @param referenceUid the reference uid of this dossier document
	*/
	@Override
	public void setReferenceUid(String referenceUid) {
		_dossierDocument.setReferenceUid(referenceUid);
	}

	/**
	* Sets the user ID of this dossier document.
	*
	* @param userId the user ID of this dossier document
	*/
	@Override
	public void setUserId(long userId) {
		_dossierDocument.setUserId(userId);
	}

	/**
	* Sets the user uuid of this dossier document.
	*
	* @param userUuid the user uuid of this dossier document
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dossierDocument.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier document.
	*
	* @param uuid the uuid of this dossier document
	*/
	@Override
	public void setUuid(String uuid) {
		_dossierDocument.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierDocument> toCacheModel() {
		return _dossierDocument.toCacheModel();
	}

	@Override
	public DossierDocument toEscapedModel() {
		return new DossierDocumentWrapper(_dossierDocument.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dossierDocument.toString();
	}

	@Override
	public DossierDocument toUnescapedModel() {
		return new DossierDocumentWrapper(_dossierDocument.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dossierDocument.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierDocumentWrapper)) {
			return false;
		}

		DossierDocumentWrapper dossierDocumentWrapper = (DossierDocumentWrapper)obj;

		if (Objects.equals(_dossierDocument,
					dossierDocumentWrapper._dossierDocument)) {
			return true;
		}

		return false;
	}

	@Override
	public DossierDocument getWrappedModel() {
		return _dossierDocument;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossierDocument.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossierDocument.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossierDocument.resetOriginalValues();
	}

	private final DossierDocument _dossierDocument;
}