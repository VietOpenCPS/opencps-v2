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

package org.opencps.backend.systemlogmgt.model;

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
public class SystemLogSoap implements Serializable {
	public static SystemLogSoap toSoapModel(SystemLog model) {
		SystemLogSoap soapModel = new SystemLogSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setLogId(model.getLogId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setClassName(model.getClassName());
		soapModel.setMessage(model.getMessage());
		soapModel.setType(model.getType());
		soapModel.setLine(model.getLine());

		return soapModel;
	}

	public static SystemLogSoap[] toSoapModels(SystemLog[] models) {
		SystemLogSoap[] soapModels = new SystemLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SystemLogSoap[][] toSoapModels(SystemLog[][] models) {
		SystemLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SystemLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SystemLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SystemLogSoap[] toSoapModels(List<SystemLog> models) {
		List<SystemLogSoap> soapModels = new ArrayList<SystemLogSoap>(models.size());

		for (SystemLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SystemLogSoap[soapModels.size()]);
	}

	public SystemLogSoap() {
	}

	public long getPrimaryKey() {
		return _logId;
	}

	public void setPrimaryKey(long pk) {
		setLogId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getLogId() {
		return _logId;
	}

	public void setLogId(long logId) {
		_logId = logId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public String getMessage() {
		return _message;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public int getLine() {
		return _line;
	}

	public void setLine(int line) {
		_line = line;
	}

	private String _uuid;
	private long _logId;
	private long _groupId;
	private Date _createDate;
	private String _className;
	private String _message;
	private String _type;
	private int _line;
}