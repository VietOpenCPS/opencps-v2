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

import org.opencps.dossiermgt.model.MenuConfig;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MenuConfig in entity cache.
 *
 * @author huymq
 * @see MenuConfig
 * @generated
 */
@ProviderType
public class MenuConfigCacheModel implements CacheModel<MenuConfig>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MenuConfigCacheModel)) {
			return false;
		}

		MenuConfigCacheModel menuConfigCacheModel = (MenuConfigCacheModel)obj;

		if (menuConfigId == menuConfigCacheModel.menuConfigId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, menuConfigId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", menuConfigId=");
		sb.append(menuConfigId);
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
		sb.append(", menuGroup=");
		sb.append(menuGroup);
		sb.append(", menuName=");
		sb.append(menuName);
		sb.append(", order=");
		sb.append(order);
		sb.append(", menuType=");
		sb.append(menuType);
		sb.append(", queryParams=");
		sb.append(queryParams);
		sb.append(", tableConfig=");
		sb.append(tableConfig);
		sb.append(", buttonConfig=");
		sb.append(buttonConfig);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MenuConfig toEntityModel() {
		MenuConfigImpl menuConfigImpl = new MenuConfigImpl();

		if (uuid == null) {
			menuConfigImpl.setUuid("");
		}
		else {
			menuConfigImpl.setUuid(uuid);
		}

		menuConfigImpl.setMenuConfigId(menuConfigId);
		menuConfigImpl.setCompanyId(companyId);
		menuConfigImpl.setGroupId(groupId);
		menuConfigImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			menuConfigImpl.setCreateDate(null);
		}
		else {
			menuConfigImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			menuConfigImpl.setModifiedDate(null);
		}
		else {
			menuConfigImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (menuGroup == null) {
			menuConfigImpl.setMenuGroup("");
		}
		else {
			menuConfigImpl.setMenuGroup(menuGroup);
		}

		if (menuName == null) {
			menuConfigImpl.setMenuName("");
		}
		else {
			menuConfigImpl.setMenuName(menuName);
		}

		menuConfigImpl.setOrder(order);
		menuConfigImpl.setMenuType(menuType);

		if (queryParams == null) {
			menuConfigImpl.setQueryParams("");
		}
		else {
			menuConfigImpl.setQueryParams(queryParams);
		}

		if (tableConfig == null) {
			menuConfigImpl.setTableConfig("");
		}
		else {
			menuConfigImpl.setTableConfig(tableConfig);
		}

		if (buttonConfig == null) {
			menuConfigImpl.setButtonConfig("");
		}
		else {
			menuConfigImpl.setButtonConfig(buttonConfig);
		}

		menuConfigImpl.resetOriginalValues();

		return menuConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		menuConfigId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		menuGroup = objectInput.readUTF();
		menuName = objectInput.readUTF();

		order = objectInput.readInt();

		menuType = objectInput.readInt();
		queryParams = objectInput.readUTF();
		tableConfig = objectInput.readUTF();
		buttonConfig = objectInput.readUTF();
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

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (menuGroup == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(menuGroup);
		}

		if (menuName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(menuName);
		}

		objectOutput.writeInt(order);

		objectOutput.writeInt(menuType);

		if (queryParams == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(queryParams);
		}

		if (tableConfig == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tableConfig);
		}

		if (buttonConfig == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(buttonConfig);
		}
	}

	public String uuid;
	public long menuConfigId;
	public long companyId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String menuGroup;
	public String menuName;
	public int order;
	public int menuType;
	public String queryParams;
	public String tableConfig;
	public String buttonConfig;
}