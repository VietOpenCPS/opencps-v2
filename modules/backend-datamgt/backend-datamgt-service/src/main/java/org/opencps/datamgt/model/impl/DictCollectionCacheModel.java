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

import org.opencps.datamgt.model.DictCollection;

/**
 * The cache model class for representing DictCollection in entity cache.
 *
 * @author Binhth
 * @see DictCollection
 * @generated
 */
@ProviderType
public class DictCollectionCacheModel implements CacheModel<DictCollection>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictCollectionCacheModel)) {
			return false;
		}

		DictCollectionCacheModel dictCollectionCacheModel = (DictCollectionCacheModel)obj;

		if (dictCollectionId == dictCollectionCacheModel.dictCollectionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dictCollectionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dictCollectionId=");
		sb.append(dictCollectionId);
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
		sb.append(", collectionCode=");
		sb.append(collectionCode);
		sb.append(", collectionName=");
		sb.append(collectionName);
		sb.append(", collectionNameEN=");
		sb.append(collectionNameEN);
		sb.append(", description=");
		sb.append(description);
		sb.append(", dataForm=");
		sb.append(dataForm);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DictCollection toEntityModel() {
		DictCollectionImpl dictCollectionImpl = new DictCollectionImpl();

		if (uuid == null) {
			dictCollectionImpl.setUuid(StringPool.BLANK);
		}
		else {
			dictCollectionImpl.setUuid(uuid);
		}

		dictCollectionImpl.setDictCollectionId(dictCollectionId);
		dictCollectionImpl.setCompanyId(companyId);
		dictCollectionImpl.setGroupId(groupId);
		dictCollectionImpl.setUserId(userId);

		if (userName == null) {
			dictCollectionImpl.setUserName(StringPool.BLANK);
		}
		else {
			dictCollectionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dictCollectionImpl.setCreateDate(null);
		}
		else {
			dictCollectionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dictCollectionImpl.setModifiedDate(null);
		}
		else {
			dictCollectionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (collectionCode == null) {
			dictCollectionImpl.setCollectionCode(StringPool.BLANK);
		}
		else {
			dictCollectionImpl.setCollectionCode(collectionCode);
		}

		if (collectionName == null) {
			dictCollectionImpl.setCollectionName(StringPool.BLANK);
		}
		else {
			dictCollectionImpl.setCollectionName(collectionName);
		}

		if (collectionNameEN == null) {
			dictCollectionImpl.setCollectionNameEN(StringPool.BLANK);
		}
		else {
			dictCollectionImpl.setCollectionNameEN(collectionNameEN);
		}

		if (description == null) {
			dictCollectionImpl.setDescription(StringPool.BLANK);
		}
		else {
			dictCollectionImpl.setDescription(description);
		}

		if (dataForm == null) {
			dictCollectionImpl.setDataForm(StringPool.BLANK);
		}
		else {
			dictCollectionImpl.setDataForm(dataForm);
		}

		dictCollectionImpl.resetOriginalValues();

		return dictCollectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dictCollectionId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		collectionCode = objectInput.readUTF();
		collectionName = objectInput.readUTF();
		collectionNameEN = objectInput.readUTF();
		description = objectInput.readUTF();
		dataForm = objectInput.readUTF();
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

		objectOutput.writeLong(dictCollectionId);

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

		if (collectionCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(collectionCode);
		}

		if (collectionName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(collectionName);
		}

		if (collectionNameEN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(collectionNameEN);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (dataForm == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dataForm);
		}
	}

	public String uuid;
	public long dictCollectionId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String collectionCode;
	public String collectionName;
	public String collectionNameEN;
	public String description;
	public String dataForm;
}