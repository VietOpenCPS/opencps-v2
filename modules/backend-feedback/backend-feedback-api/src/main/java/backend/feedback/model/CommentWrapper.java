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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Comment}.
 * </p>
 *
 * @author sondt
 * @see Comment
 * @generated
 */
@ProviderType
public class CommentWrapper implements Comment, ModelWrapper<Comment> {
	public CommentWrapper(Comment comment) {
		_comment = comment;
	}

	@Override
	public Class<?> getModelClass() {
		return Comment.class;
	}

	@Override
	public String getModelClassName() {
		return Comment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("commentId", getCommentId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("fullname", getFullname());
		attributes.put("email", getEmail());
		attributes.put("parent", getParent());
		attributes.put("content", getContent());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("pings", getPings());
		attributes.put("upvoteCount", getUpvoteCount());
		attributes.put("userHasUpvoted", getUserHasUpvoted());
		attributes.put("upvotedUsers", getUpvotedUsers());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long commentId = (Long)attributes.get("commentId");

		if (commentId != null) {
			setCommentId(commentId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		String classPK = (String)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String fullname = (String)attributes.get("fullname");

		if (fullname != null) {
			setFullname(fullname);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		Long parent = (Long)attributes.get("parent");

		if (parent != null) {
			setParent(parent);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		String pings = (String)attributes.get("pings");

		if (pings != null) {
			setPings(pings);
		}

		Integer upvoteCount = (Integer)attributes.get("upvoteCount");

		if (upvoteCount != null) {
			setUpvoteCount(upvoteCount);
		}

		String userHasUpvoted = (String)attributes.get("userHasUpvoted");

		if (userHasUpvoted != null) {
			setUserHasUpvoted(userHasUpvoted);
		}

		String upvotedUsers = (String)attributes.get("upvotedUsers");

		if (upvotedUsers != null) {
			setUpvotedUsers(upvotedUsers);
		}
	}

	@Override
	public Object clone() {
		return new CommentWrapper((Comment)_comment.clone());
	}

	@Override
	public int compareTo(Comment comment) {
		return _comment.compareTo(comment);
	}

	/**
	* Returns the class name of this comment.
	*
	* @return the class name of this comment
	*/
	@Override
	public String getClassName() {
		return _comment.getClassName();
	}

	/**
	* Returns the class pk of this comment.
	*
	* @return the class pk of this comment
	*/
	@Override
	public String getClassPK() {
		return _comment.getClassPK();
	}

	/**
	* Returns the comment ID of this comment.
	*
	* @return the comment ID of this comment
	*/
	@Override
	public long getCommentId() {
		return _comment.getCommentId();
	}

	/**
	* Returns the company ID of this comment.
	*
	* @return the company ID of this comment
	*/
	@Override
	public long getCompanyId() {
		return _comment.getCompanyId();
	}

	/**
	* Returns the content of this comment.
	*
	* @return the content of this comment
	*/
	@Override
	public String getContent() {
		return _comment.getContent();
	}

	/**
	* Returns the create date of this comment.
	*
	* @return the create date of this comment
	*/
	@Override
	public Date getCreateDate() {
		return _comment.getCreateDate();
	}

	/**
	* Returns the email of this comment.
	*
	* @return the email of this comment
	*/
	@Override
	public String getEmail() {
		return _comment.getEmail();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _comment.getExpandoBridge();
	}

	/**
	* Returns the file entry ID of this comment.
	*
	* @return the file entry ID of this comment
	*/
	@Override
	public long getFileEntryId() {
		return _comment.getFileEntryId();
	}

	/**
	* Returns the fullname of this comment.
	*
	* @return the fullname of this comment
	*/
	@Override
	public String getFullname() {
		return _comment.getFullname();
	}

	/**
	* Returns the group ID of this comment.
	*
	* @return the group ID of this comment
	*/
	@Override
	public long getGroupId() {
		return _comment.getGroupId();
	}

	/**
	* Returns the modified date of this comment.
	*
	* @return the modified date of this comment
	*/
	@Override
	public Date getModifiedDate() {
		return _comment.getModifiedDate();
	}

	/**
	* Returns the parent of this comment.
	*
	* @return the parent of this comment
	*/
	@Override
	public long getParent() {
		return _comment.getParent();
	}

	/**
	* Returns the pings of this comment.
	*
	* @return the pings of this comment
	*/
	@Override
	public String getPings() {
		return _comment.getPings();
	}

	/**
	* Returns the primary key of this comment.
	*
	* @return the primary key of this comment
	*/
	@Override
	public long getPrimaryKey() {
		return _comment.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _comment.getPrimaryKeyObj();
	}

	/**
	* Returns the upvote count of this comment.
	*
	* @return the upvote count of this comment
	*/
	@Override
	public int getUpvoteCount() {
		return _comment.getUpvoteCount();
	}

	/**
	* Returns the upvoted users of this comment.
	*
	* @return the upvoted users of this comment
	*/
	@Override
	public String getUpvotedUsers() {
		return _comment.getUpvotedUsers();
	}

	/**
	* Returns the user has upvoted of this comment.
	*
	* @return the user has upvoted of this comment
	*/
	@Override
	public String getUserHasUpvoted() {
		return _comment.getUserHasUpvoted();
	}

	/**
	* Returns the user ID of this comment.
	*
	* @return the user ID of this comment
	*/
	@Override
	public long getUserId() {
		return _comment.getUserId();
	}

	/**
	* Returns the user name of this comment.
	*
	* @return the user name of this comment
	*/
	@Override
	public String getUserName() {
		return _comment.getUserName();
	}

	/**
	* Returns the user uuid of this comment.
	*
	* @return the user uuid of this comment
	*/
	@Override
	public String getUserUuid() {
		return _comment.getUserUuid();
	}

	/**
	* Returns the uuid of this comment.
	*
	* @return the uuid of this comment
	*/
	@Override
	public String getUuid() {
		return _comment.getUuid();
	}

	@Override
	public int hashCode() {
		return _comment.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _comment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _comment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _comment.isNew();
	}

	@Override
	public void persist() {
		_comment.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_comment.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this comment.
	*
	* @param className the class name of this comment
	*/
	@Override
	public void setClassName(String className) {
		_comment.setClassName(className);
	}

	/**
	* Sets the class pk of this comment.
	*
	* @param classPK the class pk of this comment
	*/
	@Override
	public void setClassPK(String classPK) {
		_comment.setClassPK(classPK);
	}

	/**
	* Sets the comment ID of this comment.
	*
	* @param commentId the comment ID of this comment
	*/
	@Override
	public void setCommentId(long commentId) {
		_comment.setCommentId(commentId);
	}

	/**
	* Sets the company ID of this comment.
	*
	* @param companyId the company ID of this comment
	*/
	@Override
	public void setCompanyId(long companyId) {
		_comment.setCompanyId(companyId);
	}

	/**
	* Sets the content of this comment.
	*
	* @param content the content of this comment
	*/
	@Override
	public void setContent(String content) {
		_comment.setContent(content);
	}

	/**
	* Sets the create date of this comment.
	*
	* @param createDate the create date of this comment
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_comment.setCreateDate(createDate);
	}

	/**
	* Sets the email of this comment.
	*
	* @param email the email of this comment
	*/
	@Override
	public void setEmail(String email) {
		_comment.setEmail(email);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_comment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_comment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_comment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file entry ID of this comment.
	*
	* @param fileEntryId the file entry ID of this comment
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_comment.setFileEntryId(fileEntryId);
	}

	/**
	* Sets the fullname of this comment.
	*
	* @param fullname the fullname of this comment
	*/
	@Override
	public void setFullname(String fullname) {
		_comment.setFullname(fullname);
	}

	/**
	* Sets the group ID of this comment.
	*
	* @param groupId the group ID of this comment
	*/
	@Override
	public void setGroupId(long groupId) {
		_comment.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this comment.
	*
	* @param modifiedDate the modified date of this comment
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_comment.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_comment.setNew(n);
	}

	/**
	* Sets the parent of this comment.
	*
	* @param parent the parent of this comment
	*/
	@Override
	public void setParent(long parent) {
		_comment.setParent(parent);
	}

	/**
	* Sets the pings of this comment.
	*
	* @param pings the pings of this comment
	*/
	@Override
	public void setPings(String pings) {
		_comment.setPings(pings);
	}

	/**
	* Sets the primary key of this comment.
	*
	* @param primaryKey the primary key of this comment
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_comment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_comment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the upvote count of this comment.
	*
	* @param upvoteCount the upvote count of this comment
	*/
	@Override
	public void setUpvoteCount(int upvoteCount) {
		_comment.setUpvoteCount(upvoteCount);
	}

	/**
	* Sets the upvoted users of this comment.
	*
	* @param upvotedUsers the upvoted users of this comment
	*/
	@Override
	public void setUpvotedUsers(String upvotedUsers) {
		_comment.setUpvotedUsers(upvotedUsers);
	}

	/**
	* Sets the user has upvoted of this comment.
	*
	* @param userHasUpvoted the user has upvoted of this comment
	*/
	@Override
	public void setUserHasUpvoted(String userHasUpvoted) {
		_comment.setUserHasUpvoted(userHasUpvoted);
	}

	/**
	* Sets the user ID of this comment.
	*
	* @param userId the user ID of this comment
	*/
	@Override
	public void setUserId(long userId) {
		_comment.setUserId(userId);
	}

	/**
	* Sets the user name of this comment.
	*
	* @param userName the user name of this comment
	*/
	@Override
	public void setUserName(String userName) {
		_comment.setUserName(userName);
	}

	/**
	* Sets the user uuid of this comment.
	*
	* @param userUuid the user uuid of this comment
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_comment.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this comment.
	*
	* @param uuid the uuid of this comment
	*/
	@Override
	public void setUuid(String uuid) {
		_comment.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Comment> toCacheModel() {
		return _comment.toCacheModel();
	}

	@Override
	public Comment toEscapedModel() {
		return new CommentWrapper(_comment.toEscapedModel());
	}

	@Override
	public String toString() {
		return _comment.toString();
	}

	@Override
	public Comment toUnescapedModel() {
		return new CommentWrapper(_comment.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _comment.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommentWrapper)) {
			return false;
		}

		CommentWrapper commentWrapper = (CommentWrapper)obj;

		if (Objects.equals(_comment, commentWrapper._comment)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _comment.getStagedModelType();
	}

	@Override
	public Comment getWrappedModel() {
		return _comment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _comment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _comment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_comment.resetOriginalValues();
	}

	private final Comment _comment;
}