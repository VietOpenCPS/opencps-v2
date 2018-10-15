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

package org.opencps.datamgt.model;

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
public class HolidaySoap implements Serializable {
	public static HolidaySoap toSoapModel(Holiday model) {
		HolidaySoap soapModel = new HolidaySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setHolidayId(model.getHolidayId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHolidayDate(model.getHolidayDate());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static HolidaySoap[] toSoapModels(Holiday[] models) {
		HolidaySoap[] soapModels = new HolidaySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HolidaySoap[][] toSoapModels(Holiday[][] models) {
		HolidaySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HolidaySoap[models.length][models[0].length];
		}
		else {
			soapModels = new HolidaySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HolidaySoap[] toSoapModels(List<Holiday> models) {
		List<HolidaySoap> soapModels = new ArrayList<HolidaySoap>(models.size());

		for (Holiday model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HolidaySoap[soapModels.size()]);
	}

	public HolidaySoap() {
	}

	public long getPrimaryKey() {
		return _holidayId;
	}

	public void setPrimaryKey(long pk) {
		setHolidayId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getHolidayId() {
		return _holidayId;
	}

	public void setHolidayId(long holidayId) {
		_holidayId = holidayId;
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

	public Date getHolidayDate() {
		return _holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		_holidayDate = holidayDate;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private String _uuid;
	private long _holidayId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private Date _holidayDate;
	private String _description;
}