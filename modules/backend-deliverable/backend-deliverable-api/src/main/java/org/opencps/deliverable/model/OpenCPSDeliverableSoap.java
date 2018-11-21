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

package org.opencps.deliverable.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author binhth
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableSoap implements Serializable {
	public static OpenCPSDeliverableSoap toSoapModel(OpenCPSDeliverable model) {
		OpenCPSDeliverableSoap soapModel = new OpenCPSDeliverableSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDeliverableId(model.getDeliverableId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
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
		soapModel.setFormScriptFileId(model.getFormScriptFileId());
		soapModel.setFormReport(model.getFormReport());
		soapModel.setFormReportFileId(model.getFormReportFileId());
		soapModel.setIssueDate(model.getIssueDate());
		soapModel.setExpireDate(model.getExpireDate());
		soapModel.setRevalidate(model.getRevalidate());
		soapModel.setDeliverableState(model.getDeliverableState());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setDocSync(model.getDocSync());
		soapModel.setDossierId(model.getDossierId());

		return soapModel;
	}

	public static OpenCPSDeliverableSoap[] toSoapModels(
		OpenCPSDeliverable[] models) {
		OpenCPSDeliverableSoap[] soapModels = new OpenCPSDeliverableSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OpenCPSDeliverableSoap[][] toSoapModels(
		OpenCPSDeliverable[][] models) {
		OpenCPSDeliverableSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OpenCPSDeliverableSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OpenCPSDeliverableSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OpenCPSDeliverableSoap[] toSoapModels(
		List<OpenCPSDeliverable> models) {
		List<OpenCPSDeliverableSoap> soapModels = new ArrayList<OpenCPSDeliverableSoap>(models.size());

		for (OpenCPSDeliverable model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OpenCPSDeliverableSoap[soapModels.size()]);
	}

	public OpenCPSDeliverableSoap() {
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

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public long getFormScriptFileId() {
		return _formScriptFileId;
	}

	public void setFormScriptFileId(long formScriptFileId) {
		_formScriptFileId = formScriptFileId;
	}

	public String getFormReport() {
		return _formReport;
	}

	public void setFormReport(String formReport) {
		_formReport = formReport;
	}

	public long getFormReportFileId() {
		return _formReportFileId;
	}

	public void setFormReportFileId(long formReportFileId) {
		_formReportFileId = formReportFileId;
	}

	public Date getIssueDate() {
		return _issueDate;
	}

	public void setIssueDate(Date issueDate) {
		_issueDate = issueDate;
	}

	public Date getExpireDate() {
		return _expireDate;
	}

	public void setExpireDate(Date expireDate) {
		_expireDate = expireDate;
	}

	public Date getRevalidate() {
		return _revalidate;
	}

	public void setRevalidate(Date revalidate) {
		_revalidate = revalidate;
	}

	public int getDeliverableState() {
		return _deliverableState;
	}

	public void setDeliverableState(int deliverableState) {
		_deliverableState = deliverableState;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public int getDocSync() {
		return _docSync;
	}

	public void setDocSync(int docSync) {
		_docSync = docSync;
	}

	public long getDossierId() {
		return _dossierId;
	}

	public void setDossierId(long dossierId) {
		_dossierId = dossierId;
	}

	private String _uuid;
	private long _deliverableId;
	private long _groupId;
	private long _companyId;
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
	private long _formScriptFileId;
	private String _formReport;
	private long _formReportFileId;
	private Date _issueDate;
	private Date _expireDate;
	private Date _revalidate;
	private int _deliverableState;
	private long _fileEntryId;
	private int _docSync;
	private long _dossierId;
}