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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CsdlDcUser}.
 * </p>
 *
 * @author huymq
 * @see CsdlDcUser
 * @generated
 */
@ProviderType
public class CsdlDcUserWrapper implements CsdlDcUser, ModelWrapper<CsdlDcUser> {
	public CsdlDcUserWrapper(CsdlDcUser csdlDcUser) {
		_csdlDcUser = csdlDcUser;
	}

	@Override
	public Class<?> getModelClass() {
		return CsdlDcUser.class;
	}

	@Override
	public String getModelClassName() {
		return CsdlDcUser.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("idDcUser", getIdDcUser());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("govAgencyCodeDvcqg", getGovAgencyCodeDvcqg());
		attributes.put("keyName", getKeyName());
		attributes.put("keyPass", getKeyPass());
		attributes.put("userName", getUserName());
		attributes.put("employeeEmail", getEmployeeEmail());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long idDcUser = (Long)attributes.get("idDcUser");

		if (idDcUser != null) {
			setIdDcUser(idDcUser);
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

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}

		String govAgencyCodeDvcqg = (String)attributes.get("govAgencyCodeDvcqg");

		if (govAgencyCodeDvcqg != null) {
			setGovAgencyCodeDvcqg(govAgencyCodeDvcqg);
		}

		String keyName = (String)attributes.get("keyName");

		if (keyName != null) {
			setKeyName(keyName);
		}

		String keyPass = (String)attributes.get("keyPass");

		if (keyPass != null) {
			setKeyPass(keyPass);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		String employeeEmail = (String)attributes.get("employeeEmail");

		if (employeeEmail != null) {
			setEmployeeEmail(employeeEmail);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new CsdlDcUserWrapper((CsdlDcUser)_csdlDcUser.clone());
	}

	@Override
	public int compareTo(CsdlDcUser csdlDcUser) {
		return _csdlDcUser.compareTo(csdlDcUser);
	}

	/**
	* Returns the company ID of this csdl dc user.
	*
	* @return the company ID of this csdl dc user
	*/
	@Override
	public long getCompanyId() {
		return _csdlDcUser.getCompanyId();
	}

	/**
	* Returns the employee email of this csdl dc user.
	*
	* @return the employee email of this csdl dc user
	*/
	@Override
	public String getEmployeeEmail() {
		return _csdlDcUser.getEmployeeEmail();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _csdlDcUser.getExpandoBridge();
	}

	/**
	* Returns the gov agency code of this csdl dc user.
	*
	* @return the gov agency code of this csdl dc user
	*/
	@Override
	public String getGovAgencyCode() {
		return _csdlDcUser.getGovAgencyCode();
	}

	/**
	* Returns the gov agency code dvcqg of this csdl dc user.
	*
	* @return the gov agency code dvcqg of this csdl dc user
	*/
	@Override
	public String getGovAgencyCodeDvcqg() {
		return _csdlDcUser.getGovAgencyCodeDvcqg();
	}

	/**
	* Returns the group ID of this csdl dc user.
	*
	* @return the group ID of this csdl dc user
	*/
	@Override
	public long getGroupId() {
		return _csdlDcUser.getGroupId();
	}

	/**
	* Returns the id dc user of this csdl dc user.
	*
	* @return the id dc user of this csdl dc user
	*/
	@Override
	public long getIdDcUser() {
		return _csdlDcUser.getIdDcUser();
	}

	/**
	* Returns the key name of this csdl dc user.
	*
	* @return the key name of this csdl dc user
	*/
	@Override
	public String getKeyName() {
		return _csdlDcUser.getKeyName();
	}

	/**
	* Returns the key pass of this csdl dc user.
	*
	* @return the key pass of this csdl dc user
	*/
	@Override
	public String getKeyPass() {
		return _csdlDcUser.getKeyPass();
	}

	/**
	* Returns the primary key of this csdl dc user.
	*
	* @return the primary key of this csdl dc user
	*/
	@Override
	public long getPrimaryKey() {
		return _csdlDcUser.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _csdlDcUser.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this csdl dc user.
	*
	* @return the status of this csdl dc user
	*/
	@Override
	public int getStatus() {
		return _csdlDcUser.getStatus();
	}

	/**
	* Returns the user ID of this csdl dc user.
	*
	* @return the user ID of this csdl dc user
	*/
	@Override
	public long getUserId() {
		return _csdlDcUser.getUserId();
	}

	/**
	* Returns the user name of this csdl dc user.
	*
	* @return the user name of this csdl dc user
	*/
	@Override
	public String getUserName() {
		return _csdlDcUser.getUserName();
	}

	/**
	* Returns the user uuid of this csdl dc user.
	*
	* @return the user uuid of this csdl dc user
	*/
	@Override
	public String getUserUuid() {
		return _csdlDcUser.getUserUuid();
	}

	/**
	* Returns the uuid of this csdl dc user.
	*
	* @return the uuid of this csdl dc user
	*/
	@Override
	public String getUuid() {
		return _csdlDcUser.getUuid();
	}

	@Override
	public int hashCode() {
		return _csdlDcUser.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _csdlDcUser.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _csdlDcUser.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _csdlDcUser.isNew();
	}

	@Override
	public void persist() {
		_csdlDcUser.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_csdlDcUser.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this csdl dc user.
	*
	* @param companyId the company ID of this csdl dc user
	*/
	@Override
	public void setCompanyId(long companyId) {
		_csdlDcUser.setCompanyId(companyId);
	}

	/**
	* Sets the employee email of this csdl dc user.
	*
	* @param employeeEmail the employee email of this csdl dc user
	*/
	@Override
	public void setEmployeeEmail(String employeeEmail) {
		_csdlDcUser.setEmployeeEmail(employeeEmail);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_csdlDcUser.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_csdlDcUser.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_csdlDcUser.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gov agency code of this csdl dc user.
	*
	* @param govAgencyCode the gov agency code of this csdl dc user
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_csdlDcUser.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency code dvcqg of this csdl dc user.
	*
	* @param govAgencyCodeDvcqg the gov agency code dvcqg of this csdl dc user
	*/
	@Override
	public void setGovAgencyCodeDvcqg(String govAgencyCodeDvcqg) {
		_csdlDcUser.setGovAgencyCodeDvcqg(govAgencyCodeDvcqg);
	}

	/**
	* Sets the group ID of this csdl dc user.
	*
	* @param groupId the group ID of this csdl dc user
	*/
	@Override
	public void setGroupId(long groupId) {
		_csdlDcUser.setGroupId(groupId);
	}

	/**
	* Sets the id dc user of this csdl dc user.
	*
	* @param idDcUser the id dc user of this csdl dc user
	*/
	@Override
	public void setIdDcUser(long idDcUser) {
		_csdlDcUser.setIdDcUser(idDcUser);
	}

	/**
	* Sets the key name of this csdl dc user.
	*
	* @param keyName the key name of this csdl dc user
	*/
	@Override
	public void setKeyName(String keyName) {
		_csdlDcUser.setKeyName(keyName);
	}

	/**
	* Sets the key pass of this csdl dc user.
	*
	* @param keyPass the key pass of this csdl dc user
	*/
	@Override
	public void setKeyPass(String keyPass) {
		_csdlDcUser.setKeyPass(keyPass);
	}

	@Override
	public void setNew(boolean n) {
		_csdlDcUser.setNew(n);
	}

	/**
	* Sets the primary key of this csdl dc user.
	*
	* @param primaryKey the primary key of this csdl dc user
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_csdlDcUser.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_csdlDcUser.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this csdl dc user.
	*
	* @param status the status of this csdl dc user
	*/
	@Override
	public void setStatus(int status) {
		_csdlDcUser.setStatus(status);
	}

	/**
	* Sets the user ID of this csdl dc user.
	*
	* @param userId the user ID of this csdl dc user
	*/
	@Override
	public void setUserId(long userId) {
		_csdlDcUser.setUserId(userId);
	}

	/**
	* Sets the user name of this csdl dc user.
	*
	* @param userName the user name of this csdl dc user
	*/
	@Override
	public void setUserName(String userName) {
		_csdlDcUser.setUserName(userName);
	}

	/**
	* Sets the user uuid of this csdl dc user.
	*
	* @param userUuid the user uuid of this csdl dc user
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_csdlDcUser.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this csdl dc user.
	*
	* @param uuid the uuid of this csdl dc user
	*/
	@Override
	public void setUuid(String uuid) {
		_csdlDcUser.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CsdlDcUser> toCacheModel() {
		return _csdlDcUser.toCacheModel();
	}

	@Override
	public CsdlDcUser toEscapedModel() {
		return new CsdlDcUserWrapper(_csdlDcUser.toEscapedModel());
	}

	@Override
	public String toString() {
		return _csdlDcUser.toString();
	}

	@Override
	public CsdlDcUser toUnescapedModel() {
		return new CsdlDcUserWrapper(_csdlDcUser.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _csdlDcUser.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CsdlDcUserWrapper)) {
			return false;
		}

		CsdlDcUserWrapper csdlDcUserWrapper = (CsdlDcUserWrapper)obj;

		if (Objects.equals(_csdlDcUser, csdlDcUserWrapper._csdlDcUser)) {
			return true;
		}

		return false;
	}

	@Override
	public CsdlDcUser getWrappedModel() {
		return _csdlDcUser;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _csdlDcUser.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _csdlDcUser.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_csdlDcUser.resetOriginalValues();
	}

	private final CsdlDcUser _csdlDcUser;
}