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

package org.opencps.backend.dossiermgt.model;

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
public class ServiceInfoSoap implements Serializable {
	public static ServiceInfoSoap toSoapModel(ServiceInfo model) {
		ServiceInfoSoap soapModel = new ServiceInfoSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setServiceInfoId(model.getServiceInfoId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setServiceCode(model.getServiceCode());
		soapModel.setServiceName(model.getServiceName());
		soapModel.setProcessText(model.getProcessText());
		soapModel.setMethodText(model.getMethodText());
		soapModel.setDossierText(model.getDossierText());
		soapModel.setConditionText(model.getConditionText());
		soapModel.setDurationText(model.getDurationText());
		soapModel.setApplicantText(model.getApplicantText());
		soapModel.setResultText(model.getResultText());
		soapModel.setRegularText(model.getRegularText());
		soapModel.setFeeText(model.getFeeText());
		soapModel.setAdministrationCode(model.getAdministrationCode());
		soapModel.setAdministrationName(model.getAdministrationName());
		soapModel.setAdministrationIndex(model.getAdministrationIndex());
		soapModel.setDomainCode(model.getDomainCode());
		soapModel.setDomainName(model.getDomainName());
		soapModel.setDomainIndex(model.getDomainIndex());
		soapModel.setActiveStatus(model.getActiveStatus());
		soapModel.setMaxLevel(model.getMaxLevel());

		return soapModel;
	}

	public static ServiceInfoSoap[] toSoapModels(ServiceInfo[] models) {
		ServiceInfoSoap[] soapModels = new ServiceInfoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ServiceInfoSoap[][] toSoapModels(ServiceInfo[][] models) {
		ServiceInfoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ServiceInfoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ServiceInfoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ServiceInfoSoap[] toSoapModels(List<ServiceInfo> models) {
		List<ServiceInfoSoap> soapModels = new ArrayList<ServiceInfoSoap>(models.size());

		for (ServiceInfo model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ServiceInfoSoap[soapModels.size()]);
	}

	public ServiceInfoSoap() {
	}

	public long getPrimaryKey() {
		return _serviceInfoId;
	}

	public void setPrimaryKey(long pk) {
		setServiceInfoId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getServiceInfoId() {
		return _serviceInfoId;
	}

	public void setServiceInfoId(long serviceInfoId) {
		_serviceInfoId = serviceInfoId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public String getServiceCode() {
		return _serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		_serviceCode = serviceCode;
	}

	public String getServiceName() {
		return _serviceName;
	}

	public void setServiceName(String serviceName) {
		_serviceName = serviceName;
	}

	public String getProcessText() {
		return _processText;
	}

	public void setProcessText(String processText) {
		_processText = processText;
	}

	public String getMethodText() {
		return _methodText;
	}

	public void setMethodText(String methodText) {
		_methodText = methodText;
	}

	public String getDossierText() {
		return _dossierText;
	}

	public void setDossierText(String dossierText) {
		_dossierText = dossierText;
	}

	public String getConditionText() {
		return _conditionText;
	}

	public void setConditionText(String conditionText) {
		_conditionText = conditionText;
	}

	public String getDurationText() {
		return _durationText;
	}

	public void setDurationText(String durationText) {
		_durationText = durationText;
	}

	public String getApplicantText() {
		return _applicantText;
	}

	public void setApplicantText(String applicantText) {
		_applicantText = applicantText;
	}

	public String getResultText() {
		return _resultText;
	}

	public void setResultText(String resultText) {
		_resultText = resultText;
	}

	public String getRegularText() {
		return _regularText;
	}

	public void setRegularText(String regularText) {
		_regularText = regularText;
	}

	public String getFeeText() {
		return _feeText;
	}

	public void setFeeText(String feeText) {
		_feeText = feeText;
	}

	public String getAdministrationCode() {
		return _administrationCode;
	}

	public void setAdministrationCode(String administrationCode) {
		_administrationCode = administrationCode;
	}

	public String getAdministrationName() {
		return _administrationName;
	}

	public void setAdministrationName(String administrationName) {
		_administrationName = administrationName;
	}

	public String getAdministrationIndex() {
		return _administrationIndex;
	}

	public void setAdministrationIndex(String administrationIndex) {
		_administrationIndex = administrationIndex;
	}

	public String getDomainCode() {
		return _domainCode;
	}

	public void setDomainCode(String domainCode) {
		_domainCode = domainCode;
	}

	public String getDomainName() {
		return _domainName;
	}

	public void setDomainName(String domainName) {
		_domainName = domainName;
	}

	public String getDomainIndex() {
		return _domainIndex;
	}

	public void setDomainIndex(String domainIndex) {
		_domainIndex = domainIndex;
	}

	public String getActiveStatus() {
		return _activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		_activeStatus = activeStatus;
	}

	public int getMaxLevel() {
		return _maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		_maxLevel = maxLevel;
	}

	private String _uuid;
	private long _serviceInfoId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _serviceCode;
	private String _serviceName;
	private String _processText;
	private String _methodText;
	private String _dossierText;
	private String _conditionText;
	private String _durationText;
	private String _applicantText;
	private String _resultText;
	private String _regularText;
	private String _feeText;
	private String _administrationCode;
	private String _administrationName;
	private String _administrationIndex;
	private String _domainCode;
	private String _domainName;
	private String _domainIndex;
	private String _activeStatus;
	private int _maxLevel;
}