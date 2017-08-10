create table opencps_dossieraction (
	uuid_ VARCHAR(75) null,
	dossierActionId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dossierId LONG,
	serviceProcessId LONG,
	previousActionId LONG,
	actionCode VARCHAR(75) null,
	actionUser VARCHAR(75) null,
	actionName VARCHAR(75) null,
	actionNote VARCHAR(75) null,
	overDue INTEGER,
	syncActionCode VARCHAR(75) null,
	pending VARCHAR(75) null,
	rollback VARCHAR(75) null,
	processStepId LONG,
	dueDate INTEGER,
	nextActionId LONG
);

create table opencps_dossieractionuser (
	uuid_ VARCHAR(75) null,
	dossierActionId LONG not null,
	userId LONG not null,
	moderator INTEGER,
	primary key (dossierActionId, userId)
);

create table opencps_processaction (
	uuid_ VARCHAR(75) null,
	processActionId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	serviceProcessId LONG,
	preProcessStepId LONG,
	postProcessStepId LONG,
	autoEvent VARCHAR(75) null,
	preCondition VARCHAR(75) null,
	actionCode VARCHAR(75) null,
	actionName VARCHAR(75) null,
	allowAssignUser VARCHAR(75) null,
	assignUserId LONG,
	requestPayment VARCHAR(75) null,
	paymentFee VARCHAR(75) null,
	createDossierFiles VARCHAR(75) null,
	returnDossierFiles VARCHAR(75) null,
	syncActionCode VARCHAR(75) null,
	rollback VARCHAR(75) null
);

create table opencps_processstep (
	uuid_ VARCHAR(75) null,
	processStepId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	stepCode VARCHAR(75) null,
	serviceProcessId LONG,
	stepName VARCHAR(75) null,
	sequenceNo VARCHAR(75) null,
	dossierStatus VARCHAR(75) null,
	dossierSubStatus VARCHAR(75) null,
	durationCount INTEGER,
	customProcessUrl VARCHAR(75) null
);

create table opencps_serviceprocess (
	uuid_ VARCHAR(75) null,
	serviceProcessId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	processNo VARCHAR(75) null,
	description VARCHAR(75) null,
	durationCount INTEGER,
	durationUnit INTEGER,
	counter INTEGER,
	generateDossierNo VARCHAR(75) null,
	dossierNoPattern VARCHAR(75) null,
	generateDueDate VARCHAR(75) null,
	dueDatePattern VARCHAR(75) null
);

create table opencps_stepallowance (
	uuid_ VARCHAR(75) null,
	processStepId LONG not null,
	roleId LONG not null,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	moderator INTEGER,
	primary key (processStepId, roleId)
);