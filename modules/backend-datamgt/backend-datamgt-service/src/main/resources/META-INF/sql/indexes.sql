create index IX_5B237047 on m_comment (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D1895C89 on m_comment (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_14C7ED7F on m_dictcollection (collectionCode[$COLUMN_LENGTH:75$], groupId);
create index IX_85C3E620 on m_dictcollection (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D54F74A2 on m_dictcollection (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B40881F1 on m_dictgroup (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_40FAD0B3 on m_dictgroup (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F921EA84 on m_dictitem (dictCollectionId);
create index IX_856F9695 on m_dictitem (itemCode[$COLUMN_LENGTH:75$], groupId);
create index IX_4B673C15 on m_dictitem (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7049C3D7 on m_dictitem (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_FB79763E on m_dictitemgroup (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4CC67440 on m_dictitemgroup (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_33CFD7DC on m_label (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D3E4A55E on m_label (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_4C01F201 on m_location (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2DB104C3 on m_location (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_88A7FEF3 on m_voting (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_59F9A635 on m_voting (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F887E7D0 on m_votingresult (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F3882252 on m_votingresult (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E08923FD on m_workspace (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D1485BF on m_workspace (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_4BA233F3 on m_workspacerole (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8FFD1B35 on m_workspacerole (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_5E89A2C8 on m_workspaceuser (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_722C3B4A on m_workspaceuser (uuid_[$COLUMN_LENGTH:75$], groupId);
create index IX_DE92EB6C on m_workspaceuser (workspaceId);