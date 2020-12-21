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

package org.opencps.synctracking.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.synctracking.model.SyncTracking;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SyncTracking in entity cache.
 *
 * @author giaonn
 * @see SyncTracking
 * @generated
 */
@ProviderType
public class SyncTrackingCacheModel implements CacheModel<SyncTracking>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncTrackingCacheModel)) {
			return false;
		}

		SyncTrackingCacheModel syncTrackingCacheModel = (SyncTrackingCacheModel)obj;

		if (trackingId == syncTrackingCacheModel.trackingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, trackingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", trackingId=");
		sb.append(trackingId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", dossierNo=");
		sb.append(dossierNo);
		sb.append(", referenceUid=");
		sb.append(referenceUid);
		sb.append(", serverNo=");
		sb.append(serverNo);
		sb.append(", protocol=");
		sb.append(protocol);
		sb.append(", stateSync=");
		sb.append(stateSync);
		sb.append(", serviceCode=");
		sb.append(serviceCode);
		sb.append(", api=");
		sb.append(api);
		sb.append(", fromUnit=");
		sb.append(fromUnit);
		sb.append(", toUnit=");
		sb.append(toUnit);
		sb.append(", bodyRequest=");
		sb.append(bodyRequest);
		sb.append(", response=");
		sb.append(response);
		sb.append(", metaData=");
		sb.append(metaData);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SyncTracking toEntityModel() {
		SyncTrackingImpl syncTrackingImpl = new SyncTrackingImpl();

		if (uuid == null) {
			syncTrackingImpl.setUuid("");
		}
		else {
			syncTrackingImpl.setUuid(uuid);
		}

		syncTrackingImpl.setTrackingId(trackingId);
		syncTrackingImpl.setCompanyId(companyId);
		syncTrackingImpl.setGroupId(groupId);
		syncTrackingImpl.setUserId(userId);

		if (userName == null) {
			syncTrackingImpl.setUserName("");
		}
		else {
			syncTrackingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			syncTrackingImpl.setCreateDate(null);
		}
		else {
			syncTrackingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			syncTrackingImpl.setModifiedDate(null);
		}
		else {
			syncTrackingImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (dossierNo == null) {
			syncTrackingImpl.setDossierNo("");
		}
		else {
			syncTrackingImpl.setDossierNo(dossierNo);
		}

		if (referenceUid == null) {
			syncTrackingImpl.setReferenceUid("");
		}
		else {
			syncTrackingImpl.setReferenceUid(referenceUid);
		}

		if (serverNo == null) {
			syncTrackingImpl.setServerNo("");
		}
		else {
			syncTrackingImpl.setServerNo(serverNo);
		}

		if (protocol == null) {
			syncTrackingImpl.setProtocol("");
		}
		else {
			syncTrackingImpl.setProtocol(protocol);
		}

		syncTrackingImpl.setStateSync(stateSync);

		if (serviceCode == null) {
			syncTrackingImpl.setServiceCode("");
		}
		else {
			syncTrackingImpl.setServiceCode(serviceCode);
		}

		if (api == null) {
			syncTrackingImpl.setApi("");
		}
		else {
			syncTrackingImpl.setApi(api);
		}

		if (fromUnit == null) {
			syncTrackingImpl.setFromUnit("");
		}
		else {
			syncTrackingImpl.setFromUnit(fromUnit);
		}

		if (toUnit == null) {
			syncTrackingImpl.setToUnit("");
		}
		else {
			syncTrackingImpl.setToUnit(toUnit);
		}

		if (bodyRequest == null) {
			syncTrackingImpl.setBodyRequest("");
		}
		else {
			syncTrackingImpl.setBodyRequest(bodyRequest);
		}

		if (response == null) {
			syncTrackingImpl.setResponse("");
		}
		else {
			syncTrackingImpl.setResponse(response);
		}

		if (metaData == null) {
			syncTrackingImpl.setMetaData("");
		}
		else {
			syncTrackingImpl.setMetaData(metaData);
		}

		syncTrackingImpl.resetOriginalValues();

		return syncTrackingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		trackingId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		dossierNo = objectInput.readUTF();
		referenceUid = objectInput.readUTF();
		serverNo = objectInput.readUTF();
		protocol = objectInput.readUTF();

		stateSync = objectInput.readInt();
		serviceCode = objectInput.readUTF();
		api = objectInput.readUTF();
		fromUnit = objectInput.readUTF();
		toUnit = objectInput.readUTF();
		bodyRequest = objectInput.readUTF();
		response = objectInput.readUTF();
		metaData = objectInput.readUTF();
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

		objectOutput.writeLong(trackingId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (dossierNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierNo);
		}

		if (referenceUid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(referenceUid);
		}

		if (serverNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serverNo);
		}

		if (protocol == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(protocol);
		}

		objectOutput.writeInt(stateSync);

		if (serviceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceCode);
		}

		if (api == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(api);
		}

		if (fromUnit == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fromUnit);
		}

		if (toUnit == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(toUnit);
		}

		if (bodyRequest == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(bodyRequest);
		}

		if (response == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(response);
		}

		if (metaData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(metaData);
		}
	}

	public String uuid;
	public long trackingId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String dossierNo;
	public String referenceUid;
	public String serverNo;
	public String protocol;
	public int stateSync;
	public String serviceCode;
	public String api;
	public String fromUnit;
	public String toUnit;
	public String bodyRequest;
	public String response;
	public String metaData;
}