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
public class TrackClientStatisticTable {

	public static final String TABLE_NAME = "opencps_track_client_statistic";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "trackClientStatisticId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "url", Types.VARCHAR },
			{ "year", Types.INTEGER },
			{ "month", Types.INTEGER },
			{ "day", Types.INTEGER },
			{ "region", Types.VARCHAR },
			{ "desktop", Types.BOOLEAN },
			{ "mobile", Types.BOOLEAN },
			{ "tablet", Types.BOOLEAN },
			{ "total", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("trackClientStatisticId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("url", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("year", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("month", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("day", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("region", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("desktop", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("mobile", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("tablet", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("total", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_track_client_statistic (uuid_ VARCHAR(75) null,trackClientStatisticId LONG not null primary key,createDate DATE null,modifiedDate DATE null,url VARCHAR(2048) null,year INTEGER,month INTEGER,day INTEGER,region VARCHAR(512) null,desktop BOOLEAN,mobile BOOLEAN,tablet BOOLEAN,total LONG)";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_8E58C98E on opencps_track_client_statistic (day, month, year, desktop, mobile, tablet)",
			"create index IX_68556269 on opencps_track_client_statistic (url, year, month, day, desktop, mobile, tablet)",
			"create index IX_7281E1BB on opencps_track_client_statistic (uuid_)"

		};

	

}