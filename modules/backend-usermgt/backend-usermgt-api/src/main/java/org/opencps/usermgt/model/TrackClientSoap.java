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
public class TrackClientSoap implements Serializable {
	public static TrackClientSoap toSoapModel(TrackClient model) {
		TrackClientSoap soapModel = new TrackClientSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setTrackClientId(model.getTrackClientId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setUrl(model.getUrl());
		soapModel.setYear(model.getYear());
		soapModel.setMonth(model.getMonth());
		soapModel.setDay(model.getDay());
		soapModel.setVisitDate(model.getVisitDate());
		soapModel.setLeaveDate(model.getLeaveDate());
		soapModel.setClientIP(model.getClientIP());
		soapModel.setMacAddress(model.getMacAddress());
		soapModel.setRegion(model.getRegion());
		soapModel.setNation(model.getNation());
		soapModel.setLatitude(model.getLatitude());
		soapModel.setLongitude(model.getLongitude());
		soapModel.setTimeOnPage(model.getTimeOnPage());
		soapModel.setDesktop(model.isDesktop());
		soapModel.setMobile(model.isMobile());
		soapModel.setTablet(model.isTablet());

		return soapModel;
	}

	public static TrackClientSoap[] toSoapModels(TrackClient[] models) {
		TrackClientSoap[] soapModels = new TrackClientSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrackClientSoap[][] toSoapModels(TrackClient[][] models) {
		TrackClientSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrackClientSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrackClientSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrackClientSoap[] toSoapModels(List<TrackClient> models) {
		List<TrackClientSoap> soapModels = new ArrayList<TrackClientSoap>(models.size());

		for (TrackClient model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrackClientSoap[soapModels.size()]);
	}

	public TrackClientSoap() {
	}

	public long getPrimaryKey() {
		return _trackClientId;
	}

	public void setPrimaryKey(long pk) {
		setTrackClientId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getTrackClientId() {
		return _trackClientId;
	}

	public void setTrackClientId(long trackClientId) {
		_trackClientId = trackClientId;
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

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int year) {
		_year = year;
	}

	public int getMonth() {
		return _month;
	}

	public void setMonth(int month) {
		_month = month;
	}

	public int getDay() {
		return _day;
	}

	public void setDay(int day) {
		_day = day;
	}

	public Date getVisitDate() {
		return _visitDate;
	}

	public void setVisitDate(Date visitDate) {
		_visitDate = visitDate;
	}

	public Date getLeaveDate() {
		return _leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		_leaveDate = leaveDate;
	}

	public String getClientIP() {
		return _clientIP;
	}

	public void setClientIP(String clientIP) {
		_clientIP = clientIP;
	}

	public String getMacAddress() {
		return _macAddress;
	}

	public void setMacAddress(String macAddress) {
		_macAddress = macAddress;
	}

	public String getRegion() {
		return _region;
	}

	public void setRegion(String region) {
		_region = region;
	}

	public String getNation() {
		return _nation;
	}

	public void setNation(String nation) {
		_nation = nation;
	}

	public String getLatitude() {
		return _latitude;
	}

	public void setLatitude(String latitude) {
		_latitude = latitude;
	}

	public String getLongitude() {
		return _longitude;
	}

	public void setLongitude(String longitude) {
		_longitude = longitude;
	}

	public long getTimeOnPage() {
		return _timeOnPage;
	}

	public void setTimeOnPage(long timeOnPage) {
		_timeOnPage = timeOnPage;
	}

	public boolean getDesktop() {
		return _desktop;
	}

	public boolean isDesktop() {
		return _desktop;
	}

	public void setDesktop(boolean desktop) {
		_desktop = desktop;
	}

	public boolean getMobile() {
		return _mobile;
	}

	public boolean isMobile() {
		return _mobile;
	}

	public void setMobile(boolean mobile) {
		_mobile = mobile;
	}

	public boolean getTablet() {
		return _tablet;
	}

	public boolean isTablet() {
		return _tablet;
	}

	public void setTablet(boolean tablet) {
		_tablet = tablet;
	}

	private String _uuid;
	private long _trackClientId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _sessionId;
	private String _url;
	private int _year;
	private int _month;
	private int _day;
	private Date _visitDate;
	private Date _leaveDate;
	private String _clientIP;
	private String _macAddress;
	private String _region;
	private String _nation;
	private String _latitude;
	private String _longitude;
	private long _timeOnPage;
	private boolean _desktop;
	private boolean _mobile;
	private boolean _tablet;
}