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

import org.opencps.dossiermgt.model.NewsBoard;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing NewsBoard in entity cache.
 *
 * @author huymq
 * @see NewsBoard
 * @generated
 */
@ProviderType
public class NewsBoardCacheModel implements CacheModel<NewsBoard>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NewsBoardCacheModel)) {
			return false;
		}

		NewsBoardCacheModel newsBoardCacheModel = (NewsBoardCacheModel)obj;

		if (newsBoardId == newsBoardCacheModel.newsBoardId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, newsBoardId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", newsBoardId=");
		sb.append(newsBoardId);
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
		sb.append(", newsTitle=");
		sb.append(newsTitle);
		sb.append(", newsContent=");
		sb.append(newsContent);
		sb.append(", newsStatus=");
		sb.append(newsStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NewsBoard toEntityModel() {
		NewsBoardImpl newsBoardImpl = new NewsBoardImpl();

		if (uuid == null) {
			newsBoardImpl.setUuid("");
		}
		else {
			newsBoardImpl.setUuid(uuid);
		}

		newsBoardImpl.setNewsBoardId(newsBoardId);
		newsBoardImpl.setGroupId(groupId);
		newsBoardImpl.setCompanyId(companyId);
		newsBoardImpl.setUserId(userId);

		if (userName == null) {
			newsBoardImpl.setUserName("");
		}
		else {
			newsBoardImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			newsBoardImpl.setCreateDate(null);
		}
		else {
			newsBoardImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			newsBoardImpl.setModifiedDate(null);
		}
		else {
			newsBoardImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (newsTitle == null) {
			newsBoardImpl.setNewsTitle("");
		}
		else {
			newsBoardImpl.setNewsTitle(newsTitle);
		}

		if (newsContent == null) {
			newsBoardImpl.setNewsContent("");
		}
		else {
			newsBoardImpl.setNewsContent(newsContent);
		}

		newsBoardImpl.setNewsStatus(newsStatus);

		newsBoardImpl.resetOriginalValues();

		return newsBoardImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		newsBoardId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		newsTitle = objectInput.readUTF();
		newsContent = objectInput.readUTF();

		newsStatus = objectInput.readInt();
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

		objectOutput.writeLong(newsBoardId);

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

		if (newsTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(newsTitle);
		}

		if (newsContent == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(newsContent);
		}

		objectOutput.writeInt(newsStatus);
	}

	public String uuid;
	public long newsBoardId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String newsTitle;
	public String newsContent;
	public int newsStatus;
}