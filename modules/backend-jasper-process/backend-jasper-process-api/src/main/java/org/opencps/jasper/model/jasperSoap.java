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

package org.opencps.jasper.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Binhth
 * @generated
 */
@ProviderType
public class jasperSoap implements Serializable {
	public static jasperSoap toSoapModel(jasper model) {
		jasperSoap soapModel = new jasperSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setJasperId(model.getJasperId());

		return soapModel;
	}

	public static jasperSoap[] toSoapModels(jasper[] models) {
		jasperSoap[] soapModels = new jasperSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static jasperSoap[][] toSoapModels(jasper[][] models) {
		jasperSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new jasperSoap[models.length][models[0].length];
		}
		else {
			soapModels = new jasperSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static jasperSoap[] toSoapModels(List<jasper> models) {
		List<jasperSoap> soapModels = new ArrayList<jasperSoap>(models.size());

		for (jasper model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new jasperSoap[soapModels.size()]);
	}

	public jasperSoap() {
	}

	public long getPrimaryKey() {
		return _jasperId;
	}

	public void setPrimaryKey(long pk) {
		setJasperId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getJasperId() {
		return _jasperId;
	}

	public void setJasperId(long jasperId) {
		_jasperId = jasperId;
	}

	private String _uuid;
	private long _jasperId;
}