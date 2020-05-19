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

import org.opencps.statistic.model.OpencpsDossierStatisticManual;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OpencpsDossierStatisticManual in entity cache.
 *
 * @author khoavu
 * @see OpencpsDossierStatisticManual
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticManualCacheModel implements CacheModel<OpencpsDossierStatisticManual>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpencpsDossierStatisticManualCacheModel)) {
			return false;
		}

		OpencpsDossierStatisticManualCacheModel opencpsDossierStatisticManualCacheModel =
			(OpencpsDossierStatisticManualCacheModel)obj;

		if (dossierStatisticId == opencpsDossierStatisticManualCacheModel.dossierStatisticId) {
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
		StringBundler sb = new StringBundler(103);

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
		sb.append(", system=");
		sb.append(system);
		sb.append(", viaPostalCount=");
		sb.append(viaPostalCount);
		sb.append(", notViaPostalCount=");
		sb.append(notViaPostalCount);
		sb.append(", saturdayCount=");
		sb.append(saturdayCount);
		sb.append(", dossierOnline3Count=");
		sb.append(dossierOnline3Count);
		sb.append(", dossierOnline4Count=");
		sb.append(dossierOnline4Count);
		sb.append(", receiveDossierSatCount=");
		sb.append(receiveDossierSatCount);
		sb.append(", releaseDossierSatCount=");
		sb.append(releaseDossierSatCount);
		sb.append(", fromViaPostalCount=");
		sb.append(fromViaPostalCount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OpencpsDossierStatisticManual toEntityModel() {
		OpencpsDossierStatisticManualImpl opencpsDossierStatisticManualImpl = new OpencpsDossierStatisticManualImpl();

		if (uuid == null) {
			opencpsDossierStatisticManualImpl.setUuid("");
		}
		else {
			opencpsDossierStatisticManualImpl.setUuid(uuid);
		}

		opencpsDossierStatisticManualImpl.setDossierStatisticId(dossierStatisticId);
		opencpsDossierStatisticManualImpl.setCompanyId(companyId);
		opencpsDossierStatisticManualImpl.setGroupId(groupId);
		opencpsDossierStatisticManualImpl.setUserId(userId);

		if (userName == null) {
			opencpsDossierStatisticManualImpl.setUserName("");
		}
		else {
			opencpsDossierStatisticManualImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			opencpsDossierStatisticManualImpl.setCreateDate(null);
		}
		else {
			opencpsDossierStatisticManualImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			opencpsDossierStatisticManualImpl.setModifiedDate(null);
		}
		else {
			opencpsDossierStatisticManualImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		opencpsDossierStatisticManualImpl.setMonth(month);
		opencpsDossierStatisticManualImpl.setYear(year);
		opencpsDossierStatisticManualImpl.setTotalCount(totalCount);
		opencpsDossierStatisticManualImpl.setDeniedCount(deniedCount);
		opencpsDossierStatisticManualImpl.setCancelledCount(cancelledCount);
		opencpsDossierStatisticManualImpl.setProcessCount(processCount);
		opencpsDossierStatisticManualImpl.setRemainingCount(remainingCount);
		opencpsDossierStatisticManualImpl.setReceivedCount(receivedCount);
		opencpsDossierStatisticManualImpl.setOnlineCount(onlineCount);
		opencpsDossierStatisticManualImpl.setOnegateCount(onegateCount);
		opencpsDossierStatisticManualImpl.setReleaseCount(releaseCount);
		opencpsDossierStatisticManualImpl.setBetimesCount(betimesCount);
		opencpsDossierStatisticManualImpl.setOntimeCount(ontimeCount);
		opencpsDossierStatisticManualImpl.setOvertimeCount(overtimeCount);
		opencpsDossierStatisticManualImpl.setDoneCount(doneCount);
		opencpsDossierStatisticManualImpl.setReleasingCount(releasingCount);
		opencpsDossierStatisticManualImpl.setUnresolvedCount(unresolvedCount);
		opencpsDossierStatisticManualImpl.setProcessingCount(processingCount);
		opencpsDossierStatisticManualImpl.setUndueCount(undueCount);
		opencpsDossierStatisticManualImpl.setOverdueCount(overdueCount);
		opencpsDossierStatisticManualImpl.setPausingCount(pausingCount);
		opencpsDossierStatisticManualImpl.setOntimePercentage(ontimePercentage);

		if (govAgencyCode == null) {
			opencpsDossierStatisticManualImpl.setGovAgencyCode("");
		}
		else {
			opencpsDossierStatisticManualImpl.setGovAgencyCode(govAgencyCode);
		}

		if (groupAgencyCode == null) {
			opencpsDossierStatisticManualImpl.setGroupAgencyCode("");
		}
		else {
			opencpsDossierStatisticManualImpl.setGroupAgencyCode(groupAgencyCode);
		}

		if (govAgencyName == null) {
			opencpsDossierStatisticManualImpl.setGovAgencyName("");
		}
		else {
			opencpsDossierStatisticManualImpl.setGovAgencyName(govAgencyName);
		}

		if (domainCode == null) {
			opencpsDossierStatisticManualImpl.setDomainCode("");
		}
		else {
			opencpsDossierStatisticManualImpl.setDomainCode(domainCode);
		}

		if (domainName == null) {
			opencpsDossierStatisticManualImpl.setDomainName("");
		}
		else {
			opencpsDossierStatisticManualImpl.setDomainName(domainName);
		}

		opencpsDossierStatisticManualImpl.setReporting(reporting);
		opencpsDossierStatisticManualImpl.setOvertimeInside(overtimeInside);
		opencpsDossierStatisticManualImpl.setOvertimeOutside(overtimeOutside);
		opencpsDossierStatisticManualImpl.setInteroperatingCount(interoperatingCount);
		opencpsDossierStatisticManualImpl.setWaitingCount(waitingCount);
		opencpsDossierStatisticManualImpl.setOutsideCount(outsideCount);
		opencpsDossierStatisticManualImpl.setInsideCount(insideCount);

		if (system == null) {
			opencpsDossierStatisticManualImpl.setSystem("");
		}
		else {
			opencpsDossierStatisticManualImpl.setSystem(system);
		}

		opencpsDossierStatisticManualImpl.setViaPostalCount(viaPostalCount);
		opencpsDossierStatisticManualImpl.setNotViaPostalCount(notViaPostalCount);
		opencpsDossierStatisticManualImpl.setSaturdayCount(saturdayCount);
		opencpsDossierStatisticManualImpl.setDossierOnline3Count(dossierOnline3Count);
		opencpsDossierStatisticManualImpl.setDossierOnline4Count(dossierOnline4Count);
		opencpsDossierStatisticManualImpl.setReceiveDossierSatCount(receiveDossierSatCount);
		opencpsDossierStatisticManualImpl.setReleaseDossierSatCount(releaseDossierSatCount);
		opencpsDossierStatisticManualImpl.setFromViaPostalCount(fromViaPostalCount);

		opencpsDossierStatisticManualImpl.resetOriginalValues();

		return opencpsDossierStatisticManualImpl;
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
		system = objectInput.readUTF();

		viaPostalCount = objectInput.readInt();

		notViaPostalCount = objectInput.readInt();

		saturdayCount = objectInput.readInt();

		dossierOnline3Count = objectInput.readInt();

		dossierOnline4Count = objectInput.readInt();

		receiveDossierSatCount = objectInput.readInt();

		releaseDossierSatCount = objectInput.readInt();

		fromViaPostalCount = objectInput.readInt();
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

		if (system == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(system);
		}

		objectOutput.writeInt(viaPostalCount);

		objectOutput.writeInt(notViaPostalCount);

		objectOutput.writeInt(saturdayCount);

		objectOutput.writeInt(dossierOnline3Count);

		objectOutput.writeInt(dossierOnline4Count);

		objectOutput.writeInt(receiveDossierSatCount);

		objectOutput.writeInt(releaseDossierSatCount);

		objectOutput.writeInt(fromViaPostalCount);
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
	public String system;
	public int viaPostalCount;
	public int notViaPostalCount;
	public int saturdayCount;
	public int dossierOnline3Count;
	public int dossierOnline4Count;
	public int receiveDossierSatCount;
	public int releaseDossierSatCount;
	public int fromViaPostalCount;
}