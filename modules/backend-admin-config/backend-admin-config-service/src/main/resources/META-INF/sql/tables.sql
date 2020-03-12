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