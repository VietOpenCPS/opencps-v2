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
 * This class is a wrapper for {@link Location}.
 * </p>
 *
 * @author Binhth
 * @see Location
 * @generated
 */
@ProviderType
public class LocationWrapper implements Location, ModelWrapper<Location> {
	public LocationWrapper(Location location) {
		_location = location;
	}

	@Override
	public Class<?> getModelClass() {
		return Location.class;
	}

	@Override
	public String getModelClassName() {
		return Location.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("locationId", getLocationId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("location", getLocation());
		attributes.put("geolocation", getGeolocation());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long locationId = (Long)attributes.get("locationId");

		if (locationId != null) {
			setLocationId(locationId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String location = (String)attributes.get("location");

		if (location != null) {
			setLocation(location);
		}

		String geolocation = (String)attributes.get("geolocation");

		if (geolocation != null) {
			setGeolocation(geolocation);
		}
	}

	@Override
	public Location toEscapedModel() {
		return new LocationWrapper(_location.toEscapedModel());
	}

	@Override
	public Location toUnescapedModel() {
		return new LocationWrapper(_location.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _location.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _location.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _location.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _location.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Location> toCacheModel() {
		return _location.toCacheModel();
	}

	@Override
	public int compareTo(Location location) {
		return _location.compareTo(location);
	}

	@Override
	public int hashCode() {
		return _location.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _location.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LocationWrapper((Location)_location.clone());
	}

	/**
	* Returns the geolocation of this location.
	*
	* @return the geolocation of this location
	*/
	@Override
	public java.lang.String getGeolocation() {
		return _location.getGeolocation();
	}

	/**
	* Returns the location of this location.
	*
	* @return the location of this location
	*/
	@Override
	public java.lang.String getLocation() {
		return _location.getLocation();
	}

	/**
	* Returns the user name of this location.
	*
	* @return the user name of this location
	*/
	@Override
	public java.lang.String getUserName() {
		return _location.getUserName();
	}

	/**
	* Returns the user uuid of this location.
	*
	* @return the user uuid of this location
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _location.getUserUuid();
	}

	/**
	* Returns the uuid of this location.
	*
	* @return the uuid of this location
	*/
	@Override
	public java.lang.String getUuid() {
		return _location.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _location.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _location.toXmlString();
	}

	/**
	* Returns the create date of this location.
	*
	* @return the create date of this location
	*/
	@Override
	public Date getCreateDate() {
		return _location.getCreateDate();
	}

	/**
	* Returns the modified date of this location.
	*
	* @return the modified date of this location
	*/
	@Override
	public Date getModifiedDate() {
		return _location.getModifiedDate();
	}

	/**
	* Returns the company ID of this location.
	*
	* @return the company ID of this location
	*/
	@Override
	public long getCompanyId() {
		return _location.getCompanyId();
	}

	/**
	* Returns the group ID of this location.
	*
	* @return the group ID of this location
	*/
	@Override
	public long getGroupId() {
		return _location.getGroupId();
	}

	/**
	* Returns the location ID of this location.
	*
	* @return the location ID of this location
	*/
	@Override
	public long getLocationId() {
		return _location.getLocationId();
	}

	/**
	* Returns the primary key of this location.
	*
	* @return the primary key of this location
	*/
	@Override
	public long getPrimaryKey() {
		return _location.getPrimaryKey();
	}

	/**
	* Returns the user ID of this location.
	*
	* @return the user ID of this location
	*/
	@Override
	public long getUserId() {
		return _location.getUserId();
	}

	@Override
	public void persist() {
		_location.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_location.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this location.
	*
	* @param companyId the company ID of this location
	*/
	@Override
	public void setCompanyId(long companyId) {
		_location.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this location.
	*
	* @param createDate the create date of this location
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_location.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_location.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_location.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_location.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the geolocation of this location.
	*
	* @param geolocation the geolocation of this location
	*/
	@Override
	public void setGeolocation(java.lang.String geolocation) {
		_location.setGeolocation(geolocation);
	}

	/**
	* Sets the group ID of this location.
	*
	* @param groupId the group ID of this location
	*/
	@Override
	public void setGroupId(long groupId) {
		_location.setGroupId(groupId);
	}

	/**
	* Sets the location of this location.
	*
	* @param location the location of this location
	*/
	@Override
	public void setLocation(java.lang.String location) {
		_location.setLocation(location);
	}

	/**
	* Sets the location ID of this location.
	*
	* @param locationId the location ID of this location
	*/
	@Override
	public void setLocationId(long locationId) {
		_location.setLocationId(locationId);
	}

	/**
	* Sets the modified date of this location.
	*
	* @param modifiedDate the modified date of this location
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_location.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_location.setNew(n);
	}

	/**
	* Sets the primary key of this location.
	*
	* @param primaryKey the primary key of this location
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_location.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_location.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this location.
	*
	* @param userId the user ID of this location
	*/
	@Override
	public void setUserId(long userId) {
		_location.setUserId(userId);
	}

	/**
	* Sets the user name of this location.
	*
	* @param userName the user name of this location
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_location.setUserName(userName);
	}

	/**
	* Sets the user uuid of this location.
	*
	* @param userUuid the user uuid of this location
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_location.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this location.
	*
	* @param uuid the uuid of this location
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_location.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LocationWrapper)) {
			return false;
		}

		LocationWrapper locationWrapper = (LocationWrapper)obj;

		if (Objects.equals(_location, locationWrapper._location)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _location.getStagedModelType();
	}

	@Override
	public Location getWrappedModel() {
		return _location;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _location.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _location.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_location.resetOriginalValues();
	}

	private final Location _location;
}