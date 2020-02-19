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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavu
 * @generated
 */
@ProviderType
public class QuestionSoap implements Serializable {
	public static QuestionSoap toSoapModel(Question model) {
		QuestionSoap soapModel = new QuestionSoap();

		soapModel.setQuestionId(model.getQuestionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFullname(model.getFullname());
		soapModel.setEmail(model.getEmail());
		soapModel.setContent(model.getContent());
		soapModel.setPublish(model.getPublish());
		soapModel.setDomainCode(model.getDomainCode());
		soapModel.setDomainName(model.getDomainName());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setGovAgencyName(model.getGovAgencyName());
		soapModel.setQuestionType(model.getQuestionType());
		soapModel.setSubDomainCode(model.getSubDomainCode());
		soapModel.setSubDomainName(model.getSubDomainName());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setSynced(model.getSynced());

		return soapModel;
	}

	public static QuestionSoap[] toSoapModels(Question[] models) {
		QuestionSoap[] soapModels = new QuestionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static QuestionSoap[][] toSoapModels(Question[][] models) {
		QuestionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new QuestionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new QuestionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static QuestionSoap[] toSoapModels(List<Question> models) {
		List<QuestionSoap> soapModels = new ArrayList<QuestionSoap>(models.size());

		for (Question model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new QuestionSoap[soapModels.size()]);
	}

	public QuestionSoap() {
	}

	public long getPrimaryKey() {
		return _questionId;
	}

	public void setPrimaryKey(long pk) {
		setQuestionId(pk);
	}

	public long getQuestionId() {
		return _questionId;
	}

	public void setQuestionId(long questionId) {
		_questionId = questionId;
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

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public int getPublish() {
		return _publish;
	}

	public void setPublish(int publish) {
		_publish = publish;
	}

	public String getDomainCode() {
		return _domainCode;
	}

	public void setDomainCode(String domainCode) {
		_domainCode = domainCode;
	}

	public String getDomainName() {
		return _domainName;
	}

	public void setDomainName(String domainName) {
		_domainName = domainName;
	}

	public String getGovAgencyCode() {
		return _govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		_govAgencyCode = govAgencyCode;
	}

	public String getGovAgencyName() {
		return _govAgencyName;
	}

	public void setGovAgencyName(String govAgencyName) {
		_govAgencyName = govAgencyName;
	}

	public String getQuestionType() {
		return _questionType;
	}

	public void setQuestionType(String questionType) {
		_questionType = questionType;
	}

	public String getSubDomainCode() {
		return _subDomainCode;
	}

	public void setSubDomainCode(String subDomainCode) {
		_subDomainCode = subDomainCode;
	}

	public String getSubDomainName() {
		return _subDomainName;
	}

	public void setSubDomainName(String subDomainName) {
		_subDomainName = subDomainName;
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

	public int getSynced() {
		return _synced;
	}

	public void setSynced(int synced) {
		_synced = synced;
	}

	private long _questionId;
	private long _companyId;
	private long _groupId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _fullname;
	private String _email;
	private String _content;
	private int _publish;
	private String _domainCode;
	private String _domainName;
	private String _govAgencyCode;
	private String _govAgencyName;
	private String _questionType;
	private String _subDomainCode;
	private String _subDomainName;
	private String _className;
	private String _classPK;
	private int _synced;
}