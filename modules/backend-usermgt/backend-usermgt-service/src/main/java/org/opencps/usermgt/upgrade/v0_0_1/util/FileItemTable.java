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
public class FileItemTable {

	public static final String TABLE_NAME = "opencps_fileitem";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "fileItemId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "fileTemplateNo", Types.VARCHAR },
			{ "name", Types.VARCHAR },
			{ "status", Types.INTEGER },
			{ "size_", Types.INTEGER },
			{ "fileType", Types.VARCHAR },
			{ "log_", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileItemId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileTemplateNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("size_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("fileType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("log_", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_fileitem (uuid_ VARCHAR(75) null,fileItemId LONG not null primary key,createDate DATE null,modifiedDate DATE null,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(255) null,fileTemplateNo VARCHAR(128) null,name TEXT null,status INTEGER,size_ INTEGER,fileType VARCHAR(128) null,log_ TEXT null)";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_A082263D on opencps_fileitem (groupId, fileTemplateNo)",
			"create index IX_9F5B50F8 on opencps_fileitem (groupId, status)",
			"create index IX_E9DD878C on opencps_fileitem (uuid_, companyId)",
			"create unique index IX_37FA810E on opencps_fileitem (uuid_, groupId)"

		};

	

}