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
public class BookingSoap implements Serializable {
	public static BookingSoap toSoapModel(Booking model) {
		BookingSoap soapModel = new BookingSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setBookingId(model.getBookingId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setServiceCode(model.getServiceCode());
		soapModel.setCodeNumber(model.getCodeNumber());
		soapModel.setBookingName(model.getBookingName());
		soapModel.setCheckinDate(model.getCheckinDate());
		soapModel.setGateNumber(model.getGateNumber());
		soapModel.setState(model.getState());
		soapModel.setBookingDate(model.getBookingDate());
		soapModel.setSpeaking(model.isSpeaking());
		soapModel.setServiceGroupCode(model.getServiceGroupCode());
		soapModel.setCount(model.getCount());
		soapModel.setOnline(model.isOnline());
		soapModel.setBookingInTime(model.getBookingInTime());
		soapModel.setTelNo(model.getTelNo());

		return soapModel;
	}

	public static BookingSoap[] toSoapModels(Booking[] models) {
		BookingSoap[] soapModels = new BookingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BookingSoap[][] toSoapModels(Booking[][] models) {
		BookingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BookingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BookingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BookingSoap[] toSoapModels(List<Booking> models) {
		List<BookingSoap> soapModels = new ArrayList<BookingSoap>(models.size());

		for (Booking model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BookingSoap[soapModels.size()]);
	}

	public BookingSoap() {
	}

	public long getPrimaryKey() {
		return _bookingId;
	}

	public void setPrimaryKey(long pk) {
		setBookingId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getBookingId() {
		return _bookingId;
	}

	public void setBookingId(long bookingId) {
		_bookingId = bookingId;
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

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public String getServiceCode() {
		return _serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		_serviceCode = serviceCode;
	}

	public String getCodeNumber() {
		return _codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		_codeNumber = codeNumber;
	}

	public String getBookingName() {
		return _bookingName;
	}

	public void setBookingName(String bookingName) {
		_bookingName = bookingName;
	}

	public Date getCheckinDate() {
		return _checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		_checkinDate = checkinDate;
	}

	public String getGateNumber() {
		return _gateNumber;
	}

	public void setGateNumber(String gateNumber) {
		_gateNumber = gateNumber;
	}

	public int getState() {
		return _state;
	}

	public void setState(int state) {
		_state = state;
	}

	public Date getBookingDate() {
		return _bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		_bookingDate = bookingDate;
	}

	public boolean getSpeaking() {
		return _speaking;
	}

	public boolean isSpeaking() {
		return _speaking;
	}

	public void setSpeaking(boolean speaking) {
		_speaking = speaking;
	}

	public String getServiceGroupCode() {
		return _serviceGroupCode;
	}

	public void setServiceGroupCode(String serviceGroupCode) {
		_serviceGroupCode = serviceGroupCode;
	}

	public int getCount() {
		return _count;
	}

	public void setCount(int count) {
		_count = count;
	}

	public boolean getOnline() {
		return _online;
	}

	public boolean isOnline() {
		return _online;
	}

	public void setOnline(boolean online) {
		_online = online;
	}

	public String getBookingInTime() {
		return _bookingInTime;
	}

	public void setBookingInTime(String bookingInTime) {
		_bookingInTime = bookingInTime;
	}

	public String getTelNo() {
		return _telNo;
	}

	public void setTelNo(String telNo) {
		_telNo = telNo;
	}

	private String _uuid;
	private long _bookingId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _className;
	private long _classPK;
	private String _serviceCode;
	private String _codeNumber;
	private String _bookingName;
	private Date _checkinDate;
	private String _gateNumber;
	private int _state;
	private Date _bookingDate;
	private boolean _speaking;
	private String _serviceGroupCode;
	private int _count;
	private boolean _online;
	private String _bookingInTime;
	private String _telNo;
}