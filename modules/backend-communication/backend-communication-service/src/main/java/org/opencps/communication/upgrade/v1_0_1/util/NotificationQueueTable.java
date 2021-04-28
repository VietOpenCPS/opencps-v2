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

package org.opencps.communication.upgrade.v1_0_1.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class NotificationQueueTable {

	public static final String TABLE_NAME = "opencps_notificationqueue";
	public static final Object[][] TABLE_COLUMNS = {
			{ "notificationQueueId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "notificationType", Types.VARCHAR },
			{ "className", Types.VARCHAR },
			{ "classPK", Types.VARCHAR },
			{ "payload", Types.CLOB },
			{ "fromUsername", Types.VARCHAR },
			{ "toUsername", Types.VARCHAR },
			{ "toUserId", Types.BIGINT },
			{ "toEmail", Types.VARCHAR },
			{ "toTelNo", Types.VARCHAR },
			{ "publicationDate", Types.TIMESTAMP },
			{ "expireDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("notificationQueueId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("notificationType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("className", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("classPK", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("payload", Types.CLOB);
		TABLE_COLUMNS_MAP.put("fromUsername", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("toUsername", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("toUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("toEmail", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("toTelNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("publicationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("expireDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_notificationqueue (notificationQueueId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,notificationType VARCHAR(255) null,className VARCHAR(512) null,classPK VARCHAR(255) null,payload TEXT null,fromUsername VARCHAR(512) null,toUsername VARCHAR(512) null,toUserId LONG,toEmail VARCHAR(255) null,toTelNo VARCHAR(255) null,publicationDate DATE null,expireDate DATE null)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_63B47ACA on opencps_notificationqueue (expireDate)",
			"create index IX_C0F9E920 on opencps_notificationqueue (groupId, className, classPK, toEmail)",
			"create index IX_A4B70B87 on opencps_notificationqueue (groupId, notificationType, className, classPK, toEmail)",
			"create index IX_B4D26F83 on opencps_notificationqueue (notificationType, expireDate)"
		};

	

}