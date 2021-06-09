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

package org.opencps.statistic.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.statistic.model.OpencpsDossierStatisticMgt;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OpencpsDossierStatisticMgt in entity cache.
 *
 * @author khoavu
 * @see OpencpsDossierStatisticMgt
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticMgtCacheModel implements CacheModel<OpencpsDossierStatisticMgt>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpencpsDossierStatisticMgtCacheModel)) {
			return false;
		}

		OpencpsDossierStatisticMgtCacheModel opencpsDossierStatisticMgtCacheModel =
			(OpencpsDossierStatisticMgtCacheModel)obj;

		if (dossierStatisticMgtId == opencpsDossierStatisticMgtCacheModel.dossierStatisticMgtId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierStatisticMgtId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(67);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dossierStatisticMgtId=");
		sb.append(dossierStatisticMgtId);
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
		sb.append(", month=");
		sb.append(month);
		sb.append(", year=");
		sb.append(year);
		sb.append(", totalCount=");
		sb.append(totalCount);
		sb.append(", processCount=");
		sb.append(processCount);
		sb.append(", remainingCount=");
		sb.append(remainingCount);
		sb.append(", receivedCount=");
		sb.append(receivedCount);
		sb.append(", onlineCount=");
		sb.append(onlineCount);
		sb.append(", onegateCount=");
		sb.append(onegateCount);
		sb.append(", releaseCount=");
		sb.append(releaseCount);
		sb.append(", betimesCount=");
		sb.append(betimesCount);
		sb.append(", ontimeCount=");
		sb.append(ontimeCount);
		sb.append(", overtimeCount=");
		sb.append(overtimeCount);
		sb.append(", doneCount=");
		sb.append(doneCount);
		sb.append(", releasingCount=");
		sb.append(releasingCount);
		sb.append(", processingCount=");
		sb.append(processingCount);
		sb.append(", undueCount=");
		sb.append(undueCount);
		sb.append(", overdueCount=");
		sb.append(overdueCount);
		sb.append(", ontimePercentage=");
		sb.append(ontimePercentage);
		sb.append(", waitingCount=");
		sb.append(waitingCount);
		sb.append(", govAgencyCode=");
		sb.append(govAgencyCode);
		sb.append(", govAgencyName=");
		sb.append(govAgencyName);
		sb.append(", domainCode=");
		sb.append(domainCode);
		sb.append(", domainName=");
		sb.append(domainName);
		sb.append(", serviceCode=");
		sb.append(serviceCode);
		sb.append(", serviceName=");
		sb.append(serviceName);
		sb.append(", groupBy=");
		sb.append(groupBy);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OpencpsDossierStatisticMgt toEntityModel() {
		OpencpsDossierStatisticMgtImpl opencpsDossierStatisticMgtImpl = new OpencpsDossierStatisticMgtImpl();

		if (uuid == null) {
			opencpsDossierStatisticMgtImpl.setUuid("");
		}
		else {
			opencpsDossierStatisticMgtImpl.setUuid(uuid);
		}

		opencpsDossierStatisticMgtImpl.setDossierStatisticMgtId(dossierStatisticMgtId);
		opencpsDossierStatisticMgtImpl.setGroupId(groupId);
		opencpsDossierStatisticMgtImpl.setUserId(userId);

		if (userName == null) {
			opencpsDossierStatisticMgtImpl.setUserName("");
		}
		else {
			opencpsDossierStatisticMgtImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			opencpsDossierStatisticMgtImpl.setCreateDate(null);
		}
		else {
			opencpsDossierStatisticMgtImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			opencpsDossierStatisticMgtImpl.setModifiedDate(null);
		}
		else {
			opencpsDossierStatisticMgtImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		opencpsDossierStatisticMgtImpl.setMonth(month);
		opencpsDossierStatisticMgtImpl.setYear(year);
		opencpsDossierStatisticMgtImpl.setTotalCount(totalCount);
		opencpsDossierStatisticMgtImpl.setProcessCount(processCount);
		opencpsDossierStatisticMgtImpl.setRemainingCount(remainingCount);
		opencpsDossierStatisticMgtImpl.setReceivedCount(receivedCount);
		opencpsDossierStatisticMgtImpl.setOnlineCount(onlineCount);
		opencpsDossierStatisticMgtImpl.setOnegateCount(onegateCount);
		opencpsDossierStatisticMgtImpl.setReleaseCount(releaseCount);
		opencpsDossierStatisticMgtImpl.setBetimesCount(betimesCount);
		opencpsDossierStatisticMgtImpl.setOntimeCount(ontimeCount);
		opencpsDossierStatisticMgtImpl.setOvertimeCount(overtimeCount);
		opencpsDossierStatisticMgtImpl.setDoneCount(doneCount);
		opencpsDossierStatisticMgtImpl.setReleasingCount(releasingCount);
		opencpsDossierStatisticMgtImpl.setProcessingCount(processingCount);
		opencpsDossierStatisticMgtImpl.setUndueCount(undueCount);
		opencpsDossierStatisticMgtImpl.setOverdueCount(overdueCount);
		opencpsDossierStatisticMgtImpl.setOntimePercentage(ontimePercentage);
		opencpsDossierStatisticMgtImpl.setWaitingCount(waitingCount);

		if (govAgencyCode == null) {
			opencpsDossierStatisticMgtImpl.setGovAgencyCode("");
		}
		else {
			opencpsDossierStatisticMgtImpl.setGovAgencyCode(govAgencyCode);
		}

		if (govAgencyName == null) {
			opencpsDossierStatisticMgtImpl.setGovAgencyName("");
		}
		else {
			opencpsDossierStatisticMgtImpl.setGovAgencyName(govAgencyName);
		}

		if (domainCode == null) {
			opencpsDossierStatisticMgtImpl.setDomainCode("");
		}
		else {
			opencpsDossierStatisticMgtImpl.setDomainCode(domainCode);
		}

		if (domainName == null) {
			opencpsDossierStatisticMgtImpl.setDomainName("");
		}
		else {
			opencpsDossierStatisticMgtImpl.setDomainName(domainName);
		}

		if (serviceCode == null) {
			opencpsDossierStatisticMgtImpl.setServiceCode("");
		}
		else {
			opencpsDossierStatisticMgtImpl.setServiceCode(serviceCode);
		}

		if (serviceName == null) {
			opencpsDossierStatisticMgtImpl.setServiceName("");
		}
		else {
			opencpsDossierStatisticMgtImpl.setServiceName(serviceName);
		}

		opencpsDossierStatisticMgtImpl.setGroupBy(groupBy);

		opencpsDossierStatisticMgtImpl.resetOriginalValues();

		return opencpsDossierStatisticMgtImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dossierStatisticMgtId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		month = objectInput.readInt();

		year = objectInput.readInt();

		totalCount = objectInput.readInt();

		processCount = objectInput.readInt();

		remainingCount = objectInput.readInt();

		receivedCount = objectInput.readInt();

		onlineCount = objectInput.readInt();

		onegateCount = objectInput.readInt();

		releaseCount = objectInput.readInt();

		betimesCount = objectInput.readInt();

		ontimeCount = objectInput.readInt();

		overtimeCount = objectInput.readInt();

		doneCount = objectInput.readInt();

		releasingCount = objectInput.readInt();

		processingCount = objectInput.readInt();

		undueCount = objectInput.readInt();

		overdueCount = objectInput.readInt();

		ontimePercentage = objectInput.readInt();

		waitingCount = objectInput.readInt();
		govAgencyCode = objectInput.readUTF();
		govAgencyName = objectInput.readUTF();
		domainCode = objectInput.readUTF();
		domainName = objectInput.readUTF();
		serviceCode = objectInput.readUTF();
		serviceName = objectInput.readUTF();

		groupBy = objectInput.readInt();
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

		objectOutput.writeLong(dossierStatisticMgtId);

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

		objectOutput.writeInt(month);

		objectOutput.writeInt(year);

		objectOutput.writeInt(totalCount);

		objectOutput.writeInt(processCount);

		objectOutput.writeInt(remainingCount);

		objectOutput.writeInt(receivedCount);

		objectOutput.writeInt(onlineCount);

		objectOutput.writeInt(onegateCount);

		objectOutput.writeInt(releaseCount);

		objectOutput.writeInt(betimesCount);

		objectOutput.writeInt(ontimeCount);

		objectOutput.writeInt(overtimeCount);

		objectOutput.writeInt(doneCount);

		objectOutput.writeInt(releasingCount);

		objectOutput.writeInt(processingCount);

		objectOutput.writeInt(undueCount);

		objectOutput.writeInt(overdueCount);

		objectOutput.writeInt(ontimePercentage);

		objectOutput.writeInt(waitingCount);

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

		if (domainCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(domainCode);
		}

		if (domainName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(domainName);
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

		objectOutput.writeInt(groupBy);
	}

	public String uuid;
	public long dossierStatisticMgtId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int month;
	public int year;
	public int totalCount;
	public int processCount;
	public int remainingCount;
	public int receivedCount;
	public int onlineCount;
	public int onegateCount;
	public int releaseCount;
	public int betimesCount;
	public int ontimeCount;
	public int overtimeCount;
	public int doneCount;
	public int releasingCount;
	public int processingCount;
	public int undueCount;
	public int overdueCount;
	public int ontimePercentage;
	public int waitingCount;
	public String govAgencyCode;
	public String govAgencyName;
	public String domainCode;
	public String domainName;
	public String serviceCode;
	public String serviceName;
	public int groupBy;
}