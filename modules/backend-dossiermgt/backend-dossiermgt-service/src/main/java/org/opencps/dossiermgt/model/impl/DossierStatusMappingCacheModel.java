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

import org.opencps.dossiermgt.model.DossierStatusMapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DossierStatusMapping in entity cache.
 *
 * @author huymq
 * @see DossierStatusMapping
 * @generated
 */
@ProviderType
public class DossierStatusMappingCacheModel implements CacheModel<DossierStatusMapping>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierStatusMappingCacheModel)) {
			return false;
		}

		DossierStatusMappingCacheModel dossierStatusMappingCacheModel = (DossierStatusMappingCacheModel)obj;

		if (dossierStatusMappingId == dossierStatusMappingCacheModel.dossierStatusMappingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierStatusMappingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{dossierStatusMappingId=");
		sb.append(dossierStatusMappingId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", statusCode=");
		sb.append(statusCode);
		sb.append(", statusCodeDVCQG=");
		sb.append(statusCodeDVCQG);
		sb.append(", subStatusCode=");
		sb.append(subStatusCode);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierStatusMapping toEntityModel() {
		DossierStatusMappingImpl dossierStatusMappingImpl = new DossierStatusMappingImpl();

		dossierStatusMappingImpl.setDossierStatusMappingId(dossierStatusMappingId);
		dossierStatusMappingImpl.setGroupId(groupId);
		dossierStatusMappingImpl.setCompanyId(companyId);
		dossierStatusMappingImpl.setUserId(userId);

		if (userName == null) {
			dossierStatusMappingImpl.setUserName("");
		}
		else {
			dossierStatusMappingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dossierStatusMappingImpl.setCreateDate(null);
		}
		else {
			dossierStatusMappingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dossierStatusMappingImpl.setModifiedDate(null);
		}
		else {
			dossierStatusMappingImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (statusCode == null) {
			dossierStatusMappingImpl.setStatusCode("");
		}
		else {
			dossierStatusMappingImpl.setStatusCode(statusCode);
		}

		if (statusCodeDVCQG == null) {
			dossierStatusMappingImpl.setStatusCodeDVCQG("");
		}
		else {
			dossierStatusMappingImpl.setStatusCodeDVCQG(statusCodeDVCQG);
		}

		if (subStatusCode == null) {
			dossierStatusMappingImpl.setSubStatusCode("");
		}
		else {
			dossierStatusMappingImpl.setSubStatusCode(subStatusCode);
		}

		dossierStatusMappingImpl.resetOriginalValues();

		return dossierStatusMappingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		dossierStatusMappingId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		statusCode = objectInput.readUTF();
		statusCodeDVCQG = objectInput.readUTF();
		subStatusCode = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(dossierStatusMappingId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (statusCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusCode);
		}

		if (statusCodeDVCQG == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusCodeDVCQG);
		}

		if (subStatusCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subStatusCode);
		}
	}

	public long dossierStatusMappingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String statusCode;
	public String statusCodeDVCQG;
	public String subStatusCode;
}