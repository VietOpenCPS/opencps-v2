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

import org.opencps.dossiermgt.model.ServiceInfoMapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ServiceInfoMapping in entity cache.
 *
 * @author huymq
 * @see ServiceInfoMapping
 * @generated
 */
@ProviderType
public class ServiceInfoMappingCacheModel implements CacheModel<ServiceInfoMapping>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceInfoMappingCacheModel)) {
			return false;
		}

		ServiceInfoMappingCacheModel serviceInfoMappingCacheModel = (ServiceInfoMappingCacheModel)obj;

		if (serviceInfoMappingId == serviceInfoMappingCacheModel.serviceInfoMappingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, serviceInfoMappingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{serviceInfoMappingId=");
		sb.append(serviceInfoMappingId);
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
		sb.append(", serviceCode=");
		sb.append(serviceCode);
		sb.append(", serviceCodeDVCQG=");
		sb.append(serviceCodeDVCQG);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ServiceInfoMapping toEntityModel() {
		ServiceInfoMappingImpl serviceInfoMappingImpl = new ServiceInfoMappingImpl();

		serviceInfoMappingImpl.setServiceInfoMappingId(serviceInfoMappingId);
		serviceInfoMappingImpl.setGroupId(groupId);
		serviceInfoMappingImpl.setCompanyId(companyId);
		serviceInfoMappingImpl.setUserId(userId);

		if (userName == null) {
			serviceInfoMappingImpl.setUserName("");
		}
		else {
			serviceInfoMappingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			serviceInfoMappingImpl.setCreateDate(null);
		}
		else {
			serviceInfoMappingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			serviceInfoMappingImpl.setModifiedDate(null);
		}
		else {
			serviceInfoMappingImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (serviceCode == null) {
			serviceInfoMappingImpl.setServiceCode("");
		}
		else {
			serviceInfoMappingImpl.setServiceCode(serviceCode);
		}

		if (serviceCodeDVCQG == null) {
			serviceInfoMappingImpl.setServiceCodeDVCQG("");
		}
		else {
			serviceInfoMappingImpl.setServiceCodeDVCQG(serviceCodeDVCQG);
		}

		serviceInfoMappingImpl.resetOriginalValues();

		return serviceInfoMappingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		serviceInfoMappingId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		serviceCode = objectInput.readUTF();
		serviceCodeDVCQG = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(serviceInfoMappingId);

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

		if (serviceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceCode);
		}

		if (serviceCodeDVCQG == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceCodeDVCQG);
		}
	}

	public long serviceInfoMappingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String serviceCode;
	public String serviceCodeDVCQG;
}