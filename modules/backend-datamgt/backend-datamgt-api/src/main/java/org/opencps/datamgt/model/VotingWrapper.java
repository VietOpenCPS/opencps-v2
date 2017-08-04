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

package org.opencps.datamgt.model;

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
 * This class is a wrapper for {@link Voting}.
 * </p>
 *
 * @author Binhth
 * @see Voting
 * @generated
 */
@ProviderType
public class VotingWrapper implements Voting, ModelWrapper<Voting> {
	public VotingWrapper(Voting voting) {
		_voting = voting;
	}

	@Override
	public Class<?> getModelClass() {
		return Voting.class;
	}

	@Override
	public String getModelClassName() {
		return Voting.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("votingId", getVotingId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("subject", getSubject());
		attributes.put("answers", getAnswers());
		attributes.put("dueDate", getDueDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long votingId = (Long)attributes.get("votingId");

		if (votingId != null) {
			setVotingId(votingId);
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

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		String answers = (String)attributes.get("answers");

		if (answers != null) {
			setAnswers(answers);
		}

		Date dueDate = (Date)attributes.get("dueDate");

		if (dueDate != null) {
			setDueDate(dueDate);
		}
	}

	@Override
	public Voting toEscapedModel() {
		return new VotingWrapper(_voting.toEscapedModel());
	}

	@Override
	public Voting toUnescapedModel() {
		return new VotingWrapper(_voting.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _voting.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _voting.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _voting.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _voting.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Voting> toCacheModel() {
		return _voting.toCacheModel();
	}

	@Override
	public int compareTo(Voting voting) {
		return _voting.compareTo(voting);
	}

	@Override
	public int hashCode() {
		return _voting.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _voting.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new VotingWrapper((Voting)_voting.clone());
	}

	/**
	* Returns the answers of this voting.
	*
	* @return the answers of this voting
	*/
	@Override
	public java.lang.String getAnswers() {
		return _voting.getAnswers();
	}

	/**
	* Returns the class name of this voting.
	*
	* @return the class name of this voting
	*/
	@Override
	public java.lang.String getClassName() {
		return _voting.getClassName();
	}

	/**
	* Returns the class pk of this voting.
	*
	* @return the class pk of this voting
	*/
	@Override
	public java.lang.String getClassPK() {
		return _voting.getClassPK();
	}

	/**
	* Returns the subject of this voting.
	*
	* @return the subject of this voting
	*/
	@Override
	public java.lang.String getSubject() {
		return _voting.getSubject();
	}

	/**
	* Returns the user name of this voting.
	*
	* @return the user name of this voting
	*/
	@Override
	public java.lang.String getUserName() {
		return _voting.getUserName();
	}

	/**
	* Returns the user uuid of this voting.
	*
	* @return the user uuid of this voting
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _voting.getUserUuid();
	}

	/**
	* Returns the uuid of this voting.
	*
	* @return the uuid of this voting
	*/
	@Override
	public java.lang.String getUuid() {
		return _voting.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _voting.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _voting.toXmlString();
	}

	/**
	* Returns the create date of this voting.
	*
	* @return the create date of this voting
	*/
	@Override
	public Date getCreateDate() {
		return _voting.getCreateDate();
	}

	/**
	* Returns the due date of this voting.
	*
	* @return the due date of this voting
	*/
	@Override
	public Date getDueDate() {
		return _voting.getDueDate();
	}

	/**
	* Returns the modified date of this voting.
	*
	* @return the modified date of this voting
	*/
	@Override
	public Date getModifiedDate() {
		return _voting.getModifiedDate();
	}

	/**
	* Returns the company ID of this voting.
	*
	* @return the company ID of this voting
	*/
	@Override
	public long getCompanyId() {
		return _voting.getCompanyId();
	}

	/**
	* Returns the group ID of this voting.
	*
	* @return the group ID of this voting
	*/
	@Override
	public long getGroupId() {
		return _voting.getGroupId();
	}

	/**
	* Returns the primary key of this voting.
	*
	* @return the primary key of this voting
	*/
	@Override
	public long getPrimaryKey() {
		return _voting.getPrimaryKey();
	}

	/**
	* Returns the user ID of this voting.
	*
	* @return the user ID of this voting
	*/
	@Override
	public long getUserId() {
		return _voting.getUserId();
	}

	/**
	* Returns the voting ID of this voting.
	*
	* @return the voting ID of this voting
	*/
	@Override
	public long getVotingId() {
		return _voting.getVotingId();
	}

	@Override
	public void persist() {
		_voting.persist();
	}

	/**
	* Sets the answers of this voting.
	*
	* @param answers the answers of this voting
	*/
	@Override
	public void setAnswers(java.lang.String answers) {
		_voting.setAnswers(answers);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_voting.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this voting.
	*
	* @param className the class name of this voting
	*/
	@Override
	public void setClassName(java.lang.String className) {
		_voting.setClassName(className);
	}

	/**
	* Sets the class pk of this voting.
	*
	* @param classPK the class pk of this voting
	*/
	@Override
	public void setClassPK(java.lang.String classPK) {
		_voting.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this voting.
	*
	* @param companyId the company ID of this voting
	*/
	@Override
	public void setCompanyId(long companyId) {
		_voting.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this voting.
	*
	* @param createDate the create date of this voting
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_voting.setCreateDate(createDate);
	}

	/**
	* Sets the due date of this voting.
	*
	* @param dueDate the due date of this voting
	*/
	@Override
	public void setDueDate(Date dueDate) {
		_voting.setDueDate(dueDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_voting.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_voting.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_voting.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this voting.
	*
	* @param groupId the group ID of this voting
	*/
	@Override
	public void setGroupId(long groupId) {
		_voting.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this voting.
	*
	* @param modifiedDate the modified date of this voting
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_voting.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_voting.setNew(n);
	}

	/**
	* Sets the primary key of this voting.
	*
	* @param primaryKey the primary key of this voting
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_voting.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_voting.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the subject of this voting.
	*
	* @param subject the subject of this voting
	*/
	@Override
	public void setSubject(java.lang.String subject) {
		_voting.setSubject(subject);
	}

	/**
	* Sets the user ID of this voting.
	*
	* @param userId the user ID of this voting
	*/
	@Override
	public void setUserId(long userId) {
		_voting.setUserId(userId);
	}

	/**
	* Sets the user name of this voting.
	*
	* @param userName the user name of this voting
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_voting.setUserName(userName);
	}

	/**
	* Sets the user uuid of this voting.
	*
	* @param userUuid the user uuid of this voting
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_voting.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this voting.
	*
	* @param uuid the uuid of this voting
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_voting.setUuid(uuid);
	}

	/**
	* Sets the voting ID of this voting.
	*
	* @param votingId the voting ID of this voting
	*/
	@Override
	public void setVotingId(long votingId) {
		_voting.setVotingId(votingId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VotingWrapper)) {
			return false;
		}

		VotingWrapper votingWrapper = (VotingWrapper)obj;

		if (Objects.equals(_voting, votingWrapper._voting)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _voting.getStagedModelType();
	}

	@Override
	public Voting getWrappedModel() {
		return _voting;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _voting.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _voting.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_voting.resetOriginalValues();
	}

	private final Voting _voting;
}