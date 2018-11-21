create index IX_59547196 on opencps_deliverable (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7586598 on opencps_deliverable (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E0B1DAA2 on opencps_deliverablelog (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FCD4A1A4 on opencps_deliverablelog (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_4CDC5D4E on opencps_deliverabletype (groupId);
create index IX_9314D557 on opencps_deliverabletype (typeCode[$COLUMN_LENGTH:75$]);
create index IX_A47511D0 on opencps_deliverabletype (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2CB7CC52 on opencps_deliverabletype (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_396D0046 on opencps_deliverabletyperole (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8A4E048 on opencps_deliverabletyperole (uuid_[$COLUMN_LENGTH:75$], groupId);