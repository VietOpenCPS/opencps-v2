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

import org.opencps.dossiermgt.model.DossierActionSync;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DossierActionSync in entity cache.
 *
 * @author huymq
 * @see DossierActionSync
 * @generated
 */
@ProviderType
public class DossierActionSyncCacheModel implements CacheModel<DossierActionSync>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierActionSyncCacheModel)) {
			return false;
		}

		DossierActionSyncCacheModel dossierActionSyncCacheModel = (DossierActionSyncCacheModel)obj;

		if (dossierActionSyncId == dossierActionSyncCacheModel.dossierActionSyncId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierActionSyncId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dossierActionSyncId=");
		sb.append(dossierActionSyncId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", dossierId=");
		sb.append(dossierId);
		sb.append(", dossierActionId=");
		sb.append(dossierActionId);
		sb.append(", createDossier=");
		sb.append(createDossier);
		sb.append(", referenceUid=");
		sb.append(referenceUid);
		sb.append(", actionCode=");
		sb.append(actionCode);
		sb.append(", actionUser=");
		sb.append(actionUser);
		sb.append(", actionNote=");
		sb.append(actionNote);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierActionSync toEntityModel() {
		DossierActionSyncImpl dossierActionSyncImpl = new DossierActionSyncImpl();

		if (uuid == null) {
			dossierActionSyncImpl.setUuid("");
		}
		else {
			dossierActionSyncImpl.setUuid(uuid);
		}

		dossierActionSyncImpl.setDossierActionSyncId(dossierActionSyncId);
		dossierActionSyncImpl.setCompanyId(companyId);
		dossierActionSyncImpl.setGroupId(groupId);
		dossierActionSyncImpl.setUserId(userId);

		if (userName == null) {
			dossierActionSyncImpl.setUserName("");
		}
		else {
			dossierActionSyncImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dossierActionSyncImpl.setCreateDate(null);
		}
		else {
			dossierActionSyncImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dossierActionSyncImpl.setModifiedDate(null);
		}
		else {
			dossierActionSyncImpl.setModifiedDate(new Date(modifiedDate));
		}

		dossierActionSyncImpl.setDossierId(dossierId);
		dossierActionSyncImpl.setDossierActionId(dossierActionId);
		dossierActionSyncImpl.setCreateDossier(createDossier);

		if (referenceUid == null) {
			dossierActionSyncImpl.setReferenceUid("");
		}
		else {
			dossierActionSyncImpl.setReferenceUid(referenceUid);
		}

		if (actionCode == null) {
			dossierActionSyncImpl.setActionCode("");
		}
		else {
			dossierActionSyncImpl.setActionCode(actionCode);
		}

		if (actionUser == null) {
			dossierActionSyncImpl.setActionUser("");
		}
		else {
			dossierActionSyncImpl.setActionUser(actionUser);
		}

		if (actionNote == null) {
			dossierActionSyncImpl.setActionNote("");
		}
		else {
			dossierActionSyncImpl.setActionNote(actionNote);
		}

		dossierActionSyncImpl.resetOriginalValues();

		return dossierActionSyncImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dossierActionSyncId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dossierId = objectInput.readLong();

		dossierActionId = objectInput.readLong();

		createDossier = objectInput.readBoolean();
		referenceUid = objectInput.readUTF();
		actionCode = objectInput.readUTF();
		actionUser = objectInput.readUTF();
		actionNote = objectInput.readUTF();
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

		objectOutput.writeLong(dossierActionSyncId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(dossierId);

		objectOutput.writeLong(dossierActionId);

		objectOutput.writeBoolean(createDossier);

		if (referenceUid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(referenceUid);
		}

		if (actionCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(actionCode);
		}

		if (actionUser == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(actionUser);
		}

		if (actionNote == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(actionNote);
		}
	}

	public String uuid;
	public long dossierActionSyncId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long dossierId;
	public long dossierActionId;
	public boolean createDossier;
	public String referenceUid;
	public String actionCode;
	public String actionUser;
	public String actionNote;
}