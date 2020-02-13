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

import org.opencps.adminconfig.model.ReportRole;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ReportRole in entity cache.
 *
 * @author binhth
 * @see ReportRole
 * @generated
 */
@ProviderType
public class ReportRoleCacheModel implements CacheModel<ReportRole>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReportRoleCacheModel)) {
			return false;
		}

		ReportRoleCacheModel reportRoleCacheModel = (ReportRoleCacheModel)obj;

		if (reportRoleId == reportRoleCacheModel.reportRoleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, reportRoleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{reportRoleId=");
		sb.append(reportRoleId);
		sb.append(", dynamicReportId=");
		sb.append(dynamicReportId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ReportRole toEntityModel() {
		ReportRoleImpl reportRoleImpl = new ReportRoleImpl();

		reportRoleImpl.setReportRoleId(reportRoleId);
		reportRoleImpl.setDynamicReportId(dynamicReportId);
		reportRoleImpl.setRoleId(roleId);

		reportRoleImpl.resetOriginalValues();

		return reportRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		reportRoleId = objectInput.readLong();

		dynamicReportId = objectInput.readLong();

		roleId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(reportRoleId);

		objectOutput.writeLong(dynamicReportId);

		objectOutput.writeLong(roleId);
	}

	public long reportRoleId;
	public long dynamicReportId;
	public long roleId;
}