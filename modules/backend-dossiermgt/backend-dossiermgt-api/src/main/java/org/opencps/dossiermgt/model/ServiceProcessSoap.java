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
public class ServiceProcessSoap implements Serializable {
	public static ServiceProcessSoap toSoapModel(ServiceProcess model) {
		ServiceProcessSoap soapModel = new ServiceProcessSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setServiceProcessId(model.getServiceProcessId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setProcessNo(model.getProcessNo());
		soapModel.setProcessName(model.getProcessName());
		soapModel.setDescription(model.getDescription());
		soapModel.setDurationCount(model.getDurationCount());
		soapModel.setDurationUnit(model.getDurationUnit());
		soapModel.setCounter(model.getCounter());
		soapModel.setGenerateDossierNo(model.isGenerateDossierNo());
		soapModel.setDossierNoPattern(model.getDossierNoPattern());
		soapModel.setGenerateDueDate(model.isGenerateDueDate());
		soapModel.setDueDatePattern(model.getDueDatePattern());
		soapModel.setGeneratePassword(model.isGeneratePassword());
		soapModel.setDirectNotification(model.isDirectNotification());
		soapModel.setServerNo(model.getServerNo());
		soapModel.setServerName(model.getServerName());
		soapModel.setRequestPayment(model.isRequestPayment());
		soapModel.setPaymentFee(model.getPaymentFee());

		return soapModel;
	}

	public static ServiceProcessSoap[] toSoapModels(ServiceProcess[] models) {
		ServiceProcessSoap[] soapModels = new ServiceProcessSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ServiceProcessSoap[][] toSoapModels(ServiceProcess[][] models) {
		ServiceProcessSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ServiceProcessSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ServiceProcessSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ServiceProcessSoap[] toSoapModels(List<ServiceProcess> models) {
		List<ServiceProcessSoap> soapModels = new ArrayList<ServiceProcessSoap>(models.size());

		for (ServiceProcess model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ServiceProcessSoap[soapModels.size()]);
	}

	public ServiceProcessSoap() {
	}

	public long getPrimaryKey() {
		return _serviceProcessId;
	}

	public void setPrimaryKey(long pk) {
		setServiceProcessId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getServiceProcessId() {
		return _serviceProcessId;
	}

	public void setServiceProcessId(long serviceProcessId) {
		_serviceProcessId = serviceProcessId;
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

	public String getProcessNo() {
		return _processNo;
	}

	public void setProcessNo(String processNo) {
		_processNo = processNo;
	}

	public String getProcessName() {
		return _processName;
	}

	public void setProcessName(String processName) {
		_processName = processName;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public double getDurationCount() {
		return _durationCount;
	}

	public void setDurationCount(double durationCount) {
		_durationCount = durationCount;
	}

	public int getDurationUnit() {
		return _durationUnit;
	}

	public void setDurationUnit(int durationUnit) {
		_durationUnit = durationUnit;
	}

	public long getCounter() {
		return _counter;
	}

	public void setCounter(long counter) {
		_counter = counter;
	}

	public boolean getGenerateDossierNo() {
		return _generateDossierNo;
	}

	public boolean isGenerateDossierNo() {
		return _generateDossierNo;
	}

	public void setGenerateDossierNo(boolean generateDossierNo) {
		_generateDossierNo = generateDossierNo;
	}

	public String getDossierNoPattern() {
		return _dossierNoPattern;
	}

	public void setDossierNoPattern(String dossierNoPattern) {
		_dossierNoPattern = dossierNoPattern;
	}

	public boolean getGenerateDueDate() {
		return _generateDueDate;
	}

	public boolean isGenerateDueDate() {
		return _generateDueDate;
	}

	public void setGenerateDueDate(boolean generateDueDate) {
		_generateDueDate = generateDueDate;
	}

	public String getDueDatePattern() {
		return _dueDatePattern;
	}

	public void setDueDatePattern(String dueDatePattern) {
		_dueDatePattern = dueDatePattern;
	}

	public boolean getGeneratePassword() {
		return _generatePassword;
	}

	public boolean isGeneratePassword() {
		return _generatePassword;
	}

	public void setGeneratePassword(boolean generatePassword) {
		_generatePassword = generatePassword;
	}

	public boolean getDirectNotification() {
		return _directNotification;
	}

	public boolean isDirectNotification() {
		return _directNotification;
	}

	public void setDirectNotification(boolean directNotification) {
		_directNotification = directNotification;
	}

	public String getServerNo() {
		return _serverNo;
	}

	public void setServerNo(String serverNo) {
		_serverNo = serverNo;
	}

	public String getServerName() {
		return _serverName;
	}

	public void setServerName(String serverName) {
		_serverName = serverName;
	}

	public boolean getRequestPayment() {
		return _requestPayment;
	}

	public boolean isRequestPayment() {
		return _requestPayment;
	}

	public void setRequestPayment(boolean requestPayment) {
		_requestPayment = requestPayment;
	}

	public String getPaymentFee() {
		return _paymentFee;
	}

	public void setPaymentFee(String paymentFee) {
		_paymentFee = paymentFee;
	}

	private String _uuid;
	private long _serviceProcessId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _processNo;
	private String _processName;
	private String _description;
	private double _durationCount;
	private int _durationUnit;
	private long _counter;
	private boolean _generateDossierNo;
	private String _dossierNoPattern;
	private boolean _generateDueDate;
	private String _dueDatePattern;
	private boolean _generatePassword;
	private boolean _directNotification;
	private String _serverNo;
	private String _serverName;
	private boolean _requestPayment;
	private String _paymentFee;
}