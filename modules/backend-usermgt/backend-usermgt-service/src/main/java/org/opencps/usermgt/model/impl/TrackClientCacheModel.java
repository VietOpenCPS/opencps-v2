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

package org.opencps.usermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.usermgt.model.TrackClient;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TrackClient in entity cache.
 *
 * @author khoavu
 * @see TrackClient
 * @generated
 */
@ProviderType
public class TrackClientCacheModel implements CacheModel<TrackClient>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrackClientCacheModel)) {
			return false;
		}

		TrackClientCacheModel trackClientCacheModel = (TrackClientCacheModel)obj;

		if (trackClientId == trackClientCacheModel.trackClientId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, trackClientId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", trackClientId=");
		sb.append(trackClientId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", url=");
		sb.append(url);
		sb.append(", year=");
		sb.append(year);
		sb.append(", month=");
		sb.append(month);
		sb.append(", day=");
		sb.append(day);
		sb.append(", visitDate=");
		sb.append(visitDate);
		sb.append(", leaveDate=");
		sb.append(leaveDate);
		sb.append(", clientIP=");
		sb.append(clientIP);
		sb.append(", macAddress=");
		sb.append(macAddress);
		sb.append(", region=");
		sb.append(region);
		sb.append(", nation=");
		sb.append(nation);
		sb.append(", latitude=");
		sb.append(latitude);
		sb.append(", longitude=");
		sb.append(longitude);
		sb.append(", timeOnPage=");
		sb.append(timeOnPage);
		sb.append(", desktop=");
		sb.append(desktop);
		sb.append(", mobile=");
		sb.append(mobile);
		sb.append(", tablet=");
		sb.append(tablet);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TrackClient toEntityModel() {
		TrackClientImpl trackClientImpl = new TrackClientImpl();

		if (uuid == null) {
			trackClientImpl.setUuid("");
		}
		else {
			trackClientImpl.setUuid(uuid);
		}

		trackClientImpl.setTrackClientId(trackClientId);

		if (createDate == Long.MIN_VALUE) {
			trackClientImpl.setCreateDate(null);
		}
		else {
			trackClientImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			trackClientImpl.setModifiedDate(null);
		}
		else {
			trackClientImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (sessionId == null) {
			trackClientImpl.setSessionId("");
		}
		else {
			trackClientImpl.setSessionId(sessionId);
		}

		if (url == null) {
			trackClientImpl.setUrl("");
		}
		else {
			trackClientImpl.setUrl(url);
		}

		trackClientImpl.setYear(year);
		trackClientImpl.setMonth(month);
		trackClientImpl.setDay(day);

		if (visitDate == Long.MIN_VALUE) {
			trackClientImpl.setVisitDate(null);
		}
		else {
			trackClientImpl.setVisitDate(new Date(visitDate));
		}

		if (leaveDate == Long.MIN_VALUE) {
			trackClientImpl.setLeaveDate(null);
		}
		else {
			trackClientImpl.setLeaveDate(new Date(leaveDate));
		}

		if (clientIP == null) {
			trackClientImpl.setClientIP("");
		}
		else {
			trackClientImpl.setClientIP(clientIP);
		}

		if (macAddress == null) {
			trackClientImpl.setMacAddress("");
		}
		else {
			trackClientImpl.setMacAddress(macAddress);
		}

		if (region == null) {
			trackClientImpl.setRegion("");
		}
		else {
			trackClientImpl.setRegion(region);
		}

		if (nation == null) {
			trackClientImpl.setNation("");
		}
		else {
			trackClientImpl.setNation(nation);
		}

		if (latitude == null) {
			trackClientImpl.setLatitude("");
		}
		else {
			trackClientImpl.setLatitude(latitude);
		}

		if (longitude == null) {
			trackClientImpl.setLongitude("");
		}
		else {
			trackClientImpl.setLongitude(longitude);
		}

		trackClientImpl.setTimeOnPage(timeOnPage);
		trackClientImpl.setDesktop(desktop);
		trackClientImpl.setMobile(mobile);
		trackClientImpl.setTablet(tablet);

		trackClientImpl.resetOriginalValues();

		return trackClientImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		trackClientId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		sessionId = objectInput.readUTF();
		url = objectInput.readUTF();

		year = objectInput.readInt();

		month = objectInput.readInt();

		day = objectInput.readInt();
		visitDate = objectInput.readLong();
		leaveDate = objectInput.readLong();
		clientIP = objectInput.readUTF();
		macAddress = objectInput.readUTF();
		region = objectInput.readUTF();
		nation = objectInput.readUTF();
		latitude = objectInput.readUTF();
		longitude = objectInput.readUTF();

		timeOnPage = objectInput.readLong();

		desktop = objectInput.readBoolean();

		mobile = objectInput.readBoolean();

		tablet = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(trackClientId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (sessionId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sessionId);
		}

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeInt(year);

		objectOutput.writeInt(month);

		objectOutput.writeInt(day);
		objectOutput.writeLong(visitDate);
		objectOutput.writeLong(leaveDate);

		if (clientIP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(clientIP);
		}

		if (macAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(macAddress);
		}

		if (region == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(region);
		}

		if (nation == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(nation);
		}

		if (latitude == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(latitude);
		}

		if (longitude == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(longitude);
		}

		objectOutput.writeLong(timeOnPage);

		objectOutput.writeBoolean(desktop);

		objectOutput.writeBoolean(mobile);

		objectOutput.writeBoolean(tablet);
	}

	public String uuid;
	public long trackClientId;
	public long createDate;
	public long modifiedDate;
	public String sessionId;
	public String url;
	public int year;
	public int month;
	public int day;
	public long visitDate;
	public long leaveDate;
	public String clientIP;
	public String macAddress;
	public String region;
	public String nation;
	public String latitude;
	public String longitude;
	public long timeOnPage;
	public boolean desktop;
	public boolean mobile;
	public boolean tablet;
}