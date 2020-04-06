create table m_fileattach (
	fileAttachId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	className VARCHAR(500) null,
	classPK VARCHAR(255) null,
	fullName VARCHAR(500) null,
	email VARCHAR(500) null,
	fileEntryId LONG,
	source VARCHAR(500) null,
	sourceUrl VARCHAR(500) null,
	docFileId LONG,
	fileName VARCHAR(500) null
);

create table opencps_comment (
	uuid_ VARCHAR(75) null,
	commentId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	className VARCHAR(75) null,
	classPK VARCHAR(75) null,
	fullname VARCHAR(75) null,
	email VARCHAR(75) null,
	parent LONG,
	content VARCHAR(75) null,
	fileEntryId LONG,
	pings VARCHAR(75) null,
	upvoteCount INTEGER,
	userHasUpvoted VARCHAR(75) null,
	upvotedUsers VARCHAR(75) null,
	opinion BOOLEAN
);

create table opencps_dictItemmapping (
	mappingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	itemCode VARCHAR(75) null,
	itemCodeDVCQG VARCHAR(75) null,
	collectionId LONG
);

create table opencps_dictcollection (
	uuid_ VARCHAR(75) null,
	dictCollectionId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	collectionCode VARCHAR(100) null,
	collectionName STRING null,
	collectionNameEN STRING null,
	description TEXT null,
	dataForm TEXT null,
	status INTEGER
);

create table opencps_dictgroup (
	uuid_ VARCHAR(75) null,
	dictGroupId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	dictCollectionId LONG,
	groupCode VARCHAR(100) null,
	groupName STRING null,
	groupNameEN STRING null,
	groupDescription TEXT null
);

create table opencps_dictitem (
	uuid_ VARCHAR(75) null,
	dictItemId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	dictCollectionId LONG,
	itemCode VARCHAR(100) null,
	itemName STRING null,
	itemNameEN STRING null,
	itemDescription TEXT null,
	parentItemId LONG,
	level INTEGER,
	sibling VARCHAR(255) null,
	treeIndex VARCHAR(255) null,
	metaData TEXT null
);

create table opencps_dictitemgroup (
	uuid_ VARCHAR(75) null,
	dictItemGroupId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	dictGroupId LONG,
	dictItemId LONG,
	dictGroupName VARCHAR(255) null
);

create table opencps_holiday (
	uuid_ VARCHAR(75) null,
	holidayId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	holidayDate DATE null,
	description TEXT null,
	holidayType INTEGER
);

create table opencps_voting (
	uuid_ VARCHAR(75) null,
	votingId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	className VARCHAR(75) null,
	classPK VARCHAR(75) null,
	subject VARCHAR(75) null,
	choices VARCHAR(75) null,
	templateNo VARCHAR(75) null,
	commentable BOOLEAN
);

create table opencps_votingresult (
	uuid_ VARCHAR(75) null,
	votingResultId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	votingId LONG,
	fullname VARCHAR(75) null,
	email VARCHAR(75) null,
	comment_ VARCHAR(75) null,
	selected VARCHAR(75) null
);

create table opencps_workTime (
	uuid_ VARCHAR(75) null,
	workTimeId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	day INTEGER,
	hours VARCHAR(128) null
);