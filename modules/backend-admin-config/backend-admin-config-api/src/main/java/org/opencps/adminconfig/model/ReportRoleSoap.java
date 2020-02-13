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
public class ReportRoleSoap implements Serializable {
	public static ReportRoleSoap toSoapModel(ReportRole model) {
		ReportRoleSoap soapModel = new ReportRoleSoap();

		soapModel.setReportRoleId(model.getReportRoleId());
		soapModel.setDynamicReportId(model.getDynamicReportId());
		soapModel.setRoleId(model.getRoleId());

		return soapModel;
	}

	public static ReportRoleSoap[] toSoapModels(ReportRole[] models) {
		ReportRoleSoap[] soapModels = new ReportRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ReportRoleSoap[][] toSoapModels(ReportRole[][] models) {
		ReportRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ReportRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ReportRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ReportRoleSoap[] toSoapModels(List<ReportRole> models) {
		List<ReportRoleSoap> soapModels = new ArrayList<ReportRoleSoap>(models.size());

		for (ReportRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ReportRoleSoap[soapModels.size()]);
	}

	public ReportRoleSoap() {
	}

	public long getPrimaryKey() {
		return _reportRoleId;
	}

	public void setPrimaryKey(long pk) {
		setReportRoleId(pk);
	}

	public long getReportRoleId() {
		return _reportRoleId;
	}

	public void setReportRoleId(long reportRoleId) {
		_reportRoleId = reportRoleId;
	}

	public long getDynamicReportId() {
		return _dynamicReportId;
	}

	public void setDynamicReportId(long dynamicReportId) {
		_dynamicReportId = dynamicReportId;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	private long _reportRoleId;
	private long _dynamicReportId;
	private long _roleId;
}