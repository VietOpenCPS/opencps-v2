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

package org.opencps.dossiermgt.upgrade.table;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PostConnectTable {

	public static final String TABLE_NAME = "opencps_postconnect";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "postConnectId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "dossierId", Types.BIGINT },
			{ "postService", Types.INTEGER },
			{ "postType", Types.INTEGER },
			{ "orderNumber", Types.VARCHAR },
			{ "postStatus", Types.INTEGER },
			{ "metadata", Types.VARCHAR },
			{ "syncState", Types.INTEGER },
			{ "retry", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postConnectId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("dossierId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("postService", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("postType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("orderNumber", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postStatus", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("metadata", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("syncState", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("retry", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_postconnect (uuid_ VARCHAR(75) null,postConnectId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,dossierId LONG,postService INTEGER,postType INTEGER,orderNumber VARCHAR(75) null,postStatus INTEGER,metadata VARCHAR(75) null,syncState INTEGER,retry INTEGER)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_BB72AF29 on opencps_postconnect (groupId, syncState)",
			"create index IX_31CF29C0 on opencps_postconnect (orderNumber, postStatus)",
			"create index IX_5E22119 on opencps_postconnect (syncState)",
			"create index IX_42CAC661 on opencps_postconnect (uuid_, companyId)",
			"create unique index IX_C7637123 on opencps_postconnect (uuid_, groupId)"
		};

	

}