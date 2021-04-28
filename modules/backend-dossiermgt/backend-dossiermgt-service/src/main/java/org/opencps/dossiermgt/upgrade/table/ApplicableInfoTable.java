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
public class ApplicableInfoTable {

	public static final String TABLE_NAME = "opencps_applicableInfo";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "applicableInfoId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "serviceCode", Types.VARCHAR },
			{ "govAgencyCode", Types.VARCHAR },
			{ "serviceLevel", Types.INTEGER },
			{ "serviceConfigMappingId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("applicableInfoId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("serviceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("govAgencyCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceLevel", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("serviceConfigMappingId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_applicableInfo (uuid_ VARCHAR(75) null,applicableInfoId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,serviceCode VARCHAR(75) null,govAgencyCode VARCHAR(75) null,serviceLevel INTEGER,serviceConfigMappingId LONG)";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_362B3AF5 on opencps_applicableInfo (groupId, serviceCode, govAgencyCode, serviceLevel)",
			"create index IX_938871C on opencps_applicableInfo (serviceConfigMappingId)",
			"create index IX_F494E0AA on opencps_applicableInfo (uuid_, companyId)",
			"create unique index IX_FC5189AC on opencps_applicableInfo (uuid_, groupId)"
		};

	

}