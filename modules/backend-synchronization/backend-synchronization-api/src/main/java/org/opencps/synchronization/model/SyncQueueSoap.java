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

package org.opencps.synchronization.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author trungdk
 * @generated
 */
@ProviderType
public class SyncQueueSoap implements Serializable {
	public static SyncQueueSoap toSoapModel(SyncQueue model) {
		SyncQueueSoap soapModel = new SyncQueueSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSyncQueueId(model.getSyncQueueId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setServerNo(model.getServerNo());
		soapModel.setClassName(model.getClassName());
		soapModel.setJsonObject(model.getJsonObject());
		soapModel.setStatus(model.getStatus());
		soapModel.setRetryCount(model.getRetryCount());
		soapModel.setPriority(model.getPriority());
		soapModel.setMethod(model.getMethod());

		return soapModel;
	}

	public static SyncQueueSoap[] toSoapModels(SyncQueue[] models) {
		SyncQueueSoap[] soapModels = new SyncQueueSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SyncQueueSoap[][] toSoapModels(SyncQueue[][] models) {
		SyncQueueSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SyncQueueSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SyncQueueSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SyncQueueSoap[] toSoapModels(List<SyncQueue> models) {
		List<SyncQueueSoap> soapModels = new ArrayList<SyncQueueSoap>(models.size());

		for (SyncQueue model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SyncQueueSoap[soapModels.size()]);
	}

	public SyncQueueSoap() {
	}

	public long getPrimaryKey() {
		return _syncQueueId;
	}

	public void setPrimaryKey(long pk) {
		setSyncQueueId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSyncQueueId() {
		return _syncQueueId;
	}

	public void setSyncQueueId(long syncQueueId) {
		_syncQueueId = syncQueueId;
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

	public String getServerNo() {
		return _serverNo;
	}

	public void setServerNo(String serverNo) {
		_serverNo = serverNo;
	}

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public String getJsonObject() {
		return _jsonObject;
	}

	public void setJsonObject(String jsonObject) {
		_jsonObject = jsonObject;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public int getRetryCount() {
		return _retryCount;
	}

	public void setRetryCount(int retryCount) {
		_retryCount = retryCount;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	public String getMethod() {
		return _method;
	}

	public void setMethod(String method) {
		_method = method;
	}

	private String _uuid;
	private long _syncQueueId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _serverNo;
	private String _className;
	private String _jsonObject;
	private int _status;
	private int _retryCount;
	private int _priority;
	private String _method;
}