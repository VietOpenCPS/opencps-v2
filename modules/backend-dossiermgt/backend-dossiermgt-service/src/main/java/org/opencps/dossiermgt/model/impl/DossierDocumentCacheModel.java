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

import org.opencps.dossiermgt.model.DossierDocument;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DossierDocument in entity cache.
 *
 * @author huymq
 * @see DossierDocument
 * @generated
 */
@ProviderType
public class DossierDocumentCacheModel implements CacheModel<DossierDocument>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierDocumentCacheModel)) {
			return false;
		}

		DossierDocumentCacheModel dossierDocumentCacheModel = (DossierDocumentCacheModel)obj;

		if (DossierDocumentId == dossierDocumentCacheModel.DossierDocumentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, DossierDocumentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", DossierDocumentId=");
		sb.append(DossierDocumentId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", dossierId=");
		sb.append(dossierId);
		sb.append(", referenceUid=");
		sb.append(referenceUid);
		sb.append(", dossierActionId=");
		sb.append(dossierActionId);
		sb.append(", documentType=");
		sb.append(documentType);
		sb.append(", documentName=");
		sb.append(documentName);
		sb.append(", documentCode=");
		sb.append(documentCode);
		sb.append(", documentFileId=");
		sb.append(documentFileId);
		sb.append(", docSync=");
		sb.append(docSync);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierDocument toEntityModel() {
		DossierDocumentImpl dossierDocumentImpl = new DossierDocumentImpl();

		if (uuid == null) {
			dossierDocumentImpl.setUuid("");
		}
		else {
			dossierDocumentImpl.setUuid(uuid);
		}

		dossierDocumentImpl.setDossierDocumentId(DossierDocumentId);
		dossierDocumentImpl.setGroupId(groupId);
		dossierDocumentImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			dossierDocumentImpl.setCreateDate(null);
		}
		else {
			dossierDocumentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dossierDocumentImpl.setModifiedDate(null);
		}
		else {
			dossierDocumentImpl.setModifiedDate(new Date(modifiedDate));
		}

		dossierDocumentImpl.setDossierId(dossierId);

		if (referenceUid == null) {
			dossierDocumentImpl.setReferenceUid("");
		}
		else {
			dossierDocumentImpl.setReferenceUid(referenceUid);
		}

		dossierDocumentImpl.setDossierActionId(dossierActionId);

		if (documentType == null) {
			dossierDocumentImpl.setDocumentType("");
		}
		else {
			dossierDocumentImpl.setDocumentType(documentType);
		}

		if (documentName == null) {
			dossierDocumentImpl.setDocumentName("");
		}
		else {
			dossierDocumentImpl.setDocumentName(documentName);
		}

		if (documentCode == null) {
			dossierDocumentImpl.setDocumentCode("");
		}
		else {
			dossierDocumentImpl.setDocumentCode(documentCode);
		}

		dossierDocumentImpl.setDocumentFileId(documentFileId);
		dossierDocumentImpl.setDocSync(docSync);

		dossierDocumentImpl.resetOriginalValues();

		return dossierDocumentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		DossierDocumentId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dossierId = objectInput.readLong();
		referenceUid = objectInput.readUTF();

		dossierActionId = objectInput.readLong();
		documentType = objectInput.readUTF();
		documentName = objectInput.readUTF();
		documentCode = objectInput.readUTF();

		documentFileId = objectInput.readLong();

		docSync = objectInput.readInt();
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

		objectOutput.writeLong(DossierDocumentId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(dossierId);

		if (referenceUid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(referenceUid);
		}

		objectOutput.writeLong(dossierActionId);

		if (documentType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(documentType);
		}

		if (documentName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(documentName);
		}

		if (documentCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(documentCode);
		}

		objectOutput.writeLong(documentFileId);

		objectOutput.writeInt(docSync);
	}

	public String uuid;
	public long DossierDocumentId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long dossierId;
	public String referenceUid;
	public long dossierActionId;
	public String documentType;
	public String documentName;
	public String documentCode;
	public long documentFileId;
	public int docSync;
}