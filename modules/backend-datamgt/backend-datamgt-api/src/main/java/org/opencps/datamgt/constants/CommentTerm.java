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

package org.opencps.datamgt.constants;

import java.util.Date;

public class CommentTerm {

	public static final String COMMENT_ID = "commentId";

	public static final String COMPANY_ID = "companyId";

	public static final String GROUP_ID = "groupId";

	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	public static final String CREATE_DATE = "createDate";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String CLASS_NAME = "className";
	
	public static final String CLASS_PK = "classPK";
	
	public static final String FULL_NAME = "fullname";
	
	public static final String EMAIL = "email";
	
	public static final String PARENT = "parent";
	
	public static final String CONTENT = "content";
	
	public static final String FILE_ENTRY_ID = "fileEntryId";
	
	public static final String UPVOTE_COUNT = "upvoteCount";
	
	public static final String USER_HAS_UPVOTED = "upvotedUsers";
	
	public static final String CREATED_BY_CURRENT_USER = "createdByCurrentUser";
	
	public static final String PINGS = "pings";
	
	public static final String OPINION = "opinion"; 
	
	// sortable
	public static final String COMMENT_ID_SORTABLE = "commentId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String USER_ID_SORTABLE = "userId_sortable";

	public static final String USER_NAME_SORTABLE = "userName_sortable";

	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";

	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String CLASS_NAME_SORTABLE = "className_sortable";
	
	public static final String CLASS_PK_SORTABLE = "classPK_sortable";
	
	public static final String FULL_NAME_SORTABLE = "fullname_sortable";
	
	public static final String EMAIL_SORTABLE = "email_sortable";
	
	public static final String PARENT_SORTABLE = "parent_sortable";
	
	public static final String CONTENT_SORTABLE = "content_sortable";
	
	public static final String FILE_ENTRY_ID_SORTABLE = "fileEntryId_sortable";
	
	public static final String UPVOTE_COUNT_SORTABLE = "upvoteCount_sortable";
	
	public static final String USER_HAS_UPVOTED_SORTABLE = "upvotedUsers_sortable";
	
	public static final String CREATED_BY_CURRENT_USER_SORTABLE = "createdByCurrentUser_sortable";

	private long commentId;
	private long companyId;
	private long groupId;

	private long userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;

	private String className;
	private String classPK;
	private String fullname;
	private String email;
	private long parent;
	private String content;
	private long fileEntryId;
	private long upvoteCount;
	private String userHasUpvoted;
	private long createdByCurrentUser;
	
	public CommentTerm() {

		// TODO Auto-generated constructor stub

	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassPK() {
		return classPK;
	}

	public void setClassPK(String classPK) {
		this.classPK = classPK;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getParent() {
		return parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getFileEntryId() {
		return fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		this.fileEntryId = fileEntryId;
	}

	public long getUpvoteCount() {
		return upvoteCount;
	}

	public void setUpvoteCount(long upvoteCount) {
		this.upvoteCount = upvoteCount;
	}

	public long getCreatedByCurrentUser() {
		return createdByCurrentUser;
	}

	public void setCreatedByCurrentUser(long createdByCurrentUser) {
		this.createdByCurrentUser = createdByCurrentUser;
	}

	public String getUserHasUpvoted() {
		return userHasUpvoted;
	}

	public void setUserHasUpvoted(String userHasUpvoted) {
		this.userHasUpvoted = userHasUpvoted;
	}
	
}

