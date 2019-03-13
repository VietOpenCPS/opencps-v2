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

import org.opencps.dossiermgt.model.DeliverableTypeRole;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DeliverableTypeRole in entity cache.
 *
 * @author huymq
 * @see DeliverableTypeRole
 * @generated
 */
@ProviderType
public class DeliverableTypeRoleCacheModel implements CacheModel<DeliverableTypeRole>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeliverableTypeRoleCacheModel)) {
			return false;
		}

		DeliverableTypeRoleCacheModel deliverableTypeRoleCacheModel = (DeliverableTypeRoleCacheModel)obj;

		if (deliverableTypeRoleId == deliverableTypeRoleCacheModel.deliverableTypeRoleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, deliverableTypeRoleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", deliverableTypeRoleId=");
		sb.append(deliverableTypeRoleId);
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
		sb.append(", deliverableTypeId=");
		sb.append(deliverableTypeId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append(", moderator=");
		sb.append(moderator);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DeliverableTypeRole toEntityModel() {
		DeliverableTypeRoleImpl deliverableTypeRoleImpl = new DeliverableTypeRoleImpl();

		if (uuid == null) {
			deliverableTypeRoleImpl.setUuid("");
		}
		else {
			deliverableTypeRoleImpl.setUuid(uuid);
		}

		deliverableTypeRoleImpl.setDeliverableTypeRoleId(deliverableTypeRoleId);
		deliverableTypeRoleImpl.setGroupId(groupId);
		deliverableTypeRoleImpl.setCompanyId(companyId);
		deliverableTypeRoleImpl.setUserId(userId);

		if (userName == null) {
			deliverableTypeRoleImpl.setUserName("");
		}
		else {
			deliverableTypeRoleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			deliverableTypeRoleImpl.setCreateDate(null);
		}
		else {
			deliverableTypeRoleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			deliverableTypeRoleImpl.setModifiedDate(null);
		}
		else {
			deliverableTypeRoleImpl.setModifiedDate(new Date(modifiedDate));
		}

		deliverableTypeRoleImpl.setDeliverableTypeId(deliverableTypeId);
		deliverableTypeRoleImpl.setRoleId(roleId);
		deliverableTypeRoleImpl.setModerator(moderator);

		deliverableTypeRoleImpl.resetOriginalValues();

		return deliverableTypeRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		deliverableTypeRoleId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		deliverableTypeId = objectInput.readLong();

		roleId = objectInput.readLong();

		moderator = objectInput.readBoolean();
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

		objectOutput.writeLong(deliverableTypeRoleId);

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

		objectOutput.writeLong(deliverableTypeId);

		objectOutput.writeLong(roleId);

		objectOutput.writeBoolean(moderator);
	}

	public String uuid;
	public long deliverableTypeRoleId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long deliverableTypeId;
	public long roleId;
	public boolean moderator;
}