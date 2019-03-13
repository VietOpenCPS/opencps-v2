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
 * This class is a wrapper for {@link DeliverableTypeRole}.
 * </p>
 *
 * @author huymq
 * @see DeliverableTypeRole
 * @generated
 */
@ProviderType
public class DeliverableTypeRoleWrapper implements DeliverableTypeRole,
	ModelWrapper<DeliverableTypeRole> {
	public DeliverableTypeRoleWrapper(DeliverableTypeRole deliverableTypeRole) {
		_deliverableTypeRole = deliverableTypeRole;
	}

	@Override
	public Class<?> getModelClass() {
		return DeliverableTypeRole.class;
	}

	@Override
	public String getModelClassName() {
		return DeliverableTypeRole.class.getName();
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
		return new DeliverableTypeRoleWrapper((DeliverableTypeRole)_deliverableTypeRole.clone());
	}

	@Override
	public int compareTo(DeliverableTypeRole deliverableTypeRole) {
		return _deliverableTypeRole.compareTo(deliverableTypeRole);
	}

	/**
	* Returns the company ID of this deliverable type role.
	*
	* @return the company ID of this deliverable type role
	*/
	@Override
	public long getCompanyId() {
		return _deliverableTypeRole.getCompanyId();
	}

	/**
	* Returns the create date of this deliverable type role.
	*
	* @return the create date of this deliverable type role
	*/
	@Override
	public Date getCreateDate() {
		return _deliverableTypeRole.getCreateDate();
	}

	/**
	* Returns the deliverable type ID of this deliverable type role.
	*
	* @return the deliverable type ID of this deliverable type role
	*/
	@Override
	public long getDeliverableTypeId() {
		return _deliverableTypeRole.getDeliverableTypeId();
	}

	/**
	* Returns the deliverable type role ID of this deliverable type role.
	*
	* @return the deliverable type role ID of this deliverable type role
	*/
	@Override
	public long getDeliverableTypeRoleId() {
		return _deliverableTypeRole.getDeliverableTypeRoleId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _deliverableTypeRole.getExpandoBridge();
	}

	/**
	* Returns the group ID of this deliverable type role.
	*
	* @return the group ID of this deliverable type role
	*/
	@Override
	public long getGroupId() {
		return _deliverableTypeRole.getGroupId();
	}

	/**
	* Returns the moderator of this deliverable type role.
	*
	* @return the moderator of this deliverable type role
	*/
	@Override
	public boolean getModerator() {
		return _deliverableTypeRole.getModerator();
	}

	/**
	* Returns the modified date of this deliverable type role.
	*
	* @return the modified date of this deliverable type role
	*/
	@Override
	public Date getModifiedDate() {
		return _deliverableTypeRole.getModifiedDate();
	}

	/**
	* Returns the primary key of this deliverable type role.
	*
	* @return the primary key of this deliverable type role
	*/
	@Override
	public long getPrimaryKey() {
		return _deliverableTypeRole.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _deliverableTypeRole.getPrimaryKeyObj();
	}

	/**
	* Returns the role ID of this deliverable type role.
	*
	* @return the role ID of this deliverable type role
	*/
	@Override
	public long getRoleId() {
		return _deliverableTypeRole.getRoleId();
	}

	/**
	* Returns the user ID of this deliverable type role.
	*
	* @return the user ID of this deliverable type role
	*/
	@Override
	public long getUserId() {
		return _deliverableTypeRole.getUserId();
	}

	/**
	* Returns the user name of this deliverable type role.
	*
	* @return the user name of this deliverable type role
	*/
	@Override
	public String getUserName() {
		return _deliverableTypeRole.getUserName();
	}

	/**
	* Returns the user uuid of this deliverable type role.
	*
	* @return the user uuid of this deliverable type role
	*/
	@Override
	public String getUserUuid() {
		return _deliverableTypeRole.getUserUuid();
	}

	/**
	* Returns the uuid of this deliverable type role.
	*
	* @return the uuid of this deliverable type role
	*/
	@Override
	public String getUuid() {
		return _deliverableTypeRole.getUuid();
	}

	@Override
	public int hashCode() {
		return _deliverableTypeRole.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _deliverableTypeRole.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _deliverableTypeRole.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this deliverable type role is moderator.
	*
	* @return <code>true</code> if this deliverable type role is moderator; <code>false</code> otherwise
	*/
	@Override
	public boolean isModerator() {
		return _deliverableTypeRole.isModerator();
	}

	@Override
	public boolean isNew() {
		return _deliverableTypeRole.isNew();
	}

	@Override
	public void persist() {
		_deliverableTypeRole.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_deliverableTypeRole.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this deliverable type role.
	*
	* @param companyId the company ID of this deliverable type role
	*/
	@Override
	public void setCompanyId(long companyId) {
		_deliverableTypeRole.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this deliverable type role.
	*
	* @param createDate the create date of this deliverable type role
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_deliverableTypeRole.setCreateDate(createDate);
	}

	/**
	* Sets the deliverable type ID of this deliverable type role.
	*
	* @param deliverableTypeId the deliverable type ID of this deliverable type role
	*/
	@Override
	public void setDeliverableTypeId(long deliverableTypeId) {
		_deliverableTypeRole.setDeliverableTypeId(deliverableTypeId);
	}

	/**
	* Sets the deliverable type role ID of this deliverable type role.
	*
	* @param deliverableTypeRoleId the deliverable type role ID of this deliverable type role
	*/
	@Override
	public void setDeliverableTypeRoleId(long deliverableTypeRoleId) {
		_deliverableTypeRole.setDeliverableTypeRoleId(deliverableTypeRoleId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_deliverableTypeRole.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_deliverableTypeRole.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_deliverableTypeRole.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this deliverable type role.
	*
	* @param groupId the group ID of this deliverable type role
	*/
	@Override
	public void setGroupId(long groupId) {
		_deliverableTypeRole.setGroupId(groupId);
	}

	/**
	* Sets whether this deliverable type role is moderator.
	*
	* @param moderator the moderator of this deliverable type role
	*/
	@Override
	public void setModerator(boolean moderator) {
		_deliverableTypeRole.setModerator(moderator);
	}

	/**
	* Sets the modified date of this deliverable type role.
	*
	* @param modifiedDate the modified date of this deliverable type role
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_deliverableTypeRole.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_deliverableTypeRole.setNew(n);
	}

	/**
	* Sets the primary key of this deliverable type role.
	*
	* @param primaryKey the primary key of this deliverable type role
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_deliverableTypeRole.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_deliverableTypeRole.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role ID of this deliverable type role.
	*
	* @param roleId the role ID of this deliverable type role
	*/
	@Override
	public void setRoleId(long roleId) {
		_deliverableTypeRole.setRoleId(roleId);
	}

	/**
	* Sets the user ID of this deliverable type role.
	*
	* @param userId the user ID of this deliverable type role
	*/
	@Override
	public void setUserId(long userId) {
		_deliverableTypeRole.setUserId(userId);
	}

	/**
	* Sets the user name of this deliverable type role.
	*
	* @param userName the user name of this deliverable type role
	*/
	@Override
	public void setUserName(String userName) {
		_deliverableTypeRole.setUserName(userName);
	}

	/**
	* Sets the user uuid of this deliverable type role.
	*
	* @param userUuid the user uuid of this deliverable type role
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_deliverableTypeRole.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this deliverable type role.
	*
	* @param uuid the uuid of this deliverable type role
	*/
	@Override
	public void setUuid(String uuid) {
		_deliverableTypeRole.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DeliverableTypeRole> toCacheModel() {
		return _deliverableTypeRole.toCacheModel();
	}

	@Override
	public DeliverableTypeRole toEscapedModel() {
		return new DeliverableTypeRoleWrapper(_deliverableTypeRole.toEscapedModel());
	}

	@Override
	public String toString() {
		return _deliverableTypeRole.toString();
	}

	@Override
	public DeliverableTypeRole toUnescapedModel() {
		return new DeliverableTypeRoleWrapper(_deliverableTypeRole.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _deliverableTypeRole.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeliverableTypeRoleWrapper)) {
			return false;
		}

		DeliverableTypeRoleWrapper deliverableTypeRoleWrapper = (DeliverableTypeRoleWrapper)obj;

		if (Objects.equals(_deliverableTypeRole,
					deliverableTypeRoleWrapper._deliverableTypeRole)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _deliverableTypeRole.getStagedModelType();
	}

	@Override
	public DeliverableTypeRole getWrappedModel() {
		return _deliverableTypeRole;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _deliverableTypeRole.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _deliverableTypeRole.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_deliverableTypeRole.resetOriginalValues();
	}

	private final DeliverableTypeRole _deliverableTypeRole;
}