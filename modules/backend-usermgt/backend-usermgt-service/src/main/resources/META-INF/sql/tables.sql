create table m_jobposwork (
	uuid_ VARCHAR(75) null,
	jobPosWorkId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	jobPostId LONG,
	checklistCat VARCHAR(512) null
);

create table opencps_answer (
	answerId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	questionId LONG,
	content VARCHAR(75) null,
	publish INTEGER
);

create table opencps_applicant (
	uuid_ VARCHAR(75) null,
	applicantId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	applicantName TEXT null,
	applicantIdType VARCHAR(75) null,
	applicantIdNo VARCHAR(75) null,
	applicantIdDate DATE null,
	address TEXT null,
	cityCode VARCHAR(75) null,
	cityName TEXT null,
	districtCode VARCHAR(75) null,
	districtName TEXT null,
	wardCode VARCHAR(75) null,
	wardName TEXT null,
	contactName VARCHAR(75) null,
	contactTelNo VARCHAR(75) null,
	contactEmail VARCHAR(255) null,
	mappingUserId LONG,
	activationCode VARCHAR(75) null,
	lock_ BOOLEAN,
	profile TEXT null,
	tmpPass VARCHAR(75) null,
	representativeEnterprise TEXT null,
	verification INTEGER,
	mappingClassName VARCHAR(255) null,
	mappingClassPK VARCHAR(255) null
);

create table opencps_employee (
	uuid_ VARCHAR(75) null,
	employeeId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	employeeNo VARCHAR(75) null,
	fullName VARCHAR(1024) null,
	title VARCHAR(1024) null,
	gender INTEGER,
	birthdate DATE null,
	telNo VARCHAR(75) null,
	mobile VARCHAR(75) null,
	email VARCHAR(512) null,
	workingStatus INTEGER,
	mappingUserId LONG,
	mainJobPostId LONG,
	photoFileEntryId LONG,
	recruitDate DATE null,
	leaveDate DATE null,
	fileCertId LONG,
	fileSignId LONG,
	fileCertPath VARCHAR(512) null,
	fileSignPath VARCHAR(512) null,
	scope VARCHAR(128) null
);

create table opencps_employee_jobpos (
	uuid_ VARCHAR(75) null,
	employeeJobPosId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
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
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	secret VARCHAR(255) null,
	permanent BOOLEAN
);

create table opencps_jobpos (
	uuid_ VARCHAR(75) null,
	jobPosId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	jobPosCode VARCHAR(75) null,
	title VARCHAR(255) null,
	description TEXT null,
	mappingRoleId LONG,
	leader INTEGER
);

create table opencps_officesite (
	uuid_ VARCHAR(75) null,
	officeSiteId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(512) null,
	enName VARCHAR(512) null,
	govAgencyCode VARCHAR(75) null,
	address VARCHAR(1024) null,
	telNo VARCHAR(75) null,
	faxNo VARCHAR(75) null,
	email VARCHAR(255) null,
	website VARCHAR(255) null,
	logoFileEntryId LONG,
	siteGroupId LONG,
	adminUserId LONG,
	preferences TEXT null,
	ceremonyDate DATE null
);

create table opencps_preferences (
	uuid_ VARCHAR(75) null,
	preferencesId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	preferences TEXT null
);

create table opencps_question (
	questionId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	fullname VARCHAR(512) null,
	email VARCHAR(255) null,
	content TEXT null,
	publish INTEGER,
	domainCode VARCHAR(75) null,
	domainName VARCHAR(1024) null,
	govAgencyCode VARCHAR(75) null,
	govAgencyName VARCHAR(1024) null,
	questionType VARCHAR(75) null,
	subDomainCode VARCHAR(75) null,
	subDomainName VARCHAR(1024) null,
	phone VARCHAR(75) null,
	address TEXT null
);

create table opencps_resourcerole (
	uuid_ VARCHAR(75) null,
	resourceRoleId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	className VARCHAR(255) null,
	classPK VARCHAR(255) null,
	roleId LONG,
	readonly INTEGER
);

create table opencps_resourceuser (
	uuid_ VARCHAR(75) null,
	resourceUserId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	className VARCHAR(255) null,
	classPK VARCHAR(75) null,
	toUserId LONG,
	fullname VARCHAR(1024) null,
	email VARCHAR(255) null,
	readonly BOOLEAN
);

create table opencps_userlogin (
	uuid_ VARCHAR(75) null,
	userLoginId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	sessionId VARCHAR(75) null,
	hits INTEGER,
	logout DATE null,
	ipAddress VARCHAR(255) null,
	online_ BOOLEAN
);

create table opencps_usertrackpath (
	uuid_ VARCHAR(75) null,
	userTrackPathId LONG not null primary key,
	companyId LONG,
	modifiedDate DATE null,
	userLoginId LONG,
	path_ VARCHAR(255) null,
	pathDate DATE null
);

create table opencps_visibility (
	uuid_ VARCHAR(75) null,
	visibilityId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	className VARCHAR(255) null,
	classPK VARCHAR(255) null,
	visibility INTEGER,
	security VARCHAR(255) null
);

create table opencps_workingunit (
	uuid_ VARCHAR(75) null,
	workingUnitId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(512) null,
	enName VARCHAR(512) null,
	govAgencyCode VARCHAR(75) null,
	parentWorkingUnitId LONG,
	sibling VARCHAR(75) null,
	treeIndex VARCHAR(75) null,
	address TEXT null,
	telNo VARCHAR(75) null,
	faxNo VARCHAR(75) null,
	email VARCHAR(255) null,
	website VARCHAR(255) null,
	logoFileEntryId LONG,
	level INTEGER,
	ceremonyDate DATE null
);