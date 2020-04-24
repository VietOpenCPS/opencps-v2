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

import org.opencps.usermgt.model.TrackClientStatistic;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TrackClientStatistic in entity cache.
 *
 * @author khoavu
 * @see TrackClientStatistic
 * @generated
 */
@ProviderType
public class TrackClientStatisticCacheModel implements CacheModel<TrackClientStatistic>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrackClientStatisticCacheModel)) {
			return false;
		}

		TrackClientStatisticCacheModel trackClientStatisticCacheModel = (TrackClientStatisticCacheModel)obj;

		if (trackClientStatisticId == trackClientStatisticCacheModel.trackClientStatisticId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, trackClientStatisticId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", trackClientStatisticId=");
		sb.append(trackClientStatisticId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", url=");
		sb.append(url);
		sb.append(", year=");
		sb.append(year);
		sb.append(", month=");
		sb.append(month);
		sb.append(", day=");
		sb.append(day);
		sb.append(", region=");
		sb.append(region);
		sb.append(", desktop=");
		sb.append(desktop);
		sb.append(", mobile=");
		sb.append(mobile);
		sb.append(", tablet=");
		sb.append(tablet);
		sb.append(", total=");
		sb.append(total);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TrackClientStatistic toEntityModel() {
		TrackClientStatisticImpl trackClientStatisticImpl = new TrackClientStatisticImpl();

		if (uuid == null) {
			trackClientStatisticImpl.setUuid("");
		}
		else {
			trackClientStatisticImpl.setUuid(uuid);
		}

		trackClientStatisticImpl.setTrackClientStatisticId(trackClientStatisticId);

		if (createDate == Long.MIN_VALUE) {
			trackClientStatisticImpl.setCreateDate(null);
		}
		else {
			trackClientStatisticImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			trackClientStatisticImpl.setModifiedDate(null);
		}
		else {
			trackClientStatisticImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (url == null) {
			trackClientStatisticImpl.setUrl("");
		}
		else {
			trackClientStatisticImpl.setUrl(url);
		}

		trackClientStatisticImpl.setYear(year);
		trackClientStatisticImpl.setMonth(month);
		trackClientStatisticImpl.setDay(day);

		if (region == null) {
			trackClientStatisticImpl.setRegion("");
		}
		else {
			trackClientStatisticImpl.setRegion(region);
		}

		trackClientStatisticImpl.setDesktop(desktop);
		trackClientStatisticImpl.setMobile(mobile);
		trackClientStatisticImpl.setTablet(tablet);
		trackClientStatisticImpl.setTotal(total);

		trackClientStatisticImpl.resetOriginalValues();

		return trackClientStatisticImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		trackClientStatisticId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		url = objectInput.readUTF();

		year = objectInput.readInt();

		month = objectInput.readInt();

		day = objectInput.readInt();
		region = objectInput.readUTF();

		desktop = objectInput.readBoolean();

		mobile = objectInput.readBoolean();

		tablet = objectInput.readBoolean();

		total = objectInput.readLong();
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

		objectOutput.writeLong(trackClientStatisticId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeInt(year);

		objectOutput.writeInt(month);

		objectOutput.writeInt(day);

		if (region == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(region);
		}

		objectOutput.writeBoolean(desktop);

		objectOutput.writeBoolean(mobile);

		objectOutput.writeBoolean(tablet);

		objectOutput.writeLong(total);
	}

	public String uuid;
	public long trackClientStatisticId;
	public long createDate;
	public long modifiedDate;
	public String url;
	public int year;
	public int month;
	public int day;
	public String region;
	public boolean desktop;
	public boolean mobile;
	public boolean tablet;
	public long total;
}