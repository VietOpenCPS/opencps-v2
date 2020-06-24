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
 * This class is a wrapper for {@link PhiLePhi}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PhiLePhi
 * @generated
 */
@ProviderType
public class PhiLePhiWrapper implements PhiLePhi, ModelWrapper<PhiLePhi> {
	public PhiLePhiWrapper(PhiLePhi phiLePhi) {
		_phiLePhi = phiLePhi;
	}

	@Override
	public Class<?> getModelClass() {
		return PhiLePhi.class;
	}

	@Override
	public String getModelClassName() {
		return PhiLePhi.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("phiLePhiId", getPhiLePhiId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("loaiPLP", getLoaiPLP());
		attributes.put("maPLP", getMaPLP());
		attributes.put("tenPLP", getTenPLP());
		attributes.put("soTien", getSoTien());
		attributes.put("serviceConfigMappingId", getServiceConfigMappingId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long phiLePhiId = (Long)attributes.get("phiLePhiId");

		if (phiLePhiId != null) {
			setPhiLePhiId(phiLePhiId);
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

		String loaiPLP = (String)attributes.get("loaiPLP");

		if (loaiPLP != null) {
			setLoaiPLP(loaiPLP);
		}

		String maPLP = (String)attributes.get("maPLP");

		if (maPLP != null) {
			setMaPLP(maPLP);
		}

		String tenPLP = (String)attributes.get("tenPLP");

		if (tenPLP != null) {
			setTenPLP(tenPLP);
		}

		Long soTien = (Long)attributes.get("soTien");

		if (soTien != null) {
			setSoTien(soTien);
		}

		Long serviceConfigMappingId = (Long)attributes.get(
				"serviceConfigMappingId");

		if (serviceConfigMappingId != null) {
			setServiceConfigMappingId(serviceConfigMappingId);
		}
	}

	@Override
	public Object clone() {
		return new PhiLePhiWrapper((PhiLePhi)_phiLePhi.clone());
	}

	@Override
	public int compareTo(PhiLePhi phiLePhi) {
		return _phiLePhi.compareTo(phiLePhi);
	}

	/**
	* Returns the company ID of this phi le phi.
	*
	* @return the company ID of this phi le phi
	*/
	@Override
	public long getCompanyId() {
		return _phiLePhi.getCompanyId();
	}

	/**
	* Returns the create date of this phi le phi.
	*
	* @return the create date of this phi le phi
	*/
	@Override
	public Date getCreateDate() {
		return _phiLePhi.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _phiLePhi.getExpandoBridge();
	}

	/**
	* Returns the group ID of this phi le phi.
	*
	* @return the group ID of this phi le phi
	*/
	@Override
	public long getGroupId() {
		return _phiLePhi.getGroupId();
	}

	/**
	* Returns the loai plp of this phi le phi.
	*
	* @return the loai plp of this phi le phi
	*/
	@Override
	public String getLoaiPLP() {
		return _phiLePhi.getLoaiPLP();
	}

	/**
	* Returns the ma plp of this phi le phi.
	*
	* @return the ma plp of this phi le phi
	*/
	@Override
	public String getMaPLP() {
		return _phiLePhi.getMaPLP();
	}

	/**
	* Returns the modified date of this phi le phi.
	*
	* @return the modified date of this phi le phi
	*/
	@Override
	public Date getModifiedDate() {
		return _phiLePhi.getModifiedDate();
	}

	/**
	* Returns the phi le phi ID of this phi le phi.
	*
	* @return the phi le phi ID of this phi le phi
	*/
	@Override
	public long getPhiLePhiId() {
		return _phiLePhi.getPhiLePhiId();
	}

	/**
	* Returns the primary key of this phi le phi.
	*
	* @return the primary key of this phi le phi
	*/
	@Override
	public long getPrimaryKey() {
		return _phiLePhi.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _phiLePhi.getPrimaryKeyObj();
	}

	/**
	* Returns the service config mapping ID of this phi le phi.
	*
	* @return the service config mapping ID of this phi le phi
	*/
	@Override
	public long getServiceConfigMappingId() {
		return _phiLePhi.getServiceConfigMappingId();
	}

	/**
	* Returns the so tien of this phi le phi.
	*
	* @return the so tien of this phi le phi
	*/
	@Override
	public long getSoTien() {
		return _phiLePhi.getSoTien();
	}

	/**
	* Returns the ten plp of this phi le phi.
	*
	* @return the ten plp of this phi le phi
	*/
	@Override
	public String getTenPLP() {
		return _phiLePhi.getTenPLP();
	}

	/**
	* Returns the user ID of this phi le phi.
	*
	* @return the user ID of this phi le phi
	*/
	@Override
	public long getUserId() {
		return _phiLePhi.getUserId();
	}

	/**
	* Returns the user name of this phi le phi.
	*
	* @return the user name of this phi le phi
	*/
	@Override
	public String getUserName() {
		return _phiLePhi.getUserName();
	}

	/**
	* Returns the user uuid of this phi le phi.
	*
	* @return the user uuid of this phi le phi
	*/
	@Override
	public String getUserUuid() {
		return _phiLePhi.getUserUuid();
	}

	/**
	* Returns the uuid of this phi le phi.
	*
	* @return the uuid of this phi le phi
	*/
	@Override
	public String getUuid() {
		return _phiLePhi.getUuid();
	}

	@Override
	public int hashCode() {
		return _phiLePhi.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _phiLePhi.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _phiLePhi.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _phiLePhi.isNew();
	}

	@Override
	public void persist() {
		_phiLePhi.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_phiLePhi.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this phi le phi.
	*
	* @param companyId the company ID of this phi le phi
	*/
	@Override
	public void setCompanyId(long companyId) {
		_phiLePhi.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this phi le phi.
	*
	* @param createDate the create date of this phi le phi
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_phiLePhi.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_phiLePhi.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_phiLePhi.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_phiLePhi.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this phi le phi.
	*
	* @param groupId the group ID of this phi le phi
	*/
	@Override
	public void setGroupId(long groupId) {
		_phiLePhi.setGroupId(groupId);
	}

	/**
	* Sets the loai plp of this phi le phi.
	*
	* @param loaiPLP the loai plp of this phi le phi
	*/
	@Override
	public void setLoaiPLP(String loaiPLP) {
		_phiLePhi.setLoaiPLP(loaiPLP);
	}

	/**
	* Sets the ma plp of this phi le phi.
	*
	* @param maPLP the ma plp of this phi le phi
	*/
	@Override
	public void setMaPLP(String maPLP) {
		_phiLePhi.setMaPLP(maPLP);
	}

	/**
	* Sets the modified date of this phi le phi.
	*
	* @param modifiedDate the modified date of this phi le phi
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_phiLePhi.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_phiLePhi.setNew(n);
	}

	/**
	* Sets the phi le phi ID of this phi le phi.
	*
	* @param phiLePhiId the phi le phi ID of this phi le phi
	*/
	@Override
	public void setPhiLePhiId(long phiLePhiId) {
		_phiLePhi.setPhiLePhiId(phiLePhiId);
	}

	/**
	* Sets the primary key of this phi le phi.
	*
	* @param primaryKey the primary key of this phi le phi
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_phiLePhi.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_phiLePhi.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service config mapping ID of this phi le phi.
	*
	* @param serviceConfigMappingId the service config mapping ID of this phi le phi
	*/
	@Override
	public void setServiceConfigMappingId(long serviceConfigMappingId) {
		_phiLePhi.setServiceConfigMappingId(serviceConfigMappingId);
	}

	/**
	* Sets the so tien of this phi le phi.
	*
	* @param soTien the so tien of this phi le phi
	*/
	@Override
	public void setSoTien(long soTien) {
		_phiLePhi.setSoTien(soTien);
	}

	/**
	* Sets the ten plp of this phi le phi.
	*
	* @param tenPLP the ten plp of this phi le phi
	*/
	@Override
	public void setTenPLP(String tenPLP) {
		_phiLePhi.setTenPLP(tenPLP);
	}

	/**
	* Sets the user ID of this phi le phi.
	*
	* @param userId the user ID of this phi le phi
	*/
	@Override
	public void setUserId(long userId) {
		_phiLePhi.setUserId(userId);
	}

	/**
	* Sets the user name of this phi le phi.
	*
	* @param userName the user name of this phi le phi
	*/
	@Override
	public void setUserName(String userName) {
		_phiLePhi.setUserName(userName);
	}

	/**
	* Sets the user uuid of this phi le phi.
	*
	* @param userUuid the user uuid of this phi le phi
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_phiLePhi.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this phi le phi.
	*
	* @param uuid the uuid of this phi le phi
	*/
	@Override
	public void setUuid(String uuid) {
		_phiLePhi.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PhiLePhi> toCacheModel() {
		return _phiLePhi.toCacheModel();
	}

	@Override
	public PhiLePhi toEscapedModel() {
		return new PhiLePhiWrapper(_phiLePhi.toEscapedModel());
	}

	@Override
	public String toString() {
		return _phiLePhi.toString();
	}

	@Override
	public PhiLePhi toUnescapedModel() {
		return new PhiLePhiWrapper(_phiLePhi.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _phiLePhi.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PhiLePhiWrapper)) {
			return false;
		}

		PhiLePhiWrapper phiLePhiWrapper = (PhiLePhiWrapper)obj;

		if (Objects.equals(_phiLePhi, phiLePhiWrapper._phiLePhi)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _phiLePhi.getStagedModelType();
	}

	@Override
	public PhiLePhi getWrappedModel() {
		return _phiLePhi;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _phiLePhi.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _phiLePhi.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_phiLePhi.resetOriginalValues();
	}

	private final PhiLePhi _phiLePhi;
}