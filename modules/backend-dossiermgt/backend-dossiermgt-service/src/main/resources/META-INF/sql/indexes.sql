create index IX_435DDDE1 on opencps_accesstoken (groupId, className[$COLUMN_LENGTH:255$]);

create index IX_29353100 on opencps_actionconfig (groupId, actionCode[$COLUMN_LENGTH:255$]);
create index IX_1833407B on opencps_actionconfig (groupId, eventType);
create index IX_D5B50DF5 on opencps_actionconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EDA28DB7 on opencps_actionconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_362B3AF5 on opencps_applicableInfo (groupId, serviceCode[$COLUMN_LENGTH:75$], govAgencyCode[$COLUMN_LENGTH:75$], serviceLevel);
create index IX_938871C on opencps_applicableInfo (serviceConfigMappingId);
create index IX_F494E0AA on opencps_applicableInfo (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FC5189AC on opencps_applicableInfo (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_96163B06 on opencps_booking (codeNumber[$COLUMN_LENGTH:255$]);
create index IX_35D7C193 on opencps_booking (groupId, bookingDate, online_);
create index IX_68310EDE on opencps_booking (groupId, className[$COLUMN_LENGTH:255$], classPK);
create index IX_D55B8904 on opencps_booking (groupId, serviceCode[$COLUMN_LENGTH:128$]);
create index IX_915B3B70 on opencps_booking (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F25BDDF2 on opencps_booking (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F420D66C on opencps_configcounter (groupId, counterCode[$COLUMN_LENGTH:255$]);
create index IX_6442A351 on opencps_configcounter (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EA0DCA13 on opencps_configcounter (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_62C6C2A0 on opencps_deliverable (deliverableCode[$COLUMN_LENGTH:128$], deliverableState);
create index IX_8D12CC2D on opencps_deliverable (deliverableState, govAgencyCode[$COLUMN_LENGTH:128$], deliverableType[$COLUMN_LENGTH:255$], applicantIdNo[$COLUMN_LENGTH:128$]);
create index IX_B5D727C6 on opencps_deliverable (groupId, applicantIdNo[$COLUMN_LENGTH:128$]);
create index IX_6113CEF4 on opencps_deliverable (groupId, deliverableCode[$COLUMN_LENGTH:128$]);
create index IX_CBA1DBE2 on opencps_deliverable (groupId, deliverableId);
create index IX_E86EDBAE on opencps_deliverable (groupId, dossierId);
create index IX_59547196 on opencps_deliverable (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7586598 on opencps_deliverable (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F87561BC on opencps_deliverablelog (deliverableId);
create index IX_E0B1DAA2 on opencps_deliverablelog (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FCD4A1A4 on opencps_deliverablelog (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_4DB953CC on opencps_deliverabletype (companyId);
create index IX_4F093D49 on opencps_deliverabletype (groupId, typeCode[$COLUMN_LENGTH:128$]);
create index IX_A47511D0 on opencps_deliverabletype (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2CB7CC52 on opencps_deliverabletype (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_1F0A1772 on opencps_deliverabletyperole (deliverableTypeId);
create index IX_A059E5D1 on opencps_deliverabletyperole (groupId, deliverableTypeId, roleId);
create index IX_396D0046 on opencps_deliverabletyperole (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8A4E048 on opencps_deliverabletyperole (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_43862807 on opencps_documenttype (groupId, typeCode[$COLUMN_LENGTH:255$]);
create unique index IX_FF8B0A54 on opencps_documenttype (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_9905611E on opencps_dossier (documentNo[$COLUMN_LENGTH:255$]);
create index IX_93AD8453 on opencps_dossier (dossierCounter[$COLUMN_LENGTH:128$]);
create index IX_A19EE260 on opencps_dossier (dossierNo[$COLUMN_LENGTH:255$], applicantIdNo[$COLUMN_LENGTH:128$]);
create index IX_3EE7C4FC on opencps_dossier (dossierNo[$COLUMN_LENGTH:255$], groupId);
create index IX_BC4A37EA on opencps_dossier (dossierStatus[$COLUMN_LENGTH:255$], modifiedDate);
create index IX_6C6803BB on opencps_dossier (groupId, applicantIdNo[$COLUMN_LENGTH:128$], dossierStatus[$COLUMN_LENGTH:255$]);
create index IX_6E05E1CA on opencps_dossier (groupId, applicantIdNo[$COLUMN_LENGTH:128$], serviceCode[$COLUMN_LENGTH:128$], govAgencyCode[$COLUMN_LENGTH:128$], dossierTemplateNo[$COLUMN_LENGTH:128$], originDossierId);
create index IX_292E6FC5 on opencps_dossier (groupId, companyId, govAgencyCode[$COLUMN_LENGTH:128$], serviceCode[$COLUMN_LENGTH:128$], dossierTemplateNo[$COLUMN_LENGTH:128$], dossierStatus[$COLUMN_LENGTH:255$]);
create index IX_D4ACCBFA on opencps_dossier (groupId, dossierId);
create index IX_D4AF3B20 on opencps_dossier (groupId, dossierNo[$COLUMN_LENGTH:255$]);
create index IX_5769E47A on opencps_dossier (groupId, dossierStatus[$COLUMN_LENGTH:255$], originality, dossierTemplateNo[$COLUMN_LENGTH:128$]);
create index IX_1BC51B84 on opencps_dossier (groupId, dossierStatus[$COLUMN_LENGTH:255$], originality, processNo[$COLUMN_LENGTH:128$]);
create index IX_624F4C36 on opencps_dossier (groupId, dossierStatus[$COLUMN_LENGTH:255$], originality, serviceCode[$COLUMN_LENGTH:128$]);
create index IX_A09BF1BA on opencps_dossier (groupId, dossierTemplateNo[$COLUMN_LENGTH:128$]);
create index IX_CE4B5438 on opencps_dossier (groupId, govAgencyCode[$COLUMN_LENGTH:128$], serviceCode[$COLUMN_LENGTH:128$], dossierId);
create index IX_4935E3DB on opencps_dossier (groupId, govAgencyCode[$COLUMN_LENGTH:128$], serviceCode[$COLUMN_LENGTH:128$], dossierTemplateNo[$COLUMN_LENGTH:128$], dossierStatus[$COLUMN_LENGTH:255$], applicantIdType[$COLUMN_LENGTH:128$], originality);
create index IX_699FDB37 on opencps_dossier (groupId, groupDossierId[$COLUMN_LENGTH:75$]);
create index IX_92D12774 on opencps_dossier (groupId, originDossierId);
create index IX_92D3969A on opencps_dossier (groupId, originDossierNo[$COLUMN_LENGTH:255$]);
create index IX_562A1F7A on opencps_dossier (groupId, originality, dossierStatus[$COLUMN_LENGTH:255$], serviceCode[$COLUMN_LENGTH:128$], govAgencyCode[$COLUMN_LENGTH:128$]);
create index IX_217868C4 on opencps_dossier (groupId, processNo[$COLUMN_LENGTH:128$]);
create index IX_13B226B5 on opencps_dossier (groupId, referenceUid[$COLUMN_LENGTH:255$]);
create index IX_C8644976 on opencps_dossier (groupId, serviceCode[$COLUMN_LENGTH:128$]);
create index IX_FB4E5F17 on opencps_dossier (groupId, userId, dossierStatus[$COLUMN_LENGTH:255$]);
create index IX_5FB90585 on opencps_dossier (groupId, vnpostalStatus);
create index IX_C5B31468 on opencps_dossier (originDossierNo[$COLUMN_LENGTH:255$]);
create index IX_CE1517AE on opencps_dossier (originality, dossierStatus[$COLUMN_LENGTH:255$]);
create index IX_A2D94A0F on opencps_dossier (postalCodeReceived[$COLUMN_LENGTH:75$], groupId);
create index IX_542C5208 on opencps_dossier (postalCodeSend[$COLUMN_LENGTH:75$], groupId);
create index IX_7B50F925 on opencps_dossier (userId, groupId, govAgencyCode[$COLUMN_LENGTH:128$], serviceCode[$COLUMN_LENGTH:128$], dossierActionId, originality);
create index IX_50872872 on opencps_dossier (userId, groupId, govAgencyCode[$COLUMN_LENGTH:128$], serviceCode[$COLUMN_LENGTH:128$], dossierTemplateNo[$COLUMN_LENGTH:128$], dossierStatus[$COLUMN_LENGTH:255$], originality);
create index IX_C4AA04E2 on opencps_dossier (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_9525BE4 on opencps_dossier (uuid_[$COLUMN_LENGTH:75$], groupId);
create index IX_6DA8BB on opencps_dossier (viaPostal);

create index IX_49AE3BC9 on opencps_dossieraction (dossierId, actionCode[$COLUMN_LENGTH:100$]);
create index IX_FE50C2F2 on opencps_dossieraction (dossierId, fromSequenceNo[$COLUMN_LENGTH:255$]);
create index IX_66DCCD03 on opencps_dossieraction (dossierId, fromStepCode[$COLUMN_LENGTH:255$], dossierActionId);
create index IX_E4D6634A on opencps_dossieraction (dossierId, nextActionId);
create index IX_5637775 on opencps_dossieraction (dossierId, pending);
create index IX_18340D0D on opencps_dossieraction (dossierId, stepCode[$COLUMN_LENGTH:255$], dossierActionId);
create index IX_D1FD26E3 on opencps_dossieraction (dossierId, userId, fromStepCode[$COLUMN_LENGTH:255$]);
create index IX_8965699 on opencps_dossieraction (dossierId, userId, stepCode[$COLUMN_LENGTH:255$]);
create index IX_AD14846 on opencps_dossieraction (dueDate, nextActionId);
create index IX_850662D0 on opencps_dossieraction (groupId, dossierId, fromSequenceNo[$COLUMN_LENGTH:255$]);
create index IX_9252EF06 on opencps_dossieraction (groupId, dossierId, sequenceNo[$COLUMN_LENGTH:255$]);
create index IX_51E985C1 on opencps_dossieraction (groupId, pending);
create index IX_2EFE2E18 on opencps_dossieraction (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_35EB5A9A on opencps_dossieraction (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D7C8B3ED on opencps_dossieractionsync (dossierActionId);
create index IX_BBC9CE33 on opencps_dossieractionsync (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4DC64575 on opencps_dossieractionsync (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_662474BD on opencps_dossieractionuser (dossierActionId);
create index IX_607DAE14 on opencps_dossieractionuser (dossierId, dossierActionId, stepCode[$COLUMN_LENGTH:255$], assigned);
create index IX_D187820C on opencps_dossieractionuser (dossierId, dossierActionId, userId, stepCode[$COLUMN_LENGTH:255$]);
create index IX_D10B6FB4 on opencps_dossieractionuser (dossierId, stepCode[$COLUMN_LENGTH:255$]);
create index IX_E334306E on opencps_dossieractionuser (dossierId, userId, stepCode[$COLUMN_LENGTH:255$]);
create index IX_ECE52C69 on opencps_dossieractionuser (userId);
create index IX_B06993A5 on opencps_dossieractionuser (uuid_[$COLUMN_LENGTH:75$]);

create index IX_7E9B770D on opencps_dossierdocument (dossierId);
create index IX_FCE3302B on opencps_dossierdocument (groupId, dossierActionId);
create index IX_D80E15BE on opencps_dossierdocument (groupId, dossierId, documentType[$COLUMN_LENGTH:255$]);
create index IX_703C7CE on opencps_dossierdocument (groupId, dossierId, referenceUid[$COLUMN_LENGTH:75$]);
create unique index IX_15D0143F on opencps_dossierdocument (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_65A9A912 on opencps_dossierfile (deliverableCode[$COLUMN_LENGTH:255$]);
create index IX_32059C0D on opencps_dossierfile (dossierId, dossierPartNo[$COLUMN_LENGTH:255$], removed);
create index IX_A0D470F4 on opencps_dossierfile (dossierId, dossierPartType, removed);
create index IX_967E0D40 on opencps_dossierfile (dossierId, fileTemplateNo[$COLUMN_LENGTH:255$], dossierPartType, fileEntryId, removed);
create index IX_7FA1EF5F on opencps_dossierfile (dossierId, fileTemplateNo[$COLUMN_LENGTH:255$], dossierPartType, removed);
create index IX_32686D5F on opencps_dossierfile (dossierId, fileTemplateNo[$COLUMN_LENGTH:255$], dossierTemplateNo[$COLUMN_LENGTH:255$]);
create index IX_ECAF32F9 on opencps_dossierfile (dossierId, fileTemplateNo[$COLUMN_LENGTH:255$], removed);
create index IX_4C1D4556 on opencps_dossierfile (dossierId, isNew, removed);
create index IX_AEEE542B on opencps_dossierfile (dossierId, referenceUid[$COLUMN_LENGTH:75$], removed);
create index IX_72D2A0C4 on opencps_dossierfile (dossierId, removed);
create index IX_C7669357 on opencps_dossierfile (fileEntryId);
create index IX_9D20E3F0 on opencps_dossierfile (groupId, dossierId, dossierPartNo[$COLUMN_LENGTH:255$], eForm, removed);
create index IX_AEED7A26 on opencps_dossierfile (groupId, dossierId, dossierTemplateNo[$COLUMN_LENGTH:255$], dossierPartNo[$COLUMN_LENGTH:255$], eForm, removed);
create index IX_F9DB3FD4 on opencps_dossierfile (groupId, dossierId, fileTemplateNo[$COLUMN_LENGTH:255$], removed, original);
create index IX_EF5A17DF on opencps_dossierfile (groupId, dossierId, removed, original);
create index IX_6C6A28B9 on opencps_dossierfile (groupId, referenceUid[$COLUMN_LENGTH:75$]);
create index IX_E3E31523 on opencps_dossierfile (referenceUid[$COLUMN_LENGTH:75$]);
create index IX_F3B1D05E on opencps_dossierfile (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_558ED660 on opencps_dossierfile (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_AD25FC88 on opencps_dossierlog (groupId);
create index IX_653C8C3B on opencps_dossierlog (notificationType[$COLUMN_LENGTH:200$], dossierId);
create index IX_7C31F3D6 on opencps_dossierlog (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FC3177D8 on opencps_dossierlog (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_217E22C8 on opencps_dossiermark (groupId, dossierId, dossierPartNo[$COLUMN_LENGTH:255$]);
create index IX_1031790A on opencps_dossiermark (groupId, dossierId, fileMark, recordCount[$COLUMN_LENGTH:512$]);
create index IX_F8E80F2F on opencps_dossiermark (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DE389571 on opencps_dossiermark (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B54EC54 on opencps_dossierpart (groupId, fileTemplateNo[$COLUMN_LENGTH:255$]);
create index IX_7715C063 on opencps_dossierpart (groupId, templateNo[$COLUMN_LENGTH:255$], fileTemplateNo[$COLUMN_LENGTH:255$]);
create index IX_991D1DC0 on opencps_dossierpart (groupId, templateNo[$COLUMN_LENGTH:255$], partNo[$COLUMN_LENGTH:255$]);
create index IX_72E94D19 on opencps_dossierpart (templateNo[$COLUMN_LENGTH:255$], partNo[$COLUMN_LENGTH:255$], partType, eSign);
create index IX_5E4D5D55 on opencps_dossierpart (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_ACC73517 on opencps_dossierpart (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_73DD95C2 on opencps_dossierrequests (dossierId, isNew);
create index IX_FF9163D5 on opencps_dossierrequests (dossierId, requestType[$COLUMN_LENGTH:128$]);
create index IX_7FF14FD4 on opencps_dossierrequests (isNew);
create index IX_F9F4B2A6 on opencps_dossierrequests (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EA35AAA8 on opencps_dossierrequests (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B951EA5 on opencps_dossierstatistic (groupId, month, year);
create index IX_37EA9DA7 on opencps_dossierstatistic (groupId, userId, year);
create index IX_97D829E2 on opencps_dossierstatistic (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_ABC5C0E4 on opencps_dossierstatistic (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E8CF21E3 on opencps_dossierstatusmapping (groupId, statusCode[$COLUMN_LENGTH:255$]);
create index IX_DA0AB528 on opencps_dossierstatusmapping (groupId, statusCodeDVCQG[$COLUMN_LENGTH:128$]);
create index IX_46BD81F on opencps_dossierstatusmapping (groupId, subStatusCode[$COLUMN_LENGTH:128$]);

create index IX_C5F16ADD on opencps_dossiersync (groupId, actionCode[$COLUMN_LENGTH:128$], syncType, infoType);
create index IX_78FE34CE on opencps_dossiersync (groupId, dossierId, dossierActionId, actionCode[$COLUMN_LENGTH:128$]);
create index IX_A9312537 on opencps_dossiersync (groupId, dossierId, state_);
create index IX_3CD8A2B3 on opencps_dossiersync (groupId, dossierRefUid[$COLUMN_LENGTH:128$], infoType);
create index IX_886CA32B on opencps_dossiersync (state_);
create unique index IX_27D791BF on opencps_dossiersync (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_7351EDD1 on opencps_dossiertemplate (groupId, templateNo[$COLUMN_LENGTH:255$]);
create index IX_3B70A85C on opencps_dossiertemplate (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_167F95DE on opencps_dossiertemplate (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_AD6C265D on opencps_dossieruser (dossierId);
create index IX_CAC462B3 on opencps_dossieruser (userId);
create index IX_86056C1B on opencps_dossieruser (uuid_[$COLUMN_LENGTH:75$]);

create index IX_20618BC on opencps_eform (groupId, eFormNo[$COLUMN_LENGTH:128$]);
create index IX_C7E6F694 on opencps_eform (groupId, serviceCode[$COLUMN_LENGTH:128$]);
create index IX_855BF100 on opencps_eform (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D72CB782 on opencps_eform (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_4CA0C550 on opencps_menuconfig (groupId, menuGroup[$COLUMN_LENGTH:255$]);
create index IX_6A14E8A6 on opencps_menuconfig (menuGroup[$COLUMN_LENGTH:255$]);
create index IX_EE2AE89E on opencps_menuconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_5FF0FEA0 on opencps_menuconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_864BDC33 on opencps_menurole (menuConfigId, roleId);
create index IX_FCA73F43 on opencps_menurole (roleId);
create index IX_DA361616 on opencps_menurole (uuid_[$COLUMN_LENGTH:75$]);

create index IX_DF045F14 on opencps_newsboard (groupId);
create index IX_398BAECA on opencps_newsboard (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_96E55FCC on opencps_newsboard (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_636A0EF5 on opencps_notarization (groupId, dossierId);

create index IX_DEC2C7F1 on opencps_paymentconfig (groupId, govAgencyCode[$COLUMN_LENGTH:128$]);
create index IX_1C8F6A99 on opencps_paymentconfig (groupId, invoiceTemplateNo[$COLUMN_LENGTH:32767$]);
create index IX_50A3049F on opencps_paymentconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C61226E1 on opencps_paymentconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_53CC0B5D on opencps_paymentfeeinfo (serviceConfigMappingId);
create index IX_42CE9AB on opencps_paymentfeeinfo (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C0A9DEED on opencps_paymentfeeinfo (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_AB66566A on opencps_paymentfile (dossierId, referenceUid[$COLUMN_LENGTH:75$]);
create index IX_928D77F1 on opencps_paymentfile (groupId, dossierId);
create index IX_8EF25743 on opencps_paymentfile (groupId, paymentStatus);
create index IX_51CA8DC3 on opencps_paymentfile (paymentStatus);
create index IX_6BE34299 on opencps_paymentfile (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C65D9B5B on opencps_paymentfile (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_BB72AF29 on opencps_postconnect (groupId, syncState);
create index IX_31CF29C0 on opencps_postconnect (orderNumber[$COLUMN_LENGTH:75$], postStatus);
create index IX_5E22119 on opencps_postconnect (syncState);
create index IX_42CAC661 on opencps_postconnect (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C7637123 on opencps_postconnect (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_11472582 on opencps_process_plugin (stepCode[$COLUMN_LENGTH:128$], serviceProcessId);
create index IX_8A6577C0 on opencps_process_plugin (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BA68EE42 on opencps_process_plugin (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E0EDED53 on opencps_processaction (autoEvent[$COLUMN_LENGTH:255$]);
create index IX_17F0C502 on opencps_processaction (groupId, actionCode[$COLUMN_LENGTH:255$], serviceProcessId);
create index IX_84A534F9 on opencps_processaction (groupId, autoEvent[$COLUMN_LENGTH:255$]);
create index IX_6689E997 on opencps_processaction (groupId, serviceProcessId, actionCode[$COLUMN_LENGTH:255$], preStepCode[$COLUMN_LENGTH:255$], postStepCode[$COLUMN_LENGTH:255$]);
create index IX_77A1E779 on opencps_processaction (groupId, serviceProcessId, preStepCode[$COLUMN_LENGTH:255$], createDossiers[$COLUMN_LENGTH:255$]);
create index IX_3F5FEA35 on opencps_processaction (postStepCode[$COLUMN_LENGTH:255$], groupId);
create index IX_C791AD26 on opencps_processaction (preStepCode[$COLUMN_LENGTH:255$], groupId);
create index IX_10C9A8BD on opencps_processaction (serviceProcessId, actionCode[$COLUMN_LENGTH:255$], actionName[$COLUMN_LENGTH:255$]);
create index IX_9E61E6B2 on opencps_processaction (serviceProcessId, preStepCode[$COLUMN_LENGTH:255$], actionCode[$COLUMN_LENGTH:255$]);
create index IX_34CDC00 on opencps_processaction (serviceProcessId, preStepCode[$COLUMN_LENGTH:255$], autoEvent[$COLUMN_LENGTH:255$]);
create index IX_EBDC8BC on opencps_processaction (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_39594E3E on opencps_processaction (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D8C5543 on opencps_processoption (groupId);
create index IX_28B30015 on opencps_processoption (serviceConfigId, dossierTemplateId);
create index IX_5B82BB8E on opencps_processoption (serviceConfigId, optionOrder);
create index IX_9D238C20 on opencps_processoption (serviceProcessId, dossierTemplateId);
create index IX_DF143D7B on opencps_processoption (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C5F3E6BD on opencps_processoption (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E3769E4D on opencps_processsequence (groupId, sequenceNo[$COLUMN_LENGTH:255$]);
create index IX_88235576 on opencps_processsequence (groupId, serviceProcessId, sequenceNo[$COLUMN_LENGTH:255$]);
create index IX_C6D26BC7 on opencps_processsequence (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EBC13809 on opencps_processsequence (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_66A75140 on opencps_processstep (dossierStatus[$COLUMN_LENGTH:255$], dossierSubStatus[$COLUMN_LENGTH:255$], groupId);
create index IX_5562AF73 on opencps_processstep (groupId, serviceProcessId, dossierStatus[$COLUMN_LENGTH:255$], dossierSubStatus[$COLUMN_LENGTH:255$]);
create index IX_B9D0A8CB on opencps_processstep (groupId, serviceProcessId, sequenceNo[$COLUMN_LENGTH:255$]);
create index IX_D7A5B642 on opencps_processstep (groupId, serviceProcessId, stepCode[$COLUMN_LENGTH:255$]);
create index IX_2D87C7C7 on opencps_processstep (serviceProcessId);
create index IX_388C6AC8 on opencps_processstep (stepCode[$COLUMN_LENGTH:255$], groupId, serviceProcessId);
create index IX_55196C94 on opencps_processstep (stepCode[$COLUMN_LENGTH:255$], serviceProcessId);
create index IX_D0400D52 on opencps_processstep (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F6AFC054 on opencps_processstep (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_85322B32 on opencps_processsteprole (processStepId);
create index IX_30CABA8B on opencps_processsteprole (roleCode[$COLUMN_LENGTH:255$]);
create index IX_134D7D60 on opencps_processsteprole (uuid_[$COLUMN_LENGTH:75$]);

create index IX_3EBC1A0E on opencps_publish_queue (groupId, dossierId, serverNo[$COLUMN_LENGTH:255$], status);
create index IX_62B041F5 on opencps_publish_queue (status, modifiedDate);
create unique index IX_6E7DABBA on opencps_publish_queue (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E4E0E58C on opencps_registration (applicantIdNo[$COLUMN_LENGTH:128$]);
create index IX_521D957E on opencps_registration (groupId, applicantIdNo[$COLUMN_LENGTH:128$], govAgencyCode[$COLUMN_LENGTH:255$], registrationState);
create index IX_B5AC1550 on opencps_registration (groupId, registrationId);
create index IX_4E58ED22 on opencps_registration (groupId, submitting);
create index IX_F5277266 on opencps_registration (groupId, userId, registrationState);
create index IX_41C6165C on opencps_registration (groupId, userId, submitting);
create index IX_44278B36 on opencps_registration (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_41656738 on opencps_registration (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_1160C3E5 on opencps_registrationform (groupId, registrationId, formNo[$COLUMN_LENGTH:128$]);
create index IX_CDC8C5 on opencps_registrationform (groupId, registrationId, referenceUid[$COLUMN_LENGTH:75$]);
create index IX_561922BF on opencps_registrationform (referenceUid[$COLUMN_LENGTH:75$]);
create index IX_28EFF987 on opencps_registrationform (registrationId, formNo[$COLUMN_LENGTH:128$]);
create index IX_734D61D8 on opencps_registrationform (registrationId, isNew);
create index IX_1AAFAFFA on opencps_registrationform (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1277ECFC on opencps_registrationform (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3D316604 on opencps_registrationlog (groupId, registrationId);
create index IX_ACA4DD02 on opencps_registrationlog (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6C64BC04 on opencps_registrationlog (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_5ADCCA49 on opencps_registrationtemplate (groupId, formNo[$COLUMN_LENGTH:128$], govAgencyCode[$COLUMN_LENGTH:128$]);
create index IX_D7DDCD42 on opencps_registrationtemplate (groupId, govAgencyCode[$COLUMN_LENGTH:128$]);
create index IX_B9CBE330 on opencps_registrationtemplate (groupId, registrationTemplateId);
create unique index IX_22CC0D32 on opencps_registrationtemplate (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_29DADB16 on opencps_serviceconfig (govAgencyCode[$COLUMN_LENGTH:128$]);
create index IX_E713E0A0 on opencps_serviceconfig (groupId, govAgencyCode[$COLUMN_LENGTH:128$]);
create index IX_ABABE3D2 on opencps_serviceconfig (groupId, serviceInfoId, govAgencyCode[$COLUMN_LENGTH:128$]);
create index IX_1275B97B on opencps_serviceconfig (groupId, serviceLevel, serviceInfoId);
create index IX_4F240C8E on opencps_serviceconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6FC25E90 on opencps_serviceconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_80005EC8 on opencps_serviceconfigmapping (groupId, serviceCode[$COLUMN_LENGTH:75$]);
create index IX_6399334 on opencps_serviceconfigmapping (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_57B556B6 on opencps_serviceconfigmapping (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3AF75462 on opencps_serviceinfo (companyId);
create index IX_216AA2FF on opencps_serviceinfo (domainCode[$COLUMN_LENGTH:128$], groupId);
create index IX_FCA7408C on opencps_serviceinfo (groupId, public_, serviceInfoId);
create index IX_2FC150E on opencps_serviceinfo (groupId, serviceCode[$COLUMN_LENGTH:128$]);
create index IX_3E222E7E on opencps_serviceinfo (serviceCode[$COLUMN_LENGTH:128$], groupId);
create index IX_D3675C7A on opencps_serviceinfo (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D630B97C on opencps_serviceinfo (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_ADAA235C on opencps_serviceinfomapping (groupId, serviceCode[$COLUMN_LENGTH:128$]);
create index IX_6962B74F on opencps_serviceinfomapping (groupId, serviceCodeDVCQG[$COLUMN_LENGTH:128$]);

create index IX_CE466FF9 on opencps_serviceprocess (groupId, processNo[$COLUMN_LENGTH:255$]);
create index IX_996BC77F on opencps_serviceprocess (groupId, serverNo[$COLUMN_LENGTH:255$]);
create index IX_79BCECBB on opencps_serviceprocess (serverNo[$COLUMN_LENGTH:255$]);
create index IX_D84A5557 on opencps_serviceprocess (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8A464599 on opencps_serviceprocess (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_645BCD10 on opencps_serviceprocessrole (roleCode[$COLUMN_LENGTH:255$]);
create index IX_B5CAB0FE on opencps_serviceprocessrole (roleId);
create index IX_8F9EE2C2 on opencps_serviceprocessrole (serviceProcessId);
create index IX_645000FB on opencps_serviceprocessrole (uuid_[$COLUMN_LENGTH:75$]);

create index IX_C0C5CAB0 on opencps_services_filetemplates (serviceInfoId, eForm);
create index IX_71CB1E6F on opencps_services_filetemplates (uuid_[$COLUMN_LENGTH:75$]);

create index IX_5E562335 on opencps_stepconfig (groupId, dossierStatus[$COLUMN_LENGTH:128$], dossierSubStatus[$COLUMN_LENGTH:128$]);
create index IX_9154D4C0 on opencps_stepconfig (groupId, stepCode[$COLUMN_LENGTH:128$]);
create index IX_C7F6BBCB on opencps_stepconfig (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BB01B90D on opencps_stepconfig (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_9BD8108 on opencps_userinfolog (userId);
create index IX_24F4AF26 on opencps_userinfolog (uuid_[$COLUMN_LENGTH:75$]);