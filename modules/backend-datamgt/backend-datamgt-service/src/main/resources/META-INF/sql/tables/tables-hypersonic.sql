create table opencps_comment (
	uuid_ varchar(75) null,
	commentId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	className varchar(75) null,
	classPK varchar(75) null,
	fullname varchar(75) null,
	email varchar(75) null,
	parent bigint,
	content varchar(75) null,
	fileEntryId bigint,
	upvoteCount bigint,
	userHasUpvoted varchar(75) null,
	createdByCurrentUser bigint
);

create table opencps_dictcollection (
	uuid_ varchar(75) null,
	dictCollectionId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	collectionCode varchar(75) null,
	collectionName varchar(75) null,
	collectionNameEN varchar(75) null,
	description varchar(75) null,
	dataForm varchar(75) null
);

create table opencps_dictgroup (
	uuid_ varchar(75) null,
	dictGroupId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	dictCollectionId bigint,
	groupCode varchar(75) null,
	groupName varchar(75) null,
	groupNameEN varchar(75) null,
	groupDescription varchar(75) null
);

create table opencps_dictitem (
	uuid_ varchar(75) null,
	dictItemId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	dictCollectionId bigint,
	itemCode varchar(75) null,
	itemName varchar(75) null,
	itemNameEN varchar(75) null,
	itemDescription varchar(75) null,
	parentItemId bigint,
	level int,
	sibling varchar(75) null,
	treeIndex varchar(75) null,
	dataForm varchar(75) null,
	metaData varchar(75) null
);

create table opencps_dictitemgroup (
	uuid_ varchar(75) null,
	dictItemGroupId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	dictGroupId bigint,
	dictItemId bigint
);

create table opencps_holiday (
	uuid_ varchar(75) null,
	holidayId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	holidayDate timestamp null,
	description varchar(75) null
);

create table opencps_voting (
	uuid_ varchar(75) null,
	votingId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	className varchar(75) null,
	classPK varchar(75) null,
	subject varchar(75) null,
	answers varchar(75) null,
	dueDate timestamp null
);

create table opencps_votingresult (
	uuid_ varchar(75) null,
	votingResultId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	votingId bigint,
	toUserId bigint,
	fullname varchar(75) null,
	email varchar(75) null,
	comment_ varchar(75) null,
	selected varchar(75) null
);

create table opencps_workTime (
	uuid_ varchar(75) null,
	workTimeId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	day int,
	hours varchar(75) null
);
