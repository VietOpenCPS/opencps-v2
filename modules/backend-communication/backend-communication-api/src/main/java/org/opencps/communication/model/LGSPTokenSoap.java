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

package org.opencps.communication.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavd
 * @generated
 */
@ProviderType
public class LGSPTokenSoap implements Serializable {
	public static LGSPTokenSoap toSoapModel(LGSPToken model) {
		LGSPTokenSoap soapModel = new LGSPTokenSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setTokenId(model.getTokenId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setToken(model.getToken());
		soapModel.setTokenType(model.getTokenType());
		soapModel.setRefreshToken(model.getRefreshToken());
		soapModel.setExpiryDate(model.getExpiryDate());

		return soapModel;
	}

	public static LGSPTokenSoap[] toSoapModels(LGSPToken[] models) {
		LGSPTokenSoap[] soapModels = new LGSPTokenSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LGSPTokenSoap[][] toSoapModels(LGSPToken[][] models) {
		LGSPTokenSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LGSPTokenSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LGSPTokenSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LGSPTokenSoap[] toSoapModels(List<LGSPToken> models) {
		List<LGSPTokenSoap> soapModels = new ArrayList<LGSPTokenSoap>(models.size());

		for (LGSPToken model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LGSPTokenSoap[soapModels.size()]);
	}

	public LGSPTokenSoap() {
	}

	public long getPrimaryKey() {
		return _tokenId;
	}

	public void setPrimaryKey(long pk) {
		setTokenId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getTokenId() {
		return _tokenId;
	}

	public void setTokenId(long tokenId) {
		_tokenId = tokenId;
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

	public String getToken() {
		return _token;
	}

	public void setToken(String token) {
		_token = token;
	}

	public String getTokenType() {
		return _tokenType;
	}

	public void setTokenType(String tokenType) {
		_tokenType = tokenType;
	}

	public String getRefreshToken() {
		return _refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		_refreshToken = refreshToken;
	}

	public Date getExpiryDate() {
		return _expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		_expiryDate = expiryDate;
	}

	private String _uuid;
	private long _tokenId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _token;
	private String _tokenType;
	private String _refreshToken;
	private Date _expiryDate;
}