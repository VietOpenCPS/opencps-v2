create table opencps_comment (
	uuid_ VARCHAR(75) null,
	commentId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	className VARCHAR(255) null,
	classPK VARCHAR(255) null,
	fullname VARCHAR(1024) null,
	email VARCHAR(255) null,
	parent LONG,
	content TEXT null,
	fileEntryId LONG,
	pings VARCHAR(75) null,
	upvoteCount INTEGER,
	userHasUpvoted VARCHAR(255) null,
	upvotedUsers VARCHAR(255) null,
	opinion BOOLEAN
);

create table opencps_evaluation (
	uuid_ VARCHAR(75) null,
	evaluationId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	employeeId LONG,
	employeeName VARCHAR(1024) null,
	score INTEGER
);

create table opencps_voting (
	uuid_ VARCHAR(75) null,
	votingId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	className VARCHAR(75) null,
	classPK VARCHAR(255) null,
	subject TEXT null,
	choices TEXT null,
	templateNo VARCHAR(255) null,
	commentable BOOLEAN,
	votingCode VARCHAR(255) null
);

create table opencps_votingresult (
	uuid_ VARCHAR(75) null,
	votingResultId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	votingId LONG,
	fullname VARCHAR(1024) null,
	email VARCHAR(255) null,
	comment_ TEXT null,
	selected VARCHAR(1024) null
);