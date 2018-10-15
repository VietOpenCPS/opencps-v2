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
 * This class is a wrapper for {@link Applicant}.
 * </p>
 *
 * @author khoavu
 * @see Applicant
 * @generated
 */
@ProviderType
public class ApplicantWrapper implements Applicant, ModelWrapper<Applicant> {
	public ApplicantWrapper(Applicant applicant) {
		_applicant = applicant;
	}

	@Override
	public Class<?> getModelClass() {
		return Applicant.class;
	}

	@Override
	public String getModelClassName() {
		return Applicant.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("applicantId", getApplicantId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
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
		attributes.put("mappingUserId", getMappingUserId());
		attributes.put("activationCode", getActivationCode());
		attributes.put("lock_", isLock_());
		attributes.put("profile", getProfile());
		attributes.put("tmpPass", getTmpPass());
		attributes.put("representativeEnterprise", getRepresentativeEnterprise());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long applicantId = (Long)attributes.get("applicantId");

		if (applicantId != null) {
			setApplicantId(applicantId);
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

		Long mappingUserId = (Long)attributes.get("mappingUserId");

		if (mappingUserId != null) {
			setMappingUserId(mappingUserId);
		}

		String activationCode = (String)attributes.get("activationCode");

		if (activationCode != null) {
			setActivationCode(activationCode);
		}

		Boolean lock_ = (Boolean)attributes.get("lock_");

		if (lock_ != null) {
			setLock_(lock_);
		}

		String profile = (String)attributes.get("profile");

		if (profile != null) {
			setProfile(profile);
		}

		String tmpPass = (String)attributes.get("tmpPass");

		if (tmpPass != null) {
			setTmpPass(tmpPass);
		}

		String representativeEnterprise = (String)attributes.get(
				"representativeEnterprise");

		if (representativeEnterprise != null) {
			setRepresentativeEnterprise(representativeEnterprise);
		}
	}

	@Override
	public Object clone() {
		return new ApplicantWrapper((Applicant)_applicant.clone());
	}

	@Override
	public int compareTo(Applicant applicant) {
		return _applicant.compareTo(applicant);
	}

	/**
	* Returns the activation code of this applicant.
	*
	* @return the activation code of this applicant
	*/
	@Override
	public String getActivationCode() {
		return _applicant.getActivationCode();
	}

	/**
	* Returns the address of this applicant.
	*
	* @return the address of this applicant
	*/
	@Override
	public String getAddress() {
		return _applicant.getAddress();
	}

	/**
	* Returns the applicant ID of this applicant.
	*
	* @return the applicant ID of this applicant
	*/
	@Override
	public long getApplicantId() {
		return _applicant.getApplicantId();
	}

	/**
	* Returns the applicant ID date of this applicant.
	*
	* @return the applicant ID date of this applicant
	*/
	@Override
	public Date getApplicantIdDate() {
		return _applicant.getApplicantIdDate();
	}

	/**
	* Returns the applicant ID no of this applicant.
	*
	* @return the applicant ID no of this applicant
	*/
	@Override
	public String getApplicantIdNo() {
		return _applicant.getApplicantIdNo();
	}

	/**
	* Returns the applicant ID type of this applicant.
	*
	* @return the applicant ID type of this applicant
	*/
	@Override
	public String getApplicantIdType() {
		return _applicant.getApplicantIdType();
	}

	/**
	* Returns the applicant name of this applicant.
	*
	* @return the applicant name of this applicant
	*/
	@Override
	public String getApplicantName() {
		return _applicant.getApplicantName();
	}

	/**
	* Returns the city code of this applicant.
	*
	* @return the city code of this applicant
	*/
	@Override
	public String getCityCode() {
		return _applicant.getCityCode();
	}

	/**
	* Returns the city name of this applicant.
	*
	* @return the city name of this applicant
	*/
	@Override
	public String getCityName() {
		return _applicant.getCityName();
	}

	/**
	* Returns the company ID of this applicant.
	*
	* @return the company ID of this applicant
	*/
	@Override
	public long getCompanyId() {
		return _applicant.getCompanyId();
	}

	/**
	* Returns the contact email of this applicant.
	*
	* @return the contact email of this applicant
	*/
	@Override
	public String getContactEmail() {
		return _applicant.getContactEmail();
	}

	/**
	* Returns the contact name of this applicant.
	*
	* @return the contact name of this applicant
	*/
	@Override
	public String getContactName() {
		return _applicant.getContactName();
	}

	/**
	* Returns the contact tel no of this applicant.
	*
	* @return the contact tel no of this applicant
	*/
	@Override
	public String getContactTelNo() {
		return _applicant.getContactTelNo();
	}

	/**
	* Returns the create date of this applicant.
	*
	* @return the create date of this applicant
	*/
	@Override
	public Date getCreateDate() {
		return _applicant.getCreateDate();
	}

	/**
	* Returns the district code of this applicant.
	*
	* @return the district code of this applicant
	*/
	@Override
	public String getDistrictCode() {
		return _applicant.getDistrictCode();
	}

	/**
	* Returns the district name of this applicant.
	*
	* @return the district name of this applicant
	*/
	@Override
	public String getDistrictName() {
		return _applicant.getDistrictName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _applicant.getExpandoBridge();
	}

	/**
	* Returns the group ID of this applicant.
	*
	* @return the group ID of this applicant
	*/
	@Override
	public long getGroupId() {
		return _applicant.getGroupId();
	}

	/**
	* Returns the lock_ of this applicant.
	*
	* @return the lock_ of this applicant
	*/
	@Override
	public boolean getLock_() {
		return _applicant.getLock_();
	}

	/**
	* Returns the mapping user ID of this applicant.
	*
	* @return the mapping user ID of this applicant
	*/
	@Override
	public long getMappingUserId() {
		return _applicant.getMappingUserId();
	}

	/**
	* Returns the mapping user uuid of this applicant.
	*
	* @return the mapping user uuid of this applicant
	*/
	@Override
	public String getMappingUserUuid() {
		return _applicant.getMappingUserUuid();
	}

	/**
	* Returns the modified date of this applicant.
	*
	* @return the modified date of this applicant
	*/
	@Override
	public Date getModifiedDate() {
		return _applicant.getModifiedDate();
	}

	/**
	* Returns the primary key of this applicant.
	*
	* @return the primary key of this applicant
	*/
	@Override
	public long getPrimaryKey() {
		return _applicant.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _applicant.getPrimaryKeyObj();
	}

	/**
	* Returns the profile of this applicant.
	*
	* @return the profile of this applicant
	*/
	@Override
	public String getProfile() {
		return _applicant.getProfile();
	}

	/**
	* Returns the representative enterprise of this applicant.
	*
	* @return the representative enterprise of this applicant
	*/
	@Override
	public String getRepresentativeEnterprise() {
		return _applicant.getRepresentativeEnterprise();
	}

	/**
	* Returns the tmp pass of this applicant.
	*
	* @return the tmp pass of this applicant
	*/
	@Override
	public String getTmpPass() {
		return _applicant.getTmpPass();
	}

	/**
	* Returns the user ID of this applicant.
	*
	* @return the user ID of this applicant
	*/
	@Override
	public long getUserId() {
		return _applicant.getUserId();
	}

	/**
	* Returns the user name of this applicant.
	*
	* @return the user name of this applicant
	*/
	@Override
	public String getUserName() {
		return _applicant.getUserName();
	}

	/**
	* Returns the user uuid of this applicant.
	*
	* @return the user uuid of this applicant
	*/
	@Override
	public String getUserUuid() {
		return _applicant.getUserUuid();
	}

	/**
	* Returns the uuid of this applicant.
	*
	* @return the uuid of this applicant
	*/
	@Override
	public String getUuid() {
		return _applicant.getUuid();
	}

	/**
	* Returns the ward code of this applicant.
	*
	* @return the ward code of this applicant
	*/
	@Override
	public String getWardCode() {
		return _applicant.getWardCode();
	}

	/**
	* Returns the ward name of this applicant.
	*
	* @return the ward name of this applicant
	*/
	@Override
	public String getWardName() {
		return _applicant.getWardName();
	}

	@Override
	public int hashCode() {
		return _applicant.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _applicant.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _applicant.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this applicant is lock_.
	*
	* @return <code>true</code> if this applicant is lock_; <code>false</code> otherwise
	*/
	@Override
	public boolean isLock_() {
		return _applicant.isLock_();
	}

	@Override
	public boolean isNew() {
		return _applicant.isNew();
	}

	@Override
	public void persist() {
		_applicant.persist();
	}

	/**
	* Sets the activation code of this applicant.
	*
	* @param activationCode the activation code of this applicant
	*/
	@Override
	public void setActivationCode(String activationCode) {
		_applicant.setActivationCode(activationCode);
	}

	/**
	* Sets the address of this applicant.
	*
	* @param address the address of this applicant
	*/
	@Override
	public void setAddress(String address) {
		_applicant.setAddress(address);
	}

	/**
	* Sets the applicant ID of this applicant.
	*
	* @param applicantId the applicant ID of this applicant
	*/
	@Override
	public void setApplicantId(long applicantId) {
		_applicant.setApplicantId(applicantId);
	}

	/**
	* Sets the applicant ID date of this applicant.
	*
	* @param applicantIdDate the applicant ID date of this applicant
	*/
	@Override
	public void setApplicantIdDate(Date applicantIdDate) {
		_applicant.setApplicantIdDate(applicantIdDate);
	}

	/**
	* Sets the applicant ID no of this applicant.
	*
	* @param applicantIdNo the applicant ID no of this applicant
	*/
	@Override
	public void setApplicantIdNo(String applicantIdNo) {
		_applicant.setApplicantIdNo(applicantIdNo);
	}

	/**
	* Sets the applicant ID type of this applicant.
	*
	* @param applicantIdType the applicant ID type of this applicant
	*/
	@Override
	public void setApplicantIdType(String applicantIdType) {
		_applicant.setApplicantIdType(applicantIdType);
	}

	/**
	* Sets the applicant name of this applicant.
	*
	* @param applicantName the applicant name of this applicant
	*/
	@Override
	public void setApplicantName(String applicantName) {
		_applicant.setApplicantName(applicantName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_applicant.setCachedModel(cachedModel);
	}

	/**
	* Sets the city code of this applicant.
	*
	* @param cityCode the city code of this applicant
	*/
	@Override
	public void setCityCode(String cityCode) {
		_applicant.setCityCode(cityCode);
	}

	/**
	* Sets the city name of this applicant.
	*
	* @param cityName the city name of this applicant
	*/
	@Override
	public void setCityName(String cityName) {
		_applicant.setCityName(cityName);
	}

	/**
	* Sets the company ID of this applicant.
	*
	* @param companyId the company ID of this applicant
	*/
	@Override
	public void setCompanyId(long companyId) {
		_applicant.setCompanyId(companyId);
	}

	/**
	* Sets the contact email of this applicant.
	*
	* @param contactEmail the contact email of this applicant
	*/
	@Override
	public void setContactEmail(String contactEmail) {
		_applicant.setContactEmail(contactEmail);
	}

	/**
	* Sets the contact name of this applicant.
	*
	* @param contactName the contact name of this applicant
	*/
	@Override
	public void setContactName(String contactName) {
		_applicant.setContactName(contactName);
	}

	/**
	* Sets the contact tel no of this applicant.
	*
	* @param contactTelNo the contact tel no of this applicant
	*/
	@Override
	public void setContactTelNo(String contactTelNo) {
		_applicant.setContactTelNo(contactTelNo);
	}

	/**
	* Sets the create date of this applicant.
	*
	* @param createDate the create date of this applicant
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_applicant.setCreateDate(createDate);
	}

	/**
	* Sets the district code of this applicant.
	*
	* @param districtCode the district code of this applicant
	*/
	@Override
	public void setDistrictCode(String districtCode) {
		_applicant.setDistrictCode(districtCode);
	}

	/**
	* Sets the district name of this applicant.
	*
	* @param districtName the district name of this applicant
	*/
	@Override
	public void setDistrictName(String districtName) {
		_applicant.setDistrictName(districtName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_applicant.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_applicant.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_applicant.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this applicant.
	*
	* @param groupId the group ID of this applicant
	*/
	@Override
	public void setGroupId(long groupId) {
		_applicant.setGroupId(groupId);
	}

	/**
	* Sets whether this applicant is lock_.
	*
	* @param lock_ the lock_ of this applicant
	*/
	@Override
	public void setLock_(boolean lock_) {
		_applicant.setLock_(lock_);
	}

	/**
	* Sets the mapping user ID of this applicant.
	*
	* @param mappingUserId the mapping user ID of this applicant
	*/
	@Override
	public void setMappingUserId(long mappingUserId) {
		_applicant.setMappingUserId(mappingUserId);
	}

	/**
	* Sets the mapping user uuid of this applicant.
	*
	* @param mappingUserUuid the mapping user uuid of this applicant
	*/
	@Override
	public void setMappingUserUuid(String mappingUserUuid) {
		_applicant.setMappingUserUuid(mappingUserUuid);
	}

	/**
	* Sets the modified date of this applicant.
	*
	* @param modifiedDate the modified date of this applicant
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_applicant.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_applicant.setNew(n);
	}

	/**
	* Sets the primary key of this applicant.
	*
	* @param primaryKey the primary key of this applicant
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_applicant.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_applicant.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the profile of this applicant.
	*
	* @param profile the profile of this applicant
	*/
	@Override
	public void setProfile(String profile) {
		_applicant.setProfile(profile);
	}

	/**
	* Sets the representative enterprise of this applicant.
	*
	* @param representativeEnterprise the representative enterprise of this applicant
	*/
	@Override
	public void setRepresentativeEnterprise(String representativeEnterprise) {
		_applicant.setRepresentativeEnterprise(representativeEnterprise);
	}

	/**
	* Sets the tmp pass of this applicant.
	*
	* @param tmpPass the tmp pass of this applicant
	*/
	@Override
	public void setTmpPass(String tmpPass) {
		_applicant.setTmpPass(tmpPass);
	}

	/**
	* Sets the user ID of this applicant.
	*
	* @param userId the user ID of this applicant
	*/
	@Override
	public void setUserId(long userId) {
		_applicant.setUserId(userId);
	}

	/**
	* Sets the user name of this applicant.
	*
	* @param userName the user name of this applicant
	*/
	@Override
	public void setUserName(String userName) {
		_applicant.setUserName(userName);
	}

	/**
	* Sets the user uuid of this applicant.
	*
	* @param userUuid the user uuid of this applicant
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_applicant.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this applicant.
	*
	* @param uuid the uuid of this applicant
	*/
	@Override
	public void setUuid(String uuid) {
		_applicant.setUuid(uuid);
	}

	/**
	* Sets the ward code of this applicant.
	*
	* @param wardCode the ward code of this applicant
	*/
	@Override
	public void setWardCode(String wardCode) {
		_applicant.setWardCode(wardCode);
	}

	/**
	* Sets the ward name of this applicant.
	*
	* @param wardName the ward name of this applicant
	*/
	@Override
	public void setWardName(String wardName) {
		_applicant.setWardName(wardName);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Applicant> toCacheModel() {
		return _applicant.toCacheModel();
	}

	@Override
	public Applicant toEscapedModel() {
		return new ApplicantWrapper(_applicant.toEscapedModel());
	}

	@Override
	public String toString() {
		return _applicant.toString();
	}

	@Override
	public Applicant toUnescapedModel() {
		return new ApplicantWrapper(_applicant.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _applicant.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApplicantWrapper)) {
			return false;
		}

		ApplicantWrapper applicantWrapper = (ApplicantWrapper)obj;

		if (Objects.equals(_applicant, applicantWrapper._applicant)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _applicant.getStagedModelType();
	}

	@Override
	public Applicant getWrappedModel() {
		return _applicant;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _applicant.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _applicant.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_applicant.resetOriginalValues();
	}

	private final Applicant _applicant;
}