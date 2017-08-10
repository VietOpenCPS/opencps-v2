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

import org.opencps.backend.processmgt.model.ProcessStep;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProcessStep in entity cache.
 *
 * @author khoavu
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
		StringBundler sb = new StringBundler(33);

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
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProcessStep toEntityModel() {
		ProcessStepImpl processStepImpl = new ProcessStepImpl();

		if (uuid == null) {
			processStepImpl.setUuid(StringPool.BLANK);
		}
		else {
			processStepImpl.setUuid(uuid);
		}

		processStepImpl.setProcessStepId(processStepId);
		processStepImpl.setCompanyId(companyId);
		processStepImpl.setGroupId(groupId);
		processStepImpl.setUserId(userId);

		if (userName == null) {
			processStepImpl.setUserName(StringPool.BLANK);
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
			processStepImpl.setStepCode(StringPool.BLANK);
		}
		else {
			processStepImpl.setStepCode(stepCode);
		}

		processStepImpl.setServiceProcessId(serviceProcessId);

		if (stepName == null) {
			processStepImpl.setStepName(StringPool.BLANK);
		}
		else {
			processStepImpl.setStepName(stepName);
		}

		if (sequenceNo == null) {
			processStepImpl.setSequenceNo(StringPool.BLANK);
		}
		else {
			processStepImpl.setSequenceNo(sequenceNo);
		}

		if (dossierStatus == null) {
			processStepImpl.setDossierStatus(StringPool.BLANK);
		}
		else {
			processStepImpl.setDossierStatus(dossierStatus);
		}

		if (dossierSubStatus == null) {
			processStepImpl.setDossierSubStatus(StringPool.BLANK);
		}
		else {
			processStepImpl.setDossierSubStatus(dossierSubStatus);
		}

		processStepImpl.setDurationCount(durationCount);

		if (customProcessUrl == null) {
			processStepImpl.setCustomProcessUrl(StringPool.BLANK);
		}
		else {
			processStepImpl.setCustomProcessUrl(customProcessUrl);
		}

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

		durationCount = objectInput.readInt();
		customProcessUrl = objectInput.readUTF();
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

		objectOutput.writeLong(processStepId);

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

		if (stepCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stepCode);
		}

		objectOutput.writeLong(serviceProcessId);

		if (stepName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stepName);
		}

		if (sequenceNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sequenceNo);
		}

		if (dossierStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierStatus);
		}

		if (dossierSubStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierSubStatus);
		}

		objectOutput.writeInt(durationCount);

		if (customProcessUrl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(customProcessUrl);
		}
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
	public int durationCount;
	public String customProcessUrl;
}