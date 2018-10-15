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

import org.opencps.dossiermgt.model.ProcessStep;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProcessStep in entity cache.
 *
 * @author huymq
 * @see ProcessStep
 * @generated
 */
@ProviderType
public class ProcessStepCacheModel implements CacheModel<ProcessStep>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessStepCacheModel)) {
			return false;
		}

		ProcessStepCacheModel processStepCacheModel = (ProcessStepCacheModel)obj;

		if (processStepId == processStepCacheModel.processStepId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, processStepId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", processStepId=");
		sb.append(processStepId);
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
		sb.append(", stepCode=");
		sb.append(stepCode);
		sb.append(", serviceProcessId=");
		sb.append(serviceProcessId);
		sb.append(", stepName=");
		sb.append(stepName);
		sb.append(", sequenceNo=");
		sb.append(sequenceNo);
		sb.append(", dossierStatus=");
		sb.append(dossierStatus);
		sb.append(", dossierSubStatus=");
		sb.append(dossierSubStatus);
		sb.append(", durationCount=");
		sb.append(durationCount);
		sb.append(", customProcessUrl=");
		sb.append(customProcessUrl);
		sb.append(", stepInstruction=");
		sb.append(stepInstruction);
		sb.append(", briefNote=");
		sb.append(briefNote);
		sb.append(", editable=");
		sb.append(editable);
		sb.append(", restrictDossier=");
		sb.append(restrictDossier);
		sb.append(", lockState=");
		sb.append(lockState);
		sb.append(", groupName=");
		sb.append(groupName);
		sb.append(", roleAsStep=");
		sb.append(roleAsStep);
		sb.append(", checkInput=");
		sb.append(checkInput);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProcessStep toEntityModel() {
		ProcessStepImpl processStepImpl = new ProcessStepImpl();

		if (uuid == null) {
			processStepImpl.setUuid("");
		}
		else {
			processStepImpl.setUuid(uuid);
		}

		processStepImpl.setProcessStepId(processStepId);
		processStepImpl.setCompanyId(companyId);
		processStepImpl.setGroupId(groupId);
		processStepImpl.setUserId(userId);

		if (userName == null) {
			processStepImpl.setUserName("");
		}
		else {
			processStepImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			processStepImpl.setCreateDate(null);
		}
		else {
			processStepImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			processStepImpl.setModifiedDate(null);
		}
		else {
			processStepImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (stepCode == null) {
			processStepImpl.setStepCode("");
		}
		else {
			processStepImpl.setStepCode(stepCode);
		}

		processStepImpl.setServiceProcessId(serviceProcessId);

		if (stepName == null) {
			processStepImpl.setStepName("");
		}
		else {
			processStepImpl.setStepName(stepName);
		}

		if (sequenceNo == null) {
			processStepImpl.setSequenceNo("");
		}
		else {
			processStepImpl.setSequenceNo(sequenceNo);
		}

		if (dossierStatus == null) {
			processStepImpl.setDossierStatus("");
		}
		else {
			processStepImpl.setDossierStatus(dossierStatus);
		}

		if (dossierSubStatus == null) {
			processStepImpl.setDossierSubStatus("");
		}
		else {
			processStepImpl.setDossierSubStatus(dossierSubStatus);
		}

		processStepImpl.setDurationCount(durationCount);

		if (customProcessUrl == null) {
			processStepImpl.setCustomProcessUrl("");
		}
		else {
			processStepImpl.setCustomProcessUrl(customProcessUrl);
		}

		if (stepInstruction == null) {
			processStepImpl.setStepInstruction("");
		}
		else {
			processStepImpl.setStepInstruction(stepInstruction);
		}

		if (briefNote == null) {
			processStepImpl.setBriefNote("");
		}
		else {
			processStepImpl.setBriefNote(briefNote);
		}

		processStepImpl.setEditable(editable);

		if (restrictDossier == null) {
			processStepImpl.setRestrictDossier("");
		}
		else {
			processStepImpl.setRestrictDossier(restrictDossier);
		}

		if (lockState == null) {
			processStepImpl.setLockState("");
		}
		else {
			processStepImpl.setLockState(lockState);
		}

		if (groupName == null) {
			processStepImpl.setGroupName("");
		}
		else {
			processStepImpl.setGroupName(groupName);
		}

		if (roleAsStep == null) {
			processStepImpl.setRoleAsStep("");
		}
		else {
			processStepImpl.setRoleAsStep(roleAsStep);
		}

		processStepImpl.setCheckInput(checkInput);

		processStepImpl.resetOriginalValues();

		return processStepImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		processStepId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		stepCode = objectInput.readUTF();

		serviceProcessId = objectInput.readLong();
		stepName = objectInput.readUTF();
		sequenceNo = objectInput.readUTF();
		dossierStatus = objectInput.readUTF();
		dossierSubStatus = objectInput.readUTF();

		durationCount = objectInput.readDouble();
		customProcessUrl = objectInput.readUTF();
		stepInstruction = objectInput.readUTF();
		briefNote = objectInput.readUTF();

		editable = objectInput.readBoolean();
		restrictDossier = objectInput.readUTF();
		lockState = objectInput.readUTF();
		groupName = objectInput.readUTF();
		roleAsStep = objectInput.readUTF();

		checkInput = objectInput.readInt();
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

		objectOutput.writeLong(processStepId);

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

		if (stepCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stepCode);
		}

		objectOutput.writeLong(serviceProcessId);

		if (stepName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stepName);
		}

		if (sequenceNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sequenceNo);
		}

		if (dossierStatus == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierStatus);
		}

		if (dossierSubStatus == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierSubStatus);
		}

		objectOutput.writeDouble(durationCount);

		if (customProcessUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(customProcessUrl);
		}

		if (stepInstruction == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stepInstruction);
		}

		if (briefNote == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(briefNote);
		}

		objectOutput.writeBoolean(editable);

		if (restrictDossier == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(restrictDossier);
		}

		if (lockState == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lockState);
		}

		if (groupName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(groupName);
		}

		if (roleAsStep == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(roleAsStep);
		}

		objectOutput.writeInt(checkInput);
	}

	public String uuid;
	public long processStepId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String stepCode;
	public long serviceProcessId;
	public String stepName;
	public String sequenceNo;
	public String dossierStatus;
	public String dossierSubStatus;
	public double durationCount;
	public String customProcessUrl;
	public String stepInstruction;
	public String briefNote;
	public boolean editable;
	public String restrictDossier;
	public String lockState;
	public String groupName;
	public String roleAsStep;
	public int checkInput;
}