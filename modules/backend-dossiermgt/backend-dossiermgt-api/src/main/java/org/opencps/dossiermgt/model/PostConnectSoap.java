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
public class PostConnectSoap implements Serializable {
	public static PostConnectSoap toSoapModel(PostConnect model) {
		PostConnectSoap soapModel = new PostConnectSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPostConnectId(model.getPostConnectId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setPostService(model.getPostService());
		soapModel.setPostType(model.getPostType());
		soapModel.setOrderNumber(model.getOrderNumber());
		soapModel.setPostStatus(model.getPostStatus());
		soapModel.setMetadata(model.getMetadata());
		soapModel.setSyncState(model.getSyncState());
		soapModel.setRetry(model.getRetry());

		return soapModel;
	}

	public static PostConnectSoap[] toSoapModels(PostConnect[] models) {
		PostConnectSoap[] soapModels = new PostConnectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PostConnectSoap[][] toSoapModels(PostConnect[][] models) {
		PostConnectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PostConnectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PostConnectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PostConnectSoap[] toSoapModels(List<PostConnect> models) {
		List<PostConnectSoap> soapModels = new ArrayList<PostConnectSoap>(models.size());

		for (PostConnect model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PostConnectSoap[soapModels.size()]);
	}

	public PostConnectSoap() {
	}

	public long getPrimaryKey() {
		return _postConnectId;
	}

	public void setPrimaryKey(long pk) {
		setPostConnectId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPostConnectId() {
		return _postConnectId;
	}

	public void setPostConnectId(long postConnectId) {
		_postConnectId = postConnectId;
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

	public long getDossierId() {
		return _dossierId;
	}

	public void setDossierId(long dossierId) {
		_dossierId = dossierId;
	}

	public int getPostService() {
		return _postService;
	}

	public void setPostService(int postService) {
		_postService = postService;
	}

	public int getPostType() {
		return _postType;
	}

	public void setPostType(int postType) {
		_postType = postType;
	}

	public String getOrderNumber() {
		return _orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		_orderNumber = orderNumber;
	}

	public int getPostStatus() {
		return _postStatus;
	}

	public void setPostStatus(int postStatus) {
		_postStatus = postStatus;
	}

	public String getMetadata() {
		return _metadata;
	}

	public void setMetadata(String metadata) {
		_metadata = metadata;
	}

	public int getSyncState() {
		return _syncState;
	}

	public void setSyncState(int syncState) {
		_syncState = syncState;
	}

	public int getRetry() {
		return _retry;
	}

	public void setRetry(int retry) {
		_retry = retry;
	}

	private String _uuid;
	private long _postConnectId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dossierId;
	private int _postService;
	private int _postType;
	private String _orderNumber;
	private int _postStatus;
	private String _metadata;
	private int _syncState;
	private int _retry;
}