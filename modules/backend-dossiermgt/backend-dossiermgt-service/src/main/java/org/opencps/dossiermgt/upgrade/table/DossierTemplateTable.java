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
public class DossierTemplateTable {

	public static final String TABLE_NAME = "opencps_dossiertemplate";

	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "dossierTemplateId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "templateName", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "templateNo", Types.VARCHAR },
			{ "newFormScript", Types.VARCHAR },
			{ "formMeta", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierTemplateId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("templateName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("templateNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("newFormScript", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("formMeta", Types.VARCHAR);
	}

	
	public static final String TABLE_SQL_CREATE = "create table opencps_dossiertemplate (uuid_ VARCHAR(75) null,dossierTemplateId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,templateName STRING null,description TEXT null,templateNo VARCHAR(255) null,newFormScript TEXT null,formMeta TEXT null)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create unique index IX_7351EDD1 on opencps_dossiertemplate (groupId, templateNo)",
			"create index IX_3B70A85C on opencps_dossiertemplate (uuid_, companyId)",
			"create unique index IX_167F95DE on opencps_dossiertemplate (uuid_, groupId)"
		};

	

}