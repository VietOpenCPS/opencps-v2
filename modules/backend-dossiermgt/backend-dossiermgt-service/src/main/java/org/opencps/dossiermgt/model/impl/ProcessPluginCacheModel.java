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

import org.opencps.dossiermgt.model.ProcessPlugin;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProcessPlugin in entity cache.
 *
 * @author huymq
 * @see ProcessPlugin
 * @generated
 */
@ProviderType
public class ProcessPluginCacheModel implements CacheModel<ProcessPlugin>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessPluginCacheModel)) {
			return false;
		}

		ProcessPluginCacheModel processPluginCacheModel = (ProcessPluginCacheModel)obj;

		if (processPluginId == processPluginCacheModel.processPluginId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, processPluginId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", processPluginId=");
		sb.append(processPluginId);
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
		sb.append(", pluginName=");
		sb.append(pluginName);
		sb.append(", pluginType=");
		sb.append(pluginType);
		sb.append(", sequenceNo=");
		sb.append(sequenceNo);
		sb.append(", pluginForm=");
		sb.append(pluginForm);
		sb.append(", sampleData=");
		sb.append(sampleData);
		sb.append(", autoRun=");
		sb.append(autoRun);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProcessPlugin toEntityModel() {
		ProcessPluginImpl processPluginImpl = new ProcessPluginImpl();

		if (uuid == null) {
			processPluginImpl.setUuid("");
		}
		else {
			processPluginImpl.setUuid(uuid);
		}

		processPluginImpl.setProcessPluginId(processPluginId);
		processPluginImpl.setCompanyId(companyId);
		processPluginImpl.setGroupId(groupId);
		processPluginImpl.setUserId(userId);

		if (userName == null) {
			processPluginImpl.setUserName("");
		}
		else {
			processPluginImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			processPluginImpl.setCreateDate(null);
		}
		else {
			processPluginImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			processPluginImpl.setModifiedDate(null);
		}
		else {
			processPluginImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (stepCode == null) {
			processPluginImpl.setStepCode("");
		}
		else {
			processPluginImpl.setStepCode(stepCode);
		}

		processPluginImpl.setServiceProcessId(serviceProcessId);

		if (pluginName == null) {
			processPluginImpl.setPluginName("");
		}
		else {
			processPluginImpl.setPluginName(pluginName);
		}

		processPluginImpl.setPluginType(pluginType);

		if (sequenceNo == null) {
			processPluginImpl.setSequenceNo("");
		}
		else {
			processPluginImpl.setSequenceNo(sequenceNo);
		}

		if (pluginForm == null) {
			processPluginImpl.setPluginForm("");
		}
		else {
			processPluginImpl.setPluginForm(pluginForm);
		}

		if (sampleData == null) {
			processPluginImpl.setSampleData("");
		}
		else {
			processPluginImpl.setSampleData(sampleData);
		}

		processPluginImpl.setAutoRun(autoRun);

		processPluginImpl.resetOriginalValues();

		return processPluginImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		processPluginId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		stepCode = objectInput.readUTF();

		serviceProcessId = objectInput.readLong();
		pluginName = objectInput.readUTF();

		pluginType = objectInput.readInt();
		sequenceNo = objectInput.readUTF();
		pluginForm = objectInput.readUTF();
		sampleData = objectInput.readUTF();

		autoRun = objectInput.readBoolean();
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

		objectOutput.writeLong(processPluginId);

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

		if (pluginName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(pluginName);
		}

		objectOutput.writeInt(pluginType);

		if (sequenceNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sequenceNo);
		}

		if (pluginForm == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(pluginForm);
		}

		if (sampleData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sampleData);
		}

		objectOutput.writeBoolean(autoRun);
	}

	public String uuid;
	public long processPluginId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String stepCode;
	public long serviceProcessId;
	public String pluginName;
	public int pluginType;
	public String sequenceNo;
	public String pluginForm;
	public String sampleData;
	public boolean autoRun;
}