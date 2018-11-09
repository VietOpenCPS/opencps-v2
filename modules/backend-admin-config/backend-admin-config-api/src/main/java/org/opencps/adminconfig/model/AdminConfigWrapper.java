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
 * This class is a wrapper for {@link AdminConfig}.
 * </p>
 *
 * @author binhth
 * @see AdminConfig
 * @generated
 */
@ProviderType
public class AdminConfigWrapper implements AdminConfig,
	ModelWrapper<AdminConfig> {
	public AdminConfigWrapper(AdminConfig adminConfig) {
		_adminConfig = adminConfig;
	}

	@Override
	public Class<?> getModelClass() {
		return AdminConfig.class;
	}

	@Override
	public String getModelClassName() {
		return AdminConfig.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("code", getCode());
		attributes.put("name", getName());
		attributes.put("bundleName", getBundleName());
		attributes.put("modelName", getModelName());
		attributes.put("serviceUtilName", getServiceUtilName());
		attributes.put("headersName", getHeadersName());
		attributes.put("columns", getColumns());
		attributes.put("detailColumns", getDetailColumns());
		attributes.put("extForm", isExtForm());
		attributes.put("groupFilter", isGroupFilter());
		attributes.put("publicManager", isPublicManager());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String code = (String)attributes.get("code");

		if (code != null) {
			setCode(code);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String bundleName = (String)attributes.get("bundleName");

		if (bundleName != null) {
			setBundleName(bundleName);
		}

		String modelName = (String)attributes.get("modelName");

		if (modelName != null) {
			setModelName(modelName);
		}

		String serviceUtilName = (String)attributes.get("serviceUtilName");

		if (serviceUtilName != null) {
			setServiceUtilName(serviceUtilName);
		}

		String headersName = (String)attributes.get("headersName");

		if (headersName != null) {
			setHeadersName(headersName);
		}

		String columns = (String)attributes.get("columns");

		if (columns != null) {
			setColumns(columns);
		}

		String detailColumns = (String)attributes.get("detailColumns");

		if (detailColumns != null) {
			setDetailColumns(detailColumns);
		}

		Boolean extForm = (Boolean)attributes.get("extForm");

		if (extForm != null) {
			setExtForm(extForm);
		}

		Boolean groupFilter = (Boolean)attributes.get("groupFilter");

		if (groupFilter != null) {
			setGroupFilter(groupFilter);
		}

		Boolean publicManager = (Boolean)attributes.get("publicManager");

		if (publicManager != null) {
			setPublicManager(publicManager);
		}
	}

	@Override
	public Object clone() {
		return new AdminConfigWrapper((AdminConfig)_adminConfig.clone());
	}

	@Override
	public int compareTo(AdminConfig adminConfig) {
		return _adminConfig.compareTo(adminConfig);
	}

	/**
	* Returns the bundle name of this admin config.
	*
	* @return the bundle name of this admin config
	*/
	@Override
	public String getBundleName() {
		return _adminConfig.getBundleName();
	}

	/**
	* Returns the code of this admin config.
	*
	* @return the code of this admin config
	*/
	@Override
	public String getCode() {
		return _adminConfig.getCode();
	}

	/**
	* Returns the columns of this admin config.
	*
	* @return the columns of this admin config
	*/
	@Override
	public String getColumns() {
		return _adminConfig.getColumns();
	}

	/**
	* Returns the detail columns of this admin config.
	*
	* @return the detail columns of this admin config
	*/
	@Override
	public String getDetailColumns() {
		return _adminConfig.getDetailColumns();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _adminConfig.getExpandoBridge();
	}

	/**
	* Returns the ext form of this admin config.
	*
	* @return the ext form of this admin config
	*/
	@Override
	public boolean getExtForm() {
		return _adminConfig.getExtForm();
	}

	/**
	* Returns the group filter of this admin config.
	*
	* @return the group filter of this admin config
	*/
	@Override
	public boolean getGroupFilter() {
		return _adminConfig.getGroupFilter();
	}

	/**
	* Returns the headers name of this admin config.
	*
	* @return the headers name of this admin config
	*/
	@Override
	public String getHeadersName() {
		return _adminConfig.getHeadersName();
	}

	/**
	* Returns the ID of this admin config.
	*
	* @return the ID of this admin config
	*/
	@Override
	public long getId() {
		return _adminConfig.getId();
	}

	/**
	* Returns the model name of this admin config.
	*
	* @return the model name of this admin config
	*/
	@Override
	public String getModelName() {
		return _adminConfig.getModelName();
	}

	/**
	* Returns the name of this admin config.
	*
	* @return the name of this admin config
	*/
	@Override
	public String getName() {
		return _adminConfig.getName();
	}

	/**
	* Returns the primary key of this admin config.
	*
	* @return the primary key of this admin config
	*/
	@Override
	public long getPrimaryKey() {
		return _adminConfig.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _adminConfig.getPrimaryKeyObj();
	}

	/**
	* Returns the public manager of this admin config.
	*
	* @return the public manager of this admin config
	*/
	@Override
	public boolean getPublicManager() {
		return _adminConfig.getPublicManager();
	}

	/**
	* Returns the service util name of this admin config.
	*
	* @return the service util name of this admin config
	*/
	@Override
	public String getServiceUtilName() {
		return _adminConfig.getServiceUtilName();
	}

	@Override
	public int hashCode() {
		return _adminConfig.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _adminConfig.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _adminConfig.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this admin config is ext form.
	*
	* @return <code>true</code> if this admin config is ext form; <code>false</code> otherwise
	*/
	@Override
	public boolean isExtForm() {
		return _adminConfig.isExtForm();
	}

	/**
	* Returns <code>true</code> if this admin config is group filter.
	*
	* @return <code>true</code> if this admin config is group filter; <code>false</code> otherwise
	*/
	@Override
	public boolean isGroupFilter() {
		return _adminConfig.isGroupFilter();
	}

	@Override
	public boolean isNew() {
		return _adminConfig.isNew();
	}

	/**
	* Returns <code>true</code> if this admin config is public manager.
	*
	* @return <code>true</code> if this admin config is public manager; <code>false</code> otherwise
	*/
	@Override
	public boolean isPublicManager() {
		return _adminConfig.isPublicManager();
	}

	@Override
	public void persist() {
		_adminConfig.persist();
	}

	/**
	* Sets the bundle name of this admin config.
	*
	* @param bundleName the bundle name of this admin config
	*/
	@Override
	public void setBundleName(String bundleName) {
		_adminConfig.setBundleName(bundleName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_adminConfig.setCachedModel(cachedModel);
	}

	/**
	* Sets the code of this admin config.
	*
	* @param code the code of this admin config
	*/
	@Override
	public void setCode(String code) {
		_adminConfig.setCode(code);
	}

	/**
	* Sets the columns of this admin config.
	*
	* @param columns the columns of this admin config
	*/
	@Override
	public void setColumns(String columns) {
		_adminConfig.setColumns(columns);
	}

	/**
	* Sets the detail columns of this admin config.
	*
	* @param detailColumns the detail columns of this admin config
	*/
	@Override
	public void setDetailColumns(String detailColumns) {
		_adminConfig.setDetailColumns(detailColumns);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_adminConfig.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_adminConfig.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_adminConfig.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this admin config is ext form.
	*
	* @param extForm the ext form of this admin config
	*/
	@Override
	public void setExtForm(boolean extForm) {
		_adminConfig.setExtForm(extForm);
	}

	/**
	* Sets whether this admin config is group filter.
	*
	* @param groupFilter the group filter of this admin config
	*/
	@Override
	public void setGroupFilter(boolean groupFilter) {
		_adminConfig.setGroupFilter(groupFilter);
	}

	/**
	* Sets the headers name of this admin config.
	*
	* @param headersName the headers name of this admin config
	*/
	@Override
	public void setHeadersName(String headersName) {
		_adminConfig.setHeadersName(headersName);
	}

	/**
	* Sets the ID of this admin config.
	*
	* @param id the ID of this admin config
	*/
	@Override
	public void setId(long id) {
		_adminConfig.setId(id);
	}

	/**
	* Sets the model name of this admin config.
	*
	* @param modelName the model name of this admin config
	*/
	@Override
	public void setModelName(String modelName) {
		_adminConfig.setModelName(modelName);
	}

	/**
	* Sets the name of this admin config.
	*
	* @param name the name of this admin config
	*/
	@Override
	public void setName(String name) {
		_adminConfig.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_adminConfig.setNew(n);
	}

	/**
	* Sets the primary key of this admin config.
	*
	* @param primaryKey the primary key of this admin config
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_adminConfig.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_adminConfig.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this admin config is public manager.
	*
	* @param publicManager the public manager of this admin config
	*/
	@Override
	public void setPublicManager(boolean publicManager) {
		_adminConfig.setPublicManager(publicManager);
	}

	/**
	* Sets the service util name of this admin config.
	*
	* @param serviceUtilName the service util name of this admin config
	*/
	@Override
	public void setServiceUtilName(String serviceUtilName) {
		_adminConfig.setServiceUtilName(serviceUtilName);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AdminConfig> toCacheModel() {
		return _adminConfig.toCacheModel();
	}

	@Override
	public AdminConfig toEscapedModel() {
		return new AdminConfigWrapper(_adminConfig.toEscapedModel());
	}

	@Override
	public String toString() {
		return _adminConfig.toString();
	}

	@Override
	public AdminConfig toUnescapedModel() {
		return new AdminConfigWrapper(_adminConfig.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _adminConfig.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AdminConfigWrapper)) {
			return false;
		}

		AdminConfigWrapper adminConfigWrapper = (AdminConfigWrapper)obj;

		if (Objects.equals(_adminConfig, adminConfigWrapper._adminConfig)) {
			return true;
		}

		return false;
	}

	@Override
	public AdminConfig getWrappedModel() {
		return _adminConfig;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _adminConfig.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _adminConfig.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_adminConfig.resetOriginalValues();
	}

	private final AdminConfig _adminConfig;
}