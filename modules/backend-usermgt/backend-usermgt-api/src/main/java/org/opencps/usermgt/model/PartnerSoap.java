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

package org.opencps.usermgt.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Binhth
 * @generated
 */
@ProviderType
public class PartnerSoap implements Serializable {
	public static PartnerSoap toSoapModel(Partner model) {
		PartnerSoap soapModel = new PartnerSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPartnerId(model.getPartnerId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setAddress(model.getAddress());
		soapModel.setTelNo(model.getTelNo());
		soapModel.setFaxNo(model.getFaxNo());
		soapModel.setEmail(model.getEmail());
		soapModel.setWebsite(model.getWebsite());
		soapModel.setPartnerClass(model.getPartnerClass());
		soapModel.setAccountUserId(model.getAccountUserId());
		soapModel.setDocFileId(model.getDocFileId());

		return soapModel;
	}

	public static PartnerSoap[] toSoapModels(Partner[] models) {
		PartnerSoap[] soapModels = new PartnerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PartnerSoap[][] toSoapModels(Partner[][] models) {
		PartnerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PartnerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PartnerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PartnerSoap[] toSoapModels(List<Partner> models) {
		List<PartnerSoap> soapModels = new ArrayList<PartnerSoap>(models.size());

		for (Partner model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PartnerSoap[soapModels.size()]);
	}

	public PartnerSoap() {
	}

	public long getPrimaryKey() {
		return _partnerId;
	}

	public void setPrimaryKey(long pk) {
		setPartnerId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPartnerId() {
		return _partnerId;
	}

	public void setPartnerId(long partnerId) {
		_partnerId = partnerId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public String getTelNo() {
		return _telNo;
	}

	public void setTelNo(String telNo) {
		_telNo = telNo;
	}

	public String getFaxNo() {
		return _faxNo;
	}

	public void setFaxNo(String faxNo) {
		_faxNo = faxNo;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getWebsite() {
		return _website;
	}

	public void setWebsite(String website) {
		_website = website;
	}

	public int getPartnerClass() {
		return _partnerClass;
	}

	public void setPartnerClass(int partnerClass) {
		_partnerClass = partnerClass;
	}

	public long getAccountUserId() {
		return _accountUserId;
	}

	public void setAccountUserId(long accountUserId) {
		_accountUserId = accountUserId;
	}

	public String getDocFileId() {
		return _docFileId;
	}

	public void setDocFileId(String docFileId) {
		_docFileId = docFileId;
	}

	private String _uuid;
	private long _partnerId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _address;
	private String _telNo;
	private String _faxNo;
	private String _email;
	private String _website;
	private int _partnerClass;
	private long _accountUserId;
	private String _docFileId;
}