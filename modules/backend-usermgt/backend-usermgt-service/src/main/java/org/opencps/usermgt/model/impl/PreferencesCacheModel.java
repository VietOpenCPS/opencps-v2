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

import org.opencps.usermgt.model.Preferences;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Preferences in entity cache.
 *
 * @author khoavu
 * @see Preferences
 * @generated
 */
@ProviderType
public class PreferencesCacheModel implements CacheModel<Preferences>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PreferencesCacheModel)) {
			return false;
		}

		PreferencesCacheModel preferencesCacheModel = (PreferencesCacheModel)obj;

		if (preferencesId == preferencesCacheModel.preferencesId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, preferencesId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", preferencesId=");
		sb.append(preferencesId);
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
		sb.append(", preferences=");
		sb.append(preferences);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Preferences toEntityModel() {
		PreferencesImpl preferencesImpl = new PreferencesImpl();

		if (uuid == null) {
			preferencesImpl.setUuid("");
		}
		else {
			preferencesImpl.setUuid(uuid);
		}

		preferencesImpl.setPreferencesId(preferencesId);
		preferencesImpl.setGroupId(groupId);
		preferencesImpl.setCompanyId(companyId);
		preferencesImpl.setUserId(userId);

		if (userName == null) {
			preferencesImpl.setUserName("");
		}
		else {
			preferencesImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			preferencesImpl.setCreateDate(null);
		}
		else {
			preferencesImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			preferencesImpl.setModifiedDate(null);
		}
		else {
			preferencesImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (preferences == null) {
			preferencesImpl.setPreferences("");
		}
		else {
			preferencesImpl.setPreferences(preferences);
		}

		preferencesImpl.resetOriginalValues();

		return preferencesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		preferencesId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		preferences = objectInput.readUTF();
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

		objectOutput.writeLong(preferencesId);

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

		if (preferences == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(preferences);
		}
	}

	public String uuid;
	public long preferencesId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String preferences;
}