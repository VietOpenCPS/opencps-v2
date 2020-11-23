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
public class TrackClientTable {

	public static final String TABLE_NAME = "opencps_track_client";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "trackClientId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "sessionId", Types.VARCHAR },
			{ "url", Types.VARCHAR },
			{ "year", Types.INTEGER },
			{ "month", Types.INTEGER },
			{ "day", Types.INTEGER },
			{ "visitDate", Types.TIMESTAMP },
			{ "leaveDate", Types.TIMESTAMP },
			{ "clientIP", Types.VARCHAR },
			{ "macAddress", Types.VARCHAR },
			{ "region", Types.VARCHAR },
			{ "nation", Types.VARCHAR },
			{ "latitude", Types.VARCHAR },
			{ "longitude", Types.VARCHAR },
			{ "timeOnPage", Types.BIGINT },
			{ "desktop", Types.BOOLEAN },
			{ "mobile", Types.BOOLEAN },
			{ "tablet", Types.BOOLEAN },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("trackClientId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("sessionId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("url", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("year", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("month", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("day", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("visitDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("leaveDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("clientIP", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("macAddress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("region", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("nation", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("latitude", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("longitude", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("timeOnPage", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("desktop", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("mobile", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("tablet", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_track_client (uuid_ VARCHAR(75) null,trackClientId LONG not null primary key,createDate DATE null,modifiedDate DATE null,sessionId VARCHAR(128) null,url VARCHAR(2048) null,year INTEGER,month INTEGER,day INTEGER,visitDate DATE null,leaveDate DATE null,clientIP VARCHAR(128) null,macAddress VARCHAR(128) null,region VARCHAR(512) null,nation VARCHAR(512) null,latitude VARCHAR(128) null,longitude VARCHAR(128) null,timeOnPage LONG,desktop BOOLEAN,mobile BOOLEAN,tablet BOOLEAN,userId LONG,userName VARCHAR(75) null)";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_6B3973DC on opencps_track_client (sessionId, leaveDate)",
			"create index IX_4D2C3370 on opencps_track_client (sessionId, visitDate)",
			"create index IX_D692EFEC on opencps_track_client (uuid_)"

		};

	

}