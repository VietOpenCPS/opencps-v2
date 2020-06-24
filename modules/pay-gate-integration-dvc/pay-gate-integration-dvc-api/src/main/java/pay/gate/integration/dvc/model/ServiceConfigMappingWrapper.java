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
 * This class is a wrapper for {@link ServiceConfigMapping}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceConfigMapping
 * @generated
 */
@ProviderType
public class ServiceConfigMappingWrapper implements ServiceConfigMapping,
	ModelWrapper<ServiceConfigMapping> {
	public ServiceConfigMappingWrapper(
		ServiceConfigMapping serviceConfigMapping) {
		_serviceConfigMapping = serviceConfigMapping;
	}

	@Override
	public Class<?> getModelClass() {
		return ServiceConfigMapping.class;
	}

	@Override
	public String getModelClassName() {
		return ServiceConfigMapping.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("serviceConfigMappingId", getServiceConfigMappingId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("maDVC", getMaDVC());
		attributes.put("tenDVC", getTenDVC());
		attributes.put("maTTHC", getMaTTHC());
		attributes.put("tenTTHC", getTenTTHC());
		attributes.put("tenCQBH", getTenCQBH());
		attributes.put("tenLinhVuc", getTenLinhVuc());
		attributes.put("apdungDVC", getApdungDVC());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long serviceConfigMappingId = (Long)attributes.get(
				"serviceConfigMappingId");

		if (serviceConfigMappingId != null) {
			setServiceConfigMappingId(serviceConfigMappingId);
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

		String maDVC = (String)attributes.get("maDVC");

		if (maDVC != null) {
			setMaDVC(maDVC);
		}

		String tenDVC = (String)attributes.get("tenDVC");

		if (tenDVC != null) {
			setTenDVC(tenDVC);
		}

		String maTTHC = (String)attributes.get("maTTHC");

		if (maTTHC != null) {
			setMaTTHC(maTTHC);
		}

		String tenTTHC = (String)attributes.get("tenTTHC");

		if (tenTTHC != null) {
			setTenTTHC(tenTTHC);
		}

		String tenCQBH = (String)attributes.get("tenCQBH");

		if (tenCQBH != null) {
			setTenCQBH(tenCQBH);
		}

		String tenLinhVuc = (String)attributes.get("tenLinhVuc");

		if (tenLinhVuc != null) {
			setTenLinhVuc(tenLinhVuc);
		}

		String apdungDVC = (String)attributes.get("apdungDVC");

		if (apdungDVC != null) {
			setApdungDVC(apdungDVC);
		}
	}

	@Override
	public Object clone() {
		return new ServiceConfigMappingWrapper((ServiceConfigMapping)_serviceConfigMapping.clone());
	}

	@Override
	public int compareTo(ServiceConfigMapping serviceConfigMapping) {
		return _serviceConfigMapping.compareTo(serviceConfigMapping);
	}

	/**
	* Returns the apdung dvc of this service config mapping.
	*
	* @return the apdung dvc of this service config mapping
	*/
	@Override
	public String getApdungDVC() {
		return _serviceConfigMapping.getApdungDVC();
	}

	/**
	* Returns the company ID of this service config mapping.
	*
	* @return the company ID of this service config mapping
	*/
	@Override
	public long getCompanyId() {
		return _serviceConfigMapping.getCompanyId();
	}

	/**
	* Returns the create date of this service config mapping.
	*
	* @return the create date of this service config mapping
	*/
	@Override
	public Date getCreateDate() {
		return _serviceConfigMapping.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _serviceConfigMapping.getExpandoBridge();
	}

	/**
	* Returns the group ID of this service config mapping.
	*
	* @return the group ID of this service config mapping
	*/
	@Override
	public long getGroupId() {
		return _serviceConfigMapping.getGroupId();
	}

	/**
	* Returns the ma dvc of this service config mapping.
	*
	* @return the ma dvc of this service config mapping
	*/
	@Override
	public String getMaDVC() {
		return _serviceConfigMapping.getMaDVC();
	}

	/**
	* Returns the ma tthc of this service config mapping.
	*
	* @return the ma tthc of this service config mapping
	*/
	@Override
	public String getMaTTHC() {
		return _serviceConfigMapping.getMaTTHC();
	}

	/**
	* Returns the modified date of this service config mapping.
	*
	* @return the modified date of this service config mapping
	*/
	@Override
	public Date getModifiedDate() {
		return _serviceConfigMapping.getModifiedDate();
	}

	/**
	* Returns the primary key of this service config mapping.
	*
	* @return the primary key of this service config mapping
	*/
	@Override
	public long getPrimaryKey() {
		return _serviceConfigMapping.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serviceConfigMapping.getPrimaryKeyObj();
	}

	/**
	* Returns the service config mapping ID of this service config mapping.
	*
	* @return the service config mapping ID of this service config mapping
	*/
	@Override
	public long getServiceConfigMappingId() {
		return _serviceConfigMapping.getServiceConfigMappingId();
	}

	/**
	* Returns the ten cqbh of this service config mapping.
	*
	* @return the ten cqbh of this service config mapping
	*/
	@Override
	public String getTenCQBH() {
		return _serviceConfigMapping.getTenCQBH();
	}

	/**
	* Returns the ten dvc of this service config mapping.
	*
	* @return the ten dvc of this service config mapping
	*/
	@Override
	public String getTenDVC() {
		return _serviceConfigMapping.getTenDVC();
	}

	/**
	* Returns the ten linh vuc of this service config mapping.
	*
	* @return the ten linh vuc of this service config mapping
	*/
	@Override
	public String getTenLinhVuc() {
		return _serviceConfigMapping.getTenLinhVuc();
	}

	/**
	* Returns the ten tthc of this service config mapping.
	*
	* @return the ten tthc of this service config mapping
	*/
	@Override
	public String getTenTTHC() {
		return _serviceConfigMapping.getTenTTHC();
	}

	/**
	* Returns the user ID of this service config mapping.
	*
	* @return the user ID of this service config mapping
	*/
	@Override
	public long getUserId() {
		return _serviceConfigMapping.getUserId();
	}

	/**
	* Returns the user name of this service config mapping.
	*
	* @return the user name of this service config mapping
	*/
	@Override
	public String getUserName() {
		return _serviceConfigMapping.getUserName();
	}

	/**
	* Returns the user uuid of this service config mapping.
	*
	* @return the user uuid of this service config mapping
	*/
	@Override
	public String getUserUuid() {
		return _serviceConfigMapping.getUserUuid();
	}

	/**
	* Returns the uuid of this service config mapping.
	*
	* @return the uuid of this service config mapping
	*/
	@Override
	public String getUuid() {
		return _serviceConfigMapping.getUuid();
	}

	@Override
	public int hashCode() {
		return _serviceConfigMapping.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _serviceConfigMapping.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _serviceConfigMapping.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _serviceConfigMapping.isNew();
	}

	@Override
	public void persist() {
		_serviceConfigMapping.persist();
	}

	/**
	* Sets the apdung dvc of this service config mapping.
	*
	* @param apdungDVC the apdung dvc of this service config mapping
	*/
	@Override
	public void setApdungDVC(String apdungDVC) {
		_serviceConfigMapping.setApdungDVC(apdungDVC);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_serviceConfigMapping.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this service config mapping.
	*
	* @param companyId the company ID of this service config mapping
	*/
	@Override
	public void setCompanyId(long companyId) {
		_serviceConfigMapping.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this service config mapping.
	*
	* @param createDate the create date of this service config mapping
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_serviceConfigMapping.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_serviceConfigMapping.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_serviceConfigMapping.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_serviceConfigMapping.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this service config mapping.
	*
	* @param groupId the group ID of this service config mapping
	*/
	@Override
	public void setGroupId(long groupId) {
		_serviceConfigMapping.setGroupId(groupId);
	}

	/**
	* Sets the ma dvc of this service config mapping.
	*
	* @param maDVC the ma dvc of this service config mapping
	*/
	@Override
	public void setMaDVC(String maDVC) {
		_serviceConfigMapping.setMaDVC(maDVC);
	}

	/**
	* Sets the ma tthc of this service config mapping.
	*
	* @param maTTHC the ma tthc of this service config mapping
	*/
	@Override
	public void setMaTTHC(String maTTHC) {
		_serviceConfigMapping.setMaTTHC(maTTHC);
	}

	/**
	* Sets the modified date of this service config mapping.
	*
	* @param modifiedDate the modified date of this service config mapping
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_serviceConfigMapping.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_serviceConfigMapping.setNew(n);
	}

	/**
	* Sets the primary key of this service config mapping.
	*
	* @param primaryKey the primary key of this service config mapping
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_serviceConfigMapping.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_serviceConfigMapping.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service config mapping ID of this service config mapping.
	*
	* @param serviceConfigMappingId the service config mapping ID of this service config mapping
	*/
	@Override
	public void setServiceConfigMappingId(long serviceConfigMappingId) {
		_serviceConfigMapping.setServiceConfigMappingId(serviceConfigMappingId);
	}

	/**
	* Sets the ten cqbh of this service config mapping.
	*
	* @param tenCQBH the ten cqbh of this service config mapping
	*/
	@Override
	public void setTenCQBH(String tenCQBH) {
		_serviceConfigMapping.setTenCQBH(tenCQBH);
	}

	/**
	* Sets the ten dvc of this service config mapping.
	*
	* @param tenDVC the ten dvc of this service config mapping
	*/
	@Override
	public void setTenDVC(String tenDVC) {
		_serviceConfigMapping.setTenDVC(tenDVC);
	}

	/**
	* Sets the ten linh vuc of this service config mapping.
	*
	* @param tenLinhVuc the ten linh vuc of this service config mapping
	*/
	@Override
	public void setTenLinhVuc(String tenLinhVuc) {
		_serviceConfigMapping.setTenLinhVuc(tenLinhVuc);
	}

	/**
	* Sets the ten tthc of this service config mapping.
	*
	* @param tenTTHC the ten tthc of this service config mapping
	*/
	@Override
	public void setTenTTHC(String tenTTHC) {
		_serviceConfigMapping.setTenTTHC(tenTTHC);
	}

	/**
	* Sets the user ID of this service config mapping.
	*
	* @param userId the user ID of this service config mapping
	*/
	@Override
	public void setUserId(long userId) {
		_serviceConfigMapping.setUserId(userId);
	}

	/**
	* Sets the user name of this service config mapping.
	*
	* @param userName the user name of this service config mapping
	*/
	@Override
	public void setUserName(String userName) {
		_serviceConfigMapping.setUserName(userName);
	}

	/**
	* Sets the user uuid of this service config mapping.
	*
	* @param userUuid the user uuid of this service config mapping
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_serviceConfigMapping.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this service config mapping.
	*
	* @param uuid the uuid of this service config mapping
	*/
	@Override
	public void setUuid(String uuid) {
		_serviceConfigMapping.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ServiceConfigMapping> toCacheModel() {
		return _serviceConfigMapping.toCacheModel();
	}

	@Override
	public ServiceConfigMapping toEscapedModel() {
		return new ServiceConfigMappingWrapper(_serviceConfigMapping.toEscapedModel());
	}

	@Override
	public String toString() {
		return _serviceConfigMapping.toString();
	}

	@Override
	public ServiceConfigMapping toUnescapedModel() {
		return new ServiceConfigMappingWrapper(_serviceConfigMapping.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _serviceConfigMapping.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceConfigMappingWrapper)) {
			return false;
		}

		ServiceConfigMappingWrapper serviceConfigMappingWrapper = (ServiceConfigMappingWrapper)obj;

		if (Objects.equals(_serviceConfigMapping,
					serviceConfigMappingWrapper._serviceConfigMapping)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _serviceConfigMapping.getStagedModelType();
	}

	@Override
	public ServiceConfigMapping getWrappedModel() {
		return _serviceConfigMapping;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _serviceConfigMapping.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _serviceConfigMapping.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_serviceConfigMapping.resetOriginalValues();
	}

	private final ServiceConfigMapping _serviceConfigMapping;
}