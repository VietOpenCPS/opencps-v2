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

import org.opencps.adminconfig.model.DynamicReport;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DynamicReport in entity cache.
 *
 * @author binhth
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
		StringBundler sb = new StringBundler(27);

		sb.append("{dynamicReportId=");
		sb.append(dynamicReportId);
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
		sb.append(", sharing=");
		sb.append(sharing);
		sb.append(", reportName=");
		sb.append(reportName);
		sb.append(", reportCode=");
		sb.append(reportCode);
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

		dynamicReportImpl.setDynamicReportId(dynamicReportId);
		dynamicReportImpl.setGroupId(groupId);
		dynamicReportImpl.setCompanyId(companyId);
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

		dynamicReportImpl.setSharing(sharing);

		if (reportName == null) {
			dynamicReportImpl.setReportName("");
		}
		else {
			dynamicReportImpl.setReportName(reportName);
		}

		if (reportCode == null) {
			dynamicReportImpl.setReportCode("");
		}
		else {
			dynamicReportImpl.setReportCode(reportCode);
		}

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
		dynamicReportId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		sharing = objectInput.readInt();
		reportName = objectInput.readUTF();
		reportCode = objectInput.readUTF();
		filterConfig = objectInput.readUTF();
		tableConfig = objectInput.readUTF();
		userConfig = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(dynamicReportId);

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

		objectOutput.writeInt(sharing);

		if (reportName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reportName);
		}

		if (reportCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reportCode);
		}

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

	public long dynamicReportId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int sharing;
	public String reportName;
	public String reportCode;
	public String filterConfig;
	public String tableConfig;
	public String userConfig;
}