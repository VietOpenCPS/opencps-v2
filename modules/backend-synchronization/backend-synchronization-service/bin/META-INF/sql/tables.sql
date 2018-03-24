create table opencps_pushcollection (
	uuid_ VARCHAR(75) null,
	pushCollectionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	collectionCode VARCHAR(75) null,
	collectionName VARCHAR(75) null,
	collectionNameEN VARCHAR(75) null,
	description TEXT null,
	dataForm TEXT null,
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
	collectionCode VARCHAR(75) null,
	groupCode VARCHAR(75) null,
	groupName VARCHAR(75) null,
	groupNameEN VARCHAR(75) null,
	groupDescription TEXT null,
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
	collectionCode VARCHAR(75) null,
	itemCode VARCHAR(75) null,
	itemName VARCHAR(75) null,
	itemNameEN VARCHAR(75) null,
	itemDescription TEXT null,
	parentItemCode VARCHAR(75) null,
	sibling VARCHAR(75) null,
	method VARCHAR(75) null,
	metaData TEXT null
);