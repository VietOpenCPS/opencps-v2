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

import org.opencps.dossiermgt.model.DossierRequestUD;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DossierRequestUD in entity cache.
 *
 * @author huymq
 * @see DossierRequestUD
 * @generated
 */
@ProviderType
public class DossierRequestUDCacheModel implements CacheModel<DossierRequestUD>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierRequestUDCacheModel)) {
			return false;
		}

		DossierRequestUDCacheModel dossierRequestUDCacheModel = (DossierRequestUDCacheModel)obj;

		if (dossierRequestId == dossierRequestUDCacheModel.dossierRequestId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierRequestId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dossierRequestId=");
		sb.append(dossierRequestId);
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
		sb.append(", referenceUid=");
		sb.append(referenceUid);
		sb.append(", requestType=");
		sb.append(requestType);
		sb.append(", comment=");
		sb.append(comment);
		sb.append(", isNew=");
		sb.append(isNew);
		sb.append(", statusReg=");
		sb.append(statusReg);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierRequestUD toEntityModel() {
		DossierRequestUDImpl dossierRequestUDImpl = new DossierRequestUDImpl();

		if (uuid == null) {
			dossierRequestUDImpl.setUuid("");
		}
		else {
			dossierRequestUDImpl.setUuid(uuid);
		}

		dossierRequestUDImpl.setDossierRequestId(dossierRequestId);
		dossierRequestUDImpl.setCompanyId(companyId);
		dossierRequestUDImpl.setGroupId(groupId);
		dossierRequestUDImpl.setUserId(userId);

		if (userName == null) {
			dossierRequestUDImpl.setUserName("");
		}
		else {
			dossierRequestUDImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dossierRequestUDImpl.setCreateDate(null);
		}
		else {
			dossierRequestUDImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dossierRequestUDImpl.setModifiedDate(null);
		}
		else {
			dossierRequestUDImpl.setModifiedDate(new Date(modifiedDate));
		}

		dossierRequestUDImpl.setDossierId(dossierId);

		if (referenceUid == null) {
			dossierRequestUDImpl.setReferenceUid("");
		}
		else {
			dossierRequestUDImpl.setReferenceUid(referenceUid);
		}

		if (requestType == null) {
			dossierRequestUDImpl.setRequestType("");
		}
		else {
			dossierRequestUDImpl.setRequestType(requestType);
		}

		if (comment == null) {
			dossierRequestUDImpl.setComment("");
		}
		else {
			dossierRequestUDImpl.setComment(comment);
		}

		dossierRequestUDImpl.setIsNew(isNew);
		dossierRequestUDImpl.setStatusReg(statusReg);

		dossierRequestUDImpl.resetOriginalValues();

		return dossierRequestUDImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dossierRequestId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dossierId = objectInput.readLong();
		referenceUid = objectInput.readUTF();
		requestType = objectInput.readUTF();
		comment = objectInput.readUTF();

		isNew = objectInput.readInt();

		statusReg = objectInput.readInt();
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

		objectOutput.writeLong(dossierRequestId);

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

		if (referenceUid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(referenceUid);
		}

		if (requestType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(requestType);
		}

		if (comment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comment);
		}

		objectOutput.writeInt(isNew);

		objectOutput.writeInt(statusReg);
	}

	public String uuid;
	public long dossierRequestId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long dossierId;
	public String referenceUid;
	public String requestType;
	public String comment;
	public int isNew;
	public int statusReg;
}