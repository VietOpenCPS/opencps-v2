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
public class ContactSoap implements Serializable {
	public static ContactSoap toSoapModel(Contact model) {
		ContactSoap soapModel = new ContactSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setContactId(model.getContactId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFullName(model.getFullName());
		soapModel.setCompanyName(model.getCompanyName());
		soapModel.setTelNo(model.getTelNo());
		soapModel.setEmail(model.getEmail());
		soapModel.setUserMappingId(model.getUserMappingId());
		soapModel.setIsOrg(model.getIsOrg());
		soapModel.setShared(model.getShared());

		return soapModel;
	}

	public static ContactSoap[] toSoapModels(Contact[] models) {
		ContactSoap[] soapModels = new ContactSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContactSoap[][] toSoapModels(Contact[][] models) {
		ContactSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContactSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContactSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContactSoap[] toSoapModels(List<Contact> models) {
		List<ContactSoap> soapModels = new ArrayList<ContactSoap>(models.size());

		for (Contact model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContactSoap[soapModels.size()]);
	}

	public ContactSoap() {
	}

	public long getPrimaryKey() {
		return _contactId;
	}

	public void setPrimaryKey(long pk) {
		setContactId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getContactId() {
		return _contactId;
	}

	public void setContactId(long contactId) {
		_contactId = contactId;
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

	public String getFullName() {
		return _fullName;
	}

	public void setFullName(String fullName) {
		_fullName = fullName;
	}

	public String getCompanyName() {
		return _companyName;
	}

	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	public String getTelNo() {
		return _telNo;
	}

	public void setTelNo(String telNo) {
		_telNo = telNo;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public long getUserMappingId() {
		return _userMappingId;
	}

	public void setUserMappingId(long userMappingId) {
		_userMappingId = userMappingId;
	}

	public boolean getIsOrg() {
		return _isOrg;
	}

	public boolean isIsOrg() {
		return _isOrg;
	}

	public void setIsOrg(boolean isOrg) {
		_isOrg = isOrg;
	}

	public int getShared() {
		return _shared;
	}

	public void setShared(int shared) {
		_shared = shared;
	}

	private String _uuid;
	private long _contactId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _fullName;
	private String _companyName;
	private String _telNo;
	private String _email;
	private long _userMappingId;
	private boolean _isOrg;
	private int _shared;
}