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

import org.opencps.usermgt.model.Visibility;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Visibility in entity cache.
 *
 * @author khoavu
 * @see Visibility
 * @generated
 */
@ProviderType
public class VisibilityCacheModel implements CacheModel<Visibility>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VisibilityCacheModel)) {
			return false;
		}

		VisibilityCacheModel visibilityCacheModel = (VisibilityCacheModel)obj;

		if (visibilityId == visibilityCacheModel.visibilityId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, visibilityId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", visibilityId=");
		sb.append(visibilityId);
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
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", visibility=");
		sb.append(visibility);
		sb.append(", security=");
		sb.append(security);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Visibility toEntityModel() {
		VisibilityImpl visibilityImpl = new VisibilityImpl();

		if (uuid == null) {
			visibilityImpl.setUuid("");
		}
		else {
			visibilityImpl.setUuid(uuid);
		}

		visibilityImpl.setVisibilityId(visibilityId);
		visibilityImpl.setCompanyId(companyId);
		visibilityImpl.setGroupId(groupId);
		visibilityImpl.setUserId(userId);

		if (userName == null) {
			visibilityImpl.setUserName("");
		}
		else {
			visibilityImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			visibilityImpl.setCreateDate(null);
		}
		else {
			visibilityImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			visibilityImpl.setModifiedDate(null);
		}
		else {
			visibilityImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (className == null) {
			visibilityImpl.setClassName("");
		}
		else {
			visibilityImpl.setClassName(className);
		}

		if (classPK == null) {
			visibilityImpl.setClassPK("");
		}
		else {
			visibilityImpl.setClassPK(classPK);
		}

		visibilityImpl.setVisibility(visibility);

		if (security == null) {
			visibilityImpl.setSecurity("");
		}
		else {
			visibilityImpl.setSecurity(security);
		}

		visibilityImpl.resetOriginalValues();

		return visibilityImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		visibilityId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		className = objectInput.readUTF();
		classPK = objectInput.readUTF();

		visibility = objectInput.readInt();
		security = objectInput.readUTF();
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

		objectOutput.writeLong(visibilityId);

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

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}

		if (classPK == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(classPK);
		}

		objectOutput.writeInt(visibility);

		if (security == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(security);
		}
	}

	public String uuid;
	public long visibilityId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String className;
	public String classPK;
	public int visibility;
	public String security;
}