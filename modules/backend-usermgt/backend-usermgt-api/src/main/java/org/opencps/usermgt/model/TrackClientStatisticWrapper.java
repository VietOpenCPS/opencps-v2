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
 * This class is a wrapper for {@link TrackClientStatistic}.
 * </p>
 *
 * @author khoavu
 * @see TrackClientStatistic
 * @generated
 */
@ProviderType
public class TrackClientStatisticWrapper implements TrackClientStatistic,
	ModelWrapper<TrackClientStatistic> {
	public TrackClientStatisticWrapper(
		TrackClientStatistic trackClientStatistic) {
		_trackClientStatistic = trackClientStatistic;
	}

	@Override
	public Class<?> getModelClass() {
		return TrackClientStatistic.class;
	}

	@Override
	public String getModelClassName() {
		return TrackClientStatistic.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("trackClientStatisticId", getTrackClientStatisticId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("url", getUrl());
		attributes.put("year", getYear());
		attributes.put("month", getMonth());
		attributes.put("day", getDay());
		attributes.put("region", getRegion());
		attributes.put("desktop", isDesktop());
		attributes.put("mobile", isMobile());
		attributes.put("tablet", isTablet());
		attributes.put("total", getTotal());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long trackClientStatisticId = (Long)attributes.get(
				"trackClientStatisticId");

		if (trackClientStatisticId != null) {
			setTrackClientStatisticId(trackClientStatisticId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
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

		String region = (String)attributes.get("region");

		if (region != null) {
			setRegion(region);
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

		Long total = (Long)attributes.get("total");

		if (total != null) {
			setTotal(total);
		}
	}

	@Override
	public Object clone() {
		return new TrackClientStatisticWrapper((TrackClientStatistic)_trackClientStatistic.clone());
	}

	@Override
	public int compareTo(TrackClientStatistic trackClientStatistic) {
		return _trackClientStatistic.compareTo(trackClientStatistic);
	}

	/**
	* Returns the create date of this track client statistic.
	*
	* @return the create date of this track client statistic
	*/
	@Override
	public Date getCreateDate() {
		return _trackClientStatistic.getCreateDate();
	}

	/**
	* Returns the day of this track client statistic.
	*
	* @return the day of this track client statistic
	*/
	@Override
	public int getDay() {
		return _trackClientStatistic.getDay();
	}

	/**
	* Returns the desktop of this track client statistic.
	*
	* @return the desktop of this track client statistic
	*/
	@Override
	public boolean getDesktop() {
		return _trackClientStatistic.getDesktop();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _trackClientStatistic.getExpandoBridge();
	}

	/**
	* Returns the mobile of this track client statistic.
	*
	* @return the mobile of this track client statistic
	*/
	@Override
	public boolean getMobile() {
		return _trackClientStatistic.getMobile();
	}

	/**
	* Returns the modified date of this track client statistic.
	*
	* @return the modified date of this track client statistic
	*/
	@Override
	public Date getModifiedDate() {
		return _trackClientStatistic.getModifiedDate();
	}

	/**
	* Returns the month of this track client statistic.
	*
	* @return the month of this track client statistic
	*/
	@Override
	public int getMonth() {
		return _trackClientStatistic.getMonth();
	}

	/**
	* Returns the primary key of this track client statistic.
	*
	* @return the primary key of this track client statistic
	*/
	@Override
	public long getPrimaryKey() {
		return _trackClientStatistic.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _trackClientStatistic.getPrimaryKeyObj();
	}

	/**
	* Returns the region of this track client statistic.
	*
	* @return the region of this track client statistic
	*/
	@Override
	public String getRegion() {
		return _trackClientStatistic.getRegion();
	}

	/**
	* Returns the tablet of this track client statistic.
	*
	* @return the tablet of this track client statistic
	*/
	@Override
	public boolean getTablet() {
		return _trackClientStatistic.getTablet();
	}

	/**
	* Returns the total of this track client statistic.
	*
	* @return the total of this track client statistic
	*/
	@Override
	public long getTotal() {
		return _trackClientStatistic.getTotal();
	}

	/**
	* Returns the track client statistic ID of this track client statistic.
	*
	* @return the track client statistic ID of this track client statistic
	*/
	@Override
	public long getTrackClientStatisticId() {
		return _trackClientStatistic.getTrackClientStatisticId();
	}

	/**
	* Returns the url of this track client statistic.
	*
	* @return the url of this track client statistic
	*/
	@Override
	public String getUrl() {
		return _trackClientStatistic.getUrl();
	}

	/**
	* Returns the uuid of this track client statistic.
	*
	* @return the uuid of this track client statistic
	*/
	@Override
	public String getUuid() {
		return _trackClientStatistic.getUuid();
	}

	/**
	* Returns the year of this track client statistic.
	*
	* @return the year of this track client statistic
	*/
	@Override
	public int getYear() {
		return _trackClientStatistic.getYear();
	}

	@Override
	public int hashCode() {
		return _trackClientStatistic.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _trackClientStatistic.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this track client statistic is desktop.
	*
	* @return <code>true</code> if this track client statistic is desktop; <code>false</code> otherwise
	*/
	@Override
	public boolean isDesktop() {
		return _trackClientStatistic.isDesktop();
	}

	@Override
	public boolean isEscapedModel() {
		return _trackClientStatistic.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this track client statistic is mobile.
	*
	* @return <code>true</code> if this track client statistic is mobile; <code>false</code> otherwise
	*/
	@Override
	public boolean isMobile() {
		return _trackClientStatistic.isMobile();
	}

	@Override
	public boolean isNew() {
		return _trackClientStatistic.isNew();
	}

	/**
	* Returns <code>true</code> if this track client statistic is tablet.
	*
	* @return <code>true</code> if this track client statistic is tablet; <code>false</code> otherwise
	*/
	@Override
	public boolean isTablet() {
		return _trackClientStatistic.isTablet();
	}

	@Override
	public void persist() {
		_trackClientStatistic.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_trackClientStatistic.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this track client statistic.
	*
	* @param createDate the create date of this track client statistic
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_trackClientStatistic.setCreateDate(createDate);
	}

	/**
	* Sets the day of this track client statistic.
	*
	* @param day the day of this track client statistic
	*/
	@Override
	public void setDay(int day) {
		_trackClientStatistic.setDay(day);
	}

	/**
	* Sets whether this track client statistic is desktop.
	*
	* @param desktop the desktop of this track client statistic
	*/
	@Override
	public void setDesktop(boolean desktop) {
		_trackClientStatistic.setDesktop(desktop);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_trackClientStatistic.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_trackClientStatistic.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_trackClientStatistic.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this track client statistic is mobile.
	*
	* @param mobile the mobile of this track client statistic
	*/
	@Override
	public void setMobile(boolean mobile) {
		_trackClientStatistic.setMobile(mobile);
	}

	/**
	* Sets the modified date of this track client statistic.
	*
	* @param modifiedDate the modified date of this track client statistic
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_trackClientStatistic.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this track client statistic.
	*
	* @param month the month of this track client statistic
	*/
	@Override
	public void setMonth(int month) {
		_trackClientStatistic.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_trackClientStatistic.setNew(n);
	}

	/**
	* Sets the primary key of this track client statistic.
	*
	* @param primaryKey the primary key of this track client statistic
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_trackClientStatistic.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_trackClientStatistic.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the region of this track client statistic.
	*
	* @param region the region of this track client statistic
	*/
	@Override
	public void setRegion(String region) {
		_trackClientStatistic.setRegion(region);
	}

	/**
	* Sets whether this track client statistic is tablet.
	*
	* @param tablet the tablet of this track client statistic
	*/
	@Override
	public void setTablet(boolean tablet) {
		_trackClientStatistic.setTablet(tablet);
	}

	/**
	* Sets the total of this track client statistic.
	*
	* @param total the total of this track client statistic
	*/
	@Override
	public void setTotal(long total) {
		_trackClientStatistic.setTotal(total);
	}

	/**
	* Sets the track client statistic ID of this track client statistic.
	*
	* @param trackClientStatisticId the track client statistic ID of this track client statistic
	*/
	@Override
	public void setTrackClientStatisticId(long trackClientStatisticId) {
		_trackClientStatistic.setTrackClientStatisticId(trackClientStatisticId);
	}

	/**
	* Sets the url of this track client statistic.
	*
	* @param url the url of this track client statistic
	*/
	@Override
	public void setUrl(String url) {
		_trackClientStatistic.setUrl(url);
	}

	/**
	* Sets the uuid of this track client statistic.
	*
	* @param uuid the uuid of this track client statistic
	*/
	@Override
	public void setUuid(String uuid) {
		_trackClientStatistic.setUuid(uuid);
	}

	/**
	* Sets the year of this track client statistic.
	*
	* @param year the year of this track client statistic
	*/
	@Override
	public void setYear(int year) {
		_trackClientStatistic.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TrackClientStatistic> toCacheModel() {
		return _trackClientStatistic.toCacheModel();
	}

	@Override
	public TrackClientStatistic toEscapedModel() {
		return new TrackClientStatisticWrapper(_trackClientStatistic.toEscapedModel());
	}

	@Override
	public String toString() {
		return _trackClientStatistic.toString();
	}

	@Override
	public TrackClientStatistic toUnescapedModel() {
		return new TrackClientStatisticWrapper(_trackClientStatistic.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _trackClientStatistic.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrackClientStatisticWrapper)) {
			return false;
		}

		TrackClientStatisticWrapper trackClientStatisticWrapper = (TrackClientStatisticWrapper)obj;

		if (Objects.equals(_trackClientStatistic,
					trackClientStatisticWrapper._trackClientStatistic)) {
			return true;
		}

		return false;
	}

	@Override
	public TrackClientStatistic getWrappedModel() {
		return _trackClientStatistic;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _trackClientStatistic.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _trackClientStatistic.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_trackClientStatistic.resetOriginalValues();
	}

	private final TrackClientStatistic _trackClientStatistic;
}