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
public class NotarizationTable {

	public static final String TABLE_NAME = "opencps_notarization";
	public static final Object[][] TABLE_COLUMNS = {
			{ "notarizationId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "dossierId", Types.BIGINT },
			{ "fileName", Types.VARCHAR },
			{ "totalRecord", Types.INTEGER },
			{ "totalPage", Types.INTEGER },
			{ "totalCopy", Types.INTEGER },
			{ "totalFee", Types.BIGINT },
			{ "notarizationNo", Types.BIGINT },
			{ "notarizationYear", Types.INTEGER },
			{ "notarizationDate", Types.TIMESTAMP },
			{ "signerName", Types.VARCHAR },
			{ "signerPosition", Types.VARCHAR },
			{ "statusCode", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("notarizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("dossierId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("totalRecord", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("totalPage", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("totalCopy", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("totalFee", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("notarizationNo", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("notarizationYear", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("notarizationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("signerName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("signerPosition", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusCode", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_notarization (notarizationId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,dossierId LONG,fileName VARCHAR(1024) null,totalRecord INTEGER,totalPage INTEGER,totalCopy INTEGER,totalFee LONG,notarizationNo LONG,notarizationYear INTEGER,notarizationDate DATE null,signerName VARCHAR(512) null,signerPosition VARCHAR(512) null,statusCode VARCHAR(75) null)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_636A0EF5 on opencps_notarization (groupId, dossierId)"
		};

	

}