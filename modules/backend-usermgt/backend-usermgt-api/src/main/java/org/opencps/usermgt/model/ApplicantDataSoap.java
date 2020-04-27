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
public class ApplicantDataSoap implements Serializable {
	public static ApplicantDataSoap toSoapModel(ApplicantData model) {
		ApplicantDataSoap soapModel = new ApplicantDataSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setApplicantDataId(model.getApplicantDataId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setFileTemplateNo(model.getFileTemplateNo());
		soapModel.setFileNo(model.getFileNo());
		soapModel.setFileName(model.getFileName());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setMetadata(model.getMetadata());
		soapModel.setStatus(model.getStatus());
		soapModel.setApplicantIdNo(model.getApplicantIdNo());
		soapModel.setApplicantDataType(model.getApplicantDataType());

		return soapModel;
	}

	public static ApplicantDataSoap[] toSoapModels(ApplicantData[] models) {
		ApplicantDataSoap[] soapModels = new ApplicantDataSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ApplicantDataSoap[][] toSoapModels(ApplicantData[][] models) {
		ApplicantDataSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ApplicantDataSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ApplicantDataSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ApplicantDataSoap[] toSoapModels(List<ApplicantData> models) {
		List<ApplicantDataSoap> soapModels = new ArrayList<ApplicantDataSoap>(models.size());

		for (ApplicantData model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ApplicantDataSoap[soapModels.size()]);
	}

	public ApplicantDataSoap() {
	}

	public long getPrimaryKey() {
		return _applicantDataId;
	}

	public void setPrimaryKey(long pk) {
		setApplicantDataId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getApplicantDataId() {
		return _applicantDataId;
	}

	public void setApplicantDataId(long applicantDataId) {
		_applicantDataId = applicantDataId;
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

	public String getFileTemplateNo() {
		return _fileTemplateNo;
	}

	public void setFileTemplateNo(String fileTemplateNo) {
		_fileTemplateNo = fileTemplateNo;
	}

	public String getFileNo() {
		return _fileNo;
	}

	public void setFileNo(String fileNo) {
		_fileNo = fileNo;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public String getMetadata() {
		return _metadata;
	}

	public void setMetadata(String metadata) {
		_metadata = metadata;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public String getApplicantIdNo() {
		return _applicantIdNo;
	}

	public void setApplicantIdNo(String applicantIdNo) {
		_applicantIdNo = applicantIdNo;
	}

	public int getApplicantDataType() {
		return _applicantDataType;
	}

	public void setApplicantDataType(int applicantDataType) {
		_applicantDataType = applicantDataType;
	}

	private String _uuid;
	private long _applicantDataId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private String _fileTemplateNo;
	private String _fileNo;
	private String _fileName;
	private long _fileEntryId;
	private String _metadata;
	private int _status;
	private String _applicantIdNo;
	private int _applicantDataType;
}