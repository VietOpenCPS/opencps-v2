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

import org.opencps.dossiermgt.model.ActionConfig;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ActionConfig in entity cache.
 *
 * @author huymq
 * @see ActionConfig
 * @generated
 */
@ProviderType
public class ActionConfigCacheModel implements CacheModel<ActionConfig>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ActionConfigCacheModel)) {
			return false;
		}

		ActionConfigCacheModel actionConfigCacheModel = (ActionConfigCacheModel)obj;

		if (actionConfigId == actionConfigCacheModel.actionConfigId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, actionConfigId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", actionConfigId=");
		sb.append(actionConfigId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", actionCode=");
		sb.append(actionCode);
		sb.append(", actionName=");
		sb.append(actionName);
		sb.append(", extraForm=");
		sb.append(extraForm);
		sb.append(", formConfig=");
		sb.append(formConfig);
		sb.append(", sampleData=");
		sb.append(sampleData);
		sb.append(", insideProcess=");
		sb.append(insideProcess);
		sb.append(", userNote=");
		sb.append(userNote);
		sb.append(", syncType=");
		sb.append(syncType);
		sb.append(", eventType=");
		sb.append(eventType);
		sb.append(", infoType=");
		sb.append(infoType);
		sb.append(", pending=");
		sb.append(pending);
		sb.append(", rollbackable=");
		sb.append(rollbackable);
		sb.append(", notificationType=");
		sb.append(notificationType);
		sb.append(", documentType=");
		sb.append(documentType);
		sb.append(", mappingAction=");
		sb.append(mappingAction);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ActionConfig toEntityModel() {
		ActionConfigImpl actionConfigImpl = new ActionConfigImpl();

		if (uuid == null) {
			actionConfigImpl.setUuid("");
		}
		else {
			actionConfigImpl.setUuid(uuid);
		}

		actionConfigImpl.setActionConfigId(actionConfigId);
		actionConfigImpl.setCompanyId(companyId);
		actionConfigImpl.setGroupId(groupId);
		actionConfigImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			actionConfigImpl.setCreateDate(null);
		}
		else {
			actionConfigImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			actionConfigImpl.setModifiedDate(null);
		}
		else {
			actionConfigImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (actionCode == null) {
			actionConfigImpl.setActionCode("");
		}
		else {
			actionConfigImpl.setActionCode(actionCode);
		}

		if (actionName == null) {
			actionConfigImpl.setActionName("");
		}
		else {
			actionConfigImpl.setActionName(actionName);
		}

		actionConfigImpl.setExtraForm(extraForm);

		if (formConfig == null) {
			actionConfigImpl.setFormConfig("");
		}
		else {
			actionConfigImpl.setFormConfig(formConfig);
		}

		if (sampleData == null) {
			actionConfigImpl.setSampleData("");
		}
		else {
			actionConfigImpl.setSampleData(sampleData);
		}

		actionConfigImpl.setInsideProcess(insideProcess);
		actionConfigImpl.setUserNote(userNote);
		actionConfigImpl.setSyncType(syncType);
		actionConfigImpl.setEventType(eventType);
		actionConfigImpl.setInfoType(infoType);
		actionConfigImpl.setPending(pending);
		actionConfigImpl.setRollbackable(rollbackable);

		if (notificationType == null) {
			actionConfigImpl.setNotificationType("");
		}
		else {
			actionConfigImpl.setNotificationType(notificationType);
		}

		if (documentType == null) {
			actionConfigImpl.setDocumentType("");
		}
		else {
			actionConfigImpl.setDocumentType(documentType);
		}

		if (mappingAction == null) {
			actionConfigImpl.setMappingAction("");
		}
		else {
			actionConfigImpl.setMappingAction(mappingAction);
		}

		actionConfigImpl.resetOriginalValues();

		return actionConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		actionConfigId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		actionCode = objectInput.readUTF();
		actionName = objectInput.readUTF();

		extraForm = objectInput.readBoolean();
		formConfig = objectInput.readUTF();
		sampleData = objectInput.readUTF();

		insideProcess = objectInput.readBoolean();

		userNote = objectInput.readInt();

		syncType = objectInput.readInt();

		eventType = objectInput.readInt();

		infoType = objectInput.readInt();

		pending = objectInput.readBoolean();

		rollbackable = objectInput.readBoolean();
		notificationType = objectInput.readUTF();
		documentType = objectInput.readUTF();
		mappingAction = objectInput.readUTF();
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

		objectOutput.writeLong(actionConfigId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

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

		objectOutput.writeBoolean(extraForm);

		if (formConfig == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formConfig);
		}

		if (sampleData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sampleData);
		}

		objectOutput.writeBoolean(insideProcess);

		objectOutput.writeInt(userNote);

		objectOutput.writeInt(syncType);

		objectOutput.writeInt(eventType);

		objectOutput.writeInt(infoType);

		objectOutput.writeBoolean(pending);

		objectOutput.writeBoolean(rollbackable);

		if (notificationType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(notificationType);
		}

		if (documentType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(documentType);
		}

		if (mappingAction == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mappingAction);
		}
	}

	public String uuid;
	public long actionConfigId;
	public long companyId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String actionCode;
	public String actionName;
	public boolean extraForm;
	public String formConfig;
	public String sampleData;
	public boolean insideProcess;
	public int userNote;
	public int syncType;
	public int eventType;
	public int infoType;
	public boolean pending;
	public boolean rollbackable;
	public String notificationType;
	public String documentType;
	public String mappingAction;
}