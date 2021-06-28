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

import org.opencps.dossiermgt.model.CsdlDcServiceInfo;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CsdlDcServiceInfo in entity cache.
 *
 * @author huymq
 * @see CsdlDcServiceInfo
 * @generated
 */
@ProviderType
public class CsdlDcServiceInfoCacheModel implements CacheModel<CsdlDcServiceInfo>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CsdlDcServiceInfoCacheModel)) {
			return false;
		}

		CsdlDcServiceInfoCacheModel csdlDcServiceInfoCacheModel = (CsdlDcServiceInfoCacheModel)obj;

		if (idDcService == csdlDcServiceInfoCacheModel.idDcService) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, idDcService);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", idDcService=");
		sb.append(idDcService);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", serviceCode=");
		sb.append(serviceCode);
		sb.append(", serviceCodeDvcqg=");
		sb.append(serviceCodeDvcqg);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CsdlDcServiceInfo toEntityModel() {
		CsdlDcServiceInfoImpl csdlDcServiceInfoImpl = new CsdlDcServiceInfoImpl();

		if (uuid == null) {
			csdlDcServiceInfoImpl.setUuid("");
		}
		else {
			csdlDcServiceInfoImpl.setUuid(uuid);
		}

		csdlDcServiceInfoImpl.setIdDcService(idDcService);
		csdlDcServiceInfoImpl.setGroupId(groupId);
		csdlDcServiceInfoImpl.setCompanyId(companyId);
		csdlDcServiceInfoImpl.setUserId(userId);

		if (serviceCode == null) {
			csdlDcServiceInfoImpl.setServiceCode("");
		}
		else {
			csdlDcServiceInfoImpl.setServiceCode(serviceCode);
		}

		if (serviceCodeDvcqg == null) {
			csdlDcServiceInfoImpl.setServiceCodeDvcqg("");
		}
		else {
			csdlDcServiceInfoImpl.setServiceCodeDvcqg(serviceCodeDvcqg);
		}

		csdlDcServiceInfoImpl.setStatus(status);

		csdlDcServiceInfoImpl.resetOriginalValues();

		return csdlDcServiceInfoImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		idDcService = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		serviceCode = objectInput.readUTF();
		serviceCodeDvcqg = objectInput.readUTF();

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

		objectOutput.writeLong(idDcService);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (serviceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceCode);
		}

		if (serviceCodeDvcqg == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceCodeDvcqg);
		}

		objectOutput.writeInt(status);
	}

	public String uuid;
	public long idDcService;
	public long groupId;
	public long companyId;
	public long userId;
	public String serviceCode;
	public String serviceCodeDvcqg;
	public int status;
}