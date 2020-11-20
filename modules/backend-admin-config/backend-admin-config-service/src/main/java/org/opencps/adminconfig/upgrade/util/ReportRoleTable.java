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

package org.opencps.adminconfig.upgrade.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ReportRoleTable {

	public static final String TABLE_NAME = "opencps_reportrole";
	public static final Object[][] TABLE_COLUMNS = {
			{ "reportRoleId", Types.BIGINT },
			{ "dynamicReportId", Types.BIGINT },
			{ "roleId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("reportRoleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("dynamicReportId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("roleId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_reportrole (reportRoleId LONG not null primary key,dynamicReportId LONG,roleId LONG)";
	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_A0A5C810 on opencps_reportrole (dynamicReportId, roleId)",
			"create index IX_19973198 on opencps_reportrole (roleId)"
		};

	

}