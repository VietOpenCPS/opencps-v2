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

package org.opencps.synctracking.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author duongnt
 * @generated
 */
@ProviderType
public class SyncTrackingSoap implements Serializable {
	public static SyncTrackingSoap toSoapModel(SyncTracking model) {
		SyncTrackingSoap soapModel = new SyncTrackingSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setTrackingId(model.getTrackingId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierNo(model.getDossierNo());
		soapModel.setReferenceUid(model.getReferenceUid());
		soapModel.setServerNo(model.getServerNo());
		soapModel.setProtocol(model.getProtocol());
		soapModel.setStateSync(model.getStateSync());
		soapModel.setServiceCode(model.getServiceCode());
		soapModel.setApi(model.getApi());
		soapModel.setFromUnit(model.getFromUnit());
		soapModel.setToUnit(model.getToUnit());
		soapModel.setBodyRequest(model.getBodyRequest());
		soapModel.setResponse(model.getResponse());
		soapModel.setMetaData(model.getMetaData());

		return soapModel;
	}

	public static SyncTrackingSoap[] toSoapModels(SyncTracking[] models) {
		SyncTrackingSoap[] soapModels = new SyncTrackingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SyncTrackingSoap[][] toSoapModels(SyncTracking[][] models) {
		SyncTrackingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SyncTrackingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SyncTrackingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SyncTrackingSoap[] toSoapModels(List<SyncTracking> models) {
		List<SyncTrackingSoap> soapModels = new ArrayList<SyncTrackingSoap>(models.size());

		for (SyncTracking model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SyncTrackingSoap[soapModels.size()]);
	}

	public SyncTrackingSoap() {
	}

	public long getPrimaryKey() {
		return _trackingId;
	}

	public void setPrimaryKey(long pk) {
		setTrackingId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getTrackingId() {
		return _trackingId;
	}

	public void setTrackingId(long trackingId) {
		_trackingId = trackingId;
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

	public String getDossierNo() {
		return _dossierNo;
	}

	public void setDossierNo(String dossierNo) {
		_dossierNo = dossierNo;
	}

	public String getReferenceUid() {
		return _referenceUid;
	}

	public void setReferenceUid(String referenceUid) {
		_referenceUid = referenceUid;
	}

	public String getServerNo() {
		return _serverNo;
	}

	public void setServerNo(String serverNo) {
		_serverNo = serverNo;
	}

	public String getProtocol() {
		return _protocol;
	}

	public void setProtocol(String protocol) {
		_protocol = protocol;
	}

	public int getStateSync() {
		return _stateSync;
	}

	public void setStateSync(int stateSync) {
		_stateSync = stateSync;
	}

	public String getServiceCode() {
		return _serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		_serviceCode = serviceCode;
	}

	public String getApi() {
		return _api;
	}

	public void setApi(String api) {
		_api = api;
	}

	public String getFromUnit() {
		return _fromUnit;
	}

	public void setFromUnit(String fromUnit) {
		_fromUnit = fromUnit;
	}

	public String getToUnit() {
		return _toUnit;
	}

	public void setToUnit(String toUnit) {
		_toUnit = toUnit;
	}

	public String getBodyRequest() {
		return _bodyRequest;
	}

	public void setBodyRequest(String bodyRequest) {
		_bodyRequest = bodyRequest;
	}

	public String getResponse() {
		return _response;
	}

	public void setResponse(String response) {
		_response = response;
	}

	public String getMetaData() {
		return _metaData;
	}

	public void setMetaData(String metaData) {
		_metaData = metaData;
	}

	private String _uuid;
	private long _trackingId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _dossierNo;
	private String _referenceUid;
	private String _serverNo;
	private String _protocol;
	private int _stateSync;
	private String _serviceCode;
	private String _api;
	private String _fromUnit;
	private String _toUnit;
	private String _bodyRequest;
	private String _response;
	private String _metaData;
}