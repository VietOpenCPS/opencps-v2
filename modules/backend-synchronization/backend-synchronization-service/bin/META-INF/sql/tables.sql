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
	description VARCHAR(75) null,
	dataForm VARCHAR(75) null,
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
	itemDescription VARCHAR(75) null,
	parentItemCode VARCHAR(75) null,
	sibling VARCHAR(75) null,
	method VARCHAR(75) null,
	metaData VARCHAR(75) null
);