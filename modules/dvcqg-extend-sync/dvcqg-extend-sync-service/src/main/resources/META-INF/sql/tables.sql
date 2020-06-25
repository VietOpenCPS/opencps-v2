create table opencps_apdungDVC (
	uuid_ VARCHAR(75) null,
	apdungDVCId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	maTTHC VARCHAR(75) null,
	maCQTH VARCHAR(75) null,
	mucdo INTEGER,
	serviceConfigMappingId LONG
);

create table opencps_philephi (
	uuid_ VARCHAR(75) null,
	phiLePhiId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	loaiPLP VARCHAR(75) null,
	maPLP VARCHAR(75) null,
	tenPLP VARCHAR(75) null,
	soTien LONG,
	serviceConfigMappingId LONG
);

create table opencps_serviceconfigmapping (
	uuid_ VARCHAR(75) null,
	serviceConfigMappingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	maDVC VARCHAR(75) null,
	tenDVC VARCHAR(75) null,
	maTTHC VARCHAR(75) null,
	tenTTHC VARCHAR(75) null,
	tenCQBH VARCHAR(75) null,
	tenLinhVuc VARCHAR(75) null,
	apdungDVC VARCHAR(75) null
);