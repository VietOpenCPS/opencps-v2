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

package org.opencps.synctracking.upgrade.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SyncTrackingTable {

	public static final String TABLE_NAME = "opencps_synctracking";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "trackingId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "dossierNo", Types.VARCHAR },
			{ "referenceUid", Types.VARCHAR },
			{ "serverNo", Types.VARCHAR },
			{ "protocol", Types.VARCHAR },
			{ "stateSync", Types.INTEGER },
			{ "serviceCode", Types.VARCHAR },
			{ "api", Types.VARCHAR },
			{ "fromUnit", Types.VARCHAR },
			{ "toUnit", Types.VARCHAR },
			{ "bodyRequest", Types.VARCHAR },
			{ "response", Types.VARCHAR },
			{ "metaData", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("trackingId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("dossierNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("referenceUid", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serverNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("protocol", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("stateSync", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("serviceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("api", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fromUnit", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("toUnit", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("bodyRequest", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("response", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("metaData", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_synctracking (uuid_ VARCHAR(75) null,trackingId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,dossierNo VARCHAR(75) null,referenceUid VARCHAR(75) null,serverNo VARCHAR(75) null,protocol VARCHAR(75) null,stateSync INTEGER,serviceCode VARCHAR(75) null,api VARCHAR(75) null,fromUnit VARCHAR(75) null,toUnit VARCHAR(75) null,bodyRequest VARCHAR(75) null,response VARCHAR(75) null,metaData VARCHAR(75) null)";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_5D8AF1B8 on opencps_synctracking (groupId, createDate, modifiedDate)",
			"create index IX_FC376796 on opencps_synctracking (groupId, dossierNo, createDate, modifiedDate)",
			"create index IX_C61B1D01 on opencps_synctracking (groupId, protocol, dossierNo)",
			"create index IX_E5F3717 on opencps_synctracking (groupId, protocol, serviceCode)",
			"create index IX_2C0E4F2C on opencps_synctracking (groupId, serviceCode, createDate, modifiedDate)",
			"create index IX_1366D9A2 on opencps_synctracking (groupId, serviceCode, dossierNo, createDate, modifiedDate)",
			"create index IX_27BF4A4F on opencps_synctracking (uuid, companyId)",
			"create unique index IX_49E41891 on opencps_synctracking (uuid, groupId)"

		};

	

}