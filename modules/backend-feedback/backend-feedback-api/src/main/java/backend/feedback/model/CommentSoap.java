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

package backend.feedback.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author sondt
 * @generated
 */
@ProviderType
public class CommentSoap implements Serializable {
	public static CommentSoap toSoapModel(Comment model) {
		CommentSoap soapModel = new CommentSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCommentId(model.getCommentId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setFullname(model.getFullname());
		soapModel.setEmail(model.getEmail());
		soapModel.setParent(model.getParent());
		soapModel.setContent(model.getContent());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setPings(model.getPings());
		soapModel.setUpvoteCount(model.getUpvoteCount());
		soapModel.setUserHasUpvoted(model.getUserHasUpvoted());
		soapModel.setUpvotedUsers(model.getUpvotedUsers());

		return soapModel;
	}

	public static CommentSoap[] toSoapModels(Comment[] models) {
		CommentSoap[] soapModels = new CommentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommentSoap[][] toSoapModels(Comment[][] models) {
		CommentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommentSoap[] toSoapModels(List<Comment> models) {
		List<CommentSoap> soapModels = new ArrayList<CommentSoap>(models.size());

		for (Comment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommentSoap[soapModels.size()]);
	}

	public CommentSoap() {
	}

	public long getPrimaryKey() {
		return _commentId;
	}

	public void setPrimaryKey(long pk) {
		setCommentId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCommentId() {
		return _commentId;
	}

	public void setCommentId(long commentId) {
		_commentId = commentId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public String getClassPK() {
		return _classPK;
	}

	public void setClassPK(String classPK) {
		_classPK = classPK;
	}

	public String getFullname() {
		return _fullname;
	}

	public void setFullname(String fullname) {
		_fullname = fullname;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public long getParent() {
		return _parent;
	}

	public void setParent(long parent) {
		_parent = parent;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public String getPings() {
		return _pings;
	}

	public void setPings(String pings) {
		_pings = pings;
	}

	public int getUpvoteCount() {
		return _upvoteCount;
	}

	public void setUpvoteCount(int upvoteCount) {
		_upvoteCount = upvoteCount;
	}

	public String getUserHasUpvoted() {
		return _userHasUpvoted;
	}

	public void setUserHasUpvoted(String userHasUpvoted) {
		_userHasUpvoted = userHasUpvoted;
	}

	public String getUpvotedUsers() {
		return _upvotedUsers;
	}

	public void setUpvotedUsers(String upvotedUsers) {
		_upvotedUsers = upvotedUsers;
	}

	private String _uuid;
	private long _commentId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _className;
	private String _classPK;
	private String _fullname;
	private String _email;
	private long _parent;
	private String _content;
	private long _fileEntryId;
	private String _pings;
	private int _upvoteCount;
	private String _userHasUpvoted;
	private String _upvotedUsers;
}