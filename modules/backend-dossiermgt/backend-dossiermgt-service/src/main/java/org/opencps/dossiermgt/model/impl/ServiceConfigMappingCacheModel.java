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

import org.opencps.dossiermgt.model.ServiceConfigMapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ServiceConfigMapping in entity cache.
 *
 * @author huymq
 * @see ServiceConfigMapping
 * @generated
 */
@ProviderType
public class ServiceConfigMappingCacheModel implements CacheModel<ServiceConfigMapping>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceConfigMappingCacheModel)) {
			return false;
		}

		ServiceConfigMappingCacheModel serviceConfigMappingCacheModel = (ServiceConfigMappingCacheModel)obj;

		if (serviceConfigMappingId == serviceConfigMappingCacheModel.serviceConfigMappingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, serviceConfigMappingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", serviceConfigMappingId=");
		sb.append(serviceConfigMappingId);
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
		sb.append(", serviceConfigCode=");
		sb.append(serviceConfigCode);
		sb.append(", serviceConfigName=");
		sb.append(serviceConfigName);
		sb.append(", serviceCode=");
		sb.append(serviceCode);
		sb.append(", serviceName=");
		sb.append(serviceName);
		sb.append(", govAgencyName=");
		sb.append(govAgencyName);
		sb.append(", domainName=");
		sb.append(domainName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ServiceConfigMapping toEntityModel() {
		ServiceConfigMappingImpl serviceConfigMappingImpl = new ServiceConfigMappingImpl();

		if (uuid == null) {
			serviceConfigMappingImpl.setUuid("");
		}
		else {
			serviceConfigMappingImpl.setUuid(uuid);
		}

		serviceConfigMappingImpl.setServiceConfigMappingId(serviceConfigMappingId);
		serviceConfigMappingImpl.setGroupId(groupId);
		serviceConfigMappingImpl.setCompanyId(companyId);
		serviceConfigMappingImpl.setUserId(userId);

		if (userName == null) {
			serviceConfigMappingImpl.setUserName("");
		}
		else {
			serviceConfigMappingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			serviceConfigMappingImpl.setCreateDate(null);
		}
		else {
			serviceConfigMappingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			serviceConfigMappingImpl.setModifiedDate(null);
		}
		else {
			serviceConfigMappingImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (serviceConfigCode == null) {
			serviceConfigMappingImpl.setServiceConfigCode("");
		}
		else {
			serviceConfigMappingImpl.setServiceConfigCode(serviceConfigCode);
		}

		if (serviceConfigName == null) {
			serviceConfigMappingImpl.setServiceConfigName("");
		}
		else {
			serviceConfigMappingImpl.setServiceConfigName(serviceConfigName);
		}

		if (serviceCode == null) {
			serviceConfigMappingImpl.setServiceCode("");
		}
		else {
			serviceConfigMappingImpl.setServiceCode(serviceCode);
		}

		if (serviceName == null) {
			serviceConfigMappingImpl.setServiceName("");
		}
		else {
			serviceConfigMappingImpl.setServiceName(serviceName);
		}

		if (govAgencyName == null) {
			serviceConfigMappingImpl.setGovAgencyName("");
		}
		else {
			serviceConfigMappingImpl.setGovAgencyName(govAgencyName);
		}

		if (domainName == null) {
			serviceConfigMappingImpl.setDomainName("");
		}
		else {
			serviceConfigMappingImpl.setDomainName(domainName);
		}

		serviceConfigMappingImpl.resetOriginalValues();

		return serviceConfigMappingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		serviceConfigMappingId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		serviceConfigCode = objectInput.readUTF();
		serviceConfigName = objectInput.readUTF();
		serviceCode = objectInput.readUTF();
		serviceName = objectInput.readUTF();
		govAgencyName = objectInput.readUTF();
		domainName = objectInput.readUTF();
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

		objectOutput.writeLong(serviceConfigMappingId);

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

		if (serviceConfigCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceConfigCode);
		}

		if (serviceConfigName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceConfigName);
		}

		if (serviceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceCode);
		}

		if (serviceName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceName);
		}

		if (govAgencyName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(govAgencyName);
		}

		if (domainName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(domainName);
		}
	}

	public String uuid;
	public long serviceConfigMappingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String serviceConfigCode;
	public String serviceConfigName;
	public String serviceCode;
	public String serviceName;
	public String govAgencyName;
	public String domainName;
}