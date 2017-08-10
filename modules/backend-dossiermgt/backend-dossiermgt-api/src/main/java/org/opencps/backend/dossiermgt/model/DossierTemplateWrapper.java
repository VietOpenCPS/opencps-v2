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

package org.opencps.backend.dossiermgt.model;

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
 * This class is a wrapper for {@link DossierTemplate}.
 * </p>
 *
 * @author huymq
 * @see DossierTemplate
 * @generated
 */
@ProviderType
public class DossierTemplateWrapper implements DossierTemplate,
	ModelWrapper<DossierTemplate> {
	public DossierTemplateWrapper(DossierTemplate dossierTemplate) {
		_dossierTemplate = dossierTemplate;
	}

	@Override
	public Class<?> getModelClass() {
		return DossierTemplate.class;
	}

	@Override
	public String getModelClassName() {
		return DossierTemplate.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dossierTemplateId", getDossierTemplateId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("templateName", getTemplateName());
		attributes.put("description", getDescription());
		attributes.put("templateNo", getTemplateNo());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dossierTemplateId = (Long)attributes.get("dossierTemplateId");

		if (dossierTemplateId != null) {
			setDossierTemplateId(dossierTemplateId);
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

		String templateName = (String)attributes.get("templateName");

		if (templateName != null) {
			setTemplateName(templateName);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String templateNo = (String)attributes.get("templateNo");

		if (templateNo != null) {
			setTemplateNo(templateNo);
		}
	}

	@Override
	public DossierTemplate toEscapedModel() {
		return new DossierTemplateWrapper(_dossierTemplate.toEscapedModel());
	}

	@Override
	public DossierTemplate toUnescapedModel() {
		return new DossierTemplateWrapper(_dossierTemplate.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _dossierTemplate.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossierTemplate.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dossierTemplate.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierTemplate.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierTemplate> toCacheModel() {
		return _dossierTemplate.toCacheModel();
	}

	@Override
	public int compareTo(DossierTemplate dossierTemplate) {
		return _dossierTemplate.compareTo(dossierTemplate);
	}

	@Override
	public int hashCode() {
		return _dossierTemplate.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierTemplate.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DossierTemplateWrapper((DossierTemplate)_dossierTemplate.clone());
	}

	/**
	* Returns the description of this dossier template.
	*
	* @return the description of this dossier template
	*/
	@Override
	public java.lang.String getDescription() {
		return _dossierTemplate.getDescription();
	}

	/**
	* Returns the template name of this dossier template.
	*
	* @return the template name of this dossier template
	*/
	@Override
	public java.lang.String getTemplateName() {
		return _dossierTemplate.getTemplateName();
	}

	/**
	* Returns the template no of this dossier template.
	*
	* @return the template no of this dossier template
	*/
	@Override
	public java.lang.String getTemplateNo() {
		return _dossierTemplate.getTemplateNo();
	}

	/**
	* Returns the user name of this dossier template.
	*
	* @return the user name of this dossier template
	*/
	@Override
	public java.lang.String getUserName() {
		return _dossierTemplate.getUserName();
	}

	/**
	* Returns the user uuid of this dossier template.
	*
	* @return the user uuid of this dossier template
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _dossierTemplate.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier template.
	*
	* @return the uuid of this dossier template
	*/
	@Override
	public java.lang.String getUuid() {
		return _dossierTemplate.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _dossierTemplate.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dossierTemplate.toXmlString();
	}

	/**
	* Returns the create date of this dossier template.
	*
	* @return the create date of this dossier template
	*/
	@Override
	public Date getCreateDate() {
		return _dossierTemplate.getCreateDate();
	}

	/**
	* Returns the modified date of this dossier template.
	*
	* @return the modified date of this dossier template
	*/
	@Override
	public Date getModifiedDate() {
		return _dossierTemplate.getModifiedDate();
	}

	/**
	* Returns the company ID of this dossier template.
	*
	* @return the company ID of this dossier template
	*/
	@Override
	public long getCompanyId() {
		return _dossierTemplate.getCompanyId();
	}

	/**
	* Returns the dossier template ID of this dossier template.
	*
	* @return the dossier template ID of this dossier template
	*/
	@Override
	public long getDossierTemplateId() {
		return _dossierTemplate.getDossierTemplateId();
	}

	/**
	* Returns the group ID of this dossier template.
	*
	* @return the group ID of this dossier template
	*/
	@Override
	public long getGroupId() {
		return _dossierTemplate.getGroupId();
	}

	/**
	* Returns the primary key of this dossier template.
	*
	* @return the primary key of this dossier template
	*/
	@Override
	public long getPrimaryKey() {
		return _dossierTemplate.getPrimaryKey();
	}

	/**
	* Returns the user ID of this dossier template.
	*
	* @return the user ID of this dossier template
	*/
	@Override
	public long getUserId() {
		return _dossierTemplate.getUserId();
	}

	@Override
	public void persist() {
		_dossierTemplate.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossierTemplate.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dossier template.
	*
	* @param companyId the company ID of this dossier template
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dossierTemplate.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dossier template.
	*
	* @param createDate the create date of this dossier template
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dossierTemplate.setCreateDate(createDate);
	}

	/**
	* Sets the description of this dossier template.
	*
	* @param description the description of this dossier template
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_dossierTemplate.setDescription(description);
	}

	/**
	* Sets the dossier template ID of this dossier template.
	*
	* @param dossierTemplateId the dossier template ID of this dossier template
	*/
	@Override
	public void setDossierTemplateId(long dossierTemplateId) {
		_dossierTemplate.setDossierTemplateId(dossierTemplateId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierTemplate.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierTemplate.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierTemplate.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dossier template.
	*
	* @param groupId the group ID of this dossier template
	*/
	@Override
	public void setGroupId(long groupId) {
		_dossierTemplate.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this dossier template.
	*
	* @param modifiedDate the modified date of this dossier template
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dossierTemplate.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dossierTemplate.setNew(n);
	}

	/**
	* Sets the primary key of this dossier template.
	*
	* @param primaryKey the primary key of this dossier template
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dossierTemplate.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierTemplate.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the template name of this dossier template.
	*
	* @param templateName the template name of this dossier template
	*/
	@Override
	public void setTemplateName(java.lang.String templateName) {
		_dossierTemplate.setTemplateName(templateName);
	}

	/**
	* Sets the template no of this dossier template.
	*
	* @param templateNo the template no of this dossier template
	*/
	@Override
	public void setTemplateNo(java.lang.String templateNo) {
		_dossierTemplate.setTemplateNo(templateNo);
	}

	/**
	* Sets the user ID of this dossier template.
	*
	* @param userId the user ID of this dossier template
	*/
	@Override
	public void setUserId(long userId) {
		_dossierTemplate.setUserId(userId);
	}

	/**
	* Sets the user name of this dossier template.
	*
	* @param userName the user name of this dossier template
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_dossierTemplate.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dossier template.
	*
	* @param userUuid the user uuid of this dossier template
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_dossierTemplate.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier template.
	*
	* @param uuid the uuid of this dossier template
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_dossierTemplate.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierTemplateWrapper)) {
			return false;
		}

		DossierTemplateWrapper dossierTemplateWrapper = (DossierTemplateWrapper)obj;

		if (Objects.equals(_dossierTemplate,
					dossierTemplateWrapper._dossierTemplate)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dossierTemplate.getStagedModelType();
	}

	@Override
	public DossierTemplate getWrappedModel() {
		return _dossierTemplate;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossierTemplate.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossierTemplate.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossierTemplate.resetOriginalValues();
	}

	private final DossierTemplate _dossierTemplate;
}