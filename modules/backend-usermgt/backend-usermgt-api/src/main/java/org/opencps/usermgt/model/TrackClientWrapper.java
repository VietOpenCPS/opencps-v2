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
 * This class is a wrapper for {@link TrackClient}.
 * </p>
 *
 * @author khoavu
 * @see TrackClient
 * @generated
 */
@ProviderType
public class TrackClientWrapper implements TrackClient,
	ModelWrapper<TrackClient> {
	public TrackClientWrapper(TrackClient trackClient) {
		_trackClient = trackClient;
	}

	@Override
	public Class<?> getModelClass() {
		return TrackClient.class;
	}

	@Override
	public String getModelClassName() {
		return TrackClient.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("trackClientId", getTrackClientId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("sessionId", getSessionId());
		attributes.put("url", getUrl());
		attributes.put("year", getYear());
		attributes.put("month", getMonth());
		attributes.put("day", getDay());
		attributes.put("visitDate", getVisitDate());
		attributes.put("leaveDate", getLeaveDate());
		attributes.put("clientIP", getClientIP());
		attributes.put("macAddress", getMacAddress());
		attributes.put("region", getRegion());
		attributes.put("nation", getNation());
		attributes.put("latitude", getLatitude());
		attributes.put("longitude", getLongitude());
		attributes.put("timeOnPage", getTimeOnPage());
		attributes.put("desktop", isDesktop());
		attributes.put("mobile", isMobile());
		attributes.put("tablet", isTablet());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long trackClientId = (Long)attributes.get("trackClientId");

		if (trackClientId != null) {
			setTrackClientId(trackClientId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		Integer month = (Integer)attributes.get("month");

		if (month != null) {
			setMonth(month);
		}

		Integer day = (Integer)attributes.get("day");

		if (day != null) {
			setDay(day);
		}

		Date visitDate = (Date)attributes.get("visitDate");

		if (visitDate != null) {
			setVisitDate(visitDate);
		}

		Date leaveDate = (Date)attributes.get("leaveDate");

		if (leaveDate != null) {
			setLeaveDate(leaveDate);
		}

		String clientIP = (String)attributes.get("clientIP");

		if (clientIP != null) {
			setClientIP(clientIP);
		}

		String macAddress = (String)attributes.get("macAddress");

		if (macAddress != null) {
			setMacAddress(macAddress);
		}

		String region = (String)attributes.get("region");

		if (region != null) {
			setRegion(region);
		}

		String nation = (String)attributes.get("nation");

		if (nation != null) {
			setNation(nation);
		}

		String latitude = (String)attributes.get("latitude");

		if (latitude != null) {
			setLatitude(latitude);
		}

		String longitude = (String)attributes.get("longitude");

		if (longitude != null) {
			setLongitude(longitude);
		}

		Long timeOnPage = (Long)attributes.get("timeOnPage");

		if (timeOnPage != null) {
			setTimeOnPage(timeOnPage);
		}

		Boolean desktop = (Boolean)attributes.get("desktop");

		if (desktop != null) {
			setDesktop(desktop);
		}

		Boolean mobile = (Boolean)attributes.get("mobile");

		if (mobile != null) {
			setMobile(mobile);
		}

		Boolean tablet = (Boolean)attributes.get("tablet");

		if (tablet != null) {
			setTablet(tablet);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}
	}

	@Override
	public Object clone() {
		return new TrackClientWrapper((TrackClient)_trackClient.clone());
	}

	@Override
	public int compareTo(TrackClient trackClient) {
		return _trackClient.compareTo(trackClient);
	}

	/**
	* Returns the client ip of this track client.
	*
	* @return the client ip of this track client
	*/
	@Override
	public String getClientIP() {
		return _trackClient.getClientIP();
	}

	/**
	* Returns the create date of this track client.
	*
	* @return the create date of this track client
	*/
	@Override
	public Date getCreateDate() {
		return _trackClient.getCreateDate();
	}

	/**
	* Returns the day of this track client.
	*
	* @return the day of this track client
	*/
	@Override
	public int getDay() {
		return _trackClient.getDay();
	}

	/**
	* Returns the desktop of this track client.
	*
	* @return the desktop of this track client
	*/
	@Override
	public boolean getDesktop() {
		return _trackClient.getDesktop();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _trackClient.getExpandoBridge();
	}

	/**
	* Returns the latitude of this track client.
	*
	* @return the latitude of this track client
	*/
	@Override
	public String getLatitude() {
		return _trackClient.getLatitude();
	}

	/**
	* Returns the leave date of this track client.
	*
	* @return the leave date of this track client
	*/
	@Override
	public Date getLeaveDate() {
		return _trackClient.getLeaveDate();
	}

	/**
	* Returns the longitude of this track client.
	*
	* @return the longitude of this track client
	*/
	@Override
	public String getLongitude() {
		return _trackClient.getLongitude();
	}

	/**
	* Returns the mac address of this track client.
	*
	* @return the mac address of this track client
	*/
	@Override
	public String getMacAddress() {
		return _trackClient.getMacAddress();
	}

	/**
	* Returns the mobile of this track client.
	*
	* @return the mobile of this track client
	*/
	@Override
	public boolean getMobile() {
		return _trackClient.getMobile();
	}

	/**
	* Returns the modified date of this track client.
	*
	* @return the modified date of this track client
	*/
	@Override
	public Date getModifiedDate() {
		return _trackClient.getModifiedDate();
	}

	/**
	* Returns the month of this track client.
	*
	* @return the month of this track client
	*/
	@Override
	public int getMonth() {
		return _trackClient.getMonth();
	}

	/**
	* Returns the nation of this track client.
	*
	* @return the nation of this track client
	*/
	@Override
	public String getNation() {
		return _trackClient.getNation();
	}

	/**
	* Returns the primary key of this track client.
	*
	* @return the primary key of this track client
	*/
	@Override
	public long getPrimaryKey() {
		return _trackClient.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _trackClient.getPrimaryKeyObj();
	}

	/**
	* Returns the region of this track client.
	*
	* @return the region of this track client
	*/
	@Override
	public String getRegion() {
		return _trackClient.getRegion();
	}

	/**
	* Returns the session ID of this track client.
	*
	* @return the session ID of this track client
	*/
	@Override
	public String getSessionId() {
		return _trackClient.getSessionId();
	}

	/**
	* Returns the tablet of this track client.
	*
	* @return the tablet of this track client
	*/
	@Override
	public boolean getTablet() {
		return _trackClient.getTablet();
	}

	/**
	* Returns the time on page of this track client.
	*
	* @return the time on page of this track client
	*/
	@Override
	public long getTimeOnPage() {
		return _trackClient.getTimeOnPage();
	}

	/**
	* Returns the track client ID of this track client.
	*
	* @return the track client ID of this track client
	*/
	@Override
	public long getTrackClientId() {
		return _trackClient.getTrackClientId();
	}

	/**
	* Returns the url of this track client.
	*
	* @return the url of this track client
	*/
	@Override
	public String getUrl() {
		return _trackClient.getUrl();
	}

	/**
	* Returns the user ID of this track client.
	*
	* @return the user ID of this track client
	*/
	@Override
	public long getUserId() {
		return _trackClient.getUserId();
	}

	/**
	* Returns the user name of this track client.
	*
	* @return the user name of this track client
	*/
	@Override
	public String getUserName() {
		return _trackClient.getUserName();
	}

	/**
	* Returns the user uuid of this track client.
	*
	* @return the user uuid of this track client
	*/
	@Override
	public String getUserUuid() {
		return _trackClient.getUserUuid();
	}

	/**
	* Returns the uuid of this track client.
	*
	* @return the uuid of this track client
	*/
	@Override
	public String getUuid() {
		return _trackClient.getUuid();
	}

	/**
	* Returns the visit date of this track client.
	*
	* @return the visit date of this track client
	*/
	@Override
	public Date getVisitDate() {
		return _trackClient.getVisitDate();
	}

	/**
	* Returns the year of this track client.
	*
	* @return the year of this track client
	*/
	@Override
	public int getYear() {
		return _trackClient.getYear();
	}

	@Override
	public int hashCode() {
		return _trackClient.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _trackClient.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this track client is desktop.
	*
	* @return <code>true</code> if this track client is desktop; <code>false</code> otherwise
	*/
	@Override
	public boolean isDesktop() {
		return _trackClient.isDesktop();
	}

	@Override
	public boolean isEscapedModel() {
		return _trackClient.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this track client is mobile.
	*
	* @return <code>true</code> if this track client is mobile; <code>false</code> otherwise
	*/
	@Override
	public boolean isMobile() {
		return _trackClient.isMobile();
	}

	@Override
	public boolean isNew() {
		return _trackClient.isNew();
	}

	/**
	* Returns <code>true</code> if this track client is tablet.
	*
	* @return <code>true</code> if this track client is tablet; <code>false</code> otherwise
	*/
	@Override
	public boolean isTablet() {
		return _trackClient.isTablet();
	}

	@Override
	public void persist() {
		_trackClient.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_trackClient.setCachedModel(cachedModel);
	}

	/**
	* Sets the client ip of this track client.
	*
	* @param clientIP the client ip of this track client
	*/
	@Override
	public void setClientIP(String clientIP) {
		_trackClient.setClientIP(clientIP);
	}

	/**
	* Sets the create date of this track client.
	*
	* @param createDate the create date of this track client
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_trackClient.setCreateDate(createDate);
	}

	/**
	* Sets the day of this track client.
	*
	* @param day the day of this track client
	*/
	@Override
	public void setDay(int day) {
		_trackClient.setDay(day);
	}

	/**
	* Sets whether this track client is desktop.
	*
	* @param desktop the desktop of this track client
	*/
	@Override
	public void setDesktop(boolean desktop) {
		_trackClient.setDesktop(desktop);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_trackClient.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_trackClient.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_trackClient.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the latitude of this track client.
	*
	* @param latitude the latitude of this track client
	*/
	@Override
	public void setLatitude(String latitude) {
		_trackClient.setLatitude(latitude);
	}

	/**
	* Sets the leave date of this track client.
	*
	* @param leaveDate the leave date of this track client
	*/
	@Override
	public void setLeaveDate(Date leaveDate) {
		_trackClient.setLeaveDate(leaveDate);
	}

	/**
	* Sets the longitude of this track client.
	*
	* @param longitude the longitude of this track client
	*/
	@Override
	public void setLongitude(String longitude) {
		_trackClient.setLongitude(longitude);
	}

	/**
	* Sets the mac address of this track client.
	*
	* @param macAddress the mac address of this track client
	*/
	@Override
	public void setMacAddress(String macAddress) {
		_trackClient.setMacAddress(macAddress);
	}

	/**
	* Sets whether this track client is mobile.
	*
	* @param mobile the mobile of this track client
	*/
	@Override
	public void setMobile(boolean mobile) {
		_trackClient.setMobile(mobile);
	}

	/**
	* Sets the modified date of this track client.
	*
	* @param modifiedDate the modified date of this track client
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_trackClient.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this track client.
	*
	* @param month the month of this track client
	*/
	@Override
	public void setMonth(int month) {
		_trackClient.setMonth(month);
	}

	/**
	* Sets the nation of this track client.
	*
	* @param nation the nation of this track client
	*/
	@Override
	public void setNation(String nation) {
		_trackClient.setNation(nation);
	}

	@Override
	public void setNew(boolean n) {
		_trackClient.setNew(n);
	}

	/**
	* Sets the primary key of this track client.
	*
	* @param primaryKey the primary key of this track client
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_trackClient.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_trackClient.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the region of this track client.
	*
	* @param region the region of this track client
	*/
	@Override
	public void setRegion(String region) {
		_trackClient.setRegion(region);
	}

	/**
	* Sets the session ID of this track client.
	*
	* @param sessionId the session ID of this track client
	*/
	@Override
	public void setSessionId(String sessionId) {
		_trackClient.setSessionId(sessionId);
	}

	/**
	* Sets whether this track client is tablet.
	*
	* @param tablet the tablet of this track client
	*/
	@Override
	public void setTablet(boolean tablet) {
		_trackClient.setTablet(tablet);
	}

	/**
	* Sets the time on page of this track client.
	*
	* @param timeOnPage the time on page of this track client
	*/
	@Override
	public void setTimeOnPage(long timeOnPage) {
		_trackClient.setTimeOnPage(timeOnPage);
	}

	/**
	* Sets the track client ID of this track client.
	*
	* @param trackClientId the track client ID of this track client
	*/
	@Override
	public void setTrackClientId(long trackClientId) {
		_trackClient.setTrackClientId(trackClientId);
	}

	/**
	* Sets the url of this track client.
	*
	* @param url the url of this track client
	*/
	@Override
	public void setUrl(String url) {
		_trackClient.setUrl(url);
	}

	/**
	* Sets the user ID of this track client.
	*
	* @param userId the user ID of this track client
	*/
	@Override
	public void setUserId(long userId) {
		_trackClient.setUserId(userId);
	}

	/**
	* Sets the user name of this track client.
	*
	* @param userName the user name of this track client
	*/
	@Override
	public void setUserName(String userName) {
		_trackClient.setUserName(userName);
	}

	/**
	* Sets the user uuid of this track client.
	*
	* @param userUuid the user uuid of this track client
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_trackClient.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this track client.
	*
	* @param uuid the uuid of this track client
	*/
	@Override
	public void setUuid(String uuid) {
		_trackClient.setUuid(uuid);
	}

	/**
	* Sets the visit date of this track client.
	*
	* @param visitDate the visit date of this track client
	*/
	@Override
	public void setVisitDate(Date visitDate) {
		_trackClient.setVisitDate(visitDate);
	}

	/**
	* Sets the year of this track client.
	*
	* @param year the year of this track client
	*/
	@Override
	public void setYear(int year) {
		_trackClient.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TrackClient> toCacheModel() {
		return _trackClient.toCacheModel();
	}

	@Override
	public TrackClient toEscapedModel() {
		return new TrackClientWrapper(_trackClient.toEscapedModel());
	}

	@Override
	public String toString() {
		return _trackClient.toString();
	}

	@Override
	public TrackClient toUnescapedModel() {
		return new TrackClientWrapper(_trackClient.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _trackClient.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrackClientWrapper)) {
			return false;
		}

		TrackClientWrapper trackClientWrapper = (TrackClientWrapper)obj;

		if (Objects.equals(_trackClient, trackClientWrapper._trackClient)) {
			return true;
		}

		return false;
	}

	@Override
	public TrackClient getWrappedModel() {
		return _trackClient;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _trackClient.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _trackClient.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_trackClient.resetOriginalValues();
	}

	private final TrackClient _trackClient;
}