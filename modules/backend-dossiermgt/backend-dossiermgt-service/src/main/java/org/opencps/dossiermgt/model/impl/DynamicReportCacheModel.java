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

import org.opencps.dossiermgt.model.DynamicReport;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DynamicReport in entity cache.
 *
 * @author huymq
 * @see DynamicReport
 * @generated
 */
@ProviderType
public class DynamicReportCacheModel implements CacheModel<DynamicReport>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DynamicReportCacheModel)) {
			return false;
		}

		DynamicReportCacheModel dynamicReportCacheModel = (DynamicReportCacheModel)obj;

		if (dynamicReportId == dynamicReportCacheModel.dynamicReportId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dynamicReportId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dynamicReportId=");
		sb.append(dynamicReportId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", reportCode=");
		sb.append(reportCode);
		sb.append(", reportName=");
		sb.append(reportName);
		sb.append(", sharing=");
		sb.append(sharing);
		sb.append(", filterConfig=");
		sb.append(filterConfig);
		sb.append(", tableConfig=");
		sb.append(tableConfig);
		sb.append(", userConfig=");
		sb.append(userConfig);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DynamicReport toEntityModel() {
		DynamicReportImpl dynamicReportImpl = new DynamicReportImpl();

		if (uuid == null) {
			dynamicReportImpl.setUuid("");
		}
		else {
			dynamicReportImpl.setUuid(uuid);
		}

		dynamicReportImpl.setDynamicReportId(dynamicReportId);
		dynamicReportImpl.setCompanyId(companyId);
		dynamicReportImpl.setGroupId(groupId);
		dynamicReportImpl.setUserId(userId);

		if (userName == null) {
			dynamicReportImpl.setUserName("");
		}
		else {
			dynamicReportImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dynamicReportImpl.setCreateDate(null);
		}
		else {
			dynamicReportImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dynamicReportImpl.setModifiedDate(null);
		}
		else {
			dynamicReportImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (reportCode == null) {
			dynamicReportImpl.setReportCode("");
		}
		else {
			dynamicReportImpl.setReportCode(reportCode);
		}

		if (reportName == null) {
			dynamicReportImpl.setReportName("");
		}
		else {
			dynamicReportImpl.setReportName(reportName);
		}

		dynamicReportImpl.setSharing(sharing);

		if (filterConfig == null) {
			dynamicReportImpl.setFilterConfig("");
		}
		else {
			dynamicReportImpl.setFilterConfig(filterConfig);
		}

		if (tableConfig == null) {
			dynamicReportImpl.setTableConfig("");
		}
		else {
			dynamicReportImpl.setTableConfig(tableConfig);
		}

		if (userConfig == null) {
			dynamicReportImpl.setUserConfig("");
		}
		else {
			dynamicReportImpl.setUserConfig(userConfig);
		}

		dynamicReportImpl.resetOriginalValues();

		return dynamicReportImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dynamicReportId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		reportCode = objectInput.readUTF();
		reportName = objectInput.readUTF();

		sharing = objectInput.readInt();
		filterConfig = objectInput.readUTF();
		tableConfig = objectInput.readUTF();
		userConfig = objectInput.readUTF();
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

		objectOutput.writeLong(dynamicReportId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (reportCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reportCode);
		}

		if (reportName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reportName);
		}

		objectOutput.writeInt(sharing);

		if (filterConfig == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(filterConfig);
		}

		if (tableConfig == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tableConfig);
		}

		if (userConfig == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userConfig);
		}
	}

	public String uuid;
	public long dynamicReportId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String reportCode;
	public String reportName;
	public int sharing;
	public String filterConfig;
	public String tableConfig;
	public String userConfig;
}