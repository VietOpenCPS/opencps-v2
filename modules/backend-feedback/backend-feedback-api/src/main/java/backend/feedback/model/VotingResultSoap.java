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
public class VotingResultSoap implements Serializable {
	public static VotingResultSoap toSoapModel(VotingResult model) {
		VotingResultSoap soapModel = new VotingResultSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setVotingResultId(model.getVotingResultId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setVotingId(model.getVotingId());
		soapModel.setFullname(model.getFullname());
		soapModel.setEmail(model.getEmail());
		soapModel.setComment(model.getComment());
		soapModel.setSelected(model.getSelected());

		return soapModel;
	}

	public static VotingResultSoap[] toSoapModels(VotingResult[] models) {
		VotingResultSoap[] soapModels = new VotingResultSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VotingResultSoap[][] toSoapModels(VotingResult[][] models) {
		VotingResultSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VotingResultSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VotingResultSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VotingResultSoap[] toSoapModels(List<VotingResult> models) {
		List<VotingResultSoap> soapModels = new ArrayList<VotingResultSoap>(models.size());

		for (VotingResult model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VotingResultSoap[soapModels.size()]);
	}

	public VotingResultSoap() {
	}

	public long getPrimaryKey() {
		return _votingResultId;
	}

	public void setPrimaryKey(long pk) {
		setVotingResultId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getVotingResultId() {
		return _votingResultId;
	}

	public void setVotingResultId(long votingResultId) {
		_votingResultId = votingResultId;
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

	public long getVotingId() {
		return _votingId;
	}

	public void setVotingId(long votingId) {
		_votingId = votingId;
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

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public String getSelected() {
		return _selected;
	}

	public void setSelected(String selected) {
		_selected = selected;
	}

	private String _uuid;
	private long _votingResultId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _votingId;
	private String _fullname;
	private String _email;
	private String _comment;
	private String _selected;
}