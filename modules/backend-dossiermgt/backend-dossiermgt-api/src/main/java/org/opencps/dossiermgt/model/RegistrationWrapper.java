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
 * This class is a wrapper for {@link Registration}.
 * </p>
 *
 * @author huymq
 * @see Registration
 * @generated
 */
@ProviderType
public class RegistrationWrapper implements Registration,
	ModelWrapper<Registration> {
	public RegistrationWrapper(Registration registration) {
		_registration = registration;
	}

	@Override
	public Class<?> getModelClass() {
		return Registration.class;
	}

	@Override
	public String getModelClassName() {
		return Registration.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("registrationId", getRegistrationId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("applicantName", getApplicantName());
		attributes.put("applicantIdType", getApplicantIdType());
		attributes.put("applicantIdNo", getApplicantIdNo());
		attributes.put("applicantIdDate", getApplicantIdDate());
		attributes.put("address", getAddress());
		attributes.put("cityCode", getCityCode());
		attributes.put("cityName", getCityName());
		attributes.put("districtCode", getDistrictCode());
		attributes.put("districtName", getDistrictName());
		attributes.put("wardCode", getWardCode());
		attributes.put("wardName", getWardName());
		attributes.put("contactName", getContactName());
		attributes.put("contactTelNo", getContactTelNo());
		attributes.put("contactEmail", getContactEmail());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("govAgencyName", getGovAgencyName());
		attributes.put("registrationState", getRegistrationState());
		attributes.put("registrationClass", getRegistrationClass());
		attributes.put("submitting", isSubmitting());
		attributes.put("representativeEnterprise", getRepresentativeEnterprise());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long registrationId = (Long)attributes.get("registrationId");

		if (registrationId != null) {
			setRegistrationId(registrationId);
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

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String applicantName = (String)attributes.get("applicantName");

		if (applicantName != null) {
			setApplicantName(applicantName);
		}

		String applicantIdType = (String)attributes.get("applicantIdType");

		if (applicantIdType != null) {
			setApplicantIdType(applicantIdType);
		}

		String applicantIdNo = (String)attributes.get("applicantIdNo");

		if (applicantIdNo != null) {
			setApplicantIdNo(applicantIdNo);
		}

		Date applicantIdDate = (Date)attributes.get("applicantIdDate");

		if (applicantIdDate != null) {
			setApplicantIdDate(applicantIdDate);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		String cityCode = (String)attributes.get("cityCode");

		if (cityCode != null) {
			setCityCode(cityCode);
		}

		String cityName = (String)attributes.get("cityName");

		if (cityName != null) {
			setCityName(cityName);
		}

		String districtCode = (String)attributes.get("districtCode");

		if (districtCode != null) {
			setDistrictCode(districtCode);
		}

		String districtName = (String)attributes.get("districtName");

		if (districtName != null) {
			setDistrictName(districtName);
		}

		String wardCode = (String)attributes.get("wardCode");

		if (wardCode != null) {
			setWardCode(wardCode);
		}

		String wardName = (String)attributes.get("wardName");

		if (wardName != null) {
			setWardName(wardName);
		}

		String contactName = (String)attributes.get("contactName");

		if (contactName != null) {
			setContactName(contactName);
		}

		String contactTelNo = (String)attributes.get("contactTelNo");

		if (contactTelNo != null) {
			setContactTelNo(contactTelNo);
		}

		String contactEmail = (String)attributes.get("contactEmail");

		if (contactEmail != null) {
			setContactEmail(contactEmail);
		}

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}

		String govAgencyName = (String)attributes.get("govAgencyName");

		if (govAgencyName != null) {
			setGovAgencyName(govAgencyName);
		}

		Integer registrationState = (Integer)attributes.get("registrationState");

		if (registrationState != null) {
			setRegistrationState(registrationState);
		}

		String registrationClass = (String)attributes.get("registrationClass");

		if (registrationClass != null) {
			setRegistrationClass(registrationClass);
		}

		Boolean submitting = (Boolean)attributes.get("submitting");

		if (submitting != null) {
			setSubmitting(submitting);
		}

		String representativeEnterprise = (String)attributes.get(
				"representativeEnterprise");

		if (representativeEnterprise != null) {
			setRepresentativeEnterprise(representativeEnterprise);
		}
	}

	@Override
	public Object clone() {
		return new RegistrationWrapper((Registration)_registration.clone());
	}

	@Override
	public int compareTo(Registration registration) {
		return _registration.compareTo(registration);
	}

	/**
	* Returns the address of this registration.
	*
	* @return the address of this registration
	*/
	@Override
	public String getAddress() {
		return _registration.getAddress();
	}

	/**
	* Returns the applicant ID date of this registration.
	*
	* @return the applicant ID date of this registration
	*/
	@Override
	public Date getApplicantIdDate() {
		return _registration.getApplicantIdDate();
	}

	/**
	* Returns the applicant ID no of this registration.
	*
	* @return the applicant ID no of this registration
	*/
	@Override
	public String getApplicantIdNo() {
		return _registration.getApplicantIdNo();
	}

	/**
	* Returns the applicant ID type of this registration.
	*
	* @return the applicant ID type of this registration
	*/
	@Override
	public String getApplicantIdType() {
		return _registration.getApplicantIdType();
	}

	/**
	* Returns the applicant name of this registration.
	*
	* @return the applicant name of this registration
	*/
	@Override
	public String getApplicantName() {
		return _registration.getApplicantName();
	}

	/**
	* Returns the city code of this registration.
	*
	* @return the city code of this registration
	*/
	@Override
	public String getCityCode() {
		return _registration.getCityCode();
	}

	/**
	* Returns the city name of this registration.
	*
	* @return the city name of this registration
	*/
	@Override
	public String getCityName() {
		return _registration.getCityName();
	}

	/**
	* Returns the company ID of this registration.
	*
	* @return the company ID of this registration
	*/
	@Override
	public long getCompanyId() {
		return _registration.getCompanyId();
	}

	/**
	* Returns the contact email of this registration.
	*
	* @return the contact email of this registration
	*/
	@Override
	public String getContactEmail() {
		return _registration.getContactEmail();
	}

	/**
	* Returns the contact name of this registration.
	*
	* @return the contact name of this registration
	*/
	@Override
	public String getContactName() {
		return _registration.getContactName();
	}

	/**
	* Returns the contact tel no of this registration.
	*
	* @return the contact tel no of this registration
	*/
	@Override
	public String getContactTelNo() {
		return _registration.getContactTelNo();
	}

	/**
	* Returns the create date of this registration.
	*
	* @return the create date of this registration
	*/
	@Override
	public Date getCreateDate() {
		return _registration.getCreateDate();
	}

	/**
	* Returns the district code of this registration.
	*
	* @return the district code of this registration
	*/
	@Override
	public String getDistrictCode() {
		return _registration.getDistrictCode();
	}

	/**
	* Returns the district name of this registration.
	*
	* @return the district name of this registration
	*/
	@Override
	public String getDistrictName() {
		return _registration.getDistrictName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _registration.getExpandoBridge();
	}

	/**
	* Returns the gov agency code of this registration.
	*
	* @return the gov agency code of this registration
	*/
	@Override
	public String getGovAgencyCode() {
		return _registration.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this registration.
	*
	* @return the gov agency name of this registration
	*/
	@Override
	public String getGovAgencyName() {
		return _registration.getGovAgencyName();
	}

	/**
	* Returns the group ID of this registration.
	*
	* @return the group ID of this registration
	*/
	@Override
	public long getGroupId() {
		return _registration.getGroupId();
	}

	/**
	* Returns the modified date of this registration.
	*
	* @return the modified date of this registration
	*/
	@Override
	public Date getModifiedDate() {
		return _registration.getModifiedDate();
	}

	/**
	* Returns the primary key of this registration.
	*
	* @return the primary key of this registration
	*/
	@Override
	public long getPrimaryKey() {
		return _registration.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _registration.getPrimaryKeyObj();
	}

	/**
	* Returns the registration class of this registration.
	*
	* @return the registration class of this registration
	*/
	@Override
	public String getRegistrationClass() {
		return _registration.getRegistrationClass();
	}

	/**
	* Returns the registration ID of this registration.
	*
	* @return the registration ID of this registration
	*/
	@Override
	public long getRegistrationId() {
		return _registration.getRegistrationId();
	}

	/**
	* Returns the registration state of this registration.
	*
	* @return the registration state of this registration
	*/
	@Override
	public int getRegistrationState() {
		return _registration.getRegistrationState();
	}

	/**
	* Returns the representative enterprise of this registration.
	*
	* @return the representative enterprise of this registration
	*/
	@Override
	public String getRepresentativeEnterprise() {
		return _registration.getRepresentativeEnterprise();
	}

	/**
	* Returns the submitting of this registration.
	*
	* @return the submitting of this registration
	*/
	@Override
	public boolean getSubmitting() {
		return _registration.getSubmitting();
	}

	/**
	* Returns the user ID of this registration.
	*
	* @return the user ID of this registration
	*/
	@Override
	public long getUserId() {
		return _registration.getUserId();
	}

	/**
	* Returns the user uuid of this registration.
	*
	* @return the user uuid of this registration
	*/
	@Override
	public String getUserUuid() {
		return _registration.getUserUuid();
	}

	/**
	* Returns the uuid of this registration.
	*
	* @return the uuid of this registration
	*/
	@Override
	public String getUuid() {
		return _registration.getUuid();
	}

	/**
	* Returns the ward code of this registration.
	*
	* @return the ward code of this registration
	*/
	@Override
	public String getWardCode() {
		return _registration.getWardCode();
	}

	/**
	* Returns the ward name of this registration.
	*
	* @return the ward name of this registration
	*/
	@Override
	public String getWardName() {
		return _registration.getWardName();
	}

	@Override
	public int hashCode() {
		return _registration.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _registration.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _registration.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _registration.isNew();
	}

	/**
	* Returns <code>true</code> if this registration is submitting.
	*
	* @return <code>true</code> if this registration is submitting; <code>false</code> otherwise
	*/
	@Override
	public boolean isSubmitting() {
		return _registration.isSubmitting();
	}

	@Override
	public void persist() {
		_registration.persist();
	}

	/**
	* Sets the address of this registration.
	*
	* @param address the address of this registration
	*/
	@Override
	public void setAddress(String address) {
		_registration.setAddress(address);
	}

	/**
	* Sets the applicant ID date of this registration.
	*
	* @param applicantIdDate the applicant ID date of this registration
	*/
	@Override
	public void setApplicantIdDate(Date applicantIdDate) {
		_registration.setApplicantIdDate(applicantIdDate);
	}

	/**
	* Sets the applicant ID no of this registration.
	*
	* @param applicantIdNo the applicant ID no of this registration
	*/
	@Override
	public void setApplicantIdNo(String applicantIdNo) {
		_registration.setApplicantIdNo(applicantIdNo);
	}

	/**
	* Sets the applicant ID type of this registration.
	*
	* @param applicantIdType the applicant ID type of this registration
	*/
	@Override
	public void setApplicantIdType(String applicantIdType) {
		_registration.setApplicantIdType(applicantIdType);
	}

	/**
	* Sets the applicant name of this registration.
	*
	* @param applicantName the applicant name of this registration
	*/
	@Override
	public void setApplicantName(String applicantName) {
		_registration.setApplicantName(applicantName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_registration.setCachedModel(cachedModel);
	}

	/**
	* Sets the city code of this registration.
	*
	* @param cityCode the city code of this registration
	*/
	@Override
	public void setCityCode(String cityCode) {
		_registration.setCityCode(cityCode);
	}

	/**
	* Sets the city name of this registration.
	*
	* @param cityName the city name of this registration
	*/
	@Override
	public void setCityName(String cityName) {
		_registration.setCityName(cityName);
	}

	/**
	* Sets the company ID of this registration.
	*
	* @param companyId the company ID of this registration
	*/
	@Override
	public void setCompanyId(long companyId) {
		_registration.setCompanyId(companyId);
	}

	/**
	* Sets the contact email of this registration.
	*
	* @param contactEmail the contact email of this registration
	*/
	@Override
	public void setContactEmail(String contactEmail) {
		_registration.setContactEmail(contactEmail);
	}

	/**
	* Sets the contact name of this registration.
	*
	* @param contactName the contact name of this registration
	*/
	@Override
	public void setContactName(String contactName) {
		_registration.setContactName(contactName);
	}

	/**
	* Sets the contact tel no of this registration.
	*
	* @param contactTelNo the contact tel no of this registration
	*/
	@Override
	public void setContactTelNo(String contactTelNo) {
		_registration.setContactTelNo(contactTelNo);
	}

	/**
	* Sets the create date of this registration.
	*
	* @param createDate the create date of this registration
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_registration.setCreateDate(createDate);
	}

	/**
	* Sets the district code of this registration.
	*
	* @param districtCode the district code of this registration
	*/
	@Override
	public void setDistrictCode(String districtCode) {
		_registration.setDistrictCode(districtCode);
	}

	/**
	* Sets the district name of this registration.
	*
	* @param districtName the district name of this registration
	*/
	@Override
	public void setDistrictName(String districtName) {
		_registration.setDistrictName(districtName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_registration.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_registration.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_registration.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gov agency code of this registration.
	*
	* @param govAgencyCode the gov agency code of this registration
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_registration.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this registration.
	*
	* @param govAgencyName the gov agency name of this registration
	*/
	@Override
	public void setGovAgencyName(String govAgencyName) {
		_registration.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the group ID of this registration.
	*
	* @param groupId the group ID of this registration
	*/
	@Override
	public void setGroupId(long groupId) {
		_registration.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this registration.
	*
	* @param modifiedDate the modified date of this registration
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_registration.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_registration.setNew(n);
	}

	/**
	* Sets the primary key of this registration.
	*
	* @param primaryKey the primary key of this registration
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_registration.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_registration.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the registration class of this registration.
	*
	* @param registrationClass the registration class of this registration
	*/
	@Override
	public void setRegistrationClass(String registrationClass) {
		_registration.setRegistrationClass(registrationClass);
	}

	/**
	* Sets the registration ID of this registration.
	*
	* @param registrationId the registration ID of this registration
	*/
	@Override
	public void setRegistrationId(long registrationId) {
		_registration.setRegistrationId(registrationId);
	}

	/**
	* Sets the registration state of this registration.
	*
	* @param registrationState the registration state of this registration
	*/
	@Override
	public void setRegistrationState(int registrationState) {
		_registration.setRegistrationState(registrationState);
	}

	/**
	* Sets the representative enterprise of this registration.
	*
	* @param representativeEnterprise the representative enterprise of this registration
	*/
	@Override
	public void setRepresentativeEnterprise(String representativeEnterprise) {
		_registration.setRepresentativeEnterprise(representativeEnterprise);
	}

	/**
	* Sets whether this registration is submitting.
	*
	* @param submitting the submitting of this registration
	*/
	@Override
	public void setSubmitting(boolean submitting) {
		_registration.setSubmitting(submitting);
	}

	/**
	* Sets the user ID of this registration.
	*
	* @param userId the user ID of this registration
	*/
	@Override
	public void setUserId(long userId) {
		_registration.setUserId(userId);
	}

	/**
	* Sets the user uuid of this registration.
	*
	* @param userUuid the user uuid of this registration
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_registration.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this registration.
	*
	* @param uuid the uuid of this registration
	*/
	@Override
	public void setUuid(String uuid) {
		_registration.setUuid(uuid);
	}

	/**
	* Sets the ward code of this registration.
	*
	* @param wardCode the ward code of this registration
	*/
	@Override
	public void setWardCode(String wardCode) {
		_registration.setWardCode(wardCode);
	}

	/**
	* Sets the ward name of this registration.
	*
	* @param wardName the ward name of this registration
	*/
	@Override
	public void setWardName(String wardName) {
		_registration.setWardName(wardName);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Registration> toCacheModel() {
		return _registration.toCacheModel();
	}

	@Override
	public Registration toEscapedModel() {
		return new RegistrationWrapper(_registration.toEscapedModel());
	}

	@Override
	public String toString() {
		return _registration.toString();
	}

	@Override
	public Registration toUnescapedModel() {
		return new RegistrationWrapper(_registration.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _registration.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RegistrationWrapper)) {
			return false;
		}

		RegistrationWrapper registrationWrapper = (RegistrationWrapper)obj;

		if (Objects.equals(_registration, registrationWrapper._registration)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _registration.getStagedModelType();
	}

	@Override
	public Registration getWrappedModel() {
		return _registration;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _registration.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _registration.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_registration.resetOriginalValues();
	}

	private final Registration _registration;
}