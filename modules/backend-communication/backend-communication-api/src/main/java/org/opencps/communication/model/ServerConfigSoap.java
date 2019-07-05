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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavd
 * @generated
 */
@ProviderType
public class ServerConfigSoap implements Serializable {
	public static ServerConfigSoap toSoapModel(ServerConfig model) {
		ServerConfigSoap soapModel = new ServerConfigSoap();

		soapModel.setServerConfigId(model.getServerConfigId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setServerNo(model.getServerNo());
		soapModel.setServerName(model.getServerName());
		soapModel.setProtocol(model.getProtocol());
		soapModel.setConfigs(model.getConfigs());
		soapModel.setLastSync(model.getLastSync());

		return soapModel;
	}

	public static ServerConfigSoap[] toSoapModels(ServerConfig[] models) {
		ServerConfigSoap[] soapModels = new ServerConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ServerConfigSoap[][] toSoapModels(ServerConfig[][] models) {
		ServerConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ServerConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ServerConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ServerConfigSoap[] toSoapModels(List<ServerConfig> models) {
		List<ServerConfigSoap> soapModels = new ArrayList<ServerConfigSoap>(models.size());

		for (ServerConfig model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ServerConfigSoap[soapModels.size()]);
	}

	public ServerConfigSoap() {
	}

	public long getPrimaryKey() {
		return _serverConfigId;
	}

	public void setPrimaryKey(long pk) {
		setServerConfigId(pk);
	}

	public long getServerConfigId() {
		return _serverConfigId;
	}

	public void setServerConfigId(long serverConfigId) {
		_serverConfigId = serverConfigId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getGovAgencyCode() {
		return _govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		_govAgencyCode = govAgencyCode;
	}

	public String getServerNo() {
		return _serverNo;
	}

	public void setServerNo(String serverNo) {
		_serverNo = serverNo;
	}

	public String getServerName() {
		return _serverName;
	}

	public void setServerName(String serverName) {
		_serverName = serverName;
	}

	public String getProtocol() {
		return _protocol;
	}

	public void setProtocol(String protocol) {
		_protocol = protocol;
	}

	public String getConfigs() {
		return _configs;
	}

	public void setConfigs(String configs) {
		_configs = configs;
	}

	public Date getLastSync() {
		return _lastSync;
	}

	public void setLastSync(Date lastSync) {
		_lastSync = lastSync;
	}

	private long _serverConfigId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _govAgencyCode;
	private String _serverNo;
	private String _serverName;
	private String _protocol;
	private String _configs;
	private Date _lastSync;
}