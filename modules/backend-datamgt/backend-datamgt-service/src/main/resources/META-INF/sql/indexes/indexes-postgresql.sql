create index IX_354CE276 on opencps_comment (uuid_, companyId);
create unique index IX_2E778E78 on opencps_comment (uuid_, groupId);

create index IX_A7CA616E on opencps_dictcollection (collectionCode, groupId);
create index IX_ABBCA0ED on opencps_dictcollection (groupId);
create index IX_F3CD7111 on opencps_dictcollection (uuid_, companyId);
create unique index IX_C57107D3 on opencps_dictcollection (uuid_, groupId);

create index IX_2ED489DA on opencps_dictgroup (groupCode, groupId);
create index IX_A99E2460 on opencps_dictgroup (uuid_, companyId);
create unique index IX_1B2442E2 on opencps_dictgroup (uuid_, groupId);

create index IX_9E48E215 on opencps_dictitem (dictCollectionId, groupId);
create index IX_750A2581 on opencps_dictitem (itemCode, dictCollectionId, groupId);
create index IX_7B053904 on opencps_dictitem (itemCode, groupId);
create index IX_B66C0FC6 on opencps_dictitem (uuid_, companyId);
create unique index IX_B121CFC8 on opencps_dictitem (uuid_, groupId);

create index IX_B8A2C8BB on opencps_dictitemgroup (groupId, dictGroupId, dictItemId);
create index IX_481F429 on opencps_dictitemgroup (groupId, dictItemId);
create index IX_F8A492D on opencps_dictitemgroup (uuid_, companyId);
create unique index IX_337D36EF on opencps_dictitemgroup (uuid_, groupId);

create index IX_A0188CE7 on opencps_holiday (groupId, holidayDate);
create index IX_FFAC65EF on opencps_holiday (uuid_, companyId);
create unique index IX_93A39C31 on opencps_holiday (uuid_, groupId);

create index IX_C9800AE4 on opencps_voting (uuid_, companyId);
create unique index IX_D095FA66 on opencps_voting (uuid_, groupId);

create index IX_E8A97B01 on opencps_votingresult (uuid_, companyId);
create unique index IX_B0A6CDC3 on opencps_votingresult (uuid_, groupId);

create index IX_7002EEE9 on opencps_workTime (groupId, day);
create index IX_570304FB on opencps_workTime (uuid_, companyId);
create unique index IX_51EE8E3D on opencps_workTime (uuid_, groupId);
