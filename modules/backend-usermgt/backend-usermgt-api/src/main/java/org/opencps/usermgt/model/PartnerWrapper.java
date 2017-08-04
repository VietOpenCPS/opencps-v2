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
 * This class is a wrapper for {@link Partner}.
 * </p>
 *
 * @author Binhth
 * @see Partner
 * @generated
 */
@ProviderType
public class PartnerWrapper implements Partner, ModelWrapper<Partner> {
	public PartnerWrapper(Partner partner) {
		_partner = partner;
	}

	@Override
	public Class<?> getModelClass() {
		return Partner.class;
	}

	@Override
	public String getModelClassName() {
		return Partner.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("partnerId", getPartnerId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("address", getAddress());
		attributes.put("telNo", getTelNo());
		attributes.put("faxNo", getFaxNo());
		attributes.put("email", getEmail());
		attributes.put("website", getWebsite());
		attributes.put("partnerClass", getPartnerClass());
		attributes.put("accountUserId", getAccountUserId());
		attributes.put("docFileId", getDocFileId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long partnerId = (Long)attributes.get("partnerId");

		if (partnerId != null) {
			setPartnerId(partnerId);
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

		Integer partnerClass = (Integer)attributes.get("partnerClass");

		if (partnerClass != null) {
			setPartnerClass(partnerClass);
		}

		Long accountUserId = (Long)attributes.get("accountUserId");

		if (accountUserId != null) {
			setAccountUserId(accountUserId);
		}

		String docFileId = (String)attributes.get("docFileId");

		if (docFileId != null) {
			setDocFileId(docFileId);
		}
	}

	@Override
	public Partner toEscapedModel() {
		return new PartnerWrapper(_partner.toEscapedModel());
	}

	@Override
	public Partner toUnescapedModel() {
		return new PartnerWrapper(_partner.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _partner.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _partner.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _partner.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _partner.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Partner> toCacheModel() {
		return _partner.toCacheModel();
	}

	@Override
	public int compareTo(Partner partner) {
		return _partner.compareTo(partner);
	}

	/**
	* Returns the partner class of this partner.
	*
	* @return the partner class of this partner
	*/
	@Override
	public int getPartnerClass() {
		return _partner.getPartnerClass();
	}

	@Override
	public int hashCode() {
		return _partner.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _partner.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PartnerWrapper((Partner)_partner.clone());
	}

	/**
	* Returns the account user uuid of this partner.
	*
	* @return the account user uuid of this partner
	*/
	@Override
	public java.lang.String getAccountUserUuid() {
		return _partner.getAccountUserUuid();
	}

	/**
	* Returns the address of this partner.
	*
	* @return the address of this partner
	*/
	@Override
	public java.lang.String getAddress() {
		return _partner.getAddress();
	}

	/**
	* Returns the doc file ID of this partner.
	*
	* @return the doc file ID of this partner
	*/
	@Override
	public java.lang.String getDocFileId() {
		return _partner.getDocFileId();
	}

	/**
	* Returns the email of this partner.
	*
	* @return the email of this partner
	*/
	@Override
	public java.lang.String getEmail() {
		return _partner.getEmail();
	}

	/**
	* Returns the fax no of this partner.
	*
	* @return the fax no of this partner
	*/
	@Override
	public java.lang.String getFaxNo() {
		return _partner.getFaxNo();
	}

	/**
	* Returns the name of this partner.
	*
	* @return the name of this partner
	*/
	@Override
	public java.lang.String getName() {
		return _partner.getName();
	}

	/**
	* Returns the tel no of this partner.
	*
	* @return the tel no of this partner
	*/
	@Override
	public java.lang.String getTelNo() {
		return _partner.getTelNo();
	}

	/**
	* Returns the user name of this partner.
	*
	* @return the user name of this partner
	*/
	@Override
	public java.lang.String getUserName() {
		return _partner.getUserName();
	}

	/**
	* Returns the user uuid of this partner.
	*
	* @return the user uuid of this partner
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _partner.getUserUuid();
	}

	/**
	* Returns the uuid of this partner.
	*
	* @return the uuid of this partner
	*/
	@Override
	public java.lang.String getUuid() {
		return _partner.getUuid();
	}

	/**
	* Returns the website of this partner.
	*
	* @return the website of this partner
	*/
	@Override
	public java.lang.String getWebsite() {
		return _partner.getWebsite();
	}

	@Override
	public java.lang.String toString() {
		return _partner.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _partner.toXmlString();
	}

	/**
	* Returns the create date of this partner.
	*
	* @return the create date of this partner
	*/
	@Override
	public Date getCreateDate() {
		return _partner.getCreateDate();
	}

	/**
	* Returns the modified date of this partner.
	*
	* @return the modified date of this partner
	*/
	@Override
	public Date getModifiedDate() {
		return _partner.getModifiedDate();
	}

	/**
	* Returns the account user ID of this partner.
	*
	* @return the account user ID of this partner
	*/
	@Override
	public long getAccountUserId() {
		return _partner.getAccountUserId();
	}

	/**
	* Returns the company ID of this partner.
	*
	* @return the company ID of this partner
	*/
	@Override
	public long getCompanyId() {
		return _partner.getCompanyId();
	}

	/**
	* Returns the group ID of this partner.
	*
	* @return the group ID of this partner
	*/
	@Override
	public long getGroupId() {
		return _partner.getGroupId();
	}

	/**
	* Returns the partner ID of this partner.
	*
	* @return the partner ID of this partner
	*/
	@Override
	public long getPartnerId() {
		return _partner.getPartnerId();
	}

	/**
	* Returns the primary key of this partner.
	*
	* @return the primary key of this partner
	*/
	@Override
	public long getPrimaryKey() {
		return _partner.getPrimaryKey();
	}

	/**
	* Returns the user ID of this partner.
	*
	* @return the user ID of this partner
	*/
	@Override
	public long getUserId() {
		return _partner.getUserId();
	}

	@Override
	public void persist() {
		_partner.persist();
	}

	/**
	* Sets the account user ID of this partner.
	*
	* @param accountUserId the account user ID of this partner
	*/
	@Override
	public void setAccountUserId(long accountUserId) {
		_partner.setAccountUserId(accountUserId);
	}

	/**
	* Sets the account user uuid of this partner.
	*
	* @param accountUserUuid the account user uuid of this partner
	*/
	@Override
	public void setAccountUserUuid(java.lang.String accountUserUuid) {
		_partner.setAccountUserUuid(accountUserUuid);
	}

	/**
	* Sets the address of this partner.
	*
	* @param address the address of this partner
	*/
	@Override
	public void setAddress(java.lang.String address) {
		_partner.setAddress(address);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_partner.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this partner.
	*
	* @param companyId the company ID of this partner
	*/
	@Override
	public void setCompanyId(long companyId) {
		_partner.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this partner.
	*
	* @param createDate the create date of this partner
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_partner.setCreateDate(createDate);
	}

	/**
	* Sets the doc file ID of this partner.
	*
	* @param docFileId the doc file ID of this partner
	*/
	@Override
	public void setDocFileId(java.lang.String docFileId) {
		_partner.setDocFileId(docFileId);
	}

	/**
	* Sets the email of this partner.
	*
	* @param email the email of this partner
	*/
	@Override
	public void setEmail(java.lang.String email) {
		_partner.setEmail(email);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_partner.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_partner.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_partner.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fax no of this partner.
	*
	* @param faxNo the fax no of this partner
	*/
	@Override
	public void setFaxNo(java.lang.String faxNo) {
		_partner.setFaxNo(faxNo);
	}

	/**
	* Sets the group ID of this partner.
	*
	* @param groupId the group ID of this partner
	*/
	@Override
	public void setGroupId(long groupId) {
		_partner.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this partner.
	*
	* @param modifiedDate the modified date of this partner
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_partner.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this partner.
	*
	* @param name the name of this partner
	*/
	@Override
	public void setName(java.lang.String name) {
		_partner.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_partner.setNew(n);
	}

	/**
	* Sets the partner class of this partner.
	*
	* @param partnerClass the partner class of this partner
	*/
	@Override
	public void setPartnerClass(int partnerClass) {
		_partner.setPartnerClass(partnerClass);
	}

	/**
	* Sets the partner ID of this partner.
	*
	* @param partnerId the partner ID of this partner
	*/
	@Override
	public void setPartnerId(long partnerId) {
		_partner.setPartnerId(partnerId);
	}

	/**
	* Sets the primary key of this partner.
	*
	* @param primaryKey the primary key of this partner
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_partner.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_partner.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the tel no of this partner.
	*
	* @param telNo the tel no of this partner
	*/
	@Override
	public void setTelNo(java.lang.String telNo) {
		_partner.setTelNo(telNo);
	}

	/**
	* Sets the user ID of this partner.
	*
	* @param userId the user ID of this partner
	*/
	@Override
	public void setUserId(long userId) {
		_partner.setUserId(userId);
	}

	/**
	* Sets the user name of this partner.
	*
	* @param userName the user name of this partner
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_partner.setUserName(userName);
	}

	/**
	* Sets the user uuid of this partner.
	*
	* @param userUuid the user uuid of this partner
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_partner.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this partner.
	*
	* @param uuid the uuid of this partner
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_partner.setUuid(uuid);
	}

	/**
	* Sets the website of this partner.
	*
	* @param website the website of this partner
	*/
	@Override
	public void setWebsite(java.lang.String website) {
		_partner.setWebsite(website);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PartnerWrapper)) {
			return false;
		}

		PartnerWrapper partnerWrapper = (PartnerWrapper)obj;

		if (Objects.equals(_partner, partnerWrapper._partner)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _partner.getStagedModelType();
	}

	@Override
	public Partner getWrappedModel() {
		return _partner;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _partner.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _partner.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_partner.resetOriginalValues();
	}

	private final Partner _partner;
}