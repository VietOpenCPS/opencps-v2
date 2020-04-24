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
public class TrackClientStatisticSoap implements Serializable {
	public static TrackClientStatisticSoap toSoapModel(
		TrackClientStatistic model) {
		TrackClientStatisticSoap soapModel = new TrackClientStatisticSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setTrackClientStatisticId(model.getTrackClientStatisticId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setUrl(model.getUrl());
		soapModel.setYear(model.getYear());
		soapModel.setMonth(model.getMonth());
		soapModel.setDay(model.getDay());
		soapModel.setRegion(model.getRegion());
		soapModel.setDesktop(model.isDesktop());
		soapModel.setMobile(model.isMobile());
		soapModel.setTablet(model.isTablet());
		soapModel.setTotal(model.getTotal());

		return soapModel;
	}

	public static TrackClientStatisticSoap[] toSoapModels(
		TrackClientStatistic[] models) {
		TrackClientStatisticSoap[] soapModels = new TrackClientStatisticSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrackClientStatisticSoap[][] toSoapModels(
		TrackClientStatistic[][] models) {
		TrackClientStatisticSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrackClientStatisticSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrackClientStatisticSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrackClientStatisticSoap[] toSoapModels(
		List<TrackClientStatistic> models) {
		List<TrackClientStatisticSoap> soapModels = new ArrayList<TrackClientStatisticSoap>(models.size());

		for (TrackClientStatistic model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrackClientStatisticSoap[soapModels.size()]);
	}

	public TrackClientStatisticSoap() {
	}

	public long getPrimaryKey() {
		return _trackClientStatisticId;
	}

	public void setPrimaryKey(long pk) {
		setTrackClientStatisticId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getTrackClientStatisticId() {
		return _trackClientStatisticId;
	}

	public void setTrackClientStatisticId(long trackClientStatisticId) {
		_trackClientStatisticId = trackClientStatisticId;
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

	public String getRegion() {
		return _region;
	}

	public void setRegion(String region) {
		_region = region;
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

	public long getTotal() {
		return _total;
	}

	public void setTotal(long total) {
		_total = total;
	}

	private String _uuid;
	private long _trackClientStatisticId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _url;
	private int _year;
	private int _month;
	private int _day;
	private String _region;
	private boolean _desktop;
	private boolean _mobile;
	private boolean _tablet;
	private long _total;
}