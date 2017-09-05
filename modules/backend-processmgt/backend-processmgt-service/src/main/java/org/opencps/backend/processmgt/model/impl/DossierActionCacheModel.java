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

package org.opencps.backend.processmgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.opencps.backend.processmgt.model.DossierAction;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DossierAction in entity cache.
 *
 * @author khoavu
 * @see DossierAction
 * @generated
 */
@ProviderType
public class DossierActionCacheModel implements CacheModel<DossierAction>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierActionCacheModel)) {
			return false;
		}

		DossierActionCacheModel dossierActionCacheModel = (DossierActionCacheModel)obj;

		if (dossierActionId == dossierActionCacheModel.dossierActionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierActionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dossierActionId=");
		sb.append(dossierActionId);
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
		sb.append(", dossierId=");
		sb.append(dossierId);
		sb.append(", serviceProcessId=");
		sb.append(serviceProcessId);
		sb.append(", previousActionId=");
		sb.append(previousActionId);
		sb.append(", actionCode=");
		sb.append(actionCode);
		sb.append(", actionUser=");
		sb.append(actionUser);
		sb.append(", actionName=");
		sb.append(actionName);
		sb.append(", actionNote=");
		sb.append(actionNote);
		sb.append(", overDue=");
		sb.append(overDue);
		sb.append(", syncActionCode=");
		sb.append(syncActionCode);
		sb.append(", pending=");
		sb.append(pending);
		sb.append(", rollback=");
		sb.append(rollback);
		sb.append(", processStepId=");
		sb.append(processStepId);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append(", nextActionId=");
		sb.append(nextActionId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierAction toEntityModel() {
		DossierActionImpl dossierActionImpl = new DossierActionImpl();

		if (uuid == null) {
			dossierActionImpl.setUuid(StringPool.BLANK);
		}
		else {
			dossierActionImpl.setUuid(uuid);
		}

		dossierActionImpl.setDossierActionId(dossierActionId);
		dossierActionImpl.setCompanyId(companyId);
		dossierActionImpl.setGroupId(groupId);
		dossierActionImpl.setUserId(userId);

		if (userName == null) {
			dossierActionImpl.setUserName(StringPool.BLANK);
		}
		else {
			dossierActionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dossierActionImpl.setCreateDate(null);
		}
		else {
			dossierActionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dossierActionImpl.setModifiedDate(null);
		}
		else {
			dossierActionImpl.setModifiedDate(new Date(modifiedDate));
		}

		dossierActionImpl.setDossierId(dossierId);
		dossierActionImpl.setServiceProcessId(serviceProcessId);
		dossierActionImpl.setPreviousActionId(previousActionId);

		if (actionCode == null) {
			dossierActionImpl.setActionCode(StringPool.BLANK);
		}
		else {
			dossierActionImpl.setActionCode(actionCode);
		}

		if (actionUser == null) {
			dossierActionImpl.setActionUser(StringPool.BLANK);
		}
		else {
			dossierActionImpl.setActionUser(actionUser);
		}

		if (actionName == null) {
			dossierActionImpl.setActionName(StringPool.BLANK);
		}
		else {
			dossierActionImpl.setActionName(actionName);
		}

		if (actionNote == null) {
			dossierActionImpl.setActionNote(StringPool.BLANK);
		}
		else {
			dossierActionImpl.setActionNote(actionNote);
		}

		dossierActionImpl.setOverDue(overDue);

		if (syncActionCode == null) {
			dossierActionImpl.setSyncActionCode(StringPool.BLANK);
		}
		else {
			dossierActionImpl.setSyncActionCode(syncActionCode);
		}

		if (pending == null) {
			dossierActionImpl.setPending(StringPool.BLANK);
		}
		else {
			dossierActionImpl.setPending(pending);
		}

		if (rollback == null) {
			dossierActionImpl.setRollback(StringPool.BLANK);
		}
		else {
			dossierActionImpl.setRollback(rollback);
		}

		dossierActionImpl.setProcessStepId(processStepId);
		dossierActionImpl.setDueDate(dueDate);
		dossierActionImpl.setNextActionId(nextActionId);

		dossierActionImpl.resetOriginalValues();

		return dossierActionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dossierActionId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dossierId = objectInput.readLong();

		serviceProcessId = objectInput.readLong();

		previousActionId = objectInput.readLong();
		actionCode = objectInput.readUTF();
		actionUser = objectInput.readUTF();
		actionName = objectInput.readUTF();
		actionNote = objectInput.readUTF();

		overDue = objectInput.readInt();
		syncActionCode = objectInput.readUTF();
		pending = objectInput.readUTF();
		rollback = objectInput.readUTF();

		processStepId = objectInput.readLong();

		dueDate = objectInput.readInt();

		nextActionId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(dossierActionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(dossierId);

		objectOutput.writeLong(serviceProcessId);

		objectOutput.writeLong(previousActionId);

		if (actionCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionCode);
		}

		if (actionUser == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionUser);
		}

		if (actionName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionName);
		}

		if (actionNote == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionNote);
		}

		objectOutput.writeInt(overDue);

		if (syncActionCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(syncActionCode);
		}

		if (pending == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(pending);
		}

		if (rollback == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rollback);
		}

		objectOutput.writeLong(processStepId);

		objectOutput.writeInt(dueDate);

		objectOutput.writeLong(nextActionId);
	}

	public String uuid;
	public long dossierActionId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long dossierId;
	public long serviceProcessId;
	public long previousActionId;
	public String actionCode;
	public String actionUser;
	public String actionName;
	public String actionNote;
	public int overDue;
	public String syncActionCode;
	public String pending;
	public String rollback;
	public long processStepId;
	public int dueDate;
	public long nextActionId;
}