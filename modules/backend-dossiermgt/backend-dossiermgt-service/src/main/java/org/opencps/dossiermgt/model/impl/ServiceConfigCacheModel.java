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

import org.opencps.dossiermgt.model.ServiceConfig;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ServiceConfig in entity cache.
 *
 * @author huymq
 * @see ServiceConfig
 * @generated
 */
@ProviderType
public class ServiceConfigCacheModel implements CacheModel<ServiceConfig>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceConfigCacheModel)) {
			return false;
		}

		ServiceConfigCacheModel serviceConfigCacheModel = (ServiceConfigCacheModel)obj;

		if (serviceConfigId == serviceConfigCacheModel.serviceConfigId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, serviceConfigId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", serviceConfigId=");
		sb.append(serviceConfigId);
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
		sb.append(", serviceInfoId=");
		sb.append(serviceInfoId);
		sb.append(", govAgencyCode=");
		sb.append(govAgencyCode);
		sb.append(", govAgencyName=");
		sb.append(govAgencyName);
		sb.append(", serviceInstruction=");
		sb.append(serviceInstruction);
		sb.append(", serviceLevel=");
		sb.append(serviceLevel);
		sb.append(", serviceUrl=");
		sb.append(serviceUrl);
		sb.append(", forCitizen=");
		sb.append(forCitizen);
		sb.append(", forBusiness=");
		sb.append(forBusiness);
		sb.append(", postService=");
		sb.append(postService);
		sb.append(", registration=");
		sb.append(registration);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ServiceConfig toEntityModel() {
		ServiceConfigImpl serviceConfigImpl = new ServiceConfigImpl();

		if (uuid == null) {
			serviceConfigImpl.setUuid("");
		}
		else {
			serviceConfigImpl.setUuid(uuid);
		}

		serviceConfigImpl.setServiceConfigId(serviceConfigId);
		serviceConfigImpl.setGroupId(groupId);
		serviceConfigImpl.setCompanyId(companyId);
		serviceConfigImpl.setUserId(userId);

		if (userName == null) {
			serviceConfigImpl.setUserName("");
		}
		else {
			serviceConfigImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			serviceConfigImpl.setCreateDate(null);
		}
		else {
			serviceConfigImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			serviceConfigImpl.setModifiedDate(null);
		}
		else {
			serviceConfigImpl.setModifiedDate(new Date(modifiedDate));
		}

		serviceConfigImpl.setServiceInfoId(serviceInfoId);

		if (govAgencyCode == null) {
			serviceConfigImpl.setGovAgencyCode("");
		}
		else {
			serviceConfigImpl.setGovAgencyCode(govAgencyCode);
		}

		if (govAgencyName == null) {
			serviceConfigImpl.setGovAgencyName("");
		}
		else {
			serviceConfigImpl.setGovAgencyName(govAgencyName);
		}

		if (serviceInstruction == null) {
			serviceConfigImpl.setServiceInstruction("");
		}
		else {
			serviceConfigImpl.setServiceInstruction(serviceInstruction);
		}

		serviceConfigImpl.setServiceLevel(serviceLevel);

		if (serviceUrl == null) {
			serviceConfigImpl.setServiceUrl("");
		}
		else {
			serviceConfigImpl.setServiceUrl(serviceUrl);
		}

		serviceConfigImpl.setForCitizen(forCitizen);
		serviceConfigImpl.setForBusiness(forBusiness);
		serviceConfigImpl.setPostService(postService);
		serviceConfigImpl.setRegistration(registration);

		serviceConfigImpl.resetOriginalValues();

		return serviceConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		serviceConfigId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		serviceInfoId = objectInput.readLong();
		govAgencyCode = objectInput.readUTF();
		govAgencyName = objectInput.readUTF();
		serviceInstruction = objectInput.readUTF();

		serviceLevel = objectInput.readInt();
		serviceUrl = objectInput.readUTF();

		forCitizen = objectInput.readBoolean();

		forBusiness = objectInput.readBoolean();

		postService = objectInput.readBoolean();

		registration = objectInput.readBoolean();
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

		objectOutput.writeLong(serviceConfigId);

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

		objectOutput.writeLong(serviceInfoId);

		if (govAgencyCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(govAgencyCode);
		}

		if (govAgencyName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(govAgencyName);
		}

		if (serviceInstruction == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceInstruction);
		}

		objectOutput.writeInt(serviceLevel);

		if (serviceUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceUrl);
		}

		objectOutput.writeBoolean(forCitizen);

		objectOutput.writeBoolean(forBusiness);

		objectOutput.writeBoolean(postService);

		objectOutput.writeBoolean(registration);
	}

	public String uuid;
	public long serviceConfigId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long serviceInfoId;
	public String govAgencyCode;
	public String govAgencyName;
	public String serviceInstruction;
	public int serviceLevel;
	public String serviceUrl;
	public boolean forCitizen;
	public boolean forBusiness;
	public boolean postService;
	public boolean registration;
}