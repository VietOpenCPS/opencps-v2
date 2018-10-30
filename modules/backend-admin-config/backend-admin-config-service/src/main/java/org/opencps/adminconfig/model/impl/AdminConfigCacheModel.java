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

package org.opencps.adminconfig.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.adminconfig.model.AdminConfig;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AdminConfig in entity cache.
 *
 * @author binhth
 * @see AdminConfig
 * @generated
 */
@ProviderType
public class AdminConfigCacheModel implements CacheModel<AdminConfig>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AdminConfigCacheModel)) {
			return false;
		}

		AdminConfigCacheModel adminConfigCacheModel = (AdminConfigCacheModel)obj;

		if (id == adminConfigCacheModel.id) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{id=");
		sb.append(id);
		sb.append(", code=");
		sb.append(code);
		sb.append(", name=");
		sb.append(name);
		sb.append(", bundleName=");
		sb.append(bundleName);
		sb.append(", modelName=");
		sb.append(modelName);
		sb.append(", serviceUtilName=");
		sb.append(serviceUtilName);
		sb.append(", headersName=");
		sb.append(headersName);
		sb.append(", columns=");
		sb.append(columns);
		sb.append(", detailColumns=");
		sb.append(detailColumns);
		sb.append(", extForm=");
		sb.append(extForm);
		sb.append(", groupFilter=");
		sb.append(groupFilter);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AdminConfig toEntityModel() {
		AdminConfigImpl adminConfigImpl = new AdminConfigImpl();

		adminConfigImpl.setId(id);

		if (code == null) {
			adminConfigImpl.setCode("");
		}
		else {
			adminConfigImpl.setCode(code);
		}

		if (name == null) {
			adminConfigImpl.setName("");
		}
		else {
			adminConfigImpl.setName(name);
		}

		if (bundleName == null) {
			adminConfigImpl.setBundleName("");
		}
		else {
			adminConfigImpl.setBundleName(bundleName);
		}

		if (modelName == null) {
			adminConfigImpl.setModelName("");
		}
		else {
			adminConfigImpl.setModelName(modelName);
		}

		if (serviceUtilName == null) {
			adminConfigImpl.setServiceUtilName("");
		}
		else {
			adminConfigImpl.setServiceUtilName(serviceUtilName);
		}

		if (headersName == null) {
			adminConfigImpl.setHeadersName("");
		}
		else {
			adminConfigImpl.setHeadersName(headersName);
		}

		if (columns == null) {
			adminConfigImpl.setColumns("");
		}
		else {
			adminConfigImpl.setColumns(columns);
		}

		if (detailColumns == null) {
			adminConfigImpl.setDetailColumns("");
		}
		else {
			adminConfigImpl.setDetailColumns(detailColumns);
		}

		adminConfigImpl.setExtForm(extForm);
		adminConfigImpl.setGroupFilter(groupFilter);

		adminConfigImpl.resetOriginalValues();

		return adminConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();
		code = objectInput.readUTF();
		name = objectInput.readUTF();
		bundleName = objectInput.readUTF();
		modelName = objectInput.readUTF();
		serviceUtilName = objectInput.readUTF();
		headersName = objectInput.readUTF();
		columns = objectInput.readUTF();
		detailColumns = objectInput.readUTF();

		extForm = objectInput.readBoolean();

		groupFilter = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(id);

		if (code == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(code);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (bundleName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(bundleName);
		}

		if (modelName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(modelName);
		}

		if (serviceUtilName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceUtilName);
		}

		if (headersName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(headersName);
		}

		if (columns == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(columns);
		}

		if (detailColumns == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(detailColumns);
		}

		objectOutput.writeBoolean(extForm);

		objectOutput.writeBoolean(groupFilter);
	}

	public long id;
	public String code;
	public String name;
	public String bundleName;
	public String modelName;
	public String serviceUtilName;
	public String headersName;
	public String columns;
	public String detailColumns;
	public boolean extForm;
	public boolean groupFilter;
}