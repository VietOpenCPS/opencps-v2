package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.serviceinfo.model.FileTemplateModel;
import org.opencps.api.serviceinfo.model.FileTemplateResultModel;
import org.opencps.dossiermgt.model.ServiceFileTemplate;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class ServiceFileTemplateUtils {
	public static List<FileTemplateResultModel> getFileTemplateResultsModel(List<ServiceFileTemplate> serviceFileTemplates) {
		List<FileTemplateResultModel> fileTemplateResultsModel = new ArrayList<FileTemplateResultModel>();

		try {
			for (ServiceFileTemplate serviceFileTemplate : serviceFileTemplates) {

				FileTemplateResultModel fileTemplateResultModel = new FileTemplateResultModel();
				
				fileTemplateResultModel.setFileTemplateNo(serviceFileTemplate.getFileTemplateNo());
				fileTemplateResultModel.setTemplateName(serviceFileTemplate.getTemplateName());
				
				fileTemplateResultsModel.add(fileTemplateResultModel);
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return fileTemplateResultsModel;
	}
	
	public static FileTemplateModel getFileTemplateModel(ServiceFileTemplate serviceFileTemplate) {
		FileTemplateModel fileTemplateModel = new FileTemplateModel();
		
		fileTemplateModel.setFileTemplateNo(serviceFileTemplate.getFileTemplateNo());
		fileTemplateModel.setTemplateName(serviceFileTemplate.getTemplateName());
		
		return fileTemplateModel;
	}
	
	private static Log _log = LogFactoryUtil.getLog(ServiceFileTemplateUtils.class);
}
