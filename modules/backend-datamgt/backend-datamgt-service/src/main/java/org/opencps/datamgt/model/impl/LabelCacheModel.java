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

package org.opencps.datamgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.opencps.datamgt.model.Label;

/**
 * The cache model class for representing Label in entity cache.
 *
 * @author Binhth
 * @see Label
 * @generated
 */
@ProviderType
public class LabelCacheModel implements CacheModel<Label>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LabelCacheModel)) {
			return false;
		}

		LabelCacheModel labelCacheModel = (LabelCacheModel)obj;

		if (labelId == labelCacheModel.labelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, labelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", labelId=");
		sb.append(labelId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", color=");
		sb.append(color);
		sb.append(", scope=");
		sb.append(scope);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Label toEntityModel() {
		LabelImpl labelImpl = new LabelImpl();

		if (uuid == null) {
			labelImpl.setUuid(StringPool.BLANK);
		}
		else {
			labelImpl.setUuid(uuid);
		}

		labelImpl.setLabelId(labelId);
		labelImpl.setCompanyId(companyId);
		labelImpl.setGroupId(groupId);
		labelImpl.setUserId(userId);

		if (userName == null) {
			labelImpl.setUserName(StringPool.BLANK);
		}
		else {
			labelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			labelImpl.setCreateDate(null);
		}
		else {
			labelImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			labelImpl.setModifiedDate(null);
		}
		else {
			labelImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			labelImpl.setName(StringPool.BLANK);
		}
		else {
			labelImpl.setName(name);
		}

		if (color == null) {
			labelImpl.setColor(StringPool.BLANK);
		}
		else {
			labelImpl.setColor(color);
		}

		labelImpl.setScope(scope);

		labelImpl.resetOriginalValues();

		return labelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		labelId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		color = objectInput.readUTF();

		scope = objectInput.readInt();
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

		objectOutput.writeLong(labelId);

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

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (color == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(color);
		}

		objectOutput.writeInt(scope);
	}

	public String uuid;
	public long labelId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String color;
	public int scope;
}