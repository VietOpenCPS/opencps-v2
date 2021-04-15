create index IX_A5F1D312 on opencps_systemlog (groupId);
create index IX_A886A7DF on opencps_systemlog (message[$COLUMN_LENGTH:75$]);
create index IX_1713F4AD on opencps_systemlog (method[$COLUMN_LENGTH:75$]);
create index IX_2001F063 on opencps_systemlog (moduleName[$COLUMN_LENGTH:75$]);
create index IX_2ACCEC7C on opencps_systemlog (preMethod[$COLUMN_LENGTH:75$]);
create index IX_866F2911 on opencps_systemlog (threadId[$COLUMN_LENGTH:75$]);
create index IX_A575423D on opencps_systemlog (type_[$COLUMN_LENGTH:75$]);
create unique index IX_D3EB120E on opencps_systemlog (uuid_[$COLUMN_LENGTH:75$], groupId);