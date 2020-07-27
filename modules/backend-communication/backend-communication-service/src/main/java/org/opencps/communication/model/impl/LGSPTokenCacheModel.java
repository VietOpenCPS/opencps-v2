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

package org.opencps.communication.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.communication.model.LGSPToken;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LGSPToken in entity cache.
 *
 * @author khoavd
 * @see LGSPToken
 * @generated
 */
@ProviderType
public class LGSPTokenCacheModel implements CacheModel<LGSPToken>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LGSPTokenCacheModel)) {
			return false;
		}

		LGSPTokenCacheModel lgspTokenCacheModel = (LGSPTokenCacheModel)obj;

		if (tokenId == lgspTokenCacheModel.tokenId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, tokenId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", tokenId=");
		sb.append(tokenId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", token=");
		sb.append(token);
		sb.append(", tokenType=");
		sb.append(tokenType);
		sb.append(", refreshToken=");
		sb.append(refreshToken);
		sb.append(", expiryDate=");
		sb.append(expiryDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LGSPToken toEntityModel() {
		LGSPTokenImpl lgspTokenImpl = new LGSPTokenImpl();

		if (uuid == null) {
			lgspTokenImpl.setUuid("");
		}
		else {
			lgspTokenImpl.setUuid(uuid);
		}

		lgspTokenImpl.setTokenId(tokenId);

		if (createDate == Long.MIN_VALUE) {
			lgspTokenImpl.setCreateDate(null);
		}
		else {
			lgspTokenImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			lgspTokenImpl.setModifiedDate(null);
		}
		else {
			lgspTokenImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (token == null) {
			lgspTokenImpl.setToken("");
		}
		else {
			lgspTokenImpl.setToken(token);
		}

		if (tokenType == null) {
			lgspTokenImpl.setTokenType("");
		}
		else {
			lgspTokenImpl.setTokenType(tokenType);
		}

		if (refreshToken == null) {
			lgspTokenImpl.setRefreshToken("");
		}
		else {
			lgspTokenImpl.setRefreshToken(refreshToken);
		}

		if (expiryDate == Long.MIN_VALUE) {
			lgspTokenImpl.setExpiryDate(null);
		}
		else {
			lgspTokenImpl.setExpiryDate(new Date(expiryDate));
		}

		lgspTokenImpl.resetOriginalValues();

		return lgspTokenImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		tokenId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		token = objectInput.readUTF();
		tokenType = objectInput.readUTF();
		refreshToken = objectInput.readUTF();
		expiryDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(tokenId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (token == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(token);
		}

		if (tokenType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tokenType);
		}

		if (refreshToken == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(refreshToken);
		}

		objectOutput.writeLong(expiryDate);
	}

	public String uuid;
	public long tokenId;
	public long createDate;
	public long modifiedDate;
	public String token;
	public String tokenType;
	public String refreshToken;
	public long expiryDate;
}