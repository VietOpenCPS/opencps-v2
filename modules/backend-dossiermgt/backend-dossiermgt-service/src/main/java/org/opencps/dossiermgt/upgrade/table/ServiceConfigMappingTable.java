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

package org.opencps.dossiermgt.upgrade.v0_0_1.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ServiceConfigMappingTable {

	public static final String TABLE_NAME = "opencps_serviceconfigmapping";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "serviceConfigMappingId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "serviceConfigCode", Types.VARCHAR },
			{ "serviceConfigName", Types.VARCHAR },
			{ "serviceCode", Types.VARCHAR },
			{ "serviceName", Types.VARCHAR },
			{ "govAgencyName", Types.VARCHAR },
			{ "domainName", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceConfigMappingId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("serviceConfigCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceConfigName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("govAgencyName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("domainName", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_serviceconfigmapping (uuid_ VARCHAR(75) null,serviceConfigMappingId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,serviceConfigCode VARCHAR(75) null,serviceConfigName VARCHAR(75) null,serviceCode VARCHAR(75) null,serviceName VARCHAR(75) null,govAgencyName VARCHAR(75) null,domainName VARCHAR(75) null)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_80005EC8 on opencps_serviceconfigmapping (groupId, serviceCode)",
			"create index IX_6399334 on opencps_serviceconfigmapping (uuid_, companyId)",
			"create unique index IX_57B556B6 on opencps_serviceconfigmapping (uuid_, groupId)"
		};

	

}