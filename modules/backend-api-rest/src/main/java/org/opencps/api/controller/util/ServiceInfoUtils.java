package org.opencps.api.controller.util;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.liferay.portal.kernel.util.Validator;
import org.opencps.api.serviceinfo.model.FileTemplateModel;
import org.opencps.api.serviceinfo.model.FileTemplates;
import org.opencps.api.serviceinfo.model.ServiceInfoDetailModel;
import org.opencps.api.serviceinfo.model.ServiceInfoInputModel;
import org.opencps.api.serviceinfo.model.ServiceInfoModel;
import org.opencps.api.serviceinfo.model.ServiceInfoServiceConfig;
import org.opencps.api.serviceinfo.model.ServiceRecentDetailModel;
import org.opencps.dossiermgt.action.ServiceConfigActions;
import org.opencps.dossiermgt.action.impl.ServiceConfigActionImpl;
import org.opencps.dossiermgt.constants.ServiceConfigTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.impl.ServiceInfoImpl;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

public class ServiceInfoUtils {

	@SuppressWarnings("unchecked")
	public static List<ServiceInfoModel> mappingToServiceInfoResultModel(List<Document> documents, long
			groupId, long userId, boolean filterApplicant,
			ServiceContext serviceContext) {
		List<ServiceInfoModel> data = new ArrayList<ServiceInfoModel>();

		try {
			for (Document doc : documents) {
				ServiceInfoModel model = new ServiceInfoModel();

				model.setServiceName(doc.get(ServiceInfoTerm.SERVICE_NAME));
				model.setServiceInfoId(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
				model.setCreateDate(doc.get(Field.CREATE_DATE));
				model.setModifiedDate(doc.get(Field.MODIFIED_DATE));
				model.setServiceCode(doc.get(ServiceInfoTerm.SERVICE_CODE));
				model.setServiceCodeDVCQG(doc.get(ServiceInfoTerm.SERVICE_CODE_DVCQG));
				model.setServiceNameDVCQG(doc.get(ServiceInfoTerm.SERVICE_NAME_DVCQG));
				model.setMappingClassPK(GetterUtil.getLong(doc.get(ServiceInfoTerm.MAPPING_CLASSPK)));
				model.setServiceName(doc.get(ServiceInfoTerm.SERVICE_NAME));
				model.setProcessText(doc.get(ServiceInfoTerm.PROCESS_TEXT));
				model.setMethodText(doc.get(ServiceInfoTerm.METHOD_TEXT));
				model.setDossierText(doc.get(ServiceInfoTerm.DOSSIER_EXT));
				model.setConditionText(doc.get(ServiceInfoTerm.CONDITION_TEXT));
				model.setDurationText(doc.get(ServiceInfoTerm.DURATION_TEXT));
				model.setApplicantText(doc.get(ServiceInfoTerm.APPLICANT_TEXT));
				model.setResultText(doc.get(ServiceInfoTerm.RESULT_TEXT));
				model.setRegularText(doc.get(ServiceInfoTerm.REGULAR_TEXT));
				model.setFeeText(doc.get(ServiceInfoTerm.FEE_TEXT));
				model.setAdministrationCode(doc.get(ServiceInfoTerm.ADMINISTRATION_CODE));
				model.setAdministrationName(doc.get(ServiceInfoTerm.ADMINISTRATION_NAME));
				model.setDomainCode(doc.get(ServiceInfoTerm.DOMAIN_CODE));
				model.setDomainName(doc.get(ServiceInfoTerm.DOMAIN_NAME));
				model.setMaxLevel(GetterUtil.getInteger(doc.get(ServiceInfoTerm.MAX_LEVEL)));
				model.setSynced(GetterUtil.getInteger(doc.get(ServiceInfoTerm.SYNCED)));
				//model.setPublic(doc.get(ServiceInfoTerm.PUBLIC_));
				model.setActive(doc.get(ServiceInfoTerm.PUBLIC_));
				model.setTagCode(doc.get(ServiceInfoTerm.TAGCODE));
				model.setTagName(doc.get(ServiceInfoTerm.TAGNAME));

				List<ServiceInfoServiceConfig> lsServiceConfig = new ArrayList<ServiceInfoServiceConfig>();

				//ServiceConfigActions serviceConfigActions = new ServiceConfigActionImpl();

				LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

				params.put(Field.GROUP_ID, String.valueOf(doc.get(Field.GROUP_ID)));

				params.put(ServiceConfigTerm.SERVICE_CODE, doc.get(ServiceInfoTerm.SERVICE_CODE));

//				Sort[] sorts = new Sort[] {
//						SortFactoryUtil.create("_sortable", Sort.STRING_TYPE, Boolean.valueOf(StringPool.BLANK)) };

//				JSONObject jsonData = serviceConfigActions.getServiceConfigs(serviceContext.getUserId(),
//						serviceContext.getCompanyId(), Long.parseLong(doc.get(Field.GROUP_ID)), params, sorts,
//						QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);

				List<ServiceConfig> lstScs = ServiceConfigLocalServiceUtil.getByServiceInfo(Long.valueOf(doc.get(Field.GROUP_ID)), GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
				
//				List<Document> serviceConfigs = (List<Document>) jsonData.get("data");
				ApplicantActions actions = new ApplicantActionsImpl();
				Applicant applicant = actions.getApplicantByMappingUserId(userId);
				if (Validator.isNotNull(applicant)) {
					boolean citizen = false;
					boolean business = false;
					boolean active = false;
					if ("citizen".equals(applicant.getApplicantIdType())) {
						citizen = true;
					} else if ("business".equals(applicant.getApplicantIdType())) {
						business = true;
					}
					for (ServiceConfig sc : lstScs) {
						ServiceInfoServiceConfig cf = new ServiceInfoServiceConfig();
						List<ProcessOption> lstOption = ProcessOptionLocalServiceUtil.getByServiceConfigId(sc.getServiceConfigId());
						if (lstOption != null && !lstOption.isEmpty()) {
							for (ProcessOption option : lstOption) {
								if (citizen && option.isForCitizen()) {
									active = true;
									break;
								} else if (business && option.isForBusiness()) {
									active = true;
									break;
								}
							}
							if (active) {
								cf.setGovAgencyCode(sc.getGovAgencyCode());
								cf.setGovAgencyName(sc.getGovAgencyName());
								cf.setServiceInstruction(sc.getServiceInstruction());
								cf.setServiceUr(sc.getServiceUrl());
								cf.setServiceLevel(sc.getServiceLevel());
								cf.setServiceConfigId(sc.getServiceConfigId());

								lsServiceConfig.add(cf);
							}
						}
					}

				}else {
					for (ServiceConfig sc : lstScs) {
						ServiceInfoServiceConfig cf = new ServiceInfoServiceConfig();
						cf.setGovAgencyCode(sc.getGovAgencyCode());
						cf.setGovAgencyName(sc.getGovAgencyName());
						cf.setServiceInstruction(sc.getServiceInstruction());
						cf.setServiceUr(sc.getServiceUrl());
						cf.setServiceLevel(sc.getServiceLevel());
						cf.setServiceConfigId(sc.getServiceConfigId());

						lsServiceConfig.add(cf);
					}
				}
//				for (Document serviceConfig : serviceConfigs) {
//					ServiceInfoServiceConfig cf = new ServiceInfoServiceConfig();
	//
//					cf.setGovAgencyCode(serviceConfig.get(ServiceConfigTerm.GOVAGENCY_CODE));
//					cf.setGovAgencyName(serviceConfig.get(ServiceConfigTerm.GOVAGENCY_NAME));
//					cf.setServiceInstruction(serviceConfig.get(ServiceConfigTerm.SERVICE_INSTRUCTION));
//					cf.setServiceUr(serviceConfig.get(ServiceConfigTerm.SERVICE_URL));
//					cf.setServiceLevel(Integer.parseInt(serviceConfig.get(ServiceConfigTerm.SERVICE_LEVEL)));
//					cf.setServiceConfigId(GetterUtil.getLong(serviceConfig.get(Field.ENTRY_CLASS_PK)));
//					
//					lsServiceConfig.add(cf);
//				}
				
				model.getServiceConfigs().addAll(lsServiceConfig);
				if(filterApplicant && model.getServiceConfigs().size() >0) {
					data.add(model);
				}else if(!filterApplicant){
					data.add(model);
				}

				data.add(model);
			}
		} catch (Exception e) {
			_log.error(e);
		}
		

		return data;
	}

	public static List<ServiceInfo> mappingToServiceInfoResultModels(List<Document> documents, long groupId) {
		List<ServiceInfo> data = new ArrayList<ServiceInfo>();

		try {
			for (Document doc : documents) {
				ServiceInfo model = new ServiceInfoImpl();

				model.setServiceName(doc.get(ServiceInfoTerm.SERVICE_NAME));
				model.setServiceInfoId(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
//				model.setCreateDate(doc.get(Field.CREATE_DATE));
//				model.setModifiedDate(doc.get(Field.MODIFIED_DATE));
				model.setServiceCode(doc.get(ServiceInfoTerm.SERVICE_CODE));
				model.setServiceName(doc.get(ServiceInfoTerm.SERVICE_NAME));
				model.setProcessText(doc.get(ServiceInfoTerm.PROCESS_TEXT));
				model.setMethodText(doc.get(ServiceInfoTerm.METHOD_TEXT));
				model.setDossierText(doc.get(ServiceInfoTerm.DOSSIER_EXT));
				model.setConditionText(doc.get(ServiceInfoTerm.CONDITION_TEXT));
				model.setDurationText(doc.get(ServiceInfoTerm.DURATION_TEXT));
				model.setApplicantText(doc.get(ServiceInfoTerm.APPLICANT_TEXT));
				model.setResultText(doc.get(ServiceInfoTerm.RESULT_TEXT));
				model.setRegularText(doc.get(ServiceInfoTerm.REGULAR_TEXT));
				model.setFeeText(doc.get(ServiceInfoTerm.FEE_TEXT));
				model.setAdministrationCode(doc.get(ServiceInfoTerm.ADMINISTRATION_CODE));
				model.setAdministrationName(doc.get(ServiceInfoTerm.ADMINISTRATION_NAME));
				model.setDomainCode(doc.get(ServiceInfoTerm.DOMAIN_CODE));
				model.setDomainName(doc.get(ServiceInfoTerm.DOMAIN_NAME));
				model.setMaxLevel(GetterUtil.getInteger(doc.get(ServiceInfoTerm.MAX_LEVEL)));

				data.add(model);
			}
		} catch (Exception e) {
			_log.error(e);
		}


		return data;
	}

	public static ServiceInfoInputModel mappingToServiceInfoInputModel(ServiceInfo serviceInfo) {
		ServiceInfoInputModel model = new ServiceInfoInputModel();

		model.setServiceName(serviceInfo.getServiceName());
		model.setServiceInfoId(serviceInfo.getServiceInfoId());
		model.setServiceCode(serviceInfo.getServiceCode());
		model.setServiceName(serviceInfo.getServiceName());
		model.setProcessText(serviceInfo.getProcessText());
		model.setMethodText(serviceInfo.getMethodText());
		model.setDossierText(serviceInfo.getDossierText());
		model.setConditionText(serviceInfo.getConditionText());
		model.setDurationText(serviceInfo.getDurationText());
		model.setApplicantText(serviceInfo.getApplicantText());
		model.setRegularText(serviceInfo.getRegularText());
		model.setResultText(serviceInfo.getResultText());
		model.setFeeText(serviceInfo.getFeeText());
		model.setAdministrationCode(serviceInfo.getAdministrationCode());
		model.setAdministrationName(serviceInfo.getAdministrationName());
		model.setDomainCode(serviceInfo.getDomainCode());
		model.setDomainName(serviceInfo.getDomainName());
		model.setMaxLevel(serviceInfo.getMaxLevel());
		model.setActive(Boolean.toString(serviceInfo.getPublic_()));
		model.setGovAgencyText(serviceInfo.getGovAgencyText());
		model.setTagCode(serviceInfo.getTagCode());
		model.setTagName(serviceInfo.getTagName());

		return model;
	}

	public static ServiceInfoDetailModel mappingToServiceInfoDetailModel(ServiceInfo serviceInfo) {

		ServiceInfoDetailModel model = new ServiceInfoDetailModel();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		
		model.setServiceInfoId(serviceInfo.getServiceInfoId());
		model.setServiceName(serviceInfo.getServiceName());
		model.setServiceInfoId(serviceInfo.getServiceInfoId());
		model.setModifiedDate(sdf.format(serviceInfo.getModifiedDate()));
		model.setCreateDate(sdf.format(serviceInfo.getCreateDate()));
		model.setServiceCode(serviceInfo.getServiceCode());
		model.setServiceName(serviceInfo.getServiceName());
		model.setProcessText(serviceInfo.getProcessText());
		model.setMethodText(serviceInfo.getMethodText());
		model.setDossierText(serviceInfo.getDossierText());
		model.setConditionText(serviceInfo.getConditionText());
		model.setDurationText(serviceInfo.getDurationText());
		model.setApplicantText(serviceInfo.getApplicantText());
		model.setRegularText(serviceInfo.getRegularText());
		model.setResultText(serviceInfo.getResultText());
		model.setFeeText(serviceInfo.getFeeText());
		model.setAdministrationCode(serviceInfo.getAdministrationCode());
		model.setAdministrationName(serviceInfo.getAdministrationName());
		model.setDomainCode(serviceInfo.getDomainCode());
		model.setDomainName(serviceInfo.getDomainName());
		model.setMaxLevel(serviceInfo.getMaxLevel());
		model.setActive(Boolean.toString(serviceInfo.getPublic_()));
		model.setGovAgencyText(serviceInfo.getGovAgencyText());
		model.setIsNotarization(serviceInfo.getIsNotarization());
		
		List<ServiceFileTemplate> serviceFileTemplates = ServiceFileTemplateLocalServiceUtil
				.getByServiceInfoId(serviceInfo.getServiceInfoId());
		
		
		List<ServiceConfig> configList = ServiceConfigLocalServiceUtil.getByServiceInfo(serviceInfo.getGroupId(),
				serviceInfo.getServiceInfoId());
		
		List<ServiceInfoServiceConfig> lsServiceConfig = new ArrayList<ServiceInfoServiceConfig>();
		if (configList != null && configList.size() > 0) {
			ServiceInfoServiceConfig cf = null;
			for (ServiceConfig serviceConfig : configList) {
				cf = new ServiceInfoServiceConfig();

				cf.setServiceConfigId(serviceConfig.getServiceConfigId());
				cf.setGovAgencyCode(serviceConfig.getGovAgencyCode());
				cf.setGovAgencyName(serviceConfig.getGovAgencyName());
				cf.setServiceInstruction(serviceConfig.getServiceInstruction());
				cf.setServiceUr(serviceConfig.getServiceUrl());
				cf.setServiceLevel(serviceConfig.getServiceLevel());

			lsServiceConfig.add(cf);
			}
		}

		model.getServiceConfigs().addAll(lsServiceConfig);
		model.getFileTemplates().addAll(mappingToFileTemplates(serviceFileTemplates));

		return model;
	}

	public static List<FileTemplates> mappingToFileTemplates(List<ServiceFileTemplate> serviceFileTemplates) {
		List<FileTemplates> fileTemplates = new ArrayList<FileTemplates>();

		if (serviceFileTemplates != null && serviceFileTemplates.size() > 0) {
			for (ServiceFileTemplate sft : serviceFileTemplates) {
				FileTemplates fileTemplate = new FileTemplates();

				fileTemplate.setTemplateName(sft.getTemplateName());

				if (sft.getFileEntryId() != 0) {
					try {
						FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(sft.getFileEntryId());

						fileTemplate.setFileSize(GetterUtil.getInteger(fileEntry.getSize()));
						fileTemplate.setFileType(fileEntry.getExtension());
						fileTemplate.setFileTemplateNo(sft.getFileTemplateNo());
						fileTemplate.setTemplateName(sft.getTemplateName());

					} catch (Exception e) {
						_log.debug(e);
						_log.error("Can't get ServiceFileTemplate");
					}
				}

				fileTemplate.seteForm(sft.getEForm());
				fileTemplate.setFormScriptFileId(sft.getFormScriptFileId());
				fileTemplate.setFormReportFileId(sft.getFormReportFileId());
				fileTemplate.seteFormNoPattern(sft.getEFormNoPattern());
				fileTemplate.seteFormNamePattern(sft.getEFormNamePattern());
				
				fileTemplates.add(fileTemplate);
			}
		}

		return fileTemplates;
	}

	public static FileTemplateModel mappingToFileTemplateModel(ServiceFileTemplate serviceFileTemplate) {

		FileTemplateModel fileTemplate = new FileTemplateModel();

		fileTemplate.setTemplateName(serviceFileTemplate.getTemplateName());
		fileTemplate.setFileTemplateNo(serviceFileTemplate.getFileTemplateNo());

		if (serviceFileTemplate.getFileEntryId() != 0) {

			try {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(serviceFileTemplate.getFileEntryId());

				fileTemplate.setFileSize(GetterUtil.getInteger(fileEntry.getSize()));
				fileTemplate.setFileType(fileEntry.getExtension());

			} catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
				_log.error("Can't get ServiceFileTemplate");
			}
		}

		return fileTemplate;
	}

	public static ServiceRecentDetailModel mappingToServiceRecentDetailModel(ServiceInfo serviceInfo) {

		ServiceRecentDetailModel model = new ServiceRecentDetailModel();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		
		model.setServiceInfoId(serviceInfo.getServiceInfoId());
		model.setServiceName(serviceInfo.getServiceName());
		model.setServiceInfoId(serviceInfo.getServiceInfoId());
		model.setModifiedDate(sdf.format(serviceInfo.getModifiedDate()));
		model.setCreateDate(sdf.format(serviceInfo.getCreateDate()));
		model.setServiceCode(serviceInfo.getServiceCode());
		model.setServiceName(serviceInfo.getServiceName());
		model.setProcessText(serviceInfo.getProcessText());
		model.setMethodText(serviceInfo.getMethodText());
		model.setDossierText(serviceInfo.getDossierText());
		model.setConditionText(serviceInfo.getConditionText());
		model.setDurationText(serviceInfo.getDurationText());
		model.setApplicantText(serviceInfo.getApplicantText());
		model.setRegularText(serviceInfo.getRegularText());
		model.setResultText(serviceInfo.getResultText());
		model.setFeeText(serviceInfo.getFeeText());
		model.setAdministrationCode(serviceInfo.getAdministrationCode());
		model.setAdministrationName(serviceInfo.getAdministrationName());
		model.setDomainCode(serviceInfo.getDomainCode());
		model.setDomainName(serviceInfo.getDomainName());
		model.setMaxLevel(serviceInfo.getMaxLevel());
		model.setActive(Boolean.toString(serviceInfo.getPublic_()));
		model.setGovAgencyText(serviceInfo.getGovAgencyText());

		List<ServiceFileTemplate> serviceFileTemplates = ServiceFileTemplateLocalServiceUtil
				.getByServiceInfoId(serviceInfo.getServiceInfoId());
		
		
		List<ServiceConfig> configList = ServiceConfigLocalServiceUtil.getByServiceInfo(serviceInfo.getGroupId(),
				serviceInfo.getServiceInfoId());
		
		List<ServiceInfoServiceConfig> lsServiceConfig = new ArrayList<ServiceInfoServiceConfig>();
		if (configList != null && configList.size() > 0) {
			ServiceInfoServiceConfig cf = null;
			for (ServiceConfig serviceConfig : configList) {
				cf = new ServiceInfoServiceConfig();

				cf.setServiceConfigId(serviceConfig.getServiceConfigId());
				cf.setGovAgencyCode(serviceConfig.getGovAgencyCode());
				cf.setGovAgencyName(serviceConfig.getGovAgencyName());
				cf.setServiceInstruction(serviceConfig.getServiceInstruction());
				cf.setServiceUr(serviceConfig.getServiceUrl());
				cf.setServiceLevel(serviceConfig.getServiceLevel());

			lsServiceConfig.add(cf);
			}
		}

		model.getServiceConfigs().addAll(lsServiceConfig);
		model.getFileTemplates().addAll(mappingToFileTemplates(serviceFileTemplates));

		return model;
	}

	private static Log _log = LogFactoryUtil.getLog(ServiceInfoUtils.class);
}
