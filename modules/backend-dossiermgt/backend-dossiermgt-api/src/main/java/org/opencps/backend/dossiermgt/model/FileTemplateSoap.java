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

package org.opencps.backend.dossiermgt.model;

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
public class FileTemplateSoap implements Serializable {
	public static FileTemplateSoap toSoapModel(FileTemplate model) {
		FileTemplateSoap soapModel = new FileTemplateSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setFileTemplateId(model.getFileTemplateId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFileName(model.getFileName());
		soapModel.setFileNo(model.getFileNo());
		soapModel.setFileEntryId(model.getFileEntryId());

		return soapModel;
	}

	public static FileTemplateSoap[] toSoapModels(FileTemplate[] models) {
		FileTemplateSoap[] soapModels = new FileTemplateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FileTemplateSoap[][] toSoapModels(FileTemplate[][] models) {
		FileTemplateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FileTemplateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FileTemplateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FileTemplateSoap[] toSoapModels(List<FileTemplate> models) {
		List<FileTemplateSoap> soapModels = new ArrayList<FileTemplateSoap>(models.size());

		for (FileTemplate model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FileTemplateSoap[soapModels.size()]);
	}

	public FileTemplateSoap() {
	}

	public long getPrimaryKey() {
		return _fileTemplateId;
	}

	public void setPrimaryKey(long pk) {
		setFileTemplateId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getFileTemplateId() {
		return _fileTemplateId;
	}

	public void setFileTemplateId(long fileTemplateId) {
		_fileTemplateId = fileTemplateId;
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

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public String getFileNo() {
		return _fileNo;
	}

	public void setFileNo(String fileNo) {
		_fileNo = fileNo;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	private String _uuid;
	private long _fileTemplateId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _fileName;
	private String _fileNo;
	private long _fileEntryId;
}