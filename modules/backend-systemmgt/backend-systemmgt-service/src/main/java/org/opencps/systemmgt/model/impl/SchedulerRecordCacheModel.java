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

package org.opencps.systemmgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.systemmgt.model.SchedulerRecord;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SchedulerRecord in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SchedulerRecord
 * @generated
 */
@ProviderType
public class SchedulerRecordCacheModel implements CacheModel<SchedulerRecord>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SchedulerRecordCacheModel)) {
			return false;
		}

		SchedulerRecordCacheModel schedulerRecordCacheModel = (SchedulerRecordCacheModel)obj;

		if (schedulerId == schedulerRecordCacheModel.schedulerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, schedulerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{schedulerId=");
		sb.append(schedulerId);
		sb.append(", schedulerType=");
		sb.append(schedulerType);
		sb.append(", onTime=");
		sb.append(onTime);
		sb.append(", nextTime=");
		sb.append(nextTime);
		sb.append(", expiredTime=");
		sb.append(expiredTime);
		sb.append(", minDuration=");
		sb.append(minDuration);
		sb.append(", maxDuration=");
		sb.append(maxDuration);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SchedulerRecord toEntityModel() {
		SchedulerRecordImpl schedulerRecordImpl = new SchedulerRecordImpl();

		schedulerRecordImpl.setSchedulerId(schedulerId);

		if (schedulerType == null) {
			schedulerRecordImpl.setSchedulerType("");
		}
		else {
			schedulerRecordImpl.setSchedulerType(schedulerType);
		}

		if (onTime == Long.MIN_VALUE) {
			schedulerRecordImpl.setOnTime(null);
		}
		else {
			schedulerRecordImpl.setOnTime(new Date(onTime));
		}

		if (nextTime == Long.MIN_VALUE) {
			schedulerRecordImpl.setNextTime(null);
		}
		else {
			schedulerRecordImpl.setNextTime(new Date(nextTime));
		}

		if (expiredTime == Long.MIN_VALUE) {
			schedulerRecordImpl.setExpiredTime(null);
		}
		else {
			schedulerRecordImpl.setExpiredTime(new Date(expiredTime));
		}

		schedulerRecordImpl.setMinDuration(minDuration);
		schedulerRecordImpl.setMaxDuration(maxDuration);

		schedulerRecordImpl.resetOriginalValues();

		return schedulerRecordImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		schedulerId = objectInput.readLong();
		schedulerType = objectInput.readUTF();
		onTime = objectInput.readLong();
		nextTime = objectInput.readLong();
		expiredTime = objectInput.readLong();

		minDuration = objectInput.readLong();

		maxDuration = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(schedulerId);

		if (schedulerType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(schedulerType);
		}

		objectOutput.writeLong(onTime);
		objectOutput.writeLong(nextTime);
		objectOutput.writeLong(expiredTime);

		objectOutput.writeLong(minDuration);

		objectOutput.writeLong(maxDuration);
	}

	public long schedulerId;
	public String schedulerType;
	public long onTime;
	public long nextTime;
	public long expiredTime;
	public long minDuration;
	public long maxDuration;
}