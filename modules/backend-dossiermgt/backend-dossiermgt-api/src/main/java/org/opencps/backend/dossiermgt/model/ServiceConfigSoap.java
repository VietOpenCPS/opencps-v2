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

package org.opencps.backend.dossiermgt.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author huymq
 * @generated
 */
@ProviderType
public class ServiceConfigSoap implements Serializable {
	public static ServiceConfigSoap toSoapModel(ServiceConfig model) {
		ServiceConfigSoap soapModel = new ServiceConfigSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setServiceConfigId(model.getServiceConfigId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setServiceInfoId(model.getServiceInfoId());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setGovAgencyName(model.getGovAgencyName());
		soapModel.setServiceInstruction(model.getServiceInstruction());
		soapModel.setServiceLevel(model.getServiceLevel());
		soapModel.setServiceUrl(model.getServiceUrl());
		soapModel.setAuthentication(model.getAuthentication());

		return soapModel;
	}

	public static ServiceConfigSoap[] toSoapModels(ServiceConfig[] models) {
		ServiceConfigSoap[] soapModels = new ServiceConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ServiceConfigSoap[][] toSoapModels(ServiceConfig[][] models) {
		ServiceConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ServiceConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ServiceConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ServiceConfigSoap[] toSoapModels(List<ServiceConfig> models) {
		List<ServiceConfigSoap> soapModels = new ArrayList<ServiceConfigSoap>(models.size());

		for (ServiceConfig model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ServiceConfigSoap[soapModels.size()]);
	}

	public ServiceConfigSoap() {
	}

	public long getPrimaryKey() {
		return _serviceConfigId;
	}

	public void setPrimaryKey(long pk) {
		setServiceConfigId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getServiceConfigId() {
		return _serviceConfigId;
	}

	public void setServiceConfigId(long serviceConfigId) {
		_serviceConfigId = serviceConfigId;
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

	public long getServiceInfoId() {
		return _serviceInfoId;
	}

	public void setServiceInfoId(long serviceInfoId) {
		_serviceInfoId = serviceInfoId;
	}

	public String getGovAgencyCode() {
		return _govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		_govAgencyCode = govAgencyCode;
	}

	public String getGovAgencyName() {
		return _govAgencyName;
	}

	public void setGovAgencyName(String govAgencyName) {
		_govAgencyName = govAgencyName;
	}

	public String getServiceInstruction() {
		return _serviceInstruction;
	}

	public void setServiceInstruction(String serviceInstruction) {
		_serviceInstruction = serviceInstruction;
	}

	public int getServiceLevel() {
		return _serviceLevel;
	}

	public void setServiceLevel(int serviceLevel) {
		_serviceLevel = serviceLevel;
	}

	public String getServiceUrl() {
		return _serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		_serviceUrl = serviceUrl;
	}

	public int getAuthentication() {
		return _authentication;
	}

	public void setAuthentication(int authentication) {
		_authentication = authentication;
	}

	private String _uuid;
	private long _serviceConfigId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _serviceInfoId;
	private String _govAgencyCode;
	private String _govAgencyName;
	private String _serviceInstruction;
	private int _serviceLevel;
	private String _serviceUrl;
	private int _authentication;
}