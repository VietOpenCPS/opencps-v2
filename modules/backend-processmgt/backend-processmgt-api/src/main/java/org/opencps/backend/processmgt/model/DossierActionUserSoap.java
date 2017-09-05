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

package org.opencps.backend.processmgt.model;

import aQute.bnd.annotation.ProviderType;

import org.opencps.backend.processmgt.service.persistence.DossierActionUserPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavu
 * @generated
 */
@ProviderType
public class DossierActionUserSoap implements Serializable {
	public static DossierActionUserSoap toSoapModel(DossierActionUser model) {
		DossierActionUserSoap soapModel = new DossierActionUserSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierActionId(model.getDossierActionId());
		soapModel.setUserId(model.getUserId());
		soapModel.setModerator(model.getModerator());

		return soapModel;
	}

	public static DossierActionUserSoap[] toSoapModels(
		DossierActionUser[] models) {
		DossierActionUserSoap[] soapModels = new DossierActionUserSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierActionUserSoap[][] toSoapModels(
		DossierActionUser[][] models) {
		DossierActionUserSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierActionUserSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierActionUserSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierActionUserSoap[] toSoapModels(
		List<DossierActionUser> models) {
		List<DossierActionUserSoap> soapModels = new ArrayList<DossierActionUserSoap>(models.size());

		for (DossierActionUser model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierActionUserSoap[soapModels.size()]);
	}

	public DossierActionUserSoap() {
	}

	public DossierActionUserPK getPrimaryKey() {
		return new DossierActionUserPK(_dossierActionId, _userId);
	}

	public void setPrimaryKey(DossierActionUserPK pk) {
		setDossierActionId(pk.dossierActionId);
		setUserId(pk.userId);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierActionId() {
		return _dossierActionId;
	}

	public void setDossierActionId(long dossierActionId) {
		_dossierActionId = dossierActionId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public int getModerator() {
		return _moderator;
	}

	public void setModerator(int moderator) {
		_moderator = moderator;
	}

	private String _uuid;
	private long _dossierActionId;
	private long _userId;
	private int _moderator;
}