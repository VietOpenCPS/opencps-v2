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
 * This class is a wrapper for {@link ProcessAction}.
 * </p>
 *
 * @author huymq
 * @see ProcessAction
 * @generated
 */
@ProviderType
public class ProcessActionWrapper implements ProcessAction,
	ModelWrapper<ProcessAction> {
	public ProcessActionWrapper(ProcessAction processAction) {
		_processAction = processAction;
	}

	@Override
	public Class<?> getModelClass() {
		return ProcessAction.class;
	}

	@Override
	public String getModelClassName() {
		return ProcessAction.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("processActionId", getProcessActionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serviceProcessId", getServiceProcessId());
		attributes.put("preStepCode", getPreStepCode());
		attributes.put("postStepCode", getPostStepCode());
		attributes.put("autoEvent", getAutoEvent());
		attributes.put("preCondition", getPreCondition());
		attributes.put("actionCode", getActionCode());
		attributes.put("actionName", getActionName());
		attributes.put("allowAssignUser", getAllowAssignUser());
		attributes.put("assignUserId", getAssignUserId());
		attributes.put("requestPayment", getRequestPayment());
		attributes.put("paymentFee", getPaymentFee());
		attributes.put("createDossierFiles", getCreateDossierFiles());
		attributes.put("returnDossierFiles", getReturnDossierFiles());
		attributes.put("makeBriefNote", getMakeBriefNote());
		attributes.put("syncActionCode", getSyncActionCode());
		attributes.put("rollbackable", isRollbackable());
		attributes.put("createDossierNo", isCreateDossierNo());
		attributes.put("eSignature", isESignature());
		attributes.put("configNote", getConfigNote());
		attributes.put("dossierTemplateNo", getDossierTemplateNo());
		attributes.put("signatureType", getSignatureType());
		attributes.put("createDossiers", getCreateDossiers());
		attributes.put("checkInput", getCheckInput());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long processActionId = (Long)attributes.get("processActionId");

		if (processActionId != null) {
			setProcessActionId(processActionId);
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

		Long serviceProcessId = (Long)attributes.get("serviceProcessId");

		if (serviceProcessId != null) {
			setServiceProcessId(serviceProcessId);
		}

		String preStepCode = (String)attributes.get("preStepCode");

		if (preStepCode != null) {
			setPreStepCode(preStepCode);
		}

		String postStepCode = (String)attributes.get("postStepCode");

		if (postStepCode != null) {
			setPostStepCode(postStepCode);
		}

		String autoEvent = (String)attributes.get("autoEvent");

		if (autoEvent != null) {
			setAutoEvent(autoEvent);
		}

		String preCondition = (String)attributes.get("preCondition");

		if (preCondition != null) {
			setPreCondition(preCondition);
		}

		String actionCode = (String)attributes.get("actionCode");

		if (actionCode != null) {
			setActionCode(actionCode);
		}

		String actionName = (String)attributes.get("actionName");

		if (actionName != null) {
			setActionName(actionName);
		}

		Integer allowAssignUser = (Integer)attributes.get("allowAssignUser");

		if (allowAssignUser != null) {
			setAllowAssignUser(allowAssignUser);
		}

		Long assignUserId = (Long)attributes.get("assignUserId");

		if (assignUserId != null) {
			setAssignUserId(assignUserId);
		}

		Integer requestPayment = (Integer)attributes.get("requestPayment");

		if (requestPayment != null) {
			setRequestPayment(requestPayment);
		}

		String paymentFee = (String)attributes.get("paymentFee");

		if (paymentFee != null) {
			setPaymentFee(paymentFee);
		}

		String createDossierFiles = (String)attributes.get("createDossierFiles");

		if (createDossierFiles != null) {
			setCreateDossierFiles(createDossierFiles);
		}

		String returnDossierFiles = (String)attributes.get("returnDossierFiles");

		if (returnDossierFiles != null) {
			setReturnDossierFiles(returnDossierFiles);
		}

		String makeBriefNote = (String)attributes.get("makeBriefNote");

		if (makeBriefNote != null) {
			setMakeBriefNote(makeBriefNote);
		}

		String syncActionCode = (String)attributes.get("syncActionCode");

		if (syncActionCode != null) {
			setSyncActionCode(syncActionCode);
		}

		Boolean rollbackable = (Boolean)attributes.get("rollbackable");

		if (rollbackable != null) {
			setRollbackable(rollbackable);
		}

		Boolean createDossierNo = (Boolean)attributes.get("createDossierNo");

		if (createDossierNo != null) {
			setCreateDossierNo(createDossierNo);
		}

		Boolean eSignature = (Boolean)attributes.get("eSignature");

		if (eSignature != null) {
			setESignature(eSignature);
		}

		String configNote = (String)attributes.get("configNote");

		if (configNote != null) {
			setConfigNote(configNote);
		}

		String dossierTemplateNo = (String)attributes.get("dossierTemplateNo");

		if (dossierTemplateNo != null) {
			setDossierTemplateNo(dossierTemplateNo);
		}

		String signatureType = (String)attributes.get("signatureType");

		if (signatureType != null) {
			setSignatureType(signatureType);
		}

		String createDossiers = (String)attributes.get("createDossiers");

		if (createDossiers != null) {
			setCreateDossiers(createDossiers);
		}

		Integer checkInput = (Integer)attributes.get("checkInput");

		if (checkInput != null) {
			setCheckInput(checkInput);
		}
	}

	@Override
	public Object clone() {
		return new ProcessActionWrapper((ProcessAction)_processAction.clone());
	}

	@Override
	public int compareTo(ProcessAction processAction) {
		return _processAction.compareTo(processAction);
	}

	/**
	* Returns the action code of this process action.
	*
	* @return the action code of this process action
	*/
	@Override
	public String getActionCode() {
		return _processAction.getActionCode();
	}

	/**
	* Returns the action name of this process action.
	*
	* @return the action name of this process action
	*/
	@Override
	public String getActionName() {
		return _processAction.getActionName();
	}

	/**
	* Returns the allow assign user of this process action.
	*
	* @return the allow assign user of this process action
	*/
	@Override
	public int getAllowAssignUser() {
		return _processAction.getAllowAssignUser();
	}

	/**
	* Returns the assign user ID of this process action.
	*
	* @return the assign user ID of this process action
	*/
	@Override
	public long getAssignUserId() {
		return _processAction.getAssignUserId();
	}

	/**
	* Returns the assign user uuid of this process action.
	*
	* @return the assign user uuid of this process action
	*/
	@Override
	public String getAssignUserUuid() {
		return _processAction.getAssignUserUuid();
	}

	/**
	* Returns the auto event of this process action.
	*
	* @return the auto event of this process action
	*/
	@Override
	public String getAutoEvent() {
		return _processAction.getAutoEvent();
	}

	/**
	* Returns the check input of this process action.
	*
	* @return the check input of this process action
	*/
	@Override
	public int getCheckInput() {
		return _processAction.getCheckInput();
	}

	/**
	* Returns the company ID of this process action.
	*
	* @return the company ID of this process action
	*/
	@Override
	public long getCompanyId() {
		return _processAction.getCompanyId();
	}

	/**
	* Returns the config note of this process action.
	*
	* @return the config note of this process action
	*/
	@Override
	public String getConfigNote() {
		return _processAction.getConfigNote();
	}

	/**
	* Returns the create date of this process action.
	*
	* @return the create date of this process action
	*/
	@Override
	public Date getCreateDate() {
		return _processAction.getCreateDate();
	}

	/**
	* Returns the create dossier files of this process action.
	*
	* @return the create dossier files of this process action
	*/
	@Override
	public String getCreateDossierFiles() {
		return _processAction.getCreateDossierFiles();
	}

	/**
	* Returns the create dossier no of this process action.
	*
	* @return the create dossier no of this process action
	*/
	@Override
	public boolean getCreateDossierNo() {
		return _processAction.getCreateDossierNo();
	}

	/**
	* Returns the create dossiers of this process action.
	*
	* @return the create dossiers of this process action
	*/
	@Override
	public String getCreateDossiers() {
		return _processAction.getCreateDossiers();
	}

	/**
	* Returns the dossier template no of this process action.
	*
	* @return the dossier template no of this process action
	*/
	@Override
	public String getDossierTemplateNo() {
		return _processAction.getDossierTemplateNo();
	}

	/**
	* Returns the e signature of this process action.
	*
	* @return the e signature of this process action
	*/
	@Override
	public boolean getESignature() {
		return _processAction.getESignature();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _processAction.getExpandoBridge();
	}

	/**
	* Returns the group ID of this process action.
	*
	* @return the group ID of this process action
	*/
	@Override
	public long getGroupId() {
		return _processAction.getGroupId();
	}

	/**
	* Returns the make brief note of this process action.
	*
	* @return the make brief note of this process action
	*/
	@Override
	public String getMakeBriefNote() {
		return _processAction.getMakeBriefNote();
	}

	/**
	* Returns the modified date of this process action.
	*
	* @return the modified date of this process action
	*/
	@Override
	public Date getModifiedDate() {
		return _processAction.getModifiedDate();
	}

	/**
	* Returns the payment fee of this process action.
	*
	* @return the payment fee of this process action
	*/
	@Override
	public String getPaymentFee() {
		return _processAction.getPaymentFee();
	}

	/**
	* Returns the post step code of this process action.
	*
	* @return the post step code of this process action
	*/
	@Override
	public String getPostStepCode() {
		return _processAction.getPostStepCode();
	}

	/**
	* Returns the pre condition of this process action.
	*
	* @return the pre condition of this process action
	*/
	@Override
	public String getPreCondition() {
		return _processAction.getPreCondition();
	}

	/**
	* Returns the pre step code of this process action.
	*
	* @return the pre step code of this process action
	*/
	@Override
	public String getPreStepCode() {
		return _processAction.getPreStepCode();
	}

	/**
	* Returns the primary key of this process action.
	*
	* @return the primary key of this process action
	*/
	@Override
	public long getPrimaryKey() {
		return _processAction.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _processAction.getPrimaryKeyObj();
	}

	/**
	* Returns the process action ID of this process action.
	*
	* @return the process action ID of this process action
	*/
	@Override
	public long getProcessActionId() {
		return _processAction.getProcessActionId();
	}

	/**
	* Returns the request payment of this process action.
	*
	* @return the request payment of this process action
	*/
	@Override
	public int getRequestPayment() {
		return _processAction.getRequestPayment();
	}

	/**
	* Returns the return dossier files of this process action.
	*
	* @return the return dossier files of this process action
	*/
	@Override
	public String getReturnDossierFiles() {
		return _processAction.getReturnDossierFiles();
	}

	/**
	* Returns the rollbackable of this process action.
	*
	* @return the rollbackable of this process action
	*/
	@Override
	public boolean getRollbackable() {
		return _processAction.getRollbackable();
	}

	/**
	* Returns the service process ID of this process action.
	*
	* @return the service process ID of this process action
	*/
	@Override
	public long getServiceProcessId() {
		return _processAction.getServiceProcessId();
	}

	/**
	* Returns the signature type of this process action.
	*
	* @return the signature type of this process action
	*/
	@Override
	public String getSignatureType() {
		return _processAction.getSignatureType();
	}

	/**
	* Returns the sync action code of this process action.
	*
	* @return the sync action code of this process action
	*/
	@Override
	public String getSyncActionCode() {
		return _processAction.getSyncActionCode();
	}

	/**
	* Returns the user ID of this process action.
	*
	* @return the user ID of this process action
	*/
	@Override
	public long getUserId() {
		return _processAction.getUserId();
	}

	/**
	* Returns the user name of this process action.
	*
	* @return the user name of this process action
	*/
	@Override
	public String getUserName() {
		return _processAction.getUserName();
	}

	/**
	* Returns the user uuid of this process action.
	*
	* @return the user uuid of this process action
	*/
	@Override
	public String getUserUuid() {
		return _processAction.getUserUuid();
	}

	/**
	* Returns the uuid of this process action.
	*
	* @return the uuid of this process action
	*/
	@Override
	public String getUuid() {
		return _processAction.getUuid();
	}

	@Override
	public int hashCode() {
		return _processAction.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _processAction.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this process action is create dossier no.
	*
	* @return <code>true</code> if this process action is create dossier no; <code>false</code> otherwise
	*/
	@Override
	public boolean isCreateDossierNo() {
		return _processAction.isCreateDossierNo();
	}

	@Override
	public boolean isEscapedModel() {
		return _processAction.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this process action is e signature.
	*
	* @return <code>true</code> if this process action is e signature; <code>false</code> otherwise
	*/
	@Override
	public boolean isESignature() {
		return _processAction.isESignature();
	}

	@Override
	public boolean isNew() {
		return _processAction.isNew();
	}

	/**
	* Returns <code>true</code> if this process action is rollbackable.
	*
	* @return <code>true</code> if this process action is rollbackable; <code>false</code> otherwise
	*/
	@Override
	public boolean isRollbackable() {
		return _processAction.isRollbackable();
	}

	@Override
	public void persist() {
		_processAction.persist();
	}

	/**
	* Sets the action code of this process action.
	*
	* @param actionCode the action code of this process action
	*/
	@Override
	public void setActionCode(String actionCode) {
		_processAction.setActionCode(actionCode);
	}

	/**
	* Sets the action name of this process action.
	*
	* @param actionName the action name of this process action
	*/
	@Override
	public void setActionName(String actionName) {
		_processAction.setActionName(actionName);
	}

	/**
	* Sets the allow assign user of this process action.
	*
	* @param allowAssignUser the allow assign user of this process action
	*/
	@Override
	public void setAllowAssignUser(int allowAssignUser) {
		_processAction.setAllowAssignUser(allowAssignUser);
	}

	/**
	* Sets the assign user ID of this process action.
	*
	* @param assignUserId the assign user ID of this process action
	*/
	@Override
	public void setAssignUserId(long assignUserId) {
		_processAction.setAssignUserId(assignUserId);
	}

	/**
	* Sets the assign user uuid of this process action.
	*
	* @param assignUserUuid the assign user uuid of this process action
	*/
	@Override
	public void setAssignUserUuid(String assignUserUuid) {
		_processAction.setAssignUserUuid(assignUserUuid);
	}

	/**
	* Sets the auto event of this process action.
	*
	* @param autoEvent the auto event of this process action
	*/
	@Override
	public void setAutoEvent(String autoEvent) {
		_processAction.setAutoEvent(autoEvent);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_processAction.setCachedModel(cachedModel);
	}

	/**
	* Sets the check input of this process action.
	*
	* @param checkInput the check input of this process action
	*/
	@Override
	public void setCheckInput(int checkInput) {
		_processAction.setCheckInput(checkInput);
	}

	/**
	* Sets the company ID of this process action.
	*
	* @param companyId the company ID of this process action
	*/
	@Override
	public void setCompanyId(long companyId) {
		_processAction.setCompanyId(companyId);
	}

	/**
	* Sets the config note of this process action.
	*
	* @param configNote the config note of this process action
	*/
	@Override
	public void setConfigNote(String configNote) {
		_processAction.setConfigNote(configNote);
	}

	/**
	* Sets the create date of this process action.
	*
	* @param createDate the create date of this process action
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_processAction.setCreateDate(createDate);
	}

	/**
	* Sets the create dossier files of this process action.
	*
	* @param createDossierFiles the create dossier files of this process action
	*/
	@Override
	public void setCreateDossierFiles(String createDossierFiles) {
		_processAction.setCreateDossierFiles(createDossierFiles);
	}

	/**
	* Sets whether this process action is create dossier no.
	*
	* @param createDossierNo the create dossier no of this process action
	*/
	@Override
	public void setCreateDossierNo(boolean createDossierNo) {
		_processAction.setCreateDossierNo(createDossierNo);
	}

	/**
	* Sets the create dossiers of this process action.
	*
	* @param createDossiers the create dossiers of this process action
	*/
	@Override
	public void setCreateDossiers(String createDossiers) {
		_processAction.setCreateDossiers(createDossiers);
	}

	/**
	* Sets the dossier template no of this process action.
	*
	* @param dossierTemplateNo the dossier template no of this process action
	*/
	@Override
	public void setDossierTemplateNo(String dossierTemplateNo) {
		_processAction.setDossierTemplateNo(dossierTemplateNo);
	}

	/**
	* Sets whether this process action is e signature.
	*
	* @param eSignature the e signature of this process action
	*/
	@Override
	public void setESignature(boolean eSignature) {
		_processAction.setESignature(eSignature);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_processAction.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_processAction.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_processAction.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this process action.
	*
	* @param groupId the group ID of this process action
	*/
	@Override
	public void setGroupId(long groupId) {
		_processAction.setGroupId(groupId);
	}

	/**
	* Sets the make brief note of this process action.
	*
	* @param makeBriefNote the make brief note of this process action
	*/
	@Override
	public void setMakeBriefNote(String makeBriefNote) {
		_processAction.setMakeBriefNote(makeBriefNote);
	}

	/**
	* Sets the modified date of this process action.
	*
	* @param modifiedDate the modified date of this process action
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_processAction.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_processAction.setNew(n);
	}

	/**
	* Sets the payment fee of this process action.
	*
	* @param paymentFee the payment fee of this process action
	*/
	@Override
	public void setPaymentFee(String paymentFee) {
		_processAction.setPaymentFee(paymentFee);
	}

	/**
	* Sets the post step code of this process action.
	*
	* @param postStepCode the post step code of this process action
	*/
	@Override
	public void setPostStepCode(String postStepCode) {
		_processAction.setPostStepCode(postStepCode);
	}

	/**
	* Sets the pre condition of this process action.
	*
	* @param preCondition the pre condition of this process action
	*/
	@Override
	public void setPreCondition(String preCondition) {
		_processAction.setPreCondition(preCondition);
	}

	/**
	* Sets the pre step code of this process action.
	*
	* @param preStepCode the pre step code of this process action
	*/
	@Override
	public void setPreStepCode(String preStepCode) {
		_processAction.setPreStepCode(preStepCode);
	}

	/**
	* Sets the primary key of this process action.
	*
	* @param primaryKey the primary key of this process action
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_processAction.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_processAction.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process action ID of this process action.
	*
	* @param processActionId the process action ID of this process action
	*/
	@Override
	public void setProcessActionId(long processActionId) {
		_processAction.setProcessActionId(processActionId);
	}

	/**
	* Sets the request payment of this process action.
	*
	* @param requestPayment the request payment of this process action
	*/
	@Override
	public void setRequestPayment(int requestPayment) {
		_processAction.setRequestPayment(requestPayment);
	}

	/**
	* Sets the return dossier files of this process action.
	*
	* @param returnDossierFiles the return dossier files of this process action
	*/
	@Override
	public void setReturnDossierFiles(String returnDossierFiles) {
		_processAction.setReturnDossierFiles(returnDossierFiles);
	}

	/**
	* Sets whether this process action is rollbackable.
	*
	* @param rollbackable the rollbackable of this process action
	*/
	@Override
	public void setRollbackable(boolean rollbackable) {
		_processAction.setRollbackable(rollbackable);
	}

	/**
	* Sets the service process ID of this process action.
	*
	* @param serviceProcessId the service process ID of this process action
	*/
	@Override
	public void setServiceProcessId(long serviceProcessId) {
		_processAction.setServiceProcessId(serviceProcessId);
	}

	/**
	* Sets the signature type of this process action.
	*
	* @param signatureType the signature type of this process action
	*/
	@Override
	public void setSignatureType(String signatureType) {
		_processAction.setSignatureType(signatureType);
	}

	/**
	* Sets the sync action code of this process action.
	*
	* @param syncActionCode the sync action code of this process action
	*/
	@Override
	public void setSyncActionCode(String syncActionCode) {
		_processAction.setSyncActionCode(syncActionCode);
	}

	/**
	* Sets the user ID of this process action.
	*
	* @param userId the user ID of this process action
	*/
	@Override
	public void setUserId(long userId) {
		_processAction.setUserId(userId);
	}

	/**
	* Sets the user name of this process action.
	*
	* @param userName the user name of this process action
	*/
	@Override
	public void setUserName(String userName) {
		_processAction.setUserName(userName);
	}

	/**
	* Sets the user uuid of this process action.
	*
	* @param userUuid the user uuid of this process action
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_processAction.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this process action.
	*
	* @param uuid the uuid of this process action
	*/
	@Override
	public void setUuid(String uuid) {
		_processAction.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProcessAction> toCacheModel() {
		return _processAction.toCacheModel();
	}

	@Override
	public ProcessAction toEscapedModel() {
		return new ProcessActionWrapper(_processAction.toEscapedModel());
	}

	@Override
	public String toString() {
		return _processAction.toString();
	}

	@Override
	public ProcessAction toUnescapedModel() {
		return new ProcessActionWrapper(_processAction.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _processAction.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessActionWrapper)) {
			return false;
		}

		ProcessActionWrapper processActionWrapper = (ProcessActionWrapper)obj;

		if (Objects.equals(_processAction, processActionWrapper._processAction)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _processAction.getStagedModelType();
	}

	@Override
	public ProcessAction getWrappedModel() {
		return _processAction;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _processAction.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _processAction.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_processAction.resetOriginalValues();
	}

	private final ProcessAction _processAction;
}