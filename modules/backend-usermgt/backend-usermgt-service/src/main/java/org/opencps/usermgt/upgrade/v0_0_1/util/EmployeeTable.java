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
public class EmployeeTable {

	public static final String TABLE_NAME = "opencps_employee";

	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "employeeId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "employeeNo", Types.VARCHAR },
			{ "fullName", Types.VARCHAR },
			{ "title", Types.VARCHAR },
			{ "gender", Types.INTEGER },
			{ "birthdate", Types.TIMESTAMP },
			{ "telNo", Types.VARCHAR },
			{ "mobile", Types.VARCHAR },
			{ "email", Types.VARCHAR },
			{ "workingStatus", Types.INTEGER },
			{ "mappingUserId", Types.BIGINT },
			{ "mainJobPostId", Types.BIGINT },
			{ "photoFileEntryId", Types.BIGINT },
			{ "recruitDate", Types.TIMESTAMP },
			{ "leaveDate", Types.TIMESTAMP },
			{ "fileCertId", Types.BIGINT },
			{ "fileSignId", Types.BIGINT },
			{ "fileCertPath", Types.VARCHAR },
			{ "fileSignPath", Types.VARCHAR },
			{ "jobPosTitle", Types.VARCHAR },
			{ "scope", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("employeeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("employeeNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fullName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("gender", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("birthdate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("telNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("mobile", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("email", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("workingStatus", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("mappingUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("mainJobPostId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("photoFileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("recruitDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("leaveDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("fileCertId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileSignId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileCertPath", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileSignPath", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("jobPosTitle", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("scope", Types.VARCHAR);
	}
	
	public static final String TABLE_SQL_CREATE = "create table opencps_employee (uuid_ VARCHAR(75) null,employeeId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,employeeNo VARCHAR(255) null,fullName VARCHAR(1024) null,title VARCHAR(1024) null,gender INTEGER,birthdate DATE null,telNo VARCHAR(255) null,mobile VARCHAR(255) null,email VARCHAR(512) null,workingStatus INTEGER,mappingUserId LONG,mainJobPostId LONG,photoFileEntryId LONG,recruitDate DATE null,leaveDate DATE null,fileCertId LONG,fileSignId LONG,fileCertPath VARCHAR(512) null,fileSignPath VARCHAR(512) null,jobPosTitle VARCHAR(75) null,scope VARCHAR(128) null)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_370E9375 on opencps_employee (email)",
			"create index IX_A8503959 on opencps_employee (groupId, email)",
			"create index IX_FFD92AB0 on opencps_employee (groupId, employeeId)",
			"create index IX_FFDB99D6 on opencps_employee (groupId, employeeNo)",
			"create index IX_11A42D71 on opencps_employee (groupId, mappingUserId)",
			"create index IX_24724BCD on opencps_employee (groupId, userId)",
			"create index IX_4BDCCB46 on opencps_employee (mappingUserId, workingStatus)",
			"create index IX_7941D62B on opencps_employee (uuid_, companyId)",
			"create unique index IX_370FEB6D on opencps_employee (uuid_, groupId)"
		};

	

}