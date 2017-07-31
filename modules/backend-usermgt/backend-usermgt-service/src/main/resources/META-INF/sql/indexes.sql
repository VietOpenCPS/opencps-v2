create unique index IX_D966AF1A on m_contact (userMappingId);
create index IX_D2D93B88 on m_contact (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_A30B040A on m_contact (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_5AAC26AB on m_contactgroup (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_37D45BED on m_contactgroup (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E3D027A on m_employee (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F637DF7C on m_employee (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_4DA418D5 on m_employee_file (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_9196D097 on m_employee_file (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2FA74939 on m_employee_jobpos (employeeId);
create index IX_A6A3AD90 on m_employee_jobpos (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_CD5A5812 on m_employee_jobpos (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_DF687B43 on m_jobpos (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F84CF685 on m_jobpos (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F8FBA4F on m_join_site_status (employeeId, joinSiteGroupId);
create index IX_DA335CA1 on m_join_site_status (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_407C9763 on m_join_site_status (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_74A722CF on m_officesite (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7E81111 on m_officesite (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_6B341030 on m_partner (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_91E2E2B2 on m_partner (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_6457A8CC on m_partnerFile (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B10BF24E on m_partnerFile (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_AD6EF81D on m_workingunit (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4BA0E1DF on m_workingunit (uuid_[$COLUMN_LENGTH:75$], groupId);