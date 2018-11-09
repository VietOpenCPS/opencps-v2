create table opencps_adminconfig (
	id_ LONG not null primary key,
	code_ VARCHAR(75) null,
	name VARCHAR(75) null,
	bundleName VARCHAR(75) null,
	modelName VARCHAR(75) null,
	serviceUtilName VARCHAR(75) null,
	headersName VARCHAR(75) null,
	columns VARCHAR(75) null,
	detailColumns VARCHAR(75) null,
	extForm BOOLEAN,
	groupFilter BOOLEAN,
	publicManager BOOLEAN
);