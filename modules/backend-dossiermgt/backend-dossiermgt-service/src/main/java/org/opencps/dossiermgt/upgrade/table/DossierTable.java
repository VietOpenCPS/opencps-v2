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
public class DossierTable {

	public static final String TABLE_NAME = "opencps_dossier";

	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "dossierId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "referenceUid", Types.VARCHAR },
			{ "counter", Types.BIGINT },
			{ "registerBookCode", Types.VARCHAR },
			{ "registerBookName", Types.VARCHAR },
			{ "dossierRegister", Types.VARCHAR },
			{ "processNo", Types.VARCHAR },
			{ "serviceCode", Types.VARCHAR },
			{ "serviceName", Types.VARCHAR },
			{ "govAgencyCode", Types.VARCHAR },
			{ "govAgencyName", Types.VARCHAR },
			{ "applicantName", Types.VARCHAR },
			{ "applicantIdType", Types.VARCHAR },
			{ "applicantIdNo", Types.VARCHAR },
			{ "applicantIdDate", Types.TIMESTAMP },
			{ "address", Types.VARCHAR },
			{ "cityCode", Types.VARCHAR },
			{ "cityName", Types.VARCHAR },
			{ "districtCode", Types.VARCHAR },
			{ "districtName", Types.VARCHAR },
			{ "wardCode", Types.VARCHAR },
			{ "wardName", Types.VARCHAR },
			{ "contactName", Types.VARCHAR },
			{ "contactTelNo", Types.VARCHAR },
			{ "contactEmail", Types.VARCHAR },
			{ "delegateType", Types.INTEGER },
			{ "delegateName", Types.VARCHAR },
			{ "delegateIdNo", Types.VARCHAR },
			{ "delegateTelNo", Types.VARCHAR },
			{ "delegateEmail", Types.VARCHAR },
			{ "delegateAddress", Types.VARCHAR },
			{ "delegateCityCode", Types.VARCHAR },
			{ "delegateCityName", Types.VARCHAR },
			{ "delegateDistrictCode", Types.VARCHAR },
			{ "delegateDistrictName", Types.VARCHAR },
			{ "delegateWardCode", Types.VARCHAR },
			{ "delegateWardName", Types.VARCHAR },
			{ "documentNo", Types.VARCHAR },
			{ "documentDate", Types.TIMESTAMP },
			{ "dossierTemplateNo", Types.VARCHAR },
			{ "dossierTemplateName", Types.VARCHAR },
			{ "dossierNote", Types.VARCHAR },
			{ "submissionNote", Types.VARCHAR },
			{ "applicantNote", Types.VARCHAR },
			{ "briefNote", Types.VARCHAR },
			{ "dossierNo", Types.VARCHAR },
			{ "submitting", Types.BOOLEAN },
			{ "processDate", Types.TIMESTAMP },
			{ "submitDate", Types.TIMESTAMP },
			{ "receiveDate", Types.TIMESTAMP },
			{ "dueDate", Types.TIMESTAMP },
			{ "extendDate", Types.TIMESTAMP },
			{ "releaseDate", Types.TIMESTAMP },
			{ "finishDate", Types.TIMESTAMP },
			{ "cancellingDate", Types.TIMESTAMP },
			{ "correcttingDate", Types.TIMESTAMP },
			{ "dossierStatus", Types.VARCHAR },
			{ "dossierStatusText", Types.VARCHAR },
			{ "dossierSubStatus", Types.VARCHAR },
			{ "dossierSubStatusText", Types.VARCHAR },
			{ "folderId", Types.BIGINT },
			{ "dossierActionId", Types.BIGINT },
			{ "viaPostal", Types.INTEGER },
			{ "postalServiceCode", Types.VARCHAR },
			{ "postalServiceName", Types.VARCHAR },
			{ "postalAddress", Types.VARCHAR },
			{ "postalCityCode", Types.VARCHAR },
			{ "postalCityName", Types.VARCHAR },
			{ "postalDistrictCode", Types.VARCHAR },
			{ "postalDistrictName", Types.VARCHAR },
			{ "postalWardCode", Types.VARCHAR },
			{ "postalWardName", Types.VARCHAR },
			{ "postalTelNo", Types.VARCHAR },
			{ "password_", Types.VARCHAR },
			{ "notification", Types.BOOLEAN },
			{ "online_", Types.BOOLEAN },
			{ "original", Types.BOOLEAN },
			{ "serverNo", Types.VARCHAR },
			{ "endorsementDate", Types.TIMESTAMP },
			{ "lockState", Types.VARCHAR },
			{ "originality", Types.INTEGER },
			{ "originDossierId", Types.BIGINT },
			{ "sampleCount", Types.BIGINT },
			{ "durationUnit", Types.INTEGER },
			{ "durationCount", Types.DOUBLE },
			{ "dossierName", Types.VARCHAR },
			{ "originDossierNo", Types.VARCHAR },
			{ "groupDossierId", Types.VARCHAR },
			{ "metaData", Types.VARCHAR },
			{ "systemId", Types.INTEGER },
			{ "dossierCounter", Types.VARCHAR },
			{ "vnpostalStatus", Types.INTEGER },
			{ "vnpostalProfile", Types.VARCHAR },
			{ "fromViaPostal", Types.INTEGER },
			{ "multipleCheck", Types.VARCHAR },
			{ "postalCodeSend", Types.VARCHAR },
			{ "postalCodeReceived", Types.VARCHAR },
			{ "lastReceiveDate", Types.TIMESTAMP },
			{ "lastSendDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("referenceUid", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("counter", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("registerBookCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("registerBookName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierRegister", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("processNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("govAgencyCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("govAgencyName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("applicantName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("applicantIdType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("applicantIdNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("applicantIdDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("address", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("cityCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("cityName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("districtCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("districtName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("wardCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("wardName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("contactName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("contactTelNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("contactEmail", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("delegateType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("delegateName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("delegateIdNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("delegateTelNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("delegateEmail", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("delegateAddress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("delegateCityCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("delegateCityName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("delegateDistrictCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("delegateDistrictName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("delegateWardCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("delegateWardName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("documentNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("documentDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("dossierTemplateNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierTemplateName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierNote", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("submissionNote", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("applicantNote", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("briefNote", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("submitting", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("processDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("submitDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("receiveDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("dueDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("extendDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("releaseDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("finishDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("cancellingDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("correcttingDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("dossierStatus", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierStatusText", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierSubStatus", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierSubStatusText", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("folderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("dossierActionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("viaPostal", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("postalServiceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postalServiceName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postalAddress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postalCityCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postalCityName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postalDistrictCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postalDistrictName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postalWardCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postalWardName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postalTelNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("password_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("notification", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("online_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("original", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("serverNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("endorsementDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lockState", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("originality", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("originDossierId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sampleCount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("durationUnit", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("durationCount", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("dossierName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("originDossierNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupDossierId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("metaData", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("systemId", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("dossierCounter", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("vnpostalStatus", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("vnpostalProfile", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fromViaPostal", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("multipleCheck", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postalCodeSend", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postalCodeReceived", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastReceiveDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lastSendDate", Types.TIMESTAMP);
	}

	
	public static final String TABLE_SQL_CREATE = "create table opencps_dossier (uuid_ VARCHAR(75) null,dossierId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(255) null,createDate DATE null,modifiedDate DATE null,referenceUid VARCHAR(255) null,counter LONG,registerBookCode VARCHAR(100) null,registerBookName VARCHAR(1024) null,dossierRegister VARCHAR(255) null,processNo VARCHAR(128) null,serviceCode VARCHAR(128) null,serviceName TEXT null,govAgencyCode VARCHAR(128) null,govAgencyName VARCHAR(512) null,applicantName VARCHAR(500) null,applicantIdType VARCHAR(128) null,applicantIdNo VARCHAR(128) null,applicantIdDate DATE null,address TEXT null,cityCode VARCHAR(128) null,cityName VARCHAR(255) null,districtCode VARCHAR(128) null,districtName VARCHAR(512) null,wardCode VARCHAR(128) null,wardName VARCHAR(512) null,contactName TEXT null,contactTelNo VARCHAR(128) null,contactEmail VARCHAR(255) null,delegateType INTEGER,delegateName VARCHAR(512) null,delegateIdNo VARCHAR(128) null,delegateTelNo VARCHAR(128) null,delegateEmail VARCHAR(255) null,delegateAddress TEXT null,delegateCityCode VARCHAR(128) null,delegateCityName VARCHAR(512) null,delegateDistrictCode VARCHAR(128) null,delegateDistrictName VARCHAR(512) null,delegateWardCode VARCHAR(128) null,delegateWardName VARCHAR(512) null,documentNo VARCHAR(255) null,documentDate DATE null,dossierTemplateNo VARCHAR(128) null,dossierTemplateName TEXT null,dossierNote TEXT null,submissionNote TEXT null,applicantNote TEXT null,briefNote TEXT null,dossierNo VARCHAR(255) null,submitting BOOLEAN,processDate DATE null,submitDate DATE null,receiveDate DATE null,dueDate DATE null,extendDate DATE null,releaseDate DATE null,finishDate DATE null,cancellingDate DATE null,correcttingDate DATE null,dossierStatus VARCHAR(255) null,dossierStatusText TEXT null,dossierSubStatus VARCHAR(128) null,dossierSubStatusText TEXT null,folderId LONG,dossierActionId LONG,viaPostal INTEGER,postalServiceCode VARCHAR(255) null,postalServiceName VARCHAR(1024) null,postalAddress TEXT null,postalCityCode VARCHAR(255) null,postalCityName VARCHAR(512) null,postalDistrictCode VARCHAR(255) null,postalDistrictName VARCHAR(512) null,postalWardCode VARCHAR(255) null,postalWardName VARCHAR(512) null,postalTelNo VARCHAR(128) null,password_ VARCHAR(75) null,notification BOOLEAN,online_ BOOLEAN,original BOOLEAN,serverNo VARCHAR(255) null,endorsementDate DATE null,lockState VARCHAR(200) null,originality INTEGER,originDossierId LONG,sampleCount LONG,durationUnit INTEGER,durationCount DOUBLE,dossierName VARCHAR(1000) null,originDossierNo VARCHAR(255) null,groupDossierId VARCHAR(75) null,metaData TEXT null,systemId INTEGER,dossierCounter VARCHAR(128) null,vnpostalStatus INTEGER,vnpostalProfile TEXT null,fromViaPostal INTEGER,multipleCheck VARCHAR(75) null,postalCodeSend VARCHAR(75) null,postalCodeReceived VARCHAR(75) null,lastReceiveDate DATE null,lastSendDate DATE null)";

	
	public static final String[] TABLE_SQL_ADD_INDEXES = {
			"create index IX_93AD8453 on opencps_dossier (dossierCounter)",
			"create index IX_A19EE260 on opencps_dossier (dossierNo, applicantIdNo)",
			"create index IX_3EE7C4FC on opencps_dossier (dossierNo, groupId)",
			"create index IX_BC4A37EA on opencps_dossier (dossierStatus, modifiedDate)",
			"create index IX_6C6803BB on opencps_dossier (groupId, applicantIdNo, dossierStatus)",
			"create index IX_6E05E1CA on opencps_dossier (groupId, applicantIdNo, serviceCode, govAgencyCode, dossierTemplateNo, originDossierId)",
			"create index IX_292E6FC5 on opencps_dossier (groupId, companyId, govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus)",
			"create index IX_D4ACCBFA on opencps_dossier (groupId, dossierId)",
			"create index IX_D4AF3B20 on opencps_dossier (groupId, dossierNo)",
			"create index IX_5769E47A on opencps_dossier (groupId, dossierStatus, originality, dossierTemplateNo)",
			"create index IX_1BC51B84 on opencps_dossier (groupId, dossierStatus, originality, processNo)",
			"create index IX_624F4C36 on opencps_dossier (groupId, dossierStatus, originality, serviceCode)",
			"create index IX_A09BF1BA on opencps_dossier (groupId, dossierTemplateNo)",
			"create index IX_CE4B5438 on opencps_dossier (groupId, govAgencyCode, serviceCode, dossierId)",
			"create index IX_4935E3DB on opencps_dossier (groupId, govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus, applicantIdType, originality)",
			"create index IX_699FDB37 on opencps_dossier (groupId, groupDossierId)",
			"create index IX_92D12774 on opencps_dossier (groupId, originDossierId)",
			"create index IX_92D3969A on opencps_dossier (groupId, originDossierNo)",
			"create index IX_562A1F7A on opencps_dossier (groupId, originality, dossierStatus, serviceCode, govAgencyCode)",
			"create index IX_217868C4 on opencps_dossier (groupId, processNo)",
			"create index IX_13B226B5 on opencps_dossier (groupId, referenceUid)",
			"create index IX_C8644976 on opencps_dossier (groupId, serviceCode)",
			"create index IX_FB4E5F17 on opencps_dossier (groupId, userId, dossierStatus)",
			"create index IX_5FB90585 on opencps_dossier (groupId, vnpostalStatus)",
			"create index IX_C5B31468 on opencps_dossier (originDossierNo)",
			"create index IX_CE1517AE on opencps_dossier (originality, dossierStatus)",
			"create index IX_A2D94A0F on opencps_dossier (postalCodeReceived, groupId)",
			"create index IX_542C5208 on opencps_dossier (postalCodeSend, groupId)",
			"create index IX_7B50F925 on opencps_dossier (userId, groupId, govAgencyCode, serviceCode, dossierActionId, originality)",
			"create index IX_50872872 on opencps_dossier (userId, groupId, govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus, originality)",
			"create index IX_C4AA04E2 on opencps_dossier (uuid_, companyId)",
			"create unique index IX_9525BE4 on opencps_dossier (uuid_, groupId)",
			"create index IX_6DA8BB on opencps_dossier (viaPostal)",
			"create index IX_7D659302 on opencps_publish_queue (status, serverNo)"
		};

	

}