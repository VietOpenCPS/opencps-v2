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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link UserTrackPath}.
 * </p>
 *
 * @author khoavu
 * @see UserTrackPath
 * @generated
 */
@ProviderType
public class UserTrackPathWrapper implements UserTrackPath,
	ModelWrapper<UserTrackPath> {
	public UserTrackPathWrapper(UserTrackPath userTrackPath) {
		_userTrackPath = userTrackPath;
	}

	@Override
	public Class<?> getModelClass() {
		return UserTrackPath.class;
	}

	@Override
	public String getModelClassName() {
		return UserTrackPath.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("userTrackPathId", getUserTrackPathId());
		attributes.put("companyId", getCompanyId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("userLoginId", getUserLoginId());
		attributes.put("path", getPath());
		attributes.put("pathDate", getPathDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long userTrackPathId = (Long)attributes.get("userTrackPathId");

		if (userTrackPathId != null) {
			setUserTrackPathId(userTrackPathId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long userLoginId = (Long)attributes.get("userLoginId");

		if (userLoginId != null) {
			setUserLoginId(userLoginId);
		}

		String path = (String)attributes.get("path");

		if (path != null) {
			setPath(path);
		}

		Date pathDate = (Date)attributes.get("pathDate");

		if (pathDate != null) {
			setPathDate(pathDate);
		}
	}

	@Override
	public Object clone() {
		return new UserTrackPathWrapper((UserTrackPath)_userTrackPath.clone());
	}

	@Override
	public int compareTo(UserTrackPath userTrackPath) {
		return _userTrackPath.compareTo(userTrackPath);
	}

	/**
	* Returns the company ID of this user track path.
	*
	* @return the company ID of this user track path
	*/
	@Override
	public long getCompanyId() {
		return _userTrackPath.getCompanyId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _userTrackPath.getExpandoBridge();
	}

	/**
	* Returns the modified date of this user track path.
	*
	* @return the modified date of this user track path
	*/
	@Override
	public Date getModifiedDate() {
		return _userTrackPath.getModifiedDate();
	}

	/**
	* Returns the path of this user track path.
	*
	* @return the path of this user track path
	*/
	@Override
	public String getPath() {
		return _userTrackPath.getPath();
	}

	/**
	* Returns the path date of this user track path.
	*
	* @return the path date of this user track path
	*/
	@Override
	public Date getPathDate() {
		return _userTrackPath.getPathDate();
	}

	/**
	* Returns the primary key of this user track path.
	*
	* @return the primary key of this user track path
	*/
	@Override
	public long getPrimaryKey() {
		return _userTrackPath.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userTrackPath.getPrimaryKeyObj();
	}

	/**
	* Returns the user login ID of this user track path.
	*
	* @return the user login ID of this user track path
	*/
	@Override
	public long getUserLoginId() {
		return _userTrackPath.getUserLoginId();
	}

	/**
	* Returns the user track path ID of this user track path.
	*
	* @return the user track path ID of this user track path
	*/
	@Override
	public long getUserTrackPathId() {
		return _userTrackPath.getUserTrackPathId();
	}

	/**
	* Returns the uuid of this user track path.
	*
	* @return the uuid of this user track path
	*/
	@Override
	public String getUuid() {
		return _userTrackPath.getUuid();
	}

	@Override
	public int hashCode() {
		return _userTrackPath.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _userTrackPath.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _userTrackPath.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _userTrackPath.isNew();
	}

	@Override
	public void persist() {
		_userTrackPath.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_userTrackPath.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this user track path.
	*
	* @param companyId the company ID of this user track path
	*/
	@Override
	public void setCompanyId(long companyId) {
		_userTrackPath.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_userTrackPath.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_userTrackPath.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_userTrackPath.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this user track path.
	*
	* @param modifiedDate the modified date of this user track path
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_userTrackPath.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_userTrackPath.setNew(n);
	}

	/**
	* Sets the path of this user track path.
	*
	* @param path the path of this user track path
	*/
	@Override
	public void setPath(String path) {
		_userTrackPath.setPath(path);
	}

	/**
	* Sets the path date of this user track path.
	*
	* @param pathDate the path date of this user track path
	*/
	@Override
	public void setPathDate(Date pathDate) {
		_userTrackPath.setPathDate(pathDate);
	}

	/**
	* Sets the primary key of this user track path.
	*
	* @param primaryKey the primary key of this user track path
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_userTrackPath.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_userTrackPath.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user login ID of this user track path.
	*
	* @param userLoginId the user login ID of this user track path
	*/
	@Override
	public void setUserLoginId(long userLoginId) {
		_userTrackPath.setUserLoginId(userLoginId);
	}

	/**
	* Sets the user track path ID of this user track path.
	*
	* @param userTrackPathId the user track path ID of this user track path
	*/
	@Override
	public void setUserTrackPathId(long userTrackPathId) {
		_userTrackPath.setUserTrackPathId(userTrackPathId);
	}

	/**
	* Sets the uuid of this user track path.
	*
	* @param uuid the uuid of this user track path
	*/
	@Override
	public void setUuid(String uuid) {
		_userTrackPath.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<UserTrackPath> toCacheModel() {
		return _userTrackPath.toCacheModel();
	}

	@Override
	public UserTrackPath toEscapedModel() {
		return new UserTrackPathWrapper(_userTrackPath.toEscapedModel());
	}

	@Override
	public String toString() {
		return _userTrackPath.toString();
	}

	@Override
	public UserTrackPath toUnescapedModel() {
		return new UserTrackPathWrapper(_userTrackPath.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _userTrackPath.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserTrackPathWrapper)) {
			return false;
		}

		UserTrackPathWrapper userTrackPathWrapper = (UserTrackPathWrapper)obj;

		if (Objects.equals(_userTrackPath, userTrackPathWrapper._userTrackPath)) {
			return true;
		}

		return false;
	}

	@Override
	public UserTrackPath getWrappedModel() {
		return _userTrackPath;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _userTrackPath.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _userTrackPath.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_userTrackPath.resetOriginalValues();
	}

	private final UserTrackPath _userTrackPath;
}