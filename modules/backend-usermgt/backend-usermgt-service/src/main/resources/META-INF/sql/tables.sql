create table m_jobposwork (
	uuid_ VARCHAR(75) null,
	jobPosWorkId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	jobPostId LONG,
	checklistCat VARCHAR(75) null
);

create table opencps_applicant (
	uuid_ VARCHAR(75) null,
	applicantId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	applicantName VARCHAR(75) null,
	applicantIdType VARCHAR(75) null,
	applicantIdNo VARCHAR(75) null,
	applicantIdDate DATE null,
	address VARCHAR(75) null,
	cityCode VARCHAR(75) null,
	cityName VARCHAR(75) null,
	districtCode VARCHAR(75) null,
	districtName VARCHAR(75) null,
	wardCode VARCHAR(75) null,
	wardName VARCHAR(75) null,
	contactName VARCHAR(75) null,
	contactTelNo VARCHAR(75) null,
	contactEmail VARCHAR(75) null,
	mappingUserId LONG,
	activationCode VARCHAR(75) null,
	lock_ BOOLEAN,
	profile VARCHAR(75) null,
	tmpPass VARCHAR(75) null,
	representativeEnterprise VARCHAR(75) null
);

create table opencps_employee (
	uuid_ VARCHAR(75) null,
	employeeId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	employeeNo VARCHAR(75) null,
	fullName VARCHAR(75) null,
	title VARCHAR(75) null,
	gender INTEGER,
	birthdate DATE null,
	telNo VARCHAR(75) null,
	mobile VARCHAR(75) null,
	email VARCHAR(75) null,
	workingStatus INTEGER,
	mappingUserId LONG,
	mainJobPostId LONG,
	photoFileEntryId LONG,
	recruitDate DATE null,
	leaveDate DATE null,
	fileCertId LONG,
	fileSignId LONG,
	fileCertPath VARCHAR(75) null,
	fileSignPath VARCHAR(75) null
);

create table opencps_employee_jobpos (
	uuid_ VARCHAR(75) null,
	employeeJobPosId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	employeeId LONG,
	jobPostId LONG,
	workingUnitId LONG
);

create table opencps_hmacauth (
	hmacAuthId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	secret VARCHAR(75) null,
	permanent BOOLEAN
);

create table opencps_jobpos (
	uuid_ VARCHAR(75) null,
	jobPosId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	jobPosCode VARCHAR(75) null,
	title VARCHAR(75) null,
	description VARCHAR(75) null,
	mappingRoleId LONG,
	leader INTEGER
);

create table opencps_officesite (
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
	preferences VARCHAR(75) null,
	ceremonyDate DATE null
);

create table opencps_preferences (
	uuid_ VARCHAR(75) null,
	preferencesId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	preferences VARCHAR(75) null
);

create table opencps_resourcerole (
	uuid_ VARCHAR(75) null,
	resourceRoleId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	className VARCHAR(75) null,
	classPK VARCHAR(75) null,
	roleId LONG,
	readonly INTEGER
);

create table opencps_resourceuser (
	uuid_ VARCHAR(75) null,
	resourceUserId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	className VARCHAR(75) null,
	classPK VARCHAR(75) null,
	toUserId LONG,
	fullname VARCHAR(75) null,
	email VARCHAR(75) null,
	readonly BOOLEAN
);

create table opencps_visibility (
	uuid_ VARCHAR(75) null,
	visibilityId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	className VARCHAR(75) null,
	classPK VARCHAR(75) null,
	visibility INTEGER,
	security VARCHAR(75) null
);

create table opencps_workingunit (
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
	sibling VARCHAR(75) null,
	treeIndex VARCHAR(75) null,
	address VARCHAR(75) null,
	telNo VARCHAR(75) null,
	faxNo VARCHAR(75) null,
	email VARCHAR(75) null,
	website VARCHAR(75) null,
	logoFileEntryId LONG,
	level INTEGER,
	ceremonyDate DATE null
);