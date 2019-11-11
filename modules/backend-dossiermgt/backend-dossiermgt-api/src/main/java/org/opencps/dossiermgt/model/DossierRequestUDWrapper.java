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
 * This class is a wrapper for {@link DossierRequestUD}.
 * </p>
 *
 * @author huymq
 * @see DossierRequestUD
 * @generated
 */
@ProviderType
public class DossierRequestUDWrapper implements DossierRequestUD,
	ModelWrapper<DossierRequestUD> {
	public DossierRequestUDWrapper(DossierRequestUD dossierRequestUD) {
		_dossierRequestUD = dossierRequestUD;
	}

	@Override
	public Class<?> getModelClass() {
		return DossierRequestUD.class;
	}

	@Override
	public String getModelClassName() {
		return DossierRequestUD.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dossierRequestId", getDossierRequestId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierId", getDossierId());
		attributes.put("referenceUid", getReferenceUid());
		attributes.put("requestType", getRequestType());
		attributes.put("comment", getComment());
		attributes.put("isNew", getIsNew());
		attributes.put("statusReg", getStatusReg());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dossierRequestId = (Long)attributes.get("dossierRequestId");

		if (dossierRequestId != null) {
			setDossierRequestId(dossierRequestId);
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

		Long dossierId = (Long)attributes.get("dossierId");

		if (dossierId != null) {
			setDossierId(dossierId);
		}

		String referenceUid = (String)attributes.get("referenceUid");

		if (referenceUid != null) {
			setReferenceUid(referenceUid);
		}

		String requestType = (String)attributes.get("requestType");

		if (requestType != null) {
			setRequestType(requestType);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}

		Integer isNew = (Integer)attributes.get("isNew");

		if (isNew != null) {
			setIsNew(isNew);
		}

		Integer statusReg = (Integer)attributes.get("statusReg");

		if (statusReg != null) {
			setStatusReg(statusReg);
		}
	}

	@Override
	public Object clone() {
		return new DossierRequestUDWrapper((DossierRequestUD)_dossierRequestUD.clone());
	}

	@Override
	public int compareTo(DossierRequestUD dossierRequestUD) {
		return _dossierRequestUD.compareTo(dossierRequestUD);
	}

	/**
	* Returns the comment of this dossier request ud.
	*
	* @return the comment of this dossier request ud
	*/
	@Override
	public String getComment() {
		return _dossierRequestUD.getComment();
	}

	/**
	* Returns the company ID of this dossier request ud.
	*
	* @return the company ID of this dossier request ud
	*/
	@Override
	public long getCompanyId() {
		return _dossierRequestUD.getCompanyId();
	}

	/**
	* Returns the create date of this dossier request ud.
	*
	* @return the create date of this dossier request ud
	*/
	@Override
	public Date getCreateDate() {
		return _dossierRequestUD.getCreateDate();
	}

	/**
	* Returns the dossier ID of this dossier request ud.
	*
	* @return the dossier ID of this dossier request ud
	*/
	@Override
	public long getDossierId() {
		return _dossierRequestUD.getDossierId();
	}

	/**
	* Returns the dossier request ID of this dossier request ud.
	*
	* @return the dossier request ID of this dossier request ud
	*/
	@Override
	public long getDossierRequestId() {
		return _dossierRequestUD.getDossierRequestId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierRequestUD.getExpandoBridge();
	}

	/**
	* Returns the group ID of this dossier request ud.
	*
	* @return the group ID of this dossier request ud
	*/
	@Override
	public long getGroupId() {
		return _dossierRequestUD.getGroupId();
	}

	/**
	* Returns the is new of this dossier request ud.
	*
	* @return the is new of this dossier request ud
	*/
	@Override
	public int getIsNew() {
		return _dossierRequestUD.getIsNew();
	}

	/**
	* Returns the modified date of this dossier request ud.
	*
	* @return the modified date of this dossier request ud
	*/
	@Override
	public Date getModifiedDate() {
		return _dossierRequestUD.getModifiedDate();
	}

	/**
	* Returns the primary key of this dossier request ud.
	*
	* @return the primary key of this dossier request ud
	*/
	@Override
	public long getPrimaryKey() {
		return _dossierRequestUD.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierRequestUD.getPrimaryKeyObj();
	}

	/**
	* Returns the reference uid of this dossier request ud.
	*
	* @return the reference uid of this dossier request ud
	*/
	@Override
	public String getReferenceUid() {
		return _dossierRequestUD.getReferenceUid();
	}

	/**
	* Returns the request type of this dossier request ud.
	*
	* @return the request type of this dossier request ud
	*/
	@Override
	public String getRequestType() {
		return _dossierRequestUD.getRequestType();
	}

	/**
	* Returns the status reg of this dossier request ud.
	*
	* @return the status reg of this dossier request ud
	*/
	@Override
	public int getStatusReg() {
		return _dossierRequestUD.getStatusReg();
	}

	/**
	* Returns the user ID of this dossier request ud.
	*
	* @return the user ID of this dossier request ud
	*/
	@Override
	public long getUserId() {
		return _dossierRequestUD.getUserId();
	}

	/**
	* Returns the user name of this dossier request ud.
	*
	* @return the user name of this dossier request ud
	*/
	@Override
	public String getUserName() {
		return _dossierRequestUD.getUserName();
	}

	/**
	* Returns the user uuid of this dossier request ud.
	*
	* @return the user uuid of this dossier request ud
	*/
	@Override
	public String getUserUuid() {
		return _dossierRequestUD.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier request ud.
	*
	* @return the uuid of this dossier request ud
	*/
	@Override
	public String getUuid() {
		return _dossierRequestUD.getUuid();
	}

	@Override
	public int hashCode() {
		return _dossierRequestUD.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dossierRequestUD.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossierRequestUD.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dossierRequestUD.isNew();
	}

	@Override
	public void persist() {
		_dossierRequestUD.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossierRequestUD.setCachedModel(cachedModel);
	}

	/**
	* Sets the comment of this dossier request ud.
	*
	* @param comment the comment of this dossier request ud
	*/
	@Override
	public void setComment(String comment) {
		_dossierRequestUD.setComment(comment);
	}

	/**
	* Sets the company ID of this dossier request ud.
	*
	* @param companyId the company ID of this dossier request ud
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dossierRequestUD.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dossier request ud.
	*
	* @param createDate the create date of this dossier request ud
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dossierRequestUD.setCreateDate(createDate);
	}

	/**
	* Sets the dossier ID of this dossier request ud.
	*
	* @param dossierId the dossier ID of this dossier request ud
	*/
	@Override
	public void setDossierId(long dossierId) {
		_dossierRequestUD.setDossierId(dossierId);
	}

	/**
	* Sets the dossier request ID of this dossier request ud.
	*
	* @param dossierRequestId the dossier request ID of this dossier request ud
	*/
	@Override
	public void setDossierRequestId(long dossierRequestId) {
		_dossierRequestUD.setDossierRequestId(dossierRequestId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierRequestUD.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierRequestUD.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierRequestUD.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dossier request ud.
	*
	* @param groupId the group ID of this dossier request ud
	*/
	@Override
	public void setGroupId(long groupId) {
		_dossierRequestUD.setGroupId(groupId);
	}

	/**
	* Sets the is new of this dossier request ud.
	*
	* @param isNew the is new of this dossier request ud
	*/
	@Override
	public void setIsNew(int isNew) {
		_dossierRequestUD.setIsNew(isNew);
	}

	/**
	* Sets the modified date of this dossier request ud.
	*
	* @param modifiedDate the modified date of this dossier request ud
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dossierRequestUD.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dossierRequestUD.setNew(n);
	}

	/**
	* Sets the primary key of this dossier request ud.
	*
	* @param primaryKey the primary key of this dossier request ud
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dossierRequestUD.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierRequestUD.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reference uid of this dossier request ud.
	*
	* @param referenceUid the reference uid of this dossier request ud
	*/
	@Override
	public void setReferenceUid(String referenceUid) {
		_dossierRequestUD.setReferenceUid(referenceUid);
	}

	/**
	* Sets the request type of this dossier request ud.
	*
	* @param requestType the request type of this dossier request ud
	*/
	@Override
	public void setRequestType(String requestType) {
		_dossierRequestUD.setRequestType(requestType);
	}

	/**
	* Sets the status reg of this dossier request ud.
	*
	* @param statusReg the status reg of this dossier request ud
	*/
	@Override
	public void setStatusReg(int statusReg) {
		_dossierRequestUD.setStatusReg(statusReg);
	}

	/**
	* Sets the user ID of this dossier request ud.
	*
	* @param userId the user ID of this dossier request ud
	*/
	@Override
	public void setUserId(long userId) {
		_dossierRequestUD.setUserId(userId);
	}

	/**
	* Sets the user name of this dossier request ud.
	*
	* @param userName the user name of this dossier request ud
	*/
	@Override
	public void setUserName(String userName) {
		_dossierRequestUD.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dossier request ud.
	*
	* @param userUuid the user uuid of this dossier request ud
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dossierRequestUD.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier request ud.
	*
	* @param uuid the uuid of this dossier request ud
	*/
	@Override
	public void setUuid(String uuid) {
		_dossierRequestUD.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierRequestUD> toCacheModel() {
		return _dossierRequestUD.toCacheModel();
	}

	@Override
	public DossierRequestUD toEscapedModel() {
		return new DossierRequestUDWrapper(_dossierRequestUD.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dossierRequestUD.toString();
	}

	@Override
	public DossierRequestUD toUnescapedModel() {
		return new DossierRequestUDWrapper(_dossierRequestUD.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dossierRequestUD.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierRequestUDWrapper)) {
			return false;
		}

		DossierRequestUDWrapper dossierRequestUDWrapper = (DossierRequestUDWrapper)obj;

		if (Objects.equals(_dossierRequestUD,
					dossierRequestUDWrapper._dossierRequestUD)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dossierRequestUD.getStagedModelType();
	}

	@Override
	public DossierRequestUD getWrappedModel() {
		return _dossierRequestUD;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossierRequestUD.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossierRequestUD.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossierRequestUD.resetOriginalValues();
	}

	private final DossierRequestUD _dossierRequestUD;
}