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

package org.opencps.kyso.model;

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
public class kysoSoap implements Serializable {
	public static kysoSoap toSoapModel(kyso model) {
		kysoSoap soapModel = new kysoSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setJasperId(model.getJasperId());

		return soapModel;
	}

	public static kysoSoap[] toSoapModels(kyso[] models) {
		kysoSoap[] soapModels = new kysoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static kysoSoap[][] toSoapModels(kyso[][] models) {
		kysoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new kysoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new kysoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static kysoSoap[] toSoapModels(List<kyso> models) {
		List<kysoSoap> soapModels = new ArrayList<kysoSoap>(models.size());

		for (kyso model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new kysoSoap[soapModels.size()]);
	}

	public kysoSoap() {
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