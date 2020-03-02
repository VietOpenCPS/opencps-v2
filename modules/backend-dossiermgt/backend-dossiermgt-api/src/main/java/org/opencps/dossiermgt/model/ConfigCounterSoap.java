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

package org.opencps.dossiermgt.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author huymq
 * @generated
 */
@ProviderType
public class ConfigCounterSoap implements Serializable {
	public static ConfigCounterSoap toSoapModel(ConfigCounter model) {
		ConfigCounterSoap soapModel = new ConfigCounterSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setConfigCounterId(model.getConfigCounterId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCounterCode(model.getCounterCode());
		soapModel.setPatternCode(model.getPatternCode());
		soapModel.setStartCounter(model.getStartCounter());

		return soapModel;
	}

	public static ConfigCounterSoap[] toSoapModels(ConfigCounter[] models) {
		ConfigCounterSoap[] soapModels = new ConfigCounterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ConfigCounterSoap[][] toSoapModels(ConfigCounter[][] models) {
		ConfigCounterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ConfigCounterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ConfigCounterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ConfigCounterSoap[] toSoapModels(List<ConfigCounter> models) {
		List<ConfigCounterSoap> soapModels = new ArrayList<ConfigCounterSoap>(models.size());

		for (ConfigCounter model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ConfigCounterSoap[soapModels.size()]);
	}

	public ConfigCounterSoap() {
	}

	public long getPrimaryKey() {
		return _configCounterId;
	}

	public void setPrimaryKey(long pk) {
		setConfigCounterId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getConfigCounterId() {
		return _configCounterId;
	}

	public void setConfigCounterId(long configCounterId) {
		_configCounterId = configCounterId;
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

	public String getCounterCode() {
		return _counterCode;
	}

	public void setCounterCode(String counterCode) {
		_counterCode = counterCode;
	}

	public String getPatternCode() {
		return _patternCode;
	}

	public void setPatternCode(String patternCode) {
		_patternCode = patternCode;
	}

	public int getStartCounter() {
		return _startCounter;
	}

	public void setStartCounter(int startCounter) {
		_startCounter = startCounter;
	}

	private String _uuid;
	private long _configCounterId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _counterCode;
	private String _patternCode;
	private int _startCounter;
}