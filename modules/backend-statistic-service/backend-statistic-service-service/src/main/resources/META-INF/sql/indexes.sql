create index IX_751D6585 on opencps_statistic (groupId, domainCode[$COLUMN_LENGTH:75$], month, year);
create index IX_1124F771 on opencps_statistic (groupId, govAgencyCode[$COLUMN_LENGTH:75$], month, year, domainCode[$COLUMN_LENGTH:75$], reporting);
create index IX_10D3CA4B on opencps_statistic (groupId, month, year, govAgencyCode[$COLUMN_LENGTH:75$], domainCode[$COLUMN_LENGTH:75$]);
create index IX_EF7CF302 on opencps_statistic (groupId, userId, year);
create index IX_80F6A7A7 on opencps_statistic (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8F82EBE9 on opencps_statistic (uuid_[$COLUMN_LENGTH:75$], groupId);