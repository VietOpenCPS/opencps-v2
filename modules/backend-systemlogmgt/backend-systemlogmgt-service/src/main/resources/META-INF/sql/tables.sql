create table opencps_systemlog (
	uuid_ VARCHAR(75) null,
	logId LONG not null primary key,
	groupId LONG,
	createDate DATE null,
	className VARCHAR(75) null,
	message VARCHAR(75) null,
	type_ VARCHAR(75) null,
	line INTEGER
);