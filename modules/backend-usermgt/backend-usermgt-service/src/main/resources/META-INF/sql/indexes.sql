create index IX_FEDE85E2 on m_jobposwork (groupId, jobPostId, checklistCat[$COLUMN_LENGTH:512$]);
create index IX_EF986354 on m_jobposwork (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_34C9AED6 on m_jobposwork (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E0215973 on opencps_answer (groupId, className[$COLUMN_LENGTH:255$], classPK[$COLUMN_LENGTH:255$]);
create index IX_5C24A68A on opencps_answer (groupId, publish, synced);
create index IX_9B933367 on opencps_answer (groupId, questionId, publish);

create unique index IX_58ABB49 on opencps_applicant (applicantIdNo[$COLUMN_LENGTH:255$]);
create unique index IX_70B3C735 on opencps_applicant (contactEmail[$COLUMN_LENGTH:255$]);
create unique index IX_7D1942D5 on opencps_applicant (contactTelNo[$COLUMN_LENGTH:255$]);
create index IX_84E6D849 on opencps_applicant (groupId, applicantIdNo[$COLUMN_LENGTH:255$]);
create index IX_F17CD202 on opencps_applicant (groupId, applicantIdType[$COLUMN_LENGTH:255$]);
create unique index IX_B6E00A35 on opencps_applicant (groupId, contactEmail[$COLUMN_LENGTH:255$]);
create unique index IX_C34585D5 on opencps_applicant (groupId, contactTelNo[$COLUMN_LENGTH:255$]);
create index IX_6D125507 on opencps_applicant (groupId, mappingClassName[$COLUMN_LENGTH:255$], mappingClassPK[$COLUMN_LENGTH:255$]);
create index IX_EE04E01F on opencps_applicant (mappingUserId);
create index IX_58078059 on opencps_applicant (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_35F6491B on opencps_applicant (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_7160D281 on opencps_applicant_data (groupId, dossierNo[$COLUMN_LENGTH:128$], fileTemplateNo[$COLUMN_LENGTH:255$], applicantIdNo[$COLUMN_LENGTH:128$]);
create index IX_14A28584 on opencps_applicant_data (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_49569D06 on opencps_applicant_data (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_370E9375 on opencps_employee (email[$COLUMN_LENGTH:512$]);
create index IX_A8503959 on opencps_employee (groupId, email[$COLUMN_LENGTH:512$]);
create index IX_FFD92AB0 on opencps_employee (groupId, employeeId);
create index IX_FFDB99D6 on opencps_employee (groupId, employeeNo[$COLUMN_LENGTH:255$]);
create index IX_11A42D71 on opencps_employee (groupId, mappingUserId);
create index IX_24724BCD on opencps_employee (groupId, userId);
create index IX_4BDCCB46 on opencps_employee (mappingUserId, workingStatus);
create index IX_7941D62B on opencps_employee (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_370FEB6D on opencps_employee (uuid_[$COLUMN_LENGTH:75$], groupId);
create index IX_16EDAF5F on opencps_employee (workingStatus, employeeNo[$COLUMN_LENGTH:255$]);

create index IX_253CEBA8 on opencps_employee_jobpos (employeeId);
create index IX_45F412F4 on opencps_employee_jobpos (groupId, employeeId, jobPostId, workingUnitId);
create index IX_DCBDEC9 on opencps_employee_jobpos (groupId, jobPostId);
create index IX_F9CB80BF on opencps_employee_jobpos (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E16B2B01 on opencps_employee_jobpos (uuid_[$COLUMN_LENGTH:75$], groupId);
create index IX_B6CA9215 on opencps_employee_jobpos (workingUnitId);

create index IX_A082263D on opencps_fileitem (groupId, fileTemplateNo[$COLUMN_LENGTH:128$]);
create index IX_9F5B50F8 on opencps_fileitem (groupId, status);
create index IX_E9DD878C on opencps_fileitem (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_37FA810E on opencps_fileitem (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A68549C2 on opencps_jobpos (groupId, jobPosCode[$COLUMN_LENGTH:255$]);
create index IX_3D2FB230 on opencps_jobpos (groupId, jobPosId);
create index IX_D307AE5 on opencps_jobpos (groupId, mappingRoleId);
create index IX_F3E0B05E on opencps_jobpos (groupId, title[$COLUMN_LENGTH:255$]);
create index IX_20408734 on opencps_jobpos (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6EE94AB6 on opencps_jobpos (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_20BCFC05 on opencps_officesite (groupId, siteGroupId);
create index IX_31C5CE40 on opencps_officesite (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_72ECE4C2 on opencps_officesite (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_A55BFF69 on opencps_preferences (groupId, userId);
create index IX_158E960F on opencps_preferences (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_A6935451 on opencps_preferences (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E14E050B on opencps_question (groupId, className[$COLUMN_LENGTH:255$], classPK[$COLUMN_LENGTH:255$]);
create index IX_B1131CB8 on opencps_question (groupId, publish, questionType[$COLUMN_LENGTH:255$], govAgencyCode[$COLUMN_LENGTH:255$]);
create index IX_92C13CE9 on opencps_question (groupId, publish, questionType[$COLUMN_LENGTH:255$], subDomainCode[$COLUMN_LENGTH:255$]);
create index IX_91A961F2 on opencps_question (groupId, publish, synced);

create index IX_91CAEB92 on opencps_resourcerole (groupId, className[$COLUMN_LENGTH:255$], classPK[$COLUMN_LENGTH:255$], roleId);
create index IX_6858DC81 on opencps_resourcerole (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8FC08F43 on opencps_resourcerole (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_EA37248D on opencps_resourceuser (groupId, className[$COLUMN_LENGTH:255$], classPK[$COLUMN_LENGTH:75$], toUserId);
create index IX_7B404B56 on opencps_resourceuser (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_71EFAF58 on opencps_resourceuser (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_99ABEB80 on opencps_save_pick_field (groupId, userId, classPK[$COLUMN_LENGTH:75$]);
create unique index IX_D092ECB7 on opencps_save_pick_field (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E6980DC5 on opencps_sync_scheduler (className[$COLUMN_LENGTH:255$], retry);
create index IX_2247AC10 on opencps_sync_scheduler (className[$COLUMN_LENGTH:255$], syncDate);
create index IX_4F2917EE on opencps_sync_scheduler (className[$COLUMN_LENGTH:255$], typeCode[$COLUMN_LENGTH:255$]);
create unique index IX_7962DE56 on opencps_sync_scheduler (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_6B3973DC on opencps_track_client (sessionId[$COLUMN_LENGTH:128$], leaveDate);
create index IX_4D2C3370 on opencps_track_client (sessionId[$COLUMN_LENGTH:128$], visitDate);
create index IX_D692EFEC on opencps_track_client (uuid_[$COLUMN_LENGTH:75$]);

create index IX_8E58C98E on opencps_track_client_statistic (day, month, year, desktop, mobile, tablet);
create index IX_68556269 on opencps_track_client_statistic (url[$COLUMN_LENGTH:2048$], year, month, day, desktop, mobile, tablet);
create index IX_7281E1BB on opencps_track_client_statistic (uuid_[$COLUMN_LENGTH:75$]);

create index IX_BFD53D86 on opencps_userlogin (userId, sessionId[$COLUMN_LENGTH:255$]);
create index IX_26EF75D5 on opencps_userlogin (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_10156D97 on opencps_userlogin (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F0FE0BDC on opencps_usertrackpath (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_13DBB3CF on opencps_visibility (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_5D0CE211 on opencps_visibility (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F35D685E on opencps_workingunit (groupId, govAgencyCode[$COLUMN_LENGTH:255$]);
create index IX_122E55D0 on opencps_workingunit (groupId, parentWorkingUnitId, level);
create index IX_87079A72 on opencps_workingunit (groupId, treeIndex[$COLUMN_LENGTH:255$]);
create index IX_1989BDEE on opencps_workingunit (groupId, workingUnitId);
create index IX_9425BACC on opencps_workingunit (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4136844E on opencps_workingunit (uuid_[$COLUMN_LENGTH:75$], groupId);