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
 * This class is a wrapper for {@link VotingResult}.
 * </p>
 *
 * @author sondt
 * @see VotingResult
 * @generated
 */
@ProviderType
public class VotingResultWrapper implements VotingResult,
	ModelWrapper<VotingResult> {
	public VotingResultWrapper(VotingResult votingResult) {
		_votingResult = votingResult;
	}

	@Override
	public Class<?> getModelClass() {
		return VotingResult.class;
	}

	@Override
	public String getModelClassName() {
		return VotingResult.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("votingResultId", getVotingResultId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("votingId", getVotingId());
		attributes.put("fullname", getFullname());
		attributes.put("email", getEmail());
		attributes.put("comment", getComment());
		attributes.put("selected", getSelected());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long votingResultId = (Long)attributes.get("votingResultId");

		if (votingResultId != null) {
			setVotingResultId(votingResultId);
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

		Long votingId = (Long)attributes.get("votingId");

		if (votingId != null) {
			setVotingId(votingId);
		}

		String fullname = (String)attributes.get("fullname");

		if (fullname != null) {
			setFullname(fullname);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}

		String selected = (String)attributes.get("selected");

		if (selected != null) {
			setSelected(selected);
		}
	}

	@Override
	public Object clone() {
		return new VotingResultWrapper((VotingResult)_votingResult.clone());
	}

	@Override
	public int compareTo(VotingResult votingResult) {
		return _votingResult.compareTo(votingResult);
	}

	/**
	* Returns the comment of this voting result.
	*
	* @return the comment of this voting result
	*/
	@Override
	public String getComment() {
		return _votingResult.getComment();
	}

	/**
	* Returns the company ID of this voting result.
	*
	* @return the company ID of this voting result
	*/
	@Override
	public long getCompanyId() {
		return _votingResult.getCompanyId();
	}

	/**
	* Returns the create date of this voting result.
	*
	* @return the create date of this voting result
	*/
	@Override
	public Date getCreateDate() {
		return _votingResult.getCreateDate();
	}

	/**
	* Returns the email of this voting result.
	*
	* @return the email of this voting result
	*/
	@Override
	public String getEmail() {
		return _votingResult.getEmail();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _votingResult.getExpandoBridge();
	}

	/**
	* Returns the fullname of this voting result.
	*
	* @return the fullname of this voting result
	*/
	@Override
	public String getFullname() {
		return _votingResult.getFullname();
	}

	/**
	* Returns the group ID of this voting result.
	*
	* @return the group ID of this voting result
	*/
	@Override
	public long getGroupId() {
		return _votingResult.getGroupId();
	}

	/**
	* Returns the modified date of this voting result.
	*
	* @return the modified date of this voting result
	*/
	@Override
	public Date getModifiedDate() {
		return _votingResult.getModifiedDate();
	}

	/**
	* Returns the primary key of this voting result.
	*
	* @return the primary key of this voting result
	*/
	@Override
	public long getPrimaryKey() {
		return _votingResult.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _votingResult.getPrimaryKeyObj();
	}

	/**
	* Returns the selected of this voting result.
	*
	* @return the selected of this voting result
	*/
	@Override
	public String getSelected() {
		return _votingResult.getSelected();
	}

	/**
	* Returns the user ID of this voting result.
	*
	* @return the user ID of this voting result
	*/
	@Override
	public long getUserId() {
		return _votingResult.getUserId();
	}

	/**
	* Returns the user name of this voting result.
	*
	* @return the user name of this voting result
	*/
	@Override
	public String getUserName() {
		return _votingResult.getUserName();
	}

	/**
	* Returns the user uuid of this voting result.
	*
	* @return the user uuid of this voting result
	*/
	@Override
	public String getUserUuid() {
		return _votingResult.getUserUuid();
	}

	/**
	* Returns the uuid of this voting result.
	*
	* @return the uuid of this voting result
	*/
	@Override
	public String getUuid() {
		return _votingResult.getUuid();
	}

	/**
	* Returns the voting ID of this voting result.
	*
	* @return the voting ID of this voting result
	*/
	@Override
	public long getVotingId() {
		return _votingResult.getVotingId();
	}

	/**
	* Returns the voting result ID of this voting result.
	*
	* @return the voting result ID of this voting result
	*/
	@Override
	public long getVotingResultId() {
		return _votingResult.getVotingResultId();
	}

	@Override
	public int hashCode() {
		return _votingResult.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _votingResult.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _votingResult.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _votingResult.isNew();
	}

	@Override
	public void persist() {
		_votingResult.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_votingResult.setCachedModel(cachedModel);
	}

	/**
	* Sets the comment of this voting result.
	*
	* @param comment the comment of this voting result
	*/
	@Override
	public void setComment(String comment) {
		_votingResult.setComment(comment);
	}

	/**
	* Sets the company ID of this voting result.
	*
	* @param companyId the company ID of this voting result
	*/
	@Override
	public void setCompanyId(long companyId) {
		_votingResult.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this voting result.
	*
	* @param createDate the create date of this voting result
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_votingResult.setCreateDate(createDate);
	}

	/**
	* Sets the email of this voting result.
	*
	* @param email the email of this voting result
	*/
	@Override
	public void setEmail(String email) {
		_votingResult.setEmail(email);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_votingResult.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_votingResult.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_votingResult.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fullname of this voting result.
	*
	* @param fullname the fullname of this voting result
	*/
	@Override
	public void setFullname(String fullname) {
		_votingResult.setFullname(fullname);
	}

	/**
	* Sets the group ID of this voting result.
	*
	* @param groupId the group ID of this voting result
	*/
	@Override
	public void setGroupId(long groupId) {
		_votingResult.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this voting result.
	*
	* @param modifiedDate the modified date of this voting result
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_votingResult.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_votingResult.setNew(n);
	}

	/**
	* Sets the primary key of this voting result.
	*
	* @param primaryKey the primary key of this voting result
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_votingResult.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_votingResult.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the selected of this voting result.
	*
	* @param selected the selected of this voting result
	*/
	@Override
	public void setSelected(String selected) {
		_votingResult.setSelected(selected);
	}

	/**
	* Sets the user ID of this voting result.
	*
	* @param userId the user ID of this voting result
	*/
	@Override
	public void setUserId(long userId) {
		_votingResult.setUserId(userId);
	}

	/**
	* Sets the user name of this voting result.
	*
	* @param userName the user name of this voting result
	*/
	@Override
	public void setUserName(String userName) {
		_votingResult.setUserName(userName);
	}

	/**
	* Sets the user uuid of this voting result.
	*
	* @param userUuid the user uuid of this voting result
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_votingResult.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this voting result.
	*
	* @param uuid the uuid of this voting result
	*/
	@Override
	public void setUuid(String uuid) {
		_votingResult.setUuid(uuid);
	}

	/**
	* Sets the voting ID of this voting result.
	*
	* @param votingId the voting ID of this voting result
	*/
	@Override
	public void setVotingId(long votingId) {
		_votingResult.setVotingId(votingId);
	}

	/**
	* Sets the voting result ID of this voting result.
	*
	* @param votingResultId the voting result ID of this voting result
	*/
	@Override
	public void setVotingResultId(long votingResultId) {
		_votingResult.setVotingResultId(votingResultId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<VotingResult> toCacheModel() {
		return _votingResult.toCacheModel();
	}

	@Override
	public VotingResult toEscapedModel() {
		return new VotingResultWrapper(_votingResult.toEscapedModel());
	}

	@Override
	public String toString() {
		return _votingResult.toString();
	}

	@Override
	public VotingResult toUnescapedModel() {
		return new VotingResultWrapper(_votingResult.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _votingResult.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VotingResultWrapper)) {
			return false;
		}

		VotingResultWrapper votingResultWrapper = (VotingResultWrapper)obj;

		if (Objects.equals(_votingResult, votingResultWrapper._votingResult)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _votingResult.getStagedModelType();
	}

	@Override
	public VotingResult getWrappedModel() {
		return _votingResult;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _votingResult.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _votingResult.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_votingResult.resetOriginalValues();
	}

	private final VotingResult _votingResult;
}