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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link DossierSync}.
 * </p>
 *
 * @author huymq
 * @see DossierSync
 * @generated
 */
@ProviderType
public class DossierSyncWrapper implements DossierSync,
	ModelWrapper<DossierSync> {
	public DossierSyncWrapper(DossierSync dossierSync) {
		_dossierSync = dossierSync;
	}

	@Override
	public Class<?> getModelClass() {
		return DossierSync.class;
	}

	@Override
	public String getModelClassName() {
		return DossierSync.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("DossierSyncId", getDossierSyncId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierId", getDossierId());
		attributes.put("dossierRefUid", getDossierRefUid());
		attributes.put("syncRefUid", getSyncRefUid());
		attributes.put("dossierActionId", getDossierActionId());
		attributes.put("actionCode", getActionCode());
		attributes.put("actionName", getActionName());
		attributes.put("actionUser", getActionUser());
		attributes.put("actionNote", getActionNote());
		attributes.put("syncType", getSyncType());
		attributes.put("infoType", getInfoType());
		attributes.put("payload", getPayload());
		attributes.put("serverNo", getServerNo());
		attributes.put("state", getState());
		attributes.put("retry", getRetry());
		attributes.put("messageText", getMessageText());
		attributes.put("acknowlegement", getAcknowlegement());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long DossierSyncId = (Long)attributes.get("DossierSyncId");

		if (DossierSyncId != null) {
			setDossierSyncId(DossierSyncId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
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

		String dossierRefUid = (String)attributes.get("dossierRefUid");

		if (dossierRefUid != null) {
			setDossierRefUid(dossierRefUid);
		}

		String syncRefUid = (String)attributes.get("syncRefUid");

		if (syncRefUid != null) {
			setSyncRefUid(syncRefUid);
		}

		Long dossierActionId = (Long)attributes.get("dossierActionId");

		if (dossierActionId != null) {
			setDossierActionId(dossierActionId);
		}

		String actionCode = (String)attributes.get("actionCode");

		if (actionCode != null) {
			setActionCode(actionCode);
		}

		String actionName = (String)attributes.get("actionName");

		if (actionName != null) {
			setActionName(actionName);
		}

		String actionUser = (String)attributes.get("actionUser");

		if (actionUser != null) {
			setActionUser(actionUser);
		}

		String actionNote = (String)attributes.get("actionNote");

		if (actionNote != null) {
			setActionNote(actionNote);
		}

		Integer syncType = (Integer)attributes.get("syncType");

		if (syncType != null) {
			setSyncType(syncType);
		}

		Integer infoType = (Integer)attributes.get("infoType");

		if (infoType != null) {
			setInfoType(infoType);
		}

		String payload = (String)attributes.get("payload");

		if (payload != null) {
			setPayload(payload);
		}

		String serverNo = (String)attributes.get("serverNo");

		if (serverNo != null) {
			setServerNo(serverNo);
		}

		Integer state = (Integer)attributes.get("state");

		if (state != null) {
			setState(state);
		}

		Integer retry = (Integer)attributes.get("retry");

		if (retry != null) {
			setRetry(retry);
		}

		String messageText = (String)attributes.get("messageText");

		if (messageText != null) {
			setMessageText(messageText);
		}

		String acknowlegement = (String)attributes.get("acknowlegement");

		if (acknowlegement != null) {
			setAcknowlegement(acknowlegement);
		}
	}

	@Override
	public Object clone() {
		return new DossierSyncWrapper((DossierSync)_dossierSync.clone());
	}

	@Override
	public int compareTo(DossierSync dossierSync) {
		return _dossierSync.compareTo(dossierSync);
	}

	/**
	* Returns the acknowlegement of this dossier sync.
	*
	* @return the acknowlegement of this dossier sync
	*/
	@Override
	public String getAcknowlegement() {
		return _dossierSync.getAcknowlegement();
	}

	/**
	* Returns the action code of this dossier sync.
	*
	* @return the action code of this dossier sync
	*/
	@Override
	public String getActionCode() {
		return _dossierSync.getActionCode();
	}

	/**
	* Returns the action name of this dossier sync.
	*
	* @return the action name of this dossier sync
	*/
	@Override
	public String getActionName() {
		return _dossierSync.getActionName();
	}

	/**
	* Returns the action note of this dossier sync.
	*
	* @return the action note of this dossier sync
	*/
	@Override
	public String getActionNote() {
		return _dossierSync.getActionNote();
	}

	/**
	* Returns the action user of this dossier sync.
	*
	* @return the action user of this dossier sync
	*/
	@Override
	public String getActionUser() {
		return _dossierSync.getActionUser();
	}

	/**
	* Returns the create date of this dossier sync.
	*
	* @return the create date of this dossier sync
	*/
	@Override
	public Date getCreateDate() {
		return _dossierSync.getCreateDate();
	}

	/**
	* Returns the dossier action ID of this dossier sync.
	*
	* @return the dossier action ID of this dossier sync
	*/
	@Override
	public long getDossierActionId() {
		return _dossierSync.getDossierActionId();
	}

	/**
	* Returns the dossier ID of this dossier sync.
	*
	* @return the dossier ID of this dossier sync
	*/
	@Override
	public long getDossierId() {
		return _dossierSync.getDossierId();
	}

	/**
	* Returns the dossier ref uid of this dossier sync.
	*
	* @return the dossier ref uid of this dossier sync
	*/
	@Override
	public String getDossierRefUid() {
		return _dossierSync.getDossierRefUid();
	}

	/**
	* Returns the dossier sync ID of this dossier sync.
	*
	* @return the dossier sync ID of this dossier sync
	*/
	@Override
	public long getDossierSyncId() {
		return _dossierSync.getDossierSyncId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierSync.getExpandoBridge();
	}

	/**
	* Returns the group ID of this dossier sync.
	*
	* @return the group ID of this dossier sync
	*/
	@Override
	public long getGroupId() {
		return _dossierSync.getGroupId();
	}

	/**
	* Returns the info type of this dossier sync.
	*
	* @return the info type of this dossier sync
	*/
	@Override
	public int getInfoType() {
		return _dossierSync.getInfoType();
	}

	/**
	* Returns the message text of this dossier sync.
	*
	* @return the message text of this dossier sync
	*/
	@Override
	public String getMessageText() {
		return _dossierSync.getMessageText();
	}

	/**
	* Returns the modified date of this dossier sync.
	*
	* @return the modified date of this dossier sync
	*/
	@Override
	public Date getModifiedDate() {
		return _dossierSync.getModifiedDate();
	}

	/**
	* Returns the payload of this dossier sync.
	*
	* @return the payload of this dossier sync
	*/
	@Override
	public String getPayload() {
		return _dossierSync.getPayload();
	}

	/**
	* Returns the primary key of this dossier sync.
	*
	* @return the primary key of this dossier sync
	*/
	@Override
	public long getPrimaryKey() {
		return _dossierSync.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierSync.getPrimaryKeyObj();
	}

	/**
	* Returns the retry of this dossier sync.
	*
	* @return the retry of this dossier sync
	*/
	@Override
	public int getRetry() {
		return _dossierSync.getRetry();
	}

	/**
	* Returns the server no of this dossier sync.
	*
	* @return the server no of this dossier sync
	*/
	@Override
	public String getServerNo() {
		return _dossierSync.getServerNo();
	}

	/**
	* Returns the state of this dossier sync.
	*
	* @return the state of this dossier sync
	*/
	@Override
	public int getState() {
		return _dossierSync.getState();
	}

	/**
	* Returns the sync ref uid of this dossier sync.
	*
	* @return the sync ref uid of this dossier sync
	*/
	@Override
	public String getSyncRefUid() {
		return _dossierSync.getSyncRefUid();
	}

	/**
	* Returns the sync type of this dossier sync.
	*
	* @return the sync type of this dossier sync
	*/
	@Override
	public int getSyncType() {
		return _dossierSync.getSyncType();
	}

	/**
	* Returns the user ID of this dossier sync.
	*
	* @return the user ID of this dossier sync
	*/
	@Override
	public long getUserId() {
		return _dossierSync.getUserId();
	}

	/**
	* Returns the user uuid of this dossier sync.
	*
	* @return the user uuid of this dossier sync
	*/
	@Override
	public String getUserUuid() {
		return _dossierSync.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier sync.
	*
	* @return the uuid of this dossier sync
	*/
	@Override
	public String getUuid() {
		return _dossierSync.getUuid();
	}

	@Override
	public int hashCode() {
		return _dossierSync.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dossierSync.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossierSync.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dossierSync.isNew();
	}

	@Override
	public void persist() {
		_dossierSync.persist();
	}

	/**
	* Sets the acknowlegement of this dossier sync.
	*
	* @param acknowlegement the acknowlegement of this dossier sync
	*/
	@Override
	public void setAcknowlegement(String acknowlegement) {
		_dossierSync.setAcknowlegement(acknowlegement);
	}

	/**
	* Sets the action code of this dossier sync.
	*
	* @param actionCode the action code of this dossier sync
	*/
	@Override
	public void setActionCode(String actionCode) {
		_dossierSync.setActionCode(actionCode);
	}

	/**
	* Sets the action name of this dossier sync.
	*
	* @param actionName the action name of this dossier sync
	*/
	@Override
	public void setActionName(String actionName) {
		_dossierSync.setActionName(actionName);
	}

	/**
	* Sets the action note of this dossier sync.
	*
	* @param actionNote the action note of this dossier sync
	*/
	@Override
	public void setActionNote(String actionNote) {
		_dossierSync.setActionNote(actionNote);
	}

	/**
	* Sets the action user of this dossier sync.
	*
	* @param actionUser the action user of this dossier sync
	*/
	@Override
	public void setActionUser(String actionUser) {
		_dossierSync.setActionUser(actionUser);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossierSync.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this dossier sync.
	*
	* @param createDate the create date of this dossier sync
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dossierSync.setCreateDate(createDate);
	}

	/**
	* Sets the dossier action ID of this dossier sync.
	*
	* @param dossierActionId the dossier action ID of this dossier sync
	*/
	@Override
	public void setDossierActionId(long dossierActionId) {
		_dossierSync.setDossierActionId(dossierActionId);
	}

	/**
	* Sets the dossier ID of this dossier sync.
	*
	* @param dossierId the dossier ID of this dossier sync
	*/
	@Override
	public void setDossierId(long dossierId) {
		_dossierSync.setDossierId(dossierId);
	}

	/**
	* Sets the dossier ref uid of this dossier sync.
	*
	* @param dossierRefUid the dossier ref uid of this dossier sync
	*/
	@Override
	public void setDossierRefUid(String dossierRefUid) {
		_dossierSync.setDossierRefUid(dossierRefUid);
	}

	/**
	* Sets the dossier sync ID of this dossier sync.
	*
	* @param DossierSyncId the dossier sync ID of this dossier sync
	*/
	@Override
	public void setDossierSyncId(long DossierSyncId) {
		_dossierSync.setDossierSyncId(DossierSyncId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierSync.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierSync.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierSync.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dossier sync.
	*
	* @param groupId the group ID of this dossier sync
	*/
	@Override
	public void setGroupId(long groupId) {
		_dossierSync.setGroupId(groupId);
	}

	/**
	* Sets the info type of this dossier sync.
	*
	* @param infoType the info type of this dossier sync
	*/
	@Override
	public void setInfoType(int infoType) {
		_dossierSync.setInfoType(infoType);
	}

	/**
	* Sets the message text of this dossier sync.
	*
	* @param messageText the message text of this dossier sync
	*/
	@Override
	public void setMessageText(String messageText) {
		_dossierSync.setMessageText(messageText);
	}

	/**
	* Sets the modified date of this dossier sync.
	*
	* @param modifiedDate the modified date of this dossier sync
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dossierSync.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dossierSync.setNew(n);
	}

	/**
	* Sets the payload of this dossier sync.
	*
	* @param payload the payload of this dossier sync
	*/
	@Override
	public void setPayload(String payload) {
		_dossierSync.setPayload(payload);
	}

	/**
	* Sets the primary key of this dossier sync.
	*
	* @param primaryKey the primary key of this dossier sync
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dossierSync.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierSync.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the retry of this dossier sync.
	*
	* @param retry the retry of this dossier sync
	*/
	@Override
	public void setRetry(int retry) {
		_dossierSync.setRetry(retry);
	}

	/**
	* Sets the server no of this dossier sync.
	*
	* @param serverNo the server no of this dossier sync
	*/
	@Override
	public void setServerNo(String serverNo) {
		_dossierSync.setServerNo(serverNo);
	}

	/**
	* Sets the state of this dossier sync.
	*
	* @param state the state of this dossier sync
	*/
	@Override
	public void setState(int state) {
		_dossierSync.setState(state);
	}

	/**
	* Sets the sync ref uid of this dossier sync.
	*
	* @param syncRefUid the sync ref uid of this dossier sync
	*/
	@Override
	public void setSyncRefUid(String syncRefUid) {
		_dossierSync.setSyncRefUid(syncRefUid);
	}

	/**
	* Sets the sync type of this dossier sync.
	*
	* @param syncType the sync type of this dossier sync
	*/
	@Override
	public void setSyncType(int syncType) {
		_dossierSync.setSyncType(syncType);
	}

	/**
	* Sets the user ID of this dossier sync.
	*
	* @param userId the user ID of this dossier sync
	*/
	@Override
	public void setUserId(long userId) {
		_dossierSync.setUserId(userId);
	}

	/**
	* Sets the user uuid of this dossier sync.
	*
	* @param userUuid the user uuid of this dossier sync
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dossierSync.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier sync.
	*
	* @param uuid the uuid of this dossier sync
	*/
	@Override
	public void setUuid(String uuid) {
		_dossierSync.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierSync> toCacheModel() {
		return _dossierSync.toCacheModel();
	}

	@Override
	public DossierSync toEscapedModel() {
		return new DossierSyncWrapper(_dossierSync.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dossierSync.toString();
	}

	@Override
	public DossierSync toUnescapedModel() {
		return new DossierSyncWrapper(_dossierSync.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dossierSync.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierSyncWrapper)) {
			return false;
		}

		DossierSyncWrapper dossierSyncWrapper = (DossierSyncWrapper)obj;

		if (Objects.equals(_dossierSync, dossierSyncWrapper._dossierSync)) {
			return true;
		}

		return false;
	}

	@Override
	public DossierSync getWrappedModel() {
		return _dossierSync;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossierSync.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossierSync.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossierSync.resetOriginalValues();
	}

	private final DossierSync _dossierSync;
}