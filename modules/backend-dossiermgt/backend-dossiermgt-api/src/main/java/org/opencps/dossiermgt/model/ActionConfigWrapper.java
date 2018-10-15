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
 * This class is a wrapper for {@link ActionConfig}.
 * </p>
 *
 * @author huymq
 * @see ActionConfig
 * @generated
 */
@ProviderType
public class ActionConfigWrapper implements ActionConfig,
	ModelWrapper<ActionConfig> {
	public ActionConfigWrapper(ActionConfig actionConfig) {
		_actionConfig = actionConfig;
	}

	@Override
	public Class<?> getModelClass() {
		return ActionConfig.class;
	}

	@Override
	public String getModelClassName() {
		return ActionConfig.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("actionConfigId", getActionConfigId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("actionCode", getActionCode());
		attributes.put("actionName", getActionName());
		attributes.put("extraForm", isExtraForm());
		attributes.put("formConfig", getFormConfig());
		attributes.put("sampleData", getSampleData());
		attributes.put("insideProcess", isInsideProcess());
		attributes.put("userNote", getUserNote());
		attributes.put("syncType", getSyncType());
		attributes.put("eventType", getEventType());
		attributes.put("infoType", getInfoType());
		attributes.put("pending", isPending());
		attributes.put("rollbackable", isRollbackable());
		attributes.put("notificationType", getNotificationType());
		attributes.put("documentType", getDocumentType());
		attributes.put("mappingAction", getMappingAction());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long actionConfigId = (Long)attributes.get("actionConfigId");

		if (actionConfigId != null) {
			setActionConfigId(actionConfigId);
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

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String actionCode = (String)attributes.get("actionCode");

		if (actionCode != null) {
			setActionCode(actionCode);
		}

		String actionName = (String)attributes.get("actionName");

		if (actionName != null) {
			setActionName(actionName);
		}

		Boolean extraForm = (Boolean)attributes.get("extraForm");

		if (extraForm != null) {
			setExtraForm(extraForm);
		}

		String formConfig = (String)attributes.get("formConfig");

		if (formConfig != null) {
			setFormConfig(formConfig);
		}

		String sampleData = (String)attributes.get("sampleData");

		if (sampleData != null) {
			setSampleData(sampleData);
		}

		Boolean insideProcess = (Boolean)attributes.get("insideProcess");

		if (insideProcess != null) {
			setInsideProcess(insideProcess);
		}

		Integer userNote = (Integer)attributes.get("userNote");

		if (userNote != null) {
			setUserNote(userNote);
		}

		Integer syncType = (Integer)attributes.get("syncType");

		if (syncType != null) {
			setSyncType(syncType);
		}

		Integer eventType = (Integer)attributes.get("eventType");

		if (eventType != null) {
			setEventType(eventType);
		}

		Integer infoType = (Integer)attributes.get("infoType");

		if (infoType != null) {
			setInfoType(infoType);
		}

		Boolean pending = (Boolean)attributes.get("pending");

		if (pending != null) {
			setPending(pending);
		}

		Boolean rollbackable = (Boolean)attributes.get("rollbackable");

		if (rollbackable != null) {
			setRollbackable(rollbackable);
		}

		String notificationType = (String)attributes.get("notificationType");

		if (notificationType != null) {
			setNotificationType(notificationType);
		}

		String documentType = (String)attributes.get("documentType");

		if (documentType != null) {
			setDocumentType(documentType);
		}

		String mappingAction = (String)attributes.get("mappingAction");

		if (mappingAction != null) {
			setMappingAction(mappingAction);
		}
	}

	@Override
	public Object clone() {
		return new ActionConfigWrapper((ActionConfig)_actionConfig.clone());
	}

	@Override
	public int compareTo(ActionConfig actionConfig) {
		return _actionConfig.compareTo(actionConfig);
	}

	/**
	* Returns the action code of this action config.
	*
	* @return the action code of this action config
	*/
	@Override
	public String getActionCode() {
		return _actionConfig.getActionCode();
	}

	/**
	* Returns the action config ID of this action config.
	*
	* @return the action config ID of this action config
	*/
	@Override
	public long getActionConfigId() {
		return _actionConfig.getActionConfigId();
	}

	/**
	* Returns the action name of this action config.
	*
	* @return the action name of this action config
	*/
	@Override
	public String getActionName() {
		return _actionConfig.getActionName();
	}

	/**
	* Returns the company ID of this action config.
	*
	* @return the company ID of this action config
	*/
	@Override
	public long getCompanyId() {
		return _actionConfig.getCompanyId();
	}

	/**
	* Returns the create date of this action config.
	*
	* @return the create date of this action config
	*/
	@Override
	public Date getCreateDate() {
		return _actionConfig.getCreateDate();
	}

	/**
	* Returns the document type of this action config.
	*
	* @return the document type of this action config
	*/
	@Override
	public String getDocumentType() {
		return _actionConfig.getDocumentType();
	}

	/**
	* Returns the event type of this action config.
	*
	* @return the event type of this action config
	*/
	@Override
	public int getEventType() {
		return _actionConfig.getEventType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _actionConfig.getExpandoBridge();
	}

	/**
	* Returns the extra form of this action config.
	*
	* @return the extra form of this action config
	*/
	@Override
	public boolean getExtraForm() {
		return _actionConfig.getExtraForm();
	}

	/**
	* Returns the form config of this action config.
	*
	* @return the form config of this action config
	*/
	@Override
	public String getFormConfig() {
		return _actionConfig.getFormConfig();
	}

	/**
	* Returns the group ID of this action config.
	*
	* @return the group ID of this action config
	*/
	@Override
	public long getGroupId() {
		return _actionConfig.getGroupId();
	}

	/**
	* Returns the info type of this action config.
	*
	* @return the info type of this action config
	*/
	@Override
	public int getInfoType() {
		return _actionConfig.getInfoType();
	}

	/**
	* Returns the inside process of this action config.
	*
	* @return the inside process of this action config
	*/
	@Override
	public boolean getInsideProcess() {
		return _actionConfig.getInsideProcess();
	}

	/**
	* Returns the mapping action of this action config.
	*
	* @return the mapping action of this action config
	*/
	@Override
	public String getMappingAction() {
		return _actionConfig.getMappingAction();
	}

	/**
	* Returns the modified date of this action config.
	*
	* @return the modified date of this action config
	*/
	@Override
	public Date getModifiedDate() {
		return _actionConfig.getModifiedDate();
	}

	/**
	* Returns the notification type of this action config.
	*
	* @return the notification type of this action config
	*/
	@Override
	public String getNotificationType() {
		return _actionConfig.getNotificationType();
	}

	/**
	* Returns the pending of this action config.
	*
	* @return the pending of this action config
	*/
	@Override
	public boolean getPending() {
		return _actionConfig.getPending();
	}

	/**
	* Returns the primary key of this action config.
	*
	* @return the primary key of this action config
	*/
	@Override
	public long getPrimaryKey() {
		return _actionConfig.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _actionConfig.getPrimaryKeyObj();
	}

	/**
	* Returns the rollbackable of this action config.
	*
	* @return the rollbackable of this action config
	*/
	@Override
	public boolean getRollbackable() {
		return _actionConfig.getRollbackable();
	}

	/**
	* Returns the sample data of this action config.
	*
	* @return the sample data of this action config
	*/
	@Override
	public String getSampleData() {
		return _actionConfig.getSampleData();
	}

	/**
	* Returns the sync type of this action config.
	*
	* @return the sync type of this action config
	*/
	@Override
	public int getSyncType() {
		return _actionConfig.getSyncType();
	}

	/**
	* Returns the user ID of this action config.
	*
	* @return the user ID of this action config
	*/
	@Override
	public long getUserId() {
		return _actionConfig.getUserId();
	}

	/**
	* Returns the user note of this action config.
	*
	* @return the user note of this action config
	*/
	@Override
	public int getUserNote() {
		return _actionConfig.getUserNote();
	}

	/**
	* Returns the user uuid of this action config.
	*
	* @return the user uuid of this action config
	*/
	@Override
	public String getUserUuid() {
		return _actionConfig.getUserUuid();
	}

	/**
	* Returns the uuid of this action config.
	*
	* @return the uuid of this action config
	*/
	@Override
	public String getUuid() {
		return _actionConfig.getUuid();
	}

	@Override
	public int hashCode() {
		return _actionConfig.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _actionConfig.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _actionConfig.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this action config is extra form.
	*
	* @return <code>true</code> if this action config is extra form; <code>false</code> otherwise
	*/
	@Override
	public boolean isExtraForm() {
		return _actionConfig.isExtraForm();
	}

	/**
	* Returns <code>true</code> if this action config is inside process.
	*
	* @return <code>true</code> if this action config is inside process; <code>false</code> otherwise
	*/
	@Override
	public boolean isInsideProcess() {
		return _actionConfig.isInsideProcess();
	}

	@Override
	public boolean isNew() {
		return _actionConfig.isNew();
	}

	/**
	* Returns <code>true</code> if this action config is pending.
	*
	* @return <code>true</code> if this action config is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _actionConfig.isPending();
	}

	/**
	* Returns <code>true</code> if this action config is rollbackable.
	*
	* @return <code>true</code> if this action config is rollbackable; <code>false</code> otherwise
	*/
	@Override
	public boolean isRollbackable() {
		return _actionConfig.isRollbackable();
	}

	@Override
	public void persist() {
		_actionConfig.persist();
	}

	/**
	* Sets the action code of this action config.
	*
	* @param actionCode the action code of this action config
	*/
	@Override
	public void setActionCode(String actionCode) {
		_actionConfig.setActionCode(actionCode);
	}

	/**
	* Sets the action config ID of this action config.
	*
	* @param actionConfigId the action config ID of this action config
	*/
	@Override
	public void setActionConfigId(long actionConfigId) {
		_actionConfig.setActionConfigId(actionConfigId);
	}

	/**
	* Sets the action name of this action config.
	*
	* @param actionName the action name of this action config
	*/
	@Override
	public void setActionName(String actionName) {
		_actionConfig.setActionName(actionName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_actionConfig.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this action config.
	*
	* @param companyId the company ID of this action config
	*/
	@Override
	public void setCompanyId(long companyId) {
		_actionConfig.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this action config.
	*
	* @param createDate the create date of this action config
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_actionConfig.setCreateDate(createDate);
	}

	/**
	* Sets the document type of this action config.
	*
	* @param documentType the document type of this action config
	*/
	@Override
	public void setDocumentType(String documentType) {
		_actionConfig.setDocumentType(documentType);
	}

	/**
	* Sets the event type of this action config.
	*
	* @param eventType the event type of this action config
	*/
	@Override
	public void setEventType(int eventType) {
		_actionConfig.setEventType(eventType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_actionConfig.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_actionConfig.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_actionConfig.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this action config is extra form.
	*
	* @param extraForm the extra form of this action config
	*/
	@Override
	public void setExtraForm(boolean extraForm) {
		_actionConfig.setExtraForm(extraForm);
	}

	/**
	* Sets the form config of this action config.
	*
	* @param formConfig the form config of this action config
	*/
	@Override
	public void setFormConfig(String formConfig) {
		_actionConfig.setFormConfig(formConfig);
	}

	/**
	* Sets the group ID of this action config.
	*
	* @param groupId the group ID of this action config
	*/
	@Override
	public void setGroupId(long groupId) {
		_actionConfig.setGroupId(groupId);
	}

	/**
	* Sets the info type of this action config.
	*
	* @param infoType the info type of this action config
	*/
	@Override
	public void setInfoType(int infoType) {
		_actionConfig.setInfoType(infoType);
	}

	/**
	* Sets whether this action config is inside process.
	*
	* @param insideProcess the inside process of this action config
	*/
	@Override
	public void setInsideProcess(boolean insideProcess) {
		_actionConfig.setInsideProcess(insideProcess);
	}

	/**
	* Sets the mapping action of this action config.
	*
	* @param mappingAction the mapping action of this action config
	*/
	@Override
	public void setMappingAction(String mappingAction) {
		_actionConfig.setMappingAction(mappingAction);
	}

	/**
	* Sets the modified date of this action config.
	*
	* @param modifiedDate the modified date of this action config
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_actionConfig.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_actionConfig.setNew(n);
	}

	/**
	* Sets the notification type of this action config.
	*
	* @param notificationType the notification type of this action config
	*/
	@Override
	public void setNotificationType(String notificationType) {
		_actionConfig.setNotificationType(notificationType);
	}

	/**
	* Sets whether this action config is pending.
	*
	* @param pending the pending of this action config
	*/
	@Override
	public void setPending(boolean pending) {
		_actionConfig.setPending(pending);
	}

	/**
	* Sets the primary key of this action config.
	*
	* @param primaryKey the primary key of this action config
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_actionConfig.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_actionConfig.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this action config is rollbackable.
	*
	* @param rollbackable the rollbackable of this action config
	*/
	@Override
	public void setRollbackable(boolean rollbackable) {
		_actionConfig.setRollbackable(rollbackable);
	}

	/**
	* Sets the sample data of this action config.
	*
	* @param sampleData the sample data of this action config
	*/
	@Override
	public void setSampleData(String sampleData) {
		_actionConfig.setSampleData(sampleData);
	}

	/**
	* Sets the sync type of this action config.
	*
	* @param syncType the sync type of this action config
	*/
	@Override
	public void setSyncType(int syncType) {
		_actionConfig.setSyncType(syncType);
	}

	/**
	* Sets the user ID of this action config.
	*
	* @param userId the user ID of this action config
	*/
	@Override
	public void setUserId(long userId) {
		_actionConfig.setUserId(userId);
	}

	/**
	* Sets the user note of this action config.
	*
	* @param userNote the user note of this action config
	*/
	@Override
	public void setUserNote(int userNote) {
		_actionConfig.setUserNote(userNote);
	}

	/**
	* Sets the user uuid of this action config.
	*
	* @param userUuid the user uuid of this action config
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_actionConfig.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this action config.
	*
	* @param uuid the uuid of this action config
	*/
	@Override
	public void setUuid(String uuid) {
		_actionConfig.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ActionConfig> toCacheModel() {
		return _actionConfig.toCacheModel();
	}

	@Override
	public ActionConfig toEscapedModel() {
		return new ActionConfigWrapper(_actionConfig.toEscapedModel());
	}

	@Override
	public String toString() {
		return _actionConfig.toString();
	}

	@Override
	public ActionConfig toUnescapedModel() {
		return new ActionConfigWrapper(_actionConfig.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _actionConfig.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ActionConfigWrapper)) {
			return false;
		}

		ActionConfigWrapper actionConfigWrapper = (ActionConfigWrapper)obj;

		if (Objects.equals(_actionConfig, actionConfigWrapper._actionConfig)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _actionConfig.getStagedModelType();
	}

	@Override
	public ActionConfig getWrappedModel() {
		return _actionConfig;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _actionConfig.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _actionConfig.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_actionConfig.resetOriginalValues();
	}

	private final ActionConfig _actionConfig;
}