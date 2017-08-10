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
public class ServiceOptionSoap implements Serializable {
	public static ServiceOptionSoap toSoapModel(ServiceOption model) {
		ServiceOptionSoap soapModel = new ServiceOptionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setServiceOptionId(model.getServiceOptionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOptionCode(model.getOptionCode());
		soapModel.setOptionName(model.getOptionName());
		soapModel.setOptionOrder(model.getOptionOrder());
		soapModel.setAutoSelect(model.getAutoSelect());
		soapModel.setDossierTemplateId(model.getDossierTemplateId());
		soapModel.setServiceProcessId(model.getServiceProcessId());
		soapModel.setInstructionNote(model.getInstructionNote());

		return soapModel;
	}

	public static ServiceOptionSoap[] toSoapModels(ServiceOption[] models) {
		ServiceOptionSoap[] soapModels = new ServiceOptionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ServiceOptionSoap[][] toSoapModels(ServiceOption[][] models) {
		ServiceOptionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ServiceOptionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ServiceOptionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ServiceOptionSoap[] toSoapModels(List<ServiceOption> models) {
		List<ServiceOptionSoap> soapModels = new ArrayList<ServiceOptionSoap>(models.size());

		for (ServiceOption model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ServiceOptionSoap[soapModels.size()]);
	}

	public ServiceOptionSoap() {
	}

	public long getPrimaryKey() {
		return _serviceOptionId;
	}

	public void setPrimaryKey(long pk) {
		setServiceOptionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getServiceOptionId() {
		return _serviceOptionId;
	}

	public void setServiceOptionId(long serviceOptionId) {
		_serviceOptionId = serviceOptionId;
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

	public String getOptionCode() {
		return _optionCode;
	}

	public void setOptionCode(String optionCode) {
		_optionCode = optionCode;
	}

	public String getOptionName() {
		return _optionName;
	}

	public void setOptionName(String optionName) {
		_optionName = optionName;
	}

	public int getOptionOrder() {
		return _optionOrder;
	}

	public void setOptionOrder(int optionOrder) {
		_optionOrder = optionOrder;
	}

	public String getAutoSelect() {
		return _autoSelect;
	}

	public void setAutoSelect(String autoSelect) {
		_autoSelect = autoSelect;
	}

	public long getDossierTemplateId() {
		return _dossierTemplateId;
	}

	public void setDossierTemplateId(long dossierTemplateId) {
		_dossierTemplateId = dossierTemplateId;
	}

	public long getServiceProcessId() {
		return _serviceProcessId;
	}

	public void setServiceProcessId(long serviceProcessId) {
		_serviceProcessId = serviceProcessId;
	}

	public String getInstructionNote() {
		return _instructionNote;
	}

	public void setInstructionNote(String instructionNote) {
		_instructionNote = instructionNote;
	}

	private String _uuid;
	private long _serviceOptionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _optionCode;
	private String _optionName;
	private int _optionOrder;
	private String _autoSelect;
	private long _dossierTemplateId;
	private long _serviceProcessId;
	private String _instructionNote;
}