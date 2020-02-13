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

package org.opencps.adminconfig.model;

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
 * This class is a wrapper for {@link ReportRole}.
 * </p>
 *
 * @author binhth
 * @see ReportRole
 * @generated
 */
@ProviderType
public class ReportRoleWrapper implements ReportRole, ModelWrapper<ReportRole> {
	public ReportRoleWrapper(ReportRole reportRole) {
		_reportRole = reportRole;
	}

	@Override
	public Class<?> getModelClass() {
		return ReportRole.class;
	}

	@Override
	public String getModelClassName() {
		return ReportRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("reportRoleId", getReportRoleId());
		attributes.put("dynamicReportId", getDynamicReportId());
		attributes.put("roleId", getRoleId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long reportRoleId = (Long)attributes.get("reportRoleId");

		if (reportRoleId != null) {
			setReportRoleId(reportRoleId);
		}

		Long dynamicReportId = (Long)attributes.get("dynamicReportId");

		if (dynamicReportId != null) {
			setDynamicReportId(dynamicReportId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}
	}

	@Override
	public Object clone() {
		return new ReportRoleWrapper((ReportRole)_reportRole.clone());
	}

	@Override
	public int compareTo(ReportRole reportRole) {
		return _reportRole.compareTo(reportRole);
	}

	/**
	* Returns the dynamic report ID of this report role.
	*
	* @return the dynamic report ID of this report role
	*/
	@Override
	public long getDynamicReportId() {
		return _reportRole.getDynamicReportId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _reportRole.getExpandoBridge();
	}

	/**
	* Returns the primary key of this report role.
	*
	* @return the primary key of this report role
	*/
	@Override
	public long getPrimaryKey() {
		return _reportRole.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _reportRole.getPrimaryKeyObj();
	}

	/**
	* Returns the report role ID of this report role.
	*
	* @return the report role ID of this report role
	*/
	@Override
	public long getReportRoleId() {
		return _reportRole.getReportRoleId();
	}

	/**
	* Returns the role ID of this report role.
	*
	* @return the role ID of this report role
	*/
	@Override
	public long getRoleId() {
		return _reportRole.getRoleId();
	}

	@Override
	public int hashCode() {
		return _reportRole.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _reportRole.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _reportRole.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _reportRole.isNew();
	}

	@Override
	public void persist() {
		_reportRole.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_reportRole.setCachedModel(cachedModel);
	}

	/**
	* Sets the dynamic report ID of this report role.
	*
	* @param dynamicReportId the dynamic report ID of this report role
	*/
	@Override
	public void setDynamicReportId(long dynamicReportId) {
		_reportRole.setDynamicReportId(dynamicReportId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_reportRole.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_reportRole.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_reportRole.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_reportRole.setNew(n);
	}

	/**
	* Sets the primary key of this report role.
	*
	* @param primaryKey the primary key of this report role
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_reportRole.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_reportRole.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the report role ID of this report role.
	*
	* @param reportRoleId the report role ID of this report role
	*/
	@Override
	public void setReportRoleId(long reportRoleId) {
		_reportRole.setReportRoleId(reportRoleId);
	}

	/**
	* Sets the role ID of this report role.
	*
	* @param roleId the role ID of this report role
	*/
	@Override
	public void setRoleId(long roleId) {
		_reportRole.setRoleId(roleId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ReportRole> toCacheModel() {
		return _reportRole.toCacheModel();
	}

	@Override
	public ReportRole toEscapedModel() {
		return new ReportRoleWrapper(_reportRole.toEscapedModel());
	}

	@Override
	public String toString() {
		return _reportRole.toString();
	}

	@Override
	public ReportRole toUnescapedModel() {
		return new ReportRoleWrapper(_reportRole.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _reportRole.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReportRoleWrapper)) {
			return false;
		}

		ReportRoleWrapper reportRoleWrapper = (ReportRoleWrapper)obj;

		if (Objects.equals(_reportRole, reportRoleWrapper._reportRole)) {
			return true;
		}

		return false;
	}

	@Override
	public ReportRole getWrappedModel() {
		return _reportRole;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _reportRole.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _reportRole.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_reportRole.resetOriginalValues();
	}

	private final ReportRole _reportRole;
}