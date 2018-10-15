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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author huymq
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
		soapModel.setPreStepCode(model.getPreStepCode());
		soapModel.setPostStepCode(model.getPostStepCode());
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
		soapModel.setMakeBriefNote(model.getMakeBriefNote());
		soapModel.setSyncActionCode(model.getSyncActionCode());
		soapModel.setRollbackable(model.isRollbackable());
		soapModel.setCreateDossierNo(model.isCreateDossierNo());
		soapModel.setESignature(model.isESignature());
		soapModel.setConfigNote(model.getConfigNote());
		soapModel.setDossierTemplateNo(model.getDossierTemplateNo());
		soapModel.setSignatureType(model.getSignatureType());
		soapModel.setCreateDossiers(model.getCreateDossiers());
		soapModel.setCheckInput(model.getCheckInput());

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

	public String getPreStepCode() {
		return _preStepCode;
	}

	public void setPreStepCode(String preStepCode) {
		_preStepCode = preStepCode;
	}

	public String getPostStepCode() {
		return _postStepCode;
	}

	public void setPostStepCode(String postStepCode) {
		_postStepCode = postStepCode;
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

	public int getAllowAssignUser() {
		return _allowAssignUser;
	}

	public void setAllowAssignUser(int allowAssignUser) {
		_allowAssignUser = allowAssignUser;
	}

	public long getAssignUserId() {
		return _assignUserId;
	}

	public void setAssignUserId(long assignUserId) {
		_assignUserId = assignUserId;
	}

	public int getRequestPayment() {
		return _requestPayment;
	}

	public void setRequestPayment(int requestPayment) {
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

	public String getMakeBriefNote() {
		return _makeBriefNote;
	}

	public void setMakeBriefNote(String makeBriefNote) {
		_makeBriefNote = makeBriefNote;
	}

	public String getSyncActionCode() {
		return _syncActionCode;
	}

	public void setSyncActionCode(String syncActionCode) {
		_syncActionCode = syncActionCode;
	}

	public boolean getRollbackable() {
		return _rollbackable;
	}

	public boolean isRollbackable() {
		return _rollbackable;
	}

	public void setRollbackable(boolean rollbackable) {
		_rollbackable = rollbackable;
	}

	public boolean getCreateDossierNo() {
		return _createDossierNo;
	}

	public boolean isCreateDossierNo() {
		return _createDossierNo;
	}

	public void setCreateDossierNo(boolean createDossierNo) {
		_createDossierNo = createDossierNo;
	}

	public boolean getESignature() {
		return _eSignature;
	}

	public boolean isESignature() {
		return _eSignature;
	}

	public void setESignature(boolean eSignature) {
		_eSignature = eSignature;
	}

	public String getConfigNote() {
		return _configNote;
	}

	public void setConfigNote(String configNote) {
		_configNote = configNote;
	}

	public String getDossierTemplateNo() {
		return _dossierTemplateNo;
	}

	public void setDossierTemplateNo(String dossierTemplateNo) {
		_dossierTemplateNo = dossierTemplateNo;
	}

	public String getSignatureType() {
		return _signatureType;
	}

	public void setSignatureType(String signatureType) {
		_signatureType = signatureType;
	}

	public String getCreateDossiers() {
		return _createDossiers;
	}

	public void setCreateDossiers(String createDossiers) {
		_createDossiers = createDossiers;
	}

	public int getCheckInput() {
		return _checkInput;
	}

	public void setCheckInput(int checkInput) {
		_checkInput = checkInput;
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
	private String _preStepCode;
	private String _postStepCode;
	private String _autoEvent;
	private String _preCondition;
	private String _actionCode;
	private String _actionName;
	private int _allowAssignUser;
	private long _assignUserId;
	private int _requestPayment;
	private String _paymentFee;
	private String _createDossierFiles;
	private String _returnDossierFiles;
	private String _makeBriefNote;
	private String _syncActionCode;
	private boolean _rollbackable;
	private boolean _createDossierNo;
	private boolean _eSignature;
	private String _configNote;
	private String _dossierTemplateNo;
	private String _signatureType;
	private String _createDossiers;
	private int _checkInput;
}