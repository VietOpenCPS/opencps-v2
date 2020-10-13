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
public class ServiceInfoTable {

	public static final String TABLE_NAME = "opencps_serviceinfo";

	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "serviceInfoId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "serviceCode", Types.VARCHAR },
			{ "serviceName", Types.VARCHAR },
			{ "processText", Types.VARCHAR },
			{ "methodText", Types.VARCHAR },
			{ "dossierText", Types.VARCHAR },
			{ "conditionText", Types.VARCHAR },
			{ "durationText", Types.VARCHAR },
			{ "applicantText", Types.VARCHAR },
			{ "resultText", Types.VARCHAR },
			{ "regularText", Types.VARCHAR },
			{ "feeText", Types.VARCHAR },
			{ "administrationCode", Types.VARCHAR },
			{ "administrationName", Types.VARCHAR },
			{ "administrationIndex", Types.VARCHAR },
			{ "domainCode", Types.VARCHAR },
			{ "domainName", Types.VARCHAR },
			{ "domainIndex", Types.VARCHAR },
			{ "maxLevel", Types.INTEGER },
			{ "public_", Types.BOOLEAN },
			{ "govAgencyText", Types.VARCHAR },
			{ "isNotarization", Types.BOOLEAN },
			{ "serviceNameTitle", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceInfoId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("serviceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("processText", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("methodText", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierText", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("conditionText", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("durationText", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("applicantText", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("resultText", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("regularText", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("feeText", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("administrationCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("administrationName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("administrationIndex", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("domainCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("domainName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("domainIndex", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("maxLevel", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("public_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("govAgencyText", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("isNotarization", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("serviceNameTitle", Types.VARCHAR);
	}

	
	public static final String TABLE_SQL_CREATE = "create table opencps_serviceinfo (uuid_ VARCHAR(75) null,serviceInfoId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,serviceCode VARCHAR(128) null,serviceName TEXT null,processText TEXT null,methodText TEXT null,dossierText TEXT null,conditionText TEXT null,durationText TEXT null,applicantText TEXT null,resultText TEXT null,regularText TEXT null,feeText TEXT null,administrationCode VARCHAR(128) null,administrationName VARCHAR(500) null,administrationIndex VARCHAR(128) null,domainCode VARCHAR(128) null,domainName TEXT null,domainIndex VARCHAR(128) null,maxLevel INTEGER,public_ BOOLEAN,govAgencyText TEXT null,isNotarization BOOLEAN,serviceNameTitle VARCHAR(500) null)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_3AF75462 on opencps_serviceinfo (companyId)",
			"create index IX_216AA2FF on opencps_serviceinfo (domainCode, groupId)",
			"create index IX_FCA7408C on opencps_serviceinfo (groupId, public_, serviceInfoId)",
			"create index IX_2FC150E on opencps_serviceinfo (groupId, serviceCode)",
			"create index IX_3E222E7E on opencps_serviceinfo (serviceCode, groupId)",
			"create index IX_D3675C7A on opencps_serviceinfo (uuid_, companyId)",
			"create unique index IX_D630B97C on opencps_serviceinfo (uuid_, groupId)"
		};

	

}