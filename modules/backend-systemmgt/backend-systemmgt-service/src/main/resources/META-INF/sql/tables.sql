create table opencps_scheduler (
	schedulerId LONG not null primary key,
	schedulerType VARCHAR(255) null,
	onTime DATE null,
	nextTime DATE null,
	expiredTime DATE null,
	minDuration LONG,
	maxDuration LONG
);