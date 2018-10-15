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

import org.opencps.datamgt.model.Voting;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Voting in entity cache.
 *
 * @author khoavu
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
		StringBundler sb = new StringBundler(29);

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
		sb.append(", choices=");
		sb.append(choices);
		sb.append(", templateNo=");
		sb.append(templateNo);
		sb.append(", commentable=");
		sb.append(commentable);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Voting toEntityModel() {
		VotingImpl votingImpl = new VotingImpl();

		if (uuid == null) {
			votingImpl.setUuid("");
		}
		else {
			votingImpl.setUuid(uuid);
		}

		votingImpl.setVotingId(votingId);
		votingImpl.setCompanyId(companyId);
		votingImpl.setGroupId(groupId);
		votingImpl.setUserId(userId);

		if (userName == null) {
			votingImpl.setUserName("");
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
			votingImpl.setClassName("");
		}
		else {
			votingImpl.setClassName(className);
		}

		if (classPK == null) {
			votingImpl.setClassPK("");
		}
		else {
			votingImpl.setClassPK(classPK);
		}

		if (subject == null) {
			votingImpl.setSubject("");
		}
		else {
			votingImpl.setSubject(subject);
		}

		if (choices == null) {
			votingImpl.setChoices("");
		}
		else {
			votingImpl.setChoices(choices);
		}

		if (templateNo == null) {
			votingImpl.setTemplateNo("");
		}
		else {
			votingImpl.setTemplateNo(templateNo);
		}

		votingImpl.setCommentable(commentable);

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
		choices = objectInput.readUTF();
		templateNo = objectInput.readUTF();

		commentable = objectInput.readBoolean();
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

		objectOutput.writeLong(votingId);

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

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}

		if (classPK == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(classPK);
		}

		if (subject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subject);
		}

		if (choices == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(choices);
		}

		if (templateNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(templateNo);
		}

		objectOutput.writeBoolean(commentable);
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
	public String choices;
	public String templateNo;
	public boolean commentable;
}