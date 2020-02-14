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

package org.opencps.usermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.usermgt.model.Answer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Answer in entity cache.
 *
 * @author khoavu
 * @see Answer
 * @generated
 */
@ProviderType
public class AnswerCacheModel implements CacheModel<Answer>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AnswerCacheModel)) {
			return false;
		}

		AnswerCacheModel answerCacheModel = (AnswerCacheModel)obj;

		if (answerId == answerCacheModel.answerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, answerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{answerId=");
		sb.append(answerId);
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
		sb.append(", questionId=");
		sb.append(questionId);
		sb.append(", content=");
		sb.append(content);
		sb.append(", publish=");
		sb.append(publish);
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", synced=");
		sb.append(synced);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Answer toEntityModel() {
		AnswerImpl answerImpl = new AnswerImpl();

		answerImpl.setAnswerId(answerId);
		answerImpl.setCompanyId(companyId);
		answerImpl.setGroupId(groupId);
		answerImpl.setUserId(userId);

		if (userName == null) {
			answerImpl.setUserName("");
		}
		else {
			answerImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			answerImpl.setCreateDate(null);
		}
		else {
			answerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			answerImpl.setModifiedDate(null);
		}
		else {
			answerImpl.setModifiedDate(new Date(modifiedDate));
		}

		answerImpl.setQuestionId(questionId);

		if (content == null) {
			answerImpl.setContent("");
		}
		else {
			answerImpl.setContent(content);
		}

		answerImpl.setPublish(publish);

		if (className == null) {
			answerImpl.setClassName("");
		}
		else {
			answerImpl.setClassName(className);
		}

		if (classPK == null) {
			answerImpl.setClassPK("");
		}
		else {
			answerImpl.setClassPK(classPK);
		}

		answerImpl.setSynced(synced);

		answerImpl.resetOriginalValues();

		return answerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		answerId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		questionId = objectInput.readLong();
		content = objectInput.readUTF();

		publish = objectInput.readInt();
		className = objectInput.readUTF();
		classPK = objectInput.readUTF();

		synced = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(answerId);

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

		objectOutput.writeLong(questionId);

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeInt(publish);

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

		objectOutput.writeInt(synced);
	}

	public long answerId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long questionId;
	public String content;
	public int publish;
	public String className;
	public String classPK;
	public int synced;
}