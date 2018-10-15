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
public class VisibilitySoap implements Serializable {
	public static VisibilitySoap toSoapModel(Visibility model) {
		VisibilitySoap soapModel = new VisibilitySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setVisibilityId(model.getVisibilityId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setVisibility(model.getVisibility());
		soapModel.setSecurity(model.getSecurity());

		return soapModel;
	}

	public static VisibilitySoap[] toSoapModels(Visibility[] models) {
		VisibilitySoap[] soapModels = new VisibilitySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VisibilitySoap[][] toSoapModels(Visibility[][] models) {
		VisibilitySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VisibilitySoap[models.length][models[0].length];
		}
		else {
			soapModels = new VisibilitySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VisibilitySoap[] toSoapModels(List<Visibility> models) {
		List<VisibilitySoap> soapModels = new ArrayList<VisibilitySoap>(models.size());

		for (Visibility model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VisibilitySoap[soapModels.size()]);
	}

	public VisibilitySoap() {
	}

	public long getPrimaryKey() {
		return _visibilityId;
	}

	public void setPrimaryKey(long pk) {
		setVisibilityId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getVisibilityId() {
		return _visibilityId;
	}

	public void setVisibilityId(long visibilityId) {
		_visibilityId = visibilityId;
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

	public int getVisibility() {
		return _visibility;
	}

	public void setVisibility(int visibility) {
		_visibility = visibility;
	}

	public String getSecurity() {
		return _security;
	}

	public void setSecurity(String security) {
		_security = security;
	}

	private String _uuid;
	private long _visibilityId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _className;
	private String _classPK;
	private int _visibility;
	private String _security;
}