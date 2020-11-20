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
public class DictItemMappingTable {

	public static final String TABLE_NAME = "opencps_dictItemmapping";

	public static final Object[][] TABLE_COLUMNS = {
			{ "mappingId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "itemCode", Types.VARCHAR },
			{ "itemCodeDVCQG", Types.VARCHAR },
			{ "collectionId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mappingId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("itemCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("itemCodeDVCQG", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("collectionId", Types.BIGINT);
	}
	
	public static final String TABLE_SQL_CREATE = "create table opencps_dictItemmapping (mappingId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,itemCode VARCHAR(255) null,itemCodeDVCQG VARCHAR(255) null,collectionId LONG)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_BD0BDD4F on opencps_dictItemmapping (groupId, collectionId)",
			"create index IX_23C02E63 on opencps_dictItemmapping (groupId, itemCode, collectionId)",
			"create index IX_A1CE1162 on opencps_dictItemmapping (groupId, itemCodeDVCQG, collectionId)"
		};

	public static final String TABLE_SQL_DROP = "drop table opencps_dictItemmapping";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long STATUS_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long MODIFIEDDATE_COLUMN_BITMASK = 16L;

	public static final long CREATEDATE_COLUMN_BITMASK = 32L;

}