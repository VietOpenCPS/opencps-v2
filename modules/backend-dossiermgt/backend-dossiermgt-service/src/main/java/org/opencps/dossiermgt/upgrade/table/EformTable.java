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
public class EformTable {

	public static final String TABLE_NAME = "opencps_eform";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "eFormId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "eFormNo", Types.VARCHAR },
			{ "serviceCode", Types.VARCHAR },
			{ "fileTemplateNo", Types.VARCHAR },
			{ "eFormName", Types.VARCHAR },
			{ "formScriptFileId", Types.BIGINT },
			{ "formReportFileId", Types.BIGINT },
			{ "eFormData", Types.VARCHAR },
			{ "email", Types.VARCHAR },
			{ "secret", Types.VARCHAR },
			{ "govAgencyCode", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("eFormId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("eFormNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileTemplateNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("eFormName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("formScriptFileId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("formReportFileId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("eFormData", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("email", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("secret", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("govAgencyCode", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_eform (uuid_ VARCHAR(75) null,eFormId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,eFormNo VARCHAR(128) null,serviceCode VARCHAR(128) null,fileTemplateNo VARCHAR(128) null,eFormName VARCHAR(512) null,formScriptFileId LONG,formReportFileId LONG,eFormData TEXT null,email VARCHAR(255) null,secret VARCHAR(75) null,govAgencyCode VARCHAR(75) null)";
	
	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_20618BC on opencps_eform (groupId, eFormNo)",
			"create index IX_C7E6F694 on opencps_eform (groupId, serviceCode)",
			"create index IX_855BF100 on opencps_eform (uuid_, companyId)",
			"create unique index IX_D72CB782 on opencps_eform (uuid_, groupId)"
		};

	

}