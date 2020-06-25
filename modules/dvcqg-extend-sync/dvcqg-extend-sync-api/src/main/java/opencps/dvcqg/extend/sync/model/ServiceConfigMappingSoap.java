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

package opencps.dvcqg.extend.sync.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ServiceConfigMappingSoap implements Serializable {
	public static ServiceConfigMappingSoap toSoapModel(
		ServiceConfigMapping model) {
		ServiceConfigMappingSoap soapModel = new ServiceConfigMappingSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setServiceConfigMappingId(model.getServiceConfigMappingId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setServiceConfigCode(model.getServiceConfigCode());
		soapModel.setServiceConfigName(model.getServiceConfigName());
		soapModel.setServiceCode(model.getServiceCode());
		soapModel.setServiceName(model.getServiceName());
		soapModel.setGovAgencyName(model.getGovAgencyName());
		soapModel.setDomainName(model.getDomainName());

		return soapModel;
	}

	public static ServiceConfigMappingSoap[] toSoapModels(
		ServiceConfigMapping[] models) {
		ServiceConfigMappingSoap[] soapModels = new ServiceConfigMappingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ServiceConfigMappingSoap[][] toSoapModels(
		ServiceConfigMapping[][] models) {
		ServiceConfigMappingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ServiceConfigMappingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ServiceConfigMappingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ServiceConfigMappingSoap[] toSoapModels(
		List<ServiceConfigMapping> models) {
		List<ServiceConfigMappingSoap> soapModels = new ArrayList<ServiceConfigMappingSoap>(models.size());

		for (ServiceConfigMapping model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ServiceConfigMappingSoap[soapModels.size()]);
	}

	public ServiceConfigMappingSoap() {
	}

	public long getPrimaryKey() {
		return _serviceConfigMappingId;
	}

	public void setPrimaryKey(long pk) {
		setServiceConfigMappingId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getServiceConfigMappingId() {
		return _serviceConfigMappingId;
	}

	public void setServiceConfigMappingId(long serviceConfigMappingId) {
		_serviceConfigMappingId = serviceConfigMappingId;
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

	public String getServiceConfigCode() {
		return _serviceConfigCode;
	}

	public void setServiceConfigCode(String serviceConfigCode) {
		_serviceConfigCode = serviceConfigCode;
	}

	public String getServiceConfigName() {
		return _serviceConfigName;
	}

	public void setServiceConfigName(String serviceConfigName) {
		_serviceConfigName = serviceConfigName;
	}

	public String getServiceCode() {
		return _serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		_serviceCode = serviceCode;
	}

	public String getServiceName() {
		return _serviceName;
	}

	public void setServiceName(String serviceName) {
		_serviceName = serviceName;
	}

	public String getGovAgencyName() {
		return _govAgencyName;
	}

	public void setGovAgencyName(String govAgencyName) {
		_govAgencyName = govAgencyName;
	}

	public String getDomainName() {
		return _domainName;
	}

	public void setDomainName(String domainName) {
		_domainName = domainName;
	}

	private String _uuid;
	private long _serviceConfigMappingId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _serviceConfigCode;
	private String _serviceConfigName;
	private String _serviceCode;
	private String _serviceName;
	private String _govAgencyName;
	private String _domainName;
}