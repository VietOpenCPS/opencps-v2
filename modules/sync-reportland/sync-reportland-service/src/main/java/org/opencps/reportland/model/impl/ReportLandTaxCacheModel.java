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

package org.opencps.reportland.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import org.opencps.reportland.model.ReportLandTax;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ReportLandTax in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ReportLandTax
 * @generated
 */
@ProviderType
public class ReportLandTaxCacheModel implements CacheModel<ReportLandTax>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReportLandTaxCacheModel)) {
			return false;
		}

		ReportLandTaxCacheModel reportLandTaxCacheModel = (ReportLandTaxCacheModel)obj;

		if (reportId == reportLandTaxCacheModel.reportId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, reportId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", reportId=");
		sb.append(reportId);
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
		sb.append(", dossierNo=");
		sb.append(dossierNo);
		sb.append(", bodyRequest=");
		sb.append(bodyRequest);
		sb.append(", response=");
		sb.append(response);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ReportLandTax toEntityModel() {
		ReportLandTaxImpl reportLandTaxImpl = new ReportLandTaxImpl();

		if (uuid == null) {
			reportLandTaxImpl.setUuid("");
		}
		else {
			reportLandTaxImpl.setUuid(uuid);
		}

		reportLandTaxImpl.setReportId(reportId);
		reportLandTaxImpl.setGroupId(groupId);
		reportLandTaxImpl.setCompanyId(companyId);
		reportLandTaxImpl.setUserId(userId);

		if (userName == null) {
			reportLandTaxImpl.setUserName("");
		}
		else {
			reportLandTaxImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			reportLandTaxImpl.setCreateDate(null);
		}
		else {
			reportLandTaxImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			reportLandTaxImpl.setModifiedDate(null);
		}
		else {
			reportLandTaxImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (dossierNo == null) {
			reportLandTaxImpl.setDossierNo("");
		}
		else {
			reportLandTaxImpl.setDossierNo(dossierNo);
		}

		if (bodyRequest == null) {
			reportLandTaxImpl.setBodyRequest("");
		}
		else {
			reportLandTaxImpl.setBodyRequest(bodyRequest);
		}

		if (response == null) {
			reportLandTaxImpl.setResponse("");
		}
		else {
			reportLandTaxImpl.setResponse(response);
		}

		reportLandTaxImpl.resetOriginalValues();

		return reportLandTaxImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		reportId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		dossierNo = objectInput.readUTF();
		bodyRequest = objectInput.readUTF();
		response = objectInput.readUTF();
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

		objectOutput.writeLong(reportId);

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

		if (dossierNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierNo);
		}

		if (bodyRequest == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(bodyRequest);
		}

		if (response == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(response);
		}
	}

	public String uuid;
	public long reportId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String dossierNo;
	public String bodyRequest;
	public String response;
}