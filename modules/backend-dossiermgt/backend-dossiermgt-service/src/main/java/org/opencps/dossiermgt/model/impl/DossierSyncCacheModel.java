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

import org.opencps.dossiermgt.model.DossierSync;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DossierSync in entity cache.
 *
 * @author huymq
 * @see DossierSync
 * @generated
 */
@ProviderType
public class DossierSyncCacheModel implements CacheModel<DossierSync>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierSyncCacheModel)) {
			return false;
		}

		DossierSyncCacheModel dossierSyncCacheModel = (DossierSyncCacheModel)obj;

		if (DossierSyncId == dossierSyncCacheModel.DossierSyncId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, DossierSyncId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", DossierSyncId=");
		sb.append(DossierSyncId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", dossierId=");
		sb.append(dossierId);
		sb.append(", dossierRefUid=");
		sb.append(dossierRefUid);
		sb.append(", syncRefUid=");
		sb.append(syncRefUid);
		sb.append(", dossierActionId=");
		sb.append(dossierActionId);
		sb.append(", actionCode=");
		sb.append(actionCode);
		sb.append(", actionName=");
		sb.append(actionName);
		sb.append(", actionUser=");
		sb.append(actionUser);
		sb.append(", actionNote=");
		sb.append(actionNote);
		sb.append(", syncType=");
		sb.append(syncType);
		sb.append(", infoType=");
		sb.append(infoType);
		sb.append(", payload=");
		sb.append(payload);
		sb.append(", serverNo=");
		sb.append(serverNo);
		sb.append(", state=");
		sb.append(state);
		sb.append(", retry=");
		sb.append(retry);
		sb.append(", messageText=");
		sb.append(messageText);
		sb.append(", acknowlegement=");
		sb.append(acknowlegement);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierSync toEntityModel() {
		DossierSyncImpl dossierSyncImpl = new DossierSyncImpl();

		if (uuid == null) {
			dossierSyncImpl.setUuid("");
		}
		else {
			dossierSyncImpl.setUuid(uuid);
		}

		dossierSyncImpl.setDossierSyncId(DossierSyncId);
		dossierSyncImpl.setGroupId(groupId);
		dossierSyncImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			dossierSyncImpl.setCreateDate(null);
		}
		else {
			dossierSyncImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dossierSyncImpl.setModifiedDate(null);
		}
		else {
			dossierSyncImpl.setModifiedDate(new Date(modifiedDate));
		}

		dossierSyncImpl.setDossierId(dossierId);

		if (dossierRefUid == null) {
			dossierSyncImpl.setDossierRefUid("");
		}
		else {
			dossierSyncImpl.setDossierRefUid(dossierRefUid);
		}

		if (syncRefUid == null) {
			dossierSyncImpl.setSyncRefUid("");
		}
		else {
			dossierSyncImpl.setSyncRefUid(syncRefUid);
		}

		dossierSyncImpl.setDossierActionId(dossierActionId);

		if (actionCode == null) {
			dossierSyncImpl.setActionCode("");
		}
		else {
			dossierSyncImpl.setActionCode(actionCode);
		}

		if (actionName == null) {
			dossierSyncImpl.setActionName("");
		}
		else {
			dossierSyncImpl.setActionName(actionName);
		}

		if (actionUser == null) {
			dossierSyncImpl.setActionUser("");
		}
		else {
			dossierSyncImpl.setActionUser(actionUser);
		}

		if (actionNote == null) {
			dossierSyncImpl.setActionNote("");
		}
		else {
			dossierSyncImpl.setActionNote(actionNote);
		}

		dossierSyncImpl.setSyncType(syncType);
		dossierSyncImpl.setInfoType(infoType);

		if (payload == null) {
			dossierSyncImpl.setPayload("");
		}
		else {
			dossierSyncImpl.setPayload(payload);
		}

		if (serverNo == null) {
			dossierSyncImpl.setServerNo("");
		}
		else {
			dossierSyncImpl.setServerNo(serverNo);
		}

		dossierSyncImpl.setState(state);
		dossierSyncImpl.setRetry(retry);

		if (messageText == null) {
			dossierSyncImpl.setMessageText("");
		}
		else {
			dossierSyncImpl.setMessageText(messageText);
		}

		if (acknowlegement == null) {
			dossierSyncImpl.setAcknowlegement("");
		}
		else {
			dossierSyncImpl.setAcknowlegement(acknowlegement);
		}

		dossierSyncImpl.resetOriginalValues();

		return dossierSyncImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		DossierSyncId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dossierId = objectInput.readLong();
		dossierRefUid = objectInput.readUTF();
		syncRefUid = objectInput.readUTF();

		dossierActionId = objectInput.readLong();
		actionCode = objectInput.readUTF();
		actionName = objectInput.readUTF();
		actionUser = objectInput.readUTF();
		actionNote = objectInput.readUTF();

		syncType = objectInput.readInt();

		infoType = objectInput.readInt();
		payload = objectInput.readUTF();
		serverNo = objectInput.readUTF();

		state = objectInput.readInt();

		retry = objectInput.readInt();
		messageText = objectInput.readUTF();
		acknowlegement = objectInput.readUTF();
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

		objectOutput.writeLong(DossierSyncId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(dossierId);

		if (dossierRefUid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierRefUid);
		}

		if (syncRefUid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(syncRefUid);
		}

		objectOutput.writeLong(dossierActionId);

		if (actionCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(actionCode);
		}

		if (actionName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(actionName);
		}

		if (actionUser == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(actionUser);
		}

		if (actionNote == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(actionNote);
		}

		objectOutput.writeInt(syncType);

		objectOutput.writeInt(infoType);

		if (payload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(payload);
		}

		if (serverNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serverNo);
		}

		objectOutput.writeInt(state);

		objectOutput.writeInt(retry);

		if (messageText == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(messageText);
		}

		if (acknowlegement == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(acknowlegement);
		}
	}

	public String uuid;
	public long DossierSyncId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long dossierId;
	public String dossierRefUid;
	public String syncRefUid;
	public long dossierActionId;
	public String actionCode;
	public String actionName;
	public String actionUser;
	public String actionNote;
	public int syncType;
	public int infoType;
	public String payload;
	public String serverNo;
	public int state;
	public int retry;
	public String messageText;
	public String acknowlegement;
}