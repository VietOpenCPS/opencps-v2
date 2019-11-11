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
 * This class is a wrapper for {@link DossierActionSync}.
 * </p>
 *
 * @author huymq
 * @see DossierActionSync
 * @generated
 */
@ProviderType
public class DossierActionSyncWrapper implements DossierActionSync,
	ModelWrapper<DossierActionSync> {
	public DossierActionSyncWrapper(DossierActionSync dossierActionSync) {
		_dossierActionSync = dossierActionSync;
	}

	@Override
	public Class<?> getModelClass() {
		return DossierActionSync.class;
	}

	@Override
	public String getModelClassName() {
		return DossierActionSync.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dossierActionSyncId", getDossierActionSyncId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierId", getDossierId());
		attributes.put("dossierActionId", getDossierActionId());
		attributes.put("createDossier", isCreateDossier());
		attributes.put("referenceUid", getReferenceUid());
		attributes.put("actionCode", getActionCode());
		attributes.put("actionUser", getActionUser());
		attributes.put("actionNote", getActionNote());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dossierActionSyncId = (Long)attributes.get("dossierActionSyncId");

		if (dossierActionSyncId != null) {
			setDossierActionSyncId(dossierActionSyncId);
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

		Long dossierActionId = (Long)attributes.get("dossierActionId");

		if (dossierActionId != null) {
			setDossierActionId(dossierActionId);
		}

		Boolean createDossier = (Boolean)attributes.get("createDossier");

		if (createDossier != null) {
			setCreateDossier(createDossier);
		}

		String referenceUid = (String)attributes.get("referenceUid");

		if (referenceUid != null) {
			setReferenceUid(referenceUid);
		}

		String actionCode = (String)attributes.get("actionCode");

		if (actionCode != null) {
			setActionCode(actionCode);
		}

		String actionUser = (String)attributes.get("actionUser");

		if (actionUser != null) {
			setActionUser(actionUser);
		}

		String actionNote = (String)attributes.get("actionNote");

		if (actionNote != null) {
			setActionNote(actionNote);
		}
	}

	@Override
	public Object clone() {
		return new DossierActionSyncWrapper((DossierActionSync)_dossierActionSync.clone());
	}

	@Override
	public int compareTo(DossierActionSync dossierActionSync) {
		return _dossierActionSync.compareTo(dossierActionSync);
	}

	/**
	* Returns the action code of this dossier action sync.
	*
	* @return the action code of this dossier action sync
	*/
	@Override
	public String getActionCode() {
		return _dossierActionSync.getActionCode();
	}

	/**
	* Returns the action note of this dossier action sync.
	*
	* @return the action note of this dossier action sync
	*/
	@Override
	public String getActionNote() {
		return _dossierActionSync.getActionNote();
	}

	/**
	* Returns the action user of this dossier action sync.
	*
	* @return the action user of this dossier action sync
	*/
	@Override
	public String getActionUser() {
		return _dossierActionSync.getActionUser();
	}

	/**
	* Returns the company ID of this dossier action sync.
	*
	* @return the company ID of this dossier action sync
	*/
	@Override
	public long getCompanyId() {
		return _dossierActionSync.getCompanyId();
	}

	/**
	* Returns the create date of this dossier action sync.
	*
	* @return the create date of this dossier action sync
	*/
	@Override
	public Date getCreateDate() {
		return _dossierActionSync.getCreateDate();
	}

	/**
	* Returns the create dossier of this dossier action sync.
	*
	* @return the create dossier of this dossier action sync
	*/
	@Override
	public boolean getCreateDossier() {
		return _dossierActionSync.getCreateDossier();
	}

	/**
	* Returns the dossier action ID of this dossier action sync.
	*
	* @return the dossier action ID of this dossier action sync
	*/
	@Override
	public long getDossierActionId() {
		return _dossierActionSync.getDossierActionId();
	}

	/**
	* Returns the dossier action sync ID of this dossier action sync.
	*
	* @return the dossier action sync ID of this dossier action sync
	*/
	@Override
	public long getDossierActionSyncId() {
		return _dossierActionSync.getDossierActionSyncId();
	}

	/**
	* Returns the dossier ID of this dossier action sync.
	*
	* @return the dossier ID of this dossier action sync
	*/
	@Override
	public long getDossierId() {
		return _dossierActionSync.getDossierId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierActionSync.getExpandoBridge();
	}

	/**
	* Returns the group ID of this dossier action sync.
	*
	* @return the group ID of this dossier action sync
	*/
	@Override
	public long getGroupId() {
		return _dossierActionSync.getGroupId();
	}

	/**
	* Returns the modified date of this dossier action sync.
	*
	* @return the modified date of this dossier action sync
	*/
	@Override
	public Date getModifiedDate() {
		return _dossierActionSync.getModifiedDate();
	}

	/**
	* Returns the primary key of this dossier action sync.
	*
	* @return the primary key of this dossier action sync
	*/
	@Override
	public long getPrimaryKey() {
		return _dossierActionSync.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierActionSync.getPrimaryKeyObj();
	}

	/**
	* Returns the reference uid of this dossier action sync.
	*
	* @return the reference uid of this dossier action sync
	*/
	@Override
	public String getReferenceUid() {
		return _dossierActionSync.getReferenceUid();
	}

	/**
	* Returns the user ID of this dossier action sync.
	*
	* @return the user ID of this dossier action sync
	*/
	@Override
	public long getUserId() {
		return _dossierActionSync.getUserId();
	}

	/**
	* Returns the user name of this dossier action sync.
	*
	* @return the user name of this dossier action sync
	*/
	@Override
	public String getUserName() {
		return _dossierActionSync.getUserName();
	}

	/**
	* Returns the user uuid of this dossier action sync.
	*
	* @return the user uuid of this dossier action sync
	*/
	@Override
	public String getUserUuid() {
		return _dossierActionSync.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier action sync.
	*
	* @return the uuid of this dossier action sync
	*/
	@Override
	public String getUuid() {
		return _dossierActionSync.getUuid();
	}

	@Override
	public int hashCode() {
		return _dossierActionSync.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dossierActionSync.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this dossier action sync is create dossier.
	*
	* @return <code>true</code> if this dossier action sync is create dossier; <code>false</code> otherwise
	*/
	@Override
	public boolean isCreateDossier() {
		return _dossierActionSync.isCreateDossier();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossierActionSync.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dossierActionSync.isNew();
	}

	@Override
	public void persist() {
		_dossierActionSync.persist();
	}

	/**
	* Sets the action code of this dossier action sync.
	*
	* @param actionCode the action code of this dossier action sync
	*/
	@Override
	public void setActionCode(String actionCode) {
		_dossierActionSync.setActionCode(actionCode);
	}

	/**
	* Sets the action note of this dossier action sync.
	*
	* @param actionNote the action note of this dossier action sync
	*/
	@Override
	public void setActionNote(String actionNote) {
		_dossierActionSync.setActionNote(actionNote);
	}

	/**
	* Sets the action user of this dossier action sync.
	*
	* @param actionUser the action user of this dossier action sync
	*/
	@Override
	public void setActionUser(String actionUser) {
		_dossierActionSync.setActionUser(actionUser);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossierActionSync.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dossier action sync.
	*
	* @param companyId the company ID of this dossier action sync
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dossierActionSync.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dossier action sync.
	*
	* @param createDate the create date of this dossier action sync
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dossierActionSync.setCreateDate(createDate);
	}

	/**
	* Sets whether this dossier action sync is create dossier.
	*
	* @param createDossier the create dossier of this dossier action sync
	*/
	@Override
	public void setCreateDossier(boolean createDossier) {
		_dossierActionSync.setCreateDossier(createDossier);
	}

	/**
	* Sets the dossier action ID of this dossier action sync.
	*
	* @param dossierActionId the dossier action ID of this dossier action sync
	*/
	@Override
	public void setDossierActionId(long dossierActionId) {
		_dossierActionSync.setDossierActionId(dossierActionId);
	}

	/**
	* Sets the dossier action sync ID of this dossier action sync.
	*
	* @param dossierActionSyncId the dossier action sync ID of this dossier action sync
	*/
	@Override
	public void setDossierActionSyncId(long dossierActionSyncId) {
		_dossierActionSync.setDossierActionSyncId(dossierActionSyncId);
	}

	/**
	* Sets the dossier ID of this dossier action sync.
	*
	* @param dossierId the dossier ID of this dossier action sync
	*/
	@Override
	public void setDossierId(long dossierId) {
		_dossierActionSync.setDossierId(dossierId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierActionSync.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierActionSync.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierActionSync.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dossier action sync.
	*
	* @param groupId the group ID of this dossier action sync
	*/
	@Override
	public void setGroupId(long groupId) {
		_dossierActionSync.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this dossier action sync.
	*
	* @param modifiedDate the modified date of this dossier action sync
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dossierActionSync.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dossierActionSync.setNew(n);
	}

	/**
	* Sets the primary key of this dossier action sync.
	*
	* @param primaryKey the primary key of this dossier action sync
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dossierActionSync.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierActionSync.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reference uid of this dossier action sync.
	*
	* @param referenceUid the reference uid of this dossier action sync
	*/
	@Override
	public void setReferenceUid(String referenceUid) {
		_dossierActionSync.setReferenceUid(referenceUid);
	}

	/**
	* Sets the user ID of this dossier action sync.
	*
	* @param userId the user ID of this dossier action sync
	*/
	@Override
	public void setUserId(long userId) {
		_dossierActionSync.setUserId(userId);
	}

	/**
	* Sets the user name of this dossier action sync.
	*
	* @param userName the user name of this dossier action sync
	*/
	@Override
	public void setUserName(String userName) {
		_dossierActionSync.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dossier action sync.
	*
	* @param userUuid the user uuid of this dossier action sync
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dossierActionSync.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier action sync.
	*
	* @param uuid the uuid of this dossier action sync
	*/
	@Override
	public void setUuid(String uuid) {
		_dossierActionSync.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierActionSync> toCacheModel() {
		return _dossierActionSync.toCacheModel();
	}

	@Override
	public DossierActionSync toEscapedModel() {
		return new DossierActionSyncWrapper(_dossierActionSync.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dossierActionSync.toString();
	}

	@Override
	public DossierActionSync toUnescapedModel() {
		return new DossierActionSyncWrapper(_dossierActionSync.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dossierActionSync.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierActionSyncWrapper)) {
			return false;
		}

		DossierActionSyncWrapper dossierActionSyncWrapper = (DossierActionSyncWrapper)obj;

		if (Objects.equals(_dossierActionSync,
					dossierActionSyncWrapper._dossierActionSync)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dossierActionSync.getStagedModelType();
	}

	@Override
	public DossierActionSync getWrappedModel() {
		return _dossierActionSync;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossierActionSync.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossierActionSync.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossierActionSync.resetOriginalValues();
	}

	private final DossierActionSync _dossierActionSync;
}