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
public class ResourceRoleSoap implements Serializable {
	public static ResourceRoleSoap toSoapModel(ResourceRole model) {
		ResourceRoleSoap soapModel = new ResourceRoleSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setResourceRoleId(model.getResourceRoleId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setRoleId(model.getRoleId());
		soapModel.setReadonly(model.getReadonly());

		return soapModel;
	}

	public static ResourceRoleSoap[] toSoapModels(ResourceRole[] models) {
		ResourceRoleSoap[] soapModels = new ResourceRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ResourceRoleSoap[][] toSoapModels(ResourceRole[][] models) {
		ResourceRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ResourceRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ResourceRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ResourceRoleSoap[] toSoapModels(List<ResourceRole> models) {
		List<ResourceRoleSoap> soapModels = new ArrayList<ResourceRoleSoap>(models.size());

		for (ResourceRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ResourceRoleSoap[soapModels.size()]);
	}

	public ResourceRoleSoap() {
	}

	public long getPrimaryKey() {
		return _resourceRoleId;
	}

	public void setPrimaryKey(long pk) {
		setResourceRoleId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getResourceRoleId() {
		return _resourceRoleId;
	}

	public void setResourceRoleId(long resourceRoleId) {
		_resourceRoleId = resourceRoleId;
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

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	public int getReadonly() {
		return _readonly;
	}

	public void setReadonly(int readonly) {
		_readonly = readonly;
	}

	private String _uuid;
	private long _resourceRoleId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _className;
	private String _classPK;
	private long _roleId;
	private int _readonly;
}