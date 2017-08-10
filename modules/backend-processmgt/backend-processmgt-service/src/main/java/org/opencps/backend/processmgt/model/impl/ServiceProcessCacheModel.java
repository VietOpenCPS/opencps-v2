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

import org.opencps.backend.processmgt.model.ServiceProcess;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ServiceProcess in entity cache.
 *
 * @author khoavu
 * @see ServiceProcess
 * @generated
 */
@ProviderType
public class ServiceProcessCacheModel implements CacheModel<ServiceProcess>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceProcessCacheModel)) {
			return false;
		}

		ServiceProcessCacheModel serviceProcessCacheModel = (ServiceProcessCacheModel)obj;

		if (serviceProcessId == serviceProcessCacheModel.serviceProcessId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, serviceProcessId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", serviceProcessId=");
		sb.append(serviceProcessId);
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
		sb.append(", processNo=");
		sb.append(processNo);
		sb.append(", description=");
		sb.append(description);
		sb.append(", durationCount=");
		sb.append(durationCount);
		sb.append(", durationUnit=");
		sb.append(durationUnit);
		sb.append(", counter=");
		sb.append(counter);
		sb.append(", generateDossierNo=");
		sb.append(generateDossierNo);
		sb.append(", dossierNoPattern=");
		sb.append(dossierNoPattern);
		sb.append(", generateDueDate=");
		sb.append(generateDueDate);
		sb.append(", dueDatePattern=");
		sb.append(dueDatePattern);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ServiceProcess toEntityModel() {
		ServiceProcessImpl serviceProcessImpl = new ServiceProcessImpl();

		if (uuid == null) {
			serviceProcessImpl.setUuid(StringPool.BLANK);
		}
		else {
			serviceProcessImpl.setUuid(uuid);
		}

		serviceProcessImpl.setServiceProcessId(serviceProcessId);
		serviceProcessImpl.setCompanyId(companyId);
		serviceProcessImpl.setGroupId(groupId);
		serviceProcessImpl.setUserId(userId);

		if (userName == null) {
			serviceProcessImpl.setUserName(StringPool.BLANK);
		}
		else {
			serviceProcessImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			serviceProcessImpl.setCreateDate(null);
		}
		else {
			serviceProcessImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			serviceProcessImpl.setModifiedDate(null);
		}
		else {
			serviceProcessImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (processNo == null) {
			serviceProcessImpl.setProcessNo(StringPool.BLANK);
		}
		else {
			serviceProcessImpl.setProcessNo(processNo);
		}

		if (description == null) {
			serviceProcessImpl.setDescription(StringPool.BLANK);
		}
		else {
			serviceProcessImpl.setDescription(description);
		}

		serviceProcessImpl.setDurationCount(durationCount);
		serviceProcessImpl.setDurationUnit(durationUnit);
		serviceProcessImpl.setCounter(counter);

		if (generateDossierNo == null) {
			serviceProcessImpl.setGenerateDossierNo(StringPool.BLANK);
		}
		else {
			serviceProcessImpl.setGenerateDossierNo(generateDossierNo);
		}

		if (dossierNoPattern == null) {
			serviceProcessImpl.setDossierNoPattern(StringPool.BLANK);
		}
		else {
			serviceProcessImpl.setDossierNoPattern(dossierNoPattern);
		}

		if (generateDueDate == null) {
			serviceProcessImpl.setGenerateDueDate(StringPool.BLANK);
		}
		else {
			serviceProcessImpl.setGenerateDueDate(generateDueDate);
		}

		if (dueDatePattern == null) {
			serviceProcessImpl.setDueDatePattern(StringPool.BLANK);
		}
		else {
			serviceProcessImpl.setDueDatePattern(dueDatePattern);
		}

		serviceProcessImpl.resetOriginalValues();

		return serviceProcessImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		serviceProcessId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		processNo = objectInput.readUTF();
		description = objectInput.readUTF();

		durationCount = objectInput.readInt();

		durationUnit = objectInput.readInt();

		counter = objectInput.readInt();
		generateDossierNo = objectInput.readUTF();
		dossierNoPattern = objectInput.readUTF();
		generateDueDate = objectInput.readUTF();
		dueDatePattern = objectInput.readUTF();
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

		objectOutput.writeLong(serviceProcessId);

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

		if (processNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(processNo);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(durationCount);

		objectOutput.writeInt(durationUnit);

		objectOutput.writeInt(counter);

		if (generateDossierNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(generateDossierNo);
		}

		if (dossierNoPattern == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierNoPattern);
		}

		if (generateDueDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(generateDueDate);
		}

		if (dueDatePattern == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dueDatePattern);
		}
	}

	public String uuid;
	public long serviceProcessId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String processNo;
	public String description;
	public int durationCount;
	public int durationUnit;
	public int counter;
	public String generateDossierNo;
	public String dossierNoPattern;
	public String generateDueDate;
	public String dueDatePattern;
}