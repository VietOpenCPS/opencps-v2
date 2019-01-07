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

import org.opencps.statistic.model.OpencpsPersonStatistic;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OpencpsPersonStatistic in entity cache.
 *
 * @author khoavu
 * @see OpencpsPersonStatistic
 * @generated
 */
@ProviderType
public class OpencpsPersonStatisticCacheModel implements CacheModel<OpencpsPersonStatistic>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpencpsPersonStatisticCacheModel)) {
			return false;
		}

		OpencpsPersonStatisticCacheModel opencpsPersonStatisticCacheModel = (OpencpsPersonStatisticCacheModel)obj;

		if (personStatisticId == opencpsPersonStatisticCacheModel.personStatisticId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, personStatisticId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(47);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", personStatisticId=");
		sb.append(personStatisticId);
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
		sb.append(", govAgencyCode=");
		sb.append(govAgencyCode);
		sb.append(", govAgencyName=");
		sb.append(govAgencyName);
		sb.append(", employeeId=");
		sb.append(employeeId);
		sb.append(", votingCode=");
		sb.append(votingCode);
		sb.append(", votingSubject=");
		sb.append(votingSubject);
		sb.append(", totalVoted=");
		sb.append(totalVoted);
		sb.append(", veryGoodCount=");
		sb.append(veryGoodCount);
		sb.append(", goodCount=");
		sb.append(goodCount);
		sb.append(", badCount=");
		sb.append(badCount);
		sb.append(", percentVeryGood=");
		sb.append(percentVeryGood);
		sb.append(", percentGood=");
		sb.append(percentGood);
		sb.append(", percentBad=");
		sb.append(percentBad);
		sb.append(", totalCount=");
		sb.append(totalCount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OpencpsPersonStatistic toEntityModel() {
		OpencpsPersonStatisticImpl opencpsPersonStatisticImpl = new OpencpsPersonStatisticImpl();

		if (uuid == null) {
			opencpsPersonStatisticImpl.setUuid("");
		}
		else {
			opencpsPersonStatisticImpl.setUuid(uuid);
		}

		opencpsPersonStatisticImpl.setPersonStatisticId(personStatisticId);
		opencpsPersonStatisticImpl.setCompanyId(companyId);
		opencpsPersonStatisticImpl.setGroupId(groupId);
		opencpsPersonStatisticImpl.setUserId(userId);

		if (userName == null) {
			opencpsPersonStatisticImpl.setUserName("");
		}
		else {
			opencpsPersonStatisticImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			opencpsPersonStatisticImpl.setCreateDate(null);
		}
		else {
			opencpsPersonStatisticImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			opencpsPersonStatisticImpl.setModifiedDate(null);
		}
		else {
			opencpsPersonStatisticImpl.setModifiedDate(new Date(modifiedDate));
		}

		opencpsPersonStatisticImpl.setMonth(month);
		opencpsPersonStatisticImpl.setYear(year);

		if (govAgencyCode == null) {
			opencpsPersonStatisticImpl.setGovAgencyCode("");
		}
		else {
			opencpsPersonStatisticImpl.setGovAgencyCode(govAgencyCode);
		}

		if (govAgencyName == null) {
			opencpsPersonStatisticImpl.setGovAgencyName("");
		}
		else {
			opencpsPersonStatisticImpl.setGovAgencyName(govAgencyName);
		}

		opencpsPersonStatisticImpl.setEmployeeId(employeeId);

		if (votingCode == null) {
			opencpsPersonStatisticImpl.setVotingCode("");
		}
		else {
			opencpsPersonStatisticImpl.setVotingCode(votingCode);
		}

		if (votingSubject == null) {
			opencpsPersonStatisticImpl.setVotingSubject("");
		}
		else {
			opencpsPersonStatisticImpl.setVotingSubject(votingSubject);
		}

		opencpsPersonStatisticImpl.setTotalVoted(totalVoted);
		opencpsPersonStatisticImpl.setVeryGoodCount(veryGoodCount);
		opencpsPersonStatisticImpl.setGoodCount(goodCount);
		opencpsPersonStatisticImpl.setBadCount(badCount);
		opencpsPersonStatisticImpl.setPercentVeryGood(percentVeryGood);
		opencpsPersonStatisticImpl.setPercentGood(percentGood);
		opencpsPersonStatisticImpl.setPercentBad(percentBad);
		opencpsPersonStatisticImpl.setTotalCount(totalCount);

		opencpsPersonStatisticImpl.resetOriginalValues();

		return opencpsPersonStatisticImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		personStatisticId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		month = objectInput.readInt();

		year = objectInput.readInt();
		govAgencyCode = objectInput.readUTF();
		govAgencyName = objectInput.readUTF();

		employeeId = objectInput.readLong();
		votingCode = objectInput.readUTF();
		votingSubject = objectInput.readUTF();

		totalVoted = objectInput.readInt();

		veryGoodCount = objectInput.readInt();

		goodCount = objectInput.readInt();

		badCount = objectInput.readInt();

		percentVeryGood = objectInput.readInt();

		percentGood = objectInput.readInt();

		percentBad = objectInput.readInt();

		totalCount = objectInput.readInt();
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

		objectOutput.writeLong(personStatisticId);

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

		objectOutput.writeLong(employeeId);

		if (votingCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(votingCode);
		}

		if (votingSubject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(votingSubject);
		}

		objectOutput.writeInt(totalVoted);

		objectOutput.writeInt(veryGoodCount);

		objectOutput.writeInt(goodCount);

		objectOutput.writeInt(badCount);

		objectOutput.writeInt(percentVeryGood);

		objectOutput.writeInt(percentGood);

		objectOutput.writeInt(percentBad);

		objectOutput.writeInt(totalCount);
	}

	public String uuid;
	public long personStatisticId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int month;
	public int year;
	public String govAgencyCode;
	public String govAgencyName;
	public long employeeId;
	public String votingCode;
	public String votingSubject;
	public int totalVoted;
	public int veryGoodCount;
	public int goodCount;
	public int badCount;
	public int percentVeryGood;
	public int percentGood;
	public int percentBad;
	public int totalCount;
}