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

package backend.feedback.model.impl;

import aQute.bnd.annotation.ProviderType;

import backend.feedback.model.Comment;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Comment in entity cache.
 *
 * @author sondt
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
		StringBundler sb = new StringBundler(39);

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
		sb.append(", pings=");
		sb.append(pings);
		sb.append(", upvoteCount=");
		sb.append(upvoteCount);
		sb.append(", userHasUpvoted=");
		sb.append(userHasUpvoted);
		sb.append(", upvotedUsers=");
		sb.append(upvotedUsers);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Comment toEntityModel() {
		CommentImpl commentImpl = new CommentImpl();

		if (uuid == null) {
			commentImpl.setUuid("");
		}
		else {
			commentImpl.setUuid(uuid);
		}

		commentImpl.setCommentId(commentId);
		commentImpl.setCompanyId(companyId);
		commentImpl.setGroupId(groupId);
		commentImpl.setUserId(userId);

		if (userName == null) {
			commentImpl.setUserName("");
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
			commentImpl.setClassName("");
		}
		else {
			commentImpl.setClassName(className);
		}

		if (classPK == null) {
			commentImpl.setClassPK("");
		}
		else {
			commentImpl.setClassPK(classPK);
		}

		if (fullname == null) {
			commentImpl.setFullname("");
		}
		else {
			commentImpl.setFullname(fullname);
		}

		if (email == null) {
			commentImpl.setEmail("");
		}
		else {
			commentImpl.setEmail(email);
		}

		commentImpl.setParent(parent);

		if (content == null) {
			commentImpl.setContent("");
		}
		else {
			commentImpl.setContent(content);
		}

		commentImpl.setFileEntryId(fileEntryId);

		if (pings == null) {
			commentImpl.setPings("");
		}
		else {
			commentImpl.setPings(pings);
		}

		commentImpl.setUpvoteCount(upvoteCount);

		if (userHasUpvoted == null) {
			commentImpl.setUserHasUpvoted("");
		}
		else {
			commentImpl.setUserHasUpvoted(userHasUpvoted);
		}

		if (upvotedUsers == null) {
			commentImpl.setUpvotedUsers("");
		}
		else {
			commentImpl.setUpvotedUsers(upvotedUsers);
		}

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
		pings = objectInput.readUTF();

		upvoteCount = objectInput.readInt();
		userHasUpvoted = objectInput.readUTF();
		upvotedUsers = objectInput.readUTF();
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

		objectOutput.writeLong(commentId);

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

		objectOutput.writeLong(parent);

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeLong(fileEntryId);

		if (pings == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(pings);
		}

		objectOutput.writeInt(upvoteCount);

		if (userHasUpvoted == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userHasUpvoted);
		}

		if (upvotedUsers == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(upvotedUsers);
		}
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
	public String pings;
	public int upvoteCount;
	public String userHasUpvoted;
	public String upvotedUsers;
}