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
 * This class is a wrapper for {@link NewsBoard}.
 * </p>
 *
 * @author huymq
 * @see NewsBoard
 * @generated
 */
@ProviderType
public class NewsBoardWrapper implements NewsBoard, ModelWrapper<NewsBoard> {
	public NewsBoardWrapper(NewsBoard newsBoard) {
		_newsBoard = newsBoard;
	}

	@Override
	public Class<?> getModelClass() {
		return NewsBoard.class;
	}

	@Override
	public String getModelClassName() {
		return NewsBoard.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("newBoardId", getNewBoardId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("newsTitle", getNewsTitle());
		attributes.put("newsContent", getNewsContent());
		attributes.put("newsStatus", getNewsStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long newBoardId = (Long)attributes.get("newBoardId");

		if (newBoardId != null) {
			setNewBoardId(newBoardId);
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

		String newsTitle = (String)attributes.get("newsTitle");

		if (newsTitle != null) {
			setNewsTitle(newsTitle);
		}

		String newsContent = (String)attributes.get("newsContent");

		if (newsContent != null) {
			setNewsContent(newsContent);
		}

		Integer newsStatus = (Integer)attributes.get("newsStatus");

		if (newsStatus != null) {
			setNewsStatus(newsStatus);
		}
	}

	@Override
	public Object clone() {
		return new NewsBoardWrapper((NewsBoard)_newsBoard.clone());
	}

	@Override
	public int compareTo(NewsBoard newsBoard) {
		return _newsBoard.compareTo(newsBoard);
	}

	/**
	* Returns the company ID of this news board.
	*
	* @return the company ID of this news board
	*/
	@Override
	public long getCompanyId() {
		return _newsBoard.getCompanyId();
	}

	/**
	* Returns the create date of this news board.
	*
	* @return the create date of this news board
	*/
	@Override
	public Date getCreateDate() {
		return _newsBoard.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _newsBoard.getExpandoBridge();
	}

	/**
	* Returns the group ID of this news board.
	*
	* @return the group ID of this news board
	*/
	@Override
	public long getGroupId() {
		return _newsBoard.getGroupId();
	}

	/**
	* Returns the modified date of this news board.
	*
	* @return the modified date of this news board
	*/
	@Override
	public Date getModifiedDate() {
		return _newsBoard.getModifiedDate();
	}

	/**
	* Returns the new board ID of this news board.
	*
	* @return the new board ID of this news board
	*/
	@Override
	public long getNewBoardId() {
		return _newsBoard.getNewBoardId();
	}

	/**
	* Returns the news content of this news board.
	*
	* @return the news content of this news board
	*/
	@Override
	public String getNewsContent() {
		return _newsBoard.getNewsContent();
	}

	/**
	* Returns the news status of this news board.
	*
	* @return the news status of this news board
	*/
	@Override
	public int getNewsStatus() {
		return _newsBoard.getNewsStatus();
	}

	/**
	* Returns the news title of this news board.
	*
	* @return the news title of this news board
	*/
	@Override
	public String getNewsTitle() {
		return _newsBoard.getNewsTitle();
	}

	/**
	* Returns the primary key of this news board.
	*
	* @return the primary key of this news board
	*/
	@Override
	public long getPrimaryKey() {
		return _newsBoard.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _newsBoard.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this news board.
	*
	* @return the user ID of this news board
	*/
	@Override
	public long getUserId() {
		return _newsBoard.getUserId();
	}

	/**
	* Returns the user name of this news board.
	*
	* @return the user name of this news board
	*/
	@Override
	public String getUserName() {
		return _newsBoard.getUserName();
	}

	/**
	* Returns the user uuid of this news board.
	*
	* @return the user uuid of this news board
	*/
	@Override
	public String getUserUuid() {
		return _newsBoard.getUserUuid();
	}

	/**
	* Returns the uuid of this news board.
	*
	* @return the uuid of this news board
	*/
	@Override
	public String getUuid() {
		return _newsBoard.getUuid();
	}

	@Override
	public int hashCode() {
		return _newsBoard.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _newsBoard.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _newsBoard.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _newsBoard.isNew();
	}

	@Override
	public void persist() {
		_newsBoard.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_newsBoard.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this news board.
	*
	* @param companyId the company ID of this news board
	*/
	@Override
	public void setCompanyId(long companyId) {
		_newsBoard.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this news board.
	*
	* @param createDate the create date of this news board
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_newsBoard.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_newsBoard.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_newsBoard.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_newsBoard.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this news board.
	*
	* @param groupId the group ID of this news board
	*/
	@Override
	public void setGroupId(long groupId) {
		_newsBoard.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this news board.
	*
	* @param modifiedDate the modified date of this news board
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_newsBoard.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_newsBoard.setNew(n);
	}

	/**
	* Sets the new board ID of this news board.
	*
	* @param newBoardId the new board ID of this news board
	*/
	@Override
	public void setNewBoardId(long newBoardId) {
		_newsBoard.setNewBoardId(newBoardId);
	}

	/**
	* Sets the news content of this news board.
	*
	* @param newsContent the news content of this news board
	*/
	@Override
	public void setNewsContent(String newsContent) {
		_newsBoard.setNewsContent(newsContent);
	}

	/**
	* Sets the news status of this news board.
	*
	* @param newsStatus the news status of this news board
	*/
	@Override
	public void setNewsStatus(int newsStatus) {
		_newsBoard.setNewsStatus(newsStatus);
	}

	/**
	* Sets the news title of this news board.
	*
	* @param newsTitle the news title of this news board
	*/
	@Override
	public void setNewsTitle(String newsTitle) {
		_newsBoard.setNewsTitle(newsTitle);
	}

	/**
	* Sets the primary key of this news board.
	*
	* @param primaryKey the primary key of this news board
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_newsBoard.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_newsBoard.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this news board.
	*
	* @param userId the user ID of this news board
	*/
	@Override
	public void setUserId(long userId) {
		_newsBoard.setUserId(userId);
	}

	/**
	* Sets the user name of this news board.
	*
	* @param userName the user name of this news board
	*/
	@Override
	public void setUserName(String userName) {
		_newsBoard.setUserName(userName);
	}

	/**
	* Sets the user uuid of this news board.
	*
	* @param userUuid the user uuid of this news board
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_newsBoard.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this news board.
	*
	* @param uuid the uuid of this news board
	*/
	@Override
	public void setUuid(String uuid) {
		_newsBoard.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NewsBoard> toCacheModel() {
		return _newsBoard.toCacheModel();
	}

	@Override
	public NewsBoard toEscapedModel() {
		return new NewsBoardWrapper(_newsBoard.toEscapedModel());
	}

	@Override
	public String toString() {
		return _newsBoard.toString();
	}

	@Override
	public NewsBoard toUnescapedModel() {
		return new NewsBoardWrapper(_newsBoard.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _newsBoard.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NewsBoardWrapper)) {
			return false;
		}

		NewsBoardWrapper newsBoardWrapper = (NewsBoardWrapper)obj;

		if (Objects.equals(_newsBoard, newsBoardWrapper._newsBoard)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _newsBoard.getStagedModelType();
	}

	@Override
	public NewsBoard getWrappedModel() {
		return _newsBoard;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _newsBoard.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _newsBoard.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_newsBoard.resetOriginalValues();
	}

	private final NewsBoard _newsBoard;
}