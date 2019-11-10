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
 * This class is a wrapper for {@link Booking}.
 * </p>
 *
 * @author huymq
 * @see Booking
 * @generated
 */
@ProviderType
public class BookingWrapper implements Booking, ModelWrapper<Booking> {
	public BookingWrapper(Booking booking) {
		_booking = booking;
	}

	@Override
	public Class<?> getModelClass() {
		return Booking.class;
	}

	@Override
	public String getModelClassName() {
		return Booking.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("bookingId", getBookingId());
		attributes.put(Field.GROUP_ID, getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("serviceCode", getServiceCode());
		attributes.put("codeNumber", getCodeNumber());
		attributes.put("bookingName", getBookingName());
		attributes.put("checkinDate", getCheckinDate());
		attributes.put("gateNumber", getGateNumber());
		attributes.put("state", getState());
		attributes.put("bookingDate", getBookingDate());
		attributes.put("speaking", isSpeaking());
		attributes.put("serviceGroupCode", getServiceGroupCode());
		attributes.put("count", getCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long bookingId = (Long)attributes.get("bookingId");

		if (bookingId != null) {
			setBookingId(bookingId);
		}

		Long groupId = (Long)attributes.get(Field.GROUP_ID);

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

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String serviceCode = (String)attributes.get("serviceCode");

		if (serviceCode != null) {
			setServiceCode(serviceCode);
		}

		String codeNumber = (String)attributes.get("codeNumber");

		if (codeNumber != null) {
			setCodeNumber(codeNumber);
		}

		String bookingName = (String)attributes.get("bookingName");

		if (bookingName != null) {
			setBookingName(bookingName);
		}

		Date checkinDate = (Date)attributes.get("checkinDate");

		if (checkinDate != null) {
			setCheckinDate(checkinDate);
		}

		String gateNumber = (String)attributes.get("gateNumber");

		if (gateNumber != null) {
			setGateNumber(gateNumber);
		}

		Integer state = (Integer)attributes.get("state");

		if (state != null) {
			setState(state);
		}

		Date bookingDate = (Date)attributes.get("bookingDate");

		if (bookingDate != null) {
			setBookingDate(bookingDate);
		}

		Boolean speaking = (Boolean)attributes.get("speaking");

		if (speaking != null) {
			setSpeaking(speaking);
		}

		String serviceGroupCode = (String)attributes.get("serviceGroupCode");

		if (serviceGroupCode != null) {
			setServiceGroupCode(serviceGroupCode);
		}

		Integer count = (Integer)attributes.get("count");

		if (count != null) {
			setCount(count);
		}
	}

	@Override
	public Object clone() {
		return new BookingWrapper((Booking)_booking.clone());
	}

	@Override
	public int compareTo(Booking booking) {
		return _booking.compareTo(booking);
	}

	/**
	* Returns the booking date of this booking.
	*
	* @return the booking date of this booking
	*/
	@Override
	public Date getBookingDate() {
		return _booking.getBookingDate();
	}

	/**
	* Returns the booking ID of this booking.
	*
	* @return the booking ID of this booking
	*/
	@Override
	public long getBookingId() {
		return _booking.getBookingId();
	}

	/**
	* Returns the booking name of this booking.
	*
	* @return the booking name of this booking
	*/
	@Override
	public String getBookingName() {
		return _booking.getBookingName();
	}

	/**
	* Returns the checkin date of this booking.
	*
	* @return the checkin date of this booking
	*/
	@Override
	public Date getCheckinDate() {
		return _booking.getCheckinDate();
	}

	/**
	* Returns the class name of this booking.
	*
	* @return the class name of this booking
	*/
	@Override
	public String getClassName() {
		return _booking.getClassName();
	}

	/**
	* Returns the class pk of this booking.
	*
	* @return the class pk of this booking
	*/
	@Override
	public long getClassPK() {
		return _booking.getClassPK();
	}

	/**
	* Returns the code number of this booking.
	*
	* @return the code number of this booking
	*/
	@Override
	public String getCodeNumber() {
		return _booking.getCodeNumber();
	}

	/**
	* Returns the company ID of this booking.
	*
	* @return the company ID of this booking
	*/
	@Override
	public long getCompanyId() {
		return _booking.getCompanyId();
	}

	/**
	* Returns the count of this booking.
	*
	* @return the count of this booking
	*/
	@Override
	public int getCount() {
		return _booking.getCount();
	}

	/**
	* Returns the create date of this booking.
	*
	* @return the create date of this booking
	*/
	@Override
	public Date getCreateDate() {
		return _booking.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _booking.getExpandoBridge();
	}

	/**
	* Returns the gate number of this booking.
	*
	* @return the gate number of this booking
	*/
	@Override
	public String getGateNumber() {
		return _booking.getGateNumber();
	}

	/**
	* Returns the group ID of this booking.
	*
	* @return the group ID of this booking
	*/
	@Override
	public long getGroupId() {
		return _booking.getGroupId();
	}

	/**
	* Returns the modified date of this booking.
	*
	* @return the modified date of this booking
	*/
	@Override
	public Date getModifiedDate() {
		return _booking.getModifiedDate();
	}

	/**
	* Returns the primary key of this booking.
	*
	* @return the primary key of this booking
	*/
	@Override
	public long getPrimaryKey() {
		return _booking.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _booking.getPrimaryKeyObj();
	}

	/**
	* Returns the service code of this booking.
	*
	* @return the service code of this booking
	*/
	@Override
	public String getServiceCode() {
		return _booking.getServiceCode();
	}

	/**
	* Returns the service group code of this booking.
	*
	* @return the service group code of this booking
	*/
	@Override
	public String getServiceGroupCode() {
		return _booking.getServiceGroupCode();
	}

	/**
	* Returns the speaking of this booking.
	*
	* @return the speaking of this booking
	*/
	@Override
	public boolean getSpeaking() {
		return _booking.getSpeaking();
	}

	/**
	* Returns the state of this booking.
	*
	* @return the state of this booking
	*/
	@Override
	public int getState() {
		return _booking.getState();
	}

	/**
	* Returns the user ID of this booking.
	*
	* @return the user ID of this booking
	*/
	@Override
	public long getUserId() {
		return _booking.getUserId();
	}

	/**
	* Returns the user name of this booking.
	*
	* @return the user name of this booking
	*/
	@Override
	public String getUserName() {
		return _booking.getUserName();
	}

	/**
	* Returns the user uuid of this booking.
	*
	* @return the user uuid of this booking
	*/
	@Override
	public String getUserUuid() {
		return _booking.getUserUuid();
	}

	/**
	* Returns the uuid of this booking.
	*
	* @return the uuid of this booking
	*/
	@Override
	public String getUuid() {
		return _booking.getUuid();
	}

	@Override
	public int hashCode() {
		return _booking.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _booking.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _booking.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _booking.isNew();
	}

	/**
	* Returns <code>true</code> if this booking is speaking.
	*
	* @return <code>true</code> if this booking is speaking; <code>false</code> otherwise
	*/
	@Override
	public boolean isSpeaking() {
		return _booking.isSpeaking();
	}

	@Override
	public void persist() {
		_booking.persist();
	}

	/**
	* Sets the booking date of this booking.
	*
	* @param bookingDate the booking date of this booking
	*/
	@Override
	public void setBookingDate(Date bookingDate) {
		_booking.setBookingDate(bookingDate);
	}

	/**
	* Sets the booking ID of this booking.
	*
	* @param bookingId the booking ID of this booking
	*/
	@Override
	public void setBookingId(long bookingId) {
		_booking.setBookingId(bookingId);
	}

	/**
	* Sets the booking name of this booking.
	*
	* @param bookingName the booking name of this booking
	*/
	@Override
	public void setBookingName(String bookingName) {
		_booking.setBookingName(bookingName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_booking.setCachedModel(cachedModel);
	}

	/**
	* Sets the checkin date of this booking.
	*
	* @param checkinDate the checkin date of this booking
	*/
	@Override
	public void setCheckinDate(Date checkinDate) {
		_booking.setCheckinDate(checkinDate);
	}

	/**
	* Sets the class name of this booking.
	*
	* @param className the class name of this booking
	*/
	@Override
	public void setClassName(String className) {
		_booking.setClassName(className);
	}

	/**
	* Sets the class pk of this booking.
	*
	* @param classPK the class pk of this booking
	*/
	@Override
	public void setClassPK(long classPK) {
		_booking.setClassPK(classPK);
	}

	/**
	* Sets the code number of this booking.
	*
	* @param codeNumber the code number of this booking
	*/
	@Override
	public void setCodeNumber(String codeNumber) {
		_booking.setCodeNumber(codeNumber);
	}

	/**
	* Sets the company ID of this booking.
	*
	* @param companyId the company ID of this booking
	*/
	@Override
	public void setCompanyId(long companyId) {
		_booking.setCompanyId(companyId);
	}

	/**
	* Sets the count of this booking.
	*
	* @param count the count of this booking
	*/
	@Override
	public void setCount(int count) {
		_booking.setCount(count);
	}

	/**
	* Sets the create date of this booking.
	*
	* @param createDate the create date of this booking
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_booking.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_booking.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_booking.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_booking.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gate number of this booking.
	*
	* @param gateNumber the gate number of this booking
	*/
	@Override
	public void setGateNumber(String gateNumber) {
		_booking.setGateNumber(gateNumber);
	}

	/**
	* Sets the group ID of this booking.
	*
	* @param groupId the group ID of this booking
	*/
	@Override
	public void setGroupId(long groupId) {
		_booking.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this booking.
	*
	* @param modifiedDate the modified date of this booking
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_booking.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_booking.setNew(n);
	}

	/**
	* Sets the primary key of this booking.
	*
	* @param primaryKey the primary key of this booking
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_booking.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_booking.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service code of this booking.
	*
	* @param serviceCode the service code of this booking
	*/
	@Override
	public void setServiceCode(String serviceCode) {
		_booking.setServiceCode(serviceCode);
	}

	/**
	* Sets the service group code of this booking.
	*
	* @param serviceGroupCode the service group code of this booking
	*/
	@Override
	public void setServiceGroupCode(String serviceGroupCode) {
		_booking.setServiceGroupCode(serviceGroupCode);
	}

	/**
	* Sets whether this booking is speaking.
	*
	* @param speaking the speaking of this booking
	*/
	@Override
	public void setSpeaking(boolean speaking) {
		_booking.setSpeaking(speaking);
	}

	/**
	* Sets the state of this booking.
	*
	* @param state the state of this booking
	*/
	@Override
	public void setState(int state) {
		_booking.setState(state);
	}

	/**
	* Sets the user ID of this booking.
	*
	* @param userId the user ID of this booking
	*/
	@Override
	public void setUserId(long userId) {
		_booking.setUserId(userId);
	}

	/**
	* Sets the user name of this booking.
	*
	* @param userName the user name of this booking
	*/
	@Override
	public void setUserName(String userName) {
		_booking.setUserName(userName);
	}

	/**
	* Sets the user uuid of this booking.
	*
	* @param userUuid the user uuid of this booking
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_booking.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this booking.
	*
	* @param uuid the uuid of this booking
	*/
	@Override
	public void setUuid(String uuid) {
		_booking.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Booking> toCacheModel() {
		return _booking.toCacheModel();
	}

	@Override
	public Booking toEscapedModel() {
		return new BookingWrapper(_booking.toEscapedModel());
	}

	@Override
	public String toString() {
		return _booking.toString();
	}

	@Override
	public Booking toUnescapedModel() {
		return new BookingWrapper(_booking.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _booking.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BookingWrapper)) {
			return false;
		}

		BookingWrapper bookingWrapper = (BookingWrapper)obj;

		if (Objects.equals(_booking, bookingWrapper._booking)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _booking.getStagedModelType();
	}

	@Override
	public Booking getWrappedModel() {
		return _booking;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _booking.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _booking.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_booking.resetOriginalValues();
	}

	private final Booking _booking;
}