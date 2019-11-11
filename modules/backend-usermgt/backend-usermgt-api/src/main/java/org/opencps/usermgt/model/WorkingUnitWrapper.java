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
 * This class is a wrapper for {@link WorkingUnit}.
 * </p>
 *
 * @author khoavu
 * @see WorkingUnit
 * @generated
 */
@ProviderType
public class WorkingUnitWrapper implements WorkingUnit,
	ModelWrapper<WorkingUnit> {
	public WorkingUnitWrapper(WorkingUnit workingUnit) {
		_workingUnit = workingUnit;
	}

	@Override
	public Class<?> getModelClass() {
		return WorkingUnit.class;
	}

	@Override
	public String getModelClassName() {
		return WorkingUnit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("workingUnitId", getWorkingUnitId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("enName", getEnName());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("parentWorkingUnitId", getParentWorkingUnitId());
		attributes.put("sibling", getSibling());
		attributes.put("treeIndex", getTreeIndex());
		attributes.put("address", getAddress());
		attributes.put("telNo", getTelNo());
		attributes.put("faxNo", getFaxNo());
		attributes.put("email", getEmail());
		attributes.put("website", getWebsite());
		attributes.put("logoFileEntryId", getLogoFileEntryId());
		attributes.put("level", getLevel());
		attributes.put("ceremonyDate", getCeremonyDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long workingUnitId = (Long)attributes.get("workingUnitId");

		if (workingUnitId != null) {
			setWorkingUnitId(workingUnitId);
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

		Long parentWorkingUnitId = (Long)attributes.get("parentWorkingUnitId");

		if (parentWorkingUnitId != null) {
			setParentWorkingUnitId(parentWorkingUnitId);
		}

		String sibling = (String)attributes.get("sibling");

		if (sibling != null) {
			setSibling(sibling);
		}

		String treeIndex = (String)attributes.get("treeIndex");

		if (treeIndex != null) {
			setTreeIndex(treeIndex);
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

		Integer level = (Integer)attributes.get("level");

		if (level != null) {
			setLevel(level);
		}

		Date ceremonyDate = (Date)attributes.get("ceremonyDate");

		if (ceremonyDate != null) {
			setCeremonyDate(ceremonyDate);
		}
	}

	@Override
	public Object clone() {
		return new WorkingUnitWrapper((WorkingUnit)_workingUnit.clone());
	}

	@Override
	public int compareTo(WorkingUnit workingUnit) {
		return _workingUnit.compareTo(workingUnit);
	}

	/**
	* Returns the address of this working unit.
	*
	* @return the address of this working unit
	*/
	@Override
	public String getAddress() {
		return _workingUnit.getAddress();
	}

	/**
	* Returns the ceremony date of this working unit.
	*
	* @return the ceremony date of this working unit
	*/
	@Override
	public Date getCeremonyDate() {
		return _workingUnit.getCeremonyDate();
	}

	/**
	* Returns the company ID of this working unit.
	*
	* @return the company ID of this working unit
	*/
	@Override
	public long getCompanyId() {
		return _workingUnit.getCompanyId();
	}

	/**
	* Returns the create date of this working unit.
	*
	* @return the create date of this working unit
	*/
	@Override
	public Date getCreateDate() {
		return _workingUnit.getCreateDate();
	}

	/**
	* Returns the email of this working unit.
	*
	* @return the email of this working unit
	*/
	@Override
	public String getEmail() {
		return _workingUnit.getEmail();
	}

	/**
	* Returns the en name of this working unit.
	*
	* @return the en name of this working unit
	*/
	@Override
	public String getEnName() {
		return _workingUnit.getEnName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _workingUnit.getExpandoBridge();
	}

	/**
	* Returns the fax no of this working unit.
	*
	* @return the fax no of this working unit
	*/
	@Override
	public String getFaxNo() {
		return _workingUnit.getFaxNo();
	}

	/**
	* Returns the gov agency code of this working unit.
	*
	* @return the gov agency code of this working unit
	*/
	@Override
	public String getGovAgencyCode() {
		return _workingUnit.getGovAgencyCode();
	}

	/**
	* Returns the group ID of this working unit.
	*
	* @return the group ID of this working unit
	*/
	@Override
	public long getGroupId() {
		return _workingUnit.getGroupId();
	}

	/**
	* Returns the level of this working unit.
	*
	* @return the level of this working unit
	*/
	@Override
	public int getLevel() {
		return _workingUnit.getLevel();
	}

	/**
	* Returns the logo file entry ID of this working unit.
	*
	* @return the logo file entry ID of this working unit
	*/
	@Override
	public long getLogoFileEntryId() {
		return _workingUnit.getLogoFileEntryId();
	}

	/**
	* Returns the modified date of this working unit.
	*
	* @return the modified date of this working unit
	*/
	@Override
	public Date getModifiedDate() {
		return _workingUnit.getModifiedDate();
	}

	/**
	* Returns the name of this working unit.
	*
	* @return the name of this working unit
	*/
	@Override
	public String getName() {
		return _workingUnit.getName();
	}

	/**
	* Returns the parent working unit ID of this working unit.
	*
	* @return the parent working unit ID of this working unit
	*/
	@Override
	public long getParentWorkingUnitId() {
		return _workingUnit.getParentWorkingUnitId();
	}

	/**
	* Returns the primary key of this working unit.
	*
	* @return the primary key of this working unit
	*/
	@Override
	public long getPrimaryKey() {
		return _workingUnit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _workingUnit.getPrimaryKeyObj();
	}

	/**
	* Returns the sibling of this working unit.
	*
	* @return the sibling of this working unit
	*/
	@Override
	public String getSibling() {
		return _workingUnit.getSibling();
	}

	/**
	* Returns the tel no of this working unit.
	*
	* @return the tel no of this working unit
	*/
	@Override
	public String getTelNo() {
		return _workingUnit.getTelNo();
	}

	/**
	* Returns the tree index of this working unit.
	*
	* @return the tree index of this working unit
	*/
	@Override
	public String getTreeIndex() {
		return _workingUnit.getTreeIndex();
	}

	/**
	* Returns the user ID of this working unit.
	*
	* @return the user ID of this working unit
	*/
	@Override
	public long getUserId() {
		return _workingUnit.getUserId();
	}

	/**
	* Returns the user name of this working unit.
	*
	* @return the user name of this working unit
	*/
	@Override
	public String getUserName() {
		return _workingUnit.getUserName();
	}

	/**
	* Returns the user uuid of this working unit.
	*
	* @return the user uuid of this working unit
	*/
	@Override
	public String getUserUuid() {
		return _workingUnit.getUserUuid();
	}

	/**
	* Returns the uuid of this working unit.
	*
	* @return the uuid of this working unit
	*/
	@Override
	public String getUuid() {
		return _workingUnit.getUuid();
	}

	/**
	* Returns the website of this working unit.
	*
	* @return the website of this working unit
	*/
	@Override
	public String getWebsite() {
		return _workingUnit.getWebsite();
	}

	/**
	* Returns the working unit ID of this working unit.
	*
	* @return the working unit ID of this working unit
	*/
	@Override
	public long getWorkingUnitId() {
		return _workingUnit.getWorkingUnitId();
	}

	@Override
	public int hashCode() {
		return _workingUnit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _workingUnit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _workingUnit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _workingUnit.isNew();
	}

	@Override
	public void persist() {
		_workingUnit.persist();
	}

	/**
	* Sets the address of this working unit.
	*
	* @param address the address of this working unit
	*/
	@Override
	public void setAddress(String address) {
		_workingUnit.setAddress(address);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_workingUnit.setCachedModel(cachedModel);
	}

	/**
	* Sets the ceremony date of this working unit.
	*
	* @param ceremonyDate the ceremony date of this working unit
	*/
	@Override
	public void setCeremonyDate(Date ceremonyDate) {
		_workingUnit.setCeremonyDate(ceremonyDate);
	}

	/**
	* Sets the company ID of this working unit.
	*
	* @param companyId the company ID of this working unit
	*/
	@Override
	public void setCompanyId(long companyId) {
		_workingUnit.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this working unit.
	*
	* @param createDate the create date of this working unit
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_workingUnit.setCreateDate(createDate);
	}

	/**
	* Sets the email of this working unit.
	*
	* @param email the email of this working unit
	*/
	@Override
	public void setEmail(String email) {
		_workingUnit.setEmail(email);
	}

	/**
	* Sets the en name of this working unit.
	*
	* @param enName the en name of this working unit
	*/
	@Override
	public void setEnName(String enName) {
		_workingUnit.setEnName(enName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_workingUnit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_workingUnit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_workingUnit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fax no of this working unit.
	*
	* @param faxNo the fax no of this working unit
	*/
	@Override
	public void setFaxNo(String faxNo) {
		_workingUnit.setFaxNo(faxNo);
	}

	/**
	* Sets the gov agency code of this working unit.
	*
	* @param govAgencyCode the gov agency code of this working unit
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_workingUnit.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the group ID of this working unit.
	*
	* @param groupId the group ID of this working unit
	*/
	@Override
	public void setGroupId(long groupId) {
		_workingUnit.setGroupId(groupId);
	}

	/**
	* Sets the level of this working unit.
	*
	* @param level the level of this working unit
	*/
	@Override
	public void setLevel(int level) {
		_workingUnit.setLevel(level);
	}

	/**
	* Sets the logo file entry ID of this working unit.
	*
	* @param logoFileEntryId the logo file entry ID of this working unit
	*/
	@Override
	public void setLogoFileEntryId(long logoFileEntryId) {
		_workingUnit.setLogoFileEntryId(logoFileEntryId);
	}

	/**
	* Sets the modified date of this working unit.
	*
	* @param modifiedDate the modified date of this working unit
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_workingUnit.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this working unit.
	*
	* @param name the name of this working unit
	*/
	@Override
	public void setName(String name) {
		_workingUnit.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_workingUnit.setNew(n);
	}

	/**
	* Sets the parent working unit ID of this working unit.
	*
	* @param parentWorkingUnitId the parent working unit ID of this working unit
	*/
	@Override
	public void setParentWorkingUnitId(long parentWorkingUnitId) {
		_workingUnit.setParentWorkingUnitId(parentWorkingUnitId);
	}

	/**
	* Sets the primary key of this working unit.
	*
	* @param primaryKey the primary key of this working unit
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_workingUnit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_workingUnit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sibling of this working unit.
	*
	* @param sibling the sibling of this working unit
	*/
	@Override
	public void setSibling(String sibling) {
		_workingUnit.setSibling(sibling);
	}

	/**
	* Sets the tel no of this working unit.
	*
	* @param telNo the tel no of this working unit
	*/
	@Override
	public void setTelNo(String telNo) {
		_workingUnit.setTelNo(telNo);
	}

	/**
	* Sets the tree index of this working unit.
	*
	* @param treeIndex the tree index of this working unit
	*/
	@Override
	public void setTreeIndex(String treeIndex) {
		_workingUnit.setTreeIndex(treeIndex);
	}

	/**
	* Sets the user ID of this working unit.
	*
	* @param userId the user ID of this working unit
	*/
	@Override
	public void setUserId(long userId) {
		_workingUnit.setUserId(userId);
	}

	/**
	* Sets the user name of this working unit.
	*
	* @param userName the user name of this working unit
	*/
	@Override
	public void setUserName(String userName) {
		_workingUnit.setUserName(userName);
	}

	/**
	* Sets the user uuid of this working unit.
	*
	* @param userUuid the user uuid of this working unit
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_workingUnit.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this working unit.
	*
	* @param uuid the uuid of this working unit
	*/
	@Override
	public void setUuid(String uuid) {
		_workingUnit.setUuid(uuid);
	}

	/**
	* Sets the website of this working unit.
	*
	* @param website the website of this working unit
	*/
	@Override
	public void setWebsite(String website) {
		_workingUnit.setWebsite(website);
	}

	/**
	* Sets the working unit ID of this working unit.
	*
	* @param workingUnitId the working unit ID of this working unit
	*/
	@Override
	public void setWorkingUnitId(long workingUnitId) {
		_workingUnit.setWorkingUnitId(workingUnitId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WorkingUnit> toCacheModel() {
		return _workingUnit.toCacheModel();
	}

	@Override
	public WorkingUnit toEscapedModel() {
		return new WorkingUnitWrapper(_workingUnit.toEscapedModel());
	}

	@Override
	public String toString() {
		return _workingUnit.toString();
	}

	@Override
	public WorkingUnit toUnescapedModel() {
		return new WorkingUnitWrapper(_workingUnit.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _workingUnit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkingUnitWrapper)) {
			return false;
		}

		WorkingUnitWrapper workingUnitWrapper = (WorkingUnitWrapper)obj;

		if (Objects.equals(_workingUnit, workingUnitWrapper._workingUnit)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _workingUnit.getStagedModelType();
	}

	@Override
	public WorkingUnit getWrappedModel() {
		return _workingUnit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _workingUnit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _workingUnit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_workingUnit.resetOriginalValues();
	}

	private final WorkingUnit _workingUnit;
}