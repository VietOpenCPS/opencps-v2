create index IX_9F60A0BE on m_fileattach (groupId, className[$COLUMN_LENGTH:75$], classPK[$COLUMN_LENGTH:75$], docFileId);

create index IX_A4AAE818 on opencps_comment (groupId, className[$COLUMN_LENGTH:75$], classPK[$COLUMN_LENGTH:75$]);
create index IX_19A6AF20 on opencps_comment (groupId, userId, className[$COLUMN_LENGTH:75$], classPK[$COLUMN_LENGTH:75$], opinion);
create index IX_354CE276 on opencps_comment (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2E778E78 on opencps_comment (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A7CA616E on opencps_dictcollection (collectionCode[$COLUMN_LENGTH:75$], groupId);
create index IX_ABBCA0ED on opencps_dictcollection (groupId);
create index IX_1DAB9C2 on opencps_dictcollection (modifiedDate, groupId);
create index IX_F3CD7111 on opencps_dictcollection (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C57107D3 on opencps_dictcollection (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D45B5ABB on opencps_dictgroup (dictCollectionId, groupId);
create index IX_A170B7BD on opencps_dictgroup (groupCode[$COLUMN_LENGTH:75$], groupId, dictCollectionId);
create index IX_6850BFD3 on opencps_dictgroup (modifiedDate, groupId);
create index IX_A99E2460 on opencps_dictgroup (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1B2442E2 on opencps_dictgroup (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_9E48E215 on opencps_dictitem (dictCollectionId, groupId);
create index IX_5EE7EDA3 on opencps_dictitem (dictCollectionId, parentItemId, treeIndex[$COLUMN_LENGTH:75$]);
create index IX_8CC4CD on opencps_dictitem (groupId, dictCollectionId, parentItemId, level);
create index IX_750A2581 on opencps_dictitem (itemCode[$COLUMN_LENGTH:75$], dictCollectionId, groupId);
create index IX_7B053904 on opencps_dictitem (itemCode[$COLUMN_LENGTH:75$], groupId);
create index IX_48C042D on opencps_dictitem (modifiedDate, groupId);
create index IX_9A0339E on opencps_dictitem (parentItemId);
create index IX_B66C0FC6 on opencps_dictitem (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B121CFC8 on opencps_dictitem (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B8A2C8BB on opencps_dictitemgroup (groupId, dictGroupId, dictItemId);
create index IX_481F429 on opencps_dictitemgroup (groupId, dictItemId);
create index IX_A22C9326 on opencps_dictitemgroup (modifiedDate, groupId);
create index IX_F8A492D on opencps_dictitemgroup (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_337D36EF on opencps_dictitemgroup (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A0188CE7 on opencps_holiday (groupId, holidayDate);
create index IX_FFAC65EF on opencps_holiday (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_93A39C31 on opencps_holiday (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_C9800AE4 on opencps_voting (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D095FA66 on opencps_voting (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E8A97B01 on opencps_votingresult (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B0A6CDC3 on opencps_votingresult (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_7002EEE9 on opencps_workTime (groupId, day);
create index IX_570304FB on opencps_workTime (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_51EE8E3D on opencps_workTime (uuid_[$COLUMN_LENGTH:75$], groupId);