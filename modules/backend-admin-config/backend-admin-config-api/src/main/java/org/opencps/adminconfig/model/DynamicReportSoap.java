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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author binhth
 * @generated
 */
@ProviderType
public class DynamicReportSoap implements Serializable {
	public static DynamicReportSoap toSoapModel(DynamicReport model) {
		DynamicReportSoap soapModel = new DynamicReportSoap();

		soapModel.setDynamicReportId(model.getDynamicReportId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSharing(model.getSharing());
		soapModel.setReportName(model.getReportName());
		soapModel.setReportCode(model.getReportCode());
		soapModel.setFilterConfig(model.getFilterConfig());
		soapModel.setTableConfig(model.getTableConfig());
		soapModel.setUserConfig(model.getUserConfig());
		soapModel.setReportType(model.getReportType());

		return soapModel;
	}

	public static DynamicReportSoap[] toSoapModels(DynamicReport[] models) {
		DynamicReportSoap[] soapModels = new DynamicReportSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DynamicReportSoap[][] toSoapModels(DynamicReport[][] models) {
		DynamicReportSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DynamicReportSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DynamicReportSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DynamicReportSoap[] toSoapModels(List<DynamicReport> models) {
		List<DynamicReportSoap> soapModels = new ArrayList<DynamicReportSoap>(models.size());

		for (DynamicReport model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DynamicReportSoap[soapModels.size()]);
	}

	public DynamicReportSoap() {
	}

	public long getPrimaryKey() {
		return _dynamicReportId;
	}

	public void setPrimaryKey(long pk) {
		setDynamicReportId(pk);
	}

	public long getDynamicReportId() {
		return _dynamicReportId;
	}

	public void setDynamicReportId(long dynamicReportId) {
		_dynamicReportId = dynamicReportId;
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

	public int getSharing() {
		return _sharing;
	}

	public void setSharing(int sharing) {
		_sharing = sharing;
	}

	public String getReportName() {
		return _reportName;
	}

	public void setReportName(String reportName) {
		_reportName = reportName;
	}

	public String getReportCode() {
		return _reportCode;
	}

	public void setReportCode(String reportCode) {
		_reportCode = reportCode;
	}

	public String getFilterConfig() {
		return _filterConfig;
	}

	public void setFilterConfig(String filterConfig) {
		_filterConfig = filterConfig;
	}

	public String getTableConfig() {
		return _tableConfig;
	}

	public void setTableConfig(String tableConfig) {
		_tableConfig = tableConfig;
	}

	public String getUserConfig() {
		return _userConfig;
	}

	public void setUserConfig(String userConfig) {
		_userConfig = userConfig;
	}

	public String getReportType() {
		return _reportType;
	}

	public void setReportType(String reportType) {
		_reportType = reportType;
	}

	private long _dynamicReportId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _sharing;
	private String _reportName;
	private String _reportCode;
	private String _filterConfig;
	private String _tableConfig;
	private String _userConfig;
	private String _reportType;
}