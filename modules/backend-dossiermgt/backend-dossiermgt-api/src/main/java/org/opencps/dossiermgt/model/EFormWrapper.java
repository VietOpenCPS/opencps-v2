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
 * This class is a wrapper for {@link EForm}.
 * </p>
 *
 * @author huymq
 * @see EForm
 * @generated
 */
@ProviderType
public class EFormWrapper implements EForm, ModelWrapper<EForm> {
	public EFormWrapper(EForm eForm) {
		_eForm = eForm;
	}

	@Override
	public Class<?> getModelClass() {
		return EForm.class;
	}

	@Override
	public String getModelClassName() {
		return EForm.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("eFormId", getEFormId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("eFormNo", getEFormNo());
		attributes.put("serviceCode", getServiceCode());
		attributes.put("fileTemplateNo", getFileTemplateNo());
		attributes.put("eFormName", getEFormName());
		attributes.put("formScriptFileId", getFormScriptFileId());
		attributes.put("formReportFileId", getFormReportFileId());
		attributes.put("eFormData", getEFormData());
		attributes.put("email", getEmail());
		attributes.put("secret", getSecret());
		attributes.put("govAgencyCode", getGovAgencyCode());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long eFormId = (Long)attributes.get("eFormId");

		if (eFormId != null) {
			setEFormId(eFormId);
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

		String eFormNo = (String)attributes.get("eFormNo");

		if (eFormNo != null) {
			setEFormNo(eFormNo);
		}

		String serviceCode = (String)attributes.get("serviceCode");

		if (serviceCode != null) {
			setServiceCode(serviceCode);
		}

		String fileTemplateNo = (String)attributes.get("fileTemplateNo");

		if (fileTemplateNo != null) {
			setFileTemplateNo(fileTemplateNo);
		}

		String eFormName = (String)attributes.get("eFormName");

		if (eFormName != null) {
			setEFormName(eFormName);
		}

		Long formScriptFileId = (Long)attributes.get("formScriptFileId");

		if (formScriptFileId != null) {
			setFormScriptFileId(formScriptFileId);
		}

		Long formReportFileId = (Long)attributes.get("formReportFileId");

		if (formReportFileId != null) {
			setFormReportFileId(formReportFileId);
		}

		String eFormData = (String)attributes.get("eFormData");

		if (eFormData != null) {
			setEFormData(eFormData);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String secret = (String)attributes.get("secret");

		if (secret != null) {
			setSecret(secret);
		}

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}
	}

	@Override
	public Object clone() {
		return new EFormWrapper((EForm)_eForm.clone());
	}

	@Override
	public int compareTo(EForm eForm) {
		return _eForm.compareTo(eForm);
	}

	/**
	* Returns the company ID of this e form.
	*
	* @return the company ID of this e form
	*/
	@Override
	public long getCompanyId() {
		return _eForm.getCompanyId();
	}

	/**
	* Returns the create date of this e form.
	*
	* @return the create date of this e form
	*/
	@Override
	public Date getCreateDate() {
		return _eForm.getCreateDate();
	}

	/**
	* Returns the e form data of this e form.
	*
	* @return the e form data of this e form
	*/
	@Override
	public String getEFormData() {
		return _eForm.getEFormData();
	}

	/**
	* Returns the e form ID of this e form.
	*
	* @return the e form ID of this e form
	*/
	@Override
	public long getEFormId() {
		return _eForm.getEFormId();
	}

	/**
	* Returns the e form name of this e form.
	*
	* @return the e form name of this e form
	*/
	@Override
	public String getEFormName() {
		return _eForm.getEFormName();
	}

	/**
	* Returns the e form no of this e form.
	*
	* @return the e form no of this e form
	*/
	@Override
	public String getEFormNo() {
		return _eForm.getEFormNo();
	}

	/**
	* Returns the email of this e form.
	*
	* @return the email of this e form
	*/
	@Override
	public String getEmail() {
		return _eForm.getEmail();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _eForm.getExpandoBridge();
	}

	/**
	* Returns the file template no of this e form.
	*
	* @return the file template no of this e form
	*/
	@Override
	public String getFileTemplateNo() {
		return _eForm.getFileTemplateNo();
	}

	/**
	* Returns the form report file ID of this e form.
	*
	* @return the form report file ID of this e form
	*/
	@Override
	public long getFormReportFileId() {
		return _eForm.getFormReportFileId();
	}

	/**
	* Returns the form script file ID of this e form.
	*
	* @return the form script file ID of this e form
	*/
	@Override
	public long getFormScriptFileId() {
		return _eForm.getFormScriptFileId();
	}

	/**
	* Returns the gov agency code of this e form.
	*
	* @return the gov agency code of this e form
	*/
	@Override
	public String getGovAgencyCode() {
		return _eForm.getGovAgencyCode();
	}

	/**
	* Returns the group ID of this e form.
	*
	* @return the group ID of this e form
	*/
	@Override
	public long getGroupId() {
		return _eForm.getGroupId();
	}

	/**
	* Returns the modified date of this e form.
	*
	* @return the modified date of this e form
	*/
	@Override
	public Date getModifiedDate() {
		return _eForm.getModifiedDate();
	}

	/**
	* Returns the primary key of this e form.
	*
	* @return the primary key of this e form
	*/
	@Override
	public long getPrimaryKey() {
		return _eForm.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _eForm.getPrimaryKeyObj();
	}

	/**
	* Returns the secret of this e form.
	*
	* @return the secret of this e form
	*/
	@Override
	public String getSecret() {
		return _eForm.getSecret();
	}

	/**
	* Returns the service code of this e form.
	*
	* @return the service code of this e form
	*/
	@Override
	public String getServiceCode() {
		return _eForm.getServiceCode();
	}

	/**
	* Returns the user ID of this e form.
	*
	* @return the user ID of this e form
	*/
	@Override
	public long getUserId() {
		return _eForm.getUserId();
	}

	/**
	* Returns the user name of this e form.
	*
	* @return the user name of this e form
	*/
	@Override
	public String getUserName() {
		return _eForm.getUserName();
	}

	/**
	* Returns the user uuid of this e form.
	*
	* @return the user uuid of this e form
	*/
	@Override
	public String getUserUuid() {
		return _eForm.getUserUuid();
	}

	/**
	* Returns the uuid of this e form.
	*
	* @return the uuid of this e form
	*/
	@Override
	public String getUuid() {
		return _eForm.getUuid();
	}

	@Override
	public int hashCode() {
		return _eForm.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _eForm.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _eForm.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _eForm.isNew();
	}

	@Override
	public void persist() {
		_eForm.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_eForm.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this e form.
	*
	* @param companyId the company ID of this e form
	*/
	@Override
	public void setCompanyId(long companyId) {
		_eForm.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this e form.
	*
	* @param createDate the create date of this e form
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_eForm.setCreateDate(createDate);
	}

	/**
	* Sets the e form data of this e form.
	*
	* @param eFormData the e form data of this e form
	*/
	@Override
	public void setEFormData(String eFormData) {
		_eForm.setEFormData(eFormData);
	}

	/**
	* Sets the e form ID of this e form.
	*
	* @param eFormId the e form ID of this e form
	*/
	@Override
	public void setEFormId(long eFormId) {
		_eForm.setEFormId(eFormId);
	}

	/**
	* Sets the e form name of this e form.
	*
	* @param eFormName the e form name of this e form
	*/
	@Override
	public void setEFormName(String eFormName) {
		_eForm.setEFormName(eFormName);
	}

	/**
	* Sets the e form no of this e form.
	*
	* @param eFormNo the e form no of this e form
	*/
	@Override
	public void setEFormNo(String eFormNo) {
		_eForm.setEFormNo(eFormNo);
	}

	/**
	* Sets the email of this e form.
	*
	* @param email the email of this e form
	*/
	@Override
	public void setEmail(String email) {
		_eForm.setEmail(email);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_eForm.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_eForm.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_eForm.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file template no of this e form.
	*
	* @param fileTemplateNo the file template no of this e form
	*/
	@Override
	public void setFileTemplateNo(String fileTemplateNo) {
		_eForm.setFileTemplateNo(fileTemplateNo);
	}

	/**
	* Sets the form report file ID of this e form.
	*
	* @param formReportFileId the form report file ID of this e form
	*/
	@Override
	public void setFormReportFileId(long formReportFileId) {
		_eForm.setFormReportFileId(formReportFileId);
	}

	/**
	* Sets the form script file ID of this e form.
	*
	* @param formScriptFileId the form script file ID of this e form
	*/
	@Override
	public void setFormScriptFileId(long formScriptFileId) {
		_eForm.setFormScriptFileId(formScriptFileId);
	}

	/**
	* Sets the gov agency code of this e form.
	*
	* @param govAgencyCode the gov agency code of this e form
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_eForm.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the group ID of this e form.
	*
	* @param groupId the group ID of this e form
	*/
	@Override
	public void setGroupId(long groupId) {
		_eForm.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this e form.
	*
	* @param modifiedDate the modified date of this e form
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_eForm.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_eForm.setNew(n);
	}

	/**
	* Sets the primary key of this e form.
	*
	* @param primaryKey the primary key of this e form
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_eForm.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_eForm.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the secret of this e form.
	*
	* @param secret the secret of this e form
	*/
	@Override
	public void setSecret(String secret) {
		_eForm.setSecret(secret);
	}

	/**
	* Sets the service code of this e form.
	*
	* @param serviceCode the service code of this e form
	*/
	@Override
	public void setServiceCode(String serviceCode) {
		_eForm.setServiceCode(serviceCode);
	}

	/**
	* Sets the user ID of this e form.
	*
	* @param userId the user ID of this e form
	*/
	@Override
	public void setUserId(long userId) {
		_eForm.setUserId(userId);
	}

	/**
	* Sets the user name of this e form.
	*
	* @param userName the user name of this e form
	*/
	@Override
	public void setUserName(String userName) {
		_eForm.setUserName(userName);
	}

	/**
	* Sets the user uuid of this e form.
	*
	* @param userUuid the user uuid of this e form
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_eForm.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this e form.
	*
	* @param uuid the uuid of this e form
	*/
	@Override
	public void setUuid(String uuid) {
		_eForm.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<EForm> toCacheModel() {
		return _eForm.toCacheModel();
	}

	@Override
	public EForm toEscapedModel() {
		return new EFormWrapper(_eForm.toEscapedModel());
	}

	@Override
	public String toString() {
		return _eForm.toString();
	}

	@Override
	public EForm toUnescapedModel() {
		return new EFormWrapper(_eForm.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _eForm.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EFormWrapper)) {
			return false;
		}

		EFormWrapper eFormWrapper = (EFormWrapper)obj;

		if (Objects.equals(_eForm, eFormWrapper._eForm)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _eForm.getStagedModelType();
	}

	@Override
	public EForm getWrappedModel() {
		return _eForm;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _eForm.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _eForm.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_eForm.resetOriginalValues();
	}

	private final EForm _eForm;
}