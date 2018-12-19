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
 * This class is a wrapper for {@link Voting}.
 * </p>
 *
 * @author sondt
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
		attributes.put("choices", getChoices());
		attributes.put("templateNo", getTemplateNo());
		attributes.put("commentable", isCommentable());
		attributes.put("votingCode", getVotingCode());

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

		String choices = (String)attributes.get("choices");

		if (choices != null) {
			setChoices(choices);
		}

		String templateNo = (String)attributes.get("templateNo");

		if (templateNo != null) {
			setTemplateNo(templateNo);
		}

		Boolean commentable = (Boolean)attributes.get("commentable");

		if (commentable != null) {
			setCommentable(commentable);
		}

		String votingCode = (String)attributes.get("votingCode");

		if (votingCode != null) {
			setVotingCode(votingCode);
		}
	}

	@Override
	public Object clone() {
		return new VotingWrapper((Voting)_voting.clone());
	}

	@Override
	public int compareTo(Voting voting) {
		return _voting.compareTo(voting);
	}

	/**
	* Returns the choices of this voting.
	*
	* @return the choices of this voting
	*/
	@Override
	public String getChoices() {
		return _voting.getChoices();
	}

	/**
	* Returns the class name of this voting.
	*
	* @return the class name of this voting
	*/
	@Override
	public String getClassName() {
		return _voting.getClassName();
	}

	/**
	* Returns the class pk of this voting.
	*
	* @return the class pk of this voting
	*/
	@Override
	public String getClassPK() {
		return _voting.getClassPK();
	}

	/**
	* Returns the commentable of this voting.
	*
	* @return the commentable of this voting
	*/
	@Override
	public boolean getCommentable() {
		return _voting.getCommentable();
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
	* Returns the create date of this voting.
	*
	* @return the create date of this voting
	*/
	@Override
	public Date getCreateDate() {
		return _voting.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _voting.getExpandoBridge();
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
	* Returns the modified date of this voting.
	*
	* @return the modified date of this voting
	*/
	@Override
	public Date getModifiedDate() {
		return _voting.getModifiedDate();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _voting.getPrimaryKeyObj();
	}

	/**
	* Returns the subject of this voting.
	*
	* @return the subject of this voting
	*/
	@Override
	public String getSubject() {
		return _voting.getSubject();
	}

	/**
	* Returns the template no of this voting.
	*
	* @return the template no of this voting
	*/
	@Override
	public String getTemplateNo() {
		return _voting.getTemplateNo();
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
	* Returns the user name of this voting.
	*
	* @return the user name of this voting
	*/
	@Override
	public String getUserName() {
		return _voting.getUserName();
	}

	/**
	* Returns the user uuid of this voting.
	*
	* @return the user uuid of this voting
	*/
	@Override
	public String getUserUuid() {
		return _voting.getUserUuid();
	}

	/**
	* Returns the uuid of this voting.
	*
	* @return the uuid of this voting
	*/
	@Override
	public String getUuid() {
		return _voting.getUuid();
	}

	/**
	* Returns the voting code of this voting.
	*
	* @return the voting code of this voting
	*/
	@Override
	public String getVotingCode() {
		return _voting.getVotingCode();
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
	public int hashCode() {
		return _voting.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _voting.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this voting is commentable.
	*
	* @return <code>true</code> if this voting is commentable; <code>false</code> otherwise
	*/
	@Override
	public boolean isCommentable() {
		return _voting.isCommentable();
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
	public void persist() {
		_voting.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_voting.setCachedModel(cachedModel);
	}

	/**
	* Sets the choices of this voting.
	*
	* @param choices the choices of this voting
	*/
	@Override
	public void setChoices(String choices) {
		_voting.setChoices(choices);
	}

	/**
	* Sets the class name of this voting.
	*
	* @param className the class name of this voting
	*/
	@Override
	public void setClassName(String className) {
		_voting.setClassName(className);
	}

	/**
	* Sets the class pk of this voting.
	*
	* @param classPK the class pk of this voting
	*/
	@Override
	public void setClassPK(String classPK) {
		_voting.setClassPK(classPK);
	}

	/**
	* Sets whether this voting is commentable.
	*
	* @param commentable the commentable of this voting
	*/
	@Override
	public void setCommentable(boolean commentable) {
		_voting.setCommentable(commentable);
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

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_voting.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_voting.setExpandoBridgeAttributes(expandoBridge);
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
	public void setSubject(String subject) {
		_voting.setSubject(subject);
	}

	/**
	* Sets the template no of this voting.
	*
	* @param templateNo the template no of this voting
	*/
	@Override
	public void setTemplateNo(String templateNo) {
		_voting.setTemplateNo(templateNo);
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
	public void setUserName(String userName) {
		_voting.setUserName(userName);
	}

	/**
	* Sets the user uuid of this voting.
	*
	* @param userUuid the user uuid of this voting
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_voting.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this voting.
	*
	* @param uuid the uuid of this voting
	*/
	@Override
	public void setUuid(String uuid) {
		_voting.setUuid(uuid);
	}

	/**
	* Sets the voting code of this voting.
	*
	* @param votingCode the voting code of this voting
	*/
	@Override
	public void setVotingCode(String votingCode) {
		_voting.setVotingCode(votingCode);
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
	public com.liferay.portal.kernel.model.CacheModel<Voting> toCacheModel() {
		return _voting.toCacheModel();
	}

	@Override
	public Voting toEscapedModel() {
		return new VotingWrapper(_voting.toEscapedModel());
	}

	@Override
	public String toString() {
		return _voting.toString();
	}

	@Override
	public Voting toUnescapedModel() {
		return new VotingWrapper(_voting.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _voting.toXmlString();
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