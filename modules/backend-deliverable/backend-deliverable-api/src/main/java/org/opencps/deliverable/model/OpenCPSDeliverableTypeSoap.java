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

package org.opencps.deliverable.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author binhth
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableTypeSoap implements Serializable {
	public static OpenCPSDeliverableTypeSoap toSoapModel(
		OpenCPSDeliverableType model) {
		OpenCPSDeliverableTypeSoap soapModel = new OpenCPSDeliverableTypeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDeliverableTypeId(model.getDeliverableTypeId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTypeCode(model.getTypeCode());
		soapModel.setTypeName(model.getTypeName());
		soapModel.setFormScriptFileId(model.getFormScriptFileId());
		soapModel.setFormReportFileId(model.getFormReportFileId());
		soapModel.setCodePattern(model.getCodePattern());
		soapModel.setCounter(model.getCounter());
		soapModel.setMappingData(model.getMappingData());
		soapModel.setDataConfig(model.getDataConfig());
		soapModel.setTableConfig(model.getTableConfig());
		soapModel.setDocSync(model.getDocSync());
		soapModel.setGovAgencies(model.getGovAgencies());

		return soapModel;
	}

	public static OpenCPSDeliverableTypeSoap[] toSoapModels(
		OpenCPSDeliverableType[] models) {
		OpenCPSDeliverableTypeSoap[] soapModels = new OpenCPSDeliverableTypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OpenCPSDeliverableTypeSoap[][] toSoapModels(
		OpenCPSDeliverableType[][] models) {
		OpenCPSDeliverableTypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OpenCPSDeliverableTypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OpenCPSDeliverableTypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OpenCPSDeliverableTypeSoap[] toSoapModels(
		List<OpenCPSDeliverableType> models) {
		List<OpenCPSDeliverableTypeSoap> soapModels = new ArrayList<OpenCPSDeliverableTypeSoap>(models.size());

		for (OpenCPSDeliverableType model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OpenCPSDeliverableTypeSoap[soapModels.size()]);
	}

	public OpenCPSDeliverableTypeSoap() {
	}

	public long getPrimaryKey() {
		return _deliverableTypeId;
	}

	public void setPrimaryKey(long pk) {
		setDeliverableTypeId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDeliverableTypeId() {
		return _deliverableTypeId;
	}

	public void setDeliverableTypeId(long deliverableTypeId) {
		_deliverableTypeId = deliverableTypeId;
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

	public String getTypeCode() {
		return _typeCode;
	}

	public void setTypeCode(String typeCode) {
		_typeCode = typeCode;
	}

	public String getTypeName() {
		return _typeName;
	}

	public void setTypeName(String typeName) {
		_typeName = typeName;
	}

	public long getFormScriptFileId() {
		return _formScriptFileId;
	}

	public void setFormScriptFileId(long formScriptFileId) {
		_formScriptFileId = formScriptFileId;
	}

	public long getFormReportFileId() {
		return _formReportFileId;
	}

	public void setFormReportFileId(long formReportFileId) {
		_formReportFileId = formReportFileId;
	}

	public String getCodePattern() {
		return _codePattern;
	}

	public void setCodePattern(String codePattern) {
		_codePattern = codePattern;
	}

	public long getCounter() {
		return _counter;
	}

	public void setCounter(long counter) {
		_counter = counter;
	}

	public String getMappingData() {
		return _mappingData;
	}

	public void setMappingData(String mappingData) {
		_mappingData = mappingData;
	}

	public String getDataConfig() {
		return _dataConfig;
	}

	public void setDataConfig(String dataConfig) {
		_dataConfig = dataConfig;
	}

	public String getTableConfig() {
		return _tableConfig;
	}

	public void setTableConfig(String tableConfig) {
		_tableConfig = tableConfig;
	}

	public int getDocSync() {
		return _docSync;
	}

	public void setDocSync(int docSync) {
		_docSync = docSync;
	}

	public String getGovAgencies() {
		return _govAgencies;
	}

	public void setGovAgencies(String govAgencies) {
		_govAgencies = govAgencies;
	}

	private String _uuid;
	private long _deliverableTypeId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _typeCode;
	private String _typeName;
	private long _formScriptFileId;
	private long _formReportFileId;
	private String _codePattern;
	private long _counter;
	private String _mappingData;
	private String _dataConfig;
	private String _tableConfig;
	private int _docSync;
	private String _govAgencies;
}