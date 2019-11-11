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
 * This class is a wrapper for {@link DossierPart}.
 * </p>
 *
 * @author huymq
 * @see DossierPart
 * @generated
 */
@ProviderType
public class DossierPartWrapper implements DossierPart,
	ModelWrapper<DossierPart> {
	public DossierPartWrapper(DossierPart dossierPart) {
		_dossierPart = dossierPart;
	}

	@Override
	public Class<?> getModelClass() {
		return DossierPart.class;
	}

	@Override
	public String getModelClassName() {
		return DossierPart.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dossierPartId", getDossierPartId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("templateNo", getTemplateNo());
		attributes.put("partNo", getPartNo());
		attributes.put("partName", getPartName());
		attributes.put("partTip", getPartTip());
		attributes.put("partType", getPartType());
		attributes.put("multiple", isMultiple());
		attributes.put("formScript", getFormScript());
		attributes.put("formReport", getFormReport());
		attributes.put("sampleData", getSampleData());
		attributes.put("required", isRequired());
		attributes.put("fileTemplateNo", getFileTemplateNo());
		attributes.put("eSign", isESign());
		attributes.put("deliverableType", getDeliverableType());
		attributes.put("deliverableAction", getDeliverableAction());
		attributes.put("eForm", isEForm());
		attributes.put("fileMark", getFileMark());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dossierPartId = (Long)attributes.get("dossierPartId");

		if (dossierPartId != null) {
			setDossierPartId(dossierPartId);
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

		String templateNo = (String)attributes.get("templateNo");

		if (templateNo != null) {
			setTemplateNo(templateNo);
		}

		String partNo = (String)attributes.get("partNo");

		if (partNo != null) {
			setPartNo(partNo);
		}

		String partName = (String)attributes.get("partName");

		if (partName != null) {
			setPartName(partName);
		}

		String partTip = (String)attributes.get("partTip");

		if (partTip != null) {
			setPartTip(partTip);
		}

		Integer partType = (Integer)attributes.get("partType");

		if (partType != null) {
			setPartType(partType);
		}

		Boolean multiple = (Boolean)attributes.get("multiple");

		if (multiple != null) {
			setMultiple(multiple);
		}

		String formScript = (String)attributes.get("formScript");

		if (formScript != null) {
			setFormScript(formScript);
		}

		String formReport = (String)attributes.get("formReport");

		if (formReport != null) {
			setFormReport(formReport);
		}

		String sampleData = (String)attributes.get("sampleData");

		if (sampleData != null) {
			setSampleData(sampleData);
		}

		Boolean required = (Boolean)attributes.get("required");

		if (required != null) {
			setRequired(required);
		}

		String fileTemplateNo = (String)attributes.get("fileTemplateNo");

		if (fileTemplateNo != null) {
			setFileTemplateNo(fileTemplateNo);
		}

		Boolean eSign = (Boolean)attributes.get("eSign");

		if (eSign != null) {
			setESign(eSign);
		}

		String deliverableType = (String)attributes.get("deliverableType");

		if (deliverableType != null) {
			setDeliverableType(deliverableType);
		}

		Integer deliverableAction = (Integer)attributes.get("deliverableAction");

		if (deliverableAction != null) {
			setDeliverableAction(deliverableAction);
		}

		Boolean eForm = (Boolean)attributes.get("eForm");

		if (eForm != null) {
			setEForm(eForm);
		}

		Integer fileMark = (Integer)attributes.get("fileMark");

		if (fileMark != null) {
			setFileMark(fileMark);
		}
	}

	@Override
	public Object clone() {
		return new DossierPartWrapper((DossierPart)_dossierPart.clone());
	}

	@Override
	public int compareTo(DossierPart dossierPart) {
		return _dossierPart.compareTo(dossierPart);
	}

	/**
	* Returns the company ID of this dossier part.
	*
	* @return the company ID of this dossier part
	*/
	@Override
	public long getCompanyId() {
		return _dossierPart.getCompanyId();
	}

	/**
	* Returns the create date of this dossier part.
	*
	* @return the create date of this dossier part
	*/
	@Override
	public Date getCreateDate() {
		return _dossierPart.getCreateDate();
	}

	/**
	* Returns the deliverable action of this dossier part.
	*
	* @return the deliverable action of this dossier part
	*/
	@Override
	public int getDeliverableAction() {
		return _dossierPart.getDeliverableAction();
	}

	/**
	* Returns the deliverable type of this dossier part.
	*
	* @return the deliverable type of this dossier part
	*/
	@Override
	public String getDeliverableType() {
		return _dossierPart.getDeliverableType();
	}

	/**
	* Returns the dossier part ID of this dossier part.
	*
	* @return the dossier part ID of this dossier part
	*/
	@Override
	public long getDossierPartId() {
		return _dossierPart.getDossierPartId();
	}

	/**
	* Returns the e form of this dossier part.
	*
	* @return the e form of this dossier part
	*/
	@Override
	public boolean getEForm() {
		return _dossierPart.getEForm();
	}

	/**
	* Returns the e sign of this dossier part.
	*
	* @return the e sign of this dossier part
	*/
	@Override
	public boolean getESign() {
		return _dossierPart.getESign();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierPart.getExpandoBridge();
	}

	/**
	* Returns the file mark of this dossier part.
	*
	* @return the file mark of this dossier part
	*/
	@Override
	public int getFileMark() {
		return _dossierPart.getFileMark();
	}

	/**
	* Returns the file template no of this dossier part.
	*
	* @return the file template no of this dossier part
	*/
	@Override
	public String getFileTemplateNo() {
		return _dossierPart.getFileTemplateNo();
	}

	/**
	* Returns the form report of this dossier part.
	*
	* @return the form report of this dossier part
	*/
	@Override
	public String getFormReport() {
		return _dossierPart.getFormReport();
	}

	/**
	* Returns the form script of this dossier part.
	*
	* @return the form script of this dossier part
	*/
	@Override
	public String getFormScript() {
		return _dossierPart.getFormScript();
	}

	/**
	* Returns the group ID of this dossier part.
	*
	* @return the group ID of this dossier part
	*/
	@Override
	public long getGroupId() {
		return _dossierPart.getGroupId();
	}

	/**
	* Returns the modified date of this dossier part.
	*
	* @return the modified date of this dossier part
	*/
	@Override
	public Date getModifiedDate() {
		return _dossierPart.getModifiedDate();
	}

	/**
	* Returns the multiple of this dossier part.
	*
	* @return the multiple of this dossier part
	*/
	@Override
	public boolean getMultiple() {
		return _dossierPart.getMultiple();
	}

	/**
	* Returns the part name of this dossier part.
	*
	* @return the part name of this dossier part
	*/
	@Override
	public String getPartName() {
		return _dossierPart.getPartName();
	}

	/**
	* Returns the part no of this dossier part.
	*
	* @return the part no of this dossier part
	*/
	@Override
	public String getPartNo() {
		return _dossierPart.getPartNo();
	}

	/**
	* Returns the part tip of this dossier part.
	*
	* @return the part tip of this dossier part
	*/
	@Override
	public String getPartTip() {
		return _dossierPart.getPartTip();
	}

	/**
	* Returns the part type of this dossier part.
	*
	* @return the part type of this dossier part
	*/
	@Override
	public int getPartType() {
		return _dossierPart.getPartType();
	}

	/**
	* Returns the primary key of this dossier part.
	*
	* @return the primary key of this dossier part
	*/
	@Override
	public long getPrimaryKey() {
		return _dossierPart.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierPart.getPrimaryKeyObj();
	}

	/**
	* Returns the required of this dossier part.
	*
	* @return the required of this dossier part
	*/
	@Override
	public boolean getRequired() {
		return _dossierPart.getRequired();
	}

	/**
	* Returns the sample data of this dossier part.
	*
	* @return the sample data of this dossier part
	*/
	@Override
	public String getSampleData() {
		return _dossierPart.getSampleData();
	}

	/**
	* Returns the template no of this dossier part.
	*
	* @return the template no of this dossier part
	*/
	@Override
	public String getTemplateNo() {
		return _dossierPart.getTemplateNo();
	}

	/**
	* Returns the user ID of this dossier part.
	*
	* @return the user ID of this dossier part
	*/
	@Override
	public long getUserId() {
		return _dossierPart.getUserId();
	}

	/**
	* Returns the user name of this dossier part.
	*
	* @return the user name of this dossier part
	*/
	@Override
	public String getUserName() {
		return _dossierPart.getUserName();
	}

	/**
	* Returns the user uuid of this dossier part.
	*
	* @return the user uuid of this dossier part
	*/
	@Override
	public String getUserUuid() {
		return _dossierPart.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier part.
	*
	* @return the uuid of this dossier part
	*/
	@Override
	public String getUuid() {
		return _dossierPart.getUuid();
	}

	@Override
	public int hashCode() {
		return _dossierPart.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dossierPart.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this dossier part is e form.
	*
	* @return <code>true</code> if this dossier part is e form; <code>false</code> otherwise
	*/
	@Override
	public boolean isEForm() {
		return _dossierPart.isEForm();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossierPart.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this dossier part is e sign.
	*
	* @return <code>true</code> if this dossier part is e sign; <code>false</code> otherwise
	*/
	@Override
	public boolean isESign() {
		return _dossierPart.isESign();
	}

	/**
	* Returns <code>true</code> if this dossier part is multiple.
	*
	* @return <code>true</code> if this dossier part is multiple; <code>false</code> otherwise
	*/
	@Override
	public boolean isMultiple() {
		return _dossierPart.isMultiple();
	}

	@Override
	public boolean isNew() {
		return _dossierPart.isNew();
	}

	/**
	* Returns <code>true</code> if this dossier part is required.
	*
	* @return <code>true</code> if this dossier part is required; <code>false</code> otherwise
	*/
	@Override
	public boolean isRequired() {
		return _dossierPart.isRequired();
	}

	@Override
	public void persist() {
		_dossierPart.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossierPart.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dossier part.
	*
	* @param companyId the company ID of this dossier part
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dossierPart.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dossier part.
	*
	* @param createDate the create date of this dossier part
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dossierPart.setCreateDate(createDate);
	}

	/**
	* Sets the deliverable action of this dossier part.
	*
	* @param deliverableAction the deliverable action of this dossier part
	*/
	@Override
	public void setDeliverableAction(int deliverableAction) {
		_dossierPart.setDeliverableAction(deliverableAction);
	}

	/**
	* Sets the deliverable type of this dossier part.
	*
	* @param deliverableType the deliverable type of this dossier part
	*/
	@Override
	public void setDeliverableType(String deliverableType) {
		_dossierPart.setDeliverableType(deliverableType);
	}

	/**
	* Sets the dossier part ID of this dossier part.
	*
	* @param dossierPartId the dossier part ID of this dossier part
	*/
	@Override
	public void setDossierPartId(long dossierPartId) {
		_dossierPart.setDossierPartId(dossierPartId);
	}

	/**
	* Sets whether this dossier part is e form.
	*
	* @param eForm the e form of this dossier part
	*/
	@Override
	public void setEForm(boolean eForm) {
		_dossierPart.setEForm(eForm);
	}

	/**
	* Sets whether this dossier part is e sign.
	*
	* @param eSign the e sign of this dossier part
	*/
	@Override
	public void setESign(boolean eSign) {
		_dossierPart.setESign(eSign);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierPart.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierPart.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierPart.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file mark of this dossier part.
	*
	* @param fileMark the file mark of this dossier part
	*/
	@Override
	public void setFileMark(int fileMark) {
		_dossierPart.setFileMark(fileMark);
	}

	/**
	* Sets the file template no of this dossier part.
	*
	* @param fileTemplateNo the file template no of this dossier part
	*/
	@Override
	public void setFileTemplateNo(String fileTemplateNo) {
		_dossierPart.setFileTemplateNo(fileTemplateNo);
	}

	/**
	* Sets the form report of this dossier part.
	*
	* @param formReport the form report of this dossier part
	*/
	@Override
	public void setFormReport(String formReport) {
		_dossierPart.setFormReport(formReport);
	}

	/**
	* Sets the form script of this dossier part.
	*
	* @param formScript the form script of this dossier part
	*/
	@Override
	public void setFormScript(String formScript) {
		_dossierPart.setFormScript(formScript);
	}

	/**
	* Sets the group ID of this dossier part.
	*
	* @param groupId the group ID of this dossier part
	*/
	@Override
	public void setGroupId(long groupId) {
		_dossierPart.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this dossier part.
	*
	* @param modifiedDate the modified date of this dossier part
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dossierPart.setModifiedDate(modifiedDate);
	}

	/**
	* Sets whether this dossier part is multiple.
	*
	* @param multiple the multiple of this dossier part
	*/
	@Override
	public void setMultiple(boolean multiple) {
		_dossierPart.setMultiple(multiple);
	}

	@Override
	public void setNew(boolean n) {
		_dossierPart.setNew(n);
	}

	/**
	* Sets the part name of this dossier part.
	*
	* @param partName the part name of this dossier part
	*/
	@Override
	public void setPartName(String partName) {
		_dossierPart.setPartName(partName);
	}

	/**
	* Sets the part no of this dossier part.
	*
	* @param partNo the part no of this dossier part
	*/
	@Override
	public void setPartNo(String partNo) {
		_dossierPart.setPartNo(partNo);
	}

	/**
	* Sets the part tip of this dossier part.
	*
	* @param partTip the part tip of this dossier part
	*/
	@Override
	public void setPartTip(String partTip) {
		_dossierPart.setPartTip(partTip);
	}

	/**
	* Sets the part type of this dossier part.
	*
	* @param partType the part type of this dossier part
	*/
	@Override
	public void setPartType(int partType) {
		_dossierPart.setPartType(partType);
	}

	/**
	* Sets the primary key of this dossier part.
	*
	* @param primaryKey the primary key of this dossier part
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dossierPart.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierPart.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this dossier part is required.
	*
	* @param required the required of this dossier part
	*/
	@Override
	public void setRequired(boolean required) {
		_dossierPart.setRequired(required);
	}

	/**
	* Sets the sample data of this dossier part.
	*
	* @param sampleData the sample data of this dossier part
	*/
	@Override
	public void setSampleData(String sampleData) {
		_dossierPart.setSampleData(sampleData);
	}

	/**
	* Sets the template no of this dossier part.
	*
	* @param templateNo the template no of this dossier part
	*/
	@Override
	public void setTemplateNo(String templateNo) {
		_dossierPart.setTemplateNo(templateNo);
	}

	/**
	* Sets the user ID of this dossier part.
	*
	* @param userId the user ID of this dossier part
	*/
	@Override
	public void setUserId(long userId) {
		_dossierPart.setUserId(userId);
	}

	/**
	* Sets the user name of this dossier part.
	*
	* @param userName the user name of this dossier part
	*/
	@Override
	public void setUserName(String userName) {
		_dossierPart.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dossier part.
	*
	* @param userUuid the user uuid of this dossier part
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dossierPart.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier part.
	*
	* @param uuid the uuid of this dossier part
	*/
	@Override
	public void setUuid(String uuid) {
		_dossierPart.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierPart> toCacheModel() {
		return _dossierPart.toCacheModel();
	}

	@Override
	public DossierPart toEscapedModel() {
		return new DossierPartWrapper(_dossierPart.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dossierPart.toString();
	}

	@Override
	public DossierPart toUnescapedModel() {
		return new DossierPartWrapper(_dossierPart.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dossierPart.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierPartWrapper)) {
			return false;
		}

		DossierPartWrapper dossierPartWrapper = (DossierPartWrapper)obj;

		if (Objects.equals(_dossierPart, dossierPartWrapper._dossierPart)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dossierPart.getStagedModelType();
	}

	@Override
	public DossierPart getWrappedModel() {
		return _dossierPart;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossierPart.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossierPart.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossierPart.resetOriginalValues();
	}

	private final DossierPart _dossierPart;
}