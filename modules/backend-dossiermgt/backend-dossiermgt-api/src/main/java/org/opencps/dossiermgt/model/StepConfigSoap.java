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
public class StepConfigSoap implements Serializable {
	public static StepConfigSoap toSoapModel(StepConfig model) {
		StepConfigSoap soapModel = new StepConfigSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setStepConfigId(model.getStepConfigId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStepCode(model.getStepCode());
		soapModel.setStepName(model.getStepName());
		soapModel.setStepType(model.getStepType());
		soapModel.setDossierStatus(model.getDossierStatus());
		soapModel.setDossierSubStatus(model.getDossierSubStatus());
		soapModel.setMenuGroup(model.getMenuGroup());
		soapModel.setMenuStepName(model.getMenuStepName());
		soapModel.setButtonConfig(model.getButtonConfig());

		return soapModel;
	}

	public static StepConfigSoap[] toSoapModels(StepConfig[] models) {
		StepConfigSoap[] soapModels = new StepConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StepConfigSoap[][] toSoapModels(StepConfig[][] models) {
		StepConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StepConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StepConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StepConfigSoap[] toSoapModels(List<StepConfig> models) {
		List<StepConfigSoap> soapModels = new ArrayList<StepConfigSoap>(models.size());

		for (StepConfig model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StepConfigSoap[soapModels.size()]);
	}

	public StepConfigSoap() {
	}

	public long getPrimaryKey() {
		return _stepConfigId;
	}

	public void setPrimaryKey(long pk) {
		setStepConfigId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getStepConfigId() {
		return _stepConfigId;
	}

	public void setStepConfigId(long stepConfigId) {
		_stepConfigId = stepConfigId;
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

	public String getStepCode() {
		return _stepCode;
	}

	public void setStepCode(String stepCode) {
		_stepCode = stepCode;
	}

	public String getStepName() {
		return _stepName;
	}

	public void setStepName(String stepName) {
		_stepName = stepName;
	}

	public int getStepType() {
		return _stepType;
	}

	public void setStepType(int stepType) {
		_stepType = stepType;
	}

	public String getDossierStatus() {
		return _dossierStatus;
	}

	public void setDossierStatus(String dossierStatus) {
		_dossierStatus = dossierStatus;
	}

	public String getDossierSubStatus() {
		return _dossierSubStatus;
	}

	public void setDossierSubStatus(String dossierSubStatus) {
		_dossierSubStatus = dossierSubStatus;
	}

	public String getMenuGroup() {
		return _menuGroup;
	}

	public void setMenuGroup(String menuGroup) {
		_menuGroup = menuGroup;
	}

	public String getMenuStepName() {
		return _menuStepName;
	}

	public void setMenuStepName(String menuStepName) {
		_menuStepName = menuStepName;
	}

	public String getButtonConfig() {
		return _buttonConfig;
	}

	public void setButtonConfig(String buttonConfig) {
		_buttonConfig = buttonConfig;
	}

	private String _uuid;
	private long _stepConfigId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _stepCode;
	private String _stepName;
	private int _stepType;
	private String _dossierStatus;
	private String _dossierSubStatus;
	private String _menuGroup;
	private String _menuStepName;
	private String _buttonConfig;
}