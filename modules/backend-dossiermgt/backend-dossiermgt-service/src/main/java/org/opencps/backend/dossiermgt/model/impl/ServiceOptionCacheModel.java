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

package org.opencps.backend.dossiermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.opencps.backend.dossiermgt.model.ServiceOption;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ServiceOption in entity cache.
 *
 * @author huymq
 * @see ServiceOption
 * @generated
 */
@ProviderType
public class ServiceOptionCacheModel implements CacheModel<ServiceOption>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceOptionCacheModel)) {
			return false;
		}

		ServiceOptionCacheModel serviceOptionCacheModel = (ServiceOptionCacheModel)obj;

		if (serviceOptionId == serviceOptionCacheModel.serviceOptionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, serviceOptionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", serviceOptionId=");
		sb.append(serviceOptionId);
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
		sb.append(", optionCode=");
		sb.append(optionCode);
		sb.append(", optionName=");
		sb.append(optionName);
		sb.append(", optionOrder=");
		sb.append(optionOrder);
		sb.append(", autoSelect=");
		sb.append(autoSelect);
		sb.append(", dossierTemplateId=");
		sb.append(dossierTemplateId);
		sb.append(", serviceProcessId=");
		sb.append(serviceProcessId);
		sb.append(", instructionNote=");
		sb.append(instructionNote);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ServiceOption toEntityModel() {
		ServiceOptionImpl serviceOptionImpl = new ServiceOptionImpl();

		if (uuid == null) {
			serviceOptionImpl.setUuid(StringPool.BLANK);
		}
		else {
			serviceOptionImpl.setUuid(uuid);
		}

		serviceOptionImpl.setServiceOptionId(serviceOptionId);
		serviceOptionImpl.setGroupId(groupId);
		serviceOptionImpl.setCompanyId(companyId);
		serviceOptionImpl.setUserId(userId);

		if (userName == null) {
			serviceOptionImpl.setUserName(StringPool.BLANK);
		}
		else {
			serviceOptionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			serviceOptionImpl.setCreateDate(null);
		}
		else {
			serviceOptionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			serviceOptionImpl.setModifiedDate(null);
		}
		else {
			serviceOptionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (optionCode == null) {
			serviceOptionImpl.setOptionCode(StringPool.BLANK);
		}
		else {
			serviceOptionImpl.setOptionCode(optionCode);
		}

		if (optionName == null) {
			serviceOptionImpl.setOptionName(StringPool.BLANK);
		}
		else {
			serviceOptionImpl.setOptionName(optionName);
		}

		serviceOptionImpl.setOptionOrder(optionOrder);

		if (autoSelect == null) {
			serviceOptionImpl.setAutoSelect(StringPool.BLANK);
		}
		else {
			serviceOptionImpl.setAutoSelect(autoSelect);
		}

		serviceOptionImpl.setDossierTemplateId(dossierTemplateId);
		serviceOptionImpl.setServiceProcessId(serviceProcessId);

		if (instructionNote == null) {
			serviceOptionImpl.setInstructionNote(StringPool.BLANK);
		}
		else {
			serviceOptionImpl.setInstructionNote(instructionNote);
		}

		serviceOptionImpl.resetOriginalValues();

		return serviceOptionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		serviceOptionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		optionCode = objectInput.readUTF();
		optionName = objectInput.readUTF();

		optionOrder = objectInput.readInt();
		autoSelect = objectInput.readUTF();

		dossierTemplateId = objectInput.readLong();

		serviceProcessId = objectInput.readLong();
		instructionNote = objectInput.readUTF();
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

		objectOutput.writeLong(serviceOptionId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (optionCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(optionCode);
		}

		if (optionName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(optionName);
		}

		objectOutput.writeInt(optionOrder);

		if (autoSelect == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(autoSelect);
		}

		objectOutput.writeLong(dossierTemplateId);

		objectOutput.writeLong(serviceProcessId);

		if (instructionNote == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(instructionNote);
		}
	}

	public String uuid;
	public long serviceOptionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String optionCode;
	public String optionName;
	public int optionOrder;
	public String autoSelect;
	public long dossierTemplateId;
	public long serviceProcessId;
	public String instructionNote;
}