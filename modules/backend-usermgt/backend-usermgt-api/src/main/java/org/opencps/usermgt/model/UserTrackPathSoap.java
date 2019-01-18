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

package org.opencps.usermgt.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavu
 * @generated
 */
@ProviderType
public class UserTrackPathSoap implements Serializable {
	public static UserTrackPathSoap toSoapModel(UserTrackPath model) {
		UserTrackPathSoap soapModel = new UserTrackPathSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setUserTrackPathId(model.getUserTrackPathId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setUserLoginId(model.getUserLoginId());
		soapModel.setPath(model.getPath());
		soapModel.setPathDate(model.getPathDate());

		return soapModel;
	}

	public static UserTrackPathSoap[] toSoapModels(UserTrackPath[] models) {
		UserTrackPathSoap[] soapModels = new UserTrackPathSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserTrackPathSoap[][] toSoapModels(UserTrackPath[][] models) {
		UserTrackPathSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserTrackPathSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserTrackPathSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserTrackPathSoap[] toSoapModels(List<UserTrackPath> models) {
		List<UserTrackPathSoap> soapModels = new ArrayList<UserTrackPathSoap>(models.size());

		for (UserTrackPath model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserTrackPathSoap[soapModels.size()]);
	}

	public UserTrackPathSoap() {
	}

	public long getPrimaryKey() {
		return _userTrackPathId;
	}

	public void setPrimaryKey(long pk) {
		setUserTrackPathId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getUserTrackPathId() {
		return _userTrackPathId;
	}

	public void setUserTrackPathId(long userTrackPathId) {
		_userTrackPathId = userTrackPathId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getUserLoginId() {
		return _userLoginId;
	}

	public void setUserLoginId(long userLoginId) {
		_userLoginId = userLoginId;
	}

	public String getPath() {
		return _path;
	}

	public void setPath(String path) {
		_path = path;
	}

	public Date getPathDate() {
		return _pathDate;
	}

	public void setPathDate(Date pathDate) {
		_pathDate = pathDate;
	}

	private String _uuid;
	private long _userTrackPathId;
	private long _companyId;
	private Date _modifiedDate;
	private long _userLoginId;
	private String _path;
	private Date _pathDate;
}