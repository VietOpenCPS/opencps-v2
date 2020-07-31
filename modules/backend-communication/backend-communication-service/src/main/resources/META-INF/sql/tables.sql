create table opencps_lgsp_token (
	uuid_ VARCHAR(75) null,
	tokenId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	token VARCHAR(75) null,
	tokenType VARCHAR(75) null,
	refreshToken VARCHAR(75) null,
	expiryDate DATE null
);

create table opencps_notificationqueue (
	notificationQueueId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	notificationType VARCHAR(255) null,
	className VARCHAR(512) null,
	classPK VARCHAR(255) null,
	payload TEXT null,
	fromUsername VARCHAR(512) null,
	toUsername VARCHAR(512) null,
	toUserId LONG,
	toEmail VARCHAR(255) null,
	toTelNo VARCHAR(255) null,
	publicationDate DATE null,
	expireDate DATE null
);

create table opencps_notificationtemplate (
	notificationTemplateId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	notificationType VARCHAR(255) null,
	emailSubject TEXT null,
	emailBody TEXT null,
	textMessage TEXT null,
	notifyMessage TEXT null,
	sendSMS BOOLEAN,
	sendEmail BOOLEAN,
	sendNotification BOOLEAN,
	expireDuration INTEGER,
	userUrlPattern VARCHAR(1024) null,
	guestUrlPattern VARCHAR(1024) null,
	interval_ VARCHAR(255) null,
	grouping BOOLEAN
);

create table opencps_serverconfig (
	serverConfigId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	govAgencyCode VARCHAR(255) null,
	serverNo VARCHAR(255) null,
	serverName TEXT null,
	protocol VARCHAR(255) null,
	configs TEXT null,
	lastSync DATE null
);

create table opencps_zalomap (
	zaloMapId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	uId VARCHAR(255) null,
	telNo VARCHAR(128) null,
	zaloOAId VARCHAR(255) null,
	isFollowed INTEGER,
	payload TEXT null
);