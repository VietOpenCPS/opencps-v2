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

package org.opencps.dossiermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.dossiermgt.model.Booking;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Booking in entity cache.
 *
 * @author huymq
 * @see Booking
 * @generated
 */
@ProviderType
public class BookingCacheModel implements CacheModel<Booking>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BookingCacheModel)) {
			return false;
		}

		BookingCacheModel bookingCacheModel = (BookingCacheModel)obj;

		if (bookingId == bookingCacheModel.bookingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, bookingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", bookingId=");
		sb.append(bookingId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", serviceCode=");
		sb.append(serviceCode);
		sb.append(", codeNumber=");
		sb.append(codeNumber);
		sb.append(", bookingName=");
		sb.append(bookingName);
		sb.append(", checkinDate=");
		sb.append(checkinDate);
		sb.append(", gateNumber=");
		sb.append(gateNumber);
		sb.append(", state=");
		sb.append(state);
		sb.append(", bookingDate=");
		sb.append(bookingDate);
		sb.append(", speaking=");
		sb.append(speaking);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Booking toEntityModel() {
		BookingImpl bookingImpl = new BookingImpl();

		if (uuid == null) {
			bookingImpl.setUuid("");
		}
		else {
			bookingImpl.setUuid(uuid);
		}

		bookingImpl.setBookingId(bookingId);
		bookingImpl.setGroupId(groupId);
		bookingImpl.setCompanyId(companyId);
		bookingImpl.setUserId(userId);

		if (userName == null) {
			bookingImpl.setUserName("");
		}
		else {
			bookingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			bookingImpl.setCreateDate(null);
		}
		else {
			bookingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			bookingImpl.setModifiedDate(null);
		}
		else {
			bookingImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (className == null) {
			bookingImpl.setClassName("");
		}
		else {
			bookingImpl.setClassName(className);
		}

		bookingImpl.setClassPK(classPK);

		if (serviceCode == null) {
			bookingImpl.setServiceCode("");
		}
		else {
			bookingImpl.setServiceCode(serviceCode);
		}

		if (codeNumber == null) {
			bookingImpl.setCodeNumber("");
		}
		else {
			bookingImpl.setCodeNumber(codeNumber);
		}

		if (bookingName == null) {
			bookingImpl.setBookingName("");
		}
		else {
			bookingImpl.setBookingName(bookingName);
		}

		if (checkinDate == Long.MIN_VALUE) {
			bookingImpl.setCheckinDate(null);
		}
		else {
			bookingImpl.setCheckinDate(new Date(checkinDate));
		}

		if (gateNumber == null) {
			bookingImpl.setGateNumber("");
		}
		else {
			bookingImpl.setGateNumber(gateNumber);
		}

		bookingImpl.setState(state);

		if (bookingDate == Long.MIN_VALUE) {
			bookingImpl.setBookingDate(null);
		}
		else {
			bookingImpl.setBookingDate(new Date(bookingDate));
		}

		bookingImpl.setSpeaking(speaking);

		bookingImpl.resetOriginalValues();

		return bookingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		bookingId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		className = objectInput.readUTF();

		classPK = objectInput.readLong();
		serviceCode = objectInput.readUTF();
		codeNumber = objectInput.readUTF();
		bookingName = objectInput.readUTF();
		checkinDate = objectInput.readLong();
		gateNumber = objectInput.readUTF();

		state = objectInput.readInt();
		bookingDate = objectInput.readLong();

		speaking = objectInput.readBoolean();
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

		objectOutput.writeLong(bookingId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}

		objectOutput.writeLong(classPK);

		if (serviceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceCode);
		}

		if (codeNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(codeNumber);
		}

		if (bookingName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(bookingName);
		}

		objectOutput.writeLong(checkinDate);

		if (gateNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(gateNumber);
		}

		objectOutput.writeInt(state);
		objectOutput.writeLong(bookingDate);

		objectOutput.writeBoolean(speaking);
	}

	public String uuid;
	public long bookingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String className;
	public long classPK;
	public String serviceCode;
	public String codeNumber;
	public String bookingName;
	public long checkinDate;
	public String gateNumber;
	public int state;
	public long bookingDate;
	public boolean speaking;
}