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