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

import org.opencps.dossiermgt.model.PublishQueue;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PublishQueue in entity cache.
 *
 * @author huymq
 * @see PublishQueue
 * @generated
 */
@ProviderType
public class PublishQueueCacheModel implements CacheModel<PublishQueue>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PublishQueueCacheModel)) {
			return false;
		}

		PublishQueueCacheModel publishQueueCacheModel = (PublishQueueCacheModel)obj;

		if (publishQueueId == publishQueueCacheModel.publishQueueId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, publishQueueId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", publishQueueId=");
		sb.append(publishQueueId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", dossierId=");
		sb.append(dossierId);
		sb.append(", serverNo=");
		sb.append(serverNo);
		sb.append(", status=");
		sb.append(status);
		sb.append(", retry=");
		sb.append(retry);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PublishQueue toEntityModel() {
		PublishQueueImpl publishQueueImpl = new PublishQueueImpl();

		if (uuid == null) {
			publishQueueImpl.setUuid("");
		}
		else {
			publishQueueImpl.setUuid(uuid);
		}

		publishQueueImpl.setPublishQueueId(publishQueueId);
		publishQueueImpl.setGroupId(groupId);
		publishQueueImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			publishQueueImpl.setCreateDate(null);
		}
		else {
			publishQueueImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			publishQueueImpl.setModifiedDate(null);
		}
		else {
			publishQueueImpl.setModifiedDate(new Date(modifiedDate));
		}

		publishQueueImpl.setDossierId(dossierId);

		if (serverNo == null) {
			publishQueueImpl.setServerNo("");
		}
		else {
			publishQueueImpl.setServerNo(serverNo);
		}

		publishQueueImpl.setStatus(status);
		publishQueueImpl.setRetry(retry);

		publishQueueImpl.resetOriginalValues();

		return publishQueueImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		publishQueueId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dossierId = objectInput.readLong();
		serverNo = objectInput.readUTF();

		status = objectInput.readInt();

		retry = objectInput.readInt();
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

		objectOutput.writeLong(publishQueueId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(dossierId);

		if (serverNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serverNo);
		}

		objectOutput.writeInt(status);

		objectOutput.writeInt(retry);
	}

	public String uuid;
	public long publishQueueId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long dossierId;
	public String serverNo;
	public int status;
	public int retry;
}