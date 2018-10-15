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

import org.opencps.dossiermgt.model.DossierMark;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DossierMark in entity cache.
 *
 * @author huymq
 * @see DossierMark
 * @generated
 */
@ProviderType
public class DossierMarkCacheModel implements CacheModel<DossierMark>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierMarkCacheModel)) {
			return false;
		}

		DossierMarkCacheModel dossierMarkCacheModel = (DossierMarkCacheModel)obj;

		if (dossierMarkId == dossierMarkCacheModel.dossierMarkId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierMarkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dossierMarkId=");
		sb.append(dossierMarkId);
		sb.append(", companyId=");
		sb.append(companyId);
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
		sb.append(", dossierPartNo=");
		sb.append(dossierPartNo);
		sb.append(", fileCheck=");
		sb.append(fileCheck);
		sb.append(", fileMark=");
		sb.append(fileMark);
		sb.append(", fileComment=");
		sb.append(fileComment);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierMark toEntityModel() {
		DossierMarkImpl dossierMarkImpl = new DossierMarkImpl();

		if (uuid == null) {
			dossierMarkImpl.setUuid("");
		}
		else {
			dossierMarkImpl.setUuid(uuid);
		}

		dossierMarkImpl.setDossierMarkId(dossierMarkId);
		dossierMarkImpl.setCompanyId(companyId);
		dossierMarkImpl.setGroupId(groupId);
		dossierMarkImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			dossierMarkImpl.setCreateDate(null);
		}
		else {
			dossierMarkImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dossierMarkImpl.setModifiedDate(null);
		}
		else {
			dossierMarkImpl.setModifiedDate(new Date(modifiedDate));
		}

		dossierMarkImpl.setDossierId(dossierId);

		if (dossierPartNo == null) {
			dossierMarkImpl.setDossierPartNo("");
		}
		else {
			dossierMarkImpl.setDossierPartNo(dossierPartNo);
		}

		dossierMarkImpl.setFileCheck(fileCheck);
		dossierMarkImpl.setFileMark(fileMark);

		if (fileComment == null) {
			dossierMarkImpl.setFileComment("");
		}
		else {
			dossierMarkImpl.setFileComment(fileComment);
		}

		dossierMarkImpl.resetOriginalValues();

		return dossierMarkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dossierMarkId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dossierId = objectInput.readLong();
		dossierPartNo = objectInput.readUTF();

		fileCheck = objectInput.readInt();

		fileMark = objectInput.readInt();
		fileComment = objectInput.readUTF();
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

		objectOutput.writeLong(dossierMarkId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(dossierId);

		if (dossierPartNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierPartNo);
		}

		objectOutput.writeInt(fileCheck);

		objectOutput.writeInt(fileMark);

		if (fileComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileComment);
		}
	}

	public String uuid;
	public long dossierMarkId;
	public long companyId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long dossierId;
	public String dossierPartNo;
	public int fileCheck;
	public int fileMark;
	public String fileComment;
}