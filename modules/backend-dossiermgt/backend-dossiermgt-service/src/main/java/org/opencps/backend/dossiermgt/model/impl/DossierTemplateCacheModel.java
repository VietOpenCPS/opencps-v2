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

package org.opencps.backend.dossiermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.opencps.backend.dossiermgt.model.DossierTemplate;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DossierTemplate in entity cache.
 *
 * @author huymq
 * @see DossierTemplate
 * @generated
 */
@ProviderType
public class DossierTemplateCacheModel implements CacheModel<DossierTemplate>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierTemplateCacheModel)) {
			return false;
		}

		DossierTemplateCacheModel dossierTemplateCacheModel = (DossierTemplateCacheModel)obj;

		if (dossierTemplateId == dossierTemplateCacheModel.dossierTemplateId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierTemplateId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dossierTemplateId=");
		sb.append(dossierTemplateId);
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
		sb.append(", templateName=");
		sb.append(templateName);
		sb.append(", description=");
		sb.append(description);
		sb.append(", templateNo=");
		sb.append(templateNo);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierTemplate toEntityModel() {
		DossierTemplateImpl dossierTemplateImpl = new DossierTemplateImpl();

		if (uuid == null) {
			dossierTemplateImpl.setUuid(StringPool.BLANK);
		}
		else {
			dossierTemplateImpl.setUuid(uuid);
		}

		dossierTemplateImpl.setDossierTemplateId(dossierTemplateId);
		dossierTemplateImpl.setGroupId(groupId);
		dossierTemplateImpl.setCompanyId(companyId);
		dossierTemplateImpl.setUserId(userId);

		if (userName == null) {
			dossierTemplateImpl.setUserName(StringPool.BLANK);
		}
		else {
			dossierTemplateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dossierTemplateImpl.setCreateDate(null);
		}
		else {
			dossierTemplateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dossierTemplateImpl.setModifiedDate(null);
		}
		else {
			dossierTemplateImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (templateName == null) {
			dossierTemplateImpl.setTemplateName(StringPool.BLANK);
		}
		else {
			dossierTemplateImpl.setTemplateName(templateName);
		}

		if (description == null) {
			dossierTemplateImpl.setDescription(StringPool.BLANK);
		}
		else {
			dossierTemplateImpl.setDescription(description);
		}

		if (templateNo == null) {
			dossierTemplateImpl.setTemplateNo(StringPool.BLANK);
		}
		else {
			dossierTemplateImpl.setTemplateNo(templateNo);
		}

		dossierTemplateImpl.resetOriginalValues();

		return dossierTemplateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dossierTemplateId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		templateName = objectInput.readUTF();
		description = objectInput.readUTF();
		templateNo = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(dossierTemplateId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (templateName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(templateName);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (templateNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(templateNo);
		}
	}

	public String uuid;
	public long dossierTemplateId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String templateName;
	public String description;
	public String templateNo;
}