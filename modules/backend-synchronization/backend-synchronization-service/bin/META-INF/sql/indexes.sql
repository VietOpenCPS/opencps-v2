create index IX_14583E7D on opencps_pushcollection (groupId, collectionCode[$COLUMN_LENGTH:75$], method[$COLUMN_LENGTH:75$]);
create index IX_3F820D75 on opencps_pushcollection (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_3DA96D37 on opencps_pushcollection (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F6315886 on opencps_pushdictitem (groupId, collectionCode[$COLUMN_LENGTH:75$], itemCode[$COLUMN_LENGTH:75$], method[$COLUMN_LENGTH:75$]);
create index IX_90789A60 on opencps_pushdictitem (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C22438E2 on opencps_pushdictitem (uuid_[$COLUMN_LENGTH:75$], groupId);