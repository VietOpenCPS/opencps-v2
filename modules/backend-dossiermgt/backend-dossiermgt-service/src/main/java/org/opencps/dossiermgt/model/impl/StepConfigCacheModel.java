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

import org.opencps.dossiermgt.model.StepConfig;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StepConfig in entity cache.
 *
 * @author huymq
 * @see StepConfig
 * @generated
 */
@ProviderType
public class StepConfigCacheModel implements CacheModel<StepConfig>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StepConfigCacheModel)) {
			return false;
		}

		StepConfigCacheModel stepConfigCacheModel = (StepConfigCacheModel)obj;

		if (stepConfigId == stepConfigCacheModel.stepConfigId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stepConfigId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", stepConfigId=");
		sb.append(stepConfigId);
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
		sb.append(", stepCode=");
		sb.append(stepCode);
		sb.append(", stepName=");
		sb.append(stepName);
		sb.append(", stepType=");
		sb.append(stepType);
		sb.append(", dossierStatus=");
		sb.append(dossierStatus);
		sb.append(", dossierSubStatus=");
		sb.append(dossierSubStatus);
		sb.append(", menuGroup=");
		sb.append(menuGroup);
		sb.append(", menuStepName=");
		sb.append(menuStepName);
		sb.append(", buttonConfig=");
		sb.append(buttonConfig);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StepConfig toEntityModel() {
		StepConfigImpl stepConfigImpl = new StepConfigImpl();

		if (uuid == null) {
			stepConfigImpl.setUuid("");
		}
		else {
			stepConfigImpl.setUuid(uuid);
		}

		stepConfigImpl.setStepConfigId(stepConfigId);
		stepConfigImpl.setCompanyId(companyId);
		stepConfigImpl.setGroupId(groupId);
		stepConfigImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			stepConfigImpl.setCreateDate(null);
		}
		else {
			stepConfigImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			stepConfigImpl.setModifiedDate(null);
		}
		else {
			stepConfigImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (stepCode == null) {
			stepConfigImpl.setStepCode("");
		}
		else {
			stepConfigImpl.setStepCode(stepCode);
		}

		if (stepName == null) {
			stepConfigImpl.setStepName("");
		}
		else {
			stepConfigImpl.setStepName(stepName);
		}

		stepConfigImpl.setStepType(stepType);

		if (dossierStatus == null) {
			stepConfigImpl.setDossierStatus("");
		}
		else {
			stepConfigImpl.setDossierStatus(dossierStatus);
		}

		if (dossierSubStatus == null) {
			stepConfigImpl.setDossierSubStatus("");
		}
		else {
			stepConfigImpl.setDossierSubStatus(dossierSubStatus);
		}

		if (menuGroup == null) {
			stepConfigImpl.setMenuGroup("");
		}
		else {
			stepConfigImpl.setMenuGroup(menuGroup);
		}

		if (menuStepName == null) {
			stepConfigImpl.setMenuStepName("");
		}
		else {
			stepConfigImpl.setMenuStepName(menuStepName);
		}

		if (buttonConfig == null) {
			stepConfigImpl.setButtonConfig("");
		}
		else {
			stepConfigImpl.setButtonConfig(buttonConfig);
		}

		stepConfigImpl.resetOriginalValues();

		return stepConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		stepConfigId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		stepCode = objectInput.readUTF();
		stepName = objectInput.readUTF();

		stepType = objectInput.readInt();
		dossierStatus = objectInput.readUTF();
		dossierSubStatus = objectInput.readUTF();
		menuGroup = objectInput.readUTF();
		menuStepName = objectInput.readUTF();
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

		objectOutput.writeLong(stepConfigId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (stepCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stepCode);
		}

		if (stepName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stepName);
		}

		objectOutput.writeInt(stepType);

		if (dossierStatus == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierStatus);
		}

		if (dossierSubStatus == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierSubStatus);
		}

		if (menuGroup == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(menuGroup);
		}

		if (menuStepName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(menuStepName);
		}

		if (buttonConfig == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(buttonConfig);
		}
	}

	public String uuid;
	public long stepConfigId;
	public long companyId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String stepCode;
	public String stepName;
	public int stepType;
	public String dossierStatus;
	public String dossierSubStatus;
	public String menuGroup;
	public String menuStepName;
	public String buttonConfig;
}