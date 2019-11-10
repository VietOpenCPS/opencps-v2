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

package org.opencps.communication.model;

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
 * This class is a wrapper for {@link ServerConfig}.
 * </p>
 *
 * @author khoavd
 * @see ServerConfig
 * @generated
 */
@ProviderType
public class ServerConfigWrapper implements ServerConfig,
	ModelWrapper<ServerConfig> {
	public ServerConfigWrapper(ServerConfig serverConfig) {
		_serverConfig = serverConfig;
	}

	@Override
	public Class<?> getModelClass() {
		return ServerConfig.class;
	}

	@Override
	public String getModelClassName() {
		return ServerConfig.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("serverConfigId", getServerConfigId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("serverNo", getServerNo());
		attributes.put("serverName", getServerName());
		attributes.put("protocol", getProtocol());
		attributes.put("configs", getConfigs());
		attributes.put("lastSync", getLastSync());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long serverConfigId = (Long)attributes.get("serverConfigId");

		if (serverConfigId != null) {
			setServerConfigId(serverConfigId);
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

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}

		String serverNo = (String)attributes.get("serverNo");

		if (serverNo != null) {
			setServerNo(serverNo);
		}

		String serverName = (String)attributes.get("serverName");

		if (serverName != null) {
			setServerName(serverName);
		}

		String protocol = (String)attributes.get("protocol");

		if (protocol != null) {
			setProtocol(protocol);
		}

		String configs = (String)attributes.get("configs");

		if (configs != null) {
			setConfigs(configs);
		}

		Date lastSync = (Date)attributes.get("lastSync");

		if (lastSync != null) {
			setLastSync(lastSync);
		}
	}

	@Override
	public Object clone() {
		return new ServerConfigWrapper((ServerConfig)_serverConfig.clone());
	}

	@Override
	public int compareTo(ServerConfig serverConfig) {
		return _serverConfig.compareTo(serverConfig);
	}

	/**
	* Returns the company ID of this server config.
	*
	* @return the company ID of this server config
	*/
	@Override
	public long getCompanyId() {
		return _serverConfig.getCompanyId();
	}

	/**
	* Returns the configs of this server config.
	*
	* @return the configs of this server config
	*/
	@Override
	public String getConfigs() {
		return _serverConfig.getConfigs();
	}

	/**
	* Returns the create date of this server config.
	*
	* @return the create date of this server config
	*/
	@Override
	public Date getCreateDate() {
		return _serverConfig.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _serverConfig.getExpandoBridge();
	}

	/**
	* Returns the gov agency code of this server config.
	*
	* @return the gov agency code of this server config
	*/
	@Override
	public String getGovAgencyCode() {
		return _serverConfig.getGovAgencyCode();
	}

	/**
	* Returns the group ID of this server config.
	*
	* @return the group ID of this server config
	*/
	@Override
	public long getGroupId() {
		return _serverConfig.getGroupId();
	}

	/**
	* Returns the last sync of this server config.
	*
	* @return the last sync of this server config
	*/
	@Override
	public Date getLastSync() {
		return _serverConfig.getLastSync();
	}

	/**
	* Returns the modified date of this server config.
	*
	* @return the modified date of this server config
	*/
	@Override
	public Date getModifiedDate() {
		return _serverConfig.getModifiedDate();
	}

	/**
	* Returns the primary key of this server config.
	*
	* @return the primary key of this server config
	*/
	@Override
	public long getPrimaryKey() {
		return _serverConfig.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serverConfig.getPrimaryKeyObj();
	}

	/**
	* Returns the protocol of this server config.
	*
	* @return the protocol of this server config
	*/
	@Override
	public String getProtocol() {
		return _serverConfig.getProtocol();
	}

	/**
	* Returns the server config ID of this server config.
	*
	* @return the server config ID of this server config
	*/
	@Override
	public long getServerConfigId() {
		return _serverConfig.getServerConfigId();
	}

	/**
	* Returns the server name of this server config.
	*
	* @return the server name of this server config
	*/
	@Override
	public String getServerName() {
		return _serverConfig.getServerName();
	}

	/**
	* Returns the server no of this server config.
	*
	* @return the server no of this server config
	*/
	@Override
	public String getServerNo() {
		return _serverConfig.getServerNo();
	}

	/**
	* Returns the user ID of this server config.
	*
	* @return the user ID of this server config
	*/
	@Override
	public long getUserId() {
		return _serverConfig.getUserId();
	}

	/**
	* Returns the user name of this server config.
	*
	* @return the user name of this server config
	*/
	@Override
	public String getUserName() {
		return _serverConfig.getUserName();
	}

	/**
	* Returns the user uuid of this server config.
	*
	* @return the user uuid of this server config
	*/
	@Override
	public String getUserUuid() {
		return _serverConfig.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _serverConfig.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _serverConfig.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _serverConfig.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _serverConfig.isNew();
	}

	@Override
	public void persist() {
		_serverConfig.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_serverConfig.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this server config.
	*
	* @param companyId the company ID of this server config
	*/
	@Override
	public void setCompanyId(long companyId) {
		_serverConfig.setCompanyId(companyId);
	}

	/**
	* Sets the configs of this server config.
	*
	* @param configs the configs of this server config
	*/
	@Override
	public void setConfigs(String configs) {
		_serverConfig.setConfigs(configs);
	}

	/**
	* Sets the create date of this server config.
	*
	* @param createDate the create date of this server config
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_serverConfig.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_serverConfig.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_serverConfig.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_serverConfig.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gov agency code of this server config.
	*
	* @param govAgencyCode the gov agency code of this server config
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_serverConfig.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the group ID of this server config.
	*
	* @param groupId the group ID of this server config
	*/
	@Override
	public void setGroupId(long groupId) {
		_serverConfig.setGroupId(groupId);
	}

	/**
	* Sets the last sync of this server config.
	*
	* @param lastSync the last sync of this server config
	*/
	@Override
	public void setLastSync(Date lastSync) {
		_serverConfig.setLastSync(lastSync);
	}

	/**
	* Sets the modified date of this server config.
	*
	* @param modifiedDate the modified date of this server config
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_serverConfig.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_serverConfig.setNew(n);
	}

	/**
	* Sets the primary key of this server config.
	*
	* @param primaryKey the primary key of this server config
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_serverConfig.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_serverConfig.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the protocol of this server config.
	*
	* @param protocol the protocol of this server config
	*/
	@Override
	public void setProtocol(String protocol) {
		_serverConfig.setProtocol(protocol);
	}

	/**
	* Sets the server config ID of this server config.
	*
	* @param serverConfigId the server config ID of this server config
	*/
	@Override
	public void setServerConfigId(long serverConfigId) {
		_serverConfig.setServerConfigId(serverConfigId);
	}

	/**
	* Sets the server name of this server config.
	*
	* @param serverName the server name of this server config
	*/
	@Override
	public void setServerName(String serverName) {
		_serverConfig.setServerName(serverName);
	}

	/**
	* Sets the server no of this server config.
	*
	* @param serverNo the server no of this server config
	*/
	@Override
	public void setServerNo(String serverNo) {
		_serverConfig.setServerNo(serverNo);
	}

	/**
	* Sets the user ID of this server config.
	*
	* @param userId the user ID of this server config
	*/
	@Override
	public void setUserId(long userId) {
		_serverConfig.setUserId(userId);
	}

	/**
	* Sets the user name of this server config.
	*
	* @param userName the user name of this server config
	*/
	@Override
	public void setUserName(String userName) {
		_serverConfig.setUserName(userName);
	}

	/**
	* Sets the user uuid of this server config.
	*
	* @param userUuid the user uuid of this server config
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_serverConfig.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ServerConfig> toCacheModel() {
		return _serverConfig.toCacheModel();
	}

	@Override
	public ServerConfig toEscapedModel() {
		return new ServerConfigWrapper(_serverConfig.toEscapedModel());
	}

	@Override
	public String toString() {
		return _serverConfig.toString();
	}

	@Override
	public ServerConfig toUnescapedModel() {
		return new ServerConfigWrapper(_serverConfig.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _serverConfig.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServerConfigWrapper)) {
			return false;
		}

		ServerConfigWrapper serverConfigWrapper = (ServerConfigWrapper)obj;

		if (Objects.equals(_serverConfig, serverConfigWrapper._serverConfig)) {
			return true;
		}

		return false;
	}

	@Override
	public ServerConfig getWrappedModel() {
		return _serverConfig;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _serverConfig.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _serverConfig.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_serverConfig.resetOriginalValues();
	}

	private final ServerConfig _serverConfig;
}