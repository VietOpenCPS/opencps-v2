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
 * This class is a wrapper for {@link Question}.
 * </p>
 *
 * @author khoavu
 * @see Question
 * @generated
 */
@ProviderType
public class QuestionWrapper implements Question, ModelWrapper<Question> {
	public QuestionWrapper(Question question) {
		_question = question;
	}

	@Override
	public Class<?> getModelClass() {
		return Question.class;
	}

	@Override
	public String getModelClassName() {
		return Question.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("questionId", getQuestionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("fullname", getFullname());
		attributes.put("email", getEmail());
		attributes.put("content", getContent());
		attributes.put("publish", getPublish());
		attributes.put("domainCode", getDomainCode());
		attributes.put("domainName", getDomainName());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("govAgencyName", getGovAgencyName());
		attributes.put("questionType", getQuestionType());
		attributes.put("subDomainCode", getSubDomainCode());
		attributes.put("subDomainName", getSubDomainName());
		attributes.put("phone", getPhone());
		attributes.put("address", getAddress());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("synced", getSynced());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long questionId = (Long)attributes.get("questionId");

		if (questionId != null) {
			setQuestionId(questionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String fullname = (String)attributes.get("fullname");

		if (fullname != null) {
			setFullname(fullname);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer publish = (Integer)attributes.get("publish");

		if (publish != null) {
			setPublish(publish);
		}

		String domainCode = (String)attributes.get("domainCode");

		if (domainCode != null) {
			setDomainCode(domainCode);
		}

		String domainName = (String)attributes.get("domainName");

		if (domainName != null) {
			setDomainName(domainName);
		}

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}

		String govAgencyName = (String)attributes.get("govAgencyName");

		if (govAgencyName != null) {
			setGovAgencyName(govAgencyName);
		}

		String questionType = (String)attributes.get("questionType");

		if (questionType != null) {
			setQuestionType(questionType);
		}

		String subDomainCode = (String)attributes.get("subDomainCode");

		if (subDomainCode != null) {
			setSubDomainCode(subDomainCode);
		}

		String subDomainName = (String)attributes.get("subDomainName");

		if (subDomainName != null) {
			setSubDomainName(subDomainName);
		}

		String phone = (String)attributes.get("phone");

		if (phone != null) {
			setPhone(phone);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		String classPK = (String)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer synced = (Integer)attributes.get("synced");

		if (synced != null) {
			setSynced(synced);
		}
	}

	@Override
	public Object clone() {
		return new QuestionWrapper((Question)_question.clone());
	}

	@Override
	public int compareTo(Question question) {
		return _question.compareTo(question);
	}

	/**
	* Returns the address of this question.
	*
	* @return the address of this question
	*/
	@Override
	public String getAddress() {
		return _question.getAddress();
	}

	/**
	* Returns the class name of this question.
	*
	* @return the class name of this question
	*/
	@Override
	public String getClassName() {
		return _question.getClassName();
	}

	/**
	* Returns the class pk of this question.
	*
	* @return the class pk of this question
	*/
	@Override
	public String getClassPK() {
		return _question.getClassPK();
	}

	/**
	* Returns the company ID of this question.
	*
	* @return the company ID of this question
	*/
	@Override
	public long getCompanyId() {
		return _question.getCompanyId();
	}

	/**
	* Returns the content of this question.
	*
	* @return the content of this question
	*/
	@Override
	public String getContent() {
		return _question.getContent();
	}

	/**
	* Returns the create date of this question.
	*
	* @return the create date of this question
	*/
	@Override
	public Date getCreateDate() {
		return _question.getCreateDate();
	}

	/**
	* Returns the domain code of this question.
	*
	* @return the domain code of this question
	*/
	@Override
	public String getDomainCode() {
		return _question.getDomainCode();
	}

	/**
	* Returns the domain name of this question.
	*
	* @return the domain name of this question
	*/
	@Override
	public String getDomainName() {
		return _question.getDomainName();
	}

	/**
	* Returns the email of this question.
	*
	* @return the email of this question
	*/
	@Override
	public String getEmail() {
		return _question.getEmail();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _question.getExpandoBridge();
	}

	/**
	* Returns the fullname of this question.
	*
	* @return the fullname of this question
	*/
	@Override
	public String getFullname() {
		return _question.getFullname();
	}

	/**
	* Returns the gov agency code of this question.
	*
	* @return the gov agency code of this question
	*/
	@Override
	public String getGovAgencyCode() {
		return _question.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this question.
	*
	* @return the gov agency name of this question
	*/
	@Override
	public String getGovAgencyName() {
		return _question.getGovAgencyName();
	}

	/**
	* Returns the group ID of this question.
	*
	* @return the group ID of this question
	*/
	@Override
	public long getGroupId() {
		return _question.getGroupId();
	}

	/**
	* Returns the modified date of this question.
	*
	* @return the modified date of this question
	*/
	@Override
	public Date getModifiedDate() {
		return _question.getModifiedDate();
	}

	/**
	* Returns the phone of this question.
	*
	* @return the phone of this question
	*/
	@Override
	public String getPhone() {
		return _question.getPhone();
	}

	/**
	* Returns the primary key of this question.
	*
	* @return the primary key of this question
	*/
	@Override
	public long getPrimaryKey() {
		return _question.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _question.getPrimaryKeyObj();
	}

	/**
	* Returns the publish of this question.
	*
	* @return the publish of this question
	*/
	@Override
	public int getPublish() {
		return _question.getPublish();
	}

	/**
	* Returns the question ID of this question.
	*
	* @return the question ID of this question
	*/
	@Override
	public long getQuestionId() {
		return _question.getQuestionId();
	}

	/**
	* Returns the question type of this question.
	*
	* @return the question type of this question
	*/
	@Override
	public String getQuestionType() {
		return _question.getQuestionType();
	}

	/**
	* Returns the sub domain code of this question.
	*
	* @return the sub domain code of this question
	*/
	@Override
	public String getSubDomainCode() {
		return _question.getSubDomainCode();
	}

	/**
	* Returns the sub domain name of this question.
	*
	* @return the sub domain name of this question
	*/
	@Override
	public String getSubDomainName() {
		return _question.getSubDomainName();
	}

	/**
	* Returns the synced of this question.
	*
	* @return the synced of this question
	*/
	@Override
	public int getSynced() {
		return _question.getSynced();
	}

	@Override
	public int hashCode() {
		return _question.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _question.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _question.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _question.isNew();
	}

	@Override
	public void persist() {
		_question.persist();
	}

	/**
	* Sets the address of this question.
	*
	* @param address the address of this question
	*/
	@Override
	public void setAddress(String address) {
		_question.setAddress(address);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_question.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this question.
	*
	* @param className the class name of this question
	*/
	@Override
	public void setClassName(String className) {
		_question.setClassName(className);
	}

	/**
	* Sets the class pk of this question.
	*
	* @param classPK the class pk of this question
	*/
	@Override
	public void setClassPK(String classPK) {
		_question.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this question.
	*
	* @param companyId the company ID of this question
	*/
	@Override
	public void setCompanyId(long companyId) {
		_question.setCompanyId(companyId);
	}

	/**
	* Sets the content of this question.
	*
	* @param content the content of this question
	*/
	@Override
	public void setContent(String content) {
		_question.setContent(content);
	}

	/**
	* Sets the create date of this question.
	*
	* @param createDate the create date of this question
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_question.setCreateDate(createDate);
	}

	/**
	* Sets the domain code of this question.
	*
	* @param domainCode the domain code of this question
	*/
	@Override
	public void setDomainCode(String domainCode) {
		_question.setDomainCode(domainCode);
	}

	/**
	* Sets the domain name of this question.
	*
	* @param domainName the domain name of this question
	*/
	@Override
	public void setDomainName(String domainName) {
		_question.setDomainName(domainName);
	}

	/**
	* Sets the email of this question.
	*
	* @param email the email of this question
	*/
	@Override
	public void setEmail(String email) {
		_question.setEmail(email);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_question.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_question.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_question.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fullname of this question.
	*
	* @param fullname the fullname of this question
	*/
	@Override
	public void setFullname(String fullname) {
		_question.setFullname(fullname);
	}

	/**
	* Sets the gov agency code of this question.
	*
	* @param govAgencyCode the gov agency code of this question
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_question.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this question.
	*
	* @param govAgencyName the gov agency name of this question
	*/
	@Override
	public void setGovAgencyName(String govAgencyName) {
		_question.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the group ID of this question.
	*
	* @param groupId the group ID of this question
	*/
	@Override
	public void setGroupId(long groupId) {
		_question.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this question.
	*
	* @param modifiedDate the modified date of this question
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_question.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_question.setNew(n);
	}

	/**
	* Sets the phone of this question.
	*
	* @param phone the phone of this question
	*/
	@Override
	public void setPhone(String phone) {
		_question.setPhone(phone);
	}

	/**
	* Sets the primary key of this question.
	*
	* @param primaryKey the primary key of this question
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_question.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_question.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publish of this question.
	*
	* @param publish the publish of this question
	*/
	@Override
	public void setPublish(int publish) {
		_question.setPublish(publish);
	}

	/**
	* Sets the question ID of this question.
	*
	* @param questionId the question ID of this question
	*/
	@Override
	public void setQuestionId(long questionId) {
		_question.setQuestionId(questionId);
	}

	/**
	* Sets the question type of this question.
	*
	* @param questionType the question type of this question
	*/
	@Override
	public void setQuestionType(String questionType) {
		_question.setQuestionType(questionType);
	}

	/**
	* Sets the sub domain code of this question.
	*
	* @param subDomainCode the sub domain code of this question
	*/
	@Override
	public void setSubDomainCode(String subDomainCode) {
		_question.setSubDomainCode(subDomainCode);
	}

	/**
	* Sets the sub domain name of this question.
	*
	* @param subDomainName the sub domain name of this question
	*/
	@Override
	public void setSubDomainName(String subDomainName) {
		_question.setSubDomainName(subDomainName);
	}

	/**
	* Sets the synced of this question.
	*
	* @param synced the synced of this question
	*/
	@Override
	public void setSynced(int synced) {
		_question.setSynced(synced);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Question> toCacheModel() {
		return _question.toCacheModel();
	}

	@Override
	public Question toEscapedModel() {
		return new QuestionWrapper(_question.toEscapedModel());
	}

	@Override
	public String toString() {
		return _question.toString();
	}

	@Override
	public Question toUnescapedModel() {
		return new QuestionWrapper(_question.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _question.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof QuestionWrapper)) {
			return false;
		}

		QuestionWrapper questionWrapper = (QuestionWrapper)obj;

		if (Objects.equals(_question, questionWrapper._question)) {
			return true;
		}

		return false;
	}

	@Override
	public Question getWrappedModel() {
		return _question;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _question.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _question.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_question.resetOriginalValues();
	}

	private final Question _question;
}