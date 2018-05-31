package org.opencps.api.controller.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.api.serviceinfo.model.FileTemplateModel;
import org.opencps.api.serviceinfo.model.FileTemplates;
import org.opencps.api.serviceinfo.model.ServiceInfoDetailModel;
import org.opencps.api.serviceinfo.model.ServiceInfoInputModel;
import org.opencps.api.serviceinfo.model.ServiceInfoModel;
import org.opencps.api.serviceinfo.model.ServiceInfoServiceConfig;
import org.opencps.dossiermgt.action.ServiceConfigActions;
import org.opencps.dossiermgt.action.impl.ServiceConfigActionImpl;
import org.opencps.dossiermgt.constants.ServiceConfigTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

public class ServiceInfoUtils {

	public static List<ServiceInfoModel> mappingToServiceInfoResultModel(List<Document> documents,
			ServiceContext serviceContext) {
		List<ServiceInfoModel> data = new ArrayList<ServiceInfoModel>();

		for (Document doc : documents) {
			ServiceInfoModel model = new ServiceInfoModel();

			model.setServiceName(doc.get(ServiceInfoTerm.SERVICE_NAME));
			model.setServiceInfoId(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
			model.setCreateDate(doc.get(Field.CREATE_DATE));
			model.setModifiedDate(doc.get(Field.MODIFIED_DATE));
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
			//model.setPublic(doc.get(ServiceInfoTerm.PUBLIC_));
			model.setActive(doc.get(ServiceInfoTerm.PUBLIC_));
			
			List<ServiceInfoServiceConfig> lsServiceConfig = new ArrayList<ServiceInfoServiceConfig>();

			ServiceConfigActions serviceConfigActions = new ServiceConfigActionImpl();

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(doc.get(Field.GROUP_ID)));

			params.put(ServiceConfigTerm.SERVICE_CODE, doc.get(ServiceInfoTerm.SERVICE_CODE));

			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create("_sortable", Sort.STRING_TYPE, Boolean.valueOf(StringPool.BLANK)) };

			JSONObject jsonData = serviceConfigActions.getServiceConfigs(serviceContext.getUserId(),
					serviceContext.getCompanyId(), Long.parseLong(doc.get(Field.GROUP_ID)), params, sorts,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);

			List<Document> serviceConfigs = (List<Document>) jsonData.get("data");

			for (Document serviceConfig : serviceConfigs) {
				ServiceInfoServiceConfig cf = new ServiceInfoServiceConfig();

				cf.setGovAgencyCode(serviceConfig.get(ServiceConfigTerm.GOVAGENCY_CODE));
				cf.setGovAgencyName(serviceConfig.get(ServiceConfigTerm.GOVAGENCY_NAME));
				cf.setServiceInstruction(serviceConfig.get(ServiceConfigTerm.SERVICE_INSTRUCTION));
				cf.setServiceUr(serviceConfig.get(ServiceConfigTerm.SERVICE_URL));
				cf.setServiceLevel(Integer.parseInt(serviceConfig.get(ServiceConfigTerm.SERVICE_LEVEL)));

				lsServiceConfig.add(cf);
			}
			
			model.getServiceConfigs().addAll(lsServiceConfig);
			
			data.add(model);
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

		return model;
	}

	public static ServiceInfoDetailModel mappingToServiceInfoDetailModel(ServiceInfo serviceInfo,
			ServiceContext serviceContext) {

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
		
		List<ServiceInfoServiceConfig> lsServiceConfig = new ArrayList<ServiceInfoServiceConfig>();

		List<ServiceFileTemplate> serviceFileTemplates = ServiceFileTemplateLocalServiceUtil
				.getByServiceInfoId(serviceInfo.getServiceInfoId());
		
		ServiceConfigActions serviceConfigActions = new ServiceConfigActionImpl();

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		params.put(Field.GROUP_ID, String.valueOf(serviceInfo.getGroupId()));

		params.put(ServiceConfigTerm.SERVICE_CODE, serviceInfo.getServiceCode());

		Sort[] sorts = new Sort[] {
				SortFactoryUtil.create("_sortable", Sort.STRING_TYPE, Boolean.valueOf(StringPool.BLANK)) };

		JSONObject jsonData = serviceConfigActions.getServiceConfigs(serviceContext.getUserId(),
				serviceContext.getCompanyId(), serviceInfo.getGroupId(), params, sorts,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);

		List<Document> serviceConfigs = (List<Document>) jsonData.get("data");

		for (Document serviceConfig : serviceConfigs) {
			ServiceInfoServiceConfig cf = new ServiceInfoServiceConfig();

			cf.setGovAgencyCode(serviceConfig.get(ServiceConfigTerm.GOVAGENCY_CODE));
			cf.setGovAgencyName(serviceConfig.get(ServiceConfigTerm.GOVAGENCY_NAME));
			cf.setServiceInstruction(serviceConfig.get(ServiceConfigTerm.SERVICE_INSTRUCTION));
			cf.setServiceUr(serviceConfig.get(ServiceConfigTerm.SERVICE_URL));
			cf.setServiceLevel(Integer.parseInt(serviceConfig.get(ServiceConfigTerm.SERVICE_LEVEL)));

			lsServiceConfig.add(cf);
		}
		
		model.getServiceConfigs().addAll(lsServiceConfig);
		model.getFileTemplates().addAll(mappingToFileTemplates(serviceFileTemplates));

		return model;
	}

	public static List<FileTemplates> mappingToFileTemplates(List<ServiceFileTemplate> serviceFileTemplates) {
		List<FileTemplates> fileTemplates = new ArrayList<FileTemplates>();

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
					_log.error("Can't get ServiceFileTemplate");
				}
			}
			fileTemplates.add(fileTemplate);
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
				_log.error("Can't get ServiceFileTemplate");
			}
		}

		return fileTemplate;
	}

	private static Log _log = LogFactoryUtil.getLog(ServiceInfoUtils.class);
}
