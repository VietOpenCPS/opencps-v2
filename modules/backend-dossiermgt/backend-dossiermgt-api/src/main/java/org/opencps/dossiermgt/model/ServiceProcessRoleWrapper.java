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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ServiceProcessRole}.
 * </p>
 *
 * @author huymq
 * @see ServiceProcessRole
 * @generated
 */
@ProviderType
public class ServiceProcessRoleWrapper implements ServiceProcessRole,
	ModelWrapper<ServiceProcessRole> {
	public ServiceProcessRoleWrapper(ServiceProcessRole serviceProcessRole) {
		_serviceProcessRole = serviceProcessRole;
	}

	@Override
	public Class<?> getModelClass() {
		return ServiceProcessRole.class;
	}

	@Override
	public String getModelClassName() {
		return ServiceProcessRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("serviceProcessId", getServiceProcessId());
		attributes.put("roleId", getRoleId());
		attributes.put("roleCode", getRoleCode());
		attributes.put("roleName", getRoleName());
		attributes.put("moderator", isModerator());
		attributes.put("condition", getCondition());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long serviceProcessId = (Long)attributes.get("serviceProcessId");

		if (serviceProcessId != null) {
			setServiceProcessId(serviceProcessId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		String roleCode = (String)attributes.get("roleCode");

		if (roleCode != null) {
			setRoleCode(roleCode);
		}

		String roleName = (String)attributes.get("roleName");

		if (roleName != null) {
			setRoleName(roleName);
		}

		Boolean moderator = (Boolean)attributes.get("moderator");

		if (moderator != null) {
			setModerator(moderator);
		}

		String condition = (String)attributes.get("condition");

		if (condition != null) {
			setCondition(condition);
		}
	}

	@Override
	public Object clone() {
		return new ServiceProcessRoleWrapper((ServiceProcessRole)_serviceProcessRole.clone());
	}

	@Override
	public int compareTo(ServiceProcessRole serviceProcessRole) {
		return _serviceProcessRole.compareTo(serviceProcessRole);
	}

	/**
	* Returns the condition of this service process role.
	*
	* @return the condition of this service process role
	*/
	@Override
	public String getCondition() {
		return _serviceProcessRole.getCondition();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _serviceProcessRole.getExpandoBridge();
	}

	/**
	* Returns the moderator of this service process role.
	*
	* @return the moderator of this service process role
	*/
	@Override
	public boolean getModerator() {
		return _serviceProcessRole.getModerator();
	}

	/**
	* Returns the primary key of this service process role.
	*
	* @return the primary key of this service process role
	*/
	@Override
	public org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK getPrimaryKey() {
		return _serviceProcessRole.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serviceProcessRole.getPrimaryKeyObj();
	}

	/**
	* Returns the role code of this service process role.
	*
	* @return the role code of this service process role
	*/
	@Override
	public String getRoleCode() {
		return _serviceProcessRole.getRoleCode();
	}

	/**
	* Returns the role ID of this service process role.
	*
	* @return the role ID of this service process role
	*/
	@Override
	public long getRoleId() {
		return _serviceProcessRole.getRoleId();
	}

	/**
	* Returns the role name of this service process role.
	*
	* @return the role name of this service process role
	*/
	@Override
	public String getRoleName() {
		return _serviceProcessRole.getRoleName();
	}

	/**
	* Returns the service process ID of this service process role.
	*
	* @return the service process ID of this service process role
	*/
	@Override
	public long getServiceProcessId() {
		return _serviceProcessRole.getServiceProcessId();
	}

	/**
	* Returns the uuid of this service process role.
	*
	* @return the uuid of this service process role
	*/
	@Override
	public String getUuid() {
		return _serviceProcessRole.getUuid();
	}

	@Override
	public int hashCode() {
		return _serviceProcessRole.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _serviceProcessRole.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _serviceProcessRole.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this service process role is moderator.
	*
	* @return <code>true</code> if this service process role is moderator; <code>false</code> otherwise
	*/
	@Override
	public boolean isModerator() {
		return _serviceProcessRole.isModerator();
	}

	@Override
	public boolean isNew() {
		return _serviceProcessRole.isNew();
	}

	@Override
	public void persist() {
		_serviceProcessRole.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_serviceProcessRole.setCachedModel(cachedModel);
	}

	/**
	* Sets the condition of this service process role.
	*
	* @param condition the condition of this service process role
	*/
	@Override
	public void setCondition(String condition) {
		_serviceProcessRole.setCondition(condition);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_serviceProcessRole.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_serviceProcessRole.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_serviceProcessRole.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this service process role is moderator.
	*
	* @param moderator the moderator of this service process role
	*/
	@Override
	public void setModerator(boolean moderator) {
		_serviceProcessRole.setModerator(moderator);
	}

	@Override
	public void setNew(boolean n) {
		_serviceProcessRole.setNew(n);
	}

	/**
	* Sets the primary key of this service process role.
	*
	* @param primaryKey the primary key of this service process role
	*/
	@Override
	public void setPrimaryKey(
		org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK primaryKey) {
		_serviceProcessRole.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_serviceProcessRole.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role code of this service process role.
	*
	* @param roleCode the role code of this service process role
	*/
	@Override
	public void setRoleCode(String roleCode) {
		_serviceProcessRole.setRoleCode(roleCode);
	}

	/**
	* Sets the role ID of this service process role.
	*
	* @param roleId the role ID of this service process role
	*/
	@Override
	public void setRoleId(long roleId) {
		_serviceProcessRole.setRoleId(roleId);
	}

	/**
	* Sets the role name of this service process role.
	*
	* @param roleName the role name of this service process role
	*/
	@Override
	public void setRoleName(String roleName) {
		_serviceProcessRole.setRoleName(roleName);
	}

	/**
	* Sets the service process ID of this service process role.
	*
	* @param serviceProcessId the service process ID of this service process role
	*/
	@Override
	public void setServiceProcessId(long serviceProcessId) {
		_serviceProcessRole.setServiceProcessId(serviceProcessId);
	}

	/**
	* Sets the uuid of this service process role.
	*
	* @param uuid the uuid of this service process role
	*/
	@Override
	public void setUuid(String uuid) {
		_serviceProcessRole.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ServiceProcessRole> toCacheModel() {
		return _serviceProcessRole.toCacheModel();
	}

	@Override
	public ServiceProcessRole toEscapedModel() {
		return new ServiceProcessRoleWrapper(_serviceProcessRole.toEscapedModel());
	}

	@Override
	public String toString() {
		return _serviceProcessRole.toString();
	}

	@Override
	public ServiceProcessRole toUnescapedModel() {
		return new ServiceProcessRoleWrapper(_serviceProcessRole.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _serviceProcessRole.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceProcessRoleWrapper)) {
			return false;
		}

		ServiceProcessRoleWrapper serviceProcessRoleWrapper = (ServiceProcessRoleWrapper)obj;

		if (Objects.equals(_serviceProcessRole,
					serviceProcessRoleWrapper._serviceProcessRole)) {
			return true;
		}

		return false;
	}

	@Override
	public ServiceProcessRole getWrappedModel() {
		return _serviceProcessRole;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _serviceProcessRole.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _serviceProcessRole.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_serviceProcessRole.resetOriginalValues();
	}

	private final ServiceProcessRole _serviceProcessRole;
}