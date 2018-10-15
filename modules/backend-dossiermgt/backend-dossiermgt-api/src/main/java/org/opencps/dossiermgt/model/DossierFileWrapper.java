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

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link DossierFile}.
 * </p>
 *
 * @author huymq
 * @see DossierFile
 * @generated
 */
@ProviderType
public class DossierFileWrapper implements DossierFile,
	ModelWrapper<DossierFile> {
	public DossierFileWrapper(DossierFile dossierFile) {
		_dossierFile = dossierFile;
	}

	@Override
	public Class<?> getModelClass() {
		return DossierFile.class;
	}

	@Override
	public String getModelClassName() {
		return DossierFile.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dossierFileId", getDossierFileId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierId", getDossierId());
		attributes.put("referenceUid", getReferenceUid());
		attributes.put("dossierTemplateNo", getDossierTemplateNo());
		attributes.put("dossierPartNo", getDossierPartNo());
		attributes.put("dossierPartType", getDossierPartType());
		attributes.put("fileTemplateNo", getFileTemplateNo());
		attributes.put("displayName", getDisplayName());
		attributes.put("formData", getFormData());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("original", isOriginal());
		attributes.put("eForm", isEForm());
		attributes.put("isNew", isIsNew());
		attributes.put("removed", isRemoved());
		attributes.put("signCheck", getSignCheck());
		attributes.put("signInfo", getSignInfo());
		attributes.put("formScript", getFormScript());
		attributes.put("formReport", getFormReport());
		attributes.put("formSchema", getFormSchema());
		attributes.put("deliverableCode", getDeliverableCode());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dossierFileId = (Long)attributes.get("dossierFileId");

		if (dossierFileId != null) {
			setDossierFileId(dossierFileId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
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

		String dossierTemplateNo = (String)attributes.get("dossierTemplateNo");

		if (dossierTemplateNo != null) {
			setDossierTemplateNo(dossierTemplateNo);
		}

		String dossierPartNo = (String)attributes.get("dossierPartNo");

		if (dossierPartNo != null) {
			setDossierPartNo(dossierPartNo);
		}

		Integer dossierPartType = (Integer)attributes.get("dossierPartType");

		if (dossierPartType != null) {
			setDossierPartType(dossierPartType);
		}

		String fileTemplateNo = (String)attributes.get("fileTemplateNo");

		if (fileTemplateNo != null) {
			setFileTemplateNo(fileTemplateNo);
		}

		String displayName = (String)attributes.get("displayName");

		if (displayName != null) {
			setDisplayName(displayName);
		}

		String formData = (String)attributes.get("formData");

		if (formData != null) {
			setFormData(formData);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Boolean original = (Boolean)attributes.get("original");

		if (original != null) {
			setOriginal(original);
		}

		Boolean eForm = (Boolean)attributes.get("eForm");

		if (eForm != null) {
			setEForm(eForm);
		}

		Boolean isNew = (Boolean)attributes.get("isNew");

		if (isNew != null) {
			setIsNew(isNew);
		}

		Boolean removed = (Boolean)attributes.get("removed");

		if (removed != null) {
			setRemoved(removed);
		}

		Integer signCheck = (Integer)attributes.get("signCheck");

		if (signCheck != null) {
			setSignCheck(signCheck);
		}

		String signInfo = (String)attributes.get("signInfo");

		if (signInfo != null) {
			setSignInfo(signInfo);
		}

		String formScript = (String)attributes.get("formScript");

		if (formScript != null) {
			setFormScript(formScript);
		}

		String formReport = (String)attributes.get("formReport");

		if (formReport != null) {
			setFormReport(formReport);
		}

		String formSchema = (String)attributes.get("formSchema");

		if (formSchema != null) {
			setFormSchema(formSchema);
		}

		String deliverableCode = (String)attributes.get("deliverableCode");

		if (deliverableCode != null) {
			setDeliverableCode(deliverableCode);
		}
	}

	@Override
	public Object clone() {
		return new DossierFileWrapper((DossierFile)_dossierFile.clone());
	}

	@Override
	public int compareTo(DossierFile dossierFile) {
		return _dossierFile.compareTo(dossierFile);
	}

	/**
	* Returns the company ID of this dossier file.
	*
	* @return the company ID of this dossier file
	*/
	@Override
	public long getCompanyId() {
		return _dossierFile.getCompanyId();
	}

	/**
	* Returns the create date of this dossier file.
	*
	* @return the create date of this dossier file
	*/
	@Override
	public Date getCreateDate() {
		return _dossierFile.getCreateDate();
	}

	/**
	* Returns the deliverable code of this dossier file.
	*
	* @return the deliverable code of this dossier file
	*/
	@Override
	public String getDeliverableCode() {
		return _dossierFile.getDeliverableCode();
	}

	/**
	* Returns the display name of this dossier file.
	*
	* @return the display name of this dossier file
	*/
	@Override
	public String getDisplayName() {
		return _dossierFile.getDisplayName();
	}

	/**
	* Returns the dossier file ID of this dossier file.
	*
	* @return the dossier file ID of this dossier file
	*/
	@Override
	public long getDossierFileId() {
		return _dossierFile.getDossierFileId();
	}

	/**
	* Returns the dossier ID of this dossier file.
	*
	* @return the dossier ID of this dossier file
	*/
	@Override
	public long getDossierId() {
		return _dossierFile.getDossierId();
	}

	/**
	* Returns the dossier part no of this dossier file.
	*
	* @return the dossier part no of this dossier file
	*/
	@Override
	public String getDossierPartNo() {
		return _dossierFile.getDossierPartNo();
	}

	/**
	* Returns the dossier part type of this dossier file.
	*
	* @return the dossier part type of this dossier file
	*/
	@Override
	public int getDossierPartType() {
		return _dossierFile.getDossierPartType();
	}

	/**
	* Returns the dossier template no of this dossier file.
	*
	* @return the dossier template no of this dossier file
	*/
	@Override
	public String getDossierTemplateNo() {
		return _dossierFile.getDossierTemplateNo();
	}

	/**
	* Returns the e form of this dossier file.
	*
	* @return the e form of this dossier file
	*/
	@Override
	public boolean getEForm() {
		return _dossierFile.getEForm();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierFile.getExpandoBridge();
	}

	/**
	* Returns the file entry ID of this dossier file.
	*
	* @return the file entry ID of this dossier file
	*/
	@Override
	public long getFileEntryId() {
		return _dossierFile.getFileEntryId();
	}

	/**
	* Returns the file template no of this dossier file.
	*
	* @return the file template no of this dossier file
	*/
	@Override
	public String getFileTemplateNo() {
		return _dossierFile.getFileTemplateNo();
	}

	/**
	* Returns the form data of this dossier file.
	*
	* @return the form data of this dossier file
	*/
	@Override
	public String getFormData() {
		return _dossierFile.getFormData();
	}

	/**
	* Returns the form report of this dossier file.
	*
	* @return the form report of this dossier file
	*/
	@Override
	public String getFormReport() {
		return _dossierFile.getFormReport();
	}

	/**
	* Returns the form schema of this dossier file.
	*
	* @return the form schema of this dossier file
	*/
	@Override
	public String getFormSchema() {
		return _dossierFile.getFormSchema();
	}

	/**
	* Returns the form script of this dossier file.
	*
	* @return the form script of this dossier file
	*/
	@Override
	public String getFormScript() {
		return _dossierFile.getFormScript();
	}

	/**
	* Returns the group ID of this dossier file.
	*
	* @return the group ID of this dossier file
	*/
	@Override
	public long getGroupId() {
		return _dossierFile.getGroupId();
	}

	/**
	* Returns the is new of this dossier file.
	*
	* @return the is new of this dossier file
	*/
	@Override
	public boolean getIsNew() {
		return _dossierFile.getIsNew();
	}

	/**
	* Returns the modified date of this dossier file.
	*
	* @return the modified date of this dossier file
	*/
	@Override
	public Date getModifiedDate() {
		return _dossierFile.getModifiedDate();
	}

	/**
	* Returns the original of this dossier file.
	*
	* @return the original of this dossier file
	*/
	@Override
	public boolean getOriginal() {
		return _dossierFile.getOriginal();
	}

	/**
	* Returns the primary key of this dossier file.
	*
	* @return the primary key of this dossier file
	*/
	@Override
	public long getPrimaryKey() {
		return _dossierFile.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierFile.getPrimaryKeyObj();
	}

	/**
	* Returns the reference uid of this dossier file.
	*
	* @return the reference uid of this dossier file
	*/
	@Override
	public String getReferenceUid() {
		return _dossierFile.getReferenceUid();
	}

	/**
	* Returns the removed of this dossier file.
	*
	* @return the removed of this dossier file
	*/
	@Override
	public boolean getRemoved() {
		return _dossierFile.getRemoved();
	}

	/**
	* Returns the sign check of this dossier file.
	*
	* @return the sign check of this dossier file
	*/
	@Override
	public int getSignCheck() {
		return _dossierFile.getSignCheck();
	}

	/**
	* Returns the sign info of this dossier file.
	*
	* @return the sign info of this dossier file
	*/
	@Override
	public String getSignInfo() {
		return _dossierFile.getSignInfo();
	}

	/**
	* Returns the user ID of this dossier file.
	*
	* @return the user ID of this dossier file
	*/
	@Override
	public long getUserId() {
		return _dossierFile.getUserId();
	}

	/**
	* Returns the user name of this dossier file.
	*
	* @return the user name of this dossier file
	*/
	@Override
	public String getUserName() {
		return _dossierFile.getUserName();
	}

	/**
	* Returns the user uuid of this dossier file.
	*
	* @return the user uuid of this dossier file
	*/
	@Override
	public String getUserUuid() {
		return _dossierFile.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier file.
	*
	* @return the uuid of this dossier file
	*/
	@Override
	public String getUuid() {
		return _dossierFile.getUuid();
	}

	@Override
	public int hashCode() {
		return _dossierFile.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dossierFile.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this dossier file is e form.
	*
	* @return <code>true</code> if this dossier file is e form; <code>false</code> otherwise
	*/
	@Override
	public boolean isEForm() {
		return _dossierFile.isEForm();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossierFile.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this dossier file is is new.
	*
	* @return <code>true</code> if this dossier file is is new; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsNew() {
		return _dossierFile.isIsNew();
	}

	@Override
	public boolean isNew() {
		return _dossierFile.isNew();
	}

	/**
	* Returns <code>true</code> if this dossier file is original.
	*
	* @return <code>true</code> if this dossier file is original; <code>false</code> otherwise
	*/
	@Override
	public boolean isOriginal() {
		return _dossierFile.isOriginal();
	}

	/**
	* Returns <code>true</code> if this dossier file is removed.
	*
	* @return <code>true</code> if this dossier file is removed; <code>false</code> otherwise
	*/
	@Override
	public boolean isRemoved() {
		return _dossierFile.isRemoved();
	}

	@Override
	public void persist() {
		_dossierFile.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossierFile.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dossier file.
	*
	* @param companyId the company ID of this dossier file
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dossierFile.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dossier file.
	*
	* @param createDate the create date of this dossier file
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dossierFile.setCreateDate(createDate);
	}

	/**
	* Sets the deliverable code of this dossier file.
	*
	* @param deliverableCode the deliverable code of this dossier file
	*/
	@Override
	public void setDeliverableCode(String deliverableCode) {
		_dossierFile.setDeliverableCode(deliverableCode);
	}

	/**
	* Sets the display name of this dossier file.
	*
	* @param displayName the display name of this dossier file
	*/
	@Override
	public void setDisplayName(String displayName) {
		_dossierFile.setDisplayName(displayName);
	}

	/**
	* Sets the dossier file ID of this dossier file.
	*
	* @param dossierFileId the dossier file ID of this dossier file
	*/
	@Override
	public void setDossierFileId(long dossierFileId) {
		_dossierFile.setDossierFileId(dossierFileId);
	}

	/**
	* Sets the dossier ID of this dossier file.
	*
	* @param dossierId the dossier ID of this dossier file
	*/
	@Override
	public void setDossierId(long dossierId) {
		_dossierFile.setDossierId(dossierId);
	}

	/**
	* Sets the dossier part no of this dossier file.
	*
	* @param dossierPartNo the dossier part no of this dossier file
	*/
	@Override
	public void setDossierPartNo(String dossierPartNo) {
		_dossierFile.setDossierPartNo(dossierPartNo);
	}

	/**
	* Sets the dossier part type of this dossier file.
	*
	* @param dossierPartType the dossier part type of this dossier file
	*/
	@Override
	public void setDossierPartType(int dossierPartType) {
		_dossierFile.setDossierPartType(dossierPartType);
	}

	/**
	* Sets the dossier template no of this dossier file.
	*
	* @param dossierTemplateNo the dossier template no of this dossier file
	*/
	@Override
	public void setDossierTemplateNo(String dossierTemplateNo) {
		_dossierFile.setDossierTemplateNo(dossierTemplateNo);
	}

	/**
	* Sets whether this dossier file is e form.
	*
	* @param eForm the e form of this dossier file
	*/
	@Override
	public void setEForm(boolean eForm) {
		_dossierFile.setEForm(eForm);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierFile.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierFile.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierFile.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file entry ID of this dossier file.
	*
	* @param fileEntryId the file entry ID of this dossier file
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_dossierFile.setFileEntryId(fileEntryId);
	}

	/**
	* Sets the file template no of this dossier file.
	*
	* @param fileTemplateNo the file template no of this dossier file
	*/
	@Override
	public void setFileTemplateNo(String fileTemplateNo) {
		_dossierFile.setFileTemplateNo(fileTemplateNo);
	}

	/**
	* Sets the form data of this dossier file.
	*
	* @param formData the form data of this dossier file
	*/
	@Override
	public void setFormData(String formData) {
		_dossierFile.setFormData(formData);
	}

	/**
	* Sets the form report of this dossier file.
	*
	* @param formReport the form report of this dossier file
	*/
	@Override
	public void setFormReport(String formReport) {
		_dossierFile.setFormReport(formReport);
	}

	/**
	* Sets the form schema of this dossier file.
	*
	* @param formSchema the form schema of this dossier file
	*/
	@Override
	public void setFormSchema(String formSchema) {
		_dossierFile.setFormSchema(formSchema);
	}

	/**
	* Sets the form script of this dossier file.
	*
	* @param formScript the form script of this dossier file
	*/
	@Override
	public void setFormScript(String formScript) {
		_dossierFile.setFormScript(formScript);
	}

	/**
	* Sets the group ID of this dossier file.
	*
	* @param groupId the group ID of this dossier file
	*/
	@Override
	public void setGroupId(long groupId) {
		_dossierFile.setGroupId(groupId);
	}

	/**
	* Sets whether this dossier file is is new.
	*
	* @param isNew the is new of this dossier file
	*/
	@Override
	public void setIsNew(boolean isNew) {
		_dossierFile.setIsNew(isNew);
	}

	/**
	* Sets the modified date of this dossier file.
	*
	* @param modifiedDate the modified date of this dossier file
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dossierFile.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dossierFile.setNew(n);
	}

	/**
	* Sets whether this dossier file is original.
	*
	* @param original the original of this dossier file
	*/
	@Override
	public void setOriginal(boolean original) {
		_dossierFile.setOriginal(original);
	}

	/**
	* Sets the primary key of this dossier file.
	*
	* @param primaryKey the primary key of this dossier file
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dossierFile.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierFile.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reference uid of this dossier file.
	*
	* @param referenceUid the reference uid of this dossier file
	*/
	@Override
	public void setReferenceUid(String referenceUid) {
		_dossierFile.setReferenceUid(referenceUid);
	}

	/**
	* Sets whether this dossier file is removed.
	*
	* @param removed the removed of this dossier file
	*/
	@Override
	public void setRemoved(boolean removed) {
		_dossierFile.setRemoved(removed);
	}

	/**
	* Sets the sign check of this dossier file.
	*
	* @param signCheck the sign check of this dossier file
	*/
	@Override
	public void setSignCheck(int signCheck) {
		_dossierFile.setSignCheck(signCheck);
	}

	/**
	* Sets the sign info of this dossier file.
	*
	* @param signInfo the sign info of this dossier file
	*/
	@Override
	public void setSignInfo(String signInfo) {
		_dossierFile.setSignInfo(signInfo);
	}

	/**
	* Sets the user ID of this dossier file.
	*
	* @param userId the user ID of this dossier file
	*/
	@Override
	public void setUserId(long userId) {
		_dossierFile.setUserId(userId);
	}

	/**
	* Sets the user name of this dossier file.
	*
	* @param userName the user name of this dossier file
	*/
	@Override
	public void setUserName(String userName) {
		_dossierFile.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dossier file.
	*
	* @param userUuid the user uuid of this dossier file
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dossierFile.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier file.
	*
	* @param uuid the uuid of this dossier file
	*/
	@Override
	public void setUuid(String uuid) {
		_dossierFile.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierFile> toCacheModel() {
		return _dossierFile.toCacheModel();
	}

	@Override
	public DossierFile toEscapedModel() {
		return new DossierFileWrapper(_dossierFile.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dossierFile.toString();
	}

	@Override
	public DossierFile toUnescapedModel() {
		return new DossierFileWrapper(_dossierFile.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dossierFile.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierFileWrapper)) {
			return false;
		}

		DossierFileWrapper dossierFileWrapper = (DossierFileWrapper)obj;

		if (Objects.equals(_dossierFile, dossierFileWrapper._dossierFile)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dossierFile.getStagedModelType();
	}

	@Override
	public DossierFile getWrappedModel() {
		return _dossierFile;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossierFile.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossierFile.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossierFile.resetOriginalValues();
	}

	private final DossierFile _dossierFile;
}