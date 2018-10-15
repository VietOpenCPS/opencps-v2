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

package org.opencps.kyso.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.kyso.model.kyso;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing kyso in entity cache.
 *
 * @author Binhth
 * @see kyso
 * @generated
 */
@ProviderType
public class kysoCacheModel implements CacheModel<kyso>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof kysoCacheModel)) {
			return false;
		}

		kysoCacheModel kysoCacheModel = (kysoCacheModel)obj;

		if (jasperId == kysoCacheModel.jasperId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, jasperId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", jasperId=");
		sb.append(jasperId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public kyso toEntityModel() {
		kysoImpl kysoImpl = new kysoImpl();

		if (uuid == null) {
			kysoImpl.setUuid("");
		}
		else {
			kysoImpl.setUuid(uuid);
		}

		kysoImpl.setJasperId(jasperId);

		kysoImpl.resetOriginalValues();

		return kysoImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		jasperId = objectInput.readLong();
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

		objectOutput.writeLong(jasperId);
	}

	public String uuid;
	public long jasperId;
}