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

package pay.gate.integration.dvc.model;

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
 * This class is a wrapper for {@link ApdungDVC}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApdungDVC
 * @generated
 */
@ProviderType
public class ApdungDVCWrapper implements ApdungDVC, ModelWrapper<ApdungDVC> {
	public ApdungDVCWrapper(ApdungDVC apdungDVC) {
		_apdungDVC = apdungDVC;
	}

	@Override
	public Class<?> getModelClass() {
		return ApdungDVC.class;
	}

	@Override
	public String getModelClassName() {
		return ApdungDVC.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("apdungDVCId", getApdungDVCId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("maTTHC", getMaTTHC());
		attributes.put("maCQTH", getMaCQTH());
		attributes.put("mucdo", getMucdo());
		attributes.put("serviceConfigMappingId", getServiceConfigMappingId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long apdungDVCId = (Long)attributes.get("apdungDVCId");

		if (apdungDVCId != null) {
			setApdungDVCId(apdungDVCId);
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

		String maTTHC = (String)attributes.get("maTTHC");

		if (maTTHC != null) {
			setMaTTHC(maTTHC);
		}

		String maCQTH = (String)attributes.get("maCQTH");

		if (maCQTH != null) {
			setMaCQTH(maCQTH);
		}

		Integer mucdo = (Integer)attributes.get("mucdo");

		if (mucdo != null) {
			setMucdo(mucdo);
		}

		Long serviceConfigMappingId = (Long)attributes.get(
				"serviceConfigMappingId");

		if (serviceConfigMappingId != null) {
			setServiceConfigMappingId(serviceConfigMappingId);
		}
	}

	@Override
	public Object clone() {
		return new ApdungDVCWrapper((ApdungDVC)_apdungDVC.clone());
	}

	@Override
	public int compareTo(ApdungDVC apdungDVC) {
		return _apdungDVC.compareTo(apdungDVC);
	}

	/**
	* Returns the apdung dvc ID of this apdung dvc.
	*
	* @return the apdung dvc ID of this apdung dvc
	*/
	@Override
	public long getApdungDVCId() {
		return _apdungDVC.getApdungDVCId();
	}

	/**
	* Returns the company ID of this apdung dvc.
	*
	* @return the company ID of this apdung dvc
	*/
	@Override
	public long getCompanyId() {
		return _apdungDVC.getCompanyId();
	}

	/**
	* Returns the create date of this apdung dvc.
	*
	* @return the create date of this apdung dvc
	*/
	@Override
	public Date getCreateDate() {
		return _apdungDVC.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _apdungDVC.getExpandoBridge();
	}

	/**
	* Returns the group ID of this apdung dvc.
	*
	* @return the group ID of this apdung dvc
	*/
	@Override
	public long getGroupId() {
		return _apdungDVC.getGroupId();
	}

	/**
	* Returns the ma cqth of this apdung dvc.
	*
	* @return the ma cqth of this apdung dvc
	*/
	@Override
	public String getMaCQTH() {
		return _apdungDVC.getMaCQTH();
	}

	/**
	* Returns the ma tthc of this apdung dvc.
	*
	* @return the ma tthc of this apdung dvc
	*/
	@Override
	public String getMaTTHC() {
		return _apdungDVC.getMaTTHC();
	}

	/**
	* Returns the modified date of this apdung dvc.
	*
	* @return the modified date of this apdung dvc
	*/
	@Override
	public Date getModifiedDate() {
		return _apdungDVC.getModifiedDate();
	}

	/**
	* Returns the mucdo of this apdung dvc.
	*
	* @return the mucdo of this apdung dvc
	*/
	@Override
	public int getMucdo() {
		return _apdungDVC.getMucdo();
	}

	/**
	* Returns the primary key of this apdung dvc.
	*
	* @return the primary key of this apdung dvc
	*/
	@Override
	public long getPrimaryKey() {
		return _apdungDVC.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _apdungDVC.getPrimaryKeyObj();
	}

	/**
	* Returns the service config mapping ID of this apdung dvc.
	*
	* @return the service config mapping ID of this apdung dvc
	*/
	@Override
	public long getServiceConfigMappingId() {
		return _apdungDVC.getServiceConfigMappingId();
	}

	/**
	* Returns the user ID of this apdung dvc.
	*
	* @return the user ID of this apdung dvc
	*/
	@Override
	public long getUserId() {
		return _apdungDVC.getUserId();
	}

	/**
	* Returns the user name of this apdung dvc.
	*
	* @return the user name of this apdung dvc
	*/
	@Override
	public String getUserName() {
		return _apdungDVC.getUserName();
	}

	/**
	* Returns the user uuid of this apdung dvc.
	*
	* @return the user uuid of this apdung dvc
	*/
	@Override
	public String getUserUuid() {
		return _apdungDVC.getUserUuid();
	}

	/**
	* Returns the uuid of this apdung dvc.
	*
	* @return the uuid of this apdung dvc
	*/
	@Override
	public String getUuid() {
		return _apdungDVC.getUuid();
	}

	@Override
	public int hashCode() {
		return _apdungDVC.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _apdungDVC.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _apdungDVC.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _apdungDVC.isNew();
	}

	@Override
	public void persist() {
		_apdungDVC.persist();
	}

	/**
	* Sets the apdung dvc ID of this apdung dvc.
	*
	* @param apdungDVCId the apdung dvc ID of this apdung dvc
	*/
	@Override
	public void setApdungDVCId(long apdungDVCId) {
		_apdungDVC.setApdungDVCId(apdungDVCId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_apdungDVC.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this apdung dvc.
	*
	* @param companyId the company ID of this apdung dvc
	*/
	@Override
	public void setCompanyId(long companyId) {
		_apdungDVC.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this apdung dvc.
	*
	* @param createDate the create date of this apdung dvc
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_apdungDVC.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_apdungDVC.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_apdungDVC.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_apdungDVC.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this apdung dvc.
	*
	* @param groupId the group ID of this apdung dvc
	*/
	@Override
	public void setGroupId(long groupId) {
		_apdungDVC.setGroupId(groupId);
	}

	/**
	* Sets the ma cqth of this apdung dvc.
	*
	* @param maCQTH the ma cqth of this apdung dvc
	*/
	@Override
	public void setMaCQTH(String maCQTH) {
		_apdungDVC.setMaCQTH(maCQTH);
	}

	/**
	* Sets the ma tthc of this apdung dvc.
	*
	* @param maTTHC the ma tthc of this apdung dvc
	*/
	@Override
	public void setMaTTHC(String maTTHC) {
		_apdungDVC.setMaTTHC(maTTHC);
	}

	/**
	* Sets the modified date of this apdung dvc.
	*
	* @param modifiedDate the modified date of this apdung dvc
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_apdungDVC.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the mucdo of this apdung dvc.
	*
	* @param mucdo the mucdo of this apdung dvc
	*/
	@Override
	public void setMucdo(int mucdo) {
		_apdungDVC.setMucdo(mucdo);
	}

	@Override
	public void setNew(boolean n) {
		_apdungDVC.setNew(n);
	}

	/**
	* Sets the primary key of this apdung dvc.
	*
	* @param primaryKey the primary key of this apdung dvc
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_apdungDVC.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_apdungDVC.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service config mapping ID of this apdung dvc.
	*
	* @param serviceConfigMappingId the service config mapping ID of this apdung dvc
	*/
	@Override
	public void setServiceConfigMappingId(long serviceConfigMappingId) {
		_apdungDVC.setServiceConfigMappingId(serviceConfigMappingId);
	}

	/**
	* Sets the user ID of this apdung dvc.
	*
	* @param userId the user ID of this apdung dvc
	*/
	@Override
	public void setUserId(long userId) {
		_apdungDVC.setUserId(userId);
	}

	/**
	* Sets the user name of this apdung dvc.
	*
	* @param userName the user name of this apdung dvc
	*/
	@Override
	public void setUserName(String userName) {
		_apdungDVC.setUserName(userName);
	}

	/**
	* Sets the user uuid of this apdung dvc.
	*
	* @param userUuid the user uuid of this apdung dvc
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_apdungDVC.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this apdung dvc.
	*
	* @param uuid the uuid of this apdung dvc
	*/
	@Override
	public void setUuid(String uuid) {
		_apdungDVC.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ApdungDVC> toCacheModel() {
		return _apdungDVC.toCacheModel();
	}

	@Override
	public ApdungDVC toEscapedModel() {
		return new ApdungDVCWrapper(_apdungDVC.toEscapedModel());
	}

	@Override
	public String toString() {
		return _apdungDVC.toString();
	}

	@Override
	public ApdungDVC toUnescapedModel() {
		return new ApdungDVCWrapper(_apdungDVC.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _apdungDVC.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApdungDVCWrapper)) {
			return false;
		}

		ApdungDVCWrapper apdungDVCWrapper = (ApdungDVCWrapper)obj;

		if (Objects.equals(_apdungDVC, apdungDVCWrapper._apdungDVC)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _apdungDVC.getStagedModelType();
	}

	@Override
	public ApdungDVC getWrappedModel() {
		return _apdungDVC;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _apdungDVC.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _apdungDVC.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_apdungDVC.resetOriginalValues();
	}

	private final ApdungDVC _apdungDVC;
}