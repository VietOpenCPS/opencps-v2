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

package org.opencps.usermgt.upgrade.v0_0_1.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApplicantDataTable {

	public static final String TABLE_NAME = "opencps_applicant_data";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "applicantDataId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "fileTemplateNo", Types.VARCHAR },
			{ "fileNo", Types.VARCHAR },
			{ "fileName", Types.VARCHAR },
			{ "fileEntryId", Types.BIGINT },
			{ "metadata", Types.VARCHAR },
			{ "status", Types.INTEGER },
			{ "applicantIdNo", Types.VARCHAR },
			{ "applicantDataType", Types.INTEGER },
			{ "dossierNo", Types.VARCHAR },
			{ "log_", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("applicantDataId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileTemplateNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("metadata", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("applicantIdNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("applicantDataType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("dossierNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("log_", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_applicant_data (uuid_ VARCHAR(75) null,applicantDataId LONG not null primary key,createDate DATE null,modifiedDate DATE null,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(255) null,fileTemplateNo VARCHAR(255) null,fileNo VARCHAR(255) null,fileName VARCHAR(1024) null,fileEntryId LONG,metadata TEXT null,status INTEGER,applicantIdNo VARCHAR(128) null,applicantDataType INTEGER,dossierNo VARCHAR(128) null,log_ TEXT null)";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_7160D281 on opencps_applicant_data (groupId, dossierNo, fileTemplateNo, applicantIdNo)",
			"create index IX_14A28584 on opencps_applicant_data (uuid_, companyId)",
			"create unique index IX_49569D06 on opencps_applicant_data (uuid_, groupId)"

	};

	

}