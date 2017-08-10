create index IX_C4AA04E2 on opencps_dossier (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_9525BE4 on opencps_dossier (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F3B1D05E on opencps_dossierfile (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_558ED660 on opencps_dossierfile (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_7C31F3D6 on opencps_dossierlog (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FC3177D8 on opencps_dossierlog (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_5E4D5D55 on opencps_dossierpart (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_ACC73517 on opencps_dossierpart (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3B70A85C on opencps_dossiertemplate (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_167F95DE on opencps_dossiertemplate (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_DBF127F3 on opencps_filetemplate (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F790F35 on opencps_filetemplate (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_50A3049F on opencps_paymentconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C61226E1 on opencps_paymentconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_6BE34299 on opencps_paymentfile (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C65D9B5B on opencps_paymentfile (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_4F240C8E on opencps_serviceconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6FC25E90 on opencps_serviceconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3AF75462 on opencps_serviceinfo (companyId);
create index IX_29AD8B64 on opencps_serviceinfo (groupId);
create index IX_D3675C7A on opencps_serviceinfo (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D630B97C on opencps_serviceinfo (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2F737A41 on opencps_serviceoption (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E4679D03 on opencps_serviceoption (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_22122363 on opencps_services_filetemplates (companyId);
create index IX_DF1604AA on opencps_services_filetemplates (fileTemplateId);
create index IX_6C3BEC69 on opencps_services_filetemplates (serviceInfoId);