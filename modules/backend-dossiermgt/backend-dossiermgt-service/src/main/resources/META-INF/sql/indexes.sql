create index IX_29353100 on opencps_actionconfig (groupId, actionCode[$COLUMN_LENGTH:75$]);
create index IX_D5B50DF5 on opencps_actionconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EDA28DB7 on opencps_actionconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_62C6C2A0 on opencps_deliverable (deliverableCode[$COLUMN_LENGTH:75$], deliverableState[$COLUMN_LENGTH:75$]);
create index IX_852F4F48 on opencps_deliverable (deliverableId);
create index IX_8D12CC2D on opencps_deliverable (deliverableState[$COLUMN_LENGTH:75$], govAgencyCode[$COLUMN_LENGTH:75$], deliverableType[$COLUMN_LENGTH:75$], applicantIdNo[$COLUMN_LENGTH:75$]);
create index IX_CBA1DBE2 on opencps_deliverable (groupId, deliverableId);
create index IX_59547196 on opencps_deliverable (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7586598 on opencps_deliverable (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E0B1DAA2 on opencps_deliverablelog (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FCD4A1A4 on opencps_deliverablelog (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_4F093D49 on opencps_deliverabletype (groupId, typeCode[$COLUMN_LENGTH:75$]);
create index IX_A47511D0 on opencps_deliverabletype (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2CB7CC52 on opencps_deliverabletype (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_43862807 on opencps_documenttype (groupId, typeCode[$COLUMN_LENGTH:75$]);
create unique index IX_FF8B0A54 on opencps_documenttype (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A19EE260 on opencps_dossier (dossierNo[$COLUMN_LENGTH:75$], applicantIdNo[$COLUMN_LENGTH:75$]);
create index IX_292E6FC5 on opencps_dossier (groupId, companyId, govAgencyCode[$COLUMN_LENGTH:75$], serviceCode[$COLUMN_LENGTH:75$], dossierTemplateNo[$COLUMN_LENGTH:75$], dossierStatus[$COLUMN_LENGTH:75$]);
create index IX_D4ACCBFA on opencps_dossier (groupId, dossierId);
create index IX_D4AF3B20 on opencps_dossier (groupId, dossierNo[$COLUMN_LENGTH:75$]);
create index IX_A09BF1BA on opencps_dossier (groupId, dossierTemplateNo[$COLUMN_LENGTH:75$]);
create index IX_CE4B5438 on opencps_dossier (groupId, govAgencyCode[$COLUMN_LENGTH:75$], serviceCode[$COLUMN_LENGTH:75$], dossierId);
create index IX_92D12774 on opencps_dossier (groupId, originDossierId);
create index IX_562A1F7A on opencps_dossier (groupId, originality, dossierStatus[$COLUMN_LENGTH:75$], serviceCode[$COLUMN_LENGTH:75$], govAgencyCode[$COLUMN_LENGTH:75$]);
create index IX_13B226B5 on opencps_dossier (groupId, referenceUid[$COLUMN_LENGTH:75$]);
create index IX_C8644976 on opencps_dossier (groupId, serviceCode[$COLUMN_LENGTH:75$]);
create index IX_9A7DEA36 on opencps_dossier (groupId, userId);
create index IX_CE1517AE on opencps_dossier (originality, dossierStatus[$COLUMN_LENGTH:75$]);
create index IX_795482FE on opencps_dossier (userId, groupId, companyId, govAgencyCode[$COLUMN_LENGTH:75$], serviceCode[$COLUMN_LENGTH:75$], dossierTemplateNo[$COLUMN_LENGTH:75$], dossierActionId);
create index IX_7B50F925 on opencps_dossier (userId, groupId, govAgencyCode[$COLUMN_LENGTH:75$], serviceCode[$COLUMN_LENGTH:75$], dossierActionId, originality);
create index IX_C4AA04E2 on opencps_dossier (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_9525BE4 on opencps_dossier (uuid_[$COLUMN_LENGTH:75$], groupId);
create index IX_6DA8BB on opencps_dossier (viaPostal);

create index IX_49AE3BC9 on opencps_dossieraction (dossierId, actionCode[$COLUMN_LENGTH:75$]);
create index IX_FE50C2F2 on opencps_dossieraction (dossierId, fromSequenceNo[$COLUMN_LENGTH:75$]);
create index IX_B1E42E29 on opencps_dossieraction (dossierId, fromStepCode[$COLUMN_LENGTH:75$]);
create index IX_E4D6634A on opencps_dossieraction (dossierId, nextActionId);
create index IX_5637775 on opencps_dossieraction (dossierId, pending);
create index IX_C6FFF0DF on opencps_dossieraction (dossierId, stepCode[$COLUMN_LENGTH:75$]);
create index IX_7A6BCA0C on opencps_dossieraction (dossierId, userId);
create index IX_850662D0 on opencps_dossieraction (groupId, dossierId, fromSequenceNo[$COLUMN_LENGTH:75$]);
create index IX_9252EF06 on opencps_dossieraction (groupId, dossierId, sequenceNo[$COLUMN_LENGTH:75$]);
create index IX_51E985C1 on opencps_dossieraction (groupId, pending);
create index IX_2EFE2E18 on opencps_dossieraction (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_35EB5A9A on opencps_dossieraction (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D7C8B3ED on opencps_dossieractionsync (dossierActionId);
create index IX_BBC9CE33 on opencps_dossieractionsync (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4DC64575 on opencps_dossieractionsync (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A4B3BFF7 on opencps_dossieractionuser (dossierActionId, userId);
create index IX_D10B6FB4 on opencps_dossieractionuser (dossierId, stepCode[$COLUMN_LENGTH:75$]);
create index IX_ECE52C69 on opencps_dossieractionuser (userId);
create index IX_B06993A5 on opencps_dossieractionuser (uuid_[$COLUMN_LENGTH:75$]);

create index IX_7E9B770D on opencps_dossierdocument (dossierId);
create index IX_FCE3302B on opencps_dossierdocument (groupId, dossierActionId);
create index IX_703C7CE on opencps_dossierdocument (groupId, dossierId, referenceUid[$COLUMN_LENGTH:75$]);
create unique index IX_15D0143F on opencps_dossierdocument (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_65A9A912 on opencps_dossierfile (deliverableCode[$COLUMN_LENGTH:75$]);
create index IX_32059C0D on opencps_dossierfile (dossierId, dossierPartNo[$COLUMN_LENGTH:75$], removed);
create index IX_A0D470F4 on opencps_dossierfile (dossierId, dossierPartType, removed);
create index IX_967E0D40 on opencps_dossierfile (dossierId, fileTemplateNo[$COLUMN_LENGTH:75$], dossierPartType, fileEntryId, removed);
create index IX_7FA1EF5F on opencps_dossierfile (dossierId, fileTemplateNo[$COLUMN_LENGTH:75$], dossierPartType, removed);
create index IX_32686D5F on opencps_dossierfile (dossierId, fileTemplateNo[$COLUMN_LENGTH:75$], dossierTemplateNo[$COLUMN_LENGTH:75$]);
create index IX_ECAF32F9 on opencps_dossierfile (dossierId, fileTemplateNo[$COLUMN_LENGTH:75$], removed);
create index IX_4C1D4556 on opencps_dossierfile (dossierId, isNew, removed);
create index IX_AEEE542B on opencps_dossierfile (dossierId, referenceUid[$COLUMN_LENGTH:75$], removed);
create index IX_72D2A0C4 on opencps_dossierfile (dossierId, removed);
create index IX_C7669357 on opencps_dossierfile (fileEntryId);
create index IX_6C6A28B9 on opencps_dossierfile (groupId, referenceUid[$COLUMN_LENGTH:75$]);
create index IX_E3E31523 on opencps_dossierfile (referenceUid[$COLUMN_LENGTH:75$]);
create index IX_F3B1D05E on opencps_dossierfile (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_558ED660 on opencps_dossierfile (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_AD25FC88 on opencps_dossierlog (groupId);
create index IX_653C8C3B on opencps_dossierlog (notificationType[$COLUMN_LENGTH:75$], dossierId);
create index IX_7C31F3D6 on opencps_dossierlog (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FC3177D8 on opencps_dossierlog (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_217E22C8 on opencps_dossiermark (groupId, dossierId, dossierPartNo[$COLUMN_LENGTH:75$]);
create index IX_14BA1164 on opencps_dossiermark (groupId, dossierId, fileMark);
create index IX_F8E80F2F on opencps_dossiermark (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DE389571 on opencps_dossiermark (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B54EC54 on opencps_dossierpart (groupId, fileTemplateNo[$COLUMN_LENGTH:75$]);
create index IX_991D1DC0 on opencps_dossierpart (groupId, templateNo[$COLUMN_LENGTH:75$], partNo[$COLUMN_LENGTH:75$]);
create index IX_72E94D19 on opencps_dossierpart (templateNo[$COLUMN_LENGTH:75$], partNo[$COLUMN_LENGTH:75$], partType, eSign);
create index IX_5E4D5D55 on opencps_dossierpart (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_ACC73517 on opencps_dossierpart (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_73DD95C2 on opencps_dossierrequests (dossierId, isNew);
create index IX_FF9163D5 on opencps_dossierrequests (dossierId, requestType[$COLUMN_LENGTH:75$]);
create index IX_7FF14FD4 on opencps_dossierrequests (isNew);
create index IX_F9F4B2A6 on opencps_dossierrequests (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EA35AAA8 on opencps_dossierrequests (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_37EA9DA7 on opencps_dossierstatistic (groupId, userId, year);
create index IX_97D829E2 on opencps_dossierstatistic (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_ABC5C0E4 on opencps_dossierstatistic (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D2130DEB on opencps_dossiersync (dossierRefUid[$COLUMN_LENGTH:75$], infoType);
create index IX_C5F16ADD on opencps_dossiersync (groupId, actionCode[$COLUMN_LENGTH:75$], syncType, infoType);
create index IX_3CD8A2B3 on opencps_dossiersync (groupId, dossierRefUid[$COLUMN_LENGTH:75$], infoType);
create index IX_886CA32B on opencps_dossiersync (state_);
create unique index IX_27D791BF on opencps_dossiersync (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E5E8009B on opencps_dossiertemplate (groupId, templateName[$COLUMN_LENGTH:500$]);
create unique index IX_7351EDD1 on opencps_dossiertemplate (groupId, templateNo[$COLUMN_LENGTH:75$]);
create index IX_3B70A85C on opencps_dossiertemplate (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_167F95DE on opencps_dossiertemplate (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B07FD197 on opencps_dossieruser (dossierId, userId);
create index IX_CAC462B3 on opencps_dossieruser (userId);
create index IX_86056C1B on opencps_dossieruser (uuid_[$COLUMN_LENGTH:75$]);

create index IX_390906C0 on opencps_menuconfig (groupId);
create index IX_BF5B751A on opencps_menuconfig (menuConfigId);
create index IX_6A14E8A6 on opencps_menuconfig (menuGroup[$COLUMN_LENGTH:75$]);
create index IX_EE2AE89E on opencps_menuconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_5FF0FEA0 on opencps_menuconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_864BDC33 on opencps_menurole (menuConfigId, roleId);
create index IX_FCA73F43 on opencps_menurole (roleId);
create index IX_DA361616 on opencps_menurole (uuid_[$COLUMN_LENGTH:75$]);

create index IX_DEC2C7F1 on opencps_paymentconfig (groupId, govAgencyCode[$COLUMN_LENGTH:75$]);
create index IX_50A3049F on opencps_paymentconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C61226E1 on opencps_paymentconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_AB66566A on opencps_paymentfile (dossierId, referenceUid[$COLUMN_LENGTH:75$]);
create index IX_928D77F1 on opencps_paymentfile (groupId, dossierId);
create index IX_6BE34299 on opencps_paymentfile (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C65D9B5B on opencps_paymentfile (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_11472582 on opencps_process_plugin (stepCode[$COLUMN_LENGTH:75$], serviceProcessId);
create index IX_8A6577C0 on opencps_process_plugin (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BA68EE42 on opencps_process_plugin (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E0EDED53 on opencps_processaction (autoEvent[$COLUMN_LENGTH:75$]);
create index IX_17F0C502 on opencps_processaction (groupId, actionCode[$COLUMN_LENGTH:75$], serviceProcessId);
create index IX_84A534F9 on opencps_processaction (groupId, autoEvent[$COLUMN_LENGTH:75$]);
create index IX_B3115CE1 on opencps_processaction (groupId, serviceProcessId, preStepCode[$COLUMN_LENGTH:75$]);
create index IX_3F5FEA35 on opencps_processaction (postStepCode[$COLUMN_LENGTH:75$], groupId);
create index IX_C791AD26 on opencps_processaction (preStepCode[$COLUMN_LENGTH:75$], groupId);
create index IX_10C9A8BD on opencps_processaction (serviceProcessId, actionCode[$COLUMN_LENGTH:75$], actionName[$COLUMN_LENGTH:75$]);
create index IX_34CDC00 on opencps_processaction (serviceProcessId, preStepCode[$COLUMN_LENGTH:75$], autoEvent[$COLUMN_LENGTH:75$]);
create index IX_EBDC8BC on opencps_processaction (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_39594E3E on opencps_processaction (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_28B30015 on opencps_processoption (serviceConfigId, dossierTemplateId);
create index IX_5B82BB8E on opencps_processoption (serviceConfigId, optionOrder);
create index IX_DF143D7B on opencps_processoption (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C5F3E6BD on opencps_processoption (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E3769E4D on opencps_processsequence (groupId, sequenceNo[$COLUMN_LENGTH:75$]);
create index IX_88235576 on opencps_processsequence (groupId, serviceProcessId, sequenceNo[$COLUMN_LENGTH:75$]);
create index IX_C6D26BC7 on opencps_processsequence (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EBC13809 on opencps_processsequence (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_66A75140 on opencps_processstep (dossierStatus[$COLUMN_LENGTH:75$], dossierSubStatus[$COLUMN_LENGTH:75$], groupId);
create index IX_B9D0A8CB on opencps_processstep (groupId, serviceProcessId, sequenceNo[$COLUMN_LENGTH:75$]);
create index IX_2D87C7C7 on opencps_processstep (serviceProcessId);
create index IX_388C6AC8 on opencps_processstep (stepCode[$COLUMN_LENGTH:75$], groupId, serviceProcessId);
create index IX_55196C94 on opencps_processstep (stepCode[$COLUMN_LENGTH:75$], serviceProcessId);
create index IX_D0400D52 on opencps_processstep (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F6AFC054 on opencps_processstep (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D0F07897 on opencps_processsteprole (processStepId, roleId);
create index IX_30CABA8B on opencps_processsteprole (roleCode[$COLUMN_LENGTH:75$]);
create index IX_134D7D60 on opencps_processsteprole (uuid_[$COLUMN_LENGTH:75$]);

create index IX_E4E0E58C on opencps_registration (applicantIdNo[$COLUMN_LENGTH:75$]);
create index IX_521D957E on opencps_registration (groupId, applicantIdNo[$COLUMN_LENGTH:75$], govAgencyCode[$COLUMN_LENGTH:75$], registrationState);
create index IX_B5AC1550 on opencps_registration (groupId, registrationId);
create index IX_4E58ED22 on opencps_registration (groupId, submitting);
create index IX_F5277266 on opencps_registration (groupId, userId, registrationState);
create index IX_41C6165C on opencps_registration (groupId, userId, submitting);
create index IX_44278B36 on opencps_registration (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_41656738 on opencps_registration (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_1160C3E5 on opencps_registrationform (groupId, registrationId, formNo[$COLUMN_LENGTH:75$]);
create index IX_CDC8C5 on opencps_registrationform (groupId, registrationId, referenceUid[$COLUMN_LENGTH:75$]);
create index IX_561922BF on opencps_registrationform (referenceUid[$COLUMN_LENGTH:75$]);
create index IX_28EFF987 on opencps_registrationform (registrationId, formNo[$COLUMN_LENGTH:75$]);
create index IX_734D61D8 on opencps_registrationform (registrationId, isNew);
create index IX_1AAFAFFA on opencps_registrationform (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1277ECFC on opencps_registrationform (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3D316604 on opencps_registrationlog (groupId, registrationId);
create index IX_ACA4DD02 on opencps_registrationlog (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6C64BC04 on opencps_registrationlog (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_5ADCCA49 on opencps_registrationtemplate (groupId, formNo[$COLUMN_LENGTH:75$], govAgencyCode[$COLUMN_LENGTH:75$]);
create index IX_D7DDCD42 on opencps_registrationtemplate (groupId, govAgencyCode[$COLUMN_LENGTH:75$]);
create index IX_B9CBE330 on opencps_registrationtemplate (groupId, registrationTemplateId);
create unique index IX_22CC0D32 on opencps_registrationtemplate (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_29DADB16 on opencps_serviceconfig (govAgencyCode[$COLUMN_LENGTH:75$]);
create index IX_ABABE3D2 on opencps_serviceconfig (groupId, serviceInfoId, govAgencyCode[$COLUMN_LENGTH:75$]);
create index IX_195F08D3 on opencps_serviceconfig (groupId, serviceLevel);
create index IX_4F240C8E on opencps_serviceconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6FC25E90 on opencps_serviceconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3AF75462 on opencps_serviceinfo (companyId);
create index IX_216AA2FF on opencps_serviceinfo (domainCode[$COLUMN_LENGTH:75$], groupId);
create index IX_29AD8B64 on opencps_serviceinfo (groupId);
create index IX_3E222E7E on opencps_serviceinfo (serviceCode[$COLUMN_LENGTH:75$], groupId);
create index IX_D3675C7A on opencps_serviceinfo (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D630B97C on opencps_serviceinfo (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_55C498C3 on opencps_serviceprocess (groupId, processName[$COLUMN_LENGTH:255$]);
create index IX_CE466FF9 on opencps_serviceprocess (groupId, processNo[$COLUMN_LENGTH:75$]);
create index IX_996BC77F on opencps_serviceprocess (groupId, serverNo[$COLUMN_LENGTH:75$]);
create index IX_79BCECBB on opencps_serviceprocess (serverNo[$COLUMN_LENGTH:75$]);
create index IX_D84A5557 on opencps_serviceprocess (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8A464599 on opencps_serviceprocess (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_645BCD10 on opencps_serviceprocessrole (roleCode[$COLUMN_LENGTH:75$]);
create index IX_8F9EE2C2 on opencps_serviceprocessrole (serviceProcessId);
create index IX_645000FB on opencps_serviceprocessrole (uuid_[$COLUMN_LENGTH:75$]);

create index IX_6C3BEC69 on opencps_services_filetemplates (serviceInfoId);
create index IX_71CB1E6F on opencps_services_filetemplates (uuid_[$COLUMN_LENGTH:75$]);

create index IX_5E562335 on opencps_stepconfig (groupId, dossierStatus[$COLUMN_LENGTH:75$], dossierSubStatus[$COLUMN_LENGTH:75$]);
create index IX_9154D4C0 on opencps_stepconfig (groupId, stepCode[$COLUMN_LENGTH:75$]);
create index IX_C7F6BBCB on opencps_stepconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BB01B90D on opencps_stepconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_9BD8108 on opencps_userinfolog (userId);
create index IX_24F4AF26 on opencps_userinfolog (uuid_[$COLUMN_LENGTH:75$]);