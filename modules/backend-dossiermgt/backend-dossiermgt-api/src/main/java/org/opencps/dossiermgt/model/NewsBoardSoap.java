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
public class NewsBoardSoap implements Serializable {
	public static NewsBoardSoap toSoapModel(NewsBoard model) {
		NewsBoardSoap soapModel = new NewsBoardSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setNewBoardId(model.getNewBoardId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setNewsTitle(model.getNewsTitle());
		soapModel.setNewsContent(model.getNewsContent());
		soapModel.setNewsStatus(model.getNewsStatus());

		return soapModel;
	}

	public static NewsBoardSoap[] toSoapModels(NewsBoard[] models) {
		NewsBoardSoap[] soapModels = new NewsBoardSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NewsBoardSoap[][] toSoapModels(NewsBoard[][] models) {
		NewsBoardSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NewsBoardSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NewsBoardSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NewsBoardSoap[] toSoapModels(List<NewsBoard> models) {
		List<NewsBoardSoap> soapModels = new ArrayList<NewsBoardSoap>(models.size());

		for (NewsBoard model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NewsBoardSoap[soapModels.size()]);
	}

	public NewsBoardSoap() {
	}

	public long getPrimaryKey() {
		return _newBoardId;
	}

	public void setPrimaryKey(long pk) {
		setNewBoardId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getNewBoardId() {
		return _newBoardId;
	}

	public void setNewBoardId(long newBoardId) {
		_newBoardId = newBoardId;
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

	public String getNewsTitle() {
		return _newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		_newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return _newsContent;
	}

	public void setNewsContent(String newsContent) {
		_newsContent = newsContent;
	}

	public int getNewsStatus() {
		return _newsStatus;
	}

	public void setNewsStatus(int newsStatus) {
		_newsStatus = newsStatus;
	}

	private String _uuid;
	private long _newBoardId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _newsTitle;
	private String _newsContent;
	private int _newsStatus;
}