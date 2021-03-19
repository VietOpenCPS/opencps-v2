create index IX_9F2DAB7B on opencps_systemlog (className[$COLUMN_LENGTH:75$]);
create index IX_43647514 on opencps_systemlog (createDate, createDate);
create index IX_A5F1D312 on opencps_systemlog (groupId);
create index IX_83304980 on opencps_systemlog (line);
create index IX_2001F063 on opencps_systemlog (moduleName[$COLUMN_LENGTH:75$]);
create index IX_A575423D on opencps_systemlog (type_[$COLUMN_LENGTH:75$]);
create unique index IX_D3EB120E on opencps_systemlog (uuid_[$COLUMN_LENGTH:75$], groupId);