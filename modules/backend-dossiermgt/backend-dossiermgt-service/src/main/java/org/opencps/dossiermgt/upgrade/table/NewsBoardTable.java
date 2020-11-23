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
public class NewsBoardTable {

	public static final String TABLE_NAME = "opencps_newsboard";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "newsBoardId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "newsTitle", Types.VARCHAR },
			{ "newsContent", Types.VARCHAR },
			{ "newsStatus", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("newsBoardId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("newsTitle", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("newsContent", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("newsStatus", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_newsboard (uuid_ VARCHAR(75) null,newsBoardId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,newsTitle VARCHAR(75) null,newsContent VARCHAR(75) null,newsStatus INTEGER)";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_DF045F14 on opencps_newsboard (groupId)",
			"create index IX_398BAECA on opencps_newsboard (uuid_, companyId)",
			"create unique index IX_96E55FCC on opencps_newsboard (uuid_, groupId)"

		};

	

}