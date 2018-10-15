create table opencps_dictcollection_temp (
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
	dataForm VARCHAR(75) null,
	status INTEGER,
	mustSync INTEGER
);

create table opencps_dictgroup_temp (
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
	groupDescription VARCHAR(75) null,
	status INTEGER
);

create table opencps_dictitem_temp (
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
	metaData VARCHAR(75) null,
	status INTEGER
);

create table opencps_dictitemgroup_temp (
	uuid_ VARCHAR(75) null,
	dictItemGroupId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dictGroupId LONG,
	dictItemId LONG,
	dictGroupName VARCHAR(75) null
);

create table opencps_pushcollection (
	uuid_ VARCHAR(75) null,
	pushCollectionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	serverNo VARCHAR(75) null,
	collectionCode VARCHAR(75) null,
	collectionName VARCHAR(75) null,
	collectionNameEN VARCHAR(75) null,
	description VARCHAR(75) null,
	dataForm VARCHAR(75) null,
	method VARCHAR(75) null
);

create table opencps_pushdictgroup (
	uuid_ VARCHAR(75) null,
	pushDictGroupId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	serverNo VARCHAR(75) null,
	collectionCode VARCHAR(75) null,
	groupCode VARCHAR(75) null,
	groupName VARCHAR(75) null,
	groupNameEN VARCHAR(75) null,
	groupDescription VARCHAR(75) null,
	itemCode VARCHAR(75) null,
	method VARCHAR(75) null
);

create table opencps_pushdictitem (
	uuid_ VARCHAR(75) null,
	pushDictItemId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	serverNo VARCHAR(75) null,
	collectionCode VARCHAR(75) null,
	itemCode VARCHAR(75) null,
	itemName VARCHAR(75) null,
	itemNameEN VARCHAR(75) null,
	itemDescription VARCHAR(75) null,
	parentItemCode VARCHAR(75) null,
	sibling VARCHAR(75) null,
	method VARCHAR(75) null,
	metaData VARCHAR(75) null
);

create table opencps_syncqueue (
	uuid_ VARCHAR(75) null,
	syncQueueId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	serverNo VARCHAR(75) null,
	className VARCHAR(75) null,
	jsonObject VARCHAR(75) null,
	status INTEGER,
	retryCount INTEGER,
	priority INTEGER,
	method VARCHAR(75) null
);