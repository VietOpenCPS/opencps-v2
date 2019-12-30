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
 * This class is a wrapper for {@link DossierStatusMapping}.
 * </p>
 *
 * @author huymq
 * @see DossierStatusMapping
 * @generated
 */
@ProviderType
public class DossierStatusMappingWrapper implements DossierStatusMapping,
	ModelWrapper<DossierStatusMapping> {
	public DossierStatusMappingWrapper(
		DossierStatusMapping dossierStatusMapping) {
		_dossierStatusMapping = dossierStatusMapping;
	}

	@Override
	public Class<?> getModelClass() {
		return DossierStatusMapping.class;
	}

	@Override
	public String getModelClassName() {
		return DossierStatusMapping.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("dossierStatusMappingId", getDossierStatusMappingId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("statusCode", getStatusCode());
		attributes.put("statusCodeDVCQG", getStatusCodeDVCQG());
		attributes.put("subStatusCode", getSubStatusCode());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long dossierStatusMappingId = (Long)attributes.get(
				"dossierStatusMappingId");

		if (dossierStatusMappingId != null) {
			setDossierStatusMappingId(dossierStatusMappingId);
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

		String statusCode = (String)attributes.get("statusCode");

		if (statusCode != null) {
			setStatusCode(statusCode);
		}

		String statusCodeDVCQG = (String)attributes.get("statusCodeDVCQG");

		if (statusCodeDVCQG != null) {
			setStatusCodeDVCQG(statusCodeDVCQG);
		}

		String subStatusCode = (String)attributes.get("subStatusCode");

		if (subStatusCode != null) {
			setSubStatusCode(subStatusCode);
		}
	}

	@Override
	public Object clone() {
		return new DossierStatusMappingWrapper((DossierStatusMapping)_dossierStatusMapping.clone());
	}

	@Override
	public int compareTo(DossierStatusMapping dossierStatusMapping) {
		return _dossierStatusMapping.compareTo(dossierStatusMapping);
	}

	/**
	* Returns the company ID of this dossier status mapping.
	*
	* @return the company ID of this dossier status mapping
	*/
	@Override
	public long getCompanyId() {
		return _dossierStatusMapping.getCompanyId();
	}

	/**
	* Returns the create date of this dossier status mapping.
	*
	* @return the create date of this dossier status mapping
	*/
	@Override
	public Date getCreateDate() {
		return _dossierStatusMapping.getCreateDate();
	}

	/**
	* Returns the dossier status mapping ID of this dossier status mapping.
	*
	* @return the dossier status mapping ID of this dossier status mapping
	*/
	@Override
	public long getDossierStatusMappingId() {
		return _dossierStatusMapping.getDossierStatusMappingId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierStatusMapping.getExpandoBridge();
	}

	/**
	* Returns the group ID of this dossier status mapping.
	*
	* @return the group ID of this dossier status mapping
	*/
	@Override
	public long getGroupId() {
		return _dossierStatusMapping.getGroupId();
	}

	/**
	* Returns the modified date of this dossier status mapping.
	*
	* @return the modified date of this dossier status mapping
	*/
	@Override
	public Date getModifiedDate() {
		return _dossierStatusMapping.getModifiedDate();
	}

	/**
	* Returns the primary key of this dossier status mapping.
	*
	* @return the primary key of this dossier status mapping
	*/
	@Override
	public long getPrimaryKey() {
		return _dossierStatusMapping.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierStatusMapping.getPrimaryKeyObj();
	}

	/**
	* Returns the status code of this dossier status mapping.
	*
	* @return the status code of this dossier status mapping
	*/
	@Override
	public String getStatusCode() {
		return _dossierStatusMapping.getStatusCode();
	}

	/**
	* Returns the status code dvcqg of this dossier status mapping.
	*
	* @return the status code dvcqg of this dossier status mapping
	*/
	@Override
	public String getStatusCodeDVCQG() {
		return _dossierStatusMapping.getStatusCodeDVCQG();
	}

	/**
	* Returns the sub status code of this dossier status mapping.
	*
	* @return the sub status code of this dossier status mapping
	*/
	@Override
	public String getSubStatusCode() {
		return _dossierStatusMapping.getSubStatusCode();
	}

	/**
	* Returns the user ID of this dossier status mapping.
	*
	* @return the user ID of this dossier status mapping
	*/
	@Override
	public long getUserId() {
		return _dossierStatusMapping.getUserId();
	}

	/**
	* Returns the user name of this dossier status mapping.
	*
	* @return the user name of this dossier status mapping
	*/
	@Override
	public String getUserName() {
		return _dossierStatusMapping.getUserName();
	}

	/**
	* Returns the user uuid of this dossier status mapping.
	*
	* @return the user uuid of this dossier status mapping
	*/
	@Override
	public String getUserUuid() {
		return _dossierStatusMapping.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _dossierStatusMapping.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dossierStatusMapping.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossierStatusMapping.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dossierStatusMapping.isNew();
	}

	@Override
	public void persist() {
		_dossierStatusMapping.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossierStatusMapping.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dossier status mapping.
	*
	* @param companyId the company ID of this dossier status mapping
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dossierStatusMapping.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dossier status mapping.
	*
	* @param createDate the create date of this dossier status mapping
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dossierStatusMapping.setCreateDate(createDate);
	}

	/**
	* Sets the dossier status mapping ID of this dossier status mapping.
	*
	* @param dossierStatusMappingId the dossier status mapping ID of this dossier status mapping
	*/
	@Override
	public void setDossierStatusMappingId(long dossierStatusMappingId) {
		_dossierStatusMapping.setDossierStatusMappingId(dossierStatusMappingId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierStatusMapping.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierStatusMapping.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierStatusMapping.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dossier status mapping.
	*
	* @param groupId the group ID of this dossier status mapping
	*/
	@Override
	public void setGroupId(long groupId) {
		_dossierStatusMapping.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this dossier status mapping.
	*
	* @param modifiedDate the modified date of this dossier status mapping
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dossierStatusMapping.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dossierStatusMapping.setNew(n);
	}

	/**
	* Sets the primary key of this dossier status mapping.
	*
	* @param primaryKey the primary key of this dossier status mapping
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dossierStatusMapping.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierStatusMapping.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status code of this dossier status mapping.
	*
	* @param statusCode the status code of this dossier status mapping
	*/
	@Override
	public void setStatusCode(String statusCode) {
		_dossierStatusMapping.setStatusCode(statusCode);
	}

	/**
	* Sets the status code dvcqg of this dossier status mapping.
	*
	* @param statusCodeDVCQG the status code dvcqg of this dossier status mapping
	*/
	@Override
	public void setStatusCodeDVCQG(String statusCodeDVCQG) {
		_dossierStatusMapping.setStatusCodeDVCQG(statusCodeDVCQG);
	}

	/**
	* Sets the sub status code of this dossier status mapping.
	*
	* @param subStatusCode the sub status code of this dossier status mapping
	*/
	@Override
	public void setSubStatusCode(String subStatusCode) {
		_dossierStatusMapping.setSubStatusCode(subStatusCode);
	}

	/**
	* Sets the user ID of this dossier status mapping.
	*
	* @param userId the user ID of this dossier status mapping
	*/
	@Override
	public void setUserId(long userId) {
		_dossierStatusMapping.setUserId(userId);
	}

	/**
	* Sets the user name of this dossier status mapping.
	*
	* @param userName the user name of this dossier status mapping
	*/
	@Override
	public void setUserName(String userName) {
		_dossierStatusMapping.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dossier status mapping.
	*
	* @param userUuid the user uuid of this dossier status mapping
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dossierStatusMapping.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierStatusMapping> toCacheModel() {
		return _dossierStatusMapping.toCacheModel();
	}

	@Override
	public DossierStatusMapping toEscapedModel() {
		return new DossierStatusMappingWrapper(_dossierStatusMapping.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dossierStatusMapping.toString();
	}

	@Override
	public DossierStatusMapping toUnescapedModel() {
		return new DossierStatusMappingWrapper(_dossierStatusMapping.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dossierStatusMapping.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierStatusMappingWrapper)) {
			return false;
		}

		DossierStatusMappingWrapper dossierStatusMappingWrapper = (DossierStatusMappingWrapper)obj;

		if (Objects.equals(_dossierStatusMapping,
					dossierStatusMappingWrapper._dossierStatusMapping)) {
			return true;
		}

		return false;
	}

	@Override
	public DossierStatusMapping getWrappedModel() {
		return _dossierStatusMapping;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossierStatusMapping.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossierStatusMapping.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossierStatusMapping.resetOriginalValues();
	}

	private final DossierStatusMapping _dossierStatusMapping;
}