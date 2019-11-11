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
 * This class is a wrapper for {@link Preferences}.
 * </p>
 *
 * @author khoavu
 * @see Preferences
 * @generated
 */
@ProviderType
public class PreferencesWrapper implements Preferences,
	ModelWrapper<Preferences> {
	public PreferencesWrapper(Preferences preferences) {
		_preferences = preferences;
	}

	@Override
	public Class<?> getModelClass() {
		return Preferences.class;
	}

	@Override
	public String getModelClassName() {
		return Preferences.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("preferencesId", getPreferencesId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("preferences", getPreferences());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long preferencesId = (Long)attributes.get("preferencesId");

		if (preferencesId != null) {
			setPreferencesId(preferencesId);
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

		String preferences = (String)attributes.get("preferences");

		if (preferences != null) {
			setPreferences(preferences);
		}
	}

	@Override
	public Object clone() {
		return new PreferencesWrapper((Preferences)_preferences.clone());
	}

	@Override
	public int compareTo(Preferences preferences) {
		return _preferences.compareTo(preferences);
	}

	/**
	* Returns the company ID of this preferences.
	*
	* @return the company ID of this preferences
	*/
	@Override
	public long getCompanyId() {
		return _preferences.getCompanyId();
	}

	/**
	* Returns the create date of this preferences.
	*
	* @return the create date of this preferences
	*/
	@Override
	public Date getCreateDate() {
		return _preferences.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _preferences.getExpandoBridge();
	}

	/**
	* Returns the group ID of this preferences.
	*
	* @return the group ID of this preferences
	*/
	@Override
	public long getGroupId() {
		return _preferences.getGroupId();
	}

	/**
	* Returns the modified date of this preferences.
	*
	* @return the modified date of this preferences
	*/
	@Override
	public Date getModifiedDate() {
		return _preferences.getModifiedDate();
	}

	/**
	* Returns the preferences of this preferences.
	*
	* @return the preferences of this preferences
	*/
	@Override
	public String getPreferences() {
		return _preferences.getPreferences();
	}

	/**
	* Returns the preferences ID of this preferences.
	*
	* @return the preferences ID of this preferences
	*/
	@Override
	public long getPreferencesId() {
		return _preferences.getPreferencesId();
	}

	/**
	* Returns the primary key of this preferences.
	*
	* @return the primary key of this preferences
	*/
	@Override
	public long getPrimaryKey() {
		return _preferences.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _preferences.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this preferences.
	*
	* @return the user ID of this preferences
	*/
	@Override
	public long getUserId() {
		return _preferences.getUserId();
	}

	/**
	* Returns the user name of this preferences.
	*
	* @return the user name of this preferences
	*/
	@Override
	public String getUserName() {
		return _preferences.getUserName();
	}

	/**
	* Returns the user uuid of this preferences.
	*
	* @return the user uuid of this preferences
	*/
	@Override
	public String getUserUuid() {
		return _preferences.getUserUuid();
	}

	/**
	* Returns the uuid of this preferences.
	*
	* @return the uuid of this preferences
	*/
	@Override
	public String getUuid() {
		return _preferences.getUuid();
	}

	@Override
	public int hashCode() {
		return _preferences.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _preferences.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _preferences.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _preferences.isNew();
	}

	@Override
	public void persist() {
		_preferences.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_preferences.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this preferences.
	*
	* @param companyId the company ID of this preferences
	*/
	@Override
	public void setCompanyId(long companyId) {
		_preferences.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this preferences.
	*
	* @param createDate the create date of this preferences
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_preferences.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_preferences.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_preferences.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_preferences.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this preferences.
	*
	* @param groupId the group ID of this preferences
	*/
	@Override
	public void setGroupId(long groupId) {
		_preferences.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this preferences.
	*
	* @param modifiedDate the modified date of this preferences
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_preferences.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_preferences.setNew(n);
	}

	/**
	* Sets the preferences of this preferences.
	*
	* @param preferences the preferences of this preferences
	*/
	@Override
	public void setPreferences(String preferences) {
		_preferences.setPreferences(preferences);
	}

	/**
	* Sets the preferences ID of this preferences.
	*
	* @param preferencesId the preferences ID of this preferences
	*/
	@Override
	public void setPreferencesId(long preferencesId) {
		_preferences.setPreferencesId(preferencesId);
	}

	/**
	* Sets the primary key of this preferences.
	*
	* @param primaryKey the primary key of this preferences
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_preferences.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_preferences.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this preferences.
	*
	* @param userId the user ID of this preferences
	*/
	@Override
	public void setUserId(long userId) {
		_preferences.setUserId(userId);
	}

	/**
	* Sets the user name of this preferences.
	*
	* @param userName the user name of this preferences
	*/
	@Override
	public void setUserName(String userName) {
		_preferences.setUserName(userName);
	}

	/**
	* Sets the user uuid of this preferences.
	*
	* @param userUuid the user uuid of this preferences
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_preferences.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this preferences.
	*
	* @param uuid the uuid of this preferences
	*/
	@Override
	public void setUuid(String uuid) {
		_preferences.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Preferences> toCacheModel() {
		return _preferences.toCacheModel();
	}

	@Override
	public Preferences toEscapedModel() {
		return new PreferencesWrapper(_preferences.toEscapedModel());
	}

	@Override
	public String toString() {
		return _preferences.toString();
	}

	@Override
	public Preferences toUnescapedModel() {
		return new PreferencesWrapper(_preferences.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _preferences.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PreferencesWrapper)) {
			return false;
		}

		PreferencesWrapper preferencesWrapper = (PreferencesWrapper)obj;

		if (Objects.equals(_preferences, preferencesWrapper._preferences)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _preferences.getStagedModelType();
	}

	@Override
	public Preferences getWrappedModel() {
		return _preferences;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _preferences.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _preferences.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_preferences.resetOriginalValues();
	}

	private final Preferences _preferences;
}