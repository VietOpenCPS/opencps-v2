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
 * This class is a wrapper for {@link CsdlDcServiceInfo}.
 * </p>
 *
 * @author huymq
 * @see CsdlDcServiceInfo
 * @generated
 */
@ProviderType
public class CsdlDcServiceInfoWrapper implements CsdlDcServiceInfo,
	ModelWrapper<CsdlDcServiceInfo> {
	public CsdlDcServiceInfoWrapper(CsdlDcServiceInfo csdlDcServiceInfo) {
		_csdlDcServiceInfo = csdlDcServiceInfo;
	}

	@Override
	public Class<?> getModelClass() {
		return CsdlDcServiceInfo.class;
	}

	@Override
	public String getModelClassName() {
		return CsdlDcServiceInfo.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("idDcService", getIdDcService());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("serviceCode", getServiceCode());
		attributes.put("serviceCodeDvcqg", getServiceCodeDvcqg());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long idDcService = (Long)attributes.get("idDcService");

		if (idDcService != null) {
			setIdDcService(idDcService);
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

		String serviceCode = (String)attributes.get("serviceCode");

		if (serviceCode != null) {
			setServiceCode(serviceCode);
		}

		String serviceCodeDvcqg = (String)attributes.get("serviceCodeDvcqg");

		if (serviceCodeDvcqg != null) {
			setServiceCodeDvcqg(serviceCodeDvcqg);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new CsdlDcServiceInfoWrapper((CsdlDcServiceInfo)_csdlDcServiceInfo.clone());
	}

	@Override
	public int compareTo(CsdlDcServiceInfo csdlDcServiceInfo) {
		return _csdlDcServiceInfo.compareTo(csdlDcServiceInfo);
	}

	/**
	* Returns the company ID of this csdl dc service info.
	*
	* @return the company ID of this csdl dc service info
	*/
	@Override
	public long getCompanyId() {
		return _csdlDcServiceInfo.getCompanyId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _csdlDcServiceInfo.getExpandoBridge();
	}

	/**
	* Returns the group ID of this csdl dc service info.
	*
	* @return the group ID of this csdl dc service info
	*/
	@Override
	public long getGroupId() {
		return _csdlDcServiceInfo.getGroupId();
	}

	/**
	* Returns the id dc service of this csdl dc service info.
	*
	* @return the id dc service of this csdl dc service info
	*/
	@Override
	public long getIdDcService() {
		return _csdlDcServiceInfo.getIdDcService();
	}

	/**
	* Returns the primary key of this csdl dc service info.
	*
	* @return the primary key of this csdl dc service info
	*/
	@Override
	public long getPrimaryKey() {
		return _csdlDcServiceInfo.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _csdlDcServiceInfo.getPrimaryKeyObj();
	}

	/**
	* Returns the service code of this csdl dc service info.
	*
	* @return the service code of this csdl dc service info
	*/
	@Override
	public String getServiceCode() {
		return _csdlDcServiceInfo.getServiceCode();
	}

	/**
	* Returns the service code dvcqg of this csdl dc service info.
	*
	* @return the service code dvcqg of this csdl dc service info
	*/
	@Override
	public String getServiceCodeDvcqg() {
		return _csdlDcServiceInfo.getServiceCodeDvcqg();
	}

	/**
	* Returns the status of this csdl dc service info.
	*
	* @return the status of this csdl dc service info
	*/
	@Override
	public int getStatus() {
		return _csdlDcServiceInfo.getStatus();
	}

	/**
	* Returns the user ID of this csdl dc service info.
	*
	* @return the user ID of this csdl dc service info
	*/
	@Override
	public long getUserId() {
		return _csdlDcServiceInfo.getUserId();
	}

	/**
	* Returns the user uuid of this csdl dc service info.
	*
	* @return the user uuid of this csdl dc service info
	*/
	@Override
	public String getUserUuid() {
		return _csdlDcServiceInfo.getUserUuid();
	}

	/**
	* Returns the uuid of this csdl dc service info.
	*
	* @return the uuid of this csdl dc service info
	*/
	@Override
	public String getUuid() {
		return _csdlDcServiceInfo.getUuid();
	}

	@Override
	public int hashCode() {
		return _csdlDcServiceInfo.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _csdlDcServiceInfo.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _csdlDcServiceInfo.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _csdlDcServiceInfo.isNew();
	}

	@Override
	public void persist() {
		_csdlDcServiceInfo.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_csdlDcServiceInfo.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this csdl dc service info.
	*
	* @param companyId the company ID of this csdl dc service info
	*/
	@Override
	public void setCompanyId(long companyId) {
		_csdlDcServiceInfo.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_csdlDcServiceInfo.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_csdlDcServiceInfo.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_csdlDcServiceInfo.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this csdl dc service info.
	*
	* @param groupId the group ID of this csdl dc service info
	*/
	@Override
	public void setGroupId(long groupId) {
		_csdlDcServiceInfo.setGroupId(groupId);
	}

	/**
	* Sets the id dc service of this csdl dc service info.
	*
	* @param idDcService the id dc service of this csdl dc service info
	*/
	@Override
	public void setIdDcService(long idDcService) {
		_csdlDcServiceInfo.setIdDcService(idDcService);
	}

	@Override
	public void setNew(boolean n) {
		_csdlDcServiceInfo.setNew(n);
	}

	/**
	* Sets the primary key of this csdl dc service info.
	*
	* @param primaryKey the primary key of this csdl dc service info
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_csdlDcServiceInfo.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_csdlDcServiceInfo.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service code of this csdl dc service info.
	*
	* @param serviceCode the service code of this csdl dc service info
	*/
	@Override
	public void setServiceCode(String serviceCode) {
		_csdlDcServiceInfo.setServiceCode(serviceCode);
	}

	/**
	* Sets the service code dvcqg of this csdl dc service info.
	*
	* @param serviceCodeDvcqg the service code dvcqg of this csdl dc service info
	*/
	@Override
	public void setServiceCodeDvcqg(String serviceCodeDvcqg) {
		_csdlDcServiceInfo.setServiceCodeDvcqg(serviceCodeDvcqg);
	}

	/**
	* Sets the status of this csdl dc service info.
	*
	* @param status the status of this csdl dc service info
	*/
	@Override
	public void setStatus(int status) {
		_csdlDcServiceInfo.setStatus(status);
	}

	/**
	* Sets the user ID of this csdl dc service info.
	*
	* @param userId the user ID of this csdl dc service info
	*/
	@Override
	public void setUserId(long userId) {
		_csdlDcServiceInfo.setUserId(userId);
	}

	/**
	* Sets the user uuid of this csdl dc service info.
	*
	* @param userUuid the user uuid of this csdl dc service info
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_csdlDcServiceInfo.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this csdl dc service info.
	*
	* @param uuid the uuid of this csdl dc service info
	*/
	@Override
	public void setUuid(String uuid) {
		_csdlDcServiceInfo.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CsdlDcServiceInfo> toCacheModel() {
		return _csdlDcServiceInfo.toCacheModel();
	}

	@Override
	public CsdlDcServiceInfo toEscapedModel() {
		return new CsdlDcServiceInfoWrapper(_csdlDcServiceInfo.toEscapedModel());
	}

	@Override
	public String toString() {
		return _csdlDcServiceInfo.toString();
	}

	@Override
	public CsdlDcServiceInfo toUnescapedModel() {
		return new CsdlDcServiceInfoWrapper(_csdlDcServiceInfo.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _csdlDcServiceInfo.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CsdlDcServiceInfoWrapper)) {
			return false;
		}

		CsdlDcServiceInfoWrapper csdlDcServiceInfoWrapper = (CsdlDcServiceInfoWrapper)obj;

		if (Objects.equals(_csdlDcServiceInfo,
					csdlDcServiceInfoWrapper._csdlDcServiceInfo)) {
			return true;
		}

		return false;
	}

	@Override
	public CsdlDcServiceInfo getWrappedModel() {
		return _csdlDcServiceInfo;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _csdlDcServiceInfo.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _csdlDcServiceInfo.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_csdlDcServiceInfo.resetOriginalValues();
	}

	private final CsdlDcServiceInfo _csdlDcServiceInfo;
}