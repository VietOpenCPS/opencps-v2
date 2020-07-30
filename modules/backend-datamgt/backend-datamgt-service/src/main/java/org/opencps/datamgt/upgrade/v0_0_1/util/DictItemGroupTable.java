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

package org.opencps.datamgt.upgrade.v0_0_1.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DictItemGroupTable {

	public static final String TABLE_NAME = "opencps_dictgroup";

	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "dictGroupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "dictCollectionId", Types.BIGINT },
			{ "groupCode", Types.VARCHAR },
			{ "groupName", Types.VARCHAR },
			{ "groupNameEN", Types.VARCHAR },
			{ "groupDescription", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dictGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("dictCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupNameEN", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupDescription", Types.VARCHAR);
	}
	
	public static final String TABLE_SQL_CREATE = "create table opencps_dictgroup (uuid_ VARCHAR(75) null,dictGroupId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,dictCollectionId LONG,groupCode VARCHAR(100) null,groupName STRING null,groupNameEN STRING null,groupDescription TEXT null)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_D45B5ABB on opencps_dictgroup (dictCollectionId, groupId)",
			"create index IX_A170B7BD on opencps_dictgroup (groupCode, groupId, dictCollectionId)",
			"create index IX_6850BFD3 on opencps_dictgroup (modifiedDate, groupId)",
			"create index IX_A99E2460 on opencps_dictgroup (uuid_, companyId)",
			"create unique index IX_1B2442E2 on opencps_dictgroup (uuid_ groupId)",
		};

	public static final String TABLE_SQL_DROP = "drop table opencps_dictgroup";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long STATUS_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long MODIFIEDDATE_COLUMN_BITMASK = 16L;

	public static final long CREATEDATE_COLUMN_BITMASK = 32L;

}