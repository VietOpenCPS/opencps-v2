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

package org.opencps.communication.upgrade.v1_0_1.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LGSPTokenTable {

	public static final String TABLE_NAME = "opencps_lgsp_token";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "tokenId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "token", Types.VARCHAR },
			{ "tokenType", Types.VARCHAR },
			{ "refreshToken", Types.VARCHAR },
			{ "expiryDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("tokenId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("token", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("tokenType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("refreshToken", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("expiryDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_lgsp_token (uuid_ VARCHAR(75) null,tokenId LONG not null primary key,createDate DATE null,modifiedDate DATE null,token VARCHAR(75) null,tokenType VARCHAR(75) null,refreshToken VARCHAR(75) null,expiryDate DATE null)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create unique index IX_42C3A548 on opencps_lgsp_token (tokenType)",
			"create index IX_D07481B9 on opencps_lgsp_token (uuid_)"
		};

	

}