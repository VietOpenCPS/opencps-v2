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
public class EmployeeJobPosTable {

	public static final String TABLE_NAME = "opencps_employee_jobpos";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "employeeJobPosId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "employeeId", Types.BIGINT },
			{ "jobPostId", Types.BIGINT },
			{ "workingUnitId", Types.BIGINT },
			{ "status", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("employeeJobPosId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("employeeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("jobPostId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("workingUnitId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_employee_jobpos (uuid_ VARCHAR(75) null,employeeJobPosId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,employeeId LONG,jobPostId LONG,workingUnitId LONG,status INTEGER)";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_253CEBA8 on opencps_employee_jobpos (employeeId)",
			"create index IX_45F412F4 on opencps_employee_jobpos (groupId, employeeId, jobPostId, workingUnitId)",
			"create index IX_DCBDEC9 on opencps_employee_jobpos (groupId, jobPostId)",
			"create index IX_F9CB80BF on opencps_employee_jobpos (uuid_, companyId)",
			"create unique index IX_E16B2B01 on opencps_employee_jobpos (uuid_, groupId)",
			"create index IX_B6CA9215 on opencps_employee_jobpos (workingUnitId)"

		};

	

}