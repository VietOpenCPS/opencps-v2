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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author huymq
 * @generated
 */
@ProviderType
public class CsdlDcServiceInfoSoap implements Serializable {
	public static CsdlDcServiceInfoSoap toSoapModel(CsdlDcServiceInfo model) {
		CsdlDcServiceInfoSoap soapModel = new CsdlDcServiceInfoSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setIdDcService(model.getIdDcService());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setServiceCode(model.getServiceCode());
		soapModel.setServiceCodeDvcqg(model.getServiceCodeDvcqg());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static CsdlDcServiceInfoSoap[] toSoapModels(
		CsdlDcServiceInfo[] models) {
		CsdlDcServiceInfoSoap[] soapModels = new CsdlDcServiceInfoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CsdlDcServiceInfoSoap[][] toSoapModels(
		CsdlDcServiceInfo[][] models) {
		CsdlDcServiceInfoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CsdlDcServiceInfoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CsdlDcServiceInfoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CsdlDcServiceInfoSoap[] toSoapModels(
		List<CsdlDcServiceInfo> models) {
		List<CsdlDcServiceInfoSoap> soapModels = new ArrayList<CsdlDcServiceInfoSoap>(models.size());

		for (CsdlDcServiceInfo model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CsdlDcServiceInfoSoap[soapModels.size()]);
	}

	public CsdlDcServiceInfoSoap() {
	}

	public long getPrimaryKey() {
		return _idDcService;
	}

	public void setPrimaryKey(long pk) {
		setIdDcService(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getIdDcService() {
		return _idDcService;
	}

	public void setIdDcService(long idDcService) {
		_idDcService = idDcService;
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

	public String getServiceCode() {
		return _serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		_serviceCode = serviceCode;
	}

	public String getServiceCodeDvcqg() {
		return _serviceCodeDvcqg;
	}

	public void setServiceCodeDvcqg(String serviceCodeDvcqg) {
		_serviceCodeDvcqg = serviceCodeDvcqg;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private String _uuid;
	private long _idDcService;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _serviceCode;
	private String _serviceCodeDvcqg;
	private int _status;
}