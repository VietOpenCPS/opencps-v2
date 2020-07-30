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

package org.opencps.datamgt.upgrade.v0_0_1.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MFileAttachTable {

	public static final String TABLE_NAME = "m_fileattach";

	public static final Object[][] TABLE_COLUMNS = {
			{ "fileAttachId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "className", Types.VARCHAR },
			{ "classPK", Types.VARCHAR },
			{ "fullName", Types.VARCHAR },
			{ "email", Types.VARCHAR },
			{ "fileEntryId", Types.BIGINT },
			{ "source", Types.VARCHAR },
			{ "sourceUrl", Types.VARCHAR },
			{ "docFileId", Types.BIGINT },
			{ "fileName", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("fileAttachId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("className", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("classPK", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fullName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("email", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("source", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sourceUrl", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("docFileId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileName", Types.VARCHAR);
	}
	
	public static final String TABLE_SQL_CREATE = "create table m_fileattach (fileAttachId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,className VARCHAR(500) null,classPK VARCHAR(255) null,fullName VARCHAR(500) null,email VARCHAR(500) null,fileEntryId LONG,source VARCHAR(500) null,sourceUrl VARCHAR(500) null,docFileId LONG,fileName VARCHAR(500) null)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_9F60A0BE on m_fileattach (groupId, className[$COLUMN_LENGTH:500$], classPK, docFileId)"
		};

	public static final String TABLE_SQL_DROP = "drop table m_fileattach";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long STATUS_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long MODIFIEDDATE_COLUMN_BITMASK = 16L;

	public static final long CREATEDATE_COLUMN_BITMASK = 32L;

}