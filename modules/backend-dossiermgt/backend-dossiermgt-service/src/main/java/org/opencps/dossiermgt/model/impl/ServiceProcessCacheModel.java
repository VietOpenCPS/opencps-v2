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

import org.opencps.dossiermgt.model.ServiceProcess;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ServiceProcess in entity cache.
 *
 * @author huymq
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
		StringBundler sb = new StringBundler(49);

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
		sb.append(", processName=");
		sb.append(processName);
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
		sb.append(", generatePassword=");
		sb.append(generatePassword);
		sb.append(", directNotification=");
		sb.append(directNotification);
		sb.append(", serverNo=");
		sb.append(serverNo);
		sb.append(", serverName=");
		sb.append(serverName);
		sb.append(", requestPayment=");
		sb.append(requestPayment);
		sb.append(", paymentFee=");
		sb.append(paymentFee);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ServiceProcess toEntityModel() {
		ServiceProcessImpl serviceProcessImpl = new ServiceProcessImpl();

		if (uuid == null) {
			serviceProcessImpl.setUuid("");
		}
		else {
			serviceProcessImpl.setUuid(uuid);
		}

		serviceProcessImpl.setServiceProcessId(serviceProcessId);
		serviceProcessImpl.setCompanyId(companyId);
		serviceProcessImpl.setGroupId(groupId);
		serviceProcessImpl.setUserId(userId);

		if (userName == null) {
			serviceProcessImpl.setUserName("");
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
			serviceProcessImpl.setProcessNo("");
		}
		else {
			serviceProcessImpl.setProcessNo(processNo);
		}

		if (processName == null) {
			serviceProcessImpl.setProcessName("");
		}
		else {
			serviceProcessImpl.setProcessName(processName);
		}

		if (description == null) {
			serviceProcessImpl.setDescription("");
		}
		else {
			serviceProcessImpl.setDescription(description);
		}

		serviceProcessImpl.setDurationCount(durationCount);
		serviceProcessImpl.setDurationUnit(durationUnit);
		serviceProcessImpl.setCounter(counter);
		serviceProcessImpl.setGenerateDossierNo(generateDossierNo);

		if (dossierNoPattern == null) {
			serviceProcessImpl.setDossierNoPattern("");
		}
		else {
			serviceProcessImpl.setDossierNoPattern(dossierNoPattern);
		}

		serviceProcessImpl.setGenerateDueDate(generateDueDate);

		if (dueDatePattern == null) {
			serviceProcessImpl.setDueDatePattern("");
		}
		else {
			serviceProcessImpl.setDueDatePattern(dueDatePattern);
		}

		serviceProcessImpl.setGeneratePassword(generatePassword);
		serviceProcessImpl.setDirectNotification(directNotification);

		if (serverNo == null) {
			serviceProcessImpl.setServerNo("");
		}
		else {
			serviceProcessImpl.setServerNo(serverNo);
		}

		if (serverName == null) {
			serviceProcessImpl.setServerName("");
		}
		else {
			serviceProcessImpl.setServerName(serverName);
		}

		serviceProcessImpl.setRequestPayment(requestPayment);

		if (paymentFee == null) {
			serviceProcessImpl.setPaymentFee("");
		}
		else {
			serviceProcessImpl.setPaymentFee(paymentFee);
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
		processName = objectInput.readUTF();
		description = objectInput.readUTF();

		durationCount = objectInput.readDouble();

		durationUnit = objectInput.readInt();

		counter = objectInput.readLong();

		generateDossierNo = objectInput.readBoolean();
		dossierNoPattern = objectInput.readUTF();

		generateDueDate = objectInput.readBoolean();
		dueDatePattern = objectInput.readUTF();

		generatePassword = objectInput.readBoolean();

		directNotification = objectInput.readBoolean();
		serverNo = objectInput.readUTF();
		serverName = objectInput.readUTF();

		requestPayment = objectInput.readBoolean();
		paymentFee = objectInput.readUTF();
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

		objectOutput.writeLong(serviceProcessId);

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

		if (processNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(processNo);
		}

		if (processName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(processName);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeDouble(durationCount);

		objectOutput.writeInt(durationUnit);

		objectOutput.writeLong(counter);

		objectOutput.writeBoolean(generateDossierNo);

		if (dossierNoPattern == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierNoPattern);
		}

		objectOutput.writeBoolean(generateDueDate);

		if (dueDatePattern == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dueDatePattern);
		}

		objectOutput.writeBoolean(generatePassword);

		objectOutput.writeBoolean(directNotification);

		if (serverNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serverNo);
		}

		if (serverName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serverName);
		}

		objectOutput.writeBoolean(requestPayment);

		if (paymentFee == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(paymentFee);
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
	public String processName;
	public String description;
	public double durationCount;
	public int durationUnit;
	public long counter;
	public boolean generateDossierNo;
	public String dossierNoPattern;
	public boolean generateDueDate;
	public String dueDatePattern;
	public boolean generatePassword;
	public boolean directNotification;
	public String serverNo;
	public String serverName;
	public boolean requestPayment;
	public String paymentFee;
}