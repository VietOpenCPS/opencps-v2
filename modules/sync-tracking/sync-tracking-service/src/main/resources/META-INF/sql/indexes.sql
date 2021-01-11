create index IX_5D8AF1B8 on opencps_synctracking (groupId, createDate, modifiedDate);
create index IX_FC376796 on opencps_synctracking (groupId, dossierNo[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_C61B1D01 on opencps_synctracking (groupId, protocol[$COLUMN_LENGTH:75$], dossierNo[$COLUMN_LENGTH:75$]);
create index IX_E5F3717 on opencps_synctracking (groupId, protocol[$COLUMN_LENGTH:75$], serviceCode[$COLUMN_LENGTH:75$]);
create index IX_1A268FF1 on opencps_synctracking (groupId, referenceUid[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_2C0E4F2C on opencps_synctracking (groupId, serviceCode[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_1366D9A2 on opencps_synctracking (groupId, serviceCode[$COLUMN_LENGTH:75$], dossierNo[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_27BF4A4F on opencps_synctracking (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_49E41891 on opencps_synctracking (uuid_[$COLUMN_LENGTH:75$], groupId);