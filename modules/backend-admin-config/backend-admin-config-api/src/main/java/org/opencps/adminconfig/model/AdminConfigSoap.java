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

package org.opencps.adminconfig.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author binhth
 * @generated
 */
@ProviderType
public class AdminConfigSoap implements Serializable {
	public static AdminConfigSoap toSoapModel(AdminConfig model) {
		AdminConfigSoap soapModel = new AdminConfigSoap();

		soapModel.setId(model.getId());
		soapModel.setCode(model.getCode());
		soapModel.setName(model.getName());
		soapModel.setBundleName(model.getBundleName());
		soapModel.setModelName(model.getModelName());
		soapModel.setServiceUtilName(model.getServiceUtilName());
		soapModel.setHeadersName(model.getHeadersName());
		soapModel.setColumns(model.getColumns());
		soapModel.setDetailColumns(model.getDetailColumns());
		soapModel.setExtForm(model.isExtForm());
		soapModel.setGroupFilter(model.isGroupFilter());
		soapModel.setPublicManager(model.isPublicManager());

		return soapModel;
	}

	public static AdminConfigSoap[] toSoapModels(AdminConfig[] models) {
		AdminConfigSoap[] soapModels = new AdminConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AdminConfigSoap[][] toSoapModels(AdminConfig[][] models) {
		AdminConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AdminConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AdminConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AdminConfigSoap[] toSoapModels(List<AdminConfig> models) {
		List<AdminConfigSoap> soapModels = new ArrayList<AdminConfigSoap>(models.size());

		for (AdminConfig model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AdminConfigSoap[soapModels.size()]);
	}

	public AdminConfigSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getBundleName() {
		return _bundleName;
	}

	public void setBundleName(String bundleName) {
		_bundleName = bundleName;
	}

	public String getModelName() {
		return _modelName;
	}

	public void setModelName(String modelName) {
		_modelName = modelName;
	}

	public String getServiceUtilName() {
		return _serviceUtilName;
	}

	public void setServiceUtilName(String serviceUtilName) {
		_serviceUtilName = serviceUtilName;
	}

	public String getHeadersName() {
		return _headersName;
	}

	public void setHeadersName(String headersName) {
		_headersName = headersName;
	}

	public String getColumns() {
		return _columns;
	}

	public void setColumns(String columns) {
		_columns = columns;
	}

	public String getDetailColumns() {
		return _detailColumns;
	}

	public void setDetailColumns(String detailColumns) {
		_detailColumns = detailColumns;
	}

	public boolean getExtForm() {
		return _extForm;
	}

	public boolean isExtForm() {
		return _extForm;
	}

	public void setExtForm(boolean extForm) {
		_extForm = extForm;
	}

	public boolean getGroupFilter() {
		return _groupFilter;
	}

	public boolean isGroupFilter() {
		return _groupFilter;
	}

	public void setGroupFilter(boolean groupFilter) {
		_groupFilter = groupFilter;
	}

	public boolean getPublicManager() {
		return _publicManager;
	}

	public boolean isPublicManager() {
		return _publicManager;
	}

	public void setPublicManager(boolean publicManager) {
		_publicManager = publicManager;
	}

	private long _id;
	private String _code;
	private String _name;
	private String _bundleName;
	private String _modelName;
	private String _serviceUtilName;
	private String _headersName;
	private String _columns;
	private String _detailColumns;
	private boolean _extForm;
	private boolean _groupFilter;
	private boolean _publicManager;
}