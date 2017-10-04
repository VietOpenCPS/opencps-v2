create index IX_C4AA04E2 on opencps_dossier (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_9525BE4 on opencps_dossier (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2EFE2E18 on opencps_dossieraction (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_35EB5A9A on opencps_dossieraction (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_BBC9CE33 on opencps_dossieractionsync (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4DC64575 on opencps_dossieractionsync (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B06993A5 on opencps_dossieractionuser (uuid_[$COLUMN_LENGTH:75$]);

create index IX_F3B1D05E on opencps_dossierfile (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_558ED660 on opencps_dossierfile (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_7C31F3D6 on opencps_dossierlog (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FC3177D8 on opencps_dossierlog (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_991D1DC0 on opencps_dossierpart (groupId, templateNo[$COLUMN_LENGTH:75$], partNo[$COLUMN_LENGTH:75$]);
create index IX_5E4D5D55 on opencps_dossierpart (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_ACC73517 on opencps_dossierpart (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E5E8009B on opencps_dossiertemplate (groupId, templateName[$COLUMN_LENGTH:75$]);
create index IX_7351EDD1 on opencps_dossiertemplate (groupId, templateNo[$COLUMN_LENGTH:75$]);
create index IX_3B70A85C on opencps_dossiertemplate (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_167F95DE on opencps_dossiertemplate (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_687E359F on opencps_paymentconfig (groupId);
create index IX_50A3049F on opencps_paymentconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C61226E1 on opencps_paymentconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_6BE34299 on opencps_paymentfile (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C65D9B5B on opencps_paymentfile (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3F5FEA35 on opencps_processaction (postStepCode[$COLUMN_LENGTH:75$], groupId);
create index IX_C791AD26 on opencps_processaction (preStepCode[$COLUMN_LENGTH:75$], groupId);
create index IX_6C058331 on opencps_processaction (serviceProcessId);
create index IX_EBDC8BC on opencps_processaction (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_39594E3E on opencps_processaction (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_BF7CC61B on opencps_processoption (serviceConfigId);
create index IX_DF143D7B on opencps_processoption (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C5F3E6BD on opencps_processoption (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2D87C7C7 on opencps_processstep (serviceProcessId);
create index IX_388C6AC8 on opencps_processstep (stepCode[$COLUMN_LENGTH:75$], groupId, serviceProcessId);
create index IX_D0400D52 on opencps_processstep (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F6AFC054 on opencps_processstep (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_85322B32 on opencps_processsteprole (processStepId);
create index IX_134D7D60 on opencps_processsteprole (uuid_[$COLUMN_LENGTH:75$]);

create index IX_4F240C8E on opencps_serviceconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6FC25E90 on opencps_serviceconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3AF75462 on opencps_serviceinfo (companyId);
create index IX_29AD8B64 on opencps_serviceinfo (groupId);
create index IX_3E222E7E on opencps_serviceinfo (serviceCode[$COLUMN_LENGTH:75$], groupId);
create index IX_D3675C7A on opencps_serviceinfo (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D630B97C on opencps_serviceinfo (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_55C498C3 on opencps_serviceprocess (groupId, processName[$COLUMN_LENGTH:75$]);
create index IX_CE466FF9 on opencps_serviceprocess (groupId, processNo[$COLUMN_LENGTH:75$]);
create index IX_D84A5557 on opencps_serviceprocess (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8A464599 on opencps_serviceprocess (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_8F9EE2C2 on opencps_serviceprocessrole (serviceProcessId);
create index IX_645000FB on opencps_serviceprocessrole (uuid_[$COLUMN_LENGTH:75$]);

create index IX_6C3BEC69 on opencps_services_filetemplates (serviceInfoId);
create index IX_71CB1E6F on opencps_services_filetemplates (uuid_[$COLUMN_LENGTH:75$]);