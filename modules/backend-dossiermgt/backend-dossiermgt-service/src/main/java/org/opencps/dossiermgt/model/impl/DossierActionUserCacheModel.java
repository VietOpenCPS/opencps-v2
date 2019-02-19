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

import org.opencps.dossiermgt.model.DossierActionUser;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DossierActionUser in entity cache.
 *
 * @author huymq
 * @see DossierActionUser
 * @generated
 */
@ProviderType
public class DossierActionUserCacheModel implements CacheModel<DossierActionUser>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierActionUserCacheModel)) {
			return false;
		}

		DossierActionUserCacheModel dossierActionUserCacheModel = (DossierActionUserCacheModel)obj;

		if (dossierActionUserId == dossierActionUserCacheModel.dossierActionUserId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierActionUserId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dossierActionUserId=");
		sb.append(dossierActionUserId);
		sb.append(", dossierActionId=");
		sb.append(dossierActionId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", dossierId=");
		sb.append(dossierId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append(", stepCode=");
		sb.append(stepCode);
		sb.append(", moderator=");
		sb.append(moderator);
		sb.append(", assigned=");
		sb.append(assigned);
		sb.append(", visited=");
		sb.append(visited);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierActionUser toEntityModel() {
		DossierActionUserImpl dossierActionUserImpl = new DossierActionUserImpl();

		if (uuid == null) {
			dossierActionUserImpl.setUuid("");
		}
		else {
			dossierActionUserImpl.setUuid(uuid);
		}

		dossierActionUserImpl.setDossierActionUserId(dossierActionUserId);
		dossierActionUserImpl.setDossierActionId(dossierActionId);
		dossierActionUserImpl.setUserId(userId);
		dossierActionUserImpl.setDossierId(dossierId);
		dossierActionUserImpl.setRoleId(roleId);

		if (stepCode == null) {
			dossierActionUserImpl.setStepCode("");
		}
		else {
			dossierActionUserImpl.setStepCode(stepCode);
		}

		dossierActionUserImpl.setModerator(moderator);
		dossierActionUserImpl.setAssigned(assigned);
		dossierActionUserImpl.setVisited(visited);

		dossierActionUserImpl.resetOriginalValues();

		return dossierActionUserImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dossierActionUserId = objectInput.readLong();

		dossierActionId = objectInput.readLong();

		userId = objectInput.readLong();

		dossierId = objectInput.readLong();

		roleId = objectInput.readLong();
		stepCode = objectInput.readUTF();

		moderator = objectInput.readInt();

		assigned = objectInput.readInt();

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

		objectOutput.writeLong(dossierActionUserId);

		objectOutput.writeLong(dossierActionId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(dossierId);

		objectOutput.writeLong(roleId);

		if (stepCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stepCode);
		}

		objectOutput.writeInt(moderator);

		objectOutput.writeInt(assigned);

		objectOutput.writeBoolean(visited);
	}

	public String uuid;
	public long dossierActionUserId;
	public long dossierActionId;
	public long userId;
	public long dossierId;
	public long roleId;
	public String stepCode;
	public int moderator;
	public int assigned;
	public boolean visited;
}