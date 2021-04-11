create index IX_6D65FCCE on opencps_adminconfig (code_[$COLUMN_LENGTH:255$]);

create index IX_4F7DE08C on opencps_api_manager (apiCode[$COLUMN_LENGTH:75$]);
create index IX_2B94549F on opencps_api_manager (groupId);

create index IX_7F39107A on opencps_api_mânger (dynamicReportId, roleId);
create index IX_B62F9FEE on opencps_api_mânger (roleId);

create index IX_57B8BA6 on opencps_api_role (groupId);
create index IX_9B97863B on opencps_api_role (roleCode[$COLUMN_LENGTH:75$]);

create index IX_D64CC8C9 on opencps_dynamicreport (groupId, reportCode[$COLUMN_LENGTH:128$]);
create index IX_F3E18AD6 on opencps_dynamicreport (groupId, reportType[$COLUMN_LENGTH:128$]);

create index IX_A0A5C810 on opencps_reportrole (dynamicReportId, roleId);
create index IX_19973198 on opencps_reportrole (roleId);

create index IX_7DCDD958 on opencps_synctracking (api[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_99F57BF6 on opencps_synctracking (api[$COLUMN_LENGTH:75$], dossierNo[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_5298CB8C on opencps_synctracking (api[$COLUMN_LENGTH:75$], serviceCode[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_4154E142 on opencps_synctracking (api[$COLUMN_LENGTH:75$], serviceCode[$COLUMN_LENGTH:75$], dossierNo[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_E86A01E4 on opencps_synctracking (createDate, modifiedDate);
create index IX_ACD6C3EA on opencps_synctracking (dossierNo[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_5D8AF1B8 on opencps_synctracking (groupId, createDate, modifiedDate);
create index IX_FC376796 on opencps_synctracking (groupId, dossierNo[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_C61B1D01 on opencps_synctracking (groupId, protocol[$COLUMN_LENGTH:75$], dossierNo[$COLUMN_LENGTH:75$]);
create index IX_E5F3717 on opencps_synctracking (groupId, protocol[$COLUMN_LENGTH:75$], serviceCode[$COLUMN_LENGTH:75$]);
create index IX_1A268FF1 on opencps_synctracking (groupId, referenceUid[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_2C0E4F2C on opencps_synctracking (groupId, serviceCode[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_1366D9A2 on opencps_synctracking (groupId, serviceCode[$COLUMN_LENGTH:75$], dossierNo[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_7C85A155 on opencps_synctracking (protocol[$COLUMN_LENGTH:75$], dossierNo[$COLUMN_LENGTH:75$]);
create index IX_D439F66B on opencps_synctracking (protocol[$COLUMN_LENGTH:75$], serviceCode[$COLUMN_LENGTH:75$]);
create index IX_DB1FE31D on opencps_synctracking (referenceUid[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_3247E680 on opencps_synctracking (serviceCode[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_6E6325CE on opencps_synctracking (serviceCode[$COLUMN_LENGTH:75$], dossierNo[$COLUMN_LENGTH:75$], createDate, modifiedDate);
create index IX_27BF4A4F on opencps_synctracking (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_49E41891 on opencps_synctracking (uuid_[$COLUMN_LENGTH:75$], groupId);