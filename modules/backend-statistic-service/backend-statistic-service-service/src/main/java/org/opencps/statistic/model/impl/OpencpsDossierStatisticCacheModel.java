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

import org.opencps.statistic.model.OpencpsDossierStatistic;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OpencpsDossierStatistic in entity cache.
 *
 * @author khoavu
 * @see OpencpsDossierStatistic
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticCacheModel implements CacheModel<OpencpsDossierStatistic>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpencpsDossierStatisticCacheModel)) {
			return false;
		}

		OpencpsDossierStatisticCacheModel opencpsDossierStatisticCacheModel = (OpencpsDossierStatisticCacheModel)obj;

		if (dossierStatisticId == opencpsDossierStatisticCacheModel.dossierStatisticId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierStatisticId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(85);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dossierStatisticId=");
		sb.append(dossierStatisticId);
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
		sb.append(", month=");
		sb.append(month);
		sb.append(", year=");
		sb.append(year);
		sb.append(", totalCount=");
		sb.append(totalCount);
		sb.append(", deniedCount=");
		sb.append(deniedCount);
		sb.append(", cancelledCount=");
		sb.append(cancelledCount);
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
		sb.append(", unresolvedCount=");
		sb.append(unresolvedCount);
		sb.append(", processingCount=");
		sb.append(processingCount);
		sb.append(", undueCount=");
		sb.append(undueCount);
		sb.append(", overdueCount=");
		sb.append(overdueCount);
		sb.append(", pausingCount=");
		sb.append(pausingCount);
		sb.append(", ontimePercentage=");
		sb.append(ontimePercentage);
		sb.append(", govAgencyCode=");
		sb.append(govAgencyCode);
		sb.append(", groupAgencyCode=");
		sb.append(groupAgencyCode);
		sb.append(", govAgencyName=");
		sb.append(govAgencyName);
		sb.append(", domainCode=");
		sb.append(domainCode);
		sb.append(", domainName=");
		sb.append(domainName);
		sb.append(", reporting=");
		sb.append(reporting);
		sb.append(", overtimeInside=");
		sb.append(overtimeInside);
		sb.append(", overtimeOutside=");
		sb.append(overtimeOutside);
		sb.append(", interoperatingCount=");
		sb.append(interoperatingCount);
		sb.append(", waitingCount=");
		sb.append(waitingCount);
		sb.append(", outsideCount=");
		sb.append(outsideCount);
		sb.append(", insideCount=");
		sb.append(insideCount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OpencpsDossierStatistic toEntityModel() {
		OpencpsDossierStatisticImpl opencpsDossierStatisticImpl = new OpencpsDossierStatisticImpl();

		if (uuid == null) {
			opencpsDossierStatisticImpl.setUuid("");
		}
		else {
			opencpsDossierStatisticImpl.setUuid(uuid);
		}

		opencpsDossierStatisticImpl.setDossierStatisticId(dossierStatisticId);
		opencpsDossierStatisticImpl.setCompanyId(companyId);
		opencpsDossierStatisticImpl.setGroupId(groupId);
		opencpsDossierStatisticImpl.setUserId(userId);

		if (userName == null) {
			opencpsDossierStatisticImpl.setUserName("");
		}
		else {
			opencpsDossierStatisticImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			opencpsDossierStatisticImpl.setCreateDate(null);
		}
		else {
			opencpsDossierStatisticImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			opencpsDossierStatisticImpl.setModifiedDate(null);
		}
		else {
			opencpsDossierStatisticImpl.setModifiedDate(new Date(modifiedDate));
		}

		opencpsDossierStatisticImpl.setMonth(month);
		opencpsDossierStatisticImpl.setYear(year);
		opencpsDossierStatisticImpl.setTotalCount(totalCount);
		opencpsDossierStatisticImpl.setDeniedCount(deniedCount);
		opencpsDossierStatisticImpl.setCancelledCount(cancelledCount);
		opencpsDossierStatisticImpl.setProcessCount(processCount);
		opencpsDossierStatisticImpl.setRemainingCount(remainingCount);
		opencpsDossierStatisticImpl.setReceivedCount(receivedCount);
		opencpsDossierStatisticImpl.setOnlineCount(onlineCount);
		opencpsDossierStatisticImpl.setOnegateCount(onegateCount);
		opencpsDossierStatisticImpl.setReleaseCount(releaseCount);
		opencpsDossierStatisticImpl.setBetimesCount(betimesCount);
		opencpsDossierStatisticImpl.setOntimeCount(ontimeCount);
		opencpsDossierStatisticImpl.setOvertimeCount(overtimeCount);
		opencpsDossierStatisticImpl.setDoneCount(doneCount);
		opencpsDossierStatisticImpl.setReleasingCount(releasingCount);
		opencpsDossierStatisticImpl.setUnresolvedCount(unresolvedCount);
		opencpsDossierStatisticImpl.setProcessingCount(processingCount);
		opencpsDossierStatisticImpl.setUndueCount(undueCount);
		opencpsDossierStatisticImpl.setOverdueCount(overdueCount);
		opencpsDossierStatisticImpl.setPausingCount(pausingCount);
		opencpsDossierStatisticImpl.setOntimePercentage(ontimePercentage);

		if (govAgencyCode == null) {
			opencpsDossierStatisticImpl.setGovAgencyCode("");
		}
		else {
			opencpsDossierStatisticImpl.setGovAgencyCode(govAgencyCode);
		}

		if (groupAgencyCode == null) {
			opencpsDossierStatisticImpl.setGroupAgencyCode("");
		}
		else {
			opencpsDossierStatisticImpl.setGroupAgencyCode(groupAgencyCode);
		}

		if (govAgencyName == null) {
			opencpsDossierStatisticImpl.setGovAgencyName("");
		}
		else {
			opencpsDossierStatisticImpl.setGovAgencyName(govAgencyName);
		}

		if (domainCode == null) {
			opencpsDossierStatisticImpl.setDomainCode("");
		}
		else {
			opencpsDossierStatisticImpl.setDomainCode(domainCode);
		}

		if (domainName == null) {
			opencpsDossierStatisticImpl.setDomainName("");
		}
		else {
			opencpsDossierStatisticImpl.setDomainName(domainName);
		}

		opencpsDossierStatisticImpl.setReporting(reporting);
		opencpsDossierStatisticImpl.setOvertimeInside(overtimeInside);
		opencpsDossierStatisticImpl.setOvertimeOutside(overtimeOutside);
		opencpsDossierStatisticImpl.setInteroperatingCount(interoperatingCount);
		opencpsDossierStatisticImpl.setWaitingCount(waitingCount);
		opencpsDossierStatisticImpl.setOutsideCount(outsideCount);
		opencpsDossierStatisticImpl.setInsideCount(insideCount);

		opencpsDossierStatisticImpl.resetOriginalValues();

		return opencpsDossierStatisticImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dossierStatisticId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		month = objectInput.readInt();

		year = objectInput.readInt();

		totalCount = objectInput.readInt();

		deniedCount = objectInput.readInt();

		cancelledCount = objectInput.readInt();

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

		unresolvedCount = objectInput.readInt();

		processingCount = objectInput.readInt();

		undueCount = objectInput.readInt();

		overdueCount = objectInput.readInt();

		pausingCount = objectInput.readInt();

		ontimePercentage = objectInput.readInt();
		govAgencyCode = objectInput.readUTF();
		groupAgencyCode = objectInput.readUTF();
		govAgencyName = objectInput.readUTF();
		domainCode = objectInput.readUTF();
		domainName = objectInput.readUTF();

		reporting = objectInput.readBoolean();

		overtimeInside = objectInput.readInt();

		overtimeOutside = objectInput.readInt();

		interoperatingCount = objectInput.readInt();

		waitingCount = objectInput.readInt();

		outsideCount = objectInput.readInt();

		insideCount = objectInput.readInt();
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

		objectOutput.writeLong(dossierStatisticId);

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

		objectOutput.writeInt(month);

		objectOutput.writeInt(year);

		objectOutput.writeInt(totalCount);

		objectOutput.writeInt(deniedCount);

		objectOutput.writeInt(cancelledCount);

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

		objectOutput.writeInt(unresolvedCount);

		objectOutput.writeInt(processingCount);

		objectOutput.writeInt(undueCount);

		objectOutput.writeInt(overdueCount);

		objectOutput.writeInt(pausingCount);

		objectOutput.writeInt(ontimePercentage);

		if (govAgencyCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(govAgencyCode);
		}

		if (groupAgencyCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(groupAgencyCode);
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

		objectOutput.writeBoolean(reporting);

		objectOutput.writeInt(overtimeInside);

		objectOutput.writeInt(overtimeOutside);

		objectOutput.writeInt(interoperatingCount);

		objectOutput.writeInt(waitingCount);

		objectOutput.writeInt(outsideCount);

		objectOutput.writeInt(insideCount);
	}

	public String uuid;
	public long dossierStatisticId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int month;
	public int year;
	public int totalCount;
	public int deniedCount;
	public int cancelledCount;
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
	public int unresolvedCount;
	public int processingCount;
	public int undueCount;
	public int overdueCount;
	public int pausingCount;
	public int ontimePercentage;
	public String govAgencyCode;
	public String groupAgencyCode;
	public String govAgencyName;
	public String domainCode;
	public String domainName;
	public boolean reporting;
	public int overtimeInside;
	public int overtimeOutside;
	public int interoperatingCount;
	public int waitingCount;
	public int outsideCount;
	public int insideCount;
}