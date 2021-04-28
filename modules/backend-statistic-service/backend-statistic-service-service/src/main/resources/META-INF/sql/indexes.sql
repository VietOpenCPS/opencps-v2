create index IX_37419090 on opencps_person_statistic (companyId, groupId, month, year);
create index IX_7E97F4A9 on opencps_person_statistic (groupId, employeeId, month, year);
create index IX_37F862A7 on opencps_person_statistic (groupId, month, year, govAgencyCode[$COLUMN_LENGTH:255$], employeeId);
create index IX_8951F6C6 on opencps_person_statistic (groupId, userId, year);
create index IX_A9B75963 on opencps_person_statistic (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_AEFCDCA5 on opencps_person_statistic (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_767B71CC on opencps_statistic (companyId, groupId, month, year);
create index IX_F2DD1962 on opencps_statistic (govAgencyCode[$COLUMN_LENGTH:255$], domainCode[$COLUMN_LENGTH:255$], groupAgencyCode[$COLUMN_LENGTH:4096$], system[$COLUMN_LENGTH:75$]);
create index IX_751D6585 on opencps_statistic (groupId, domainCode[$COLUMN_LENGTH:255$], month, year);
create index IX_1124F771 on opencps_statistic (groupId, govAgencyCode[$COLUMN_LENGTH:255$], month, year, domainCode[$COLUMN_LENGTH:255$], reporting);
create index IX_45A15230 on opencps_statistic (groupId, govAgencyCode[$COLUMN_LENGTH:255$], month, year, system[$COLUMN_LENGTH:75$], domainCode[$COLUMN_LENGTH:255$]);
create index IX_68F959EF on opencps_statistic (groupId, month, year, domainCode[$COLUMN_LENGTH:255$]);
create index IX_41411159 on opencps_statistic (groupId, month, year, govAgencyCode[$COLUMN_LENGTH:255$], domainCode[$COLUMN_LENGTH:255$], groupAgencyCode[$COLUMN_LENGTH:4096$], system[$COLUMN_LENGTH:75$]);
create index IX_ACE3952E on opencps_statistic (groupId, month, year, govAgencyCode[$COLUMN_LENGTH:255$], domainCode[$COLUMN_LENGTH:255$], system[$COLUMN_LENGTH:75$]);
create index IX_CF87F234 on opencps_statistic (groupId, month, year, reporting);
create index IX_89A15952 on opencps_statistic (groupId, month, year, system[$COLUMN_LENGTH:75$], domainCode[$COLUMN_LENGTH:255$]);
create index IX_EF7CF302 on opencps_statistic (groupId, userId, year);
create index IX_1BF001B7 on opencps_statistic (groupId, year, govAgencyCode[$COLUMN_LENGTH:255$], domainCode[$COLUMN_LENGTH:255$], groupAgencyCode[$COLUMN_LENGTH:4096$], system[$COLUMN_LENGTH:75$]);
create index IX_EFB499FD on opencps_statistic (month, year, govAgencyCode[$COLUMN_LENGTH:255$], domainCode[$COLUMN_LENGTH:255$], groupAgencyCode[$COLUMN_LENGTH:4096$], system[$COLUMN_LENGTH:75$]);
create index IX_9F4B280B on opencps_statistic (reporting);
create index IX_80F6A7A7 on opencps_statistic (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8F82EBE9 on opencps_statistic (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_70BC7181 on opencps_statistic_manual (companyId, groupId, month, year);
create index IX_C2FC5C70 on opencps_statistic_manual (groupId, domainCode[$COLUMN_LENGTH:255$], month, year);
create index IX_2906CE9C on opencps_statistic_manual (groupId, govAgencyCode[$COLUMN_LENGTH:255$], month, year, domainCode[$COLUMN_LENGTH:255$], reporting);
create index IX_79FE05E3 on opencps_statistic_manual (groupId, month, year, govAgencyCode[$COLUMN_LENGTH:255$], domainCode[$COLUMN_LENGTH:255$], system[$COLUMN_LENGTH:75$]);
create index IX_C9C8F1E9 on opencps_statistic_manual (groupId, month, year, reporting);
create index IX_92F320F7 on opencps_statistic_manual (groupId, userId, year);
create index IX_20BED492 on opencps_statistic_manual (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2AB75794 on opencps_statistic_manual (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_728E9BFE on opencps_voting_statistic (companyId, groupId, month, year);
create index IX_FB6F8193 on opencps_voting_statistic (groupId, domainCode[$COLUMN_LENGTH:255$], month, year);
create index IX_4CB1A625 on opencps_voting_statistic (groupId, month, year, govAgencyCode[$COLUMN_LENGTH:255$], domainCode[$COLUMN_LENGTH:255$], votingCode[$COLUMN_LENGTH:255$]);
create index IX_BFA3D3B4 on opencps_voting_statistic (groupId, userId, year);
create index IX_DCEE9FB5 on opencps_voting_statistic (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_25958F77 on opencps_voting_statistic (uuid_[$COLUMN_LENGTH:75$], groupId);