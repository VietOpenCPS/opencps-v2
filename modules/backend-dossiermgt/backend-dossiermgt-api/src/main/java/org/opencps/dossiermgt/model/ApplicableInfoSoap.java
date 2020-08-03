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
public class ApplicableInfoSoap implements Serializable {
	public static ApplicableInfoSoap toSoapModel(ApplicableInfo model) {
		ApplicableInfoSoap soapModel = new ApplicableInfoSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setApplicableInfoId(model.getApplicableInfoId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setServiceCode(model.getServiceCode());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setServiceLevel(model.getServiceLevel());
		soapModel.setServiceConfigMappingId(model.getServiceConfigMappingId());

		return soapModel;
	}

	public static ApplicableInfoSoap[] toSoapModels(ApplicableInfo[] models) {
		ApplicableInfoSoap[] soapModels = new ApplicableInfoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ApplicableInfoSoap[][] toSoapModels(ApplicableInfo[][] models) {
		ApplicableInfoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ApplicableInfoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ApplicableInfoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ApplicableInfoSoap[] toSoapModels(List<ApplicableInfo> models) {
		List<ApplicableInfoSoap> soapModels = new ArrayList<ApplicableInfoSoap>(models.size());

		for (ApplicableInfo model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ApplicableInfoSoap[soapModels.size()]);
	}

	public ApplicableInfoSoap() {
	}

	public long getPrimaryKey() {
		return _applicableInfoId;
	}

	public void setPrimaryKey(long pk) {
		setApplicableInfoId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getApplicableInfoId() {
		return _applicableInfoId;
	}

	public void setApplicableInfoId(long applicableInfoId) {
		_applicableInfoId = applicableInfoId;
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

	public String getGovAgencyCode() {
		return _govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		_govAgencyCode = govAgencyCode;
	}

	public int getServiceLevel() {
		return _serviceLevel;
	}

	public void setServiceLevel(int serviceLevel) {
		_serviceLevel = serviceLevel;
	}

	public long getServiceConfigMappingId() {
		return _serviceConfigMappingId;
	}

	public void setServiceConfigMappingId(long serviceConfigMappingId) {
		_serviceConfigMappingId = serviceConfigMappingId;
	}

	private String _uuid;
	private long _applicableInfoId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _serviceCode;
	private String _govAgencyCode;
	private int _serviceLevel;
	private long _serviceConfigMappingId;
}