create index IX_A96619E8 on opencps_comment (groupId);
create index IX_354CE276 on opencps_comment (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2E778E78 on opencps_comment (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_AB154920 on opencps_evaluation (employeeId, score);
create index IX_952F9EF9 on opencps_evaluation (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EDD58FBB on opencps_evaluation (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_ED73DF20 on opencps_voting (className[$COLUMN_LENGTH:75$], classPK[$COLUMN_LENGTH:75$]);
create index IX_C9800AE4 on opencps_voting (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D095FA66 on opencps_voting (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_EF6503DD on opencps_votingresult (userId, votingId);
create index IX_E8A97B01 on opencps_votingresult (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B0A6CDC3 on opencps_votingresult (uuid_[$COLUMN_LENGTH:75$], groupId);
create index IX_24D88C32 on opencps_votingresult (votingId, selected[$COLUMN_LENGTH:75$]);