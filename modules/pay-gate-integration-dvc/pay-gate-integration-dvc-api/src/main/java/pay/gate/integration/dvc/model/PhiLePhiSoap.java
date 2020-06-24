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
public class PhiLePhiSoap implements Serializable {
	public static PhiLePhiSoap toSoapModel(PhiLePhi model) {
		PhiLePhiSoap soapModel = new PhiLePhiSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPhiLePhiId(model.getPhiLePhiId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLoaiPLP(model.getLoaiPLP());
		soapModel.setMaPLP(model.getMaPLP());
		soapModel.setTenPLP(model.getTenPLP());
		soapModel.setSoTien(model.getSoTien());
		soapModel.setServiceConfigMappingId(model.getServiceConfigMappingId());

		return soapModel;
	}

	public static PhiLePhiSoap[] toSoapModels(PhiLePhi[] models) {
		PhiLePhiSoap[] soapModels = new PhiLePhiSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PhiLePhiSoap[][] toSoapModels(PhiLePhi[][] models) {
		PhiLePhiSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PhiLePhiSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PhiLePhiSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PhiLePhiSoap[] toSoapModels(List<PhiLePhi> models) {
		List<PhiLePhiSoap> soapModels = new ArrayList<PhiLePhiSoap>(models.size());

		for (PhiLePhi model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PhiLePhiSoap[soapModels.size()]);
	}

	public PhiLePhiSoap() {
	}

	public long getPrimaryKey() {
		return _phiLePhiId;
	}

	public void setPrimaryKey(long pk) {
		setPhiLePhiId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPhiLePhiId() {
		return _phiLePhiId;
	}

	public void setPhiLePhiId(long phiLePhiId) {
		_phiLePhiId = phiLePhiId;
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

	public String getLoaiPLP() {
		return _loaiPLP;
	}

	public void setLoaiPLP(String loaiPLP) {
		_loaiPLP = loaiPLP;
	}

	public String getMaPLP() {
		return _maPLP;
	}

	public void setMaPLP(String maPLP) {
		_maPLP = maPLP;
	}

	public String getTenPLP() {
		return _tenPLP;
	}

	public void setTenPLP(String tenPLP) {
		_tenPLP = tenPLP;
	}

	public long getSoTien() {
		return _soTien;
	}

	public void setSoTien(long soTien) {
		_soTien = soTien;
	}

	public long getServiceConfigMappingId() {
		return _serviceConfigMappingId;
	}

	public void setServiceConfigMappingId(long serviceConfigMappingId) {
		_serviceConfigMappingId = serviceConfigMappingId;
	}

	private String _uuid;
	private long _phiLePhiId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _loaiPLP;
	private String _maPLP;
	private String _tenPLP;
	private long _soTien;
	private long _serviceConfigMappingId;
}