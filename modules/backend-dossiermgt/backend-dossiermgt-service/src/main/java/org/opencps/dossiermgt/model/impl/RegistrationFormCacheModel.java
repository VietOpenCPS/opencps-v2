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

import org.opencps.dossiermgt.model.RegistrationForm;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RegistrationForm in entity cache.
 *
 * @author huymq
 * @see RegistrationForm
 * @generated
 */
@ProviderType
public class RegistrationFormCacheModel implements CacheModel<RegistrationForm>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RegistrationFormCacheModel)) {
			return false;
		}

		RegistrationFormCacheModel registrationFormCacheModel = (RegistrationFormCacheModel)obj;

		if (registrationFormId == registrationFormCacheModel.registrationFormId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, registrationFormId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", registrationFormId=");
		sb.append(registrationFormId);
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
		sb.append(", registrationId=");
		sb.append(registrationId);
		sb.append(", referenceUid=");
		sb.append(referenceUid);
		sb.append(", formNo=");
		sb.append(formNo);
		sb.append(", formName=");
		sb.append(formName);
		sb.append(", formData=");
		sb.append(formData);
		sb.append(", formScript=");
		sb.append(formScript);
		sb.append(", formReport=");
		sb.append(formReport);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", isNew=");
		sb.append(isNew);
		sb.append(", removed=");
		sb.append(removed);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RegistrationForm toEntityModel() {
		RegistrationFormImpl registrationFormImpl = new RegistrationFormImpl();

		if (uuid == null) {
			registrationFormImpl.setUuid("");
		}
		else {
			registrationFormImpl.setUuid(uuid);
		}

		registrationFormImpl.setRegistrationFormId(registrationFormId);
		registrationFormImpl.setCompanyId(companyId);
		registrationFormImpl.setGroupId(groupId);
		registrationFormImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			registrationFormImpl.setCreateDate(null);
		}
		else {
			registrationFormImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			registrationFormImpl.setModifiedDate(null);
		}
		else {
			registrationFormImpl.setModifiedDate(new Date(modifiedDate));
		}

		registrationFormImpl.setRegistrationId(registrationId);

		if (referenceUid == null) {
			registrationFormImpl.setReferenceUid("");
		}
		else {
			registrationFormImpl.setReferenceUid(referenceUid);
		}

		if (formNo == null) {
			registrationFormImpl.setFormNo("");
		}
		else {
			registrationFormImpl.setFormNo(formNo);
		}

		if (formName == null) {
			registrationFormImpl.setFormName("");
		}
		else {
			registrationFormImpl.setFormName(formName);
		}

		if (formData == null) {
			registrationFormImpl.setFormData("");
		}
		else {
			registrationFormImpl.setFormData(formData);
		}

		if (formScript == null) {
			registrationFormImpl.setFormScript("");
		}
		else {
			registrationFormImpl.setFormScript(formScript);
		}

		if (formReport == null) {
			registrationFormImpl.setFormReport("");
		}
		else {
			registrationFormImpl.setFormReport(formReport);
		}

		registrationFormImpl.setFileEntryId(fileEntryId);
		registrationFormImpl.setIsNew(isNew);
		registrationFormImpl.setRemoved(removed);

		registrationFormImpl.resetOriginalValues();

		return registrationFormImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		registrationFormId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		registrationId = objectInput.readLong();
		referenceUid = objectInput.readUTF();
		formNo = objectInput.readUTF();
		formName = objectInput.readUTF();
		formData = objectInput.readUTF();
		formScript = objectInput.readUTF();
		formReport = objectInput.readUTF();

		fileEntryId = objectInput.readLong();

		isNew = objectInput.readBoolean();

		removed = objectInput.readBoolean();
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

		objectOutput.writeLong(registrationFormId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(registrationId);

		if (referenceUid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(referenceUid);
		}

		if (formNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formNo);
		}

		if (formName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formName);
		}

		if (formData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formData);
		}

		if (formScript == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formScript);
		}

		if (formReport == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formReport);
		}

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeBoolean(isNew);

		objectOutput.writeBoolean(removed);
	}

	public String uuid;
	public long registrationFormId;
	public long companyId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long registrationId;
	public String referenceUid;
	public String formNo;
	public String formName;
	public String formData;
	public String formScript;
	public String formReport;
	public long fileEntryId;
	public boolean isNew;
	public boolean removed;
}