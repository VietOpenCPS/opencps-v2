create table opencps_adminconfig (
	id_ LONG not null primary key,
	code_ VARCHAR(255) null,
	name TEXT null,
	bundleName VARCHAR(255) null,
	modelName VARCHAR(255) null,
	serviceUtilName VARCHAR(255) null,
	headersName VARCHAR(1000) null,
	columns TEXT null,
	detailColumns TEXT null,
	extForm BOOLEAN,
	groupFilter BOOLEAN,
	publicManager BOOLEAN
);

create table opencps_api_manager (
	apiManagerId LONG not null primary key,
	groupId LONG,
	apiCode VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	userId LONG,
	apiName VARCHAR(75) null,
	apiDescription VARCHAR(75) null,
	apiStatus INTEGER,
	className VARCHAR(75) null
);

create table opencps_api_mânger (
	reportRoleId LONG not null primary key,
	dynamicReportId LONG,
	roleId LONG
);

create table opencps_api_role (
	apiRoleId LONG not null primary key,
	groupId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	userId LONG,
	apiCode VARCHAR(75) null,
	roleId INTEGER,
	roleCode VARCHAR(75) null,
	apiRoleStatus INTEGER
);

create table opencps_dynamicreport (
	dynamicReportId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	sharing INTEGER,
	reportName VARCHAR(1024) null,
	reportCode VARCHAR(128) null,
	filterConfig TEXT null,
	tableConfig TEXT null,
	userConfig VARCHAR(1000) null,
	reportType VARCHAR(128) null
);

create table opencps_reportrole (
	reportRoleId LONG not null primary key,
	dynamicReportId LONG,
	roleId LONG
);

create table opencps_synctracking (
	uuid_ VARCHAR(75) null,
	trackingId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dossierNo VARCHAR(75) null,
	referenceUid VARCHAR(75) null,
	serverNo VARCHAR(75) null,
	protocol VARCHAR(75) null,
	stateSync INTEGER,
	serviceCode VARCHAR(75) null,
	api VARCHAR(75) null,
	fromUnit VARCHAR(75) null,
	toUnit VARCHAR(75) null,
	bodyRequest VARCHAR(75) null,
	response VARCHAR(75) null,
	metaData VARCHAR(75) null
);