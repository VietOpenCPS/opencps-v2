package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.util.Validator;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.serviceconfig.model.ProcessOption;
import org.opencps.api.serviceconfig.model.ServiceConfig;
import org.opencps.api.serviceconfig.model.ServiceConfigDetailModel;
import org.opencps.api.serviceconfig.model.ServiceConfigResultsModel;
import org.opencps.dossiermgt.constants.ProcessOptionTerm;
import org.opencps.dossiermgt.constants.ServiceConfigTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

public class ServiceConfigUtils {
	private static Log _log = LogFactoryUtil.getLog(ServiceConfigUtils.class);
	public static ProcessOption mappingToProcessOption(org.opencps.dossiermgt.model.ProcessOption input) {
		ProcessOption model = new ProcessOption();

		model.setProcessOptionId(GetterUtil.getInteger(input.getPrimaryKey()));
		model.setSeqOrder(GetterUtil.getInteger(input.getOptionOrder()));
		model.setAutoSelect(input.getAutoSelect());
		model.setInstructionNote(input.getInstructionNote());
		model.setSubmissionNote(input.getSubmissionNote());
		model.setDossierTemplateId(GetterUtil.getInteger(input.getDossierTemplateId()));
		model.setOptionName(input.getOptionName());
		
		try {
			DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil
					.getDossierTemplate(input.getDossierTemplateId());
			model.setTemplateNo_0020(dossierTemplate.getTemplateNo());
			model.setTemplateName(dossierTemplate.getTemplateName());
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}

		try {
			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil
					.getServiceProcess(input.getServiceProcessId());
			model.setProcessNo(serviceProcess.getProcessNo());
			model.setProcessName(serviceProcess.getProcessName());
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}

		model.setServiceProcessId(GetterUtil.getInteger(input.getServiceProcessId()));
		
		return model;

	}

	public static List<ProcessOption> mappingToProcessOptionResults(List<Document> documents) {
		List<ProcessOption> processes = new ArrayList<ProcessOption>();

		for (Document doc : documents) {
			ProcessOption processOption = new ProcessOption();
			processOption.setProcessOptionId(GetterUtil.getInteger(doc.get(Field.ENTRY_CLASS_PK)));
			processOption.setSeqOrder(GetterUtil.getInteger(doc.get(ProcessOptionTerm.SEQ_ORDER)));
			processOption.setAutoSelect(doc.get(ProcessOptionTerm.AUTO_SELECT));
			processOption.setInstructionNote(doc.get(ProcessOptionTerm.INSTRUCTION_NOTE));
			processOption.setSubmissionNote(doc.get(ProcessOptionTerm.SUBMISSION_NOTE));
			processOption.setDossierTemplateId(GetterUtil.getInteger(doc.get(ProcessOptionTerm.DOSSIER_TEMPLATEID)));
			processOption.setTemplateNo_0020(doc.get(ProcessOptionTerm.TEMPLATE_NO));
			processOption.setTemplateName(doc.get(ProcessOptionTerm.TEMPLATE_NAME));
			processOption.setServiceProcessId(GetterUtil.getInteger(doc.get(ProcessOptionTerm.SERVICE_PROCESS_ID)));
			processOption.setProcessNo(doc.get(ProcessOptionTerm.PROCESS_NO));
			processOption.setProcessName(doc.get(ProcessOptionTerm.PROCESS_NAME));
			processOption.setOptionName(doc.get(ProcessOptionTerm.OPTION_NAME));
			processOption.setServiceConfigId(GetterUtil.getLong(doc.get(ProcessOptionTerm.SERVICE_CONFIG_ID)));

			processes.add(processOption);
		}

		return processes;
	}

	public static List<ProcessOption> mappingToProcessCitizenBussiness(List<Document> documents, long userId) {
		List<ProcessOption> processes = new ArrayList<ProcessOption>();
		try {
			ApplicantActions actions = new ApplicantActionsImpl();
			Applicant applicant = actions.getApplicantByMappingUserId(userId);
			if (Validator.isNotNull(applicant)) {
				boolean citizen = false;
				boolean business = false;

				if ("citizen".equals(applicant.getApplicantIdType())) {
					citizen = true;
				} else if ("business".equals(applicant.getApplicantIdType())) {
					business = true;
				}
				for (Document doc : documents) {
					boolean active = false;
					boolean forBusness = Boolean.valueOf(doc.get(ProcessOptionTerm.FOR_BUSINESS));
					boolean forCitizen = Boolean.valueOf(doc.get(ProcessOptionTerm.FOR_CITIZEN));
					if (citizen && forCitizen) {
						active = true;
					} else if (business && forBusness) {
						active = true;
					}
					if (active) {
						ProcessOption processOption = new ProcessOption();
						processOption.setProcessOptionId(GetterUtil.getInteger(doc.get(Field.ENTRY_CLASS_PK)));
						processOption.setSeqOrder(GetterUtil.getInteger(doc.get(ProcessOptionTerm.SEQ_ORDER)));
						processOption.setAutoSelect(doc.get(ProcessOptionTerm.AUTO_SELECT));
						processOption.setInstructionNote(doc.get(ProcessOptionTerm.INSTRUCTION_NOTE));
						processOption.setSubmissionNote(doc.get(ProcessOptionTerm.SUBMISSION_NOTE));
						processOption.setDossierTemplateId(GetterUtil.getInteger(doc.get(ProcessOptionTerm.DOSSIER_TEMPLATEID)));
						processOption.setTemplateNo_0020(doc.get(ProcessOptionTerm.TEMPLATE_NO));
						processOption.setTemplateName(doc.get(ProcessOptionTerm.TEMPLATE_NAME));
						processOption.setServiceProcessId(GetterUtil.getInteger(doc.get(ProcessOptionTerm.SERVICE_PROCESS_ID)));
						processOption.setProcessNo(doc.get(ProcessOptionTerm.PROCESS_NO));
						processOption.setProcessName(doc.get(ProcessOptionTerm.PROCESS_NAME));
						processOption.setOptionName(doc.get(ProcessOptionTerm.OPTION_NAME));
						processOption.setServiceConfigId(GetterUtil.getLong(doc.get(ProcessOptionTerm.SERVICE_CONFIG_ID)));

						processes.add(processOption);
					}
				}
			}

		}catch (Exception e){
			e.getMessage();
		}


		return processes;
	}

	public static ServiceConfig mappingServiceConfig(ServiceConfig config, Document doc) {
		try {


			config.setServiceConfigId(GetterUtil.getInteger(doc.get(Field.ENTRY_CLASS_PK)));
			config.setCreateDate(doc.get(Field.CREATE_DATE));
			config.setModifiedDate(doc.get(Field.MODIFIED_DATE));
			config.setServiceInfoId(GetterUtil.getInteger(doc.get(ServiceConfigTerm.SERVICEINFO_ID)));
			config.setServiceCode(doc.get(ServiceConfigTerm.SERVICE_CODE));
			config.setServiceName(doc.get(ServiceConfigTerm.SERVICE_NAME));
			config.setDomainCode_0020(doc.get(ServiceConfigTerm.DOMAIN_CODE));
			config.setDomainName(doc.get(ServiceConfigTerm.DOMAIN_NAME));
			config.setGovAgencyCode(doc.get(ServiceConfigTerm.GOVAGENCY_CODE));
			config.setGovAgencyName(doc.get(ServiceConfigTerm.GOVAGENCY_NAME));
			config.setServiceInstruction(doc.get(ServiceConfigTerm.SERVICE_INSTRUCTION));
			config.setServiceLevel(doc.get(ServiceConfigTerm.SERVICE_LEVEL));
			config.setServiceUrl(doc.get(ServiceConfigTerm.SERVICE_URL));
			config.setForCitizen(doc.get(ServiceConfigTerm.FOR_CITIZEN));
			config.setForBusiness(doc.get(ServiceConfigTerm.FOR_BUSINESS));
			config.setPostalService(doc.get(ServiceConfigTerm.POSTAL_SERVICE));
			config.setRegistration(doc.get(ServiceConfigTerm.REGISTRATION));
		} catch (Exception e) {
			e.getMessage();
		}
		return config;
	}

	public static List<ServiceConfig> mappingToServiceConfigResults(List<Document> documents, long groupId, long userId) {
		List<ServiceConfig> configs = new ArrayList<ServiceConfig>();
		ApplicantActions actions = new ApplicantActionsImpl();
		try {


			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
			Applicant applicant = actions.getApplicantByMappingUserId(userId);
			if (Validator.isNotNull(employee)) {
				for (Document doc : documents) {
					ServiceConfig config = new ServiceConfig();

					config = mappingServiceConfig(config, doc);
					configs.add(config);
				}
			} else {
				boolean citizen = false;
				boolean business = false;
				boolean active = false;
				if (Validator.isNotNull(applicant)) {
					if ("citizen".equals(applicant.getApplicantIdType())) {
						citizen = true;
					} else if ("business".equals(applicant.getApplicantIdType())) {
						business = true;
					}
					for (Document doc : documents) {
						ServiceConfig config = new ServiceConfig();
						long serviceConfigId = Long.parseLong(doc.get(ServiceConfigTerm.SERVICECONFIG_ID));
						List<org.opencps.dossiermgt.model.ProcessOption> lstOption = ProcessOptionLocalServiceUtil.getByServiceConfigId(serviceConfigId);
						if (lstOption != null && !lstOption.isEmpty()) {
							for (org.opencps.dossiermgt.model.ProcessOption option : lstOption) {
								if (citizen && option.isForCitizen()) {
									active = true;
									break;
								} else if (business && option.isForBusiness()) {
									active = true;
									break;
								}
							}
							if (active) {
								config = mappingServiceConfig(config, doc);

								configs.add(config);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return configs;
	}

	public static ServiceConfigDetailModel mapptingToServiceConfig(
			org.opencps.dossiermgt.model.ServiceConfig serviceConfig) {

		ServiceConfigDetailModel model = new ServiceConfigDetailModel();

		model.setServiceConfigId(GetterUtil.getInteger((serviceConfig.getServiceConfigId())));
		model.setGovAgencyCode(serviceConfig.getGovAgencyCode());
		model.setGovAgencyName(serviceConfig.getGovAgencyName());
		model.setServiceInstruction(serviceConfig.getServiceInstruction());
		model.setServiceLevel(String.valueOf(serviceConfig.getServiceLevel()));
		model.setServiceUrl(serviceConfig.getServiceUrl());
		model.setForBusiness(Boolean.toString(serviceConfig.getForBusiness()));
		model.setForCitizen(Boolean.toString(serviceConfig.getForCitizen()));
		model.setPostalService(Boolean.toString(serviceConfig.getPostService()));
		model.setRegistration(Boolean.toString(serviceConfig.getRegistration()));
		model.setCreateDate(String.valueOf(serviceConfig.getCreateDate()));
		model.setModifiedDate(String.valueOf(serviceConfig.getModifiedDate()));
		long serviceInfoId = serviceConfig.getServiceInfoId();
		model.setServiceInfoId(GetterUtil.getInteger(serviceInfoId));

		if (serviceInfoId != 0) {
			try {
				ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.getServiceInfo(serviceInfoId);

				model.setDomainCode_0020(serviceInfo.getDomainCode());
				model.setDomainName(serviceInfo.getDomainName());
				model.setServiceCode(serviceInfo.getServiceCode());
				model.setServiceName(serviceInfo.getServiceName());

			} catch (Exception e) {
				_log.error(e);
			}

		}

		return model;
	}
}
