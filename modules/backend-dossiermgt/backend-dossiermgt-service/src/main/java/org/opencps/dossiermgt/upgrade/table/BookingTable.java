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
public class BookingTable {

	public static final String TABLE_NAME = "opencps_booking";

	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "bookingId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "className", Types.VARCHAR },
			{ "classPK", Types.BIGINT },
			{ "serviceCode", Types.VARCHAR },
			{ "codeNumber", Types.VARCHAR },
			{ "bookingName", Types.VARCHAR },
			{ "checkinDate", Types.TIMESTAMP },
			{ "gateNumber", Types.VARCHAR },
			{ "state_", Types.INTEGER },
			{ "bookingDate", Types.TIMESTAMP },
			{ "speaking", Types.BOOLEAN },
			{ "serviceGroupCode", Types.VARCHAR },
			{ "count", Types.INTEGER },
			{ "online_", Types.BOOLEAN },
			{ "bookingInTime", Types.VARCHAR },
			{ "telNo", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("bookingId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("className", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("serviceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("codeNumber", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("bookingName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("checkinDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("gateNumber", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("state_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("bookingDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("speaking", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("serviceGroupCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("count", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("online_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("bookingInTime", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("telNo", Types.VARCHAR);
	}

	
	public static final String TABLE_SQL_CREATE = "create table opencps_booking (uuid_ VARCHAR(75) null,bookingId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,className VARCHAR(255) null,classPK LONG,serviceCode VARCHAR(128) null,codeNumber VARCHAR(255) null,bookingName VARCHAR(512) null,checkinDate DATE null,gateNumber VARCHAR(255) null,state_ INTEGER,bookingDate DATE null,speaking BOOLEAN,serviceGroupCode VARCHAR(255) null,count INTEGER,online_ BOOLEAN,bookingInTime VARCHAR(255) null,telNo VARCHAR(128) null)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_96163B06 on opencps_booking (codeNumber)",
			"create index IX_35D7C193 on opencps_booking (groupId, bookingDate, online_)",
			"create index IX_68310EDE on opencps_booking (groupId, className, classPK)",
			"create index IX_D55B8904 on opencps_booking (groupId, serviceCode)",
			"create index IX_915B3B70 on opencps_booking (uuid_, companyId)",
			"create unique index IX_F25BDDF2 on opencps_booking (uuid_, groupId)",
		};

	

}