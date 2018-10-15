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

import org.opencps.dossiermgt.model.ProcessOption;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProcessOption in entity cache.
 *
 * @author huymq
 * @see ProcessOption
 * @generated
 */
@ProviderType
public class ProcessOptionCacheModel implements CacheModel<ProcessOption>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessOptionCacheModel)) {
			return false;
		}

		ProcessOptionCacheModel processOptionCacheModel = (ProcessOptionCacheModel)obj;

		if (processOptionId == processOptionCacheModel.processOptionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, processOptionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", processOptionId=");
		sb.append(processOptionId);
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
		sb.append(", serviceConfigId=");
		sb.append(serviceConfigId);
		sb.append(", optionOrder=");
		sb.append(optionOrder);
		sb.append(", optionName=");
		sb.append(optionName);
		sb.append(", autoSelect=");
		sb.append(autoSelect);
		sb.append(", dossierTemplateId=");
		sb.append(dossierTemplateId);
		sb.append(", serviceProcessId=");
		sb.append(serviceProcessId);
		sb.append(", instructionNote=");
		sb.append(instructionNote);
		sb.append(", submissionNote=");
		sb.append(submissionNote);
		sb.append(", sampleCount=");
		sb.append(sampleCount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProcessOption toEntityModel() {
		ProcessOptionImpl processOptionImpl = new ProcessOptionImpl();

		if (uuid == null) {
			processOptionImpl.setUuid("");
		}
		else {
			processOptionImpl.setUuid(uuid);
		}

		processOptionImpl.setProcessOptionId(processOptionId);
		processOptionImpl.setGroupId(groupId);
		processOptionImpl.setCompanyId(companyId);
		processOptionImpl.setUserId(userId);

		if (userName == null) {
			processOptionImpl.setUserName("");
		}
		else {
			processOptionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			processOptionImpl.setCreateDate(null);
		}
		else {
			processOptionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			processOptionImpl.setModifiedDate(null);
		}
		else {
			processOptionImpl.setModifiedDate(new Date(modifiedDate));
		}

		processOptionImpl.setServiceConfigId(serviceConfigId);
		processOptionImpl.setOptionOrder(optionOrder);

		if (optionName == null) {
			processOptionImpl.setOptionName("");
		}
		else {
			processOptionImpl.setOptionName(optionName);
		}

		if (autoSelect == null) {
			processOptionImpl.setAutoSelect("");
		}
		else {
			processOptionImpl.setAutoSelect(autoSelect);
		}

		processOptionImpl.setDossierTemplateId(dossierTemplateId);
		processOptionImpl.setServiceProcessId(serviceProcessId);

		if (instructionNote == null) {
			processOptionImpl.setInstructionNote("");
		}
		else {
			processOptionImpl.setInstructionNote(instructionNote);
		}

		if (submissionNote == null) {
			processOptionImpl.setSubmissionNote("");
		}
		else {
			processOptionImpl.setSubmissionNote(submissionNote);
		}

		processOptionImpl.setSampleCount(sampleCount);

		processOptionImpl.resetOriginalValues();

		return processOptionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		processOptionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		serviceConfigId = objectInput.readLong();

		optionOrder = objectInput.readInt();
		optionName = objectInput.readUTF();
		autoSelect = objectInput.readUTF();

		dossierTemplateId = objectInput.readLong();

		serviceProcessId = objectInput.readLong();
		instructionNote = objectInput.readUTF();
		submissionNote = objectInput.readUTF();

		sampleCount = objectInput.readLong();
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

		objectOutput.writeLong(processOptionId);

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

		objectOutput.writeLong(serviceConfigId);

		objectOutput.writeInt(optionOrder);

		if (optionName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(optionName);
		}

		if (autoSelect == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(autoSelect);
		}

		objectOutput.writeLong(dossierTemplateId);

		objectOutput.writeLong(serviceProcessId);

		if (instructionNote == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(instructionNote);
		}

		if (submissionNote == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(submissionNote);
		}

		objectOutput.writeLong(sampleCount);
	}

	public String uuid;
	public long processOptionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long serviceConfigId;
	public int optionOrder;
	public String optionName;
	public String autoSelect;
	public long dossierTemplateId;
	public long serviceProcessId;
	public String instructionNote;
	public String submissionNote;
	public long sampleCount;
}