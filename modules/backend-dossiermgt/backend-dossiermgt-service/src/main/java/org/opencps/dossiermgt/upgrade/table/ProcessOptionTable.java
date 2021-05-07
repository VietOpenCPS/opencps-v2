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
public class ProcessOptionTable {

	public static final String TABLE_NAME = "opencps_processoption";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "processOptionId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "serviceConfigId", Types.BIGINT },
			{ "optionOrder", Types.INTEGER },
			{ "optionName", Types.VARCHAR },
			{ "autoSelect", Types.VARCHAR },
			{ "dossierTemplateId", Types.BIGINT },
			{ "serviceProcessId", Types.BIGINT },
			{ "instructionNote", Types.VARCHAR },
			{ "submissionNote", Types.VARCHAR },
			{ "sampleCount", Types.BIGINT },
			{ "registerBookCode", Types.VARCHAR },
			{ "forCitizen", Types.BOOLEAN },
			{ "forBusiness", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("processOptionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("serviceConfigId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("optionOrder", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("optionName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("autoSelect", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierTemplateId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("serviceProcessId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("instructionNote", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("submissionNote", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sampleCount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("registerBookCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("forCitizen", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("forBusiness", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_processoption (uuid_ VARCHAR(75) null,processOptionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,serviceConfigId LONG,optionOrder INTEGER,optionName VARCHAR(500) null,autoSelect VARCHAR(500) null,dossierTemplateId LONG,serviceProcessId LONG,instructionNote TEXT null,submissionNote TEXT null,sampleCount LONG,registerBookCode VARCHAR(100) null,forCitizen BOOLEAN,forBusiness BOOLEAN)";
	
	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_D8C5543 on opencps_processoption (groupId)",
			"create index IX_28B30015 on opencps_processoption (serviceConfigId, dossierTemplateId)",
			"create index IX_5B82BB8E on opencps_processoption (serviceConfigId, optionOrder)",
			"create index IX_9D238C20 on opencps_processoption (serviceProcessId, dossierTemplateId)",
			"create index IX_DF143D7B on opencps_processoption (uuid_, companyId)",
			"create unique index IX_C5F3E6BD on opencps_processoption (uuid_, groupId)"
		};

	

}