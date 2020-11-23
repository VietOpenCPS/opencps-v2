create index IX_A4AAE818 on opencps_comment (groupId, className[$COLUMN_LENGTH:255$], classPK[$COLUMN_LENGTH:255$]);
create index IX_19A6AF20 on opencps_comment (groupId, userId, className[$COLUMN_LENGTH:255$], classPK[$COLUMN_LENGTH:255$], opinion);
create index IX_354CE276 on opencps_comment (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2E778E78 on opencps_comment (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_AB154920 on opencps_evaluation (employeeId, score);
create index IX_952F9EF9 on opencps_evaluation (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EDD58FBB on opencps_evaluation (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_ED73DF20 on opencps_voting (className[$COLUMN_LENGTH:75$], classPK[$COLUMN_LENGTH:255$]);
create index IX_F64FF8CB on opencps_voting (className[$COLUMN_LENGTH:75$], votingCode[$COLUMN_LENGTH:255$]);
create index IX_92C3CEEA on opencps_voting (groupId, className[$COLUMN_LENGTH:75$], classPK[$COLUMN_LENGTH:255$]);
create index IX_C9800AE4 on opencps_voting (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D095FA66 on opencps_voting (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_EF6503DD on opencps_votingresult (userId, votingId);
create index IX_E8A97B01 on opencps_votingresult (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B0A6CDC3 on opencps_votingresult (uuid_[$COLUMN_LENGTH:75$], groupId);
create index IX_6E8166FD on opencps_votingresult (votingId, selected[$COLUMN_LENGTH:1024$], modifiedDate);