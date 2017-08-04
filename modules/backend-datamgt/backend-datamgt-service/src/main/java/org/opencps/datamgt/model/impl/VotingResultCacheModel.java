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

package org.opencps.datamgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.opencps.datamgt.model.VotingResult;

/**
 * The cache model class for representing VotingResult in entity cache.
 *
 * @author Binhth
 * @see VotingResult
 * @generated
 */
@ProviderType
public class VotingResultCacheModel implements CacheModel<VotingResult>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VotingResultCacheModel)) {
			return false;
		}

		VotingResultCacheModel votingResultCacheModel = (VotingResultCacheModel)obj;

		if (votingResultId == votingResultCacheModel.votingResultId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, votingResultId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", votingResultId=");
		sb.append(votingResultId);
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
		sb.append(", votingId=");
		sb.append(votingId);
		sb.append(", toUserId=");
		sb.append(toUserId);
		sb.append(", fullname=");
		sb.append(fullname);
		sb.append(", email=");
		sb.append(email);
		sb.append(", comment=");
		sb.append(comment);
		sb.append(", selected=");
		sb.append(selected);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VotingResult toEntityModel() {
		VotingResultImpl votingResultImpl = new VotingResultImpl();

		if (uuid == null) {
			votingResultImpl.setUuid(StringPool.BLANK);
		}
		else {
			votingResultImpl.setUuid(uuid);
		}

		votingResultImpl.setVotingResultId(votingResultId);
		votingResultImpl.setCompanyId(companyId);
		votingResultImpl.setGroupId(groupId);
		votingResultImpl.setUserId(userId);

		if (userName == null) {
			votingResultImpl.setUserName(StringPool.BLANK);
		}
		else {
			votingResultImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			votingResultImpl.setCreateDate(null);
		}
		else {
			votingResultImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			votingResultImpl.setModifiedDate(null);
		}
		else {
			votingResultImpl.setModifiedDate(new Date(modifiedDate));
		}

		votingResultImpl.setVotingId(votingId);
		votingResultImpl.setToUserId(toUserId);

		if (fullname == null) {
			votingResultImpl.setFullname(StringPool.BLANK);
		}
		else {
			votingResultImpl.setFullname(fullname);
		}

		if (email == null) {
			votingResultImpl.setEmail(StringPool.BLANK);
		}
		else {
			votingResultImpl.setEmail(email);
		}

		if (comment == null) {
			votingResultImpl.setComment(StringPool.BLANK);
		}
		else {
			votingResultImpl.setComment(comment);
		}

		if (selected == null) {
			votingResultImpl.setSelected(StringPool.BLANK);
		}
		else {
			votingResultImpl.setSelected(selected);
		}

		votingResultImpl.resetOriginalValues();

		return votingResultImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		votingResultId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		votingId = objectInput.readLong();

		toUserId = objectInput.readLong();
		fullname = objectInput.readUTF();
		email = objectInput.readUTF();
		comment = objectInput.readUTF();
		selected = objectInput.readUTF();
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

		objectOutput.writeLong(votingResultId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(votingId);

		objectOutput.writeLong(toUserId);

		if (fullname == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fullname);
		}

		if (email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (comment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(comment);
		}

		if (selected == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(selected);
		}
	}

	public String uuid;
	public long votingResultId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long votingId;
	public long toUserId;
	public String fullname;
	public String email;
	public String comment;
	public String selected;
}