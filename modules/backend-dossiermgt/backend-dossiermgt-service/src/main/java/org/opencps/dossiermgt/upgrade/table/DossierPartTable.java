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
public class DossierPartTable {

	public static final String TABLE_NAME = "opencps_dossierpart";

	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "dossierPartId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "templateNo", Types.VARCHAR },
			{ "partNo", Types.VARCHAR },
			{ "partName", Types.VARCHAR },
			{ "partTip", Types.VARCHAR },
			{ "partType", Types.INTEGER },
			{ "multiple", Types.BOOLEAN },
			{ "formScript", Types.VARCHAR },
			{ "formReport", Types.VARCHAR },
			{ "sampleData", Types.VARCHAR },
			{ "required", Types.BOOLEAN },
			{ "fileTemplateNo", Types.VARCHAR },
			{ "eSign", Types.BOOLEAN },
			{ "deliverableType", Types.VARCHAR },
			{ "deliverableAction", Types.INTEGER },
			{ "eForm", Types.BOOLEAN },
			{ "fileMark", Types.INTEGER },
			{ "partNameTitle", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierPartId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("templateNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("partNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("partName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("partTip", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("partType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("multiple", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("formScript", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("formReport", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sampleData", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("required", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("fileTemplateNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("eSign", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("deliverableType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("deliverableAction", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("eForm", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("fileMark", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("partNameTitle", Types.VARCHAR);
	}

	
	public static final String TABLE_SQL_CREATE = "create table opencps_dossierpart (uuid_ VARCHAR(75) null,dossierPartId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,templateNo VARCHAR(255) null,partNo VARCHAR(255) null,partName VARCHAR(500) null,partTip TEXT null,partType INTEGER,multiple BOOLEAN,formScript TEXT null,formReport TEXT null,sampleData TEXT null,required BOOLEAN,fileTemplateNo VARCHAR(255) null,eSign BOOLEAN,deliverableType VARCHAR(500) null,deliverableAction INTEGER,eForm BOOLEAN,fileMark INTEGER,partNameTitle VARCHAR(500) null)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_B54EC54 on opencps_dossierpart (groupId, fileTemplateNo)",
			"create index IX_7715C063 on opencps_dossierpart (groupId, templateNo, fileTemplateNo)",
			"create index IX_991D1DC0 on opencps_dossierpart (groupId, templateNo, partNo)",
			"create index IX_72E94D19 on opencps_dossierpart (templateNo, partNo, partType, eSign)",
			"create index IX_5E4D5D55 on opencps_dossierpart (uuid_, companyId)",
			"create unique index IX_ACC73517 on opencps_dossierpart (uuid_, groupId)",
		};

	

}