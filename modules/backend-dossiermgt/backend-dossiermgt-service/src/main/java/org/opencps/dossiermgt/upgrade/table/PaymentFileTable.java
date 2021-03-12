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
public class PaymentFileTable {

	public static final String TABLE_NAME = "opencps_paymentfile";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "paymentFileId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "dossierId", Types.BIGINT },
			{ "referenceUid", Types.VARCHAR },
			{ "govAgencyCode", Types.VARCHAR },
			{ "govAgencyName", Types.VARCHAR },
			{ "paymentFee", Types.VARCHAR },
			{ "advanceAmount", Types.BIGINT },
			{ "feeAmount", Types.BIGINT },
			{ "serviceAmount", Types.BIGINT },
			{ "shipAmount", Types.BIGINT },
			{ "paymentAmount", Types.BIGINT },
			{ "paymentNote", Types.VARCHAR },
			{ "epaymentProfile", Types.VARCHAR },
			{ "bankInfo", Types.VARCHAR },
			{ "paymentStatus", Types.INTEGER },
			{ "paymentMethod", Types.VARCHAR },
			{ "confirmDatetime", Types.TIMESTAMP },
			{ "confirmPayload", Types.VARCHAR },
			{ "confirmFileEntryId", Types.BIGINT },
			{ "confirmNote", Types.VARCHAR },
			{ "approveDatetime", Types.TIMESTAMP },
			{ "accountUserName", Types.VARCHAR },
			{ "govAgencyTaxNo", Types.VARCHAR },
			{ "invoiceTemplateNo", Types.VARCHAR },
			{ "invoiceIssueNo", Types.VARCHAR },
			{ "invoiceNo", Types.VARCHAR },
			{ "invoicePayload", Types.VARCHAR },
			{ "einvoice", Types.VARCHAR },
			{ "invoiceFileEntryId", Types.BIGINT }
	};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("paymentFileId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("dossierId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("referenceUid", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("govAgencyCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("govAgencyName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("paymentFee", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("advanceAmount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("feeAmount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("serviceAmount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("shipAmount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("paymentAmount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("paymentNote", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("epaymentProfile", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("bankInfo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("paymentStatus", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("paymentMethod", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("confirmDatetime", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("confirmPayload", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("confirmFileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("confirmNote", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("approveDatetime", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("accountUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("govAgencyTaxNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("invoiceTemplateNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("invoiceIssueNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("invoiceNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("invoicePayload", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("einvoice", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("invoiceFileEntryId", Types.BIGINT);
	}


	public static final String TABLE_SQL_CREATE = "create table opencps_paymentfile (uuid_ VARCHAR(75) null,paymentFileId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,dossierId LONG,referenceUid VARCHAR(75) null,govAgencyCode VARCHAR(128) null,govAgencyName VARCHAR(500) null,paymentFee VARCHAR(500) null,advanceAmount LONG,feeAmount LONG,serviceAmount LONG,shipAmount LONG,paymentAmount LONG,paymentNote VARCHAR(500) null,epaymentProfile STRING null,bankInfo STRING null,paymentStatus INTEGER,paymentMethod VARCHAR(255) null,confirmDatetime DATE null,confirmPayload STRING null,confirmFileEntryId LONG,confirmNote VARCHAR(75) null,approveDatetime DATE null,accountUserName VARCHAR(500) null,govAgencyTaxNo VARCHAR(500) null,invoiceTemplateNo VARCHAR(500) null,invoiceIssueNo VARCHAR(500) null,invoiceNo VARCHAR(500) null,invoicePayload STRING null,einvoice STRING null,invoiceFileEntryId LONG)";


	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_AB66566A on opencps_paymentfile (dossierId, referenceUid)",
			"create index IX_928D77F1 on opencps_paymentfile (groupId, dossierId)",
			"create index IX_8EF25743 on opencps_paymentfile (groupId, paymentStatus)",
			"create index IX_51CA8DC3 on opencps_paymentfile (paymentStatus)",
			"create index IX_6BE34299 on opencps_paymentfile (uuid_, companyId)",
			"create unique index IX_C65D9B5B on opencps_paymentfile (uuid_, groupId)"
		};

	

}