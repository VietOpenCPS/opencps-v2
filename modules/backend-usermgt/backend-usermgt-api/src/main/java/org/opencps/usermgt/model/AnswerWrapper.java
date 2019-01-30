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

package org.opencps.usermgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Answer}.
 * </p>
 *
 * @author khoavu
 * @see Answer
 * @generated
 */
@ProviderType
public class AnswerWrapper implements Answer, ModelWrapper<Answer> {
	public AnswerWrapper(Answer answer) {
		_answer = answer;
	}

	@Override
	public Class<?> getModelClass() {
		return Answer.class;
	}

	@Override
	public String getModelClassName() {
		return Answer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("answerId", getAnswerId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("questionId", getQuestionId());
		attributes.put("content", getContent());
		attributes.put("publish", getPublish());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long answerId = (Long)attributes.get("answerId");

		if (answerId != null) {
			setAnswerId(answerId);
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

		Long questionId = (Long)attributes.get("questionId");

		if (questionId != null) {
			setQuestionId(questionId);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer publish = (Integer)attributes.get("publish");

		if (publish != null) {
			setPublish(publish);
		}
	}

	@Override
	public Object clone() {
		return new AnswerWrapper((Answer)_answer.clone());
	}

	@Override
	public int compareTo(Answer answer) {
		return _answer.compareTo(answer);
	}

	/**
	* Returns the answer ID of this answer.
	*
	* @return the answer ID of this answer
	*/
	@Override
	public long getAnswerId() {
		return _answer.getAnswerId();
	}

	/**
	* Returns the company ID of this answer.
	*
	* @return the company ID of this answer
	*/
	@Override
	public long getCompanyId() {
		return _answer.getCompanyId();
	}

	/**
	* Returns the content of this answer.
	*
	* @return the content of this answer
	*/
	@Override
	public String getContent() {
		return _answer.getContent();
	}

	/**
	* Returns the create date of this answer.
	*
	* @return the create date of this answer
	*/
	@Override
	public Date getCreateDate() {
		return _answer.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _answer.getExpandoBridge();
	}

	/**
	* Returns the group ID of this answer.
	*
	* @return the group ID of this answer
	*/
	@Override
	public long getGroupId() {
		return _answer.getGroupId();
	}

	/**
	* Returns the modified date of this answer.
	*
	* @return the modified date of this answer
	*/
	@Override
	public Date getModifiedDate() {
		return _answer.getModifiedDate();
	}

	/**
	* Returns the primary key of this answer.
	*
	* @return the primary key of this answer
	*/
	@Override
	public long getPrimaryKey() {
		return _answer.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _answer.getPrimaryKeyObj();
	}

	/**
	* Returns the publish of this answer.
	*
	* @return the publish of this answer
	*/
	@Override
	public int getPublish() {
		return _answer.getPublish();
	}

	/**
	* Returns the question ID of this answer.
	*
	* @return the question ID of this answer
	*/
	@Override
	public long getQuestionId() {
		return _answer.getQuestionId();
	}

	/**
	* Returns the user ID of this answer.
	*
	* @return the user ID of this answer
	*/
	@Override
	public long getUserId() {
		return _answer.getUserId();
	}

	/**
	* Returns the user name of this answer.
	*
	* @return the user name of this answer
	*/
	@Override
	public String getUserName() {
		return _answer.getUserName();
	}

	/**
	* Returns the user uuid of this answer.
	*
	* @return the user uuid of this answer
	*/
	@Override
	public String getUserUuid() {
		return _answer.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _answer.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _answer.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _answer.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _answer.isNew();
	}

	@Override
	public void persist() {
		_answer.persist();
	}

	/**
	* Sets the answer ID of this answer.
	*
	* @param answerId the answer ID of this answer
	*/
	@Override
	public void setAnswerId(long answerId) {
		_answer.setAnswerId(answerId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_answer.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this answer.
	*
	* @param companyId the company ID of this answer
	*/
	@Override
	public void setCompanyId(long companyId) {
		_answer.setCompanyId(companyId);
	}

	/**
	* Sets the content of this answer.
	*
	* @param content the content of this answer
	*/
	@Override
	public void setContent(String content) {
		_answer.setContent(content);
	}

	/**
	* Sets the create date of this answer.
	*
	* @param createDate the create date of this answer
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_answer.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_answer.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_answer.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_answer.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this answer.
	*
	* @param groupId the group ID of this answer
	*/
	@Override
	public void setGroupId(long groupId) {
		_answer.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this answer.
	*
	* @param modifiedDate the modified date of this answer
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_answer.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_answer.setNew(n);
	}

	/**
	* Sets the primary key of this answer.
	*
	* @param primaryKey the primary key of this answer
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_answer.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_answer.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publish of this answer.
	*
	* @param publish the publish of this answer
	*/
	@Override
	public void setPublish(int publish) {
		_answer.setPublish(publish);
	}

	/**
	* Sets the question ID of this answer.
	*
	* @param questionId the question ID of this answer
	*/
	@Override
	public void setQuestionId(long questionId) {
		_answer.setQuestionId(questionId);
	}

	/**
	* Sets the user ID of this answer.
	*
	* @param userId the user ID of this answer
	*/
	@Override
	public void setUserId(long userId) {
		_answer.setUserId(userId);
	}

	/**
	* Sets the user name of this answer.
	*
	* @param userName the user name of this answer
	*/
	@Override
	public void setUserName(String userName) {
		_answer.setUserName(userName);
	}

	/**
	* Sets the user uuid of this answer.
	*
	* @param userUuid the user uuid of this answer
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_answer.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Answer> toCacheModel() {
		return _answer.toCacheModel();
	}

	@Override
	public Answer toEscapedModel() {
		return new AnswerWrapper(_answer.toEscapedModel());
	}

	@Override
	public String toString() {
		return _answer.toString();
	}

	@Override
	public Answer toUnescapedModel() {
		return new AnswerWrapper(_answer.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _answer.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AnswerWrapper)) {
			return false;
		}

		AnswerWrapper answerWrapper = (AnswerWrapper)obj;

		if (Objects.equals(_answer, answerWrapper._answer)) {
			return true;
		}

		return false;
	}

	@Override
	public Answer getWrappedModel() {
		return _answer;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _answer.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _answer.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_answer.resetOriginalValues();
	}

	private final Answer _answer;
}