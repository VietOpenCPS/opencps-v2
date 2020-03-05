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
 * This class is a wrapper for {@link ConfigCounter}.
 * </p>
 *
 * @author huymq
 * @see ConfigCounter
 * @generated
 */
@ProviderType
public class ConfigCounterWrapper implements ConfigCounter,
	ModelWrapper<ConfigCounter> {
	public ConfigCounterWrapper(ConfigCounter configCounter) {
		_configCounter = configCounter;
	}

	@Override
	public Class<?> getModelClass() {
		return ConfigCounter.class;
	}

	@Override
	public String getModelClassName() {
		return ConfigCounter.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("configCounterId", getConfigCounterId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("counterCode", getCounterCode());
		attributes.put("patternCode", getPatternCode());
		attributes.put("startCounter", getStartCounter());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long configCounterId = (Long)attributes.get("configCounterId");

		if (configCounterId != null) {
			setConfigCounterId(configCounterId);
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

		String counterCode = (String)attributes.get("counterCode");

		if (counterCode != null) {
			setCounterCode(counterCode);
		}

		String patternCode = (String)attributes.get("patternCode");

		if (patternCode != null) {
			setPatternCode(patternCode);
		}

		Integer startCounter = (Integer)attributes.get("startCounter");

		if (startCounter != null) {
			setStartCounter(startCounter);
		}
	}

	@Override
	public Object clone() {
		return new ConfigCounterWrapper((ConfigCounter)_configCounter.clone());
	}

	@Override
	public int compareTo(ConfigCounter configCounter) {
		return _configCounter.compareTo(configCounter);
	}

	/**
	* Returns the company ID of this config counter.
	*
	* @return the company ID of this config counter
	*/
	@Override
	public long getCompanyId() {
		return _configCounter.getCompanyId();
	}

	/**
	* Returns the config counter ID of this config counter.
	*
	* @return the config counter ID of this config counter
	*/
	@Override
	public long getConfigCounterId() {
		return _configCounter.getConfigCounterId();
	}

	/**
	* Returns the counter code of this config counter.
	*
	* @return the counter code of this config counter
	*/
	@Override
	public String getCounterCode() {
		return _configCounter.getCounterCode();
	}

	/**
	* Returns the create date of this config counter.
	*
	* @return the create date of this config counter
	*/
	@Override
	public Date getCreateDate() {
		return _configCounter.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _configCounter.getExpandoBridge();
	}

	/**
	* Returns the group ID of this config counter.
	*
	* @return the group ID of this config counter
	*/
	@Override
	public long getGroupId() {
		return _configCounter.getGroupId();
	}

	/**
	* Returns the modified date of this config counter.
	*
	* @return the modified date of this config counter
	*/
	@Override
	public Date getModifiedDate() {
		return _configCounter.getModifiedDate();
	}

	/**
	* Returns the pattern code of this config counter.
	*
	* @return the pattern code of this config counter
	*/
	@Override
	public String getPatternCode() {
		return _configCounter.getPatternCode();
	}

	/**
	* Returns the primary key of this config counter.
	*
	* @return the primary key of this config counter
	*/
	@Override
	public long getPrimaryKey() {
		return _configCounter.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _configCounter.getPrimaryKeyObj();
	}

	/**
	* Returns the start counter of this config counter.
	*
	* @return the start counter of this config counter
	*/
	@Override
	public int getStartCounter() {
		return _configCounter.getStartCounter();
	}

	/**
	* Returns the user ID of this config counter.
	*
	* @return the user ID of this config counter
	*/
	@Override
	public long getUserId() {
		return _configCounter.getUserId();
	}

	/**
	* Returns the user name of this config counter.
	*
	* @return the user name of this config counter
	*/
	@Override
	public String getUserName() {
		return _configCounter.getUserName();
	}

	/**
	* Returns the user uuid of this config counter.
	*
	* @return the user uuid of this config counter
	*/
	@Override
	public String getUserUuid() {
		return _configCounter.getUserUuid();
	}

	/**
	* Returns the uuid of this config counter.
	*
	* @return the uuid of this config counter
	*/
	@Override
	public String getUuid() {
		return _configCounter.getUuid();
	}

	@Override
	public int hashCode() {
		return _configCounter.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _configCounter.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _configCounter.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _configCounter.isNew();
	}

	@Override
	public void persist() {
		_configCounter.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_configCounter.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this config counter.
	*
	* @param companyId the company ID of this config counter
	*/
	@Override
	public void setCompanyId(long companyId) {
		_configCounter.setCompanyId(companyId);
	}

	/**
	* Sets the config counter ID of this config counter.
	*
	* @param configCounterId the config counter ID of this config counter
	*/
	@Override
	public void setConfigCounterId(long configCounterId) {
		_configCounter.setConfigCounterId(configCounterId);
	}

	/**
	* Sets the counter code of this config counter.
	*
	* @param counterCode the counter code of this config counter
	*/
	@Override
	public void setCounterCode(String counterCode) {
		_configCounter.setCounterCode(counterCode);
	}

	/**
	* Sets the create date of this config counter.
	*
	* @param createDate the create date of this config counter
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_configCounter.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_configCounter.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_configCounter.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_configCounter.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this config counter.
	*
	* @param groupId the group ID of this config counter
	*/
	@Override
	public void setGroupId(long groupId) {
		_configCounter.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this config counter.
	*
	* @param modifiedDate the modified date of this config counter
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_configCounter.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_configCounter.setNew(n);
	}

	/**
	* Sets the pattern code of this config counter.
	*
	* @param patternCode the pattern code of this config counter
	*/
	@Override
	public void setPatternCode(String patternCode) {
		_configCounter.setPatternCode(patternCode);
	}

	/**
	* Sets the primary key of this config counter.
	*
	* @param primaryKey the primary key of this config counter
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_configCounter.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_configCounter.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start counter of this config counter.
	*
	* @param startCounter the start counter of this config counter
	*/
	@Override
	public void setStartCounter(int startCounter) {
		_configCounter.setStartCounter(startCounter);
	}

	/**
	* Sets the user ID of this config counter.
	*
	* @param userId the user ID of this config counter
	*/
	@Override
	public void setUserId(long userId) {
		_configCounter.setUserId(userId);
	}

	/**
	* Sets the user name of this config counter.
	*
	* @param userName the user name of this config counter
	*/
	@Override
	public void setUserName(String userName) {
		_configCounter.setUserName(userName);
	}

	/**
	* Sets the user uuid of this config counter.
	*
	* @param userUuid the user uuid of this config counter
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_configCounter.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this config counter.
	*
	* @param uuid the uuid of this config counter
	*/
	@Override
	public void setUuid(String uuid) {
		_configCounter.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ConfigCounter> toCacheModel() {
		return _configCounter.toCacheModel();
	}

	@Override
	public ConfigCounter toEscapedModel() {
		return new ConfigCounterWrapper(_configCounter.toEscapedModel());
	}

	@Override
	public String toString() {
		return _configCounter.toString();
	}

	@Override
	public ConfigCounter toUnescapedModel() {
		return new ConfigCounterWrapper(_configCounter.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _configCounter.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ConfigCounterWrapper)) {
			return false;
		}

		ConfigCounterWrapper configCounterWrapper = (ConfigCounterWrapper)obj;

		if (Objects.equals(_configCounter, configCounterWrapper._configCounter)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _configCounter.getStagedModelType();
	}

	@Override
	public ConfigCounter getWrappedModel() {
		return _configCounter;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _configCounter.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _configCounter.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_configCounter.resetOriginalValues();
	}

	private final ConfigCounter _configCounter;
}