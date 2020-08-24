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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link PostConnect}.
 * </p>
 *
 * @author huymq
 * @see PostConnect
 * @generated
 */
@ProviderType
public class PostConnectWrapper implements PostConnect,
	ModelWrapper<PostConnect> {
	public PostConnectWrapper(PostConnect postConnect) {
		_postConnect = postConnect;
	}

	@Override
	public Class<?> getModelClass() {
		return PostConnect.class;
	}

	@Override
	public String getModelClassName() {
		return PostConnect.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("postConnectId", getPostConnectId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierId", getDossierId());
		attributes.put("postService", getPostService());
		attributes.put("postType", getPostType());
		attributes.put("orderNumber", getOrderNumber());
		attributes.put("postStatus", getPostStatus());
		attributes.put("metadata", getMetadata());
		attributes.put("syncState", getSyncState());
		attributes.put("retry", getRetry());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long postConnectId = (Long)attributes.get("postConnectId");

		if (postConnectId != null) {
			setPostConnectId(postConnectId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long dossierId = (Long)attributes.get("dossierId");

		if (dossierId != null) {
			setDossierId(dossierId);
		}

		Integer postService = (Integer)attributes.get("postService");

		if (postService != null) {
			setPostService(postService);
		}

		Integer postType = (Integer)attributes.get("postType");

		if (postType != null) {
			setPostType(postType);
		}

		String orderNumber = (String)attributes.get("orderNumber");

		if (orderNumber != null) {
			setOrderNumber(orderNumber);
		}

		Integer postStatus = (Integer)attributes.get("postStatus");

		if (postStatus != null) {
			setPostStatus(postStatus);
		}

		String metadata = (String)attributes.get("metadata");

		if (metadata != null) {
			setMetadata(metadata);
		}

		Integer syncState = (Integer)attributes.get("syncState");

		if (syncState != null) {
			setSyncState(syncState);
		}

		Integer retry = (Integer)attributes.get("retry");

		if (retry != null) {
			setRetry(retry);
		}
	}

	@Override
	public Object clone() {
		return new PostConnectWrapper((PostConnect)_postConnect.clone());
	}

	@Override
	public int compareTo(PostConnect postConnect) {
		return _postConnect.compareTo(postConnect);
	}

	/**
	* Returns the company ID of this post connect.
	*
	* @return the company ID of this post connect
	*/
	@Override
	public long getCompanyId() {
		return _postConnect.getCompanyId();
	}

	/**
	* Returns the create date of this post connect.
	*
	* @return the create date of this post connect
	*/
	@Override
	public Date getCreateDate() {
		return _postConnect.getCreateDate();
	}

	/**
	* Returns the dossier ID of this post connect.
	*
	* @return the dossier ID of this post connect
	*/
	@Override
	public long getDossierId() {
		return _postConnect.getDossierId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _postConnect.getExpandoBridge();
	}

	/**
	* Returns the group ID of this post connect.
	*
	* @return the group ID of this post connect
	*/
	@Override
	public long getGroupId() {
		return _postConnect.getGroupId();
	}

	/**
	* Returns the metadata of this post connect.
	*
	* @return the metadata of this post connect
	*/
	@Override
	public String getMetadata() {
		return _postConnect.getMetadata();
	}

	/**
	* Returns the modified date of this post connect.
	*
	* @return the modified date of this post connect
	*/
	@Override
	public Date getModifiedDate() {
		return _postConnect.getModifiedDate();
	}

	/**
	* Returns the order number of this post connect.
	*
	* @return the order number of this post connect
	*/
	@Override
	public String getOrderNumber() {
		return _postConnect.getOrderNumber();
	}

	/**
	* Returns the post connect ID of this post connect.
	*
	* @return the post connect ID of this post connect
	*/
	@Override
	public long getPostConnectId() {
		return _postConnect.getPostConnectId();
	}

	/**
	* Returns the post service of this post connect.
	*
	* @return the post service of this post connect
	*/
	@Override
	public int getPostService() {
		return _postConnect.getPostService();
	}

	/**
	* Returns the post status of this post connect.
	*
	* @return the post status of this post connect
	*/
	@Override
	public int getPostStatus() {
		return _postConnect.getPostStatus();
	}

	/**
	* Returns the post type of this post connect.
	*
	* @return the post type of this post connect
	*/
	@Override
	public int getPostType() {
		return _postConnect.getPostType();
	}

	/**
	* Returns the primary key of this post connect.
	*
	* @return the primary key of this post connect
	*/
	@Override
	public long getPrimaryKey() {
		return _postConnect.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _postConnect.getPrimaryKeyObj();
	}

	/**
	* Returns the retry of this post connect.
	*
	* @return the retry of this post connect
	*/
	@Override
	public int getRetry() {
		return _postConnect.getRetry();
	}

	/**
	* Returns the sync state of this post connect.
	*
	* @return the sync state of this post connect
	*/
	@Override
	public int getSyncState() {
		return _postConnect.getSyncState();
	}

	/**
	* Returns the user ID of this post connect.
	*
	* @return the user ID of this post connect
	*/
	@Override
	public long getUserId() {
		return _postConnect.getUserId();
	}

	/**
	* Returns the user name of this post connect.
	*
	* @return the user name of this post connect
	*/
	@Override
	public String getUserName() {
		return _postConnect.getUserName();
	}

	/**
	* Returns the user uuid of this post connect.
	*
	* @return the user uuid of this post connect
	*/
	@Override
	public String getUserUuid() {
		return _postConnect.getUserUuid();
	}

	/**
	* Returns the uuid of this post connect.
	*
	* @return the uuid of this post connect
	*/
	@Override
	public String getUuid() {
		return _postConnect.getUuid();
	}

	@Override
	public int hashCode() {
		return _postConnect.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _postConnect.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _postConnect.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _postConnect.isNew();
	}

	@Override
	public void persist() {
		_postConnect.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_postConnect.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this post connect.
	*
	* @param companyId the company ID of this post connect
	*/
	@Override
	public void setCompanyId(long companyId) {
		_postConnect.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this post connect.
	*
	* @param createDate the create date of this post connect
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_postConnect.setCreateDate(createDate);
	}

	/**
	* Sets the dossier ID of this post connect.
	*
	* @param dossierId the dossier ID of this post connect
	*/
	@Override
	public void setDossierId(long dossierId) {
		_postConnect.setDossierId(dossierId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_postConnect.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_postConnect.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_postConnect.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this post connect.
	*
	* @param groupId the group ID of this post connect
	*/
	@Override
	public void setGroupId(long groupId) {
		_postConnect.setGroupId(groupId);
	}

	/**
	* Sets the metadata of this post connect.
	*
	* @param metadata the metadata of this post connect
	*/
	@Override
	public void setMetadata(String metadata) {
		_postConnect.setMetadata(metadata);
	}

	/**
	* Sets the modified date of this post connect.
	*
	* @param modifiedDate the modified date of this post connect
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_postConnect.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_postConnect.setNew(n);
	}

	/**
	* Sets the order number of this post connect.
	*
	* @param orderNumber the order number of this post connect
	*/
	@Override
	public void setOrderNumber(String orderNumber) {
		_postConnect.setOrderNumber(orderNumber);
	}

	/**
	* Sets the post connect ID of this post connect.
	*
	* @param postConnectId the post connect ID of this post connect
	*/
	@Override
	public void setPostConnectId(long postConnectId) {
		_postConnect.setPostConnectId(postConnectId);
	}

	/**
	* Sets the post service of this post connect.
	*
	* @param postService the post service of this post connect
	*/
	@Override
	public void setPostService(int postService) {
		_postConnect.setPostService(postService);
	}

	/**
	* Sets the post status of this post connect.
	*
	* @param postStatus the post status of this post connect
	*/
	@Override
	public void setPostStatus(int postStatus) {
		_postConnect.setPostStatus(postStatus);
	}

	/**
	* Sets the post type of this post connect.
	*
	* @param postType the post type of this post connect
	*/
	@Override
	public void setPostType(int postType) {
		_postConnect.setPostType(postType);
	}

	/**
	* Sets the primary key of this post connect.
	*
	* @param primaryKey the primary key of this post connect
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_postConnect.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_postConnect.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the retry of this post connect.
	*
	* @param retry the retry of this post connect
	*/
	@Override
	public void setRetry(int retry) {
		_postConnect.setRetry(retry);
	}

	/**
	* Sets the sync state of this post connect.
	*
	* @param syncState the sync state of this post connect
	*/
	@Override
	public void setSyncState(int syncState) {
		_postConnect.setSyncState(syncState);
	}

	/**
	* Sets the user ID of this post connect.
	*
	* @param userId the user ID of this post connect
	*/
	@Override
	public void setUserId(long userId) {
		_postConnect.setUserId(userId);
	}

	/**
	* Sets the user name of this post connect.
	*
	* @param userName the user name of this post connect
	*/
	@Override
	public void setUserName(String userName) {
		_postConnect.setUserName(userName);
	}

	/**
	* Sets the user uuid of this post connect.
	*
	* @param userUuid the user uuid of this post connect
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_postConnect.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this post connect.
	*
	* @param uuid the uuid of this post connect
	*/
	@Override
	public void setUuid(String uuid) {
		_postConnect.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PostConnect> toCacheModel() {
		return _postConnect.toCacheModel();
	}

	@Override
	public PostConnect toEscapedModel() {
		return new PostConnectWrapper(_postConnect.toEscapedModel());
	}

	@Override
	public String toString() {
		return _postConnect.toString();
	}

	@Override
	public PostConnect toUnescapedModel() {
		return new PostConnectWrapper(_postConnect.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _postConnect.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PostConnectWrapper)) {
			return false;
		}

		PostConnectWrapper postConnectWrapper = (PostConnectWrapper)obj;

		if (Objects.equals(_postConnect, postConnectWrapper._postConnect)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _postConnect.getStagedModelType();
	}

	@Override
	public PostConnect getWrappedModel() {
		return _postConnect;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _postConnect.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _postConnect.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_postConnect.resetOriginalValues();
	}

	private final PostConnect _postConnect;
}