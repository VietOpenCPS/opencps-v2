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

import org.opencps.datamgt.model.DictItemMapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DictItemMapping in entity cache.
 *
 * @author khoavu
 * @see DictItemMapping
 * @generated
 */
@ProviderType
public class DictItemMappingCacheModel implements CacheModel<DictItemMapping>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictItemMappingCacheModel)) {
			return false;
		}

		DictItemMappingCacheModel dictItemMappingCacheModel = (DictItemMappingCacheModel)obj;

		if (mappingId == dictItemMappingCacheModel.mappingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mappingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{mappingId=");
		sb.append(mappingId);
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
		sb.append(", itemCode=");
		sb.append(itemCode);
		sb.append(", itemCodeDVCQG=");
		sb.append(itemCodeDVCQG);
		sb.append(", collectionId=");
		sb.append(collectionId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DictItemMapping toEntityModel() {
		DictItemMappingImpl dictItemMappingImpl = new DictItemMappingImpl();

		dictItemMappingImpl.setMappingId(mappingId);
		dictItemMappingImpl.setGroupId(groupId);
		dictItemMappingImpl.setCompanyId(companyId);
		dictItemMappingImpl.setUserId(userId);

		if (userName == null) {
			dictItemMappingImpl.setUserName("");
		}
		else {
			dictItemMappingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dictItemMappingImpl.setCreateDate(null);
		}
		else {
			dictItemMappingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dictItemMappingImpl.setModifiedDate(null);
		}
		else {
			dictItemMappingImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (itemCode == null) {
			dictItemMappingImpl.setItemCode("");
		}
		else {
			dictItemMappingImpl.setItemCode(itemCode);
		}

		if (itemCodeDVCQG == null) {
			dictItemMappingImpl.setItemCodeDVCQG("");
		}
		else {
			dictItemMappingImpl.setItemCodeDVCQG(itemCodeDVCQG);
		}

		dictItemMappingImpl.setCollectionId(collectionId);

		dictItemMappingImpl.resetOriginalValues();

		return dictItemMappingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mappingId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		itemCode = objectInput.readUTF();
		itemCodeDVCQG = objectInput.readUTF();

		collectionId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(mappingId);

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

		if (itemCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(itemCode);
		}

		if (itemCodeDVCQG == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(itemCodeDVCQG);
		}

		objectOutput.writeLong(collectionId);
	}

	public long mappingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String itemCode;
	public String itemCodeDVCQG;
	public long collectionId;
}