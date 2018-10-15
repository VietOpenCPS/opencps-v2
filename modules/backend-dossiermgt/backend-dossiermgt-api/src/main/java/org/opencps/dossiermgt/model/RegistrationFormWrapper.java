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
 * This class is a wrapper for {@link RegistrationForm}.
 * </p>
 *
 * @author huymq
 * @see RegistrationForm
 * @generated
 */
@ProviderType
public class RegistrationFormWrapper implements RegistrationForm,
	ModelWrapper<RegistrationForm> {
	public RegistrationFormWrapper(RegistrationForm registrationForm) {
		_registrationForm = registrationForm;
	}

	@Override
	public Class<?> getModelClass() {
		return RegistrationForm.class;
	}

	@Override
	public String getModelClassName() {
		return RegistrationForm.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("registrationFormId", getRegistrationFormId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("registrationId", getRegistrationId());
		attributes.put("referenceUid", getReferenceUid());
		attributes.put("formNo", getFormNo());
		attributes.put("formName", getFormName());
		attributes.put("formData", getFormData());
		attributes.put("formScript", getFormScript());
		attributes.put("formReport", getFormReport());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("isNew", isIsNew());
		attributes.put("removed", isRemoved());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long registrationFormId = (Long)attributes.get("registrationFormId");

		if (registrationFormId != null) {
			setRegistrationFormId(registrationFormId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		Long registrationId = (Long)attributes.get("registrationId");

		if (registrationId != null) {
			setRegistrationId(registrationId);
		}

		String referenceUid = (String)attributes.get("referenceUid");

		if (referenceUid != null) {
			setReferenceUid(referenceUid);
		}

		String formNo = (String)attributes.get("formNo");

		if (formNo != null) {
			setFormNo(formNo);
		}

		String formName = (String)attributes.get("formName");

		if (formName != null) {
			setFormName(formName);
		}

		String formData = (String)attributes.get("formData");

		if (formData != null) {
			setFormData(formData);
		}

		String formScript = (String)attributes.get("formScript");

		if (formScript != null) {
			setFormScript(formScript);
		}

		String formReport = (String)attributes.get("formReport");

		if (formReport != null) {
			setFormReport(formReport);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Boolean isNew = (Boolean)attributes.get("isNew");

		if (isNew != null) {
			setIsNew(isNew);
		}

		Boolean removed = (Boolean)attributes.get("removed");

		if (removed != null) {
			setRemoved(removed);
		}
	}

	@Override
	public Object clone() {
		return new RegistrationFormWrapper((RegistrationForm)_registrationForm.clone());
	}

	@Override
	public int compareTo(RegistrationForm registrationForm) {
		return _registrationForm.compareTo(registrationForm);
	}

	/**
	* Returns the company ID of this registration form.
	*
	* @return the company ID of this registration form
	*/
	@Override
	public long getCompanyId() {
		return _registrationForm.getCompanyId();
	}

	/**
	* Returns the create date of this registration form.
	*
	* @return the create date of this registration form
	*/
	@Override
	public Date getCreateDate() {
		return _registrationForm.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _registrationForm.getExpandoBridge();
	}

	/**
	* Returns the file entry ID of this registration form.
	*
	* @return the file entry ID of this registration form
	*/
	@Override
	public long getFileEntryId() {
		return _registrationForm.getFileEntryId();
	}

	/**
	* Returns the form data of this registration form.
	*
	* @return the form data of this registration form
	*/
	@Override
	public String getFormData() {
		return _registrationForm.getFormData();
	}

	/**
	* Returns the form name of this registration form.
	*
	* @return the form name of this registration form
	*/
	@Override
	public String getFormName() {
		return _registrationForm.getFormName();
	}

	/**
	* Returns the form no of this registration form.
	*
	* @return the form no of this registration form
	*/
	@Override
	public String getFormNo() {
		return _registrationForm.getFormNo();
	}

	/**
	* Returns the form report of this registration form.
	*
	* @return the form report of this registration form
	*/
	@Override
	public String getFormReport() {
		return _registrationForm.getFormReport();
	}

	/**
	* Returns the form script of this registration form.
	*
	* @return the form script of this registration form
	*/
	@Override
	public String getFormScript() {
		return _registrationForm.getFormScript();
	}

	/**
	* Returns the group ID of this registration form.
	*
	* @return the group ID of this registration form
	*/
	@Override
	public long getGroupId() {
		return _registrationForm.getGroupId();
	}

	/**
	* Returns the is new of this registration form.
	*
	* @return the is new of this registration form
	*/
	@Override
	public boolean getIsNew() {
		return _registrationForm.getIsNew();
	}

	/**
	* Returns the modified date of this registration form.
	*
	* @return the modified date of this registration form
	*/
	@Override
	public Date getModifiedDate() {
		return _registrationForm.getModifiedDate();
	}

	/**
	* Returns the primary key of this registration form.
	*
	* @return the primary key of this registration form
	*/
	@Override
	public long getPrimaryKey() {
		return _registrationForm.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _registrationForm.getPrimaryKeyObj();
	}

	/**
	* Returns the reference uid of this registration form.
	*
	* @return the reference uid of this registration form
	*/
	@Override
	public String getReferenceUid() {
		return _registrationForm.getReferenceUid();
	}

	/**
	* Returns the registration form ID of this registration form.
	*
	* @return the registration form ID of this registration form
	*/
	@Override
	public long getRegistrationFormId() {
		return _registrationForm.getRegistrationFormId();
	}

	/**
	* Returns the registration ID of this registration form.
	*
	* @return the registration ID of this registration form
	*/
	@Override
	public long getRegistrationId() {
		return _registrationForm.getRegistrationId();
	}

	/**
	* Returns the removed of this registration form.
	*
	* @return the removed of this registration form
	*/
	@Override
	public boolean getRemoved() {
		return _registrationForm.getRemoved();
	}

	/**
	* Returns the user ID of this registration form.
	*
	* @return the user ID of this registration form
	*/
	@Override
	public long getUserId() {
		return _registrationForm.getUserId();
	}

	/**
	* Returns the user uuid of this registration form.
	*
	* @return the user uuid of this registration form
	*/
	@Override
	public String getUserUuid() {
		return _registrationForm.getUserUuid();
	}

	/**
	* Returns the uuid of this registration form.
	*
	* @return the uuid of this registration form
	*/
	@Override
	public String getUuid() {
		return _registrationForm.getUuid();
	}

	@Override
	public int hashCode() {
		return _registrationForm.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _registrationForm.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _registrationForm.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this registration form is is new.
	*
	* @return <code>true</code> if this registration form is is new; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsNew() {
		return _registrationForm.isIsNew();
	}

	@Override
	public boolean isNew() {
		return _registrationForm.isNew();
	}

	/**
	* Returns <code>true</code> if this registration form is removed.
	*
	* @return <code>true</code> if this registration form is removed; <code>false</code> otherwise
	*/
	@Override
	public boolean isRemoved() {
		return _registrationForm.isRemoved();
	}

	@Override
	public void persist() {
		_registrationForm.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_registrationForm.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this registration form.
	*
	* @param companyId the company ID of this registration form
	*/
	@Override
	public void setCompanyId(long companyId) {
		_registrationForm.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this registration form.
	*
	* @param createDate the create date of this registration form
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_registrationForm.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_registrationForm.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_registrationForm.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_registrationForm.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file entry ID of this registration form.
	*
	* @param fileEntryId the file entry ID of this registration form
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_registrationForm.setFileEntryId(fileEntryId);
	}

	/**
	* Sets the form data of this registration form.
	*
	* @param formData the form data of this registration form
	*/
	@Override
	public void setFormData(String formData) {
		_registrationForm.setFormData(formData);
	}

	/**
	* Sets the form name of this registration form.
	*
	* @param formName the form name of this registration form
	*/
	@Override
	public void setFormName(String formName) {
		_registrationForm.setFormName(formName);
	}

	/**
	* Sets the form no of this registration form.
	*
	* @param formNo the form no of this registration form
	*/
	@Override
	public void setFormNo(String formNo) {
		_registrationForm.setFormNo(formNo);
	}

	/**
	* Sets the form report of this registration form.
	*
	* @param formReport the form report of this registration form
	*/
	@Override
	public void setFormReport(String formReport) {
		_registrationForm.setFormReport(formReport);
	}

	/**
	* Sets the form script of this registration form.
	*
	* @param formScript the form script of this registration form
	*/
	@Override
	public void setFormScript(String formScript) {
		_registrationForm.setFormScript(formScript);
	}

	/**
	* Sets the group ID of this registration form.
	*
	* @param groupId the group ID of this registration form
	*/
	@Override
	public void setGroupId(long groupId) {
		_registrationForm.setGroupId(groupId);
	}

	/**
	* Sets whether this registration form is is new.
	*
	* @param isNew the is new of this registration form
	*/
	@Override
	public void setIsNew(boolean isNew) {
		_registrationForm.setIsNew(isNew);
	}

	/**
	* Sets the modified date of this registration form.
	*
	* @param modifiedDate the modified date of this registration form
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_registrationForm.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_registrationForm.setNew(n);
	}

	/**
	* Sets the primary key of this registration form.
	*
	* @param primaryKey the primary key of this registration form
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_registrationForm.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_registrationForm.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reference uid of this registration form.
	*
	* @param referenceUid the reference uid of this registration form
	*/
	@Override
	public void setReferenceUid(String referenceUid) {
		_registrationForm.setReferenceUid(referenceUid);
	}

	/**
	* Sets the registration form ID of this registration form.
	*
	* @param registrationFormId the registration form ID of this registration form
	*/
	@Override
	public void setRegistrationFormId(long registrationFormId) {
		_registrationForm.setRegistrationFormId(registrationFormId);
	}

	/**
	* Sets the registration ID of this registration form.
	*
	* @param registrationId the registration ID of this registration form
	*/
	@Override
	public void setRegistrationId(long registrationId) {
		_registrationForm.setRegistrationId(registrationId);
	}

	/**
	* Sets whether this registration form is removed.
	*
	* @param removed the removed of this registration form
	*/
	@Override
	public void setRemoved(boolean removed) {
		_registrationForm.setRemoved(removed);
	}

	/**
	* Sets the user ID of this registration form.
	*
	* @param userId the user ID of this registration form
	*/
	@Override
	public void setUserId(long userId) {
		_registrationForm.setUserId(userId);
	}

	/**
	* Sets the user uuid of this registration form.
	*
	* @param userUuid the user uuid of this registration form
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_registrationForm.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this registration form.
	*
	* @param uuid the uuid of this registration form
	*/
	@Override
	public void setUuid(String uuid) {
		_registrationForm.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<RegistrationForm> toCacheModel() {
		return _registrationForm.toCacheModel();
	}

	@Override
	public RegistrationForm toEscapedModel() {
		return new RegistrationFormWrapper(_registrationForm.toEscapedModel());
	}

	@Override
	public String toString() {
		return _registrationForm.toString();
	}

	@Override
	public RegistrationForm toUnescapedModel() {
		return new RegistrationFormWrapper(_registrationForm.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _registrationForm.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RegistrationFormWrapper)) {
			return false;
		}

		RegistrationFormWrapper registrationFormWrapper = (RegistrationFormWrapper)obj;

		if (Objects.equals(_registrationForm,
					registrationFormWrapper._registrationForm)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _registrationForm.getStagedModelType();
	}

	@Override
	public RegistrationForm getWrappedModel() {
		return _registrationForm;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _registrationForm.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _registrationForm.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_registrationForm.resetOriginalValues();
	}

	private final RegistrationForm _registrationForm;
}