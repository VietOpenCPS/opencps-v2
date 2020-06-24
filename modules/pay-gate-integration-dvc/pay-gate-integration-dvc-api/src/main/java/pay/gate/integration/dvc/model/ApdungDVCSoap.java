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

package pay.gate.integration.dvc.model;

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
public class ApdungDVCSoap implements Serializable {
	public static ApdungDVCSoap toSoapModel(ApdungDVC model) {
		ApdungDVCSoap soapModel = new ApdungDVCSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setApdungDVCId(model.getApdungDVCId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setMaTTHC(model.getMaTTHC());
		soapModel.setMaCQTH(model.getMaCQTH());
		soapModel.setMucdo(model.getMucdo());
		soapModel.setServiceConfigMappingId(model.getServiceConfigMappingId());

		return soapModel;
	}

	public static ApdungDVCSoap[] toSoapModels(ApdungDVC[] models) {
		ApdungDVCSoap[] soapModels = new ApdungDVCSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ApdungDVCSoap[][] toSoapModels(ApdungDVC[][] models) {
		ApdungDVCSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ApdungDVCSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ApdungDVCSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ApdungDVCSoap[] toSoapModels(List<ApdungDVC> models) {
		List<ApdungDVCSoap> soapModels = new ArrayList<ApdungDVCSoap>(models.size());

		for (ApdungDVC model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ApdungDVCSoap[soapModels.size()]);
	}

	public ApdungDVCSoap() {
	}

	public long getPrimaryKey() {
		return _apdungDVCId;
	}

	public void setPrimaryKey(long pk) {
		setApdungDVCId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getApdungDVCId() {
		return _apdungDVCId;
	}

	public void setApdungDVCId(long apdungDVCId) {
		_apdungDVCId = apdungDVCId;
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

	public String getMaTTHC() {
		return _maTTHC;
	}

	public void setMaTTHC(String maTTHC) {
		_maTTHC = maTTHC;
	}

	public String getMaCQTH() {
		return _maCQTH;
	}

	public void setMaCQTH(String maCQTH) {
		_maCQTH = maCQTH;
	}

	public int getMucdo() {
		return _mucdo;
	}

	public void setMucdo(int mucdo) {
		_mucdo = mucdo;
	}

	public long getServiceConfigMappingId() {
		return _serviceConfigMappingId;
	}

	public void setServiceConfigMappingId(long serviceConfigMappingId) {
		_serviceConfigMappingId = serviceConfigMappingId;
	}

	private String _uuid;
	private long _apdungDVCId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _maTTHC;
	private String _maCQTH;
	private int _mucdo;
	private long _serviceConfigMappingId;
}