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

import org.opencps.dossiermgt.model.DocumentType;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DocumentType in entity cache.
 *
 * @author huymq
 * @see DocumentType
 * @generated
 */
@ProviderType
public class DocumentTypeCacheModel implements CacheModel<DocumentType>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DocumentTypeCacheModel)) {
			return false;
		}

		DocumentTypeCacheModel documentTypeCacheModel = (DocumentTypeCacheModel)obj;

		if (DocumentTypeId == documentTypeCacheModel.DocumentTypeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, DocumentTypeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", DocumentTypeId=");
		sb.append(DocumentTypeId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", typeCode=");
		sb.append(typeCode);
		sb.append(", templateClass=");
		sb.append(templateClass);
		sb.append(", documentName=");
		sb.append(documentName);
		sb.append(", codePattern=");
		sb.append(codePattern);
		sb.append(", documentScript=");
		sb.append(documentScript);
		sb.append(", docSync=");
		sb.append(docSync);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DocumentType toEntityModel() {
		DocumentTypeImpl documentTypeImpl = new DocumentTypeImpl();

		if (uuid == null) {
			documentTypeImpl.setUuid("");
		}
		else {
			documentTypeImpl.setUuid(uuid);
		}

		documentTypeImpl.setDocumentTypeId(DocumentTypeId);
		documentTypeImpl.setGroupId(groupId);
		documentTypeImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			documentTypeImpl.setCreateDate(null);
		}
		else {
			documentTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			documentTypeImpl.setModifiedDate(null);
		}
		else {
			documentTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (typeCode == null) {
			documentTypeImpl.setTypeCode("");
		}
		else {
			documentTypeImpl.setTypeCode(typeCode);
		}

		documentTypeImpl.setTemplateClass(templateClass);

		if (documentName == null) {
			documentTypeImpl.setDocumentName("");
		}
		else {
			documentTypeImpl.setDocumentName(documentName);
		}

		if (codePattern == null) {
			documentTypeImpl.setCodePattern("");
		}
		else {
			documentTypeImpl.setCodePattern(codePattern);
		}

		if (documentScript == null) {
			documentTypeImpl.setDocumentScript("");
		}
		else {
			documentTypeImpl.setDocumentScript(documentScript);
		}

		documentTypeImpl.setDocSync(docSync);

		documentTypeImpl.resetOriginalValues();

		return documentTypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		DocumentTypeId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		typeCode = objectInput.readUTF();

		templateClass = objectInput.readInt();
		documentName = objectInput.readUTF();
		codePattern = objectInput.readUTF();
		documentScript = objectInput.readUTF();

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

		objectOutput.writeLong(DocumentTypeId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (typeCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(typeCode);
		}

		objectOutput.writeInt(templateClass);

		if (documentName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(documentName);
		}

		if (codePattern == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(codePattern);
		}

		if (documentScript == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(documentScript);
		}

		objectOutput.writeInt(docSync);
	}

	public String uuid;
	public long DocumentTypeId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String typeCode;
	public int templateClass;
	public String documentName;
	public String codePattern;
	public String documentScript;
	public int docSync;
}