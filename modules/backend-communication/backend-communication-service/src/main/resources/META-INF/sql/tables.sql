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
	classPK VARCHAR(75) null,
	payload TEXT null,
	fromUsername VARCHAR(75) null,
	toUsername VARCHAR(75) null,
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
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	notificationType VARCHAR(255) null,
	emailSubject STRING null,
	emailBody TEXT null,
	textMessage STRING null,
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

create table opencps_preferences (
	uuid_ VARCHAR(75) null,
	preferencesId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	preferences TEXT null
);

create table opencps_serverconfig (
	serverConfigId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
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
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	uId VARCHAR(75) null,
	telNo VARCHAR(75) null,
	oAId VARCHAR(75) null,
	isFollowed INTEGER,
	payload VARCHAR(75) null
);