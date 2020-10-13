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
public class PaymentFeeInfoTable {

	public static final String TABLE_NAME = "opencps_paymentfeeinfo";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "paymentFeeInfoId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "type_", Types.VARCHAR },
			{ "paymentFeeCode", Types.VARCHAR },
			{ "paymentFeeName", Types.VARCHAR },
			{ "amount", Types.VARCHAR },
			{ "serviceConfigMappingId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("paymentFeeInfoId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("paymentFeeCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("paymentFeeName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("amount", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceConfigMappingId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_paymentfeeinfo (uuid_ VARCHAR(75) null,paymentFeeInfoId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,type_ VARCHAR(75) null,paymentFeeCode VARCHAR(75) null,paymentFeeName VARCHAR(75) null,amount VARCHAR(75) null,serviceConfigMappingId LONG)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_53CC0B5D on opencps_paymentfeeinfo (serviceConfigMappingId)",
			"create index IX_42CE9AB on opencps_paymentfeeinfo (uuid_, companyId)",
			"create unique index IX_C0A9DEED on opencps_paymentfeeinfo (uuid_, groupId)"
		};

	

}