create index IX_354CE276 on opencps_comment (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2E778E78 on opencps_comment (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A7CA616E on opencps_dictcollection (collectionCode[$COLUMN_LENGTH:75$], groupId);
create index IX_F3CD7111 on opencps_dictcollection (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C57107D3 on opencps_dictcollection (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A99E2460 on opencps_dictgroup (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1B2442E2 on opencps_dictgroup (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_6426BE35 on opencps_dictitem (dictCollectionId);
create index IX_7B053904 on opencps_dictitem (itemCode[$COLUMN_LENGTH:75$], groupId);
create index IX_B66C0FC6 on opencps_dictitem (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B121CFC8 on opencps_dictitem (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F8A492D on opencps_dictitemgroup (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_337D36EF on opencps_dictitemgroup (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_90BE09CB on opencps_label (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_944870D on opencps_label (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B706C5B2 on opencps_location (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6E8910B4 on opencps_location (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_C9800AE4 on opencps_voting (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D095FA66 on opencps_voting (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E8A97B01 on opencps_votingresult (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B0A6CDC3 on opencps_votingresult (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D61EC66C on opencps_workspace (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E73DF7EE on opencps_workspace (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_5FB306E2 on opencps_workspacerole (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_76B3DDE4 on opencps_workspacerole (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_729A75B7 on opencps_workspaceuser (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_58E2FDF9 on opencps_workspaceuser (uuid_[$COLUMN_LENGTH:75$], groupId);
create index IX_4997BF1D on opencps_workspaceuser (workspaceId);