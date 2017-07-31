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

package org.mobilink.backend.usermgt.model;

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
 * This class is a wrapper for {@link OfficeSite}.
 * </p>
 *
 * @author Binhth
 * @see OfficeSite
 * @generated
 */
@ProviderType
public class OfficeSiteWrapper implements OfficeSite, ModelWrapper<OfficeSite> {
	public OfficeSiteWrapper(OfficeSite officeSite) {
		_officeSite = officeSite;
	}

	@Override
	public Class<?> getModelClass() {
		return OfficeSite.class;
	}

	@Override
	public String getModelClassName() {
		return OfficeSite.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("officeSiteId", getOfficeSiteId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("enName", getEnName());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("address", getAddress());
		attributes.put("telNo", getTelNo());
		attributes.put("faxNo", getFaxNo());
		attributes.put("email", getEmail());
		attributes.put("website", getWebsite());
		attributes.put("logoFileEntryId", getLogoFileEntryId());
		attributes.put("siteGroupId", getSiteGroupId());
		attributes.put("adminUserId", getAdminUserId());
		attributes.put("preferences", getPreferences());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long officeSiteId = (Long)attributes.get("officeSiteId");

		if (officeSiteId != null) {
			setOfficeSiteId(officeSiteId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String enName = (String)attributes.get("enName");

		if (enName != null) {
			setEnName(enName);
		}

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		String telNo = (String)attributes.get("telNo");

		if (telNo != null) {
			setTelNo(telNo);
		}

		String faxNo = (String)attributes.get("faxNo");

		if (faxNo != null) {
			setFaxNo(faxNo);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String website = (String)attributes.get("website");

		if (website != null) {
			setWebsite(website);
		}

		Long logoFileEntryId = (Long)attributes.get("logoFileEntryId");

		if (logoFileEntryId != null) {
			setLogoFileEntryId(logoFileEntryId);
		}

		Long siteGroupId = (Long)attributes.get("siteGroupId");

		if (siteGroupId != null) {
			setSiteGroupId(siteGroupId);
		}

		Long adminUserId = (Long)attributes.get("adminUserId");

		if (adminUserId != null) {
			setAdminUserId(adminUserId);
		}

		String preferences = (String)attributes.get("preferences");

		if (preferences != null) {
			setPreferences(preferences);
		}
	}

	@Override
	public OfficeSite toEscapedModel() {
		return new OfficeSiteWrapper(_officeSite.toEscapedModel());
	}

	@Override
	public OfficeSite toUnescapedModel() {
		return new OfficeSiteWrapper(_officeSite.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _officeSite.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _officeSite.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _officeSite.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _officeSite.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OfficeSite> toCacheModel() {
		return _officeSite.toCacheModel();
	}

	@Override
	public int compareTo(OfficeSite officeSite) {
		return _officeSite.compareTo(officeSite);
	}

	@Override
	public int hashCode() {
		return _officeSite.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _officeSite.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new OfficeSiteWrapper((OfficeSite)_officeSite.clone());
	}

	/**
	* Returns the address of this office site.
	*
	* @return the address of this office site
	*/
	@Override
	public java.lang.String getAddress() {
		return _officeSite.getAddress();
	}

	/**
	* Returns the admin user uuid of this office site.
	*
	* @return the admin user uuid of this office site
	*/
	@Override
	public java.lang.String getAdminUserUuid() {
		return _officeSite.getAdminUserUuid();
	}

	/**
	* Returns the email of this office site.
	*
	* @return the email of this office site
	*/
	@Override
	public java.lang.String getEmail() {
		return _officeSite.getEmail();
	}

	/**
	* Returns the en name of this office site.
	*
	* @return the en name of this office site
	*/
	@Override
	public java.lang.String getEnName() {
		return _officeSite.getEnName();
	}

	/**
	* Returns the fax no of this office site.
	*
	* @return the fax no of this office site
	*/
	@Override
	public java.lang.String getFaxNo() {
		return _officeSite.getFaxNo();
	}

	/**
	* Returns the gov agency code of this office site.
	*
	* @return the gov agency code of this office site
	*/
	@Override
	public java.lang.String getGovAgencyCode() {
		return _officeSite.getGovAgencyCode();
	}

	/**
	* Returns the name of this office site.
	*
	* @return the name of this office site
	*/
	@Override
	public java.lang.String getName() {
		return _officeSite.getName();
	}

	/**
	* Returns the preferences of this office site.
	*
	* @return the preferences of this office site
	*/
	@Override
	public java.lang.String getPreferences() {
		return _officeSite.getPreferences();
	}

	/**
	* Returns the tel no of this office site.
	*
	* @return the tel no of this office site
	*/
	@Override
	public java.lang.String getTelNo() {
		return _officeSite.getTelNo();
	}

	/**
	* Returns the user name of this office site.
	*
	* @return the user name of this office site
	*/
	@Override
	public java.lang.String getUserName() {
		return _officeSite.getUserName();
	}

	/**
	* Returns the user uuid of this office site.
	*
	* @return the user uuid of this office site
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _officeSite.getUserUuid();
	}

	/**
	* Returns the uuid of this office site.
	*
	* @return the uuid of this office site
	*/
	@Override
	public java.lang.String getUuid() {
		return _officeSite.getUuid();
	}

	/**
	* Returns the website of this office site.
	*
	* @return the website of this office site
	*/
	@Override
	public java.lang.String getWebsite() {
		return _officeSite.getWebsite();
	}

	@Override
	public java.lang.String toString() {
		return _officeSite.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _officeSite.toXmlString();
	}

	/**
	* Returns the create date of this office site.
	*
	* @return the create date of this office site
	*/
	@Override
	public Date getCreateDate() {
		return _officeSite.getCreateDate();
	}

	/**
	* Returns the modified date of this office site.
	*
	* @return the modified date of this office site
	*/
	@Override
	public Date getModifiedDate() {
		return _officeSite.getModifiedDate();
	}

	/**
	* Returns the admin user ID of this office site.
	*
	* @return the admin user ID of this office site
	*/
	@Override
	public long getAdminUserId() {
		return _officeSite.getAdminUserId();
	}

	/**
	* Returns the company ID of this office site.
	*
	* @return the company ID of this office site
	*/
	@Override
	public long getCompanyId() {
		return _officeSite.getCompanyId();
	}

	/**
	* Returns the group ID of this office site.
	*
	* @return the group ID of this office site
	*/
	@Override
	public long getGroupId() {
		return _officeSite.getGroupId();
	}

	/**
	* Returns the logo file entry ID of this office site.
	*
	* @return the logo file entry ID of this office site
	*/
	@Override
	public long getLogoFileEntryId() {
		return _officeSite.getLogoFileEntryId();
	}

	/**
	* Returns the office site ID of this office site.
	*
	* @return the office site ID of this office site
	*/
	@Override
	public long getOfficeSiteId() {
		return _officeSite.getOfficeSiteId();
	}

	/**
	* Returns the primary key of this office site.
	*
	* @return the primary key of this office site
	*/
	@Override
	public long getPrimaryKey() {
		return _officeSite.getPrimaryKey();
	}

	/**
	* Returns the site group ID of this office site.
	*
	* @return the site group ID of this office site
	*/
	@Override
	public long getSiteGroupId() {
		return _officeSite.getSiteGroupId();
	}

	/**
	* Returns the user ID of this office site.
	*
	* @return the user ID of this office site
	*/
	@Override
	public long getUserId() {
		return _officeSite.getUserId();
	}

	@Override
	public void persist() {
		_officeSite.persist();
	}

	/**
	* Sets the address of this office site.
	*
	* @param address the address of this office site
	*/
	@Override
	public void setAddress(java.lang.String address) {
		_officeSite.setAddress(address);
	}

	/**
	* Sets the admin user ID of this office site.
	*
	* @param adminUserId the admin user ID of this office site
	*/
	@Override
	public void setAdminUserId(long adminUserId) {
		_officeSite.setAdminUserId(adminUserId);
	}

	/**
	* Sets the admin user uuid of this office site.
	*
	* @param adminUserUuid the admin user uuid of this office site
	*/
	@Override
	public void setAdminUserUuid(java.lang.String adminUserUuid) {
		_officeSite.setAdminUserUuid(adminUserUuid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_officeSite.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this office site.
	*
	* @param companyId the company ID of this office site
	*/
	@Override
	public void setCompanyId(long companyId) {
		_officeSite.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this office site.
	*
	* @param createDate the create date of this office site
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_officeSite.setCreateDate(createDate);
	}

	/**
	* Sets the email of this office site.
	*
	* @param email the email of this office site
	*/
	@Override
	public void setEmail(java.lang.String email) {
		_officeSite.setEmail(email);
	}

	/**
	* Sets the en name of this office site.
	*
	* @param enName the en name of this office site
	*/
	@Override
	public void setEnName(java.lang.String enName) {
		_officeSite.setEnName(enName);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_officeSite.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_officeSite.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_officeSite.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fax no of this office site.
	*
	* @param faxNo the fax no of this office site
	*/
	@Override
	public void setFaxNo(java.lang.String faxNo) {
		_officeSite.setFaxNo(faxNo);
	}

	/**
	* Sets the gov agency code of this office site.
	*
	* @param govAgencyCode the gov agency code of this office site
	*/
	@Override
	public void setGovAgencyCode(java.lang.String govAgencyCode) {
		_officeSite.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the group ID of this office site.
	*
	* @param groupId the group ID of this office site
	*/
	@Override
	public void setGroupId(long groupId) {
		_officeSite.setGroupId(groupId);
	}

	/**
	* Sets the logo file entry ID of this office site.
	*
	* @param logoFileEntryId the logo file entry ID of this office site
	*/
	@Override
	public void setLogoFileEntryId(long logoFileEntryId) {
		_officeSite.setLogoFileEntryId(logoFileEntryId);
	}

	/**
	* Sets the modified date of this office site.
	*
	* @param modifiedDate the modified date of this office site
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_officeSite.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this office site.
	*
	* @param name the name of this office site
	*/
	@Override
	public void setName(java.lang.String name) {
		_officeSite.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_officeSite.setNew(n);
	}

	/**
	* Sets the office site ID of this office site.
	*
	* @param officeSiteId the office site ID of this office site
	*/
	@Override
	public void setOfficeSiteId(long officeSiteId) {
		_officeSite.setOfficeSiteId(officeSiteId);
	}

	/**
	* Sets the preferences of this office site.
	*
	* @param preferences the preferences of this office site
	*/
	@Override
	public void setPreferences(java.lang.String preferences) {
		_officeSite.setPreferences(preferences);
	}

	/**
	* Sets the primary key of this office site.
	*
	* @param primaryKey the primary key of this office site
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_officeSite.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_officeSite.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the site group ID of this office site.
	*
	* @param siteGroupId the site group ID of this office site
	*/
	@Override
	public void setSiteGroupId(long siteGroupId) {
		_officeSite.setSiteGroupId(siteGroupId);
	}

	/**
	* Sets the tel no of this office site.
	*
	* @param telNo the tel no of this office site
	*/
	@Override
	public void setTelNo(java.lang.String telNo) {
		_officeSite.setTelNo(telNo);
	}

	/**
	* Sets the user ID of this office site.
	*
	* @param userId the user ID of this office site
	*/
	@Override
	public void setUserId(long userId) {
		_officeSite.setUserId(userId);
	}

	/**
	* Sets the user name of this office site.
	*
	* @param userName the user name of this office site
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_officeSite.setUserName(userName);
	}

	/**
	* Sets the user uuid of this office site.
	*
	* @param userUuid the user uuid of this office site
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_officeSite.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this office site.
	*
	* @param uuid the uuid of this office site
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_officeSite.setUuid(uuid);
	}

	/**
	* Sets the website of this office site.
	*
	* @param website the website of this office site
	*/
	@Override
	public void setWebsite(java.lang.String website) {
		_officeSite.setWebsite(website);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfficeSiteWrapper)) {
			return false;
		}

		OfficeSiteWrapper officeSiteWrapper = (OfficeSiteWrapper)obj;

		if (Objects.equals(_officeSite, officeSiteWrapper._officeSite)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _officeSite.getStagedModelType();
	}

	@Override
	public OfficeSite getWrappedModel() {
		return _officeSite;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _officeSite.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _officeSite.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_officeSite.resetOriginalValues();
	}

	private final OfficeSite _officeSite;
}