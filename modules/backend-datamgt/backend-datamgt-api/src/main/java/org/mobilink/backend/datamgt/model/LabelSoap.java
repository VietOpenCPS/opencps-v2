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

package org.mobilink.backend.datamgt.model;

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
public class LabelSoap implements Serializable {
	public static LabelSoap toSoapModel(Label model) {
		LabelSoap soapModel = new LabelSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setLabelId(model.getLabelId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setColor(model.getColor());
		soapModel.setScope(model.getScope());

		return soapModel;
	}

	public static LabelSoap[] toSoapModels(Label[] models) {
		LabelSoap[] soapModels = new LabelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LabelSoap[][] toSoapModels(Label[][] models) {
		LabelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LabelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LabelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LabelSoap[] toSoapModels(List<Label> models) {
		List<LabelSoap> soapModels = new ArrayList<LabelSoap>(models.size());

		for (Label model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LabelSoap[soapModels.size()]);
	}

	public LabelSoap() {
	}

	public long getPrimaryKey() {
		return _labelId;
	}

	public void setPrimaryKey(long pk) {
		setLabelId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getLabelId() {
		return _labelId;
	}

	public void setLabelId(long labelId) {
		_labelId = labelId;
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

	public String getColor() {
		return _color;
	}

	public void setColor(String color) {
		_color = color;
	}

	public int getScope() {
		return _scope;
	}

	public void setScope(int scope) {
		_scope = scope;
	}

	private String _uuid;
	private long _labelId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _color;
	private int _scope;
}