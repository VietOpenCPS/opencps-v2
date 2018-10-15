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
public class PreferencesSoap implements Serializable {
	public static PreferencesSoap toSoapModel(Preferences model) {
		PreferencesSoap soapModel = new PreferencesSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPreferencesId(model.getPreferencesId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setPreferences(model.getPreferences());

		return soapModel;
	}

	public static PreferencesSoap[] toSoapModels(Preferences[] models) {
		PreferencesSoap[] soapModels = new PreferencesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PreferencesSoap[][] toSoapModels(Preferences[][] models) {
		PreferencesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PreferencesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PreferencesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PreferencesSoap[] toSoapModels(List<Preferences> models) {
		List<PreferencesSoap> soapModels = new ArrayList<PreferencesSoap>(models.size());

		for (Preferences model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PreferencesSoap[soapModels.size()]);
	}

	public PreferencesSoap() {
	}

	public long getPrimaryKey() {
		return _preferencesId;
	}

	public void setPrimaryKey(long pk) {
		setPreferencesId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPreferencesId() {
		return _preferencesId;
	}

	public void setPreferencesId(long preferencesId) {
		_preferencesId = preferencesId;
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

	public String getPreferences() {
		return _preferences;
	}

	public void setPreferences(String preferences) {
		_preferences = preferences;
	}

	private String _uuid;
	private long _preferencesId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _preferences;
}