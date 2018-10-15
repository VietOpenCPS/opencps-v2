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

import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ServiceProcessRole in entity cache.
 *
 * @author huymq
 * @see ServiceProcessRole
 * @generated
 */
@ProviderType
public class ServiceProcessRoleCacheModel implements CacheModel<ServiceProcessRole>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceProcessRoleCacheModel)) {
			return false;
		}

		ServiceProcessRoleCacheModel serviceProcessRoleCacheModel = (ServiceProcessRoleCacheModel)obj;

		if (serviceProcessRolePK.equals(
					serviceProcessRoleCacheModel.serviceProcessRolePK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, serviceProcessRolePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", serviceProcessId=");
		sb.append(serviceProcessId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append(", roleCode=");
		sb.append(roleCode);
		sb.append(", roleName=");
		sb.append(roleName);
		sb.append(", moderator=");
		sb.append(moderator);
		sb.append(", condition=");
		sb.append(condition);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ServiceProcessRole toEntityModel() {
		ServiceProcessRoleImpl serviceProcessRoleImpl = new ServiceProcessRoleImpl();

		if (uuid == null) {
			serviceProcessRoleImpl.setUuid("");
		}
		else {
			serviceProcessRoleImpl.setUuid(uuid);
		}

		serviceProcessRoleImpl.setServiceProcessId(serviceProcessId);
		serviceProcessRoleImpl.setRoleId(roleId);

		if (roleCode == null) {
			serviceProcessRoleImpl.setRoleCode("");
		}
		else {
			serviceProcessRoleImpl.setRoleCode(roleCode);
		}

		if (roleName == null) {
			serviceProcessRoleImpl.setRoleName("");
		}
		else {
			serviceProcessRoleImpl.setRoleName(roleName);
		}

		serviceProcessRoleImpl.setModerator(moderator);

		if (condition == null) {
			serviceProcessRoleImpl.setCondition("");
		}
		else {
			serviceProcessRoleImpl.setCondition(condition);
		}

		serviceProcessRoleImpl.resetOriginalValues();

		return serviceProcessRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		serviceProcessId = objectInput.readLong();

		roleId = objectInput.readLong();
		roleCode = objectInput.readUTF();
		roleName = objectInput.readUTF();

		moderator = objectInput.readBoolean();
		condition = objectInput.readUTF();

		serviceProcessRolePK = new ServiceProcessRolePK(serviceProcessId, roleId);
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

		objectOutput.writeLong(serviceProcessId);

		objectOutput.writeLong(roleId);

		if (roleCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(roleCode);
		}

		if (roleName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(roleName);
		}

		objectOutput.writeBoolean(moderator);

		if (condition == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(condition);
		}
	}

	public String uuid;
	public long serviceProcessId;
	public long roleId;
	public String roleCode;
	public String roleName;
	public boolean moderator;
	public String condition;
	public transient ServiceProcessRolePK serviceProcessRolePK;
}