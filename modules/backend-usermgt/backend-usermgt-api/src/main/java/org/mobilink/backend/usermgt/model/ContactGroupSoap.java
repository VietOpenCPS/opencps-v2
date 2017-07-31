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

package org.mobilink.backend.usermgt.model;

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
public class ContactGroupSoap implements Serializable {
	public static ContactGroupSoap toSoapModel(ContactGroup model) {
		ContactGroupSoap soapModel = new ContactGroupSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setContactGroupId(model.getContactGroupId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setGroupName(model.getGroupName());
		soapModel.setContactList(model.getContactList());
		soapModel.setShared(model.getShared());

		return soapModel;
	}

	public static ContactGroupSoap[] toSoapModels(ContactGroup[] models) {
		ContactGroupSoap[] soapModels = new ContactGroupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContactGroupSoap[][] toSoapModels(ContactGroup[][] models) {
		ContactGroupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContactGroupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContactGroupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContactGroupSoap[] toSoapModels(List<ContactGroup> models) {
		List<ContactGroupSoap> soapModels = new ArrayList<ContactGroupSoap>(models.size());

		for (ContactGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContactGroupSoap[soapModels.size()]);
	}

	public ContactGroupSoap() {
	}

	public long getPrimaryKey() {
		return _contactGroupId;
	}

	public void setPrimaryKey(long pk) {
		setContactGroupId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getContactGroupId() {
		return _contactGroupId;
	}

	public void setContactGroupId(long contactGroupId) {
		_contactGroupId = contactGroupId;
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

	public String getGroupName() {
		return _groupName;
	}

	public void setGroupName(String groupName) {
		_groupName = groupName;
	}

	public String getContactList() {
		return _contactList;
	}

	public void setContactList(String contactList) {
		_contactList = contactList;
	}

	public int getShared() {
		return _shared;
	}

	public void setShared(int shared) {
		_shared = shared;
	}

	private String _uuid;
	private long _contactGroupId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _groupName;
	private String _contactList;
	private int _shared;
}