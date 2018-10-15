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
	upvotedUsers VARCHAR(75) null
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
	employeeName VARCHAR(75) null,
	score INTEGER
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