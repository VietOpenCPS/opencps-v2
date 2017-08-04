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

import org.opencps.datamgt.model.Voting;

/**
 * The cache model class for representing Voting in entity cache.
 *
 * @author Binhth
 * @see Voting
 * @generated
 */
@ProviderType
public class VotingCacheModel implements CacheModel<Voting>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VotingCacheModel)) {
			return false;
		}

		VotingCacheModel votingCacheModel = (VotingCacheModel)obj;

		if (votingId == votingCacheModel.votingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, votingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", votingId=");
		sb.append(votingId);
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
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", answers=");
		sb.append(answers);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Voting toEntityModel() {
		VotingImpl votingImpl = new VotingImpl();

		if (uuid == null) {
			votingImpl.setUuid(StringPool.BLANK);
		}
		else {
			votingImpl.setUuid(uuid);
		}

		votingImpl.setVotingId(votingId);
		votingImpl.setCompanyId(companyId);
		votingImpl.setGroupId(groupId);
		votingImpl.setUserId(userId);

		if (userName == null) {
			votingImpl.setUserName(StringPool.BLANK);
		}
		else {
			votingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			votingImpl.setCreateDate(null);
		}
		else {
			votingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			votingImpl.setModifiedDate(null);
		}
		else {
			votingImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (className == null) {
			votingImpl.setClassName(StringPool.BLANK);
		}
		else {
			votingImpl.setClassName(className);
		}

		if (classPK == null) {
			votingImpl.setClassPK(StringPool.BLANK);
		}
		else {
			votingImpl.setClassPK(classPK);
		}

		if (subject == null) {
			votingImpl.setSubject(StringPool.BLANK);
		}
		else {
			votingImpl.setSubject(subject);
		}

		if (answers == null) {
			votingImpl.setAnswers(StringPool.BLANK);
		}
		else {
			votingImpl.setAnswers(answers);
		}

		if (dueDate == Long.MIN_VALUE) {
			votingImpl.setDueDate(null);
		}
		else {
			votingImpl.setDueDate(new Date(dueDate));
		}

		votingImpl.resetOriginalValues();

		return votingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		votingId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		className = objectInput.readUTF();
		classPK = objectInput.readUTF();
		subject = objectInput.readUTF();
		answers = objectInput.readUTF();
		dueDate = objectInput.readLong();
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

		objectOutput.writeLong(votingId);

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

		if (className == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(className);
		}

		if (classPK == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(classPK);
		}

		if (subject == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subject);
		}

		if (answers == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(answers);
		}

		objectOutput.writeLong(dueDate);
	}

	public String uuid;
	public long votingId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String className;
	public String classPK;
	public String subject;
	public String answers;
	public long dueDate;
}