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
public class SyncSchedulerTable {

	public static final String TABLE_NAME = "opencps_sync_scheduler";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "syncSchedulerId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "groupId", Types.BIGINT },
			{ "className", Types.VARCHAR },
			{ "typeCode", Types.VARCHAR },
			{ "syncDate", Types.TIMESTAMP },
			{ "retry", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("syncSchedulerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("className", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("typeCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("syncDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("retry", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_sync_scheduler (uuid_ VARCHAR(75) null,syncSchedulerId LONG not null primary key,createDate DATE null,modifiedDate DATE null,groupId LONG,className VARCHAR(255) null,typeCode VARCHAR(255) null,syncDate DATE null,retry INTEGER)";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_E6980DC5 on opencps_sync_scheduler (className, retry)",
			"create index IX_2247AC10 on opencps_sync_scheduler (className, syncDate)",
			"create index IX_4F2917EE on opencps_sync_scheduler (className, typeCode)",
			"create unique index IX_7962DE56 on opencps_sync_scheduler (uuid_, groupId)"
	};

	

}