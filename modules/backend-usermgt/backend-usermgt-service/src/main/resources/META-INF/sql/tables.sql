create table m_contact (
	uuid_ VARCHAR(75) null,
	contactId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	fullName VARCHAR(75) null,
	companyName VARCHAR(75) null,
	telNo VARCHAR(75) null,
	email VARCHAR(75) null,
	userMappingId LONG,
	isOrg BOOLEAN,
	shared INTEGER
);

create table m_contactgroup (
	uuid_ VARCHAR(75) null,
	contactGroupId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	groupName VARCHAR(75) null,
	contactList VARCHAR(75) null,
	shared INTEGER
);

create table m_employee (
	uuid_ VARCHAR(75) null,
	employeeId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	fullName VARCHAR(75) null,
	employeeNo VARCHAR(75) null,
	gender INTEGER,
	birthDate DATE null,
	telNo VARCHAR(75) null,
	mobile VARCHAR(75) null,
	email VARCHAR(75) null,
	workingStatus INTEGER,
	mappingUserId LONG,
	mainJobPostId LONG,
	photoFileEntryId LONG,
	fileDocId LONG,
	preferences VARCHAR(75) null
);

create table m_employee_file (
	uuid_ VARCHAR(75) null,
	employeeFileId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	employeeId LONG,
	fileEntryId LONG
);

create table m_employee_jobpos (
	uuid_ VARCHAR(75) null,
	employeeJobPosId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	employeeId LONG,
	jobPostId LONG
);

create table m_jobpos (
	uuid_ VARCHAR(75) null,
	jobPosId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	workingUnitId LONG,
	title VARCHAR(75) null,
	description VARCHAR(75) null,
	directWorkingUnitId LONG,
	leader INTEGER,
	mappingRoleId LONG,
	hiddenJobPos BOOLEAN
);

create table m_join_site_status (
	uuid_ VARCHAR(75) null,
	JoinSiteStatusId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	employeeId LONG,
	joinSiteGroupId LONG,
	status INTEGER
);

create table m_officesite (
	uuid_ VARCHAR(75) null,
	officeSiteId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	enName VARCHAR(75) null,
	govAgencyCode VARCHAR(75) null,
	address VARCHAR(75) null,
	telNo VARCHAR(75) null,
	faxNo VARCHAR(75) null,
	email VARCHAR(75) null,
	website VARCHAR(75) null,
	logoFileEntryId LONG,
	siteGroupId LONG,
	adminUserId LONG,
	preferences VARCHAR(75) null
);

create table m_partner (
	uuid_ VARCHAR(75) null,
	partnerId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	address VARCHAR(75) null,
	telNo VARCHAR(75) null,
	faxNo VARCHAR(75) null,
	email VARCHAR(75) null,
	website VARCHAR(75) null,
	partnerClass INTEGER,
	accountUserId LONG,
	docFileId VARCHAR(75) null
);

create table m_partnerFile (
	uuid_ VARCHAR(75) null,
	partnerFileId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	partnerId LONG,
	fileEntryId LONG
);

create table m_workingunit (
	uuid_ VARCHAR(75) null,
	workingUnitId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	enName VARCHAR(75) null,
	govAgencyCode VARCHAR(75) null,
	parentWorkingUnitId LONG,
	address VARCHAR(75) null,
	telNo VARCHAR(75) null,
	faxNo VARCHAR(75) null,
	email VARCHAR(75) null,
	website VARCHAR(75) null,
	logoFileEntryId LONG,
	sibling VARCHAR(75) null,
	treeIndex VARCHAR(75) null
);