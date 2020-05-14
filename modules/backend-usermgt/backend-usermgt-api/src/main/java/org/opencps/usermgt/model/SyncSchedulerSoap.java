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
public class SyncSchedulerSoap implements Serializable {
	public static SyncSchedulerSoap toSoapModel(SyncScheduler model) {
		SyncSchedulerSoap soapModel = new SyncSchedulerSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSyncSchedulerId(model.getSyncSchedulerId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setClassName(model.getClassName());
		soapModel.setTypeCode(model.getTypeCode());
		soapModel.setSyncDate(model.getSyncDate());
		soapModel.setRetry(model.getRetry());

		return soapModel;
	}

	public static SyncSchedulerSoap[] toSoapModels(SyncScheduler[] models) {
		SyncSchedulerSoap[] soapModels = new SyncSchedulerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SyncSchedulerSoap[][] toSoapModels(SyncScheduler[][] models) {
		SyncSchedulerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SyncSchedulerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SyncSchedulerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SyncSchedulerSoap[] toSoapModels(List<SyncScheduler> models) {
		List<SyncSchedulerSoap> soapModels = new ArrayList<SyncSchedulerSoap>(models.size());

		for (SyncScheduler model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SyncSchedulerSoap[soapModels.size()]);
	}

	public SyncSchedulerSoap() {
	}

	public long getPrimaryKey() {
		return _syncSchedulerId;
	}

	public void setPrimaryKey(long pk) {
		setSyncSchedulerId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSyncSchedulerId() {
		return _syncSchedulerId;
	}

	public void setSyncSchedulerId(long syncSchedulerId) {
		_syncSchedulerId = syncSchedulerId;
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

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public String getTypeCode() {
		return _typeCode;
	}

	public void setTypeCode(String typeCode) {
		_typeCode = typeCode;
	}

	public Date getSyncDate() {
		return _syncDate;
	}

	public void setSyncDate(Date syncDate) {
		_syncDate = syncDate;
	}

	public int getRetry() {
		return _retry;
	}

	public void setRetry(int retry) {
		_retry = retry;
	}

	private String _uuid;
	private long _syncSchedulerId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _groupId;
	private String _className;
	private String _typeCode;
	private Date _syncDate;
	private int _retry;
}