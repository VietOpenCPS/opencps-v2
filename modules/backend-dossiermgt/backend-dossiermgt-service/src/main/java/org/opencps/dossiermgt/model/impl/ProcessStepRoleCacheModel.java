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

import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.service.persistence.ProcessStepRolePK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProcessStepRole in entity cache.
 *
 * @author huymq
 * @see ProcessStepRole
 * @generated
 */
@ProviderType
public class ProcessStepRoleCacheModel implements CacheModel<ProcessStepRole>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessStepRoleCacheModel)) {
			return false;
		}

		ProcessStepRoleCacheModel processStepRoleCacheModel = (ProcessStepRoleCacheModel)obj;

		if (processStepRolePK.equals(
					processStepRoleCacheModel.processStepRolePK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, processStepRolePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", processStepId=");
		sb.append(processStepId);
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
	public ProcessStepRole toEntityModel() {
		ProcessStepRoleImpl processStepRoleImpl = new ProcessStepRoleImpl();

		if (uuid == null) {
			processStepRoleImpl.setUuid("");
		}
		else {
			processStepRoleImpl.setUuid(uuid);
		}

		processStepRoleImpl.setProcessStepId(processStepId);
		processStepRoleImpl.setRoleId(roleId);

		if (roleCode == null) {
			processStepRoleImpl.setRoleCode("");
		}
		else {
			processStepRoleImpl.setRoleCode(roleCode);
		}

		if (roleName == null) {
			processStepRoleImpl.setRoleName("");
		}
		else {
			processStepRoleImpl.setRoleName(roleName);
		}

		processStepRoleImpl.setModerator(moderator);

		if (condition == null) {
			processStepRoleImpl.setCondition("");
		}
		else {
			processStepRoleImpl.setCondition(condition);
		}

		processStepRoleImpl.resetOriginalValues();

		return processStepRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		processStepId = objectInput.readLong();

		roleId = objectInput.readLong();
		roleCode = objectInput.readUTF();
		roleName = objectInput.readUTF();

		moderator = objectInput.readBoolean();
		condition = objectInput.readUTF();

		processStepRolePK = new ProcessStepRolePK(processStepId, roleId);
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

		objectOutput.writeLong(processStepId);

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
	public long processStepId;
	public long roleId;
	public String roleCode;
	public String roleName;
	public boolean moderator;
	public String condition;
	public transient ProcessStepRolePK processStepRolePK;
}