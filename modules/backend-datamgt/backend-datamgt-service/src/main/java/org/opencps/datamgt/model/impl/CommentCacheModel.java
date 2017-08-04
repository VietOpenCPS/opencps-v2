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

import org.opencps.datamgt.model.Comment;

/**
 * The cache model class for representing Comment in entity cache.
 *
 * @author Binhth
 * @see Comment
 * @generated
 */
@ProviderType
public class CommentCacheModel implements CacheModel<Comment>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommentCacheModel)) {
			return false;
		}

		CommentCacheModel commentCacheModel = (CommentCacheModel)obj;

		if (commentId == commentCacheModel.commentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", commentId=");
		sb.append(commentId);
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
		sb.append(", fullname=");
		sb.append(fullname);
		sb.append(", email=");
		sb.append(email);
		sb.append(", parent=");
		sb.append(parent);
		sb.append(", content=");
		sb.append(content);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", upvoteCount=");
		sb.append(upvoteCount);
		sb.append(", userHasUpvoted=");
		sb.append(userHasUpvoted);
		sb.append(", createdByCurrentUser=");
		sb.append(createdByCurrentUser);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Comment toEntityModel() {
		CommentImpl commentImpl = new CommentImpl();

		if (uuid == null) {
			commentImpl.setUuid(StringPool.BLANK);
		}
		else {
			commentImpl.setUuid(uuid);
		}

		commentImpl.setCommentId(commentId);
		commentImpl.setCompanyId(companyId);
		commentImpl.setGroupId(groupId);
		commentImpl.setUserId(userId);

		if (userName == null) {
			commentImpl.setUserName(StringPool.BLANK);
		}
		else {
			commentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commentImpl.setCreateDate(null);
		}
		else {
			commentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commentImpl.setModifiedDate(null);
		}
		else {
			commentImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (className == null) {
			commentImpl.setClassName(StringPool.BLANK);
		}
		else {
			commentImpl.setClassName(className);
		}

		if (classPK == null) {
			commentImpl.setClassPK(StringPool.BLANK);
		}
		else {
			commentImpl.setClassPK(classPK);
		}

		if (fullname == null) {
			commentImpl.setFullname(StringPool.BLANK);
		}
		else {
			commentImpl.setFullname(fullname);
		}

		if (email == null) {
			commentImpl.setEmail(StringPool.BLANK);
		}
		else {
			commentImpl.setEmail(email);
		}

		commentImpl.setParent(parent);

		if (content == null) {
			commentImpl.setContent(StringPool.BLANK);
		}
		else {
			commentImpl.setContent(content);
		}

		commentImpl.setFileEntryId(fileEntryId);
		commentImpl.setUpvoteCount(upvoteCount);

		if (userHasUpvoted == null) {
			commentImpl.setUserHasUpvoted(StringPool.BLANK);
		}
		else {
			commentImpl.setUserHasUpvoted(userHasUpvoted);
		}

		commentImpl.setCreatedByCurrentUser(createdByCurrentUser);

		commentImpl.resetOriginalValues();

		return commentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		commentId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		className = objectInput.readUTF();
		classPK = objectInput.readUTF();
		fullname = objectInput.readUTF();
		email = objectInput.readUTF();

		parent = objectInput.readLong();
		content = objectInput.readUTF();

		fileEntryId = objectInput.readLong();

		upvoteCount = objectInput.readLong();
		userHasUpvoted = objectInput.readUTF();

		createdByCurrentUser = objectInput.readLong();
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

		objectOutput.writeLong(commentId);

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

		objectOutput.writeLong(parent);

		if (content == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeLong(upvoteCount);

		if (userHasUpvoted == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userHasUpvoted);
		}

		objectOutput.writeLong(createdByCurrentUser);
	}

	public String uuid;
	public long commentId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String className;
	public String classPK;
	public String fullname;
	public String email;
	public long parent;
	public String content;
	public long fileEntryId;
	public long upvoteCount;
	public String userHasUpvoted;
	public long createdByCurrentUser;
}