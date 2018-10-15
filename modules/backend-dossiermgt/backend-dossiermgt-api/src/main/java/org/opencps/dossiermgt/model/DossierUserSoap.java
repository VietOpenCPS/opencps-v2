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

import org.opencps.dossiermgt.service.persistence.DossierUserPK;

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
public class DossierUserSoap implements Serializable {
	public static DossierUserSoap toSoapModel(DossierUser model) {
		DossierUserSoap soapModel = new DossierUserSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setUserId(model.getUserId());
		soapModel.setModerator(model.getModerator());
		soapModel.setVisited(model.isVisited());

		return soapModel;
	}

	public static DossierUserSoap[] toSoapModels(DossierUser[] models) {
		DossierUserSoap[] soapModels = new DossierUserSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierUserSoap[][] toSoapModels(DossierUser[][] models) {
		DossierUserSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierUserSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierUserSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierUserSoap[] toSoapModels(List<DossierUser> models) {
		List<DossierUserSoap> soapModels = new ArrayList<DossierUserSoap>(models.size());

		for (DossierUser model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierUserSoap[soapModels.size()]);
	}

	public DossierUserSoap() {
	}

	public DossierUserPK getPrimaryKey() {
		return new DossierUserPK(_dossierId, _userId);
	}

	public void setPrimaryKey(DossierUserPK pk) {
		setDossierId(pk.dossierId);
		setUserId(pk.userId);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierId() {
		return _dossierId;
	}

	public void setDossierId(long dossierId) {
		_dossierId = dossierId;
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

	public boolean getVisited() {
		return _visited;
	}

	public boolean isVisited() {
		return _visited;
	}

	public void setVisited(boolean visited) {
		_visited = visited;
	}

	private String _uuid;
	private long _dossierId;
	private long _userId;
	private int _moderator;
	private boolean _visited;
}