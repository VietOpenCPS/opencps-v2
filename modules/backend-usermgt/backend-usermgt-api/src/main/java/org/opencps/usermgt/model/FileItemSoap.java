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
public class FileItemSoap implements Serializable {
	public static FileItemSoap toSoapModel(FileItem model) {
		FileItemSoap soapModel = new FileItemSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setFileItemId(model.getFileItemId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setFileTemplateNo(model.getFileTemplateNo());
		soapModel.setName(model.getName());
		soapModel.setStatus(model.getStatus());
		soapModel.setSize(model.getSize());
		soapModel.setFileType(model.getFileType());
		soapModel.setLog(model.getLog());

		return soapModel;
	}

	public static FileItemSoap[] toSoapModels(FileItem[] models) {
		FileItemSoap[] soapModels = new FileItemSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FileItemSoap[][] toSoapModels(FileItem[][] models) {
		FileItemSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FileItemSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FileItemSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FileItemSoap[] toSoapModels(List<FileItem> models) {
		List<FileItemSoap> soapModels = new ArrayList<FileItemSoap>(models.size());

		for (FileItem model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FileItemSoap[soapModels.size()]);
	}

	public FileItemSoap() {
	}

	public long getPrimaryKey() {
		return _fileItemId;
	}

	public void setPrimaryKey(long pk) {
		setFileItemId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getFileItemId() {
		return _fileItemId;
	}

	public void setFileItemId(long fileItemId) {
		_fileItemId = fileItemId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public int getSize() {
		return _size;
	}

	public void setSize(int size) {
		_size = size;
	}

	public String getFileType() {
		return _fileType;
	}

	public void setFileType(String fileType) {
		_fileType = fileType;
	}

	public String getLog() {
		return _log;
	}

	public void setLog(String log) {
		_log = log;
	}

	private String _uuid;
	private long _fileItemId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private String _fileTemplateNo;
	private String _name;
	private int _status;
	private int _size;
	private String _fileType;
	private String _log;
}