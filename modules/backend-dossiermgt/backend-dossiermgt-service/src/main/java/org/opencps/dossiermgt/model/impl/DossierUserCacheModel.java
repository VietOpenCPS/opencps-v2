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

package org.opencps.dossiermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.dossiermgt.model.DossierUser;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DossierUser in entity cache.
 *
 * @author huymq
 * @see DossierUser
 * @generated
 */
@ProviderType
public class DossierUserCacheModel implements CacheModel<DossierUser>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierUserCacheModel)) {
			return false;
		}

		DossierUserCacheModel dossierUserCacheModel = (DossierUserCacheModel)obj;

		if (dossierUserId == dossierUserCacheModel.dossierUserId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierUserId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dossierUserId=");
		sb.append(dossierUserId);
		sb.append(", dossierId=");
		sb.append(dossierId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", moderator=");
		sb.append(moderator);
		sb.append(", visited=");
		sb.append(visited);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierUser toEntityModel() {
		DossierUserImpl dossierUserImpl = new DossierUserImpl();

		if (uuid == null) {
			dossierUserImpl.setUuid("");
		}
		else {
			dossierUserImpl.setUuid(uuid);
		}

		dossierUserImpl.setDossierUserId(dossierUserId);
		dossierUserImpl.setDossierId(dossierId);
		dossierUserImpl.setUserId(userId);
		dossierUserImpl.setModerator(moderator);
		dossierUserImpl.setVisited(visited);

		dossierUserImpl.resetOriginalValues();

		return dossierUserImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dossierUserId = objectInput.readLong();

		dossierId = objectInput.readLong();

		userId = objectInput.readLong();

		moderator = objectInput.readInt();

		visited = objectInput.readBoolean();
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

		objectOutput.writeLong(dossierUserId);

		objectOutput.writeLong(dossierId);

		objectOutput.writeLong(userId);

		objectOutput.writeInt(moderator);

		objectOutput.writeBoolean(visited);
	}

	public String uuid;
	public long dossierUserId;
	public long dossierId;
	public long userId;
	public int moderator;
	public boolean visited;
}