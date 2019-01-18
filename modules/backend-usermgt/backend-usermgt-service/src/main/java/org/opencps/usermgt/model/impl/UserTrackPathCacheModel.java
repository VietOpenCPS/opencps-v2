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

import org.opencps.usermgt.model.UserTrackPath;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserTrackPath in entity cache.
 *
 * @author khoavu
 * @see UserTrackPath
 * @generated
 */
@ProviderType
public class UserTrackPathCacheModel implements CacheModel<UserTrackPath>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserTrackPathCacheModel)) {
			return false;
		}

		UserTrackPathCacheModel userTrackPathCacheModel = (UserTrackPathCacheModel)obj;

		if (userTrackPathId == userTrackPathCacheModel.userTrackPathId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userTrackPathId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", userTrackPathId=");
		sb.append(userTrackPathId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", userLoginId=");
		sb.append(userLoginId);
		sb.append(", path=");
		sb.append(path);
		sb.append(", pathDate=");
		sb.append(pathDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserTrackPath toEntityModel() {
		UserTrackPathImpl userTrackPathImpl = new UserTrackPathImpl();

		if (uuid == null) {
			userTrackPathImpl.setUuid("");
		}
		else {
			userTrackPathImpl.setUuid(uuid);
		}

		userTrackPathImpl.setUserTrackPathId(userTrackPathId);
		userTrackPathImpl.setCompanyId(companyId);

		if (modifiedDate == Long.MIN_VALUE) {
			userTrackPathImpl.setModifiedDate(null);
		}
		else {
			userTrackPathImpl.setModifiedDate(new Date(modifiedDate));
		}

		userTrackPathImpl.setUserLoginId(userLoginId);

		if (path == null) {
			userTrackPathImpl.setPath("");
		}
		else {
			userTrackPathImpl.setPath(path);
		}

		if (pathDate == Long.MIN_VALUE) {
			userTrackPathImpl.setPathDate(null);
		}
		else {
			userTrackPathImpl.setPathDate(new Date(pathDate));
		}

		userTrackPathImpl.resetOriginalValues();

		return userTrackPathImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		userTrackPathId = objectInput.readLong();

		companyId = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		userLoginId = objectInput.readLong();
		path = objectInput.readUTF();
		pathDate = objectInput.readLong();
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

		objectOutput.writeLong(userTrackPathId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(userLoginId);

		if (path == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(path);
		}

		objectOutput.writeLong(pathDate);
	}

	public String uuid;
	public long userTrackPathId;
	public long companyId;
	public long modifiedDate;
	public long userLoginId;
	public String path;
	public long pathDate;
}