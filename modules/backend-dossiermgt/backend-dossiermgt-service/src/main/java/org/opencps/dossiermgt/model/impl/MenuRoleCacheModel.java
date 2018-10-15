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

import org.opencps.dossiermgt.model.MenuRole;
import org.opencps.dossiermgt.service.persistence.MenuRolePK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MenuRole in entity cache.
 *
 * @author huymq
 * @see MenuRole
 * @generated
 */
@ProviderType
public class MenuRoleCacheModel implements CacheModel<MenuRole>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MenuRoleCacheModel)) {
			return false;
		}

		MenuRoleCacheModel menuRoleCacheModel = (MenuRoleCacheModel)obj;

		if (menuRolePK.equals(menuRoleCacheModel.menuRolePK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, menuRolePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", menuConfigId=");
		sb.append(menuConfigId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MenuRole toEntityModel() {
		MenuRoleImpl menuRoleImpl = new MenuRoleImpl();

		if (uuid == null) {
			menuRoleImpl.setUuid("");
		}
		else {
			menuRoleImpl.setUuid(uuid);
		}

		menuRoleImpl.setMenuConfigId(menuConfigId);
		menuRoleImpl.setRoleId(roleId);

		menuRoleImpl.resetOriginalValues();

		return menuRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		menuConfigId = objectInput.readLong();

		roleId = objectInput.readLong();

		menuRolePK = new MenuRolePK(menuConfigId, roleId);
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

		objectOutput.writeLong(menuConfigId);

		objectOutput.writeLong(roleId);
	}

	public String uuid;
	public long menuConfigId;
	public long roleId;
	public transient MenuRolePK menuRolePK;
}