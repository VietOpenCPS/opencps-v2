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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavu
 * @generated
 */
@ProviderType
public class ProcessActionSoap implements Serializable {
	public static ProcessActionSoap toSoapModel(ProcessAction model) {
		ProcessActionSoap soapModel = new ProcessActionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setProcessActionId(model.getProcessActionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setServiceProcessId(model.getServiceProcessId());
		soapModel.setPreProcessStepId(model.getPreProcessStepId());
		soapModel.setPostProcessStepId(model.getPostProcessStepId());
		soapModel.setAutoEvent(model.getAutoEvent());
		soapModel.setPreCondition(model.getPreCondition());
		soapModel.setActionCode(model.getActionCode());
		soapModel.setActionName(model.getActionName());
		soapModel.setAllowAssignUser(model.getAllowAssignUser());
		soapModel.setAssignUserId(model.getAssignUserId());
		soapModel.setRequestPayment(model.getRequestPayment());
		soapModel.setPaymentFee(model.getPaymentFee());
		soapModel.setCreateDossierFiles(model.getCreateDossierFiles());
		soapModel.setReturnDossierFiles(model.getReturnDossierFiles());
		soapModel.setSyncActionCode(model.getSyncActionCode());
		soapModel.setRollback(model.getRollback());

		return soapModel;
	}

	public static ProcessActionSoap[] toSoapModels(ProcessAction[] models) {
		ProcessActionSoap[] soapModels = new ProcessActionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProcessActionSoap[][] toSoapModels(ProcessAction[][] models) {
		ProcessActionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProcessActionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProcessActionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProcessActionSoap[] toSoapModels(List<ProcessAction> models) {
		List<ProcessActionSoap> soapModels = new ArrayList<ProcessActionSoap>(models.size());

		for (ProcessAction model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProcessActionSoap[soapModels.size()]);
	}

	public ProcessActionSoap() {
	}

	public long getPrimaryKey() {
		return _processActionId;
	}

	public void setPrimaryKey(long pk) {
		setProcessActionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getProcessActionId() {
		return _processActionId;
	}

	public void setProcessActionId(long processActionId) {
		_processActionId = processActionId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getServiceProcessId() {
		return _serviceProcessId;
	}

	public void setServiceProcessId(long serviceProcessId) {
		_serviceProcessId = serviceProcessId;
	}

	public long getPreProcessStepId() {
		return _preProcessStepId;
	}

	public void setPreProcessStepId(long preProcessStepId) {
		_preProcessStepId = preProcessStepId;
	}

	public long getPostProcessStepId() {
		return _postProcessStepId;
	}

	public void setPostProcessStepId(long postProcessStepId) {
		_postProcessStepId = postProcessStepId;
	}

	public String getAutoEvent() {
		return _autoEvent;
	}

	public void setAutoEvent(String autoEvent) {
		_autoEvent = autoEvent;
	}

	public String getPreCondition() {
		return _preCondition;
	}

	public void setPreCondition(String preCondition) {
		_preCondition = preCondition;
	}

	public String getActionCode() {
		return _actionCode;
	}

	public void setActionCode(String actionCode) {
		_actionCode = actionCode;
	}

	public String getActionName() {
		return _actionName;
	}

	public void setActionName(String actionName) {
		_actionName = actionName;
	}

	public String getAllowAssignUser() {
		return _allowAssignUser;
	}

	public void setAllowAssignUser(String allowAssignUser) {
		_allowAssignUser = allowAssignUser;
	}

	public long getAssignUserId() {
		return _assignUserId;
	}

	public void setAssignUserId(long assignUserId) {
		_assignUserId = assignUserId;
	}

	public String getRequestPayment() {
		return _requestPayment;
	}

	public void setRequestPayment(String requestPayment) {
		_requestPayment = requestPayment;
	}

	public String getPaymentFee() {
		return _paymentFee;
	}

	public void setPaymentFee(String paymentFee) {
		_paymentFee = paymentFee;
	}

	public String getCreateDossierFiles() {
		return _createDossierFiles;
	}

	public void setCreateDossierFiles(String createDossierFiles) {
		_createDossierFiles = createDossierFiles;
	}

	public String getReturnDossierFiles() {
		return _returnDossierFiles;
	}

	public void setReturnDossierFiles(String returnDossierFiles) {
		_returnDossierFiles = returnDossierFiles;
	}

	public String getSyncActionCode() {
		return _syncActionCode;
	}

	public void setSyncActionCode(String syncActionCode) {
		_syncActionCode = syncActionCode;
	}

	public String getRollback() {
		return _rollback;
	}

	public void setRollback(String rollback) {
		_rollback = rollback;
	}

	private String _uuid;
	private long _processActionId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _serviceProcessId;
	private long _preProcessStepId;
	private long _postProcessStepId;
	private String _autoEvent;
	private String _preCondition;
	private String _actionCode;
	private String _actionName;
	private String _allowAssignUser;
	private long _assignUserId;
	private String _requestPayment;
	private String _paymentFee;
	private String _createDossierFiles;
	private String _returnDossierFiles;
	private String _syncActionCode;
	private String _rollback;
}