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

import org.opencps.dossiermgt.model.EForm;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing EForm in entity cache.
 *
 * @author huymq
 * @see EForm
 * @generated
 */
@ProviderType
public class EFormCacheModel implements CacheModel<EForm>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EFormCacheModel)) {
			return false;
		}

		EFormCacheModel eFormCacheModel = (EFormCacheModel)obj;

		if (eFormId == eFormCacheModel.eFormId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, eFormId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", eFormId=");
		sb.append(eFormId);
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
		sb.append(", eFormNo=");
		sb.append(eFormNo);
		sb.append(", serviceCode=");
		sb.append(serviceCode);
		sb.append(", fileTemplateNo=");
		sb.append(fileTemplateNo);
		sb.append(", eFormName=");
		sb.append(eFormName);
		sb.append(", formScriptFileId=");
		sb.append(formScriptFileId);
		sb.append(", formReportFileId=");
		sb.append(formReportFileId);
		sb.append(", eFormData=");
		sb.append(eFormData);
		sb.append(", email=");
		sb.append(email);
		sb.append(", secret=");
		sb.append(secret);
		sb.append(", govAgencyCode=");
		sb.append(govAgencyCode);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EForm toEntityModel() {
		EFormImpl eFormImpl = new EFormImpl();

		if (uuid == null) {
			eFormImpl.setUuid("");
		}
		else {
			eFormImpl.setUuid(uuid);
		}

		eFormImpl.setEFormId(eFormId);
		eFormImpl.setGroupId(groupId);
		eFormImpl.setCompanyId(companyId);
		eFormImpl.setUserId(userId);

		if (userName == null) {
			eFormImpl.setUserName("");
		}
		else {
			eFormImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			eFormImpl.setCreateDate(null);
		}
		else {
			eFormImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			eFormImpl.setModifiedDate(null);
		}
		else {
			eFormImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (eFormNo == null) {
			eFormImpl.setEFormNo("");
		}
		else {
			eFormImpl.setEFormNo(eFormNo);
		}

		if (serviceCode == null) {
			eFormImpl.setServiceCode("");
		}
		else {
			eFormImpl.setServiceCode(serviceCode);
		}

		if (fileTemplateNo == null) {
			eFormImpl.setFileTemplateNo("");
		}
		else {
			eFormImpl.setFileTemplateNo(fileTemplateNo);
		}

		if (eFormName == null) {
			eFormImpl.setEFormName("");
		}
		else {
			eFormImpl.setEFormName(eFormName);
		}

		eFormImpl.setFormScriptFileId(formScriptFileId);
		eFormImpl.setFormReportFileId(formReportFileId);

		if (eFormData == null) {
			eFormImpl.setEFormData("");
		}
		else {
			eFormImpl.setEFormData(eFormData);
		}

		if (email == null) {
			eFormImpl.setEmail("");
		}
		else {
			eFormImpl.setEmail(email);
		}

		if (secret == null) {
			eFormImpl.setSecret("");
		}
		else {
			eFormImpl.setSecret(secret);
		}

		if (govAgencyCode == null) {
			eFormImpl.setGovAgencyCode("");
		}
		else {
			eFormImpl.setGovAgencyCode(govAgencyCode);
		}

		eFormImpl.resetOriginalValues();

		return eFormImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		eFormId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		eFormNo = objectInput.readUTF();
		serviceCode = objectInput.readUTF();
		fileTemplateNo = objectInput.readUTF();
		eFormName = objectInput.readUTF();

		formScriptFileId = objectInput.readLong();

		formReportFileId = objectInput.readLong();
		eFormData = objectInput.readUTF();
		email = objectInput.readUTF();
		secret = objectInput.readUTF();
		govAgencyCode = objectInput.readUTF();
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

		objectOutput.writeLong(eFormId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (eFormNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(eFormNo);
		}

		if (serviceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceCode);
		}

		if (fileTemplateNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileTemplateNo);
		}

		if (eFormName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(eFormName);
		}

		objectOutput.writeLong(formScriptFileId);

		objectOutput.writeLong(formReportFileId);

		if (eFormData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(eFormData);
		}

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (secret == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(secret);
		}

		if (govAgencyCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(govAgencyCode);
		}
	}

	public String uuid;
	public long eFormId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String eFormNo;
	public String serviceCode;
	public String fileTemplateNo;
	public String eFormName;
	public long formScriptFileId;
	public long formReportFileId;
	public String eFormData;
	public String email;
	public String secret;
	public String govAgencyCode;
}