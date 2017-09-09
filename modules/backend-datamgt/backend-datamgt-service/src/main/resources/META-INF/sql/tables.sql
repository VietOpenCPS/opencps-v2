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
	upvoteCount LONG,
	userHasUpvoted VARCHAR(75) null,
	createdByCurrentUser LONG
);

create table opencps_dictcollection (
	uuid_ VARCHAR(75) null,
	dictCollectionId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	collectionCode VARCHAR(75) null,
	collectionName VARCHAR(75) null,
	collectionNameEN VARCHAR(75) null,
	description VARCHAR(75) null,
	dataForm VARCHAR(75) null
);

create table opencps_dictgroup (
	uuid_ VARCHAR(75) null,
	dictGroupId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dictCollectionId LONG,
	groupCode VARCHAR(75) null,
	groupName VARCHAR(75) null,
	groupNameEN VARCHAR(75) null,
	groupDescription VARCHAR(75) null
);

create table opencps_dictitem (
	uuid_ VARCHAR(75) null,
	dictItemId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dictCollectionId LONG,
	itemCode VARCHAR(75) null,
	itemName VARCHAR(75) null,
	itemNameEN VARCHAR(75) null,
	itemDescription VARCHAR(75) null,
	parentItemId LONG,
	level INTEGER,
	sibling VARCHAR(75) null,
	treeIndex VARCHAR(75) null,
	dataForm VARCHAR(75) null
);

create table opencps_dictitemgroup (
	uuid_ VARCHAR(75) null,
	dictItemGroupId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dictGroupId LONG,
	dictItemId LONG
);

create table opencps_label (
	uuid_ VARCHAR(75) null,
	labelId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	color VARCHAR(75) null,
	scope INTEGER
);

create table opencps_location (
	uuid_ VARCHAR(75) null,
	locationId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	location VARCHAR(75) null,
	geolocation VARCHAR(75) null
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
	answers VARCHAR(75) null,
	dueDate DATE null
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
	toUserId LONG,
	fullname VARCHAR(75) null,
	email VARCHAR(75) null,
	comment_ VARCHAR(75) null,
	selected VARCHAR(75) null
);

create table opencps_workspace (
	uuid_ VARCHAR(75) null,
	workspaceId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	seqOrder INTEGER
);

create table opencps_workspacerole (
	uuid_ VARCHAR(75) null,
	workspaceRoleId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	workspaceId LONG,
	roleId LONG
);

create table opencps_workspaceuser (
	uuid_ VARCHAR(75) null,
	workspaceUserId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	workspaceId LONG,
	assignUserId LONG
);