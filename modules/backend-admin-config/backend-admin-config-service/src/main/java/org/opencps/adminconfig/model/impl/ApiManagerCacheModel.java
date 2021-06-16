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

package org.opencps.adminconfig.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.adminconfig.model.ApiManager;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ApiManager in entity cache.
 *
 * @author binhth
 * @see ApiManager
 * @generated
 */
@ProviderType
public class ApiManagerCacheModel implements CacheModel<ApiManager>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApiManagerCacheModel)) {
			return false;
		}

		ApiManagerCacheModel apiManagerCacheModel = (ApiManagerCacheModel)obj;

		if (apiManagerId == apiManagerCacheModel.apiManagerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, apiManagerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{apiManagerId=");
		sb.append(apiManagerId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", apiCode=");
		sb.append(apiCode);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", apiName=");
		sb.append(apiName);
		sb.append(", apiDescription=");
		sb.append(apiDescription);
		sb.append(", apiStatus=");
		sb.append(apiStatus);
		sb.append(", className=");
		sb.append(className);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ApiManager toEntityModel() {
		ApiManagerImpl apiManagerImpl = new ApiManagerImpl();

		apiManagerImpl.setApiManagerId(apiManagerId);
		apiManagerImpl.setGroupId(groupId);

		if (apiCode == null) {
			apiManagerImpl.setApiCode("");
		}
		else {
			apiManagerImpl.setApiCode(apiCode);
		}

		if (createDate == Long.MIN_VALUE) {
			apiManagerImpl.setCreateDate(null);
		}
		else {
			apiManagerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			apiManagerImpl.setModifiedDate(null);
		}
		else {
			apiManagerImpl.setModifiedDate(new Date(modifiedDate));
		}

		apiManagerImpl.setUserId(userId);

		if (apiName == null) {
			apiManagerImpl.setApiName("");
		}
		else {
			apiManagerImpl.setApiName(apiName);
		}

		if (apiDescription == null) {
			apiManagerImpl.setApiDescription("");
		}
		else {
			apiManagerImpl.setApiDescription(apiDescription);
		}

		apiManagerImpl.setApiStatus(apiStatus);

		if (className == null) {
			apiManagerImpl.setClassName("");
		}
		else {
			apiManagerImpl.setClassName(className);
		}

		apiManagerImpl.resetOriginalValues();

		return apiManagerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		apiManagerId = objectInput.readLong();

		groupId = objectInput.readLong();
		apiCode = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		userId = objectInput.readLong();
		apiName = objectInput.readUTF();
		apiDescription = objectInput.readUTF();

		apiStatus = objectInput.readInt();
		className = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(apiManagerId);

		objectOutput.writeLong(groupId);

		if (apiCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(apiCode);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(userId);

		if (apiName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(apiName);
		}

		if (apiDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(apiDescription);
		}

		objectOutput.writeInt(apiStatus);

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}
	}

	public long apiManagerId;
	public long groupId;
	public String apiCode;
	public long createDate;
	public long modifiedDate;
	public long userId;
	public String apiName;
	public String apiDescription;
	public int apiStatus;
	public String className;
}