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
public class SavePickFieldSoap implements Serializable {
	public static SavePickFieldSoap toSoapModel(SavePickField model) {
		SavePickFieldSoap soapModel = new SavePickFieldSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setFieldPickId(model.getFieldPickId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFormData(model.getFormData());
		soapModel.setClassPK(model.getClassPK());

		return soapModel;
	}

	public static SavePickFieldSoap[] toSoapModels(SavePickField[] models) {
		SavePickFieldSoap[] soapModels = new SavePickFieldSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SavePickFieldSoap[][] toSoapModels(SavePickField[][] models) {
		SavePickFieldSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SavePickFieldSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SavePickFieldSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SavePickFieldSoap[] toSoapModels(List<SavePickField> models) {
		List<SavePickFieldSoap> soapModels = new ArrayList<SavePickFieldSoap>(models.size());

		for (SavePickField model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SavePickFieldSoap[soapModels.size()]);
	}

	public SavePickFieldSoap() {
	}

	public long getPrimaryKey() {
		return _fieldPickId;
	}

	public void setPrimaryKey(long pk) {
		setFieldPickId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getFieldPickId() {
		return _fieldPickId;
	}

	public void setFieldPickId(long fieldPickId) {
		_fieldPickId = fieldPickId;
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

	public String getFormData() {
		return _formData;
	}

	public void setFormData(String formData) {
		_formData = formData;
	}

	public String getClassPK() {
		return _classPK;
	}

	public void setClassPK(String classPK) {
		_classPK = classPK;
	}

	private String _uuid;
	private long _fieldPickId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _formData;
	private String _classPK;
}