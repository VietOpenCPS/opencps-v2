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

package org.opencps.backend.dossiermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.opencps.backend.dossiermgt.model.ServiceInfo;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ServiceInfo in entity cache.
 *
 * @author huymq
 * @see ServiceInfo
 * @generated
 */
@ProviderType
public class ServiceInfoCacheModel implements CacheModel<ServiceInfo>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceInfoCacheModel)) {
			return false;
		}

		ServiceInfoCacheModel serviceInfoCacheModel = (ServiceInfoCacheModel)obj;

		if (serviceInfoId == serviceInfoCacheModel.serviceInfoId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, serviceInfoId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(55);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", serviceInfoId=");
		sb.append(serviceInfoId);
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
		sb.append(", serviceName=");
		sb.append(serviceName);
		sb.append(", processText=");
		sb.append(processText);
		sb.append(", methodText=");
		sb.append(methodText);
		sb.append(", dossierText=");
		sb.append(dossierText);
		sb.append(", conditionText=");
		sb.append(conditionText);
		sb.append(", durationText=");
		sb.append(durationText);
		sb.append(", applicantText=");
		sb.append(applicantText);
		sb.append(", resultText=");
		sb.append(resultText);
		sb.append(", regularText=");
		sb.append(regularText);
		sb.append(", feeText=");
		sb.append(feeText);
		sb.append(", administrationCode=");
		sb.append(administrationCode);
		sb.append(", administrationName=");
		sb.append(administrationName);
		sb.append(", administrationIndex=");
		sb.append(administrationIndex);
		sb.append(", domainCode=");
		sb.append(domainCode);
		sb.append(", domainName=");
		sb.append(domainName);
		sb.append(", domainIndex=");
		sb.append(domainIndex);
		sb.append(", activeStatus=");
		sb.append(activeStatus);
		sb.append(", maxLevel=");
		sb.append(maxLevel);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ServiceInfo toEntityModel() {
		ServiceInfoImpl serviceInfoImpl = new ServiceInfoImpl();

		if (uuid == null) {
			serviceInfoImpl.setUuid(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setUuid(uuid);
		}

		serviceInfoImpl.setServiceInfoId(serviceInfoId);
		serviceInfoImpl.setGroupId(groupId);
		serviceInfoImpl.setCompanyId(companyId);
		serviceInfoImpl.setUserId(userId);

		if (userName == null) {
			serviceInfoImpl.setUserName(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			serviceInfoImpl.setCreateDate(null);
		}
		else {
			serviceInfoImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			serviceInfoImpl.setModifiedDate(null);
		}
		else {
			serviceInfoImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (serviceCode == null) {
			serviceInfoImpl.setServiceCode(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setServiceCode(serviceCode);
		}

		if (serviceName == null) {
			serviceInfoImpl.setServiceName(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setServiceName(serviceName);
		}

		if (processText == null) {
			serviceInfoImpl.setProcessText(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setProcessText(processText);
		}

		if (methodText == null) {
			serviceInfoImpl.setMethodText(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setMethodText(methodText);
		}

		if (dossierText == null) {
			serviceInfoImpl.setDossierText(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setDossierText(dossierText);
		}

		if (conditionText == null) {
			serviceInfoImpl.setConditionText(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setConditionText(conditionText);
		}

		if (durationText == null) {
			serviceInfoImpl.setDurationText(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setDurationText(durationText);
		}

		if (applicantText == null) {
			serviceInfoImpl.setApplicantText(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setApplicantText(applicantText);
		}

		if (resultText == null) {
			serviceInfoImpl.setResultText(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setResultText(resultText);
		}

		if (regularText == null) {
			serviceInfoImpl.setRegularText(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setRegularText(regularText);
		}

		if (feeText == null) {
			serviceInfoImpl.setFeeText(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setFeeText(feeText);
		}

		if (administrationCode == null) {
			serviceInfoImpl.setAdministrationCode(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setAdministrationCode(administrationCode);
		}

		if (administrationName == null) {
			serviceInfoImpl.setAdministrationName(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setAdministrationName(administrationName);
		}

		if (administrationIndex == null) {
			serviceInfoImpl.setAdministrationIndex(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setAdministrationIndex(administrationIndex);
		}

		if (domainCode == null) {
			serviceInfoImpl.setDomainCode(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setDomainCode(domainCode);
		}

		if (domainName == null) {
			serviceInfoImpl.setDomainName(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setDomainName(domainName);
		}

		if (domainIndex == null) {
			serviceInfoImpl.setDomainIndex(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setDomainIndex(domainIndex);
		}

		if (activeStatus == null) {
			serviceInfoImpl.setActiveStatus(StringPool.BLANK);
		}
		else {
			serviceInfoImpl.setActiveStatus(activeStatus);
		}

		serviceInfoImpl.setMaxLevel(maxLevel);

		serviceInfoImpl.resetOriginalValues();

		return serviceInfoImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		serviceInfoId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		serviceCode = objectInput.readUTF();
		serviceName = objectInput.readUTF();
		processText = objectInput.readUTF();
		methodText = objectInput.readUTF();
		dossierText = objectInput.readUTF();
		conditionText = objectInput.readUTF();
		durationText = objectInput.readUTF();
		applicantText = objectInput.readUTF();
		resultText = objectInput.readUTF();
		regularText = objectInput.readUTF();
		feeText = objectInput.readUTF();
		administrationCode = objectInput.readUTF();
		administrationName = objectInput.readUTF();
		administrationIndex = objectInput.readUTF();
		domainCode = objectInput.readUTF();
		domainName = objectInput.readUTF();
		domainIndex = objectInput.readUTF();
		activeStatus = objectInput.readUTF();

		maxLevel = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(serviceInfoId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (serviceCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(serviceCode);
		}

		if (serviceName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(serviceName);
		}

		if (processText == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(processText);
		}

		if (methodText == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(methodText);
		}

		if (dossierText == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierText);
		}

		if (conditionText == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(conditionText);
		}

		if (durationText == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(durationText);
		}

		if (applicantText == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(applicantText);
		}

		if (resultText == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(resultText);
		}

		if (regularText == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(regularText);
		}

		if (feeText == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(feeText);
		}

		if (administrationCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(administrationCode);
		}

		if (administrationName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(administrationName);
		}

		if (administrationIndex == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(administrationIndex);
		}

		if (domainCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(domainCode);
		}

		if (domainName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(domainName);
		}

		if (domainIndex == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(domainIndex);
		}

		if (activeStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(activeStatus);
		}

		objectOutput.writeInt(maxLevel);
	}

	public String uuid;
	public long serviceInfoId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String serviceCode;
	public String serviceName;
	public String processText;
	public String methodText;
	public String dossierText;
	public String conditionText;
	public String durationText;
	public String applicantText;
	public String resultText;
	public String regularText;
	public String feeText;
	public String administrationCode;
	public String administrationName;
	public String administrationIndex;
	public String domainCode;
	public String domainName;
	public String domainIndex;
	public String activeStatus;
	public int maxLevel;
}