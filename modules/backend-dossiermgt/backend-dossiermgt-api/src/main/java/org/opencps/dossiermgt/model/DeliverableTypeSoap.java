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
public class DeliverableTypeSoap implements Serializable {
	public static DeliverableTypeSoap toSoapModel(DeliverableType model) {
		DeliverableTypeSoap soapModel = new DeliverableTypeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDeliverableTypeId(model.getDeliverableTypeId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTypeCode(model.getTypeCode());
		soapModel.setTypeName(model.getTypeName());
		soapModel.setFormScript(model.getFormScript());
		soapModel.setFormReport(model.getFormReport());
		soapModel.setCodePattern(model.getCodePattern());
		soapModel.setCounter(model.getCounter());
		soapModel.setMappingData(model.getMappingData());
		soapModel.setDocSync(model.getDocSync());
		soapModel.setGovAgencies(model.getGovAgencies());

		return soapModel;
	}

	public static DeliverableTypeSoap[] toSoapModels(DeliverableType[] models) {
		DeliverableTypeSoap[] soapModels = new DeliverableTypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DeliverableTypeSoap[][] toSoapModels(
		DeliverableType[][] models) {
		DeliverableTypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DeliverableTypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DeliverableTypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DeliverableTypeSoap[] toSoapModels(
		List<DeliverableType> models) {
		List<DeliverableTypeSoap> soapModels = new ArrayList<DeliverableTypeSoap>(models.size());

		for (DeliverableType model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DeliverableTypeSoap[soapModels.size()]);
	}

	public DeliverableTypeSoap() {
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

	public String getFormScript() {
		return _formScript;
	}

	public void setFormScript(String formScript) {
		_formScript = formScript;
	}

	public String getFormReport() {
		return _formReport;
	}

	public void setFormReport(String formReport) {
		_formReport = formReport;
	}

	public String getCodePattern() {
		return _codePattern;
	}

	public void setCodePattern(String codePattern) {
		_codePattern = codePattern;
	}

	public String getCounter() {
		return _counter;
	}

	public void setCounter(String counter) {
		_counter = counter;
	}

	public String getMappingData() {
		return _mappingData;
	}

	public void setMappingData(String mappingData) {
		_mappingData = mappingData;
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
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _typeCode;
	private String _typeName;
	private String _formScript;
	private String _formReport;
	private String _codePattern;
	private String _counter;
	private String _mappingData;
	private int _docSync;
	private String _govAgencies;
}