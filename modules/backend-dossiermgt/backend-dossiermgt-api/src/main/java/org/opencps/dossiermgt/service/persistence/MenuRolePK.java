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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author huymq
 * @generated
 */
@ProviderType
public class MenuRolePK implements Comparable<MenuRolePK>, Serializable {
	public long menuConfigId;
	public long roleId;

	public MenuRolePK() {
	}

	public MenuRolePK(long menuConfigId, long roleId) {
		this.menuConfigId = menuConfigId;
		this.roleId = roleId;
	}

	public long getMenuConfigId() {
		return menuConfigId;
	}

	public void setMenuConfigId(long menuConfigId) {
		this.menuConfigId = menuConfigId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Override
	public int compareTo(MenuRolePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (menuConfigId < pk.menuConfigId) {
			value = -1;
		}
		else if (menuConfigId > pk.menuConfigId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (roleId < pk.roleId) {
			value = -1;
		}
		else if (roleId > pk.roleId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MenuRolePK)) {
			return false;
		}

		MenuRolePK pk = (MenuRolePK)obj;

		if ((menuConfigId == pk.menuConfigId) && (roleId == pk.roleId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, menuConfigId);
		hashCode = HashUtil.hash(hashCode, roleId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("menuConfigId=");

		sb.append(menuConfigId);
		sb.append(", roleId=");

		sb.append(roleId);

		sb.append("}");

		return sb.toString();
	}
}