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

package org.opencps.dossiermgt.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author huymq
 * @generated
 */
@ProviderType
public class DeliverableSoap implements Serializable {
	public static DeliverableSoap toSoapModel(Deliverable model) {
		DeliverableSoap soapModel = new DeliverableSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDeliverableId(model.getDeliverableId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDeliverableCode(model.getDeliverableCode());
		soapModel.setDeliverableName(model.getDeliverableName());
		soapModel.setDeliverableType(model.getDeliverableType());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setGovAgencyName(model.getGovAgencyName());
		soapModel.setApplicantIdNo(model.getApplicantIdNo());
		soapModel.setApplicantName(model.getApplicantName());
		soapModel.setSubject(model.getSubject());
		soapModel.setFormData(model.getFormData());
		soapModel.setFormScript(model.getFormScript());
		soapModel.setFormReport(model.getFormReport());
		soapModel.setExpireDate(model.getExpireDate());
		soapModel.setIssueDate(model.getIssueDate());
		soapModel.setRevalidate(model.getRevalidate());
		soapModel.setDeliverableState(model.getDeliverableState());

		return soapModel;
	}

	public static DeliverableSoap[] toSoapModels(Deliverable[] models) {
		DeliverableSoap[] soapModels = new DeliverableSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DeliverableSoap[][] toSoapModels(Deliverable[][] models) {
		DeliverableSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DeliverableSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DeliverableSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DeliverableSoap[] toSoapModels(List<Deliverable> models) {
		List<DeliverableSoap> soapModels = new ArrayList<DeliverableSoap>(models.size());

		for (Deliverable model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DeliverableSoap[soapModels.size()]);
	}

	public DeliverableSoap() {
	}

	public long getPrimaryKey() {
		return _deliverableId;
	}

	public void setPrimaryKey(long pk) {
		setDeliverableId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDeliverableId() {
		return _deliverableId;
	}

	public void setDeliverableId(long deliverableId) {
		_deliverableId = deliverableId;
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

	public String getDeliverableCode() {
		return _deliverableCode;
	}

	public void setDeliverableCode(String deliverableCode) {
		_deliverableCode = deliverableCode;
	}

	public String getDeliverableName() {
		return _deliverableName;
	}

	public void setDeliverableName(String deliverableName) {
		_deliverableName = deliverableName;
	}

	public String getDeliverableType() {
		return _deliverableType;
	}

	public void setDeliverableType(String deliverableType) {
		_deliverableType = deliverableType;
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

	public String getApplicantIdNo() {
		return _applicantIdNo;
	}

	public void setApplicantIdNo(String applicantIdNo) {
		_applicantIdNo = applicantIdNo;
	}

	public String getApplicantName() {
		return _applicantName;
	}

	public void setApplicantName(String applicantName) {
		_applicantName = applicantName;
	}

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public String getFormData() {
		return _formData;
	}

	public void setFormData(String formData) {
		_formData = formData;
	}

	public String getFormScript() {
		return _formScript;
	}

	public void setFormScript(String formScript) {
		_formScript = formScript;
	}

	public String getFormReport() {
		return _formReport;
	}

	public void setFormReport(String formReport) {
		_formReport = formReport;
	}

	public Date getExpireDate() {
		return _expireDate;
	}

	public void setExpireDate(Date expireDate) {
		_expireDate = expireDate;
	}

	public Date getIssueDate() {
		return _issueDate;
	}

	public void setIssueDate(Date issueDate) {
		_issueDate = issueDate;
	}

	public Date getRevalidate() {
		return _revalidate;
	}

	public void setRevalidate(Date revalidate) {
		_revalidate = revalidate;
	}

	public String getDeliverableState() {
		return _deliverableState;
	}

	public void setDeliverableState(String deliverableState) {
		_deliverableState = deliverableState;
	}

	private String _uuid;
	private long _deliverableId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _deliverableCode;
	private String _deliverableName;
	private String _deliverableType;
	private String _govAgencyCode;
	private String _govAgencyName;
	private String _applicantIdNo;
	private String _applicantName;
	private String _subject;
	private String _formData;
	private String _formScript;
	private String _formReport;
	private Date _expireDate;
	private Date _issueDate;
	private Date _revalidate;
	private String _deliverableState;
}