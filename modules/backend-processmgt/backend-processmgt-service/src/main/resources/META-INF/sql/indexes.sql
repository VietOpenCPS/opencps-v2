create index IX_FF7CD006 on opencps_dossieraction (groupId);
create index IX_2EFE2E18 on opencps_dossieraction (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_35EB5A9A on opencps_dossieraction (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_662474BD on opencps_dossieractionuser (dossierActionId);
create index IX_B06993A5 on opencps_dossieractionuser (uuid_[$COLUMN_LENGTH:75$]);

create index IX_96DAB1FE on opencps_processaction (postProcessStepId);
create index IX_7AFD956F on opencps_processaction (preProcessStepId);
create index IX_6C058331 on opencps_processaction (serviceProcessId);
create index IX_EBDC8BC on opencps_processaction (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_39594E3E on opencps_processaction (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2D87C7C7 on opencps_processstep (serviceProcessId);
create index IX_D0400D52 on opencps_processstep (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F6AFC054 on opencps_processstep (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D00EB5E7 on opencps_serviceprocess (groupId);
create index IX_D84A5557 on opencps_serviceprocess (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8A464599 on opencps_serviceprocess (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_83FA2337 on opencps_stepallowance (processStepId);
create index IX_7101B8A3 on opencps_stepallowance (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_47D60BE5 on opencps_stepallowance (uuid_[$COLUMN_LENGTH:75$], groupId);