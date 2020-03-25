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

import org.opencps.usermgt.model.Question;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Question in entity cache.
 *
 * @author khoavu
 * @see Question
 * @generated
 */
@ProviderType
public class QuestionCacheModel implements CacheModel<Question>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof QuestionCacheModel)) {
			return false;
		}

		QuestionCacheModel questionCacheModel = (QuestionCacheModel)obj;

		if (questionId == questionCacheModel.questionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, questionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{questionId=");
		sb.append(questionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", fullname=");
		sb.append(fullname);
		sb.append(", email=");
		sb.append(email);
		sb.append(", content=");
		sb.append(content);
		sb.append(", publish=");
		sb.append(publish);
		sb.append(", domainCode=");
		sb.append(domainCode);
		sb.append(", domainName=");
		sb.append(domainName);
		sb.append(", govAgencyCode=");
		sb.append(govAgencyCode);
		sb.append(", govAgencyName=");
		sb.append(govAgencyName);
		sb.append(", questionType=");
		sb.append(questionType);
		sb.append(", subDomainCode=");
		sb.append(subDomainCode);
		sb.append(", subDomainName=");
		sb.append(subDomainName);
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
	public Question toEntityModel() {
		QuestionImpl questionImpl = new QuestionImpl();

		questionImpl.setQuestionId(questionId);
		questionImpl.setCompanyId(companyId);
		questionImpl.setGroupId(groupId);

		if (createDate == Long.MIN_VALUE) {
			questionImpl.setCreateDate(null);
		}
		else {
			questionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			questionImpl.setModifiedDate(null);
		}
		else {
			questionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (fullname == null) {
			questionImpl.setFullname("");
		}
		else {
			questionImpl.setFullname(fullname);
		}

		if (email == null) {
			questionImpl.setEmail("");
		}
		else {
			questionImpl.setEmail(email);
		}

		if (content == null) {
			questionImpl.setContent("");
		}
		else {
			questionImpl.setContent(content);
		}

		questionImpl.setPublish(publish);

		if (domainCode == null) {
			questionImpl.setDomainCode("");
		}
		else {
			questionImpl.setDomainCode(domainCode);
		}

		if (domainName == null) {
			questionImpl.setDomainName("");
		}
		else {
			questionImpl.setDomainName(domainName);
		}

		if (govAgencyCode == null) {
			questionImpl.setGovAgencyCode("");
		}
		else {
			questionImpl.setGovAgencyCode(govAgencyCode);
		}

		if (govAgencyName == null) {
			questionImpl.setGovAgencyName("");
		}
		else {
			questionImpl.setGovAgencyName(govAgencyName);
		}

		if (questionType == null) {
			questionImpl.setQuestionType("");
		}
		else {
			questionImpl.setQuestionType(questionType);
		}

		if (subDomainCode == null) {
			questionImpl.setSubDomainCode("");
		}
		else {
			questionImpl.setSubDomainCode(subDomainCode);
		}

		if (subDomainName == null) {
			questionImpl.setSubDomainName("");
		}
		else {
			questionImpl.setSubDomainName(subDomainName);
		}

		if (className == null) {
			questionImpl.setClassName("");
		}
		else {
			questionImpl.setClassName(className);
		}

		if (classPK == null) {
			questionImpl.setClassPK("");
		}
		else {
			questionImpl.setClassPK(classPK);
		}

		questionImpl.setSynced(synced);

		questionImpl.resetOriginalValues();

		return questionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		questionId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		fullname = objectInput.readUTF();
		email = objectInput.readUTF();
		content = objectInput.readUTF();

		publish = objectInput.readInt();
		domainCode = objectInput.readUTF();
		domainName = objectInput.readUTF();
		govAgencyCode = objectInput.readUTF();
		govAgencyName = objectInput.readUTF();
		questionType = objectInput.readUTF();
		subDomainCode = objectInput.readUTF();
		subDomainName = objectInput.readUTF();
		className = objectInput.readUTF();
		classPK = objectInput.readUTF();

		synced = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(questionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (fullname == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fullname);
		}

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeInt(publish);

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

		if (questionType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(questionType);
		}

		if (subDomainCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subDomainCode);
		}

		if (subDomainName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subDomainName);
		}

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

	public long questionId;
	public long companyId;
	public long groupId;
	public long createDate;
	public long modifiedDate;
	public String fullname;
	public String email;
	public String content;
	public int publish;
	public String domainCode;
	public String domainName;
	public String govAgencyCode;
	public String govAgencyName;
	public String questionType;
	public String subDomainCode;
	public String subDomainName;
	public String className;
	public String classPK;
	public int synced;
}