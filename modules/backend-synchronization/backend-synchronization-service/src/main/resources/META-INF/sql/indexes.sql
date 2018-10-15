create index IX_CBD7F3E9 on opencps_dictcollection_temp (collectionCode[$COLUMN_LENGTH:75$], groupId);
create index IX_7AFC8968 on opencps_dictcollection_temp (groupId);
create index IX_BB90AEFD on opencps_dictcollection_temp (modifiedDate, groupId);
create index IX_C93BE2F6 on opencps_dictcollection_temp (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1B2CAEF8 on opencps_dictcollection_temp (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A2CE1F4 on opencps_dictgroup_temp (dictCollectionId, groupId);
create index IX_B3C2A2E4 on opencps_dictgroup_temp (groupCode[$COLUMN_LENGTH:75$], groupId, dictCollectionId);
create index IX_48F0CB8C on opencps_dictgroup_temp (modifiedDate, groupId);
create index IX_2FB1E907 on opencps_dictgroup_temp (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_24DF0549 on opencps_dictgroup_temp (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_642FE2DA on opencps_dictitem_temp (dictCollectionId, groupId);
create index IX_8986C468 on opencps_dictitem_temp (dictCollectionId, parentItemId, treeIndex[$COLUMN_LENGTH:75$]);
create index IX_45EE6128 on opencps_dictitem_temp (groupId, dictCollectionId, parentItemId, level);
create index IX_DA2EB406 on opencps_dictitem_temp (itemCode[$COLUMN_LENGTH:75$], dictCollectionId, groupId);
create index IX_15A7CEC9 on opencps_dictitem_temp (itemCode[$COLUMN_LENGTH:75$], groupId);
create index IX_BCB42F72 on opencps_dictitem_temp (modifiedDate, groupId);
create index IX_C76E56B9 on opencps_dictitem_temp (parentItemId);
create index IX_CBED2D61 on opencps_dictitem_temp (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_33F39823 on opencps_dictitem_temp (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_293C41E8 on opencps_dictitemgroup_temp (groupId, dictGroupId, dictItemId);
create index IX_FBFA15DC on opencps_dictitemgroup_temp (groupId, dictItemId);
create index IX_9C231419 on opencps_dictitemgroup_temp (modifiedDate, groupId);
create index IX_698DBC5A on opencps_dictitemgroup_temp (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F82D915C on opencps_dictitemgroup_temp (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_14583E7D on opencps_pushcollection (groupId, collectionCode[$COLUMN_LENGTH:75$], method[$COLUMN_LENGTH:75$]);
create index IX_192B1321 on opencps_pushcollection (groupId, serverNo[$COLUMN_LENGTH:75$]);
create index IX_3F820D75 on opencps_pushcollection (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_3DA96D37 on opencps_pushcollection (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_30B6970E on opencps_pushdictgroup (groupId, collectionCode[$COLUMN_LENGTH:75$], groupCode[$COLUMN_LENGTH:75$], itemCode[$COLUMN_LENGTH:75$], method[$COLUMN_LENGTH:75$]);
create index IX_D9F26BA on opencps_pushdictgroup (groupId, collectionCode[$COLUMN_LENGTH:75$], groupCode[$COLUMN_LENGTH:75$], method[$COLUMN_LENGTH:75$]);
create index IX_7BA625B0 on opencps_pushdictgroup (groupId, serverNo[$COLUMN_LENGTH:75$]);
create index IX_1122ED06 on opencps_pushdictgroup (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2A6EFD08 on opencps_pushdictgroup (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F6315886 on opencps_pushdictitem (groupId, collectionCode[$COLUMN_LENGTH:75$], itemCode[$COLUMN_LENGTH:75$], method[$COLUMN_LENGTH:75$]);
create index IX_E7062396 on opencps_pushdictitem (groupId, serverNo[$COLUMN_LENGTH:75$]);
create index IX_90789A60 on opencps_pushdictitem (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C22438E2 on opencps_pushdictitem (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_DACB7 on opencps_syncqueue (groupId, className[$COLUMN_LENGTH:75$], method[$COLUMN_LENGTH:75$]);
create index IX_1B86F0E9 on opencps_syncqueue (groupId, serverNo[$COLUMN_LENGTH:75$]);
create index IX_26CF56AD on opencps_syncqueue (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_ABA7A46F on opencps_syncqueue (uuid_[$COLUMN_LENGTH:75$], groupId);