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

package org.opencps.deliverable.model;

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
 * This class is a wrapper for {@link OpenCPSDeliverableTypeRole}.
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverableTypeRole
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableTypeRoleWrapper
	implements OpenCPSDeliverableTypeRole,
		ModelWrapper<OpenCPSDeliverableTypeRole> {
	public OpenCPSDeliverableTypeRoleWrapper(
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		_openCPSDeliverableTypeRole = openCPSDeliverableTypeRole;
	}

	@Override
	public Class<?> getModelClass() {
		return OpenCPSDeliverableTypeRole.class;
	}

	@Override
	public String getModelClassName() {
		return OpenCPSDeliverableTypeRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("deliverableTypeRoleId", getDeliverableTypeRoleId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("deliverableTypeId", getDeliverableTypeId());
		attributes.put("roleId", getRoleId());
		attributes.put("moderator", isModerator());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long deliverableTypeRoleId = (Long)attributes.get(
				"deliverableTypeRoleId");

		if (deliverableTypeRoleId != null) {
			setDeliverableTypeRoleId(deliverableTypeRoleId);
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

		Long deliverableTypeId = (Long)attributes.get("deliverableTypeId");

		if (deliverableTypeId != null) {
			setDeliverableTypeId(deliverableTypeId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		Boolean moderator = (Boolean)attributes.get("moderator");

		if (moderator != null) {
			setModerator(moderator);
		}
	}

	@Override
	public Object clone() {
		return new OpenCPSDeliverableTypeRoleWrapper((OpenCPSDeliverableTypeRole)_openCPSDeliverableTypeRole.clone());
	}

	@Override
	public int compareTo(OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		return _openCPSDeliverableTypeRole.compareTo(openCPSDeliverableTypeRole);
	}

	/**
	* Returns the company ID of this open cps deliverable type role.
	*
	* @return the company ID of this open cps deliverable type role
	*/
	@Override
	public long getCompanyId() {
		return _openCPSDeliverableTypeRole.getCompanyId();
	}

	/**
	* Returns the create date of this open cps deliverable type role.
	*
	* @return the create date of this open cps deliverable type role
	*/
	@Override
	public Date getCreateDate() {
		return _openCPSDeliverableTypeRole.getCreateDate();
	}

	/**
	* Returns the deliverable type ID of this open cps deliverable type role.
	*
	* @return the deliverable type ID of this open cps deliverable type role
	*/
	@Override
	public long getDeliverableTypeId() {
		return _openCPSDeliverableTypeRole.getDeliverableTypeId();
	}

	/**
	* Returns the deliverable type role ID of this open cps deliverable type role.
	*
	* @return the deliverable type role ID of this open cps deliverable type role
	*/
	@Override
	public long getDeliverableTypeRoleId() {
		return _openCPSDeliverableTypeRole.getDeliverableTypeRoleId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _openCPSDeliverableTypeRole.getExpandoBridge();
	}

	/**
	* Returns the group ID of this open cps deliverable type role.
	*
	* @return the group ID of this open cps deliverable type role
	*/
	@Override
	public long getGroupId() {
		return _openCPSDeliverableTypeRole.getGroupId();
	}

	/**
	* Returns the moderator of this open cps deliverable type role.
	*
	* @return the moderator of this open cps deliverable type role
	*/
	@Override
	public boolean getModerator() {
		return _openCPSDeliverableTypeRole.getModerator();
	}

	/**
	* Returns the modified date of this open cps deliverable type role.
	*
	* @return the modified date of this open cps deliverable type role
	*/
	@Override
	public Date getModifiedDate() {
		return _openCPSDeliverableTypeRole.getModifiedDate();
	}

	/**
	* Returns the primary key of this open cps deliverable type role.
	*
	* @return the primary key of this open cps deliverable type role
	*/
	@Override
	public long getPrimaryKey() {
		return _openCPSDeliverableTypeRole.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _openCPSDeliverableTypeRole.getPrimaryKeyObj();
	}

	/**
	* Returns the role ID of this open cps deliverable type role.
	*
	* @return the role ID of this open cps deliverable type role
	*/
	@Override
	public long getRoleId() {
		return _openCPSDeliverableTypeRole.getRoleId();
	}

	/**
	* Returns the user ID of this open cps deliverable type role.
	*
	* @return the user ID of this open cps deliverable type role
	*/
	@Override
	public long getUserId() {
		return _openCPSDeliverableTypeRole.getUserId();
	}

	/**
	* Returns the user name of this open cps deliverable type role.
	*
	* @return the user name of this open cps deliverable type role
	*/
	@Override
	public String getUserName() {
		return _openCPSDeliverableTypeRole.getUserName();
	}

	/**
	* Returns the user uuid of this open cps deliverable type role.
	*
	* @return the user uuid of this open cps deliverable type role
	*/
	@Override
	public String getUserUuid() {
		return _openCPSDeliverableTypeRole.getUserUuid();
	}

	/**
	* Returns the uuid of this open cps deliverable type role.
	*
	* @return the uuid of this open cps deliverable type role
	*/
	@Override
	public String getUuid() {
		return _openCPSDeliverableTypeRole.getUuid();
	}

	@Override
	public int hashCode() {
		return _openCPSDeliverableTypeRole.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _openCPSDeliverableTypeRole.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _openCPSDeliverableTypeRole.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this open cps deliverable type role is moderator.
	*
	* @return <code>true</code> if this open cps deliverable type role is moderator; <code>false</code> otherwise
	*/
	@Override
	public boolean isModerator() {
		return _openCPSDeliverableTypeRole.isModerator();
	}

	@Override
	public boolean isNew() {
		return _openCPSDeliverableTypeRole.isNew();
	}

	@Override
	public void persist() {
		_openCPSDeliverableTypeRole.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_openCPSDeliverableTypeRole.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this open cps deliverable type role.
	*
	* @param companyId the company ID of this open cps deliverable type role
	*/
	@Override
	public void setCompanyId(long companyId) {
		_openCPSDeliverableTypeRole.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this open cps deliverable type role.
	*
	* @param createDate the create date of this open cps deliverable type role
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_openCPSDeliverableTypeRole.setCreateDate(createDate);
	}

	/**
	* Sets the deliverable type ID of this open cps deliverable type role.
	*
	* @param deliverableTypeId the deliverable type ID of this open cps deliverable type role
	*/
	@Override
	public void setDeliverableTypeId(long deliverableTypeId) {
		_openCPSDeliverableTypeRole.setDeliverableTypeId(deliverableTypeId);
	}

	/**
	* Sets the deliverable type role ID of this open cps deliverable type role.
	*
	* @param deliverableTypeRoleId the deliverable type role ID of this open cps deliverable type role
	*/
	@Override
	public void setDeliverableTypeRoleId(long deliverableTypeRoleId) {
		_openCPSDeliverableTypeRole.setDeliverableTypeRoleId(deliverableTypeRoleId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_openCPSDeliverableTypeRole.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_openCPSDeliverableTypeRole.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_openCPSDeliverableTypeRole.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this open cps deliverable type role.
	*
	* @param groupId the group ID of this open cps deliverable type role
	*/
	@Override
	public void setGroupId(long groupId) {
		_openCPSDeliverableTypeRole.setGroupId(groupId);
	}

	/**
	* Sets whether this open cps deliverable type role is moderator.
	*
	* @param moderator the moderator of this open cps deliverable type role
	*/
	@Override
	public void setModerator(boolean moderator) {
		_openCPSDeliverableTypeRole.setModerator(moderator);
	}

	/**
	* Sets the modified date of this open cps deliverable type role.
	*
	* @param modifiedDate the modified date of this open cps deliverable type role
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_openCPSDeliverableTypeRole.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_openCPSDeliverableTypeRole.setNew(n);
	}

	/**
	* Sets the primary key of this open cps deliverable type role.
	*
	* @param primaryKey the primary key of this open cps deliverable type role
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_openCPSDeliverableTypeRole.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_openCPSDeliverableTypeRole.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role ID of this open cps deliverable type role.
	*
	* @param roleId the role ID of this open cps deliverable type role
	*/
	@Override
	public void setRoleId(long roleId) {
		_openCPSDeliverableTypeRole.setRoleId(roleId);
	}

	/**
	* Sets the user ID of this open cps deliverable type role.
	*
	* @param userId the user ID of this open cps deliverable type role
	*/
	@Override
	public void setUserId(long userId) {
		_openCPSDeliverableTypeRole.setUserId(userId);
	}

	/**
	* Sets the user name of this open cps deliverable type role.
	*
	* @param userName the user name of this open cps deliverable type role
	*/
	@Override
	public void setUserName(String userName) {
		_openCPSDeliverableTypeRole.setUserName(userName);
	}

	/**
	* Sets the user uuid of this open cps deliverable type role.
	*
	* @param userUuid the user uuid of this open cps deliverable type role
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_openCPSDeliverableTypeRole.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this open cps deliverable type role.
	*
	* @param uuid the uuid of this open cps deliverable type role
	*/
	@Override
	public void setUuid(String uuid) {
		_openCPSDeliverableTypeRole.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OpenCPSDeliverableTypeRole> toCacheModel() {
		return _openCPSDeliverableTypeRole.toCacheModel();
	}

	@Override
	public OpenCPSDeliverableTypeRole toEscapedModel() {
		return new OpenCPSDeliverableTypeRoleWrapper(_openCPSDeliverableTypeRole.toEscapedModel());
	}

	@Override
	public String toString() {
		return _openCPSDeliverableTypeRole.toString();
	}

	@Override
	public OpenCPSDeliverableTypeRole toUnescapedModel() {
		return new OpenCPSDeliverableTypeRoleWrapper(_openCPSDeliverableTypeRole.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _openCPSDeliverableTypeRole.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpenCPSDeliverableTypeRoleWrapper)) {
			return false;
		}

		OpenCPSDeliverableTypeRoleWrapper openCPSDeliverableTypeRoleWrapper = (OpenCPSDeliverableTypeRoleWrapper)obj;

		if (Objects.equals(_openCPSDeliverableTypeRole,
					openCPSDeliverableTypeRoleWrapper._openCPSDeliverableTypeRole)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _openCPSDeliverableTypeRole.getStagedModelType();
	}

	@Override
	public OpenCPSDeliverableTypeRole getWrappedModel() {
		return _openCPSDeliverableTypeRole;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _openCPSDeliverableTypeRole.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _openCPSDeliverableTypeRole.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_openCPSDeliverableTypeRole.resetOriginalValues();
	}

	private final OpenCPSDeliverableTypeRole _openCPSDeliverableTypeRole;
}