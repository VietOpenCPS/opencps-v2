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
public class NotificationTemplateTable {

	public static final String TABLE_NAME = "opencps_notificationtemplate";
	public static final Object[][] TABLE_COLUMNS = {
			{ "notificationTemplateId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "notificationType", Types.VARCHAR },
			{ "emailSubject", Types.VARCHAR },
			{ "emailBody", Types.CLOB },
			{ "textMessage", Types.VARCHAR },
			{ "notifyMessage", Types.CLOB },
			{ "sendSMS", Types.BOOLEAN },
			{ "sendEmail", Types.BOOLEAN },
			{ "sendNotification", Types.BOOLEAN },
			{ "expireDuration", Types.INTEGER },
			{ "userUrlPattern", Types.VARCHAR },
			{ "guestUrlPattern", Types.VARCHAR },
			{ "interval_", Types.VARCHAR },
			{ "grouping", Types.BOOLEAN },
			{ "priority", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("notificationTemplateId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("notificationType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("emailSubject", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("emailBody", Types.CLOB);
		TABLE_COLUMNS_MAP.put("textMessage", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("notifyMessage", Types.CLOB);
		TABLE_COLUMNS_MAP.put("sendSMS", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("sendEmail", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("sendNotification", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("expireDuration", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("userUrlPattern", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("guestUrlPattern", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("interval_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("grouping", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("priority", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_notificationtemplate (notificationTemplateId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,notificationType VARCHAR(255) null,emailSubject TEXT null,emailBody TEXT null,textMessage TEXT null,notifyMessage TEXT null,sendSMS BOOLEAN,sendEmail BOOLEAN,sendNotification BOOLEAN,expireDuration INTEGER,userUrlPattern VARCHAR(1024) null,guestUrlPattern VARCHAR(1024) null,interval_ VARCHAR(255) null,grouping BOOLEAN,priority INTEGER)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_E7D9C8D2 on opencps_notificationtemplate (emailSubject)",
			"create index IX_DE33B5F5 on opencps_notificationtemplate (expireDuration)",
			"create index IX_E0577FD5 on opencps_notificationtemplate (groupId, notificationType, interval_)",
			"create index IX_54F70B3C on opencps_notificationtemplate (interval_)"
		};

	

}