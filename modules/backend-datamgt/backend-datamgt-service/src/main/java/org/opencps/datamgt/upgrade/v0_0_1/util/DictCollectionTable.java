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
public class DictCollectionTable {

	public static final String TABLE_NAME = "opencps_dictcollection";

	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "dictCollectionId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "collectionCode", Types.VARCHAR },
			{ "collectionName", Types.VARCHAR },
			{ "collectionNameEN", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "dataForm", Types.VARCHAR },
			{ "status", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dictCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("collectionCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("collectionName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("collectionNameEN", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dataForm", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}
	
	public static final String TABLE_SQL_CREATE = "create table opencps_dictcollection (uuid_ VARCHAR(75) null,dictCollectionId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,collectionCode VARCHAR(100) null,collectionName STRING null,collectionNameEN STRING null,description TEXT null,dataForm TEXT null,status INTEGER)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_A7CA616E on opencps_dictcollection (collectionCode, groupId)",
			"create index IX_ABBCA0ED on opencps_dictcollection (groupId)",
			"create index IX_1DAB9C2 on opencps_dictcollection (modifiedDate, groupId)",
			"create index IX_F3CD7111 on opencps_dictcollection (uuid_, companyId)",
			"create unique index IX_C57107D3 on opencps_dictcollection (uuid_, groupId)"
		};

	public static final String TABLE_SQL_DROP = "drop table opencps_dictcollection";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long STATUS_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long MODIFIEDDATE_COLUMN_BITMASK = 16L;

	public static final long CREATEDATE_COLUMN_BITMASK = 32L;

}