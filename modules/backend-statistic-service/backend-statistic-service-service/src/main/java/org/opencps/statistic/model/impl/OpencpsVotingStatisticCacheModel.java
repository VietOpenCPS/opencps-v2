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

import org.opencps.statistic.model.OpencpsVotingStatistic;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OpencpsVotingStatistic in entity cache.
 *
 * @author khoavu
 * @see OpencpsVotingStatistic
 * @generated
 */
@ProviderType
public class OpencpsVotingStatisticCacheModel implements CacheModel<OpencpsVotingStatistic>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpencpsVotingStatisticCacheModel)) {
			return false;
		}

		OpencpsVotingStatisticCacheModel opencpsVotingStatisticCacheModel = (OpencpsVotingStatisticCacheModel)obj;

		if (votingStatisticId == opencpsVotingStatisticCacheModel.votingStatisticId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, votingStatisticId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", votingStatisticId=");
		sb.append(votingStatisticId);
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
		sb.append(", domainCode=");
		sb.append(domainCode);
		sb.append(", domainName=");
		sb.append(domainName);
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
	public OpencpsVotingStatistic toEntityModel() {
		OpencpsVotingStatisticImpl opencpsVotingStatisticImpl = new OpencpsVotingStatisticImpl();

		if (uuid == null) {
			opencpsVotingStatisticImpl.setUuid("");
		}
		else {
			opencpsVotingStatisticImpl.setUuid(uuid);
		}

		opencpsVotingStatisticImpl.setVotingStatisticId(votingStatisticId);
		opencpsVotingStatisticImpl.setCompanyId(companyId);
		opencpsVotingStatisticImpl.setGroupId(groupId);
		opencpsVotingStatisticImpl.setUserId(userId);

		if (userName == null) {
			opencpsVotingStatisticImpl.setUserName("");
		}
		else {
			opencpsVotingStatisticImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			opencpsVotingStatisticImpl.setCreateDate(null);
		}
		else {
			opencpsVotingStatisticImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			opencpsVotingStatisticImpl.setModifiedDate(null);
		}
		else {
			opencpsVotingStatisticImpl.setModifiedDate(new Date(modifiedDate));
		}

		opencpsVotingStatisticImpl.setMonth(month);
		opencpsVotingStatisticImpl.setYear(year);

		if (govAgencyCode == null) {
			opencpsVotingStatisticImpl.setGovAgencyCode("");
		}
		else {
			opencpsVotingStatisticImpl.setGovAgencyCode(govAgencyCode);
		}

		if (govAgencyName == null) {
			opencpsVotingStatisticImpl.setGovAgencyName("");
		}
		else {
			opencpsVotingStatisticImpl.setGovAgencyName(govAgencyName);
		}

		if (domainCode == null) {
			opencpsVotingStatisticImpl.setDomainCode("");
		}
		else {
			opencpsVotingStatisticImpl.setDomainCode(domainCode);
		}

		if (domainName == null) {
			opencpsVotingStatisticImpl.setDomainName("");
		}
		else {
			opencpsVotingStatisticImpl.setDomainName(domainName);
		}

		if (votingCode == null) {
			opencpsVotingStatisticImpl.setVotingCode("");
		}
		else {
			opencpsVotingStatisticImpl.setVotingCode(votingCode);
		}

		if (votingSubject == null) {
			opencpsVotingStatisticImpl.setVotingSubject("");
		}
		else {
			opencpsVotingStatisticImpl.setVotingSubject(votingSubject);
		}

		opencpsVotingStatisticImpl.setTotalVoted(totalVoted);
		opencpsVotingStatisticImpl.setVeryGoodCount(veryGoodCount);
		opencpsVotingStatisticImpl.setGoodCount(goodCount);
		opencpsVotingStatisticImpl.setBadCount(badCount);
		opencpsVotingStatisticImpl.setPercentVeryGood(percentVeryGood);
		opencpsVotingStatisticImpl.setPercentGood(percentGood);
		opencpsVotingStatisticImpl.setPercentBad(percentBad);
		opencpsVotingStatisticImpl.setTotalCount(totalCount);

		opencpsVotingStatisticImpl.resetOriginalValues();

		return opencpsVotingStatisticImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		votingStatisticId = objectInput.readLong();

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
		domainCode = objectInput.readUTF();
		domainName = objectInput.readUTF();
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

		objectOutput.writeLong(votingStatisticId);

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
	public long votingStatisticId;
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
	public String domainCode;
	public String domainName;
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