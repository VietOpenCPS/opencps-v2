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
 * This class is a wrapper for {@link DossierAction}.
 * </p>
 *
 * @author huymq
 * @see DossierAction
 * @generated
 */
@ProviderType
public class DossierActionWrapper implements DossierAction,
	ModelWrapper<DossierAction> {
	public DossierActionWrapper(DossierAction dossierAction) {
		_dossierAction = dossierAction;
	}

	@Override
	public Class<?> getModelClass() {
		return DossierAction.class;
	}

	@Override
	public String getModelClassName() {
		return DossierAction.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dossierActionId", getDossierActionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierId", getDossierId());
		attributes.put("serviceProcessId", getServiceProcessId());
		attributes.put("previousActionId", getPreviousActionId());
		attributes.put("fromStepCode", getFromStepCode());
		attributes.put("fromStepName", getFromStepName());
		attributes.put("fromSequenceNo", getFromSequenceNo());
		attributes.put("actionCode", getActionCode());
		attributes.put("actionUser", getActionUser());
		attributes.put("actionName", getActionName());
		attributes.put("actionNote", getActionNote());
		attributes.put("actionOverdue", getActionOverdue());
		attributes.put("syncActionCode", getSyncActionCode());
		attributes.put("pending", isPending());
		attributes.put("rollbackable", isRollbackable());
		attributes.put("stepCode", getStepCode());
		attributes.put("stepName", getStepName());
		attributes.put("sequenceNo", getSequenceNo());
		attributes.put("dueDate", getDueDate());
		attributes.put("nextActionId", getNextActionId());
		attributes.put("payload", getPayload());
		attributes.put("stepInstruction", getStepInstruction());
		attributes.put("state", getState());
		attributes.put("eventStatus", getEventStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dossierActionId = (Long)attributes.get("dossierActionId");

		if (dossierActionId != null) {
			setDossierActionId(dossierActionId);
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

		Long serviceProcessId = (Long)attributes.get("serviceProcessId");

		if (serviceProcessId != null) {
			setServiceProcessId(serviceProcessId);
		}

		Long previousActionId = (Long)attributes.get("previousActionId");

		if (previousActionId != null) {
			setPreviousActionId(previousActionId);
		}

		String fromStepCode = (String)attributes.get("fromStepCode");

		if (fromStepCode != null) {
			setFromStepCode(fromStepCode);
		}

		String fromStepName = (String)attributes.get("fromStepName");

		if (fromStepName != null) {
			setFromStepName(fromStepName);
		}

		String fromSequenceNo = (String)attributes.get("fromSequenceNo");

		if (fromSequenceNo != null) {
			setFromSequenceNo(fromSequenceNo);
		}

		String actionCode = (String)attributes.get("actionCode");

		if (actionCode != null) {
			setActionCode(actionCode);
		}

		String actionUser = (String)attributes.get("actionUser");

		if (actionUser != null) {
			setActionUser(actionUser);
		}

		String actionName = (String)attributes.get("actionName");

		if (actionName != null) {
			setActionName(actionName);
		}

		String actionNote = (String)attributes.get("actionNote");

		if (actionNote != null) {
			setActionNote(actionNote);
		}

		Integer actionOverdue = (Integer)attributes.get("actionOverdue");

		if (actionOverdue != null) {
			setActionOverdue(actionOverdue);
		}

		String syncActionCode = (String)attributes.get("syncActionCode");

		if (syncActionCode != null) {
			setSyncActionCode(syncActionCode);
		}

		Boolean pending = (Boolean)attributes.get("pending");

		if (pending != null) {
			setPending(pending);
		}

		Boolean rollbackable = (Boolean)attributes.get("rollbackable");

		if (rollbackable != null) {
			setRollbackable(rollbackable);
		}

		String stepCode = (String)attributes.get("stepCode");

		if (stepCode != null) {
			setStepCode(stepCode);
		}

		String stepName = (String)attributes.get("stepName");

		if (stepName != null) {
			setStepName(stepName);
		}

		String sequenceNo = (String)attributes.get("sequenceNo");

		if (sequenceNo != null) {
			setSequenceNo(sequenceNo);
		}

		Date dueDate = (Date)attributes.get("dueDate");

		if (dueDate != null) {
			setDueDate(dueDate);
		}

		Long nextActionId = (Long)attributes.get("nextActionId");

		if (nextActionId != null) {
			setNextActionId(nextActionId);
		}

		String payload = (String)attributes.get("payload");

		if (payload != null) {
			setPayload(payload);
		}

		String stepInstruction = (String)attributes.get("stepInstruction");

		if (stepInstruction != null) {
			setStepInstruction(stepInstruction);
		}

		Integer state = (Integer)attributes.get("state");

		if (state != null) {
			setState(state);
		}

		Integer eventStatus = (Integer)attributes.get("eventStatus");

		if (eventStatus != null) {
			setEventStatus(eventStatus);
		}
	}

	@Override
	public Object clone() {
		return new DossierActionWrapper((DossierAction)_dossierAction.clone());
	}

	@Override
	public int compareTo(DossierAction dossierAction) {
		return _dossierAction.compareTo(dossierAction);
	}

	/**
	* Returns the action code of this dossier action.
	*
	* @return the action code of this dossier action
	*/
	@Override
	public String getActionCode() {
		return _dossierAction.getActionCode();
	}

	/**
	* Returns the action name of this dossier action.
	*
	* @return the action name of this dossier action
	*/
	@Override
	public String getActionName() {
		return _dossierAction.getActionName();
	}

	/**
	* Returns the action note of this dossier action.
	*
	* @return the action note of this dossier action
	*/
	@Override
	public String getActionNote() {
		return _dossierAction.getActionNote();
	}

	/**
	* Returns the action overdue of this dossier action.
	*
	* @return the action overdue of this dossier action
	*/
	@Override
	public int getActionOverdue() {
		return _dossierAction.getActionOverdue();
	}

	/**
	* Returns the action user of this dossier action.
	*
	* @return the action user of this dossier action
	*/
	@Override
	public String getActionUser() {
		return _dossierAction.getActionUser();
	}

	/**
	* Returns the company ID of this dossier action.
	*
	* @return the company ID of this dossier action
	*/
	@Override
	public long getCompanyId() {
		return _dossierAction.getCompanyId();
	}

	/**
	* Returns the create date of this dossier action.
	*
	* @return the create date of this dossier action
	*/
	@Override
	public Date getCreateDate() {
		return _dossierAction.getCreateDate();
	}

	/**
	* Returns the dossier action ID of this dossier action.
	*
	* @return the dossier action ID of this dossier action
	*/
	@Override
	public long getDossierActionId() {
		return _dossierAction.getDossierActionId();
	}

	/**
	* Returns the dossier ID of this dossier action.
	*
	* @return the dossier ID of this dossier action
	*/
	@Override
	public long getDossierId() {
		return _dossierAction.getDossierId();
	}

	/**
	* Returns the due date of this dossier action.
	*
	* @return the due date of this dossier action
	*/
	@Override
	public Date getDueDate() {
		return _dossierAction.getDueDate();
	}

	/**
	* Returns the event status of this dossier action.
	*
	* @return the event status of this dossier action
	*/
	@Override
	public int getEventStatus() {
		return _dossierAction.getEventStatus();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierAction.getExpandoBridge();
	}

	/**
	* Returns the from sequence no of this dossier action.
	*
	* @return the from sequence no of this dossier action
	*/
	@Override
	public String getFromSequenceNo() {
		return _dossierAction.getFromSequenceNo();
	}

	/**
	* Returns the from step code of this dossier action.
	*
	* @return the from step code of this dossier action
	*/
	@Override
	public String getFromStepCode() {
		return _dossierAction.getFromStepCode();
	}

	/**
	* Returns the from step name of this dossier action.
	*
	* @return the from step name of this dossier action
	*/
	@Override
	public String getFromStepName() {
		return _dossierAction.getFromStepName();
	}

	/**
	* Returns the group ID of this dossier action.
	*
	* @return the group ID of this dossier action
	*/
	@Override
	public long getGroupId() {
		return _dossierAction.getGroupId();
	}

	/**
	* Returns the modified date of this dossier action.
	*
	* @return the modified date of this dossier action
	*/
	@Override
	public Date getModifiedDate() {
		return _dossierAction.getModifiedDate();
	}

	/**
	* Returns the next action ID of this dossier action.
	*
	* @return the next action ID of this dossier action
	*/
	@Override
	public long getNextActionId() {
		return _dossierAction.getNextActionId();
	}

	/**
	* Returns the payload of this dossier action.
	*
	* @return the payload of this dossier action
	*/
	@Override
	public String getPayload() {
		return _dossierAction.getPayload();
	}

	/**
	* Returns the pending of this dossier action.
	*
	* @return the pending of this dossier action
	*/
	@Override
	public boolean getPending() {
		return _dossierAction.getPending();
	}

	/**
	* Returns the previous action ID of this dossier action.
	*
	* @return the previous action ID of this dossier action
	*/
	@Override
	public long getPreviousActionId() {
		return _dossierAction.getPreviousActionId();
	}

	/**
	* Returns the primary key of this dossier action.
	*
	* @return the primary key of this dossier action
	*/
	@Override
	public long getPrimaryKey() {
		return _dossierAction.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierAction.getPrimaryKeyObj();
	}

	/**
	* Returns the rollbackable of this dossier action.
	*
	* @return the rollbackable of this dossier action
	*/
	@Override
	public boolean getRollbackable() {
		return _dossierAction.getRollbackable();
	}

	/**
	* Returns the sequence no of this dossier action.
	*
	* @return the sequence no of this dossier action
	*/
	@Override
	public String getSequenceNo() {
		return _dossierAction.getSequenceNo();
	}

	/**
	* Returns the service process ID of this dossier action.
	*
	* @return the service process ID of this dossier action
	*/
	@Override
	public long getServiceProcessId() {
		return _dossierAction.getServiceProcessId();
	}

	/**
	* Returns the state of this dossier action.
	*
	* @return the state of this dossier action
	*/
	@Override
	public int getState() {
		return _dossierAction.getState();
	}

	/**
	* Returns the step code of this dossier action.
	*
	* @return the step code of this dossier action
	*/
	@Override
	public String getStepCode() {
		return _dossierAction.getStepCode();
	}

	/**
	* Returns the step instruction of this dossier action.
	*
	* @return the step instruction of this dossier action
	*/
	@Override
	public String getStepInstruction() {
		return _dossierAction.getStepInstruction();
	}

	/**
	* Returns the step name of this dossier action.
	*
	* @return the step name of this dossier action
	*/
	@Override
	public String getStepName() {
		return _dossierAction.getStepName();
	}

	/**
	* Returns the sync action code of this dossier action.
	*
	* @return the sync action code of this dossier action
	*/
	@Override
	public String getSyncActionCode() {
		return _dossierAction.getSyncActionCode();
	}

	/**
	* Returns the user ID of this dossier action.
	*
	* @return the user ID of this dossier action
	*/
	@Override
	public long getUserId() {
		return _dossierAction.getUserId();
	}

	/**
	* Returns the user name of this dossier action.
	*
	* @return the user name of this dossier action
	*/
	@Override
	public String getUserName() {
		return _dossierAction.getUserName();
	}

	/**
	* Returns the user uuid of this dossier action.
	*
	* @return the user uuid of this dossier action
	*/
	@Override
	public String getUserUuid() {
		return _dossierAction.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier action.
	*
	* @return the uuid of this dossier action
	*/
	@Override
	public String getUuid() {
		return _dossierAction.getUuid();
	}

	@Override
	public int hashCode() {
		return _dossierAction.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dossierAction.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossierAction.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dossierAction.isNew();
	}

	/**
	* Returns <code>true</code> if this dossier action is pending.
	*
	* @return <code>true</code> if this dossier action is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _dossierAction.isPending();
	}

	/**
	* Returns <code>true</code> if this dossier action is rollbackable.
	*
	* @return <code>true</code> if this dossier action is rollbackable; <code>false</code> otherwise
	*/
	@Override
	public boolean isRollbackable() {
		return _dossierAction.isRollbackable();
	}

	@Override
	public void persist() {
		_dossierAction.persist();
	}

	/**
	* Sets the action code of this dossier action.
	*
	* @param actionCode the action code of this dossier action
	*/
	@Override
	public void setActionCode(String actionCode) {
		_dossierAction.setActionCode(actionCode);
	}

	/**
	* Sets the action name of this dossier action.
	*
	* @param actionName the action name of this dossier action
	*/
	@Override
	public void setActionName(String actionName) {
		_dossierAction.setActionName(actionName);
	}

	/**
	* Sets the action note of this dossier action.
	*
	* @param actionNote the action note of this dossier action
	*/
	@Override
	public void setActionNote(String actionNote) {
		_dossierAction.setActionNote(actionNote);
	}

	/**
	* Sets the action overdue of this dossier action.
	*
	* @param actionOverdue the action overdue of this dossier action
	*/
	@Override
	public void setActionOverdue(int actionOverdue) {
		_dossierAction.setActionOverdue(actionOverdue);
	}

	/**
	* Sets the action user of this dossier action.
	*
	* @param actionUser the action user of this dossier action
	*/
	@Override
	public void setActionUser(String actionUser) {
		_dossierAction.setActionUser(actionUser);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossierAction.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dossier action.
	*
	* @param companyId the company ID of this dossier action
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dossierAction.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dossier action.
	*
	* @param createDate the create date of this dossier action
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dossierAction.setCreateDate(createDate);
	}

	/**
	* Sets the dossier action ID of this dossier action.
	*
	* @param dossierActionId the dossier action ID of this dossier action
	*/
	@Override
	public void setDossierActionId(long dossierActionId) {
		_dossierAction.setDossierActionId(dossierActionId);
	}

	/**
	* Sets the dossier ID of this dossier action.
	*
	* @param dossierId the dossier ID of this dossier action
	*/
	@Override
	public void setDossierId(long dossierId) {
		_dossierAction.setDossierId(dossierId);
	}

	/**
	* Sets the due date of this dossier action.
	*
	* @param dueDate the due date of this dossier action
	*/
	@Override
	public void setDueDate(Date dueDate) {
		_dossierAction.setDueDate(dueDate);
	}

	/**
	* Sets the event status of this dossier action.
	*
	* @param eventStatus the event status of this dossier action
	*/
	@Override
	public void setEventStatus(int eventStatus) {
		_dossierAction.setEventStatus(eventStatus);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierAction.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierAction.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierAction.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the from sequence no of this dossier action.
	*
	* @param fromSequenceNo the from sequence no of this dossier action
	*/
	@Override
	public void setFromSequenceNo(String fromSequenceNo) {
		_dossierAction.setFromSequenceNo(fromSequenceNo);
	}

	/**
	* Sets the from step code of this dossier action.
	*
	* @param fromStepCode the from step code of this dossier action
	*/
	@Override
	public void setFromStepCode(String fromStepCode) {
		_dossierAction.setFromStepCode(fromStepCode);
	}

	/**
	* Sets the from step name of this dossier action.
	*
	* @param fromStepName the from step name of this dossier action
	*/
	@Override
	public void setFromStepName(String fromStepName) {
		_dossierAction.setFromStepName(fromStepName);
	}

	/**
	* Sets the group ID of this dossier action.
	*
	* @param groupId the group ID of this dossier action
	*/
	@Override
	public void setGroupId(long groupId) {
		_dossierAction.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this dossier action.
	*
	* @param modifiedDate the modified date of this dossier action
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dossierAction.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dossierAction.setNew(n);
	}

	/**
	* Sets the next action ID of this dossier action.
	*
	* @param nextActionId the next action ID of this dossier action
	*/
	@Override
	public void setNextActionId(long nextActionId) {
		_dossierAction.setNextActionId(nextActionId);
	}

	/**
	* Sets the payload of this dossier action.
	*
	* @param payload the payload of this dossier action
	*/
	@Override
	public void setPayload(String payload) {
		_dossierAction.setPayload(payload);
	}

	/**
	* Sets whether this dossier action is pending.
	*
	* @param pending the pending of this dossier action
	*/
	@Override
	public void setPending(boolean pending) {
		_dossierAction.setPending(pending);
	}

	/**
	* Sets the previous action ID of this dossier action.
	*
	* @param previousActionId the previous action ID of this dossier action
	*/
	@Override
	public void setPreviousActionId(long previousActionId) {
		_dossierAction.setPreviousActionId(previousActionId);
	}

	/**
	* Sets the primary key of this dossier action.
	*
	* @param primaryKey the primary key of this dossier action
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dossierAction.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierAction.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this dossier action is rollbackable.
	*
	* @param rollbackable the rollbackable of this dossier action
	*/
	@Override
	public void setRollbackable(boolean rollbackable) {
		_dossierAction.setRollbackable(rollbackable);
	}

	/**
	* Sets the sequence no of this dossier action.
	*
	* @param sequenceNo the sequence no of this dossier action
	*/
	@Override
	public void setSequenceNo(String sequenceNo) {
		_dossierAction.setSequenceNo(sequenceNo);
	}

	/**
	* Sets the service process ID of this dossier action.
	*
	* @param serviceProcessId the service process ID of this dossier action
	*/
	@Override
	public void setServiceProcessId(long serviceProcessId) {
		_dossierAction.setServiceProcessId(serviceProcessId);
	}

	/**
	* Sets the state of this dossier action.
	*
	* @param state the state of this dossier action
	*/
	@Override
	public void setState(int state) {
		_dossierAction.setState(state);
	}

	/**
	* Sets the step code of this dossier action.
	*
	* @param stepCode the step code of this dossier action
	*/
	@Override
	public void setStepCode(String stepCode) {
		_dossierAction.setStepCode(stepCode);
	}

	/**
	* Sets the step instruction of this dossier action.
	*
	* @param stepInstruction the step instruction of this dossier action
	*/
	@Override
	public void setStepInstruction(String stepInstruction) {
		_dossierAction.setStepInstruction(stepInstruction);
	}

	/**
	* Sets the step name of this dossier action.
	*
	* @param stepName the step name of this dossier action
	*/
	@Override
	public void setStepName(String stepName) {
		_dossierAction.setStepName(stepName);
	}

	/**
	* Sets the sync action code of this dossier action.
	*
	* @param syncActionCode the sync action code of this dossier action
	*/
	@Override
	public void setSyncActionCode(String syncActionCode) {
		_dossierAction.setSyncActionCode(syncActionCode);
	}

	/**
	* Sets the user ID of this dossier action.
	*
	* @param userId the user ID of this dossier action
	*/
	@Override
	public void setUserId(long userId) {
		_dossierAction.setUserId(userId);
	}

	/**
	* Sets the user name of this dossier action.
	*
	* @param userName the user name of this dossier action
	*/
	@Override
	public void setUserName(String userName) {
		_dossierAction.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dossier action.
	*
	* @param userUuid the user uuid of this dossier action
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dossierAction.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier action.
	*
	* @param uuid the uuid of this dossier action
	*/
	@Override
	public void setUuid(String uuid) {
		_dossierAction.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierAction> toCacheModel() {
		return _dossierAction.toCacheModel();
	}

	@Override
	public DossierAction toEscapedModel() {
		return new DossierActionWrapper(_dossierAction.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dossierAction.toString();
	}

	@Override
	public DossierAction toUnescapedModel() {
		return new DossierActionWrapper(_dossierAction.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dossierAction.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierActionWrapper)) {
			return false;
		}

		DossierActionWrapper dossierActionWrapper = (DossierActionWrapper)obj;

		if (Objects.equals(_dossierAction, dossierActionWrapper._dossierAction)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dossierAction.getStagedModelType();
	}

	@Override
	public DossierAction getWrappedModel() {
		return _dossierAction;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossierAction.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossierAction.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossierAction.resetOriginalValues();
	}

	private final DossierAction _dossierAction;
}