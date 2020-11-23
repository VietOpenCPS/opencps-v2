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

package org.opencps.statistic.upgrade.v0_0_1.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class StatisticTable {

	public static final String TABLE_NAME = "opencps_statistic";

	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "dossierStatisticId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "month", Types.INTEGER },
			{ "year", Types.INTEGER },
			{ "totalCount", Types.INTEGER },
			{ "deniedCount", Types.INTEGER },
			{ "cancelledCount", Types.INTEGER },
			{ "processCount", Types.INTEGER },
			{ "remainingCount", Types.INTEGER },
			{ "receivedCount", Types.INTEGER },
			{ "onlineCount", Types.INTEGER },
			{ "onegateCount", Types.INTEGER },
			{ "releaseCount", Types.INTEGER },
			{ "betimesCount", Types.INTEGER },
			{ "ontimeCount", Types.INTEGER },
			{ "overtimeCount", Types.INTEGER },
			{ "doneCount", Types.INTEGER },
			{ "releasingCount", Types.INTEGER },
			{ "unresolvedCount", Types.INTEGER },
			{ "processingCount", Types.INTEGER },
			{ "undueCount", Types.INTEGER },
			{ "overdueCount", Types.INTEGER },
			{ "pausingCount", Types.INTEGER },
			{ "ontimePercentage", Types.INTEGER },
			{ "govAgencyCode", Types.VARCHAR },
			{ "groupAgencyCode", Types.VARCHAR },
			{ "govAgencyName", Types.VARCHAR },
			{ "domainCode", Types.VARCHAR },
			{ "domainName", Types.VARCHAR },
			{ "reporting", Types.BOOLEAN },
			{ "overtimeInside", Types.INTEGER },
			{ "overtimeOutside", Types.INTEGER },
			{ "interoperatingCount", Types.INTEGER },
			{ "waitingCount", Types.INTEGER },
			{ "outsideCount", Types.INTEGER },
			{ "insideCount", Types.INTEGER },
			{ "system", Types.VARCHAR },
			{ "viaPostalCount", Types.INTEGER },
			{ "notViaPostalCount", Types.INTEGER },
			{ "saturdayCount", Types.INTEGER },
			{ "dossierOnline3Count", Types.INTEGER },
			{ "dossierOnline4Count", Types.INTEGER },
			{ "receiveDossierSatCount", Types.INTEGER },
			{ "releaseDossierSatCount", Types.INTEGER },
			{ "fromViaPostalCount", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierStatisticId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("month", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("year", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("totalCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("deniedCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("cancelledCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("processCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("remainingCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("receivedCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("onlineCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("onegateCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("releaseCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("betimesCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ontimeCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("overtimeCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("doneCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("releasingCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("unresolvedCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("processingCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("undueCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("overdueCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("pausingCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ontimePercentage", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("govAgencyCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupAgencyCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("govAgencyName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("domainCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("domainName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("reporting", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("overtimeInside", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("overtimeOutside", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("interoperatingCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("waitingCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("outsideCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("insideCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("system", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("viaPostalCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("notViaPostalCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("saturdayCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("dossierOnline3Count", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("dossierOnline4Count", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("receiveDossierSatCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("releaseDossierSatCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("fromViaPostalCount", Types.INTEGER);
	}

	
	public static final String TABLE_SQL_CREATE = "create table opencps_statistic (uuid_ VARCHAR(75) null,dossierStatisticId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,month INTEGER,year INTEGER,totalCount INTEGER,deniedCount INTEGER,cancelledCount INTEGER,processCount INTEGER,remainingCount INTEGER,receivedCount INTEGER,onlineCount INTEGER,onegateCount INTEGER,releaseCount INTEGER,betimesCount INTEGER,ontimeCount INTEGER,overtimeCount INTEGER,doneCount INTEGER,releasingCount INTEGER,unresolvedCount INTEGER,processingCount INTEGER,undueCount INTEGER,overdueCount INTEGER,pausingCount INTEGER,ontimePercentage INTEGER,govAgencyCode VARCHAR(255) null,groupAgencyCode TEXT null,govAgencyName TEXT null,domainCode VARCHAR(255) null,domainName TEXT null,reporting BOOLEAN,overtimeInside INTEGER,overtimeOutside INTEGER,interoperatingCount INTEGER,waitingCount INTEGER,outsideCount INTEGER,insideCount INTEGER,system VARCHAR(75) null,viaPostalCount INTEGER,notViaPostalCount INTEGER,saturdayCount INTEGER,dossierOnline3Count INTEGER,dossierOnline4Count INTEGER,receiveDossierSatCount INTEGER,releaseDossierSatCount INTEGER,fromViaPostalCount INTEGER)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_767B71CC on opencps_statistic (companyId, groupId, month, year)",
			"create index IX_751D6585 on opencps_statistic (groupId, domainCode, month, year)",
			"create index IX_1124F771 on opencps_statistic (groupId, govAgencyCode, month, year, domainCode, reporting)",
			"create index IX_ACE3952E on opencps_statistic (groupId, month, year, govAgencyCode, domainCode, system)",
			"create index IX_CF87F234 on opencps_statistic (groupId, month, year, reporting)",
			"create index IX_EF7CF302 on opencps_statistic (groupId, userId, year)",
			"create index IX_80F6A7A7 on opencps_statistic (uuid_, companyId)",
			"create unique index IX_8F82EBE9 on opencps_statistic (uuid_, groupId)"
		};

	

}