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

package org.opencps.backend.processmgt.model;

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
 * @author khoavu
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
		attributes.put("actionCode", getActionCode());
		attributes.put("actionUser", getActionUser());
		attributes.put("actionName", getActionName());
		attributes.put("actionNote", getActionNote());
		attributes.put("overDue", getOverDue());
		attributes.put("syncActionCode", getSyncActionCode());
		attributes.put("pending", getPending());
		attributes.put("rollback", getRollback());
		attributes.put("processStepId", getProcessStepId());
		attributes.put("dueDate", getDueDate());
		attributes.put("nextActionId", getNextActionId());

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

		Integer overDue = (Integer)attributes.get("overDue");

		if (overDue != null) {
			setOverDue(overDue);
		}

		String syncActionCode = (String)attributes.get("syncActionCode");

		if (syncActionCode != null) {
			setSyncActionCode(syncActionCode);
		}

		String pending = (String)attributes.get("pending");

		if (pending != null) {
			setPending(pending);
		}

		String rollback = (String)attributes.get("rollback");

		if (rollback != null) {
			setRollback(rollback);
		}

		Long processStepId = (Long)attributes.get("processStepId");

		if (processStepId != null) {
			setProcessStepId(processStepId);
		}

		Integer dueDate = (Integer)attributes.get("dueDate");

		if (dueDate != null) {
			setDueDate(dueDate);
		}

		Long nextActionId = (Long)attributes.get("nextActionId");

		if (nextActionId != null) {
			setNextActionId(nextActionId);
		}
	}

	@Override
	public DossierAction toEscapedModel() {
		return new DossierActionWrapper(_dossierAction.toEscapedModel());
	}

	@Override
	public DossierAction toUnescapedModel() {
		return new DossierActionWrapper(_dossierAction.toUnescapedModel());
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

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierAction.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierAction> toCacheModel() {
		return _dossierAction.toCacheModel();
	}

	@Override
	public int compareTo(DossierAction dossierAction) {
		return _dossierAction.compareTo(dossierAction);
	}

	/**
	* Returns the due date of this dossier action.
	*
	* @return the due date of this dossier action
	*/
	@Override
	public int getDueDate() {
		return _dossierAction.getDueDate();
	}

	/**
	* Returns the over due of this dossier action.
	*
	* @return the over due of this dossier action
	*/
	@Override
	public int getOverDue() {
		return _dossierAction.getOverDue();
	}

	@Override
	public int hashCode() {
		return _dossierAction.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierAction.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DossierActionWrapper((DossierAction)_dossierAction.clone());
	}

	/**
	* Returns the action code of this dossier action.
	*
	* @return the action code of this dossier action
	*/
	@Override
	public java.lang.String getActionCode() {
		return _dossierAction.getActionCode();
	}

	/**
	* Returns the action name of this dossier action.
	*
	* @return the action name of this dossier action
	*/
	@Override
	public java.lang.String getActionName() {
		return _dossierAction.getActionName();
	}

	/**
	* Returns the action note of this dossier action.
	*
	* @return the action note of this dossier action
	*/
	@Override
	public java.lang.String getActionNote() {
		return _dossierAction.getActionNote();
	}

	/**
	* Returns the action user of this dossier action.
	*
	* @return the action user of this dossier action
	*/
	@Override
	public java.lang.String getActionUser() {
		return _dossierAction.getActionUser();
	}

	/**
	* Returns the pending of this dossier action.
	*
	* @return the pending of this dossier action
	*/
	@Override
	public java.lang.String getPending() {
		return _dossierAction.getPending();
	}

	/**
	* Returns the rollback of this dossier action.
	*
	* @return the rollback of this dossier action
	*/
	@Override
	public java.lang.String getRollback() {
		return _dossierAction.getRollback();
	}

	/**
	* Returns the sync action code of this dossier action.
	*
	* @return the sync action code of this dossier action
	*/
	@Override
	public java.lang.String getSyncActionCode() {
		return _dossierAction.getSyncActionCode();
	}

	/**
	* Returns the user name of this dossier action.
	*
	* @return the user name of this dossier action
	*/
	@Override
	public java.lang.String getUserName() {
		return _dossierAction.getUserName();
	}

	/**
	* Returns the user uuid of this dossier action.
	*
	* @return the user uuid of this dossier action
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _dossierAction.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier action.
	*
	* @return the uuid of this dossier action
	*/
	@Override
	public java.lang.String getUuid() {
		return _dossierAction.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _dossierAction.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dossierAction.toXmlString();
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
	* Returns the modified date of this dossier action.
	*
	* @return the modified date of this dossier action
	*/
	@Override
	public Date getModifiedDate() {
		return _dossierAction.getModifiedDate();
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
	* Returns the group ID of this dossier action.
	*
	* @return the group ID of this dossier action
	*/
	@Override
	public long getGroupId() {
		return _dossierAction.getGroupId();
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

	/**
	* Returns the process step ID of this dossier action.
	*
	* @return the process step ID of this dossier action
	*/
	@Override
	public long getProcessStepId() {
		return _dossierAction.getProcessStepId();
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
	* Returns the user ID of this dossier action.
	*
	* @return the user ID of this dossier action
	*/
	@Override
	public long getUserId() {
		return _dossierAction.getUserId();
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
	public void setActionCode(java.lang.String actionCode) {
		_dossierAction.setActionCode(actionCode);
	}

	/**
	* Sets the action name of this dossier action.
	*
	* @param actionName the action name of this dossier action
	*/
	@Override
	public void setActionName(java.lang.String actionName) {
		_dossierAction.setActionName(actionName);
	}

	/**
	* Sets the action note of this dossier action.
	*
	* @param actionNote the action note of this dossier action
	*/
	@Override
	public void setActionNote(java.lang.String actionNote) {
		_dossierAction.setActionNote(actionNote);
	}

	/**
	* Sets the action user of this dossier action.
	*
	* @param actionUser the action user of this dossier action
	*/
	@Override
	public void setActionUser(java.lang.String actionUser) {
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
	public void setDueDate(int dueDate) {
		_dossierAction.setDueDate(dueDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierAction.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierAction.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierAction.setExpandoBridgeAttributes(serviceContext);
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
	* Sets the over due of this dossier action.
	*
	* @param overDue the over due of this dossier action
	*/
	@Override
	public void setOverDue(int overDue) {
		_dossierAction.setOverDue(overDue);
	}

	/**
	* Sets the pending of this dossier action.
	*
	* @param pending the pending of this dossier action
	*/
	@Override
	public void setPending(java.lang.String pending) {
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
	* Sets the process step ID of this dossier action.
	*
	* @param processStepId the process step ID of this dossier action
	*/
	@Override
	public void setProcessStepId(long processStepId) {
		_dossierAction.setProcessStepId(processStepId);
	}

	/**
	* Sets the rollback of this dossier action.
	*
	* @param rollback the rollback of this dossier action
	*/
	@Override
	public void setRollback(java.lang.String rollback) {
		_dossierAction.setRollback(rollback);
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
	* Sets the sync action code of this dossier action.
	*
	* @param syncActionCode the sync action code of this dossier action
	*/
	@Override
	public void setSyncActionCode(java.lang.String syncActionCode) {
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
	public void setUserName(java.lang.String userName) {
		_dossierAction.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dossier action.
	*
	* @param userUuid the user uuid of this dossier action
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_dossierAction.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier action.
	*
	* @param uuid the uuid of this dossier action
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_dossierAction.setUuid(uuid);
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