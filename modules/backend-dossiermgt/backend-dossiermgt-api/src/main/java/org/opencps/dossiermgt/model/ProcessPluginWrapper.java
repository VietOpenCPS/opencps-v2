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
 * This class is a wrapper for {@link ProcessPlugin}.
 * </p>
 *
 * @author huymq
 * @see ProcessPlugin
 * @generated
 */
@ProviderType
public class ProcessPluginWrapper implements ProcessPlugin,
	ModelWrapper<ProcessPlugin> {
	public ProcessPluginWrapper(ProcessPlugin processPlugin) {
		_processPlugin = processPlugin;
	}

	@Override
	public Class<?> getModelClass() {
		return ProcessPlugin.class;
	}

	@Override
	public String getModelClassName() {
		return ProcessPlugin.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("processPluginId", getProcessPluginId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("stepCode", getStepCode());
		attributes.put("serviceProcessId", getServiceProcessId());
		attributes.put("pluginName", getPluginName());
		attributes.put("pluginType", getPluginType());
		attributes.put("sequenceNo", getSequenceNo());
		attributes.put("pluginForm", getPluginForm());
		attributes.put("sampleData", getSampleData());
		attributes.put("autoRun", isAutoRun());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long processPluginId = (Long)attributes.get("processPluginId");

		if (processPluginId != null) {
			setProcessPluginId(processPluginId);
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

		String stepCode = (String)attributes.get("stepCode");

		if (stepCode != null) {
			setStepCode(stepCode);
		}

		Long serviceProcessId = (Long)attributes.get("serviceProcessId");

		if (serviceProcessId != null) {
			setServiceProcessId(serviceProcessId);
		}

		String pluginName = (String)attributes.get("pluginName");

		if (pluginName != null) {
			setPluginName(pluginName);
		}

		Integer pluginType = (Integer)attributes.get("pluginType");

		if (pluginType != null) {
			setPluginType(pluginType);
		}

		String sequenceNo = (String)attributes.get("sequenceNo");

		if (sequenceNo != null) {
			setSequenceNo(sequenceNo);
		}

		String pluginForm = (String)attributes.get("pluginForm");

		if (pluginForm != null) {
			setPluginForm(pluginForm);
		}

		String sampleData = (String)attributes.get("sampleData");

		if (sampleData != null) {
			setSampleData(sampleData);
		}

		Boolean autoRun = (Boolean)attributes.get("autoRun");

		if (autoRun != null) {
			setAutoRun(autoRun);
		}
	}

	@Override
	public Object clone() {
		return new ProcessPluginWrapper((ProcessPlugin)_processPlugin.clone());
	}

	@Override
	public int compareTo(ProcessPlugin processPlugin) {
		return _processPlugin.compareTo(processPlugin);
	}

	/**
	* Returns the auto run of this process plugin.
	*
	* @return the auto run of this process plugin
	*/
	@Override
	public boolean getAutoRun() {
		return _processPlugin.getAutoRun();
	}

	/**
	* Returns the company ID of this process plugin.
	*
	* @return the company ID of this process plugin
	*/
	@Override
	public long getCompanyId() {
		return _processPlugin.getCompanyId();
	}

	/**
	* Returns the create date of this process plugin.
	*
	* @return the create date of this process plugin
	*/
	@Override
	public Date getCreateDate() {
		return _processPlugin.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _processPlugin.getExpandoBridge();
	}

	/**
	* Returns the group ID of this process plugin.
	*
	* @return the group ID of this process plugin
	*/
	@Override
	public long getGroupId() {
		return _processPlugin.getGroupId();
	}

	/**
	* Returns the modified date of this process plugin.
	*
	* @return the modified date of this process plugin
	*/
	@Override
	public Date getModifiedDate() {
		return _processPlugin.getModifiedDate();
	}

	/**
	* Returns the plugin form of this process plugin.
	*
	* @return the plugin form of this process plugin
	*/
	@Override
	public String getPluginForm() {
		return _processPlugin.getPluginForm();
	}

	/**
	* Returns the plugin name of this process plugin.
	*
	* @return the plugin name of this process plugin
	*/
	@Override
	public String getPluginName() {
		return _processPlugin.getPluginName();
	}

	/**
	* Returns the plugin type of this process plugin.
	*
	* @return the plugin type of this process plugin
	*/
	@Override
	public int getPluginType() {
		return _processPlugin.getPluginType();
	}

	/**
	* Returns the primary key of this process plugin.
	*
	* @return the primary key of this process plugin
	*/
	@Override
	public long getPrimaryKey() {
		return _processPlugin.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _processPlugin.getPrimaryKeyObj();
	}

	/**
	* Returns the process plugin ID of this process plugin.
	*
	* @return the process plugin ID of this process plugin
	*/
	@Override
	public long getProcessPluginId() {
		return _processPlugin.getProcessPluginId();
	}

	/**
	* Returns the sample data of this process plugin.
	*
	* @return the sample data of this process plugin
	*/
	@Override
	public String getSampleData() {
		return _processPlugin.getSampleData();
	}

	/**
	* Returns the sequence no of this process plugin.
	*
	* @return the sequence no of this process plugin
	*/
	@Override
	public String getSequenceNo() {
		return _processPlugin.getSequenceNo();
	}

	/**
	* Returns the service process ID of this process plugin.
	*
	* @return the service process ID of this process plugin
	*/
	@Override
	public long getServiceProcessId() {
		return _processPlugin.getServiceProcessId();
	}

	/**
	* Returns the step code of this process plugin.
	*
	* @return the step code of this process plugin
	*/
	@Override
	public String getStepCode() {
		return _processPlugin.getStepCode();
	}

	/**
	* Returns the user ID of this process plugin.
	*
	* @return the user ID of this process plugin
	*/
	@Override
	public long getUserId() {
		return _processPlugin.getUserId();
	}

	/**
	* Returns the user name of this process plugin.
	*
	* @return the user name of this process plugin
	*/
	@Override
	public String getUserName() {
		return _processPlugin.getUserName();
	}

	/**
	* Returns the user uuid of this process plugin.
	*
	* @return the user uuid of this process plugin
	*/
	@Override
	public String getUserUuid() {
		return _processPlugin.getUserUuid();
	}

	/**
	* Returns the uuid of this process plugin.
	*
	* @return the uuid of this process plugin
	*/
	@Override
	public String getUuid() {
		return _processPlugin.getUuid();
	}

	@Override
	public int hashCode() {
		return _processPlugin.hashCode();
	}

	/**
	* Returns <code>true</code> if this process plugin is auto run.
	*
	* @return <code>true</code> if this process plugin is auto run; <code>false</code> otherwise
	*/
	@Override
	public boolean isAutoRun() {
		return _processPlugin.isAutoRun();
	}

	@Override
	public boolean isCachedModel() {
		return _processPlugin.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _processPlugin.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _processPlugin.isNew();
	}

	@Override
	public void persist() {
		_processPlugin.persist();
	}

	/**
	* Sets whether this process plugin is auto run.
	*
	* @param autoRun the auto run of this process plugin
	*/
	@Override
	public void setAutoRun(boolean autoRun) {
		_processPlugin.setAutoRun(autoRun);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_processPlugin.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this process plugin.
	*
	* @param companyId the company ID of this process plugin
	*/
	@Override
	public void setCompanyId(long companyId) {
		_processPlugin.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this process plugin.
	*
	* @param createDate the create date of this process plugin
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_processPlugin.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_processPlugin.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_processPlugin.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_processPlugin.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this process plugin.
	*
	* @param groupId the group ID of this process plugin
	*/
	@Override
	public void setGroupId(long groupId) {
		_processPlugin.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this process plugin.
	*
	* @param modifiedDate the modified date of this process plugin
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_processPlugin.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_processPlugin.setNew(n);
	}

	/**
	* Sets the plugin form of this process plugin.
	*
	* @param pluginForm the plugin form of this process plugin
	*/
	@Override
	public void setPluginForm(String pluginForm) {
		_processPlugin.setPluginForm(pluginForm);
	}

	/**
	* Sets the plugin name of this process plugin.
	*
	* @param pluginName the plugin name of this process plugin
	*/
	@Override
	public void setPluginName(String pluginName) {
		_processPlugin.setPluginName(pluginName);
	}

	/**
	* Sets the plugin type of this process plugin.
	*
	* @param pluginType the plugin type of this process plugin
	*/
	@Override
	public void setPluginType(int pluginType) {
		_processPlugin.setPluginType(pluginType);
	}

	/**
	* Sets the primary key of this process plugin.
	*
	* @param primaryKey the primary key of this process plugin
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_processPlugin.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_processPlugin.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process plugin ID of this process plugin.
	*
	* @param processPluginId the process plugin ID of this process plugin
	*/
	@Override
	public void setProcessPluginId(long processPluginId) {
		_processPlugin.setProcessPluginId(processPluginId);
	}

	/**
	* Sets the sample data of this process plugin.
	*
	* @param sampleData the sample data of this process plugin
	*/
	@Override
	public void setSampleData(String sampleData) {
		_processPlugin.setSampleData(sampleData);
	}

	/**
	* Sets the sequence no of this process plugin.
	*
	* @param sequenceNo the sequence no of this process plugin
	*/
	@Override
	public void setSequenceNo(String sequenceNo) {
		_processPlugin.setSequenceNo(sequenceNo);
	}

	/**
	* Sets the service process ID of this process plugin.
	*
	* @param serviceProcessId the service process ID of this process plugin
	*/
	@Override
	public void setServiceProcessId(long serviceProcessId) {
		_processPlugin.setServiceProcessId(serviceProcessId);
	}

	/**
	* Sets the step code of this process plugin.
	*
	* @param stepCode the step code of this process plugin
	*/
	@Override
	public void setStepCode(String stepCode) {
		_processPlugin.setStepCode(stepCode);
	}

	/**
	* Sets the user ID of this process plugin.
	*
	* @param userId the user ID of this process plugin
	*/
	@Override
	public void setUserId(long userId) {
		_processPlugin.setUserId(userId);
	}

	/**
	* Sets the user name of this process plugin.
	*
	* @param userName the user name of this process plugin
	*/
	@Override
	public void setUserName(String userName) {
		_processPlugin.setUserName(userName);
	}

	/**
	* Sets the user uuid of this process plugin.
	*
	* @param userUuid the user uuid of this process plugin
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_processPlugin.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this process plugin.
	*
	* @param uuid the uuid of this process plugin
	*/
	@Override
	public void setUuid(String uuid) {
		_processPlugin.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProcessPlugin> toCacheModel() {
		return _processPlugin.toCacheModel();
	}

	@Override
	public ProcessPlugin toEscapedModel() {
		return new ProcessPluginWrapper(_processPlugin.toEscapedModel());
	}

	@Override
	public String toString() {
		return _processPlugin.toString();
	}

	@Override
	public ProcessPlugin toUnescapedModel() {
		return new ProcessPluginWrapper(_processPlugin.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _processPlugin.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessPluginWrapper)) {
			return false;
		}

		ProcessPluginWrapper processPluginWrapper = (ProcessPluginWrapper)obj;

		if (Objects.equals(_processPlugin, processPluginWrapper._processPlugin)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _processPlugin.getStagedModelType();
	}

	@Override
	public ProcessPlugin getWrappedModel() {
		return _processPlugin;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _processPlugin.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _processPlugin.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_processPlugin.resetOriginalValues();
	}

	private final ProcessPlugin _processPlugin;
}