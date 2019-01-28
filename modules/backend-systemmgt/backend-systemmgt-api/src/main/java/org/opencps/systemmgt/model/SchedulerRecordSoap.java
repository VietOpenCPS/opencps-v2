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

package org.opencps.systemmgt.model;

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
public class SchedulerRecordSoap implements Serializable {
	public static SchedulerRecordSoap toSoapModel(SchedulerRecord model) {
		SchedulerRecordSoap soapModel = new SchedulerRecordSoap();

		soapModel.setSchedulerId(model.getSchedulerId());
		soapModel.setSchedulerType(model.getSchedulerType());
		soapModel.setOnTime(model.getOnTime());
		soapModel.setNextTime(model.getNextTime());
		soapModel.setExpiredTime(model.getExpiredTime());
		soapModel.setMinDuration(model.getMinDuration());
		soapModel.setMaxDuration(model.getMaxDuration());

		return soapModel;
	}

	public static SchedulerRecordSoap[] toSoapModels(SchedulerRecord[] models) {
		SchedulerRecordSoap[] soapModels = new SchedulerRecordSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SchedulerRecordSoap[][] toSoapModels(
		SchedulerRecord[][] models) {
		SchedulerRecordSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SchedulerRecordSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SchedulerRecordSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SchedulerRecordSoap[] toSoapModels(
		List<SchedulerRecord> models) {
		List<SchedulerRecordSoap> soapModels = new ArrayList<SchedulerRecordSoap>(models.size());

		for (SchedulerRecord model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SchedulerRecordSoap[soapModels.size()]);
	}

	public SchedulerRecordSoap() {
	}

	public long getPrimaryKey() {
		return _schedulerId;
	}

	public void setPrimaryKey(long pk) {
		setSchedulerId(pk);
	}

	public long getSchedulerId() {
		return _schedulerId;
	}

	public void setSchedulerId(long schedulerId) {
		_schedulerId = schedulerId;
	}

	public String getSchedulerType() {
		return _schedulerType;
	}

	public void setSchedulerType(String schedulerType) {
		_schedulerType = schedulerType;
	}

	public Date getOnTime() {
		return _onTime;
	}

	public void setOnTime(Date onTime) {
		_onTime = onTime;
	}

	public Date getNextTime() {
		return _nextTime;
	}

	public void setNextTime(Date nextTime) {
		_nextTime = nextTime;
	}

	public Date getExpiredTime() {
		return _expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		_expiredTime = expiredTime;
	}

	public long getMinDuration() {
		return _minDuration;
	}

	public void setMinDuration(long minDuration) {
		_minDuration = minDuration;
	}

	public long getMaxDuration() {
		return _maxDuration;
	}

	public void setMaxDuration(long maxDuration) {
		_maxDuration = maxDuration;
	}

	private long _schedulerId;
	private String _schedulerType;
	private Date _onTime;
	private Date _nextTime;
	private Date _expiredTime;
	private long _minDuration;
	private long _maxDuration;
}