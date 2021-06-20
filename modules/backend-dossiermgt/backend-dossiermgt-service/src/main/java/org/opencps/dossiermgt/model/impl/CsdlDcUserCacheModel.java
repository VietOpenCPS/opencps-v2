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

import org.opencps.dossiermgt.model.CsdlDcUser;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CsdlDcUser in entity cache.
 *
 * @author huymq
 * @see CsdlDcUser
 * @generated
 */
@ProviderType
public class CsdlDcUserCacheModel implements CacheModel<CsdlDcUser>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CsdlDcUserCacheModel)) {
			return false;
		}

		CsdlDcUserCacheModel csdlDcUserCacheModel = (CsdlDcUserCacheModel)obj;

		if (idDcUser == csdlDcUserCacheModel.idDcUser) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, idDcUser);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", idDcUser=");
		sb.append(idDcUser);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", govAgencyCode=");
		sb.append(govAgencyCode);
		sb.append(", govAgencyCodeDvcqg=");
		sb.append(govAgencyCodeDvcqg);
		sb.append(", keyName=");
		sb.append(keyName);
		sb.append(", keyPass=");
		sb.append(keyPass);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", employeeEmail=");
		sb.append(employeeEmail);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CsdlDcUser toEntityModel() {
		CsdlDcUserImpl csdlDcUserImpl = new CsdlDcUserImpl();

		if (uuid == null) {
			csdlDcUserImpl.setUuid("");
		}
		else {
			csdlDcUserImpl.setUuid(uuid);
		}

		csdlDcUserImpl.setIdDcUser(idDcUser);
		csdlDcUserImpl.setGroupId(groupId);
		csdlDcUserImpl.setCompanyId(companyId);
		csdlDcUserImpl.setUserId(userId);

		if (govAgencyCode == null) {
			csdlDcUserImpl.setGovAgencyCode("");
		}
		else {
			csdlDcUserImpl.setGovAgencyCode(govAgencyCode);
		}

		if (govAgencyCodeDvcqg == null) {
			csdlDcUserImpl.setGovAgencyCodeDvcqg("");
		}
		else {
			csdlDcUserImpl.setGovAgencyCodeDvcqg(govAgencyCodeDvcqg);
		}

		if (keyName == null) {
			csdlDcUserImpl.setKeyName("");
		}
		else {
			csdlDcUserImpl.setKeyName(keyName);
		}

		if (keyPass == null) {
			csdlDcUserImpl.setKeyPass("");
		}
		else {
			csdlDcUserImpl.setKeyPass(keyPass);
		}

		if (userName == null) {
			csdlDcUserImpl.setUserName("");
		}
		else {
			csdlDcUserImpl.setUserName(userName);
		}

		if (employeeEmail == null) {
			csdlDcUserImpl.setEmployeeEmail("");
		}
		else {
			csdlDcUserImpl.setEmployeeEmail(employeeEmail);
		}

		csdlDcUserImpl.setStatus(status);

		csdlDcUserImpl.resetOriginalValues();

		return csdlDcUserImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		idDcUser = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		govAgencyCode = objectInput.readUTF();
		govAgencyCodeDvcqg = objectInput.readUTF();
		keyName = objectInput.readUTF();
		keyPass = objectInput.readUTF();
		userName = objectInput.readUTF();
		employeeEmail = objectInput.readUTF();

		status = objectInput.readInt();
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

		objectOutput.writeLong(idDcUser);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (govAgencyCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(govAgencyCode);
		}

		if (govAgencyCodeDvcqg == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(govAgencyCodeDvcqg);
		}

		if (keyName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(keyName);
		}

		if (keyPass == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(keyPass);
		}

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		if (employeeEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(employeeEmail);
		}

		objectOutput.writeInt(status);
	}

	public String uuid;
	public long idDcUser;
	public long groupId;
	public long companyId;
	public long userId;
	public String govAgencyCode;
	public String govAgencyCodeDvcqg;
	public String keyName;
	public String keyPass;
	public String userName;
	public String employeeEmail;
	public int status;
}