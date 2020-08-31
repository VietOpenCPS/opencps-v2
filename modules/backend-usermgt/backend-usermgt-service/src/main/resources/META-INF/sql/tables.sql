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
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	questionId LONG,
	content TEXT null,
	publish INTEGER,
	className VARCHAR(255) null,
	classPK VARCHAR(255) null,
	synced INTEGER
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
	applicantIdType VARCHAR(255) null,
	applicantIdNo VARCHAR(255) null,
	applicantIdDate DATE null,
	address TEXT null,
	cityCode VARCHAR(255) null,
	cityName TEXT null,
	districtCode VARCHAR(255) null,
	districtName TEXT null,
	wardCode VARCHAR(255) null,
	wardName TEXT null,
	contactName TEXT null,
	contactTelNo VARCHAR(255) null,
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

create table opencps_applicant_data (
	uuid_ VARCHAR(75) null,
	applicantDataId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	fileTemplateNo VARCHAR(255) null,
	fileNo VARCHAR(255) null,
	fileName VARCHAR(1024) null,
	fileEntryId LONG,
	metadata TEXT null,
	status INTEGER,
	applicantIdNo VARCHAR(128) null,
	applicantDataType INTEGER,
	dossierNo VARCHAR(128) null,
	log_ TEXT null
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
	employeeNo VARCHAR(255) null,
	fullName VARCHAR(1024) null,
	title VARCHAR(1024) null,
	gender INTEGER,
	birthdate DATE null,
	telNo VARCHAR(255) null,
	mobile VARCHAR(255) null,
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
	jobPosTitle VARCHAR(75) null,
	scope VARCHAR(128) null,
	workingUnitId LONG
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
	workingUnitId LONG,
	status INTEGER
);

create table opencps_fileitem (
	uuid_ VARCHAR(75) null,
	fileItemId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	fileTemplateNo VARCHAR(128) null,
	name TEXT null,
	status INTEGER,
	size_ INTEGER,
	fileType VARCHAR(128) null,
	log_ TEXT null
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
	jobPosCode VARCHAR(255) null,
	title VARCHAR(255) null,
	description TEXT null,
	mappingRoleId LONG,
	leader INTEGER
);

create table opencps_lgsp_token (
	uuid_ VARCHAR(75) null,
	tokenId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	token TEXT null,
	tokenType VARCHAR(75) null,
	refreshToken VARCHAR(75) null,
	expiryDate DATE null
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
	telNo VARCHAR(255) null,
	faxNo VARCHAR(255) null,
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
	domainCode VARCHAR(255) null,
	domainName VARCHAR(1024) null,
	govAgencyCode VARCHAR(255) null,
	govAgencyName VARCHAR(1024) null,
	questionType VARCHAR(255) null,
	subDomainCode VARCHAR(255) null,
	subDomainName VARCHAR(1024) null,
	phone VARCHAR(255) null,
	address TEXT null,
	className VARCHAR(255) null,
	classPK VARCHAR(255) null,
	synced INTEGER
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

create table opencps_save_pick_field (
	uuid_ VARCHAR(75) null,
	fieldPickId LONG not null primary key,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	formData VARCHAR(2048) null,
	classPK VARCHAR(75) null
);

create table opencps_sync_scheduler (
	uuid_ VARCHAR(75) null,
	syncSchedulerId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	groupId LONG,
	className VARCHAR(255) null,
	typeCode VARCHAR(255) null,
	syncDate DATE null,
	retry INTEGER
);

create table opencps_track_client (
	uuid_ VARCHAR(75) null,
	trackClientId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	sessionId VARCHAR(128) null,
	url VARCHAR(2048) null,
	year INTEGER,
	month INTEGER,
	day INTEGER,
	visitDate DATE null,
	leaveDate DATE null,
	clientIP VARCHAR(128) null,
	macAddress VARCHAR(128) null,
	region VARCHAR(512) null,
	nation VARCHAR(512) null,
	latitude VARCHAR(128) null,
	longitude VARCHAR(128) null,
	timeOnPage LONG,
	desktop BOOLEAN,
	mobile BOOLEAN,
	tablet BOOLEAN,
	userId LONG,
	userName VARCHAR(75) null
);

create table opencps_track_client_statistic (
	uuid_ VARCHAR(75) null,
	trackClientStatisticId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	url VARCHAR(2048) null,
	year INTEGER,
	month INTEGER,
	day INTEGER,
	region VARCHAR(512) null,
	desktop BOOLEAN,
	mobile BOOLEAN,
	tablet BOOLEAN,
	total LONG
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
	sessionId VARCHAR(255) null,
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
	govAgencyCode VARCHAR(255) null,
	parentWorkingUnitId LONG,
	sibling VARCHAR(255) null,
	treeIndex VARCHAR(255) null,
	address TEXT null,
	telNo VARCHAR(255) null,
	faxNo VARCHAR(255) null,
	email VARCHAR(255) null,
	website VARCHAR(255) null,
	logoFileEntryId LONG,
	level INTEGER,
	ceremonyDate DATE null
);