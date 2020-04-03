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
public class ServiceInfoMappingSoap implements Serializable {
	public static ServiceInfoMappingSoap toSoapModel(ServiceInfoMapping model) {
		ServiceInfoMappingSoap soapModel = new ServiceInfoMappingSoap();

		soapModel.setServiceInfoMappingId(model.getServiceInfoMappingId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setServiceCode(model.getServiceCode());
		soapModel.setServiceCodeDVCQG(model.getServiceCodeDVCQG());
		soapModel.setServiceNameDVCQG(model.getServiceNameDVCQG());
		soapModel.setSynced(model.getSynced());

		return soapModel;
	}

	public static ServiceInfoMappingSoap[] toSoapModels(
		ServiceInfoMapping[] models) {
		ServiceInfoMappingSoap[] soapModels = new ServiceInfoMappingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ServiceInfoMappingSoap[][] toSoapModels(
		ServiceInfoMapping[][] models) {
		ServiceInfoMappingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ServiceInfoMappingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ServiceInfoMappingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ServiceInfoMappingSoap[] toSoapModels(
		List<ServiceInfoMapping> models) {
		List<ServiceInfoMappingSoap> soapModels = new ArrayList<ServiceInfoMappingSoap>(models.size());

		for (ServiceInfoMapping model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ServiceInfoMappingSoap[soapModels.size()]);
	}

	public ServiceInfoMappingSoap() {
	}

	public long getPrimaryKey() {
		return _serviceInfoMappingId;
	}

	public void setPrimaryKey(long pk) {
		setServiceInfoMappingId(pk);
	}

	public long getServiceInfoMappingId() {
		return _serviceInfoMappingId;
	}

	public void setServiceInfoMappingId(long serviceInfoMappingId) {
		_serviceInfoMappingId = serviceInfoMappingId;
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

	public String getServiceCode() {
		return _serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		_serviceCode = serviceCode;
	}

	public String getServiceCodeDVCQG() {
		return _serviceCodeDVCQG;
	}

	public void setServiceCodeDVCQG(String serviceCodeDVCQG) {
		_serviceCodeDVCQG = serviceCodeDVCQG;
	}

	public String getServiceNameDVCQG() {
		return _serviceNameDVCQG;
	}

	public void setServiceNameDVCQG(String serviceNameDVCQG) {
		_serviceNameDVCQG = serviceNameDVCQG;
	}

	public int getSynced() {
		return _synced;
	}

	public void setSynced(int synced) {
		_synced = synced;
	}

	private long _serviceInfoMappingId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _serviceCode;
	private String _serviceCodeDVCQG;
	private String _serviceNameDVCQG;
	private int _synced;
}