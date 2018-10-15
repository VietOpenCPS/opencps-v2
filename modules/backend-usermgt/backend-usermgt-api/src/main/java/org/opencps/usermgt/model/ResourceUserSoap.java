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
 * @author khoavu
 * @generated
 */
@ProviderType
public class ResourceUserSoap implements Serializable {
	public static ResourceUserSoap toSoapModel(ResourceUser model) {
		ResourceUserSoap soapModel = new ResourceUserSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setResourceUserId(model.getResourceUserId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setToUserId(model.getToUserId());
		soapModel.setFullname(model.getFullname());
		soapModel.setEmail(model.getEmail());
		soapModel.setReadonly(model.isReadonly());

		return soapModel;
	}

	public static ResourceUserSoap[] toSoapModels(ResourceUser[] models) {
		ResourceUserSoap[] soapModels = new ResourceUserSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ResourceUserSoap[][] toSoapModels(ResourceUser[][] models) {
		ResourceUserSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ResourceUserSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ResourceUserSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ResourceUserSoap[] toSoapModels(List<ResourceUser> models) {
		List<ResourceUserSoap> soapModels = new ArrayList<ResourceUserSoap>(models.size());

		for (ResourceUser model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ResourceUserSoap[soapModels.size()]);
	}

	public ResourceUserSoap() {
	}

	public long getPrimaryKey() {
		return _resourceUserId;
	}

	public void setPrimaryKey(long pk) {
		setResourceUserId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getResourceUserId() {
		return _resourceUserId;
	}

	public void setResourceUserId(long resourceUserId) {
		_resourceUserId = resourceUserId;
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

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public String getClassPK() {
		return _classPK;
	}

	public void setClassPK(String classPK) {
		_classPK = classPK;
	}

	public long getToUserId() {
		return _toUserId;
	}

	public void setToUserId(long toUserId) {
		_toUserId = toUserId;
	}

	public String getFullname() {
		return _fullname;
	}

	public void setFullname(String fullname) {
		_fullname = fullname;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public boolean getReadonly() {
		return _readonly;
	}

	public boolean isReadonly() {
		return _readonly;
	}

	public void setReadonly(boolean readonly) {
		_readonly = readonly;
	}

	private String _uuid;
	private long _resourceUserId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _className;
	private String _classPK;
	private long _toUserId;
	private String _fullname;
	private String _email;
	private boolean _readonly;
}