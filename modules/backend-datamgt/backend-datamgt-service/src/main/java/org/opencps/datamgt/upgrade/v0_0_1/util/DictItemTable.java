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
public class DictItemTable {

	public static final String TABLE_NAME = "opencps_dictitem";

	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "dictItemId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "dictCollectionId", Types.BIGINT },
			{ "itemCode", Types.VARCHAR },
			{ "itemName", Types.VARCHAR },
			{ "itemNameEN", Types.VARCHAR },
			{ "itemDescription", Types.VARCHAR },
			{ "parentItemId", Types.BIGINT },
			{ "level", Types.INTEGER },
			{ "sibling", Types.VARCHAR },
			{ "treeIndex", Types.VARCHAR },
			{ "metaData", Types.VARCHAR },
			{ "idLGSP", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dictItemId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("dictCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("itemCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("itemName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("itemNameEN", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("itemDescription", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("parentItemId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("level", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("sibling", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("treeIndex", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("metaData", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("idLGSP", Types.BIGINT);
	}
	
	public static final String TABLE_SQL_CREATE = "create table opencps_dictitem (uuid_ VARCHAR(75) null,dictItemId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,dictCollectionId LONG,itemCode VARCHAR(100) null,itemName STRING null,itemNameEN STRING null,itemDescription TEXT null,parentItemId LONG,level INTEGER,sibling VARCHAR(255) null,treeIndex VARCHAR(255) null,metaData TEXT null,idLGSP LONG)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_9E48E215 on opencps_dictitem (dictCollectionId, groupId)",
			"create index IX_5EE7EDA3 on opencps_dictitem (dictCollectionId, parentItemId, treeIndex)",
			"create index IX_8CC4CD on opencps_dictitem (groupId, dictCollectionId, parentItemId, level)",
			"create index IX_D0E44BDC on opencps_dictitem (idLGSP, dictCollectionId)",
			"create index IX_750A2581 on opencps_dictitem (itemCode, dictCollectionId, groupId)",
			"create index IX_7B053904 on opencps_dictitem (itemCode, groupId)",
			"create index IX_48C042D on opencps_dictitem (modifiedDate, groupId)",
			"create index IX_9A0339E on opencps_dictitem (parentItemId)",
			"create index IX_B66C0FC6 on opencps_dictitem (uuid_, companyId)",
			"create unique index IX_B121CFC8 on opencps_dictitem (uuid_, groupId)"
		};

	public static final String TABLE_SQL_DROP = "drop table opencps_dictitem";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long STATUS_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long MODIFIEDDATE_COLUMN_BITMASK = 16L;

	public static final long CREATEDATE_COLUMN_BITMASK = 32L;

}