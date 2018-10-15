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

package org.opencps.jasper.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.jasper.model.jasper;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing jasper in entity cache.
 *
 * @author Binhth
 * @see jasper
 * @generated
 */
@ProviderType
public class jasperCacheModel implements CacheModel<jasper>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof jasperCacheModel)) {
			return false;
		}

		jasperCacheModel jasperCacheModel = (jasperCacheModel)obj;

		if (jasperId == jasperCacheModel.jasperId) {
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
	public jasper toEntityModel() {
		jasperImpl jasperImpl = new jasperImpl();

		if (uuid == null) {
			jasperImpl.setUuid("");
		}
		else {
			jasperImpl.setUuid(uuid);
		}

		jasperImpl.setJasperId(jasperId);

		jasperImpl.resetOriginalValues();

		return jasperImpl;
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