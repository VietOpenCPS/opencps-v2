create index IX_767B71CC on opencps_statistic (companyId, groupId, month, year);
create index IX_751D6585 on opencps_statistic (groupId, domainCode[$COLUMN_LENGTH:75$], month, year);
create index IX_1124F771 on opencps_statistic (groupId, govAgencyCode[$COLUMN_LENGTH:75$], month, year, domainCode[$COLUMN_LENGTH:75$], reporting);
create index IX_10D3CA4B on opencps_statistic (groupId, month, year, govAgencyCode[$COLUMN_LENGTH:75$], domainCode[$COLUMN_LENGTH:75$]);
create index IX_EF7CF302 on opencps_statistic (groupId, userId, year);
create index IX_80F6A7A7 on opencps_statistic (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8F82EBE9 on opencps_statistic (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_728E9BFE on opencps_voting_statistic (companyId, groupId, month, year);
create index IX_FB6F8193 on opencps_voting_statistic (groupId, domainCode[$COLUMN_LENGTH:75$], month, year);
create index IX_2680507D on opencps_voting_statistic (groupId, month, year, govAgencyCode[$COLUMN_LENGTH:75$], domainCode[$COLUMN_LENGTH:75$]);
create index IX_BFA3D3B4 on opencps_voting_statistic (groupId, userId, year);
create index IX_DCEE9FB5 on opencps_voting_statistic (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_25958F77 on opencps_voting_statistic (uuid_[$COLUMN_LENGTH:75$], groupId);