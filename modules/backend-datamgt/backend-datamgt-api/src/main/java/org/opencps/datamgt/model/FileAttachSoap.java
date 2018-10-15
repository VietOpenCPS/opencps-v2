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
 * @author khoavu
 * @generated
 */
@ProviderType
public class FileAttachSoap implements Serializable {
	public static FileAttachSoap toSoapModel(FileAttach model) {
		FileAttachSoap soapModel = new FileAttachSoap();

		soapModel.setFileAttachId(model.getFileAttachId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setFullName(model.getFullName());
		soapModel.setEmail(model.getEmail());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setSource(model.getSource());
		soapModel.setSourceUrl(model.getSourceUrl());
		soapModel.setDocFileId(model.getDocFileId());
		soapModel.setFileName(model.getFileName());

		return soapModel;
	}

	public static FileAttachSoap[] toSoapModels(FileAttach[] models) {
		FileAttachSoap[] soapModels = new FileAttachSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FileAttachSoap[][] toSoapModels(FileAttach[][] models) {
		FileAttachSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FileAttachSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FileAttachSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FileAttachSoap[] toSoapModels(List<FileAttach> models) {
		List<FileAttachSoap> soapModels = new ArrayList<FileAttachSoap>(models.size());

		for (FileAttach model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FileAttachSoap[soapModels.size()]);
	}

	public FileAttachSoap() {
	}

	public long getPrimaryKey() {
		return _fileAttachId;
	}

	public void setPrimaryKey(long pk) {
		setFileAttachId(pk);
	}

	public long getFileAttachId() {
		return _fileAttachId;
	}

	public void setFileAttachId(long fileAttachId) {
		_fileAttachId = fileAttachId;
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

	public String getFullName() {
		return _fullName;
	}

	public void setFullName(String fullName) {
		_fullName = fullName;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getSourceUrl() {
		return _sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		_sourceUrl = sourceUrl;
	}

	public long getDocFileId() {
		return _docFileId;
	}

	public void setDocFileId(long docFileId) {
		_docFileId = docFileId;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	private long _fileAttachId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _className;
	private String _classPK;
	private String _fullName;
	private String _email;
	private long _fileEntryId;
	private String _source;
	private String _sourceUrl;
	private long _docFileId;
	private String _fileName;
}