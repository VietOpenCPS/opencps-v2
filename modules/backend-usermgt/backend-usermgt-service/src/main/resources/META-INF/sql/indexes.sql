create index IX_FEDE85E2 on m_jobposwork (groupId, jobPostId, checklistCat[$COLUMN_LENGTH:75$]);
create index IX_EF986354 on m_jobposwork (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_34C9AED6 on m_jobposwork (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_58ABB49 on opencps_applicant (applicantIdNo[$COLUMN_LENGTH:75$]);
create unique index IX_70B3C735 on opencps_applicant (contactEmail[$COLUMN_LENGTH:75$]);
create unique index IX_7D1942D5 on opencps_applicant (contactTelNo[$COLUMN_LENGTH:75$]);
create index IX_84E6D849 on opencps_applicant (groupId, applicantIdNo[$COLUMN_LENGTH:75$]);
create unique index IX_EE04E01F on opencps_applicant (mappingUserId);
create index IX_58078059 on opencps_applicant (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_35F6491B on opencps_applicant (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A8503959 on opencps_employee (groupId, email[$COLUMN_LENGTH:75$]);
create index IX_FFDB99D6 on opencps_employee (groupId, employeeNo[$COLUMN_LENGTH:75$]);
create index IX_11A42D71 on opencps_employee (groupId, mappingUserId);
create index IX_24724BCD on opencps_employee (groupId, userId);
create index IX_48E4AB8D on opencps_employee (mappingUserId);
create index IX_7941D62B on opencps_employee (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_370FEB6D on opencps_employee (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_253CEBA8 on opencps_employee_jobpos (employeeId);
create index IX_45F412F4 on opencps_employee_jobpos (groupId, employeeId, jobPostId, workingUnitId);
create index IX_DCBDEC9 on opencps_employee_jobpos (groupId, jobPostId);
create index IX_F9CB80BF on opencps_employee_jobpos (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E16B2B01 on opencps_employee_jobpos (uuid_[$COLUMN_LENGTH:75$], groupId);
create index IX_B6CA9215 on opencps_employee_jobpos (workingUnitId);

create index IX_A68549C2 on opencps_jobpos (groupId, jobPosCode[$COLUMN_LENGTH:75$]);
create index IX_D307AE5 on opencps_jobpos (groupId, mappingRoleId);
create index IX_F3E0B05E on opencps_jobpos (groupId, title[$COLUMN_LENGTH:75$]);
create index IX_20408734 on opencps_jobpos (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6EE94AB6 on opencps_jobpos (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_20BCFC05 on opencps_officesite (groupId, siteGroupId);
create index IX_31C5CE40 on opencps_officesite (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_72ECE4C2 on opencps_officesite (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_A55BFF69 on opencps_preferences (groupId, userId);
create index IX_158E960F on opencps_preferences (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_A6935451 on opencps_preferences (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_91CAEB92 on opencps_resourcerole (groupId, className[$COLUMN_LENGTH:75$], classPK[$COLUMN_LENGTH:75$], roleId);
create index IX_6858DC81 on opencps_resourcerole (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8FC08F43 on opencps_resourcerole (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_EA37248D on opencps_resourceuser (groupId, className[$COLUMN_LENGTH:75$], classPK[$COLUMN_LENGTH:75$], toUserId);
create index IX_7B404B56 on opencps_resourceuser (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_71EFAF58 on opencps_resourceuser (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_13DBB3CF on opencps_visibility (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_5D0CE211 on opencps_visibility (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F35D685E on opencps_workingunit (groupId, govAgencyCode[$COLUMN_LENGTH:75$]);
create index IX_122E55D0 on opencps_workingunit (groupId, parentWorkingUnitId, level);
create index IX_87079A72 on opencps_workingunit (groupId, treeIndex[$COLUMN_LENGTH:75$]);
create index IX_1989BDEE on opencps_workingunit (groupId, workingUnitId);
create index IX_9425BACC on opencps_workingunit (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4136844E on opencps_workingunit (uuid_[$COLUMN_LENGTH:75$], groupId);