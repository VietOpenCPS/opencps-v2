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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Binhth
 * @generated
 */
@ProviderType
public class VotingSoap implements Serializable {
	public static VotingSoap toSoapModel(Voting model) {
		VotingSoap soapModel = new VotingSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setVotingId(model.getVotingId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setSubject(model.getSubject());
		soapModel.setAnswers(model.getAnswers());
		soapModel.setDueDate(model.getDueDate());

		return soapModel;
	}

	public static VotingSoap[] toSoapModels(Voting[] models) {
		VotingSoap[] soapModels = new VotingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VotingSoap[][] toSoapModels(Voting[][] models) {
		VotingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VotingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VotingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VotingSoap[] toSoapModels(List<Voting> models) {
		List<VotingSoap> soapModels = new ArrayList<VotingSoap>(models.size());

		for (Voting model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VotingSoap[soapModels.size()]);
	}

	public VotingSoap() {
	}

	public long getPrimaryKey() {
		return _votingId;
	}

	public void setPrimaryKey(long pk) {
		setVotingId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getVotingId() {
		return _votingId;
	}

	public void setVotingId(long votingId) {
		_votingId = votingId;
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

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public String getAnswers() {
		return _answers;
	}

	public void setAnswers(String answers) {
		_answers = answers;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	private String _uuid;
	private long _votingId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _className;
	private String _classPK;
	private String _subject;
	private String _answers;
	private Date _dueDate;
}