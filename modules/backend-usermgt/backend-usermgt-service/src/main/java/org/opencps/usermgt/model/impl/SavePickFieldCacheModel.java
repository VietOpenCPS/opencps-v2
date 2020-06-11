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

package org.opencps.usermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.usermgt.model.SavePickField;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SavePickField in entity cache.
 *
 * @author khoavu
 * @see SavePickField
 * @generated
 */
@ProviderType
public class SavePickFieldCacheModel implements CacheModel<SavePickField>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SavePickFieldCacheModel)) {
			return false;
		}

		SavePickFieldCacheModel savePickFieldCacheModel = (SavePickFieldCacheModel)obj;

		if (fieldPickId == savePickFieldCacheModel.fieldPickId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, fieldPickId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", fieldPickId=");
		sb.append(fieldPickId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", formData=");
		sb.append(formData);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SavePickField toEntityModel() {
		SavePickFieldImpl savePickFieldImpl = new SavePickFieldImpl();

		if (uuid == null) {
			savePickFieldImpl.setUuid("");
		}
		else {
			savePickFieldImpl.setUuid(uuid);
		}

		savePickFieldImpl.setFieldPickId(fieldPickId);
		savePickFieldImpl.setGroupId(groupId);
		savePickFieldImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			savePickFieldImpl.setCreateDate(null);
		}
		else {
			savePickFieldImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			savePickFieldImpl.setModifiedDate(null);
		}
		else {
			savePickFieldImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (formData == null) {
			savePickFieldImpl.setFormData("");
		}
		else {
			savePickFieldImpl.setFormData(formData);
		}

		if (classPK == null) {
			savePickFieldImpl.setClassPK("");
		}
		else {
			savePickFieldImpl.setClassPK(classPK);
		}

		savePickFieldImpl.resetOriginalValues();

		return savePickFieldImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		fieldPickId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		formData = objectInput.readUTF();
		classPK = objectInput.readUTF();
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

		objectOutput.writeLong(fieldPickId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (formData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formData);
		}

		if (classPK == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(classPK);
		}
	}

	public String uuid;
	public long fieldPickId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String formData;
	public String classPK;
}